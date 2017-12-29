package com.retailers.hnc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.WxAuthUser;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.service.WxAuthUserService;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.hnc.web.constant.WebSystemConstant;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.encrypt.EncryptUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.config.WxConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.PoolingClientConnectionManager;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.constraints.NotEmpty;
import java.net.URLDecoder;
import java.net.URLEncoder;
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

    @RequestMapping("/login")
    @ResponseBody
    public Map<String,Object> queryProjectByGt(String code, WxAuthUser wxAuthUser,String phone){
        return loginReturnMap(code,wxAuthUser,phone);
    }















    public Map<String,Object> loginReturnMap(String code,WxAuthUser wxAuthUser,String phone) {
        String url = "https://api.weixin.qq.com/sns/jscode2session?" +
                "appid=" + WxConfig.APP_ID+
                "&secret=" + WxConfig.APP_SECRET+
                "&grant_type=authorization_code" +
                "&js_code="+code;
        Long curTime = System.currentTimeMillis();
        Long loginouttime = 2*60*60*1000L;//有效时间两个小时
        Date endTime = new Date(curTime+loginouttime);
        Map returnMap = new HashMap();
        try {
            String respStr = "";
            if(ObjectUtils.isNotEmpty(url)){
                System.out.println(url);
                respStr = GetFromServer(url);
                System.out.println(respStr);

                JSONObject jsonObject = JSON.parseObject(respStr);
                String openid = jsonObject.getString("openid");
                String unionid = jsonObject.getString("unionid");
                if(ObjectUtils.isNotEmpty(openid)){
                    Map params = new HashMap();
                    params.put("wauOpenid",openid);
                    params.put("wauUnionid",unionid);

                    //登录用户类型 0客户 1职业顾问 2管理员
                    Integer type = 0;
                    Map map1 = new HashMap();
                    map1.put("isDelete",0L);
                    map1.put("wxPhone",phone);
                    List<EmployeeManage> empList = employeeManageService.queryEmployeeManageList(map1,1,1).getData();
                    if(ObjectUtils.isNotEmpty(empList)){
                        EmployeeManage employeeManage = empList.get(0);
                        type = employeeManage.getEmType();
                    }

                    List<WxAuthUser> list = wxAuthUserService.queryWxAuthUserList(params,1,1).getData();
                    if(ObjectUtils.isEmpty(list)){
                        //保存客户的信息
                        ClientManage clientManage = new ClientManage();
                        clientManage.setIsDelete(0);
                        clientManage.setTmPhone(phone);
                        clientManage.setTmLoginStatus(type);
                        clientManage = clientManageService.saveClientManage(clientManage);

                        wxAuthUser.setWauUid(clientManage.getTmId());
                        wxAuthUser.setWauOpenid(openid);
                        wxAuthUser.setWauUnionid(unionid);
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

                    String randStr = DESUtils.encryptDES(openid, DesKey.WEB_KEY);
                    randStr = URLEncoder.encode(randStr,"utf-8");
                    Map map = WebSystemConstant.globelOpenId;
                    map.put(randStr,endTime);
                    returnMap.put("randStr",randStr);
                    returnMap.put("type",randStr);
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
        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=wx53881ce6778c5e1f&secret=356ea90409ec9e34064e1c860f2bf667&grant_type=authorization_code&js_code=013GT1dm0KUDNl1ktH9m0f40dm0GT1dW";
        String str = GetFromServer(url);
    System.out.println(str);
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
