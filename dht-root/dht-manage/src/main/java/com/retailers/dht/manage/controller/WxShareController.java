package com.retailers.dht.manage.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.retailers.dht.common.service.GoodsService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.encrypt.Sha1DESUtils;
import com.retailers.tools.utils.HttpClientUtil;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.ShareImageUtils;
import com.retailers.tools.utils.StringUtils;
import com.retailers.wx.common.config.WxConfig;
import com.retailers.wx.common.utils.wx.WXPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("wxShare")
public class WxShareController extends BaseController{

    @Autowired
    private GoodsService goodsService;

    @RequestMapping("shareImage")
    public void shareImage(HttpServletRequest request,HttpServletResponse response,String goodsImgUrl,String url,String goodsPrice,String goodsNm){
        setResponseHeaders(response);
//        goodsImgUrl="http://dht.kuaiyis.com/attachment/goods/2018/01/02/0a017bf6583676949a70662f164bd25d_originalfile.jpg";
        try{
            String gidStr = url.substring(url.lastIndexOf("/")+1,url.indexOf(".html"));
            System.out.println(gidStr);
            Long gid = Long.parseLong(gidStr);
            goodsNm = goodsService.queryGoodsByGid(gid).getGname();
            url = url.substring(1);
            url = SysParameterConfigConstant.getValue(SysParameterConfigConstant.MASTER_SERVER_MOBILE_URL)+url;
            if("0".equals(goodsPrice)){
                goodsPrice = "特价商品";
            }else if("1".equals(goodsPrice)){
                goodsPrice = "秒杀商品";
            }else if("2".equals(goodsPrice)){
                goodsPrice = "砍价商品";
            }

            OutputStream outputStream=response.getOutputStream();
            ShareImageUtils.generateShareImage(goodsNm,goodsPrice,url,goodsImgUrl,outputStream);
            outputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }

    }
    protected void setResponseHeaders(HttpServletResponse response) {
        response.setContentType("image/png");
        response.setHeader("Cache-Control", "no-cache, no-store");
        response.setHeader("Pragma", "no-cache");
    }
}
