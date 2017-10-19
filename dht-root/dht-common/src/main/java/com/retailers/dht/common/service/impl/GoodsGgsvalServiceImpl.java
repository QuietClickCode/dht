
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGgsval;
import com.retailers.dht.common.dao.GoodsGgsvalMapper;
import com.retailers.dht.common.service.GoodsGgsvalService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与规格值关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-19 18:43:18
 */
@Service("goodsggsvalService")
public class GoodsGgsvalServiceImpl implements GoodsGgsvalService {
	@Autowired
	private GoodsGgsvalMapper goodsGgsvalMapper;
	public boolean saveGoodsGgsval(GoodsGgsval goodsGgsval) {
		int status = goodsGgsvalMapper.saveGoodsGgsval(goodsGgsval);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGgsval(GoodsGgsval goodsGgsval) {
		int status = goodsGgsvalMapper.updateGoodsGgsval(goodsGgsval);
		return status == 1 ? true : false;
	}
	public GoodsGgsval queryGoodsGgsvalByGgsId(Long ggsId) {
		return goodsGgsvalMapper.queryGoodsGgsvalByGgsId(ggsId);
	}

	public Pagination<GoodsGgsval> queryGoodsGgsvalList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGgsval> page = new Pagination<GoodsGgsval>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGgsval> list = goodsGgsvalMapper.queryGoodsGgsvalList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGgsvalByGgsId(Long ggsId) {
		int status = goodsGgsvalMapper.deleteGoodsGgsvalByGgsId(ggsId);
		return status == 1 ? true : false;
	}
}

