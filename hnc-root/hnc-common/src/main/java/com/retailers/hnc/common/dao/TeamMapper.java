package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.Team;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：团队表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 13:58:29
 */
public interface TeamMapper {

	/**
	 * 添加团队表
	 * @param team
	 * @return
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public int saveTeam(Team team);
	/**
	 * 编辑团队表
	 * @param team
	 * @return
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public int updateTeam(Team team);
	/**
	 * 根据Tid删除团队表
	 * @param tid
	 * @return
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public int deleteTeamByTid(Long tid);
	/**
	 * 根据Tid查询团队表
	 * @param tid
	 * @return
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public Team queryTeamByTid(Long tid);
	/**
	 * 查询团队表列表
	 * @param pagination 分页对象
	 * @return  团队表列表
	 * @author wangjue
	 * @date 2017-12-22 13:58:29
	 */
	public List<Team> queryTeamList(Pagination<Team> pagination);

}
