
package com.retailers.auth.service.impl;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.dao.OrgUserMapper;
import com.retailers.auth.dao.SysUserMapper;
import com.retailers.auth.entity.OrgUser;
import com.retailers.auth.entity.SysUser;
import com.retailers.auth.service.SysUserService;
import com.retailers.auth.vo.SysUserVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.Md5Encrypt;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
/**
 * 描述：公司员工表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-22 17:51:06
 */
@Service("sysuserService")
public class SysUserServiceImpl implements SysUserService {
	Logger logger = LoggerFactory.getLogger(SysUserServiceImpl.class);
	@Autowired
	private SysUserMapper sysUserMapper;
	@Autowired
	private OrgUserMapper orgUserMapper;

	public boolean saveSysUser(SysUser sysUser) {
		int status = sysUserMapper.saveSysUser(sysUser);
		return status == 1 ? true : false;
	}
	public boolean updateSysUser(SysUser sysUser) {
		int status = sysUserMapper.updateSysUser(sysUser);
		return status == 1 ? true : false;
	}
	public SysUser querySysUserByUid(Long uid) {
		return sysUserMapper.querySysUserByUid(uid);
	}

	public Pagination<SysUserVo> querySysUserList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<SysUserVo> page = new Pagination<SysUserVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SysUserVo> list = sysUserMapper.querySysUserLists(page);
		page.setData(list);
		return page;
	}
	public boolean deleteSysUserByUid(Long uid) {
		int status = sysUserMapper.deleteSysUserByUid(uid);
		return status == 1 ? true : false;
	}

	/**
	 * 添加职工
	 * @param sysUserVo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean addSysUser(SysUserVo sysUserVo) {
		Date curDate = new Date();
		logger.info("当前时间：{}",DateUtil.dateToString(curDate,DateUtil.DATE_WITHMILLISECOND_FORMAT));
		curDate=DateUtil.stringToDate(DateUtil.dateToString(curDate,DateUtil.DATE_LONG_SIMPLE_FORMAT),DateUtil.DATE_LONG_SIMPLE_FORMAT);
		logger.info("当前时间：{}",DateUtil.dateToString(curDate,DateUtil.DATE_WITHMILLISECOND_FORMAT));
		//添加默认密码
		sysUserVo.setUcreateTime(curDate);
		logger.info("添加用户生成密码码为：{}",StringUtils.formate(SystemConstant.SYS_USER_DEFAULT_PASSWORD, DateUtil.dateToString(curDate, DateUtil.DATE_LONG_SIMPLE_FORMAT)));
		//初始化用户密码
		sysUserVo.setUpassword(Md5Encrypt.md5(StringUtils.formate(SystemConstant.SYS_USER_DEFAULT_PASSWORD, DateUtil.dateToString(curDate, DateUtil.DATE_LONG_SIMPLE_FORMAT))));
		logger.info("生成密码为：{}",sysUserVo.getUpassword());
		sysUserVo.setIsDelete((long)SystemConstant.SYS_IS_DELETE_NO);
		sysUserVo.setIsValid((long)SystemConstant.SYS_IS_VALID_YES);
		//添加职工
		sysUserMapper.saveSysUser(sysUserVo);
		saveUserOrg(sysUserVo.getOrgIds(),sysUserVo.getUid());
		return true;
	}

	/**
	 * 编辑职工信息
	 * @param sysUserVo
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean editorSysUser(SysUserVo sysUserVo)throws AppException {
		SysUser sysUser = sysUserMapper.querySysUserByUid(sysUserVo.getUid());
		if(ObjectUtils.isEmpty(sysUser)){
			throw new AppException("编辑员工["+sysUserVo.getUname()+"]不存在");
		}
		BeanUtils.copyProperties(sysUserVo,sysUser,new String[]{"uid","uaccount","upassword","ucreateTime"});
		int total = sysUserMapper.updateSysUser(sysUser);
		if(total==0){
			throw new AppException("编辑员工失败");
		}
		//清除用户所在部门
		orgUserMapper.clearUserOrg(sysUser.getUid());
		//添加用户所在部门
		saveUserOrg(sysUserVo.getOrgIds(),sysUserVo.getUid());
		return true;
	}

	/**
	 * 删除职工
	 * @param uid
	 * @return
	 */
	@Transactional(rollbackFor = Exception.class)
	public boolean delSysUser(Long uid) {
		int status = sysUserMapper.delSysUser(uid);
		return status == 1 ? true : false;
	}

	private void saveUserOrg(String orgIds,Long userId){
		if(ObjectUtils.isNotEmpty(orgIds)){
			String[] orgIds_=orgIds.split(",");
			List<OrgUser> lists = new ArrayList<OrgUser>();
			for(String orgId:orgIds_){
				OrgUser orgUser = new OrgUser();
				orgUser.setOuOrgId(Long.parseLong(orgId));
				orgUser.setOuSid(userId);
				lists.add(orgUser);
			}
			orgUserMapper.saveOrgUsers(lists);
		}
	}

	/**
	 * 根据账号查找员工
	 * @param account
	 * @return
	 */
	public SysUser querySyUserByAccount(String account,String sysUserPwd) throws AppException {
		SysUser sysUser = sysUserMapper.querySyUserByAccount(account);
		if(sysUser == null){
			throw new AppException("请输入正确的账号");
		}
		logger.info("登陆用户密码为：{}",StringUtils.formate(SystemConstant.SYS_USER_DEFAULT_PASSWORD, DateUtil.dateToString(sysUser.getUcreateTime(), DateUtil.DATE_LONG_SIMPLE_FORMAT)));
		String pwd = Md5Encrypt.md5(StringUtils.formate(sysUserPwd, DateUtil.dateToString(sysUser.getUcreateTime(), DateUtil.DATE_LONG_SIMPLE_FORMAT)));
		logger.info("密码：{}",pwd);
		if(!pwd.equals(sysUser.getUpassword())) {
			throw new AppException("密码不正确");
		}
		return sysUser;
	}

	public boolean editSysUserPassword(String account, String sysUserPwd, String newPwd) throws AppException {
		try {
			SysUser sysUser = querySyUserByAccount(account, sysUserPwd);
			String pwd = Md5Encrypt.md5(StringUtils.formate(newPwd, DateUtil.dateToString(sysUser.getUcreateTime(), DateUtil.DATE_LONG_SIMPLE_FORMAT)));
			sysUser.setUpassword(pwd);
			return updateSysUser(sysUser);
		} catch (AppException e) {
			throw new AppException("账户或者密码不正确");
		}
	}
}

