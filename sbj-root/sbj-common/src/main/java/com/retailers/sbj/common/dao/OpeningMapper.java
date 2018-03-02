package com.retailers.sbj.common.dao;

import com.retailers.sbj.common.entity.Opening;
import com.retailers.sbj.common.vo.OpeningVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;

/**
 * 描述：开盘表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 16:36:52
 */
public interface OpeningMapper {

	/**
	 * 添加开盘表
	 * @param opening
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public int saveOpening(Opening opening);
	/**
	 * 编辑开盘表
	 * @param opening
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public int updateOpening(Opening opening);
	/**
	 * 根据Oid删除开盘表
	 * @param oid
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public int deleteOpeningByOid(Long oid);
	/**
	 * 根据Oid查询开盘表
	 * @param oid
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public Opening queryOpeningByOid(Long oid);
	/**
	 * 查询开盘表列表
	 * @param pagination 分页对象
	 * @return  开盘表列表
	 * @author fanghui
	 * @date 2017-12-22 16:36:52
	 */
	public List<Opening> queryOpeningList(Pagination<Opening> pagination);

	public List<OpeningVo> queryOpeningVoList(Pagination<OpeningVo> pagination);

	public List<OpeningVo> queryOFrelByOid(Long oid);

	public Opening queryEarlyOpening();

	public Opening queryLastOpening();

	public Opening queryRuningOpening();
}
