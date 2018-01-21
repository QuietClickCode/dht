
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.constant.UserCardPackageConstant;
import com.retailers.dht.common.constant.OrderConstant;
import com.retailers.dht.common.dao.*;
import com.retailers.dht.common.entity.*;
import com.retailers.dht.common.service.*;
import com.retailers.dht.common.view.GoodsCouponView;
import com.retailers.dht.common.vo.*;
import com.retailers.mybatis.common.constant.SingleThreadLockConstant;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.common.dao.SysParameterConfigMapper;
import com.retailers.mybatis.common.enm.OrderEnum;
import com.retailers.mybatis.common.entity.SysParameterConfig;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.mybatis.common.service.SysParameterConfigService;
import com.retailers.mybatis.common.vo.SysParamVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
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
	private GoodsFreightService goodsFreightService;
    @Autowired
	private GoodsCouponService goodsCouponService;
    @Autowired
	private BuyCarService buyCarService;
	@Autowired
	private CutPriceLogService cutPriceLogService;
	@Autowired
	private GoodsGdsprelMapper goodsGdsprelMapper;
	@Autowired
	private GoodsIsbuycpService goodsIsbuycpService;
	@Autowired
	private  GoodsIsbuyspService goodsIsbuyspService;
	@Autowired
	private GoodsGdcprelService goodsGdcprelService;
	@Autowired
	private GoodsGdsprelService goodsGdsprelService;
	@Autowired
	private GoodsGgsvalDetailService goodsGgsvalDetailService;
	@Autowired
	private OrderSuccessQueueMapper orderSuccessQueueMapper;
	@Autowired
	private CouponUserMapper couponUserMapper;
	@Autowired
	private UserCardPackageService userCardPackageService;
	/**
	 * 系统常量设置
	 */
	@Autowired
	private SysParameterConfigMapper sysParameterConfigMapper;
	@Autowired
	private SysParameterConfigService sysParameterConfigService;

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
	 * @param buyInfos 购买详情
	 * @return
	 * @throws AppException
	 */
    @Transactional(rollbackFor = Exception.class)
	public Map<String,Object> shoppingOrder(Long uid, BuyInfoVo buyInfos,Long inviterUid,Long inviterGid)throws AppException{
        logger.info("创建购物订单,购买用户:[{}],商品列表:[{}]",uid,JSON.toJSON(buyInfos));
        Date curDate=new Date();
        String key=StringUtils.formate(SingleThreadLockConstant.USER_BUY_GOODS,uid+"");
        procedureToolsService.singleLockManager(key);
		Map<String,Object> rtnMap=new HashMap<String,Object>();
		String orderNo="";
        try{
			//取得用户地址
			UserAddress userAddress=checkUserAddress(uid,buyInfos.getAddress());
			//取得快递费
			GoodsFreight goodsFreight = goodsFreightService.queryFreightByAddress(userAddress.getUaAllAddress());
			long logisticsPrice=0l;
			if(ObjectUtils.isEmpty(goodsFreight)){
				logisticsPrice= SysParameterConfigConstant.getValue(SysParameterConfigConstant.DEFAULT_LOGISTICS_PRICE,Long.class);
			}else{
				logisticsPrice=goodsFreight.getGfPrice();
			}
			//规格使用的选择的优惠例表
			Map<Long,List<Long>> gcpMaps=new HashMap<Long, List<Long>>();
			//购买规格例表
			List<Long> buyGIds=new ArrayList<Long>();
			//规格ids
			String gdIds="";
			//取得购物车例表
			List<Long> carIds=new ArrayList<Long>();
			//购买商品例表
			List<Long> gids=new ArrayList<Long>();
			//购买商品例表属性
			List<GoodsTypePriceVo> buyDetailInfos = new ArrayList<GoodsTypePriceVo>();
			//规格对应的购买数量
			Map<Long,Long> gdidUnNum=new HashMap<Long, Long>();
			//规格id关联商品
			Map<Long,Long> gdidUnGid=new HashMap<Long, Long>();
			//取得购买商中使用了商品优惠的
			for(BuyGoodsDetailVo bgVo:buyInfos.getBuyGoods()){
				if(ObjectUtils.isNotEmpty(bgVo.getGcpIds())){
					String gcpIds=bgVo.getGcpIds();
					List<Long> gcps=new ArrayList<Long>();
					for(String gcp:gcpIds.split(",")){
						gcps.add(Long.parseLong(gcp));
					}
					gcpMaps.put(bgVo.getGdId(),gcps);
				}
				buyGIds.add(bgVo.getGdId());
				gdIds+=bgVo.getGdId()+",";
				carIds.add(bgVo.getBuyCarId());
				GoodsTypePriceVo gtpv=new GoodsTypePriceVo(bgVo.getGoodsId(),bgVo.getGdId(),null,null,bgVo.getNum().longValue());
				buyDetailInfos.add(gtpv);
				gdidUnNum.put(bgVo.getGdId(),-bgVo.getNum().longValue());
			}
			//从购物车中取得购买商品相应的推荐人
			Map<Long,Long> buyCarMaps=buyCarService.queryInviterIdByBcIds(carIds);
			//根据规格取得详细例表
			List<GoodsDetailVo> gds=goodsDetailService.queryGoodsDetailByGdIds(gdIds);
			//规格对应的价格
			Map<Long,Long> gdPrice=new HashMap<Long, Long>();
			//商品关联的商品种类
			Map<Long,Long> gidUnGtid=new HashMap<Long, Long>();
			//商品关联是否允许打折（会员购买时)
			Map<Long,Long> gidUnDiscount=new HashMap<Long, Long>();
			for(GoodsDetailVo gd:gds){
				gdPrice.put(gd.getGdId(),gd.getGdPrice());
				gidUnGtid.put(gd.getGid(),gd.getGclass());
				gidUnDiscount.put(gd.getGid(),gd.getIsMenberdiscount());
				gdidUnGid.put(gd.getGdId(),gd.getGid());
			}
			//取得商品优惠卷
			Map<String,Object> couponInfos = goodsCouponService.queryGoodsCouponLists(uid,buyInfos.getBuyGoods());

			//订单详情
			List<OrderDetail> ods=new ArrayList<OrderDetail>();
			//商品优惠使用情况
			List<OrderGoodsCoupon> ogcs=new ArrayList<OrderGoodsCoupon>();
			//订单总金额（未使用优惠卷，商品优惠）
			long totalPrice = 0;
			//实际支付金额
			long actualPrice=0;
			//生成订单详情
			for(BuyGoodsDetailVo bgVo:buyInfos.getBuyGoods()){
				OrderDetail od=new OrderDetail();
				od.setOdBuyNumber(bgVo.getNum());
				od.setOdGoodsId(gdidUnGid.get(bgVo.getGdId()));
				od.setRemark(bgVo.getRemark());
				//判断商品是否存在推荐人
				if(ObjectUtils.isNotEmpty(bgVo.getBuyCarId())){
					od.setOdInviterUid(buyCarMaps.get(bgVo.getBuyCarId()));
				}
				long p=gdPrice.get(bgVo.getGdId());
				od.setOdGoodsPrice(p*bgVo.getNum());
				od.setOdGdId(bgVo.getGdId());
				//设置会员是否打折
				od.setOdIsDiscount(gidUnDiscount.get(od.getOdGoodsId()));
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
				od.setOdActualPrice(p*bgVo.getNum());
				od.setOdMenberPrice(od.getOdActualPrice());
				ods.add(od);
			}
			long gcPrice=0l;
            //商品优惠
            if(ObjectUtils.isNotEmpty(gcpMaps)){
                Map<String,Object> rtnMaps= editorGoodsCoupon(ods,gcpMaps,(Map<String,List<GoodsCouponView>>)couponInfos.get("gcLists"));
                //判断商品优惠中是否存在包邮
                if(Boolean.valueOf(rtnMaps.get("isFreeShipping")+"")){
                    logisticsPrice=0l;
                }
                if(ObjectUtils.isNotEmpty(rtnMaps.get("couponTotalPrice"))){
					gcPrice=Long.valueOf(rtnMaps.get("couponTotalPrice")+"");
				}

            }
            //取得使用的优惠卷
            String couponIds=buyInfos.getCpIds();
            List<Long> cpuIds=new ArrayList<Long>();
			//商品优惠金额
			Long cpPrice=0l;
            //判断是否使用优惠卷
            if(ObjectUtils.isNotEmpty(couponIds)){
            	List<Long> cids=new ArrayList<Long>();
            	for(String cid:couponIds.split(",")){
            		cids.add(Long.parseLong(cid));
				}
            	//判断用户是否存在优惠卷
            	if(ObjectUtils.isNotEmpty(couponInfos.get("userCoupons"))){
					//用户拥有的优惠卷
					List<CouponWebVo> cwvs=(List<CouponWebVo>)couponInfos.get("userCoupons");
					Map<Long,Long> maps=editorUserCoupon(ods,cids,gidUnGtid,cwvs);
					List<Long> used=new ArrayList<Long>();
					for(Long cid:maps.keySet()){
						used.add(cid);
						cpPrice+=maps.get(cid);
					}
					cpuIds.addAll(used);
				}
            }

            //计算商品实际价格
            for(OrderDetail od:ods){
				totalPrice+=od.getOdGoodsPrice();
				actualPrice+=od.getOdActualPrice();
			}
			Order order =createOrder(OrderEnum.SHOPPING,userAddress,totalPrice,cpPrice,gcPrice,actualPrice,logisticsPrice,ods,ogcs);
			//批量添加优惠卷
			orderNo=order.getOrderNo();
			//清除购物车数据
			boolean flag =buyCarService.updateBuyCatHadBuy(carIds);
			if(!flag){
				logger.error("清除购物车异常");
				throw new AppException("创建订单异常");
			}
			//修改商品库存
			boolean success =goodsGgsvalDetailService.editGoodsInventorys(gdidUnNum);
			if(!success){
				logger.error("修改商品库存失败，请重试");
				throw new AppException("下单失败");
			}
			if(ObjectUtils.isNotEmpty(cpuIds)){
				//设置用户使用优惠卷
				long total=couponUserMapper.useCouponByOIds(uid,order.getId(),cpuIds,curDate);
				if(cpuIds.size()!=total){
					throw new AppException("优惠卷己被使用");
				}
			}
			rtnMap.put("orderNo",orderNo);
			rtnMap.put("totalPrice",order.getOrderTradePrice());
			rtnMap.put("type",order.getOrderType());
		}finally {
        	logger.info("创建购物订单完毕，执行时间：[{}]",(System.currentTimeMillis()-curDate.getTime()));
			logger.info("创建购物订单完成，生成订单号:[{}],执行时间:[{}]",orderNo,(System.currentTimeMillis()-curDate.getTime()));
			procedureToolsService.singleUnLockManager(key);
		}
		return rtnMap;
	}

	/**
	 *购买特价商品
	 * @param uid
	 * @param buyInfos
	 * @param inviterUid
	 * @return
	 * @throws AppException
	 */
	public Map<String, Object> buySpecialOfferGoods(Long uid, BuyInfoVo buyInfos,Long inviterUid,Long cspId) throws AppException {
		logger.info("购买特价商品,购买用户:[{}],商品列表:[{}]",uid,JSON.toJSON(buyInfos));
		Date curDate=new Date();
		String key=StringUtils.formate(SingleThreadLockConstant.USER_BUY_GOODS,uid+"");
		procedureToolsService.singleUnLockManager(key);
		Map<String,Object> rtnMap=new HashMap<String,Object>();
		try{
			Order order = createCouponOrder(uid,buyInfos,inviterUid,OrderEnum.SPECIAL_OFFER,cspId);
			rtnMap.put("orderNo",order.getOrderNo());
			rtnMap.put("totalPrice",order.getOrderTradePrice());
			rtnMap.put("type",order.getOrderType());
		}finally {
			procedureToolsService.singleUnLockManager(key);
			logger.info("执行时间：[{}]",(System.currentTimeMillis()-curDate.getTime()));
		}
		return rtnMap;
	}
	/**
	 *购买秒杀商品
	 * @param uid
	 * @param buyInfos
	 * @param inviterUid 是否是推荐商品
	 * @return
	 * @throws AppException
	 */
	public Map<String, Object> buySeckillGoods(Long uid, BuyInfoVo buyInfos, Long inviterUid,Long cspId) throws AppException {
		logger.info("购买秒杀商品,购买用户:[{}],商品列表:[{}]",uid,JSON.toJSON(buyInfos));
		Date curDate=new Date();
		String key=StringUtils.formate(SingleThreadLockConstant.USER_BUY_GOODS,uid+"");
		procedureToolsService.singleUnLockManager(key);
		Map<String,Object> rtnMap=new HashMap<String,Object>();
		try{
			Order order = createCouponOrder(uid,buyInfos,inviterUid,OrderEnum.SECKILL,cspId);
			rtnMap.put("orderNo",order.getOrderNo());
			rtnMap.put("totalPrice",order.getOrderTradePrice());
			rtnMap.put("type",order.getOrderType());
		}finally {
			procedureToolsService.singleUnLockManager(key);
			logger.info("执行时间：[{}]",(System.currentTimeMillis()-curDate.getTime()));
		}
		return rtnMap;
	}

	/**
	 *购买砍价商品
	 * @param uid
	 * @param buyInfos
	 * @return
	 * @throws AppException
	 */
	public Map<String, Object> buyCutPrice(Long uid, BuyInfoVo buyInfos,Long cspId) throws AppException {
		logger.info("购买秒杀商品,购买用户:[{}],商品列表:[{}]",uid,JSON.toJSON(buyInfos));
		Date curDate=new Date();
		String key=StringUtils.formate(SingleThreadLockConstant.USER_BUY_GOODS,uid+"");
		procedureToolsService.singleUnLockManager(key);
		Map<String,Object> rtnMap=new HashMap<String,Object>();
		try{
			UserAddress userAddress=checkUserAddress(uid,buyInfos.getAddress());
			//取得快递费
			GoodsFreight goodsFreight = goodsFreightService.queryFreightByAddress(userAddress.getUaAllAddress());
			long logisticsPrice=0l;
			if(ObjectUtils.isEmpty(goodsFreight)){
				logisticsPrice= SysParameterConfigConstant.getValue(SysParameterConfigConstant.DEFAULT_LOGISTICS_PRICE,Long.class);
			}else{
				logisticsPrice=goodsFreight.getGfPrice();
			}
			Long gdId=null;
			Integer num=null;
			Long gid=null;
			String remark="";
			for(BuyGoodsDetailVo bgd:buyInfos.getBuyGoods()){
				gdId=bgd.getGdId();
				num=bgd.getNum();
				gid=bgd.getGoodsId();
				remark=bgd.getRemark();
			}
			//取得商品价格
			Map<String,Long> cutLog=cutPriceLogService.queryCutpriceByGdId(gdId,uid);
			//判断购买数量是否大于限制数量
			if(ObjectUtils.isEmpty(cutLog.get("cpInventory"))){
				logger.info("未设置砍价商品购买最大数量");
				throw new AppException("商品购买异常");
			}
			if(cutLog.get("cpInventory").intValue()<num.intValue()){
				throw new AppException("购买超限");
			}
			List<OrderDetail> ods=new ArrayList<OrderDetail>();
			OrderDetail  od=new OrderDetail();
			od.setOdGoodsId(gid);
			od.setOdGdId(gdId);
			od.setOdBuyNumber(num);
			od.setRemark(remark);
			od.setOdIsDiscount(cutLog.get("isMenberdiscount"));
			od.setOdGoodsPrice(cutLog.get("gdPrice"));
			od.setOdActualPrice((cutLog.get("gdPrice")-cutLog.get("finalPrice"))*num);
			od.setOdMenberPrice(od.getOdActualPrice());
			ods.add(od);
			Long total =cutLog.get("gdPrice")*num;
			Long actualPrice =(cutLog.get("gdPrice")-cutLog.get("finalPrice"))*num;
			Long gcPice=cutLog.get("finalPrice")*num;
			Order order=createOrder(OrderEnum.CUT_PRICE,userAddress,total,0l,gcPice,actualPrice,logisticsPrice,ods,null);
			//设置购买日志
			GoodsIsbuycp gibcp=new GoodsIsbuycp();
			gibcp.setCpId(cspId);
			gibcp.setUid(uid);
			gibcp.setIsDelete((long)SystemConstant.SYS_IS_DELETE_NO);
			goodsIsbuycpService.saveGoodsIsbuycp(gibcp);
			//修改库存数量
			Map<Long,Long> gdidUnNm=new HashMap<Long, Long>();
			gdidUnNm.put(gdId,-num.longValue());
			boolean success = goodsGdcprelService.editGoodsInventorys(gdidUnNm);
			if(!success){
				logger.error("修改商品库存失败，请重试");
				throw new AppException("下单失败");
			}
			rtnMap.put("orderNo",order.getOrderNo());
			rtnMap.put("totalPrice",order.getOrderTradePrice());
			rtnMap.put("type",order.getOrderType());
		}finally {
			procedureToolsService.singleUnLockManager(key);
			logger.info("执行时间：[{}]",(System.currentTimeMillis()-curDate.getTime()));
		}
		return rtnMap;
	}

	/**
	 * 创建订单
	 * @param orderEnum 订单类型
	 * @param userAddress 用户收货地址
	 * @param gTotalPrice 商品总额
	 * @param cPrice 优惠卷金额
	 * @param gcPrice 商品优惠金额
	 * @param actualPrice 实际金额
	 * @param orderLogisticsPrice 物流费
	 * @param ods 商品详情
	 * @param ogcs 商品优惠详情
	 * @return
	 */
	private Order createOrder(OrderEnum orderEnum,UserAddress userAddress,Long gTotalPrice,Long cPrice,Long gcPrice,
							  Long actualPrice,Long orderLogisticsPrice,List<OrderDetail>ods,List<OrderGoodsCoupon> ogcs){
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
		order.setOrderTradePrice(actualPrice+orderLogisticsPrice);
		order.setOrderGoodsTotalPrice(gTotalPrice);
		order.setOrderGoodsActualPayPrice(actualPrice);
		order.setOrderMenberPrice(actualPrice);
		order.setOrderGoodsCouponPrice(cPrice);
		order.setOrderCouponPrice(gcPrice);
		order.setOrderLogisticsPrice(orderLogisticsPrice);
		orderMapper.saveOrder(order);
		long orderId=order.getId();
		for(OrderDetail od:ods){
			od.setOdOrderId(orderId);
		}
		//批量添加订单详情
		orderDetailMapper.saveOrderDetails(ods);
		if(ObjectUtils.isNotEmpty(ogcs)){
			for(OrderGoodsCoupon ogc:ogcs){
				ogc.setOcOrderId(orderId);
			}
			//批量添加订单商品优惠
			orderGoodsCouponMapper.saveOrderGoodsCoupons(ogcs);
		}
		return order;
	}

	/**
	 * 用户充值
	 * @param uid 用户id
	 * @param rid 充值id
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
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
            order.setOrderBuyDel(SystemConstant.SYS_IS_DELETE_NO);
            order.setIsReal(OrderConstant.ORDER_IS_REAL_YES);
            order.setOrderSellDel(SystemConstant.SYS_IS_DELETE_NO);
			orderMapper.saveOrder(order);
			OrderDetail orderDetail=new OrderDetail();
			orderDetail.setOdOrderId(order.getId());
			orderDetail.setOdGoodsId(recharge.getRid());
			orderDetail.setOdBuyNumber(1);
			orderDetail.setRemark("用户购买充值卡");
			orderDetail.setOdActualPrice(recharge.getRprice());
			orderDetail.setOdGoodsPrice(recharge.getRprice());
			orderDetail.setOdIsRefund(OrderConstant.ORDER_REFUND_STATUS_UN);
			orderDetailMapper.saveOrderDetail(orderDetail);
			rtn.put("orderNo",orderNo);
			rtn.put("price",recharge.getRprice());
			rtn.put("type",order.getOrderType());
		}finally {
			logger.info("用户充值处理结束,执行时间:[{}],返回数据：[{}]",(System.currentTimeMillis()-curDate.getTime()),JSON.toJSON(rtn));
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
		Order order = orderMapper.queryOrderByOrderNo(opq.getOrderNo());
		if(ObjectUtils.isNotEmpty(order)){
			JSONObject obj = JSONObject.parseObject(opq.getParams());
			order.setOrderPayUseWay(obj.getInteger("orderPayUseWay"));
			order.setOrderPayWay(obj.getInteger("orderPayWay"));
			order.setOrderPayDate(obj.getDate("orderPayDate"));
			orderMapper.updateOrder(order);
		}
		return true;
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
		try{
			//判断处理状态
			if(ObjectUtils.isNotEmpty(opq.getParams())){
				JSONObject obj = JSONObject.parseObject(opq.getParams());
				boolean isSuccess=false;
				Integer status=OrderConstant.ORDER_STATUS_PAY_FAILE;
				if(obj.containsKey("isSuccess")){
					isSuccess=obj.getBoolean("isSuccess");
					if(isSuccess){
						status=OrderConstant.ORDER_STATUS_PAY_SUCCESS;
                        //充值订单直接设置发货且确认
                        if(order.getOrderType().equals(OrderEnum.RECHARGE.getKey())){
                            status=OrderConstant.ORDER_STATUS_PAY_END;
                        }
					}
				}
				//设置支付通道（0 微信，1 支付宝，2 钱包)
				order.setOrderPayWay(obj.getInteger("orderPayWay"));
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
						//取得用户卡包
						UserCardPackage ucp=userCardPackageMapper.queryUserCardPackageById(order.getOrderBuyUid());
						//修改用户钱包
						long updateSize=userCardPackageMapper.userRechage(ucp.getId(),order.getOrderTradePrice(),ucp.getVersion());
						if(updateSize==0){
							throw new AppException("数据己变更");
						}
						List<OrderDetail> ods=orderDetailMapper.queryOrderDetailByOdId(order.getId());
						Long rechageId=null;
						for(OrderDetail od:ods){
							rechageId=od.getOdGoodsId();
						}
						String remark=StringUtils.formates("用户充值，充值金额:[{}],充值前金额:[{}]",NumberUtils.formaterNumberPower(order.getOrderTradePrice()),NumberUtils.formaterNumberPower(ucp.getUcurWallet()));
						userCardPackageService.addUserCardPackageLog(ucp.getId(), UserCardPackageConstant.USER_CARD_PACKAGE_TYPE_WALLET_IN,order.getId(),order.getOrderTradePrice(),ucp.getUcurWallet(),remark,curDate);
						User user=userMapper.queryUserByUid(order.getOrderBuyUid());
						user.setUrechage(rechageId);
						//修改用户会员类型
						userMapper.editorCustomerType(user.getUid(),rechageId,user.getVersion());
					}else{
//						UserCardPackage ucp=userCardPackageMapper.queryUserCardPackageById(order.getOrderBuyUid());
//						//返积分
//						if(order.getOrderIntegralOrCash().intValue()==OrderConstant.ORDER_RETURN_TYPE_INTEGRAL){
//							//修改用户消费
//							long updateSize=userCardPackageMapper.userIntegral(ucp.getId(),order.getOrderTradePrice(),ucp.getVersion());
//							if(updateSize==0){
//								throw new AppException("数据己变更");
//							}
//							String remark=StringUtils.formates("用户购物返积分，返还积分:[{}],当前积分:[{}]",NumberUtils.formaterNumberPower(order.getOrderTradePrice()),NumberUtils.formaterNumberPower(ucp.getUtotalIntegral()));
//							addUserCardPackageLog(ucp.getId(),LogUserCardPackageConstant.USER_CARD_PACKAGE_TYPE_INTEGRAL_OUT,order.getId(),order.getOrderTradePrice(),ucp.getUcurWallet(),remark,curDate);
//							//订单返现
//							generateCashBack(order);
//							//返现
//						}else if(order.getOrderPayWay().intValue()==OrderConstant.ORDER_PAY_WAY_WALLET){
//							//修改用户消费
//							long updateSize=userCardPackageMapper.userWalletConsume(ucp.getId(),order.getOrderTradePrice(),ucp.getVersion());
//							if(updateSize==0){
//								throw new AppException("数据己变更");
//							}
//							//订单返现
//							generateCashBack(order);
//						}
						addOrderSuccessQueue(order.getId());
					}
				}
			}
		}catch(DuplicateKeyException e){
			throw new AppException(e.getMessage());
		}
		return true;
	}

	/**
	 * 生成返现队例
	 * @param order
	 */
	private void generateCashBack(Order order){
		//取得订单例表
		List<OrderDetail> ods=orderDetailMapper.queryOrderDetailByOdId(order.getId());
		List<WalletCashBackQueue> list = new ArrayList<WalletCashBackQueue>();
		WalletCashBackQueue wcbq=new WalletCashBackQueue();
	}
	/**
	 * 校验收货地址
	 * @param uid 用户
	 * @param addressId 收货人地址id
	 * @return
	 * @throws AppException
	 */
	private UserAddress checkUserAddress(Long uid,Long addressId)throws AppException{
		//取得用户地址
		UserAddress userAddress=userAddressMapper.queryUserAddressByUaId(addressId);
		if(ObjectUtils.isEmpty(userAddress)){
			throw new AppException("请填写收货人地址");
		}
		//判断用户地址是否异常
		if(userAddress.getUaUid().intValue()!=uid.intValue()){
			throw new AppException("请填写收货人地址");
		}
		return userAddress;
	}

	/**
	 * 创建优惠订单
	 * @param uid 用户id
	 * @param buyInfos 购买信息
	 * @param inviterUid 是否分享
	 * @param orderEnum 订单类型
	 * @return
	 * @throws AppException
	 */
	private Order createCouponOrder(Long uid,BuyInfoVo buyInfos,Long inviterUid,OrderEnum orderEnum,Long cspId)throws AppException{
		UserAddress userAddress=checkUserAddress(uid,buyInfos.getAddress());
		//取得快递费
		GoodsFreight goodsFreight = goodsFreightService.queryFreightByAddress(userAddress.getUaAllAddress());
		long logisticsPrice=0l;
		if(ObjectUtils.isEmpty(goodsFreight)){
			logisticsPrice= SysParameterConfigConstant.getValue(SysParameterConfigConstant.DEFAULT_LOGISTICS_PRICE,Long.class);
		}else{
			logisticsPrice=goodsFreight.getGfPrice();
		}
		if(buyInfos.getBuyGoods().size()>1){
			throw new AppException("特价/秒杀商品只能单一购买");
		}
		BuyGoodsDetailVo bgd=buyInfos.getBuyGoods().get(0);
		GoodsGdsprelVo goodsGdsprel=goodsGdsprelMapper.queryGoodsGdsprelVoByGdspId(bgd.getGdId());
		if(ObjectUtils.isEmpty(goodsGdsprel)){
			throw new AppException("购买商品异常");
		}
		//判断是否存在购买限制
		if(ObjectUtils.isNotEmpty(goodsGdsprel.getSpBounds())){
			if(bgd.getNum()>goodsGdsprel.getSpBounds()){
				throw new AppException("超过购买限制，限购数量"+goodsGdsprel.getSpBounds());
			}
		}
		Map<Long,Long> gdidUnNum=new HashMap<Long, Long>();
		gdidUnNum.put(bgd.getGdId(),-bgd.getNum().longValue());
		//取得商品价格
		List<OrderDetail> ods=new ArrayList<OrderDetail>();
		long totalPrice=0l;
		totalPrice=goodsGdsprel.getSpSale()*bgd.getNum();
		OrderDetail  od=new OrderDetail();
		od.setOdGoodsId(bgd.getGoodsId());
		od.setOdGdId(goodsGdsprel.getGdspId());
		od.setOdBuyNumber(bgd.getNum());
		od.setRemark(bgd.getRemark());
		od.setOdGoodsPrice(totalPrice);
		od.setOdActualPrice(totalPrice);
		od.setOdMenberPrice(totalPrice);
		od.setOdIsDiscount(goodsGdsprel.getIsMenberdiscount());
		od.setOdInviterUid(inviterUid);
		ods.add(od);
		Order order=createOrder(orderEnum,userAddress,totalPrice,0l,0l,totalPrice,logisticsPrice,ods,null);
		//添加购买日志
		GoodsIsbuysp gibsp=new GoodsIsbuysp();
		gibsp.setUid(uid);
		gibsp.setSpId(cspId);
		gibsp.setIsDelete((long)SystemConstant.SYS_IS_DELETE_NO);
		//保存 商品购买日志
		goodsIsbuyspService.saveGoodsIsbuysp(gibsp);
		//变更现有商品库存
		boolean success=goodsGdsprelService.editGoodsInventorys(gdidUnNum);
		if(!success){
			logger.error("修改商品库存失败，请重试");
			throw new AppException("下单失败");
		}
		return order;
	}

    /**
     * 根据用户选择商品优惠，设置商品实际价格
     * @param ods 商品详情
     * @param useGc 用户使用商品优惠（根据gdid 对应使用的商品优惠）
     * @param allowGc 商品上挂的优惠活动
     */
	public Map<String,Object> editorGoodsCoupon(List<OrderDetail> ods,Map<Long,List<Long>>useGc,Map<String,List<GoodsCouponView>>allowGc)throws AppException{
		Map<String,Object> rtnMaps=new HashMap<String,Object>();
	    //是否包邮
	    boolean isFreeShipping=false;
	    long couponTotalPrice=0l;
	    Map<Long,Map<Long,GoodsCouponView>> allowGcMaps=new HashMap<Long, Map<Long, GoodsCouponView>>();
	    for(String key:allowGc.keySet()){
	        Map<Long,GoodsCouponView> allow=new HashMap<Long, GoodsCouponView>();
	        if(ObjectUtils.isNotEmpty(allowGc.get(key))){
	            for(GoodsCouponView gcv:allowGc.get(key)){
                    allow.put(gcv.getGcpId(),gcv);
                }
            }
            allowGcMaps.put(Long.parseLong(key),allow);
        }
        StringBuffer error=new StringBuffer();
        //判断用户使用商品优惠是否合规格  商品上使用的优惠卷
        for(Long key:useGc.keySet()){
	        if(allowGcMaps.containsKey(key)){
                if(ObjectUtils.isNotEmpty(useGc.get(key))){
                    for(Long gcId:useGc.get(key)){
                        if(!allowGcMaps.get(key).containsKey(gcId)){
                            error.append("商品id[]"+key+"使用不合规优惠卷["+gcId+"]\r\n");
                        }
                    }
                }
            }else{
	            error.append("商品id["+key+"]使用不合规优惠卷");
            }
        }
        if(ObjectUtils.isNotEmpty(error)){
            throw new AppException(error.toString());
        }
        //计算商品使用优惠卷后的金额
        for(OrderDetail od:ods){
            //判断是否使用优惠卷
            if(useGc.containsKey(od.getOdGdId())){
                //计算金额
                List<Long> gcs=useGc.get(od.getOdGdId());
                Map<Long,GoodsCouponView> gcsMap=allowGcMaps.get(od.getOdGdId());
                long gPirce=od.getOdActualPrice();
                for(Long gc:gcs){
                    GoodsCouponView gcv=gcsMap.get(gc);
                    if(ObjectUtils.isNotEmpty(gcv)){
                        if(gcv.getGcpType().intValue()== CouponConstant.GCP_TYPE_MONEY){
                            gPirce=gPirce-gcv.getLongVal();
                        }else if(gcv.getGcpType().intValue()==CouponConstant.GCP_TYPE_DISCOUNT){
                            double p=NumberUtils.priceChangeYuan(gPirce)*NumberUtils.priceChangeYuan(gcv.getLongVal());
                            gPirce=NumberUtils.priceChangeFen(NumberUtils.formaterNumber(p,2));
                        }else if(gcv.getGcpType().intValue()==CouponConstant.GCP_TYPE_FREE_SHIPPING){
                            isFreeShipping=true;
                        }
                    }
                }

                if(gPirce<0){
					gPirce=0;
				}
				couponTotalPrice+=od.getOdActualPrice()-gPirce;
                od.setOdActualPrice(gPirce);
                od.setOdMenberPrice(gPirce);
            }
        }
		rtnMaps.put("isFreeShipping",isFreeShipping);
		rtnMaps.put("couponTotalPrice",couponTotalPrice);
        return rtnMaps;
    }

	/**
	 * 用户优惠卷使用
	 * @param ods 订单详情
	 * @param useCoupon 使用的优惠卷
	 * @param gidUnGt 商品id对应的商品类型
	 * @param gidUnGt 商品关联商品种类
	 * @param userHave 用户拥有的优惠卷
	 * @throws AppException
	 */
    private Map<Long,Long> editorUserCoupon(List<OrderDetail> ods,List<Long> useCoupon,Map<Long,Long> gidUnGt,List<CouponWebVo> userHave)throws AppException{
		Map<Long,Long> rtnMaps=new HashMap<Long, Long>();
    	Map<Long,CouponWebVo> userCouponMaps=new HashMap<Long, CouponWebVo>();
    	for(CouponWebVo cwv:userHave){
			userCouponMaps.put(cwv.getCpId(),cwv);
		}
		StringBuffer error=new StringBuffer();
    	int djgs=0;
		for(Long uc:useCoupon){
    		if(!userCouponMaps.containsKey(uc)){
				error.append("无法使用优惠卷"+uc);
			}
			CouponWebVo cwv=userCouponMaps.get(uc);
    		if(cwv.getCpIsOverlapUse().intValue()==0){
				djgs++;
			}
		}
		if(!ObjectUtils.isEmpty(error)){
			throw new AppException(error.toString());
		}
		if(useCoupon.size()>djgs+1){
			throw new AppException("优惠卷选择错误 ，使用了不能叠加使用的优惠卷");
		}
		//根据使用的优惠卷重新计算商品价格
		for(Long uc:useCoupon){
			CouponWebVo cwv=userCouponMaps.get(uc);
			long jmje=0l;
			if(cwv.getCpIsRestricted()==CouponConstant.COUPON_USED_RANGE_ALL&&
					ObjectUtils.isEmpty(cwv.getgIds())&&
					ObjectUtils.isEmpty(cwv.getGgIds())){
				jmje=goodsCoupon(ods,cwv,false,gidUnGt);
			}else{
				jmje=goodsCoupon(ods,cwv,true,gidUnGt);
			}
			rtnMaps.put(cwv.getCpuId(),jmje);
		}
		return rtnMaps;
    }

	/**
	 * 商品使用优惠卷 价格计算
	 * @param ods 购买商品详情
	 * @param cwv 优惠卷详情
	 * @param isXz 是否限制使用
	 * @param gidUnGt 商品id关联商品类型
	 * return 返回优惠金额
	 */
    private Long goodsCoupon(List<OrderDetail> ods,CouponWebVo cwv,boolean isXz,Map<Long,Long> gidUnGt){
		//返回优惠金额
		long rtnWallet=0l;
		//商品种类
		List<Long> spzl=new ArrayList<Long>();
		//商品id
		List<Long> spid=new ArrayList<Long>();
		if(ObjectUtils.isNotEmpty(cwv.getGgIds())){
			for(String ggid:cwv.getGgIds().split(",")){
				spzl.add(Long.parseLong(ggid));
			}
		}
		//取得商品允许使用的优惠卷
		if(ObjectUtils.isNotEmpty(cwv.getgIds())){
			for(String gid:cwv.getgIds().split(",")){
				spid.add(Long.parseLong(gid));
			}
		}
		//取得名个优惠卷优惠额度
		long couponTotalPrice=0l;
		//剩余金额
		long syCouponTotalPrice=0l;
		//购买商品总额
		long buyGoodsPrice=0l;
		Map<Long,Long> oidPrice=new HashMap<Long, Long>();
		//允许使用传入优惠卷的商品
		List<Long> allowPrice=new ArrayList<Long>();
		if(cwv.getCpType().intValue()==CouponConstant.GCP_TYPE_MONEY){
			couponTotalPrice=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(cwv.getCouponVal()));
			syCouponTotalPrice=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(cwv.getCouponVal()));
			for(OrderDetail od:ods){
				//根据商品id取得商品类型id
				Long gtid=gidUnGt.get(od.getOdGoodsId());
				if(!isXz||(spzl.contains(gtid)||spid.contains(od.getOdGoodsId()))){
					buyGoodsPrice=od.getOdActualPrice();
					oidPrice.put(od.getId(),od.getOdActualPrice());
					allowPrice.add(od.getId());
				}
			}
			//循环取得减免金额
			for(int i=0;i<allowPrice.size();i++){
				Long odId=allowPrice.get(i);
				//最后一条
				if(i==allowPrice.size()-1){
					oidPrice.put(odId,syCouponTotalPrice);
				}else{
					//本次金额
					long price=oidPrice.get(odId);
					//根据购买金额取得减免金额
					long jmje=getJmje(price,buyGoodsPrice,couponTotalPrice);
					if(jmje==0){
						oidPrice.remove(odId);
					}else{
						oidPrice.put(odId,jmje);
						syCouponTotalPrice=syCouponTotalPrice-jmje;
					}
				}
			}
		}

		//设置商品价格
		for(OrderDetail od:ods){
			long goodsCurPrice=od.getOdActualPrice();
			//根据商品id取得商品类型id
			Long gtid=gidUnGt.get(od.getOdGoodsId());
			if(!isXz||(spzl.contains(gtid)||spid.contains(od.getOdGoodsId()))){
				if(cwv.getCpType().intValue()==CouponConstant.GCP_TYPE_MONEY){
					goodsCurPrice=goodsCurPrice-oidPrice.get(od.getId());
				}else{
					goodsCurPrice=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(goodsCurPrice*NumberUtils.formaterNumberr(cwv.getCouponVal())));
				}
			}
			rtnWallet+=od.getOdActualPrice()-goodsCurPrice;
			od.setOdActualPrice(goodsCurPrice);
			od.setOdMenberPrice(goodsCurPrice);
		}
		return rtnWallet;
	}

	/**
	 * 取得优惠卷减免金额
	 * @param buyPrice
	 * @param totalPrice
	 * @param couponPrice
	 * @return
	 */
	private long getJmje(long buyPrice,long totalPrice,long couponPrice){
		long jmje=0l;
		if(totalPrice==0){
			return jmje;
		}
		if(buyPrice==0){
			return jmje;
		}
		//取得购买比例
		double bl=((double)buyPrice/(double)totalPrice);
		//取得减免金额
		jmje=(long) bl*couponPrice;
		return jmje;
	}
    /**
     * 钱包支付
     * @param uid 用户id
     * @param orderNo 订单号
     * @return
     * @throws AppException
     */
    @Transactional(rollbackFor = Exception.class)
    public boolean walletPay(Long uid, String orderNo,String payPwd) throws AppException {
        logger.info("开始执行钱包支付，支付单号:[{}],支付用户:[{}]",orderNo,uid);
        String lockKey=StringUtils.formate(SingleThreadLockConstant.WALLET_ORDER_PAY,uid+"");
        procedureToolsService.singleLockManager(lockKey);
		Date curDate=new Date();
        try{
            Order order=orderMapper.queryOrderByOrderNo(orderNo);
            if(ObjectUtils.isEmpty(order)){
                throw new AppException("支付订单不存在");
            }
            if(order.getOrderBuyUid().intValue()!=uid.intValue()){
                throw new AppException("不能支付他人订单");
            }
            if(order.getOrderType().equals(OrderEnum.RECHARGE.getKey())){
				throw new AppException("充值订单不能使用钱包支付");
			}
            User user=userMapper.queryUserByUid(uid);
            if(ObjectUtils.isEmpty(user.getUpayPwd())){
                throw new AppException("未设置支付密码，请设置支付密码");
            }
            String md5Pwd= Md5Encrypt.md5(StringUtils.concat(user.getUid()+"",payPwd));
            if(!md5Pwd.equals(user.getUpayPwd())){
                throw new AppException("支付密码错误");
            }
			//取得用户折扣 取得用户有充值记录
			Recharge recharge=rechargeMapper.queryUserBuyRecharge(user.getUrechage());
			//支付方式（钱包支付)
			order.setOrderPayWay(OrderConstant.ORDER_PAY_WAY_WALLET);
			//支付时间
			order.setOrderPayCallbackDate(curDate);
			//支付单号
			order.setOrderPayCallbackNo(StringUtils.formate(order.getOrderNo(),"wallet"));

			String discountRemark="活动购买，不享受折扣";
			if(ObjectUtils.isEmpty(recharge)){
				order.setOrderIntegralOrCash(OrderConstant.ORDER_RETURN_TYPE_INTEGRAL);
				discountRemark="老用户钱包购买不享受折扣";
			}else{
				//取得是否返现 0 不返现，1 返现
				int rcashback =recharge.getRcashback();
				//设置订单是否返现
				order.setOrderIntegralOrCash(rcashback);
			}
			long payPrice=order.getOrderGoodsActualPayPrice()+order.getOrderLogisticsPrice();
			//判断是否是一般购买 且享受折扣
			if(order.getOrderType().equals(OrderEnum.SHOPPING.getKey())&&ObjectUtils.isNotEmpty(recharge)){
				//取得是否返现 0 不返现，1 返现
				int rcashback =recharge.getRcashback();
				//设置订单是否返现
				order.setOrderIntegralOrCash(rcashback);
				//取得用户的折扣
				long discount=recharge.getRdiscount();
				//设置订单折扣
				order.setOrderDiscount(discount);
				//钱包支付实际支付金额
				Long total =0l;
				//取得用户享受折扣
				List<OrderDetail> ods=orderDetailMapper.queryOrderDetailByOdId(order.getId());
				for(OrderDetail od:ods){
					long actualPrice=od.getOdActualPrice();
					//判断该件商品是否支持折扣
					if(od.getOdIsDiscount().intValue()== OrderConstant.BUY_GOODS_MENBER_DISCOUNT_YES){
						actualPrice = NumberUtils.calculationDiscountPrice(actualPrice,discount);
					}
					od.setOdMenberPrice(actualPrice);
					total+=actualPrice;
				}
				//判断购买是否产生邮费
				if(ObjectUtils.isNotEmpty(order.getOrderLogisticsPrice())&&order.getOrderLogisticsPrice().intValue()>0){
					order.setOrderLogisticsPrice(NumberUtils.calculationDiscountPrice(order.getOrderLogisticsPrice(),discount));
				}else{
					order.setOrderLogisticsPrice(0l);
				}
				//设置购买商品详情订单
				orderDetailMapper.updateOrderDetails(ods);
				//用户支付金额
				payPrice=total+order.getOrderLogisticsPrice();
				order.setOrderMenberPrice(total);
				discountRemark="会员享受折扣:"+NumberUtils.priceChangeYuan(discount);
			}
			//取得用户卡包
			UserCardPackage ucp=userCardPackageMapper.queryUserCardPackageById(uid);
			if(ObjectUtils.isEmpty(ucp.getUcurWallet())||ucp.getUcurWallet().intValue()<order.getOrderTradePrice().intValue()){
				throw new AppException("钱包余额不足，请换其他支付");
			}
			//修改用户钱包
			long updateSize=userCardPackageMapper.userWalletPay(ucp.getId(),order.getOrderTradePrice(),ucp.getVersion());
			if(updateSize==0){
				logger.info("钱包支付失败，余额不足");
				throw new AppException("钱包余额不足，请换其他支付");
			}
			String orderRemark="用户平台购物，消耗用户钱包，使用金额:"+NumberUtils.formaterNumberPower(order.getOrderTradePrice())+","+discountRemark;
			//添加钱包使用日志
			userCardPackageService.addUserCardPackageLog(uid, UserCardPackageConstant.USER_CARD_PACKAGE_TYPE_WALLET_OUT,order.getId(),payPrice,ucp.getUcurWallet(),orderRemark,curDate);
			order.setOrderPayCallbackRemark("用户钱包支付，原价，"+order.getOrderTradePrice()+",会员折扣,"+discountRemark+",实际支付："+payPrice);
			order.setOrderStatus(OrderConstant.ORDER_STATUS_PAY_SUCCESS);
			orderMapper.updateOrder(order);
			//添加至订单回调处理
			addOrderSuccessQueue(order.getId());
        }catch(DuplicateKeyException e){
			throw new AppException("己支付，无需重新支付");
		}finally {
        	logger.info("钱包支付完成，执行时间:[{}]",(System.currentTimeMillis()-curDate.getTime()));
            procedureToolsService.singleUnLockManager(lockKey);
        }
        return true;
    }

	/**
	 * 生成订单成功队列(处理返现和推广佣金）
	 * @param orderId
	 */
	private void addOrderSuccessQueue(Long orderId){
		try{
			OrderSuccessQueue osq=new OrderSuccessQueue();
			osq.setCreateTime(new Date());
			osq.setExecuteNum(0);
			osq.setOrderId(orderId);
			osq.setStatus(0);
			orderSuccessQueueMapper.saveOrderSuccessQueue(osq);
		}catch (Exception e){
			e.printStackTrace();
		}
	}

	/**
	 * 取得订单详情列表
	 * @param params
	 * 	查询条件
	 * 		orderType 订单类型
	 * 		orderNo 订单号
	 * 		orderStatus 订单状态
	 * 		orderPayWay	支付方式
	 * 		orderBuyNm 购买人姓名
	 * 		orderBuyUid 购买人id
	 * 		orderLogisticsCode 快递单号
	 * 		orderUaName 收货人姓名
	 *		orderUaPhone 收货人话
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination<OrderVo> queryOrderLists(Map<String, Object> params, int pageNo, int pageSize) {
		//取得分页订单
		Pagination<OrderVo> page = new Pagination<OrderVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		//取得详查详情列表
		List<OrderVo> lists = orderMapper.queryOrderInfoLists(page);

		Map<Long,String> oderTypes=new HashMap<Long, String>();
		if(ObjectUtils.isNotEmpty(lists)){
			List<Long> orderIds=new ArrayList<Long>();
			//取得订单详情
			for(OrderVo ov:lists){
				orderIds.add(ov.getId());
				oderTypes.put(ov.getId(),ov.getOrderType());
			}
			//取得所有购买详情
			List<OrderDetailVo> ods=orderDetailMapper.buyOrderDetailInfos(orderIds);
			Map<Long,List<OrderDetailVo>> maps=new HashMap<Long, List<OrderDetailVo>	>();
			for(OrderDetailVo od:ods){
				if(maps.containsKey(od.getOdOrderId())){
					maps.get(od.getOdOrderId()).add(od);
				}else{
					List<OrderDetailVo> odvs=new ArrayList<OrderDetailVo>();
					odvs.add(od);
					maps.put(od.getOdOrderId(),odvs);
				}
			}
			//设置订单关联商品详情
			for(OrderVo o:lists){
				o.setOds(maps.get(o.getId()));
			}
		}
		page.setData(lists);
		return page;
	}

	/**
	 * 根据订单类型取得对应的id值
	 * @param orderType
	 * @return
	 */
	private Long getTradeType(String orderType){
		long rtn =0;
		if(orderType.equals(OrderEnum.SHOPPING.getKey())){
			rtn =0;
		}else if(orderType.equals(OrderEnum.CUT_PRICE.getKey())){
			rtn=1;
		}else if(orderType.equals(OrderEnum.RECHARGE.getKey())){

		}else if(orderType.equals(OrderEnum.SECKILL.getKey())||orderType.equals(OrderEnum.SPECIAL_OFFER.getKey())){
			rtn = 2;
		}
		return rtn;
	}

	/**
	 * 订单确认收货
	 * @param uid 用户id
	 * @param orderId 订单id
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean orderConfirm(Long uid, Long orderId) throws AppException {
		logger.info("用户确认收货");
		Date curDate=new Date();
		String lockKey=StringUtils.formate(SingleThreadLockConstant.CONFIRM_TRADE_ORDER,orderId+"");
		procedureToolsService.singleLockManager(lockKey);
		try{
			Order order=orderMapper.queryOrderById(orderId);
			if(ObjectUtils.isEmpty(order)){
				throw new AppException("订单不存在");
			}
			if(order.getOrderStatus().intValue()!=OrderConstant.ORDER_STATUS_PAY_SEND_GOODS){
				throw new AppException("订单状态异常");
			}
			if(order.getOrderBuyDel().intValue()!=0){
				return true;
			}
			if(order.getOrderBuyUid().intValue()!=uid.intValue()){
				throw new AppException("不能确认他人订单");
			}
			order.setOrderConfirmDate(new Date());
			order.setOrderStatus(OrderConstant.ORDER_STATUS_PAY_SEND_GOODS_RECEIPT);
			orderMapper.updateOrder(order);
		}finally {
			procedureToolsService.singleUnLockManager(lockKey);
			logger.info("确认收货执行完成，执行时间：[{}]",(System.currentTimeMillis()-curDate.getTime()));
		}
		return true;
	}
	/**
	 * 取消订单
	 * @param uid 用户id
	 * @param orderId 订单id
	 * @return
	 * @throws AppException
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean cancelOrder(Long uid, Long orderId) throws AppException {
		logger.info("用户取消订单");
		Date curDate=new Date();
		String lockKey=StringUtils.formate(SingleThreadLockConstant.CONFIRM_TRADE_ORDER,orderId+"");
		procedureToolsService.singleLockManager(lockKey);
		try{
			Order order=orderMapper.queryOrderById(orderId);
			if(ObjectUtils.isEmpty(order)){
				throw new AppException("订单不存在");
			}
			if(order.getOrderStatus().intValue()!=OrderConstant.ORDER_STATUS_CREATE){
				throw new AppException("订单状态异常");
			}
			if(order.getOrderBuyDel().intValue()!=0){
				return true;
			}
			if(order.getOrderBuyUid().intValue()!=uid.intValue()){
				throw new AppException("不能取消他人订单");
			}
			order.setOrderBuyDel(SystemConstant.SYS_IS_DELETE_YES);
			orderMapper.updateOrder(order);
		}finally {
			procedureToolsService.singleUnLockManager(lockKey);
			logger.info("确认收货执行完成，执行时间：[{}]",(System.currentTimeMillis()-curDate.getTime()));
		}
		return true;
	}

	/**
	 * 商品发货
	 * @param uid
	 * @param orderId
	 * @param orderLogisticsCode
	 * @param sendRemark
	 * @return
	 * @throws AppException
	 */
	@Transactional
	public boolean sendGoods(Long uid, Long orderId,String logisticsCompany,String orderLogisticsCode, String sendRemark) throws AppException {
		logger.info("商品发货,订单ID:[{}],发货人：[{}],快递单号:[{}]",orderId,uid,orderLogisticsCode);
		Date curDate=new Date();
		String lockKey=StringUtils.formate(SingleThreadLockConstant.CONFIRM_TRADE_ORDER,orderId+"");
		procedureToolsService.singleLockManager(lockKey);
		try{
			Order order=orderMapper.queryOrderById(orderId);
			if(ObjectUtils.isEmpty(order)){
				throw new AppException("订单不存在");
			}
			if(order.getOrderStatus().intValue()!=OrderConstant.ORDER_STATUS_PAY_SUCCESS){
				throw new AppException("订单状态己变更");
			}
			order.setOrderStatus(OrderConstant.ORDER_STATUS_PAY_SEND_GOODS);
			order.setOrderLogisticsCode(orderLogisticsCode);
			order.setOrderSendDate(curDate);
			order.setOrderLogisticsCompany(logisticsCompany);
			order.setOrderSendUid(uid);
			order.setOrderSendRemark(sendRemark);
			orderMapper.updateOrder(order);
		}finally {
			procedureToolsService.singleUnLockManager(lockKey);
			logger.info("商品发货结束,订单ID:[{}],发货人：[{}],快递单号:[{}],执行时间:[{}]",orderId,uid,orderLogisticsCode,(System.currentTimeMillis()-curDate.getTime()));
		}
		return true;
	}

	public boolean orderSetting(String orderExpireDate, String orderConfirmDate, String orderCompleteDate, String defaultLogisPrice) {
		List<SysParamVo> lists=new ArrayList<SysParamVo>();
		Long price=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(Double.parseDouble(defaultLogisPrice)));
		SysParamVo oed=new SysParamVo(SysParameterConfigConstant.ORDER_EXPIRE_DATE,orderExpireDate);
		SysParamVo ocd=new SysParamVo(SysParameterConfigConstant.ORDER_CONFIRM_DATE,orderConfirmDate);
		SysParamVo ocds=new SysParamVo(SysParameterConfigConstant.ORDER_COMPLETE_DATE,orderCompleteDate);
		SysParamVo dlp=new SysParamVo(SysParameterConfigConstant.DEFAULT_LOGISTICS_PRICE,price+"");
		lists.add(oed);
		lists.add(ocd);
		lists.add(ocds);
		lists.add(dlp);
		sysParameterConfigMapper.batchUpdateSysParameterConfig(lists);
		sysParameterConfigService.reloadSysParameterConfig();
		return false;
	}

	/**
	 * 清除超时订单
	 */
	public void clearExpireOrder()throws AppException{
		Date curDate=new Date();
		//取得时时间
		Double expireTime=SysParameterConfigConstant.getValue(SysParameterConfigConstant.ORDER_EXPIRE_DATE,Double.class);
		if(ObjectUtils.isEmpty(expireTime)){
			expireTime=24d;
		}
		Date expireDate= DateUtil.addHour(curDate,-expireTime);
		// 取得超时订单
		List<Order> expireOrders=orderMapper.queryOrderByStatus(OrderConstant.ORDER_STATUS_CREATE,"order_create_date",expireDate);
		//取得超进订单
		List<Long> orderIds=new ArrayList<Long>();
		//订单id对应的订单类型
		Map<Long,String> orderTypeMaps=new HashMap<Long, String>();
		Map<String,Map<Long,Long>> typeNum=new HashMap<String, Map<Long, Long>>();
		for(Order order:expireOrders){
			orderIds.add(order.getId());
			orderTypeMaps.put(order.getId(),order.getOrderType());
		}
		//取得订单详情
		List<OrderDetail> ods=orderDetailMapper.queryOrderDetailByOdIds(orderIds);
		for(OrderDetail od:ods){
			String orderType=orderTypeMaps.get(od.getOdOrderId());
			if(!orderType.equals(OrderEnum.RECHARGE.getKey())){
				//判断订单类型
				if(typeNum.containsKey(orderType)){
					typeNum.get(orderType).put(od.getOdGoodsId(),od.getOdBuyNumber().longValue());
				}else{
					Map<Long,Long> gns=new HashMap<Long, Long>();
					gns.put(od.getOdGdId(),od.getOdBuyNumber().longValue());
					typeNum.put(orderType,gns);
				}
			}
		}
		//返还商品库存
		if(typeNum.containsKey(OrderEnum.SHOPPING.getKey())&&ObjectUtils.isNotEmpty(typeNum.get(OrderEnum.SHOPPING.getKey()))){
			//商品购买库存返现
			boolean success =goodsGgsvalDetailService.editGoodsInventorys(typeNum.get(OrderEnum.SHOPPING.getKey()));
		}else if(typeNum.containsKey(OrderEnum.SPECIAL_OFFER.getKey())&&ObjectUtils.isNotEmpty(typeNum.get(OrderEnum.SPECIAL_OFFER.getKey()))){
			//特价商品库存返还
			boolean success=goodsGdsprelService.editGoodsInventorys(typeNum.get(OrderEnum.SHOPPING.getKey()));
		}else if(typeNum.containsKey(OrderEnum.SECKILL.getKey())&&ObjectUtils.isNotEmpty(typeNum.get(OrderEnum.SECKILL.getKey()))){
			//钞杀商品库存返还
			boolean success=goodsGdsprelService.editGoodsInventorys(typeNum.get(OrderEnum.SECKILL.getKey()));
		}else if(typeNum.containsKey(OrderEnum.CUT_PRICE.getKey())&&ObjectUtils.isNotEmpty(typeNum.get(OrderEnum.CUT_PRICE.getKey()))){
			boolean success = goodsGdcprelService.editGoodsInventorys(typeNum.get(OrderEnum.CUT_PRICE.getKey()));
		}
		//设置订单超时
		orderMapper.clearExpireOrders(orderIds);
		//批量设置订单超时
		System.out.println(JSON.toJSON(expireOrders));

	}
}

