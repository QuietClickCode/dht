
package com.retailers.sbj.common.service;

import com.retailers.sbj.common.entity.WxAuthUser;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;

/**
 * 描述：微信用户表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 16:08:40
 */
public interface WxAuthUserService {

	/**
	 * 添加微信用户表
	 * @param wxAuthUser
	 * @return
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public WxAuthUser saveWxAuthUser(WxAuthUser wxAuthUser);
	/**
	 * 编辑微信用户表
	 * @param wxAuthUser
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateWxAuthUser(WxAuthUser wxAuthUser);
	/**
	 * 根据id查询微信用户表
	 * @param wauId
	 * @return wxAuthUser
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public WxAuthUser queryWxAuthUserByWauId(Long wauId);
	/**
	 * 查询微信用户表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public Pagination<WxAuthUser> queryWxAuthUserList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据wauId删除微信用户表
	 * @param wauId
	 * @return
	 * @author fanghui
	 * @date 2017-12-25 16:08:40
	 */
	public boolean deleteWxAuthUserByWauId(Long wauId);

	public List<WxAuthUser> queryWxAuthUserListByParams(Map<String, Object> params);
}


