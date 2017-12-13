package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsDetailCopy;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品详情副本表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 13:28:42
 */
public interface GoodsDetailCopyMapper {

	/**
	 * 添加商品详情副本表
	 * @param goodsDetailCopy
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 13:28:42
	 */
	public int saveGoodsDetailCopy(GoodsDetailCopy goodsDetailCopy);
	/**
	 * 编辑商品详情副本表
	 * @param goodsDetailCopy
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 13:28:42
	 */
	public int updateGoodsDetailCopy(GoodsDetailCopy goodsDetailCopy);
	/**
	 * 根据GdcId删除商品详情副本表
	 * @param gdcId
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 13:28:42
	 */
	public int deleteGoodsDetailCopyByGdcId(Long gdcId);
	/**
	 * 根据GdcId查询商品详情副本表
	 * @param gdcId
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 13:28:42
	 */
	public GoodsDetailCopy queryGoodsDetailCopyByGdcId(Long gdcId);
	/**
	 * 查询商品详情副本表列表
	 * @param pagination 分页对象
	 * @return  商品详情副本表列表
	 * @author fanghui
	 * @date 2017-12-13 13:28:42
	 */
	public List<GoodsDetailCopy> queryGoodsDetailCopyList(Pagination<GoodsDetailCopy> pagination);

}
