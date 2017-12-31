
package com.retailers.hnc.common.service.impl;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.Project;
import com.retailers.hnc.common.dao.ProjectMapper;
import com.retailers.hnc.common.entity.ProjectImg;
import com.retailers.hnc.common.service.ProjectImgService;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.util.AttachmentUploadImageUtils;
import com.retailers.hnc.common.vo.ProjectImgVo;
import com.retailers.hnc.common.vo.ProjectVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.tools.utils.ObjectUtils;
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
	@Autowired
	private ProjectImgService projectImgService;
	@Autowired
	private AttachmentService attachmentService;
	public boolean saveProject(Project project) {
		int status = projectMapper.saveProject(project);
		editAtt(project,status);
		return status == 1 ? true : false;
	}
	public boolean updateProject(Project project) {
		int status = projectMapper.updateProject(project);
		editAtt(project,status);
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
		if(ObjectUtils.isNotEmpty(list)){
			ProjectVo projectVo = list.get(0);
			projectVo.setLogoImgUrl(AttachmentConstant.IMAGE_SHOW_URL+projectVo.getLogoImgUrl());
			Long pid = projectVo.getPid();
			List<ProjectImgVo> projectImgVos = projectImgService.queryProjectImgVoListByPid(pid);

			List<String> imgsList = new ArrayList<String>();
			for(ProjectImgVo projectImgVo:projectImgVos){
				imgsList.add(AttachmentConstant.IMAGE_SHOW_URL+projectImgVo.getPiUrl());
			}
			projectVo.setImgsList(imgsList);
		}
		return list;
	}

	public void editAtt(Project project,int status){
		if(status==1){
			String desc = project.getPdescription();
			Map<Long,Long> atts = AttachmentUploadImageUtils.findUploadImages(desc);
			List<Long> list = new ArrayList<Long>();
			if(!atts.isEmpty()){
				for(Long id:atts.keySet()){
					list.add(id);
				}
			}
			attachmentService.editorAttachment(list);
		}
	}
}

