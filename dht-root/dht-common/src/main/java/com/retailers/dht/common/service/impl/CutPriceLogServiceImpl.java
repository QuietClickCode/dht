
package com.retailers.dht.common.service.impl;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.entity.CutPriceLog;
import com.retailers.dht.common.dao.CutPriceLogMapper;
import com.retailers.dht.common.entity.CutPricePrice;
import com.retailers.dht.common.service.CutPriceLogService;
import com.retailers.dht.common.service.CutPricePriceService;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：砍价日志表Service
 * @author fanghui
 * @version 1.0
 * @since 1.8
 * @date 2017-12-07 15:50:55
 */
@Service("cutpricelogService")
public class CutPriceLogServiceImpl implements CutPriceLogService {
	@Autowired
	private CutPriceLogMapper cutPriceLogMapper;
	@Autowired
	private CutPricePriceService cutPricePriceService;
	public boolean saveCutPriceLog(CutPriceLog cutPriceLog) {
		Long gdcpId = cutPriceLog.getGdcpId();
		Map params = new HashMap();
		params.put("gdcpId",gdcpId);
		params.put("isDelete",0L);
		List<CutPricePrice> list = cutPricePriceService.queryCutPricePriceList(params,1,1000).getData();
		if(!ObjectUtils.isEmpty(list)){
			CutPricePrice cutPricePrice = list.get(0);
			cutPriceLog.setCplCutdownprice(cutPricePrice.getCppPrice());
			cutPricePrice.setIsDelete(1L);
			cutPricePriceService.updateCutPricePrice(cutPricePrice);
		}
		int status = cutPriceLogMapper.saveCutPriceLog(cutPriceLog);
		return status == 1 ? true : false;
	}
	public boolean updateCutPriceLog(CutPriceLog cutPriceLog) {
		int status = cutPriceLogMapper.updateCutPriceLog(cutPriceLog);
		return status == 1 ? true : false;
	}
	public CutPriceLog queryCutPriceLogByCplId(Long cplId) {
		return cutPriceLogMapper.queryCutPriceLogByCplId(cplId);
	}

	public Pagination<CutPriceLog> queryCutPriceLogList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<CutPriceLog> page = new Pagination<CutPriceLog>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<CutPriceLog> list = cutPriceLogMapper.queryCutPriceLogList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteCutPriceLogByCplId(Long cplId) {
		int status = cutPriceLogMapper.deleteCutPriceLogByCplId(cplId);
		return status == 1 ? true : false;
	}
}

