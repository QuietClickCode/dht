
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.dao.GoodsGgclrelMapper;
import com.retailers.dht.common.entity.GoodsCommentlabel;
import com.retailers.dht.common.dao.GoodsCommentlabelMapper;
import com.retailers.dht.common.entity.GoodsGgclrel;
import com.retailers.dht.common.service.GoodsCommentlabelService;
import com.retailers.dht.common.service.GoodsGgclrelService;
import com.retailers.dht.common.service.GoodsGgcrelService;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品评论表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-06 11:18:22
 */
@Service("goodscommentlabelService")
public class GoodsCommentlabelServiceImpl implements GoodsCommentlabelService {
	@Autowired
	private GoodsCommentlabelMapper goodsCommentlabelMapper;
	@Autowired
	private GoodsGgclrelService goodsGgclrelService;
	@Autowired
	private GoodsGgclrelMapper goodsGgclrelMapper;
	public GoodsCommentlabel saveGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel) {
		int status = goodsCommentlabelMapper.saveGoodsCommentlabel(goodsCommentlabel);
		return status == 1 ? goodsCommentlabel : null;
	}
	public boolean updateGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel) {
		Long isClass = goodsCommentlabelMapper.queryGoodsCommentlabelByGclId(goodsCommentlabel.getGclId()).getIsClass();
		if(isClass!=goodsCommentlabel.getIsClass()){
			clearggclrel(goodsCommentlabel.getGclId());
		}
		int status = goodsCommentlabelMapper.updateGoodsCommentlabel(goodsCommentlabel);
		return status == 1 ? true : false;
	}
	public GoodsCommentlabel queryGoodsCommentlabelByGclId(Long gclId) {
		return goodsCommentlabelMapper.queryGoodsCommentlabelByGclId(gclId);
	}

	public Pagination<GoodsCommentlabel> queryGoodsCommentlabelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsCommentlabel> page = new Pagination<GoodsCommentlabel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsCommentlabel> list = goodsCommentlabelMapper.queryGoodsCommentlabelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsCommentlabelByGclId(Long gclId) {
		GoodsCommentlabel goodsCommentlabel = goodsCommentlabelMapper.queryGoodsCommentlabelByGclId(gclId);
		goodsCommentlabel.setIsDelete(1L);
		int status = goodsCommentlabelMapper.updateGoodsCommentlabel(goodsCommentlabel);
		return status == 1 ? true : false;
	}

	public void clearggclrel(Long gclId){
		Map map = new HashMap();
		map.put("gclId",gclId);
		map.put("isDelete",0L);
		Pagination<GoodsGgclrel> pagination = new Pagination<GoodsGgclrel>();
		pagination.setPageNo(1);
		pagination.setPageSize(100);
		pagination.setParams(map);
		List<GoodsGgclrel> list = goodsGgclrelMapper.queryGoodsGgclrelList(pagination);
		for(GoodsGgclrel goodsGgclrel:list){
			GoodsGgclrel g = goodsGgclrelMapper.queryGoodsGgclrelByGgclId(goodsGgclrel.getGgclId());
			g.setIsDelete(1L);
			goodsGgclrelMapper.updateGoodsGgclrel(g);
		}

	}
}

