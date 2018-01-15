
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGsval;
import java.util.Map;
/**
 * 描述：商品规格值表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 09:46:10
 */
public interface GoodsGsvalService {

	/**
	 * 添加商品规格值表
	 * @param goodsGsval
	 * @return
	 * @author fanghui
	 * @date 2017-10-18 09:46:10
	 */
	public GoodsGsval saveGoodsGsval(GoodsGsval goodsGsval);
	/**
	 * 编辑商品规格值表
	 * @param goodsGsval
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGsval(GoodsGsval goodsGsval);
	/**
	 * 根据id查询商品规格值表
	 * @param gsvId
	 * @return goodsGsval
	 * @author fanghui
	 * @date 2017-10-18 09:46:10
	 */
	public GoodsGsval queryGoodsGsvalByGsvId(Long gsvId);
	/**
	 * 查询商品规格值表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-18 09:46:10
	 */
	public Pagination<GoodsGsval> queryGoodsGsvalList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gsvId删除商品规格值表
	 * @param gsvId
	 * @return
	 * @author fanghui
	 * @date 2017-10-18 09:46:10
	 */
	public boolean deleteGoodsGsvalByGsvId(Long gsvId);

}


