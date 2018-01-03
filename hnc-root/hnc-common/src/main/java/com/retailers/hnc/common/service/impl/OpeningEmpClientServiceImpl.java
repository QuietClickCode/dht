
package com.retailers.hnc.common.service.impl;

import com.retailers.hnc.common.dao.OpeningEmpClientMapper;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.entity.OpeningEmpClient;
import com.retailers.hnc.common.service.FloorManageService;
import com.retailers.hnc.common.service.HouseTypeManageService;
import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.service.OpeningService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.hnc.common.vo.ClientManageVo;
import com.retailers.hnc.common.vo.OpeningEmpClientVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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
		page.setData(list);
		return page;
	}

	public boolean addCheckClient(Long oid,Long eid,String cmIds){
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
		return status==index?true:false;
	}

	public boolean updateOpeningEmpClientByOecIds(String oecIds,Long status,String msg){
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
}

