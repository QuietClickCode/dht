
package com.retailers.sbj.common.service.impl;

import com.retailers.sbj.common.dao.WxAuthUserMapper;
import com.retailers.sbj.common.entity.WxAuthUser;
import com.retailers.sbj.common.service.WxAuthUserService;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：微信用户表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-25 16:08:40
 */
@Service("wxauthuserService")
public class WxAuthUserServiceImpl implements WxAuthUserService {
	@Autowired
	private WxAuthUserMapper wxAuthUserMapper;
	public WxAuthUser saveWxAuthUser(WxAuthUser wxAuthUser) {
		int status = wxAuthUserMapper.saveWxAuthUser(wxAuthUser);
		return status == 1 ? wxAuthUser : null;
	}
	public boolean updateWxAuthUser(WxAuthUser wxAuthUser) {
		int status = wxAuthUserMapper.updateWxAuthUser(wxAuthUser);
		return status == 1 ? true : false;
	}
	public WxAuthUser queryWxAuthUserByWauId(Long wauId) {
		return wxAuthUserMapper.queryWxAuthUserByWauId(wauId);
	}

	public Pagination<WxAuthUser> queryWxAuthUserList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<WxAuthUser> page = new Pagination<WxAuthUser>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WxAuthUser> list = wxAuthUserMapper.queryWxAuthUserList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteWxAuthUserByWauId(Long wauId) {
		int status = wxAuthUserMapper.deleteWxAuthUserByWauId(wauId);
		return status == 1 ? true : false;
	}

	public List<WxAuthUser> queryWxAuthUserListByParams(Map<String, Object> params){
		return wxAuthUserMapper.queryWxAuthUserListByParams(params);
	}
}

