
package com.retailers.aliyun.sms.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.retailers.aliyun.sms.SmsUtils;
import com.retailers.aliyun.sms.dao.AliyunSmsTemplateMapper;
import com.retailers.aliyun.sms.dao.SmsSendRecordMapper;
import com.retailers.aliyun.sms.entity.AliyunSmsTemplate;
import com.retailers.aliyun.sms.entity.SmsSendRecord;
import com.retailers.aliyun.sms.service.AliyunSmsTemplateService;
import com.retailers.tools.base.AsyncCallback;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：阿里云短信模版管理Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-04 15:05:09
 */
@Service("aliyunsmstemplateService")
public class AliyunSmsTemplateServiceImpl implements AliyunSmsTemplateService {
	@Autowired
	private AliyunSmsTemplateMapper aliyunSmsTemplateMapper;
	@Autowired
	private SmsSendRecordMapper smsSendRecordMapper;
	public boolean saveAliyunSmsTemplate(AliyunSmsTemplate aliyunSmsTemplate) {
		int status = aliyunSmsTemplateMapper.saveAliyunSmsTemplate(aliyunSmsTemplate);
		return status == 1 ? true : false;
	}
	public boolean updateAliyunSmsTemplate(AliyunSmsTemplate aliyunSmsTemplate) {
		int status = aliyunSmsTemplateMapper.updateAliyunSmsTemplate(aliyunSmsTemplate);
		return status == 1 ? true : false;
	}
	public AliyunSmsTemplate queryAliyunSmsTemplateById(Long id) {
		return aliyunSmsTemplateMapper.queryAliyunSmsTemplateById(id);
	}

	public Pagination<AliyunSmsTemplate> queryAliyunSmsTemplateList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<AliyunSmsTemplate> page = new Pagination<AliyunSmsTemplate>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<AliyunSmsTemplate> list = aliyunSmsTemplateMapper.queryAliyunSmsTemplateList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteAliyunSmsTemplateById(Long id) {
		int status = aliyunSmsTemplateMapper.deleteAliyunSmsTemplateById(id);
		return status == 1 ? true : false;
	}

	/**
	 * 发送短信
	 * @param phone 接收短信手机号
	 * @param aliyunSmsTemplate 短信模版信息
	 * @param params 传入参数
	 * @return
	 */
	@Async
	public void sendSMS(String phone, AliyunSmsTemplate aliyunSmsTemplate, Map<String, String> params,Long smsId) {
		try{
			SendSmsResponse obj = SmsUtils.sendSms(phone,"大汇堂",aliyunSmsTemplate.getCode(),JSON.toJSONString(params));
			Map<String,String> rtnMap=new HashMap<String, String>();
			rtnMap.put("code",obj.getCode());
			rtnMap.put("bizId",obj.getBizId());
			SmsSendRecord sms=smsSendRecordMapper.querySmsSendRecordById(smsId);
			if(ObjectUtils.isNotEmpty(sms)){
				sms.setOutId(StringUtils.concat(obj.getRequestId(),"_@_",obj.getBizId()));
				sms.setOutMsg(obj.getMessage());
				sms.setOutDate(new Date());
				smsSendRecordMapper.updateSmsSendRecord(sms);
			}
		}catch(Exception e){
			e.printStackTrace();
		}
	}
}

