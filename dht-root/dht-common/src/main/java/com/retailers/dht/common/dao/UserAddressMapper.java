package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.UserAddress;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：用户收货地址列表DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 16:19:49
 */
public interface UserAddressMapper {

	/**
	 * 添加用户收货地址列表
	 * @param userAddress
	 * @return
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public int saveUserAddress(UserAddress userAddress);
	/**
	 * 编辑用户收货地址列表
	 * @param userAddress
	 * @return
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public int updateUserAddress(UserAddress userAddress);
	/**
	 * 根据UaId删除用户收货地址列表
	 * @param uaId
	 * @return
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public int deleteUserAddressByUaId(Long uaId);
	/**
	 * 根据UaId查询用户收货地址列表
	 * @param uaId
	 * @return
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public UserAddress queryUserAddressByUaId(Long uaId);
	/**
	 * 查询用户收货地址列表列表
	 * @param pagination 分页对象
	 * @return  用户收货地址列表列表
	 * @author zhongp
	 * @date 2017-11-20 16:19:49
	 */
	public List<UserAddress> queryUserAddressList(Pagination<UserAddress> pagination);

	/**
	 * 清除用户默认地址
	 * @param uid 用户id
	 * @return
	 */
	public long clearUserDeafultAddress(Long uid);

}
