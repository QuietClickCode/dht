package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.UserDetail;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：用户信息详情表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 17:24:33
 */
public interface UserDetailMapper {

	/**
	 * 添加用户信息详情表
	 * @param userDetail
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public int saveUserDetail(UserDetail userDetail);
	/**
	 * 编辑用户信息详情表
	 * @param userDetail
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public int updateUserDetail(UserDetail userDetail);
	/**
	 * 根据UdId删除用户信息详情表
	 * @param udId
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public int deleteUserDetailByUdId(Long udId);
	/**
	 * 根据UdId查询用户信息详情表
	 * @param udId
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public UserDetail queryUserDetailByUdId(Long udId);
	/**
	 * 查询用户信息详情表列表
	 * @param pagination 分页对象
	 * @return  用户信息详情表列表
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public List<UserDetail> queryUserDetailList(Pagination<UserDetail> pagination);

}
