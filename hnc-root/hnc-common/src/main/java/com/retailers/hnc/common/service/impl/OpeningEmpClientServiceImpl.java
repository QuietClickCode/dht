
package com.retailers.hnc.common.service.impl;

import com.retailers.hnc.common.dao.OpeningEmpClientMapper;
import com.retailers.hnc.common.entity.FloorManage;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.entity.OpeningEmpClient;
import com.retailers.hnc.common.service.FloorManageService;
import com.retailers.hnc.common.service.HouseTypeManageService;
import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.vo.ClientIntentionVo;
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

	public Pagination<ClientManageVo> queryCheckingandpassandnotpassList(Map<String, Object> params, int pageNo, int pageSize){
		Pagination<ClientManageVo> page = new Pagination<ClientManageVo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ClientManageVo> list = openingEmpClientMapper.queryCheckingandpassandnotpassList(page);
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

	public boolean changeClientStatus(Long oid,Long eid,String cmIds,Long status){

		return false;
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


}

