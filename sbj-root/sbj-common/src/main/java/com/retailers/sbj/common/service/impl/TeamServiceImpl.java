
package com.retailers.sbj.common.service.impl;

import com.retailers.sbj.common.dao.TeamMapper;
import com.retailers.sbj.common.entity.Team;
import com.retailers.sbj.common.service.TeamService;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：团队表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 13:58:29
 */
@Service("teamService")
public class TeamServiceImpl implements TeamService {
	@Autowired
	private TeamMapper teamMapper;
	public boolean saveTeam(Team team) {
		int status = teamMapper.saveTeam(team);
		return status == 1 ? true : false;
	}
	public boolean updateTeam(Team team) {
		Team team1 = queryTeamByTid(team.getTid());
		team.setVersion(team1.getVersion());
		int status = teamMapper.updateTeam(team);
		return status == 1 ? true : false;
	}
	public Team queryTeamByTid(Long tid) {
		return teamMapper.queryTeamByTid(tid);
	}

	public Pagination<Team> queryTeamList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Team> page = new Pagination<Team>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Team> list = teamMapper.queryTeamList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteTeamByTid(Long tid) {
		Team team = queryTeamByTid(tid);
		team.setIsDelete(1);
		return updateTeam(team);
	}

	public List<Team> queryAllTeam() {
		return teamMapper.queryAllTeam();
	}
}

