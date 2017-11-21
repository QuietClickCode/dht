
package com.retailers.dht.common.service.impl;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.enm.OrderEnum;
import com.retailers.dht.common.entity.Order;
import com.retailers.dht.common.dao.OrderMapper;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.service.ProcedureToolsService;
import com.retailers.tools.exception.AppException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.mybatis.pagination.Pagination;
import org.springframework.transaction.annotation.Transactional;

/**
 * 描述：订单Service
 * @author zhongp
 * @version 1.0
 * @since 1.8
 * @date 2017-11-21 14:23:59
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService {
	Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

    @Autowired
	private OrderMapper orderMapper;

    @Autowired
    private ProcedureToolsService procedureToolsService;

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
    @Transactional(rollbackFor = Exception.class)
	public Map<String, Object> shoppingOrder(Long uid, String goodsIds, String numbers, String orderRemark, Long uaId) throws AppException {
        logger.info("创建购物订单,购买用户:[{}],商品列表:[{}],数量列表:[{}],订单备注:[{}],收货人地址:[{}]",uid,goodsIds,numbers,orderRemark,uaId);
        Date curDate=new Date();
        Map<String,Object> rtnMap=new HashMap<String,Object>();
        String orderNo=procedureToolsService.executeOrderNo(OrderEnum.SHOPPING);
        try{
            rtnMap.put("orderNo",orderNo);
        }finally {
            logger.info("创建购物订单完成，生成订单号:[{}],执行时间:[{}]",orderNo,(System.currentTimeMillis()-curDate.getTime()));
        }
		return rtnMap;
	}
}

