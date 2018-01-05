
package com.retailers.hnc.common.service;
import com.retailers.hnc.common.vo.CheckUserVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.hnc.common.entity.CheckUser;

import java.util.List;
import java.util.Map;
/**
 * 描述：客户通过审核记录表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2018-01-03 23:28:11
 */
public interface CheckUserService {

	/**
	 * 添加客户通过审核记录表
	 * @param checkUser
	 * @return
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public boolean saveCheckUser(CheckUser checkUser);
	/**
	 * 编辑客户通过审核记录表
	 * @param checkUser
	 * @return
	 * @author fanghui
	 * @date
	 */
	public boolean updateCheckUser(CheckUser checkUser);
	/**
	 * 根据id查询客户通过审核记录表
	 * @param cuId
	 * @return checkUser
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public CheckUser queryCheckUserByCuId(Long cuId);
	/**
	 * 查询客户通过审核记录表列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public Pagination<CheckUser> queryCheckUserList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据cuId删除客户通过审核记录表
	 * @param cuId
	 * @return
	 * @author fanghui
	 * @date 2018-01-03 23:28:11
	 */
	public boolean deleteCheckUserByCuId(Long cuId);

	public Map checkUser(String validateCode,Long eid);

	public CheckUserVo queryCheckUserValidateCode( Long cid);

	public List<CheckUserVo> queryCheckUserVoList(Map<String, Object> params, int pageNo, int pageSize);

	public Map queryCheckUserNum(Long oid);

	public List<CheckUserVo> queryAchievement( Map params);
}


