
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.dao.GoodsMapper;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsGglrel;
import com.retailers.dht.common.dao.GoodsGglrelMapper;
import com.retailers.dht.common.entity.GoodsGglrelCopy;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsGglrelCopyService;
import com.retailers.dht.common.service.GoodsGglrelService;
import com.retailers.dht.common.vo.GoodsGglrelVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品与标签关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 14:28:44
 */
@Service("goodsgglrelService")
public class GoodsGglrelServiceImpl implements GoodsGglrelService {
	@Autowired
	private GoodsGglrelMapper goodsGglrelMapper;
	@Autowired
	private GoodsGglrelCopyService goodsGglrelCopyService;
	@Autowired
	private GoodsMapper goodsMapper;
	@Autowired
	private GoodsClassificationService goodsClassificationService;
	public boolean saveGoodsGglrel(String glIds,Long gid,Long uploadpersonId) {
		String[] glIdsArr = glIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(glIdsArr)){
			for (String glId:glIdsArr){
				Long glIdLong = Long.parseLong(glId);
				GoodsGglrel goodsGglrel = new GoodsGglrel();
				goodsGglrel.setIsDelete(0L);
				goodsGglrel.setGlId(glIdLong);
				goodsGglrel.setGid(gid);
				GoodsGglrelCopy g = new GoodsGglrelCopy();
				BeanUtils.copyProperties(goodsGglrel,g);
				g.setGglUploadpersonId(uploadpersonId);
				goodsGglrelCopyService.saveGoodsGglrelCopy(g);
				status += goodsGglrelMapper.saveGoodsGglrel(goodsGglrel);
			}
		}
		return status == glIdsArr.length ? true : false;
	}
	public boolean updateGoodsGglrel(GoodsGglrel goodsGglrel) {
		int status = goodsGglrelMapper.updateGoodsGglrel(goodsGglrel);
		return status == 1 ? true : false;
	}
	public GoodsGglrel queryGoodsGglrelByGglId(Long gglId) {
		return goodsGglrelMapper.queryGoodsGglrelByGglId(gglId);
	}

	public Pagination<GoodsGglrelVo> queryGoodsGglrelList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsGglrelVo> page = new Pagination<GoodsGglrelVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGglrelVo> list = goodsGglrelMapper.queryGoodsGglrelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGglrelByGglId(String gglIds,Long uploadpersonId) {
		String[] gglIdsArr = gglIds.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gglIdsArr)){
			for (String gglId:gglIdsArr){
				Long gglIdLong = Long.parseLong(gglId);
				GoodsGglrel goodsGglrel = goodsGglrelMapper.queryGoodsGglrelByGglId(gglIdLong);
				goodsGglrel.setIsDelete(1L);
				GoodsGglrelCopy g = new GoodsGglrelCopy();
				BeanUtils.copyProperties(goodsGglrel,g);
				g.setGglUploadpersonId(uploadpersonId);
				goodsGglrelCopyService.saveGoodsGglrelCopy(g);
				status += goodsGglrelMapper.updateGoodsGglrel(goodsGglrel);
			}
		}
		return status == gglIdsArr.length ? true : false;
	}

	public boolean saveGoodsGglrelByGids(String gids,Long glId,Long uploadpersonId) {
		String[] gidsArr = gids.replaceAll(","," ").trim().split(" ");
		int status = 0;
		if(!ObjectUtils.isEmpty(gidsArr)){
			for (String gid:gidsArr){
				Long gidLong = Long.parseLong(gid);
				GoodsGglrel goodsGglrel = new GoodsGglrel();
				goodsGglrel.setIsDelete(0L);
				goodsGglrel.setGlId(glId);
				goodsGglrel.setGid(gidLong);
				GoodsGglrelCopy g = new GoodsGglrelCopy();
				BeanUtils.copyProperties(goodsGglrel,g);
				g.setGglUploadpersonId(uploadpersonId);
				goodsGglrelCopyService.saveGoodsGglrelCopy(g);
				status += goodsGglrelMapper.saveGoodsGglrel(goodsGglrel);
			}
		}
		return status == gidsArr.length ? true : false;
	}

	public List<GoodsGglrelVo> queryGclassGoodsGglrelLists(Long gid){
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

		List<GoodsGglrelVo> list = goodsGglrelMapper.queryGclassGoodsGglrelLists(gclassIds);

		List<GoodsGglrel> removeList = removedeletedgglclass(gid);

		List<GoodsGglrelVo> index = new ArrayList<GoodsGglrelVo>();

		for (int i=0;i<list.size();i++){
			for(int j=0;j<removeList.size();j++){
				GoodsGglrelVo g1 = list.get(i);
				GoodsGglrel g2 = removeList.get(j);
				if(g1.getGlId()==g2.getGlId()){
					index.add(g1);
					break;
				}
			}
		}

		for(GoodsGglrelVo g:index){
			list.remove(g);
		}

		return list;
	}

	public boolean saveGclassGoodsGglrel(Long glId,String gclassIds){
		clearrels(glId);

		int status = 0;
		String[] gclassIdsArr = gclassIds.split(",");
		for(String gclassId:gclassIdsArr){
			Long gclassIdLong = Long.parseLong(gclassId);
			GoodsGglrel goodsGglrel = new GoodsGglrel();
			goodsGglrel.setIsDelete(0L);
			goodsGglrel.setGclassId(gclassIdLong);
			goodsGglrel.setGlId(glId);
			goodsGglrelMapper.saveGoodsGglrel(goodsGglrel);
		}

		return status==gclassIdsArr.length?true:false;
	}

	public void clearrels(Long glId){
		Map map = new HashMap();
		map.put("glId",glId);
		map.put("isDelete",0L);
		Pagination<GoodsGglrelVo> pagination = queryGoodsGglrelList(map,1,100);
		for(GoodsGglrel g:pagination.getData()){
			g.setIsDelete(1L);
			goodsGglrelMapper.updateGoodsGglrel(g);
		}
	}

	public List<GoodsGglrel> removedeletedgglclass(Long gid){
		Map map = new HashMap();
		map.put("isDelete",1L);
		map.put("isUse",0L);
		map.put("gid",gid);
		Pagination<GoodsGglrel> pagination = queryGoodsGglrelList(map,1,100);

		return pagination.getData();
	}

	public boolean removeGclassGoodsGglrel(Long gid,String glIds){
		int status = 0;
		String[] glIdsArr = glIds.split(",");
		for(String glId:glIdsArr){
			Long glIdLong = Long.parseLong(glId);
			GoodsGglrel goodsGglrel = new GoodsGglrel();
			goodsGglrel.setGlId(glIdLong);
			goodsGglrel.setIsUse(0L);
			goodsGglrel.setIsDelete(1L);
			goodsGglrel.setGid(gid);
			status += goodsGglrelMapper.saveGoodsGglrel(goodsGglrel);
		}

		return status==glIdsArr.length?true:false;
	}
}

