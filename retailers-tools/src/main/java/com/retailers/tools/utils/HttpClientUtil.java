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
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.nio.charset.Charset;
import java.util.*;


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

	public static String doGet(String url) {
		return  doGet(url, new HashMap<String, Object>());
	}
	/**
	 * 发送 GET 请求（HTTP），K-V形式
	 * @param url
	 * @param params
	 * @return
	 */
	public static String doGet(String url, Map<String, Object> params) {
		String apiUrl = url;
		StringBuffer param = new StringBuffer();
		int i = 0;
		for (String key : params.keySet()) {
			if (i == 0)
				param.append("?");
			else
				param.append("&");
			param.append(key).append("=").append(params.get(key));
			i++;
		}
		apiUrl += param;
		String result = null;
		CloseableHttpClient httpclient = HttpClientManager.getHttpClient();
		try {
			HttpGet httpPost = new HttpGet(apiUrl);
			HttpResponse response = httpclient.execute(httpPost);
			int statusCode = response.getStatusLine().getStatusCode();
			System.out.println("执行状态码 : " + statusCode);
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
	 * 发送 POST 请求（HTTP），JSON形式
	 * @param apiUrl
	 * @param json json对象
	 * @return
	 */
	public static String doPostBodyJson(String apiUrl, String json) {
		CloseableHttpClient httpclient =HttpClientManager.getHttpClient();
		HttpClientContext context = HttpClientContext.create();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		try {
			httpPost.setConfig(HttpClientManager.requestConfig);
			StringEntity stringEntity = new StringEntity(json,"UTF-8");//解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpclient.execute(httpPost,context);
			HttpEntity entity = response.getEntity();
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
	 * @param apiUrl
	 * @param json json对象
	 * @return
	 */
	public static InputStream doPostRQCode(String apiUrl, String json,HttpServletResponse out) {
		CloseableHttpClient httpclient =HttpClientManager.getHttpClient();
		HttpClientContext context = HttpClientContext.create();
		String httpStr = null;
		HttpPost httpPost = new HttpPost(apiUrl);
		CloseableHttpResponse response = null;
		try {
			httpPost.setConfig(HttpClientManager.requestConfig);
			StringEntity stringEntity = new StringEntity(json,"UTF-8");//解决中文乱码问题
			stringEntity.setContentEncoding("UTF-8");
			stringEntity.setContentType("application/json");
			httpPost.setEntity(stringEntity);
			response = httpclient.execute(httpPost,context);
//			HttpEntity entity = response.getEntity();
//			HttpResponse response = httpClient.execute(httpPost);
			if (response != null) {
				HttpEntity resEntity = response.getEntity();
				if (resEntity != null) {
					InputStream instreams = resEntity.getContent();

					try{
						OutputStream outputStream=out.getOutputStream();
						byte[] buffer=new byte[1024];
						int len = 0;
						while((len=instreams.read(buffer))!=-1){
							outputStream.write(buffer,0,len);
						}
						outputStream.close();
						instreams.close();
					}catch(Exception e){
						e.printStackTrace();
					}


//					saveToImgByInputStream(instreams, "D:\\", "apr.jpg");
				}
			}
//			httpStr = EntityUtils.toString(entity, "UTF-8");
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
		return null;
	}
}
