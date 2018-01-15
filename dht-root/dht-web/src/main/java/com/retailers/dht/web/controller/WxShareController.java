package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.auth.annotation.CheckSession;
import com.retailers.dht.common.constant.SystemConstant;
import com.retailers.dht.common.entity.PayCallback;
import com.retailers.dht.common.entity.PayInfo;
import com.retailers.dht.common.service.PayCallbackService;
import com.retailers.dht.common.service.PayInfoService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.encrypt.Sha1DESUtils;
import com.retailers.tools.utils.*;
import com.retailers.wx.common.config.WxConfig;
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
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.PublicKey;
import java.util.*;

@Controller
@RequestMapping("wxShare")
public class WxShareController extends BaseController{

    @RequestMapping("createWxShare")
    @ResponseBody
    public  Map<String,Object> createWxPay(HttpServletRequest request,String path) {
        path = path.substring(1);
        String noncestr = createRandomString();
        String jsapi_ticket = WxConfig.ACCESS_TICKET;
        Long timestamp = new Date().getTime()/1000;
        String signature = "";
        String homePath = SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_MOBILE_URL);  //"http://fhdy.s1.natapp.cc";//SysParameterConfigConstant.MASTER_SERVER_MOBILE_URL   "http://www.kuaiyis.com"
        homePath = homePath+path;
        try {
            signature = Sha1DESUtils.SHA1(noncestr,jsapi_ticket, timestamp, homePath);
        }catch (Exception e){
            e.printStackTrace();
        }

        Map map = new HashMap();
        map.put("homePath",homePath);

        map.put("nonceStr",noncestr);
        map.put("timestamp",timestamp);
        map.put("appid","wxfd2628cfc7f6defb");
        map.put("signature",signature);
        return map;
    }

    public String createRandomString(){
        return WXPayUtil.getStringRandom(30);
    }

    public String getTick(String access_token){
        Map map = new HashMap();
        map.put("access_token",access_token);
        map.put("type","jsapi");
        String respStr = HttpClientUtil.doGet("https://api.weixin.qq.com/cgi-bin/ticket/getticket",map);
        JSONObject jsonObject = JSON.parseObject(respStr);

        return jsonObject.getString("ticket");
    }

    public String getAccessToken(){
        Map map = new HashMap();
        map.put("grant_type","client_credential");
        map.put("appid","wxfd2628cfc7f6defb");
        map.put("secret","89150c76c3925859cf95375fc901c047");
        String respStr = HttpClientUtil.doGet("https://api.weixin.qq.com/cgi-bin/token",map);
        JSONObject jsonObject = JSON.parseObject(respStr);
        return jsonObject.getString("access_token");
    }
    @RequestMapping("shareImage")
    public void shareImage(HttpServletResponse response){
        setResponseHeaders(response);
        String goodsImgUrl="http://dht.kuaiyis.com/attachment/goods/2018/01/02/0a017bf6583676949a70662f164bd25d_originalfile.jpg";
        try{
            OutputStream outputStream=response.getOutputStream();
            ShareImageUtils.generateShareImage("测试商品","￥12.36","www.baidu.com",goodsImgUrl,outputStream);
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
    }
}
