package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.service.GoodsService;
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
public class GoodsController extends BaseController {

    @Autowired
    GoodsService goodsService;

    @RequestMapping("/openGoods")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openGoods",description = "商品管理",sort = 1,label = "商品管理")
    public String openGoods(){
        return "goods/goods";
    }

    @RequestMapping("editGoods")
    @Function(label = "编辑商品",parentRes = "goods.openGoods",resourse = "goods.editGoods",description = "编辑商品",sort = 2)
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = "未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp editGoods(Goods Goods){
        boolean flag = goodsService.updateGoods(Goods);
        if(flag){
            return success("修改商品品牌["+Goods.getGname()+"]成功");
        }else{
            return errorForSystem("修改商品品牌["+Goods.getGname()+"]失败");
        }
    }

    @RequestMapping("/removeGoods")
    @Function(label="删除商品", description = "删除商品", resourse = "goods.removeGoods",sort=3,parentRes="goods.openGoods")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp removeGoods(Long gbId){
        boolean flag=goodsService.deleteGoodsByGid(gbId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsLists")
    @Function(label="商品列表", description = "所有商品列表", resourse = "goods.queryGoodsLists",sort=1,parentRes="goods.openGoods")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public  Map<String,Object> queryGoodsLists(String gbName,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gbName",gbName);
        Pagination<Goods> GoodsPagination = goodsService.queryGoodsList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsPagination.getTotalCount());
        gtm.put("rows",GoodsPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoods")
    @Function(label="增加商品", description = "增加商品", resourse = "goods.addGoods",parentRes="goods.openGoods")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp addGoods(Goods Goods){
        boolean flag=goodsService.saveGoods(Goods);
        return success(flag);
    }

}
