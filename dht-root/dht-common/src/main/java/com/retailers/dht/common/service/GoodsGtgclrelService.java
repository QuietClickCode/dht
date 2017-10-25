
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGtgclrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGtgclrel;
import java.util.Map;
/**
 * 描述：大类与商品评论关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-25 10:54:33
 */
public interface GoodsGtgclrelService {

	/**
	 * 添加大类与商品评论关系表
	 * @param gclIds,gtId
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public boolean saveGoodsGtgclrel(String gclIds,Long gtId);
	/**
	 * 编辑大类与商品评论关系表
	 * @param goodsGtgclrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGtgclrel(GoodsGtgclrel goodsGtgclrel);
	/**
	 * 根据id查询大类与商品评论关系表
	 * @param gtgclId
	 * @return goodsGtgclrel
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public GoodsGtgclrel queryGoodsGtgclrelByGtgclId(Long gtgclId);
	/**
	 * 查询大类与商品评论关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public Pagination<GoodsGtgclrelVo> queryGoodsGtgclrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gtgclId删除大类与商品评论关系表
	 * @param gtgclIds
	 * @return
	 * @author fanghui
	 * @date 2017-10-25 10:54:33
	 */
	public boolean deleteGoodsGtgclrelByGtgclId(String gtgclIds);

}


