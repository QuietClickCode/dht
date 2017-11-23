package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.CouponUser;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：用户优惠卷领取记录DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 23:31:01
 */
public interface CouponUserMapper {

	/**
	 * 添加用户优惠卷领取记录
	 * @param couponUser
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:31:01
	 */
	public int saveCouponUser(CouponUser couponUser);
	/**
	 * 编辑用户优惠卷领取记录
	 * @param couponUser
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:31:01
	 */
	public int updateCouponUser(CouponUser couponUser);
	/**
	 * 根据CpuId删除用户优惠卷领取记录
	 * @param cpuId
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:31:01
	 */
	public int deleteCouponUserByCpuId(Long cpuId);
	/**
	 * 根据CpuId查询用户优惠卷领取记录
	 * @param cpuId
	 * @return
	 * @author zhongp
	 * @date 2017-11-16 23:31:01
	 */
	public CouponUser queryCouponUserByCpuId(Long cpuId);
	/**
	 * 查询用户优惠卷领取记录列表
	 * @param pagination 分页对象
	 * @return  用户优惠卷领取记录列表
	 * @author zhongp
	 * @date 2017-11-16 23:31:01
	 */
	public List<CouponUser> queryCouponUserList(Pagination<CouponUser> pagination);

	/**
	 * 校验用户是否领取过该优惠卷
	 * @param cpId
	 * @param uId
	 * @return
	 */
	public int checkCouponUser(@Param("cpId")Long cpId,@Param("uId")Long uId);

	/**
	 * 设置用户优惠卷己过期
	 * @param cpId 优惠卷id
	 * @return
	 */
	public int expireCouponUser(Long cpId);

}
