
package com.retailers.dht.common.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.dao.RechargeMapper;
import com.retailers.dht.common.enm.OrderEnum;
import com.retailers.dht.common.entity.Order;
import com.retailers.dht.common.dao.OrderMapper;
import com.retailers.dht.common.entity.Recharge;
import com.retailers.dht.common.entity.UserAddress;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.service.ProcedureToolsService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.apache.ibatis.ognl.enhance.OrderedReturn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：订单Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-21 14:23:59
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
	private OrderMapper orderMapper;
	@Autowired
	private RechargeMapper rechargeMapper;

    @Autowired
    private ProcedureToolsService procedureToolsService;

	public boolean saveOrder(Order order) {
		int status = orderMapper.saveOrder(order);
		return status == 1 ? true : false;
	}
	public boolean updateOrder(Order order) {
		int status = orderMapper.updateOrder(order);
		return status == 1 ? true : false;
	}
	public Order queryOrderById(Long id) {
		return orderMapper.queryOrderById(id);
	}

	public Pagination<Order> queryOrderList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Order> page = new Pagination<Order>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Order> list = orderMapper.queryOrderList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOrderById(Long id) {
		int status = orderMapper.deleteOrderById(id);
		return status == 1 ? true : false;
	}

	/**
	 * 购物订单
	 * @param uid 购买用户id
	 * @param goodsIds 商品ids 多个商品用逗号隔开
	 * @param numbers 购买商品数量多个用逗号隔开
	 * @param orderRemark 备注
	 * @param uaId 收货人id
	 * @return
	 * @throws AppException
	 */
    @Transactional(rollbackFor = Exception.class)
	public Map<String, Object> shoppingOrder(Long uid, String goodsIds, String numbers, String orderRemark, Long uaId) throws AppException {
        logger.info("创建购物订单,购买用户:[{}],商品列表:[{}],数量列表:[{}],订单备注:[{}],收货人地址:[{}]",uid,goodsIds,numbers,orderRemark,uaId);
        Date curDate=new Date();
        Map<String,Object> rtnMap=new HashMap<String,Object>();
        String orderNo=procedureToolsService.executeOrderNo(OrderEnum.SHOPPING);
        try{
            rtnMap.put("orderNo",orderNo);
        }finally {
            logger.info("创建购物订单完成，生成订单号:[{}],执行时间:[{}]",orderNo,(System.currentTimeMillis()-curDate.getTime()));
        }
		return rtnMap;
	}

	/**
	 * 用户充值
	 * @param uid 用户id
	 * @param rid 充值id
	 * @return
	 * @throws AppException
	 */
	public Map<String, Object> userRecharge(Long uid, Long rid) throws AppException {
		logger.info("用户充值处理开始,充值用户id:[{}],充值卡id:[{}]",uid,rid);
		Date curDate = new Date();
		Map<String,Object> rtn=new HashMap<String, Object>();
		try{
			//取得充值信息
			Recharge recharge = rechargeMapper.queryRechargeByRid(rid);
			if(ObjectUtils.isEmpty(recharge)){
				throw new AppException("你所购买的充值卡己下架。请刷新后再试");
			}
			OrderEnum oe= OrderEnum.RECHARGE;
			//取得单号
			String orderNo=procedureToolsService.executeOrderNo(oe);
			Order order=new Order();
			order.setOrderType(oe.getKey());
			order.setOrderNo(orderNo);
			//出售用户
			order.setOrderSellUid(com.retailers.dht.common.constant.SystemConstant.GOODS_SHOP_USER);
			//购买者
			order.setOrderBuyUid(uid);
			order.setOrderTradePrice(recharge.getRprice());
			order.setOrderShopPrice(recharge.getRprice());
			String illustrate="用户[{}]购买充值卡，充值金额：{}，充值后享受的折扣:{}";
			illustrate= StringUtils.formates(illustrate,uid, NumberUtils.formaterNumberPower(recharge.getRprice()),NumberUtils.formaterNumberPower(recharge.getRdiscount()));
			order.setOrderIllustrate(illustrate);
			order.setOrderCreateDate(curDate);
			orderMapper.saveOrder(order);
			rtn.put("orderNo",orderNo);
		}finally {
			logger.info("用户充值处理结束,执行时间:[{}]",(System.currentTimeMillis()-curDate.getTime()));
		}
		return rtn;
	}


}

