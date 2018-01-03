
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.CheckUser;
import com.retailers.hnc.common.dao.CheckUserMapper;
import com.retailers.hnc.common.service.CheckUserService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：客户通过审核记录表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-03 23:28:11
 */
@Service("checkuserService")
public class CheckUserServiceImpl implements CheckUserService {
	@Autowired
	private CheckUserMapper checkUserMapper;
	public boolean saveCheckUser(CheckUser checkUser) {
		int status = checkUserMapper.saveCheckUser(checkUser);
		return status == 1 ? true : false;
	}
	public boolean updateCheckUser(CheckUser checkUser) {
		int status = checkUserMapper.updateCheckUser(checkUser);
		return status == 1 ? true : false;
	}
	public CheckUser queryCheckUserByCuId(Long cuId) {
		return checkUserMapper.queryCheckUserByCuId(cuId);
	}

	public Pagination<CheckUser> queryCheckUserList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<CheckUser> page = new Pagination<CheckUser>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CheckUser> list = checkUserMapper.queryCheckUserList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCheckUserByCuId(Long cuId) {
		int status = checkUserMapper.deleteCheckUserByCuId(cuId);
		return status == 1 ? true : false;
	}
	public Map checkUser(String validateCode,Long eid){



		return  null;
	}
}

