package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGtgclrel;
import com.retailers.dht.common.vo.GoodsGtgclrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：大类与商品评论关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 10:54:33
 */
public interface GoodsGtgclrelMapper {

	/**
	 * 添加大类与商品评论关系表
	 * @param goodsGtgclrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public int saveGoodsGtgclrel(GoodsGtgclrel goodsGtgclrel);
	/**
	 * 编辑大类与商品评论关系表
	 * @param goodsGtgclrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public int updateGoodsGtgclrel(GoodsGtgclrel goodsGtgclrel);
	/**
	 * 根据GtgclId删除大类与商品评论关系表
	 * @param gtgclId
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public int deleteGoodsGtgclrelByGtgclId(Long gtgclId);
	/**
	 * 根据GtgclId查询大类与商品评论关系表
	 * @param gtgclId
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public GoodsGtgclrel queryGoodsGtgclrelByGtgclId(Long gtgclId);
	/**
	 * 查询大类与商品评论关系表列表
	 * @param pagination 分页对象
	 * @return  大类与商品评论关系表列表
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public List<GoodsGtgclrelVo> queryGoodsGtgclrelList(Pagination<GoodsGtgclrelVo> pagination);

}
