package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.UserShare;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：分享记录表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-01 11:20:50
 */
public interface UserShareMapper {

	/**
	 * 添加分享记录表
	 * @param userShare
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public int saveUserShare(UserShare userShare);
	/**
	 * 编辑分享记录表
	 * @param userShare
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public int updateUserShare(UserShare userShare);
	/**
	 * 根据UsId删除分享记录表
	 * @param usId
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public int deleteUserShareByUsId(Long usId);
	/**
	 * 根据UsId查询分享记录表
	 * @param usId
	 * @return
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public UserShare queryUserShareByUsId(Long usId);
	/**
	 * 查询分享记录表列表
	 * @param pagination 分页对象
	 * @return  分享记录表列表
	 * @author fanghui
	 * @date 2017-12-01 11:20:50
	 */
	public List<UserShare> queryUserShareList(Pagination<UserShare> pagination);

}
