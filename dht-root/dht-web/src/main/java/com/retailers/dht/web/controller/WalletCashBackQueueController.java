package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.service.WalletCashBackQueueService;
import com.retailers.dht.common.view.WalletCashBackQueueView;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 返现队队列管理
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/10
 */
@Controller
@RequestMapping("ranking")
public class WalletCashBackQueueController extends BaseController {
    @Autowired
    private WalletCashBackQueueService walletCashBackQueueService;
    @Autowired
    private GoodsClassificationService goodsClassificationService;

    /**
     * 打开排名公式页面
      * @return
     */
    @RequestMapping("index")
    public ModelAndView openRankingPage(HttpServletRequest request){
        List<GoodsClassification> list = goodsClassificationService.queryParent();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("gts",list);
        modelAndView.setViewName(redirectUrl(request,"ranking/ranking"));
        return modelAndView;
    }

    /**
     * 取得返现列表
     * @return
     */
    @RequestMapping("queryRankingList")
    @ResponseBody
    public BaseResp queryRankingList(Long gcId){
        if(ObjectUtils.isEmpty(gcId)){
            return errorForParam("商品类型ID不能为空");
        }
        List<WalletCashBackQueueView> list = walletCashBackQueueService.queryWalletCashBackQueues(gcId);
        return success(list);
    }
}
