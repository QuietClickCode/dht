package com.retailers.auth.dao;

import com.retailers.auth.entity.SysUser;
import com.retailers.auth.vo.SysUserVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
/**
 * 描述：公司员工表DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-22 17:51:06
 */
public interface SysUserMapper {

	/**
	 * 添加公司员工表
	 * @param sysUser
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public int saveSysUser(SysUser sysUser);
	/**
	 * 编辑公司员工表
	 * @param sysUser
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public int updateSysUser(SysUser sysUser);
	/**
	 * 根据Uid删除公司员工表
	 * @param uid
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public int deleteSysUserByUid(Long uid);
	/**
	 * 根据Uid查询公司员工表
	 * @param uid
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public SysUser querySysUserByUid(Long uid);
	/**
	 * 查询公司员工表列表
	 * @param pagination 分页对象
	 * @return  公司员工表列表
	 * @author zhongp
	 * @date 2017-09-22 17:51:06
	 */
	public List<SysUser> querySysUserList(Pagination<SysUser> pagination);

	/**
	 * 取得后台职员列表
	 * @param pagination
	 * @return
	 */
	public List<SysUserVo> querySysUserLists(Pagination<SysUserVo> pagination);

	/**
	 * 移聊员工
	 * @param uid
	 * @return
	 */
	public int delSysUser(Long uid);

	/**
	 * 根据账号查找员工
	 * @param account
	 * @return
	 */
	public SysUser querySyUserByAccount(String account);


}
