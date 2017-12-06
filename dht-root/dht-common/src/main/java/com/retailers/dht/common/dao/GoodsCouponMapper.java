package com.retailers.dht.common.dao;

import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
/**
 * 描述：商品优惠活动DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 20:29:50
 */
public interface GoodsCouponMapper {

	/**
	 * 添加商品优惠活动
	 * @param goodsCoupon
	 * @return
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public int saveGoodsCoupon(GoodsCoupon goodsCoupon);
	/**
	 * 编辑商品优惠活动
	 * @param goodsCoupon
	 * @return
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public int updateGoodsCoupon(GoodsCoupon goodsCoupon);
	/**
	 * 根据GcpId删除商品优惠活动
	 * @param gcpId
	 * @return
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public int deleteGoodsCouponByGcpId(Long gcpId);
	/**
	 * 根据GcpId查询商品优惠活动
	 * @param gcpId
	 * @return
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public GoodsCoupon queryGoodsCouponByGcpId(Long gcpId);
	/**
	 * 查询商品优惠活动列表
	 * @param pagination 分页对象
	 * @return  商品优惠活动列表
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public List<GoodsCouponShowVo> queryGoodsCouponList(Pagination<GoodsCouponShowVo> pagination);

}
