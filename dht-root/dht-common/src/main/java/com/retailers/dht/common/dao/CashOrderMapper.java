package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.CashOrder;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：用户提现订单DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-02-27 23:04:40
 */
public interface CashOrderMapper {

	/**
	 * 添加用户提现订单
	 * @param cashOrder
	 * @return
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public int saveCashOrder(CashOrder cashOrder);
	/**
	 * 编辑用户提现订单
	 * @param cashOrder
	 * @return
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public int updateCashOrder(CashOrder cashOrder);
	/**
	 * 根据CoId删除用户提现订单
	 * @param coId
	 * @return
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public int deleteCashOrderByCoId(Long coId);
	/**
	 * 根据CoId查询用户提现订单
	 * @param coId
	 * @return
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public CashOrder queryCashOrderByCoId(Long coId);
	/**
	 * 查询用户提现订单列表
	 * @param pagination 分页对象
	 * @return  用户提现订单列表
	 * @author zhongp
	 * @date 2018-02-27 23:04:40
	 */
	public List<CashOrder> queryCashOrderList(Pagination<CashOrder> pagination);

}
