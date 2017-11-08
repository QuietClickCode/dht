
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGgclrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGgclrel;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品与评论关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-06 11:17:50
 */
public interface GoodsGgclrelService {

	/**
	 * 添加商品与评论关系表
	 * @param gclIds,gid
	 * @return
	 * @author fanghui
	 * @date 2017-11-06 11:17:50
	 */
	public boolean saveGoodsGgclrel(String gclIds,Long gid);
	/**
	 * 编辑商品与评论关系表
	 * @param goodsGgclrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGgclrel(GoodsGgclrel goodsGgclrel);
	/**
	 * 根据id查询商品与评论关系表
	 * @param ggclId
	 * @return goodsGgclrel
	 * @author fanghui
	 * @date 2017-11-06 11:17:50
	 */
	public GoodsGgclrel queryGoodsGgclrelByGgclId(Long ggclId);
	/**
	 * 查询商品与评论关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-11-06 11:17:50
	 */
	public List<GoodsGgclrelVo> queryGoodsGgclrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ggclId删除商品与评论关系表
	 * @param ggclIds
	 * @return
	 * @author fanghui
	 * @date 2017-11-06 11:17:50
	 */
	public boolean deleteGoodsGgclrelByGgclId(String ggclIds);

	public List<GoodsGgclrelVo> queryMyGoodsGgclrelList(Map map);

	public boolean saveGoodsGgclrelByGids(String gids,Long gclId);

	public boolean addGoodsGgcrelByGclass(String gclassIds,Long gclId);

	public List<GoodsGgclrelVo> queryGclassGoodsGgclrelLists(Long gid);

	public boolean saveDeleteGt(String gclIds,Long gid);
}


