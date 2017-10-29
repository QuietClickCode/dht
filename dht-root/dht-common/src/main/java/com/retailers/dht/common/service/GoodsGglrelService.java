
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGglrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGglrel;
import java.util.Map;
/**
 * 描述：商品与标签关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 14:28:44
 */
public interface GoodsGglrelService {

	/**
	 * 添加商品与标签关系表
	 * @param glIds,gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public boolean saveGoodsGglrel(String glIds,Long gid,Long uploadpersonId);
	/**
	 * 编辑商品与标签关系表
	 * @param goodsGglrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGglrel(GoodsGglrel goodsGglrel);
	/**
	 * 根据id查询商品与标签关系表
	 * @param gglId
	 * @return goodsGglrel
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public GoodsGglrel queryGoodsGglrelByGglId(Long gglId);
	/**
	 * 查询商品与标签关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public Pagination<GoodsGglrelVo> queryGoodsGglrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gglId删除商品与标签关系表
	 * @param gglIds
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 14:28:44
	 */
	public boolean deleteGoodsGglrelByGglId(String gglIds,Long uploadpersonId);

	public boolean saveGoodsGglrelByGids(String gids,Long glId,Long uploadpersonId);

}


