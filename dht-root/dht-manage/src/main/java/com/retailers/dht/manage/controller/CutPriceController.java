package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.CutPrice;
import com.retailers.dht.common.service.CutPriceService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.common.vo.CutPriceVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("openCutPrice")
public class CutPriceController extends BaseController{
    @Autowired
    CutPriceService cutPriceService;

    @RequestMapping("/CutPriceMapping")
    @Menu(parentRes = "sys.manager.promotion",resourse = "openCutPrice.CutPriceMapping",description = "砍价",label = "砍价")
    public String CutPriceMapping(){
        return "promotion/cut_price";
    }

    @RequestMapping("/addCutPrice")
    @Function(label = "添加砍价名单",description = "添加砍价名单",resourse = "openCutPrice.addCutPrice",sort = 3,parentRes = "openCutPrice.CutPriceMapping")
    @ResponseBody
    public BaseResp addCutPrice(CutPrice cutPrice,String cpStartTimes,String cpEndTimes){
        cutPrice = addDate(cutPrice, cpStartTimes, cpEndTimes);
        boolean flag = cutPriceService.saveCutPrice(cutPrice);
        return success(flag);
    }

    @RequestMapping("/updateCutPrice")
    @Function(label = "修改砍价名单",description = "修改砍价名单",resourse = "openCutPrice.updateCutPrice",sort = 3,parentRes = "openCutPrice.CutPriceMapping")
    @ResponseBody
    public BaseResp updateCutPrice(CutPrice cutPrice){
        boolean flag = cutPriceService.updateCutPrice(cutPrice);
        if(flag)
            return success("修改秒杀[" + cutPrice.getCpName() + "]成功");
        else
            return errorForSystem("修改秒杀[" + cutPrice.getCpName() + "]失败");
    }

    @RequestMapping("/removeCutPrice")
    @Function(label = "删除砍价名单",description = "删除砍价名单",resourse = "openCutPrice.removeCutPrice",sort = 3,parentRes = "openCutPrice.CutPriceMapping")
    @ResponseBody
    public BaseResp removeCutPrice(Long cpId){
        boolean flag = cutPriceService.deleteCutPriceByCpId(cpId);
        return  success(flag);
    }

    @RequestMapping("/queryCutPriceLists")
    @Function(label="砍价名单集合", description = "砍价名单集合", resourse = "openCutPrice.queryCutPriceLists",sort=1,parentRes="openCutPrice.CutPriceMapping")
    @ResponseBody
    public Map<String,Object> queryCutPriceLists(){
        List<CutPriceVo> promotionVos = cutPriceService.queryCutPriceTree();
        Map gtm = new HashMap();
        gtm.put("rows",promotionVos);
        return gtm;
    }

    public CutPrice addDate(CutPrice cutPrice, String startTime, String endTime ){
        if (!ObjectUtils.isEmpty(startTime) && !ObjectUtils.isEmpty(endTime)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date spStarttime = sdf.parse(startTime);
                Date spEndtime = sdf.parse(endTime);
                cutPrice.setCpStartTime(spStarttime);
                cutPrice.setCpEndTime(spEndtime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return cutPrice;
    }

//    @RequestMapping("/getHasNocpGoods")
//    @ResponseBody
//    public Map<String,Object> getHasNoSpGoods(String gname,Long parentId){
//        List<GoodsVo> list = promotionService.queryHasNoSpGoods(gname,parentId);
//        Map map = new HashMap();
//        map.put("rows",list);
//        return map;
//    }
}
