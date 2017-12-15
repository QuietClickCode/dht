package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.UserCardPackage;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：用户钱包，积分DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:13:08
 */
public interface UserCardPackageMapper {

	/**
	 * 添加用户钱包，积分
	 * @param userCardPackage
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public int saveUserCardPackage(UserCardPackage userCardPackage);
	/**
	 * 编辑用户钱包，积分
	 * @param userCardPackage
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public int updateUserCardPackage(UserCardPackage userCardPackage);

	/**
	 * 用户充值
	 * @param uid 用户id
	 * @param wallet 用户钱包
	 * @param version 版本号
	 * @return
	 */
	public int userRechage(@Param("uid")Long uid, @Param("wallet")Long wallet, @Param("version") Integer version);

	/**
	 * 编辑用户积分
	 * @param uid 用户id
	 * @param integral 积分
	 * @param version 版本号
	 * @return
	 */
	public int userIntegral(@Param("uid")Long uid, @Param("integral")Long integral, @Param("version") Integer version);
	/**
	 * 根据Id删除用户钱包，积分
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public int deleteUserCardPackageById(Long id);
	/**
	 * 根据Id查询用户钱包，积分
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public UserCardPackage queryUserCardPackageById(Long id);
	/**
	 * 查询用户钱包，积分列表
	 * @param pagination 分页对象
	 * @return  用户钱包，积分列表
	 * @author zhongp
	 * @date 2017-11-22 23:13:08
	 */
	public List<UserCardPackage> queryUserCardPackageList(Pagination<UserCardPackage> pagination);

}
