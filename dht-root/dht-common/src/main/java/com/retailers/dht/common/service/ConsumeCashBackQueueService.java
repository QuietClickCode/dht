
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.ConsumeCashBackQueue;
import java.util.Map;
/**
 * 描述：第三方消费返现Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:46:01
 */
public interface ConsumeCashBackQueueService {

	/**
	 * 添加第三方消费返现
	 * @param consumeCashBackQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public boolean saveConsumeCashBackQueue(ConsumeCashBackQueue consumeCashBackQueue);
	/**
	 * 编辑第三方消费返现
	 * @param consumeCashBackQueue
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateConsumeCashBackQueue(ConsumeCashBackQueue consumeCashBackQueue);
	/**
	 * 根据id查询第三方消费返现
	 * @param icbqId
	 * @return consumeCashBackQueue
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public ConsumeCashBackQueue queryConsumeCashBackQueueByIcbqId(Long icbqId);
	/**
	 * 查询第三方消费返现列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public Pagination<ConsumeCashBackQueue> queryConsumeCashBackQueueList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据icbqId删除第三方消费返现
	 * @param icbqId
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public boolean deleteConsumeCashBackQueueByIcbqId(Long icbqId);



}


