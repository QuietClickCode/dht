package com.retailers.wx.common.utils;

import com.retailers.mybatis.common.constant.AttachmentConstant;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.common.entity.SysParameterConfig;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.http.HttpClientManager;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
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
import java.net.URL;
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
        System.out.println("退款订单生成的格式："+data+",微信mathId"+WxConfig.WX_MCH_ID);
        KeyStore keyStore  = KeyStore.getInstance("PKCS12");
        String path = SysParameterConfigConstant.getValue(SysParameterConfigConstant.WX_PAY_CERT_LOCAL_ADDRESS)+WxConfig.WX_CRET_FILE;//request.getSession().getServletContext().getRealPath("/apiclient_cert.p12");//获取证书文件
        System.out.println("证书路径："+path);
        File file=new File(path);
        if(!file.exists()){
            throw new Exception("未上传微信支会密钥");
        }
        FileInputStream instream = new FileInputStream(new File(path));
        try {
            keyStore.load(instream, WxConfig.WX_MCH_ID.toCharArray());
        } finally {
            instream.close();
        }
        SSLContext sslcontext = SSLContexts.custom()
                .loadKeyMaterial(keyStore, WxConfig.WX_MCH_ID.toCharArray())// 这里也是写密码的
                .build();
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

    /**
     * 微信支付文件下载
     * @param savePath 保存文件路径
     * @param fileNm 保存文件名
     * @param remoteFile 远程服务器地址
     */
    public static  void downWxCretFile(String savePath,String fileNm,String remoteFile){
        String path = StringUtils.concat(savePath,fileNm);
        File file = new File(path);
        if(file.exists()){
            return;
        }
        if(ObjectUtils.isNotEmpty(remoteFile)){
            download(StringUtils.concat(AttachmentConstant.IMAGE_SHOW_URL,remoteFile), savePath,fileNm);
        }
        System.out.println("证书路径："+path);
    }

    /**
     * 下载文件
     * @param savePath
     * @param fileNm
     */
    private static void downWxCretFile(String savePath,String fileNm){
        downWxCretFile(savePath,fileNm,WxConfig.WX_REMOTE_FILE_URL);
    }


    /**
     * 使用传统io stream 下载文件
     * @param url
     * @param saveDir
     * @param fileName
     */
    private static void download(String url, String saveDir, String fileName) {
        System.out.println(url);
        BufferedOutputStream bos = null;
        InputStream is = null;
        try {
            byte[] buff = new byte[8192];
            is = new URL(url).openStream();
            File file = new File(saveDir, fileName);
            file.getParentFile().mkdirs();
            bos = new BufferedOutputStream(new FileOutputStream(file));
            int count = 0;
            while ( (count = is.read(buff)) != -1) {
                bos.write(buff, 0, count);
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        finally {
            if (is != null) {
                try {
                    is.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

        }

    }
}
