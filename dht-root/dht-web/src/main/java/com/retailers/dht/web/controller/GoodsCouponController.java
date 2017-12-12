package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.constant.CouponConstant;
import com.retailers.dht.common.constant.CouponUseRangeConstant;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.common.vo.GoodsCouponShowVo;
import com.retailers.dht.common.vo.GoodsCouponVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
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

}
