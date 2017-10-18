package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsImageCopy;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品图片副本表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 14:25:01
 */
public interface GoodsImageCopyMapper {

	/**
	 * 添加商品图片副本表
	 * @param goodsImageCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public int saveGoodsImageCopy(GoodsImageCopy goodsImageCopy);
	/**
	 * 编辑商品图片副本表
	 * @param goodsImageCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public int updateGoodsImageCopy(GoodsImageCopy goodsImageCopy);
	/**
	 * 根据GicId删除商品图片副本表
	 * @param gicId
	 * @return
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public int deleteGoodsImageCopyByGicId(Long gicId);
	/**
	 * 根据GicId查询商品图片副本表
	 * @param gicId
	 * @return
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public GoodsImageCopy queryGoodsImageCopyByGicId(Long gicId);
	/**
	 * 查询商品图片副本表列表
	 * @param pagination 分页对象
	 * @return  商品图片副本表列表
	 * @author fanghui
	 * @date 2017-10-17 14:25:01
	 */
	public List<GoodsImageCopy> queryGoodsImageCopyList(Pagination<GoodsImageCopy> pagination);

}
