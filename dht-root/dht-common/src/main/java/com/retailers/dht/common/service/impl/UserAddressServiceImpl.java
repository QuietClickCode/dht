
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.UserAddress;
import com.retailers.dht.common.dao.UserAddressMapper;
import com.retailers.dht.common.service.UserAddressService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.UUIDUtils;
import org.apache.ibatis.ognl.IntHashMap;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：用户收货地址列表Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-20 16:19:49
 */
@Service("useraddressService")
public class UserAddressServiceImpl implements UserAddressService {
	@Autowired
	private UserAddressMapper userAddressMapper;
	public boolean saveUserAddress(UserAddress userAddress) {
		String uuid=UUIDUtils.getUUID();
		userAddress.setUaUuid(uuid);
		userAddress.setIsDelete(SystemConstant.SYS_IS_DELETE_NO);
		userAddress.setIsValida(SystemConstant.SYS_IS_VALID_YES);
		int status = userAddressMapper.saveUserAddress(userAddress);
		return status == 1 ? true : false;
	}
	public boolean updateUserAddress(UserAddress userAddress) {
		int status = userAddressMapper.updateUserAddress(userAddress);
		return status == 1 ? true : false;
	}
	public UserAddress queryUserAddressByUaId(Long uaId) {
		return userAddressMapper.queryUserAddressByUaId(uaId);
	}

	public Pagination<UserAddress> queryUserAddressList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<UserAddress> page = new Pagination<UserAddress>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<UserAddress> list = userAddressMapper.queryUserAddressList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteUserAddressByUaId(Long uaId) {
		int status = userAddressMapper.deleteUserAddressByUaId(uaId);
		return status == 1 ? true : false;
	}

	public Pagination<UserAddress> queryUserAddress(Long userId,int pageNo,int pageSize){
		Map<String,Object> params=new HashMap<String,Object>();
		params.put("uaUid",userId);
		params.put("isValida", SystemConstant.SYS_IS_VALID_YES);
		params.put("isDelete", SystemConstant.SYS_IS_DELETE_NO);
		return  queryUserAddressList(params,pageNo,pageSize);
	}

	public boolean defaultUserAddress(Long userId, Long uaId) throws AppException {
		return false;
	}
}

