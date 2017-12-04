
package com.retailers.aliyun.sms.service.impl;

import com.alibaba.fastjson.JSON;
import com.retailers.aliyun.sms.config.AppConfig;
import com.retailers.aliyun.sms.constant.SmsSendRecordConstant;
import com.retailers.aliyun.sms.dao.AliyunSmsTemplateMapper;
import com.retailers.aliyun.sms.dao.SmsSendRecordMapper;
import com.retailers.aliyun.sms.enm.SmsSendRecordEnum;
import com.retailers.aliyun.sms.entity.AliyunSmsTemplate;
import com.retailers.aliyun.sms.entity.SmsSendRecord;
import com.retailers.aliyun.sms.service.AliyunSmsTemplateService;
import com.retailers.aliyun.sms.service.SmsSendRecordService;
import com.retailers.mybatis.common.constant.SingleThreadLockConstant;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：短信发送列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-04 15:43:07
 */
@Service("smssendrecordService")
public class SmsSendRecordServiceImpl implements SmsSendRecordService {
	Logger logger = LoggerFactory.getLogger(SmsSendRecordServiceImpl.class);
	@Autowired
	private SmsSendRecordMapper smsSendRecordMapper;
	@Autowired
	private ProcedureToolsService procedureToolsService;
	@Autowired
	private AliyunSmsTemplateMapper aliyunSmsTemplateMapper;
	@Autowired
	private AliyunSmsTemplateService aliyunSmsTemplateService;

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

	/**
	 * 发送短信
	 * @param uid 用户id
	 * @param phone 电话号码
	 * @param type 发送短信类型（0 绑定短信，1 密码找回短信，2 解除绑定短信）
	 * @return
	 * @throws AppException
	 */
	public SmsSendRecord sendSmsCode(Long uid, String phone, long type) throws AppException {
		Map<String, String> params=new HashMap<String, String>();
		String code="666666";
		if(!AppConfig.IS_DEBUG){
			code= StringUtils.randomNumberCode(6);
		}
		params.put("code",code);
		return sendSmsCode(uid,phone,type,params);
	}

	public SmsSendRecord sendSmsCode(Long uid, String phone, long type, Map<String, String> params) throws AppException {
		logger.info("开始发送短信，发送用户id:[{}],接收短信手机号码：[{}],短信类型:[{}]",uid,phone,type);
		//根据短信类型 取得短信模版
		String funCode= SmsSendRecordEnum.getSmsSend(type).getFunCode();
		AliyunSmsTemplate aliyunSmsTemplate=aliyunSmsTemplateMapper.queryAliyunSmsTemplateByFunCode(funCode);
		if(ObjectUtils.isEmpty(aliyunSmsTemplate)){
			throw new AppException("短信模版不存在");
		}
		Date curDate= new Date();
		String key=phone;
		if(ObjectUtils.isNotEmpty(uid)){
			key=uid+"";
		}
		String lockKey= StringUtils.formate(SingleThreadLockConstant.SEND_MSG_CODE,key);
		//添加同步线程锁
		procedureToolsService.singleLockManager(lockKey);
		SmsSendRecord ssr;
		try{
			//判断发送短信间隔
			int total = smsSendRecordMapper.checkSendSms(uid,phone,type,curDate);
			if(total>0){
				throw new AppException("短信发送过于频繁，请稍后再试");
			}
			//失效时间
			Date onceSendDate= DateUtil.addMinute(curDate,1);
			//失效时间
			Date expireDate= DateUtil.addMinute(curDate, 1);
			ssr=new SmsSendRecord();
			ssr.setUid(uid);
			ssr.setContext(aliyunSmsTemplate.getContext());
			ssr.setPhone(phone);
			ssr.setType((int)type);
			ssr.setCreateDate(curDate);
			ssr.setExpireDate(expireDate);
			ssr.setOnceSendDate(onceSendDate);
			ssr.setStatus(SmsSendRecordConstant.SMS_SEND_STAUTS_UN_USE);
			ssr.setCode(params.get("code"));
			ssr.setParams(JSON.toJSONString(params));
			smsSendRecordMapper.saveSmsSendRecord(ssr);
			//判断是否是
			if(!AppConfig.IS_DEBUG){
				aliyunSmsTemplateService.sendSMS(phone,aliyunSmsTemplate,params,ssr.getId());
			}
		}finally {
			logger.info("发送短信结束，发送用户id:[{}],接收短信手机号码：[{}],短信类型:[{}],执行时间:[{}],生成验证码：[{}]",uid,phone,type,(System.currentTimeMillis()-curDate.getTime()),params.get("code"));
			procedureToolsService.singleUnLockManager(lockKey);
		}
		return ssr;
	}

	public SmsSendRecord queryCurSmsSendRecordByPhone(String phone, int type, String code, Date curDate) {
		SmsSendRecord sendRecord = smsSendRecordMapper.queryCurSmsSendRecordByPhone(phone,type,code,curDate);
		return sendRecord;
	}

	public int checkSendSms(Long uid, String phone, long type, Date curDate) {
		return smsSendRecordMapper.checkSendSms(uid,phone,type,curDate);
	}
}

