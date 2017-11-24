package com.retailers.wx.common.utils;

import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.wx.common.config.WxConfig;
import sun.reflect.ReflectionFactory;

/**
 * 微信请注公用类
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/31
 */
public class WxReqUtils {

    /**
     * 根据appid 和开发者密码取得微信的token
     * @return
     */
    public static String getToken(String appId,String appSecret){
        String url=String.format(WxConfig.REQ_TOKEN_URL,appId,appSecret);
        String s  = HttpClientUtil.doGet(url);
//        if(ObjectUtils.isNotEmpty(s)){
//            try{
//                JSONObject obj= JSONObject.parseObject(s);
//                if(obj.containsKey("access_token")){
//                    WxConfig.ACCESS_TOKEN=obj.getString("access_token");
//                    System.out.println(WxConfig.ACCESS_TOKEN);
//                }
//            }catch(Exception e){
//            }
//        }
        return s;
    }

    public static String getTicket(String accessToken){
        String url=String.format(WxConfig.REQ_TICKET_TOKEN_URL,accessToken);
        System.out.println(url);
        String s  = HttpClientUtil.doGet(url);
        return s;
    }

    public static void main(String[] args) {
        String s1=getToken("wxfd2628cfc7f6defb","89150c76c3925859cf95375fc901c047");
        System.out.println(s1);
//        String str=getTicket("xBii272xGz-Kbgk9vtyT-ChI51WgMdIvFZUkomC557sEcQ3YNQHyziPqZdQ8F_ZOcTHw7hwdMOO3gU8mu5ObxzXRIjMrMpmw996CXyr4RVuI4pnHDivqdd_9rnxPBLcvGFWaAHAJIZ");
//        System.out.println(str);
    }
}
