
package com.retailers.dht.common.service;

import com.retailers.dht.common.entity.HomeAdvertising;
import com.retailers.dht.common.vo.HomeAdvertisingVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.Map;
/**
 * 描述：首页广告设置表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-20 17:07:09
 */
public interface HomeAdvertisingService {

	/**
	 * 添加首页广告设置表
	 * @param homeAdvertising
	 * @return
	 * @author wangjue
	 * @date 2017-10-20 17:07:09
	 */
	public boolean saveHomeAdvertising(HomeAdvertising homeAdvertising);
	/**
	 * 编辑首页广告设置表
	 * @param homeAdvertising
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateHomeAdvertising(HomeAdvertising homeAdvertising);
	/**
	 * 根据id查询首页广告设置表
	 * @param haId
	 * @return homeAdvertising
	 * @author wangjue
	 * @date 2017-10-20 17:07:09
	 */
	public HomeAdvertising queryHomeAdvertisingByHaId(Long haId);
	/**
	 * 查询首页广告设置表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-10-20 17:07:09
	 */
	public Pagination<HomeAdvertisingVo> queryHomeAdvertisingList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据haId删除首页广告设置表
	 * @param haId
	 * @return
	 * @author wangjue
	 * @date 2017-10-20 17:07:09
	 */
	public boolean deleteHomeAdvertisingByHaId(Long haId);


}


