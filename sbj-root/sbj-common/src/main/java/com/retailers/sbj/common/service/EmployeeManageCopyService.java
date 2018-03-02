
package com.retailers.sbj.common.service;

import com.retailers.sbj.common.entity.EmployeeManageCopy;
import com.retailers.sbj.common.vo.EmployeeManageCopyVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.Map;

/**
 * 描述：员工副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:01:36
 */
public interface EmployeeManageCopyService {

	/**
	 * 添加员工副本表
	 * @param employeeManageCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public boolean saveEmployeeManageCopy(EmployeeManageCopy employeeManageCopy);
	/**
	 * 编辑员工副本表
	 * @param employeeManageCopy
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateEmployeeManageCopy(EmployeeManageCopy employeeManageCopy);
	/**
	 * 根据id查询员工副本表
	 * @param emcId
	 * @return employeeManageCopy
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public EmployeeManageCopy queryEmployeeManageCopyByEmcId(Long emcId);
	/**
	 * 查询员工副本表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public Pagination<EmployeeManageCopyVo> queryEmployeeManageCopyList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据emcId删除员工副本表
	 * @param emcId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public boolean deleteEmployeeManageCopyByEmcId(Long emcId);

}


