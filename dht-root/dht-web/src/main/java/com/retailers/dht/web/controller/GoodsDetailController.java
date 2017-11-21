package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.Goods;
import com.retailers.dht.common.service.GoodsDetailService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.GoodsDetailVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goodsDetail")
public class GoodsDetailController extends BaseController {

    @Autowired
    GoodsDetailService goodsDetailService;

    @RequestMapping("/queryGoodsDetailVoLists")
    @ResponseBody
    public  Map<String,Object> queryGoodsDetailVoLists(String gsvalIds,Long gid){
        Map params = new HashMap();
        params.put("gid",gid);
        if(!ObjectUtils.isEmpty(gsvalIds)){
            String[] gsvalIdsArr = gsvalIds.split(",");
            List<Long> l = new ArrayList<Long>();
            for(String str:gsvalIdsArr){
                Long a = Long.parseLong(str);
                l.add(a);
            }
            params.put("gsvId",l);
        }
        GoodsDetailVo goodsDetailVo = goodsDetailService.queryGoodsDetailVoLists(params);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("row",goodsDetailVo);
        return gtm;
    }

}
