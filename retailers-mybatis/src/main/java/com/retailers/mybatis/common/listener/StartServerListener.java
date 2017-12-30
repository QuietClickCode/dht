package com.retailers.mybatis.common.listener;

import com.retailers.mybatis.common.constant.DBSystemConstant;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.management.*;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;
import java.util.Set;

/**
 * Created by admin on 2016/11/30.
 */
public class StartServerListener implements ServletContextListener {
    Logger logger = LoggerFactory.getLogger(StartServerListener.class);
    public void contextDestroyed(ServletContextEvent arg0) {

    }
    public void contextInitialized(ServletContextEvent context) {
        getPortByMBean();
    }

    /**
     * 取得服务器的ip 和端口
     * @return
     */
    private String getPortByMBean() {
        String logicNodeLogStr = "";
        InetAddress addr = null;
        String ip = "";
        addr = getLocalhost();
        ip = addr.getHostAddress().toString();
        logger.info("取得本机ip地址:[{}]",ip);
        if(ObjectUtils.isEmpty(ip)){
            try{
                ip=getIp();
            }catch(Exception e){
            }
        }

        MBeanServer mBeanServer = null;
        ArrayList<MBeanServer> mBeanServers = MBeanServerFactory.findMBeanServer(null);
        if (mBeanServers.size() > 0) {
            for (MBeanServer _mBeanServer : mBeanServers) {
                mBeanServer = _mBeanServer;
                break;
            }
        }
        if (mBeanServer == null) {
            throw new IllegalStateException("没有发现JVM中关联的MBeanServer.");
        }
        Set<ObjectName> objectNames = null;
        try {

            objectNames = mBeanServer.queryNames(new ObjectName("Catalina:type=Connector,*"), null);
        } catch (MalformedObjectNameException e) {
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        if (objectNames == null || objectNames.size() <= 0) {
            throw new IllegalStateException("没有发现JVM中关联的MBeanServer : "
                    + mBeanServer.getDefaultDomain() + " 中的对象名称.");
        }
        try {
            for (ObjectName objectName : objectNames) {
                String protocol = (String) mBeanServer.getAttribute(objectName, "protocol");
                if (protocol.equals("HTTP/1.1")) {
                    int port = (Integer) mBeanServer.getAttribute(objectName, "port");
                    DBSystemConstant.SERVER_PORT=port+"";
                    DBSystemConstant.SERVER_IP=ip;
                    logicNodeLogStr = ip + ":" + port;
                }
            }
        } catch (AttributeNotFoundException e) {
            e.printStackTrace();
        } catch (InstanceNotFoundException e) {
            e.printStackTrace();
        } catch (MBeanException e) {
            e.printStackTrace();
        } catch (ReflectionException e) {
            e.printStackTrace();
        }
        logger.info("取得本机服务的ip地址及端口：{}",logicNodeLogStr);
        return logicNodeLogStr;
    }
    /**
     * 获取本机网卡IP地址，这个地址为所有网卡中非回路地址的第一个<br>
     * 如果获取失败调用 {@link InetAddress#getLocalHost()}方法获取。<br>
     * 此方法不会抛出异常，获取失败将返回<code>null</code><br>
     *
     * 参考：http://stackoverflow.com/questions/9481865/getting-the-ip-address-of-the-current-machine-using-java
     *
     * @return 本机网卡IP地址，获取失败返回<code>null</code>
     * @since 3.0.1
     */
    public static InetAddress getLocalhost() {
        InetAddress candidateAddress = null;
        NetworkInterface iface;
        InetAddress inetAddr;
        try {
            for (Enumeration<NetworkInterface> ifaces = NetworkInterface.getNetworkInterfaces(); ifaces.hasMoreElements();) {
                iface = ifaces.nextElement();
                for (Enumeration<InetAddress> inetAddrs = iface.getInetAddresses(); inetAddrs.hasMoreElements();) {
                    inetAddr = inetAddrs.nextElement();
                    if (false == inetAddr.isLoopbackAddress()) {
                        if (inetAddr.isSiteLocalAddress()) {
                            return inetAddr;
                        } else if (null == candidateAddress) {
                            // 非site-local地址做为候选地址返回
                            candidateAddress = inetAddr;
                        }
                    }
                }
            }
        } catch (SocketException e) {
            // ignore socket exception, and return null;
        }

        if (null == candidateAddress) {
            try {
                candidateAddress = InetAddress.getLocalHost();
            } catch (UnknownHostException e) {
                // ignore
            }
        }

        return candidateAddress;
    }
    /**
     * linux 下无外网的ip取得方式
     * @return
     * @throws Exception
     */
    private String getIp()throws Exception{
        Process p = Runtime.getRuntime().exec("ifconfig -a");
        InputStreamReader ir = new InputStreamReader(p.getInputStream());
        LineNumberReader input = new LineNumberReader(ir);
        p.waitFor();
        boolean flag = true;
        List<String> ipLists = new ArrayList<String>();
        while (flag) {
            String str = input.readLine();
            if (str != null) {
                if (str.indexOf("inet") >= 0) {
                    str=str.trim();
                    str=str.substring(0, str.indexOf("netmask"));
                    str=str.trim();
                    str=str.substring(str.indexOf("inet")+4);
                    str=str.trim();
                    if(!str.equals("127.0.0.1")){
                        ipLists.add(str);
                    }
                }
            } else
                flag = false;
        }
        if(ipLists.size()>0){
            return ipLists.get(0);
        }
        return "";
    }
}
