package com.retailers.dht.common.service.impl;
import java.util.Currency;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.SingleThreadLockConstant;
import com.retailers.dht.common.constant.SmsSendRecordConstant;
import com.retailers.dht.common.constant.SysParameterConfigConstant;
import com.retailers.dht.common.enm.SmsSendRecordEnum;
import com.retailers.dht.common.entity.SmsSendRecord;
import com.retailers.dht.common.dao.SmsSendRecordMapper;
import com.retailers.dht.common.service.ProcedureToolsService;
import com.retailers.dht.common.service.SmsSendRecordService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：短信发送列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 00:38:54
 */
@Service("smssendrecordService")
public class SmsSendRecordServiceImpl implements SmsSendRecordService {
	Logger logger= LoggerFactory.getLogger(SmsSendRecordServiceImpl.class);

	@Autowired
	private SmsSendRecordMapper smsSendRecordMapper;
	@Autowired
	private ProcedureToolsService procedureToolsService;

	public boolean saveSmsSendRecord(SmsSendRecord smsSendRecord) {
		int status = smsSendRecordMapper.saveSmsSendRecord(smsSendRecord);
		return status == 1 ? true : false;
	}
	public boolean updateSmsSendRecord(SmsSendRecord smsSendRecord) {
		int status = smsSendRecordMapper.updateSmsSendRecord(smsSendRecord);
		return status == 1 ? true : false;
	}
	public SmsSendRecord querySmsSendRecordById(Long id) {
		return smsSendRecordMapper.querySmsSendRecordById(id);
	}

	public Pagination<SmsSendRecord> querySmsSendRecordList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<SmsSendRecord> page = new Pagination<SmsSendRecord>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SmsSendRecord> list = smsSendRecordMapper.querySmsSendRecordList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteSmsSendRecordById(Long id) {
		int status = smsSendRecordMapper.deleteSmsSendRecordById(id);
		return status == 1 ? true : false;
	}

	public boolean sendSmsCode(Long uid, String phone, long type) throws AppException {
		logger.info("开始发送短信，发送用户id:[{}],接收短信手机号码：[{}],短信类型:[{}]",uid,phone,type);
		Date curDate= new Date();
		String code= StringUtils.randomNumberCode(6);
		String key=phone;
		if(ObjectUtils.isNotEmpty(uid)){
			key=uid+"";
		}
		String lockKey= StringUtils.formate(SingleThreadLockConstant.SEND_MSG_CODE,key);
		//添加同步线程锁
		procedureToolsService.singleLockManager(lockKey);
		try{
			//判断发送短信间隔
			int total = smsSendRecordMapper.checkSendSms(uid,phone,type,curDate);
			if(total>0){
				throw new AppException("短信发送过于频繁，请稍后再试");
			}
			//失效时间
			Date onceSendDate= DateUtil.addMinute(curDate, SysParameterConfigConstant.getValue(SysParameterConfigConstant.SMS_VALID_CODE_INTERVAL_TIME,Integer.class));
			//失效时间
			Date expireDate= DateUtil.addMinute(curDate, SysParameterConfigConstant.getValue(SysParameterConfigConstant.SMS_VALID_CODE_VALID_TIME,Integer.class));
			SmsSendRecord ssr=new SmsSendRecord();
			ssr.setUid(uid);
			String context=SmsSendRecordEnum.getSmsSend(type).getContext();
			context=StringUtils.formates(context,code);
			ssr.setContext(context);
			ssr.setPhone(phone);
			ssr.setType((int)type);
			ssr.setCreateDate(curDate);
			ssr.setExpireDate(expireDate);
			ssr.setOnceSendDate(onceSendDate);
			ssr.setStatus(SmsSendRecordConstant.SMS_SEND_STAUTS_UN_USE);
			ssr.setCode(code);
			smsSendRecordMapper.saveSmsSendRecord(ssr);
		}finally {
			logger.info("发送短信结束，发送用户id:[{}],接收短信手机号码：[{}],短信类型:[{}],执行时间:[{}],生成验证码：[{}]",uid,phone,type,(System.currentTimeMillis()-curDate.getTime()),code);
			procedureToolsService.singleUnLockManager(lockKey);
		}
		return true;
	}
}

