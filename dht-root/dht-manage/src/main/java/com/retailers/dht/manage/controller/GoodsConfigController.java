package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsConfig;
import com.retailers.dht.common.service.GoodsConfigService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsConfigController extends BaseController {

    @Autowired
    GoodsConfigService goodsConfigService;

    @RequestMapping("/editGoodsConfig")
    @ResponseBody
    public BaseResp editGoodsConfig(GoodsConfig goodsConfig,String gedts,HttpServletRequest request){
        if(!ObjectUtils.isEmpty(gedts)){
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                Date gedt = sdf.parse(gedts);
                goodsConfig.setGedt(gedt);
            } catch (ParseException e) {
                goodsConfig.setGedt(null);
            }
        }else{
            goodsConfig.setGedt(null);
        }

        boolean flag = goodsConfigService.updateGoodsConfig(goodsConfig,getCurLoginUserId(request));
        if(flag){
            return success("修改商品配置成功");
        }else{
            return errorForSystem("修改商品配置失败");
        }
    }

    @RequestMapping("/removeGoodsConfig")
    @ResponseBody
    public BaseResp removeGoodsConfig(Long gid){
        boolean flag=goodsConfigService.deleteGoodsConfigByGcId(gid);
        return success(flag);
    }

    @RequestMapping("/queryGoodsConfigBygid")
    @ResponseBody
    public  Map<String,Object> queryGoodsConfigLists(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        Pagination<GoodsConfig> goodsConfigPagination = goodsConfigService.queryGoodsConfigList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(goodsConfigPagination.getData())){
            gtm.put("goodsConfig",goodsConfigPagination.getData().get(0));
        }

        return gtm;
    }

    @RequestMapping("/addGoodsConfig")
    @ResponseBody
    public BaseResp addGoodsConfig(GoodsConfig GoodsConfig,String gedts){
        GoodsConfig.setIsDelete(0L);
        if(!ObjectUtils.isEmpty(gedts)){
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
            try {
                Date gedt = sdf.parse(gedts);
                GoodsConfig.setGedt(gedt);
            }catch (Exception e){
                e.printStackTrace();
            }

        }
        boolean flag=goodsConfigService.saveGoodsConfig(GoodsConfig);
        return success(flag);
    }

}
