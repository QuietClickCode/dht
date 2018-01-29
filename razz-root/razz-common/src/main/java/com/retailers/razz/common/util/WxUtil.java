package com.retailers.razz.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zaqhr on 2018/1/11.
 */
public class WxUtil {

    //获取公众号AccessToken
    public static String getWxAccessToken(String appid,String appsecret){
        JSONObject jsonObject = new JSONObject();
        String url = "https://api.weixin.qq.com/cgi-bin/token";
        jsonObject.put("grant_type","client_credential");
        jsonObject.put("appid","appid");
        jsonObject.put("secret","appsecret");
        String res = HttpUtils.reqPost(url,jsonObject.toJSONString());
        JSONObject returnresObj = JSON.parseObject(res);
        String accessToken = returnresObj.getString("access_token");
        return accessToken;
    }

    //通过微信公众号的AccessToken批量获取客户的openid
    public static List<String> getUserOpenids(String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/user/get?access_token="+accessToken;
        String res = HttpUtils.reqPost(url,"");
        JSONObject jsonObject = JSON.parseObject(res);
        JSONArray jsonArrayOpenid = jsonObject.getJSONObject("data").getJSONArray("openid");
        List<String> openids = new ArrayList<String>();
        for(int i=0;i<jsonArrayOpenid.size();i++){
            String openid = jsonArrayOpenid.getString(i);
            openids.add(openid);
        }
        return openids;
    }

    //通过多个openid获取对应的unionid unionid为key，openid为value
    public static List<Map<String,String>> getUserUnionids(List<String> openids,String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/user/info/batchget?access_token="+accessToken;
        int openidsSize = openids.size();
        double openidsindex = Math.ceil(openidsSize/100.0d);
        int index = (int)openidsindex;
        List<Map<String,String>> unionIds = new ArrayList<Map<String,String>>();
        for(int x=0;x<index;x++){
            List<Map> param = new ArrayList<Map>();
            int last = 0;
            if(x==index-1){
                last = openidsSize;
            }else{
                last = (x+1)*100;
            }
            System.out.println(last);
            for(int i=x*100;i<last;i++){
                Map map = new HashMap();
                map.put("openid",openids.get(i));
                map.put("lang","zh_CN");
                param.add(map);
            }
            Map params = new HashMap();
            params.put("user_list",param);
            String res = HttpUtils.reqPost(url,JSON.toJSONString(params));
            System.out.println(res);
            JSONArray jsonArray = JSON.parseObject(res).getJSONArray("user_info_list");
            for(int i=0;i<jsonArray.size();i++){
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                int subscribe = jsonObject.getInteger("subscribe");
                //判断是否关注公众号
                if(subscribe==1){
                    String unionid = jsonObject.getString("unionid");
                    String openid = jsonObject.getString("openid");
                    Map map = new HashMap();
                    map.put(unionid,openid);
                    unionIds.add(map);
                }
            }
        }
        return unionIds;
    }


    //发送模板消息
    /**
     *
     * @param params 保存发送模板消息所需要的参数 包括AccessToken
     */
    public void sendModalMsg(Map params,String accessToken){
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accessToken;
        String res = HttpUtils.reqPost(url,JSON.toJSONString(params));
        System.out.println(res);
    }

}
