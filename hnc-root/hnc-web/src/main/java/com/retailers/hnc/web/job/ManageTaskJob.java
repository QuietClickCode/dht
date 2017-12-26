package com.retailers.hnc.web.job;

import com.retailers.hnc.web.constant.WebSystemConstant;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 后台管理定时任务
 * @author zhongp
 * @version 1.0.1
 * @data 2017-10-07
 */
@Service("mTaskJob")
public class ManageTaskJob {
    Logger logger = LoggerFactory.getLogger(ManageTaskJob.class);

    /**
     * 清除退出用户
     */
    public void clearLoginOutUser(){
        logger.info("开始清除退出用户");
        Map<String,Object> map = WebSystemConstant.globelOpenId;
        List<String> strArr = new ArrayList<String>();
        for (Map.Entry<String, Object> entry : map.entrySet()) {
            String key = entry.getKey();
            Date date = (Date) entry.getValue();
            if(date.getTime()<System.currentTimeMillis()){
                strArr.add(key);
            }
        }
        if(ObjectUtils.isNotEmpty(strArr)){
            for(String key:strArr){
                map.remove(key);
            }
        }
        logger.info("清除退出用户完毕");
    }



}
