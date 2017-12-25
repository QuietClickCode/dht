package com.retailers.dht.com.service;

import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.service.UserAddressService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/22
 */
public class UserAddressServiceTest extends TestBaseJunit {
    @Autowired
    private UserAddressService userAddressService;

    @Test
    public void defaultUserAddress()throws Exception{
        userAddressService.defaultUserAddress(-1l,8l);
    }
}
