
package com.retailers.aliyun.sms.service;
import com.retailers.aliyun.sms.entity.AliyunSmsTemplate;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.AsyncCallback;

import java.util.Map;
/**
 * 描述：阿里云短信模版管理Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-04 15:05:09
 */
public interface AliyunSmsTemplateService {

	/**
	 * 添加阿里云短信模版管理
	 * @param aliyunSmsTemplate
	 * @return
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public boolean saveAliyunSmsTemplate(AliyunSmsTemplate aliyunSmsTemplate);
	/**
	 * 编辑阿里云短信模版管理
	 * @param aliyunSmsTemplate
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateAliyunSmsTemplate(AliyunSmsTemplate aliyunSmsTemplate);
	/**
	 * 根据id查询阿里云短信模版管理
	 * @param id
	 * @return aliyunSmsTemplate
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public AliyunSmsTemplate queryAliyunSmsTemplateById(Long id);
	/**
	 * 查询阿里云短信模版管理列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public Pagination<AliyunSmsTemplate> queryAliyunSmsTemplateList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除阿里云短信模版管理
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-04 15:05:09
	 */
	public boolean deleteAliyunSmsTemplateById(Long id);

	/**
	 * 发送短信
	 * @param phone 接收短信手机号
	 * @param aliyunSmsTemplate 短信模版信息
	 * @param params 传入参数
	 * @return
	 */
	public void sendSMS(String phone, AliyunSmsTemplate aliyunSmsTemplate, Map<String,String> params,Long smsId);
}


