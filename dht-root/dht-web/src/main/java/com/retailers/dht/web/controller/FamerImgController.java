package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.FamerImg;
import com.retailers.dht.common.service.FamerImgService;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.common.vo.FamerImgVo;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("famerImg")
public class FamerImgController extends BaseController {

    @Autowired
    FamerImgService famerImgService;

    @RequestMapping("queryFamerImgVoList")
    @ResponseBody
    public Map queryFamerImgVoList(Long famerid){
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("fid",famerid);
        List<FamerImgVo> famerImgs = famerImgService.queryFamerImgVoList(params,1,999).getData();
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(famerImgs)){
            map.put("rows",famerImgs);
        }
        return map;
    }
}
