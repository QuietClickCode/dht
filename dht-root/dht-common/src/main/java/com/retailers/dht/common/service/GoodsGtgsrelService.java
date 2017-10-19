
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGtgsrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGtgsrel;
import java.util.Map;
/**
 * 描述：大类与规格关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-19 11:57:43
 */
public interface GoodsGtgsrelService {

	/**
	 * 添加大类与规格关系表
	 * @param gsIds,gtId
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public boolean saveGoodsGtgsrel(String gsIds,Long gtId);
	/**
	 * 编辑大类与规格关系表
	 * @param goodsGtgsrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGtgsrel(GoodsGtgsrel goodsGtgsrel);
	/**
	 * 根据id查询大类与规格关系表
	 * @param gtgsId
	 * @return goodsGtgsrel
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public GoodsGtgsrel queryGoodsGtgsrelByGtgsId(Long gtgsId);
	/**
	 * 查询大类与规格关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public Pagination<GoodsGtgsrelVo> queryGoodsGtgsrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gtgsId删除大类与规格关系表
	 * @param gtgsIds
	 * @return
	 * @author fanghui
	 * @date 2017-10-19 11:57:43
	 */
	public boolean deleteGoodsGtgsrelByGtgsId(String gtgsIds);

}


