package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGgclrel;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与商品评论关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 13:55:59
 */
public interface GoodsGgclrelMapper {

	/**
	 * 添加商品与商品评论关系表
	 * @param goodsGgclrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 13:55:59
	 */
	public int saveGoodsGgclrel(GoodsGgclrel goodsGgclrel);
	/**
	 * 编辑商品与商品评论关系表
	 * @param goodsGgclrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 13:55:59
	 */
	public int updateGoodsGgclrel(GoodsGgclrel goodsGgclrel);
	/**
	 * 根据GgclId删除商品与商品评论关系表
	 * @param ggclId
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 13:55:59
	 */
	public int deleteGoodsGgclrelByGgclId(Long ggclId);
	/**
	 * 根据GgclId查询商品与商品评论关系表
	 * @param ggclId
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 13:55:59
	 */
	public GoodsGgclrel queryGoodsGgclrelByGgclId(Long ggclId);
	/**
	 * 查询商品与商品评论关系表列表
	 * @param pagination 分页对象
	 * @return  商品与商品评论关系表列表
	 * @author fanghui
	 * @date 2017-10-25 13:55:59
	 */
	public List<GoodsGgclrelVo> queryGoodsGgclrelList(Pagination<GoodsGgclrelVo> pagination);

}
