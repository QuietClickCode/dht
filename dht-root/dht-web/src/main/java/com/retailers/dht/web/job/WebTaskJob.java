package com.retailers.dht.web.job;

import com.retailers.dht.common.service.ExecuteQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/11/18.
 */
@Service("webTaskJob")
public class WebTaskJob {
    public void init(){
        System.out.println("init==================================================================");
    }
}
