
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsConfigCopy;
import java.util.Map;
/**
 * 描述：商品配置副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-16 17:33:04
 */
public interface GoodsConfigCopyService {

	/**
	 * 添加商品配置副本表
	 * @param goodsConfigCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public boolean saveGoodsConfigCopy(GoodsConfigCopy goodsConfigCopy);
	/**
	 * 编辑商品配置副本表
	 * @param goodsConfigCopy
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsConfigCopy(GoodsConfigCopy goodsConfigCopy);
	/**
	 * 根据id查询商品配置副本表
	 * @param gccId
	 * @return goodsConfigCopy
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public GoodsConfigCopy queryGoodsConfigCopyByGccId(Long gccId);
	/**
	 * 查询商品配置副本表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public Pagination<GoodsConfigCopy> queryGoodsConfigCopyList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gccId删除商品配置副本表
	 * @param gccId
	 * @return
	 * @author fanghui
	 * @date 2017-10-16 17:33:04
	 */
	public boolean deleteGoodsConfigCopyByGccId(Long gccId);

}


