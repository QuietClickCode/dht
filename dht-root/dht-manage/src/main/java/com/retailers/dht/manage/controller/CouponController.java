package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.constant.CouponUseRangeConstant;
import com.retailers.dht.common.entity.Coupon;
import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.common.vo.CouponShowVo;
import com.retailers.dht.common.vo.CouponVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.PageUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 优惠卷管理
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/11
 */
@Controller
@RequestMapping("coupon")
public class CouponController extends BaseController{
    Logger logger= LoggerFactory.getLogger(CouponController.class);
    @Autowired
    private CouponService couponService;

    @RequestMapping("openCouponPage")
    @Menu(label = "优惠卷管理",resourse = "coupon.openCouponPage",parentRes = "sys.manager.promotion",sort = 1)
    public String openCouponPage(){
        return "promotion/coupon";
    }

    /**
     * 取得卡卷数据
     * @param isValid 卡卷状态
     * @param sendWay 发送方式
     * @param coupouType 卡卷类型
     * @param name 卡卷名称
     * @return
     */
    @RequestMapping("queryCoupons")
    @Function(label = "查询",resourse = "coupon.queryCoupons",parentRes ="coupon.openCouponPage",sort = 0)
    @ResponseBody
    public Map<String,Object> queryCoupons(Long isValid, Long sendWay, Long coupouType, String name,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("isValid",isValid);
        params.put("cpSendWay",sendWay);
        params.put("cpCoinType",coupouType);
        params.put("type", CouponUseRangeConstant.TYPE_COUPON);
        params.put("cpName",name);
        params.put("isDelete",0);
        Pagination<CouponShowVo> pages= couponService.queryCouponList(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }


    /**
     * 添加商品优惠
     * @param goodsCoupon 商品优惠数据
     * @return
     */
    @RequestMapping("addCoupon")
    @Function(label="添加优惠卷", description = "添加优惠卷", resourse = "coupon.addCoupon",sort=2,parentRes="coupon.openCouponPage")
    @ResponseBody
    public BaseResp addCoupon(HttpServletRequest request,CouponVo goodsCoupon){
        try{
            validateParams(goodsCoupon);
        }catch(Exception e){
            return errorForParam(e.getMessage());
        }
        try{
            boolean flag = couponService.saveCoupon(goodsCoupon,getCurLoginUserId(request));
            if(flag){
                return success("优惠卷添加成功");
            }else{
                return success("优惠卷添加失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }
    }
    /**
     * 修改商品优惠
     * @param goodsCoupon 商品优惠数据
     * @return
     */
    @RequestMapping("editorCoupon")
    @Function(label="编辑优惠卷", description = "编辑优惠卷", resourse = "coupon.editorCoupon",sort=3,parentRes="coupon.openCouponPage")
    @ResponseBody
    public BaseResp editorCoupon(HttpServletRequest request,CouponVo goodsCoupon){
        try{
            validateParams(goodsCoupon);
        }catch(Exception e){
            return errorForParam(e.getMessage());
        }
        try{
            boolean flag = couponService.updateCoupon(goodsCoupon,getCurLoginUserId(request));
            if(flag){
                return success("编辑优惠成功");
            }else{
                return success("编辑优惠失败");
            }
        }catch (Exception e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 校验传入参数 是否合法
     * @param couponVo
     * @throws AppException
     */
    private void validateParams(CouponVo couponVo)throws AppException{

    }
}
