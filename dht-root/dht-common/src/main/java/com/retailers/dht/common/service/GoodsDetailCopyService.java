
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsDetailCopy;
import java.util.Map;
/**
 * 描述：商品详情副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 09:34:07
 */
public interface GoodsDetailCopyService {

	/**
	 * 添加商品详情副本表
	 * @param goodsDetailCopy
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 09:34:07
	 */
	public boolean saveGoodsDetailCopy(GoodsDetailCopy goodsDetailCopy);
	/**
	 * 编辑商品详情副本表
	 * @param goodsDetailCopy
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsDetailCopy(GoodsDetailCopy goodsDetailCopy);
	/**
	 * 根据id查询商品详情副本表
	 * @param gdId
	 * @return goodsDetailCopy
	 * @author fanghui
	 * @date 2017-12-13 09:34:07
	 */
	public GoodsDetailCopy queryGoodsDetailCopyByGdId(Long gdId);
	/**
	 * 查询商品详情副本表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-13 09:34:07
	 */
	public Pagination<GoodsDetailCopy> queryGoodsDetailCopyList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gdId删除商品详情副本表
	 * @param gdId
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 09:34:07
	 */
	public boolean deleteGoodsDetailCopyByGdId(Long gdId);

}


