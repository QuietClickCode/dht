package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：楼栋关系表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-18 11:07:35
 */
public interface FloorRelationshipMapper {

	/**
	 * 添加楼栋关系表
	 * @param floorRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public int saveFloorRelationship(FloorRelationship floorRelationship);
	/**
	 * 编辑楼栋关系表
	 * @param floorRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public int updateFloorRelationship(FloorRelationship floorRelationship);
	/**
	 * 根据FlId删除楼栋关系表
	 * @param flId
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public int deleteFloorRelationshipByFlId(Long flId);
	/**
	 * 根据FlId查询楼栋关系表
	 * @param flId
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public FloorRelationship queryFloorRelationshipByFlId(Long flId);
	/**
	 * 查询楼栋关系表列表
	 * @param pagination 分页对象
	 * @return  楼栋关系表列表
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public List<FloorRelationship> queryFloorRelationshipList(Pagination<FloorRelationship> pagination);

	/**
	 * 根据楼栋ID查询该楼栋所关联的户型
	 * @param fmId 楼栋ID
	 * @return  楼栋关系表列表
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public List<HouseTypeManage> queryFloorType(Long fmId);

	/**
	 * 根据户型ID查询该楼栋所关联的楼栋
	 * @param htId 户型ID
	 * @return  楼栋关系表列表
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public List<FloorManage> queryHouseType(Long htId);
}
