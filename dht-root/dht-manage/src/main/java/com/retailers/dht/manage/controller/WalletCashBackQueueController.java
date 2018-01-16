package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.Recharge;
import com.retailers.dht.common.entity.ReturnList;
import com.retailers.dht.common.service.ReturnListService;
import com.retailers.dht.common.service.WalletCashBackQueueService;
import com.retailers.dht.common.view.WalletCashBackQueueView;
import com.retailers.dht.manage.base.BaseController;
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
    private ReturnListService returnListService;
    /**
     * 打开排名公式页面
     * @return
     */
    @RequestMapping("openRankingPage")
    @Menu(label = "排名公示",description = "排名公示",resourse = "ranking.openRankingPage",parentRes = "sys.manager.customer",sort = 3)
    public ModelAndView openRankingPage(HttpServletRequest request){
        List<ReturnList> list = returnListService.queryAllReturnListList();
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("gts",list);
        modelAndView.setViewName("ranking/ranking");
        return modelAndView;
    }

    /**
     * 取得返现列表
     * @param gcId 返现类型
     * @param userNm 用户名
     * @param phone 用户电话
     * @return
     */
    @RequestMapping("queryRankingList")
    @Function(label="取得排名公示列表", description = "取得排名公示列表", resourse = "ranking.queryRankingList",sort=1,parentRes="ranking.openRankingPage")
    @ResponseBody
    public Map<String,Object> queryRankingList(Long gcId, String userNm, String phone){
        if(ObjectUtils.isEmpty(gcId)){
            return null;
        }
        List<WalletCashBackQueueView> list = walletCashBackQueueService.queryWalletCashBackQueues(gcId);
        Map<String,Object> rtn=new HashMap<String, Object>();
        rtn.put("total",20);
        rtn.put("rows",list);
        return rtn;
    }
}
