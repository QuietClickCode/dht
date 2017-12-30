
package com.retailers.hnc.common.service;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.ClientIntention;

import java.util.List;
import java.util.Map;
/**
 * 描述：客户意向表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 17:32:34
 */
public interface ClientIntentionService {

	/**
	 * 添加客户意向表
	 * @param clientIntention
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public boolean saveClientIntention(ClientIntention clientIntention);
	/**
	 * 编辑客户意向表
	 * @param clientIntention
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateClientIntention(ClientIntention clientIntention);
	/**
	 * 根据id查询客户意向表
	 * @param iid
	 * @return clientIntention
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public ClientIntention queryClientIntentionByIid(Long iid);
	/**
	 * 查询客户意向表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public Pagination<ClientIntention> queryClientIntentionList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据iid删除客户意向表
	 * @param iid
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public boolean deleteClientIntentionByIid(Long iid);

	public List<ClientIntentionVo> queryClientIntentionVoListByCmId(Long cmId);

}


