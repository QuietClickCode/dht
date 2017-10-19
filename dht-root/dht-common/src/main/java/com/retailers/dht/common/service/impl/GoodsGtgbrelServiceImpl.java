
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGtgbrel;
import com.retailers.dht.common.dao.GoodsGtgbrelMapper;
import com.retailers.dht.common.service.GoodsGtgbrelService;
import com.retailers.dht.common.vo.GoodsGtgbrelVo;
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
	public boolean saveGoodsGtgbrel(String gbIds,Long gtId) {
		int status = 0;
		GoodsGtgbrel goodsGtgbrel = new GoodsGtgbrel();
		gbIds = gbIds.replaceAll(","," ");
		String[] gbIdArr = gbIds.trim().split(" ");
		for(int i=0; i<gbIdArr.length; i++){
			Long gbId = Long.parseLong(gbIdArr[i]);
			goodsGtgbrel.setIsDelete(0L);
			goodsGtgbrel.setGtId(gtId);
			goodsGtgbrel.setGbId(gbId);
			status += goodsGtgbrelMapper.saveGoodsGtgbrel(goodsGtgbrel);
		}

		return status == gbIdArr.length ? true : false;
	}
	public boolean updateGoodsGtgbrel(GoodsGtgbrel goodsGtgbrel) {
		int status = goodsGtgbrelMapper.updateGoodsGtgbrel(goodsGtgbrel);
		return status == 1 ? true : false;
	}
	public GoodsGtgbrel queryGoodsGtgbrelByGtgbId(Long gtgbId) {
		return goodsGtgbrelMapper.queryGoodsGtgbrelByGtgbId(gtgbId);
	}

	public Pagination<GoodsGtgbrelVo> queryGoodsGtgbrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGtgbrelVo> page = new Pagination<GoodsGtgbrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGtgbrelVo> list = goodsGtgbrelMapper.queryGoodsGtgbrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGtgbrelByGtgbId(String gtgbIds) {
		String [] gtgbId = gtgbIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		for(int i=0; i<gtgbId.length; i++){
			System.out.println(gtgbId[i]);
			Long gtgbIdLong = Long.parseLong(gtgbId[i]);
			GoodsGtgbrel goodsGtgbrel = goodsGtgbrelMapper.queryGoodsGtgbrelByGtgbId(gtgbIdLong);
			goodsGtgbrel.setIsDelete(1L);
			status += goodsGtgbrelMapper.updateGoodsGtgbrel(goodsGtgbrel);
		}
		return status == gtgbId.length ? true : false;
	}
}

