
package com.retailers.razz.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.razz.common.entity.PositionManage;
import com.retailers.razz.common.dao.PositionManageMapper;
import com.retailers.razz.common.service.PositionManageService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：职位表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-27 16:06:04
 */
@Service("positionmanageService")
public class PositionManageServiceImpl implements PositionManageService {
	@Autowired
	private PositionManageMapper positionManageMapper;
	public boolean savePositionManage(PositionManage positionManage) {
		int status = positionManageMapper.savePositionManage(positionManage);
		return status == 1 ? true : false;
	}
	public boolean updatePositionManage(PositionManage positionManage) {
		int status = positionManageMapper.updatePositionManage(positionManage);
		return status == 1 ? true : false;
	}
	public PositionManage queryPositionManageById(Long id) {
		return positionManageMapper.queryPositionManageById(id);
	}

	public Pagination<PositionManage> queryPositionManageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<PositionManage> page = new Pagination<PositionManage>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<PositionManage> list = positionManageMapper.queryPositionManageList(page);
		page.setData(list);
		return page;
	}
	public boolean deletePositionManageById(Long id) {
		PositionManage positionManage = positionManageMapper.queryPositionManageById(id);
		positionManage.setIsDelete(1L);
		int status = positionManageMapper.updatePositionManage(positionManage);
		return status == 1 ? true : false;
	}
}

