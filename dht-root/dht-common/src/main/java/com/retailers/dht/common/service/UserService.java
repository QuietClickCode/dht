
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.User;
import com.retailers.tools.exception.AppException;

import java.util.Map;
/**
 * 描述：平台会员Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-23 00:47:29
 */
public interface UserService {

	/**
	 * 添加平台会员
	 * @param user
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public boolean saveUser(User user);
	/**
	 * 编辑平台会员
	 * @param user
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateUser(User user);
	/**
	 * 根据id查询平台会员
	 * @param uid
	 * @return user
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public User queryUserByUid(Long uid);
	/**
	 * 查询平台会员列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public Pagination<User> queryUserList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据uid删除平台会员
	 * @param uid
	 * @return
	 * @author zhongp
	 * @date 2017-11-23 00:47:29
	 */
	public boolean deleteUserByUid(Long uid);

	/**
	 * 修改用户昵称
	 * @param uid
	 * @param name 昵称
	 * @return
	 * @throws AppException
	 */
	public boolean updateUserName(Long uid,String name)throws AppException;

	/**
	 * 修改用户性别
	 * @param uid
	 * @param sex
	 * @return
	 * @throws AppException
	 */
	public boolean updateUserSex(Long uid,int sex)throws AppException;

	/**
	 * 修改用户头像
	 * @param uid
	 * @param attachmentId
	 * @return
	 */
	public boolean updateUserHead(Long uid,Long attachmentId)throws AppException;

	/**
	 * 绑定手机
	 * @param uid 用户id
	 * @param phone 手机号
	 * @param code 验证码
	 * @return
	 * @throws AppException
	 */
	public boolean bindPhone(Long uid,String phone,String code) throws AppException;

	/**
	 * 校验手机是否可用
	 * @param uid 用户id
	 * @param phone 手机号
	 * @return
	 * @throws AppException
	 */
	public boolean checkPhone(Long uid,String phone)throws AppException;

	/**
	 * 设置密码
	 * @param uid 用户id
	 * @param pwd 密码
	 * @param type 密码类型(0 登录密码，1 支付密码)
	 * @return
	 * @throws AppException
	 */
	public boolean addPwd(Long uid,String pwd,long type)throws AppException;

	/**
	 * 修改密码
	 * @param uid 用户id
	 * @param oldPwd 原始密码
	 * @param pwd 密码
	 * @param type 密码类型(0 登录密码，1 支付密码)
	 * @return
	 * @throws AppException
	 */
	public boolean changePwd(Long uid,String oldPwd,String pwd,long type)throws AppException;

}


