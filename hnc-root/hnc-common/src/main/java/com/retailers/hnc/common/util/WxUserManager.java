package com.retailers.hnc.common.util;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.*;

/**
 * 微信用户管理
 * @author zhongp
 * @version 1.0.1
 *@date 2017-09-07
 */
public class WxUserManager {
    /**
     * 取得用户信息
     * @param openId
     */
    public static void queryUserInfo(String openId)throws Exception{
        TreeMap<String,Object> params=new TreeMap<String,Object>();
        params.put("openid",openId);
        params.put("lang","zh_CN");
        String s =HttpUtils.reqGet(WxConfig.REQU_USER_DETAIL_INFO_URL,params);
        System.out.println(s);
    }
    /**
     * 批量获取用户基本信息
     * @param openIds
     */
    public static void queryUserInfo(List<String> openIds)throws Exception{
        TreeMap<String,Object> params=new TreeMap<String,Object>();
        List<Map<String,String>> users=new ArrayList<Map<String, String>>();
        for(String openId:openIds){
            Map<String,String> map=new HashMap<String,String>();
            map.put("openid",openId);
            map.put("lang","zh_CN");
            users.add(map);
        }
        params.put("user_list",users);
        String s =HttpUtils.reqPostByJson(WxConfig.REQU_BATCH_USER_DETAIL_INFO_URL,params);
        System.out.println(s);
    }
    public static void queryUserLists()throws Exception{
        TreeMap<String,Object> params=new TreeMap<String,Object>();
        String s =HttpUtils.reqGet(WxConfig.REQ_USER_LISTS,params);
        //{"total":60,"count":60,"data":{"openid":["oEIKZxOHy-ovkImvP8X78sMCYlGA","oEIKZxC7CgbAsmy19P5wtS2fkct8","oEIKZxGZ5jpVsoWl_QVUBvrzhM1s","oEIKZxJvMD3d_Brbiw8UT1KB78eQ","oEIKZxFXGiUR8pE4ykBNRyARcivM","oEIKZxLgm9b4_IVZh3y39eSGtZbo","oEIKZxOm0me9UPcQwKgSNdmmzw_A","oEIKZxHxuk2OoNaDSaO8gjJRhZwo","oEIKZxFi4dOLqtNZl5rlrpPzhiVA","oEIKZxK39ooy23oMcXGndAzXALvk","oEIKZxPhawHnPCDu0JWh3k4Qt9-g","oEIKZxGe61zUmVT1kaLnEkvV6YEA","oEIKZxAAgiq8h42b5WFFZ0CtH8Ww","oEIKZxLX3HQjnIK85EMdI963Nswg","oEIKZxAVYVX-tOCTKObirD0KXO30","oEIKZxDA8gjDLcAemVHS3UwxDBVk","oEIKZxB4ZaK20Uq9xnvSL4wd6QDM","oEIKZxEAmUhIfBZ8lMy2uyx3aiH4","oEIKZxIO2IBWvyX3ia-xcNkELhmE","oEIKZxPTYWB-lqyLt-M0IlJQvcxE","oEIKZxFQqJglOruNjsh5PmBtyYEk","oEIKZxLpZ3wAAIqx66-pqGLJKLWI","oEIKZxN_nyxQ2FADOBK0g2go0Pzk","oEIKZxKhcvRq6IyUC4m_JEmEWMGo","oEIKZxG_RSpmHKvUPAbp8pX4W9_8","oEIKZxJcE5fUea-VACoX0YXtRzzE","oEIKZxOJE17iFRbaKfhkQGhGbdSk","oEIKZxMnVL_BaG1ZEwCqy9OWsT3M","oEIKZxOHhtMTy2mjNZ211tKmGsTA","oEIKZxAVa63Tct_BlRyjCrUzkKgo","oEIKZxCdcRMPVoli20LH0h9b0QCI","oEIKZxC3Pdt-QNP0Oo_ndbgPYve8","oEIKZxOyPhzZrZbJ9B0HvD1cXVNA","oEIKZxLP9IIv0y0KteNIACwd5uiQ","oEIKZxAaFkJRlp9H6XQx1ItHMC0M","oEIKZxCl2mjG_h_8BTyEbCwPF5CQ","oEIKZxA1EIGUNm37pgFXyznFcuPw","oEIKZxDIzcu-guwt1FlIgBB4Y6OQ","oEIKZxNYuTu0CwCQ9pkvzwT-1E7c","oEIKZxOsMJ3Df9dXYUlWEFeIk9_c","oEIKZxNJLWLreVFVPMg1Em7nmLxE","oEIKZxGgLfOwx7kThhapq_T26LaI","oEIKZxBFzFQ4tvTINlnuPUgBl_EY","oEIKZxHrJe056OZFeAV_322Fpt68","oEIKZxH_mZVm6qo2QP_G83v9dYRc","oEIKZxBZ4w-xsuzRjFVUA9zL7ahc","oEIKZxKH7MQDnbZp9kUH-10av0sc","oEIKZxFGfa5qEcKW3PErxzJ4fCE8","oEIKZxK7rMe_0GCvitDVuibWjw6Y","oEIKZxLcI7lW_Mqe5-J9nsNzjueA","oEIKZxDQzITbDdk5wfuwVHeY2Wic","oEIKZxJDv7-f4CY5Qo7iFARALGd4","oEIKZxBh2TXLK4VgLm6-OQ8AEehM","oEIKZxAAR1alG7Bt3OHr-8YSr7oI","oEIKZxK5rMbONcrXUKoklKgaVGXU","oEIKZxN0BeLKoxml16VHhthxUoX4","oEIKZxA05-RGwpUbbCzwrRA744y0","oEIKZxJUd-uJe3tgHF32DyAOn9-s","oEIKZxEil7fV8bL4ZZr_C-L2GRC4","oEIKZxKA0IQ82VFkMNzNLRpWRYKM"]},"next_openid":"oEIKZxKA0IQ82VFkMNzNLRpWRYKM"}
        JSONObject json=JSONObject.parseObject(s);
        if(json.containsKey("data")){
            if(json.getJSONObject("data").containsKey("openid")){
                JSONArray array=json.getJSONObject("data").getJSONArray("openid");
                List<String> opids=new ArrayList<String>();
                for(int i=0;i<array.size();i++){
                    System.out.println(array.get(i).toString());
                    opids.add(array.get(i).toString());
                }
                queryUserInfo(opids);
            }
        }

    }
}
