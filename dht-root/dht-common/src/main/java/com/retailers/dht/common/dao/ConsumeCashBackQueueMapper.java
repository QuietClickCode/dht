package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.ConsumeCashBackQueue;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：第三方消费返现DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:46:01
 */
public interface ConsumeCashBackQueueMapper {

	/**
	 * 添加第三方消费返现
	 * @param consumeCashBackQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public int saveConsumeCashBackQueue(ConsumeCashBackQueue consumeCashBackQueue);
	/**
	 * 编辑第三方消费返现
	 * @param consumeCashBackQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public int updateConsumeCashBackQueue(ConsumeCashBackQueue consumeCashBackQueue);
	/**
	 * 根据IcbqId删除第三方消费返现
	 * @param icbqId
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public int deleteConsumeCashBackQueueByIcbqId(Long icbqId);
	/**
	 * 根据IcbqId查询第三方消费返现
	 * @param icbqId
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public ConsumeCashBackQueue queryConsumeCashBackQueueByIcbqId(Long icbqId);
	/**
	 * 查询第三方消费返现列表
	 * @param pagination 分页对象
	 * @return  第三方消费返现列表
	 * @author zhongp
	 * @date 2017-12-20 01:46:01
	 */
	public List<ConsumeCashBackQueue> queryConsumeCashBackQueueList(Pagination<ConsumeCashBackQueue> pagination);

}
