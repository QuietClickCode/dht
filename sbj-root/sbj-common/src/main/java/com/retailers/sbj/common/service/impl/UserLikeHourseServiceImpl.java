
package com.retailers.sbj.common.service.impl;

import com.retailers.sbj.common.dao.UserLikeHourseMapper;
import com.retailers.sbj.common.entity.UserLikeHourse;
import com.retailers.sbj.common.service.UserLikeHourseService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述：客户户型关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-31 22:12:55
 */
@Service("userlikehourseService")
public class UserLikeHourseServiceImpl implements UserLikeHourseService {
	@Autowired
	private UserLikeHourseMapper userLikeHourseMapper;
	public boolean saveUserLikeHourse(UserLikeHourse userLikeHourse) {
		Long uid = userLikeHourse.getUid();
		Long hid = userLikeHourse.getHid();
		Long isLike = userLikeHourse.getIsLike();
		Map params = new HashMap();
		params.put("isDelete",0L);
		params.put("uid",uid);
		params.put("hid",hid);
		List<UserLikeHourse> list = queryUserLikeHourseList(params,1,1).getData();
		int status =0;
		if(ObjectUtils.isEmpty(list)){
			status = userLikeHourseMapper.saveUserLikeHourse(userLikeHourse);
		}else{
			UserLikeHourse userLikeHourse1 = list.get(0);
			if(isLike!=userLikeHourse1.getIsLike()){
				userLikeHourse1.setIsLike(isLike);
				status = userLikeHourseMapper.updateUserLikeHourse(userLikeHourse1);
			}else{
				status = 1;
			}
		}
		return status == 1 ? true : false;
	}
	public boolean updateUserLikeHourse(UserLikeHourse userLikeHourse) {
		int status = userLikeHourseMapper.updateUserLikeHourse(userLikeHourse);
		return status == 1 ? true : false;
	}
	public UserLikeHourse queryUserLikeHourseByUlhId(Long ulhId) {
		return userLikeHourseMapper.queryUserLikeHourseByUlhId(ulhId);
	}

	public Pagination<UserLikeHourse> queryUserLikeHourseList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<UserLikeHourse> page = new Pagination<UserLikeHourse>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<UserLikeHourse> list = userLikeHourseMapper.queryUserLikeHourseList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteUserLikeHourseByUlhId(Long ulhId) {
		int status = userLikeHourseMapper.deleteUserLikeHourseByUlhId(ulhId);
		return status == 1 ? true : false;
	}
}

