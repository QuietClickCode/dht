
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.OrderRefundVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.OrderRefund;
import com.retailers.tools.exception.AppException;

import java.util.Map;
/**
 * 描述：退款订单列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-01-29 22:30:33
 */
public interface OrderRefundService {

	/**
	 * 添加退款订单列表
	 * @param orderRefund
	 * @return
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public boolean saveOrderRefund(OrderRefund orderRefund);
	/**
	 * 编辑退款订单列表
	 * @param orderRefund
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateOrderRefund(OrderRefund orderRefund);
	/**
	 * 根据id查询退款订单列表
	 * @param rdId
	 * @return orderRefund
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public OrderRefund queryOrderRefundByRdId(Long rdId);
	/**
	 * 查询退款订单列表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public Pagination<OrderRefundVo> queryOrderRefundList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据rdId删除退款订单列表
	 * @param rdId
	 * @return
	 * @author zhongp
	 * @date 2018-01-29 22:30:33
	 */
	public boolean deleteOrderRefundByRdId(Long rdId);

	/**
	 * 用户发起退款
	 * @param uid 用户id
	 * @param orderId 订单id
	 * @param remark 退款备注
	 * @return
	 */
	public String createRefund(Long uid,Long orderId,String remark)throws AppException;

	/**
	 * 退款审核
	 * @param uid
	 * @param orId
	 * @param status
	 * @param remark
	 * @return
	 * @throws AppException
	 */
	public boolean auditingOrderRefund(Long uid,Long orId,Long status,String remark)throws AppException;

	/**
	 * 退款
	 * @param suid 操作用户
	 * @param orId 退款单id
	 * @return
	 * @throws AppException
	 */
	public boolean orderRefund(Long suid,Long orId)throws AppException;

}


