
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.dao.GoodsMapper;
import com.retailers.dht.common.entity.GoodsCopy;
import com.retailers.dht.common.service.GoodsCopyService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsVo;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 17:29:40
 */
@Service("goodsService")
public class GoodsServiceImpl implements GoodsService {
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsCopyService goodsCopyService;
	public Goods saveGoods(Goods goods,Long uploadpersonId) {
		int status = goodsMapper.saveGoods(goods);
		return status == 1 ? goods : null;
	}
	public boolean updateGoods(Goods goods) {
		int status = goodsMapper.updateGoods(goods);
		return status == 1 ? true : false;
	}
	public Goods queryGoodsByGid(Long gid) {
		return goodsMapper.queryGoodsByGid(gid);
	}

	public Pagination<GoodsVo> queryGoodsList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsVo> page = new Pagination<GoodsVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsVo> list = goodsMapper.queryGoodsList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsByGid(Long gid) {
		Goods goods = goodsMapper.queryGoodsByGid(gid);
		goods.setIsDelete(1L);
		int status = goodsMapper.updateGoods(goods);
		return status == 1 ? true : false;
	}

	public void copyGoods(Goods goods,Long uploadpersonId){
		GoodsCopy gc = new GoodsCopy();
		BeanUtils.copyProperties(gc,goods);

		gc.setGuploadperson(uploadpersonId);
	}
}

