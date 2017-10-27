
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGgcouponrel;
import com.retailers.dht.common.dao.GoodsGgcouponrelMapper;
import com.retailers.dht.common.service.GoodsGgcouponrelService;
import com.retailers.dht.common.vo.GoodsGgcouponrelVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与优惠关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-26 14:57:28
 */
@Service("goodsggcouponrelService")
public class GoodsGgcouponrelServiceImpl implements GoodsGgcouponrelService {
	@Autowired
	private GoodsGgcouponrelMapper goodsGgcouponrelMapper;
	public boolean saveGoodsGgcouponrel(String gcIds,Long gid) {
		String[] gcIdsArr = gcIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gcIdsArr)){
			for (String gcId:gcIdsArr){
				Long gcIdLong = Long.parseLong(gcId);
				GoodsGgcouponrel goodsGgcouponrel = new GoodsGgcouponrel();
				goodsGgcouponrel.setIsDelete(0L);
				goodsGgcouponrel.setGcId(gcIdLong);
				goodsGgcouponrel.setGid(gid);
				status += goodsGgcouponrelMapper.saveGoodsGgcouponrel(goodsGgcouponrel);
			}
		}
		return status == gcIdsArr.length ? true : false;
	}
	public boolean updateGoodsGgcouponrel(GoodsGgcouponrel goodsGgcouponrel) {
		int status = goodsGgcouponrelMapper.updateGoodsGgcouponrel(goodsGgcouponrel);
		return status == 1 ? true : false;
	}
	public GoodsGgcouponrel queryGoodsGgcouponrelByGgcId(Long ggcId) {
		return goodsGgcouponrelMapper.queryGoodsGgcouponrelByGgcId(ggcId);
	}

	public Pagination<GoodsGgcouponrelVo> queryGoodsGgcouponrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGgcouponrelVo> page = new Pagination<GoodsGgcouponrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGgcouponrelVo> list = goodsGgcouponrelMapper.queryGoodsGgcouponrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGgcouponrelByGgcId(String ggcIds) {
		String[] ggcIdsArr = ggcIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(ggcIdsArr)){
			for (String ggcId:ggcIdsArr){
				Long ggcIdLong = Long.parseLong(ggcId);
				GoodsGgcouponrel goodsGgcouponrel = goodsGgcouponrelMapper.queryGoodsGgcouponrelByGgcId(ggcIdLong);
				goodsGgcouponrel.setIsDelete(1L);
				status += goodsGgcouponrelMapper.updateGoodsGgcouponrel(goodsGgcouponrel);
			}
		}
		return status == ggcIdsArr.length ? true : false;
	}
}

