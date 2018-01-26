
package com.retailers.hnc.common.service;
import com.retailers.hnc.common.vo.OpeningCopyVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.OpeningCopy;
import java.util.Map;
/**
 * 描述：开盘副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:01:16
 */
public interface OpeningCopyService {

	/**
	 * 添加开盘副本表
	 * @param openingCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public boolean saveOpeningCopy(OpeningCopy openingCopy);
	/**
	 * 编辑开盘副本表
	 * @param openingCopy
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateOpeningCopy(OpeningCopy openingCopy);
	/**
	 * 根据id查询开盘副本表
	 * @param ocId
	 * @return openingCopy
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public OpeningCopy queryOpeningCopyByOcId(Long ocId);
	/**
	 * 查询开盘副本表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public Pagination<OpeningCopyVo> queryOpeningCopyList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ocId删除开盘副本表
	 * @param ocId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public boolean deleteOpeningCopyByOcId(Long ocId);

}


