
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsCoupon;
import java.util.Map;
/**
 * 描述：商品优惠活动Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 20:29:50
 */
public interface GoodsCouponService {

	/**
	 * 添加商品优惠活动
	 * @param goodsCoupon
	 * @return
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public boolean saveGoodsCoupon(GoodsCoupon goodsCoupon);
	/**
	 * 编辑商品优惠活动
	 * @param goodsCoupon
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateGoodsCoupon(GoodsCoupon goodsCoupon);
	/**
	 * 根据id查询商品优惠活动
	 * @param gcpId
	 * @return goodsCoupon
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public GoodsCoupon queryGoodsCouponByGcpId(Long gcpId);
	/**
	 * 查询商品优惠活动列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public Pagination<GoodsCoupon> queryGoodsCouponList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gcpId删除商品优惠活动
	 * @param gcpId
	 * @return
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public boolean deleteGoodsCouponByGcpId(Long gcpId);

}


