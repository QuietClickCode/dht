package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.vo.FloorManageVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：楼栋管理DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-15 14:23:58
 */
public interface FloorManageMapper {

	/**
	 * 添加楼栋管理
	 * @param floorManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public int saveFloorManage(FloorManage floorManage);
	/**
	 * 编辑楼栋管理
	 * @param floorManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public int updateFloorManage(FloorManage floorManage);
	/**
	 * 根据FmId删除楼栋管理
	 * @param fmId
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public int deleteFloorManageByFmId(Long fmId);
	/**
	 * 根据FmId查询楼栋管理
	 * @param fmId
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public FloorManage queryFloorManageByFmId(Long fmId);
	/**
	 * 查询楼栋管理列表
	 * @param pagination 分页对象
	 * @return  楼栋管理列表
	 * @author wangjue
	 * @date 2017-12-15 14:23:58
	 */
	public List<FloorManageVo> queryFloorManageList(Pagination<FloorManageVo> pagination);

}
