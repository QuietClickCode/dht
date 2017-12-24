package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.OrderSuccessQueue;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：订单支付成功队例DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-24 11:30:58
 */
public interface OrderSuccessQueueMapper {

	/**
	 * 添加订单支付成功队例
	 * @param orderSuccessQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public int saveOrderSuccessQueue(OrderSuccessQueue orderSuccessQueue);
	/**
	 * 编辑订单支付成功队例
	 * @param orderSuccessQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public int updateOrderSuccessQueue(OrderSuccessQueue orderSuccessQueue);
	/**
	 * 根据Id删除订单支付成功队例
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public int deleteOrderSuccessQueueById(Long id);
	/**
	 * 根据Id查询订单支付成功队例
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public OrderSuccessQueue queryOrderSuccessQueueById(Long id);
	/**
	 * 查询订单支付成功队例列表
	 * @param pagination 分页对象
	 * @return  订单支付成功队例列表
	 * @author zhongp
	 * @date 2017-12-24 11:30:58
	 */
	public List<OrderSuccessQueue> queryOrderSuccessQueueList(Pagination<OrderSuccessQueue> pagination);

}
