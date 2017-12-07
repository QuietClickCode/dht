
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.CutPriceLog;
import java.util.Map;
/**
 * 描述：砍价日志表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-07 15:50:55
 */
public interface CutPriceLogService {

	/**
	 * 添加砍价日志表
	 * @param cutPriceLog
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public boolean saveCutPriceLog(CutPriceLog cutPriceLog);
	/**
	 * 编辑砍价日志表
	 * @param cutPriceLog
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateCutPriceLog(CutPriceLog cutPriceLog);
	/**
	 * 根据id查询砍价日志表
	 * @param cplId
	 * @return cutPriceLog
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public CutPriceLog queryCutPriceLogByCplId(Long cplId);
	/**
	 * 查询砍价日志表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public Pagination<CutPriceLog> queryCutPriceLogList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cplId删除砍价日志表
	 * @param cplId
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public boolean deleteCutPriceLogByCplId(Long cplId);

}


