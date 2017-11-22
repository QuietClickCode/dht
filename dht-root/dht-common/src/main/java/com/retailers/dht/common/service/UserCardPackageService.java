
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.UserCardPackage;
import java.util.Map;
/**
 * 描述：用户钱包，积分Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:13:08
 */
public interface UserCardPackageService {

	/**
	 * 添加用户钱包，积分
	 * @param userCardPackage
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public boolean saveUserCardPackage(UserCardPackage userCardPackage);
	/**
	 * 编辑用户钱包，积分
	 * @param userCardPackage
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateUserCardPackage(UserCardPackage userCardPackage);
	/**
	 * 根据id查询用户钱包，积分
	 * @param id
	 * @return userCardPackage
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public UserCardPackage queryUserCardPackageById(Long id);
	/**
	 * 查询用户钱包，积分列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public Pagination<UserCardPackage> queryUserCardPackageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除用户钱包，积分
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public boolean deleteUserCardPackageById(Long id);

	/**
	 * 取得用户卡包信息
	 * @param uid
	 * @return
	 */
	public UserCardPackage queryUserCardPackage(Long uid);

}


