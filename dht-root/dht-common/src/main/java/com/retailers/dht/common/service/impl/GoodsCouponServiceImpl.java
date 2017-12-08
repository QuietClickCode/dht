
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.constant.CouponUseRangeConstant;
import com.retailers.dht.common.dao.CouponUseRangeMapper;
import com.retailers.dht.common.entity.CouponUseRange;
import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.dao.GoodsCouponMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.CouponVo;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.dht.common.vo.GoodsCouponVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：商品优惠活动Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 20:29:50
 */
@Service("goodscouponService")
public class GoodsCouponServiceImpl implements GoodsCouponService {
	@Autowired
	private GoodsCouponMapper goodsCouponMapper;
	@Autowired
	private CouponUseRangeMapper couponUseRangeMapper;

	@Transactional(rollbackFor = Exception.class)
	public boolean saveGoodsCoupon(GoodsCouponVo goodsCouponVo) {
		goodsCouponVo.setIsDelete(SystemConstant.SYS_IS_DELETE_NO);
		GoodsCoupon gcp = new GoodsCoupon();
		BeanUtils.copyProperties(goodsCouponVo,gcp);
		int status = goodsCouponMapper.saveGoodsCoupon(gcp);
		goodsCouponVo.setGcpId(gcp.getGcpId());
		saveCouponUseRange(goodsCouponVo);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsCoupon(GoodsCoupon goodsCoupon) {
		int status = goodsCouponMapper.updateGoodsCoupon(goodsCoupon);
		return status == 1 ? true : false;
	}
	public GoodsCoupon queryGoodsCouponByGcpId(Long gcpId) {
		return goodsCouponMapper.queryGoodsCouponByGcpId(gcpId);
	}

	public Pagination<GoodsCouponShowVo> queryGoodsCouponList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsCouponShowVo> page = new Pagination<GoodsCouponShowVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsCouponShowVo> list = goodsCouponMapper.queryGoodsCouponList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsCouponByGcpId(Long gcpId) {
		int status = goodsCouponMapper.deleteGoodsCouponByGcpId(gcpId);
		return status == 1 ? true : false;
	}

	@Transactional(rollbackFor = Exception.class)
	public boolean editorGoodsCoupon(GoodsCouponVo goodsCouponVo) {
		GoodsCoupon gcp = new GoodsCoupon();
		goodsCouponVo.setIsDelete(SystemConstant.SYS_IS_DELETE_NO);
		BeanUtils.copyProperties(goodsCouponVo,gcp);
		int status = goodsCouponMapper.updateGoodsCoupon(gcp);
		//清除该优惠下的使用范围
		couponUseRangeMapper.clearCouponUseRangeByCpId(CouponUseRangeConstant.TYPE_GOODS_COUPON,gcp.getGcpId());
		if(gcp.getGcpIsRestricted().intValue()!=CouponConstant.COUPON_USED_RANGE_ALL){
			saveCouponUseRange(goodsCouponVo);
		}
		return status == 1 ? true : false;
	}

	/**
	 * 保存使用范围
	 * @param couponVo
	 */
	private void saveCouponUseRange(GoodsCouponVo couponVo){
		List<Long> ids=new ArrayList<Long>();
		//优惠卷使用范围 1 指定商品种类
		List<CouponUseRange> curs=new ArrayList<CouponUseRange>();
		if(ObjectUtils.isNotEmpty(couponVo.getSpzlId())){
			String[] cids=couponVo.getSpzlId().split(",");
			for(String id:cids){
				if(ObjectUtils.isNotEmpty(id)){
					CouponUseRange cur=createCouponUseRange(couponVo.getGcpId(),Long.parseLong(id),CouponConstant.COUPON_USED_RANGE_GOODS_TYPE);
					curs.add(cur);
				}
			}
		}
		//优惠卷使用范围2 指定商品
		if(ObjectUtils.isNotEmpty(couponVo.getSpId())){
			String[] cids=couponVo.getSpId().split(",");
			for(String id:cids){
				if(ObjectUtils.isNotEmpty(id)){
					CouponUseRange cur=createCouponUseRange(couponVo.getGcpId(),Long.parseLong(id),CouponConstant.COUPON_USED_RANGE_GOODS);
					curs.add(cur);
				}
			}
		}
		if(ObjectUtils.isNotEmpty(curs)){
			//批量添加使用范围
			couponUseRangeMapper.saveCouponUseRanges(curs);
		}
	}

	private CouponUseRange createCouponUseRange(Long gcpId,Long relevanceId,Long isRestricted){
		CouponUseRange cur=new CouponUseRange();
		cur.setCpId(gcpId);
		cur.setCpurIsAllow(CouponUseRangeConstant.IS_ALLOW_USE_YES);
		cur.setCpurRelevanceId(relevanceId);
		cur.setType(CouponUseRangeConstant.TYPE_GOODS_COUPON);
		cur.setCpurType(isRestricted);
		return cur;
	}
}



