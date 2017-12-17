package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.GoodsGdcprel;
import com.retailers.dht.common.entity.UserAddress;
import com.retailers.dht.common.service.GoodsGdcprelService;
import com.retailers.dht.common.vo.GoodsGdcprelVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {

    @Autowired
    GoodsGdcprelService goodsGdcprelService;

    @RequestMapping("checkOrder")
    public String checkOrder(HttpServletRequest request){
        return redirectUrl(request,"order/create-order");
    }

    /** isActivity: 0特价 1秒杀 2砍价 3正常*/
    @RequestMapping("getCheckOrderData")
    public String getCheckOrderData(HttpServletRequest request,String data){
        data += "\'isActivity\':3}";
        request.getSession().setAttribute("checkOrderData",data);
        return "redirect:/order/checkOrder";
    }
    @RequestMapping("getCheckOrderDataBySeckill")
    public String getCheckOrderDataBySeckill(HttpServletRequest request,String data){
        data += "\'isActivity\':1}";
        request.getSession().setAttribute("checkOrderData",data);
        return "redirect:/order/checkOrder";
    }
    @RequestMapping("getCheckOrderDataBySpecial")
    public String getCheckOrderDataBySpecial(HttpServletRequest request,String data){
        data += "\'isActivity\':0}";
        request.getSession().setAttribute("checkOrderData",data);
        return "redirect:/order/checkOrder";
    }

    @RequestMapping("getCheckOrderDataByCutPrice")
    public String getCheckOrderDataByCutPrice(HttpServletRequest request,String gname,String imgurl,String remark,Long gdcpId,Float gdprice){
        if(ObjectUtils.isNotEmpty(gdcpId)){
            Long uid = getCurLoginUserId(request);
            GoodsGdcprelVo goodsGdcprelVo = goodsGdcprelService.queryCheckOrderData(gdcpId,uid);
            if(ObjectUtils.isNotEmpty(goodsGdcprelVo)){
                String gsName = goodsGdcprelVo.getGsName();
                Long gdId = goodsGdcprelVo.getGdId();
                Long sumcount = goodsGdcprelVo.getSumcount();
                String data = "{";
                data += "\'gdId\':\'"+gdId+"\',";
                data += "\'num\':\'"+sumcount+"\',";
                data += "\'imgurl\':\'"+imgurl+"\',";
                data += "\'gsvals\':\'"+gsName+"\',";
                data += "\'remark\':\'"+remark+"\',";
                data += "\'gname\':\'"+gname+"\',";
                data += "\'gdprice\':\'"+gdprice+"\'";
                data += '}';
                data = "["+data+"]";
                data = "{data:"+data+",isActivity:2}";
                request.getSession().setAttribute("checkOrderData",data);
            }
        }
        return "redirect:/order/checkOrder";
    }

    @RequestMapping("choseAddress")
    public String choseAddress(HttpServletRequest request){
        return redirectUrl(request,"order/user-address");
    }

    @RequestMapping("getAddressData")
    public String choseAddress(HttpServletRequest request, UserAddress userAddress){
        request.getSession().setAttribute("checkOrderAddress",userAddress);
        return "redirect:/order/checkOrder";
    }
}
