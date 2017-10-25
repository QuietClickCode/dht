
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGgclrel;
import com.retailers.dht.common.dao.GoodsGgclrelMapper;
import com.retailers.dht.common.service.GoodsGgclrelService;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与商品评论关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 13:55:59
 */
@Service("goodsggclrelService")
public class GoodsGgclrelServiceImpl implements GoodsGgclrelService {
	@Autowired
	private GoodsGgclrelMapper goodsGgclrelMapper;
	public boolean saveGoodsGgclrel(String gclIds,Long gid,Long level,Long uploadpersonId) {
		int status = 0;
		String[] gclIdsArr = gclIds.replaceAll(","," ").trim().split(" ");
		if(!ObjectUtils.isEmpty(gclIdsArr)){
			for (String gclId:gclIdsArr){
				Long gclIdLong = Long.parseLong(gclId);
				GoodsGgclrel goodsGgclrel = new GoodsGgclrel();
				goodsGgclrel.setIsDelete(0L);
				goodsGgclrel.setGclId(gclIdLong);
				goodsGgclrel.setGid(gid);
				goodsGgclrel.setGclLevel(level);
				status += goodsGgclrelMapper.saveGoodsGgclrel(goodsGgclrel);
			}
		}
		return status == gclIdsArr.length ? true : false;

	}
	public boolean updateGoodsGgclrel(GoodsGgclrel goodsGgclrel) {
		int status = goodsGgclrelMapper.updateGoodsGgclrel(goodsGgclrel);
		return status == 1 ? true : false;
	}
	public GoodsGgclrel queryGoodsGgclrelByGgclId(Long ggclId) {
		return goodsGgclrelMapper.queryGoodsGgclrelByGgclId(ggclId);
	}

	public Pagination<GoodsGgclrelVo> queryGoodsGgclrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGgclrelVo> page = new Pagination<GoodsGgclrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGgclrelVo> list = goodsGgclrelMapper.queryGoodsGgclrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGgclrelByGgclId(String ggclIds,Long uplaodpersonId) {
		String[] ggclIdsArr = ggclIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(ggclIdsArr)){
			for (String ggclId:ggclIdsArr){
				Long ggclIdLong = Long.parseLong(ggclId);
				GoodsGgclrel goodsGgclrel = goodsGgclrelMapper.queryGoodsGgclrelByGgclId(ggclIdLong);
				goodsGgclrel.setIsDelete(1L);
				status += goodsGgclrelMapper.updateGoodsGgclrel(goodsGgclrel);
			}
		}
		return status == ggclIdsArr.length ? true : false;
	}
}

