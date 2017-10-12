package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGsval;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品规格值表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 09:25:26
 */
public interface GoodsGsvalMapper {

	/**
	 * 添加商品规格值表
	 * @param goodsGsval
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 09:25:26
	 */
	public int saveGoodsGsval(GoodsGsval goodsGsval);
	/**
	 * 编辑商品规格值表
	 * @param goodsGsval
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 09:25:26
	 */
	public int updateGoodsGsval(GoodsGsval goodsGsval);
	/**
	 * 根据GsvId删除商品规格值表
	 * @param gsvId
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 09:25:26
	 */
	public int deleteGoodsGsvalByGsvId(Long gsvId);
	/**
	 * 根据GsvId查询商品规格值表
	 * @param gsvId
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 09:25:26
	 */
	public GoodsGsval queryGoodsGsvalByGsvId(Long gsvId);
	/**
	 * 查询商品规格值表列表
	 * @param pagination 分页对象
	 * @return  商品规格值表列表
	 * @author fanghui
	 * @date 2017-10-12 09:25:26
	 */
	public List<GoodsGsval> queryGoodsGsvalList(Pagination<GoodsGsval> pagination);

}
