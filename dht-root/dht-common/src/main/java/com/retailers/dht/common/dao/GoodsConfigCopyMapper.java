package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsConfigCopy;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：商品配置副本表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-16 17:33:04
 */
public interface GoodsConfigCopyMapper {

	/**
	 * 添加商品配置副本表
	 * @param goodsConfigCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public int saveGoodsConfigCopy(GoodsConfigCopy goodsConfigCopy);
	/**
	 * 编辑商品配置副本表
	 * @param goodsConfigCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public int updateGoodsConfigCopy(GoodsConfigCopy goodsConfigCopy);
	/**
	 * 根据GccId删除商品配置副本表
	 * @param gccId
	 * @return
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public int deleteGoodsConfigCopyByGccId(Long gccId);
	/**
	 * 根据GccId查询商品配置副本表
	 * @param gccId
	 * @return
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public GoodsConfigCopy queryGoodsConfigCopyByGccId(Long gccId);
	/**
	 * 查询商品配置副本表列表
	 * @param pagination 分页对象
	 * @return  商品配置副本表列表
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public List<GoodsConfigCopy> queryGoodsConfigCopyList(Pagination<GoodsConfigCopy> pagination);

}
