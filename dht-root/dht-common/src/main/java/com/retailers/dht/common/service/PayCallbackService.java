
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.PayCallback;
import java.util.Map;
/**
 * 描述：支付回调信息Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-14 21:56:27
 */
public interface PayCallbackService {

	/**
	 * 添加支付回调信息
	 * @param payCallback
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public void savePayCallback(PayCallback payCallback);
	/**
	 * 编辑支付回调信息
	 * @param payCallback
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updatePayCallback(PayCallback payCallback);
	/**
	 * 根据id查询支付回调信息
	 * @param pcId
	 * @return payCallback
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public PayCallback queryPayCallbackByPcId(Long pcId);
	/**
	 * 查询支付回调信息列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public Pagination<PayCallback> queryPayCallbackList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据pcId删除支付回调信息
	 * @param pcId
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public boolean deletePayCallbackByPcId(Long pcId);

}


