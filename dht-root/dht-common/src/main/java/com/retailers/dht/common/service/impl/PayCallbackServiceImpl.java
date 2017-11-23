
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.PayCallback;
import com.retailers.dht.common.dao.PayCallbackMapper;
import com.retailers.dht.common.service.PayCallbackService;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：支付回调信息Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-14 21:56:27
 */
@Service("paycallbackService")
public class PayCallbackServiceImpl implements PayCallbackService {
	@Autowired
	private PayCallbackMapper payCallbackMapper;

	@Async
	public void savePayCallback(PayCallback payCallback) {
		payCallbackMapper.savePayCallback(payCallback);
	}
	public boolean updatePayCallback(PayCallback payCallback) {
		int status = payCallbackMapper.updatePayCallback(payCallback);
		return status == 1 ? true : false;
	}
	public PayCallback queryPayCallbackByPcId(Long pcId) {
		return payCallbackMapper.queryPayCallbackByPcId(pcId);
	}

	public Pagination<PayCallback> queryPayCallbackList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<PayCallback> page = new Pagination<PayCallback>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<PayCallback> list = payCallbackMapper.queryPayCallbackList(page);
		page.setData(list);
		return page;
	}
	public boolean deletePayCallbackByPcId(Long pcId) {
		int status = payCallbackMapper.deletePayCallbackByPcId(pcId);
		return status == 1 ? true : false;
	}
}

