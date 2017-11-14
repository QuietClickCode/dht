
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.PayInfo;
import com.retailers.dht.common.dao.PayInfoMapper;
import com.retailers.dht.common.service.PayInfoService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：支付信息Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-14 21:52:28
 */
@Service("payinfoService")
public class PayInfoServiceImpl implements PayInfoService {
	@Autowired
	private PayInfoMapper payInfoMapper;
	public boolean savePayInfo(PayInfo payInfo) {
		int status = payInfoMapper.savePayInfo(payInfo);
		return status == 1 ? true : false;
	}
	public boolean updatePayInfo(PayInfo payInfo) {
		int status = payInfoMapper.updatePayInfo(payInfo);
		return status == 1 ? true : false;
	}
	public PayInfo queryPayInfoByPiId(Long piId) {
		return payInfoMapper.queryPayInfoByPiId(piId);
	}

	public Pagination<PayInfo> queryPayInfoList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<PayInfo> page = new Pagination<PayInfo>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<PayInfo> list = payInfoMapper.queryPayInfoList(page);
		page.setData(list);
		return page;
	}
	public boolean deletePayInfoByPiId(Long piId) {
		int status = payInfoMapper.deletePayInfoByPiId(piId);
		return status == 1 ? true : false;
	}

	/**
	 * 添加支付日志信息
	 * @param payInfo
	 */
	@Async
	public void addPayInfo(PayInfo payInfo) {
		 payInfoMapper.savePayInfo(payInfo);
	}
}

