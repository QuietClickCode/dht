package com.retailers.dht.com.service;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.com.base.TestBaseJunit;
import com.retailers.dht.common.dao.WalletCashBackQueueMapper;
import com.retailers.dht.common.view.WalletCashBackQueueView;
import com.retailers.mybatis.pagination.Pagination;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/9
 */
public class WalletCashBackQueueServiceTest extends TestBaseJunit {

    @Autowired
    private WalletCashBackQueueMapper walletCashBackQueueMapper;

    @Test
    public void queryWalletCashBackQueues(){
        long gcId=14;
        List<WalletCashBackQueueView>lists= walletCashBackQueueMapper.queryWalletCashBackQueues(gcId);
        System.out.println(JSON.toJSON(lists));
    }
}
