
package com.retailers.hnc.common.service.impl;
import java.text.SimpleDateFormat;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.common.entity.*;
import com.retailers.hnc.common.dao.CheckUserMapper;
import com.retailers.hnc.common.service.*;
import com.retailers.hnc.common.util.HttpUtils;
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
	@Autowired
	private WxAuthUserService wxAuthUserService;
	@Autowired
	private ClientManageService clientManageService;
	@Autowired
	private EmployeeManageService employeeManageService;
	public boolean saveCheckUser(CheckUser checkUser,String accessToken) {
		int status = checkUserMapper.saveCheckUser(checkUser);
		//发送模板消息
		if(status==1){
			sendModalMsg(checkUser,accessToken);
		}
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
			if(ObjectUtils.isNotEmpty(scanCodes)&&eid!=null){
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
						checkUser.setUseTime(new Date());
						updateCheckUser(checkUser);
					}else{
						status=-1;
						map.put("msg","该验证码已被使用");
					}
				}else{
					status=-1;
					map.put("msg","验证码无效");
				}
			}else{
				status=-1;
				map.put("msg","暂无权限扫码");
			}
			}else{
				status = -1;
				map.put("msg","当前时间不在开盘时间内");
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
		Object obj1 = params.get("tidList");
		Object obj2 = params.get("emIdList");
		List<CheckUserVo> list1 = new ArrayList<CheckUserVo>();
		List<CheckUserVo> list2 = new ArrayList<CheckUserVo>();
		if(ObjectUtils.isEmpty(obj1)&&ObjectUtils.isEmpty(obj2) ){
			params.put("isUse",0);
			list1 = checkUserMapper.queryAllAchievement(params);
			params.put("isUse",1);
			list2 = checkUserMapper.queryAllAchievement(params);
		}else if (ObjectUtils.isNotEmpty(obj1)){
			params.put("isUse",0);
			list1 = checkUserMapper.queryTeamAchievement(params);
			params.put("isUse",1);
			list2 = checkUserMapper.queryTeamAchievement(params);
		}else if(ObjectUtils.isNotEmpty(obj2)){
			params.put("isUse",0);
			list1 = checkUserMapper.queryEmpAchievement(params);
			params.put("isUse",1);
			list2 = checkUserMapper.queryEmpAchievement(params);
		}
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

	public void sendModalMsg(CheckUser checkUser,String accessToken){
		Long oid = checkUser.getOid();
		Long cid = checkUser.getCid();
		Opening opening = openingService.queryOpeningByOid(oid);
		ClientManage clientManage = clientManageService.queryClientManageByTmId(cid);
		EmployeeManage employeeManage = employeeManageService.queryEmployeeManageByEmId(clientManage.getTmEmployee());
		Map params = new HashMap();
		params.put("wauUid",cid);
		params.put("wauWxId",0);
		List<WxAuthUser> wxAuthUserList = wxAuthUserService.queryWxAuthUserList(params,1,1).getData();
		if(ObjectUtils.isNotEmpty(wxAuthUserList)){
			WxAuthUser wxAuthUser = wxAuthUserList.get(0);
			String nickName = wxAuthUser.getWauNickname()==null||wxAuthUser.getWauNickname()=="null"?"暂无真实姓名":wxAuthUser.getWauNickname();
			String unionid = wxAuthUser.getWauUnionid();
			Map param = new HashMap();
			param.put("wauUnionid",unionid);
			param.put("wauWxId",1L);
			List<WxAuthUser> wxAuthUserList1 = wxAuthUserService.queryWxAuthUserList(param,1,1).getData();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			if(ObjectUtils.isNotEmpty(wxAuthUserList1)){
				String openid = wxAuthUserList1.get(0).getWauOpenid();//"o2ABhxH9oC2UfRw89P7yUYurep68";
				//发消息给客户通知看房
				String first = "尊敬的客户，您好";
				String keynote1 = clientManage.getTmName()==null||clientManage.getTmName()=="null"?nickName:clientManage.getTmName();
				String keynote2 = "华南城巴南华府";
				String keynote3 = sdf.format(opening.getOstartTime());
				String keynote4 = "预约成功";
				String keynote5 = employeeManage.getEmName()+"   "+employeeManage.getEmPhone();
				String remark = "请您带上身份证准时到场参加";

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

				JSONObject valJsonObjkeyword4 = new JSONObject();
				valJsonObjkeyword4.put("value",keynote4);
				dataJsonObject.put("keyword4",valJsonObjkeyword4);

				JSONObject valJsonObjkeyword5 = new JSONObject();
				valJsonObjkeyword5.put("value",keynote5);
				dataJsonObject.put("keyword5",valJsonObjkeyword5);

				JSONObject valJsonObjremark = new JSONObject();
				valJsonObjremark.put("value",remark);
				dataJsonObject.put("remark",valJsonObjremark);

				JSONObject minJsonObj = new JSONObject();
				minJsonObj.put("appid","wx53881ce6778c5e1f");
				minJsonObj.put("pagepath","pages/user/admission/admission?type=1");

				JSONObject allJsonData = new JSONObject();
				allJsonData.put("data",dataJsonObject);
				allJsonData.put("touser",openid);
				allJsonData.put("template_id","LWDa-gPnNkvsr8_EQhtPyBMjy0XhDBoPa6BB8WUl19c");
				allJsonData.put("miniprogram",minJsonObj);

				String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
				String res = HttpUtils.reqPost(url, JSON.toJSONString(allJsonData));
				System.out.println(res);
				JSONObject jsonObject = JSON.parseObject(res);
				Integer errorCode = jsonObject.getInteger("errcode");
				if(errorCode!=0){
					sendErrorMsg(clientManage,nickName,accessToken,employeeManage);
				}
			}
		}
	}

	public void sendErrorMsg(ClientManage clientManage,String nickName,String accessToken,EmployeeManage employeeManage){
		Map map = new HashMap();
		map.put("tmPhone",employeeManage.getWxPhone());
		map.put("isDelete",0L);
		List<ClientManage> clientManages = clientManageService.queryClientManageList(map,1,1).getData();
		ClientManage clientManage1 = clientManages.get(0);

		Map params = new HashMap();
		params.put("wauUid",clientManage1.getTmId());
		params.put("wauWxId",0);
		List<WxAuthUser> wxAuthUserList = wxAuthUserService.queryWxAuthUserList(params,1,1).getData();

		Map param = new HashMap();
		param.put("wauUnionid",wxAuthUserList.get(0).getWauUnionid());
		param.put("wauWxId",1L);
		List<WxAuthUser> wxAuthUserList1 = wxAuthUserService.queryWxAuthUserList(param,1,1).getData();

		String openid = wxAuthUserList1.get(0).getWauOpenid();

		String cname = clientManage.getTmName()==null|| clientManage.getTmName()=="null"?nickName:clientManage.getTmName();
		String cphone = clientManage.getTmPhone();
		//发送模板消息给置业顾问  客户没有接受到消息
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String first = "尊敬的置业顾问，您好";
		String keynote1 = "预约看房消息发送失败";
		String keynote2 = "" + sdf.format(new Date());
		String remark = "请通知您的客户"+cname+"("+cphone+")"+"前来选房";

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

		JSONObject valJsonObjremark = new JSONObject();
		valJsonObjremark.put("value",remark);
		dataJsonObject.put("remark",valJsonObjremark);

		JSONObject allJsonData = new JSONObject();
		allJsonData.put("data",dataJsonObject);
		allJsonData.put("touser",openid);
		allJsonData.put("template_id","iITiWjUwcZyBB8CsMN8p5ZhdV87FMVbNN6bHbcswXM8");

		String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
		String res = HttpUtils.reqPost(url, JSON.toJSONString(allJsonData));
		System.out.println(res);
	}
}

