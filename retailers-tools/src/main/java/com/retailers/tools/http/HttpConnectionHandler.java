package com.retailers.tools.http;


import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;


/**
 * @Description
 * @author shensw
 * @version 1.0.0
 * @date 2014/8/11.13:21
 */
public class HttpConnectionHandler {
    private static PoolingHttpClientConnectionManager cm;
    /**
     * 最大连接数
     */
    public final static int MAX_TOTAL_CONNECTIONS = 800;

    /**
     * 每个路由最大连接数
     */
    public final static int MAX_ROUTE_CONNECTIONS = 800;


    static {

        try{

            ConnectionSocketFactory sf = PlainConnectionSocketFactory.getSocketFactory();


            Registry<ConnectionSocketFactory> r = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", sf)
                    .build();

            cm = new PoolingHttpClientConnectionManager(r);
            cm.setMaxTotal(MAX_TOTAL_CONNECTIONS);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    public static CloseableHttpClient getHttpClient(){
        return HttpClients.custom().setConnectionManager(cm).build();
    }
}
