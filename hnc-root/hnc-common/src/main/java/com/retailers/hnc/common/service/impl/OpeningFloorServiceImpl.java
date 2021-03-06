
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.OpeningFloor;
import com.retailers.hnc.common.dao.OpeningFloorMapper;
import com.retailers.hnc.common.service.OpeningFloorService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：开盘与楼栋关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 16:49:53
 */
@Service("openingfloorService")
public class OpeningFloorServiceImpl implements OpeningFloorService {
	@Autowired
	private OpeningFloorMapper openingFloorMapper;
	public boolean saveOpeningFloor(OpeningFloor openingFloor) {
		int status = openingFloorMapper.saveOpeningFloor(openingFloor);
		return status == 1 ? true : false;
	}
	public boolean updateOpeningFloor(OpeningFloor openingFloor) {
		int status = openingFloorMapper.updateOpeningFloor(openingFloor);
		return status == 1 ? true : false;
	}
	public OpeningFloor queryOpeningFloorByOfId(Long ofId) {
		return openingFloorMapper.queryOpeningFloorByOfId(ofId);
	}

	public Pagination<OpeningFloor> queryOpeningFloorList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<OpeningFloor> page = new Pagination<OpeningFloor>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<OpeningFloor> list = openingFloorMapper.queryOpeningFloorList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOpeningFloorByOfId(Long ofId) {
		int status = openingFloorMapper.deleteOpeningFloorByOfId(ofId);
		return status == 1 ? true : false;
	}
}

