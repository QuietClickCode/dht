
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.constant.OrderConstant;
import com.retailers.dht.common.constant.OrderRefundConstant;
import com.retailers.dht.common.dao.OrderMapper;
import com.retailers.dht.common.dao.OrderRefundMapper;
import com.retailers.dht.common.entity.Order;
import com.retailers.dht.common.entity.OrderRefund;
import com.retailers.dht.common.service.OrderRefundService;
import com.retailers.dht.common.vo.OrderRefundVo;
import com.retailers.mybatis.common.constant.SingleThreadLockConstant;
import com.retailers.mybatis.common.enm.OrderEnum;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 描述：退款订单列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-01-29 22:30:33
 */
@Service("orderrefundService")
public class OrderRefundServiceImpl implements OrderRefundService {
	private Logger logger = LoggerFactory.getLogger(OrderRefundServiceImpl.class);

	@Autowired
	private OrderRefundMapper orderRefundMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private ProcedureToolsService procedureToolsService;


	public boolean saveOrderRefund(OrderRefund orderRefund) {
		int status = orderRefundMapper.saveOrderRefund(orderRefund);
		return status == 1 ? true : false;
	}
	public boolean updateOrderRefund(OrderRefund orderRefund) {
		int status = orderRefundMapper.updateOrderRefund(orderRefund);
		return status == 1 ? true : false;
	}
	public OrderRefund queryOrderRefundByRdId(Long rdId) {
		return orderRefundMapper.queryOrderRefundByRdId(rdId);
	}

	public Pagination<OrderRefundVo> queryOrderRefundList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<OrderRefundVo> page = new Pagination<OrderRefundVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<OrderRefundVo> list = orderRefundMapper.queryOrderRefundList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOrderRefundByRdId(Long rdId) {
		int status = orderRefundMapper.deleteOrderRefundByRdId(rdId);
		return status == 1 ? true : false;
	}

	/**
	 * 用户发起退款申请
	 * @param uid 用户id
	 * @param orderId 订单id
	 * @param remark 退款备注
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public String createRefund(Long uid, Long orderId, String remark)throws AppException {
		String lockKey= StringUtils.formate("refund",orderId+"");
		procedureToolsService.singleLockManager(lockKey);
		String rtnOrderNo="";
		try{
			Order order = orderMapper.queryOrderById(orderId);
			if(ObjectUtils.isEmpty(order)){
				throw new AppException(("订单不存在"));
			}
			//判断是否是同一个发起
			if(uid.intValue()!=order.getOrderBuyUid().intValue()){
				throw new AppException("非本人订单不能操作");
			}
			//取得当前订单状态
			int curStatus=order.getOrderStatus().intValue();
			if(curStatus== OrderConstant.ORDER_STATUS_CREATE||curStatus==OrderConstant.ORDER_STATUS_PAY_FAILE||
					curStatus==OrderConstant.ORDER_STATUS_PAY_SEND_CANCEL||curStatus==OrderConstant.ORDER_STATUS_PAY_EXPIRE_TIME){
				throw new AppException("订单未支付。");
			}
			if(curStatus==OrderConstant.ORDER_STATUS_PAY_END){
				throw new AppException("订单己完成，不能进行退货处理");
			}
			if(curStatus==OrderConstant.ORDER_STATUS_PAY_REFUND){
				throw new AppException("退款中");
			}
			//生成订单号
			rtnOrderNo = procedureToolsService.executeOrderNo(OrderEnum.REFUND);
			if(order.getOrderType().equals(OrderEnum.RECHARGE.getKey())){

			}else{
				createShopOrderRefund(rtnOrderNo,order,remark);
			}
		}finally {
			procedureToolsService.singleUnLockManager(lockKey);
		}
		return rtnOrderNo;
	}

	/**
	 * 创建购物退款订单
	 * @param rdOrderNo
	 * @param order
	 */
	private void createShopOrderRefund(String rdOrderNo,Order order,String remark){
		//取得当前订单状态
		int curStatus=order.getOrderStatus().intValue();
		//退款金额
		long refundPrice=0l;
		//根据订单状态计算退款金额  未发货 未发货退还全部订单金额 己发货只退还商品总额不退还运费
		if(curStatus==OrderConstant.ORDER_STATUS_PAY_SUCCESS){
			refundPrice=order.getOrderMenberPrice()+order.getOrderLogisticsPrice();
		}else{
			refundPrice=order.getOrderMenberPrice();
		}
		//创建退款单
		OrderRefund orf= createRefund(rdOrderNo,order.getId(),refundPrice,remark);
		orderRefundMapper.saveOrderRefund(orf);
		order.setOrderStatus(OrderConstant.ORDER_STATUS_PAY_REFUND);
		//修改订单状态为退款中
		orderMapper.updateOrder(order);
	}

	/**
	 * 生成退款单
	 * @param rdOrderNo 订单号
	 * @param orderId 订单id
	 * @param refundPrice 退款金额
	 * @param remark 备注
	 * @return
	 */
	private OrderRefund createRefund(String rdOrderNo,Long orderId,Long refundPrice,String remark){
		OrderRefund or=new OrderRefund();
		or.setRdOrderNo(rdOrderNo);
		or.setRdOrder(orderId);
		or.setRdPrice(refundPrice);
		or.setRdRemark(remark);
		or.setRdStatus((long)OrderRefundConstant.REFUND_AUDITING_STATUS_CREATE);
		or.setRdCreateDate(new Date());
		return or;
	}

	/**
	 * 审核退款申请
	 * @param uid 后台系统用户
	 * @param orId 退款申请id
	 * @param status 审核状态
	 * @param remark 备注（不通过时说明原因）
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean auditingOrderRefund(Long uid, Long orId, Long status, String remark) throws AppException {
		logger.info("开始进入退款审核操作,审核退款id:[{}]，操作人:[{}],审核状态:[{}]",orId,uid,status);
		//添加同步锁
		String key=StringUtils.formate(SingleThreadLockConstant.AUDITING_REFUND_ORDER,orId+"");
		procedureToolsService.singleLockManager(key);
		try{
			OrderRefund or = orderRefundMapper.queryOrderRefundByRdId(orId);
			if(ObjectUtils.isEmpty(or)){
				throw new AppException("退款数据不存在");
			}
			//判断退款状态（只能处理创建状态）
			if(or.getRdStatus().intValue()!= OrderRefundConstant.REFUND_AUDITING_STATUS_CREATE){
				throw new AppException("退款申请状态异常");
			}
			or.setRdStatus(status);
			or.setRdAuditingDate(new Date());
			or.setRdAuditingRemark(remark);
			or.setRdSuid(uid);
			orderRefundMapper.updateOrderRefund(or);
		}finally {
			procedureToolsService.singleUnLockManager(key);
		}
		return true;
	}
}

