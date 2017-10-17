package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.HomeNavigation;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：主页导航表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 14:58:24
 */
public interface HomeNavigationMapper {

	/**
	 * 添加主页导航表
	 * @param homeNavigation
	 * @return
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public int saveHomeNavigation(HomeNavigation homeNavigation);
	/**
	 * 编辑主页导航表
	 * @param homeNavigation
	 * @return
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public int updateHomeNavigation(HomeNavigation homeNavigation);
	/**
	 * 根据HnId删除主页导航表
	 * @param hnId
	 * @return
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public int deleteHomeNavigationByHnId(Long hnId);
	/**
	 * 根据HnId查询主页导航表
	 * @param hnId
	 * @return
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public HomeNavigation queryHomeNavigationByHnId(Long hnId);
	/**
	 * 查询主页导航表列表
	 * @param pagination 分页对象
	 * @return  主页导航表列表
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public List<HomeNavigation> queryHomeNavigationList(Pagination<HomeNavigation> pagination);

}
