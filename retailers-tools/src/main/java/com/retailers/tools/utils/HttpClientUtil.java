package com.retailers.tools.utils;

import com.retailers.tools.exception.AppException;
import com.retailers.tools.http.HttpClientManager;
import com.retailers.tools.http.HttpConnectionCallBack;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.CookieStore;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;


/**
 * Created by dell on 2015/1/13.
 */
public class HttpClientUtil {
    static Logger logger= LoggerFactory.getLogger(HttpClient.class);

    /**
     * 封装HTTP POST方法
     * @param
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
	public static String post(String url, Map<String, String> paramMap) throws IOException {
        CloseableHttpClient httpclient = HttpClientManager.getHttpClient();
        CloseableHttpResponse response=null;
        HttpPost httpPost = new HttpPost(url);
        try {
        	httpPost.setConfig(HttpClientManager.requestConfig);
        	List<NameValuePair> formparams = setHttpParams(paramMap);
            UrlEncodedFormEntity param = new UrlEncodedFormEntity(formparams);
            httpPost.setEntity(param);
            response = httpclient.execute(httpPost);
            String httpEntityContent = getHttpEntityContent(response);
            return httpEntityContent;
		} catch (IOException e) {
			e.printStackTrace();
			throw e;
		}finally{
			// 释放连接
   		 	try {
				EntityUtils.consume(response.getEntity());
				response.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
    }

	  /**
     * 封装HTTP POST方法
     * @param
     * @param
     * @return
     * @throws ClientProtocolException
     * @throws IOException
     */
	public static String postUTF8(String url, Map<String, String> paramMap) throws IOException {
        CloseableHttpClient httpclient = HttpClientManager.getHttpClient();
        CloseableHttpResponse response=null;
        HttpPost httpPost = new HttpPost(url);
        try {
        	httpPost.setConfig(HttpClientManager.requestConfig);
        	List<NameValuePair> formparams = setHttpParams(paramMap);
            UrlEncodedFormEntity param = new UrlEncodedFormEntity(formparams,"UTF-8");
            httpPost.setEntity(param);
            response = httpclient.execute(httpPost);
			String httpEntityContent = getHttpEntityContent(response);
            return httpEntityContent;
		} catch (IOException e) {
			throw e;
		}finally{
			// 释放连接
   		 	try {
				EntityUtils.consume(response.getEntity());
				response.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
    }

    /**
     * 设置请求参数
     * @param
     * @return
     */
    private static List<NameValuePair> setHttpParams(Map<String, String> paramMap) {
        List<NameValuePair> formparams = new ArrayList<NameValuePair>();
        Set<Map.Entry<String, String>> set = paramMap.entrySet();
        for (Map.Entry<String, String> entry : set) {
            formparams.add(new BasicNameValuePair(entry.getKey(), entry.getValue()));
        }
        return formparams;
    }

    /**
     * 获得响应HTTP实体内容
     * @param response
     * @return
     * @throws IOException
     * @throws java.io.UnsupportedEncodingException
     */
    private static String getHttpEntityContent(HttpResponse response) throws IOException {
        HttpEntity entity = response.getEntity();
        if (entity != null) {
            InputStream is = entity.getContent();
            BufferedReader br = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String line = br.readLine();
            StringBuilder sb = new StringBuilder();
            while (line != null) {
                sb.append(line + "\n");
                line = br.readLine();
            }
            return sb.toString();
        }
        return "";
    }



    /**
	 * 手机端登录
	 * @param url 请求的url
	 * @param httpConnectionCallBack 回调值
	 * @throws Exception
	 */
	public static Object httpGetRequest(String url,HttpConnectionCallBack httpConnectionCallBack) throws AppException,Exception{
		CloseableHttpClient httpclient = HttpClientManager.getHttpClient();
		HttpGet get = new HttpGet(url);
		HttpClientContext context = HttpClientContext.create();
		try{
			get.setConfig(HttpClientManager.requestConfig);
			get.addHeader("Accept","text/javascript, text/html, application/xml, text/xml, */*");
			get.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			CloseableHttpResponse response = httpclient.execute(get, context);
	        //执行请求
	        String responseEntity= EntityUtils.toString(response.getEntity(),"utf-8");
	        //释放连接
	        get.releaseConnection();
	        EntityUtils.consume(response.getEntity());
			if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
				logger.info("取得请求的状态值：{}",response.getStatusLine().getStatusCode());
				throw  new AppException("请求异常",response.getStatusLine().getStatusCode()+"");
			}
	        if(ObjectUtils.isNotEmpty(httpConnectionCallBack)){
	        	return httpConnectionCallBack.callback(responseEntity, context.getCookieStore());
	        }
		}finally {
			if(ObjectUtils.isNotEmpty(get)){
				get.releaseConnection();
			}
		}
		return null;
	}
	/**
	 * 手机端登录
	 * @param url 请求的url
	 * @throws Exception
	 */
	public static String httpGetRequest(String url) throws Exception{
		CloseableHttpClient httpclient = HttpClientManager.getHttpClient();
		HttpGet get = new HttpGet(url);
		HttpClientContext context = HttpClientContext.create();
		try{
			get.setConfig(HttpClientManager.requestConfig);
			get.addHeader("Accept","text/javascript, text/html, application/xml, text/xml, */*");
			get.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");

			CloseableHttpResponse response = httpclient.execute(get, context);
			//执行请求
			String responseEntity= EntityUtils.toString(response.getEntity(),"utf-8");
			//释放连接
			get.releaseConnection();
			EntityUtils.consume(response.getEntity());
			if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
                logger.info("取得请求的状态值：{}",response.getStatusLine().getStatusCode());
				throw  new AppException("请求异常",response.getStatusLine().getStatusCode()+"");
			}
			return responseEntity;
		}finally {
			if(ObjectUtils.isNotEmpty(get)){
				get.releaseConnection();
			}
		}
	}

	/**
	 * 手机端登录
	 * @param url 请求的url
	 * @throws Exception
	 */
	public static String httpGetRequest(String url,CookieStore cookieStore) throws Exception{
		CloseableHttpClient httpclient = HttpClientManager.getHttpClient(cookieStore);
		HttpGet get = new HttpGet(url);
		HttpClientContext context = HttpClientContext.create();
		try{
			get.setConfig(HttpClientManager.requestConfig);
			get.addHeader("Accept","text/javascript, text/html, application/xml, text/xml, */*");
			get.addHeader("Content-Type","application/x-www-form-urlencoded; charset=UTF-8");
			CloseableHttpResponse response = httpclient.execute(get, context);
			//执行请求
			String responseEntity= EntityUtils.toString(response.getEntity(),"utf-8");
			//释放连接
			get.releaseConnection();
			EntityUtils.consume(response.getEntity());
			if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
				logger.info("取得请求的状态值：{}",response.getStatusLine().getStatusCode());
				throw  new AppException("请求异常",response.getStatusLine().getStatusCode()+"");
			}
			return responseEntity;
		}finally {
			if(ObjectUtils.isNotEmpty(get)){
				get.releaseConnection();
			}
		}
	}

	/**
	 * 手机端登录
	 * @param url 请求的url
	 * @throws Exception
	 */
	public static String httpGetRequest(String url, CookieStore cookies,Map<String, String> postHeaders) throws Exception{
//		CloseableHttpClient httpclient = HttpClients.custom().setDefaultCookieStore(cookies).build();
		// HttpClients.custom().setConnectionManager(HttpClientManager.connMgr).setDefaultCookieStore(cookies).setKeepAliveStrategy(HttpClientManager.kaStrategy).build();
		CloseableHttpClient httpclient =HttpClientManager.getHttpClient(cookies);
		HttpGet get = new HttpGet(url);
		HttpClientContext context = HttpClientContext.create();
		try{
			get.setConfig(HttpClientManager.requestConfig);
			if(ObjectUtils.isNotEmpty(postHeaders)){
				for(String key:postHeaders.keySet()){
					get.addHeader(key,postHeaders.get(key));
				}
			}

			CloseableHttpResponse response = httpclient.execute(get, context);
			//执行请求
			String responseEntity= EntityUtils.toString(response.getEntity(),"utf-8");
			//释放连接
			get.releaseConnection();
			EntityUtils.consume(response.getEntity());
			if(response.getStatusLine().getStatusCode()!= HttpStatus.SC_OK){
				logger.info("取得请求的状态值：{}",response.getStatusLine().getStatusCode());
				throw  new AppException("请求异常",response.getStatusLine().getStatusCode()+"");
			}
			return responseEntity;
		}finally {
			if(ObjectUtils.isNotEmpty(get)){
				get.releaseConnection();
			}
		}
	}
}
