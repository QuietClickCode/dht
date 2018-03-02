
package com.retailers.sbj.common.service.impl;

import com.retailers.sbj.common.dao.FloorManageMapper;
import com.retailers.sbj.common.dao.FloorRelationshipMapper;
import com.retailers.sbj.common.entity.FloorManage;
import com.retailers.sbj.common.entity.FloorRelationship;
import com.retailers.sbj.common.entity.HouseTypeManage;
import com.retailers.sbj.common.service.FloorManageService;
import com.retailers.sbj.common.service.FloorRelationshipService;
import com.retailers.sbj.common.vo.FloorManageVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：楼栋管理Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-15 14:23:58
 */
@Service("floormanageService")
public class FloorManageServiceImpl implements FloorManageService {
	@Autowired
	private FloorManageMapper floorManageMapper;

	@Autowired
	private FloorRelationshipService relationshipService;

	@Autowired
	private FloorRelationshipMapper relationshipMapper;

	public boolean saveFloorManage(FloorManage floorManage) {
		int status = floorManageMapper.saveFloorManage(floorManage);
		return status == 1 ? true : false;
	}
	public boolean updateFloorManage(FloorManage floorManage) {
		FloorManage manage = queryFloorManageByFmId(floorManage.getFmId());
		floorManage.setVersion(manage.getVersion());
		int status = floorManageMapper.updateFloorManage(floorManage);
		return status == 1 ? true : false;
	}
	public FloorManage queryFloorManageByFmId(Long fmId) {
		FloorManage floorManage = floorManageMapper.queryFloorManageByFmId(fmId);
		return floorManage;
	}

	public Pagination<FloorManageVo> queryFloorManageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<FloorManageVo> page = new Pagination<FloorManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FloorManageVo> list = floorManageMapper.queryFloorManageList(page);
		for(FloorManageVo manageVo:list){
			manageVo.setTypeManages(relationshipMapper.queryFloorType(manageVo.getFmId()));
		}
		page.setData(list);
		return page;
	}

	public Pagination<FloorManageVo> queryFirstFloorManageList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<FloorManageVo> page = new Pagination<FloorManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FloorManageVo> list = floorManageMapper.queryFloorManageList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteFloorManageByFmId(Long fmId) {
		FloorManage manage = queryFloorManageByFmId(fmId);
		manage.setIsDelete(1);
		int status = floorManageMapper.updateFloorManage(manage);
		return status == 1 ? true : false;
	}

	public boolean addFloorRelationship(List<FloorRelationship> relationships){
		boolean flag = false;
		if(relationships != null){
			Long fmId = null;
			FloorRelationship relationship = relationships.get(0);
			fmId = relationship.getFmId();
			HashMap<Long,FloorRelationship> map = new HashMap<Long, FloorRelationship>();
			for (FloorRelationship floorRelationship : relationships) {
				map.put(floorRelationship.getFrId(),floorRelationship);
			}
			List<HouseTypeManage> houseTypeManages = relationshipService.queryFloorType(fmId);
			for (HouseTypeManage houseTypeManage : houseTypeManages) {
				if(map.get(houseTypeManage.getHtId()) == null){
					flag = relationshipService.queryHouseTypeRelationship(fmId,houseTypeManage.getHtId());
				}else if(map.get(houseTypeManage.getHtId()) != null){
					map.remove(houseTypeManage.getHtId());
				}
			}

			for (Map.Entry<Long, FloorRelationship> entry : map.entrySet()) {
				entry.getValue().setIsDelete(0);
				flag = relationshipService.saveFloorRelationship(entry.getValue());
			}
		}
		return flag;
	}
	public List<FloorManage> queryFloorManageByFmIds(String fids){
		if(ObjectUtils.isNotEmpty(fids)){
			String[] fidsArr = fids.split(",");
			List<Long> fidsList = new ArrayList<Long>();
			for(String fidStr:fidsArr){
				Long fidLong = Long.parseLong(fidStr);
				fidsList.add(fidLong);
			}
			List<FloorManage> returnList = floorManageMapper.queryFloorManageByFmIds(fidsList);
			return returnList;
		}
		return null;
	}

}

