package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.ReturnList;
import com.retailers.dht.common.service.ReturnListService;
import com.retailers.dht.common.service.WalletCashBackQueueService;
import com.retailers.dht.common.view.WalletCashBackQueueView;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @RequestMapping("index")
    public ModelAndView openRankingPage(HttpServletRequest request){
        List<ReturnList> list = returnListService.queryAllReturnListList();
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


    /**
     * 打开用户提现详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("openCashDetails")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,isOpenPage = true)
    public ModelAndView openCashDetails(HttpServletRequest request, HttpServletResponse response){
        long uid=getCurLoginUserId(request);
        //取得用户返现详情
        Map<String,String> details= walletCashBackQueueService.queryUserCashBackDetail(uid);
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("details",details);
        modelAndView.setViewName(redirectUrl(request,"ranking/user_cash_detail"));
        return modelAndView;
    }

    /**
     * 打开用户提现详情
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("openUserCashDetails")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,isOpenPage = true)
    public String openUserCashDetails(HttpServletRequest request, HttpServletResponse response){
        return redirectUrl(request,"ranking/user_rankingList");
    }


    /**
     * 打开用户排名公示列表
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("openUserRankingLists")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,isOpenPage = true)
    public String openUserRankingLists(HttpServletRequest request, HttpServletResponse response){
        return redirectUrl(request,"ranking/user_ranking");
    }

    /**
     * 取得我的返现队列
     * @param request
     * @param type 请求类型（0 己返现，1 正在排队）
     * @return
     */
    @RequestMapping("queryUserRankingLists")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp queryUserRankingLists(HttpServletRequest request,Long type){
        // 取得当前登陆用户id
        long sUid=getCurLoginUserId(request);
        List<WalletCashBackQueueView> list = walletCashBackQueueService.queryUserRankingLists(sUid,type);
        return success(list);
    }
}
