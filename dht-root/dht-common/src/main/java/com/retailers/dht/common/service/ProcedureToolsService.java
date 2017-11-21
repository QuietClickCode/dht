package com.retailers.dht.common.service;

import com.retailers.dht.common.enm.OrderEnum;
import com.retailers.tools.exception.AppException;

/**
 * 工具类
 */
public interface ProcedureToolsService {

    /**
     * 订单号生成
     * @param orderEnum 订单类型
     * @return
     */
    public String executeOrderNo(OrderEnum orderEnum);

    /**
     * 清除缓存数据
     */
    public void clearSequenceData();
}
