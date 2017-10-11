
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.Coupon;
import java.util.Map;
/**
 * 描述：优惠卷Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 20:34:20
 */
public interface CouponService {

	/**
	 * 添加优惠卷
	 * @param coupon
	 * @return
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public boolean saveCoupon(Coupon coupon);
	/**
	 * 编辑优惠卷
	 * @param coupon
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateCoupon(Coupon coupon);
	/**
	 * 根据id查询优惠卷
	 * @param cpId
	 * @return coupon
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public Coupon queryCouponByCpId(Long cpId);
	/**
	 * 查询优惠卷列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public Pagination<Coupon> queryCouponList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cpId删除优惠卷
	 * @param cpId
	 * @return
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public boolean deleteCouponByCpId(Long cpId);

}


