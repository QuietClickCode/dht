
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.constant.AttachmentConstant;
import com.retailers.dht.common.dao.GoodsGdcprelMapper;
import com.retailers.dht.common.entity.GoodsGdcprel;
import com.retailers.dht.common.service.GoodsGdcprelService;
import com.retailers.dht.common.vo.GoodsGdcprelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：商品详情与砍价关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-05 16:15:28
 */
@Service("goodsgdcprelService")
public class GoodsGdcprelServiceImpl implements GoodsGdcprelService {
	@Autowired
	private GoodsGdcprelMapper goodsGdcprelMapper;
	public boolean saveGoodsGdcprel(GoodsGdcprel goodsGdcprel) {
		int status = goodsGdcprelMapper.saveGoodsGdcprel(goodsGdcprel);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsGdcprel(GoodsGdcprel goodsGdcprel) {
		int status = goodsGdcprelMapper.updateGoodsGdcprel(goodsGdcprel);
		return status == 1 ? true : false;
	}
	public GoodsGdcprel queryGoodsGdcprelByGdcpId(Long gdcpId) {
		return goodsGdcprelMapper.queryGoodsGdcprelByGdcpId(gdcpId);
	}

	public Pagination<GoodsGdcprel> queryGoodsGdcprelList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsGdcprel> page = new Pagination<GoodsGdcprel>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsGdcprel> list = goodsGdcprelMapper.queryGoodsGdcprelList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsGdcprelByGdcpId(Long gdcpId) {
		GoodsGdcprel goodsGdcprel = goodsGdcprelMapper.queryGoodsGdcprelByGdcpId(gdcpId);
		goodsGdcprel.setIsDelete(1L);
		int status = goodsGdcprelMapper.updateGoodsGdcprel(goodsGdcprel);
		return status == 1 ? true : false;
	}

	public List<GoodsGdcprelVo> queryGoodsGdcprelListsByGid(Long gid, Long cpId){
		List<GoodsGdcprelVo> list = goodsGdcprelMapper.queryGoodsGdcprelListsByGid(gid,cpId);
		return list;
	}
	public boolean saveGoodsGdcprelByJson(String data){
		JSONArray jsonArray = JSON.parseArray(data);
		GoodsGdcprel goodsGdcprel = new GoodsGdcprel();
		goodsGdcprel.setIsDelete(0L);
		int status = 0;
		for(int i=0;i<jsonArray.size();i++){
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			Long gdId = jsonObject.getLong("gdId");
			Long cpId = jsonObject.getLong("cpId");
			Long cpSale = jsonObject.getLong("cpSale");
			Long cpInventory = jsonObject.getLong("cpInventory");
			Long cpBounds = jsonObject.getLong("cpBounds");
			goodsGdcprel.setGdId(gdId);
			goodsGdcprel.setCpId(cpId);
			goodsGdcprel.setCpSale(cpSale);
			goodsGdcprel.setCpInventory(cpInventory);
			goodsGdcprel.setCpBounds(cpBounds);
			if(i==0){
				cleargdcprel(cpId);
			}
			status += goodsGdcprelMapper.saveGoodsGdcprel(goodsGdcprel);
		}
		return status==jsonArray.size() ? true:false;
	}

	public void cleargdcprel(Long cpId){
		Map params = new HashMap();
		params.put("isDelete",0L);
		params.put("cpId",cpId);
		List<GoodsGdcprel> list = queryGoodsGdcprelList(params,1,1000).getData();
		if(!ObjectUtils.isEmpty(list)){
			for(GoodsGdcprel goodsGdcprel:list){
				goodsGdcprel.setIsDelete(1L);
				updateGoodsGdcprel(goodsGdcprel);
			}
		}
	}
	public GoodsGdcprelVo queryGoodsGdcprelVoLists(Map params){
		List<GoodsGdcprelVo> list = goodsGdcprelMapper.queryGoodsGdcprelVoLists(params);
		if(!ObjectUtils.isEmpty(list)){
			GoodsGdcprelVo goodsGdcprelVo = list.get(0);
			goodsGdcprelVo.setImgurl(AttachmentConstant.IMAGE_SHOW_URL+goodsGdcprelVo.getImgurl());
			return goodsGdcprelVo;
		}
		return null;
	}
	public GoodsGdcprelVo queryCheckOrderData(Long gdcpId,Long uid){
		GoodsGdcprelVo list = goodsGdcprelMapper.queryCheckOrderData(gdcpId,uid);
		return  list;
	}

	public boolean editGoodsInventorys(Map<Long,Long> goodsGdcprelMap){
		int status = 0;
		if(ObjectUtils.isNotEmpty(goodsGdcprelMap)){
			List<Long> gdcpIds = new ArrayList<Long>();
			for (Map.Entry<Long, Long> entry : goodsGdcprelMap.entrySet()) {
				gdcpIds.add(entry.getKey());
			}
			List<GoodsGdcprel> list = goodsGdcprelMapper.queryGoodsGdcprelListByGdcpIds(gdcpIds);
			for(GoodsGdcprel goodsGdcprel:list){
				for (Map.Entry<Long, Long> entry : goodsGdcprelMap.entrySet()) {
					Long gdcpId1 = goodsGdcprel.getGdcpId();
					Long gdcpId2 = entry.getKey();
					if(gdcpId1.equals(gdcpId2)){
						Long reduceInventory = entry.getValue();
						Long cpInventory = goodsGdcprel.getCpInventory()+reduceInventory;
						if(cpInventory<0){
							return false;
						}
						goodsGdcprel.setCpInventory(cpInventory);
						break;
					}
				}
			}

			status = goodsGdcprelMapper.editGoodsInventorys(list);
		}
		return status>0?true:false;
	}

}

