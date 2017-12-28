package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.vo.EmployeeManageVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
/**
 * 描述：员工管理DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 12:01:09
 */
public interface EmployeeManageMapper {

	/**
	 * 添加员工管理
	 * @param employeeManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public int saveEmployeeManage(EmployeeManage employeeManage);
	/**
	 * 编辑员工管理
	 * @param employeeManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public int updateEmployeeManage(EmployeeManage employeeManage);
	/**
	 * 根据EmId删除员工管理
	 * @param emId
	 * @return
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public int deleteEmployeeManageByEmId(Long emId);
	/**
	 * 根据EmId查询员工管理
	 * @param emId
	 * @return
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public EmployeeManage queryEmployeeManageByEmId(Long emId);
	/**
	 * 查询员工管理列表
	 * @param pagination 分页对象
	 * @return  员工管理列表
	 * @author wangjue
	 * @date 2017-12-25 12:01:09
	 */
	public List<EmployeeManageVo> queryEmployeeManageList(Pagination<EmployeeManageVo> pagination);

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
