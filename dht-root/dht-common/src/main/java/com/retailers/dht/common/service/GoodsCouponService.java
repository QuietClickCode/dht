
package com.retailers.dht.common.service;

import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.view.GoodsCouponView;
import com.retailers.dht.common.vo.BuyGoodsDetailVo;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.dht.common.vo.GoodsCouponVo;
import com.retailers.dht.common.vo.GoodsTypePriceVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;

import java.util.List;
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
	 * @param goodsCouponVo
	 * @return
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public boolean saveGoodsCoupon(GoodsCouponVo goodsCouponVo);
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
	public Pagination<GoodsCouponShowVo> queryGoodsCouponList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gcpId删除商品优惠活动
	 * @param gcpId
	 * @return
	 * @author zhongp
	 * @date 2017-10-09 20:29:50
	 */
	public boolean deleteGoodsCouponByGcpId(Long gcpId);

	/**
	 * 编辑商品优惠
	 * @param goodsCouponvo
	 * @return
	 */
	public boolean editorGoodsCoupon(GoodsCouponVo goodsCouponvo);

	/**
	 * 根据商品取得该商品下的所有优惠
	 * @param goodsId
	 * @return
	 */
	public List<GoodsCouponShowVo> queryGoodsCouponByGid(Long goodsId);
	/**
	 * 根据优惠名称取得优惠列表（排除己存在的）
	 * @param couponNm 优惠名称
	 * @param goodsId 商品id
	 * @return
	 */
	public List<GoodsCouponShowVo> queryUnBindGoodsCouponByGid(String couponNm,Long goodsId);

	/**
	 * 商品绑定优惠
	 * @param goodsId 商品id
	 * @param gcpIds 优惠ids（多个之间用逗号隔开)
	 * @return
	 * @throws AppException
	 */
	public boolean goodsBindCoupon(Long goodsId,String gcpIds)throws AppException;

	/**
	 * 商品取消绑定优惠
	 * @param goodsId 商品id
	 * @param gcpIds 优惠ids（多个之间用逗号隔开)
	 * @return
	 * @throws AppException
	 */
	public boolean goodsUnBindCoupon(Long goodsId,String gcpIds)throws AppException;

	/**
	 * 校验商品优惠是否可用
	 * @param gcpMaps
	 * @param buyPrices
	 * @return
	 * @throws AppException
	 */
	public Object checkGoodsCoupon(Map<Long,List<Long>> gcpMaps,Map<Long,Long> buyPrices)throws AppException;

	/**
	 * 根据购买商品信息取得商品优惠列表
	 * @param gtpvs
	 * @return
	 */
	public Map<String,List<GoodsCouponView>> queryGoodsCouponBuyGid(List<GoodsTypePriceVo> gtpvs);

	/**
	 * 根据商品购买信息取得商品对应的优惠列表，和用户能够使用的优惠卷列表
	 * @param uid 用户id
	 * @param gbs 购买商品信息
	 * @return
	 * @throws AppException
	 */
	public Map<String,Object> queryGoodsCouponLists(Long uid, List<BuyGoodsDetailVo> gbs);

}


