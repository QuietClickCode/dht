
package com.retailers.sbj.common.service.impl;

import com.retailers.sbj.common.dao.ProjectImgMapper;
import com.retailers.sbj.common.entity.ProjectImg;
import com.retailers.sbj.common.service.ProjectImgService;
import com.retailers.sbj.common.vo.ProjectImgVo;
import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

/**
 * 描述：项目图片表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 09:46:01
 */
@Service("projectimgService")
public class ProjectImgServiceImpl implements ProjectImgService {
	@Autowired
	private ProjectImgMapper projectImgMapper;

	@Autowired
	private AttachmentService attachmentService;

	public boolean saveProjectImg(ProjectImg projectImg) {
		attachmentService.editorAttachment(projectImg.getAid());
		int status = projectImgMapper.saveProjectImg(projectImg);
		return status == 1 ? true : false;
	}
	public boolean updateProjectImg(ProjectImg projectImg) {
		int status = projectImgMapper.updateProjectImg(projectImg);
		return status == 1 ? true : false;
	}
	public ProjectImg queryProjectImgByPiId(Long piId) {
		return projectImgMapper.queryProjectImgByPiId(piId);
	}

	public Pagination<ProjectImg> queryProjectImgList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<ProjectImg> page = new Pagination<ProjectImg>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<ProjectImg> list = projectImgMapper.queryProjectImgList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteProjectImgByPiId(Long piId) {
		Long imgId = queryProjectImgByPiId(piId).getAid();
		attachmentService.editorAttachment(imgId, AttachmentConstant.ATTACHMENT_STATUS_NO);
		int status = projectImgMapper.deleteProjectImgByPiId(piId);
		return status == 1 ? true : false;
	}
	public List<ProjectImgVo> queryProjectImgVoListByPid(Long pid){
		List<ProjectImgVo> list = projectImgMapper.queryProjectImgVoListByPid(pid);
		return list;
	}
}

