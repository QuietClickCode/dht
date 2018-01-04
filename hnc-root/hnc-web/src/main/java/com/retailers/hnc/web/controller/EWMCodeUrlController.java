package com.retailers.hnc.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.config.WxConfig;
import org.apache.commons.codec.binary.Base64;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("ewmCodeUrl")
public class EWMCodeUrlController extends BaseController {

    @RequestMapping("createEwmCodeUrl")
    @ResponseBody
    public void createEwmCodeUrl(String path, String scene, String width , HttpServletResponse response){
        String url = "https://api.weixin.qq.com/wxa/getwxacodeunlimit?access_token="+ WxConfig.ACCESS_TOKEN;
        System.out.println(url);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("path",path);
        jsonObject.put("scene",scene);
        jsonObject.put("width",width);
        String jsonStr = jsonObject.toJSONString();
        Map map = new HashMap();
        String resp = HttpClientUtil.doPostBodyJson(url,jsonStr);
        System.out.println(resp);
        try {
            System.out.println();
            byte[] bytes = resp.getBytes("UTF-8");
            System.out.println(bytes.length);
            response.setContentType("text/html;charset=UTF-8");
            ServletOutputStream out = response.getOutputStream();
            out.write(bytes);
        }catch (Exception e){
            e.printStackTrace();
        }
    }




}

