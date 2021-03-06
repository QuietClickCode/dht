package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.CouponService;
import com.retailers.dht.common.vo.BuyGoodsDetailVo;
import com.retailers.dht.common.vo.CouponWebVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/11/15
 */
@Controller
@RequestMapping("coupon")
public class CouponController extends BaseController{
    @Autowired
    private CouponService couponService;

    /**
     *
     * @param request
     * @return
     */
    @RequestMapping("openCoupon")
    public String openCoupon(HttpServletRequest request){
        return redirectUrl(request,"coupon/coupon");
    }

    /**
     * 优惠卷列表
     * @param request
     * @return
     */
    @RequestMapping("couponList")
    @ResponseBody
    public BaseResp couponList(HttpServletRequest request,PageUtils pageForm){
        Long uid=getCurLoginUserId(request);
        try{
           List<CouponWebVo> lists= couponService.queryCouponList(uid,pageForm.getPageNo(),pageForm.getPageSize());
            return success(lists);
        }catch(AppException e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }
    }

    /**
     * 取得用户的优惠卷
     * @param request
     * @param pageForm 分页信息
     * @param type 查询类型（0 用户未使用的优惠卷，1 己使用，2 己过期）
     * @return
     */
    @RequestMapping("queryUserCoupon")
    @ResponseBody
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    public Map<String,Object> queryUserCoupon(HttpServletRequest request, PageUtils pageForm, long type){
        long uid=getCurLoginUserId(request);
        Pagination<CouponWebVo> pages=couponService.queryUserCoupon(uid,type,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }

    /**
     * 抢夺优惠卷
     * @param request
     * @param cpId 优惠卷id
     * @return
     */
    @RequestMapping("userGrabCoupon")
    @CheckSession(key=SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    @ResponseBody
    public BaseResp userGrabCoupon(HttpServletRequest request,long cpId){
        long userId=getCurLoginUserId(request);
        try{
            couponService.userGrabCoupon(userId,cpId);
            return success("获取成功");
        }catch (AppException e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }
    }


//    @RequestMapping(value="/webAsyncTask")
//    public @ResponseBody WebAsyncTask longTimeTask(){
//        System.out.println("/longtimetask被调用 thread id is : " + Thread.currentThread().getId());
//        Callable<ModelAndView> callable = new Callable<ModelAndView>() {
//            public ModelAndView call() throws Exception {
//                Thread.sleep(3000); //假设是一些长时间任务
//                ModelAndView mav = new ModelAndView("longtimetask");
//                mav.addObject("result", "执行成功");
//                System.out.println("执行成功 thread id is : " + Thread.currentThread().getId());
//                return mav;
//            }
//        };
//        return new WebAsyncTask(callable);
//    }

    @RequestMapping("/webAsyncTask")
    public @ResponseBody Callable<String> responseBody(){
        Callable<String> asyncTask = new Callable<String>() {
            public String call() throws Exception {
                Thread.sleep(4000);
                return "现程返回";
            }
        };
        System.out.println("已交给服务线程处理");
        return asyncTask;
    }

    /**
     * 根据商品id取得支持的优惠卷列表
     * @param request
     * @param gid
     * @return
     */
    @RequestMapping("queryCouponByGid")
    @CheckSession(key =SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    public @ResponseBody BaseResp queryCouponByGid(HttpServletRequest request, String gid){
        //取得当前用户
        Long curUid=getCurLoginUserId(request);
        return null;
    }

    /**
     * 根据购买属性取得用户优惠卷例表
     * @param request
     * @param bgs  商品详情
     * @return
     */
    @RequestMapping("queryCouponListsByBuy")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    @ResponseBody
    public BaseResp queryCouponListsByBuy(HttpServletRequest request, @RequestBody List<BuyGoodsDetailVo> bgs){
        Long uid = getCurLoginUserId(request);
        List<CouponWebVo> list=couponService.queryCouponListsByBuy(uid,bgs);
        return success(list);
    }
}
