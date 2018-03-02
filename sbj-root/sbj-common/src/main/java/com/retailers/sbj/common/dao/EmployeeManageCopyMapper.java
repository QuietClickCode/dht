package com.retailers.sbj.common.dao;

import com.retailers.sbj.common.entity.EmployeeManageCopy;
import com.retailers.sbj.common.vo.EmployeeManageCopyVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;

/**
 * 描述：员工副本表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-22 12:01:36
 */
public interface EmployeeManageCopyMapper {

	/**
	 * 添加员工副本表
	 * @param employeeManageCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public int saveEmployeeManageCopy(EmployeeManageCopy employeeManageCopy);
	/**
	 * 编辑员工副本表
	 * @param employeeManageCopy
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public int updateEmployeeManageCopy(EmployeeManageCopy employeeManageCopy);
	/**
	 * 根据EmcId删除员工副本表
	 * @param emcId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public int deleteEmployeeManageCopyByEmcId(Long emcId);
	/**
	 * 根据EmcId查询员工副本表
	 * @param emcId
	 * @return
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public EmployeeManageCopy queryEmployeeManageCopyByEmcId(Long emcId);
	/**
	 * 查询员工副本表列表
	 * @param pagination 分页对象
	 * @return  员工副本表列表
	 * @author fanghui
	 * @date 2018-01-22 12:01:36
	 */
	public List<EmployeeManageCopyVo> queryEmployeeManageCopyList(Pagination<EmployeeManageCopyVo> pagination);

}
