package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.FloorAdvertising;
import com.retailers.dht.common.vo.FloorAdvertisingVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：楼层广告设置DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-10-21 15:15:44
 */
public interface FloorAdvertisingMapper {

	/**
	 * 添加楼层广告设置
	 * @param floorAdvertising
	 * @return
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public int saveFloorAdvertising(FloorAdvertising floorAdvertising);
	/**
	 * 编辑楼层广告设置
	 * @param floorAdvertising
	 * @return
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public int updateFloorAdvertising(FloorAdvertising floorAdvertising);
	/**
	 * 根据FaId删除楼层广告设置
	 * @param faId
	 * @return
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public int deleteFloorAdvertisingByFaId(Long faId);
	/**
	 * 根据FaId查询楼层广告设置
	 * @param faId
	 * @return
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public FloorAdvertising queryFloorAdvertisingByFaId(Long faId);
	/**
	 * 查询楼层广告设置列表
	 * @param pagination 分页对象
	 * @return  楼层广告设置列表
	 * @author wangjue
	 * @date 2017-10-21 15:15:44
	 */
	public List<FloorAdvertising> queryFloorAdvertisingList(Pagination<FloorAdvertising> pagination);

	public List<FloorAdvertisingVo> queryGoodsClassificationList();

	public List<FloorAdvertisingVo> queryFloorAdvertisingVo();
}
