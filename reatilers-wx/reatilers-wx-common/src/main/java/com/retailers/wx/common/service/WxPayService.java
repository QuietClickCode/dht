
package com.retailers.wx.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.exception.AppException;
import com.retailers.wx.common.entity.WxPay;
import com.retailers.wx.common.vo.WxPayVo;

import java.util.Map;
/**
 * 描述：微信支付设置Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-12-28 00:26:12
 */
public interface WxPayService {

	/**
	 * 添加微信支付设置
	 * @param wxPay
	 * @return
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public boolean saveWxPay(WxPay wxPay);
	/**
	 * 编辑微信支付设置
	 * @param wxPay
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateWxPay(WxPay wxPay);
	/**
	 * 根据id查询微信支付设置
	 * @param wxId
	 * @return wxPay
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public WxPay queryWxPayByWxId(Long wxId);
	/**
	 * 查询微信支付设置列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public Pagination<WxPay> queryWxPayList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据wxId删除微信支付设置
	 * @param wxId
	 * @return
	 * @author zhongp
	 * @date 2017-12-28 00:26:12
	 */
	public boolean deleteWxPayByWxId(Long wxId);

	/**
	 * 取得当前 支付信息
	 * @return
	 */
	public WxPayVo queryCurUsedPay();

	public boolean editorWxPay(WxPay wxPay)throws AppException;

}


