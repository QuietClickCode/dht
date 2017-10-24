
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGtgbrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGtgbrel;
import java.util.Map;
/**
 * 描述：大类与规格关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 17:46:58
 */
public interface GoodsGtgbrelService {

	/**
	 * 添加大类与规格关系表
	 * @param gbIds,gtId
	 * @return
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public boolean saveGoodsGtgbrel(String gbIds,Long gtId);
	/**
	 * 编辑大类与规格关系表
	 * @param goodsGtgbrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGtgbrel(GoodsGtgbrel goodsGtgbrel);
	/**
	 * 根据id查询大类与规格关系表
	 * @param gtgbId
	 * @return goodsGtgbrel
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public GoodsGtgbrel queryGoodsGtgbrelByGtgbId(Long gtgbId);
	/**
	 * 查询大类与规格关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public Pagination<GoodsGtgbrelVo> queryGoodsGtgbrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gtgbId删除大类与规格关系表
	 * @param gtgbIds
	 * @return
	 * @author fanghui
	 * @date 2017-10-18 17:46:58
	 */
	public boolean deleteGoodsGtgbrelByGtgbId(String gtgbIds);

}


