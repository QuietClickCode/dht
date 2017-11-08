package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsCommentlabel;
import com.retailers.dht.common.service.GoodsCommentlabelService;
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
public class GoodsCommentlabelController extends BaseController {

    @Autowired
    GoodsCommentlabelService goodsCommentlabelService;

    @RequestMapping("/openGoodsCommentlabel")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openGoodsCommentlabel",description = "商品评论管理",sort = 1,label = "商品评论管理")
    public String openGoodsCommentlabel(){
        return "goods/goodsCommentlabel";
    }

    @RequestMapping("editGoodsCommentlabel")
    @Function(label = "编辑商品评论",parentRes = "goods.openGoodsCommentlabel",resourse = "goods.editGoodsCommentlabel",description = "编辑商品评论",sort = 2)
    @ResponseBody
    public BaseResp editGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel){
        boolean flag = goodsCommentlabelService.updateGoodsCommentlabel(goodsCommentlabel);
        if(flag){
            return success("修改商品品牌["+goodsCommentlabel.getGclName()+"]成功");
        }else{
            return errorForSystem("修改商品品牌["+goodsCommentlabel.getGclName()+"]失败");
        }
    }

    @RequestMapping("/removeGoodsCommentlabel")
    @Function(label="删除商品评论", description = "删除商品评论", resourse = "goods.removeGoodsCommentlabel",sort=3,parentRes="goods.openGoodsCommentlabel")
    @ResponseBody
    public BaseResp removeGoodsCommentlabel(Long gclId){
        boolean flag=goodsCommentlabelService.deleteGoodsCommentlabelByGclId(gclId);
        return success(flag);
    }

    @RequestMapping("/queryGoodsCommentlabelLists")
    @Function(label="商品评论列表", description = "所有商品评论列表", resourse = "goods.queryGoodsCommentlabelLists",sort=1,parentRes="goods.openGoodsCommentlabel")
    @ResponseBody
    public  Map<String,Object> queryGoodsCommentlabelLists(Long gclId,String gclName,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gclName",gclName);
        map.put("gclId",gclId);
        map.put("isDelete",0);
        Pagination<GoodsCommentlabel> GoodsCommentlabelPagination = goodsCommentlabelService.queryGoodsCommentlabelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsCommentlabelPagination.getTotalCount());
        gtm.put("rows",GoodsCommentlabelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsCommentlabel")
    @Function(label="增加商品评论", description = "增加商品评论", resourse = "goods.addGoodsCommentlabel",parentRes="goods.openGoodsCommentlabel")
    @ResponseBody
    public Map<String,Object> addGoodsCommentlabel(GoodsCommentlabel goodsCommentlabel){
        goodsCommentlabel.setIsDelete(0L);
        goodsCommentlabel = goodsCommentlabelService.saveGoodsCommentlabel(goodsCommentlabel);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("row",goodsCommentlabel);
        return gtm;
    }

    @RequestMapping("editGoodsCommentlabelStart")
    @Function(label = "编辑星级",parentRes = "goods.openGoodsCommentlabel",resourse = "goods.editGoodsCommentlabelStart",description = "编辑星级",sort = 2)
    @ResponseBody
    public BaseResp editGoodsCommentlabelStart(Long gclId,String gclName){
        GoodsCommentlabel goodsCommentlabel = goodsCommentlabelService.queryGoodsCommentlabelByGclId(gclId);
        goodsCommentlabel.setGclName(gclName);
        boolean flag = goodsCommentlabelService.updateGoodsCommentlabel(goodsCommentlabel);
        return success(flag);
    }

}
