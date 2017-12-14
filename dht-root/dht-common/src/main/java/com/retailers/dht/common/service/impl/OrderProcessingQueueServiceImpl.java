
package com.retailers.dht.common.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.constant.OrderProcessingQueueConstant;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.dao.OrderMapper;
import com.retailers.dht.common.entity.Order;
import com.retailers.dht.common.entity.OrderProcessingQueue;
import com.retailers.dht.common.dao.OrderProcessingQueueMapper;
import com.retailers.dht.common.service.OrderProcessingQueueService;
import com.retailers.dht.common.service.OrderService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.SpringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.DefaultTransactionDefinition;

/**
 * 描述：订单处理队列（订单支付回调，订单状态变更）Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-14 01:32:58
 */
@Service("orderprocessingqueueService")
public class OrderProcessingQueueServiceImpl implements OrderProcessingQueueService {
	Logger logger= LoggerFactory.getLogger(OrderProcessingQueueServiceImpl.class);
	@Autowired
	private OrderProcessingQueueMapper orderProcessingQueueMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderMapper orderMapper;

	public boolean saveOrderProcessingQueue(OrderProcessingQueue orderProcessingQueue) {
		int status = orderProcessingQueueMapper.saveOrderProcessingQueue(orderProcessingQueue);
		return status == 1 ? true : false;
	}
	public boolean updateOrderProcessingQueue(OrderProcessingQueue orderProcessingQueue) {
		int status = orderProcessingQueueMapper.updateOrderProcessingQueue(orderProcessingQueue);
		return status == 1 ? true : false;
	}
	public OrderProcessingQueue queryOrderProcessingQueueById(Long id) {
		return orderProcessingQueueMapper.queryOrderProcessingQueueById(id);
	}

	public Pagination<OrderProcessingQueue> queryOrderProcessingQueueList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<OrderProcessingQueue> page = new Pagination<OrderProcessingQueue>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<OrderProcessingQueue> list = orderProcessingQueueMapper.queryOrderProcessingQueueList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOrderProcessingQueueById(Long id) {
		int status = orderProcessingQueueMapper.deleteOrderProcessingQueueById(id);
		return status == 1 ? true : false;
	}

	/**
	 * 添加消息处理队列
	 * @param orderNo 订单号
	 * @param isSuccess 是否成功
	 * @param tradeOffId 第三方回调单号
	 * @param payWay 支付方式
	 * @param message 消息
	 * @return
	 */
	public boolean addQueue(String orderNo, boolean isSuccess, String tradeOffId, Long payWay, String message) {
		OrderProcessingQueue opq=new OrderProcessingQueue();
		opq.setCreateTime(new Date());
		opq.setType(OrderProcessingQueueConstant.ORDER_QUEUE_TYPE_PAY_CALLBACK);
		opq.setOrderNo(orderNo);
		JSONObject obj=new JSONObject();
		obj.put("isSuccess",isSuccess);
		obj.put("tradeOffId",tradeOffId);
		obj.put("payWay",payWay);
		obj.put("message",message);
		opq.setParams(JSON.toJSONString(obj));
		opq.setStatus(OrderProcessingQueueConstant.ORDER_EXECUTE_STATUS_UN);
		orderProcessingQueueMapper.saveOrderProcessingQueue(opq);
		SystemConstant.addOrderQueue(opq);
		return true;
	}
	@Async
	public void executeOrderProcessingQueue() {
		logger.info("进入到订单执行队列");
		while (true){
			OrderProcessingQueue opq=SystemConstant.executeQueue();
			if(ObjectUtils.isNotEmpty(opq)){
				try{
					orderProcessingQueue(opq);
				}catch(Exception e){

				}finally {

				}
			}else{
				try{
					logger.info("暂无订单数据处理，任务休眠一秒钟");
					Thread.sleep(1000);

				}catch(Exception e){

				}
			}
		}
	}
	private void orderProcessingQueue(OrderProcessingQueue opq)throws AppException{
		DataSourceTransactionManager transactionManager = (DataSourceTransactionManager) SpringUtils.getBean("transactionManager");
		DefaultTransactionDefinition def = new DefaultTransactionDefinition();
		def.setPropagationBehavior(TransactionDefinition.PROPAGATION_REQUIRED); // 事物隔离级
		TransactionStatus ts = transactionManager.getTransaction(def);
		try{
			//判断处理类型 修改订单状态
			if(opq.getType().intValue()==OrderProcessingQueueConstant.ORDER_QUEUE_TYPE_UPDATE){
				orderService.updateOrderStatus(opq);
			}else if(opq.getType().intValue()==OrderProcessingQueueConstant.ORDER_QUEUE_TYPE_PAY_CALLBACK){
				orderService.orderPayCallback(opq);
			}else if(opq.getType().intValue()==OrderProcessingQueueConstant.ORDER_QUEUE_TYPE_REFUND){

			}
			transactionManager.commit(ts);
		}catch(AppException e){
			transactionManager.rollback(ts);
			throw new AppException(e.getMessage());
		}catch(Exception e){
			transactionManager.rollback(ts);
			throw new AppException(e.getMessage());
		}

	}

	public void test(OrderProcessingQueue opq )throws AppException{
		orderProcessingQueue(opq);
	}
}

