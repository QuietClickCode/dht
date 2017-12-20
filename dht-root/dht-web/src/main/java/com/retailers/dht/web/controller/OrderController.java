package com.retailers.dht.web.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.vo.BuyGoodsDetailVo;
import com.retailers.dht.common.vo.BuyInfoVo;
import com.retailers.dht.common.entity.GoodsGdcprel;
import com.retailers.dht.common.entity.UserAddress;
import com.retailers.dht.common.service.GoodsGdcprelService;
import com.retailers.dht.common.vo.GoodsGdcprelVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
@RequestMapping("order")
public class OrderController extends BaseController {
    Logger logger= LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;


    @Autowired
    GoodsGdcprelService goodsGdcprelService;

    @RequestMapping("checkOrder")
    public String checkOrder(HttpServletRequest request){
        return redirectUrl(request,"order/create-order");
    }

    @RequestMapping("orderList")
    public String openOrderList(HttpServletRequest request){
        return redirectUrl(request,"order/all-order");
    }

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
    public String getCheckOrderDataByCutPrice(HttpServletRequest request,String gname,String imgurl,String remark,Long gdcpId,Float gdprice,Long goodsId,Long cspId){
        if(ObjectUtils.isNotEmpty(gdcpId)){
            Long uid = getCurLoginUserId(request);
            GoodsGdcprelVo goodsGdcprelVo = goodsGdcprelService.queryCheckOrderData(gdcpId,uid);
            if(ObjectUtils.isNotEmpty(goodsGdcprelVo)){
                String gsName = goodsGdcprelVo.getGsName();
                Long gdId = goodsGdcprelVo.getGdId();
                Long sumcount = goodsGdcprelVo.getSumcount();

                Map map = new HashMap();
                map.put("gdId",gdId);
                map.put("goodsId",goodsId);
                map.put("num",sumcount);
                map.put("imgurl",imgurl);
                map.put("gsvals",gsName);
                map.put("remark",remark);
                map.put("gname",gname);
                map.put("gdprice",gdprice);
                map.put("cspId",cspId);

                List list = new ArrayList();
                list.add(map);

                Map data = new HashMap();
                data.put("data",list);
                data.put("isActivity",2);

                request.getSession().setAttribute("checkOrderData", JSON.toJSONString(data));
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
    /**
     * 用户购买商品信息
     * @param request
     * @param buyInfo 购买信息
     * @return
     */
    @RequestMapping(value = "/buyGoods",method = RequestMethod.POST)
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp buyGoods(HttpServletRequest request, @RequestBody BuyInfoVo buyInfo){
        long uid=getCurLoginUserId(request);
        try{
            //校验购买信息
            checkBuyInfo(buyInfo);
            Map<String,Object>rtn= orderService.shoppingOrder(uid,buyInfo,getShareUserId(request),getShareGoodsId(request));
            return success(rtn);
        }catch(AppException e){
            logger.error(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }catch(Exception e){
            logger.error(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 购买特价商品
     * @param request
     * @param buyInfo 购买信息
     * @return
     */
    @RequestMapping(value = "buySpecialOfferGoods",method = RequestMethod.POST)
    @ResponseBody
    public BaseResp buySpecialOfferGoods(HttpServletRequest request,@RequestBody BuyInfoVo buyInfo){
        long uid=getCurLoginUserId(request);
        try{
            //校验购买信息
            checkBuyInfo(buyInfo);
            Map<String,Object>rtn= orderService.buySpecialOfferGoods(uid,buyInfo,isInviter(request,buyInfo));
            return success(rtn);
        }catch(AppException e){
            logger.error(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }catch(Exception e){
            logger.error(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }
    }
    /**
     * 购买秒杀商品
     * @param request
     * @param buyInfo 购买信息
     * @return
     */
    @RequestMapping(value = "buySeckillGoods",method = RequestMethod.POST)
    @ResponseBody
    public BaseResp buySeckillGoods(HttpServletRequest request,@RequestBody BuyInfoVo buyInfo){
        long uid=getCurLoginUserId(request);
        try{
            //校验购买信息
            checkBuyInfo(buyInfo);
            Map<String,Object>rtn= orderService.buySeckillGoods(uid,buyInfo,isInviter(request,buyInfo));
            return success(rtn);
        }catch(AppException e){
            logger.error(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }catch(Exception e){
            logger.error(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }
    }
    /**
     * 购买砍价商品(不做分润 无分享用户）
     * @param request
     * @param buyInfo 购买信息
     * @return
     */
        @RequestMapping(value = "buyCutPrice",method = RequestMethod.POST)
    @ResponseBody
    public BaseResp buyCutPrice(HttpServletRequest request,@RequestBody BuyInfoVo buyInfo){
        long uid=getCurLoginUserId(request);
        try{
            //校验购买信息
            checkBuyInfo(buyInfo);
            Map<String,Object>rtn= orderService.buyCutPrice(uid,buyInfo);
            return success(rtn);
        }catch(AppException e){
            logger.error(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }catch(Exception e){
            logger.error(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 校验商品购买信息
     * @param buyInfoVo
     * @throws AppException
     */
    private void checkBuyInfo(BuyInfoVo buyInfoVo)throws AppException{
        if(ObjectUtils.isEmpty(buyInfoVo)){
            throw new AppException("购买信息不能为空");
        }
        //判断是否汪厍收件人地十
        if(ObjectUtils.isEmpty(buyInfoVo.getAddress())){
            throw new AppException("请添加收货地址");
        }
        if(ObjectUtils.isEmpty(buyInfoVo.getBuyGoods())){
            throw new AppException("请添加购买商品");
        }
        List<BuyGoodsDetailVo> bgdvs=buyInfoVo.getBuyGoods();
        StringBuffer error=new StringBuffer();
        int curRow =1;
        for(BuyGoodsDetailVo bgdv:bgdvs){
            boolean isFlag=false;
            if(ObjectUtils.isEmpty(bgdv.getGdId())){
                error.append("第"+curRow+"行商品规格id不能为空,");
                isFlag=true;
            }
            if(ObjectUtils.isEmpty(bgdv.getNum())){
                error.append("第"+curRow+"行商品数量不能为空");
                isFlag=true;
            }else{
                if(bgdv.getNum().intValue()<=0){
                    error.append("第"+curRow+"行，购买商品数量必须大于等于零");
                    isFlag=true;
                }
            }
            if(isFlag){
                error.append("\r\n");
            }
            curRow++;
        }
    }

    /**
     * 购买商品是否为推荐购买
     * @param request
     * @return
     */
    private Long isInviter(HttpServletRequest request,BuyInfoVo buyInfo){
        //取得推荐人
        Long inviterUid=getShareUserId(request);
        //取得推荐商品id
        Long inviterGid=getShareGoodsId(request);
        Long curGid=null;
        if(ObjectUtils.isNotEmpty(buyInfo)){
            for(BuyGoodsDetailVo bgd:buyInfo.getBuyGoods()){
                curGid=bgd.getGoodsId();
            }
        }
        if(ObjectUtils.isNotEmpty(curGid)&&ObjectUtils.isNotEmpty(inviterGid)){
            if(curGid.intValue()==inviterGid.intValue()){
                return inviterUid;
            }
        }
        return null;
    }
}

