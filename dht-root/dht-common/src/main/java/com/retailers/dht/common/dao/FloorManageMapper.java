package com.retailers.dht.common.dao;

import com.retailers.dht.common.entity.FloorManage;
import com.retailers.dht.common.vo.FloorManageVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
/**
 * 描述：主页楼层表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-12 14:20:57
 */
public interface FloorManageMapper {

	/**
	 * 添加主页楼层表
	 * @param floorManage
	 * @return
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public int saveFloorManage(FloorManage floorManage);
	/**
	 * 编辑主页楼层表
	 * @param floorManage
	 * @return
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public int updateFloorManage(FloorManage floorManage);
	/**
	 * 根据FlId删除主页楼层表
	 * @param flId
	 * @return
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public int deleteFloorManageByFlId(Long flId);
	/**
	 * 根据FlId查询主页楼层表
	 * @param flId
	 * @return
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public FloorManage queryFloorManageByFlId(Long flId);
	/**
	 * 查询主页楼层表列表
	 * @param pagination 分页对象
	 * @return  主页楼层表列表
	 * @author wangjue
	 * @date 2017-10-12 14:20:57
	 */
	public List<FloorManage> queryFloorManageList(Pagination<FloorManage> pagination);


	/*
	* 获取所有的楼层
	* */
	public List<FloorManage> queryFloorsList();

	public List<FloorManageVo> queryFloorTree();

	public List<FloorManageVo> queryFloorNode(Long menuId);

	public List<FloorManage> queryParent(Long flId);
}
