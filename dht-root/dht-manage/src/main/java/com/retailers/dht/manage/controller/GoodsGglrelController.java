package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.service.GoodsGglrelService;
import com.retailers.dht.common.vo.GoodsGglrelVo;
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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsGglrelController extends BaseController {

    @Autowired
    GoodsGglrelService goodsGglrelService;

    @RequestMapping("/removeGoodsGglrel")
    @Function(label="删除商品与标签关系", description = "删除商品与标签关系", resourse = "goods.removeGoodsGglrel",sort=3,parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp removeGoodsGglrel(String gglIds, HttpServletRequest request){
        boolean flag=goodsGglrelService.deleteGoodsGglrelByGglId(gglIds,getCurLoginUserId(request));
        return success(flag);
    }

    @RequestMapping("/queryGoodsGglrelLists")
    @Function(label="商品与标签关系列表", description = "商品与标签关系列表", resourse = "goods.queryGoodsGglrelLists",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGoodsGglrelLists(Long gid,Long glId,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("glId",glId);
        map.put("isDelete",0);
        Pagination<GoodsGglrelVo> GoodsGglrelPagination = goodsGglrelService.queryGoodsGglrelList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",GoodsGglrelPagination.getTotalCount());
        gtm.put("rows",GoodsGglrelPagination.getData());
        return gtm;
    }

    @RequestMapping("/addGoodsGglrel")
    @Function(label="增加商品与标签关系", description = "增加商品与标签关系", resourse = "goods.addGoodsGglrel",parentRes="goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsGglrel(String glIds,Long gid,String gids,Long glId,String gclassIds,HttpServletRequest request){
        boolean flag = false;
        if(!ObjectUtils.isEmpty(glIds)){
            flag = goodsGglrelService.saveGoodsGglrel(glIds,gid,getCurLoginUserId(request));
        }else if(!ObjectUtils.isEmpty(gids)){
            flag = goodsGglrelService.saveGoodsGglrelByGids(gids,glId,getCurLoginUserId(request));
        }else{
            flag = goodsGglrelService.saveGclassGoodsGglrel(glId,gclassIds);
        }
        return success(flag);
    }

    @RequestMapping("/queryGclassGoodsGglrelLists")
    @Function(label="通过子类查询商品与标签关系列表", description = "通过子类查询商品与标签关系列表", resourse = "goods.queryGclassGoodsGglrelLists",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> queryGclassGoodsGgcrelLists(Long gid){
        List<GoodsGglrelVo> list = goodsGglrelService.queryGclassGoodsGglrelLists(gid);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

    @RequestMapping("/removeGclassGoodsGglrel")
    @Function(label="删除商品与标签关系", description = "删除商品与标签关系", resourse = "goods.removeGclassGoodsGglrel",sort=1,parentRes="goods.openGoods")
    @ResponseBody
    public  Map<String,Object> removedeletedgglclass(Long gid,String glIds){


        boolean list = goodsGglrelService.removeGclassGoodsGglrel(gid,glIds);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }
}
