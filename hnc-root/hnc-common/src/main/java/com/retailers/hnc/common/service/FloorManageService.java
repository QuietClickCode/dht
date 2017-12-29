
package com.retailers.hnc.common.service;

import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.vo.FloorManageVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;
/**
 * 描述：楼栋管理Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-15 14:23:58
 */
public interface FloorManageService {

	/**
	 * 添加楼栋管理
	 * @param floorManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public boolean saveFloorManage(FloorManage floorManage);
	/**
	 * 编辑楼栋管理
	 * @param floorManage
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateFloorManage(FloorManage floorManage);
	/**
	 * 根据id查询楼栋管理
	 * @param fmId
	 * @return floorManage
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public FloorManage queryFloorManageByFmId(Long fmId);
	/**
	 * 查询楼栋管理列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public Pagination<FloorManageVo> queryFloorManageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据fmId删除楼栋管理
	 * @param fmId
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public boolean deleteFloorManageByFmId(Long fmId);


	public boolean addFloorRelationship(List<FloorRelationship> relationships);

	public List<FloorManage> queryFloorManageByFmIds(String fids);
}


