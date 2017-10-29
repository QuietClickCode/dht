
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.GoodsGgcouponrelVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGgcouponrel;
import java.util.Map;
/**
 * 描述：商品与优惠关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-26 14:57:28
 */
public interface GoodsGgcouponrelService {

	/**
	 * 添加商品与优惠关系表
	 * @param gcIds,gid
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public boolean saveGoodsGgcouponrel(String gcIds,Long gid);
	/**
	 * 编辑商品与优惠关系表
	 * @param goodsGgcouponrel
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGgcouponrel(GoodsGgcouponrel goodsGgcouponrel);
	/**
	 * 根据id查询商品与优惠关系表
	 * @param ggcId
	 * @return goodsGgcouponrel
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public GoodsGgcouponrel queryGoodsGgcouponrelByGgcId(Long ggcId);
	/**
	 * 查询商品与优惠关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public Pagination<GoodsGgcouponrelVo> queryGoodsGgcouponrelList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ggcId删除商品与优惠关系表
	 * @param ggcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-26 14:57:28
	 */
	public boolean deleteGoodsGgcouponrelByGgcId(String ggcIds);

}


