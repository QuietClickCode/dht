package com.retailers.wx.common.utils;

import com.retailers.tools.exception.AppException;
import com.retailers.tools.http.HttpClientManager;
import com.retailers.tools.utils.ObjectUtils;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/8
 */
public class WxHttpClientUtils {

    /**
     * 微信支付请求
     * @param url 请求url
     * @param xml 传入参数
     * @return
     */
    public static String reqPost(String url, String xml) {
        CloseableHttpClient httpclient = HttpClientManager.getHttpClient();
        CloseableHttpResponse response=null;
        HttpPost httpPost = new HttpPost(url);
        try {
            httpPost.addHeader("Content-type", "text/xml; charset=utf-8");
            httpPost.setEntity(new StringEntity(xml, Charset.forName("UTF-8")));
            response = httpclient.execute(httpPost);
            //执行请求
            String responseEntity= EntityUtils.toString(response.getEntity(),"utf-8");
            EntityUtils.consume(response.getEntity());
            return responseEntity;
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if(ObjectUtils.isNotEmpty(httpPost)){
                httpPost.releaseConnection();
            }
        }
        return null;
    }
}
