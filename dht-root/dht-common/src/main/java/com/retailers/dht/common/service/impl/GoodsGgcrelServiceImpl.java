
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGgcrel;
import com.retailers.dht.common.dao.GoodsGgcrelMapper;
import com.retailers.dht.common.service.GoodsGgcrelService;
import com.retailers.dht.common.vo.GoodsGgcrelVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：赠品与商品关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-27 17:14:07
 */
@Service("goodsggcrelService")
public class GoodsGgcrelServiceImpl implements GoodsGgcrelService {
	@Autowired
	private GoodsGgcrelMapper goodsGgcrelMapper;
	public boolean saveGoodsGgcrel(String gcIds,Long gid) {
		String[] gcIdsArr = gcIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gcIdsArr)){
			for (String gcId:gcIdsArr){
				Long gcIdLong = Long.parseLong(gcId);
				GoodsGgcrel goodsGgcrel = new GoodsGgcrel();
				goodsGgcrel.setIsDelete(0L);
				goodsGgcrel.setGcId(gcIdLong);
				goodsGgcrel.setGid(gid);
				status += goodsGgcrelMapper.saveGoodsGgcrel(goodsGgcrel);
			}
		}
		return status == gcIdsArr.length ? true : false;
	}
	public boolean updateGoodsGgcrel(GoodsGgcrel goodsGgcrel) {
		int status = goodsGgcrelMapper.updateGoodsGgcrel(goodsGgcrel);
		return status == 1 ? true : false;
	}
	public GoodsGgcrel queryGoodsGgcrelByGgcId(Long ggcId) {
		return goodsGgcrelMapper.queryGoodsGgcrelByGgcId(ggcId);
	}

	public Pagination<GoodsGgcrelVo> queryGoodsGgcrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGgcrelVo> page = new Pagination<GoodsGgcrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGgcrelVo> list = goodsGgcrelMapper.queryGoodsGgcrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGgcrelByGgcId(String ggcIds) {
		String[] ggcIdsArr = ggcIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(ggcIdsArr)){
			for (String ggcId:ggcIdsArr){
				Long ggcIdLong = Long.parseLong(ggcId);
				GoodsGgcrel goodsGgcrel = goodsGgcrelMapper.queryGoodsGgcrelByGgcId(ggcIdLong);
				goodsGgcrel.setIsDelete(1L);
				status += goodsGgcrelMapper.updateGoodsGgcrel(goodsGgcrel);
			}
		}
		return status == ggcIdsArr.length ? true : false;
	}
}

