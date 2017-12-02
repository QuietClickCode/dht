
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.UserShared;
import java.util.Map;
/**
 * 描述：受邀记录表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-01 11:21:06
 */
public interface UserSharedService {

	/**
	 * 添加受邀记录表
	 * @param userShared
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 11:21:06
	 */
	public boolean saveUserShared(UserShared userShared);
	/**
	 * 编辑受邀记录表
	 * @param userShared
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateUserShared(UserShared userShared);
	/**
	 * 根据id查询受邀记录表
	 * @param usdId
	 * @return userShared
	 * @author fanghui
	 * @date 2017-12-01 11:21:06
	 */
	public UserShared queryUserSharedByUsdId(Long usdId);
	/**
	 * 查询受邀记录表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-01 11:21:06
	 */
	public Pagination<UserShared> queryUserSharedList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据usdId删除受邀记录表
	 * @param usdId
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 11:21:06
	 */
	public boolean deleteUserSharedByUsdId(Long usdId);

}


