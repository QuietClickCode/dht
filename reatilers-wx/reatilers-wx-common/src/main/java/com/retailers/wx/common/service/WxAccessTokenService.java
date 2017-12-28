
package com.retailers.wx.common.service;

import com.retailers.mybatis.pagination.Pagination;
import com.retailers.wx.common.entity.WxAccessToken;

import java.util.Map;

/**
 * 描述：微信认证tokenService
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 22:05:24
 */
public interface WxAccessTokenService {

	/**
	 * 添加微信认证token
	 * @param wxAccessToken
	 * @return
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public boolean saveWxAccessToken(WxAccessToken wxAccessToken);
	/**
	 * 编辑微信认证token
	 * @param wxAccessToken
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateWxAccessToken(WxAccessToken wxAccessToken);
	/**
	 * 根据id查询微信认证token
	 * @param watId
	 * @return wxAccessToken
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public WxAccessToken queryWxAccessTokenByWatId(Long watId);
	/**
	 * 查询微信认证token列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public Pagination<WxAccessToken> queryWxAccessTokenList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据watId删除微信认证token
	 * @param watId
	 * @return
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public boolean deleteWxAccessTokenByWatId(Long watId);

    /**
     * 初始化token信息
	 */
	public void initWxToken();

	/**
	 * 初始化微信配置信息
	 */
	public void initWxConfig();

}


