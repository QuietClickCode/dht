
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.EmployeeManageCopy;
import com.retailers.hnc.common.dao.EmployeeManageCopyMapper;
import com.retailers.hnc.common.service.EmployeeManageCopyService;
import com.retailers.hnc.common.vo.EmployeeManageCopyVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：员工副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:01:36
 */
@Service("employeemanagecopyService")
public class EmployeeManageCopyServiceImpl implements EmployeeManageCopyService {
	@Autowired
	private EmployeeManageCopyMapper employeeManageCopyMapper;
	public boolean saveEmployeeManageCopy(EmployeeManageCopy employeeManageCopy) {
		int status = employeeManageCopyMapper.saveEmployeeManageCopy(employeeManageCopy);
		return status == 1 ? true : false;
	}
	public boolean updateEmployeeManageCopy(EmployeeManageCopy employeeManageCopy) {
		int status = employeeManageCopyMapper.updateEmployeeManageCopy(employeeManageCopy);
		return status == 1 ? true : false;
	}
	public EmployeeManageCopy queryEmployeeManageCopyByEmcId(Long emcId) {
		return employeeManageCopyMapper.queryEmployeeManageCopyByEmcId(emcId);
	}

	public Pagination<EmployeeManageCopyVo> queryEmployeeManageCopyList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<EmployeeManageCopyVo> page = new Pagination<EmployeeManageCopyVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<EmployeeManageCopyVo> list = employeeManageCopyMapper.queryEmployeeManageCopyList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteEmployeeManageCopyByEmcId(Long emcId) {
		int status = employeeManageCopyMapper.deleteEmployeeManageCopyByEmcId(emcId);
		return status == 1 ? true : false;
	}
}

