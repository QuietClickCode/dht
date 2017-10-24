
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsGtgsrel;
import com.retailers.dht.common.dao.GoodsGtgsrelMapper;
import com.retailers.dht.common.service.GoodsGtgsrelService;
import com.retailers.dht.common.vo.GoodsGtgsrelVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：大类与规格关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-19 11:57:43
 */
@Service("goodsgtgsrelService")
public class GoodsGtgsrelServiceImpl implements GoodsGtgsrelService {
	@Autowired
	private GoodsGtgsrelMapper goodsGtgsrelMapper;
	public boolean saveGoodsGtgsrel(String gsIds, Long gtId) {
		int status = 0;
		GoodsGtgsrel goodsGtgsrel = new GoodsGtgsrel();
		gsIds = gsIds.replaceAll(","," ");
		String[] gsIdArr = gsIds.trim().split(" ");
		for(int i=0; i<gsIdArr.length; i++){
			Long gsId = Long.parseLong(gsIdArr[i]);
			goodsGtgsrel.setIsDelete(0L);
			goodsGtgsrel.setGtId(gtId);
			goodsGtgsrel.setGsId(gsId);
			status += goodsGtgsrelMapper.saveGoodsGtgsrel(goodsGtgsrel);
		}
		return status == gsIdArr.length ? true : false;
	}
	public boolean updateGoodsGtgsrel(GoodsGtgsrel goodsGtgsrel) {
		int status = goodsGtgsrelMapper.updateGoodsGtgsrel(goodsGtgsrel);
		return status == 1 ? true : false;
	}
	public GoodsGtgsrel queryGoodsGtgsrelByGtgsId(Long gtgsId) {
		return goodsGtgsrelMapper.queryGoodsGtgsrelByGtgsId(gtgsId);
	}

	public Pagination<GoodsGtgsrelVo> queryGoodsGtgsrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGtgsrelVo> page = new Pagination<GoodsGtgsrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGtgsrelVo> list = goodsGtgsrelMapper.queryGoodsGtgsrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGtgsrelByGtgsId(String gtgsIds) {
		String[] gtgsIdArr = gtgsIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		for (int i=0; i<gtgsIdArr.length; i++){
			Long gtgsLong = Long.parseLong(gtgsIdArr[i]);
			status += goodsGtgsrelMapper.deleteGoodsGtgsrelByGtgsId(gtgsLong);
		}
		return status == gtgsIdArr.length ? true : false;
	}
}

