
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGtgbrel;
import com.retailers.dht.common.dao.GoodsGtgbrelMapper;
import com.retailers.dht.common.service.GoodsGtgbrelService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：大类与规格关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 17:46:58
 */
@Service("goodsgtgbrelService")
public class GoodsGtgbrelServiceImpl implements GoodsGtgbrelService {
	@Autowired
	private GoodsGtgbrelMapper goodsGtgbrelMapper;
	public boolean saveGoodsGtgbrel(GoodsGtgbrel goodsGtgbrel) {
		int status = goodsGtgbrelMapper.saveGoodsGtgbrel(goodsGtgbrel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGtgbrel(GoodsGtgbrel goodsGtgbrel) {
		int status = goodsGtgbrelMapper.updateGoodsGtgbrel(goodsGtgbrel);
		return status == 1 ? true : false;
	}
	public GoodsGtgbrel queryGoodsGtgbrelByGtgbId(Long gtgbId) {
		return goodsGtgbrelMapper.queryGoodsGtgbrelByGtgbId(gtgbId);
	}

	public Pagination<GoodsGtgbrel> queryGoodsGtgbrelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGtgbrel> page = new Pagination<GoodsGtgbrel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGtgbrel> list = goodsGtgbrelMapper.queryGoodsGtgbrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGtgbrelByGtgbId(Long gtgbId) {
		int status = goodsGtgbrelMapper.deleteGoodsGtgbrelByGtgbId(gtgbId);
		return status == 1 ? true : false;
	}
}

