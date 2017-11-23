
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.SmsSendRecord;
import java.util.Map;
/**
 * 描述：短信发送列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 00:38:54
 */
public interface SmsSendRecordService {

	/**
	 * 添加短信发送列表
	 * @param smsSendRecord
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:38:54
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
	 * @date 2017-11-23 00:38:54
	 */
	public SmsSendRecord querySmsSendRecordById(Long id);
	/**
	 * 查询短信发送列表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-23 00:38:54
	 */
	public Pagination<SmsSendRecord> querySmsSendRecordList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除短信发送列表
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:38:54
	 */
	public boolean deleteSmsSendRecordById(Long id);

}


