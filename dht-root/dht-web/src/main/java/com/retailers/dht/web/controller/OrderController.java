package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.vo.BuyGoodsDetailVo;
import com.retailers.dht.common.vo.BuyInfoVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
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


    @RequestMapping("checkOrder")
    public String checkOrder(HttpServletRequest request){
        return redirectUrl(request,"order/create-order");
    }

    @RequestMapping("getCheckOrderData")
    public String getCheckOrderData(HttpServletRequest request,String data){
        request.getSession().setAttribute("checkOrderData",data);
        return "redirect://order/checkOrder";
    }

    /**
     * 用户购买商品信息
     * @param request
     * @param buyInfo 购买信息
     * @return
     */
    @RequestMapping("/buyGoods")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp buyGoods(HttpServletRequest request, @RequestBody BuyInfoVo buyInfo){
        long uid=getCurLoginUserId(request);
        try{
            //校验购买信息
            checkBuyInfo(buyInfo);
            Map<String,Object>rtn= orderService.shoppingOrder(uid,buyInfo);
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
            if(ObjectUtils.isEmpty(bgdv.getGoodsId())){
                error.append("第"+curRow+"行数据商品ID不能为空,");
                isFlag=true;
            }
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
}
