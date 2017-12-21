package com.retailers.hnc.com.service;

import com.retailers.hnc.com.base.TestBaseJunit;
import com.retailers.dht.common.vo.GoodsCouponVo;
import com.retailers.tools.utils.ValidationUtils;
import org.junit.Test;

/**
 * Created by admin on 2017/10/10.
 */
public class ValidationTest extends TestBaseJunit {

    @Test
    public void checkGoodsCoupon(){
        GoodsCouponVo vo = new GoodsCouponVo();
        vo.setGcpCondition(12.3698);
        try{
            ValidationUtils.validate(vo);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
