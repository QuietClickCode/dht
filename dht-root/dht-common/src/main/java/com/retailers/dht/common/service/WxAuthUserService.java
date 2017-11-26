
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.WxAuthUser;
import com.retailers.tools.exception.AppException;

import java.util.Map;
/**
 * 描述：用户卡包操作日志（钱包，积分）Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:23:16
 */
public interface WxAuthUserService {

	/**
	 * 添加用户卡包操作日志（钱包，积分）
	 * @param wxAuthUser
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public boolean saveWxAuthUser(WxAuthUser wxAuthUser);
	/**
	 * 编辑用户卡包操作日志（钱包，积分）
	 * @param wxAuthUser
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateWxAuthUser(WxAuthUser wxAuthUser);
	/**
	 * 根据id查询用户卡包操作日志（钱包，积分）
	 * @param wauId
	 * @return wxAuthUser
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public WxAuthUser queryWxAuthUserByWauId(Long wauId);
	/**
	 * 查询用户卡包操作日志（钱包，积分）列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public Pagination<WxAuthUser> queryWxAuthUserList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据wauId删除用户卡包操作日志（钱包，积分）
	 * @param wauId
	 * @return
	 * @author zhongp
	 * @date 2017-11-22 23:23:16
	 */
	public boolean deleteWxAuthUserByWauId(Long wauId);



	/**
	 * 根据code 取得当前 授权用户的微信信息
	 * @param code
	 * @return
	 * @throws AppException
	 */
	public WxAuthUser queryWxAuthUser(String code);

}


