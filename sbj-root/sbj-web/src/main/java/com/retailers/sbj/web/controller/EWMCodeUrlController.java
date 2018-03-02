package com.retailers.sbj.web.controller;

import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.retailers.sbj.web.base.BaseController;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.wx.common.config.WxConfig;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

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
        jsonObject.put("page",path);
        jsonObject.put("scene",scene);
        jsonObject.put("width",width);
        String jsonStr = jsonObject.toJSONString();
        setResponseHeaders(response);
        HttpClientUtil.doPostRQCode(url,jsonStr,response);
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

    @RequestMapping("getQRCode")
    @ResponseBody
    public void getOneCode(String scene,HttpServletResponse resp) throws  Exception{
        String url = scene;
        if (url != null && !"".equals(url)) {
            ServletOutputStream stream = null;
            try {

                int width = 200;//图片的宽度
                int height = 200;//高度
                stream = resp.getOutputStream();
                QRCodeWriter writer = new QRCodeWriter();
                BitMatrix m = writer.encode(url, BarcodeFormat.QR_CODE, width, height);
                MatrixToImageWriter.writeToStream(m, "png", stream);
            } catch (WriterException e) {
                e.printStackTrace();
            } finally {
                if (stream != null) {
                    stream.flush();
                    stream.close();
                }
            }
        }
    }

}

