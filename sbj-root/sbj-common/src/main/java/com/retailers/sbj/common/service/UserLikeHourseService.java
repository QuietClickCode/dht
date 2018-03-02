
package com.retailers.sbj.common.service;

import com.retailers.sbj.common.entity.UserLikeHourse;
import com.retailers.mybatis.pagination.Pagination;

import java.util.Map;

/**
 * 描述：客户户型关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-31 22:12:55
 */
public interface UserLikeHourseService {

	/**
	 * 添加客户户型关系表
	 * @param userLikeHourse
	 * @return
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public boolean saveUserLikeHourse(UserLikeHourse userLikeHourse);
	/**
	 * 编辑客户户型关系表
	 * @param userLikeHourse
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateUserLikeHourse(UserLikeHourse userLikeHourse);
	/**
	 * 根据id查询客户户型关系表
	 * @param ulhId
	 * @return userLikeHourse
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public UserLikeHourse queryUserLikeHourseByUlhId(Long ulhId);
	/**
	 * 查询客户户型关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public Pagination<UserLikeHourse> queryUserLikeHourseList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ulhId删除客户户型关系表
	 * @param ulhId
	 * @return
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public boolean deleteUserLikeHourseByUlhId(Long ulhId);

}


