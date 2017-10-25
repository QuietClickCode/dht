
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGgbrel;
import com.retailers.dht.common.dao.GoodsGgbrelMapper;
import com.retailers.dht.common.service.GoodsGgbrelService;
import com.retailers.dht.common.vo.GoodsGgbrelVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与品牌关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 10:09:42
 */
@Service("goodsggbrelService")
public class GoodsGgbrelServiceImpl implements GoodsGgbrelService {
	@Autowired
	private GoodsGgbrelMapper goodsGgbrelMapper;
	public boolean saveGoodsGgbrel(String gbIds,Long gid) {
		int status = 0;
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("gid",gid);
		Pagination<GoodsGgbrelVo> pagination = queryGoodsGgbrelList(map,1,100);
		for(GoodsGgbrel goodsGgbrel:pagination.getData()){
			goodsGgbrel.setIsDelete(1L);
			updateGoodsGgbrel(goodsGgbrel);
		}
		if(!gbIds.equals(",")){
			String[] gbIdsArr = gbIds.replaceAll(","," ").trim().split(" ");
			if(!ObjectUtils.isEmpty(gbIdsArr)){
				for (String gbId:gbIdsArr){
					Long gbIdLong = Long.parseLong(gbId);
					GoodsGgbrel goodsGgbrel = new GoodsGgbrel();
					goodsGgbrel.setIsDelete(0L);
					goodsGgbrel.setGbId(gbIdLong);
					goodsGgbrel.setGid(gid);
					status += goodsGgbrelMapper.saveGoodsGgbrel(goodsGgbrel);
				}
			}
		}
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGgbrel(GoodsGgbrel goodsGgbrel) {
		int status = goodsGgbrelMapper.updateGoodsGgbrel(goodsGgbrel);
		return status == 1 ? true : false;
	}
	public GoodsGgbrel queryGoodsGgbrelByGgbId(Long ggbId) {
		return goodsGgbrelMapper.queryGoodsGgbrelByGgbId(ggbId);
	}

	public Pagination<GoodsGgbrelVo> queryGoodsGgbrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGgbrelVo> page = new Pagination<GoodsGgbrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGgbrelVo> list = goodsGgbrelMapper.queryGoodsGgbrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGgbrelByGgbId(String ggbIds) {
		String[] ggbIdsArr = ggbIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(ggbIdsArr)){
			for (String ggbId:ggbIdsArr){
				Long gbIdLong = Long.parseLong(ggbId);
				GoodsGgbrel goodsGgbrel = goodsGgbrelMapper.queryGoodsGgbrelByGgbId(gbIdLong);
				goodsGgbrel.setIsDelete(1L);
				status += goodsGgbrelMapper.updateGoodsGgbrel(goodsGgbrel);
			}
		}
		return status == ggbIdsArr.length ? true : false;
	}
}

