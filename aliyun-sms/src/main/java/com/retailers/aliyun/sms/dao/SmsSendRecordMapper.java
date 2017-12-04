package com.retailers.aliyun.sms.dao;
import com.retailers.aliyun.sms.entity.SmsSendRecord;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
/**
 * 描述：短信发送列表DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-04 15:43:07
 */
public interface SmsSendRecordMapper {

	/**
	 * 添加短信发送列表
	 * @param smsSendRecord
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:38:54
	 */
	public int saveSmsSendRecord(SmsSendRecord smsSendRecord);
	/**
	 * 编辑短信发送列表
	 * @param smsSendRecord
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:38:54
	 */
	public int updateSmsSendRecord(SmsSendRecord smsSendRecord);
	/**
	 * 根据Id删除短信发送列表
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:38:54
	 */
	public int deleteSmsSendRecordById(Long id);
	/**
	 * 根据Id查询短信发送列表
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:38:54
	 */
	public SmsSendRecord querySmsSendRecordById(Long id);
	/**
	 * 查询短信发送列表列表
	 * @param pagination 分页对象
	 * @return  短信发送列表列表
	 * @author zhongp
	 * @date 2017-11-23 00:38:54
	 */
	public List<SmsSendRecord> querySmsSendRecordList(Pagination<SmsSendRecord> pagination);

	/**
	 * 根据手机号，类型取得发送消息内容
	 * @param phone
	 * @param type
	 * @param curDate
	 * @return
	 */
	public SmsSendRecord queryCurSmsSendRecordByPhone(@Param("phone")String phone, @Param("type")int type, @Param("code")String code, @Param("curDate")Date curDate);

	/**
	 * 校验短信是否可以发送（该时间周期内未发送过短信)
	 * @param uid 用户id
	 * @param phone 接收短信手机号
	 * @param type 发送类型
	 * @param curDate 失效时间
	 * @return
	 */
	public int checkSendSms(@Param("uid")Long uid,@Param("phone")String phone, @Param("type")long type,@Param("curDate")Date curDate);

}
