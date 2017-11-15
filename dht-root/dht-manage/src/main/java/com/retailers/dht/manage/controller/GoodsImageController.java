package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.dht.common.entity.GoodsImage;
import com.retailers.dht.common.service.GoodsImageService;
import com.retailers.dht.common.vo.GoodsImageVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class GoodsImageController extends BaseController {

    @Autowired
    GoodsImageService goodsImageService;

    @RequestMapping("/editGoodsImage")
    @Function(label = "编辑商品图片",parentRes = "goods.openGoods",resourse = "GoodsImage.editGoodsImage",description = "编辑商品图片",sort = 2)
    @ResponseBody
    public BaseResp editGoodsImage(GoodsImage GoodsImage,String gedts,HttpServletRequest request){


        boolean flag = goodsImageService.updateGoodsImage(GoodsImage,getCurLoginUserId(request));
        if(flag){
            return success("修改商品图片成功");
        }else{
            return errorForSystem("修改商品图片失败");
        }
    }

    @RequestMapping("/removeGoodsImage")
    @Function(label="删除商品图片", description = "删除商品图片", resourse = "GoodsImage.removeGoodsImage",sort=3,parentRes = "goods.openGoods")
    @ResponseBody
    public BaseResp removeGoodsImage(Long giId,HttpServletRequest request){
        boolean flag=goodsImageService.deleteGoodsImageByGiId(giId,getCurLoginUserId(request));
        return success(flag);
    }

    @RequestMapping("/queryGoodsImages")
    @Function(label="商品图片列表", description = "商品图片信息", resourse = "GoodsImage.queryGoodsImageList",sort=1,parentRes = "goods.openGoods")
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

    @RequestMapping("/addGoodsImage")
    @Function(label="增加商品图片", description = "增加商品图片", resourse = "GoodsImage.addGoodsImage",parentRes = "goods.openGoods")
    @ResponseBody
    public BaseResp addGoodsImage(GoodsImage goodsImage, HttpServletRequest request){
        goodsImage.setIsDelete(0L);
        boolean flag=goodsImageService.saveGoodsImage(goodsImage,getCurLoginUserId(request));
        return success(flag);
    }

}
