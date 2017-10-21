
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.HomeAdvertising;
import com.retailers.dht.common.dao.HomeAdvertisingMapper;
import com.retailers.dht.common.service.HomeAdvertisingService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：首页广告设置表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 17:07:09
 */
@Service("homeadvertisingService")
public class HomeAdvertisingServiceImpl implements HomeAdvertisingService {
	@Autowired
	private HomeAdvertisingMapper homeAdvertisingMapper;
	public boolean saveHomeAdvertising(HomeAdvertising homeAdvertising) {
		int status = homeAdvertisingMapper.saveHomeAdvertising(homeAdvertising);
		return status == 1 ? true : false;
	}
	public boolean updateHomeAdvertising(HomeAdvertising homeAdvertising) {
		int status = homeAdvertisingMapper.updateHomeAdvertising(homeAdvertising);
		return status == 1 ? true : false;
	}
	public HomeAdvertising queryHomeAdvertisingByHaId(Long haId) {
		return homeAdvertisingMapper.queryHomeAdvertisingByHaId(haId);
	}

	public Pagination<HomeAdvertising> queryHomeAdvertisingList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<HomeAdvertising> page = new Pagination<HomeAdvertising>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<HomeAdvertising> list = homeAdvertisingMapper.queryHomeAdvertisingList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteHomeAdvertisingByHaId(Long haId) {
		int status = homeAdvertisingMapper.deleteHomeAdvertisingByHaId(haId);
		return status == 1 ? true : false;
	}
}

