
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.CurrentPlatformSales;
import com.retailers.dht.common.dao.CurrentPlatformSalesMapper;
import com.retailers.dht.common.service.CurrentPlatformSalesService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：目前平台销售情况Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 00:57:33
 */
@Service("currentplatformsalesService")
public class CurrentPlatformSalesServiceImpl implements CurrentPlatformSalesService {
	@Autowired
	private CurrentPlatformSalesMapper currentPlatformSalesMapper;
	public boolean saveCurrentPlatformSales(CurrentPlatformSales currentPlatformSales) {
		int status = currentPlatformSalesMapper.saveCurrentPlatformSales(currentPlatformSales);
		return status == 1 ? true : false;
	}
	public boolean updateCurrentPlatformSales(CurrentPlatformSales currentPlatformSales) {
		int status = currentPlatformSalesMapper.updateCurrentPlatformSales(currentPlatformSales);
		return status == 1 ? true : false;
	}
	public CurrentPlatformSales queryCurrentPlatformSalesByCpsId(Long cpsId) {
		return currentPlatformSalesMapper.queryCurrentPlatformSalesByCpsId(cpsId);
	}

	public Pagination<CurrentPlatformSales> queryCurrentPlatformSalesList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<CurrentPlatformSales> page = new Pagination<CurrentPlatformSales>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CurrentPlatformSales> list = currentPlatformSalesMapper.queryCurrentPlatformSalesList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCurrentPlatformSalesByCpsId(Long cpsId) {
		int status = currentPlatformSalesMapper.deleteCurrentPlatformSalesByCpsId(cpsId);
		return status == 1 ? true : false;
	}
}

