
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.ConsumeCashBackQueue;
import com.retailers.dht.common.dao.ConsumeCashBackQueueMapper;
import com.retailers.dht.common.service.ConsumeCashBackQueueService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：第三方消费返现Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:46:01
 */
@Service("consumecashbackqueueService")
public class ConsumeCashBackQueueServiceImpl implements ConsumeCashBackQueueService {
	@Autowired
	private ConsumeCashBackQueueMapper consumeCashBackQueueMapper;
	public boolean saveConsumeCashBackQueue(ConsumeCashBackQueue consumeCashBackQueue) {
		int status = consumeCashBackQueueMapper.saveConsumeCashBackQueue(consumeCashBackQueue);
		return status == 1 ? true : false;
	}
	public boolean updateConsumeCashBackQueue(ConsumeCashBackQueue consumeCashBackQueue) {
		int status = consumeCashBackQueueMapper.updateConsumeCashBackQueue(consumeCashBackQueue);
		return status == 1 ? true : false;
	}
	public ConsumeCashBackQueue queryConsumeCashBackQueueByIcbqId(Long icbqId) {
		return consumeCashBackQueueMapper.queryConsumeCashBackQueueByIcbqId(icbqId);
	}

	public Pagination<ConsumeCashBackQueue> queryConsumeCashBackQueueList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ConsumeCashBackQueue> page = new Pagination<ConsumeCashBackQueue>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ConsumeCashBackQueue> list = consumeCashBackQueueMapper.queryConsumeCashBackQueueList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteConsumeCashBackQueueByIcbqId(Long icbqId) {
		int status = consumeCashBackQueueMapper.deleteConsumeCashBackQueueByIcbqId(icbqId);
		return status == 1 ? true : false;
	}
}

