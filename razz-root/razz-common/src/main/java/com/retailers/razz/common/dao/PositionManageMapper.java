package com.retailers.razz.common.dao;
import com.retailers.razz.common.entity.PositionManage;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：职位表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-27 16:06:04
 */
public interface PositionManageMapper {

	/**
	 * 添加职位表
	 * @param positionManage
	 * @return
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public int savePositionManage(PositionManage positionManage);
	/**
	 * 编辑职位表
	 * @param positionManage
	 * @return
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public int updatePositionManage(PositionManage positionManage);
	/**
	 * 根据Id删除职位表
	 * @param id
	 * @return
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public int deletePositionManageById(Long id);
	/**
	 * 根据Id查询职位表
	 * @param id
	 * @return
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public PositionManage queryPositionManageById(Long id);
	/**
	 * 查询职位表列表
	 * @param pagination 分页对象
	 * @return  职位表列表
	 * @author fanghui
	 * @date 2018-01-27 16:06:04
	 */
	public List<PositionManage> queryPositionManageList(Pagination<PositionManage> pagination);

}
