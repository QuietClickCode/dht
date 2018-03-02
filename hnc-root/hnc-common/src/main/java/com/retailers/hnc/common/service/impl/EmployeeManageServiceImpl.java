
package com.retailers.hnc.common.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.dao.OrgUserMapper;
import com.retailers.auth.dao.SysUserMapper;
import com.retailers.auth.entity.OrgUser;
import com.retailers.auth.entity.SysUser;
import com.retailers.hnc.common.dao.EmployeeManageCopyMapper;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.dao.EmployeeManageMapper;
import com.retailers.hnc.common.entity.EmployeeManageCopy;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.vo.EmployeeManageVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：员工管理Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 12:01:09
 */
@Service("employeemanageService")
public class EmployeeManageServiceImpl implements EmployeeManageService {
	@Autowired
	private EmployeeManageMapper employeeManageMapper;
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private OrgUserMapper orgUserMapper;
	@Autowired
	private EmployeeManageCopyMapper employeeManageCopyMapper;
	public boolean saveEmployeeManage(EmployeeManage employeeManage,Long uploadperson) {
		SysUser sysUser = new SysUser();
		String idcard = employeeManage.getEmIdCard();
		String password = idcard.substring(idcard.length()-6);
		sysUser.setUaccount(employeeManage.getWxPhone());
		sysUser.setUpassword(password);
		sysUser.setIsDelete(0L);
		sysUserMapper.saveSysUser(sysUser);
		Long uid = sysUser.getUid();
		employeeManage.setEmId(uid);
		Integer type = employeeManage.getEmType();
		if(ObjectUtils.isNotEmpty(type)){
			OrgUser orgUser = new OrgUser();
			orgUser.setOuOrgId(Long.parseLong(""+type));
			orgUser.setOuSid(uid);
			orgUserMapper.saveOrgUser(orgUser);
		}
		int status = employeeManageMapper.saveEmployeeManage(employeeManage);
		if(status==1){
			copyEmp(employeeManage,uploadperson,0L);
		}
		return status == 1 ? true : false;
	}
	public boolean updateEmployeeManage(EmployeeManage employeeManage,Long uploadperson) {
		EmployeeManage manage = queryEmployeeManageByEmId(employeeManage.getEmId());
		employeeManage.setVersion(manage.getVersion());
		System.out.println(JSON.toJSONString(employeeManage));

		if(ObjectUtils.isNotEmpty(employeeManage.getEmType())){
			Pagination<OrgUser> page = new Pagination<OrgUser>();
			page.setPageNo(1);
			page.setPageSize(1);
			Map params = new HashMap();
			params.put("ouSid",manage.getEmId());
			page.setParams(params);
			List<OrgUser> orgUserList = orgUserMapper.queryOrgUserList(page);
			if(ObjectUtils.isNotEmpty(orgUserList)){
				OrgUser orgUser = orgUserList.get(0);
				orgUser.setOuOrgId(Long.parseLong(""+employeeManage.getEmType()));
				orgUserMapper.updateOrgUser(orgUser);
			}
		}

		int status = employeeManageMapper.updateEmployeeManage(employeeManage);
		if(status==1){
			if(ObjectUtils.isNotEmpty(employeeManage.getEmType())){
				Long emTeam = manage.getEmTeam();
				employeeManage.setEmTeam(emTeam);
			}else{
				Long emTeam = employeeManage.getEmTeam();
				manage.setEmTeam(emTeam);
				employeeManage = manage;
			}
			copyEmp(employeeManage,uploadperson,1l);
		}
		return status == 1 ? true : false;
	}
	public EmployeeManage queryEmployeeManageByEmId(Long emId) {
		return employeeManageMapper.queryEmployeeManageByEmId(emId);
	}

	public Pagination<EmployeeManageVo> queryEmployeeManageList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<EmployeeManageVo> page = new Pagination<EmployeeManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<EmployeeManageVo> list = employeeManageMapper.queryEmployeeManageList(page);
		for(EmployeeManageVo manageVo:list){
			manageVo.setCurRegisterClientCount(queryCurRegisterClient(manageVo.getEmId()));
			manageVo.setRegisterClientCount(queryRegisterClient(manageVo.getEmId()));
		}
		page.setData(list);
		return page;
	}
	public Pagination<EmployeeManageVo> queryFirstEmployeeManageList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<EmployeeManageVo> page = new Pagination<EmployeeManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<EmployeeManageVo> list = employeeManageMapper.queryEmployeeManageList(page);
		page.setData(list);
		return page;
	}
	public Pagination<EmployeeManageVo> queryEmployeeManageByNotInPhone(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<EmployeeManageVo> page = new Pagination<EmployeeManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<EmployeeManageVo> list = employeeManageMapper.queryEmployeeManageByNotInPhone(page);
		page.setData(list);
		return page;
	}
	public boolean deleteEmployeeManageByEmId(Long emId,Long uploadperson) {
		EmployeeManage employeeManage = queryEmployeeManageByEmId(emId);
		employeeManage.setIsDelete(1);
		SysUser sysUser = sysUserMapper.querySyUserByAccount(employeeManage.getEmPhone());
		sysUserMapper.deleteSysUserByUid(sysUser.getUid());
		Pagination<OrgUser> page = new Pagination<OrgUser>();
		page.setPageNo(1);
		page.setPageSize(1);
		Map params = new HashMap();
		params.put("ouSid",employeeManage.getEmId());
		page.setParams(params);
		OrgUser orgUser = orgUserMapper.queryOrgUserList(page).get(0);
		orgUserMapper.deleteOrgUserByOuId(orgUser.getOuId());

		int status = employeeManageMapper.updateEmployeeManage(employeeManage);
		if(status==1){
			copyEmp(employeeManage,uploadperson,2L);
		}
		return status==1?true:false;
	}

	public Integer queryCurRegisterClient(Long emId) {
		return employeeManageMapper.queryCurRegisterClient(emId);
	}

	public Integer queryRegisterClient(Long emId) {
		return employeeManageMapper.queryRegisterClient(emId);
	}

	public List<EmployeeManage> queryAllEmployeeByTeam(Long teamId) {
		return employeeManageMapper.queryAllEmployeeByTeam(teamId);
	}

	public void copyEmp(EmployeeManage employeeManage,Long uploadperson,Long type){
		EmployeeManageCopy employeeManageCopy = new EmployeeManageCopy();
		BeanUtils.copyProperties(employeeManage,employeeManageCopy);
		employeeManageCopy.setCreateTime(new Date());
		employeeManageCopy.setUploadperson(uploadperson);
		employeeManageCopy.setUploadtype(type);
		employeeManageCopyMapper.saveEmployeeManageCopy(employeeManageCopy);
	}
}

