package com.retailers.tools.http;

import org.apache.http.HttpResponse;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.conn.ConnectionKeepAliveStrategy;
import org.apache.http.impl.client.BasicCookieStore;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.DefaultConnectionKeepAliveStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.protocol.HttpContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpClientManager {
	public static PoolingHttpClientConnectionManager connMgr;
	public static RequestConfig requestConfig;
	public static ConnectionKeepAliveStrategy kaStrategy;
	private static Logger logger = LoggerFactory.getLogger(HttpClientManager.class);
	/**
	 * 最大连接数
	 */
	public final static int MAX_TOTAL_CONNECTIONS = 300;
	/**
	 * 获取连接的最大等待时间
	 */
	public final static int WAIT_TIMEOUT = 60 * 1000*3;
	/**
	 * 连接超时时间
	 */
	public final static int CONNECT_TIMEOUT = 1000 * 60*3;
	/**
	 * 读取超时时间
	 */
	public final static int READ_TIMEOUT = 1000 * 60*3;

	static {
		// 设置连接池
		connMgr = new PoolingHttpClientConnectionManager();
		// 设置连接池大小
		connMgr.setMaxTotal(MAX_TOTAL_CONNECTIONS);
		connMgr.setDefaultMaxPerRoute(connMgr.getMaxTotal());

		RequestConfig.Builder configBuilder = RequestConfig.custom();
		// 设置连接超时
		configBuilder.setConnectTimeout(CONNECT_TIMEOUT);
		// 设置读取超时
		configBuilder.setSocketTimeout(READ_TIMEOUT);
		// 设置从连接池获取连接实例的超时
		configBuilder.setConnectionRequestTimeout(WAIT_TIMEOUT);
		// 在提交请求之前 测试连接是否可用
		configBuilder.setStaleConnectionCheckEnabled(true);
		requestConfig = configBuilder.build();
		kaStrategy = new DefaultConnectionKeepAliveStrategy() {
		public long getKeepAliveDuration(HttpResponse response, HttpContext context) {
			long keepAlive = super.getKeepAliveDuration(response, context);
			if (keepAlive == -1) {
				//如果服务器没有设置keep-alive这个参数，我们就把它设置成1分钟
				keepAlive = 1000*60;
			}
			return keepAlive;
		}
		};
	}

	public static CloseableHttpClient getHttpClient() {
		//每次登录就重新模拟一个浏览器
		CookieStore cookieStore= new BasicCookieStore();
		return HttpClients.custom().setConnectionManager(connMgr).setDefaultCookieStore(cookieStore).setKeepAliveStrategy(kaStrategy).build();
	}
	public static CloseableHttpClient getHttpClient(CookieStore cookieStore,String userAgent) {
		return HttpClients.custom().setConnectionManager(connMgr).setDefaultCookieStore(cookieStore).setUserAgent(userAgent).setKeepAliveStrategy(kaStrategy).build();
	}
	public static CloseableHttpClient getHttpClient(CookieStore cookieStore) {
		return HttpClients.custom().setConnectionManager(connMgr).setDefaultCookieStore(cookieStore).setKeepAliveStrategy(kaStrategy).build();
	}
	public static CloseableHttpClient getHttpClient(String userAgent) {
		return HttpClients.custom().setConnectionManager(connMgr).setUserAgent(userAgent).setKeepAliveStrategy(kaStrategy).build();
	}
}
