
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.CouponUseRange;
import com.retailers.dht.common.dao.CouponUseRangeMapper;
import com.retailers.dht.common.service.CouponUseRangeService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：优惠卷使用范围Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-06 00:57:03
 */
@Service("couponuserangeService")
public class CouponUseRangeServiceImpl implements CouponUseRangeService {
	@Autowired
	private CouponUseRangeMapper couponUseRangeMapper;
	public boolean saveCouponUseRange(CouponUseRange couponUseRange) {
		int status = couponUseRangeMapper.saveCouponUseRange(couponUseRange);
		return status == 1 ? true : false;
	}
	public boolean updateCouponUseRange(CouponUseRange couponUseRange) {
		int status = couponUseRangeMapper.updateCouponUseRange(couponUseRange);
		return status == 1 ? true : false;
	}
	public CouponUseRange queryCouponUseRangeByCpurId(Long cpurId) {
		return couponUseRangeMapper.queryCouponUseRangeByCpurId(cpurId);
	}

	public Pagination<CouponUseRange> queryCouponUseRangeList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<CouponUseRange> page = new Pagination<CouponUseRange>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CouponUseRange> list = couponUseRangeMapper.queryCouponUseRangeList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCouponUseRangeByCpurId(Long cpurId) {
		int status = couponUseRangeMapper.deleteCouponUseRangeByCpurId(cpurId);
		return status == 1 ? true : false;
	}
}

