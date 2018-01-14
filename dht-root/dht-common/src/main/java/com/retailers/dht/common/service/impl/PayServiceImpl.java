package com.retailers.dht.common.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.constant.OrderConstant;
import com.retailers.dht.common.constant.OrderProcessingQueueConstant;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.dao.OrderProcessingQueueMapper;
import com.retailers.dht.common.dao.PayInfoMapper;
import com.retailers.dht.common.entity.Order;
import com.retailers.dht.common.entity.OrderProcessingQueue;
import com.retailers.dht.common.entity.PayInfo;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.service.PayService;
import com.retailers.mybatis.common.constant.SingleThreadLockConstant;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.config.WxConfig;
import com.retailers.wx.common.utils.WxHttpClientUtils;
import com.retailers.wx.common.utils.wx.WXPayConstants;
import com.retailers.wx.common.utils.wx.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Map;
import java.util.TreeMap;


/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/24
 */
@Service
public class PayServiceImpl implements PayService {
    Logger logger = LoggerFactory.getLogger(PayServiceImpl.class);

    @Autowired
    private ProcedureToolsService procedureToolsService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private PayInfoMapper payInfoMapper;
    @Autowired
    private OrderProcessingQueueMapper orderProcessingQueueMapper;

    /**
     *  公众号支付
     * @param orderNo 订单号
     * @param openId 微信用户openId
     * @param ip 支付用户的ip地址
     * @return
     * @throws AppException
     */
    public Map<String, String> createWxPay(String orderNo, String openId,String ip) throws AppException {
        logger.info("开始执行微信公众号支付，支付单号:[{}],支付用户的openId:[{}],ip地址：[{}]",orderNo,openId,ip);
        TreeMap<String, String> params = new TreeMap<String, String>();
        Date curDate=new Date();
        //添加同步锁
        String key= StringUtils.formates(SingleThreadLockConstant.PAY_LOCK,orderNo);
        procedureToolsService.singleLockManager(key);
        try{
            Order order=queryOrderNo(orderNo);
            PayInfo pi=queryPayInfo(OrderConstant.ORDER_PAY_WAY_WX,SystemConstant.WX_PAY_WAY_GZH,orderNo);
//            String apiKey="CF26762CF05A42899F1681872CE3BC89";
//            String appId="wxfd2628cfc7f6defb";
            String apiKey= WxConfig.WX_API_KEY;
            String appId=WxConfig.APP_ID;
            if(ObjectUtils.isEmpty(pi)){
//                String callbackUrl = "http://www.kuaiyis.com/wxPay/callback";
                String callbackUrl = StringUtils.concat(SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_PC_URL),"wxPay/callback");
                params.put("appid", appId);
                params.put("openid", openId);
                params.put("mch_id", WxConfig.WX_MCH_ID);
                params.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
                params.put("nonce_str", WXPayUtil.getStringRandom(30));//随机字符串，长度要求在32位以内。推荐随机数生成算法
                params.put("body", "微信支付");//商品简单描述，该字段请按照规范传递，具体请见参数规定
                params.put("out_trade_no", orderNo);// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
                params.put("total_fee", order.getOrderTradePrice()+"");//订单总金额，单位为分，详见支付金额
                params.put("spbill_create_ip", ip);//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
                params.put("notify_url", callbackUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
                params.put("trade_type", "JSAPI");// 取值如下：JSAPI--公众号支付，NATIVE--原生扫码支付，APP--app支付等，说明详见参数规定
                params.put("attach", SystemConstant.WX_PAY_WAY_GZH+"");//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用  1 公众号支付 2 扫码支付 3 h5支付
                String sign = null;
                try {
                    sign = WXPayUtil.generateSignature(params,apiKey);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                params.put("sign", sign);//通过签名算法计算得出的签名值，详见签名生成算法
                logger.info("发送的报文:" + JSON.toJSONString(params));
                String retXml = WxHttpClientUtils.reqPost(WXPayConstants.UNIFIEDORDER_URL_SUFFIX, WXPayUtil.map2Xml(params));
                logger.info("微信预付单返回:" + retXml);
                Map<String,String> obj=null;
                try{
                    obj= WXPayUtil.xmlToMap(retXml);
                    savePayInfo(obj,obj.get("prepay_id"),SystemConstant.WX_PAY_WAY_GZH,orderNo,ip);
                    params.clear();
                    params.put("appId", appId);
                    params.put("timeStamp", System.currentTimeMillis() / 1000 + "");
                    params.put("nonceStr", WXPayUtil.getStringRandom(30));
                    params.put("package", "prepay_id=" + obj.get("prepay_id"));
                    params.put("signType", "MD5");
                    params.put("sign", WXPayUtil.generateSignature(params, apiKey));
                    params.put("pkg", params.get("package").toString());
                }catch (Exception e){
                    throw new AppException(e.getMessage());
                }
            }else{
                try{
                    params.clear();
                    params.put("appId", appId);
                    params.put("timeStamp", System.currentTimeMillis() / 1000 + "");
                    params.put("nonceStr", WXPayUtil.getStringRandom(30));
                    params.put("package", "prepay_id=" + pi.getPiPrepayId());
                    params.put("signType", "MD5");
                    params.put("sign", WXPayUtil.generateSignature(params, apiKey));
                    params.put("pkg", params.get("package").toString());
                }catch(Exception e){
                    throw new AppException(e.getMessage());
                }
            }
            addOrderProcessingQueue(order.getOrderNo(),OrderConstant.ORDER_PAY_WAY_WX,SystemConstant.WX_PAY_WAY_GZH);
//
//            Map<String,String> obj=null;
//            try{
//                obj= WXPayUtil.xmlToMap(retXml);
//                savePayInfo(obj,obj.get("prepay_id"),SystemConstant.WX_PAY_WAY_GZH,orderNo,ip);
//                params.clear();
//                params.put("appId", appId);
//                params.put("timeStamp", System.currentTimeMillis() / 1000 + "");
//                params.put("nonceStr", WXPayUtil.getStringRandom(30));
//                params.put("package", "prepay_id=" + obj.get("prepay_id"));
//                params.put("signType", "MD5");
//                params.put("sign", WXPayUtil.generateSignature(params, apiKey));
//                params.put("pkg", params.get("package").toString());
//            }catch (Exception e){
//                throw new AppException(e.getMessage());
//            }
        }finally {
            procedureToolsService.singleUnLockManager(key);
            logger.info("微信公众号支付执行完成，耗时：{}",(System.currentTimeMillis()-curDate.getTime()));
        }
        return params;
    }

    private void addOrderProcessingQueue(String orderNo,Integer payWay,Integer payUseWay){
        OrderProcessingQueue opq=new OrderProcessingQueue();
        opq.setCreateTime(new Date());
        opq.setOrderNo(orderNo);
        opq.setType(OrderProcessingQueueConstant.ORDER_QUEUE_TYPE_UPDATE);
        JSONObject obj=new JSONObject();
        obj.put("orderPayWay",payWay);
        obj.put("orderPayUseWay",payUseWay);
        obj.put("orderPayDate",new Date());
        opq.setParams(JSON.toJSONString(obj));
        opq.setStatus(OrderProcessingQueueConstant.ORDER_EXECUTE_STATUS_UN);
        orderProcessingQueueMapper.saveOrderProcessingQueue(opq);
        com.retailers.dht.common.constant.SystemConstant.addOrderQueue(opq);
    }

    public String createWxH5Pay(String orderNo, String ip) throws AppException {
        logger.info("开始执行h5扫描支付，支付单号:[{}],ip地址：[{}]",orderNo,ip);
        //添加同步锁
        String key= StringUtils.formates(SingleThreadLockConstant.PAY_LOCK,orderNo);
        procedureToolsService.singleLockManager(key);
        String rtnUrl="";
        Date curDate=new Date();
        Order order=orderService.queryOrderByOrderNo(orderNo);
        if(ObjectUtils.isEmpty(order)||order.getOrderStatus().intValue()!=0){
            throw new AppException("未知订单");
        }
        try{
            logger.info("微信H5页面支付(移动端非微信扫码支付):{}", orderNo);
//            String apiKey="CF26762CF05A42899F1681872CE3BC89";
//            String appId="wxfd2628cfc7f6defb";
            String apiKey= WxConfig.WX_API_KEY;
            String appId=WxConfig.APP_ID;
            TreeMap<String, String> params = new TreeMap<String, String>();
//            String callbackUrl = "http://www.kuaiyis.com/wxPay/callback";
            String callbackUrl = StringUtils.concat(SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_PC_URL),"wxPay/callback");
            params.put("appid", appId);
            params.put("mch_id",WxConfig.WX_MCH_ID);
            params.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
            params.put("nonce_str", WXPayUtil.getStringRandom(30));//随机字符串，长度要求在32位以内。推荐随机数生成算法
            params.put("body", "微信支付");//商品简单描述，该字段请按照规范传递，具体请见参数规定
            params.put("out_trade_no",orderNo);// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
            params.put("total_fee", order.getOrderTradePrice()+"");//订单总金额，单位为分，详见支付金额
            params.put("spbill_create_ip", ip);//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
            params.put("notify_url", callbackUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
            params.put("trade_type", "MWEB");// 取值如下：JSAPI--公众号支付，NATIVE--原生扫码支付，APP--app支付等，说明详见参数规定
            params.put("attach", SystemConstant.WX_PAY_WAY_H5+"");//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用  1 公众号支付 2 扫码支付 3 h5支付
            String sign = null;
            try {
                sign = WXPayUtil.generateSignature(params,apiKey);
            } catch (Exception e) {
                e.printStackTrace();
            }
            params.put("sign", sign);//通过签名算法计算得出的签名值，详见签名生成算法
            logger.info("发送的报文:" + JSON.toJSONString(params));
            String retXml = WxHttpClientUtils.reqPost(WXPayConstants.UNIFIEDORDER_URL_SUFFIX, WXPayUtil.map2Xml(params));
            logger.info("微信预付单返回:" + retXml);
            Map<String,String> obj=null;
            try{
                obj= WXPayUtil.xmlToMap(retXml);
                savePayInfo(obj,obj.get("mweb_url"),SystemConstant.WX_PAY_WAY_H5,orderNo,ip);
                rtnUrl=obj.get("mweb_url");
            }catch (Exception e){
                e.printStackTrace();
                throw new AppException(e.getMessage());
            }
            addOrderProcessingQueue(orderNo,OrderConstant.ORDER_PAY_WAY_WX,SystemConstant.WX_PAY_WAY_SM);
        }finally {
            procedureToolsService.singleUnLockManager(key);
            logger.info("h5扫描支付执行完成，耗时：{}",(System.currentTimeMillis()-curDate.getTime()));
        }
        return rtnUrl;
    }

    public Order queryOrderNo(String orderNo)throws AppException{
        Order order=orderService.queryOrderByOrderNo(orderNo);
        return order;
    }

    /**
     *  保存存支付信息
     * @param obj 支付结果信息
     * @param prePayId 确认id（扫描支付时为二维码地址)
     * @param payType 方式
     * @param orderNo 单号
     */
    private void savePayInfo(Map<String,String> obj,String prePayId,int payType,String orderNo,String ip){
        PayInfo info=new PayInfo();
        info.setPiWay(OrderConstant.ORDER_PAY_WAY_WX);
        info.setPiType(payType);
        info.setPiContext(JSON.toJSONString(obj));
        info.setPiPrepayId(prePayId);
        info.setPiCreateTime(new Date());
        info.setPiOrderNo(orderNo);
        info.setPcRemoteAdd(ip);
        payInfoMapper.savePayInfo(info);
    }

    /**
     * 跟据单号取得支付信息
     * @param orderNo
     * @param payType
     * @return
     */
    private PayInfo queryPayInfo(int piWay,int payType,String orderNo){
        PayInfo payInfo = payInfoMapper.queryPayInfo(piWay,payType,orderNo);
        return payInfo;
    }
}
