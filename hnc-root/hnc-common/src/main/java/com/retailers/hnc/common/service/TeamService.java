
package com.retailers.hnc.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.Team;
import java.util.Map;
/**
 * 描述：团队表Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 13:58:29
 */
public interface TeamService {

	/**
	 * 添加团队表
	 * @param team
	 * @return
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public boolean saveTeam(Team team);
	/**
	 * 编辑团队表
	 * @param team
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateTeam(Team team);
	/**
	 * 根据id查询团队表
	 * @param tid
	 * @return team
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public Team queryTeamByTid(Long tid);
	/**
	 * 查询团队表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public Pagination<Team> queryTeamList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据tid删除团队表
	 * @param tid
	 * @return
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public boolean deleteTeamByTid(Long tid);

}


