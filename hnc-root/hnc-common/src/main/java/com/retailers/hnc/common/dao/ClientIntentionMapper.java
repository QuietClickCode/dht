package com.retailers.hnc.common.dao;
import com.retailers.hnc.common.entity.ClientIntention;
import com.retailers.hnc.common.vo.ClientIntentionVo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：客户意向表DAO
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-29 17:32:34
 */
public interface ClientIntentionMapper {

	/**
	 * 添加客户意向表
	 * @param clientIntention
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public int saveClientIntention(ClientIntention clientIntention);
	/**
	 * 编辑客户意向表
	 * @param clientIntention
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public int updateClientIntention(ClientIntention clientIntention);
	/**
	 * 根据Iid删除客户意向表
	 * @param iid
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public int deleteClientIntentionByIid(Long iid);
	/**
	 * 根据Iid查询客户意向表
	 * @param iid
	 * @return
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public ClientIntention queryClientIntentionByIid(Long iid);
	/**
	 * 查询客户意向表列表
	 * @param pagination 分页对象
	 * @return  客户意向表列表
	 * @author fanghui
	 * @date 2017-12-29 17:32:34
	 */
	public List<ClientIntention> queryClientIntentionList(Pagination<ClientIntention> pagination);

	public List<ClientIntentionVo> queryClientIntentionVoList(Pagination<ClientIntentionVo> pagination);

}
