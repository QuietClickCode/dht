package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.WxAuthUser;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：用户卡包操作日志（钱包，积分）DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:23:16
 */
public interface WxAuthUserMapper {

	/**
	 * 添加用户卡包操作日志（钱包，积分）
	 * @param wxAuthUser
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public int saveWxAuthUser(WxAuthUser wxAuthUser);
	/**
	 * 编辑用户卡包操作日志（钱包，积分）
	 * @param wxAuthUser
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public int updateWxAuthUser(WxAuthUser wxAuthUser);
	/**
	 * 根据WauId删除用户卡包操作日志（钱包，积分）
	 * @param wauId
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public int deleteWxAuthUserByWauId(Long wauId);
	/**
	 * 根据WauId查询用户卡包操作日志（钱包，积分）
	 * @param wauId
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public WxAuthUser queryWxAuthUserByWauId(Long wauId);
	/**
	 * 查询用户卡包操作日志（钱包，积分）列表
	 * @param pagination 分页对象
	 * @return  用户卡包操作日志（钱包，积分）列表
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public List<WxAuthUser> queryWxAuthUserList(Pagination<WxAuthUser> pagination);

}
