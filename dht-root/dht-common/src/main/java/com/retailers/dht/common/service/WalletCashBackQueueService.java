
package com.retailers.dht.common.service;
import com.retailers.dht.common.view.WalletCashBackQueueView;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.WalletCashBackQueue;

import java.util.List;
import java.util.Map;
/**
 * 描述：钱包消费返现(包括指定可返现商品类型）Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:43:22
 */
public interface WalletCashBackQueueService {

	/**
	 * 添加钱包消费返现(包括指定可返现商品类型）
	 * @param walletCashBackQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public boolean saveWalletCashBackQueue(WalletCashBackQueue walletCashBackQueue);
	/**
	 * 编辑钱包消费返现(包括指定可返现商品类型）
	 * @param walletCashBackQueue
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateWalletCashBackQueue(WalletCashBackQueue walletCashBackQueue);
	/**
	 * 根据id查询钱包消费返现(包括指定可返现商品类型）
	 * @param ccbqD
	 * @return walletCashBackQueue
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public WalletCashBackQueue queryWalletCashBackQueueByCcbqD(Long ccbqD);
	/**
	 * 查询钱包消费返现(包括指定可返现商品类型）列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public Pagination<WalletCashBackQueue> queryWalletCashBackQueueList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据ccbqD删除钱包消费返现(包括指定可返现商品类型）
	 * @param ccbqD
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public boolean deleteWalletCashBackQueueByCcbqD(Long ccbqD);

	/**
	 * 取得排名公式
	 * @param gcId
	 * @return
	 */
	public List<WalletCashBackQueueView> queryWalletCashBackQueues(Long gcId);

	/**
	 * 取得用户排名公式
	 * @param sUid 用户id
	 * @param type 排名为公式 类型 (0 己返现，1 正在排队)
	 * @return
	 */
	public List<WalletCashBackQueueView> queryUserRankingLists(Long sUid,Long type);

}


