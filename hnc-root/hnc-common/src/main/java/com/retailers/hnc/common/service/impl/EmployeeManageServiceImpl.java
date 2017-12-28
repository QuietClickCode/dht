
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;

import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.dao.EmployeeManageMapper;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.vo.EmployeeManageVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：员工管理Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 12:01:09
 */
@Service("employeemanageService")
public class EmployeeManageServiceImpl implements EmployeeManageService {
	@Autowired
	private EmployeeManageMapper employeeManageMapper;
	public boolean saveEmployeeManage(EmployeeManage employeeManage) {
		int status = employeeManageMapper.saveEmployeeManage(employeeManage);
		return status == 1 ? true : false;
	}
	public boolean updateEmployeeManage(EmployeeManage employeeManage) {
		EmployeeManage manage = queryEmployeeManageByEmId(employeeManage.getEmId());
		employeeManage.setVersion(manage.getVersion());
		int status = employeeManageMapper.updateEmployeeManage(employeeManage);
		return status == 1 ? true : false;
	}
	public EmployeeManage queryEmployeeManageByEmId(Long emId) {
		return employeeManageMapper.queryEmployeeManageByEmId(emId);
	}

	public Pagination<EmployeeManageVo> queryEmployeeManageList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<EmployeeManageVo> page = new Pagination<EmployeeManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<EmployeeManageVo> list = employeeManageMapper.queryEmployeeManageList(page);
		for(EmployeeManageVo manageVo:list){
			manageVo.setCurRegisterClientCount(queryCurRegisterClient(manageVo.getEmId()));
			manageVo.setRegisterClientCount(queryRegisterClient(manageVo.getEmId()));
		}
		page.setData(list);
		return page;
	}
	public boolean deleteEmployeeManageByEmId(Long emId) {
		EmployeeManage employeeManage = queryEmployeeManageByEmId(emId);
		employeeManage.setIsDelete(1);
		return updateEmployeeManage(employeeManage);
	}

	public Integer queryCurRegisterClient(Long emId) {
		return employeeManageMapper.queryCurRegisterClient(emId);
	}

	public Integer queryRegisterClient(Long emId) {
		return employeeManageMapper.queryRegisterClient(emId);
	}
}

