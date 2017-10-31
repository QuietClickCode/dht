package com.retailers.wx.common.dao;

import com.retailers.mybatis.pagination.Pagination;
import com.retailers.wx.common.entity.WxManager;

import java.util.List;

/**
 * 描述：公众号管理DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 20:26:22
 */
public interface WxManagerMapper {

	/**
	 * 添加公众号管理
	 * @param wxManager
	 * @return
	 * @author zhongp
	 * @date 2017-10-30 20:26:22
	 */
	public int saveWxManager(WxManager wxManager);
	/**
	 * 编辑公众号管理
	 * @param wxManager
	 * @return
	 * @author zhongp
	 * @date 2017-10-30 20:26:22
	 */
	public int updateWxManager(WxManager wxManager);
	/**
	 * 根据WxId删除公众号管理
	 * @param wxId
	 * @return
	 * @author zhongp
	 * @date 2017-10-30 20:26:22
	 */
	public int deleteWxManagerByWxId(Long wxId);
	/**
	 * 根据WxId查询公众号管理
	 * @param wxId
	 * @return
	 * @author zhongp
	 * @date 2017-10-30 20:26:22
	 */
	public WxManager queryWxManagerByWxId(Long wxId);
	/**
	 * 查询公众号管理列表
	 * @param pagination 分页对象
	 * @return  公众号管理列表
	 * @author zhongp
	 * @date 2017-10-30 20:26:22
	 */
	public List<WxManager> queryWxManagerList(Pagination<WxManager> pagination);

    /**
     * 取得当前使用的微信公众号
	 * @return
     */
	public WxManager queryCurUsedWx();
}
