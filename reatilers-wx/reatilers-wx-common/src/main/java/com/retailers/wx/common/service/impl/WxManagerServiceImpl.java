
package com.retailers.wx.common.service.impl;

import com.retailers.mybatis.pagination.Pagination;
import com.retailers.wx.common.dao.WxManagerMapper;
import com.retailers.wx.common.enm.WXAccountEnum;
import com.retailers.wx.common.entity.WxManager;
import com.retailers.wx.common.service.WxManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：公众号管理Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-30 20:28:34
 */
@Service("wxmanagerService")
public class WxManagerServiceImpl implements WxManagerService {
	@Autowired
	private WxManagerMapper wxManagerMapper;
	public boolean saveWxManager(WxManager wxManager) {
		int status = wxManagerMapper.saveWxManager(wxManager);
		return status == 1 ? true : false;
	}
	public boolean updateWxManager(WxManager wxManager) {
		int status = wxManagerMapper.updateWxManager(wxManager);
		return status == 1 ? true : false;
	}
	public WxManager queryWxManagerByWxId(Long wxId) {
		return wxManagerMapper.queryWxManagerByWxId(wxId);
	}

	public Pagination<WxManager> queryWxManagerList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<WxManager> page = new Pagination<WxManager>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WxManager> list = wxManagerMapper.queryWxManagerList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteWxManagerByWxId(Long wxId) {
		int status = wxManagerMapper.deleteWxManagerByWxId(wxId);
		return status == 1 ? true : false;
	}

    /**
     * 取得当前使用的微信公众号
	 * @return
     */
	public WxManager queryCurUsedWx(WXAccountEnum wxAccountEnum) {
		return wxManagerMapper.queryCurUsedWx(wxAccountEnum.getType());
	}
}

