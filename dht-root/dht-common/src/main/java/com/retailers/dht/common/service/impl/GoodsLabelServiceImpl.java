
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.GoodsGglrel;
import com.retailers.dht.common.entity.GoodsLabel;
import com.retailers.dht.common.dao.GoodsLabelMapper;
import com.retailers.dht.common.service.GoodsGglrelService;
import com.retailers.dht.common.service.GoodsLabelService;
import com.retailers.dht.common.vo.FloorAdvertisingVo;
import com.retailers.dht.common.vo.GoodsLabelVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品标签表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-09 13:44:22
 */
@Service("goodslabelService")
public class GoodsLabelServiceImpl implements GoodsLabelService {
	@Autowired
	private GoodsLabelMapper goodsLabelMapper;
	@Autowired
	private GoodsGglrelService goodsGglrelService;
	public boolean saveGoodsLabel(GoodsLabel goodsLabel) {
		int status = goodsLabelMapper.saveGoodsLabel(goodsLabel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsLabel(GoodsLabel goodsLabel) {
		GoodsLabel g = goodsLabelMapper.queryGoodsLabelByGlId(goodsLabel.getGlId());
		Long isGoodslabel = goodsLabel.getIsGoodslabel();
		if(g.getIsGoodslabel()!=isGoodslabel){
			goodsGglrelService.clearrels(goodsLabel.getGlId());
		}
		int status = goodsLabelMapper.updateGoodsLabel(goodsLabel);

		return status == 1 ? true : false;
	}
	public GoodsLabel queryGoodsLabelByGlId(Long glId) {
		return goodsLabelMapper.queryGoodsLabelByGlId(glId);
	}

	public Pagination<GoodsLabel> queryGoodsLabelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsLabel> page = new Pagination<GoodsLabel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsLabel> list = goodsLabelMapper.queryGoodsLabelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsLabelByGlId(Long glId) {
		GoodsLabel goodsLabel = goodsLabelMapper.queryGoodsLabelByGlId(glId);
		goodsLabel.setIsDelete(1L);
		int status = goodsLabelMapper.updateGoodsLabel(goodsLabel);
		return status == 1 ? true : false;
	}

	public List<GoodsLabelVo> queryGoodsLabelAdvertisingTree(){
		List<GoodsLabelVo> advertisingVos = goodsLabelMapper.queryGoodslabelAdvertisingList();
		for(GoodsLabelVo vo:advertisingVos)
			vo.setGlId(-vo.getGlId()*100000);
		List<GoodsLabelVo> advertisingVoList = goodsLabelMapper.queryGoodsLabelAdvertisingVo();
		for(GoodsLabelVo vo:advertisingVoList){
			System.out.println("parentid==================>"+vo.getParentId());
			vo.setParentId(vo.getParentId()*100000);
			vo.setImgUrl(AttachmentConstant.IMAGE_SHOW_URL+vo.getImgUrl());
		}
		advertisingVos.addAll(advertisingVoList);
		List<GoodsLabelVo> rtnList=new ArrayList<GoodsLabelVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(advertisingVos,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllFloors(null,child,alloShow);
		for(GoodsLabelVo vo:advertisingVos){
			if(ObjectUtils.isEmpty(vo.getParentId())){
				vo.setLevel(1l);
				rtnList.add(vo);
			}else{
				if(alloShow.containsKey(vo.getParentId())){
					rtnList.add(vo);
				}
				vo.setLevel(2l);
			}
		}
		return rtnList;
	}

	private void queryChildNode(List<GoodsLabelVo> list, Map<Long,Map<Long,Long>> child){
		for(GoodsLabelVo vo:list){
			Long parentId=vo.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getGlId(),vo.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(vo.getGlId(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}

	private void queryAllFloors(Long parentId,Map<Long,Map<Long,Long>> map,Map<Long,Long>allowMap){
		if(ObjectUtils.isEmpty(parentId)){
			parentId=-1l;
		}
		Map<Long,Long> child=map.get(parentId);
		if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
			for(Long key:child.keySet()){
				allowMap.put(key,key);
				if(map.containsKey(key)){
					queryAllFloors(key,map,allowMap);
				}
			}
		}
	}

}

