
package com.retailers.dht.common.service;

import com.retailers.dht.common.entity.GoodsType;
import com.retailers.mybatis.pagination.Pagination;

import java.util.Map;

/**
 * 描述：商品大类表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-29 14:57:58
 */
public interface GoodsTypeService {

	/**
	 * 添加商品大类表
	 * @param goodsType
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public boolean saveGoodsType(GoodsType goodsType);
	/**
	 * 编辑商品大类表
	 * @param goodsType
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsType(GoodsType goodsType);
	/**
	 * 根据id查询商品大类表
	 * @param gtId
	 * @return goodsType
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public GoodsType queryGoodsTypeByGtId(Long gtId);
	/**
	 * 查询商品大类表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public Pagination<GoodsType> queryGoodsTypeList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gtId删除商品大类表
	 * @param gtId
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public boolean deleteGoodsTypeByGtId(Long gtId);

}


