
package com.retailers.dht.common.service.impl;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.dao.LogUserCardPackageMapper;
import com.retailers.dht.common.entity.LogUserCardPackage;
import com.retailers.dht.common.entity.UserCardPackage;
import com.retailers.dht.common.dao.UserCardPackageMapper;
import com.retailers.dht.common.service.UserCardPackageService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：用户钱包，积分Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-22 23:13:08
 */
@Service("usercardpackageService")
public class UserCardPackageServiceImpl implements UserCardPackageService {
	@Autowired
	private UserCardPackageMapper userCardPackageMapper;
	@Autowired
	private LogUserCardPackageMapper logUserCardPackageMapper;

	public boolean saveUserCardPackage(UserCardPackage userCardPackage) {
		int status = userCardPackageMapper.saveUserCardPackage(userCardPackage);
		return status == 1 ? true : false;
	}
//	public boolean updateUserCardPackage(UserCardPackage userCardPackage) {
//		int status = userCardPackageMapper.updateUserCardPackage(userCardPackage);
//		return status == 1 ? true : false;
//	}
	public UserCardPackage queryUserCardPackageById(Long id) {
		return userCardPackageMapper.queryUserCardPackageById(id);
	}

	public Pagination<UserCardPackage> queryUserCardPackageList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<UserCardPackage> page = new Pagination<UserCardPackage>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<UserCardPackage> list = userCardPackageMapper.queryUserCardPackageList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteUserCardPackageById(Long id) {
		int status = userCardPackageMapper.deleteUserCardPackageById(id);
		return status == 1 ? true : false;
	}

	public UserCardPackage queryUserCardPackage(Long uid){
		UserCardPackage rtn=userCardPackageMapper.queryUserCardPackageById(uid);
		if(ObjectUtils.isEmpty(rtn)){
			rtn=new UserCardPackage();
			rtn.setUcurIntegral(SystemConstant.SYSTEM_DEFAULT_LONG_VAL);
			rtn.setUcurWallet(SystemConstant.SYSTEM_DEFAULT_LONG_VAL);
		}
		return rtn;
	}

	/**
	 *添加用户日志（钱包日志累计返现日志）
	 * @param uid 用户id
	 * @param type 操作类型
	 * @param orderId 订单id
	 * @param val 值
	 * @param curVal 当前值
	 * @param remark 备注
	 * @param curDate 当前日期
	 */
	public void addUserCardPackageLog(Long uid, int type, Long orderId, Long val, Long curVal, String remark, Date curDate) {
		//添加用户钱包日志
		LogUserCardPackage lucp=new LogUserCardPackage();
		lucp.setUid(uid);
		lucp.setType(type);
		lucp.setRelationOrderId(orderId);
		lucp.setVal(val);
		lucp.setCurVal(curVal);
		lucp.setRemark(remark);
		lucp.setCreateTime(curDate);
		logUserCardPackageMapper.saveLogUserCardPackage(lucp);
	}

	/**
	 * queryUserCardPackages
	 * @param cbUids
	 * @return
	 */
	public List<UserCardPackage> queryUserCardPackages(Set<Long> cbUids) {
		return userCardPackageMapper.queryUserCardPackages(cbUids);
	}
}

