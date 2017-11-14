
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.PayInfo;
import java.util.Map;
/**
 * 描述：支付信息Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-14 21:52:28
 */
public interface PayInfoService {

	/**
	 * 添加支付信息
	 * @param payInfo
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public boolean savePayInfo(PayInfo payInfo);
	/**
	 * 编辑支付信息
	 * @param payInfo
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updatePayInfo(PayInfo payInfo);
	/**
	 * 根据id查询支付信息
	 * @param piId
	 * @return payInfo
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public PayInfo queryPayInfoByPiId(Long piId);
	/**
	 * 查询支付信息列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public Pagination<PayInfo> queryPayInfoList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据piId删除支付信息
	 * @param piId
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public boolean deletePayInfoByPiId(Long piId);

	/**
	 * 添加支付日志
	 * @param payInfo
	 */
	public void addPayInfo(PayInfo payInfo);

}


