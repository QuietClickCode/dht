package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Order;
import com.retailers.dht.common.vo.OrderDetailVo;
import com.retailers.dht.common.vo.OrderVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
	 * 订单支付回调
	 * @param order
	 * @return
	 */
	public int orderPayCallBack(Order order);
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

	/**
	 * 取得用户第一次购买订单
	 * @param uid 用户id
	 * @return
	 */
	public Long findUserFirstBuy(Long uid);

	/**
	 * 取得订单详情
	 * @param pagination
	 * @return
	 */
	public List<OrderVo> queryOrderInfoLists(Pagination<OrderVo> pagination);

	/**
	 * 根据谭id 取得订单
	 * @param orderId
	 * @return
	 */
	public OrderVo queryOrderInfoById(Long orderId);

	/**
	 * 取得订单数据
	 * @param params
	 * @return
	 */
	public List<OrderVo> queryOrderInfos(@Param("params")Map<String,Object> params);

	/**
	 * 根据订单状态和时间取得订单列表
	 * @param orderStatus 订单状态
	 * @param column 条件列
	 * @param date 时间
	 * @return
	 */
	public List<Order> queryOrderByStatus(@Param("orderStatus") Integer orderStatus,@Param("column")String column, @Param("date") Date date);
	//设置订单超时
	public int clearExpireOrders(List<Long> orderIds);

}
