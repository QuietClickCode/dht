
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.WxAuthUser;
import com.retailers.dht.common.dao.WxAuthUserMapper;
import com.retailers.dht.common.service.WxAuthUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：用户卡包操作日志（钱包，积分）Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:23:16
 */
@Service("wxauthuserService")
public class WxAuthUserServiceImpl implements WxAuthUserService {
	@Autowired
	private WxAuthUserMapper wxAuthUserMapper;
	public boolean saveWxAuthUser(WxAuthUser wxAuthUser) {
		int status = wxAuthUserMapper.saveWxAuthUser(wxAuthUser);
		return status == 1 ? true : false;
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
}

