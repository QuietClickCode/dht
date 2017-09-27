
package com.retailers.auth.service;

import com.retailers.auth.entity.SysUser;
import com.retailers.auth.vo.SysUserVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;

import java.util.Map;
/**
 * 描述：公司员工表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-22 17:51:06
 */
public interface SysUserService {

	/**
	 * 添加公司员工表
	 * @param sysUser
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public boolean saveSysUser(SysUser sysUser);
	/**
	 * 编辑公司员工表
	 * @param sysUser
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateSysUser(SysUser sysUser);
	/**
	 * 根据id查询公司员工表
	 * @param uid
	 * @return sysUser
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public SysUser querySysUserByUid(Long uid);
	/**
	 * 查询公司员工表列表
	 * @param params 请求参数
	 * @param pageNo 起始页
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public Pagination<SysUserVo> querySysUserList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据uid删除公司员工表
	 * @param uid
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public boolean deleteSysUserByUid(Long uid);

	/**
	 * 添加职工
	 * @param sysUserVo
	 * @return
	 */
	public boolean addSysUser(SysUserVo sysUserVo);

	/**
	 * 编辑职工信息
	 * @param sysUserVo
	 * @return
	 */
	public boolean editorSysUser(SysUserVo sysUserVo)throws AppException;


	/**
	 * 删除后台职工
	 * @param uid
	 * @return
	 */
	public boolean delSysUser(Long uid);

}


