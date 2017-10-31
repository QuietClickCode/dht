
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsFreight;
import java.util.Map;
/**
 * 描述：商品运费表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-29 10:39:17
 */
public interface GoodsFreightService {

	/**
	 * 添加商品运费表
	 * @param goodsFreight
	 * @return
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public GoodsFreight saveGoodsFreight(GoodsFreight goodsFreight);
	/**
	 * 编辑商品运费表
	 * @param goodsFreight
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsFreight(GoodsFreight goodsFreight);
	/**
	 * 根据id查询商品运费表
	 * @param gfId
	 * @return goodsFreight
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public GoodsFreight queryGoodsFreightByGfId(Long gfId);
	/**
	 * 查询商品运费表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public Pagination<GoodsFreight> queryGoodsFreightList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gfId删除商品运费表
	 * @param gfId
	 * @return
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public boolean deleteGoodsFreightByGfId(Long gfId);

}


