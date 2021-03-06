package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.LogWalletCashBackQueue;
import com.retailers.dht.common.entity.WalletCashBackQueue;
import com.retailers.dht.common.view.UserCashBackDetailView;
import com.retailers.dht.common.view.WalletCashBackQueueView;
import com.retailers.dht.common.vo.RankingInfoVo;
import com.retailers.mybatis.pagination.Pagination;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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
	 * 批量添加返现队列
	 * @param walletCashBackQueues
	 * @return
	 */
	public int saveWalletCashBackQueues(List<WalletCashBackQueue> walletCashBackQueues);

	/**
	 * 批量添加日志
	 * @param logs
	 * @return
	 */
	public int saveWalletCashBackQueuesLogs(List<LogWalletCashBackQueue> logs);
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

	/**
	 * 分页查询
	 * @param pagination
	 * @return
	 */
	public List<WalletCashBackQueueView> queryWalletCashBackQueuePage(Pagination<WalletCashBackQueueView> pagination);

	/**
	 * 取得各个商品种 的返现例表
	 * @param gcId 商品类型
	 * @return
	 */
	public List<WalletCashBackQueueView> queryWalletCashBackQueues(Long gcId);

	/**
	 * 取得用户排名公式
	 * @param sUid 用户id
	 * @param types 排名为公式 类型 (0 己返现，1 正在排队)
	 * @return
	 */
	public List<WalletCashBackQueueView> queryUserRankingLists(@Param("sUid") Long sUid,@Param("types") List<Long> types);

	/**
	 * 取得用户返现详情
	 * @param sUid 用户id
	 * @param date 日期
	 * @return
	 */
	public List<UserCashBackDetailView> queryUserCashBackDetail(@Param("sUid") Long sUid, @Param("data")Date date);

	/**
	 * 根据条件取得满足条件的排名公示
	 * @param infos
	 * @param multiple 倍数
	 * @return
	 */
	public List<WalletCashBackQueue> queryRtnCondtionDatas(@Param("infos") List<RankingInfoVo> infos,@Param("multiple") Long multiple);

	/**
	 * 设置商品己返现
	 * @param wcbqIds
	 * @return
	 */
	public int editorWalletCashBack(List<Long> wcbqIds);

	/**
	 * 根据订单id 取得返现列个
	 * @param orderId
	 * @return
	 */
	public List<WalletCashBackQueue> queryWalletCashBackQueueByOid(Long orderId);

	/**
	 * 清除消费排名
	 * @param ccbqId
	 * @return
	 */
	public int clearWalletCashBackQueueByIds(List<Long> ccbqId);

	/**
	 * 初始化提成中累计达成条件
 	 * @return
	 */
	public int initWalletCashBackQueuePrice();

}
