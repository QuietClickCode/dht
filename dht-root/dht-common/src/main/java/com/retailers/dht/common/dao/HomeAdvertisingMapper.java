package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.HomeAdvertising;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：首页广告设置表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-18 16:32:55
 */
public interface HomeAdvertisingMapper {

	/**
	 * 添加首页广告设置表
	 * @param homeAdvertising
	 * @return
	 * @author wangjue
	 * @date 2017-10-18 16:32:55
	 */
	public int saveHomeAdvertising(HomeAdvertising homeAdvertising);
	/**
	 * 编辑首页广告设置表
	 * @param homeAdvertising
	 * @return
	 * @author wangjue
	 * @date 2017-10-18 16:32:55
	 */
	public int updateHomeAdvertising(HomeAdvertising homeAdvertising);
	/**
	 * 根据HaId删除首页广告设置表
	 * @param haId
	 * @return
	 * @author wangjue
	 * @date 2017-10-18 16:32:55
	 */
	public int deleteHomeAdvertisingByHaId(Long haId);
	/**
	 * 根据HaId查询首页广告设置表
	 * @param haId
	 * @return
	 * @author wangjue
	 * @date 2017-10-18 16:32:55
	 */
	public HomeAdvertising queryHomeAdvertisingByHaId(Long haId);
	/**
	 * 查询首页广告设置表列表
	 * @param pagination 分页对象
	 * @return  首页广告设置表列表
	 * @author wangjue
	 * @date 2017-10-18 16:32:55
	 */
	public List<HomeAdvertising> queryHomeAdvertisingList(Pagination<HomeAdvertising> pagination);

}
