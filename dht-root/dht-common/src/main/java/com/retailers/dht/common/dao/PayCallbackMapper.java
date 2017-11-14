package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.PayCallback;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：支付回调信息DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-14 21:56:27
 */
public interface PayCallbackMapper {

	/**
	 * 添加支付回调信息
	 * @param payCallback
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public int savePayCallback(PayCallback payCallback);
	/**
	 * 编辑支付回调信息
	 * @param payCallback
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public int updatePayCallback(PayCallback payCallback);
	/**
	 * 根据PcId删除支付回调信息
	 * @param pcId
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public int deletePayCallbackByPcId(Long pcId);
	/**
	 * 根据PcId查询支付回调信息
	 * @param pcId
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public PayCallback queryPayCallbackByPcId(Long pcId);
	/**
	 * 查询支付回调信息列表
	 * @param pagination 分页对象
	 * @return  支付回调信息列表
	 * @author zhongp
	 * @date 2017-11-14 21:56:27
	 */
	public List<PayCallback> queryPayCallbackList(Pagination<PayCallback> pagination);

}
