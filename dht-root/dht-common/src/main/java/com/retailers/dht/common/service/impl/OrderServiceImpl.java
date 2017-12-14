
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.LogUserCardPackageConstant;
import com.retailers.dht.common.constant.OrderConstant;
import com.retailers.dht.common.dao.*;
import com.retailers.dht.common.entity.*;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.vo.BuyGoodsDetailVo;
import com.retailers.dht.common.vo.BuyGoodsVo;
import com.retailers.mybatis.common.constant.SingleThreadLockConstant;
import com.retailers.mybatis.common.enm.OrderEnum;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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
	private OrderDetailMapper orderDetailMapper;
    @Autowired
	private OrderGoodsCouponMapper orderGoodsCouponMapper;
	@Autowired
	private RechargeMapper rechargeMapper;
	@Autowired
	private UserAddressMapper userAddressMapper;
	@Autowired
	private GoodsDetailService goodsDetailService;
    @Autowired
    private ProcedureToolsService procedureToolsService;
    @Autowired
	private GoodsMapper goodsMapper;
    @Autowired
	private UserMapper userMapper;
    @Autowired
	private UserCardPackageMapper userCardPackageMapper;
    @Autowired
	private LogUserCardPackageMapper logUserCardPackageMapper;



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
	 * 根据订单号取得订单信息
	 * @param orderNo
	 * @return
	 */
	public Order queryOrderByOrderNo(String orderNo) {
		return orderMapper.queryOrderByOrderNo(orderNo);
	}

	/**
	 * 购物订单
	 * @param uid 购买用户id
	 * @param buyDetails 购买商品详情  {"buyGoods":[{"goodsId":123,"num":3,"gcpId":123,"cpId":222,"specs":1,"remark":"说明"}],"address":123}
	 *                   				buyGoods 购买商品列表
	 *                   				goodsId 商品id
	 *                   				num 购买数量
	 *                   				gcpId 商品优惠id
	 *                   				cpId 优惠卷id
	 *                   				specs 规格ids
	 *                   				address 收货人地址id
	 * @return
	 * @throws AppException
	 */
    @Transactional(rollbackFor = Exception.class)
	public Map<String,Object> shoppingOrder(Long uid, JSONObject buyDetails)throws AppException{
        logger.info("创建购物订单,购买用户:[{}],商品列表:[{}]",uid,buyDetails);
        Date curDate=new Date();
        String key=StringUtils.formate(SingleThreadLockConstant.USER_BUY_GOODS,uid+"");
        procedureToolsService.singleUnLockManager(key);
		Map<String,Object> rtnMap=new HashMap<String,Object>();
		String orderNo="";
        try{
        	//判断用户是否存在未付款订单
			int unPayTotal=orderMapper.checkUserUnPayOrder(uid);
			if(unPayTotal>0){
				throw new AppException("存在款付款订单，不能进行此次购买");
			}
			//取得货品信息
			BuyGoodsDetailVo bgVos= JSONObject.parseObject(buyDetails.toJSONString(),BuyGoodsDetailVo.class);
			//开始计算商品价格
			//取得用户地址
			UserAddress userAddress=userAddressMapper.queryUserAddressByUaId(bgVos.getAddress());
			if(ObjectUtils.isEmpty(userAddress)){
				throw new AppException("请填写收货人地址");
			}
			//判断用户地址是否异常
			if(userAddress.getUaUid().intValue()!=uid.intValue()){
				throw new AppException("请填写收货人地址");
			}
			//商品对应使用的商品优惠
			Map<Long,List<Long>> gcpMaps=new HashMap<Long, List<Long>>();
			//购买商品列表
			List<Long> buyGIds=new ArrayList<Long>();
			//取得购买商中使用了商品优惠的
			for(BuyGoodsVo bgVo:bgVos.getBuyGoods()){
				if(ObjectUtils.isNotEmpty(bgVo.getGcpIds())){
					String gcpIds=bgVo.getGcpIds();
					List<Long> gcps=new ArrayList<Long>();
					for(String gcp:gcpIds.split(",")){
						gcps.add(Long.parseLong(gcp));
					}
					gcpMaps.put(bgVo.getGoodsId(),gcps);
				}
				buyGIds.add(bgVo.getGoodsId());
			}
			//判断是否使用了商品优惠
			if(ObjectUtils.isNotEmpty(gcpMaps)){
				//校验商品优惠是否异常
			}
			//取得使用的优惠卷
			String couponIds=bgVos.getCpIds();
			//判断是否使用优卷
			if(ObjectUtils.isNotEmpty(couponIds)){
			//校验优惠卷是否异常
			}
			//订单详情
			List<OrderDetail> ods=new ArrayList<OrderDetail>();
			//商品优惠使用情况
			List<OrderGoodsCoupon> ogcs=new ArrayList<OrderGoodsCoupon>();
			String gdIds="";
			//取得对应价格
			for(BuyGoodsVo bgVo:bgVos.getBuyGoods()){
				gdIds+=bgVo.getGdId()+",";
			}
			List<GoodsDetail> gds=goodsDetailService.queryGoodsDetailByGdIds(gdIds);
			Map<Long,Long> goodsPrice=new HashMap<Long, Long>();
			for(GoodsDetail gd:gds){
				goodsPrice.put(gd.getGid(),gd.getGdPrice());
			}
			long totalPrice = 0;
			//生成订单详情
			for(BuyGoodsVo bgVo:bgVos.getBuyGoods()){
				OrderDetail od=new OrderDetail();
				od.setOdBuyNumber(bgVo.getNum());
				od.setOdGoodsId(bgVo.getGoodsId());
				od.setRemark(bgVo.getRemark());
				long p=goodsPrice.get(bgVo.getGoodsId());
				od.setOdGoodsPrice(p*bgVo.getNum());
				totalPrice+=od.getOdGoodsPrice();
				od.setOdGdId(bgVo.getGdId());
				od.setOdIsRefund(OrderConstant.ORDER_REFUND_STATUS_UN);
				if(ObjectUtils.isNotEmpty(bgVo.getGcpIds())){
					String[] gcpIds=bgVo.getGcpIds().split(",");
					for(String gcpId:gcpIds){
						OrderGoodsCoupon ogc=new OrderGoodsCoupon();
						ogc.setOcGoodsId(bgVo.getGoodsId());
						ogc.setOcGcId(Long.parseLong(gcpId));
						ogcs.add(ogc);
					}
				}
				//实际支付金额
				od.setOdActualPrice(totalPrice);
				ods.add(od);
			}

			Order order =createOrder(OrderEnum.SHOPPING,userAddress,totalPrice,null,null,null,ods,ogcs);

			//批量添加优惠卷
			orderNo=order.getOrderNo();
			rtnMap.put("orderNo",orderNo);
		}finally {
        	logger.info("创建购物订单完毕，执行时间：[{}]",(System.currentTimeMillis()-curDate.getTime()));
			logger.info("创建购物订单完成，生成订单号:[{}],执行时间:[{}]",orderNo,(System.currentTimeMillis()-curDate.getTime()));
			procedureToolsService.singleUnLockManager(key);
		}
		return rtnMap;
	}
	//订单创建(购特，秒杀，团购，砍价，预购）

	/**
	 * 创建订单
	 * @param orderEnum 订单类型
	 * @param userAddress 用户收货地址
	 * @param totalPrice 总金额
	 * @param cPrice 优惠卷金额
	 * @param gcPrice 商品优惠金额
	 * @param actualPrice 实际金额
	 * @param ods 商品详情
	 * @param ogcs 商品优惠详情
	 * @return
	 */
	private Order createOrder(OrderEnum orderEnum,UserAddress userAddress,Long totalPrice,Long cPrice,Long gcPrice,
							  Long actualPrice,List<OrderDetail>ods,List<OrderGoodsCoupon> ogcs){
		Order order=new Order();
		String orderNo=procedureToolsService.executeOrderNo(orderEnum);
		order.setOrderType(orderEnum.getKey());
		order.setOrderNo(orderNo);
		order.setOrderStatus(OrderConstant.ORDER_STATUS_CREATE);
		order.setOrderBuyUid(userAddress.getUaUid());
		//出售用户
		order.setOrderSellUid(com.retailers.dht.common.constant.SystemConstant.GOODS_SHOP_USER);
		order.setOrderCreateDate(new Date());
		order.setOrderUaId(userAddress.getUaId());
		order.setOrderUaName(userAddress.getUaName());
		order.setOrderUaPhone(userAddress.getUaPhone());
		order.setOrderUaAddress(userAddress.getUaAllAddress());
		order.setOrderBuyDel(SystemConstant.SYS_IS_DELETE_NO);
		order.setOrderSellDel(SystemConstant.SYS_IS_DELETE_NO);
		order.setIsReal(OrderConstant.ORDER_IS_REAL_YES);
		order.setOrderTradePrice(totalPrice);
		order.setOrderGoodsActualPayPrice(actualPrice);
		order.setOrderGoodsCouponPrice(cPrice);
		order.setOrderGoodsCouponPrice(gcPrice);
		orderMapper.saveOrder(order);
		long orderId=order.getId();
		for(OrderDetail od:ods){
			od.setOdOrderId(orderId);
		}
		for(OrderGoodsCoupon ogc:ogcs){
			ogc.setOcOrderId(orderId);
		}
		//批量添加订单详情
		orderDetailMapper.saveOrderDetails(ods);
		//批量添加订单商品优惠
		orderGoodsCouponMapper.saveOrderGoodsCoupons(ogcs);
		return order;
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
			order.setOrderStatus(OrderConstant.ORDER_STATUS_CREATE);
			//出售用户
			order.setOrderSellUid(com.retailers.dht.common.constant.SystemConstant.GOODS_SHOP_USER);
			//购买者
			order.setOrderBuyUid(uid);
			order.setOrderTradePrice(recharge.getRprice());
			order.setOrderGoodsActualPayPrice(recharge.getRprice());
			String illustrate="用户[{}]购买充值卡，充值金额：{}，充值后享受的折扣:{}";
			illustrate= StringUtils.formates(illustrate,uid, NumberUtils.formaterNumberPower(recharge.getRprice()),NumberUtils.formaterNumberPower(recharge.getRdiscount()));
			order.setOrderIllustrate(illustrate);
			order.setOrderCreateDate(curDate);
			orderMapper.saveOrder(order);
			rtn.put("orderNo",orderNo);
			rtn.put("price",NumberUtils.formaterNumberPower(recharge.getRprice()));
		}finally {
			logger.info("用户充值处理结束,执行时间:[{}]",(System.currentTimeMillis()-curDate.getTime()));
		}
		return rtn;
	}

	/**
	 * 修改订单状态
	 * @param opq
	 * @return
	 * @throws AppException
	 */
	public boolean updateOrderStatus(OrderProcessingQueue opq) throws AppException {
		return false;
	}

	/**
	 * 订单支付回调
	 * @param opq
	 * @return
	 * @throws AppException
	 */
	public boolean orderPayCallback(OrderProcessingQueue opq) throws AppException {
		Order order = orderMapper.queryOrderByOrderNo(opq.getOrderNo());
		Date curDate=new Date();
		//判断处理状态
		if(ObjectUtils.isNotEmpty(opq.getParams())){
			JSONObject obj = JSONObject.parseObject(opq.getParams());
			boolean isSuccess=false;
			Integer status=OrderConstant.ORDER_STATUS_PAY_FAILE;
			if(obj.containsKey("isSuccess")){
				isSuccess=obj.getBoolean("isSuccess");
				if(isSuccess){
					status=OrderConstant.ORDER_STATUS_PAY_SUCCESS;
				}
			}
			order.setOrderPayUseWay(obj.getInteger("payWay"));
			order.setOrderPayCallbackNo(obj.getString("tradeOffId"));
			order.setOrderPayCallbackDate(curDate);
			order.setOrderPayCallbackRemark(obj.getString("message"));
			order.setOrderStatus(status);
			long total=orderMapper.orderPayCallBack(order);
			if(total==0){
				throw new AppException("订单状态己变更");
			}
			if(isSuccess){
				//判断订单类型 充值 添加用户钱包
				if(order.getOrderType().equals(OrderEnum.RECHARGE.getKey())){
					UserCardPackage ucp=userCardPackageMapper.queryUserCardPackageById(order.getOrderBuyUid());
					//修改用户钱包
					long updateSize=userCardPackageMapper.userRechage(ucp.getId(),order.getOrderTradePrice(),ucp.getVersion());
					if(updateSize==0){
						throw new AppException("数据己变更");
					}
					String remark=StringUtils.formates("用户充值，充值金额:[{}],充值前金额:[{}]",NumberUtils.formaterNumberPower(order.getOrderTradePrice()),NumberUtils.formaterNumberPower(ucp.getUcurWallet()));
					addUserCardPackageLog(ucp.getId(),LogUserCardPackageConstant.USER_CARD_PACKAGE_TYPE_WALLET_IN,order.getId(),order.getOrderTradePrice(),ucp.getUcurWallet(),remark,curDate);
				}else{
					//判断是返现订单还是返积分订单
					if(order.getOrderIntegralOrCash().intValue()==OrderConstant.ORDER_RETURN_TYPE_INTEGRAL){
						UserCardPackage ucp=userCardPackageMapper.queryUserCardPackageById(order.getOrderBuyUid());
						//修改用户钱包
						long updateSize=userCardPackageMapper.userIntegral(ucp.getId(),order.getOrderTradePrice(),ucp.getVersion());
						if(updateSize==0){
							throw new AppException("数据己变更");
						}
						String remark=StringUtils.formates("用户购物返积分，返还积分:[{}],当前积分:[{}]",NumberUtils.formaterNumberPower(order.getOrderTradePrice()),NumberUtils.formaterNumberPower(ucp.getUtotalIntegral()));
						addUserCardPackageLog(ucp.getId(),LogUserCardPackageConstant.USER_CARD_PACKAGE_TYPE_INTEGRAL_OUT,order.getId(),order.getOrderTradePrice(),ucp.getUcurWallet(),remark,curDate);
					//订单返现
					}else{

					}
				}
			}
		}
		return true;
	}

	/**
	 *  添加用户卡包操作日志
	 * @param uid 用户id
	 * @param type 日志类型
	 * @param orderId 订单id
	 * @param val 变更值
	 * @param curVal 当前 值
	 * @param remark 备注
	 * @param curDate 当前日期
	 */
	private void addUserCardPackageLog(Long uid,int type,Long orderId,Long val,Long curVal,String remark,Date curDate){
		//添加用户钱包日志
		LogUserCardPackage lucp=new LogUserCardPackage();
		lucp.setUid(uid);
		lucp.setType(type);
		lucp.setRelationOrderId(orderId);
		lucp.setVal(val);
		lucp.setCurVal(curVal);
		lucp.setRemark(remark);
		lucp.setCreateTime(curDate);
		logUserCardPackageMapper.saveLogUserCardPackage(lucp);
	}
}

