package com.retailers.wx.common.utils;

import com.retailers.tools.exception.AppException;
import com.retailers.tools.http.HttpClientManager;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.wx.common.config.WxConfig;
import org.apache.http.HttpEntity;
import org.apache.http.HttpException;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.HTTP;
import org.apache.http.ssl.SSLContexts;
import org.apache.http.util.Args;
import org.apache.http.util.CharArrayBuffer;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.*;
import java.nio.charset.Charset;
import java.security.KeyStore;

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

    public static String doSendMoney(String url, String data) throws Exception {
        System.out.println("退款订单生成的格式："+data);
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        String path = "";//request.getSession().getServletContext().getRealPath("/apiclient_cert.p12");//获取证书文件
        System.out.println("证书路径："+path);
        InputStream instream=WxHttpClientUtils.class.getResourceAsStream("/apiclient_cert.p12");
        try {
            keyStore.load(instream, WxConfig.WX_MCH_ID.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, WxConfig.WX_MCH_ID.toCharArray())// 这里也是写密码的
                .build();
        // Allow TLSv1 protocol only
        SSLConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(
                sslcontext, new String[] { "TLSv1" }, null,
                SSLConnectionSocketFactory.BROWSER_COMPATIBLE_HOSTNAME_VERIFIER);
        CloseableHttpClient httpclient = HttpClients.custom()
                .setSSLSocketFactory(sslsf).build();
        try {
            HttpPost httpost = new HttpPost(url); // 设置响应头信息
            httpost.addHeader("Connection", "keep-alive");
            httpost.addHeader("Accept", "*/*");
            httpost.addHeader("Content-Type",
                    "application/x-www-form-urlencoded; charset=UTF-8");
            httpost.addHeader("Host", "api.mch.weixin.qq.com");
            httpost.addHeader("X-Requested-With", "XMLHttpRequest");
            httpost.addHeader("Cache-Control", "max-age=0");
            httpost.addHeader("User-Agent",
                    "Mozilla/4.0 (compatible; MSIE 8.0; Windows NT 6.0) ");
            httpost.setEntity(new StringEntity(data, "UTF-8"));
            CloseableHttpResponse response = httpclient.execute(httpost);
            try {
                HttpEntity entity = response.getEntity();
                String jsonStr = toStringInfo(response.getEntity(), "UTF-8");
                EntityUtils.consume(entity);
                return jsonStr;
            } finally {
                response.close();
            }
        } finally {
            httpclient.close();
        }
    }
    private static String toStringInfo(HttpEntity entity, String defaultCharset)
            throws Exception, IOException {
        final InputStream instream = entity.getContent();
        if (instream == null) {
            return null;
        }
        try {
            Args.check(entity.getContentLength() <= Integer.MAX_VALUE,
                    "HTTP entity too large to be buffered in memory");
            int i = (int) entity.getContentLength();
            if (i < 0) {
                i = 4096;
            }
            Charset charset = null;
            if (charset == null) {
                charset = Charset.forName(defaultCharset);
            }
            if (charset == null) {
                charset = HTTP.DEF_CONTENT_CHARSET;
            }
            final Reader reader = new InputStreamReader(instream, charset);
            final CharArrayBuffer buffer = new CharArrayBuffer(i);
            final char[] tmp = new char[1024];
            int l;
            while ((l = reader.read(tmp)) != -1) {
                buffer.append(tmp, 0, l);
            }
            return buffer.toString();
        } finally {
            instream.close();
        }
    }
}
