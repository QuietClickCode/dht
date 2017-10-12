package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.Coupon;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：优惠卷DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 20:34:20
 */
public interface CouponMapper {

	/**
	 * 添加优惠卷
	 * @param coupon
	 * @return
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public int saveCoupon(Coupon coupon);
	/**
	 * 编辑优惠卷
	 * @param coupon
	 * @return
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public int updateCoupon(Coupon coupon);
	/**
	 * 根据CpId删除优惠卷
	 * @param cpId
	 * @return
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public int deleteCouponByCpId(Long cpId);
	/**
	 * 根据CpId查询优惠卷
	 * @param cpId
	 * @return
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public Coupon queryCouponByCpId(Long cpId);
	/**
	 * 查询优惠卷列表
	 * @param pagination 分页对象
	 * @return  优惠卷列表
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public List<Coupon> queryCouponList(Pagination<Coupon> pagination);

}
