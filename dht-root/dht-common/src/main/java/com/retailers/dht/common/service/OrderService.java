
package com.retailers.dht.common.service;
import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.entity.OrderProcessingQueue;
import com.retailers.dht.common.vo.BuyInfoVo;
import com.retailers.dht.common.vo.OrderVo;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.Order;
import com.retailers.tools.exception.AppException;

import java.util.List;
import java.util.Map;
/**
 * 描述：订单Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-21 14:23:59
 */
public interface OrderService {

	/**
	 * 添加订单
	 * @param order
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public boolean saveOrder(Order order);
	/**
	 * 编辑订单
	 * @param order
	 * @return
	 * @author zhongp
	 * @date
	 */
	public boolean updateOrder(Order order);
	/**
	 * 根据id查询订单
	 * @param id
	 * @return order
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public Order queryOrderById(Long id);
	/**
	 * 查询订单列表
	 * @param params 请求参数
	 * @param pageNo 当前页数,从1开始
	 * @param pageSize 分页条数
	 * @return 分页对象
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public Pagination<Order> queryOrderList(Map<String, Object> params, int pageNo, int pageSize);
	/**
	 * 根据id删除订单
	 * @param id
	 * @return
	 * @author zhongp
	 * @date 2017-11-21 14:23:59
	 */
	public boolean deleteOrderById(Long id);
	/**
	 *  根据订单号取得订单
	 * @param orderNo
	 * @return
	 */
	public Order queryOrderByOrderNo(String orderNo);

	/**
	 * 购物订单
	 * @param uid 购买用户id
	 * @param buyInfos 购买详情
	 * @param inviterUid 推荐人id
	 * @param inviterGid 推荐商品id
	 * @return 返回订单号和总金额
	 * @throws AppException
	 */
	public Map<String,Object> shoppingOrder(Long uid, BuyInfoVo buyInfos,Long inviterUid,Long inviterGid)throws AppException;

	/**
	 * 购买特价商品
	 * @param uid
	 * @param buyInfos
	 * @param inviterUid
	 * @param cspId 特价优惠产品记录id
	 * @return
	 * @throws AppException
	 */
	public Map<String,Object> buySpecialOfferGoods(Long uid, BuyInfoVo buyInfos,Long inviterUid,Long cspId)throws AppException;

	/**
	 * 购买秒杀商品
	 * @param uid
	 * @param buyInfos
	 * @param inviterUid 是否是推荐商品
	 * @param cspId 特价优惠产品记录id
	 * @return
	 * @throws AppException
	 */
	public Map<String,Object> buySeckillGoods(Long uid, BuyInfoVo buyInfos,Long inviterUid,Long cspId)throws AppException;

	/**
	 * 购买砍价商品
	 * @param uid
	 * @param buyInfos
	 * @param cspId
	 * @return
	 * @throws AppException
	 */
	public Map<String,Object> buyCutPrice(Long uid, BuyInfoVo buyInfos,Long cspId)throws AppException;

	/**
	 * 用户充值
	 * @param uid 用户id
	 * @param rid 充值id
	 * @return
	 * @throws AppException
	 */
	public Map<String,Object> userRecharge(Long uid,Long rid)throws AppException;

	/**
	 * 修改订单状态
	 * @param opq
	 * @return
	 * @throws AppException
	 */
	public boolean updateOrderStatus(OrderProcessingQueue opq)throws AppException;
	/**
	 * 订单支付回调处理
	 * @param opq
	 * @return
	 * @throws AppException
	 */
	public boolean orderPayCallback(OrderProcessingQueue opq)throws AppException;

	/**
	 * 钱包支付
	 * @param uid 用户id
	 * @param orderNo 订单号
	 * @param payPwd 支付密码
	 * @return
	 * @throws AppException
	 */
	public boolean walletPay(Long uid,String orderNo,String payPwd)throws AppException;

	/**
	 * 取得订单列表
	 * @param params
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public Pagination<OrderVo> queryOrderLists(Map<String, Object> params, int pageNo, int pageSize);

}


