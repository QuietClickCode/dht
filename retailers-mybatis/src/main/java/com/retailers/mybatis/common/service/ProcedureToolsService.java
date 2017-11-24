package com.retailers.mybatis.common.service;

import com.retailers.mybatis.common.enm.OrderEnum;
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

    /**
     * 添加单线程锁
     * @param key 锁的key
     */
    public void singleLockManager(String key)throws AppException;

    /**
     * 解除单线程锁
     * @param key 锁的key
     */
    public void singleUnLockManager(String key);

}
