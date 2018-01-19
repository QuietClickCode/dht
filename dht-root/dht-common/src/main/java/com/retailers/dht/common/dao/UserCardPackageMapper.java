package com.retailers.dht.common.dao;

import com.retailers.dht.common.entity.UserCardPackage;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * 用户充值
	 * @param uid 用户id
	 * @param wallet 用户钱包
	 * @param version 版本号
	 * @return
	 */
	public int userRechage(@Param("uid")Long uid, @Param("wallet")Long wallet, @Param("version") Integer version);

	/**
	 * 用户使用钱包
	 * @param uid 用户id
	 * @param wallet 使用金额
	 * @param version 版本号
	 * @return
	 */
	public int userWalletPay(@Param("uid")Long uid, @Param("wallet")Long wallet, @Param("version") Integer version);

	/**
	 * 用户消费两万返现累计
	 * @param uid 用户id
	 * @param integral 消费额
	 * @param version 版本号
	 * @return
	 */
	public int userIntegral(@Param("uid")Long uid, @Param("integral")Long integral, @Param("version") Integer version);

	/**
	 * 添加用户返现金额
	 * @param maps
	 * @return
	 */
	public int addCashBack(List<Map<String,Long>> maps);
	/**
	 * 用户消费统计
	 * @param uid 用户
	 * @param type 类型
	 * @param tradePrice 累计交易金额
	 * @param cumulationCashPrice 累计返现金额
	 * @param version 版本号
	 * @return
	 */
	public int statisticsUserSalseConsume(@Param("uid")Long uid,@Param("type")Integer type,@Param("tradePrice")Long tradePrice,@Param("cumulationCashPrice")Long cumulationCashPrice,@Param("version") Integer version);

	/**
	 * 用户钱包消费
	 * @param uid 用户id
	 * @param wallet 钱包消费额
	 * @param version 版本号
	 * @return
	 */
	public int userWalletConsume(@Param("uid")Long uid, @Param("wallet")Long wallet, @Param("version") Integer version);
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

	/**
	 * 批量取得用户钱包数据
	 * @param cbUids
	 * @return
	 */
	public List<UserCardPackage> queryUserCardPackages(@Param("cbUids") Set<Long> cbUids);
}
