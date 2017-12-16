package com.retailers.dht.common.dao;

import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.view.GoodsCouponView;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
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

	/**
	 * 根据商品类型和商品id取得该商品下允许的优惠
	 * @param goodsType 商品类型
	 * @param goodsId 商品id
	 * @param curDate 当前时间
	 * @return
	 */
	public List<GoodsCouponShowVo> queryAllowGoodsCouponByGid(@Param("goodsType")Long goodsType, @Param("goodsId")Long goodsId, @Param("curDate")Date curDate);

	/**
	 * 批量取得商品列表允许的优惠
	 * @param goodsIds
	 * @param curDate
	 * @return
	 */
	public List<GoodsCouponShowVo> queryAllowGoodsCouponByGids(@Param("goodsIds")List<Long> goodsIds, @Param("curDate")Date curDate);

	/**
	 * 根据商品id取得商品优惠列表
	 * @param goodsIds 商品ids
	 * @param curDate 当前时间
	 * @return
	 */
	public List<GoodsCouponView> queryGoodsCouponByGids(@Param("goodsIds")List<Long> goodsIds, @Param("curDate")Date curDate);

	/**
	 * 取得无限制商品优惠例表
	 * @param curDate 当前时间
	 * @return
	 */
	public List<GoodsCouponView> queryUnRestrictedGoodsCoupon(@Param("curDate")Date curDate);

	/**
	 * 取得该商品下未绑定的优惠
	 * @param couponNm 优惠名称
	 * @param gcpIds 己绑定优惠
	 * @param curDate
	 * @return
	 */
	public List<GoodsCouponShowVo> queryUnBindGoodsCuoponByGid(@Param("couponNm")String couponNm, @Param("gcpIds")List<Long> gcpIds, @Param("curDate")Date curDate);

}
