
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.ExecuteQueue;
import java.util.Map;
import java.util.Queue;

/**
 * 描述：执行队列Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 23:43:17
 */
public interface ExecuteQueueService {

	/**
	 * 添加执行队列
	 * @param executeQueue
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public boolean saveExecuteQueue(ExecuteQueue executeQueue);
	/**
	 * 编辑执行队列
	 * @param executeQueue
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateExecuteQueue(ExecuteQueue executeQueue);
	/**
	 * 根据id查询执行队列
	 * @param id
	 * @return executeQueue
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public ExecuteQueue queryExecuteQueueById(Long id);
	/**
	 * 查询执行队列列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public Pagination<ExecuteQueue> queryExecuteQueueList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除执行队列
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public boolean deleteExecuteQueueById(Long id);

	/**
	 * 添加历史记录
	 * @param executeQueue
	 */
	public void addHistoryExecuteQueue(ExecuteQueue executeQueue);

}


