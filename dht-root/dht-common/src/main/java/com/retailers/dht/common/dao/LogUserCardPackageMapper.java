package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.LogUserCardPackage;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：用户卡包操作日志（钱包，积分）DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:16:42
 */
public interface LogUserCardPackageMapper {

	/**
	 * 添加用户卡包操作日志（钱包，积分）
	 * @param logUserCardPackage
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:16:42
	 */
	public int saveLogUserCardPackage(LogUserCardPackage logUserCardPackage);
	/**
	 * 编辑用户卡包操作日志（钱包，积分）
	 * @param logUserCardPackage
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:16:42
	 */
	public int updateLogUserCardPackage(LogUserCardPackage logUserCardPackage);
	/**
	 * 根据Id删除用户卡包操作日志（钱包，积分）
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:16:42
	 */
	public int deleteLogUserCardPackageById(Long id);
	/**
	 * 根据Id查询用户卡包操作日志（钱包，积分）
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:16:42
	 */
	public LogUserCardPackage queryLogUserCardPackageById(Long id);
	/**
	 * 查询用户卡包操作日志（钱包，积分）列表
	 * @param pagination 分页对象
	 * @return  用户卡包操作日志（钱包，积分）列表
	 * @author zhongp
	 * @date 2017-11-22 23:16:42
	 */
	public List<LogUserCardPackage> queryLogUserCardPackageList(Pagination<LogUserCardPackage> pagination);

}
