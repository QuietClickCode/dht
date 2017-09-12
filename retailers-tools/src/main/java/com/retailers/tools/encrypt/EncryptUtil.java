package com.retailers.tools.encrypt;

import com.retailers.tools.utils.DateUtil;
import sun.misc.BASE64Encoder;

import java.net.URLEncoder;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;

/**
 * Created by Administrator on 2015/5/28.
 */
public class EncryptUtil {
    /**
     * MD5加密
     * @param message 加密字符串
     * @return
     */
    private static String sha256Encode(String message){
      return encode("SHA-256",message);
    }
    /**
     * MD5加密
     * @param message 加密字符串
     * @return
     */
    private static String md5Encode(String message){
        return encode("MD5",message);
    }
    /**
     * MD5加密
     * @param args 要加密的数据
     * @return
     */
    public static String md5Encode(String ... args){
        StringBuilder sb = new StringBuilder();
        for (String key : args) {
            sb.append(key);
        }
        return md5Encode(sb.toString());
    }
    public static String byteArrayToHexString(byte[] digest) {
        String ret = "";
        for (int i = 0; i < digest.length; i++) {
            String a = Integer.toHexString(digest[i] & 0xff);
            if (digest[i] < 16 && digest[i] >= 0)
                a = "0" + a;
            ret += a.toUpperCase();
        }
        return ret;
    }
    /**
     * 将摘要信息转换为相应的编码
     * @param code 编码类型
     * @param message 摘要信息
     * @return 相应的编码字符串
     */
    private static String encode(String code,String message){
        MessageDigest md;
        String encode = null;
        try {
            md = MessageDigest.getInstance(code);
            encode = byteArrayToHexString(md.digest(message
                    .getBytes()));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return encode;
    }

    public static String encryptPass(String pass, Date datetime) {
    	String datetime_ = DateUtil.dateToString(datetime, DateUtil.DATE_WITHSECOND_FORMAT);
        return md5Encode(pass + datetime_);
    }

    /**
     * 生成交易密码
     * @param tradePwd 交易密码
     * @return
     */
    public static String encryptTradePwd(String tradePwd,long userId) throws Exception {
        String md5Str = md5Encode(tradePwd + userId);
        return md5Str;
    }

    public static String encryptPass(String pass, String userKeys) {
        return md5Encode(pass + userKeys);
    }
    /**
     * BASE64编码
     * @param src
     * @return
     * @throws Exception
     */
    public static String base64Encoder(String src) throws Exception {
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(src.getBytes("UTF-8"));
    }
    /**
     * 获取用户加密key
     * @param account
     * @return
     */
    public static String userEncryptKey(String account) throws Exception {
        String md5Str = md5Encode(account);
        String key = md5Str.substring(md5Str.length() - 8);
        String enKey = DESUtils.encryptDES(key,DesKey.WEB_KEY);
        return enKey;
    }
    public static void main(String[] args) throws Exception {//C3A412A8A79D1797D9D752838FF24B8C
//    	System.out.println(md5Encode("111111341"));
//    	System.out.println(encryptTradePwd("111111",341l));
        System.out.println(DESUtils.encryptDES("126456",DesKey.WEB_KEY));
        System.out.println(URLEncoder.encode("TExiP3foDeU=","UTF-8"));
}
}
