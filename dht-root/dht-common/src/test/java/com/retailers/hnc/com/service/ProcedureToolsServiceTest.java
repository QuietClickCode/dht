package com.retailers.hnc.com.service;

import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.mybatis.common.enm.OrderEnum;
import com.retailers.mybatis.common.service.ProcedureToolsService;
import com.retailers.tools.exception.AppException;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by admin on 2017/11/21.
 */
public class ProcedureToolsServiceTest extends TestBaseJunit {
    @Autowired
    private ProcedureToolsService procedureToolsService;
    @Test
    public void executeOrderNo()throws Exception {
        long time=System.currentTimeMillis();
        String orderNo = procedureToolsService.executeOrderNo(OrderEnum.PRE_ORDER);
        System.out.println(System.currentTimeMillis()-time);
        System.out.println(orderNo);
    }

    @Test
    public void singleLockManager()throws AppException{
        procedureToolsService.singleLockManager("12");
    }

    @Test
    public void singleUnLockManager()throws Exception{
        procedureToolsService.singleUnLockManager("12");
        Thread.sleep(10000);
    }
}
