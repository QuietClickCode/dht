package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGtgbrel;
import com.retailers.dht.common.vo.GoodsGtgbrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：大类与规格关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 17:46:58
 */
public interface GoodsGtgbrelMapper {

	/**
	 * 添加大类与规格关系表
	 * @param goodsGtgbrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public int saveGoodsGtgbrel(GoodsGtgbrel goodsGtgbrel);
	/**
	 * 编辑大类与规格关系表
	 * @param goodsGtgbrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public int updateGoodsGtgbrel(GoodsGtgbrel goodsGtgbrel);
	/**
	 * 根据GtgbId删除大类与规格关系表
	 * @param gtgbId
	 * @return
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public int deleteGoodsGtgbrelByGtgbId(Long gtgbId);
	/**
	 * 根据GtgbId查询大类与规格关系表
	 * @param gtgbId
	 * @return
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public GoodsGtgbrel queryGoodsGtgbrelByGtgbId(Long gtgbId);
	/**
	 * 查询大类与规格关系表列表
	 * @param pagination 分页对象
	 * @return  大类与规格关系表列表
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public List<GoodsGtgbrelVo> queryGoodsGtgbrelList(Pagination<GoodsGtgbrelVo> pagination);

}
