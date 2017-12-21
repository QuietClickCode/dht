
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.SalePromotionMapper;
import com.retailers.dht.common.entity.GoodsGdsprel;
import com.retailers.dht.common.entity.SalePromotion;
import com.retailers.dht.common.service.GoodsGdsprelService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.service.SalePromotionService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.common.vo.SalePromotionVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：商品促销表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-11-02 16:28:41
 */
@Service("salepromotionService")
public class SalePromotionServiceImpl implements SalePromotionService {
	@Autowired
	private SalePromotionMapper salePromotionMapper;
	@Autowired
	private GoodsGdsprelService goodsGdsprelService;
	@Autowired
	private GoodsService goodsService;
	public SalePromotion saveSalePromotion(SalePromotion salePromotion) {
		int status = salePromotionMapper.saveSalePromotion(salePromotion);
		System.out.println("impl");
		return status == 1 ? salePromotion : null;
	}
	public boolean updateSalePromotion(SalePromotion salePromotion) {
		int status = salePromotionMapper.updateSalePromotion(salePromotion);
		return status == 1 ? true : false;
	}
	public SalePromotion querySalePromotionBySpId(Long spId) {
		return salePromotionMapper.querySalePromotionBySpId(spId);
	}

	public Pagination<SalePromotionVo> querySalePromotionList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<SalePromotionVo> page = new Pagination<SalePromotionVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SalePromotionVo> list = salePromotionMapper.querySalePromotionList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteSalePromotionBySpId(Long spId) {
		SalePromotion promotion = salePromotionMapper.querySalePromotionBySpId(spId);
		promotion.setIsDelete(1L);
		int status = salePromotionMapper.updateSalePromotion(promotion);
		if(status==1){
			cleargdsprel(spId);
		}
		return status == 1 ? true : false;
	}

	public void cleargdsprel(Long spId){
		Map map = new HashMap();
		map.put("spId",spId);
		List<GoodsGdsprel> list = goodsGdsprelService.queryGoodsGdsprelList(map,1,100).getData();
		if(!ObjectUtils.isEmpty(list)){
			for(GoodsGdsprel goodsGdsprel:list){
				goodsGdsprelService.deleteGoodsGdsprelByGdspId(goodsGdsprel.getGdspId());
			}
		}
	}

	public List<SalePromotionVo> querySalePromotionTree(List<SalePromotionVo> promotionVos) {
		List<SalePromotionVo> rtnList=new ArrayList<SalePromotionVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryChildNode(promotionVos,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllFloors(null,child,alloShow);
		for(SalePromotionVo vo:promotionVos){
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


	private void queryChildNode(List<SalePromotionVo> list, Map<Long,Map<Long,Long>> child){
		for(SalePromotionVo vo:list){
			Long parentId=vo.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getSpId(),vo.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(vo.getSpId(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}

	public Pagination<SalePromotionVo> querySalePromotionListWeb(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<SalePromotionVo> page = new Pagination<SalePromotionVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SalePromotionVo> list = salePromotionMapper.querySalePromotionListWeb(page);
		for(SalePromotionVo salePromotionVo:list){
			salePromotionVo.setImgurl(AttachmentConstant.IMAGE_SHOW_URL+salePromotionVo.getImgurl());
		}
		page.setData(list);
		return page;
	}

	public List<GoodsVo> queryHasNoSpGoods(String gname,Long parentId) {
		Map params = new HashMap();
		params.put("isChecked",1L);
		params.put("gname",gname);
		params.put("isDelete",0L);
		List<GoodsVo> list = goodsService.queryGoodsList(params,1,10).getData();

		Map spparams = new HashMap();
		spparams.put("parentId",parentId);
		spparams.put("isDelete",0L);
		List<SalePromotionVo> spList = querySalePromotionList(spparams,1,100).getData();

		List<GoodsVo> removeList = new ArrayList<GoodsVo>();
		if(!ObjectUtils.isEmpty(spList)){
			for(GoodsVo goodsVo:list){
				for(SalePromotionVo salePromotionVo:spList){
					Long gid = goodsVo.getGid();
					Long goodsId = salePromotionVo.getGoodsId();
					if(gid.equals(goodsId)){
						removeList.add(goodsVo);
					}
				}
			}
		}

		list.removeAll(removeList);

		return list;
	}

	public Pagination<SalePromotionVo> queryNextSalePromotionLists(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<SalePromotionVo> page = new Pagination<SalePromotionVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<SalePromotionVo> list = salePromotionMapper.queryNextSalePromotionLists(page);
		for(SalePromotionVo salePromotionVo:list){
			salePromotionVo.setImgurl(AttachmentConstant.IMAGE_SHOW_URL+salePromotionVo.getImgurl());
		}
		page.setData(list);
		return page;
	}

}

