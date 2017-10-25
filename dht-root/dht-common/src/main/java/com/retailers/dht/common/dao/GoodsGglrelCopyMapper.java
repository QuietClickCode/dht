package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsGglrelCopy;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品与标签关系副本表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-24 17:09:22
 */
public interface GoodsGglrelCopyMapper {

	/**
	 * 添加商品与标签关系副本表
	 * @param goodsGglrelCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public int saveGoodsGglrelCopy(GoodsGglrelCopy goodsGglrelCopy);
	/**
	 * 编辑商品与标签关系副本表
	 * @param goodsGglrelCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public int updateGoodsGglrelCopy(GoodsGglrelCopy goodsGglrelCopy);
	/**
	 * 根据GglId删除商品与标签关系副本表
	 * @param gglId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public int deleteGoodsGglrelCopyByGglId(Long gglId);
	/**
	 * 根据GglId查询商品与标签关系副本表
	 * @param gglId
	 * @return
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public GoodsGglrelCopy queryGoodsGglrelCopyByGglId(Long gglId);
	/**
	 * 查询商品与标签关系副本表列表
	 * @param pagination 分页对象
	 * @return  商品与标签关系副本表列表
	 * @author fanghui
	 * @date 2017-10-24 17:09:22
	 */
	public List<GoodsGglrelCopy> queryGoodsGglrelCopyList(Pagination<GoodsGglrelCopy> pagination);

}
