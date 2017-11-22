
package com.retailers.dht.common.service;
import com.retailers.dht.common.view.RechargeView;
import com.retailers.dht.common.vo.RechargeVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.Recharge;
import com.retailers.tools.exception.AppException;

import java.util.List;
import java.util.Map;
/**
 * 描述：充值管理Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-10-15 23:56:18
 */
public interface RechargeService {

	/**
	 * 添加充值管理
	 * @param recharge
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public boolean saveRecharge(Recharge recharge)throws AppException;
	/**
	 * 编辑充值管理
	 * @param recharge
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateRecharge(Recharge recharge)throws AppException;
	/**
	 * 根据id查询充值管理
	 * @param rid
	 * @return recharge
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public Recharge queryRechargeByRid(Long rid);
	/**
	 * 查询充值管理列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public Pagination<RechargeVo> queryRechargeList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据rid删除充值管理
	 * @param rid
	 * @param sId 操作人员
	 * @return
	 * @author zhongp
	 * @date 2017-10-15 23:56:18
	 */
	public boolean deleteRechargeByRid(Long rid,Long sId)throws AppException;

	/**
	 * 取得充值卡列表
	 * @return
	 * @throws AppException
	 */
	public List<RechargeView> queryRechargeLists();

}


