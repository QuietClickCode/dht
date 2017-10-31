package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.City;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：城市表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 11:21:14
 */
public interface CityMapper {

	/**
	 * 添加城市表
	 * @param city
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public int saveCity(City city);
	/**
	 * 编辑城市表
	 * @param city
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public int updateCity(City city);
	/**
	 * 根据Cityid删除城市表
	 * @param cityid
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public int deleteCityByCityid(Long cityid);
	/**
	 * 根据Cityid查询城市表
	 * @param cityid
	 * @return
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public City queryCityByCityid(Long cityid);
	/**
	 * 查询城市表列表
	 * @param pagination 分页对象
	 * @return  城市表列表
	 * @author fanghui
	 * @date 2017-10-31 11:21:14
	 */
	public List<City> queryCityList(Pagination<City> pagination);

}
