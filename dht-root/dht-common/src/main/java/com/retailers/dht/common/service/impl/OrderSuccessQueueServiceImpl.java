
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.OrderSuccessQueue;
import com.retailers.dht.common.dao.OrderSuccessQueueMapper;
import com.retailers.dht.common.service.OrderSuccessQueueService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
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
}

