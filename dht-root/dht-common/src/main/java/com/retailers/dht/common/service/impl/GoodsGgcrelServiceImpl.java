
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.dao.GoodsMapper;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsComplimentary;
import com.retailers.dht.common.entity.GoodsGgcrel;
import com.retailers.dht.common.dao.GoodsGgcrelMapper;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsComplimentaryService;
import com.retailers.dht.common.service.GoodsGgcrelService;
import com.retailers.dht.common.vo.GoodsComplimentaryVo;
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
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsClassificationService goodsClassificationService;
	@Autowired
	private GoodsComplimentaryService goodsComplimentaryService;
	public boolean saveGoodsGgcrel(String gcIds,Long gid) {
		String[] gcIdsArr = gcIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gcIdsArr)){
			for (String gcId:gcIdsArr){
				Long gcIdLong = Long.parseLong(gcId);
				GoodsGgcrel goodsGgcrel = new GoodsGgcrel();
				goodsGgcrel.setIsDelete(0L);
				goodsGgcrel.setIsUse(1L);
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

	public boolean clearGoodsGgcrel(Long gcId){
		Map map = new HashMap();
		map.put("gcId",gcId);
		map.put("isDelete",0);
		Pagination<GoodsGgcrel> pagination = queryGoodsGgcrelList(map,1,1000);
		List<GoodsGgcrel> list = pagination.getData();
		int status = 0;
		if(!ObjectUtils.isEmpty(list)){
			for (GoodsGgcrel goodsGgcrel:list){
				goodsGgcrel.setIsDelete(1L);
				status += goodsGgcrelMapper.updateGoodsGgcrel(goodsGgcrel);
			}
		}

		return status==list.size() ? true:false;
	}

	public boolean saveGoodsGgcrelByGc(String gclassIds,Long gcId){
		String[] gclassIdsArr = gclassIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gclassIdsArr)){
			for (String gclassId:gclassIdsArr){
				Long gclassIdLong = Long.parseLong(gclassId);
				GoodsGgcrel goodsGgcrel = new GoodsGgcrel();
				goodsGgcrel.setIsDelete(0L);
				goodsGgcrel.setIsUse(1L);
				goodsGgcrel.setGcId(gcId);
				goodsGgcrel.setGclassId(gclassIdLong);
				status += goodsGgcrelMapper.saveGoodsGgcrel(goodsGgcrel);
			}
		}
		return status == gclassIdsArr.length ? true : false;
	}

	public boolean saveGoodsGgcrels(String gids,Long gcId){
		String[] gidsArr = gids.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gidsArr)){
			for (String gid:gidsArr){
				Long gidLong = Long.parseLong(gid);
				GoodsGgcrel goodsGgcrel = new GoodsGgcrel();
				goodsGgcrel.setIsDelete(0L);
				goodsGgcrel.setIsUse(1L);
				goodsGgcrel.setGcId(gcId);
				goodsGgcrel.setGid(gidLong);
				status += goodsGgcrelMapper.saveGoodsGgcrel(goodsGgcrel);
			}
		}
		return status == gidsArr.length ? true : false;
	}

	public List<GoodsGgcrelVo> queryGclassGoodsGgcrelLists(Long gid){
		Goods goods = goodsMapper.queryGoodsByGid(gid);
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

		List<GoodsGgcrelVo> list = goodsGgcrelMapper.queryGclassGoodsGgcrelLists(gclassIds);

		List<GoodsGgcrelVo> removeList = querydeletedgclass(gid);

		List<GoodsGgcrelVo> index = new ArrayList<GoodsGgcrelVo>();

		for (int i=0;i<list.size();i++){
			for(int j=0;j<removeList.size();j++){
				GoodsGgcrelVo g1 = list.get(i);
				GoodsGgcrelVo g2 = removeList.get(j);
				if(g1.getGcId()==g2.getGcId()){
					index.add(g1);
					break;
				}
			}
		}

		for(GoodsGgcrelVo g:index){
			list.remove(g);
		}

		return list;
	}

	public boolean deleteGclassGoodsGgcrelByGgcId(String gcIds,Long gid){
		String[] gcIdsArr = gcIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gcIdsArr)){
			for (String gcId:gcIdsArr){
				Long gcIdLong = Long.parseLong(gcId);
				GoodsGgcrel goodsGgcrel = new GoodsGgcrel();
				goodsGgcrel.setIsDelete(1L);
				goodsGgcrel.setIsUse(0L);
				goodsGgcrel.setGcId(gcIdLong);
				goodsGgcrel.setGid(gid);
				status += goodsGgcrelMapper.saveGoodsGgcrel(goodsGgcrel);
			}
		}

		return status == gcIdsArr.length ? true : false;
	}

	public List<GoodsComplimentaryVo> queryAllGoodsGgcrelLists(Long gid){
		Map params = new HashMap();
		params.put("isDelete",0L);
		params.put("isClass",2L);
		List<GoodsComplimentaryVo> list = goodsComplimentaryService.queryGoodsComplimentaryList(params,1,1000).getData();

		Map map = new HashMap();
		map.put("isDelete",1L);
		map.put("isUse",0L);
		map.put("gid",gid);
		List<GoodsGgcrelVo> goodsGgcrelVoList = queryGoodsGgcrelList(map,1,10000).getData();

		List<GoodsComplimentaryVo> indexList = new ArrayList<GoodsComplimentaryVo>();
		for(GoodsComplimentaryVo goodsComplimentaryVo:list){
			for(GoodsGgcrelVo goodsGgcrelVo:goodsGgcrelVoList){
				Long goodsComplimentaryVoId = goodsComplimentaryVo.getGcId();
				Long goodsGgcrelVoId = goodsGgcrelVo.getGcId();
				if(goodsComplimentaryVoId==goodsGgcrelVoId){
					indexList.add(goodsComplimentaryVo);
				}

			}
		}

		if(ObjectUtils.isNotEmpty(indexList)){
			for(GoodsComplimentaryVo goodsComplimentaryVo:indexList){
				list.remove(goodsComplimentaryVo);
			}
		}

		return list;
	}

	public List<GoodsGgcrelVo> querydeletedgclass(Long gid){
		Map map = new HashMap();
		map.put("gid",gid);
		map.put("isDelete",1L);
		map.put("isUse",0L);
		Pagination<GoodsGgcrelVo> pagination = queryGoodsGgcrelList(map,1,100);
		return pagination.getData();
	}
}

