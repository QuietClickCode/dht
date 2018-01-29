package com.retailers.razz.common.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.tools.utils.Base64;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.security.spec.AlgorithmParameterSpec;

/**
 * Created by niconiconi on 2018/1/3.
 */
public class MyHttpUrlConnection {
    private final int mTimeout = 10000; // 超时时间
    /**
     * get访问
     */
    public String[] requestJson(String url) {
        return request(url);
    }
    private String[] request(String connurl) {
        String[] resultStr = new String[]{"", ""};
        StringBuilder resultData = new StringBuilder("");
        HttpURLConnection conn = null;
        try {
            URL url = new URL(connurl);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.setUseCaches(false);
            conn.setConnectTimeout(mTimeout);
            conn.connect();
            int resultCode = conn.getResponseCode();
            InputStreamReader in;
            if (resultCode == 200) {
                in = new InputStreamReader(conn.getInputStream());
                BufferedReader buffer = new BufferedReader(in);
                String inputLine;
                while ((inputLine = buffer.readLine()) != null) {
                    resultData.append(inputLine);
                    resultData.append("\n");
                }
                buffer.close();
                in.close();
            }
            resultStr[0] = resultData.toString();
            resultStr[1] = resultCode + "";
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
        return resultStr;
    }

    public static byte[] decrypt(byte[] key, byte[] iv, byte[] encData)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidAlgorithmParameterException,
        InvalidKeyException, BadPaddingException, IllegalBlockSizeException {
        AlgorithmParameterSpec ivSpec = new IvParameterSpec(iv);
        Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5Padding");
        SecretKeySpec keySpec = new SecretKeySpec(key, "AES");
        cipher.init(Cipher.DECRYPT_MODE, keySpec, ivSpec);
        return cipher.doFinal(encData);
    }

    public static String decryptPhoneData(String encryptData,String iv,String sessionKey){
        byte[] bt1 = Base64.decode(sessionKey);
        byte[] bt2 = Base64.decode(iv);
        byte[] bt3 = Base64.decode(encryptData);
        try{
            String jsonStr = new String(MyHttpUrlConnection.decrypt(bt1,bt2,bt3));
            JSONObject jsonObject = JSON.parseObject(jsonStr);
            String purePhoneNumber = jsonObject.getString("purePhoneNumber");
            return purePhoneNumber;
        }catch (Exception e){
            e.printStackTrace();
        }

        return null;
    }


}
