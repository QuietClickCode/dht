
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.dao.FloorRelationshipMapper;
import com.retailers.hnc.common.service.FloorRelationshipService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：楼栋关系表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-15 14:22:08
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
		int status = floorRelationshipMapper.updateFloorRelationship(floorRelationship);
		return status == 1 ? true : false;
	}
	public FloorRelationship queryFloorRelationshipByFrId(Long frId) {
		return floorRelationshipMapper.queryFloorRelationshipByFrId(frId);
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
	public boolean deleteFloorRelationshipByFrId(Long frId) {
		int status = floorRelationshipMapper.deleteFloorRelationshipByFrId(frId);
		return status == 1 ? true : false;
	}
}

