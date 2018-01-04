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

import javax.imageio.ImageIO;
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
        setResponseHeaders(response);
        HttpClientUtil.doPostRQCode(url,jsonStr,response);


//        try {
//            System.out.println();
//            byte[] bytes = resp.getBytes("UTF-8");
//            System.out.println(bytes.length);
//            response.setContentType("text/html;charset=UTF-8");
//            ServletOutputStream out = response.getOutputStream();
//            out.write(bytes);
//        }catch (Exception e){
//            e.printStackTrace();
//        }
    }

    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
        long time = System.currentTimeMillis();
        response.setDateHeader("Last-Modified", time);
        response.setDateHeader("Date", time);
        response.setDateHeader("Expires", time);
    }
    public static final byte[] input2byte(InputStream inStream)
            throws IOException {
        ByteArrayOutputStream swapStream = new ByteArrayOutputStream();
        byte[] buff = new byte[100];
        int rc = 0;
        while ((rc = inStream.read(buff, 0, 100)) > 0) {
            swapStream.write(buff, 0, rc);
        }
        byte[] in2b = swapStream.toByteArray();
        return in2b;
    }

}

