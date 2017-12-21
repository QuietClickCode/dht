
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.Project;
import com.retailers.hnc.common.dao.ProjectMapper;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.vo.ProjectVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：项目表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-13 17:30:49
 */
@Service("projectService")
public class ProjectServiceImpl implements ProjectService {
	@Autowired
	private ProjectMapper projectMapper;
	public boolean saveProject(Project project) {
		int status = projectMapper.saveProject(project);
		return status == 1 ? true : false;
	}
	public boolean updateProject(Project project) {
		int status = projectMapper.updateProject(project);
		return status == 1 ? true : false;
	}
	public Project queryProjectByPid(Long pid) {
		return projectMapper.queryProjectByPid(pid);
	}

	public Pagination<Project> queryProjectList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Project> page = new Pagination<Project>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Project> list = projectMapper.queryProjectList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteProjectByPid(Long pid) {
		int status = projectMapper.deleteProjectByPid(pid);
		return status == 1 ? true : false;
	}
	public List<ProjectVo> queryProjectVo(){
		List<ProjectVo> list = projectMapper.queryProjectVo();
		return list;
	}
}

