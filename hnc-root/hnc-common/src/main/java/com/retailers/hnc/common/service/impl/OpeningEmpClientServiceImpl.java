
package com.retailers.hnc.common.service.impl;

import com.retailers.hnc.common.dao.OpeningEmpClientMapper;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.entity.OpeningEmpClient;
import com.retailers.hnc.common.service.FloorManageService;
import com.retailers.hnc.common.service.HouseTypeManageService;
import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.vo.ClientManageVo;
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

	public List<ClientManageVo> queryNotGivenList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<OpeningEmpClient> page = new Pagination<OpeningEmpClient>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = openingEmpClientMapper.queryNotGivenList(page);
		return list;
	}


	public void addIntention(List<ClientManageVo> list){
		if(ObjectUtils.isNotEmpty(list)){
			Map params = new HashMap();
			params.put("isDelete",0l);
			List<FloorManage> floorManageList = floorManageService.queryFloorManageList(params,1,10000).getData();
			List<HouseTypeManage> houseTypeManageList = houseTypeManageService.queryHouseTypeManageList(params,1,10000).getData();


			for(ClientManageVo clientManageVo:list){
				List<FloorManage> floorManages = new ArrayList<FloorManage>();
				List<HouseTypeManage> houseTypeManages = new ArrayList<HouseTypeManage>();

				List<Long> fidsList = new ArrayList<Long>();
				List<Long> htIdsList = new ArrayList<Long>();

				String fidsStr = clientManageVo.getFids();
				String hidsStr = clientManageVo.getHids();

				idStringtoidList(fidsList,fidsStr);
				idStringtoidList(htIdsList,hidsStr);

				for(FloorManage floorManage:floorManageList){
					Long fmId = floorManage.getFmId();
					for(Long fidLong:fidsList){
						if(fmId==fidLong){
							floorManages.add(floorManage);
						}
					}
				}
				clientManageVo.setFloorManageList(floorManages);
				for(HouseTypeManage houseTypeManage:houseTypeManageList){
					Long htId = houseTypeManage.getHtId();
					for(Long htIdLong:htIdsList){
						if(htId==htIdLong){
							houseTypeManages.add(houseTypeManage);
						}
					}
				}
				clientManageVo.setHouseTypeManages(houseTypeManages);

			}
		}
	}

	public void idStringtoidList(List<Long> list,String str){
		if(ObjectUtils.isNotEmpty(str)){
			String[] hidsArr = str.split(",");
			for(String hidStr:hidsArr){
				Long hidsLong = Long.parseLong(hidStr);
				list.add(hidsLong);
			}
		}
	}
}

