package com.retailers.dht.common.dao;

import com.retailers.dht.common.entity.GoodsType;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;

/**
 * 描述：商品大类表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-09-29 14:57:58
 */
public interface GoodsTypeMapper {

	/**
	 * 添加商品大类表
	 * @param goodsType
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public int saveGoodsType(GoodsType goodsType);
	/**
	 * 编辑商品大类表
	 * @param goodsType
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public int updateGoodsType(GoodsType goodsType);
	/**
	 * 根据GtId删除商品大类表
	 * @param gtId
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public int deleteGoodsTypeByGtId(Long gtId);
	/**
	 * 根据GtId查询商品大类表
	 * @param gtId
	 * @return
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public GoodsType queryGoodsTypeByGtId(Long gtId);
	/**
	 * 查询商品大类表列表
	 * @param pagination 分页对象
	 * @return  商品大类表列表
	 * @author fanghui
	 * @date 2017-09-29 14:57:58
	 */
	public List<GoodsType> queryGoodsTypeList(Pagination<GoodsType> pagination);

	/**
	 * 取得有效的商品类型
	 * @return
	 */
	public List<GoodsType> queryValidateGoodsTypes();

}
