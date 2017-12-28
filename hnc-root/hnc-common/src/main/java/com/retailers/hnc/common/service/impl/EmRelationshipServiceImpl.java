
package com.retailers.hnc.common.service.impl;

import com.retailers.hnc.common.dao.ClientManageMapper;
import com.retailers.hnc.common.dao.EmRelationshipMapper;
import com.retailers.hnc.common.dao.EmployeeManageMapper;
import com.retailers.hnc.common.dao.TeamMapper;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmRelationship;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.Team;
import com.retailers.hnc.common.service.EmRelationshipService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.vo.EmRelationshipVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
/**
 * 描述：开盘和置业顾问关系表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 10:35:13
 */
@Service("emrelationshipService")
public class EmRelationshipServiceImpl implements EmRelationshipService {
	@Autowired
	private EmRelationshipMapper emRelationshipMapper;

	@Autowired
	private TeamMapper teamMapper;

	@Autowired
	private EmployeeManageMapper employeeManageMapper;

	public boolean saveEmRelationship(EmRelationship emRelationship) {
		int status = emRelationshipMapper.saveEmRelationship(emRelationship);
		return status == 1 ? true : false;
	}
	public boolean updateEmRelationship(EmRelationship emRelationship) {
		int status = emRelationshipMapper.updateEmRelationship(emRelationship);
		return status == 1 ? true : false;
	}
	public EmRelationship queryEmRelationshipByErId(Long erId) {
		return emRelationshipMapper.queryEmRelationshipByErId(erId);
	}

	public Pagination<EmRelationship> queryEmRelationshipList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<EmRelationship> page = new Pagination<EmRelationship>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<EmRelationship> list = emRelationshipMapper.queryEmRelationshipList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteEmRelationshipByErId(Long erId) {
		int status = emRelationshipMapper.deleteEmRelationshipByErId(erId);
		return status == 1 ? true : false;
	}

	public List<EmRelationshipVo> queryEmRelationshipVoList() {
		List<Team> teams = teamMapper.queryAllTeam();
		List<EmRelationshipVo> emRelationshipVos = new ArrayList<EmRelationshipVo>();
		for (Team team : teams) {
			EmRelationshipVo vo = new EmRelationshipVo();
			vo.setTid(team.getTid());
			vo.setTeam(team);
			vo.setIsDelete(0L);
			emRelationshipVos.add(vo);
		}
		List<EmployeeManage> employeeManages = employeeManageMapper.queryAllEmployee();
		for (EmployeeManage employeeManage : employeeManages) {
			EmRelationshipVo vo = new EmRelationshipVo();
			vo.setEmId(employeeManage.getEmId());
			vo.setIsDelete(0L);
			vo.setParentId(employeeManage.getEmTeam());
			vo.setEmployeeManage(employeeManage);
			emRelationshipVos.add(vo);
		}
		return emRelationshipVos;
	}

	public List<EmRelationshipVo> queryEmployeeTree(List<EmRelationshipVo> relationshipVos) {
		List<EmRelationshipVo> rtnList=new ArrayList<EmRelationshipVo>();
		Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
		queryEmployeeNode(relationshipVos,child);
		Map<Long,Long> alloShow=new HashMap<Long, Long>();
		queryAllEmployee(null,child,alloShow);
		for(EmRelationshipVo vo:relationshipVos){
			if(ObjectUtils.isEmpty(vo.getParentId())){
				vo.setLevel(1l);
				rtnList.add(vo);
			}else{
				if(alloShow.containsKey(vo.getParentId())){
					rtnList.add(vo);
				}
				vo.setLevel(2l);
			}
		}
		return rtnList;
	}

	private void queryAllEmployee(Long parentId,Map<Long,Map<Long,Long>> map,Map<Long,Long>allowMap){
		if(ObjectUtils.isEmpty(parentId)){
			parentId=-1l;
		}
		Map<Long,Long> child=map.get(parentId);
		if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
			for(Long key:child.keySet()){
				allowMap.put(key,key);
				if(map.containsKey(key)){
					queryAllEmployee(key,map,allowMap);
				}
			}
		}
	}


	private void queryEmployeeNode(List<EmRelationshipVo> list, Map<Long,Map<Long,Long>> child){
		for(EmRelationshipVo vo:list){
			Long parentId=vo.getParentId();
			if(ObjectUtils.isEmpty(parentId)){
				parentId=-1l;
			}
			if(child.containsKey(parentId)){
				child.get(parentId).put(vo.getTid(),vo.getIsDelete());
			}else{
				Map<Long,Long> maps=new HashMap<Long, Long>();
				maps.put(vo.getTid(),vo.getIsDelete());
				child.put(parentId,maps);
			}
		}
	}
}
