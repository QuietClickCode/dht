
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.entity.City;
import com.retailers.dht.common.entity.GoodsFreight;
import com.retailers.dht.common.dao.GoodsFreightMapper;
import com.retailers.dht.common.service.CityService;
import com.retailers.dht.common.service.GoodsFreightService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：商品运费表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-29 10:39:17
 */
@Service("goodsfreightService")
public class GoodsFreightServiceImpl implements GoodsFreightService {
	@Autowired
	private GoodsFreightMapper goodsFreightMapper;
	@Autowired
	private CityService cityService;
	public GoodsFreight saveGoodsFreight(GoodsFreight goodsFreight) {
		int status = goodsFreightMapper.saveGoodsFreight(goodsFreight);
		return status == 1 ? goodsFreight : null;
	}
	public boolean updateGoodsFreight(GoodsFreight goodsFreight) {
		int status = goodsFreightMapper.updateGoodsFreight(goodsFreight);
		return status == 1 ? true : false;
	}
	public GoodsFreight queryGoodsFreightByGfId(Long gfId) {
		return goodsFreightMapper.queryGoodsFreightByGfId(gfId);
	}

	public Pagination<GoodsFreight> queryGoodsFreightList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<GoodsFreight> page = new Pagination<GoodsFreight>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsFreight> list = goodsFreightMapper.queryGoodsFreightList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsFreightByGfId(Long gfId) {
		int status = goodsFreightMapper.deleteGoodsFreightByGfId(gfId);
		return status == 1 ? true : false;
	}
	public GoodsFreight queryFreightByAddress(String address){
		if(!ObjectUtils.isEmpty(address)){
			String pro = address.substring(0,2);
			Map params = new HashMap();
			params.put("cityname",pro);
			List<City> cityList = cityService.queryCityList(params,1,1).getData();
			if(!ObjectUtils.isEmpty(cityList)){
				City c = cityList.get(0);
				List<GoodsFreight> list = goodsFreightMapper.queryFreightByAddress(c.getCityid());
				GoodsFreight goodsFreight = new GoodsFreight();
				for(GoodsFreight goodsFreight1:list){
					if(goodsFreight1.getGfId()==0){
						String freeArea = goodsFreight1.getGfFreeArea();
						String[] freeAreaArr = freeArea.split(",");
						for(String freeAreaStr:freeAreaArr){
							int index = address.indexOf(freeAreaStr);
							System.out.println("index:"+index);
							if(index>=0){
								goodsFreight.setGfName(goodsFreight1.getGfName());
								goodsFreight.setGfPrice(goodsFreight1.getGfPrice());
								return goodsFreight;
							}
						}
					}else{
						BeanUtils.copyProperties( goodsFreight1,goodsFreight);
						return goodsFreight;
					}
				}
			}

		}
		return null;
	}
}

