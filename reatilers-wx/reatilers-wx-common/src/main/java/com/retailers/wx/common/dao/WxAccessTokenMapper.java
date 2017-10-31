package com.retailers.wx.common.dao;

import com.retailers.mybatis.pagination.Pagination;
import com.retailers.wx.common.entity.WxAccessToken;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

/**
 * 描述：微信认证tokenDAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-31 22:05:24
 */
public interface WxAccessTokenMapper {

	/**
	 * 添加微信认证token
	 * @param wxAccessToken
	 * @return
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public int saveWxAccessToken(WxAccessToken wxAccessToken);
	/**
	 * 编辑微信认证token
	 * @param wxAccessToken
	 * @return
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public int updateWxAccessToken(WxAccessToken wxAccessToken);
	/**
	 * 根据WatId删除微信认证token
	 * @param watId
	 * @return
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public int deleteWxAccessTokenByWatId(Long watId);
	/**
	 * 根据WatId查询微信认证token
	 * @param watId
	 * @return
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public WxAccessToken queryWxAccessTokenByWatId(Long watId);
	/**
	 * 查询微信认证token列表
	 * @param pagination 分页对象
	 * @return  微信认证token列表
	 * @author zhongp
	 * @date 2017-10-31 22:05:24
	 */
	public List<WxAccessToken> queryWxAccessTokenList(Pagination<WxAccessToken> pagination);

    /**
     * 根据微信id 取得当前使用token
	 * @param wxId
     * @return
     */
	public WxAccessToken queryCurWxAccessTokenByWxId(@Param("wxId") Long wxId, @Param("curDate") Date curDate);

}
