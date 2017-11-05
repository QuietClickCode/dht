package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.GoodsClassification;
import com.retailers.dht.common.service.GoodsClassificationService;
import com.retailers.dht.common.vo.GoodsClassificationVo;
import com.retailers.dht.web.base.BaseController;
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
@RequestMapping("goodsClassification")
public class GoodsClassificationController extends BaseController {
    @Autowired
    GoodsClassificationService goodsClassificationService;

    @RequestMapping("/queryGoodsClassificationLists")
    @ResponseBody
    public Map<String,Object> queryGoodsClassificationLists(Long parenId){
        List<GoodsClassification> goodsClassificationList = goodsClassificationService.queryGoodsClassificationListByParentId(parenId);
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",goodsClassificationList);
        return gtm;
    }

}
