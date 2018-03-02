
package com.retailers.sbj.common.service;

import com.retailers.sbj.common.entity.FloorManage;
import com.retailers.sbj.common.entity.FloorRelationship;
import com.retailers.sbj.common.entity.HouseTypeManage;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;

/**
 * 描述：楼栋关系表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-18 11:07:35
 */
public interface FloorRelationshipService {

	/**
	 * 添加楼栋关系表
	 * @param floorRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public boolean saveFloorRelationship(FloorRelationship floorRelationship);
	/**
	 * 编辑楼栋关系表
	 * @param floorRelationship
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateFloorRelationship(FloorRelationship floorRelationship);
	/**
	 * 根据id查询楼栋关系表
	 * @param flId
	 * @return floorRelationship
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public FloorRelationship queryFloorRelationshipByFlId(Long flId);
	/**
	 * 查询楼栋关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public Pagination<FloorRelationship> queryFloorRelationshipList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据flId删除楼栋关系表
	 * @param flId
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:07:35
	 */
	public boolean deleteFloorRelationshipByFlId(Long flId);

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


	public boolean queryFloorRelationship(Long htId, Long hrId);


	public boolean queryHouseTypeRelationship(Long fmId, Long frId);
}


