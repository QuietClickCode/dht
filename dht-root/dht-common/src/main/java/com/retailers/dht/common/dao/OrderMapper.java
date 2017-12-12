package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Order;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：订单DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-21 14:23:59
 */
public interface OrderMapper {

	/**
	 * 添加订单
	 * @param order
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public int saveOrder(Order order);
	/**
	 * 编辑订单
	 * @param order
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public int updateOrder(Order order);
	/**
	 * 根据Id删除订单
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public int deleteOrderById(Long id);
	/**
	 * 根据Id查询订单
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public Order queryOrderById(Long id);

	/**
	 * 根据谭号取得订单
	 * @param orderNo
	 * @return
	 */
	public Order queryOrderByOrderNo(String orderNo);
	/**
	 * 查询订单列表
	 * @param pagination 分页对象
	 * @return  订单列表
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public List<Order> queryOrderList(Pagination<Order> pagination);

	/**
	 * 校验用户是否存在未付款订单
	 * @param uid 用户id
	 * @return
	 */
	public int checkUserUnPayOrder(Long uid);

}
