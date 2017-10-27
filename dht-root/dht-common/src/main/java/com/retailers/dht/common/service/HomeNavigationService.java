
package com.retailers.dht.common.service;

import com.retailers.dht.common.entity.HomeNavigation;
import com.retailers.dht.common.vo.HomeNavigationVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.Map;
/**
 * 描述：主页导航表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-17 14:58:24
 */
public interface HomeNavigationService {

	/**
	 * 添加主页导航表
	 * @param homeNavigation
	 * @return
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public boolean saveHomeNavigation(HomeNavigation homeNavigation);
	/**
	 * 编辑主页导航表
	 * @param homeNavigation
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateHomeNavigation(HomeNavigation homeNavigation);
	/**
	 * 根据id查询主页导航表
	 * @param hnId
	 * @return homeNavigation
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public HomeNavigation queryHomeNavigationByHnId(Long hnId);
	/**
	 * 查询主页导航表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public Pagination<HomeNavigationVo> queryHomeNavigationList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据hnId删除主页导航表
	 * @param hnId
	 * @return
	 * @author wangjue
	 * @date 2017-10-17 14:58:24
	 */
	public boolean deleteHomeNavigationByHnId(Long hnId);

}


