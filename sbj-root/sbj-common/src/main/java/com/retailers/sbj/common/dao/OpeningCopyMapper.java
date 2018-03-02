package com.retailers.sbj.common.dao;

import com.retailers.sbj.common.entity.OpeningCopy;
import com.retailers.sbj.common.vo.OpeningCopyVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;

/**
 * 描述：开盘副本表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:01:16
 */
public interface OpeningCopyMapper {

	/**
	 * 添加开盘副本表
	 * @param openingCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public int saveOpeningCopy(OpeningCopy openingCopy);
	/**
	 * 编辑开盘副本表
	 * @param openingCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public int updateOpeningCopy(OpeningCopy openingCopy);
	/**
	 * 根据OcId删除开盘副本表
	 * @param ocId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public int deleteOpeningCopyByOcId(Long ocId);
	/**
	 * 根据OcId查询开盘副本表
	 * @param ocId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public OpeningCopy queryOpeningCopyByOcId(Long ocId);
	/**
	 * 查询开盘副本表列表
	 * @param pagination 分页对象
	 * @return  开盘副本表列表
	 * @author fanghui
	 * @date 2018-01-22 12:01:16
	 */
	public List<OpeningCopyVo> queryOpeningCopyList(Pagination<OpeningCopyVo> pagination);

}
