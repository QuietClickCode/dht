
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.UserDetail;
import java.util.Map;
/**
 * 描述：用户信息详情表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 17:24:33
 */
public interface UserDetailService {

	/**
	 * 添加用户信息详情表
	 * @param userDetail
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public boolean saveUserDetail(UserDetail userDetail);
	/**
	 * 编辑用户信息详情表
	 * @param userDetail
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateUserDetail(UserDetail userDetail);
	/**
	 * 根据id查询用户信息详情表
	 * @param udId
	 * @return userDetail
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public UserDetail queryUserDetailByUdId(Long udId);
	/**
	 * 查询用户信息详情表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public Pagination<UserDetail> queryUserDetailList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据udId删除用户信息详情表
	 * @param udId
	 * @return
	 * @author fanghui
	 * @date 2018-02-07 17:24:33
	 */
	public boolean deleteUserDetailByUdId(Long udId);

}


