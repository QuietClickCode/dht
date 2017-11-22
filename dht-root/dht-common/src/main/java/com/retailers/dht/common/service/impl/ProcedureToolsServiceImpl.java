package com.retailers.dht.common.service.impl;

import com.retailers.dht.common.dao.ProcedureToolsMapper;
import com.retailers.dht.common.enm.OrderEnum;
import com.retailers.dht.common.entity.Sequence;
import com.retailers.dht.common.service.ProcedureToolsService;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.DateUtil;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * 公用工具类
 * @author zhongp
 * @version 1.0.1
 */
@Service("procedureToolsService")
public class ProcedureToolsServiceImpl implements ProcedureToolsService {
    Logger logger = LoggerFactory.getLogger(ProcedureToolsServiceImpl.class);
    @Autowired
    private ProcedureToolsMapper procedureToolsMapper;
    /**
     * 根据订单类型生成订单号
     * @param orderEnum 订单类型
     * @return
     */
    public String executeOrderNo(OrderEnum orderEnum) {
        Date curDate =new Date();
        logger.info("开始执行订单号生成");
        String orderNo="QT";
        if(ObjectUtils.isNotEmpty(orderEnum)){
            orderNo=orderEnum.getShorNm();
        }
        orderNo=orderNo+DateUtil.dateToString(curDate, DateUtil.DATE_LONG_SMAIL_FORMAT);
        Sequence sequence=new Sequence();
        sequence.setTime(curDate.getTime()/1000);
        procedureToolsMapper.queryCurSequence(sequence);
        long num=sequence.getId();
        int num_=(int)(num%1000);
        orderNo=orderNo+ NumberUtils.formaterRandNumberr(num_);
        logger.info("订单生成完成，执行时间：{},订单类型:{},订单号：{}",(System.currentTimeMillis()-curDate.getTime()),orderEnum.getKey(),orderNo);
        return orderNo;
    }

    @Async
    public void clearSequenceData() {
        logger.info("执行序列数据清除");
        Date curDate =new Date();
        long time = DateUtil.addSecond(curDate,-2).getTime()/1000;
        long count = procedureToolsMapper.clearSequenceData(time);
        while (count>0){
            try{
                Thread.sleep(1000);
            }catch(Exception e){
            }
            count = procedureToolsMapper.clearSequenceData(time);
        }
        logger.info("执行时间：{},删除条数:{}",(System.currentTimeMillis()-curDate.getTime()),count);
    }

    public void singleLockManager(String key) throws AppException {
        logger.info("添加单线程锁开始,锁key:[{}]",key);
        Date curDate=new Date();
        try{
            procedureToolsMapper.singleLockManager(key,curDate);
        }catch(Exception e){
            throw new AppException("正在执行");
        }
        finally {
            logger.info("添加单线程锁结束,执行时间:[{}]",(System.currentTimeMillis()-curDate.getTime()));
        }

    }
    @Async
    public void singleUnLockManager(String key) {
        logger.info("解除单线程锁开始,锁key:[{}]",key);
        Date curDate=new Date();
        try{
            procedureToolsMapper.singleUnLockManager(key);
        }finally {
            logger.info("解除单线程锁结束,执行时间:[{}]",(System.currentTimeMillis()-curDate.getTime()));
        }
    }
}
