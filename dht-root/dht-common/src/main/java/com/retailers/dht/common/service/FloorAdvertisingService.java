
package com.retailers.dht.common.service;

import com.retailers.dht.common.entity.FloorAdvertising;
import com.retailers.dht.common.vo.FloorAdvertisingVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;
/**
 * 描述：楼层广告设置Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 15:15:44
 */
public interface FloorAdvertisingService {

	/**
	 * 添加楼层广告设置
	 * @param floorAdvertising
	 * @return
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public boolean saveFloorAdvertising(FloorAdvertising floorAdvertising);
	/**
	 * 编辑楼层广告设置
	 * @param floorAdvertising
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateFloorAdvertising(FloorAdvertising floorAdvertising);
	/**
	 * 根据id查询楼层广告设置
	 * @param faId
	 * @return floorAdvertising
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public FloorAdvertising queryFloorAdvertisingByFaId(Long faId);
	/**
	 * 查询楼层广告设置列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public Pagination<FloorAdvertising> queryFloorAdvertisingList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据faId删除楼层广告设置
	 * @param faId
	 * @return
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public boolean deleteFloorAdvertisingByFaId(Long faId);

	public List<FloorAdvertisingVo> queryFloorAdvertisingTree();

	public List<FloorAdvertisingVo> queryGoodsClassificationList();

	public List<FloorAdvertisingVo> queryFloorAdvertisingVo();

	public List<FloorAdvertisingVo> queryFloorAdvertisingListByGclassId(Map params);
}


