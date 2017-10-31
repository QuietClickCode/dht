package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsFreight;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品运费表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-29 10:39:17
 */
public interface GoodsFreightMapper {

	/**
	 * 添加商品运费表
	 * @param goodsFreight
	 * @return
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public int saveGoodsFreight(GoodsFreight goodsFreight);
	/**
	 * 编辑商品运费表
	 * @param goodsFreight
	 * @return
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public int updateGoodsFreight(GoodsFreight goodsFreight);
	/**
	 * 根据GfId删除商品运费表
	 * @param gfId
	 * @return
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public int deleteGoodsFreightByGfId(Long gfId);
	/**
	 * 根据GfId查询商品运费表
	 * @param gfId
	 * @return
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public GoodsFreight queryGoodsFreightByGfId(Long gfId);
	/**
	 * 查询商品运费表列表
	 * @param pagination 分页对象
	 * @return  商品运费表列表
	 * @author fanghui
	 * @date 2017-10-29 10:39:17
	 */
	public List<GoodsFreight> queryGoodsFreightList(Pagination<GoodsFreight> pagination);

}
