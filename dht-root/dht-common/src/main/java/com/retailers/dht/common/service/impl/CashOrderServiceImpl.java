
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.CashOrder;
import com.retailers.dht.common.dao.CashOrderMapper;
import com.retailers.dht.common.service.CashOrderService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：用户提现订单Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2018-02-27 23:04:40
 */
@Service("cashorderService")
public class CashOrderServiceImpl implements CashOrderService {
	@Autowired
	private CashOrderMapper cashOrderMapper;
	public boolean saveCashOrder(CashOrder cashOrder) {
		int status = cashOrderMapper.saveCashOrder(cashOrder);
		return status == 1 ? true : false;
	}
	public boolean updateCashOrder(CashOrder cashOrder) {
		int status = cashOrderMapper.updateCashOrder(cashOrder);
		return status == 1 ? true : false;
	}
	public CashOrder queryCashOrderByCoId(Long coId) {
		return cashOrderMapper.queryCashOrderByCoId(coId);
	}

	public Pagination<CashOrder> queryCashOrderList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<CashOrder> page = new Pagination<CashOrder>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CashOrder> list = cashOrderMapper.queryCashOrderList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCashOrderByCoId(Long coId) {
		int status = cashOrderMapper.deleteCashOrderByCoId(coId);
		return status == 1 ? true : false;
	}
}

