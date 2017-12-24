package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.ProjectImg;
import com.retailers.hnc.common.vo.ProjectImgVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;
/**
 * 描述：项目图片表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-22 09:46:01
 */
public interface ProjectImgMapper {

	/**
	 * 添加项目图片表
	 * @param projectImg
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public int saveProjectImg(ProjectImg projectImg);
	/**
	 * 编辑项目图片表
	 * @param projectImg
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public int updateProjectImg(ProjectImg projectImg);
	/**
	 * 根据PiId删除项目图片表
	 * @param piId
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public int deleteProjectImgByPiId(Long piId);
	/**
	 * 根据PiId查询项目图片表
	 * @param piId
	 * @return
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public ProjectImg queryProjectImgByPiId(Long piId);
	/**
	 * 查询项目图片表列表
	 * @param pagination 分页对象
	 * @return  项目图片表列表
	 * @author fanghui
	 * @date 2017-12-22 09:46:01
	 */
	public List<ProjectImg> queryProjectImgList(Pagination<ProjectImg> pagination);

	public List<ProjectImgVo> queryProjectImgVoListByPid(@Param("pid") Long pid);

}
