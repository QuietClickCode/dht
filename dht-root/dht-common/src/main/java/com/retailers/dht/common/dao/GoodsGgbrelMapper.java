package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGgbrel;
import com.retailers.dht.common.vo.GoodsGgbrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与品牌关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 10:09:42
 */
public interface GoodsGgbrelMapper {

	/**
	 * 添加商品与品牌关系表
	 * @param goodsGgbrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public int saveGoodsGgbrel(GoodsGgbrel goodsGgbrel);
	/**
	 * 编辑商品与品牌关系表
	 * @param goodsGgbrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public int updateGoodsGgbrel(GoodsGgbrel goodsGgbrel);
	/**
	 * 根据GgbId删除商品与品牌关系表
	 * @param ggbId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public int deleteGoodsGgbrelByGgbId(Long ggbId);
	/**
	 * 根据GgbId查询商品与品牌关系表
	 * @param ggbId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public GoodsGgbrel queryGoodsGgbrelByGgbId(Long ggbId);
	/**
	 * 查询商品与品牌关系表列表
	 * @param pagination 分页对象
	 * @return  商品与品牌关系表列表
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public List<GoodsGgbrelVo> queryGoodsGgbrelList(Pagination<GoodsGgbrelVo> pagination);

}
