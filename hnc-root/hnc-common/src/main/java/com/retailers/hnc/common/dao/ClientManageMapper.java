package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：客户管理DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-26 13:47:05
 */
public interface ClientManageMapper {

	/**
	 * 添加客户管理
	 * @param clientManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public int saveClientManage(ClientManage clientManage);
	/**
	 * 编辑客户管理
	 * @param clientManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public int updateClientManage(ClientManage clientManage);
	/**
	 * 根据TmId删除客户管理
	 * @param tmId
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public int deleteClientManageByTmId(Long tmId);
	/**
	 * 根据TmId查询客户管理
	 * @param tmId
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public ClientManage queryClientManageByTmId(Long tmId);
	/**
	 * 查询客户管理列表
	 * @param pagination 分页对象
	 * @return  客户管理列表
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public List<ClientManage> queryClientManageList(Pagination<ClientManage> pagination);

	/**
	 * 查询当天登记过的客户
	 * @return  返回当天登记过客户的总数
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public Integer queryCurClientCount();

	/**
	 * 查询所有登记过的客户
	 * @return  返回所有登记过客户的总数
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public Integer queryClientCount();
}
