package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsType;
import com.retailers.dht.common.service.GoodsTypeService;
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
public class GoodsTypeController extends BaseController {

    @Autowired
    GoodsTypeService goodsTypeService;

    @RequestMapping("/openGoodsType")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openGoodsType",description = "商品大类管理",sort = 1,label = "商品大类管理")
    public String openGoodsType(){
        return "goods/goodsType";
    }

    @RequestMapping("editGoodsType")
    @Function(label = "编辑商品大类",parentRes = "goods.openGoodsType",resourse = "goods.editGoodstype",description = "编辑商品大类",sort = 2)
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = "未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp editGoodsType(GoodsType goodsType){
        boolean flag = goodsTypeService.updateGoodsType(goodsType);
        if(flag){
            return success("修改商品大类["+goodsType.getGtName()+"]成功");
        }else{
            return errorForSystem("修改商品大类["+goodsType.getGtName()+"]失败");
        }
    }

    @RequestMapping("/removeGoodsType")
    @Function(label="删除商品大类", description = "删除商品大类", resourse = "goods.removeGoodstype",sort=3,parentRes="goods.openGoodsType")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp removeGoodsType(Long gtId){
        boolean flag=goodsTypeService.deleteGoodsTypeByGtId(gtId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsTypeLists")
    @Function(label="商品大类列表", description = "所有商品大类列表", resourse = "goods.queryGoodsTypeLists",sort=1,parentRes="goods.openGoodsType")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public  Map<String,Object> queryGoodsTypeLists(String gtName,Long isShow, PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gtName",gtName);
        map.put("isShow",isShow);
        Pagination<GoodsType> goodsTypePagination = goodsTypeService.queryGoodsTypeList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",goodsTypePagination.getTotalCount());
        gtm.put("rows",goodsTypePagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsType")
    @Function(label="增加商品大类", description = "增加商品大类", resourse = "goods.addGoodsType",parentRes="goods.openGoodsType")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp addGoodsType(GoodsType goodsType){
        boolean flag=goodsTypeService.saveGoodsType(goodsType);
        return success(flag);
    }

}
