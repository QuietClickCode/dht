
package com.retailers.aliyun.sms.service;
import com.retailers.aliyun.sms.entity.SmsSendRecord;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;

import java.util.Date;
import java.util.Map;
/**
 * 描述：短信发送列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-04 15:43:07
 */
public interface SmsSendRecordService {

	/**
	 * 添加短信发送列表
	 * @param smsSendRecord
	 * @return
	 * @author zhongp
	 * @date 2017-12-04 15:43:07
	 */
	public boolean saveSmsSendRecord(SmsSendRecord smsSendRecord);
	/**
	 * 编辑短信发送列表
	 * @param smsSendRecord
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateSmsSendRecord(SmsSendRecord smsSendRecord);
	/**
	 * 根据id查询短信发送列表
	 * @param id
	 * @return smsSendRecord
	 * @author zhongp
	 * @date 2017-12-04 15:43:07
	 */
	public SmsSendRecord querySmsSendRecordById(Long id);
	/**
	 * 查询短信发送列表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-04 15:43:07
	 */
	public Pagination<SmsSendRecord> querySmsSendRecordList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除短信发送列表
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-04 15:43:07
	 */
	public boolean deleteSmsSendRecordById(Long id);
	/**
	 * 发送短信
	 * @param uid 用户id
	 * @param phone 电话号码
	 * @param type 发送短信类型（0 绑定短信，1 密码找回短信，2 解除绑定短信）
	 * @return
	 * @throws AppException
	 */
	public SmsSendRecord sendSmsCode(Long uid,String phone,long type)throws AppException;

	/**
	 *
	 * @param uid 用户id
	 * @param phone 电话号码
	 * @param type 发送短信类型（0 绑定短信，1 密码找回短信，2 解除绑定短信）
	 * @param params 传入参数
	 * @return
	 * @throws AppException
	 */
	public SmsSendRecord sendSmsCode(Long uid,String phone,long type,Map<String,String> params)throws AppException;

	/**
	 * 根据手机号，类型取得发送消息内容
	 * @param phone
	 * @param type
	 * @param curDate
	 * @return
	 */
	public SmsSendRecord queryCurSmsSendRecordByPhone(String phone,int type,String code,Date curDate);

	/**
	 * 校验短信是否可以发送（该时间周期内未发送过短信)
	 * @param uid 用户id
	 * @param phone 接收短信手机号
	 * @param type 发送类型
	 * @param curDate 失效时间
	 * @return
	 */
	public int checkSendSms(Long uid,String phone,long type,Date curDate);
}


