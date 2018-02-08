package com.retailers.hnc.web.job;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.common.dao.WxAuthUserMapper;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.entity.OpeningEmpClient;
import com.retailers.hnc.common.entity.WxAuthUser;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.util.HttpUtils;
import com.retailers.hnc.common.util.WxUtil;
import com.retailers.hnc.web.constant.HNCGZHConstant;
import com.retailers.hnc.web.constant.WebSystemConstant;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.config.WxConfig;
import com.retailers.wx.common.entity.WxAccessToken;
import com.retailers.wx.common.service.WxAccessTokenService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * 后台管理定时任务
 * @author zhongp
 * @version 1.0.1
 * @data 2017-10-07
 */
@Service("mTaskJob")
public class ManageTaskJob {
    Logger logger = LoggerFactory.getLogger(ManageTaskJob.class);

    @Autowired
    private WxAuthUserMapper wxAuthUserMapper;
    @Autowired
    private WxAccessTokenService wxAccessTokenService;
    @Autowired
    private EmployeeManageService employeeManageService;
    @Autowired
    private ClientManageService clientManageService;
    @Autowired
    private OpeningEmpClientService openingEmpClientService;
    /**
     * 清除退出用户
     */
    public void clearLoginOutUser(){
        logger.info("开始清除退出用户");
        Map<String,Object> map = WebSystemConstant.globelOpenId;
        List<String> strArr = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Date date = (Date) entry.getValue();
            if(date.getTime()<System.currentTimeMillis()){
                strArr.add(key);
            }
        }
        if(ObjectUtils.isNotEmpty(strArr)){
            for(String key:strArr){
                map.remove(key);
            }
        }
        logger.info("清除退出用户完毕");
    }

    //获取小程序的AccessToken
    public void getAccessToken(){
        logger.info("开始获取小程序Access_Token");
        String appid = WxConfig.APP_ID;//"wx53881ce6778c5e1f";
        String appsecret = WxConfig.APP_SECRET;//"356ea90409ec9e34064e1c860f2bf667";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        String str = HttpClientUtil.doGet(url);
        JSONObject jsonObject = JSON.parseObject(str);
        String accessToken = jsonObject.getString("access_token");
        logger.info("小程序："+str);
        WxConfig.ACCESS_TOKEN = accessToken;
        logger.info("获取小程序Access_Token完毕");
    }

    //获取发送消息的微信公众号的AccessToken
    public void getGZHAccessToken(){
        logger.info("开始获取公众号Access_Token");
        String appid = HNCGZHConstant.APP_ID;//"wx53881ce6778c5e1f";
        String appsecret = HNCGZHConstant.APP_SECRET;//"356ea90409ec9e34064e1c860f2bf667";
        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
        String str = HttpClientUtil.doGet(url);
        JSONObject jsonObject = JSON.parseObject(str);
        String accessToken = jsonObject.getString("access_token");
        if(ObjectUtils.isNotEmpty(accessToken)){
            WxAccessToken wxAccessToken = new WxAccessToken();
            Date now = new Date();
            int expires = 2*60*60*1000;
            wxAccessToken.setWatTokenExpires(expires);
            wxAccessToken.setWatToken(accessToken);
            wxAccessToken.setWatWxAppId(HNCGZHConstant.APP_ID);
            wxAccessToken.setWatTokenCreateTime(now);
            wxAccessToken.setWatTokenExpiresTime(new Date(now.getTime()+expires));
            wxAccessTokenService.saveWxAccessToken(wxAccessToken);
        }
        HNCGZHConstant.ACCESS_TOKEN = accessToken;
        System.out.println("公众号："+str);
        logger.info("获取公众号Access_Token完毕");
    }

    //获取微信公众号上的用户信息 openid和unionid
    public void getGZHUserMsg(){
        logger.info("开始获取公众号用户信息");

//        String appid = HNCGZHConstant.APP_ID;//"wx53881ce6778c5e1f";
//        String appsecret = HNCGZHConstant.APP_SECRET;//"356ea90409ec9e34064e1c860f2bf667";
//        String url = "https://api.weixin.qq.com/cgi-bin/token?grant_type=client_credential&appid="+appid+"&secret="+appsecret;
//        String str = HttpClientUtil.doGet(url);
//        JSONObject jsonObject = JSON.parseObject(str);
//        String accessToken = jsonObject.getString("access_token");
//        HNCGZHConstant.ACCESS_TOKEN = accessToken;


        String accessToken = HNCGZHConstant.ACCESS_TOKEN;
        if(ObjectUtils.isNotEmpty(accessToken)){
            List<String> openids = WxUtil.getUserOpenids(accessToken);
            List<Map<String,String>> unionids = WxUtil.getUserUnionids(openids,accessToken);

            List<WxAuthUser> wxAuthUserList = new ArrayList<WxAuthUser>();
            for(Map<String,String> map:unionids){
                WxAuthUser wxAuthUser = new WxAuthUser();
                for (Map.Entry<String, String> entry : map.entrySet()) {
                    wxAuthUser.setWauUnionid(entry.getKey());
                    wxAuthUser.setWauOpenid(entry.getValue());
                }
                wxAuthUser.setWauWxId(1L);
                wxAuthUserList.add(wxAuthUser);
            }
            wxAuthUserMapper.saveGZHUser(wxAuthUserList);

        }
        logger.info("获取公众号用户信息完毕");
    }



    public void testJob(){
        logger.info("开始测试");
        logger.info("测试中");
        logger.info("结束测试");
    }

    //提醒置业顾问有哪些客户没有完善信息
    public void msgEmpAboutClient(){
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("emType",1L);
        List<EmployeeManage> employeeManages = employeeManageService.queryFirstEmployeeManageList(params,1,999).getData();
        if(ObjectUtils.isNotEmpty(employeeManages)){
            for(EmployeeManage employeeManage:employeeManages){
                Map params1 = new HashMap();
                params1.put("isDelete",0L);
                params1.put("tmEmployee",employeeManage.getEmId());
                List<ClientManage> clientManages = clientManageService.queryClientManageList(params1,1,99999).getData();
                if(ObjectUtils.isEmpty(clientManages)){
                    continue;
                }
                int index = 0;
                if(ObjectUtils.isNotEmpty(clientManages)){
                    for(ClientManage clientManage:clientManages){
                        String name = clientManage.getTmName();
                        if(ObjectUtils.isEmpty(name)){
                            index++;
                        }
                    }
                }
                if(index==0){
                    continue;
                }
                ClientManage clientManage1 = openingEmpClientService.queryClientManageByEmpId(employeeManage.getEmId());
                String openid = openingEmpClientService.queryGZHopenidByClientId(clientManage1.getTmId());
                List<String> kenote = new ArrayList<String>();
                kenote.add("客户信息审核");
                kenote.add("您还有"+index+"位客户还没有完善信息");
                //发送模板消息
                Map modalparams = new HashMap();
                modalparams.put("first","尊敬的置业顾问，您好");
                modalparams.put("remark","请在小程序中查看客户信息");
                modalparams.put("openid",openid);
                modalparams.put("keynote",kenote);
                modalparams.put("modalId","yhYNymG4jIeQ8FpzCM8iTFbaYFfUyXvb1cZU-aLu_aQ");
                modalparams.put("accessToken",HNCGZHConstant.ACCESS_TOKEN);
                WxUtil.sendModalMsg(modalparams);
            }
        }
    }

}
