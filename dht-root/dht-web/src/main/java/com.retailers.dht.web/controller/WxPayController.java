package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.utils.WxHttpClientUtils;
import com.retailers.wx.common.utils.wx.WXPayConstants;
import com.retailers.wx.common.utils.wx.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * 微信支付管理
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/7
 */
@Controller
@RequestMapping("wxPay")
public class WxPayController extends BaseController{
    Logger logger= LoggerFactory.getLogger(WxPayController.class);

    @RequestMapping("openWxPayPage")
    public String openWxPayPage(){
        return "m_modle/wx/wxPayOrder";
    }

    /**
     * 微信公从号支付（根据订单号)
     * @param request
     * @param orderNo
     * @return
     */
    @RequestMapping("createWxPay")
    public @ResponseBody Object createWxPay(HttpServletRequest request,String orderNo) {
        logger.info("微信公众号支付订单号:{}", orderNo);
        String apiKey="CF26762CF05A42899F1681872CE3BC89";
        String appId="wxfd2628cfc7f6defb";
        Date curDate=new Date();
        TreeMap<String, String> params = new TreeMap<String, String>();
        String callbackUrl = "http://www.kuaiyis.com/wxPay/callback";
        params.put("appid", appId);
        params.put("openid", "oEIKZxOHy-ovkImvP8X78sMCYlGA");
        params.put("mch_id", "1450860802");
        params.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
        params.put("nonce_str", WXPayUtil.getStringRandom(30));//随机字符串，长度要求在32位以内。推荐随机数生成算法
        params.put("body", "微信支付");//商品简单描述，该字段请按照规范传递，具体请见参数规定
        params.put("out_trade_no", curDate.getTime()+"");// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
        params.put("total_fee", 1+"");//订单总金额，单位为分，详见支付金额
        params.put("spbill_create_ip", request.getRemoteAddr());//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        params.put("notify_url", callbackUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        params.put("trade_type", "JSAPI");// 取值如下：JSAPI--公众号支付，NATIVE--原生扫码支付，APP--app支付等，说明详见参数规定
        params.put("attach", "1");//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用  1 公众号支付 2 扫码支付 3 h5支付
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
            params.clear();
            params.put("appId", appId);
            params.put("timeStamp", System.currentTimeMillis() / 1000 + "");
            params.put("nonceStr", WXPayUtil.getStringRandom(30));
            params.put("package", "prepay_id=" + obj.get("prepay_id"));
            params.put("signType", "MD5");
            params.put("sign", WXPayUtil.generateSignature(params, apiKey));
            params.put("pkg", params.get("package").toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("===============================================================");
        return params;
    }

    /**
     * 微信扫码支付
     * @param request
     * @param orderNo 订单号
     * @return
     */
    @RequestMapping("createWxQRPay")
    public @ResponseBody BaseResp createWxQRPay(HttpServletRequest request, String orderNo) {
        logger.info("微信扫码支付订单号:{}", orderNo);
        String apiKey="CF26762CF05A42899F1681872CE3BC89";
        String appId="wxfd2628cfc7f6defb";
        Date curDate=new Date();
        TreeMap<String, String> params = new TreeMap<String, String>();
        String callbackUrl = "http://www.kuaiyis.com/wxPay/callback";
        params.put("appid", appId);
        params.put("mch_id", "1450860802");
        params.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
        params.put("nonce_str", WXPayUtil.getStringRandom(30));//随机字符串，长度要求在32位以内。推荐随机数生成算法
        params.put("body", "微信支付");//商品简单描述，该字段请按照规范传递，具体请见参数规定
        params.put("out_trade_no", curDate.getTime()+"");// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
        params.put("total_fee", 1+"");//订单总金额，单位为分，详见支付金额
        params.put("spbill_create_ip", request.getRemoteAddr());//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        params.put("notify_url", callbackUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        params.put("trade_type", "NATIVE");// 取值如下：JSAPI--公众号支付，NATIVE--原生扫码支付，APP--app支付等，说明详见参数规定
        params.put("attach", "2");//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
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
        String qrCodeUrl="";
        try{
            obj= WXPayUtil.xmlToMap(retXml);
            if(ObjectUtils.isNotEmpty(obj)){
                qrCodeUrl= obj.get("code_url");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("===============================================================");
        return success(qrCodeUrl);
    }

    /**
     * 微信公从号支付（根据订单号)
     * @param request
     * @param orderNo
     * @return
     */
    @RequestMapping("createWxH5Pay")
    public @ResponseBody BaseResp createWxH5Pay(HttpServletRequest request,String orderNo) {
        logger.info("微信H5页面支付(移动端非微信扫码支付):{}", orderNo);
        String apiKey="CF26762CF05A42899F1681872CE3BC89";
        String appId="wxfd2628cfc7f6defb";
        Date curDate=new Date();
        TreeMap<String, String> params = new TreeMap<String, String>();
        String callbackUrl = "http://www.kuaiyis.com/wxPay/callback";
        params.put("appid", appId);
        params.put("openid", "oEIKZxOHy-ovkImvP8X78sMCYlGA");
        params.put("mch_id", "1450860802");
        params.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
        params.put("nonce_str", WXPayUtil.getStringRandom(30));//随机字符串，长度要求在32位以内。推荐随机数生成算法
        params.put("body", "微信支付");//商品简单描述，该字段请按照规范传递，具体请见参数规定
        params.put("out_trade_no", curDate.getTime()+"");// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
        params.put("total_fee", 1+"");//订单总金额，单位为分，详见支付金额
        params.put("spbill_create_ip", request.getRemoteAddr());//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        params.put("spbill_create_ip", "113.250.220.86");//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
        params.put("notify_url", callbackUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
        params.put("trade_type", "MWEB");// 取值如下：JSAPI--公众号支付，NATIVE--原生扫码支付，APP--app支付等，说明详见参数规定
        params.put("attach", "3");//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用  1 公众号支付 2 扫码支付 3 h5支付
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
        String rtnUrl="";
        try{
            obj= WXPayUtil.xmlToMap(retXml);
            rtnUrl=obj.get("mweb_url");
        }catch (Exception e){
            e.printStackTrace();
        }
        logger.info("===============================================================");
        return success(rtnUrl);
    }

    /**
     * 微信回调支付接口
     */
    @RequestMapping("callback")
    public void pay_callback(HttpServletRequest request, HttpServletResponse response) {
        logger.info("=================================================微信扫码支付成功后回调=================================================");
        ServletOutputStream os = null;
        String return_code = "SUCCESS";
        String return_msg = "OK";
        try {
            os = response.getOutputStream();
            InputStream is = request.getInputStream();
            byte[] cache = new byte[1024];
            int length = -1;
            StringBuffer xmlSb = new StringBuffer("");
            while ((length = is.read(cache)) != -1) {
                xmlSb.append(new String(cache, 0, length));
            }
            System.out.println("xmlSb==========================>:"+xmlSb);
//            if (!Utils.isNullOrEmpty(xmlSb.toString())) {
//                Document doc = DocumentHelper.parseText(xmlSb.toString());
//                Map<String, Object> retWXMap = XMLUtil.Dom2Map(doc);
//                logger.info("回调报文:" + retWXMap);
//                TreeMap<String, Object> retMap2 = new TreeMap<String, Object>();
//                retMap2.putAll(retWXMap);// map按key排序，用户验证签名
//                // 验证签名
//                String attachStr = retMap2.get("attach").toString();// 拓展数据
//                String storeUserId = attachStr.split("~")[0];// 店员
//                String productSerialNumber = attachStr.split("~")[1];
//                String sum = attachStr.split("~")[2];// 金额
//                String project = attachStr.split("~")[3];// 方案
//                if (OAOConstants.PAY_PROJECT_TWO.equals(project)) {// 如果是方案二，则获取对应的微信支付秘钥
//                    OwnerUser ownerUser = ownerUserService.getOwnerUserByproductSerialNumber(productSerialNumber);
//                    apiKey = ownerUser.getWechantMdKey();
//                }
//                StoreUserVo storeUserVo = storeLeagureService.findStoreUserInfoById(storeUserId);
//                if ("3".equals(project)) {// 门店独立支付方案
//                    Store store = storeService.getStoreById(storeUserVo.getStoreId());
//                    apiKey = store.getWechantMdKey();
//                }
//                String sign = retMap2.get("sign").toString();
//                retMap2.remove("sign");
//                String mySign = WXUtils.getWxPaySign(retMap2, apiKey);
//                logger.info("接受的签名:" + sign);
//                logger.info("我的签名:" + mySign);
//                if (sign.equals(mySign)) {// 签名验证成功
//                    if (retMap2.get("return_code").toString().equals("SUCCESS") && retMap2.get("result_code").toString().equals("SUCCESS")) {// 支付成功，添加交易记录，并推送至app端
//                        String tradeNo = retMap2.get("out_trade_no").toString();
//                        tradingRecordService.payAct(tradeNo, OAOConstants.WECHANT_PAY);// 修改交易记录状态
//                        logger.info("环信支付信息推送");
//                        List<TradingRecord> list = tradingRecordService.findTradingRecordByDealNumber(tradeNo, null);
//                        Long payTime = new Date().getTime();
//                        if (list != null && list.size() > 0) {
//                            payTime = list.get(0).getCreateTime().getTime();
//                        }
//                        String serverOrderNumber = attachStr.split("~")[4];
//                        if (EmptyUtil.isNullOrEmpty(serverOrderNumber)) {//购物订单
//                            appSellBillService.deleteUnused(productSerialNumber, storeUserVo.getStoreId(), storeUserId);
//                            boolean hxFlag = AppUtils.sendHXOrderMsg(AppUtils.getHXUserNameByUserId(storeUserId), 1, Double.parseDouble(sum), storeUserVo.getStoreName(), storeUserVo.getStoreUserName(), "WX", payTime, tradeNo);
//                            logger.info("推送是否成功:" + hxFlag);
//                        } else {//服务订单
//                            ServeOrder serveOrder = serveOrderService.findOrderByOrderNumber(serverOrderNumber);
//                            ServeOrderDetail serveOrderDetail = serveOrderDetailService.getServeOrderDetail(serveOrder.getId());
//                            serveOrder.setSeverTime(serveOrderDetail.getServeStartTime());
//                            serveOrder.setServeName(serveOrderDetail.getServeName());
//                            boolean hxFlag = AppUtils.sendHXServerOrderMsg(AppUtils.getHXUserNameByUserId(storeUserId), 1, serveOrder);
//                            logger.info("推送是否成功:" + hxFlag);
//                        }
//                    }
//                } else {
//                    return_code = "FAIL";
//                    return_msg = "签名验证失败";
//                    logger.info("签名验证失败");
//                }
//
//            } else {
//                return_code = "FAIL";
//                return_msg = "报文为空";
//                logger.info("报文为空");
//            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            Map<String, String> outMap = new HashMap<String, String>();
            outMap.put("return_code", return_code);
            outMap.put("return_msg", return_msg);
            String outXml = WXPayUtil.map2Xml(outMap);// 输出到微信
            try {
                os.write(outXml.getBytes("utf-8"));
                if (os != null) {
                    os.flush();
                    os.close();
                }
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            logger.info("微信接口输出:" + outXml);
            logger.info("=======================================================================================================");
        }
    }
}
