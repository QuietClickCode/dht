
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
			String pro = address.substring(0,2);//截取0-2,含头不含尾
			Map params = new HashMap();
			params.put("cityname",pro);//存入城市名字
			List<City> cityList = cityService.queryCityList(params,1,1).getData();//得到城市列表
			if(!ObjectUtils.isEmpty(cityList)){//城市数据不为空
				City c = cityList.get(0);//取得第一个城市
				List<GoodsFreight> list = goodsFreightMapper.queryFreightByAddress(c.getCityid());//查询运费对象list通过地址
				GoodsFreight goodsFreight = new GoodsFreight();
				for(GoodsFreight goodsFreight1:list){//遍历运费对象list
					if(goodsFreight1.getGfId()==0){//id==0的对象?
						String freeArea = goodsFreight1.getGfFreeArea();//得到包邮地区
						String[] freeAreaArr = freeArea.split(",");//数组中有多个地区
						for(String freeAreaStr:freeAreaArr){//遍历数组
							int index = address.indexOf(freeAreaStr);//address是否包含包邮地区
							System.out.println("index:"+index);
							if(index>=0){
								goodsFreight.setGfName(goodsFreight1.getGfName());//设置运费名称
								goodsFreight.setGfPrice(goodsFreight1.getGfPrice());//设置价格
								return goodsFreight;
							}
						}
					}else{
						BeanUtils.copyProperties( goodsFreight1,goodsFreight);
						/**
						 *
						 * 1、 通过反射将一个对象的值赋值个另外一个对象（前提是对象中属性的名字相同）。

						 2、 BeanUtils.copyProperties(obj1,obj2); 经常闹混不知道是谁给谁赋值，无意中先到"后付前"这个词来帮助自己记忆这个功能。即将obj2的值赋值给obj1。
						 */
						return goodsFreight;
					}
				}
			}

		}
		return null;
	}
}

