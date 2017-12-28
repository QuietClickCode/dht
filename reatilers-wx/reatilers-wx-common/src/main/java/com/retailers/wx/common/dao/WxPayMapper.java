package com.retailers.wx.common.dao;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.wx.common.entity.WxPay;
import com.retailers.wx.common.vo.WxPayVo;

import java.util.List;
/**
 * 描述：微信支付设置DAO
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 00:26:12
 */
public interface WxPayMapper {

	/**
	 * 添加微信支付设置
	 * @param wxPay
	 * @return
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public int saveWxPay(WxPay wxPay);
	/**
	 * 编辑微信支付设置
	 * @param wxPay
	 * @return
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public int updateWxPay(WxPay wxPay);
	/**
	 * 根据WxId删除微信支付设置
	 * @param wxId
	 * @return
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public int deleteWxPayByWxId(Long wxId);
	/**
	 * 根据WxId查询微信支付设置
	 * @param wxId
	 * @return
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public WxPay queryWxPayByWxId(Long wxId);
	/**
	 * 查询微信支付设置列表
	 * @param pagination 分页对象
	 * @return  微信支付设置列表
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public List<WxPay> queryWxPayList(Pagination<WxPay> pagination);

	public WxPayVo queryCurUsedPay();

}
