package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：楼栋关系表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-15 14:22:08
 */
public interface FloorRelationshipMapper {

	/**
	 * 添加楼栋关系表
	 * @param floorRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public int saveFloorRelationship(FloorRelationship floorRelationship);
	/**
	 * 编辑楼栋关系表
	 * @param floorRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public int updateFloorRelationship(FloorRelationship floorRelationship);
	/**
	 * 根据FrId删除楼栋关系表
	 * @param frId
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public int deleteFloorRelationshipByFrId(Long frId);
	/**
	 * 根据FrId查询楼栋关系表
	 * @param frId
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public FloorRelationship queryFloorRelationshipByFrId(Long frId);
	/**
	 * 查询楼栋关系表列表
	 * @param pagination 分页对象
	 * @return  楼栋关系表列表
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public List<FloorRelationship> queryFloorRelationshipList(Pagination<FloorRelationship> pagination);


	/**
	 * 查询与楼栋所绑定的户型
	 * @param fmId 楼栋ID
	 * @return  楼栋所绑定的户型列表
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public List<FloorRelationship> queryFloorType(Long fmId);

}
