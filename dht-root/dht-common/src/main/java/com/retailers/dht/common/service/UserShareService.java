
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.UserShare;
import java.util.Map;
/**
 * 描述：分享记录表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-01 11:20:50
 */
public interface UserShareService {

	/**
	 * 添加分享记录表
	 * @param userShare
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public boolean saveUserShare(UserShare userShare);
	/**
	 * 编辑分享记录表
	 * @param userShare
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateUserShare(UserShare userShare);
	/**
	 * 根据id查询分享记录表
	 * @param usId
	 * @return userShare
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public UserShare queryUserShareByUsId(Long usId);
	/**
	 * 查询分享记录表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public Pagination<UserShare> queryUserShareList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据usId删除分享记录表
	 * @param usId
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public boolean deleteUserShareByUsId(Long usId);

}


