
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsFreight;
import com.retailers.dht.common.dao.GoodsFreightMapper;
import com.retailers.dht.common.service.GoodsFreightService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品运费表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-29 10:39:17
 */
@Service("goodsfreightService")
public class GoodsFreightServiceImpl implements GoodsFreightService {
	@Autowired
	private GoodsFreightMapper goodsFreightMapper;
	public boolean saveGoodsFreight(GoodsFreight goodsFreight) {
		int status = goodsFreightMapper.saveGoodsFreight(goodsFreight);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsFreight(GoodsFreight goodsFreight) {
		int status = goodsFreightMapper.updateGoodsFreight(goodsFreight);
		return status == 1 ? true : false;
	}
	public GoodsFreight queryGoodsFreightByGfId(Long gfId) {
		return goodsFreightMapper.queryGoodsFreightByGfId(gfId);
	}

	public Pagination<GoodsFreight> queryGoodsFreightList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsFreight> page = new Pagination<GoodsFreight>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsFreight> list = goodsFreightMapper.queryGoodsFreightList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsFreightByGfId(Long gfId) {
		int status = goodsFreightMapper.deleteGoodsFreightByGfId(gfId);
		return status == 1 ? true : false;
	}
}

