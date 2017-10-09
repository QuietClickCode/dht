
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.dao.GoodsCouponMapper;
import com.retailers.dht.common.service.GoodsCouponService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
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
	public boolean saveGoodsCoupon(GoodsCoupon goodsCoupon) {
		int status = goodsCouponMapper.saveGoodsCoupon(goodsCoupon);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsCoupon(GoodsCoupon goodsCoupon) {
		int status = goodsCouponMapper.updateGoodsCoupon(goodsCoupon);
		return status == 1 ? true : false;
	}
	public GoodsCoupon queryGoodsCouponByGcpId(Long gcpId) {
		return goodsCouponMapper.queryGoodsCouponByGcpId(gcpId);
	}

	public Pagination<GoodsCoupon> queryGoodsCouponList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsCoupon> page = new Pagination<GoodsCoupon>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsCoupon> list = goodsCouponMapper.queryGoodsCouponList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsCouponByGcpId(Long gcpId) {
		int status = goodsCouponMapper.deleteGoodsCouponByGcpId(gcpId);
		return status == 1 ? true : false;
	}
}

