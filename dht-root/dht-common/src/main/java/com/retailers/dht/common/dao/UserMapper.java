package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.User;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：平台会员DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 00:47:29
 */
public interface UserMapper {

	/**
	 * 添加平台会员
	 * @param user
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public int saveUser(User user);
	/**
	 * 编辑平台会员
	 * @param user
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public int updateUser(User user);
	/**
	 * 根据Uid删除平台会员
	 * @param uid
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public int deleteUserByUid(Long uid);
	/**
	 * 根据Uid查询平台会员
	 * @param uid
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public User queryUserByUid(Long uid);
	/**
	 * 查询平台会员列表
	 * @param pagination 分页对象
	 * @return  平台会员列表
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public List<User> queryUserList(Pagination<User> pagination);

	/**
	 * 校验手机号是否存在
	 * @param phone
	 * @return
	 */
	public User checkPhone(@Param("phone")String phone);

}
