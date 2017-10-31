
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.GoodsComplimentary;
import com.retailers.dht.common.dao.GoodsComplimentaryMapper;
import com.retailers.dht.common.service.GoodsComplimentaryService;
import com.retailers.dht.common.service.GoodsGgcrelService;
import com.retailers.dht.common.vo.GoodsComplimentaryVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：赠品表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-27 14:58:13
 */
@Service("goodscomplimentaryService")
public class GoodsComplimentaryServiceImpl implements GoodsComplimentaryService {
	@Autowired
	private GoodsComplimentaryMapper goodsComplimentaryMapper;
	@Autowired
	private GoodsGgcrelService goodsGgcrelService;
	public boolean saveGoodsComplimentary(GoodsComplimentary goodsComplimentary) {
		int status = goodsComplimentaryMapper.saveGoodsComplimentary(goodsComplimentary);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsComplimentary(GoodsComplimentary goodsComplimentary) {
		GoodsComplimentary gc = goodsComplimentaryMapper.queryGoodsComplimentaryByGcId(goodsComplimentary.getGcId());
		if(gc.getIsClass()!=goodsComplimentary.getIsClass()){
			goodsGgcrelService.clearGoodsGgcrel(goodsComplimentary.getGcId());
		}
		int status = goodsComplimentaryMapper.updateGoodsComplimentary(goodsComplimentary);
		return status == 1 ? true : false;
	}
	public GoodsComplimentary queryGoodsComplimentaryByGcId(Long gcId) {
		return goodsComplimentaryMapper.queryGoodsComplimentaryByGcId(gcId);
	}

	public Pagination<GoodsComplimentaryVo> queryGoodsComplimentaryList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsComplimentaryVo> page = new Pagination<GoodsComplimentaryVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsComplimentaryVo> list = goodsComplimentaryMapper.queryGoodsComplimentaryList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsComplimentaryByGcId(Long gcId) {
		GoodsComplimentary goodsComplimentary = goodsComplimentaryMapper.queryGoodsComplimentaryByGcId(gcId);
		goodsComplimentary.setIsDelete(1L);
		int status = goodsComplimentaryMapper.updateGoodsComplimentary(goodsComplimentary);
		return status == 1 ? true : false;
	}
}

