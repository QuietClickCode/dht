package com.retailers.auth.dao;
import com.retailers.auth.entity.OrgMenuPermission;

import java.util.List;
/**
 * 描述：菜单部门权限DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-21 17:09:47
 */
public interface OrgMenuPermissionMapper {

	/**
	 * 添加菜单部门权限
	 * @param orgMenuPermissions
	 * @return
	 * @author zhongp
	 * @date 2017-09-21 17:09:47
	 */
	public int saveOrgMenuPermissions(List<OrgMenuPermission> orgMenuPermissions);

	/**
	 * 根据OmId删除菜单部门权限
	 * @param omId
	 * @return
	 * @author zhongp
	 * @date 2017-09-21 17:09:47
	 */
	public int deleteOrgMenuPermissionByOmId(Long omId);


	/**
	 * 根据部门id 删除所有菜单权限
	 * @param orgId
	 * @return
	 * @author zhongp
	 * @date 2017-09-21 17:09:47
	 */
	public int deleteOrgMenuPermissionByOrgId(Long orgId);

}
