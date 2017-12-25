package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.CurrentPlatformSales;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Set;

/**
 * 描述：目前平台销售情况DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 00:57:33
 */
public interface CurrentPlatformSalesMapper {

	/**
	 * 添加目前平台销售情况
	 * @param currentPlatformSales
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public int saveCurrentPlatformSales(CurrentPlatformSales currentPlatformSales);
	/**
	 * 编辑目前平台销售情况
	 * @param currentPlatformSales
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public int updateCurrentPlatformSales(CurrentPlatformSales currentPlatformSales);
	/**
	 * 根据CpsId删除目前平台销售情况
	 * @param cpsId
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public int deleteCurrentPlatformSalesByCpsId(Long cpsId);
	/**
	 * 根据CpsId查询目前平台销售情况
	 * @param cpsId
	 * @return
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public CurrentPlatformSales queryCurrentPlatformSalesByCpsId(Long cpsId);
	/**
	 * 查询目前平台销售情况列表
	 * @param pagination 分页对象
	 * @return  目前平台销售情况列表
	 * @author zhongp
	 * @date 2017-12-25 00:57:33
	 */
	public List<CurrentPlatformSales> queryCurrentPlatformSalesList(Pagination<CurrentPlatformSales> pagination);

	/**
	 * 根据类型 取得当前平台排序
	 * @param type
	 * @param gtypes
	 * @return
	 */
	public List<CurrentPlatformSales> queryCurrentPlatformSalesByGtype(@Param("type") Long type, @Param("gtypes") Set<Long> gtypes);

}
