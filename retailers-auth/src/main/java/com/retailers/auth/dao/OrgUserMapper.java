package com.retailers.auth.dao;


import com.retailers.auth.entity.OrgUser;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：部门人员表DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-09-22 17:54:20
 */
public interface OrgUserMapper {

	/**
	 * 添加部门人员表
	 * @param orgUser
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:54:20
	 */
	public int saveOrgUser(OrgUser orgUser);

	/**
	 * 批量添加职工所在部门
	 * @param orgUsers
	 * @return
	 */
	public int saveOrgUsers(List<OrgUser> orgUsers);

	/**
	 * 清除用户所在部门
	 * @param uId 用户id
	 * @return
	 */
	public int clearUserOrg(@Param("uId") Long uId);
	/**
	 * 编辑部门人员表
	 * @param orgUser
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:54:20
	 */
	public int updateOrgUser(OrgUser orgUser);
	/**
	 * 根据OuId删除部门人员表
	 * @param ouId
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:54:20
	 */
	public int deleteOrgUserByOuId(Long ouId);
	/**
	 * 根据OuId查询部门人员表
	 * @param ouId
	 * @return
	 * @author zhongp
	 * @date 2017-09-22 17:54:20
	 */
	public OrgUser queryOrgUserByOuId(Long ouId);
	/**
	 * 查询部门人员表列表
	 * @param pagination 分页对象
	 * @return  部门人员表列表
	 * @author zhongp
	 * @date 2017-09-22 17:54:20
	 */
	public List<OrgUser> queryOrgUserList(Pagination<OrgUser> pagination);

}
