
package com.retailers.wx.common.service;

import com.retailers.mybatis.pagination.Pagination;
import com.retailers.wx.common.entity.WxManager;

import java.util.Map;

/**
 * 描述：公众号管理Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 20:28:34
 */
public interface WxManagerService {

	/**
	 * 添加公众号管理
	 * @param wxManager
	 * @return
	 * @author zhongp
	 * @date 2017-10-30 20:28:34
	 */
	public boolean saveWxManager(WxManager wxManager);
	/**
	 * 编辑公众号管理
	 * @param wxManager
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateWxManager(WxManager wxManager);
	/**
	 * 根据id查询公众号管理
	 * @param wxId
	 * @return wxManager
	 * @author zhongp
	 * @date 2017-10-30 20:28:34
	 */
	public WxManager queryWxManagerByWxId(Long wxId);
	/**
	 * 查询公众号管理列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-10-30 20:28:34
	 */
	public Pagination<WxManager> queryWxManagerList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据wxId删除公众号管理
	 * @param wxId
	 * @return
	 * @author zhongp
	 * @date 2017-10-30 20:28:34
	 */
	public boolean deleteWxManagerByWxId(Long wxId);

    /**
     * 取得当前使用的微信公众号
	 * @return
     */
	public WxManager queryCurUsedWx();

}


