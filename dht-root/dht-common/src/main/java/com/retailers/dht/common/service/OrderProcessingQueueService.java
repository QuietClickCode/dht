
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.OrderProcessingQueue;
import java.util.Map;
/**
 * 描述：订单处理队列（订单支付回调，订单状态变更）Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-14 01:32:58
 */
public interface OrderProcessingQueueService {

	/**
	 * 添加订单处理队列（订单支付回调，订单状态变更）
	 * @param orderProcessingQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public boolean saveOrderProcessingQueue(OrderProcessingQueue orderProcessingQueue);
	/**
	 * 编辑订单处理队列（订单支付回调，订单状态变更）
	 * @param orderProcessingQueue
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateOrderProcessingQueue(OrderProcessingQueue orderProcessingQueue);
	/**
	 * 根据id查询订单处理队列（订单支付回调，订单状态变更）
	 * @param id
	 * @return orderProcessingQueue
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public OrderProcessingQueue queryOrderProcessingQueueById(Long id);
	/**
	 * 查询订单处理队列（订单支付回调，订单状态变更）列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public Pagination<OrderProcessingQueue> queryOrderProcessingQueueList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除订单处理队列（订单支付回调，订单状态变更）
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-14 01:32:58
	 */
	public boolean deleteOrderProcessingQueueById(Long id);

	/**
	 * 添加消息处理队列
	 * @param orderNo 订单号
	 * @param isSuccess 是否成功
	 * @param tradeOffId 第三方回调单号
	 * @param payWay 支付方式
	 * @param message 消息
	 * @return
	 */
	public boolean addQueue(String orderNo,boolean isSuccess,String tradeOffId,Long payWay,String message);

	/**
	 * 执行订单队列
	 */
	public void executeOrderProcessingQueue();

}


