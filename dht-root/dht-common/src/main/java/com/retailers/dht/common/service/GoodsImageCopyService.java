
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsImageCopy;
import java.util.Map;
/**
 * 描述：商品图片副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 14:25:01
 */
public interface GoodsImageCopyService {

	/**
	 * 添加商品图片副本表
	 * @param goodsImageCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public boolean saveGoodsImageCopy(GoodsImageCopy goodsImageCopy);
	/**
	 * 编辑商品图片副本表
	 * @param goodsImageCopy
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsImageCopy(GoodsImageCopy goodsImageCopy);
	/**
	 * 根据id查询商品图片副本表
	 * @param gicId
	 * @return goodsImageCopy
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public GoodsImageCopy queryGoodsImageCopyByGicId(Long gicId);
	/**
	 * 查询商品图片副本表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public Pagination<GoodsImageCopy> queryGoodsImageCopyList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gicId删除商品图片副本表
	 * @param gicId
	 * @return
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public boolean deleteGoodsImageCopyByGicId(Long gicId);

}


