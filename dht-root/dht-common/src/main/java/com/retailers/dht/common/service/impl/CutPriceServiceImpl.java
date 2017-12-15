
package com.retailers.dht.common.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.dao.CutPriceMapper;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.service.CutPriceService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.CutPriceVo;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.tools.utils.ObjectUtils;
import com.sun.jdi.connect.AttachingConnector;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：砍价表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:14:43
 */
@Service("cutpriceService")
public class CutPriceServiceImpl implements CutPriceService {
	@Autowired
	private CutPriceMapper cutPriceMapper;
	@Autowired
	private GoodsService goodsService;
	public CutPrice saveCutPrice(CutPrice cutPrice) {
		int status = cutPriceMapper.saveCutPrice(cutPrice);
		return status == 1 ? cutPrice : null;
	}
	public boolean updateCutPrice(CutPrice cutPrice) {
		int status = cutPriceMapper.updateCutPrice(cutPrice);
		return status == 1 ? true : false;
	}
	public CutPrice queryCutPriceByCpId(Long cpId) {
		return cutPriceMapper.queryCutPriceByCpId(cpId);
	}

	public Pagination<CutPrice> queryCutPriceList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<CutPrice> page = new Pagination<CutPrice>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CutPrice> list = cutPriceMapper.queryCutPriceList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCutPriceByCpId(Long cpId) {
		CutPrice cutPrice = queryCutPriceByCpId(cpId);
		cutPrice.setIsDelete(1L);
		int status = cutPriceMapper.updateCutPrice(cutPrice);
		return status == 1 ? true : false;
	}
	public List<CutPriceVo> queryCutPriceTree(){
		List<CutPriceVo> CutPriceList = cutPriceMapper.queryCutPriceTree();
		List<CutPriceVo> rntList = new ArrayList<CutPriceVo>();
		Map<Long, Map<Long, Long>> child = new HashMap<Long, Map<Long, Long>>();
		queryChildNode(CutPriceList, child);
		Map<Long, Long> alloShow = new HashMap<Long, Long>();
		queryAllowCutPrice(null, child, alloShow);
		for (CutPriceVo vo : CutPriceList) {
			if (ObjectUtils.isEmpty(vo.getParentId())) {
				vo.setLevel(1l);
				rntList.add(vo);
			} else {
				if (alloShow.containsKey(vo.getParentId())) {
					rntList.add(vo);
				}
				vo.setLevel(2l);
			}
		}

		return rntList;
	}


	private void queryChildNode(List<CutPriceVo> list,Map<Long,Map<Long,Long>> child){
		for(CutPriceVo vo:list){
			Long parentId=vo.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getCpId(),vo.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(vo.getCpId(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}

	private void queryAllowCutPrice(Long ggManager,Map<Long,Map<Long,Long>> map,Map<Long,Long>allowMap){
		if(ObjectUtils.isEmpty(ggManager)){
			ggManager=-1l;
		}
		Map<Long,Long> child=map.get(ggManager);
		if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
			for(Long key:child.keySet()){
				allowMap.put(key,key);
				if(map.containsKey(key)){
					queryAllowCutPrice(key,map,allowMap);
				}
			}
		}
	}

	public List<Goods> queryHasNoSpGoods(String gname,Long parentId){
		Map params = new HashMap();
		params.put("gname",gname);
		params.put("parentId",parentId);
		Pagination<Goods> page = new Pagination<Goods>();
		page.setPageNo(1);
		page.setPageSize(10);
		page.setParams(params);
		List<Goods> list = cutPriceMapper.queryHasNoSpGoods(page);
		return list;
	}
	public List<CutPriceVo> queryCutPriceListsByGid(Map params){
		List<CutPriceVo> list = cutPriceMapper.queryCutPriceListsByGid(params);
		return list;
	}
	public Pagination<CutPriceVo> queryCutPriceGoodsList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<CutPriceVo> page = new Pagination<CutPriceVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CutPriceVo> list = cutPriceMapper.queryCutPriceGoodsList(page);
		for(CutPriceVo cutPriceVo:list){
			cutPriceVo.setImgurl(AttachmentConstant.IMAGE_SHOW_URL+cutPriceVo.getImgurl());
		}
		page.setData(list);
		return page;
	}
	public Pagination<CutPriceVo> queryNextCutPriceGoodsList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<CutPriceVo> page = new Pagination<CutPriceVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CutPriceVo> list = cutPriceMapper.queryNextCutPriceGoodsList(page);
		for(CutPriceVo cutPriceVo:list){
			cutPriceVo.setImgurl(AttachmentConstant.IMAGE_SHOW_URL+cutPriceVo.getImgurl());
		}
		page.setData(list);
		return page;
	}
}

