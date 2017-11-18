package com.retailers.dht.web.job;

import com.retailers.dht.common.entity.ExecuteQueue;
import com.retailers.dht.common.service.ExecuteQueueService;
import com.retailers.dht.common.service.SysParameterConfigService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/11/18.
 */
@Service("webTaskJob")
public class WebTaskJob {
    @Autowired
    ExecuteQueueService executeQueueService;
    @Autowired
    private SysParameterConfigService sysParameterConfigService;
    public void init(){

//        executeQueueService.addHistoryExecuteQueue(null);
        sysParameterConfigService.initSysParamter();
        System.out.println("=------------------------------------->>>");


    }
}
