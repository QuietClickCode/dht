
package com.retailers.hnc.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.common.dao.OpeningEmpClientMapper;
import com.retailers.hnc.common.entity.*;
import com.retailers.hnc.common.service.*;
import com.retailers.hnc.common.util.HttpUtils;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.hnc.common.vo.ClientManageVo;
import com.retailers.hnc.common.vo.OpeningEmpClientVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.utils.wx.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * 描述：开盘与雇员和客户的关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 09:01:40
 */
@Service("openingempclientService")
public class OpeningEmpClientServiceImpl implements OpeningEmpClientService {
	@Autowired
	private OpeningEmpClientMapper openingEmpClientMapper;
	@Autowired
	private OpeningService openingService;
	@Autowired
	private FloorManageService floorManageService;
	@Autowired
	private HouseTypeManageService houseTypeManageService;
	@Autowired
	private CheckUserService checkUserService;
	@Autowired
	private EmployeeManageService employeeManageService;
	@Autowired
	private WxAuthUserService wxAuthUserService;
	@Autowired
	private ClientManageService clientManageService;

	public boolean saveOpeningEmpClient(OpeningEmpClient openingEmpClient) {
		int status = openingEmpClientMapper.saveOpeningEmpClient(openingEmpClient);
		return status == 1 ? true : false;
	}
	public boolean updateOpeningEmpClient(OpeningEmpClient openingEmpClient) {
		int status = openingEmpClientMapper.updateOpeningEmpClient(openingEmpClient);
		return status == 1 ? true : false;
	}
	public OpeningEmpClient queryOpeningEmpClientByOecId(Long oecId) {
		return openingEmpClientMapper.queryOpeningEmpClientByOecId(oecId);
	}

	public Pagination<OpeningEmpClient> queryOpeningEmpClientList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<OpeningEmpClient> page = new Pagination<OpeningEmpClient>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<OpeningEmpClient> list = openingEmpClientMapper.queryOpeningEmpClientList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOpeningEmpClientByOecId(Long oecId) {
		int status = openingEmpClientMapper.deleteOpeningEmpClientByOecId(oecId);
		return status == 1 ? true : false;
	}

	public Pagination<ClientManageVo> queryNotGivenList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<ClientManageVo> page = new Pagination<ClientManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = openingEmpClientMapper.queryNotGivenList(page);
		page.setData(list);
		return page;
	}

	public Pagination<ClientManageVo> queryNotGivenListWeb(Map<String, Object> params, int pageNo, int pageSize){
		Opening opening = openingService.queryEarlyOpening();
		params.put("oid",opening.getOid());
		Pagination<ClientManageVo> page = new Pagination<ClientManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = openingEmpClientMapper.queryNotGivenListWeb(page);


		page.setData(list);
		return page;
	}

	public Pagination<ClientManageVo> queryCheckingandpassandnotpassList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<ClientManageVo> page = new Pagination<ClientManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = openingEmpClientMapper.queryCheckingandpassandnotpassList(page);
		page.setData(list);
		return page;
	}

	public Pagination<ClientManageVo> queryCheckingandpassandnotpassListWeb(Map<String, Object> params, int pageNo, int pageSize){
		Opening opening = openingService.queryEarlyOpening();
		params.put("oid",opening.getOid());
		Pagination<ClientManageVo> page = new Pagination<ClientManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = openingEmpClientMapper.queryCheckingandpassandnotpassListWeb(page);
		for(ClientManageVo clientManageVo:list){
			String hids = clientManageVo.getHids();
			String fids = clientManageVo.getFids();
			addFloorsAndHourses(hids,fids,clientManageVo);
		}
		page.setData(list);
		return page;
	}

	public boolean addCheckClient(Long oid,Long eid,String cmIds,String accessToken){
		OpeningEmpClient openingEmpClient = new OpeningEmpClient();
		openingEmpClient.setIsDelete(0L);
		openingEmpClient.setOid(oid);
		openingEmpClient.setEid(eid);
		openingEmpClient.setOecStatus(1L);
		int status = 0;
		int index = 0;
		if(ObjectUtils.isNotEmpty(cmIds)){
			String[] cmIdsArr = cmIds.split(",");
			index = cmIdsArr.length;
			for(String cmIdStr:cmIdsArr){
				Long cmIdLong = Long.parseLong(cmIdStr);
				openingEmpClient.setCid(cmIdLong);
				status += openingEmpClientMapper.saveOpeningEmpClient(openingEmpClient);
			}
		}
		//给管理员发送消息,通知有置业顾问提交审核
		if(status==index){
			sendModalMsg(oid,eid,accessToken);
		}
		return status==index?true:false;
	}

	public boolean updateOpeningEmpClientByOecIds(String oecIds,Long status,String msg,String accessToken){
		List<Long> oecIdList = new ArrayList<Long>();
		if(ObjectUtils.isNotEmpty(oecIds)){
			String[] oecIdsArr = oecIds.split(",");
			for(String oecIdStr:oecIdsArr){
				Long oecIdLong = Long.parseLong(oecIdStr);
				oecIdList.add(oecIdLong);
			}

		}
		int index = 0;
		index = openingEmpClientMapper.updateOpeningEmpClientByOecIds(oecIdList,status,msg);
		System.out.println(status);
		if(status==2){
			if(index==oecIdList.size()){
				for(Long oecIdLong:oecIdList){
					OpeningEmpClient openingEmpClient = queryOpeningEmpClientByOecId(oecIdLong);
					CheckUser checkUser = new CheckUser();
					checkUser.setIsUse(0L);
					checkUser.setIsDelete(0L);
					checkUser.setCid(openingEmpClient.getCid());
					checkUser.setOid(openingEmpClient.getOid());
					checkUser.setCuValidateCode(WXPayUtil.getStringRandom(32));
					checkUser.setIsDelete(0L);
					checkUserService.saveCheckUser(checkUser,accessToken);
				}
			}
		}

		return index==oecIdList.size()?true:false;
	}

	public boolean checkCanChangeEmpNum(Long oid,Long eid,Long num){
		Map params = new HashMap();
		params.put("isDelete",0L);
		params.put("oid",oid);
		params.put("eid",eid);
		params.put("oecStatus",2);
		List<OpeningEmpClient> openingEmpClients = queryOpeningEmpClientList(params,1,99999).getData();
		if(ObjectUtils.isNotEmpty(openingEmpClients)){
			int length = openingEmpClients.size();
			if(length>=num){
				return false;
			}
		}
		params.put("oecStatus",1);
		openingEmpClients = queryOpeningEmpClientList(params,1,99999).getData();
		List<Long> oecIds = new ArrayList<Long>();
		for (OpeningEmpClient openingEmpClient:openingEmpClients){
			oecIds.add(openingEmpClient.getOecId());
		}
		System.out.println(oecIds.size());
		if(ObjectUtils.isNotEmpty(oecIds)){
			openingEmpClientMapper.deleteOpeningEmpClientByOecIds(oecIds);
		}
		return true;
	}

	public Map queryCheckingandpassandnotpassNumWeb(Map params){
		Map map = new HashMap();
		List<OpeningEmpClientVo> openingEmpClients = openingEmpClientMapper.queryCheckingandpassandnotpassNumWeb(params);
		if(ObjectUtils.isNotEmpty(openingEmpClients)){
			for(OpeningEmpClientVo openingEmpClientVo:openingEmpClients){
				Long status = openingEmpClientVo.getOecStatus();
				Long num = openingEmpClientVo.getCount();
				if(status==1){
					map.put("checkingNum",num);
				}else if(status==2){
					map.put("passNum",num);
				}else if(status==3){
					map.put("notpassNum",num);
				}
			}
		}else{
			map.put("checkingNum",0);
			map.put("passNum",0);
			map.put("notpassNum",0);
		}
		return map;
	}

	public List<Long> StringtoList(String str){
		String[] strArr = str.split(",");
		List<Long> returnList = new ArrayList<Long>();
		for(String strStr:strArr){
			Long l = Long.parseLong(strStr);
			returnList.add(l);
		}
		return returnList;
	}

	public void addFloorsAndHourses(String hids,String fids,ClientManageVo clientManageVo){
		if(ObjectUtils.isNotEmpty(hids)&&ObjectUtils.isNotEmpty(fids)){
			List<FloorManage> floorManages = floorManageService.queryFloorManageByFmIds(fids);
			List<HouseTypeManage> houseTypeManages = houseTypeManageService.queryHouseTypeManageByHtIds(hids);
			String floorsName = "";
			String hoursesName = "";
			for(FloorManage floorManage:floorManages){
				floorsName += ","+floorManage.getFmName();
			}
			for(HouseTypeManage houseTypeManage:houseTypeManages){
				hoursesName += ","+houseTypeManage.getHtTypeName();
			}
			if(ObjectUtils.isNotEmpty(floorsName)){
				floorsName = floorsName.substring(1);
			}
			if(ObjectUtils.isNotEmpty(hoursesName)){
				hoursesName = hoursesName.substring(1);
			}
			clientManageVo.setFloorsName(floorsName);
			clientManageVo.setHoursesName(hoursesName);
		}
	}

	public void sendModalMsg(Long oid,Long eid,String accessToken){
		Opening opening = openingService.queryOpeningByOid(oid);
		EmployeeManage employeeManage = employeeManageService.queryEmployeeManageByEmId(eid);

		//发消息给客户通知看房
		String first = "置业顾问"+employeeManage.getEmName()+"提交审核名单";
		String keynote1 = opening.getOname();//clientManage.getTmName()==null||clientManage.getTmName()=="null"?nickName:clientManage.getTmName();
		String keynote2 = "客户看房审批";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String keynote3 = sdf.format(new Date());
		String remark = "请您尽快审核";

		JSONObject dataJsonObject = new JSONObject();

		JSONObject valJsonObjFirst = new JSONObject();
		valJsonObjFirst.put("value",first);
		dataJsonObject.put("first",valJsonObjFirst);

		JSONObject valJsonObjkeyword1 = new JSONObject();
		valJsonObjkeyword1.put("value",keynote1);
		dataJsonObject.put("keyword1",valJsonObjkeyword1);

		JSONObject valJsonObjkeyword2 = new JSONObject();
		valJsonObjkeyword2.put("value",keynote2);
		dataJsonObject.put("keyword2",valJsonObjkeyword2);

		JSONObject valJsonObjkeyword3 = new JSONObject();
		valJsonObjkeyword3.put("value",keynote3);
		dataJsonObject.put("keyword3",valJsonObjkeyword3);

		JSONObject valJsonObjremark = new JSONObject();
		valJsonObjremark.put("value",remark);
		dataJsonObject.put("remark",valJsonObjremark);

		JSONObject minJsonObj = new JSONObject();
		minJsonObj.put("appid","wx53881ce6778c5e1f");
		minJsonObj.put("pagepath","pages/manage/wait-audit/wait-audit");

		JSONObject allJsonData = new JSONObject();
		allJsonData.put("data",dataJsonObject);

		allJsonData.put("template_id","thwmet3fFMGmkoG0fhaLcnxftgzRpONv4mFdJBZ3lPg");
		allJsonData.put("miniprogram",minJsonObj);

		Map paramsend = new HashMap();
		paramsend.put("tmLoginStatus","2");
		List<ClientManage> clientManageList = clientManageService.queryClientManageList(paramsend,1,100).getData();
		List<Long> cids = new ArrayList<Long>();
		for(ClientManage clientManage1:clientManageList){
			cids.add(clientManage1.getTmId());
		}
		Map map1 = new HashMap();
		map1.put("cids",cids);
		List<WxAuthUser> wxAuthUserList = wxAuthUserService.queryWxAuthUserListByParams(map1);
		List<String> manageWxAuthUserList = new ArrayList<String>();
		for(WxAuthUser wxAuthUser:wxAuthUserList){
			manageWxAuthUserList.add(wxAuthUser.getWauUnionid());
		}
		Map map2 = new HashMap();
		map2.put("unionids",manageWxAuthUserList);
		List<WxAuthUser> wxAuthUsersManage = wxAuthUserService.queryWxAuthUserListByParams(map2);
		for(WxAuthUser wxAuthUser:wxAuthUsersManage){
			String openid = wxAuthUser.getWauOpenid();
			allJsonData.put("touser",openid);
			String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
			String res = HttpUtils.reqPost(url, JSON.toJSONString(allJsonData));
			System.out.println(res);
		}
	}
}

