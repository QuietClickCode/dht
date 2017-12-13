package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.entity.PayCallback;
import com.retailers.dht.common.service.OrderProcessingQueueService;
import com.retailers.dht.common.service.PayCallbackService;
import com.retailers.dht.common.service.PayService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.IPUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.utils.wx.WXPayUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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

    @Autowired
    private PayCallbackService payCallbackService;
    @Autowired
    private OrderProcessingQueueService orderProcessingQueueService;
    @Autowired
    private PayService payService;
    @RequestMapping("payInfo")
    public ModelAndView openPayInfo(HttpServletRequest request, String orderNo,String price){
        ModelAndView model=new ModelAndView();
        model.addObject("orderNo",orderNo);
        model.addObject("price",price);
        String url=redirectUrl(request,"pay/payInfo");;
        model.setViewName(url);
        return model;
    }

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
    public @ResponseBody BaseResp createWxPay(HttpServletRequest request,String orderNo) {
//        orderNo="zp2017111423301235";
//        logger.info("微信公众号支付订单号:{}", orderNo);
//        String apiKey="CF26762CF05A42899F1681872CE3BC89";
//        String appId="wxfd2628cfc7f6defb";
        String ip=request.getRemoteAddr();
//        Date curDate=new Date();
//        TreeMap<String, String> params = new TreeMap<String, String>();
//        String callbackUrl = "http://www.kuaiyis.com/wxPay/callback";
//        params.put("appid", appId);
//        params.put("openid", "oEIKZxOHy-ovkImvP8X78sMCYlGA");
//        params.put("mch_id", "1450860802");
//        params.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
//        params.put("nonce_str", WXPayUtil.getStringRandom(30));//随机字符串，长度要求在32位以内。推荐随机数生成算法
//        params.put("body", "微信支付");//商品简单描述，该字段请按照规范传递，具体请见参数规定
//        params.put("out_trade_no", orderNo);// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
//        params.put("total_fee", 1+"");//订单总金额，单位为分，详见支付金额
//        params.put("spbill_create_ip", ip);//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
//        params.put("notify_url", callbackUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
//        params.put("trade_type", "JSAPI");// 取值如下：JSAPI--公众号支付，NATIVE--原生扫码支付，APP--app支付等，说明详见参数规定
//        params.put("attach", SystemConstant.WX_PAY_WAY_GZH+"");//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用  1 公众号支付 2 扫码支付 3 h5支付
//        String sign = null;
//        try {
//            sign = WXPayUtil.generateSignature(params,apiKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        params.put("sign", sign);//通过签名算法计算得出的签名值，详见签名生成算法
//        logger.info("发送的报文:" + JSON.toJSONString(params));
//        String retXml = WxHttpClientUtils.reqPost(WXPayConstants.UNIFIEDORDER_URL_SUFFIX, WXPayUtil.map2Xml(params));
//        logger.info("微信预付单返回:" + retXml);
//        Map<String,String> obj=null;
//        try{
//            obj= WXPayUtil.xmlToMap(retXml);
//            savePayInfo(obj,obj.get("prepay_id"),SystemConstant.WX_PAY_WAY_GZH,orderNo,ip);
//            params.clear();
//            params.put("appId", appId);
//            params.put("timeStamp", System.currentTimeMillis() / 1000 + "");
//            params.put("nonceStr", WXPayUtil.getStringRandom(30));
//            params.put("package", "prepay_id=" + obj.get("prepay_id"));
//            params.put("signType", "MD5");
//            params.put("sign", WXPayUtil.generateSignature(params, apiKey));
//            params.put("pkg", params.get("package").toString());
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        logger.info("===============================================================");
//        return params;
        try{
            Map<String,String> rtn = payService.createWxPay(orderNo,"oEIKZxOHy-ovkImvP8X78sMCYlGA",ip);
            return success(rtn);
        }catch(Exception e){
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 微信扫码支付
     * @param request
     * @param orderNo 订单号
     * @return
     */
    @RequestMapping("createWxQRPay")
    public @ResponseBody BaseResp createWxQRPay(HttpServletRequest request, String orderNo) {
//        logger.info("微信扫码支付订单号:{}", orderNo);
//        String ip=request.getRemoteAddr();
//        String apiKey="CF26762CF05A42899F1681872CE3BC89";
//        String appId="wxfd2628cfc7f6defb";
//        Date curDate=new Date();
//        TreeMap<String, String> params = new TreeMap<String, String>();
//        String callbackUrl = "http://www.kuaiyis.com/wxPay/callback";
//        params.put("appid", appId);
//        params.put("mch_id", "1450860802");
//        params.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
//        params.put("nonce_str", WXPayUtil.getStringRandom(30));//随机字符串，长度要求在32位以内。推荐随机数生成算法
//        params.put("body", "微信支付");//商品简单描述，该字段请按照规范传递，具体请见参数规定
//        params.put("out_trade_no",orderNo);// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
//        params.put("total_fee", 1+"");//订单总金额，单位为分，详见支付金额
//        params.put("spbill_create_ip", ip);//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
//        params.put("notify_url", callbackUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
//        params.put("trade_type", "NATIVE");// 取值如下：JSAPI--公众号支付，NATIVE--原生扫码支付，APP--app支付等，说明详见参数规定
//        params.put("attach", SystemConstant.WX_PAY_WAY_SM+"");//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用
//        String sign = null;
//        try {
//            sign = WXPayUtil.generateSignature(params,apiKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        params.put("sign", sign);//通过签名算法计算得出的签名值，详见签名生成算法
//        logger.info("发送的报文:" + JSON.toJSONString(params));
//        String retXml = WxHttpClientUtils.reqPost(WXPayConstants.UNIFIEDORDER_URL_SUFFIX, WXPayUtil.map2Xml(params));
//        logger.info("微信预付单返回:" + retXml);
//        Map<String,String> obj=null;
//        String qrCodeUrl="";
//        try{
//            obj= WXPayUtil.xmlToMap(retXml);
//            savePayInfo(obj,obj.get("code_url"),SystemConstant.WX_PAY_WAY_SM,orderNo,ip);
//            if(ObjectUtils.isNotEmpty(obj)){
//                qrCodeUrl= obj.get("code_url");
//            }
//        }catch (Exception e){
//            e.printStackTrace();
//        }
//        return success(qrCodeUrl);
//        try{
//            Map<String,String> rtn = payService.createWxH5Pay(orderNo,ip);
//            return success(rtn);
//        }catch(Exception e){
//            return errorForSystem(e.getMessage());
//        }
        return null;
    }

    /**
     * 微信公从号支付（根据订单号)
     * @param request
     * @param orderNo
     * @return
     */
    @RequestMapping("createWxH5Pay")
    public @ResponseBody BaseResp createWxH5Pay(HttpServletRequest request,String orderNo) {
//        orderNo="zp2017111423301235";
//        logger.info("微信H5页面支付(移动端非微信扫码支付):{}", orderNo);
//        String ip=request.getRemoteAddr();
//        String apiKey="CF26762CF05A42899F1681872CE3BC89";
//        String appId="wxfd2628cfc7f6defb";
//        Date curDate=new Date();
//        TreeMap<String, String> params = new TreeMap<String, String>();
//        String callbackUrl = "http://www.kuaiyis.com/wxPay/callback";
//        params.put("appid", appId);
//        params.put("mch_id", "1450860802");
//        params.put("device_info", "WEB");//自定义参数，可以为终端设备号(门店号或收银设备ID)，PC网页或公众号内支付可以传"WEB"
//        params.put("nonce_str", WXPayUtil.getStringRandom(30));//随机字符串，长度要求在32位以内。推荐随机数生成算法
//        params.put("body", "微信支付");//商品简单描述，该字段请按照规范传递，具体请见参数规定
//        params.put("out_trade_no",orderNo);// 商户系统内部订单号，要求32个字符内，只能是数字、大小写字母_-|*@ ，且在同一个商户号下唯一。详见商户订单号
//        params.put("total_fee", 1+"");//订单总金额，单位为分，详见支付金额
//        params.put("spbill_create_ip", ip);//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
//        params.put("spbill_create_ip", "113.250.220.86");//APP和网页支付提交用户端ip，Native支付填调用微信支付API的机器IP。
//        params.put("notify_url", callbackUrl);//异步接收微信支付结果通知的回调地址，通知url必须为外网可访问的url，不能携带参数。
//        params.put("trade_type", "MWEB");// 取值如下：JSAPI--公众号支付，NATIVE--原生扫码支付，APP--app支付等，说明详见参数规定
//        params.put("attach", SystemConstant.WX_PAY_WAY_H5+"");//附加数据，在查询API和支付通知中原样返回，可作为自定义参数使用  1 公众号支付 2 扫码支付 3 h5支付
//        String sign = null;
//        try {
//            sign = WXPayUtil.generateSignature(params,apiKey);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        params.put("sign", sign);//通过签名算法计算得出的签名值，详见签名生成算法
//        logger.info("发送的报文:" + JSON.toJSONString(params));
//        String retXml = WxHttpClientUtils.reqPost(WXPayConstants.UNIFIEDORDER_URL_SUFFIX, WXPayUtil.map2Xml(params));
//        logger.info("微信预付单返回:" + retXml);
//        Map<String,String> obj=null;
//        String rtnUrl="";
//        try{
//            obj= WXPayUtil.xmlToMap(retXml);
//            savePayInfo(obj,obj.get("mweb_url"),SystemConstant.WX_PAY_WAY_H5,orderNo,ip);
//            rtnUrl=obj.get("mweb_url");
//        }catch (Exception e){
//            e.printStackTrace();
//        }
        try{
            String url = payService.createWxH5Pay(orderNo,"113.250.222.80");
            return success(url);
        }catch(Exception e){
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 微信回调支付接口
     */
    @RequestMapping("callback")
    public void wxPayCallback(HttpServletRequest request, HttpServletResponse response) {
        logger.info("=================================================微信扫码支付成功后回调=================================================");
        ServletOutputStream os = null;
        String return_code = "SUCCESS";
        String return_msg = "OK";
        PayCallback pc=new PayCallback();
        String ip=request.getRemoteAddr();
        pc.setPcRemoteAdd(ip);
        pc.setPcWay(SystemConstant.PLATFORM_PAY_WAY_WX);
        try {
            os = response.getOutputStream();
            InputStream is = request.getInputStream();
            byte[] cache = new byte[1024];
            int length = -1;
            StringBuffer wxCallbackStr = new StringBuffer("");
            while ((length = is.read(cache)) != -1) {
                wxCallbackStr.append(new String(cache, 0, length));
            }
            if(ObjectUtils.isNotEmpty(wxCallbackStr)){
                Map<String,String> retWXMap= WXPayUtil.xmlToMap(wxCallbackStr.toString());
                logger.info("回调报文:" + retWXMap);
                TreeMap<String, String> retMap2 = new TreeMap<String, String>();
                retMap2.putAll(retWXMap);// map按key排序，用户验证签名
                String sign = retMap2.get("sign").toString();
                retMap2.remove("sign");
                String mySign = WXPayUtil.generateSignature(retMap2, "CF26762CF05A42899F1681872CE3BC89");
                logger.info("接受的签名:" + sign);
                logger.info("我的签名:" + mySign);
                if (sign.equals(mySign)) {
                    String attach=retWXMap.get("attach");
                    if(ObjectUtils.isNotEmpty(attach)){
                        pc.setPcType(Integer.parseInt(attach));
                    }
                    String tradeNo = retMap2.get("out_trade_no");
                    pc.setPcOrderNo(tradeNo);
                    pc.setPcIsSign(SystemConstant.EXECUTE_SUCCESS);
                    pc.setPcSign(StringUtils.concat(sign,":",mySign));
                    boolean isSuccess=false;
                    if (retMap2.get("return_code").equals("SUCCESS") && retMap2.get("result_code").equals("SUCCESS")) {
                        isSuccess=true;
                    }
                    long payWay=0;
                    if(ObjectUtils.isNotEmpty(retMap2.get("attach"))){
                        payWay=Long.parseLong("attach");
                    }
                    orderProcessingQueueService.addQueue(tradeNo,isSuccess,retMap2.get("transaction_id"),payWay,StringUtils.formate(retMap2.get("err_code"),retMap2.get("err_code_des")));
                }else{
                    return_code = "FAIL";
                    return_msg = "签名验证失败";
                    logger.info("签名验证失败");
                    pc.setPcIsSign(SystemConstant.EXECUTE_FAIL);
                    pc.setPcSign(StringUtils.concat(sign,":",mySign));
                }
                pc.setPcContext(JSON.toJSONString(retWXMap));
            }else {
                return_code = "FAIL";
                return_msg = "报文为空";
                logger.info("报文为空");
                pc.setPcIsSign(SystemConstant.EXECUTE_FAIL);
                pc.setPcContext("报文为空");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }catch (Exception e) {
            e.printStackTrace();
        } finally {
            Map<String, String> outMap = new HashMap<String, String>();
            outMap.put("return_code", return_code);
            outMap.put("return_msg", return_msg);
            String outXml = WXPayUtil.map2Xml(outMap);// 输出到微信
            pc.setPcReturnCode(return_code);
            pc.setPcCreateTime(new Date());
            payCallbackService.savePayCallback(pc);
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
        }
    }
//    /**
//     *  保存存支付信息
//     * @param obj 支付结果信息
//     * @param prePayId 确认id（扫描支付时为二维码地址)
//     * @param payType 方式
//     * @param orderNo 单号
//     */
//    private void savePayInfo(Map<String,String> obj,String prePayId,int payType,String orderNo,String ip){
//        PayInfo info=new PayInfo();
//        info.setPiWay(SystemConstant.PLATFORM_PAY_WAY_WX);
//        info.setPiType(payType);
//        info.setPiContext(JSON.toJSONString(obj));
//        info.setPiPrepayId(prePayId);
//        info.setPiCreateTime(new Date());
//        info.setPiOrderNo(orderNo);
//        info.setPcRemoteAdd(ip);
//        payInfoService.ad dPayInfo(info);
//    }
}
