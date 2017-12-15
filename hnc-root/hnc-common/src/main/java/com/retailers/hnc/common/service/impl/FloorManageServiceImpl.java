
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.hnc.common.dao.FloorRelationshipMapper;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.dao.FloorManageMapper;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.service.FloorManageService;
import com.retailers.hnc.common.vo.FloorManageVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
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
	private FloorRelationshipMapper relationshipMapper;

	public boolean saveFloorManage(FloorManage floorManage) {
		int status = floorManageMapper.saveFloorManage(floorManage);
		return status == 1 ? true : false;
	}
	public boolean updateFloorManage(FloorManage floorManage) {
		int status = floorManageMapper.updateFloorManage(floorManage);
		return status == 1 ? true : false;
	}
	public FloorManageVo queryFloorManageByFmId(Long fmId) {
		FloorManageVo floorManageVo = floorManageMapper.queryFloorManageByFmId(fmId);
		List<FloorRelationship> list = relationshipMapper.queryFloorType(fmId);
		floorManageVo.setRelationships(list);
		return floorManageVo;
	}

	public Pagination<FloorManageVo> queryFloorManageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<FloorManageVo> page = new Pagination<FloorManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<FloorManageVo> list = floorManageMapper.queryFloorManageList(page);
		for(FloorManageVo manageVo:list){
			manageVo.setRelationships(relationshipMapper.queryFloorType(manageVo.getFmId()));
		}
		page.setData(list);
		return page;
	}
	public boolean deleteFloorManageByFmId(Long fmId) {
		int status = floorManageMapper.deleteFloorManageByFmId(fmId);
		return status == 1 ? true : false;
	}
}

