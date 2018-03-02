package com.retailers.sbj.common.dao;
import com.retailers.sbj.common.entity.HouseTypeManage;
import com.retailers.sbj.common.vo.HouseTypeManageVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：户型表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-18 11:36:10
 */
public interface HouseTypeManageMapper {

	/**
	 * 添加户型表
	 * @param houseTypeManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public int saveHouseTypeManage(HouseTypeManage houseTypeManage);
	/**
	 * 编辑户型表
	 * @param houseTypeManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public int updateHouseTypeManage(HouseTypeManage houseTypeManage);
	/**
	 * 根据HtId删除户型表
	 * @param htId
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public int deleteHouseTypeManageByHtId(Long htId);
	/**
	 * 根据HtId查询户型表
	 * @param htId
	 * @return
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public HouseTypeManage queryHouseTypeManageByHtId(Long htId);
	/**
	 * 查询户型表列表
	 * @param pagination 分页对象
	 * @return  户型表列表
	 * @author wangjue
	 * @date 2017-12-18 11:36:10
	 */
	public List<HouseTypeManageVo> queryHouseTypeManageList(Pagination<HouseTypeManageVo> pagination);

	public List<HouseTypeManage> queryHouseTypeManageByHtIds(@Param("htIds") List<Long> htIds);

	public List<HouseTypeManageVo> queryHourseTypeManageVoWithUid(Pagination<HouseTypeManageVo> pagination);

	public List<HouseTypeManage> queryAllHouseType(List<Integer> list);
}
