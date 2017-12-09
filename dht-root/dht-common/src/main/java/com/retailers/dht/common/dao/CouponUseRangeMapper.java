package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.CouponUseRange;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：优惠卷使用范围DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-06 00:57:03
 */
public interface CouponUseRangeMapper {

	/**
	 * 添加优惠卷使用范围
	 * @param couponUseRange
	 * @return
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public int saveCouponUseRange(CouponUseRange couponUseRange);

	/**
	 * 批量添加优惠卷使用范围
	 * @param couponUseRanges
	 * @return
	 */
	public int saveCouponUseRanges(List<CouponUseRange> couponUseRanges);
	/**
	 * 编辑优惠卷使用范围
	 * @param couponUseRange
	 * @return
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public int updateCouponUseRange(CouponUseRange couponUseRange);

	/**
	 * 根据优惠使用范围限制
	 * @param cpurIds
	 * @param allowStatus
	 * @return
	 */
	public int updateCouponUseRangeAllow(@Param("cpurIds")List<Long> cpurIds,@Param("allowStatus")Integer allowStatus);
	/**
	 * 根据CpurId删除优惠卷使用范围
	 * @param cpurId
	 * @return
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public int deleteCouponUseRangeByCpurId(Long cpurId);
	/**
	 * 根据CpurId查询优惠卷使用范围
	 * @param cpurId
	 * @return
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public CouponUseRange queryCouponUseRangeByCpurId(Long cpurId);
	/**
	 * 查询优惠卷使用范围列表
	 * @param pagination 分页对象
	 * @return  优惠卷使用范围列表
	 * @author zhongp
	 * @date 2017-12-06 00:57:03
	 */
	public List<CouponUseRange> queryCouponUseRangeList(Pagination<CouponUseRange> pagination);

	/**
	 * 清除该优惠卷下所有用范围
	 * @param type 类型
	 * @param cpId 优惠id
	 */
	public void clearCouponUseRangeByCpId(@Param("type")Long type,@Param("cpId") Long cpId);

	/**
	 * 根据优惠卷id取得所有使用范围
	 * @param cpId
	 */
	public List<CouponUseRange> queryCouponUseRangeByCpId(@Param("type") Long type,@Param("cpId") Long cpId,@Param("cpurType") Long cpurType);

	/**
	 * 取得优惠范围例表
	 * @param type
	 * @param cpIds
	 * @param goodsId
	 * @return
	 */
	public List<CouponUseRange> queryCouponUseRangeByGid(@Param("type") Long type,@Param("cpIds") List<Long> cpIds,@Param("gid")Long goodsId);

}
