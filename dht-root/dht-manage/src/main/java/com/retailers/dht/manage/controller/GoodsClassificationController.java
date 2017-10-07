package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsClassificationController extends BaseController {
    @Autowired
    GoodsClassificationService goodsClassificationService;

    @RequestMapping("/openGoodsClassification")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openGoodsClassification",description = "商品子类管理",sort = 1,label = "商品子类管理")
    public String openGoodsClassification(){
        return "goods/goodsClassification";
    }

    @RequestMapping("editGoodsClassification")
    @Function(label = "编辑商品子类",parentRes = "goods.openGoodsClassification",resourse = "goods.editGoodsClassification",description = "编辑商品子类",sort = 2)
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = "未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp editGoodsClassification(GoodsClassification goodsClassification){
        boolean flag = goodsClassificationService.updateGoodsClassification(goodsClassification);
        if(flag){
            return success("修改商品子类["+goodsClassification.getGgName()+"]成功");
        }else{
            return errorForSystem("修改商品子类["+goodsClassification.getGgName()+"]失败");
        }
    }

    @RequestMapping("/removeGoodsClassification")
    @Function(label="删除商品子类", description = "删除商品子类", resourse = "goods.removeGoodsClassification",sort=3,parentRes="goods.openGoodsClassification")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp removeGoodsClassification(Long ggId){
        boolean flag=goodsClassificationService.deleteGoodsClassificationByGgId(ggId);
        return success(flag);


    }

    @RequestMapping("/queryGoodsClassificationLists")
    @Function(label="商品子类列表", description = "所有商品子类列表", resourse = "goods.queryGoodsClassificationLists",sort=1,parentRes="goods.openGoodsClassification")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public Map<String,Object> queryGoodsClassificationLists(){
        List<GoodsClassificationVo> goodsClassificationList = goodsClassificationService.queryGoodsClassificationTree();
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",1000);
        gtm.put("rows",goodsClassificationList);
        return gtm;
    }
    @RequestMapping("queryGoodsClassificationNode")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp queryGoodsClassificationNode(Long ggId){
        List<GoodsClassificationVo> goodsClassificationVoList =  goodsClassificationService.queryGoodsClassificationNode(ggId);
        return success(goodsClassificationVoList);
    }

    @RequestMapping("/addGoodsClassification")
    @Function(label="增加商品子类", description = "增加商品子类", resourse = "goods.addGoodsClassification",parentRes="goods.openGoodsClassification")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登陆，请重新登录",redirect = "http://www.baidu.com")
    @ResponseBody
    public BaseResp addGoodsClassification(GoodsClassification goodsClassification){
        boolean flag=goodsClassificationService.saveGoodsClassification(goodsClassification);

        if(flag){
            return success("新增商品子类成功");
        }else{
            return success("新增商品子类失败");
        }

    }
}