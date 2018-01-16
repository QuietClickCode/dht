package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
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
    @ResponseBody
    public BaseResp editGoods(Goods goods,HttpServletRequest request){
        boolean flag = goodsService.updateGoods(goods,getCurLoginUserId(request));
        if(flag){
            return success("修改商品["+goods.getGname()+"]成功");
        }else{
            return errorForSystem("修改商品["+goods.getGname()+"]失败");
        }
    }

    @RequestMapping("/removeGoods")
    @Function(label="删除商品", description = "删除商品", resourse = "goods.removeGoods",sort=3,parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp removeGoods(Long gid,HttpServletRequest request){
        boolean flag=goodsService.deleteGoodsByGid(gid,getCurLoginUserId(request));
        return success(flag);
    }

    @RequestMapping("/queryGoodsLists")
    @Function(label="商品列表", description = "所有商品列表", resourse = "goods.queryGoodsLists",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsLists(String gname,Long gclassification,Long gmaindirection,Long isChecked,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gname",gname);
        map.put("gclassification",gclassification);
        map.put("gmaindirection",gmaindirection);
        map.put("isChecked",isChecked);
        map.put("isDelete",0);
        Pagination<GoodsVo> GoodsPagination = goodsService.queryGoodsList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsPagination.getTotalCount());
        gtm.put("rows",GoodsPagination.getData());
        return gtm;
    }
    @RequestMapping("/coupnGoodsLists")
    @ResponseBody
    public  Map<String,Object> coupnGoodsLists(String gname,Long gclassification,Long gmaindirection,Long isChecked,PageUtils pageForm){
        return queryGoodsLists(gname,gclassification,gmaindirection,isChecked,pageForm);
    }
    @RequestMapping("/queryGoodsById")
    @Function(label="商品列表", description = "所有商品列表", resourse = "goods.queryGoodsById",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsById(Long gid){
        Goods goods = goodsService.queryGoodsByGid(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("row",goods);
        return gtm;
    }

    @RequestMapping("/addGoods")
    @Function(label="增加商品", description = "增加商品", resourse = "goods.addGoods",parentRes="goods.openGoods")
    @ResponseBody
    public Map<String,Object> addGoods(Goods goods, HttpServletRequest request){
        goods.setIsDelete(0L);
        goods.setIsChecked(0L);
        Goods returnGoods=goodsService.saveGoods(goods,getCurLoginUserId(request));
        Map<String,Object> gtm = new HashMap<String,Object>();
        if (returnGoods==null){
            gtm.put("error","新增失败！");
        }else {
            gtm.put("goods",returnGoods);
        }
        return gtm;
    }

    @RequestMapping("/checkGoods")
    @Function(label="审核商品", description = "审核商品", resourse = "goods.checkGoods",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp checkGoods(Long gid,String message,Long myidea,HttpServletRequest request){
        Goods goods = goodsService.queryGoodsByGid(gid);
        goods.setIsChecked(myidea);
        goods.setGcheckmessage(message);
        goods.setGcheckperson(getCurLoginUserId(request));
        boolean flag = goodsService.checkGoods(goods);
        return success(flag);
    }

    @RequestMapping("/updateGoodsSetNotChecked")
    @Function(label="设置商品未审核", description = "设置商品未审核", resourse = "goods.updateGoodsSetNotChecked",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp updateGoodsSetNotChecked(Long gid,HttpServletRequest request){
        boolean flag = goodsService.updateGoodsSetNotChecked(gid,getCurLoginUserId(request));
        return success(flag);
    }

    /**
     *根据商品类型取得商品
     * @param request
     * @param gt 商品类型
     * @return
     */
    @RequestMapping("queryGoodsByGt")
    @ResponseBody
    public BaseResp queryGoodsByGt(HttpServletRequest request,Long gt){
        List<ZTreeVo> list = goodsService.queryGoodsByGt(gt);
        return success(list);
    }
}
