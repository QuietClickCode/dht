package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.OrderRefund;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：退款订单列表DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-01-29 22:30:33
 */
public interface OrderRefundMapper {

	/**
	 * 添加退款订单列表
	 * @param orderRefund
	 * @return
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public int saveOrderRefund(OrderRefund orderRefund);
	/**
	 * 编辑退款订单列表
	 * @param orderRefund
	 * @return
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public int updateOrderRefund(OrderRefund orderRefund);
	/**
	 * 根据RdId删除退款订单列表
	 * @param rdId
	 * @return
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public int deleteOrderRefundByRdId(Long rdId);
	/**
	 * 根据RdId查询退款订单列表
	 * @param rdId
	 * @return
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public OrderRefund queryOrderRefundByRdId(Long rdId);
	/**
	 * 查询退款订单列表列表
	 * @param pagination 分页对象
	 * @return  退款订单列表列表
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public List<OrderRefund> queryOrderRefundList(Pagination<OrderRefund> pagination);

}
