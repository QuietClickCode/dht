
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGgbrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGgbrel;
import java.util.Map;
/**
 * 描述：商品与品牌关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 10:09:42
 */
public interface GoodsGgbrelService {

	/**
	 * 添加商品与品牌关系表
	 * @param gbIds,gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public boolean saveGoodsGgbrel(String gbIds,Long gid);
	/**
	 * 编辑商品与品牌关系表
	 * @param goodsGgbrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGgbrel(GoodsGgbrel goodsGgbrel);
	/**
	 * 根据id查询商品与品牌关系表
	 * @param ggbId
	 * @return goodsGgbrel
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public GoodsGgbrel queryGoodsGgbrelByGgbId(Long ggbId);
	/**
	 * 查询商品与品牌关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public Pagination<GoodsGgbrelVo> queryGoodsGgbrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ggbId删除商品与品牌关系表
	 * @param ggbIds
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 10:09:42
	 */
	public boolean deleteGoodsGgbrelByGgbId(String ggbIds);

}


