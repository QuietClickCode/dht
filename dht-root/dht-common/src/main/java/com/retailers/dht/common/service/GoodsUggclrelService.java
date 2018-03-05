
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsUggclrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsUggclrel;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品用户评论关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-16 13:54:57
 */
public interface GoodsUggclrelService {

	/**
	 * 添加商品用户评论关系表
	 * @param goodsUggclrel
	 * @return
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public boolean saveGoodsUggclrel(GoodsUggclrel goodsUggclrel);
	public boolean saveGoodsUggclrel(Long gid,Long uid,Long orderNo, String gclIdStr);
	/**
	 * 编辑商品用户评论关系表
	 * @param goodsUggclrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsUggclrel(GoodsUggclrel goodsUggclrel);
	/**
	 * 根据id查询商品用户评论关系表
	 * @param uggclId
	 * @return goodsUggclrel
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public GoodsUggclrel queryGoodsUggclrelByUggclId(Long uggclId);
	/**
	 * 查询商品用户评论关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public Pagination<GoodsUggclrel> queryGoodsUggclrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据uggclId删除商品用户评论关系表
	 * @param uggclId
	 * @return
	 * @author fanghui
	 * @date 2017-11-16 13:54:57
	 */
	public boolean deleteGoodsUggclrelByUggclId(Long uggclId);

	public Pagination<GoodsUggclrelVo> queryGoodsUggclrelListVo(Map<String, Object> params, int pageNo, int pageSize);

	public GoodsUggclrelVo queryGoodsclsumandavg(Long gid);

	public List<GoodsUggclrelVo> queryHasGoodscls(Long gid);


}


