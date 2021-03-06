
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
import com.retailers.dht.common.service.AsyncService;
import com.retailers.dht.common.service.OrderProcessingQueueService;
import com.retailers.dht.common.service.OrderService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.SpringUtils;
import com.retailers.tools.utils.StringUtils;
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
@Service("orderProcessingQueueService")
public class OrderProcessingQueueServiceImpl implements OrderProcessingQueueService {
	Logger logger= LoggerFactory.getLogger(OrderProcessingQueueServiceImpl.class);
	@Autowired
	private OrderProcessingQueueMapper orderProcessingQueueMapper;
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderMapper orderMapper;
	@Autowired
	private AsyncService asyncService;


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
	 * @param orderPayWay 支付方式(0 微信，1 支付宝，2 钱包）
	 * @param payWay 支付方式
	 * @param message 消息
	 * @return
	 */
	@Async
	public boolean addQueue(String orderNo, boolean isSuccess, String tradeOffId,Integer orderPayWay, Long payWay, String message) {
		OrderProcessingQueue opq=new OrderProcessingQueue();
		opq.setCreateTime(new Date());
		opq.setType(OrderProcessingQueueConstant.ORDER_QUEUE_TYPE_PAY_CALLBACK);
		opq.setOrderNo(orderNo);
		JSONObject obj=new JSONObject();
		obj.put("isSuccess",isSuccess);
		obj.put("tradeOffId",tradeOffId);
		obj.put("orderPayWay",orderPayWay);
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
					opq.setRemark("订单回调成功");
					opq.setStatus(OrderProcessingQueueConstant.ORDER_EXECUTE_STATUS_SUCCESS);
				}catch(Exception e){
					e.printStackTrace();
					String message= StringUtils.getErrorInfoFromException(e);
					if(ObjectUtils.isNotEmpty(message)){
						if(message.length()>400){
							message=message.substring(0,400);
						}
					}
					opq.setRemark(message);
					opq.setStatus(OrderProcessingQueueConstant.ORDER_EXECUTE_STATUS_FAILE);
				}finally {
					Date curDate=new Date();
					opq.setExecuteTime(curDate);
					opq.setWaitTime(curDate.getTime()-opq.getCreateTime().getTime());
					asyncService.orderCallback(opq);
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

	/**
	 * 消息队例处理
	 * @param opq
	 * @throws AppException
	 */
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
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}catch(Exception e){
			transactionManager.rollback(ts);
			e.printStackTrace();
			throw new AppException(e.getMessage());
		}

	}

	/**
	 * 初始化未处理的订单
	 */
	public void initProcessingQueue() {
		List<OrderProcessingQueue> lists =orderProcessingQueueMapper.initProcessingQueue();
		if(ObjectUtils.isNotEmpty(lists)){
			for(OrderProcessingQueue opq:lists){
				SystemConstant.addOrderQueue(opq);
			}
		}
	}

	/**
	 *
	 * @param orderProcessingQueue
	 */
	public void addProcessingQueueToHistory(OrderProcessingQueue orderProcessingQueue) {
		logger.info("开始添加订单回调处理至历史");
		Date date=new Date();
		try{
			//添加订单回调处理至队列
			orderProcessingQueueMapper.saveOrderProcessingQueueHistory(orderProcessingQueue);
			//删除原数据
			orderProcessingQueueMapper.deleteOrderProcessingQueueById(orderProcessingQueue.getId());
		}finally {
			logger.info("添加订单回调处理至历史结束，执行时间:[{}]",(System.currentTimeMillis()-date.getTime()));
		}
	}

	public void test(OrderProcessingQueue opq )throws AppException{
		orderProcessingQueue(opq);
	}
}

