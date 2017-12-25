package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.WxAuthUser;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：微信用户表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 16:08:40
 */
public interface WxAuthUserMapper {

	/**
	 * 添加微信用户表
	 * @param wxAuthUser
	 * @return
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public int saveWxAuthUser(WxAuthUser wxAuthUser);
	/**
	 * 编辑微信用户表
	 * @param wxAuthUser
	 * @return
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public int updateWxAuthUser(WxAuthUser wxAuthUser);
	/**
	 * 根据WauId删除微信用户表
	 * @param wauId
	 * @return
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public int deleteWxAuthUserByWauId(Long wauId);
	/**
	 * 根据WauId查询微信用户表
	 * @param wauId
	 * @return
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public WxAuthUser queryWxAuthUserByWauId(Long wauId);
	/**
	 * 查询微信用户表列表
	 * @param pagination 分页对象
	 * @return  微信用户表列表
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public List<WxAuthUser> queryWxAuthUserList(Pagination<WxAuthUser> pagination);

}
