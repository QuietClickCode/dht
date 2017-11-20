
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.UserAddress;
import com.retailers.tools.exception.AppException;

import java.util.Map;
/**
 * 描述：用户收货地址列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 16:19:49
 */
public interface UserAddressService {

	/**
	 * 添加用户收货地址列表
	 * @param userAddress
	 * @return
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public boolean saveUserAddress(UserAddress userAddress);
	/**
	 * 编辑用户收货地址列表
	 * @param userAddress
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateUserAddress(UserAddress userAddress)throws AppException;
	/**
	 * 根据id查询用户收货地址列表
	 * @param uaId
	 * @return userAddress
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public UserAddress queryUserAddressByUaId(Long uaId);
	/**
	 * 查询用户收货地址列表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public Pagination<UserAddress> queryUserAddressList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据uaId删除用户收货地址列表
	 * @param uaId
	 * @return
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public boolean deleteUserAddressByUaId(Long uaId);

	/**
	 * 取得用户收货地址列表
	 * @param userId 用户id
	 * @param pageNo 页号
	 * @param pageSize 显示长度
	 * @return
	 */
	public Pagination<UserAddress> queryUserAddress(Long userId,int pageNo,int pageSize);

	/**
	 * 设置地址为默认收货地址
	 * @param userId 用户id
	 * @param uaId 收货地址id
	 * @return
	 */
	public boolean defaultUserAddress(Long userId,Long uaId)throws AppException;

}


