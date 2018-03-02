package com.retailers.sbj.common.dao;

import com.retailers.sbj.common.entity.EmployeeManage;
import com.retailers.sbj.common.vo.EmployeeManageVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;

/**
 * 描述：置业顾问DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 16:19:58
 */
public interface EmployeeManageMapper {

	/**
	 * 添加置业顾问
	 * @param employeeManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 16:19:58
	 */
	public int saveEmployeeManage(EmployeeManage employeeManage);
	/**
	 * 编辑置业顾问
	 * @param employeeManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 16:19:58
	 */
	public int updateEmployeeManage(EmployeeManage employeeManage);
	/**
	 * 根据EmId删除置业顾问
	 * @param emId
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 16:19:58
	 */
	public int deleteEmployeeManageByEmId(Long emId);
	/**
	 * 根据EmId查询置业顾问
	 * @param emId
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 16:19:58
	 */
	public EmployeeManage queryEmployeeManageByEmId(Long emId);
	/**
	 * 查询置业顾问列表
	 * @param pagination 分页对象
	 * @return  置业顾问列表
	 * @author wangjue
	 * @date 2017-12-28 16:19:58
	 */
	public List<EmployeeManageVo> queryEmployeeManageList(Pagination<EmployeeManageVo> pagination);

	public List<EmployeeManageVo> queryFrstEmployeeManageList(Pagination<EmployeeManageVo> pagination);
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

	public List<EmployeeManage> queryAllEmployee();

	public List<EmployeeManageVo> queryEmployeeManageByNotInPhone(Pagination<EmployeeManageVo> pagination);


	public List<EmployeeManage> queryAllEmployeeByTeam(Long teamId);

}
