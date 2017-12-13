package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.WxAuthUser;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

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

	/**
	 * 根据微信openid 取得登录信息
	 * @param openId
	 * @return
	 */
	public WxAuthUser queryWxAuthUserByOpenId(@Param("wxId")Long wxId,@Param("openId") String openId);

	/**
	 * 取得关联用户
	 * @param unionid
	 * @param openId
	 * @return
	 */
	public Long queryRelationUserByUnionid(@Param("unionid")String unionid,@Param("openId") String openId);

	/**
	 * 微信用户关联用户
	 * @param wauId 微信id
	 * @param uid 用户id
	 * @return
	 */
	public long relationUser(@Param("wauId")Long wauId,@Param("uid")Long uid);

}
