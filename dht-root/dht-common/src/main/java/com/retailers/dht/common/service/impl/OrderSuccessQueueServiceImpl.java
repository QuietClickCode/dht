
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.constant.OrderConstant;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.dao.*;
import com.retailers.dht.common.entity.*;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.OrderSuccessQueueService;
import com.retailers.mybatis.common.enm.OrderEnum;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Key;
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
	@Autowired
	private OrderSuccessQueueMapper orderSuccessQueueMapper;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private OrderDetailMapper orderDetailMapper;
	@Autowired
	private GoodsClassificationService goodsClassificationService;
	@Autowired
	private AccumulativeAmountMapper accumulativeAmountMapper;
	@Autowired
	private CurrentPlatformSalesMapper currentPlatformSalesMapper;

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

	public void executeOrderQueue() {
		//取得待处理列表
		List<OrderSuccessQueue> osqs=orderSuccessQueueMapper.queryWaiteOrderSuccessQueue();
		if(ObjectUtils.isNotEmpty(osqs)){
			for(OrderSuccessQueue osq:osqs){
				boolean isSuccess=true;
				try{
					//取得订单
					Order order=orderMapper.queryOrderById(osq.getOrderId());
					if(ObjectUtils.isNotEmpty(order)){
						List<OrderDetail> ods=orderDetailMapper.queryOrderDetailByOdId(order.getId());
						if(order.getOrderStatus().intValue()== OrderConstant.ORDER_STATUS_PAY_SUCCESS){
							//取得详查类型
							if(!order.getOrderType().equals(OrderEnum.RECHARGE)){
								//判断用户支付类型 是否马上返现 判断是否是钱包支付 是否返现
								if(order.getOrderPayWay().intValue()==OrderConstant.ORDER_PAY_WAY_WALLET){
									//判断是否是返现
									if(order.getOrderIntegralOrCash().intValue()==OrderConstant.ORDER_RETURN_TYPE_CASH){
										//取得订单详情 进行商品分类处理


										//设置每个类型的累加值
//										queryGoodsClassificationByGids
									}
								}
							}
						}else{
							isSuccess=false;
						}
					}
				}catch (Exception e){
					isSuccess=false;
				}finally {

				}
			}
		}
	}

	/**
	 * @param ods 订单详情
	 * @param isCash 是否返现
	 * @return
	 */
	private void goodsType(List<OrderDetail> ods,Boolean isCash,Long orderId,Long buyUid){
		Date curDate=new Date();
		//商品合并
		Map<Long,List<OrderDetail>> hbsp=new HashMap<Long, List<OrderDetail>>();
		List<Long> goodsIds=new ArrayList<Long>();
		for(OrderDetail od:ods){
			goodsIds.add(od.getOdGoodsId());
			if(hbsp.containsKey(od.getOdGoodsId())){
				hbsp.get(od.getOdGoodsId()).add(od);
			}else{
				List<OrderDetail> ods_=new ArrayList<OrderDetail>();
				ods_.add(od);
				hbsp.put(od.getOdGoodsId(),ods_);
			}
		}
		//根据商品id取得商品类型
		Map<Long,Map<String,Long>> maps=goodsClassificationService.queryGoodsClassificationByGids(goodsIds);
		//是否返现
		if(isCash){
			List<WalletCashBackQueue> wcbqs=new ArrayList<WalletCashBackQueue>();
			//商品类型对应的价格
			Map<Long,Long> gtUnPrice=new HashMap<Long, Long>();
			Set<Long> gtyps=new HashSet<Long>();
			for(Long key:maps.keySet()){
				WalletCashBackQueue wcbq=new WalletCashBackQueue();
				wcbq.setCcbqUid(buyUid);
				//设置商品顶层节点
				wcbq.setCcbqGoodsType(maps.get(key).get("topNodes"));
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
			//根据当次交易
			for(Long type:gtyps){
				if(hasCpfs.containsKey(type)){
					CurrentPlatformSales cpfs=hasCpfs.get(type);
					cpfs.setCpsTotalPrice(gtUnPrice.get(type));
					batchUpdate.add(cpfs);
				}else{
					CurrentPlatformSales cpfs=new CurrentPlatformSales();
					cpfs.setCpsTotalPrice(gtUnPrice.get(type));
					cpfs.setCpsPayType(SystemConstant.CURRENT_PLATFORM_SALES_TYPE_CASH);
					cpfs.setCpsGoodsMainType(type);
					batchAdd.add(cpfs);
				}
			}
		}
	}

	/**
	 * 计算金额
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

}

