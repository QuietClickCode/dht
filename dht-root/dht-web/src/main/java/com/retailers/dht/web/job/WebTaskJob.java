package com.retailers.dht.web.job;

import com.retailers.dht.common.service.ExecuteQueueService;
import com.retailers.dht.common.service.OrderProcessingQueueService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by admin on 2017/11/18.
 */
@Service("webTaskJob")
public class WebTaskJob {
    Logger logger= LoggerFactory.getLogger(WebTaskJob.class);
    @Autowired
    private OrderProcessingQueueService orderProcessingQueueService;
    public void init(){

    }
    /**
     * 项目启动时执行
     */
    public void startJob(){
        logger.info("项目启动,订单队列数据加载");
        orderProcessingQueueService.initProcessingQueue();
        //订单回调处理
        orderProcessingQueueService.executeOrderProcessingQueue();
        logger.info("项目启动,订单队列数据结束");

    }
}
