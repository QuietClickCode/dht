
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.City;
import com.retailers.dht.common.dao.CityMapper;
import com.retailers.dht.common.service.CityService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：城市表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 11:21:14
 */
@Service("cityService")
public class CityServiceImpl implements CityService {
	@Autowired
	private CityMapper cityMapper;
	public boolean saveCity(City city) {
		int status = cityMapper.saveCity(city);
		return status == 1 ? true : false;
	}
	public boolean updateCity(City city) {
		int status = cityMapper.updateCity(city);
		return status == 1 ? true : false;
	}
	public City queryCityByCityid(Long cityid) {
		return cityMapper.queryCityByCityid(cityid);
	}

	public Pagination<City> queryCityList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<City> page = new Pagination<City>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<City> list = cityMapper.queryCityList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCityByCityid(Long cityid) {
		int status = cityMapper.deleteCityByCityid(cityid);
		return status == 1 ? true : false;
	}
}

