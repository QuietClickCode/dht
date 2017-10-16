package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsBrand;
import com.retailers.dht.common.service.GoodsBrandService;
import com.retailers.dht.common.service.GoodsBrandService;
import com.retailers.dht.common.vo.GoodsBrandVo;
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
public class GoodsBrandController extends BaseController {

    @Autowired
    GoodsBrandService goodsBrandService;

    @RequestMapping("/openGoodsBrand")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openGoodsBrand",description = "商品品牌管理",sort = 1,label = "商品品牌管理")
    public String openGoodsBrand(){
        return "goods/goodsBrand";
    }

    @RequestMapping("editGoodsBrand")
    @Function(label = "编辑商品品牌",parentRes = "goods.openGoodsBrand",resourse = "goods.editGoodsBrand",description = "编辑商品品牌",sort = 2)
    @ResponseBody
    public BaseResp editGoodsBrand(GoodsBrand GoodsBrand){
        boolean flag = goodsBrandService.updateGoodsBrand(GoodsBrand);
        if(flag){
            return success("修改商品品牌["+GoodsBrand.getGbName()+"]成功");
        }else{
            return errorForSystem("修改商品品牌["+GoodsBrand.getGbName()+"]失败");
        }
    }

    @RequestMapping("/removeGoodsBrand")
    @Function(label="删除商品品牌", description = "删除商品品牌", resourse = "goods.removeGoodsBrand",sort=3,parentRes="goods.openGoodsBrand")
    @ResponseBody
    public BaseResp removeGoodsBrand(Long gbId){
        boolean flag=goodsBrandService.deleteGoodsBrandByGbId(gbId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsBrandLists")
    @Function(label="商品品牌列表", description = "所有商品品牌列表", resourse = "goods.queryGoodsBrandLists",sort=1,parentRes="goods.openGoodsBrand")
    @ResponseBody
    public  Map<String,Object> queryGoodsBrandLists(String gbName,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gbName",gbName);
        map.put("isDelete",0);
        Pagination<GoodsBrandVo> GoodsBrandPagination = goodsBrandService.queryGoodsBrandList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsBrandPagination.getTotalCount());
        gtm.put("rows",GoodsBrandPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsBrand")
    @Function(label="增加商品品牌", description = "增加商品品牌", resourse = "goods.addGoodsBrand",parentRes="goods.openGoodsBrand")
    @ResponseBody
    public BaseResp addGoodsBrand(GoodsBrand goodsBrand){
        goodsBrand.setIsDelete(0L);
        boolean flag=goodsBrandService.saveGoodsBrand(goodsBrand);
        return success(flag);
    }

}
