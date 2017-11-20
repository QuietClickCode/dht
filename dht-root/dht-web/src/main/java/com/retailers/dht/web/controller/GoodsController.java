package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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

    @RequestMapping("/{id}.html")
    public String service(HttpServletRequest request, @PathVariable("id")String id){
        System.out.println("id====================================>:"+id);
        return redirectUrl(request,"goods/goods");
    }


    @RequestMapping("/queryGoodsById")
    @ResponseBody
    public  Map<String,Object> queryGoodsById(Long gid,Long isShow,Long isChecked){
        Map params = new HashMap();
        params.put("gid",gid);
        params.put("isDelete",0L);
        params.put("isShow",isShow);
        params.put("isChecked",isChecked);
        Pagination<Goods> pagination = goodsService.queryGoodsList(params,1,2);
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(pagination.getData())){
            gtm.put("row",pagination.getData().get(0));
        }
        return gtm;
    }

}
