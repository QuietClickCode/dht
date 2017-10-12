
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.Coupon;
import com.retailers.dht.common.dao.CouponMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.CouponService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：优惠卷Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-11 20:34:20
 */
@Service("couponService")
public class CouponServiceImpl implements CouponService {
	@Autowired
	private CouponMapper couponMapper;
	@Autowired
	private AttachmentService attachmentService;

	public boolean saveCoupon(Coupon coupon) {
		int status = couponMapper.saveCoupon(coupon);
		List<Long> attachmentIds= new ArrayList<Long>();
		attachmentIds.add(coupon.getCpLogo());
		attachmentService.editorAttachment(attachmentIds);
		return status == 1 ? true : false;
	}
	public boolean updateCoupon(Coupon coupon) {
		int status = couponMapper.updateCoupon(coupon);
		return status == 1 ? true : false;
	}
	public Coupon queryCouponByCpId(Long cpId) {
		return couponMapper.queryCouponByCpId(cpId);
	}

	public Pagination<Coupon> queryCouponList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Coupon> page = new Pagination<Coupon>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Coupon> list = couponMapper.queryCouponList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCouponByCpId(Long cpId) {
		int status = couponMapper.deleteCouponByCpId(cpId);
		return status == 1 ? true : false;
	}
}

