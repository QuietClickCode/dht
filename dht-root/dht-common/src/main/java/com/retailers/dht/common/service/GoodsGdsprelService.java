
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGdsprel;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品与特价关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-08 17:29:16
 */
public interface GoodsGdsprelService {

	/**
	 * 添加商品与特价关系表
	 * @param goodsGdsprel
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public boolean saveGoodsGdsprel(GoodsGdsprel goodsGdsprel);
	/**
	 * 编辑商品与特价关系表
	 * @param goodsGdsprel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGdsprel(GoodsGdsprel goodsGdsprel);
	/**
	 * 根据id查询商品与特价关系表
	 * @param gdspId
	 * @return goodsGdsprel
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public GoodsGdsprel queryGoodsGdsprelByGdspId(Long gdspId);
	/**
	 * 查询商品与特价关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public Pagination<GoodsGdsprel> queryGoodsGdsprelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gdspId删除商品与特价关系表
	 * @param gdspId
	 * @return
	 * @author fanghui
	 * @date 2017-11-08 17:29:16
	 */
	public boolean deleteGoodsGdsprelByGdspId(Long gdspId);

	public List<GoodsGdsprelVo> queryGoodsGdsprelListsByGid(Long gid,Long spId);

	public GoodsGdsprelVo queryGoodsGdsprelVoLists(Map<String, Object> params);

	public boolean editGoodsInventorys(Map<Long,Long> goodsGdsprelMap);
}


