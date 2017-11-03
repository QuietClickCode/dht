
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.SalePromotionMapper;
import com.retailers.dht.common.entity.SalePromotion;
import com.retailers.dht.common.service.SalePromotionService;
import com.retailers.dht.common.vo.FloorAdvertisingVo;
import com.retailers.dht.common.vo.SalePromotionVo;
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
	public boolean saveSalePromotion(SalePromotion salePromotion) {
		int status = salePromotionMapper.saveSalePromotion(salePromotion);
		return status == 1 ? true : false;
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
		int status = salePromotionMapper.deleteSalePromotionBySpId(spId);
		return status == 1 ? true : false;
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
}

