
package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.GoodsTypeMapper;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.dht.common.service.GoodsTypeService;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：商品大类表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-29 14:57:58
 */
@Service("goodstypeService")
public class GoodsTypeServiceImpl implements GoodsTypeService {
	@Autowired
	private GoodsTypeMapper goodsTypeMapper;
	public boolean saveGoodsType(GoodsType goodsType) {
		int status = goodsTypeMapper.saveGoodsType(goodsType);
		return status == 1 ? true : false;
	}
	public boolean updateGoodsType(GoodsType goodsType) {
		int status = goodsTypeMapper.updateGoodsType(goodsType);
		return status == 1 ? true : false;
	}
	public GoodsType queryGoodsTypeByGtId(Long gtId) {
		return goodsTypeMapper.queryGoodsTypeByGtId(gtId);
	}

	public Pagination<GoodsType> queryGoodsTypeList(Map<String, Object> params, int pageNo, int pageSize) {
		Pagination<GoodsType> page = new Pagination<GoodsType>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<GoodsType> list = goodsTypeMapper.queryGoodsTypeList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteGoodsTypeByGtId(Long gtId) {
		int status = goodsTypeMapper.deleteGoodsTypeByGtId(gtId);
		return status == 1 ? true : false;
	}
}

