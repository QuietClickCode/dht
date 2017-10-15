
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.GoodsCopy;
import java.util.Map;
/**
 * 描述：商品副本表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 17:30:11
 */
public interface GoodsCopyService {

	/**
	 * 添加商品副本表
	 * @param goodsCopy
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 17:30:11
	 */
	public boolean saveGoodsCopy(GoodsCopy goodsCopy);
	/**
	 * 编辑商品副本表
	 * @param goodsCopy
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateGoodsCopy(GoodsCopy goodsCopy);
	/**
	 * 根据id查询商品副本表
	 * @param gcId
	 * @return goodsCopy
	 * @author fanghui
	 * @date 2017-10-12 17:30:11
	 */
	public GoodsCopy queryGoodsCopyByGcId(Long gcId);
	/**
	 * 查询商品副本表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-12 17:30:11
	 */
	public Pagination<GoodsCopy> queryGoodsCopyList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据gcId删除商品副本表
	 * @param gcId
	 * @return
	 * @author fanghui
	 * @date 2017-10-12 17:30:11
	 */
	public boolean deleteGoodsCopyByGcId(Long gcId);

}


