package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.OpeningEmpClient;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：开盘与雇员和客户的关系表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 09:01:40
 */
public interface OpeningEmpClientMapper {

	/**
	 * 添加开盘与雇员和客户的关系表
	 * @param openingEmpClient
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public int saveOpeningEmpClient(OpeningEmpClient openingEmpClient);
	/**
	 * 编辑开盘与雇员和客户的关系表
	 * @param openingEmpClient
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public int updateOpeningEmpClient(OpeningEmpClient openingEmpClient);
	/**
	 * 根据OecId删除开盘与雇员和客户的关系表
	 * @param oecId
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public int deleteOpeningEmpClientByOecId(Long oecId);
	/**
	 * 根据OecId查询开盘与雇员和客户的关系表
	 * @param oecId
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public OpeningEmpClient queryOpeningEmpClientByOecId(Long oecId);
	/**
	 * 查询开盘与雇员和客户的关系表列表
	 * @param pagination 分页对象
	 * @return  开盘与雇员和客户的关系表列表
	 * @author fanghui
	 * @date 2017-12-29 09:01:40
	 */
	public List<OpeningEmpClient> queryOpeningEmpClientList(Pagination<OpeningEmpClient> pagination);

	public List<ClientIntentionVo> queryNotGivenList(Pagination<ClientIntentionVo> pagination);

	public List<ClientIntentionVo> queryCheckingandpassandnotpassList(Pagination<ClientIntentionVo> pagination);

}
