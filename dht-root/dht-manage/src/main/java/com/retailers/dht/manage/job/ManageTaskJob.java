package com.retailers.dht.manage.job;

import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.service.OrderSuccessQueueService;
import com.retailers.mybatis.common.constant.SingleThreadLockConstant;
import com.retailers.mybatis.common.service.AttachmentService;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 后台管理定时任务
 * @author zhongp
 * @version 1.0.1
 * @data 2017-10-07
 */
@Service("mTaskJob")
public class ManageTaskJob {
    Logger logger = LoggerFactory.getLogger(ManageTaskJob.class);
    @Autowired
    private AttachmentService attachmentService;
    @Autowired
    private ProcedureToolsService procedureToolsService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private OrderSuccessQueueService orderSuccessQueueService;
    @Autowired
    private OrderService orderser;

    /**
     * 每小时清除未被使用的附件资源
     */
    public void clearUnUsedAttachemnt(){
        logger.info("附件资源清除执行开始");
        attachmentService.clearUnUsedAttachemnt();
        logger.info("附件资源清除执行完毕");
    }

    /**
     * 清除最近一小时生成的序列
     */
    public void clearSequenceData(){
        logger.info("清除最近一小时生成的序列开始");
        procedureToolsService.clearSequenceData();
        logger.info("清除最近一小时生成的序列完毕");
    }

    /**
     * 清除过期优惠卷
     */
    public void clearExpireCoupon(){
        logger.info("清除过期优惠卷开始");
        couponService.clearExpireCoupon();
        logger.info("清除过期优惠卷完毕");
    }

    /**
     * 执行过期未付款的订单
     */
    public void executeOverdueUnPayOrder(){
        String key= SingleThreadLockConstant.CLEAR_EXPIRE_ORDER;
        try{
            procedureToolsService.singleLockManager(key);
        }catch(Exception e){
            logger.info("清除超时订单正在执行");
            return;
        }
        try{
            orderser.clearExpireOrder();
        }catch (Exception e){
            logger.info("清除失效订单异常:\r\n"+StringUtils.getErrorInfoFromException(e));
        }finally {
            procedureToolsService.singleUnLockManager(key);
        }

    }
    /**
     * 执行订单成功时处理的定时任务
     */
    public void executeOrderQueue(){
        String key="executeOrderQueue";
        try{
            procedureToolsService.singleLockManager(key);
        }catch(Exception e){
            logger.info("正在处理订单成功回调");
            return;
        }
        try{
            orderSuccessQueueService.executeOrderQueue();
        }finally {
            procedureToolsService.singleUnLockManager(key);
        }

    }

}
