package com.retailers.dht.web.controller;

import com.retailers.dht.common.entity.BuyCar;
import com.retailers.dht.common.service.BuyCarService;
import com.retailers.dht.common.service.GoodsGdsprelService;
import com.retailers.dht.common.vo.GoodsGdsprelVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("buyCar")
public class BuyCarController extends BaseController{
    @Autowired
    BuyCarService buyCarService;

    @RequestMapping("/saveBuyCar")
    @ResponseBody
    public BaseResp saveBuyCar(BuyCar buyCar, HttpServletRequest request){
        buyCar.setIsDelete(0L);
        buyCar.setBcTimmer(new Date());
        buyCar.setUid(getCurLoginUserId(request));
        boolean flag = buyCarService.saveBuyCar(buyCar);
        return  success(flag);
    }
    
}
