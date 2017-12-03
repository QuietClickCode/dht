package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.UserShared;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：受邀记录表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-01 13:25:27
 */
public interface UserSharedMapper {

	/**
	 * 添加受邀记录表
	 * @param userShared
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 13:25:27
	 */
	public int saveUserShared(UserShared userShared);
	/**
	 * 编辑受邀记录表
	 * @param userShared
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 13:25:27
	 */
	public int updateUserShared(UserShared userShared);
	/**
	 * 根据UsdId删除受邀记录表
	 * @param usdId
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 13:25:27
	 */
	public int deleteUserSharedByUsdId(Long usdId);
	/**
	 * 根据UsdId查询受邀记录表
	 * @param usdId
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 13:25:27
	 */
	public UserShared queryUserSharedByUsdId(Long usdId);
	/**
	 * 查询受邀记录表列表
	 * @param pagination 分页对象
	 * @return  受邀记录表列表
	 * @author fanghui
	 * @date 2017-12-01 13:25:27
	 */
	public List<UserShared> queryUserSharedList(Pagination<UserShared> pagination);

}
