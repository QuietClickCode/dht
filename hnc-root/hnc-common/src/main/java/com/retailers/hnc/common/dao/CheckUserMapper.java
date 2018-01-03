package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.CheckUser;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：客户通过审核记录表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-03 23:28:11
 */
public interface CheckUserMapper {

	/**
	 * 添加客户通过审核记录表
	 * @param checkUser
	 * @return
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public int saveCheckUser(CheckUser checkUser);
	/**
	 * 编辑客户通过审核记录表
	 * @param checkUser
	 * @return
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public int updateCheckUser(CheckUser checkUser);
	/**
	 * 根据CuId删除客户通过审核记录表
	 * @param cuId
	 * @return
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public int deleteCheckUserByCuId(Long cuId);
	/**
	 * 根据CuId查询客户通过审核记录表
	 * @param cuId
	 * @return
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public CheckUser queryCheckUserByCuId(Long cuId);
	/**
	 * 查询客户通过审核记录表列表
	 * @param pagination 分页对象
	 * @return  客户通过审核记录表列表
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public List<CheckUser> queryCheckUserList(Pagination<CheckUser> pagination);

}
