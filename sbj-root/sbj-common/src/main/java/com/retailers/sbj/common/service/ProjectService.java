
package com.retailers.sbj.common.service;

import com.retailers.sbj.common.entity.Project;
import com.retailers.sbj.common.vo.ProjectVo;
import com.retailers.mybatis.pagination.Pagination;

import java.util.List;
import java.util.Map;

/**
 * 描述：项目表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 17:30:49
 */
public interface ProjectService {

	/**
	 * 添加项目表
	 * @param project
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public boolean saveProject(Project project);
	/**
	 * 编辑项目表
	 * @param project
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateProject(Project project);
	/**
	 * 根据id查询项目表
	 * @param pid
	 * @return project
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public Project queryProjectByPid(Long pid);
	/**
	 * 查询项目表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public Pagination<Project> queryProjectList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据pid删除项目表
	 * @param pid
	 * @return
	 * @author fanghui
	 * @date 2017-12-13 17:30:49
	 */
	public boolean deleteProjectByPid(Long pid);

	public List<ProjectVo> queryProjectVo();
}


