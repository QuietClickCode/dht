package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.GoodsConfig;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：商品配置表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 10:37:31
 */
public interface GoodsConfigMapper {

	/**
	 * 添加商品配置表
	 * @param goodsConfig
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public int saveGoodsConfig(GoodsConfig goodsConfig);
	/**
	 * 编辑商品配置表
	 * @param goodsConfig
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public int updateGoodsConfig(GoodsConfig goodsConfig);
	/**
	 * 根据GcId删除商品配置表
	 * @param gcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public int deleteGoodsConfigByGcId(Long gcId);
	/**
	 * 根据GcId查询商品配置表
	 * @param gcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public GoodsConfig queryGoodsConfigByGcId(Long gcId);
	/**
	 * 查询商品配置表列表
	 * @param pagination 分页对象
	 * @return  商品配置表列表
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public List<GoodsConfig> queryGoodsConfigList(Pagination<GoodsConfig> pagination);

	public List<GoodsConfig> queryGoodsConfigBygids(@Param("gids") List<Long> gids);
}
