
package com.retailers.hnc.common.service;
import com.retailers.hnc.common.vo.ProjectImgVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.ProjectImg;

import java.util.List;
import java.util.Map;
/**
 * 描述：项目图片表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 09:46:01
 */
public interface ProjectImgService {

	/**
	 * 添加项目图片表
	 * @param projectImg
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public boolean saveProjectImg(ProjectImg projectImg);
	/**
	 * 编辑项目图片表
	 * @param projectImg
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateProjectImg(ProjectImg projectImg);
	/**
	 * 根据id查询项目图片表
	 * @param piId
	 * @return projectImg
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public ProjectImg queryProjectImgByPiId(Long piId);
	/**
	 * 查询项目图片表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public Pagination<ProjectImg> queryProjectImgList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据piId删除项目图片表
	 * @param piId
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public boolean deleteProjectImgByPiId(Long piId);

	public List<ProjectImgVo> queryProjectImgVoListByPid(Long pid);
}


