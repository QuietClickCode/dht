package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGgsval;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与规格值关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-19 18:43:18
 */
public interface GoodsGgsvalMapper {

	/**
	 * 添加商品与规格值关系表
	 * @param goodsGgsval
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public int saveGoodsGgsval(GoodsGgsval goodsGgsval);
	/**
	 * 编辑商品与规格值关系表
	 * @param goodsGgsval
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public int updateGoodsGgsval(GoodsGgsval goodsGgsval);
	/**
	 * 根据GgsId删除商品与规格值关系表
	 * @param ggsId
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public int deleteGoodsGgsvalByGgsId(Long ggsId);
	/**
	 * 根据GgsId查询商品与规格值关系表
	 * @param ggsId
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public GoodsGgsval queryGoodsGgsvalByGgsId(Long ggsId);
	/**
	 * 查询商品与规格值关系表列表
	 * @param pagination 分页对象
	 * @return  商品与规格值关系表列表
	 * @author fanghui
	 * @date 2017-10-19 18:43:18
	 */
	public List<GoodsGgsval> queryGoodsGgsvalList(Pagination<GoodsGgsval> pagination);

}
