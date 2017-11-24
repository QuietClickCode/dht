package com.retailers.dht.common.service;

import com.retailers.tools.exception.AppException;

import java.util.Map;

/**
 * 支付处理
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/24
 */
public interface PayService {
    /**
     * 创建微信公众号支付
     * @param orderNo 订单号
     * @param openId 微信用户openId
     * @param ip 支付用户的ip地址
     * @return
     */
    public Map<String,String> createWxPay(String orderNo,String openId,String ip)throws AppException;

    /**
     * 微信h5 支付
     * @param orderNo 订单号
     * @param ip ip 地址
     * @return
     * @throws AppException
     */
    public String createWxH5Pay(String orderNo,String ip)throws AppException;

}