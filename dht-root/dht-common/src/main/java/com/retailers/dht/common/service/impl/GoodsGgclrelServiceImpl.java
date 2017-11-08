
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.GoodsGgclrelMapper;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsGgclrel;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsCommentlabelService;
import com.retailers.dht.common.service.GoodsGgclrelService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsCommentlabelVo;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：商品与评论关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-06 11:17:50
 */
@Service("goodsggclrelService")
public class GoodsGgclrelServiceImpl implements GoodsGgclrelService {
	@Autowired
	private GoodsGgclrelMapper goodsGgclrelMapper;
	@Autowired
	private GoodsCommentlabelService goodsCommentlabelService;
	@Autowired
	private GoodsService goodsService;
	@Autowired
	private GoodsClassificationService goodsClassificationService;
	public boolean saveGoodsGgclrel(String gclIds,Long gid) {
		String[] gclIdsArr = gclIds.split(",");
		int status = 0;
		GoodsGgclrel goodsGgclrel = new GoodsGgclrel();
		for(int i=0; i<gclIdsArr.length; i++){
			Long gclIdLong = Long.parseLong(gclIdsArr[i]);
			goodsGgclrel.setIsDelete(0L);
			goodsGgclrel.setGid(gid);
			goodsGgclrel.setGclId(gclIdLong);
			status += goodsGgclrelMapper.saveGoodsGgclrel(goodsGgclrel);
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

	public List<GoodsGgclrelVo> queryGoodsGgclrelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGgclrel> page = new Pagination<GoodsGgclrel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGgclrel> list = goodsGgclrelMapper.queryGoodsGgclrelList(page);

		List<GoodsGgclrelVo> goodsGgcrelVoList = new ArrayList<GoodsGgclrelVo>();
		if(!ObjectUtils.isEmpty(list)){
			for(GoodsGgclrel goodsGgclrel:list){
				GoodsGgclrelVo goodsGgclrelVo = new GoodsGgclrelVo();
				BeanUtils.copyProperties(goodsGgclrel,goodsGgclrelVo);
				if(!ObjectUtils.isEmpty(goodsGgclrel.getGid())){
					Goods goods = goodsService.queryGoodsByGid(goodsGgclrel.getGid());
					goodsGgclrelVo.setGname(goods.getGname());
				}
				Long gclassId = goodsGgclrelVo.getGclassId();
				if(!ObjectUtils.isEmpty(gclassId)){
					goodsGgclrelVo.setGclassName(goodsClassificationService.queryGoodsClassificationByGgId(gclassId).getGgName());
				}
				goodsGgcrelVoList.add(goodsGgclrelVo);
			}
		}
		page.setData(list);
		return goodsGgcrelVoList;
	}
	public boolean deleteGoodsGgclrelByGgclId(String ggclIds) {
		String[] ggclIdsArr = ggclIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		for (int i=0; i<ggclIdsArr.length; i++){
			Long ggcllLong = Long.parseLong(ggclIdsArr[i]);
			GoodsGgclrel goodsGgclrel = goodsGgclrelMapper.queryGoodsGgclrelByGgclId(ggcllLong);
			goodsGgclrel.setIsDelete(1L);
			status += goodsGgclrelMapper.updateGoodsGgclrel(goodsGgclrel);
		}
		return status == ggclIdsArr.length ? true : false;
	}

	public List<GoodsGgclrelVo> queryMyGoodsGgclrelList(Map map){
		List<GoodsGgclrelVo> paginationList = queryGoodsGgclrelList(map,1,100);
		List<GoodsGgclrelVo> list = new ArrayList<GoodsGgclrelVo>();
		for(GoodsGgclrelVo goodsGgclrel:paginationList){
			Map m = new HashMap();
			m.put("gclId",goodsGgclrel.getGclId());
			m.put("isDelete",0L);
			Pagination<GoodsCommentlabelVo> p = goodsCommentlabelService.queryGoodsCommentlabelList(m,1,2);
			GoodsGgclrelVo goodsGgclrelVo = new GoodsGgclrelVo();
			if(!ObjectUtils.isEmpty(p.getData())){
				BeanUtils.copyProperties(p.getData().get(0),goodsGgclrelVo);
				goodsGgclrelVo.setGgclId(goodsGgclrel.getGgclId());
				list.add(goodsGgclrelVo);
			}
		}

		return list;
	}

	public boolean saveGoodsGgclrelByGids(String gids,Long gclId){
		String[] gidsArr = gids.split(",");
		int status = 0;
		GoodsGgclrel goodsGgclrel = new GoodsGgclrel();
		for(int i=0; i<gidsArr.length; i++){
			Long giddLong = Long.parseLong(gidsArr[i]);
			goodsGgclrel.setIsDelete(0L);
			goodsGgclrel.setGid(giddLong);
			goodsGgclrel.setGclId(gclId);
			status += goodsGgclrelMapper.saveGoodsGgclrel(goodsGgclrel);
		}
		return status == gidsArr.length ? true : false;
	}

	public boolean addGoodsGgcrelByGclass(String gclassIds,Long gclId){
		goodsCommentlabelService.clearggclrel(gclId);
		String[] gclassIdsArr = gclassIds.split(",");
		int status = 0;
		GoodsGgclrel goodsGgclrel = new GoodsGgclrel();
		for(int i=0; i<gclassIdsArr.length; i++){
			Long gclassIdLong = Long.parseLong(gclassIdsArr[i]);
			goodsGgclrel.setIsDelete(0L);
			goodsGgclrel.setGclassId(gclassIdLong);
			goodsGgclrel.setGclId(gclId);
			status += goodsGgclrelMapper.saveGoodsGgclrel(goodsGgclrel);
		}
		return status == gclassIdsArr.length ? true : false;
	}

	public List<GoodsGgclrelVo> queryGclassGoodsGgclrelLists(Long gid){
		Goods goods = goodsService.queryGoodsByGid(gid);
		String gclassIds = "";
		GoodsClassification goodsClassification1 = null;
		GoodsClassification goodsClassification2 = null;
		GoodsClassification goodsClassification3 = null;
		goodsClassification1 = goodsClassificationService.queryGoodsClassificationByGgId(goods.getGclassification());
		if(!ObjectUtils.isEmpty(goodsClassification1.getParentId())){
			goodsClassification2 = goodsClassificationService.queryGoodsClassificationByGgId(goodsClassification1.getParentId());
			if(!ObjectUtils.isEmpty(goodsClassification2.getParentId())){
				goodsClassification3 = goodsClassificationService.queryGoodsClassificationByGgId(goodsClassification2.getParentId());
			}
		}

		if(!ObjectUtils.isEmpty(goodsClassification1)){
			gclassIds += "," + goodsClassification1.getGgId();
		}
		if(!ObjectUtils.isEmpty(goodsClassification2)){
			gclassIds += "," + goodsClassification2.getGgId();
		}
		if(!ObjectUtils.isEmpty(goodsClassification3)){
			gclassIds += "," + goodsClassification3.getGgId();
		}
		gclassIds = gclassIds.substring(1);
		List<GoodsGgclrel> list = goodsGgclrelMapper.queryGclassGoodsGgclrelLists(gclassIds);
		List<GoodsGgclrel> removeList = deletegoodsclass(gid);
		List<GoodsGgclrel> index = new ArrayList<GoodsGgclrel>();
		for(GoodsGgclrel g1:list){
			for(GoodsGgclrel g2:removeList){
				if(g1.getGclId()==g2.getGclId()){
					index.add(g1);
					break;
				}
			}
		}
		for(GoodsGgclrel g:index){
			list.remove(g);
		}

		List<GoodsGgclrelVo> returnList = new ArrayList<GoodsGgclrelVo>();
		for(GoodsGgclrel g:list){
			Map map = new HashMap();
			map.put("isDelete",0L);
			map.put("gclId",g.getGclId());
			Pagination<GoodsGgclrelVo> p = goodsCommentlabelService.queryGoodsCommentlabelList(map,1,2);
			if(!ObjectUtils.isEmpty(p.getData())){
				returnList.add(p.getData().get(0));
			}
		}


		return returnList;
	}

	public List<GoodsGgclrel> deletegoodsclass(Long gid){
		Map map = new HashMap();
		map.put("isDelete",1L);
		map.put("isUse",0L);
		map.put("gid",gid);
		Pagination<GoodsGgclrel> p = new Pagination<GoodsGgclrel>();
		p.setParams(map);
		p.setPageNo(1);
		p.setPageSize(100);
		List<GoodsGgclrel> list = goodsGgclrelMapper.queryGoodsGgclrelList(p);
		return list;
	}

	public boolean saveDeleteGt(String gclIds,Long gid){
		String[] gclIdsArr = gclIds.split(",");
		int status = 0;
		GoodsGgclrel goodsGgclrel = new GoodsGgclrel();
		for(int i=0; i<gclIdsArr.length; i++){
			Long gclIdLong = Long.parseLong(gclIdsArr[i]);
			goodsGgclrel.setIsDelete(1L);
			goodsGgclrel.setIsUse(0L);
			goodsGgclrel.setGid(gid);
			goodsGgclrel.setGclId(gclIdLong);
			status += goodsGgclrelMapper.saveGoodsGgclrel(goodsGgclrel);
		}
		return status == gclIdsArr.length ? true : false;
	}
}

