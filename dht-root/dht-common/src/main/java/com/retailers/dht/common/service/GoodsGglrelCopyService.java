
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsGglrelCopy;
import java.util.Map;
/**
 * 描述：商品与标签关系副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 17:09:22
 */
public interface GoodsGglrelCopyService {

	/**
	 * 添加商品与标签关系副本表
	 * @param goodsGglrelCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public boolean saveGoodsGglrelCopy(GoodsGglrelCopy goodsGglrelCopy);
	/**
	 * 编辑商品与标签关系副本表
	 * @param goodsGglrelCopy
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsGglrelCopy(GoodsGglrelCopy goodsGglrelCopy);
	/**
	 * 根据id查询商品与标签关系副本表
	 * @param gglId
	 * @return goodsGglrelCopy
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public GoodsGglrelCopy queryGoodsGglrelCopyByGglId(Long gglId);
	/**
	 * 查询商品与标签关系副本表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public Pagination<GoodsGglrelCopy> queryGoodsGglrelCopyList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gglId删除商品与标签关系副本表
	 * @param gglId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public boolean deleteGoodsGglrelCopyByGglId(Long gglId);

}


