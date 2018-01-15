
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.constant.*;
import com.retailers.dht.common.dao.*;
import com.retailers.dht.common.entity.*;
import com.retailers.dht.common.service.AsyncService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.service.OrderSuccessQueueService;
import com.retailers.dht.common.service.UserCardPackageService;
import com.retailers.dht.common.vo.GoodsReturnVo;
import com.retailers.mybatis.common.enm.OrderEnum;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import java.util.*;
/**
 * 描述：订单支付成功队例Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-24 11:30:58
 */
@Service("ordersuccessqueueService")
public class OrderSuccessQueueServiceImpl implements OrderSuccessQueueService {
	Logger logger= LoggerFactory.getLogger(OrderSuccessQueueServiceImpl.class);
	@Autowired
	private OrderSuccessQueueMapper orderSuccessQueueMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private AccumulativeAmountMapper accumulativeAmountMapper;
	@Autowired
	private WalletCashBackQueueMapper walletCashBackQueueMapper;
	@Autowired
	private CurrentPlatformSalesMapper currentPlatformSalesMapper;
	@Autowired
	private UserCardPackageMapper userCardPackageMapper;
	@Autowired
	private UserCardPackageService userCardPackageService;
	@Autowired
	private UserMapper userMapper;
	@Autowired
	private RecommendStatisticsMapper recommendStatisticsMapper;
	@Autowired
	private AsyncService asyncService;
	@Autowired
	private GoodsService goodsService;


	public boolean saveOrderSuccessQueue(OrderSuccessQueue orderSuccessQueue) {
		int status = orderSuccessQueueMapper.saveOrderSuccessQueue(orderSuccessQueue);
		return status == 1 ? true : false;
	}
	public boolean updateOrderSuccessQueue(OrderSuccessQueue orderSuccessQueue) {
		int status = orderSuccessQueueMapper.updateOrderSuccessQueue(orderSuccessQueue);
		return status == 1 ? true : false;
	}
	public OrderSuccessQueue queryOrderSuccessQueueById(Long id) {
		return orderSuccessQueueMapper.queryOrderSuccessQueueById(id);
	}

	public Pagination<OrderSuccessQueue> queryOrderSuccessQueueList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<OrderSuccessQueue> page = new Pagination<OrderSuccessQueue>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<OrderSuccessQueue> list = orderSuccessQueueMapper.queryOrderSuccessQueueList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOrderSuccessQueueById(Long id) {
		int status = orderSuccessQueueMapper.deleteOrderSuccessQueueById(id);
		return status == 1 ? true : false;
	}

	/**
	 * 执行订单队列
	 */
	public void executeOrderQueue() {
		//取得待处理列表
		List<OrderSuccessQueue> osqs=orderSuccessQueueMapper.queryWaiteOrderSuccessQueue();
		if(ObjectUtils.isNotEmpty(osqs)){
			for(OrderSuccessQueue osq:osqs){
				Date curDate=new Date();
				DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) SpringUtils.getBean("transactionManager");
				DefaultTransactionDefinition def = new DefaultTransactionDefinition();
				def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 事物隔离级
				TransactionStatus ts = transactionManager.getTransaction(def);
				boolean isSuccess=false;
				String msg="";
				try{
					executeOrderQueue(osq);
					isSuccess=true;
					transactionManager.commit(ts);
					msg="执行成功,执行时间:"+ DateUtil.dateToString(curDate,DateUtil.DATE_WITHSECOND_FORMAT)+",待待时间："+(curDate.getTime()-osq.getCreateTime().getTime())+
					"，方法执行时间:"+(System.currentTimeMillis()-curDate.getTime())+"";
				}catch (AppException e){
					msg=e.getMessage();
					e.printStackTrace();
					transactionManager.rollback(ts);
				}catch (Exception e){
					msg=e.getMessage();
					e.printStackTrace();
					transactionManager.rollback(ts);
				}finally {
					if(ObjectUtils.isNotEmpty(msg)&&msg.length()>800){
						msg=msg.substring(0,800);
					}
					osq.setRemark(msg);
					asyncService.editorOrderSuccessQueue(osq,isSuccess);
				}
			}
		}
	}

	/**
	 * 执行订单分润 以及推广提成
	 * @param osq
	 * @throws AppException
	 */
	private void executeOrderQueue(OrderSuccessQueue osq)throws AppException{
		//取得订单
		Order order=orderMapper.queryOrderById(osq.getOrderId());
		if(ObjectUtils.isNotEmpty(order)){
			List<OrderDetail> ods=orderDetailMapper.queryOrderDetailByOdId(order.getId());
			if(order.getOrderStatus().intValue()== OrderConstant.ORDER_STATUS_PAY_SUCCESS||order.getOrderStatus().intValue()==OrderConstant.ORDER_STATUS_PAY_SEND_GOODS){
				//交易总金额
				long orderTradePrice=order.getOrderTradePrice();
				//取得详查类型
				if(!order.getOrderType().equals(OrderEnum.RECHARGE)){
					//判断用户支付类型 是否马上返现 判断是否是钱包支付 是否返现
					if(order.getOrderPayWay().intValue()==OrderConstant.ORDER_PAY_WAY_WALLET){
						orderTradePrice=order.getOrderMenberPrice()+NumberUtils.calculationDiscountPrice(order.getOrderLogisticsPrice(),order.getOrderDiscount());
						//判断是否是返现
						if(order.getOrderIntegralOrCash().intValue()==OrderConstant.ORDER_RETURN_TYPE_CASH){
							//取得订单详情 进行商品分类处理
							goodsType(order,ods,true,order.getId(),order.getOrderBuyUid(),orderTradePrice);
							order.getOrderMenberPrice();
						}else{
							goodsType(order,ods,false,order.getId(),order.getOrderBuyUid(),orderTradePrice);
						}
					}else{
						goodsType(order,ods,false,order.getId(),order.getOrderBuyUid(),orderTradePrice);
					}
					//计算推广提成
					popularize(order,ods);
				}
			}else{
				throw new AppException("订单状态异常");
			}
		}
	}
	/**
	 * 用户消费统计
	 * @param uid 用户id
	 * @param orderId 订单id
	 * @param type 支付类型
	 * @param tradePrice 交易金额
	 * @param unCasPrice 未返现金额
	 */
	private void statisticsUserSalseConsume(Long uid,Long orderId,Integer type,Long tradePrice,Long unCasPrice){
		UserCardPackage ucp=userCardPackageMapper.queryUserCardPackageById(uid);
		if(ObjectUtils.isNotEmpty(ucp)){
			userCardPackageMapper.statisticsUserSalseConsume(uid,type,tradePrice,unCasPrice,ucp.getVersion());
			String remark="用户购买商品，累计消费，消费金额："+ NumberUtils.formaterNumberPower(tradePrice)+",当前累计："+NumberUtils.formaterNumberPower(ucp.getUcurIntegral());
			//添加用户累计返现日志
			if(ObjectUtils.isNotEmpty(unCasPrice)&&unCasPrice>0){
				userCardPackageService.addUserCardPackageLog(uid, UserCardPackageConstant.USER_CARD_PACKAGE_TYPE_INTEGRAL_IN,orderId,unCasPrice,ucp.getUcurIntegral(),remark,new Date());
			}
		}
	}
	/**
	 * 设置返现队列
	 * @param order 购物订单
	 * @param ods 订单详情
	 * @param isCash 是否返现
	 * @param buyUid 购买用户id
	 * @return
	 */
	private void goodsType(Order order,List<OrderDetail> ods,Boolean isCash,Long orderId,Long buyUid,Long tradePrice){
		Date curDate=new Date();
		List<Long> goodsIds=new ArrayList<Long>();
		for(OrderDetail od:ods){
			goodsIds.add(od.getOdGoodsId());
		}
		//根据商品id取得商品类型
		Map<Long,GoodsReturnVo> maps=goodsService.queryGoodsReturn(goodsIds);
		//是否返现
		if(isCash){
			orderCashBack(maps,orderId,ods,buyUid);
		}else{
			//不进行返现
			//取得购买商品例表中可以直接进行返现的商品类型
			//立退返现列表
			List<OrderDetail> cash=new ArrayList<OrderDetail>();
			//自然消费，不返现列表
			List<OrderDetail> unCash=new ArrayList<OrderDetail>();
			long cashPrice=0l;
			long unCashPrice=0l;
			for(OrderDetail od:ods){
				if(ObjectUtils.isNotEmpty(maps)&& maps.containsKey(od.getOdGoodsId())){
					if(maps.get(od.getOdGoodsId()).getRtType().intValue()==OrderConstant.ORDER_RETURN_TYPE_CASH){
						cash.add(od);
						cashPrice+=od.getOdMenberPrice();
					}else{
						unCash.add(od);
					}
				}
			}
			unCashPrice=tradePrice-cashPrice;
			//判断是否存在立即返现列表
			if(ObjectUtils.isNotEmpty(cash)){
				orderCashBack(maps,orderId,cash,buyUid);
			}
			if(ObjectUtils.isNotEmpty(unCash)){
				orderUnCashBack(maps,orderId,unCash,buyUid,unCashPrice);
			}

			//添加卡包操作日志
			statisticsUserSalseConsume(order.getOrderBuyUid(),order.getId(),order.getOrderPayWay(),order.getOrderMenberPrice(),unCashPrice);
		}
	}

	/**
	 * 设置订单返现 交易返现
	 * @param maps
	 * @param orderId 订单id
	 * @param ods 商品购买列表
	 * @param buyUid
	 */
	private void orderCashBack(Map<Long,GoodsReturnVo> maps,Long orderId,List<OrderDetail> ods,Long buyUid){
		logger.info("进入订单返现方法,订单id:[{}],商品关联的类型：[{}],购买人id：[{}]",orderId, JSON.toJSON(maps),buyUid);
		Date curDate=new Date();
		//商品合并
		Map<Long,List<OrderDetail>> hbsp=new HashMap<Long, List<OrderDetail>>();
		for(OrderDetail od:ods){
			if(hbsp.containsKey(od.getOdGoodsId())){
				hbsp.get(od.getOdGoodsId()).add(od);
			}else{
				List<OrderDetail> ods_=new ArrayList<OrderDetail>();
				ods_.add(od);
				hbsp.put(od.getOdGoodsId(),ods_);
			}
		}
		List<WalletCashBackQueue> wcbqs=new ArrayList<WalletCashBackQueue>();
		//商品类型对应的价格
		Map<Long,Long> gtUnPrice=new HashMap<Long, Long>();
		Set<Long> gtyps=new HashSet<Long>();
		//批量汪加商品日志
		List<AccumulativeAmount> atas=new ArrayList<AccumulativeAmount>();
		for(Long key:maps.keySet()){
			WalletCashBackQueue wcbq=new WalletCashBackQueue();
			wcbq.setCcbqUid(buyUid);
			//设置商品顶层节点
			wcbq.setCcbqGoodsType(maps.get(key).getRtId());
			wcbq.setCcbqOrderId(orderId);
			wcbq.setCcbqMoney(buyGoodsIdTotalPrice(hbsp.get(key)));
			wcbq.setCcbqStatus(SystemConstant.PLAT_CASH_BACK_MENOY_STATUS_LINE_UP);
			wcbq.setCcbqGid(key);
			wcbq.setCcbqCreateTime(curDate);
			wcbqs.add(wcbq);
			//取得该类型下的累计金额
			if(gtUnPrice.containsKey(wcbq.getCcbqGoodsType())){
				gtUnPrice.put(wcbq.getCcbqGoodsType(),gtUnPrice.get(wcbq.getCcbqGoodsType())+wcbq.getCcbqMoney());
			}else{
				gtUnPrice.put(wcbq.getCcbqGoodsType(),wcbq.getCcbqMoney());
			}
			gtyps.add(wcbq.getCcbqGoodsType());
			AccumulativeAmount ata=new AccumulativeAmount();
			ata.setAaGoodsId(key);
			ata.setAaOrderId(orderId);
			ata.setAaGoodsParentType(wcbq.getCcbqGoodsType());
			ata.setAaConsumePrice(wcbq.getCcbqMoney());
			ata.setAaCreateTime(new Date());
			ata.setAaType(0);
			atas.add(ata);
		}
		// 添加用户至返现队列
		if(ObjectUtils.isNotEmpty(wcbqs)){
			walletCashBackQueueMapper.saveWalletCashBackQueues(wcbqs);
		}
		//添加返现队列日志
		if(ObjectUtils.isNotEmpty(atas)){
			accumulativeAmountMapper.saveAccumulativeAmounts(atas);
		}
		//修改各个商品大类下的累计消费金额
		List<CurrentPlatformSales> list = currentPlatformSalesMapper.queryCurrentPlatformSalesByGtype(SystemConstant.CURRENT_PLATFORM_SALES_TYPE_CASH,gtyps);
		Map<Long,CurrentPlatformSales> hasCpfs=new HashMap<Long, CurrentPlatformSales>();
		for(CurrentPlatformSales cpfs:list){
			hasCpfs.put(cpfs.getCpsGoodsMainType(),cpfs);
		}
		//批量新增
		List<CurrentPlatformSales> batchAdd=new ArrayList<CurrentPlatformSales>();
		//批量修改
		List<CurrentPlatformSales> batchUpdate=new ArrayList<CurrentPlatformSales>();
		//根据当次交易类型累计当前交易额
		for(Long type:gtyps){
			if(hasCpfs.containsKey(type)){
				CurrentPlatformSales cpfs=hasCpfs.get(type);
				cpfs.setCpsTotalPrice(gtUnPrice.get(type));
				batchUpdate.add(cpfs);
			}else{
				CurrentPlatformSales cpfs=new CurrentPlatformSales();
				cpfs.setCpsGoodsMainType(type);
				cpfs.setCpsTotalPrice(gtUnPrice.get(type));
				cpfs.setCpsPayType(SystemConstant.CURRENT_PLATFORM_SALES_TYPE_CASH);
				batchAdd.add(cpfs);
			}
		}
		//批量添加
		if(ObjectUtils.isNotEmpty(batchAdd)){
			currentPlatformSalesMapper.saveCurrentPlatformSaless(batchAdd);
		}
		//批量修改
		if(ObjectUtils.isNotEmpty(batchUpdate)){
			currentPlatformSalesMapper.batchUpdateCurrentPlatformSales(batchUpdate);
		}
		logger.info("进入订单返现方法结束,订单id:[{}],商品关联的类型：[{}],购买人id：[{}],执行时间:[{}]",orderId, JSON.toJSON(maps),buyUid,(System.currentTimeMillis()-curDate.getTime()));
	}
	/**
	 * 设置订单返现 交易返现
	 * @param maps
	 * @param orderId 订单id
	 * @param ods 商品购买列表
	 * @param buyUid 购买用户id
	 * @param totalPrice 总金额
	 */
	private void orderUnCashBack(Map<Long,GoodsReturnVo> maps,Long orderId,List<OrderDetail> ods,Long buyUid,Long totalPrice){
		logger.info("进入普通消费累计方法,订单id:[{}],商品关联的类型：[{}],购买人id：[{}],累计金额：[{}]",orderId, JSON.toJSON(maps),buyUid,totalPrice);
		Date curDate=new Date();
		//商品合并
		Map<Long,List<OrderDetail>> hbsp=new HashMap<Long, List<OrderDetail>>();
		for(OrderDetail od:ods){
			if(hbsp.containsKey(od.getOdGoodsId())){
				hbsp.get(od.getOdGoodsId()).add(od);
			}else{
				List<OrderDetail> ods_=new ArrayList<OrderDetail>();
				ods_.add(od);
				hbsp.put(od.getOdGoodsId(),ods_);
			}
		}
		logger.info("商品id 对应的价格:[{}]",JSON.toJSON(hbsp));
		//商品类型对应的价格
		Map<Long,Long> gtUnPrice=new HashMap<Long, Long>();
		Set<Long> gtyps=new HashSet<Long>();
		for(Long key:maps.keySet()){
			Long gt=maps.get(key).getRtId();
			long money=buyGoodsIdTotalPrice(hbsp.get(key));
			//取得该类型下的累计金额
			if(gtUnPrice.containsKey(gt)){
				gtUnPrice.put(gt,gtUnPrice.get(gt)+money);
			}else{
				gtUnPrice.put(gt,money);
			}
			gtyps.add(gt);
		}
		logger.info("本次购买的商品类型：[{}]",JSON.toJSON(gtyps));
		logger.info("本次购买的同类商品下的总计消费：[{}]",JSON.toJSON(gtUnPrice));
		//修改各个商品大类下的累计消费金额
		List<CurrentPlatformSales> list = currentPlatformSalesMapper.queryCurrentPlatformSalesByGtype(SystemConstant.CURRENT_PLATFORM_SALES_TYPE_GT_SALES_TOTAL,gtyps);
		Map<Long,CurrentPlatformSales> hasCpfs=new HashMap<Long, CurrentPlatformSales>();
		for(CurrentPlatformSales cpfs:list){
			hasCpfs.put(cpfs.getCpsGoodsMainType(),cpfs);
		}
		//批量新增
		List<CurrentPlatformSales> batchAdd=new ArrayList<CurrentPlatformSales>();
		//批量修改
		List<CurrentPlatformSales> batchUpdate=new ArrayList<CurrentPlatformSales>();
		//根据当次交易类型累计当前交易额
		for(Long type:gtyps){
			if(hasCpfs.containsKey(type)){
				CurrentPlatformSales cpfs=hasCpfs.get(type);
				cpfs.setCpsTotalPrice(gtUnPrice.get(type));
				batchUpdate.add(cpfs);
			}else{
				CurrentPlatformSales cpfs=new CurrentPlatformSales();
				cpfs.setCpsPayType(SystemConstant.CURRENT_PLATFORM_SALES_TYPE_GT_SALES_TOTAL);
				cpfs.setCpsTotalPrice(gtUnPrice.get(type));
				cpfs.setCpsGoodsMainType(type);
				batchAdd.add(cpfs);
			}
		}
		//批量添加
		if(ObjectUtils.isNotEmpty(batchAdd)){
			currentPlatformSalesMapper.saveCurrentPlatformSaless(batchAdd);
		}
		//批量修改
		if(ObjectUtils.isNotEmpty(batchUpdate)){
			currentPlatformSalesMapper.batchUpdateCurrentPlatformSales(batchUpdate);
		}
		//取得自然消费累计金额
		CurrentPlatformSales cps = currentPlatformSalesMapper.queryCurrentPlatformSalesByGt(SystemConstant.CURRENT_PLATFORM_SALES_TYPE_SALES_TOTAL);
		if(ObjectUtils.isEmpty(cps)){
			cps=new CurrentPlatformSales();
			cps.setCpsPayType(SystemConstant.CURRENT_PLATFORM_SALES_TYPE_SALES_TOTAL);
			cps.setCpsTotalPrice(totalPrice);
			cps.setCpsGoodsMainType(-1l);
			currentPlatformSalesMapper.saveCurrentPlatformSales(cps);
		}else{
			cps.setCpsTotalPrice(totalPrice);
			currentPlatformSalesMapper.updateCurrentPlatformSales(cps);
		}
		logger.info("普通消费累计方法执行结束,订单id:[{}],商品关联的类型：[{}],购买人id：[{}],累计金额：[{}],执行时间:[{}]",orderId, JSON.toJSON(maps),buyUid,totalPrice,(System.currentTimeMillis()-curDate.getTime()));
	}


	/**
	 * 计算推广提成
	 * @param order 订单数据
	 * @param ods 订单详情
	 */
	private void popularize(Order order,List<OrderDetail> ods){
		List<RecommendStatistics> rsLists=new ArrayList<RecommendStatistics>();
		//用户存在推荐人，判断用户是否是首单
		Long buyOid=orderMapper.findUserFirstBuy(order.getOrderBuyUid());
		if(ObjectUtils.isNotEmpty(buyOid)&&buyOid.intValue()==order.getId().intValue()){
			//取得推荐人
			User user = userMapper.queryRecommendUser(order.getOrderBuyUid());
			//判断用户是否存在推荐人
			if(ObjectUtils.isNotEmpty(user)&&(ObjectUtils.isNotEmpty(user.getUtype())&&user.getUtype().intValue()!= UserConstant.USER_TYPE_PT)){
				//设置首单推荐奖励
				RecommendStatistics rs=savePopularize(RecommendStatisticsConstant.RECOMMENT_TYPE_FIRST,order.getId(),-1l,order.getOrderBuyUid(),user,order.getOrderMenberPrice());
				rsLists.add(rs);
			}
		//不是首单
		}else{
			//取得推荐人例表
			Set<Long> rUid=new HashSet<Long>();
			for(OrderDetail od:ods){
				if(ObjectUtils.isNotEmpty(od.getOdInviterUid())){
					rUid.add(od.getOdInviterUid());
				}
			}
			if(ObjectUtils.isNotEmpty(rUid)){
				List<User> rusers=userMapper.queryRecommendUsers(rUid);
				Map<Long,User> uMaps=new HashMap<Long, User>();
				for(User u:rusers){
					uMaps.put(u.getUid(),u);
				}
				for(OrderDetail od:ods){
					if(ObjectUtils.isNotEmpty(od.getOdInviterUid())){
						User user=uMaps.get(od.getOdInviterUid());
						if(ObjectUtils.isNotEmpty(user)&&ObjectUtils.isNotEmpty(user.getUtype())&&user.getUtype().intValue()!=UserConstant.USER_TYPE_PT){
							//设置首单推荐奖励
							RecommendStatistics rs=savePopularize(RecommendStatisticsConstant.RECOMMENT_TYPE_SHARE,order.getId(),od.getId(),order.getOrderBuyUid(),user,od.getOdMenberPrice());
							rsLists.add(rs);
						}
					}
				}
			}
		}
		if(ObjectUtils.isNotEmpty(rsLists)){
			recommendStatisticsMapper.saveRecommendStatisticss(rsLists);
		}

	}

	/**
	 * 设置推广提成比例
	 * @param rsType 推广类型
	 * @param oid 订单id
	 * @param odid 商品订单详情id
	 * @param rsUid 购买用户
	 * @param recommentUser 推广用户
	 * @param cPrice 交易金额
	 * @return
	 */
	private RecommendStatistics savePopularize(Integer rsType,Long oid,Long odid,Long rsUid,User recommentUser,Long cPrice){
		RecommendStatistics rs=new RecommendStatistics();
		rs.setRsType(rsType);
		rs.setRsStatus(0);
		rs.setRsOid(oid);
		rs.setRsOdId(odid);
		rs.setRsUid(rsUid);
		rs.setRsRecommendUid(recommentUser.getUid());
		rs.setRsUtype(recommentUser.getUtype().longValue());
		//根据推荐类型取得推广比例
		long ratio=recommentUser.getUfirstCommission();
		if(rsType.intValue()==1){
			ratio=recommentUser.getUrecommendCommission();
		}
		rs.setRsSalesPrice(cPrice);
		rs.setRsRatio(ratio);
		double dp=NumberUtils.priceChangeYuan(cPrice)*NumberUtils.priceChangeYuan(ratio);
		long rsPrice=NumberUtils.priceChangeFen(NumberUtils.formaterNumber(dp,2));
		rs.setRsPrice(rsPrice);
		rs.setRsTime(new Date());
		return rs;
	}

	/**
	 * 计算金额 相同商品订单金额
	 * @param ods
	 * @return
	 */
	private Long buyGoodsIdTotalPrice(List<OrderDetail> ods){
		long total=0;
		if(ObjectUtils.isNotEmpty(ods)){
			for(OrderDetail od:ods){
				total+=od.getOdMenberPrice();
			}
		}
		return total;
	}

	/**
	 *
	 * @param osq
	 * @param isSuccess
	 */
	public void editorExeStatus(OrderSuccessQueue osq, boolean isSuccess) {
		if(isSuccess){
			orderSuccessQueueMapper.deleteOrderSuccessQueueById(osq.getId());
			orderSuccessQueueMapper.saveOrderSuccessQueueHistory(osq);
		}else{
			osq.setExecuteNum(osq.getExecuteNum()+1);
			orderSuccessQueueMapper.updateOrderSuccessQueue(osq);
		}
	}
}

