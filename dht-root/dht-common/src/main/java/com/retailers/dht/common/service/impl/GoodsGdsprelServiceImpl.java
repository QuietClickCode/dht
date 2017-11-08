
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGdsprel;
import com.retailers.dht.common.dao.GoodsGdsprelMapper;
import com.retailers.dht.common.service.GoodsGdsprelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与特价关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-08 17:29:16
 */
@Service("goodsgdsprelService")
public class GoodsGdsprelServiceImpl implements GoodsGdsprelService {
	@Autowired
	private GoodsGdsprelMapper goodsGdsprelMapper;
	public boolean saveGoodsGdsprel(GoodsGdsprel goodsGdsprel) {
		int status = goodsGdsprelMapper.saveGoodsGdsprel(goodsGdsprel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGdsprel(GoodsGdsprel goodsGdsprel) {
		int status = goodsGdsprelMapper.updateGoodsGdsprel(goodsGdsprel);
		return status == 1 ? true : false;
	}
	public GoodsGdsprel queryGoodsGdsprelByGdspId(Long gdspId) {
		return goodsGdsprelMapper.queryGoodsGdsprelByGdspId(gdspId);
	}

	public Pagination<GoodsGdsprel> queryGoodsGdsprelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGdsprel> page = new Pagination<GoodsGdsprel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGdsprel> list = goodsGdsprelMapper.queryGoodsGdsprelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGdsprelByGdspId(Long gdspId) {
		int status = goodsGdsprelMapper.deleteGoodsGdsprelByGdspId(gdspId);
		return status == 1 ? true : false;
	}
}

