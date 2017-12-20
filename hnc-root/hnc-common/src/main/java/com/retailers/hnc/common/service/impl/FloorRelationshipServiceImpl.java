
package com.retailers.hnc.common.service.impl;

import com.retailers.hnc.common.dao.FloorRelationshipMapper;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.service.FloorRelationshipService;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
/**
 * 描述：楼栋关系表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-18 11:07:35
 */
@Service("floorrelationshipService")
public class FloorRelationshipServiceImpl implements FloorRelationshipService {
	@Autowired
	private FloorRelationshipMapper floorRelationshipMapper;
	public boolean saveFloorRelationship(FloorRelationship floorRelationship) {
		int status = floorRelationshipMapper.saveFloorRelationship(floorRelationship);
		return status == 1 ? true : false;
	}
	public boolean updateFloorRelationship(FloorRelationship floorRelationship) {
		Long version = queryFloorRelationshipByFlId(floorRelationship.getFlId()).getVersion();
		floorRelationship.setVersion(version);
		int status = floorRelationshipMapper.updateFloorRelationship(floorRelationship);
		return status == 1 ? true : false;
	}
	public FloorRelationship queryFloorRelationshipByFlId(Long flId) {
		return floorRelationshipMapper.queryFloorRelationshipByFlId(flId);
	}

	public Pagination<FloorRelationship> queryFloorRelationshipList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<FloorRelationship> page = new Pagination<FloorRelationship>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FloorRelationship> list = floorRelationshipMapper.queryFloorRelationshipList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteFloorRelationshipByFlId(Long flId) {
		FloorRelationship relationship = queryFloorRelationshipByFlId(flId);
		relationship.setIsDelete(1);
		int status = floorRelationshipMapper.updateFloorRelationship(relationship);
		return status == 1 ? true : false;
	}

	public List<HouseTypeManage> queryFloorType(Long fmId) {
		return floorRelationshipMapper.queryFloorType(fmId);
	}

	public List<FloorManage> queryHouseType(Long htId) {
		return floorRelationshipMapper.queryHouseType(htId);
	}
}

