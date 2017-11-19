package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.ExecuteQueue;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：执行队列DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 23:43:17
 */
public interface ExecuteQueueMapper {

	/**
	 * 添加执行队列
	 * @param executeQueue
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public int saveExecuteQueue(ExecuteQueue executeQueue);

	/**
	 * 添加历史
	 * @param executeQueue
	 * @return
	 */
	public int saveExecuteQueueHistory(ExecuteQueue executeQueue);
	/**
	 * 编辑执行队列
	 * @param executeQueue
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public int updateExecuteQueue(ExecuteQueue executeQueue);
	/**
	 * 根据Id删除执行队列
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public int deleteExecuteQueueById(Long id);
	/**
	 * 根据Id查询执行队列
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public ExecuteQueue queryExecuteQueueById(Long id);
	/**
	 * 查询执行队列列表
	 * @param pagination 分页对象
	 * @return  执行队列列表
	 * @author zhongp
	 * @date 2017-11-16 23:43:17
	 */
	public List<ExecuteQueue> queryExecuteQueueList(Pagination<ExecuteQueue> pagination);

}
