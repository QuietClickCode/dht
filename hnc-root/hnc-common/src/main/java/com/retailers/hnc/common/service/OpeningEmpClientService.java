
package com.retailers.hnc.common.service;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.OpeningEmpClient;

import java.util.Map;
/**
 * 描述：开盘与雇员和客户的关系表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 09:01:40
 */
public interface OpeningEmpClientService {

	/**
	 * 添加开盘与雇员和客户的关系表
	 * @param openingEmpClient
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public boolean saveOpeningEmpClient(OpeningEmpClient openingEmpClient);
	/**
	 * 编辑开盘与雇员和客户的关系表
	 * @param openingEmpClient
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateOpeningEmpClient(OpeningEmpClient openingEmpClient);
	/**
	 * 根据id查询开盘与雇员和客户的关系表
	 * @param oecId
	 * @return openingEmpClient
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public OpeningEmpClient queryOpeningEmpClientByOecId(Long oecId);
	/**
	 * 查询开盘与雇员和客户的关系表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public Pagination<OpeningEmpClient> queryOpeningEmpClientList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据oecId删除开盘与雇员和客户的关系表
	 * @param oecId
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public boolean deleteOpeningEmpClientByOecId(Long oecId);

	public Pagination<ClientIntentionVo> queryNotGivenList(Map<String, Object> params, int pageNo, int pageSize);

	public Pagination<ClientIntentionVo> queryCheckingandpassandnotpassList(Map<String, Object> params, int pageNo, int pageSize);

	public boolean addCheckClient(Long oid,Long eid,String cmIds);

	public boolean changeClientStatus(Long oid,Long eid,String cmIds,Long status);
}


