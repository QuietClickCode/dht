
package com.retailers.hnc.common.service.impl;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.hnc.common.dao.HouseTypeManageMapper;
import com.retailers.hnc.common.entity.ClientIntention;
import com.retailers.hnc.common.dao.ClientIntentionMapper;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.service.ClientIntentionService;
import com.retailers.hnc.common.service.FloorManageService;
import com.retailers.hnc.common.service.HouseTypeManageService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：客户意向表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 17:32:34
 */
@Service("clientintentionService")
public class ClientIntentionServiceImpl implements ClientIntentionService {
	@Autowired
	private ClientIntentionMapper clientIntentionMapper;
	@Autowired
	private FloorManageService floorManageService;
	@Autowired
	private HouseTypeManageService houseTypeManageService;
	public boolean saveClientIntention(ClientIntention clientIntention) {
		int status = clientIntentionMapper.saveClientIntention(clientIntention);
		return status == 1 ? true : false;
	}
	public boolean updateClientIntention(ClientIntention clientIntention) {
		int status = clientIntentionMapper.updateClientIntention(clientIntention);
		return status == 1 ? true : false;
	}
	public ClientIntention queryClientIntentionByIid(Long iid) {
		return clientIntentionMapper.queryClientIntentionByIid(iid);
	}

	public Pagination<ClientIntention> queryClientIntentionList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ClientIntention> page = new Pagination<ClientIntention>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientIntention> list = clientIntentionMapper.queryClientIntentionList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteClientIntentionByIid(Long iid) {
		int status = clientIntentionMapper.deleteClientIntentionByIid(iid);
		return status == 1 ? true : false;
	}

	public List<ClientIntentionVo> queryClientIntentionVoListByCmId(Long cmId){
		Map params = new HashMap();
		params.put("isDelete",0l);
		params.put("cmId",cmId);
		List<ClientIntention> list = queryClientIntentionList(params,1,3).getData();

		List<FloorManage> floorManages = floorManageService.queryFloorManageList(params,1,100).getData();
		List<HouseTypeManage> houseTypeManages = houseTypeManageService.queryHouseTypeManageList(params,1,100).getData();

		List<ClientIntentionVo> returnList = new ArrayList<ClientIntentionVo>();
		for(ClientIntention clientIntention:list){
			String floorsStr = "";
			String hourseStr = "";

			String[] farr = clientIntention.getFids().split(",");
			String[] harr = clientIntention.getHids().split(",");

			List<Long> fLongList = strArrtoLongArr(farr);
			List<Long> hLongList = strArrtoLongArr(harr);

			for(FloorManage floorManage:floorManages){
				for(Long fLong:fLongList){
					if(fLong==floorManage.getFmId()){
						floorsStr += ","+floorManage.getFmName();
					}
				}
			}
			if(ObjectUtils.isNotEmpty(floorsStr)){
				floorsStr = floorsStr.substring(1);
			}


			for(HouseTypeManage houseTypeManage:houseTypeManages){
				for(Long hLong:hLongList){
					if(hLong==houseTypeManage.getHtId()){
						hourseStr += ","+houseTypeManage.getHtTypeName();
					}
				}
			}
			if(ObjectUtils.isNotEmpty(hourseStr)){
				hourseStr = hourseStr.substring(1);
			}

			ClientIntentionVo clientIntentionVo = new ClientIntentionVo();
			BeanUtils.copyProperties(clientIntention,clientIntentionVo);
			clientIntentionVo.setFloorsName(floorsStr);
			clientIntentionVo.setHoursesName(hourseStr);
			returnList.add(clientIntentionVo);
		}
		return returnList;
	}

	public List<Long> strArrtoLongArr(String[] strArr){
		if(ObjectUtils.isNotEmpty(strArr)){
			List<Long> list = new ArrayList<Long>();
			for(String str:strArr){
				Long l = Long.parseLong(str);
				list.add(l);
			}
			return list;
		}
		return null;
	}
}

