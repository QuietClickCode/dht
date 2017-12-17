package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.BuyGoodsDetailVo;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * 商品优惠活动
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/9
 */
@Controller
@RequestMapping("goodsCoupon")
public class GoodsCouponController extends BaseController{
    @Autowired
    private GoodsCouponService goodsCouponService;

    /**
     * 根据商品id取得所有的商品优惠
     * @param goodsId
     * @return
     */
    @RequestMapping("queryGoodsCouponByGid")
    @ResponseBody
    public BaseResp queryGoodsCouponByGid(Long goodsId){
        List<GoodsCouponShowVo> list = goodsCouponService.queryGoodsCouponByGid(goodsId);
        return success(list);
    }

    /**
     * 根据商品购买信息取得商品对应的优惠列表，和用户能够使用的优惠卷列表
     * @param request
     * @param bgs  商品详情
     * @return
     */
    @RequestMapping("queryGoodsCouponLists")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp queryGoodsCouponLists(HttpServletRequest request, @RequestBody List<BuyGoodsDetailVo> bgs){
        Long uid = getCurLoginUserId(request);
        Map<String,Object> rtn =goodsCouponService.queryGoodsCouponLists(uid,bgs);
        return success(rtn);
    }
}
