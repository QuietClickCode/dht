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
    @Function(label = "编辑商品配置",parentRes = "goods.openGoods",resourse = "goodsConfig.editGoodsConfig",description = "编辑商品配置",sort = 2)
    @ResponseBody
    public BaseResp editGoodsConfig(GoodsConfig goodsConfig,String gedts,HttpServletRequest request){
        if(!ObjectUtils.isEmpty(gedts)){
            SimpleDateFormat sdf = new SimpleDateFormat();
            Date gedt = null;
            try {
                gedt = sdf.parse(gedts);
            } catch (ParseException e) {
                e.printStackTrace();
            }
            goodsConfig.setGedt(gedt);
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
    @Function(label="删除商品配置", description = "删除商品配置", resourse = "goodsConfig.removeGoodsConfig",sort=3,parentRes = "goods.openGoods")
    @ResponseBody
    public BaseResp removeGoodsConfig(Long gid){
        boolean flag=goodsConfigService.deleteGoodsConfigByGcId(gid);
        return success(flag);
    }

    @RequestMapping("/queryGoodsConfigBygid")
    @Function(label="商品配置", description = "商品配置信息", resourse = "goodsConfig.queryGoodsConfigBygid",sort=1,parentRes = "goods.openGoods")
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
    @Function(label="增加商品配置", description = "增加商品配置", resourse = "goodsConfig.addGoodsConfig",parentRes = "goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsConfig(GoodsConfig GoodsConfig){
        boolean flag=goodsConfigService.saveGoodsConfig(GoodsConfig);
        return success(flag);
    }

}
