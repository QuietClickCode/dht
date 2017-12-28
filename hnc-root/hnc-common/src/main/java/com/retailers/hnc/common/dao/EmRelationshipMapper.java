package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.EmRelationship;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：开盘和置业顾问关系表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 14:55:17
 */
public interface EmRelationshipMapper {

	/**
	 * 添加开盘和置业顾问关系表
	 * @param emRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 14:55:17
	 */
	public int saveEmRelationship(EmRelationship emRelationship);
	/**
	 * 编辑开盘和置业顾问关系表
	 * @param emRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 14:55:17
	 */
	public int updateEmRelationship(EmRelationship emRelationship);
	/**
	 * 根据ErId删除开盘和置业顾问关系表
	 * @param erId
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 14:55:17
	 */
	public int deleteEmRelationshipByErId(Long erId);
	/**
	 * 根据ErId查询开盘和置业顾问关系表
	 * @param erId
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 14:55:17
	 */
	public EmRelationship queryEmRelationshipByErId(Long erId);
	/**
	 * 查询开盘和置业顾问关系表列表
	 * @param pagination 分页对象
	 * @return  开盘和置业顾问关系表列表
	 * @author wangjue
	 * @date 2017-12-28 14:55:17
	 */
	public List<EmRelationship> queryEmRelationshipList(Pagination<EmRelationship> pagination);

}
