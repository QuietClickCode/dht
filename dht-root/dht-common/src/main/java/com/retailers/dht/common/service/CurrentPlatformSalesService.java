
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.CurrentPlatformSales;
import java.util.Map;
/**
 * 描述：目前平台销售情况Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 00:57:33
 */
public interface CurrentPlatformSalesService {

	/**
	 * 添加目前平台销售情况
	 * @param currentPlatformSales
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public boolean saveCurrentPlatformSales(CurrentPlatformSales currentPlatformSales);
	/**
	 * 编辑目前平台销售情况
	 * @param currentPlatformSales
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateCurrentPlatformSales(CurrentPlatformSales currentPlatformSales);
	/**
	 * 根据id查询目前平台销售情况
	 * @param cpsId
	 * @return currentPlatformSales
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public CurrentPlatformSales queryCurrentPlatformSalesByCpsId(Long cpsId);
	/**
	 * 查询目前平台销售情况列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public Pagination<CurrentPlatformSales> queryCurrentPlatformSalesList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cpsId删除目前平台销售情况
	 * @param cpsId
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public boolean deleteCurrentPlatformSalesByCpsId(Long cpsId);

}


