package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.WalletCashBackQueue;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：钱包消费返现(包括指定可返现商品类型）DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-20 01:43:22
 */
public interface WalletCashBackQueueMapper {

	/**
	 * 添加钱包消费返现(包括指定可返现商品类型）
	 * @param walletCashBackQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public int saveWalletCashBackQueue(WalletCashBackQueue walletCashBackQueue);
	/**
	 * 编辑钱包消费返现(包括指定可返现商品类型）
	 * @param walletCashBackQueue
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public int updateWalletCashBackQueue(WalletCashBackQueue walletCashBackQueue);
	/**
	 * 根据CcbqD删除钱包消费返现(包括指定可返现商品类型）
	 * @param ccbqD
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public int deleteWalletCashBackQueueByCcbqD(Long ccbqD);
	/**
	 * 根据CcbqD查询钱包消费返现(包括指定可返现商品类型）
	 * @param ccbqD
	 * @return
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public WalletCashBackQueue queryWalletCashBackQueueByCcbqD(Long ccbqD);
	/**
	 * 查询钱包消费返现(包括指定可返现商品类型）列表
	 * @param pagination 分页对象
	 * @return  钱包消费返现(包括指定可返现商品类型）列表
	 * @author zhongp
	 * @date 2017-12-20 01:43:22
	 */
	public List<WalletCashBackQueue> queryWalletCashBackQueueList(Pagination<WalletCashBackQueue> pagination);

}
