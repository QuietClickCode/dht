
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.CashOrder;
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
	 * 根据coId删除用户提现订单
	 * @param coId
	 * @return
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public boolean deleteCashOrderByCoId(Long coId);

}


