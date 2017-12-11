package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.OrderGoodsCoupon;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：订单商品优惠关联DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-11 23:32:56
 */
public interface OrderGoodsCouponMapper {

	/**
	 * 添加订单商品优惠关联
	 * @param orderGoodsCoupon
	 * @return
	 * @author zhongp
	 * @date 2017-12-11 23:32:56
	 */
	public int saveOrderGoodsCoupon(OrderGoodsCoupon orderGoodsCoupon);
	/**
	 * 编辑订单商品优惠关联
	 * @param orderGoodsCoupon
	 * @return
	 * @author zhongp
	 * @date 2017-12-11 23:32:56
	 */
	public int updateOrderGoodsCoupon(OrderGoodsCoupon orderGoodsCoupon);
	/**
	 * 根据OcId删除订单商品优惠关联
	 * @param ocId
	 * @return
	 * @author zhongp
	 * @date 2017-12-11 23:32:56
	 */
	public int deleteOrderGoodsCouponByOcId(Long ocId);
	/**
	 * 根据OcId查询订单商品优惠关联
	 * @param ocId
	 * @return
	 * @author zhongp
	 * @date 2017-12-11 23:32:56
	 */
	public OrderGoodsCoupon queryOrderGoodsCouponByOcId(Long ocId);
	/**
	 * 查询订单商品优惠关联列表
	 * @param pagination 分页对象
	 * @return  订单商品优惠关联列表
	 * @author zhongp
	 * @date 2017-12-11 23:32:56
	 */
	public List<OrderGoodsCoupon> queryOrderGoodsCouponList(Pagination<OrderGoodsCoupon> pagination);

}
