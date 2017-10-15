
package com.retailers.dht.common.service;

import com.retailers.dht.common.entity.FloorManage;
import com.retailers.dht.common.vo.FloorManageVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;
/**
 * 描述：主页楼层表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 14:20:57
 */
public interface FloorManageService {

	/**
	 * 添加主页楼层表
	 * @param floorManage
	 * @return
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public boolean saveFloorManage(FloorManage floorManage);
	/**
	 * 编辑主页楼层表
	 * @param floorManage
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateFloorManage(FloorManage floorManage);
	/**
	 * 根据id查询主页楼层表
	 * @param flId
	 * @return floorManage
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public FloorManage queryFloorManageByFlId(Long flId);
	/**
	 * 查询主页楼层表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public Pagination<FloorManage> queryFloorManageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据flId删除主页楼层表
	 * @param flId
	 * @return
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public boolean deleteFloorManageByFlId(Long flId);

	public List<FloorManage> queryFloorsList();

	public List<FloorManageVo> queryFloorTree();

	public List<FloorManageVo> queryFloorNode(Long flId);

	public List<FloorManage> deleteFloorNode(Long flid);

}


