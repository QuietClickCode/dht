
package com.retailers.hnc.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.OpeningFloor;
import java.util.Map;
/**
 * 描述：开盘与楼栋关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 16:49:53
 */
public interface OpeningFloorService {

	/**
	 * 添加开盘与楼栋关系表
	 * @param openingFloor
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public boolean saveOpeningFloor(OpeningFloor openingFloor);
	/**
	 * 编辑开盘与楼栋关系表
	 * @param openingFloor
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateOpeningFloor(OpeningFloor openingFloor);
	/**
	 * 根据id查询开盘与楼栋关系表
	 * @param ofId
	 * @return openingFloor
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public OpeningFloor queryOpeningFloorByOfId(Long ofId);
	/**
	 * 查询开盘与楼栋关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public Pagination<OpeningFloor> queryOpeningFloorList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ofId删除开盘与楼栋关系表
	 * @param ofId
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 16:49:53
	 */
	public boolean deleteOpeningFloorByOfId(Long ofId);

}


