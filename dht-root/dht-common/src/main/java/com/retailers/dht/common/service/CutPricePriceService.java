
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.CutPricePrice;
import java.util.Map;
/**
 * 描述：砍价价格初始化表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-07 15:52:00
 */
public interface CutPricePriceService {

	/**
	 * 添加砍价价格初始化表
	 * @param cutPricePrice
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public boolean saveCutPricePrice(CutPricePrice cutPricePrice);
	/**
	 * 编辑砍价价格初始化表
	 * @param cutPricePrice
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateCutPricePrice(CutPricePrice cutPricePrice);
	/**
	 * 根据id查询砍价价格初始化表
	 * @param cppId
	 * @return cutPricePrice
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public CutPricePrice queryCutPricePriceByCppId(Long cppId);
	/**
	 * 查询砍价价格初始化表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public Pagination<CutPricePrice> queryCutPricePriceList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cppId删除砍价价格初始化表
	 * @param cppId
	 * @return
	 * @author fanghui
	 * @date 2017-12-07 15:52:00
	 */
	public boolean deleteCutPricePriceByCppId(Long cppId);

}


