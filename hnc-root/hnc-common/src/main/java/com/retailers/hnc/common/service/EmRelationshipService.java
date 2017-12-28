
package com.retailers.hnc.common.service;
import com.retailers.hnc.common.vo.EmRelationshipVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.EmRelationship;

import java.util.List;
import java.util.Map;
/**
 * 描述：开盘和置业顾问关系表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 10:35:13
 */
public interface EmRelationshipService {

	/**
	 * 添加开盘和置业顾问关系表
	 * @param emRelationship
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 10:35:13
	 */
	public boolean saveEmRelationship(EmRelationship emRelationship);
	/**
	 * 编辑开盘和置业顾问关系表
	 * @param emRelationship
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateEmRelationship(EmRelationship emRelationship);
	/**
	 * 根据id查询开盘和置业顾问关系表
	 * @param erId
	 * @return emRelationship
	 * @author wangjue
	 * @date 2017-12-28 10:35:13
	 */
	public EmRelationship queryEmRelationshipByErId(Long erId);
	/**
	 * 查询开盘和置业顾问关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-28 10:35:13
	 */
	public Pagination<EmRelationship> queryEmRelationshipList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据erId删除开盘和置业顾问关系表
	 * @param erId
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 10:35:13
	 */
	public boolean deleteEmRelationshipByErId(Long erId);

	public List<EmRelationshipVo> queryEmRelationshipVoList();

	public List<EmRelationshipVo> queryEmployeeTree(List<EmRelationshipVo> relationshipVos);

}


