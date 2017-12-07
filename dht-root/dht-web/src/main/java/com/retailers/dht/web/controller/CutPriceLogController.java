package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.CutPriceLog;
import com.retailers.dht.common.entity.CutPricePrice;
import com.retailers.dht.common.service.CutPriceLogService;
import com.retailers.dht.common.service.CutPricePriceService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("openCutPriceLog")
public class CutPriceLogController extends BaseController{
    @Autowired
    CutPriceLogService cutPriceLogService;

    @RequestMapping("/saveCutPriceLog")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp queryCutPriceListsByGid(CutPriceLog cutPriceLog, HttpServletRequest request){
        cutPriceLog.setIsDelete(0L);
        Long uid = getCurLoginUserId(request);
        cutPriceLog.setUsId(uid);
        cutPriceLog.setUsdId(uid);
        boolean flag = cutPriceLogService.saveCutPriceLog(cutPriceLog);
        return success(flag);
    }

}
