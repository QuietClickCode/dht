
package com.retailers.auth.service;

import com.retailers.auth.entity.Organization;
import com.retailers.auth.vo.OrganizationVo;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;
/**
 * 描述：组织结构Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-19 14:32:28
 */
public interface OrganizationService {

	/**
	 * 添加组织结构
	 * @param organization
	 * @return
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public boolean saveOrganization(Organization organization);
	/**
	 * 编辑组织结构
	 * @param organization
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateOrganization(Organization organization);
	/**
	 * 根据id查询组织结构
	 * @param orgId
	 * @return organization
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public Organization queryOrganizationByOrgId(Long orgId);
	/**
	 * 查询组织结构列表
	 * @param params 请求参数
	 * @param pageNo 起始页
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public Pagination<Organization> queryOrganizationList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据orgId删除组织结构
	 * @param orgId
	 * @return
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public boolean deleteOrganizationByOrgId(Long orgId);

	/**
	 * 取得部门列表(树型展示)
	 * @param orgId 部门id
	 * @return
	 */
	public List<OrganizationVo> queryOrganizationLists(Long orgId);

	/**
	 * 编辑组织结构
	 * @param org
	 * @return
	 */
	public boolean editOrg(Organization org);

	/**
	 * 删除指定部门
	 * @param orgId
	 * @return
	 */
	public boolean removeOrg(Long orgId);

	/**
	 * 取得部门所拥有的权限
	 * @param orgId 部门id
	 * @return
	 */
	public List<ZTreeVo> reqOrgPermission(Long orgId);

	/**
	 * 取得部门层级数据
	 * @param selectOrgIds
	 * @return
	 */
	public List<ZTreeVo> reqOrgTree(String selectOrgIds);

	/**
	 * 编辑部门id
	 * @param orgId 部门id
	 * @param resIds 菜单id
	 * @return
	 */
	public boolean editorOrgPermission(Long orgId, String resIds);
}


