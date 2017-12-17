package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.entity.CutPriceLog;
import com.retailers.dht.common.entity.CutPricePrice;
import com.retailers.dht.common.service.CutPriceLogService;
import com.retailers.dht.common.service.CutPricePriceService;
import com.retailers.dht.common.vo.CutPriceLogVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("openCutPriceLog")
public class CutPriceLogController extends BaseController{
    @Autowired
    CutPriceLogService cutPriceLogService;
    @Autowired
    CutPricePriceService cutPricePriceService;
    @RequestMapping("/saveCutPriceLog")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp queryCutPriceListsByGid(CutPriceLog cutPriceLog, HttpServletRequest request,Long gid){
        cutPriceLog.setIsDelete(0L);
        Long uid = getCurLoginUserId(request);
        cutPriceLog.setUsId(uid);
        cutPriceLog.setUsdId(uid);
        if(!ObjectUtils.isEmpty(gid)){
            Map params = new HashMap();
            params.put("gid",gid);
            params.put("uid",uid);
            List<CutPricePrice> cutPricePriceList = cutPricePriceService.queryGdcpIdList(params,1,1).getData();
            int a = cutPricePriceList.size();
            if(!ObjectUtils.isEmpty(cutPricePriceList)){
                Long gdcpId = cutPricePriceList.get(0).getGdcpId();
                cutPriceLog.setGdcpId(gdcpId);
            }
        }
        boolean flag = cutPriceLogService.saveCutPriceLog(cutPriceLog);
        return success(flag);
    }

    @RequestMapping("/saveOtherCutPriceLog")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp saveOtherCutPriceLog(Long gid, HttpServletRequest request){
        try{
            Long usdId = getShareUserId(request);
            Long uid = getCurLoginUserId(request);
            boolean flag = false;
            if(!ObjectUtils.isEmpty(usdId)){
                Map params = new HashMap();
                params.put("gid",gid);
                params.put("uid",usdId);
                List<CutPricePrice> cutPricePriceList = cutPricePriceService.queryGdcpIdList(params,1,1).getData();
                if(!ObjectUtils.isEmpty(cutPricePriceList)){
                    Long gdcpId = cutPricePriceList.get(0).getGdcpId();
                    CutPriceLog cutPriceLog = new CutPriceLog();
                    cutPriceLog.setGdcpId(gdcpId);
                    cutPriceLog.setUsdId(uid);
                    cutPriceLog.setUsId(usdId);
                    flag = cutPriceLogService.saveCutPriceLog(cutPriceLog);
                }
            }
            if(flag){
                return success(flag);
            }else{
                return errorForSystem("该好友价格已经砍刀最低");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return  null;
    }

    @RequestMapping("/queryCutPriceLog")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public Map<String,Object> queryCutPriceLog(Long gid, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        List<CutPriceLogVo> list = cutPriceLogService.queryCutPriceLog(gid,uid);
        Map map = new HashMap();
        map.put("rows",list);
        return map;
    }

    @RequestMapping("/queryOtherCutPriceLog")
    @ResponseBody
    public Map<String,Object> queryOtherCutPriceLog(Long gid, HttpServletRequest request){
        try {
            Long uid = getShareUserId(request);
            if(ObjectUtils.isEmpty(uid)){
                return null;
            }
            List<CutPriceLogVo> list = cutPriceLogService.queryCutPriceLog(gid,uid);
            System.out.println(JSON.toJSON(list));
            Map map = new HashMap();
            map.put("rows",list);
            return map;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
