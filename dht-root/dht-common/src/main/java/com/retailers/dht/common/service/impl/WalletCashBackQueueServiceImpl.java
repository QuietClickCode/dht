
package com.retailers.dht.common.service.impl;
import java.util.*;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.entity.WalletCashBackQueue;
import com.retailers.dht.common.dao.WalletCashBackQueueMapper;
import com.retailers.dht.common.service.WalletCashBackQueueService;
import com.retailers.dht.common.view.UserCashBackDetailView;
import com.retailers.dht.common.view.WalletCashBackQueueView;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.NumberUtils;
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

	public List<WalletCashBackQueueView> queryWalletCashBackQueues(Long gcId) {
		List<WalletCashBackQueueView>lists= walletCashBackQueueMapper.queryWalletCashBackQueues(gcId);
		return lists;
	}


	public Pagination<WalletCashBackQueueView> queryWalletCashBackQueues(Long gcId, Long type, String phone, String userNm, int pageNo, int pageSize) {
		Pagination<WalletCashBackQueueView> page = new Pagination<WalletCashBackQueueView>();
		Map<String,Object> params=new HashMap<String, Object>();
		params.put("gcId",gcId);
		params.put("type",type);
		params.put("phone",phone);
		params.put("userNm",userNm);
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<WalletCashBackQueueView> list = walletCashBackQueueMapper.queryWalletCashBackQueuePage(page);
		page.setData(list);
		return page;
	}

	/**
	 * 取得用户返现详情
	 * @param uid
	 * @return
	 */
	public Map<String, String> queryUserCashBackDetail(Long uid) {
		Map<String,Long> map=new HashMap<String, Long>();
		Date curDate=new Date();
		List<UserCashBackDetailView> list = walletCashBackQueueMapper.queryUserCashBackDetail(uid, DateUtil.addDays(curDate,-7));
		for(UserCashBackDetailView ucbd:list){
			map.put(ucbd.getType(),ucbd.getPrice());
		}
		//计算页面显示详情 待提现-不可提现 =可提现
		long allowCash=map.get("waitCash")-map.get("unCash");
		map.remove("unCash");
		Map<String,String> rtn=new HashMap<String, String>();
		for(String key:map.keySet()){
			rtn.put(key, NumberUtils.formaterNumberPower(map.get(key)));
		}
		rtn.put("allowCash",NumberUtils.formaterNumberPower(allowCash));
		return rtn;
	}

	public Map<String, Long> queryUserCashBackDetailMoney(Long uid) {
		Map<String,Long> map=new HashMap<String, Long>();
		Date curDate=new Date();
		List<UserCashBackDetailView> list = walletCashBackQueueMapper.queryUserCashBackDetail(uid, DateUtil.addDays(curDate,-7));
		for(UserCashBackDetailView ucbd:list){
			map.put(ucbd.getType(),ucbd.getPrice());
		}
		//计算页面显示详情 待提现-不可提现 =可提现
		long allowCash=map.get("waitCash")-map.get("unCash");
		map.remove("unCash");
		map.put("allowCash",allowCash);
		return map;
	}

	/**
	 *
	 * @param sUid 用户id
	 * @param type 排名为公式 类型 (0 己返现，1 正在排队)
	 * @return
	 */
	public List<WalletCashBackQueueView> queryUserRankingLists(Long sUid, Long type) {
		List<Long> types=new ArrayList<Long>();
		if(type.intValue()==0){
			types.add(SystemConstant.PLAT_CASH_BACK_MENOY_STATUS_END);
		}else{
			types.add(SystemConstant.PLAT_CASH_BACK_MENOY_STATUS_LINE_UP);
			types.add(SystemConstant.PLAT_CASH_BACK_MENOY_STATUS_LINE_FUNDRAISING);
		}
		List<WalletCashBackQueueView>lists= walletCashBackQueueMapper.queryUserRankingLists(sUid,types);
		return lists;
	}
}

