package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.constant.OrderRefundConstant;
import com.retailers.dht.common.service.OrderRefundService;
import com.retailers.dht.common.vo.OrderRefundVo;
import com.retailers.dht.common.vo.OrderVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 退款管理
 * @author zhongp
 * @version 1.0.1
 * @data 2018/1/30
 */
@Controller
@RequestMapping("refund")
public class OrderRefundController extends BaseController{
    Logger logger= LoggerFactory.getLogger(OrderRefundController.class);
    @Autowired
    private OrderRefundService orderRefundService;

    /**
     * 打开退款管理
     * @param request
     * @param response
     * @return
     */
    @RequestMapping("openRefundPage")
    @Menu(label = "退款例表",description = "退款例表",resourse = "refund.openRefundPage",parentRes = "sys.manager.orderManage",sort = 3)
    public String openRefundPage(HttpServletRequest request, HttpServletResponse response){
        return "order/refund";
    }

    /**
     * 取得退款例表
     * @param request
     * @param orderNo
     * @param refundStatus
     * @param buyUid
     * @param buyNm
     * @param pageForm
     * @return
     */
    @RequestMapping("queryRefundList")
    @Function(label = "查询退款例表",description = "查询退款例表",resourse = "refund.queryRefundList",parentRes = "refund.openRefundPage",sort = 1)
    @ResponseBody
    public Map<String,Object> queryRefundList(HttpServletRequest request,String orderNo,String refundStatus,String buyUid,String buyNm,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("orderNo",orderNo);
        if(ObjectUtils.isNotEmpty(refundStatus)){
            List<String> ots=new ArrayList<String>();
            for(String ot:refundStatus.split(",")){
                ots.add(ot);
            }
            params.put("refundStatus",ots);
        }
        params.put("buyUid",buyUid);
        params.put("buyNm",buyNm);
        Pagination<OrderRefundVo> pages = orderRefundService.queryOrderRefundList(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }

    /**
     * 退款审核
     * @param request
     * @param orId 退款单id
     * @param status 审核状态
     * @param remark 备注
     * @return
     */
    @RequestMapping("auditingOrderRefund")
    @Function(label = "退款审核",description = "退款审核",resourse = "refund.auditingOrderRefund",parentRes = "refund.openRefundPage",sort =2)
    @ResponseBody
    public BaseResp auditingOrderRefund(HttpServletRequest request,Long orId,Long status,String remark){
        long sysUid=getCurLoginUserId(request);
        try{
            if(ObjectUtils.isEmpty(status)){
                return errorForParam("审核状态不能为空");
            }else{
                if(status.intValue()!= OrderRefundConstant.REFUND_AUDITING_STATUS_FAILE&&status.intValue()!=OrderRefundConstant.REFUND_AUDITING_STATUS_SUCCESS){
                    return errorForParam("审核状态参数错误");
                }else if(status.intValue()==OrderRefundConstant.REFUND_AUDITING_STATUS_FAILE){
                    if(ObjectUtils.isEmpty(remark)){
                        return errorForParam("不同意退款时，请添加拒绝理由");
                    }
                }
            }
            orderRefundService.auditingOrderRefund(sysUid,orId,status,remark);
        }catch(AppException e){
            return errorForSystem(e.getMessage());
        }catch(Exception e){
            logger.info("退款申请审核失败:\r\n{}", StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }
        return success(true);
    }

    /**
     * 退款下划
     * @param request
     * @param orId 退款单id
     * @return
     */
    @RequestMapping("orderRefund")
    @Function(label = "退款",description = "退款",resourse = "refund.orderRefund",parentRes = "refund.openRefundPage",sort =3)
    @ResponseBody
    public BaseResp orderRefund(HttpServletRequest request,Long orId){
        long sysUid=getCurLoginUserId(request);
        try{
            orderRefundService.orderRefund(sysUid,orId);
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
        return success(true);
    }
}
