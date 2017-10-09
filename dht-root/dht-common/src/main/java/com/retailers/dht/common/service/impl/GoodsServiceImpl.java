
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.dao.GoodsMapper;
import com.retailers.dht.common.service.GoodsService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 17:29:36
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	public boolean saveGoods(Goods goods) {
		int status = goodsMapper.saveGoods(goods);
		return status == 1 ? true : false;
	}
	public boolean updateGoods(Goods goods) {
		int status = goodsMapper.updateGoods(goods);
		return status == 1 ? true : false;
	}
	public Goods queryGoodsByGid(Long gid) {
		return goodsMapper.queryGoodsByGid(gid);
	}

	public Pagination<Goods> queryGoodsList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Goods> page = new Pagination<Goods>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Goods> list = goodsMapper.queryGoodsList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsByGid(Long gid) {
		int status = goodsMapper.deleteGoodsByGid(gid);
		return status == 1 ? true : false;
	}
}

