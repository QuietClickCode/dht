package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.UserLikeHourse;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：客户户型关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-31 22:12:55
 */
public interface UserLikeHourseMapper {

	/**
	 * 添加客户户型关系表
	 * @param userLikeHourse
	 * @return
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public int saveUserLikeHourse(UserLikeHourse userLikeHourse);
	/**
	 * 编辑客户户型关系表
	 * @param userLikeHourse
	 * @return
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public int updateUserLikeHourse(UserLikeHourse userLikeHourse);
	/**
	 * 根据UlhId删除客户户型关系表
	 * @param ulhId
	 * @return
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public int deleteUserLikeHourseByUlhId(Long ulhId);
	/**
	 * 根据UlhId查询客户户型关系表
	 * @param ulhId
	 * @return
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public UserLikeHourse queryUserLikeHourseByUlhId(Long ulhId);
	/**
	 * 查询客户户型关系表列表
	 * @param pagination 分页对象
	 * @return  客户户型关系表列表
	 * @author fanghui
	 * @date 2017-12-31 22:12:55
	 */
	public List<UserLikeHourse> queryUserLikeHourseList(Pagination<UserLikeHourse> pagination);

}
