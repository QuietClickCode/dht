package com.retailers.sbj.common.dao;
import com.retailers.sbj.common.entity.ClientManage;
import com.retailers.sbj.common.vo.ClientManageVo;
import com.retailers.sbj.common.vo.OpeningVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 描述：客户表DAO
 * @author wangjue
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 15:40:49
 */
public interface ClientManageMapper {

	/**
	 * 添加客户表
	 * @param clientManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 15:40:49
	 */
	public int saveClientManage(ClientManage clientManage);
	/**
	 * 编辑客户表
	 * @param clientManage
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 15:40:49
	 */
	public int updateClientManage(ClientManage clientManage);
	/**
	 * 根据TmId删除客户表
	 * @param tmId
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 15:40:49
	 */
	public int deleteClientManageByTmId(Long tmId);
	/**
	 * 根据TmId查询客户表
	 * @param tmId
	 * @return
	 * @author wangjue
	 * @date 2017-12-28 15:40:49
	 */
	public ClientManage queryClientManageByTmId(Long tmId);

	public ClientManageVo queryClientManageVoByTmId(Long tmId);
	/**
	 * 查询客户表列表
	 * @param pagination 分页对象
	 * @return  客户表列表
	 * @author wangjue
	 * @date 2017-12-28 15:40:49
	 */
	public List<ClientManageVo> queryClientManageList(Pagination<ClientManageVo> pagination);

	public List<ClientManageVo> queryClientManageListWeb(Pagination<ClientManageVo> pagination);
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

	public List<ClientManageVo> queryClientManagerCount();

	public OpeningVo queryEarlyCanComeIn(@Param("cid") Long cid);

	/**
	 * 查询客户表列表
	 * @param pagination 分页对象
	 * @return  客户表列表
	 * @author wangjue
	 * @date 2017-12-28 15:40:49
	 */
	public List<ClientManageVo> queryClientManageVoList(Pagination<ClientManageVo> pagination);
}
