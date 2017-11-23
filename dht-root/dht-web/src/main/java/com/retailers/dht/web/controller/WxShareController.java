package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.entity.PayCallback;
import com.retailers.dht.common.entity.PayInfo;
import com.retailers.dht.common.service.PayCallbackService;
import com.retailers.dht.common.service.PayInfoService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.IPUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.utils.WxHttpClientUtils;
import com.retailers.wx.common.utils.wx.WXPayConstants;
import com.retailers.wx.common.utils.wx.WXPayUtil;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

@Controller
@RequestMapping("wxShare")
public class WxShareController extends BaseController{
    Logger logger= LoggerFactory.getLogger(WxShareController.class);

    @Autowired
    private PayInfoService payInfoService;
    @Autowired
    private PayCallbackService payCallbackService;

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
    @RequestMapping("createWxShare")
    public @ResponseBody Object createWxPay(HttpServletRequest request,String orderNo) {
        orderNo="zp2017111423301235";
        logger.info("微信公众号支付订单号:{}", orderNo);
        String apiKey="CF26762CF05A42899F1681872CE3BC89";
        String appId="wxfd2628cfc7f6defb";
        String ip=request.getRemoteAddr();
        Date curDate=new Date();



        return null;
    }

    public static void main(String[] args){
        System.out.println(new WxShareController().getAcessData());

    }

    public String getAcessData(){
        //翻译的内容用encoder编译
        String str= URLEncoder.encode("How old are you");
        //接受反回的Json
        String boty="";
        //传参的类
        List<NameValuePair> pair=new ArrayList<NameValuePair>();
        //有道翻译api接口，需要自己申请生成key
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid=wxfd2628cfc7f6defb&secret=CF26762CF05A42899F1681872CE3BC89";
        //实例化defaultHttpClient
        DefaultHttpClient hc=new DefaultHttpClient();
        try {
            //实例化post方式访问并且把路径放入
            HttpGet httppost=new HttpGet(url);
            //执行访问返回resp
            HttpResponse resp=hc.execute(httppost);
            //获取访问的结果
            HttpEntity entity=resp.getEntity();
            //把返回的结果转成字符串
            boty= EntityUtils.toString(entity);

        } catch (Exception e) {
            e.printStackTrace();
        }//运行完后执行
        finally {
            hc.getConnectionManager().shutdown();
        }
        return boty;
    }
}
