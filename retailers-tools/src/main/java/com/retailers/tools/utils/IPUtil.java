package com.retailers.tools.utils;


import javax.servlet.http.HttpServletRequest;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.UnknownHostException;
import java.util.Enumeration;

/**
 * 描述：查询ip
 * @author exu
 * @date 2015/7/29
 */
public class IPUtil {
    public static String getIpAddr(HttpServletRequest request) {
        String ipAddress = null;
        //ipAddress = request.getRemoteAddr();
        ipAddress = request.getHeader("X-Real-IP");
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("x-forwarded-for");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ipAddress == null || ipAddress.length() == 0 || "unknown".equalsIgnoreCase(ipAddress)) {
            ipAddress = request.getRemoteAddr();
            if (ipAddress.equals("127.0.0.1")) {
                //根据网卡取本机配置的IP
                InetAddress inet = null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ipAddress = inet.getHostAddress();
            }

        }

        //对于通过多个代理的情况，第一个IP为客户端真实IP,多个IP按照','分割
        if (ipAddress != null && ipAddress.length() > 15) { //"***.***.***.***".length() = 15
            if (ipAddress.indexOf(",") > 0) {
                ipAddress = ipAddress.substring(0, ipAddress.indexOf(","));
            }
        }
        return ipAddress;
    }

    /**
     * 获取项目地址
     * @param request 请求对象
     * @return
     */
    public static String getUrl(HttpServletRequest request) {
        String url = "";
        int port = request.getServerPort();
        if (port == 80) {
            url = request.getScheme() + "://" + request.getServerName() ;
        } else {
            url = request.getScheme() + "://" + request.getServerName() + ":" + port;
        }
        return url;
    }

    /**
     * 获取请求设备信息
     * @param request 请求对象
     * @return
     */
    public static String getDevice(HttpServletRequest request) {
        String header = request.getHeader("User-Agent");
        return header == null ? "" :header;
    }

    /**
     * 此方法描述的是：获得服务器的IP地址
     * @author: zhangyang33@sinopharm.com
     * @version: 2014年9月5日 下午4:57:15
     */
    public static String getLocalIP() {
        String sIP = "";
        InetAddress ip = null;
        try {
            boolean bFindIP = false;
            Enumeration<NetworkInterface> netInterfaces = ( Enumeration<NetworkInterface>) NetworkInterface
                    .getNetworkInterfaces();
            while (netInterfaces.hasMoreElements()) {
                if (bFindIP) {
                    break;
                }
                NetworkInterface ni = (NetworkInterface) netInterfaces
                        .nextElement();
                Enumeration<InetAddress> ips = ni.getInetAddresses();
                while (ips.hasMoreElements()) {
                    ip = (InetAddress) ips.nextElement();
                    if (!ip.isLoopbackAddress()
                            && ip.getHostAddress().matches(
                            "(\\d{1,3}\\.){3}\\d{1,3}")) {
                        bFindIP = true;
                        break;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (null != ip) {
            sIP = ip.getHostAddress();
        }
        return sIP;
    }
//    /**
//     * 获取ip归属地（利用淘宝接口）
//     * @param ip ip地址
//     * @return ip归属地
//     * @throws Exception
//     */
//    public static String getIPArea(String ip) {
//        String result = "";
//        String str_url = "http://api.k780.com:88/?app=ip.get&ip="+ip+"&appkey=10003&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&format=json";
//       try {
//           String data = HttpClientUtil.doHttpGet(str_url);
////        String json = "{\"success\":\"1\",\"result\":{\"status\":\"OK\",\"ip\":\"27.10.50.57\",\"ip_str\":\"27.10.50.57\",\"ip_end\":\"27.10.50.57\",\"inet_str\":\"453653049\",\"inet_end\":\"453653049\",\"operators\":\"联通\",\"att\":\"中国,重庆\",\"detailed\":\"中国西南重庆市重庆市联通\",\"area_style_simcall\":\"中国,重庆\",\"area_style_areanm\":\"中华人民共和国,重庆市\"}}";
//           JSONObject jsonObject = JSONObject.fromObject(data);
//           if (jsonObject.get("success").equals("1")) {
//               result = jsonObject.getJSONObject("result").get("att").toString();
//               result += jsonObject.getJSONObject("result").get("operators").toString();
//           }
//       } catch (Exception e) {
//           e.printStackTrace();
//       }
//        return result;
//    }
}
