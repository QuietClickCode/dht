package com.retailers.hnc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.WxAuthUser;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.service.WxAuthUserService;
import com.retailers.hnc.common.util.MyHttpUrlConnection;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.hnc.web.constant.WebSystemConstant;
import com.retailers.hnc.web.utils.AES;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.config.WxConfig;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.InvalidAlgorithmParameterException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("wxUser")
public class WxUserController extends BaseController {

    @Autowired
    WxAuthUserService wxAuthUserService;
    @Autowired
    ClientManageService clientManageService;
    @Autowired
    EmployeeManageService employeeManageService;
    Logger logger = LoggerFactory.getLogger(WxUserController.class);


    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> login(@Param("code") String code, WxAuthUser wxAuthUser, String encryptedData, String iv,String enunionidData,String unionidiv){
        System.out.println(code);
        return loginReturnMap(code,wxAuthUser,encryptedData,iv,enunionidData,unionidiv);
    }

    @RequestMapping("/firstGetOpenid")
    @ResponseBody
    public void firstGetOpenid(@Param("code") String code){
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + WxConfig.APP_ID+
                "&secret=" + WxConfig.APP_SECRET+
                "&grant_type=authorization_code" +
                "&js_code="+code;
        GetFromServer(url);
    }
//    @RequestMapping("/saveUserInfo")
//    @ResponseBody
//    public void saveUserInfo(String randStr, WxAuthUser wxAuthUser, String encryptedData, String iv){
//        Long cid = getClientIdByOpenId(randStr);
//        Map params = new HashMap();
//        params.put("wauUid",cid);
//        List list = wxAuthUserService.queryWxAuthUserList(params,1,1).getData();
//        if(ObjectUtils.isNotEmpty(list)){
//            WxAuthUser wx = (WxAuthUser)list.get(0);
//            wxAuthUser.setWauId(wx.getWauId());
//            wxAuthUserService.updateWxAuthUser(wxAuthUser);
//        }
//    }

    @RequestMapping("/checkClientComeIn")
    @ResponseBody
    public Map<String,Object> checkClientComeIn(String validateCode){
        return null;
    }










    public Map<String,Object> loginReturnMap(String code,WxAuthUser wxAuthUser,String encryptedData,String iv,String enunionidData,String unionidiv ) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + WxConfig.APP_ID+
                "&secret=" + WxConfig.APP_SECRET+
                "&grant_type=authorization_code" +
                "&js_code="+code;
        Long curTime = System.currentTimeMillis();
        Long loginouttime = 24*60*60*1000L;//有效时间24个小时
        Date endTime = new Date(curTime+loginouttime);
        Map returnMap = new HashMap();
        try {
            String respStr = "";
            if(ObjectUtils.isNotEmpty(url)){
                System.out.println(url);
                respStr = GetFromServer(url);
                System.out.println(respStr);
                logger.info(respStr);
                JSONObject jsonObject = JSON.parseObject(respStr);
                String openid = jsonObject.getString("openid");
                String unionid = jsonObject.getString("unionid");
                String sessionKey = jsonObject.getString("session_key");
                String phone = "";
                phone = MyHttpUrlConnection.decryptPhoneData(encryptedData,iv,sessionKey);
                if(ObjectUtils.isNotEmpty(openid)){
                    if(ObjectUtils.isEmpty(unionid)){
                        unionid = getUnionId(enunionidData,unionidiv,sessionKey);
                    }
                    Map params = new HashMap();
                    params.put("wauOpenid",openid);
                    params.put("wauUnionid",unionid);

                    //登录用户类型 0客户 1职业顾问 2管理员
                    Integer type = 0;
                    Map map1 = new HashMap();
                    map1.put("isDelete",0L);
                    if(ObjectUtils.isNotEmpty(phone)){
                        map1.put("wxPhone",phone);
                    }else{
                        map1.put("wxPhone","-1");
                    }
                    System.out.println("phone:"+phone);
                    List<EmployeeManage> empList = employeeManageService.queryEmployeeManageList(map1,1,1).getData();
                    if(ObjectUtils.isNotEmpty(empList)){
                        EmployeeManage employeeManage = empList.get(0);
                        type = employeeManage.getEmType();
                    }
                    System.out.println("type1:"+type);
                    List<WxAuthUser> list = wxAuthUserService.queryWxAuthUserList(params,1,1).getData();
                    if(ObjectUtils.isEmpty(list)){
                        //保存客户的信息
                        ClientManage clientManage = new ClientManage();
                        clientManage.setIsDelete(0);
                        clientManage.setTmPhone(phone);
                        clientManage.setTmLoginStatus(type);
                        clientManage.setTmRegisterTime(new Date());
                        clientManage = clientManageService.saveClientManage(clientManage);

                        wxAuthUser.setWauUid(clientManage.getTmId());
                        wxAuthUser.setWauOpenid(openid);
                        wxAuthUser.setWauUnionid(unionid);
                        wxAuthUser.setWauCreateDate(new Date());
                        wxAuthUser.setWauWxId(0L);
                        wxAuthUserService.saveWxAuthUser(wxAuthUser);
                    }else{
                        WxAuthUser wxAuthUser1 = list.get(0);
                        //这里tmid是cmid
                        ClientManage clientManage = clientManageService.queryClientManageByTmId(wxAuthUser1.getWauUid());
                        if(clientManage.getTmLoginStatus()!=type){
                            clientManage.setTmLoginStatus(type);
                            clientManageService.updateClientManage(clientManage);
                        }
                    }
                    System.out.println("type2:"+type);
                    String randStr = DESUtils.encryptDES(openid, DesKey.WEB_KEY);
                    randStr = URLEncoder.encode(randStr,"utf-8");
                    WebSystemConstant.putValue(randStr,endTime);
                    returnMap.put("randStr",randStr);
                    returnMap.put("type",type);
                    returnMap.put("phone",phone);
                    System.out.println(randStr);
                    return returnMap;
                }else{


                    returnMap.put("msg","您的信息有误");
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return returnMap;
    }

    public static void main(String[] a){

        String encode ="xOD3AmG+sSF9XKLiw0wsw46S0CK5QJCcG+eJjdc+N2s48QSmiI9Ut4DDmZ6S8xi3RgKHBJsU9pZPPmtcZJ+HtSA52s9VW074Hmn3V52IRJGliJ9rKcCAAEup7HrCohSserh0V9Q/lxxZsEzI9OM6i65qkoo1JLbcQ7d0isJWY+0yXBUA1yf8r3pSgMv1lheNBssrtj2YTEKEmNIAiBUIm2Taau5Te9aJaE22/2cJaW8QoCeXZ7l4BO0Q5N+f1g/VtFJH9i/ZyCxgGE8pEgdKiY9MOvs1grHex/E71AHGDABbUgjZXwbadB3MJvpLqWIUrZgIvDOAKq5ONG5YYe55fxDBkdk4UyZijuiSjUIPDhNENExfCOdBjKcIUa9AHZaF7f/wEg93Dt0LlHEDwuqhYzrw/pnoP6tk3SMicqu1yRcL9yVa5/Z3hexvPnoE4MtRNhu0UTLD6b41efgyZyJXZwKzMRUeZRhIBGtKhenEIm0eV0c9yUZeJ4D8PJxnLlx+r9vJMZrrkjBeLTErdQOJ6g==";
        String iv = "EEHMWcGvWbuZlQpoCwHb/Q==";
        String session_key = "QJnXlXSxJNxtxCn0P66USg==";
        Map map = new HashMap();
//        try {
//            byte[] resultByte  = AES.decrypt(Base64.decodeBase64(encode),
//                    Base64.decodeBase64(session_key),
//                    Base64.decodeBase64(iv));
//            if(null != resultByte && resultByte.length > 0){
//                String userInfo = new String(resultByte, "UTF-8");
//                map.put("status", "1");
//                map.put("msg", "解密成功");
//                map.put("userInfo", userInfo);
//            }else{
//                map.put("status", "0");
//                map.put("msg", "解密失败");
//            }
//        }catch (InvalidAlgorithmParameterException e) {
//            e.printStackTrace();
//        } catch (UnsupportedEncodingException e) {
//            e.printStackTrace();
//        }
//        String decodeJSON = JSON.toJSONString(map);
//        System.out.println(decodeJSON);
    }

    public String getUnionId(String enData,String iv,String session_key){
        Map map = new HashMap();
        try {
            byte[] resultByte  = AES.decrypt(Base64.decodeBase64(enData),
                    Base64.decodeBase64(session_key),
                    Base64.decodeBase64(iv));
            if(null != resultByte && resultByte.length > 0){
                String userInfo = new String(resultByte, "UTF-8");
                map.put("status", "1");
                map.put("msg", "解密成功");
                map.put("userInfo", userInfo);
            }else{
                map.put("status", "0");
                map.put("msg", "解密失败");
            }
        }catch (InvalidAlgorithmParameterException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String decodeJSON = JSON.toJSONString(map);
        JSONObject jsonObject = JSON.parseObject(decodeJSON);
        JSONObject jsonObject1 = jsonObject.getJSONObject("userInfo");
        String unionid = jsonObject1.getString("unionId");
        return unionid;
    }



    public static String GetFromServer(String url) {
        String retStr="";
        ClientConnectionManager connManager = new PoolingClientConnectionManager();
        DefaultHttpClient client = new DefaultHttpClient(connManager);

        HttpGet get = new HttpGet(url);
        try {
            HttpResponse response = client.execute(get);
            HttpEntity entity = response.getEntity();
            retStr = EntityUtils.toString(entity);
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(url);
            return "";
        }
        return retStr;

    }
}
