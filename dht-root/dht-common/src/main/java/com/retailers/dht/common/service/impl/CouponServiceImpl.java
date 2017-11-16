
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.Coupon;
import com.retailers.dht.common.dao.CouponMapper;
import com.retailers.dht.common.service.AttachmentService;
import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.common.utils.AttachmentUploadImageUtils;
import com.retailers.dht.common.vo.CouponShowVo;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
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
		Map<Long,Long> atts= AttachmentUploadImageUtils.findUploadImages(coupon.getCpContext());
		if(!atts.isEmpty()){
			for(Long id:atts.keySet()){
				attachmentIds.add(id);
			}
		}
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

	public Pagination<CouponShowVo> queryCouponList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<CouponShowVo> page = new Pagination<CouponShowVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CouponShowVo> list = couponMapper.queryCouponList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCouponByCpId(Long cpId) {
		int status = couponMapper.deleteCouponByCpId(cpId);
		return status == 1 ? true : false;
	}

	/**
	 *
	 * @param uid 用户id
	 * @return
	 * @throws AppException
	 */
	public List<Coupon> queryCouponList(Long uid,int pageNo, int pageSize) throws AppException {
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("uid",uid);
		Pagination<CouponShowVo> page = new Pagination<CouponShowVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		return couponMapper.queryCurValidCoupon(page);
	}
}

