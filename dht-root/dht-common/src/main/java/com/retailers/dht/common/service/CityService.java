
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.City;
import java.util.Map;
/**
 * 描述：城市表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 11:21:14
 */
public interface CityService {

	/**
	 * 添加城市表
	 * @param city
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public boolean saveCity(City city);
	/**
	 * 编辑城市表
	 * @param city
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateCity(City city);
	/**
	 * 根据id查询城市表
	 * @param cityid
	 * @return city
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public City queryCityByCityid(Long cityid);
	/**
	 * 查询城市表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public Pagination<City> queryCityList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cityid删除城市表
	 * @param cityid
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public boolean deleteCityByCityid(Long cityid);

}


