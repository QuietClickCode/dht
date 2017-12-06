
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.CouponUseRange;
import java.util.Map;
/**
 * 描述：优惠卷使用范围Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-06 00:57:03
 */
public interface CouponUseRangeService {

	/**
	 * 添加优惠卷使用范围
	 * @param couponUseRange
	 * @return
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public boolean saveCouponUseRange(CouponUseRange couponUseRange);
	/**
	 * 编辑优惠卷使用范围
	 * @param couponUseRange
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateCouponUseRange(CouponUseRange couponUseRange);
	/**
	 * 根据id查询优惠卷使用范围
	 * @param cpurId
	 * @return couponUseRange
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public CouponUseRange queryCouponUseRangeByCpurId(Long cpurId);
	/**
	 * 查询优惠卷使用范围列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public Pagination<CouponUseRange> queryCouponUseRangeList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cpurId删除优惠卷使用范围
	 * @param cpurId
	 * @return
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public boolean deleteCouponUseRangeByCpurId(Long cpurId);

}


