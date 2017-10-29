package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGgcouponrel;
import com.retailers.dht.common.vo.GoodsGgcouponrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与优惠关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-26 14:57:28
 */
public interface GoodsGgcouponrelMapper {

	/**
	 * 添加商品与优惠关系表
	 * @param goodsGgcouponrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public int saveGoodsGgcouponrel(GoodsGgcouponrel goodsGgcouponrel);
	/**
	 * 编辑商品与优惠关系表
	 * @param goodsGgcouponrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public int updateGoodsGgcouponrel(GoodsGgcouponrel goodsGgcouponrel);
	/**
	 * 根据GgcId删除商品与优惠关系表
	 * @param ggcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public int deleteGoodsGgcouponrelByGgcId(Long ggcId);
	/**
	 * 根据GgcId查询商品与优惠关系表
	 * @param ggcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public GoodsGgcouponrel queryGoodsGgcouponrelByGgcId(Long ggcId);
	/**
	 * 查询商品与优惠关系表列表
	 * @param pagination 分页对象
	 * @return  商品与优惠关系表列表
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public List<GoodsGgcouponrelVo> queryGoodsGgcouponrelList(Pagination<GoodsGgcouponrelVo> pagination);

}
