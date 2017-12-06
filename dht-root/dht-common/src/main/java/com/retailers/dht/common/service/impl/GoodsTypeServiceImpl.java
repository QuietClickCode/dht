
package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.dao.CouponUseRangeMapper;
import com.retailers.dht.common.dao.GoodsClassificationMapper;
import com.retailers.dht.common.dao.GoodsTypeMapper;
import com.retailers.dht.common.entity.CouponUseRange;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.GoodsTypeService;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import jdk.nashorn.internal.runtime.regexp.joni.constants.NodeStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
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
	@Autowired
	private GoodsClassificationService goodsClassificationService;
	@Autowired
	private CouponUseRangeMapper couponUseRangeMapper;

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
		GoodsType goodsType = goodsTypeMapper.queryGoodsTypeByGtId(gtId);
		goodsType.setIsDelete(1L);
		int status = goodsTypeMapper.updateGoodsType(goodsType);
		return status == 1 ? true : false;
	}
}

