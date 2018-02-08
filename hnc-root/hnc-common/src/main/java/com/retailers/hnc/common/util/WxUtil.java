package com.retailers.hnc.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.retailers.tools.utils.ObjectUtils;

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
     * @param params 传递发送模板消息所需的参数
     *  first 消息头
     *  remark 备注
     *  keynote 发送的信息
     *  accesstoken 公众号的accesstoken
     *  link 可以跳转的地址
     *  modalId 模板消息的id
     *  openid 需要发送用户的openid
     *  appid小程序appid
     *  path小程序路径页面
     */
    public static void sendModalMsg(Map params){
        String first = (String) params.get("first");
        String remark = (String)params.get("remark");
        List<String> keynote = (List<String>)params.get("keynote");
        String accesstoken = (String)params.get("accessToken");
        String link = (String)params.get("link");
        String appid = (String)params.get("appid");
        String path =(String)params.get("path");
        String modalId =(String) params.get("modalId");
        String openid =(String) params.get("openid");

        JSONObject dataJsonObject = new JSONObject();
        System.out.println("accessToken:"+accesstoken);
        JSONObject valJsonObjFirst = new JSONObject();
        valJsonObjFirst.put("value",first);
        dataJsonObject.put("first",valJsonObjFirst);

        for(int i=1;i<=keynote.size();i++){
            JSONObject valJsonObjkeyword = new JSONObject();
            valJsonObjkeyword.put("value",keynote.get(i-1));
            dataJsonObject.put("keyword"+i,valJsonObjkeyword);
        }

        JSONObject valJsonObjremark = new JSONObject();
        valJsonObjremark.put("value",remark);
        dataJsonObject.put("remark",valJsonObjremark);



        JSONObject allJsonData = new JSONObject();
        allJsonData.put("data",dataJsonObject);
        allJsonData.put("touser",openid);
        allJsonData.put("template_id",modalId);

        if(ObjectUtils.isNotEmpty(appid)){
            JSONObject minJsonObj = new JSONObject();
            minJsonObj.put("appid",appid);
            minJsonObj.put("pagepath",path);
            allJsonData.put("miniprogram",minJsonObj);
        }
        if(ObjectUtils.isNotEmpty(link)){
            allJsonData.put("url",link);
        }

        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+accesstoken;
        String res = HttpUtils.reqPost(url, JSON.toJSONString(allJsonData));
        System.out.println(res);
    }

}
