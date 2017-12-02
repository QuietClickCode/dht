
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.UserShare;
import com.retailers.dht.common.dao.UserShareMapper;
import com.retailers.dht.common.service.UserShareService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：分享记录表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-01 11:20:50
 */
@Service("usershareService")
public class UserShareServiceImpl implements UserShareService {
	@Autowired
	private UserShareMapper userShareMapper;
	public boolean saveUserShare(UserShare userShare) {
		int status = userShareMapper.saveUserShare(userShare);
		return status == 1 ? true : false;
	}
	public boolean updateUserShare(UserShare userShare) {
		int status = userShareMapper.updateUserShare(userShare);
		return status == 1 ? true : false;
	}
	public UserShare queryUserShareByUsId(Long usId) {
		return userShareMapper.queryUserShareByUsId(usId);
	}

	public Pagination<UserShare> queryUserShareList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<UserShare> page = new Pagination<UserShare>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<UserShare> list = userShareMapper.queryUserShareList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteUserShareByUsId(Long usId) {
		int status = userShareMapper.deleteUserShareByUsId(usId);
		return status == 1 ? true : false;
	}
}

