
package com.retailers.dht.common.service;
import com.retailers.dht.common.vo.CashOrderVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.CashOrder;
import com.retailers.tools.exception.AppException;

import java.util.Map;
/**
 * 描述：用户提现订单Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-02-27 23:04:40
 */
public interface CashOrderService {

	/**
	 * 添加用户提现订单
	 * @param cashOrder
	 * @return
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public boolean saveCashOrder(CashOrder cashOrder);
	/**
	 * 编辑用户提现订单
	 * @param cashOrder
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateCashOrder(CashOrder cashOrder);
	/**
	 * 根据id查询用户提现订单
	 * @param coId
	 * @return cashOrder
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public CashOrder queryCashOrderByCoId(Long coId);
	/**
	 * 查询用户提现订单列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public Pagination<CashOrder> queryCashOrderList(Map<String, Object> params, int pageNo, int pageSize);

	/**
	 * 后台展示提现用户列表
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination<CashOrderVo> queryCashOrderLists(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据coId删除用户提现订单
	 * @param coId
	 * @return
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public boolean deleteCashOrderByCoId(Long coId);

	/**
	 * 用户创建提现订单
	 * @param uid 提现用户id
	 * @param money 提现金额
	 * @param remark 提现备注
	 * @return
	 */
	public Map<String,Object> userCashMoney(Long uid,Long money,String remark)throws AppException;

	/**
	 * 提现申请审核
	 * @param sysUid 后台用户id
	 * @param orId 提现申请id
	 * @param status 审核状态
	 * @param remark 提现备注
	 * @return
	 * @throws AppException
	 */
	public boolean auditingCashOrder(Long sysUid,Long orId,Long status,String remark)throws AppException;

	/**
	 * 向用户打款
	 * @param ocId
	 * @param suid
	 * @return
	 * @throws AppException
	 */
	public String playMoneyToUser(Long ocId,Long suid,String ip)throws AppException;

}


