
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.OrderSuccessQueue;
import java.util.Map;
/**
 * 描述：订单支付成功队例Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-24 11:30:58
 */
public interface OrderSuccessQueueService {

	/**
	 * 添加订单支付成功队例
	 * @param orderSuccessQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public boolean saveOrderSuccessQueue(OrderSuccessQueue orderSuccessQueue);
	/**
	 * 编辑订单支付成功队例
	 * @param orderSuccessQueue
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateOrderSuccessQueue(OrderSuccessQueue orderSuccessQueue);
	/**
	 * 根据id查询订单支付成功队例
	 * @param id
	 * @return orderSuccessQueue
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public OrderSuccessQueue queryOrderSuccessQueueById(Long id);
	/**
	 * 查询订单支付成功队例列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public Pagination<OrderSuccessQueue> queryOrderSuccessQueueList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除订单支付成功队例
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public boolean deleteOrderSuccessQueueById(Long id);

	/**
	 * 执行订单回调处理
	 */
	public void executeOrderQueue();

	/**
	 * 编辑执行状态
	 * @param osq
	 * @param isSuccess
	 */
	public void editorExeStatus(OrderSuccessQueue osq,boolean isSuccess);

}


