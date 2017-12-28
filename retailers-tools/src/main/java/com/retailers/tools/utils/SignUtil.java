package com.retailers.tools.utils;

import com.alibaba.fastjson.JSON;
import com.retailers.tools.encrypt.EncryptUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * 与机器人这间的通信加密方式
 */
public class SignUtil {
    static Logger logger = LoggerFactory.getLogger(SignUtil.class);
    static String token ="A832C0F42C3308DD13180E00807B6536";
    static Random random = new Random();
    static  int randomLen=10;

    /**
     * 取得加密参数
     * @return
     */
    public static Map<String,String> getSign(){
        logger.info("加密参数开始：");
        Map<String,String> rtn = new HashMap<String,String>();
        long time=System.currentTimeMillis();
        rtn.put("t",time+"");
        //截取位置 对position%2 0 左边，1 右边
        int position=random.nextInt(randomLen);
        //截取时间截的长度
        int len = random.nextInt(randomLen);
        while (len==0){
            len = random.nextInt(randomLen);
        }
        rtn.put("r",len+""+position);
        String ranStr="";
        String s=time+"";
        if(position%2==0){
            ranStr=s.substring(0,len);
        }else{
            ranStr=s.substring(len);
        }
        rtn.put("s", EncryptUtil.md5Encode(token,ranStr));
        logger.info("参数加密结束");
        return rtn;
    }

    /**
     * 校验加密参数是否合法
     * @param time 时间
     * @param ran 随机数
     * @param sign 加密key
     * @return
     */
    public static boolean checkSign(String time,String ran,String sign){
        logger.info("判断是否是原生连接");
        //截取位置 对position%2 0 左边，1 右边
        int position=Integer.parseInt(ran.substring(1));
        //截取时间截的长度
        int len = Integer.parseInt(ran.substring(0,1));
        String ranStr="";
        if(position%2==0){
            ranStr=time.substring(0,len);
        }else{
            ranStr=time.substring(len);
        }
        String sign_=EncryptUtil.md5Encode(token,ranStr);
        logger.info("判断是否是原生连接,传入sign:{},生成sign:{}",sign,sign_);
        if(sign_.equals(sign)){
            return true;
        }
        return false;
    }

    /**
     * 加密校验
     * @param parms
     * @return
     */
    public static boolean encryptDES(Map<String,Object> parms) {
        boolean isNext = false;
        logger.info("取得参数信息:{}"+ JSON.toJSON(parms));
        if(ObjectUtils.isEmpty(parms.get("t"))||ObjectUtils.isEmpty(parms.get("r"))||ObjectUtils.isEmpty(parms.get("s"))){
            return isNext;
        }
        isNext = SignUtil.checkSign(parms.get("t")+"",parms.get("r")+"",parms.get("s")+"");
        return isNext;
    }

    /**
     * 校验加密参数是否合法
     * @param parms
     * @return
     */
    public static boolean checkSign(Map<String,Object> parms){
        boolean isNext = false;
        if(ObjectUtils.isEmpty(parms.get("t"))||ObjectUtils.isEmpty(parms.get("r"))||ObjectUtils.isEmpty(parms.get("s"))){
            return isNext;
        }
        isNext = SignUtil.checkSign(parms.get("t")+"",parms.get("r")+"",parms.get("s")+"");
        return isNext;
    }
    public static void main(String[] args){
//        for(int i=0;i<10;i++){
//            getSign();
//        }
        boolean flag =checkSign("1481101119029","89","001FDDA769C394156574E8F86007E9F");
        System.out.println(flag);
    }
}
