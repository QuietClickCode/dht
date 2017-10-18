
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.LogManagerReq;
import java.util.Map;
/**
 * 描述：后台请求日志Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 10:02:09
 */
public interface LogManagerReqService {

	/**
	 * 添加后台请求日志
	 * @param logManagerReq
	 * @return
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public boolean saveLogManagerReq(LogManagerReq logManagerReq);
	/**
	 * 编辑后台请求日志
	 * @param logManagerReq
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateLogManagerReq(LogManagerReq logManagerReq);
	/**
	 * 根据id查询后台请求日志
	 * @param id
	 * @return logManagerReq
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public LogManagerReq queryLogManagerReqById(Long id);
	/**
	 * 查询后台请求日志列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public Pagination<LogManagerReq> queryLogManagerReqList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除后台请求日志
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public boolean deleteLogManagerReqById(Long id);

}


