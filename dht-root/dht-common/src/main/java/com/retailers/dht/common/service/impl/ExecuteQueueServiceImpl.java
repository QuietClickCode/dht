
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.entity.ExecuteQueue;
import com.retailers.dht.common.dao.ExecuteQueueMapper;
import com.retailers.dht.common.service.ExecuteQueueService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：执行队列Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 23:43:17
 */
@Service("executequeueService")
public class ExecuteQueueServiceImpl implements ExecuteQueueService {
	@Autowired
	private ExecuteQueueMapper executeQueueMapper;
	public boolean saveExecuteQueue(ExecuteQueue executeQueue) {
		int status = executeQueueMapper.saveExecuteQueue(executeQueue);
		return status == 1 ? true : false;
	}
	public boolean updateExecuteQueue(ExecuteQueue executeQueue) {
		int status = executeQueueMapper.updateExecuteQueue(executeQueue);
		return status == 1 ? true : false;
	}
	public ExecuteQueue queryExecuteQueueById(Long id) {
		return executeQueueMapper.queryExecuteQueueById(id);
	}

	public Pagination<ExecuteQueue> queryExecuteQueueList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ExecuteQueue> page = new Pagination<ExecuteQueue>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ExecuteQueue> list = executeQueueMapper.queryExecuteQueueList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteExecuteQueueById(Long id) {
		int status = executeQueueMapper.deleteExecuteQueueById(id);
		return status == 1 ? true : false;
	}
	@Async
	public void addHistoryExecuteQueue(ExecuteQueue executeQueue) {
		System.out.println("addHistoryExecuteQueue------------------------------------->>");
		try{
			Thread.sleep(1000*3);
		}catch (Exception e){

		}
//		if(ObjectUtils.isNotEmpty(executeQueue.getId())){
//			deleteExecuteQueueById(executeQueue.getId());
//		}
//		if(ObjectUtils.isNotEmpty(executeQueue.getSeqRemark())&&executeQueue.getSeqRemark().length()>800){
//			executeQueue.setSeqRemark(executeQueue.getSeqRemark().substring(0,800));
//		}
//		executeQueueMapper.saveExecuteQueueHistory(executeQueue);
		System.out.println("执行完毕------------------------------------->>");
	}
}

