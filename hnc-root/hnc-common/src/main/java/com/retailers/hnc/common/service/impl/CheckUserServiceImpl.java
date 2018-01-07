
package com.retailers.hnc.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.CheckUser;
import com.retailers.hnc.common.dao.CheckUserMapper;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.entity.ScanCode;
import com.retailers.hnc.common.service.CheckUserService;
import com.retailers.hnc.common.service.OpeningService;
import com.retailers.hnc.common.service.ScanCodeService;
import com.retailers.hnc.common.vo.CheckUserVo;
import com.retailers.tools.utils.ObjectUtils;
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
	@Autowired
	private OpeningService openingService;
	@Autowired
	private ScanCodeService scanCodeService;
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
		Opening opening = openingService.queryRuningOpening();
		int status = 0;
		Map map = new HashMap();
		if(ObjectUtils.isNotEmpty(opening)){
			Long oid = opening.getOid();
			Map paramss = new HashMap();
			paramss.put("isDelete",0L);
			paramss.put("oid",oid);
			paramss.put("emId",eid);
			List<ScanCode> scanCodes = scanCodeService.queryScanCodeList(paramss,1,1).getData();
			if(ObjectUtils.isNotEmpty(scanCodes)){
				Map params = new HashMap();
				params.put("oid",oid);
				params.put("isDelete",0L);
				params.put("cuValidateCode",validateCode);
				List<CheckUser> checkUsers = queryCheckUserList(params,1,1).getData();
				if(ObjectUtils.isNotEmpty(checkUsers)){
					CheckUser checkUser = checkUsers.get(0);
					Long isUse = checkUser.getIsUse();
					if(isUse==0){
						status=0;
						map.put("msg","验证通过");
						checkUser.setIsUse(1L);
						updateCheckUser(checkUser);
					}else{
						status=-1;
						map.put("msg","改验证码已被使用");
					}
				}else{
					status=-1;
					map.put("msg","验证码无效");
				}
			}else{
				status = -1;
				map.put("msg","当前时间不在开盘时间内");
			}
			}else{
				status=-1;
				map.put("msg","暂无权限扫码");
			}
		map.put("status",status);
		return  map;
	}
	public CheckUserVo queryCheckUserValidateCode(Long cid){
		CheckUserVo checkUserVo = checkUserMapper.queryCheckUserValidateCode(cid);
		return checkUserVo;
	}
	public Pagination<CheckUserVo> queryCheckUserVoList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<CheckUserVo> page = new Pagination<CheckUserVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CheckUserVo> list = checkUserMapper.queryCheckUserVoList(page);
		page.setData(list);
		return page;
	}
	public Map queryCheckUserNum(Long oid){
		List<CheckUserVo> list = checkUserMapper.queryCheckUserNum(oid);
		Map map = new HashMap();
		for(CheckUserVo checkUserVo:list){
			if(checkUserVo.getIsUse()==0){
				map.put("useNum",checkUserVo.getCount());
			}else {
				map.put("notuseNum",checkUserVo.getCount());
			}
		}
		return map;
	}
	public List<CheckUserVo> queryAchievement( Map params){
		params.put("isUse",0);
		List<CheckUserVo> list1 = checkUserMapper.queryAchievement(params);
		params.put("isUse",1);
		List<CheckUserVo> list2 = checkUserMapper.queryAchievement(params);
		for(CheckUserVo checkUserVo:list1){
			checkUserVo.setNotuseNum(checkUserVo.getCount());
		}
		for(CheckUserVo checkUserVo1:list1){
			for(CheckUserVo checkUserVo2:list2){
				Long empId1 = checkUserVo1.getEmpId();
				Long empId2 = checkUserVo2.getEmpId();
				if(empId1==empId2){
					checkUserVo1.setUseNum(checkUserVo2.getCount());
					break;
				}
			}
		}
		return list1;
	}

	public List<CheckUserVo> queryUsedOrNotUse(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<CheckUserVo> page = new Pagination<CheckUserVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CheckUserVo> list = checkUserMapper.queryUsedOrNotUse(page);
		return list;
	}
}

