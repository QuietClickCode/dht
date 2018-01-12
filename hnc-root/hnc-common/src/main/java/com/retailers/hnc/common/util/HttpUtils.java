package com.retailers.hnc.common.util;

import com.alibaba.fastjson.JSONObject;
import com.retailers.tools.utils.ObjectUtils;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Map;
import java.util.TreeMap;

public class HttpUtils {

    /**
     * 发送 POST 请求（HTTP），JSON形式
     * @param apiUrl
     * @param json json对象
     * @return
     */
    public static String reqPost(String apiUrl, String json) {
        System.out.println(json);
        CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(apiUrl);
        CloseableHttpResponse response = null;
        try {
            StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题
            stringEntity.setContentEncoding("UTF-8");
            stringEntity.setContentType("application/json");
            httpPost.setEntity(stringEntity);
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    /**
     * 发送 POST 请求（HTTP），JSON形式
     * @param reqUrl 请求url
     * @param params
     * @return
     */
    public static String reqPostByJson(String reqUrl, TreeMap<String,Object> params) throws Exception{
        reqUrl=checkUrl(reqUrl);
        CloseableHttpClient httpClient = HttpClientManager.getHttpClient();
        String httpStr = null;
        HttpPost httpPost = new HttpPost(reqUrl);
        CloseableHttpResponse response = null;
        try {
            if(ObjectUtils.isNotEmpty(params)&&!params.isEmpty()){
                JSONObject json = new JSONObject();
                for(String key:params.keySet()){
                    json.put(key,params.get(key));
                }
                System.out.println("取得的传入json 参数");
                StringEntity stringEntity = new StringEntity(json.toString(),"UTF-8");//解决中文乱码问题
                stringEntity.setContentEncoding("UTF-8");
                stringEntity.setContentType("application/json");
                httpPost.setEntity(stringEntity);
            }
            response = httpClient.execute(httpPost);
            HttpEntity entity = response.getEntity();
            System.out.println(response.getStatusLine().getStatusCode());
            httpStr = EntityUtils.toString(entity, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (response != null) {
                try {
                    EntityUtils.consume(response.getEntity());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return httpStr;
    }

    public static  String connectHttpsByPost(String reqUrl, TreeMap<String,Object>params, File file) throws Exception{
        reqUrl=checkUrl(reqUrl);
        for(String key:params.keySet()){
            reqUrl+="&"+key+"="+params.get(key);
        }
        URL urlObj = new URL(reqUrl);
        //连接
        HttpURLConnection con = (HttpURLConnection) urlObj.openConnection();
        String result = null;
        con.setDoInput(true);

        con.setDoOutput(true);

        con.setUseCaches(false); // post方式不能使用缓存

        // 设置请求头信息
        con.setRequestProperty("Connection", "Keep-Alive");
        con.setRequestProperty("Charset", "UTF-8");
        // 设置边界
        String BOUNDARY = "----------" + System.currentTimeMillis();
        con.setRequestProperty("Content-Type",
                "multipart/form-data; boundary="
                        + BOUNDARY);
        // 请求正文信息
        // 第一部分：
        StringBuilder sb = new StringBuilder();
        sb.append("--"); // 必须多两道线
        sb.append(BOUNDARY);
        sb.append("\r\n");
        sb.append("Content-Disposition: form-data;name=\"media\";filelength=\"" + file.length() + "\";filename=\""
                + file.getName() + "\"\r\n");
        sb.append("Content-Type:application/octet-stream\r\n\r\n");
        byte[] head = sb.toString().getBytes("utf-8");
        // 获得输出流
        OutputStream out = new DataOutputStream(con.getOutputStream());
        // 输出表头
        out.write(head);

        // 文件正文部分
        // 把文件已流文件的方式 推入到url中
        DataInputStream in = new DataInputStream(new FileInputStream(file));
        int bytes = 0;
        byte[] bufferOut = new byte[1024];
        while ((bytes = in.read(bufferOut)) != -1) {
            out.write(bufferOut, 0, bytes);
        }
        in.close();
        // 结尾部分
        byte[] foot = ("\r\n--" + BOUNDARY + "--\r\n").getBytes("utf-8");// 定义最后数据分隔线
        out.write(foot);
        out.flush();
        out.close();
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = null;
        try {
            // 定义BufferedReader输入流来读取URL的响应
            reader = new BufferedReader(new InputStreamReader(con.getInputStream()));
            String line = null;
            while ((line = reader.readLine()) != null) {
                buffer.append(line);
            }
            if (result == null) {
                result = buffer.toString();
            }
        } catch (IOException e) {
            System.out.println("发送POST请求出现异常！" + e);
            e.printStackTrace();
        } finally {
            if (reader != null) {
                reader.close();
            }
        }
        return result;
    }




    /**
     * 发送 GET 请求（HTTP），K-V形式
     * @param reqUrl
     * @param params
     * @return
     */
    public static String reqGet(String reqUrl, Map<String, Object> params) throws Exception{
        reqUrl=checkUrl(reqUrl);
        for (String key : params.keySet()) {
            if(reqUrl.indexOf("?")>=0){
                reqUrl+="&"+key+"="+params.get(key);
            }else{
                reqUrl+="?"+key+"="+params.get(key);
            }
        }
        String result = null;
        CloseableHttpClient httpclient = HttpClientManager.getHttpClient();
        try {
            HttpGet httpGet = new HttpGet(reqUrl);
            HttpResponse response = httpclient.execute(httpGet);
            int statusCode = response.getStatusLine().getStatusCode();
            HttpEntity entity = response.getEntity();
            if (entity != null) {
                ContentType contentType = ContentType.getOrDefault(entity);
                Charset charset = contentType.getCharset();
                result = EntityUtils.toString(entity, charset);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    /**
     * 校验url
     * @param reqUrl
     * @return
     * @throws Exception
     */
    private static String checkUrl(String reqUrl)throws Exception{
        if(ObjectUtils.isEmpty(reqUrl)){
            throw new Exception("请求url不能为空");
        }
        if(reqUrl.indexOf("access_token")>0){
            throw new Exception("请求参数中不能传入access_token");
        }
        if(reqUrl.indexOf("?")>=0){
            reqUrl=reqUrl+"&access_token="+ WxConfig.ACCESS_TOKEN;
        }else{
            reqUrl=reqUrl+"?access_token="+ WxConfig.ACCESS_TOKEN;
        }
        return reqUrl;
    }
}
