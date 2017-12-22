
package com.retailers.hnc.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.hnc.common.entity.ProjectImg;
import com.retailers.hnc.common.dao.ProjectImgMapper;
import com.retailers.hnc.common.service.ProjectImgService;
import com.retailers.hnc.common.vo.ProjectImgVo;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
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
	public boolean saveProjectImg(ProjectImg projectImg) {
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
		int status = projectImgMapper.deleteProjectImgByPiId(piId);
		return status == 1 ? true : false;
	}
	public List<ProjectImgVo> queryProjectImgVoListByPid(Long pid){
		List<ProjectImgVo> list = projectImgMapper.queryProjectImgVoListByPid(pid);
		return list;
	}
}

