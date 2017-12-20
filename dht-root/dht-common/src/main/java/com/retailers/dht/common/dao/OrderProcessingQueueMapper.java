package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.OrderProcessingQueue;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：订单处理队列（订单支付回调，订单状态变更）DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-14 01:32:58
 */
public interface OrderProcessingQueueMapper {

	/**
	 * 添加订单处理队列（订单支付回调，订单状态变更）
	 * @param orderProcessingQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public int saveOrderProcessingQueue(OrderProcessingQueue orderProcessingQueue);

	/**
	 * 添加订单处理队列至历史
	 * @param orderProcessingQueue
	 * @return
	 */
	public int saveOrderProcessingQueueHistory(OrderProcessingQueue orderProcessingQueue);
	/**
	 * 编辑订单处理队列（订单支付回调，订单状态变更）
	 * @param orderProcessingQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public int updateOrderProcessingQueue(OrderProcessingQueue orderProcessingQueue);
	/**
	 * 根据Id删除订单处理队列（订单支付回调，订单状态变更）
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public int deleteOrderProcessingQueueById(Long id);
	/**
	 * 根据Id查询订单处理队列（订单支付回调，订单状态变更）
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public OrderProcessingQueue queryOrderProcessingQueueById(Long id);
	/**
	 * 查询订单处理队列（订单支付回调，订单状态变更）列表
	 * @param pagination 分页对象
	 * @return  订单处理队列（订单支付回调，订单状态变更）列表
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public List<OrderProcessingQueue> queryOrderProcessingQueueList(Pagination<OrderProcessingQueue> pagination);

	/**
	 * 取得所有未执行的订单状态队列
	 * @return
	 */
	public List<OrderProcessingQueue> initProcessingQueue();
}
