
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.WalletCashBackQueue;
import com.retailers.dht.common.dao.WalletCashBackQueueMapper;
import com.retailers.dht.common.service.WalletCashBackQueueService;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：钱包消费返现(包括指定可返现商品类型）Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:43:22
 */
@Service("walletcashbackqueueService")
public class WalletCashBackQueueServiceImpl implements WalletCashBackQueueService {
	@Autowired
	private WalletCashBackQueueMapper walletCashBackQueueMapper;
	public boolean saveWalletCashBackQueue(WalletCashBackQueue walletCashBackQueue) {
		int status = walletCashBackQueueMapper.saveWalletCashBackQueue(walletCashBackQueue);
		return status == 1 ? true : false;
	}
	public boolean updateWalletCashBackQueue(WalletCashBackQueue walletCashBackQueue) {
		int status = walletCashBackQueueMapper.updateWalletCashBackQueue(walletCashBackQueue);
		return status == 1 ? true : false;
	}
	public WalletCashBackQueue queryWalletCashBackQueueByCcbqD(Long ccbqD) {
		return walletCashBackQueueMapper.queryWalletCashBackQueueByCcbqD(ccbqD);
	}

	public Pagination<WalletCashBackQueue> queryWalletCashBackQueueList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<WalletCashBackQueue> page = new Pagination<WalletCashBackQueue>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WalletCashBackQueue> list = walletCashBackQueueMapper.queryWalletCashBackQueueList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteWalletCashBackQueueByCcbqD(Long ccbqD) {
		int status = walletCashBackQueueMapper.deleteWalletCashBackQueueByCcbqD(ccbqD);
		return status == 1 ? true : false;
	}
}

