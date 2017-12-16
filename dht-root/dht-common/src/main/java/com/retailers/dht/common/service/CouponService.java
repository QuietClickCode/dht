
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.CouponShowVo;
import com.retailers.dht.common.vo.CouponVo;
import com.retailers.dht.common.vo.CouponWebVo;
import com.retailers.dht.common.vo.GoodsTypePriceVo;
import com.retailers.mybatis.pagination.PageInterceptor;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.Coupon;
import com.retailers.tools.exception.AppException;

import java.util.List;
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
	 * @param couponVo
	 * @return
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public boolean saveCoupon(CouponVo couponVo,Long optionId);
	/**
	 * 编辑优惠卷
	 * @param couponVo
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateCoupon(CouponVo couponVo,Long optionId)throws AppException;
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
	public Pagination<CouponShowVo> queryCouponList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cpId删除优惠卷
	 * @param cpId
	 * @return
	 * @author zhongp
	 * @date 2017-10-11 20:34:20
	 */
	public boolean deleteCouponByCpId(Long cpId);

	/**
	 * 取得优惠卷列表
	 * @param uid 用户id
	 * @return
	 * @throws AppException
	 */
	public List<CouponWebVo> queryCouponList(Long uid, int pageNo, int pageSize)throws AppException;

	/**
	 * 抢夺优惠卷
	 * @param userId 用户id
	 * @param cpId 优惠卷id
	 * @return
	 * @throws AppException
	 */
	public boolean userGrabCoupon(Long userId,Long cpId)throws AppException;

	/**
	 * 根据状态取得用户优惠卷列表
	 * @param uid
	 * @param type
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws AppException
	 */
	public Pagination<CouponWebVo> queryUserCoupon(Long uid,long type, int pageNo, int pageSize);

	/**
	 * 清除过期优惠卷
	 */
	public void clearExpireCoupon();

	/**
	 * 取得用户在该 商品下的可用优惠卷
	 * @param gid 商品id
	 * @return
	 */
	public List<CouponShowVo> queryCouponByGid(Long gid);

	/**
	 * 校验用户的优惠卷是否正常 可是否可用于该商品
	 * @param uid 用户id
	 * @param cIds 优惠卷id
	 * @param gIds 商品ids
	 * @throws AppException
	 */
	public void checkUserUseCouponByGoodsIds(Long uid,List<Long> cIds,List<Long> gIds)throws AppException;

	/**
	 * 根据用户和选购商品取取用户可用的优惠卷
	 * @param uid 用户id
	 * @param gtpvs 购买商品对应的商品id 商品类型，商品总价
	 * @return
	 */
	public List<CouponWebVo> queryUserUseCoupons(Long uid,List<GoodsTypePriceVo> gtpvs);

}


