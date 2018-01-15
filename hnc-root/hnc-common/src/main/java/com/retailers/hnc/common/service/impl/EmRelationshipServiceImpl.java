
package com.retailers.hnc.common.service.impl;

import com.retailers.hnc.common.dao.EmRelationshipMapper;
import com.retailers.hnc.common.dao.EmployeeManageMapper;
import com.retailers.hnc.common.dao.TeamMapper;
import com.retailers.hnc.common.entity.EmRelationship;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.Team;
import com.retailers.hnc.common.service.EmRelationshipService;
import com.retailers.hnc.common.vo.EmRelationshipVo;
import com.retailers.hnc.common.vo.EmployeeRelationshipVo;
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
		EmRelationship num = queryEmployeeNum(emRelationship);
		int status;
		if(num == null){
			status = emRelationshipMapper.saveEmRelationship(emRelationship);
		}else{
			emRelationship.setErId(num.getErId());
			return updateEmRelationship(emRelationship);
		}
		return status == 1 ? true : false;
	}
	public boolean updateEmRelationship(EmRelationship emRelationship) {
		EmRelationship relationship = queryEmRelationshipByErId(emRelationship.getErId());
		emRelationship.setVersion(relationship.getVersion());
		int status = emRelationshipMapper.updateEmRelationship(emRelationship);
		return status == 1 ? true : false;
	}
	public EmRelationship queryEmRelationshipByErId(Long erId) {
		return emRelationshipMapper.queryEmRelationshipByErId(erId);
	}

	public boolean saveEmRelationshipList(List<EmRelationship> emRelationships) {
		int status = emRelationshipMapper.saveEmRelationshipList(emRelationships);
		return status == 1 ? true : false;
	}

	public EmRelationship queryEmployeeNum(EmRelationship emRelationship) {
		return emRelationshipMapper.queryEmployeeNum(emRelationship);
	}

	public Pagination<EmRelationship> queryEmRelationshipList(Map<String, Object> params, int pageNo, int pageSize) {
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

	public Integer queryOpeningStatus(Long pId) {
		return emRelationshipMapper.queryOpeningStatus(pId);
	}

	public List<EmRelationshipVo> queryEmRelationshipVoList() {
		List<Team> teams = teamMapper.queryAllTeam();
		List<EmRelationshipVo> emRelationshipVos = new ArrayList<EmRelationshipVo>();
		for (Team team : teams) {
			EmRelationshipVo vo = new EmRelationshipVo();
			vo.setTid(team.getTid());
			vo.setTeamName(team.getTname());
			vo.setTeam(team);
			vo.setIsDelete(0L);
			emRelationshipVos.add(vo);
		}
		List<EmployeeManage> employeeManages = employeeManageMapper.queryAllEmployee();
		long tmId = emRelationshipVos.get(emRelationshipVos.size() - 1).getTid() * 10000;
		for (EmployeeManage employeeManage : employeeManages) {
			EmRelationshipVo vo = new EmRelationshipVo();
			vo.setIsDelete(0L);
			vo.setTid(tmId++);
			vo.setEmId(employeeManage.getEmId());
			vo.setEmployeeName(employeeManage.getEmName());
			vo.setParentId(employeeManage.getEmTeam());
			vo.setEmployeeManage(employeeManage);
			emRelationshipVos.add(vo);
		}
		return emRelationshipVos;
	}

	/*public List<EmRelationshipVo> queryEmRelationshipVoTreeList() {
		List<Team> teams = teamMapper.queryAllTeam();
		List<EmRelationshipVo> emRelationshipVos = new ArrayList<EmRelationshipVo>();
		for (Team team : teams) {
			EmRelationshipVo vo = new EmRelationshipVo();
			vo.setTid(team.getTid());
			vo.setEmployeeManages(employeeManageMapper.queryAllEmployeeByTeam(team.getTid()));
			vo.setTeamName(team.getTname());
			vo.setTeam(team);
			vo.setIsDelete(0L);
			emRelationshipVos.add(vo);
		}
		return emRelationshipVos;
	}*/

	public List<EmployeeRelationshipVo> queryAllEmRelationshipVoList(Long pid) {
		List<Team> teams = teamMapper.queryAllTeam();
		long tid =  teams.size() * 100;
		List<EmployeeRelationshipVo> employeeRelationshipVos = emRelationshipMapper.queryAllEmRelationshipVoList(pid);
		for(EmployeeRelationshipVo relationshipVo:employeeRelationshipVos){
			relationshipVo.setLevel(2L);
			if(relationshipVo.getEmReservation() == null)
				relationshipVo.setEmReservation(0L);
			relationshipVo.setTid(null);
			relationshipVo.setTid(tid++);
		}
		for(Team team:teams){
			EmployeeRelationshipVo relationshipVo = new EmployeeRelationshipVo();
			relationshipVo.setTeamName(team.getTname());
			relationshipVo.setTid(team.getTid());
			relationshipVo.setLevel(1L);
			setTeamCount(relationshipVo,employeeRelationshipVos);
			employeeRelationshipVos.add(relationshipVo);
		}
		return employeeRelationshipVos;
	}

	public void setTeamCount(EmployeeRelationshipVo vo,List<EmployeeRelationshipVo> employeeRelationshipVos){
		Long count = 0L;
		for (EmployeeRelationshipVo employeeRelationshipVo : employeeRelationshipVos) {
			if(employeeRelationshipVo.getEmTeam() == vo.getTid()){
				count += employeeRelationshipVo.getEmReservation();
			}
		}
		vo.setCount(count);
	}

	public List<EmployeeRelationshipVo> queryReservationInfo(EmRelationship relationship) {
		List<Team> teams = teamMapper.queryAllTeam();
		List<EmployeeRelationshipVo> emRelationshipVos = new ArrayList<EmployeeRelationshipVo>();
		for (Team team : teams) {
			EmployeeRelationshipVo relationshipVo = new EmployeeRelationshipVo();
			relationshipVo.setEmTeam(team.getTid());
			relationshipVo.setTeamName(team.getTname());
			relationship.setParentId(team.getTid());
			relationshipVo.setEmployeeManages(emRelationshipMapper.queryReservationInfo(relationship));
			emRelationshipVos.add(relationshipVo);
		}
		return emRelationshipVos;
	}

	public List<EmRelationshipVo> queryOpeningEmployees(Long pId) {
		List<EmRelationship> OpeningTeams = queryOpeningTeam(pId);
		System.out.println(OpeningTeams.size()+"Test");
		long tmId = 1;
		if(OpeningTeams.size() != 0)
			tmId = OpeningTeams.get(OpeningTeams.size() - 1).getParentId() * 10000;
		List<EmRelationshipVo> relationshipVoList = emRelationshipMapper.queryOpeningEmployees(pId);
		for (EmRelationshipVo relationshipVo : relationshipVoList) {
			EmployeeManage employeeManage = employeeManageMapper.queryEmployeeManageByEmId(relationshipVo.getEmId());
			relationshipVo.setEmployeeName(employeeManage.getEmName());
			relationshipVo.setEmployeeManage(employeeManage);
			relationshipVo.setTid(tmId++);
		}

		for (EmRelationship relationship : OpeningTeams) {
			Team team = teamMapper.queryTeamByTid(relationship.getParentId());
			EmRelationshipVo vo = new EmRelationshipVo();
			vo.setTid(team.getTid());
			vo.setIsDelete(0l);
			vo.setParentId(null);
			vo.setEmployeeName(team.getTname());
			vo.setTeam(team);
			relationshipVoList.add(vo);
		}
		return relationshipVoList;
	}

	public List<EmRelationship> queryOpeningTeam(Long pId) {
		return emRelationshipMapper.queryOpeningTeam(pId);
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

