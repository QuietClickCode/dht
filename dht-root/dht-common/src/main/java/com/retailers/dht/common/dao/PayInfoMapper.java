package com.retailers.dht.common.dao;
import com.retailers.dht.common.entity.PayInfo;
import com.retailers.mybatis.pagination.Pagination;
import java.util.List;
/**
 * 描述：支付信息DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-14 21:52:28
 */
public interface PayInfoMapper {

	/**
	 * 添加支付信息
	 * @param payInfo
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public int savePayInfo(PayInfo payInfo);
	/**
	 * 编辑支付信息
	 * @param payInfo
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public int updatePayInfo(PayInfo payInfo);
	/**
	 * 根据PiId删除支付信息
	 * @param piId
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public int deletePayInfoByPiId(Long piId);
	/**
	 * 根据PiId查询支付信息
	 * @param piId
	 * @return
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public PayInfo queryPayInfoByPiId(Long piId);
	/**
	 * 查询支付信息列表
	 * @param pagination 分页对象
	 * @return  支付信息列表
	 * @author zhongp
	 * @date 2017-11-14 21:52:28
	 */
	public List<PayInfo> queryPayInfoList(Pagination<PayInfo> pagination);

}
