package com.retailers.tools.encrypt;


import com.retailers.tools.utils.Base64;
import com.retailers.tools.utils.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

/**
 * 解密方法
 */
public class DESUtils{
    public static String encryptDES(String encryptString, String encryptKey) throws Exception {
        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
        cipher.init(Cipher.ENCRYPT_MODE, key);
        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));

        return Base64.encode(encryptedData);
    }

    public static String decryptDES(String decryptString, String decryptKey) throws GeneralSecurityException {
    	String result = "";
        try {
        	byte[] byteMi = Base64.decode(decryptString);
            SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
            cipher.init(Cipher.DECRYPT_MODE, key);
            byte decryptedData[] = cipher.doFinal(byteMi);
            return new String(decryptedData,"UTF-8");
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} catch (NoSuchPaddingException e) {
			e.printStackTrace();
		} catch (InvalidKeyException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return result;
    }

    /**
     * web项目参数加密
     * @param encryptString
     * @return
     */
    public static String encryptWeb(String encryptString) {
        String result = "";
        try {
            result = encryptDES(encryptString, DesKey.WEB_KEY);
        } catch (Exception e) {
            System.out.println("des加密异常：原参数"+encryptString+",解密值："+DesKey.WEB_KEY);
            e.printStackTrace();
        }
        return result;
    }

    /**
     * web项目，参数解密
     * @param encryptString
     * @return
     */
    public static String decryptWeb(String encryptString) {
        String result = "";
        try {
            result = decryptDES(encryptString, DesKey.WEB_KEY);
        } catch (Exception e) {
            System.out.println("des解密异常：原参数"+encryptString+",解密值："+DesKey.WEB_KEY);
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 解密用户加密字段
     * @param str 加密字段
     * @return
     */
    public static String decryptUserField(String str) throws Exception {
        String value = "";
        try {
             value = DESUtils.decryptDES(str,DesKey.WEB_FIELD_KEY);
        } catch (Exception e) {
            System.out.println("des解密异常：原参数"+str+",解密后："+value+",参数："+DesKey.WEB_FIELD_KEY);
            e.printStackTrace();
            throw new Exception();
        }
        return value;
    }
    /**
     * 加密用户加密字段
     * @param str 加密字段
     * @return
     */
    public static String encryptUserField(String str) throws Exception {
        str = DESUtils.encryptDES(str,DesKey.WEB_FIELD_KEY);
        return str;
    }
    public static void main(String args[]) throws Exception {
//        System.out.println(encryptDES("35",DesKey.WEB_KEY));
//
//        System.out.println(encryptDES("35",DesKey.WEB_KEY));
        System.out.println(decryptDES("cYktLaSBYWY=",DesKey.WEB_KEY));
//        System.out.println(URLEncoder.encode(encryptDES("35",DesKey.WEB_KEY),"utf-8"));
//        System.out.println(decryptDES(URLDecoder.decode("W6wGZiOTbDQ=","utf-8"),DesKey.WEB_KEY));
//        System.out.println(decryptDES(URLDecoder.decode("W6wGZiOTbDQ=","utf-8"),DesKey.WEB_KEY));
//        System.out.println(decryptDES(URLDecoder.decode("W6wGZiOTbDQ=","utf-8"),DesKey.WEB_KEY));
//        System.out.println(decryptDES(URLDecoder.decode("W6wGZiOTbDQ=","utf-8"),DesKey.WEB_KEY));
//        System.out.println(encryptDES("16",DesKey.WEB_KEY));
//        System.out.println(URLEncoder.encode(encryptDES("16",DesKey.WEB_KEY),"utf-8"));
//        System.out.println(decryptDES("pHRW0BbLWzg=",DesKey.WEB_KEY));
        encryptDES(StringUtils.formate("123",System.currentTimeMillis()+""),DesKey.WEB_KEY);
    }
}
