
package com.retailers.hnc.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.FloorRelationship;
import java.util.Map;
/**
 * 描述：楼栋关系表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-15 14:22:08
 */
public interface FloorRelationshipService {

	/**
	 * 添加楼栋关系表
	 * @param floorRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public boolean saveFloorRelationship(FloorRelationship floorRelationship);
	/**
	 * 编辑楼栋关系表
	 * @param floorRelationship
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateFloorRelationship(FloorRelationship floorRelationship);
	/**
	 * 根据id查询楼栋关系表
	 * @param frId
	 * @return floorRelationship
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public FloorRelationship queryFloorRelationshipByFrId(Long frId);
	/**
	 * 查询楼栋关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public Pagination<FloorRelationship> queryFloorRelationshipList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据frId删除楼栋关系表
	 * @param frId
	 * @return
	 * @author wangjue
	 * @date 2017-12-15 14:22:08
	 */
	public boolean deleteFloorRelationshipByFrId(Long frId);

}


