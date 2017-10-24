package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGtgsrel;
import com.retailers.dht.common.vo.GoodsGtgsrelVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：大类与规格关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-19 11:57:43
 */
public interface GoodsGtgsrelMapper {

	/**
	 * 添加大类与规格关系表
	 * @param goodsGtgsrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public int saveGoodsGtgsrel(GoodsGtgsrel goodsGtgsrel);
	/**
	 * 编辑大类与规格关系表
	 * @param goodsGtgsrel
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public int updateGoodsGtgsrel(GoodsGtgsrel goodsGtgsrel);
	/**
	 * 根据GtgsId删除大类与规格关系表
	 * @param gtgsId
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public int deleteGoodsGtgsrelByGtgsId(Long gtgsId);
	/**
	 * 根据GtgsId查询大类与规格关系表
	 * @param gtgsId
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public GoodsGtgsrel queryGoodsGtgsrelByGtgsId(Long gtgsId);
	/**
	 * 查询大类与规格关系表列表
	 * @param pagination 分页对象
	 * @return  大类与规格关系表列表
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public List<GoodsGtgsrelVo> queryGoodsGtgsrelList(Pagination<GoodsGtgsrelVo> pagination);

}
