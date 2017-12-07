package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.CutPriceLog;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：砍价日志表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-07 15:50:55
 */
public interface CutPriceLogMapper {

	/**
	 * 添加砍价日志表
	 * @param cutPriceLog
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public int saveCutPriceLog(CutPriceLog cutPriceLog);
	/**
	 * 编辑砍价日志表
	 * @param cutPriceLog
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public int updateCutPriceLog(CutPriceLog cutPriceLog);
	/**
	 * 根据CplId删除砍价日志表
	 * @param cplId
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public int deleteCutPriceLogByCplId(Long cplId);
	/**
	 * 根据CplId查询砍价日志表
	 * @param cplId
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public CutPriceLog queryCutPriceLogByCplId(Long cplId);
	/**
	 * 查询砍价日志表列表
	 * @param pagination 分页对象
	 * @return  砍价日志表列表
	 * @author fanghui
	 * @date 2017-12-07 15:50:55
	 */
	public List<CutPriceLog> queryCutPriceLogList(Pagination<CutPriceLog> pagination);

}
