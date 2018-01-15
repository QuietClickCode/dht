package com.retailers.tools.encrypt;


import com.retailers.tools.utils.Base64;
import com.retailers.tools.utils.StringUtils;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.DESKeySpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.security.GeneralSecurityException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

/**
 * 解密方法
 */
public class DESUtils{
    /** 加密算法,可用 DES,DESede,Blowfish. */
    private final static String ALGORITHM = "DES";
    /**
     * DES解密算法
     * @param data
     * @param cryptKey  密钥 要是偶数
     * @return
     * @throws Exception
     */
    public static String decryptDES(String data, String cryptKey) throws Exception {
        return new String(decrypt(hex2byte(data.getBytes()),
                cryptKey.getBytes()));
    }

    /**
     * DES加密算法
     * @param data
     * @param cryptKey
     * @return
     * @throws Exception
     */
    public final static String encryptDES(String data, String cryptKey)
            throws Exception {
        return byte2hex(encrypt(data.getBytes(), cryptKey.getBytes()));
    }
    private static byte[] encrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成加密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.ENCRYPT_MODE, securekey, sr);
        // 现在，获取数据并加密
        // 正式执行加密操作
        return cipher.doFinal(data);
    }

    private static byte[] decrypt(byte[] data, byte[] key) throws Exception {
        // DES算法要求有一个可信任的随机数源
        SecureRandom sr = new SecureRandom();
        // 从原始密匙数据创建一个DESKeySpec对象
        DESKeySpec dks = new DESKeySpec(key);
        // 创建一个密匙工厂，然后用它把DESKeySpec对象转换成
        // 一个SecretKey对象
        SecretKeyFactory keyFactory = SecretKeyFactory.getInstance(ALGORITHM);
        SecretKey securekey = keyFactory.generateSecret(dks);
        // Cipher对象实际完成解密操作
        Cipher cipher = Cipher.getInstance(ALGORITHM);
        // 用密匙初始化Cipher对象
        cipher.init(Cipher.DECRYPT_MODE, securekey, sr);
        // 现在，获取数据并解密
        // 正式执行解密操作
        return cipher.doFinal(data);
    }

    private static byte[] hex2byte(byte[] b) {
        if ((b.length % 2) != 0)
            throw new IllegalArgumentException("长度不是偶数");
        byte[] b2 = new byte[b.length / 2];
        for (int n = 0; n < b.length; n += 2) {
            String item = new String(b, n, 2);
            b2[n / 2] = (byte) Integer.parseInt(item, 16);
        }
        return b2;
    }

    private static String byte2hex(byte[] b) {
        String hs = "";
        String stmp = "";
        for (int n = 0; n < b.length; n++) {
            stmp = (java.lang.Integer.toHexString(b[n] & 0XFF));
            if (stmp.length() == 1)
                hs = hs + "0" + stmp;
            else
                hs = hs + stmp;
        }
        return hs.toUpperCase();
    }


//    public static String encryptDES(String encryptString, String encryptKey) throws Exception {
//        SecretKeySpec key = new SecretKeySpec(encryptKey.getBytes(), "DES");
//        Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//        cipher.init(Cipher.ENCRYPT_MODE, key);
//        byte[] encryptedData = cipher.doFinal(encryptString.getBytes("UTF-8"));
//
//        return Base64.encode(encryptedData);
//    }

//    public static String decryptDES(String decryptString, String decryptKey) throws GeneralSecurityException {
//    	String result = "";
//        try {
//        	byte[] byteMi = Base64.decode(decryptString);
//            SecretKeySpec key = new SecretKeySpec(decryptKey.getBytes(), "DES");
//            Cipher cipher = Cipher.getInstance("DES/ECB/PKCS5Padding");
//            cipher.init(Cipher.DECRYPT_MODE, key);
//            byte decryptedData[] = cipher.doFinal(byteMi);
//            return new String(decryptedData,"UTF-8");
//		} catch (NoSuchAlgorithmException e) {
//			e.printStackTrace();
//		} catch (NoSuchPaddingException e) {
//			e.printStackTrace();
//		} catch (InvalidKeyException e) {
//			e.printStackTrace();
//		} catch (UnsupportedEncodingException e) {
//			e.printStackTrace();
//		}
//		return result;
//    }

//    /**
//     * web项目参数加密
//     * @param encryptString
//     * @return
//     */
//    public static String encryptWeb(String encryptString) {
//        String result = "";
//        try {
//            result = encryptDES(encryptString, DesKey.WEB_KEY);
//        } catch (Exception e) {
//            System.out.println("des加密异常：原参数"+encryptString+",解密值："+DesKey.WEB_KEY);
//            e.printStackTrace();
//        }
//        return result;
//    }

//    /**
//     * web项目，参数解密
//     * @param encryptString
//     * @return
//     */
//    public static String decryptWeb(String encryptString) {
//        String result = "";
//        try {
//            result = decryptDES(encryptString, DesKey.WEB_KEY);
//        } catch (Exception e) {
//            System.out.println("des解密异常：原参数"+encryptString+",解密值："+DesKey.WEB_KEY);
//            e.printStackTrace();
//        }
//        return result;
//    }


//    /**
//     * 解密用户加密字段
//     * @param str 加密字段
//     * @return
//     */
//    public static String decryptUserField(String str) throws Exception {
//        String value = "";
//        try {
//             value = DESUtils.decryptDES(str,DesKey.WEB_FIELD_KEY);
//        } catch (Exception e) {
//            System.out.println("des解密异常：原参数"+str+",解密后："+value+",参数："+DesKey.WEB_FIELD_KEY);
//            e.printStackTrace();
//            throw new Exception();
//        }
//        return value;
//    }
//    /**
//     * 加密用户加密字段
//     * @param str 加密字段
//     * @return
//     */
//    public static String encryptUserField(String str) throws Exception {
//        str = DESUtils.encryptDES(str,DesKey.WEB_FIELD_KEY);
//        return str;
//    }
    public static void main(String args[]) throws Exception {
        System.out.println(encryptDES("我是中国人，我爱中国",DesKey.WEB_KEY));
//
//        System.out.println(encryptDES("35",DesKey.WEB_KEY));
//        System.out.println(decryptDES(URLDecoder.decode("uUODsnCRYTk%3D"),DesKey.WEB_KEY));
//        System.out.println(decryptDES("+tvTITYd1AS7KLotqFWQfO097ll91p1F",DesKey.WEB_KEY));
//        System.out.println(URLEncoder.encode(encryptDES("35",DesKey.WEB_KEY),"utf-8"));
//        System.out.println(decryptDES(URLDecoder.decode("W6wGZiOTbDQ=","utf-8"),DesKey.WEB_KEY));
//        System.out.println(decryptDES(URLDecoder.decode("W6wGZiOTbDQ=","utf-8"),DesKey.WEB_KEY));
//        System.out.println(decryptDES(URLDecoder.decode("W6wGZiOTbDQ=","utf-8"),DesKey.WEB_KEY));
//        System.out.println(decryptDES(URLDecoder.decode("W6wGZiOTbDQ=","utf-8"),DesKey.WEB_KEY));
//        System.out.println(encryptDES("16",DesKey.WEB_KEY));
//        System.out.println(URLEncoder.encode(encryptDES("16",DesKey.WEB_KEY),"utf-8"));
//        System.out.println(decryptDES("pHRW0BbLWzg=",DesKey.WEB_KEY));
//        encryptDES(StringUtils.formate("123",System.currentTimeMillis()+""),DesKey.WEB_KEY);
    }
}
