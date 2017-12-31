
package com.retailers.hnc.common.service;

import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.vo.HouseTypeManageVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;
/**
 * 描述：户型表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-18 11:36:10
 */
public interface HouseTypeManageService {

	/**
	 * 添加户型表
	 * @param houseTypeManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public boolean saveHouseTypeManage(HouseTypeManage houseTypeManage);
	/**
	 * 编辑户型表
	 * @param houseTypeManage
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateHouseTypeManage(HouseTypeManage houseTypeManage);
	/**
	 * 根据id查询户型表
	 * @param htId
	 * @return houseTypeManage
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public HouseTypeManage queryHouseTypeManageByHtId(Long htId);
	/**
	 * 查询户型表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public Pagination<HouseTypeManageVo> queryHouseTypeManageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据htId删除户型表
	 * @param htId
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public boolean deleteHouseTypeManageByHtId(Long htId);

	public boolean addFloorRelationship(List<FloorRelationship> relationships);

	public List<HouseTypeManage> queryHouseTypeManageByHtIds(String htIds);

	public Pagination<HouseTypeManageVo> queryFirstHouseTypeManageList(Map<String, Object> params, int pageNo, int pageSize);

	public List<HouseTypeManageVo> queryHourseTypeManageVoWithUid(Map params,int pageNo,int pageSize);
}


