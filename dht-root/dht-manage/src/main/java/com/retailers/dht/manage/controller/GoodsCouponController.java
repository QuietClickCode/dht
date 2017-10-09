package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.auth.vo.SysUserVo;
import com.retailers.dht.common.constant.GoodsCouponConstant;
import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.service.GoodsCouponService;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
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
     * 打开商品优惠管理
     * @return
     */
    @RequestMapping("openGoodsCouponPage")
    @Menu(resourse = "goodsCoupon.openGoodsCouponPage",parentRes = "sys.manager.promotion",label = "商品优惠",sort = 4)
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    public String openGoodsCouponPage(){
        return "promotion/goods_coupon";
    }

    /**
     * 取得商品优惠
     * @param gcpName 优惠名称
     * @param gcpType 类型
     * @param isValid 是否有效
     * @param pageForm 分页信息
     * @return
     */
    @RequestMapping("queryGoodsCoupons")
    @Function(label="取得职员列表", description = "取得职员列表", resourse = "goodsCoupon.queryGoodsCoupons",sort=1,parentRes="goodsCoupon.openGoodsCouponPage")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg="未登录，请重新登录")
    @ResponseBody
    public Map<String,Object> queryGoodsCoupons(String gcpName, Long gcpType,Long isValid,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("gcpName",gcpName);
        params.put("gcpType",gcpType);
        params.put("isValid",isValid);
        params.put("isDelete",0);
        Pagination<GoodsCoupon> pages= goodsCouponService.queryGoodsCouponList(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }

    /**
     * 添加商品优惠
     * @param goodsCoupon 商品优惠数据
     * @return
     */
    @RequestMapping("addGoodsCoupon")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg = "未登录，请重新登录")
    @Function(label="添加商品优惠", description = "添加商品优惠", resourse = "goodsCoupon.addGoodsCoupon",sort=2,parentRes="goodsCoupon.openGoodsCouponPage")
    @ResponseBody
    public BaseResp addGoodsCoupon(GoodsCoupon goodsCoupon){
        try{
            validateForm(goodsCoupon);
        }catch (Exception e){
            e.printStackTrace();
            return errorForParam(e.getMessage());
        }
        if(goodsCoupon.getGcpType().intValue()== GoodsCouponConstant.GCP_TYPE_MONEY){
            if(ObjectUtils.isEmpty(goodsCoupon.getGcpMoney())){
                return errorForParam("优惠活动为代金卷时优惠金额不能为空");
            }
        }
        if(goodsCoupon.getGcpType().intValue()== GoodsCouponConstant.GCP_TYPE_DISCOUNT){
            if(ObjectUtils.isEmpty(goodsCoupon.getGcpMoney())){
                return errorForParam("优惠活动为折扣卷时折扣额不能为空");
            }
        }
        boolean flag = goodsCouponService.saveGoodsCoupon(goodsCoupon);
        return success(flag);
    }

    /**
     * 删除商品优惠
     * @param gcpId
     * @return
     */
    @RequestMapping("delGoodsCoupon")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg = "未登录，请重新登录")
    @Function(label="删除商品优惠", description = "删除商品优惠", resourse = "goodsCoupon.delGoodsCoupon",sort=2,parentRes="goodsCoupon.openGoodsCouponPage")
    @ResponseBody
    public BaseResp delGoodsCoupon(Long gcpId){
        if(ObjectUtils.isEmpty(gcpId)){
            return errorForParam("删除商品优惠id不能为空");
        }
        boolean flag = goodsCouponService.deleteGoodsCouponByGcpId(gcpId);
        return success(flag);
    }
}
