
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.UserShared;
import com.retailers.dht.common.dao.UserSharedMapper;
import com.retailers.dht.common.service.UserSharedService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：受邀记录表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-01 11:21:06
 */
@Service("usersharedService")
public class UserSharedServiceImpl implements UserSharedService {
	@Autowired
	private UserSharedMapper userSharedMapper;
	public boolean saveUserShared(UserShared userShared) {
		int status = userSharedMapper.saveUserShared(userShared);
		return status == 1 ? true : false;
	}
	public boolean updateUserShared(UserShared userShared) {
		int status = userSharedMapper.updateUserShared(userShared);
		return status == 1 ? true : false;
	}
	public UserShared queryUserSharedByUsdId(Long usdId) {
		return userSharedMapper.queryUserSharedByUsdId(usdId);
	}

	public Pagination<UserShared> queryUserSharedList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<UserShared> page = new Pagination<UserShared>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<UserShared> list = userSharedMapper.queryUserSharedList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteUserSharedByUsdId(Long usdId) {
		int status = userSharedMapper.deleteUserSharedByUsdId(usdId);
		return status == 1 ? true : false;
	}
}

