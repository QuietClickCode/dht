package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsConfig;
import com.retailers.dht.common.service.GoodsConfigService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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

    @RequestMapping("editGoodsConfig")
    @Function(label = "编辑商品配置",parentRes = "goods.openGoods",resourse = "goodsConfig.editGoodsConfig",description = "编辑商品",sort = 2)
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = "未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp editGoodsConfig(GoodsConfig GoodsConfig){
        boolean flag = goodsConfigService.updateGoodsConfig(GoodsConfig);
        if(flag){
            return success("修改商品配置成功");
        }else{
            return errorForSystem("修改商品配置失败");
        }
    }

    @RequestMapping("/removeGoodsConfig")
    @Function(label="删除商品配置", description = "删除商品配置", resourse = "goodsConfig.removeGoodsConfig",sort=3,parentRes = "goods.openGoods")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp removeGoodsConfig(Long gid){
        boolean flag=goodsConfigService.deleteGoodsConfigByGcId(gid);
        return success(flag);
    }

    @RequestMapping("/queryGoodsConfigBygid")
    @Function(label="商品配置", description = "商品配置信息", resourse = "goodsConfig.queryGoodsConfigBygid",sort=1,parentRes = "goods.openGoods")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public  Map<String,Object> queryGoodsConfigLists(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        Pagination<GoodsConfig> goodsConfigPagination = goodsConfigService.queryGoodsConfigList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        System.out.println(goodsConfigPagination.getData().size());
        gtm.put("goodsConfig",goodsConfigPagination.getData().get(0));
        return gtm;
    }

    @RequestMapping("/addGoodsConfig")
    @Function(label="增加商品配置", description = "增加商品配置", resourse = "goodsConfig.addGoodsConfig",parentRes = "goods.openGoods")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp addGoodsConfig(GoodsConfig GoodsConfig){
        boolean flag=goodsConfigService.saveGoodsConfig(GoodsConfig);
        return success(flag);
    }

}
