
package com.retailers.razz.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.razz.common.entity.PositionManage;
import java.util.Map;
/**
 * 描述：职位表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-27 16:06:04
 */
public interface PositionManageService {

	/**
	 * 添加职位表
	 * @param positionManage
	 * @return
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public boolean savePositionManage(PositionManage positionManage);
	/**
	 * 编辑职位表
	 * @param positionManage
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updatePositionManage(PositionManage positionManage);
	/**
	 * 根据id查询职位表
	 * @param id
	 * @return positionManage
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public PositionManage queryPositionManageById(Long id);
	/**
	 * 查询职位表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public Pagination<PositionManage> queryPositionManageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除职位表
	 * @param id
	 * @return
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public boolean deletePositionManageById(Long id);

}


