
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsConfig;

import java.util.List;
import java.util.Map;
/**
 * 描述：商品配置表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 10:37:31
 */
public interface GoodsConfigService {

	/**
	 * 添加商品配置表
	 * @param goodsConfig
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public boolean saveGoodsConfig(GoodsConfig goodsConfig);
	/**
	 * 编辑商品配置表
	 * @param goodsConfig
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsConfig(GoodsConfig goodsConfig,Long uploadpersonId);
	/**
	 * 根据id查询商品配置表
	 * @param gcId
	 * @return goodsConfig
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public GoodsConfig queryGoodsConfigByGcId(Long gcId);
	/**
	 * 查询商品配置表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public Pagination<GoodsConfig> queryGoodsConfigList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gcId删除商品配置表
	 * @param gcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 10:37:31
	 */
	public boolean deleteGoodsConfigByGcId(Long gcId);

	public List<GoodsConfig> queryGoodsConfigBygids(List<Long> gids);

}


