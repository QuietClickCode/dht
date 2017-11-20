package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.CityArea;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：城市地区表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 15:27:04
 */
public interface CityAreaMapper {

	/**
	 * 添加城市地区表
	 * @param cityArea
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:27:04
	 */
	public int saveCityArea(CityArea cityArea);
	/**
	 * 编辑城市地区表
	 * @param cityArea
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:27:04
	 */
	public int updateCityArea(CityArea cityArea);
	/**
	 * 根据TcityAreaId删除城市地区表
	 * @param tcityAreaId
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:27:04
	 */
	public int deleteCityAreaByTcityAreaId(Long tcityAreaId);
	/**
	 * 根据TcityAreaId查询城市地区表
	 * @param tcityAreaId
	 * @return
	 * @author fanghui
	 * @date 2017-11-20 15:27:04
	 */
	public CityArea queryCityAreaByTcityAreaId(Long tcityAreaId);
	/**
	 * 查询城市地区表列表
	 * @param pagination 分页对象
	 * @return  城市地区表列表
	 * @author fanghui
	 * @date 2017-11-20 15:27:04
	 */
	public List<CityArea> queryCityAreaList(Pagination<CityArea> pagination);

}
