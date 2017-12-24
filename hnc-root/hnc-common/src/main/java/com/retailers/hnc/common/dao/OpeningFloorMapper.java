package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.OpeningFloor;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：开盘与楼栋关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 16:49:53
 */
public interface OpeningFloorMapper {

	/**
	 * 添加开盘与楼栋关系表
	 * @param openingFloor
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public int saveOpeningFloor(OpeningFloor openingFloor);
	/**
	 * 编辑开盘与楼栋关系表
	 * @param openingFloor
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public int updateOpeningFloor(OpeningFloor openingFloor);
	/**
	 * 根据OfId删除开盘与楼栋关系表
	 * @param ofId
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public int deleteOpeningFloorByOfId(Long ofId);
	/**
	 * 根据OfId查询开盘与楼栋关系表
	 * @param ofId
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public OpeningFloor queryOpeningFloorByOfId(Long ofId);
	/**
	 * 查询开盘与楼栋关系表列表
	 * @param pagination 分页对象
	 * @return  开盘与楼栋关系表列表
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public List<OpeningFloor> queryOpeningFloorList(Pagination<OpeningFloor> pagination);

}
