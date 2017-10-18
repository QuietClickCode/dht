package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.LogManagerReq;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：后台请求日志DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 10:02:09
 */
public interface LogManagerReqMapper {

	/**
	 * 添加后台请求日志
	 * @param logManagerReq
	 * @return
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public int saveLogManagerReq(LogManagerReq logManagerReq);
	/**
	 * 编辑后台请求日志
	 * @param logManagerReq
	 * @return
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public int updateLogManagerReq(LogManagerReq logManagerReq);
	/**
	 * 根据Id删除后台请求日志
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public int deleteLogManagerReqById(Long id);
	/**
	 * 根据Id查询后台请求日志
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public LogManagerReq queryLogManagerReqById(Long id);
	/**
	 * 查询后台请求日志列表
	 * @param pagination 分页对象
	 * @return  后台请求日志列表
	 * @author zhongp
	 * @date 2017-10-18 10:02:09
	 */
	public List<LogManagerReq> queryLogManagerReqList(Pagination<LogManagerReq> pagination);

}
