
package com.retailers.auth.service.impl;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.dao.OrgMenuPermissionMapper;
import com.retailers.auth.dao.OrganizationMapper;
import com.retailers.auth.entity.OrgMenuPermission;
import com.retailers.auth.entity.Organization;
import com.retailers.auth.service.OrganizationService;
import com.retailers.auth.service.SysUserResPermissionService;
import com.retailers.auth.vo.OrganizationVo;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：组织结构Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-19 14:32:28
 */
@Service("organizationService")
public class OrganizationServiceImpl implements OrganizationService {
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private OrgMenuPermissionMapper orgMenuPermissionMapper;
	@Autowired
	private SysUserResPermissionService sysUserResPermissionService;

	public boolean saveOrganization(Organization organization) {
		int status = organizationMapper.saveOrganization(organization);
		return status == 1 ? true : false;
	}
	public boolean updateOrganization(Organization organization) {
		int status = organizationMapper.updateOrganization(organization);
		return status == 1 ? true : false;
	}
	public Organization queryOrganizationByOrgId(Long orgId) {
		return organizationMapper.queryOrganizationByOrgId(orgId);
	}

	public Pagination<Organization> queryOrganizationList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Organization> page = new Pagination<Organization>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Organization> list = organizationMapper.queryOrganizationList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOrganizationByOrgId(Long orgId) {
		int status = organizationMapper.deleteOrganizationByOrgId(orgId);
		return status == 1 ? true : false;
	}

	public List<OrganizationVo> queryOrganizationLists(Long orgId) {
		List<Organization> list =organizationMapper.queryOrganizationTree(orgId);
		List<OrganizationVo> rtnList=new ArrayList<OrganizationVo>();
		Map<Long,Map<Long,Integer>> child=new HashMap<Long, Map<Long, Integer>>();
		queryChildNode(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllowOrg(null,child,alloShow);
		for(Organization org:list){
			OrganizationVo orgVo=new OrganizationVo();
			BeanUtils.copyProperties(org,orgVo);
			if(ObjectUtils.isEmpty(org.getOrgPid())){
				orgVo.setLevel(1l);
				rtnList.add(orgVo);
			}else{
				orgVo.setLevel(2l);
				if(alloShow.containsKey(org.getOrgPid())){
					rtnList.add(orgVo);
				}
			}
		}
		return rtnList;
	}

	/**
	 * 取得节点下的所有子节点
	 * @param list 树型菜单
	 * @param child
	 */
	private void queryChildNode(List<Organization> list,Map<Long,Map<Long,Integer>> child){
		for(Organization vo:list){
			Long parentId=vo.getOrgPid();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getOrgId(),vo.getIsDelete());
			}else{
				Map<Long,Integer> maps=new HashMap<Long, Integer>();
				maps.put(vo.getOrgId(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}

	/**
	 * 取得允许展示节点，排除无节点的数据
	 * @param parentId
	 * @param map
	 * @param allowMap
	 */
	private void queryAllowOrg(Long parentId,Map<Long,Map<Long,Integer>> map,Map<Long,Long>allowMap){
		if(ObjectUtils.isEmpty(parentId)){
			parentId=-1l;
		}
		Map<Long,Integer> child=map.get(parentId);
		if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
			for(Long key:child.keySet()){
				if(child.get(key)== SystemConstant.SYS_IS_VALID_YES){
					allowMap.put(key,key);
					if(map.containsKey(key)){
						queryAllowOrg(key,map,allowMap);
					}
				}
			}
		}
	}

	@Transactional(rollbackFor =  Exception.class)
	public boolean editOrg(Organization org) {
		//判断是新增还是修改
		if(ObjectUtils.isNotEmpty(org.getOrgId())){
			Organization orgVo=organizationMapper.queryOrganizationByOrgId(org.getOrgId());
			//判断是否变更过上级结点
			if(!ObjectUtils.compare(org.getOrgPid(),orgVo.getOrgPid())){
				//取得数据
			}
			BeanUtils.copyProperties(org,orgVo,new String[]{"version"});
			int total =organizationMapper.updateOrganization(org);
			if(total==0){
				return false;
			}
		}else{
			//设置当前显示顺序
			if(ObjectUtils.isEmpty(org.getOrgSort())){
				int sort=organizationMapper.getCurOrgSort(org.getOrgPid());
				org.setOrgSort(sort);
			}
			org.setIsDelete(SystemConstant.SYS_IS_DELETE_NO);
			int total =organizationMapper.saveOrganization(org);
			if(total==0){
				return false;
			}
		}
		return true;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean removeOrg(Long orgId) {
		int total = organizationMapper.removeOrg(orgId);
		if(total==0){
			return false;
		}
		return true;
	}

	/**
	 * 取得部门所拥有的权限
	 * @param orgId 部门id
	 * @return
	 */
	public List<ZTreeVo> reqOrgPermission(Long orgId) {
		List<ZTreeVo> list=organizationMapper.reqRsOrgPermission(orgId);
		List<ZTreeVo> rtn = new ArrayList<ZTreeVo>();
		Map<Long,Map<Long,Integer>> child=new HashMap<Long, Map<Long, Integer>>();
		queryChildTreeNode(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllowOrg(null,child,alloShow);
		for(ZTreeVo vo:list){
			ZTreeVo ztreeVo=new ZTreeVo();
			BeanUtils.copyProperties(vo,ztreeVo);
			if(ObjectUtils.isEmpty(vo.getpId())){
				rtn.add(ztreeVo);
			}else{
				if(alloShow.containsKey(vo.getpId())){
					rtn.add(ztreeVo);
				}
			}
		}
		return rtn;
	}

	/**
	 * 取得部门层级数据
	 * @param selectOrgIds
	 * @return
	 */
	public List<ZTreeVo> reqOrgTree(String selectOrgIds) {
		List<Long> orgIds=new ArrayList<Long>();
		if(ObjectUtils.isNotEmpty(selectOrgIds)){
			String[] orgIds_=selectOrgIds.split(",");
			for(String orgId:orgIds_){
				orgIds.add(Long.parseLong(orgId));
			}
		}else{
			orgIds.add(-1l);
		}
		List<ZTreeVo> list=organizationMapper.reqOrgTree(orgIds);
		List<ZTreeVo> rtn = new ArrayList<ZTreeVo>();
		Map<Long,Map<Long,Integer>> child=new HashMap<Long, Map<Long, Integer>>();
		queryChildTreeNode(list,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllowOrg(null,child,alloShow);
		for(ZTreeVo vo:list){
			ZTreeVo ztreeVo=new ZTreeVo();
			BeanUtils.copyProperties(vo,ztreeVo);
			if(ObjectUtils.isEmpty(vo.getpId())){
				rtn.add(ztreeVo);
			}else{
				if(alloShow.containsKey(vo.getpId())){
					rtn.add(ztreeVo);
				}
			}
		}
		return rtn;
	}

	/**
	 * 取得节点下的所有子节点
	 * @param list 树型菜单
	 * @param child
	 */
	private void queryChildTreeNode(List<ZTreeVo> list,Map<Long,Map<Long,Integer>> child){
		for(ZTreeVo vo:list){
			Long parentId=vo.getpId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getId(),SystemConstant.SYS_IS_DELETE_NO);
			}else{
				Map<Long,Integer> maps=new HashMap<Long, Integer>();
				maps.put(vo.getId(),SystemConstant.SYS_IS_DELETE_NO);
				child.put(parentId,maps);
			}
		}
	}

	/**
	 * 编辑部门id
	 * @param orgId 部门id
	 * @param resIds 菜单id
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean editorOrgPermission(Long orgId, String resIds) {
		//删除该部门下的所有权限
		orgMenuPermissionMapper.deleteOrgMenuPermissionByOrgId(orgId);
		if(ObjectUtils.isNotEmpty(resIds)){
			String[] resIds_=resIds.split(",");
			List<OrgMenuPermission> list = new ArrayList<OrgMenuPermission>();
			for(String redId:resIds_){
				OrgMenuPermission org=new OrgMenuPermission();
				org.setOrgId(orgId);
				org.setRsId(Long.parseLong(redId));
				list.add(org);
			}
			orgMenuPermissionMapper.saveOrgMenuPermissions(list);
		}
		//取得所有部门权限
		//重新加载当前登陆用户权限
		sysUserResPermissionService.loadUserResPermission();
		return false;
	}
}

