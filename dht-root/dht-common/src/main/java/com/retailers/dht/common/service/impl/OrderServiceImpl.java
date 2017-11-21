
package com.retailers.dht.common.service.impl;
import java.util.List;
import java.util.Map;
import com.retailers.dht.common.entity.Order;
import com.retailers.dht.common.dao.OrderMapper;
import com.retailers.dht.common.service.OrderService;
import com.retailers.tools.exception.AppException;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
/**
 * 描述：订单Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-21 14:23:59
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderMapper orderMapper;
	public boolean saveOrder(Order order) {
		int status = orderMapper.saveOrder(order);
		return status == 1 ? true : false;
	}
	public boolean updateOrder(Order order) {
		int status = orderMapper.updateOrder(order);
		return status == 1 ? true : false;
	}
	public Order queryOrderById(Long id) {
		return orderMapper.queryOrderById(id);
	}

	public Pagination<Order> queryOrderList(Map<String, Object> params,int pageNo,int pageSize) {
		Pagination<Order> page = new Pagination<Order>();
		page.setPageNo(pageNo);
		page.setPageSize(pageSize);
		page.setParams(params);
		List<Order> list = orderMapper.queryOrderList(page);
		page.setData(list);
		return page;
	}
	public boolean deleteOrderById(Long id) {
		int status = orderMapper.deleteOrderById(id);
		return status == 1 ? true : false;
	}

	/**
	 * 购物订单
	 * @param uid 购买用户id
	 * @param goodsIds 商品ids 多个商品用逗号隔开
	 * @param numbers 购买商品数量多个用逗号隔开
	 * @param orderRemark 备注
	 * @param uaId 收货人id
	 * @return
	 * @throws AppException
	 */
	public Map<String, Object> shoppingOrder(Long uid, String goodsIds, String numbers, String orderRemark, Long uaId) throws AppException {
		return null;
	}
}

