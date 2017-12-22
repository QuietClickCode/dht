package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.Project;
import com.retailers.hnc.common.vo.ProjectVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：项目表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 17:30:49
 */
public interface ProjectMapper {

	/**
	 * 添加项目表
	 * @param project
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public int saveProject(Project project);
	/**
	 * 编辑项目表
	 * @param project
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public int updateProject(Project project);
	/**
	 * 根据Pid删除项目表
	 * @param pid
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public int deleteProjectByPid(Long pid);
	/**
	 * 根据Pid查询项目表
	 * @param pid
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public Project queryProjectByPid(Long pid);
	/**
	 * 查询项目表列表
	 * @param pagination 分页对象
	 * @return  项目表列表
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public List<Project> queryProjectList(Pagination<Project> pagination);

	public List<ProjectVo> queryProjectVo();
}
