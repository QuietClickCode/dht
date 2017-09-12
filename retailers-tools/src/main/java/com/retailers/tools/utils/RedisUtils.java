package com.retailers.tools.utils;

import com.alibaba.fastjson.JSON;
import com.retailers.tools.base.AsyncCallback;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.redis.RedisPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.Jedis;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 缓存数据库操作
 */
public class RedisUtils {
    static  Logger logger = LoggerFactory.getLogger(RedisUtils.class);
    /**
     * redis缓存session所在的db
     */
    private final static int REDIS_DB_SESSION=5;
    /**
     * redis缓存session所在的db
     */
    public final static int REDIS_DB_BET = 1;
    /**
     * redis缓存基本所在的db
     */
    public final static int REDIS_DB_OTHER=0;

    public static final String KEY_BET_ODDS_CHANGE = "bet_odds_change";
    //shop工程，首页广告图
    public static final String KEY_ADVISORY_SHOP = "key_advisory_shop";
    //redis 实现单线程锁
    public static  final String REDIS_SINGLE_THREAD_LOCK_KEY="redis_single_thread_lock_key";

    /**
     * 缓存用户登陆信息
     * @param key key
     * @param data 用户登陆信息
     * @param timeOut 超时时间 单位为秒
     */
    public static void saveSession(String key,String data,int timeOut){
        logger.info("saveSession 设置sessio超时时间,sessionId:[{}],超时间:[{}]",key,timeOut);
        Jedis jedis =null;
        try{
            jedis= RedisPool.getJedis(REDIS_DB_SESSION);
            jedis.set(key,data);
            jedis.expire(key,timeOut);
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 删除用户登陆信息
     * @param key key
     */
    public static void delSession(String key){
        logger.info("删除sessio,sessionId:[{}]",key);
        Jedis jedis =null;
        try{
            jedis=RedisPool.getJedis(REDIS_DB_SESSION);
            jedis.del(key);
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 设置数据超时间
     * @param key redis key
     * @param timeOut 超时间 单位秒
     */
    public static void setTimeOut(String key,int timeOut){
        logger.info("setTimeOut 设置sessio超时时间,sessionId:[{}],超时间:[{}]",key,timeOut);
        Jedis jedis =null;
        try{
            jedis=RedisPool.getJedis(REDIS_DB_SESSION);
            jedis.expire(key,timeOut);
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 删除用户登陆信息
     * @param key key
     */
    public static String getSessionData(String key){
        return getDate(key,REDIS_DB_SESSION);
    }
    /**
     * 获取信息
     * @param key key
     */
    public static String getDate(String key,int dataBase){
        Jedis jedis =null;
        try{
            jedis=RedisPool.getJedis(dataBase);
            return jedis.get(key);
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 取得自增序列号
     * @param key
     * @return
     */
    public static long incrementNo(String key){
        Jedis jedis=null;
        try{
            jedis =RedisPool.getJedis();
            long value = jedis.incr(key);
            return value;
        }finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    public static void setData(String key,Object value){
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis(REDIS_DB_OTHER);
            jedis.set(key,value+"");
//            jedis.expire(key,50);
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    public static byte[] serialize(Object object) {
        ObjectOutputStream oos = null;
        ByteArrayOutputStream baos = null;
        try {
            //序列化
            baos = new ByteArrayOutputStream();
            oos = new ObjectOutputStream(baos);
            oos.writeObject(object);
            byte[] bytes = baos.toByteArray();
            return bytes;
        } catch (Exception e) {
        }
        return null;
    }

    public static Object unserialize(byte[] bytes) {
        ByteArrayInputStream bais = null;
        try {
            //反序列化
            bais = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(bais);
            return ois.readObject();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 保存set数据
     * @param db 数据库
     * @param key key值
     * @param data 数据
     */
    public static long saveSet(int db, String key, String... data) {
        Jedis jedis =null;
        try{
            jedis=RedisPool.getJedis(db);
            return jedis.sadd(key,data);
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 保存hash map
     * @param db
     * @param hashKey
     * @param value
     * @return
     */
    public static String saveMaps(int db, String hashKey, Map<String,String> value){
        Jedis jedis=null;
        try{
            jedis = RedisPool.getJedis(db);
            String key =jedis.hmset(hashKey,value);
            return key;
        }finally {
            if(jedis!=null){
                RedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 根据key获取记录
     * @param  key String
     * @return 值
     * */
    public static String get(int db,String key) {
        Jedis jedis=null;
        try{
            jedis = RedisPool.getJedis(db);
            String value = jedis.get(key);
            return value;
        }finally {
            if(jedis!=null){
                RedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 添加一条记录，仅当给定的key不存在时才插入
     * @param  key String
     * @param  value String
     * @return long 状态码，1插入成功且key不存在，0未插入，key存在
     * */
    public static long setnx(int db, String key, String value) {
        Jedis jedis = null;
        try {
            jedis = RedisPool.getJedis(db);
            long str = jedis.setnx(key, value);
            return str;
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 保存hash map
     * @param db
     * @param hashKey
     * @param key
     * @param value
     * @return
     */
    public static String saveMap(int db, String hashKey, String key,String value){
        Jedis jedis=null;
        try{
            jedis = RedisPool.getJedis(db);
            Map<String,String> map=new HashMap<String,String>();
            map.put(key,value);
            return jedis.hmset(hashKey,map);
        }finally {
            if(jedis!=null){
                RedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 移除Map 中的key
     * @param db
     * @param hashKey
     * @param key
     * @return
     */
    public static long removeMap(int db,String hashKey,String... key){
        Jedis jedis=null;
        try{
            jedis = RedisPool.getJedis(db);
            return jedis.hdel(hashKey,key);
        }finally{
            if(ObjectUtils.isNotEmpty(jedis)){
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 根据key 取得相应的值
     * @param db
     * @param hashKey
     * @param keys
     * @return
     */
    public static Map<String,String>  getMaps(int db,String hashKey,String ... keys){
        Jedis jedis=null;
        try{
            Map<String,String> rtn=new HashMap<String,String>();
            if(ObjectUtils.isEmpty(keys)){
                return rtn;
            }
            jedis=RedisPool.getJedis(db);
            List<String> lists = jedis.hmget(hashKey,keys);
            int i=0;
            for(String key:keys){
                String val=lists.get(i);
                if(ObjectUtils.isEmpty(val)){
                    val="";
                }
                rtn.put(key,val);
                i++;
            }
            return rtn;
        }finally {
            if(ObjectUtils.isNotEmpty(jedis)){
                RedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 根据key 取得相应的值
     * @param db
     * @param hashKey
     * @param keys
     * @return
     */
    public static String  getMap(int db,String hashKey,String keys){
        Jedis jedis=null;
        try{
            jedis=RedisPool.getJedis(db);
            List<String> lists = jedis.hmget(hashKey,keys);
            return lists.get(0);
        }finally {
            if(ObjectUtils.isNotEmpty(jedis)){
                RedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 删除集合数据
     * @param db 数据库
     * @param key key值
     * @param data 数据
     */
    public static void removeSet(int db, String key, String... data) {
        Jedis jedis =null;
        try{
            if (ObjectUtils.isNotEmpty(data)) {
                jedis=RedisPool.getJedis(db);
                jedis.srem(key,data);
            }
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 删除常量
     * @param db 数据库
     * @param key key
     */
    public static void removeKey(int db, String key) {
        Jedis jedis =null;
        try{
            if (ObjectUtils.isNotEmpty(key)) {
                jedis=RedisPool.getJedis(db);
                jedis.del(key);
            }
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }
    /**
     * 获取set数据
     * @param db 数据库
     * @param key key
     */
    public static Set<String> getSet(int db, String key) {
        Jedis jedis =null;
        Set<String> set = null;
        try{
            if (ObjectUtils.isNotEmpty(key)) {
                jedis=RedisPool.getJedis(db);
               set = jedis.smembers(key);
            }
        } finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
        return set;
    }

    /**
     * 保存数据至list中
     * @param db
     * @param key
     * @param value
     * @return
     */
    public static long saveList(int db,String key,String value){
      return saveLists(db,key,value);
    }

    /**
     * 保存数据至list中
     * @param db
     * @param key
     * @param value
     * @return
     */
    public static long saveLists(int db,String key,String... value){
        Jedis jedis =null;
        try{
            jedis=RedisPool.getJedis(db);
            return jedis.lpush(key,value);
        }finally {
            if (jedis != null) {
                RedisPool.returnResource(jedis);
            }
        }
    }

    /**
     * 基于redis set 实现单线程锁
     * @param lockKey
     * @throws AppException
     */
    public static void singleThreadLock(String lockKey)throws AppException {
        long total =saveSet(REDIS_DB_OTHER,REDIS_SINGLE_THREAD_LOCK_KEY,lockKey);
        if(total==0){
            throw new AppException("正在执行请稍后");
        }
    }
    /**
     * 基于redis set 实现单线程锁 解锁
     * @param lockKey 要解锁的key
     */
    public static void singleThreadUnLock(String lockKey){
        removeSet(REDIS_DB_OTHER,REDIS_SINGLE_THREAD_LOCK_KEY,lockKey);
    }



    /**
     * redis 消息机制
     * @param db 所在数据库
     * @param key 监听key
     * @param callback 回调处理
     */
    public static void listenMessageQuence(int db, String key, AsyncCallback callback){
        while (true) {
            if(saveSet(db,REDIS_SINGLE_THREAD_LOCK_KEY,key)>=0){
                Jedis jedis = RedisPool.getJedis(db);
                try{
                    //BRPOP 删除最选插入队列的数据
                    List<String> msgs = jedis.brpop(30, key);
                    if (ObjectUtils.isEmpty(msgs) || msgs.isEmpty()){
                        continue;
                    }
                    try{
                        callback.callback(msgs.get(1));
                    }catch(Exception e){
                    }
                }finally {
                    RedisPool.returnResource(jedis);
                    removeSet(db,REDIS_SINGLE_THREAD_LOCK_KEY,key);
                }
            }else{
                try{
                    Thread.sleep(1000*10);
                }catch(Exception e){
                }
            }
        }
    }


    public static void main(String[] args){
        try{
//            RedisUtils.saveSet(1,"odds_change","123","135","125");
//            RedisUtils.removeKey(1,"odds_change");
//            Set<String> betSet = RedisUtils.getSet(1,"odds_cdddhange");
//            if (ObjectUtils.isEmpty(betSet)) {
//                System.out.println("没有赔率信息");
//            }

//            Code code =new Code();
//            code.setCode("zpaman");
//            code.setDescription("数据查看");
////            byte[] q =serialize(code);
////            setData("zpaman_main",q);
//            Jedis jedis =null;
//                jedis=RedisPool.getJedis(REDIS_DB_OTHER);
////                jedis.get()
////            byte[] q =serialize(code);
////            jedis.set("zpaman_main".getBytes(),q);
//            byte[] q1=jedis.get("zpaman_main".getBytes());
//           Object obj= unserialize(q1);
//            System.out.println(JSON.toJSON(obj));
//           // setData("time-out-test","jedis 数据超时设置效果");
//            Map<String,String> map=new HashMap<>();
//            map.put("ab","zbad");
//            String key=saveMap(REDIS_DB_OTHER,"zpaman",map);
//            System.out.println(key);

            Map<String,String> rtn=getMaps(REDIS_DB_OTHER,"zpaman","ab","abc","efg");
            System.out.println(JSON.toJSON(rtn));
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
