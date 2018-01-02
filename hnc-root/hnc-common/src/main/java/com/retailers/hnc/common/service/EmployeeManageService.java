
package com.retailers.hnc.common.service;

import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.vo.EmployeeManageVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.Map;
/**
 * 描述：员工管理Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 12:01:09
 */
public interface EmployeeManageService {

	/**
	 * 添加员工管理
	 * @param employeeManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public boolean saveEmployeeManage(EmployeeManage employeeManage);
	/**
	 * 编辑员工管理
	 * @param employeeManage
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateEmployeeManage(EmployeeManage employeeManage);
	/**
	 * 根据id查询员工管理
	 * @param emId
	 * @return employeeManage
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public EmployeeManage queryEmployeeManageByEmId(Long emId);
	/**
	 * 查询员工管理列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public Pagination<EmployeeManageVo> queryEmployeeManageList(Map<String, Object> params, int pageNo, int pageSize);

	public Pagination<EmployeeManageVo> queryFirstEmployeeManageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据emId删除员工管理
	 * @param emId
	 * @return
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public boolean deleteEmployeeManageByEmId(Long emId);

	/**
	 * 查询当日登记的客户
	 * @return  返回客户集合
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public Integer queryCurRegisterClient(Long emId);

	/**
	 * 查询所有与该置业顾问绑定的客户
	 * @return  返回客户集合
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public Integer queryRegisterClient(Long emId);

}


