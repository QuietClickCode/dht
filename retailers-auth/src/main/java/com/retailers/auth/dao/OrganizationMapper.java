package com.retailers.auth.dao;
import com.retailers.auth.entity.Organization;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：组织结构DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-19 14:32:28
 */
public interface OrganizationMapper {

	/**
	 * 添加组织结构
	 * @param organization
	 * @return
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public int saveOrganization(Organization organization);
	/**
	 * 编辑组织结构
	 * @param organization
	 * @return
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public int updateOrganization(Organization organization);
	/**
	 * 根据OrgId删除组织结构
	 * @param orgId
	 * @return
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public int deleteOrganizationByOrgId(Long orgId);
	/**
	 * 根据OrgId查询组织结构
	 * @param orgId
	 * @return
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public Organization queryOrganizationByOrgId(Long orgId);
	/**
	 * 查询组织结构列表
	 * @param pagination 分页对象
	 * @return  组织结构列表
	 * @author zhongp
	 * @date 2017-09-19 14:32:28
	 */
	public List<Organization> queryOrganizationList(Pagination<Organization> pagination);

	/**
	 * 取得组织机构结构（树型菜展示)
	 * @return
	 */
	public List<Organization> queryOrganizationTree(@Param("orgId") Long orgId);

	/**
	 * 取得父级节点最大值
	 * @param orgPid
	 * @return
	 */
	public Integer getCurOrgSort(@Param("orgPid") Long orgPid);

	/**
	 * 删除部门
	 * @param orgId
	 * @return
	 */
	public Integer removeOrg(@Param("orgId") Long orgId);

	/**
	 * 取得部门权限
	 * @param orgId 部门id
	 * @return
	 */
	public List<ZTreeVo> reqRsOrgPermission(Long orgId);

	/**
	 * 取得所有部门
	 * @param orgIds
	 * @return
	 */
	public List<ZTreeVo> reqOrgTree(@Param("orgIds") List<Long> orgIds);

}
