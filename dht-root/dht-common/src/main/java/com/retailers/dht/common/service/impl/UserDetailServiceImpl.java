
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.UserDetail;
import com.retailers.dht.common.dao.UserDetailMapper;
import com.retailers.dht.common.service.UserDetailService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：用户信息详情表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-02-07 17:24:33
 */
@Service("userdetailService")
public class UserDetailServiceImpl implements UserDetailService {
	@Autowired
	private UserDetailMapper userDetailMapper;
	public boolean saveUserDetail(UserDetail userDetail) {
		int status = userDetailMapper.saveUserDetail(userDetail);
		return status == 1 ? true : false;
	}
	public boolean updateUserDetail(UserDetail userDetail) {
		int status = userDetailMapper.updateUserDetail(userDetail);
		return status == 1 ? true : false;
	}
	public UserDetail queryUserDetailByUdId(Long udId) {
		return userDetailMapper.queryUserDetailByUdId(udId);
	}

	public Pagination<UserDetail> queryUserDetailList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<UserDetail> page = new Pagination<UserDetail>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<UserDetail> list = userDetailMapper.queryUserDetailList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteUserDetailByUdId(Long udId) {
		int status = userDetailMapper.deleteUserDetailByUdId(udId);
		return status == 1 ? true : false;
	}
}

