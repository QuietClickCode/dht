
package com.retailers.dht.common.service;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.dht.common.entity.Order;
import com.retailers.tools.exception.AppException;

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
	 * @param goodsIds 商品ids 多个商品用逗号隔开
	 * @param numbers 购买商品数量多个用逗号隔开
	 * @param specs 商品规格
	 * @param orderRemark 备注
	 * @param uaId 收货人id
	 * @return 返回订单号和总金额
	 * @throws AppException
	 */
	public Map<String,Object> shoppingOrder(Long uid,String goodsIds,String numbers,String specs,String orderRemark,Long uaId)throws AppException;

	/**
	 * 用户充值
	 * @param uid 用户id
	 * @param rid 充值id
	 * @return
	 * @throws AppException
	 */
	public Map<String,Object> userRecharge(Long uid,Long rid)throws AppException;



}


