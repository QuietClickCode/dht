
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGsval;
import com.retailers.dht.common.dao.GoodsGsvalMapper;
import com.retailers.dht.common.service.GoodsGsvalService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品规格值表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 09:46:10
 */
@Service("goodsgsvalService")
public class GoodsGsvalServiceImpl implements GoodsGsvalService {
	@Autowired
	private GoodsGsvalMapper goodsGsvalMapper;
	public GoodsGsval saveGoodsGsval(GoodsGsval goodsGsval) {
		int status = goodsGsvalMapper.saveGoodsGsval(goodsGsval);
		return status == 1 ? goodsGsval : null;
	}
	public boolean updateGoodsGsval(GoodsGsval goodsGsval) {
		int status = goodsGsvalMapper.updateGoodsGsval(goodsGsval);
		return status == 1 ? true : false;
	}
	public GoodsGsval queryGoodsGsvalByGsvId(Long gsvId) {
		return goodsGsvalMapper.queryGoodsGsvalByGsvId(gsvId);
	}

	public Pagination<GoodsGsval> queryGoodsGsvalList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGsval> page = new Pagination<GoodsGsval>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGsval> list = goodsGsvalMapper.queryGoodsGsvalList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGsvalByGsvId(Long gsvId) {
		GoodsGsval goodsGsval = goodsGsvalMapper.queryGoodsGsvalByGsvId(gsvId);
		goodsGsval.setIsDelete(1L);
		int status = goodsGsvalMapper.updateGoodsGsval(goodsGsval);
		return status == 1 ? true : false;
	}
}

