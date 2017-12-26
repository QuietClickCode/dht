
package com.retailers.hnc.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.ClientManage;
import java.util.Map;
/**
 * 描述：客户管理Service
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-26 13:47:05
 */
public interface ClientManageService {

	/**
	 * 添加客户管理
	 * @param clientManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public ClientManage saveClientManage(ClientManage clientManage);
	/**
	 * 编辑客户管理
	 * @param clientManage
	 * @return
	 * @author wangjue
	 * @date
	 */
	public boolean updateClientManage(ClientManage clientManage);
	/**
	 * 根据id查询客户管理
	 * @param tmId
	 * @return clientManage
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public ClientManage queryClientManageByTmId(Long tmId);
	/**
	 * 查询客户管理列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public Pagination<ClientManage> queryClientManageList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据tmId删除客户管理
	 * @param tmId
	 * @return
	 * @author wangjue
	 * @date 2017-12-26 13:47:05
	 */
	public boolean deleteClientManageByTmId(Long tmId);

}


