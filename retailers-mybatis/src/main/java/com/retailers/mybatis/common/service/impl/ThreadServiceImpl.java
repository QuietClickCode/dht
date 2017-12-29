package com.retailers.mybatis.common.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.retailers.mybatis.common.constant.DBSystemConstant;
import com.retailers.mybatis.common.service.ThreadService;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.SignUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 异步加载方法
 * @author  zhongp
 */
@Service
public class ThreadServiceImpl implements ThreadService {
    Logger logger = LoggerFactory.getLogger(ThreadServiceImpl.class);
    Logger mailLog = LoggerFactory.getLogger("maillog");

    @Async
    public void loadHttp(String url,String context){
        Map<String,String> params = new HashMap<String, String>();
        params.putAll(SignUtil.getSign());
        try {
            String str = HttpClientUtil.postUTF8(DBSystemConstant.getUrl(url,context),params);
            if(ObjectUtils.isEmpty(str)){
                mailLog.error("同步数据出错，出错服务器：{}",url);
            }
            JSONObject obj = JSONObject.parseObject(str);
            if(obj.getIntValue("status")!=0){
                mailLog.error("同步报价出错，出错服务器：{}",url);
            }
        } catch (Exception e) {
            logger.error("服务通信异常",e);
        }
    }
}
