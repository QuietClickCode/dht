package com.retailers.dht.web.controller;

import com.retailers.dht.common.service.GoodsImageService;
import com.retailers.dht.common.vo.GoodsImageVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
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
@RequestMapping("goodsImage")
public class GoodsImageController extends BaseController {

    @Autowired
    GoodsImageService goodsImageService;

    @RequestMapping("/queryGoodsImages")
    @ResponseBody
    public  Map<String,Object> queryGoodsImageLists(Long gid,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("gid",gid);
        map.put("isDelete",0);
        Pagination<GoodsImageVo> GoodsImagePagination = goodsImageService.queryGoodsImageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("goodsImages",GoodsImagePagination.getData());
        return gtm;
    }


}
