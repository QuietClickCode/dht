package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.constant.CashMoneyConstant;
import com.retailers.dht.common.service.CashOrderService;
import com.retailers.dht.common.vo.CashOrderVo;
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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/2/28
 */
@Controller
@RequestMapping("cashOrder")
public class CashOrderController extends BaseController {
    Logger logger= LoggerFactory.getLogger(CashOrderController.class);

    @Autowired
    private CashOrderService cashOrderService;

    /**
     * 打开订单列表
     * @return
     */
    @RequestMapping("openOrderPage")
    @Menu(label = "提现列表",description = "提现订单列表",resourse = "cashOrder.openCashOrderPage",parentRes = "sys.manager.orderManage",sort = 6)
    public String openCashOrderPage(){
        return "order/cash_order";
    }


    /**
     * 取得提现列表
     * @param request
     * @param cashNo 订单号
     * @param cashStatus 提现状态
     * @param cashUid 提现用户id
     * @param cashNm 提现用户姓名
     * @param
     * @param pageForm
     * @return
     */
    @RequestMapping("queryCashOrderLists")
    @Function(label = "查询提现列表",description = "查询提现列表",resourse = "cashOrder.queryCashOrderLists",parentRes = "cashOrder.openCashOrderPage",sort = 1)
    @ResponseBody
    public Map<String,Object> queryCashOrderLists(HttpServletRequest request,String cashNo,String cashStatus,String cashUid,String cashNm,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("cashNo",cashNo);
        if(ObjectUtils.isNotEmpty(cashStatus)){
            List<String> ots=new ArrayList<String>();
            for(String ot:cashStatus.split(",")){
                ots.add(ot);
            }
            params.put("cashStatus",ots);
        }
        params.put("cashUid",cashUid);
        params.put("cashNm",cashNm);
        Pagination<CashOrderVo> pages = cashOrderService.queryCashOrderLists(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }

    /**
     * 提现审核
     * @param request
     * @param coId 提现订单ID
     * @param coStatus 审核状态
     * @param remark 备注
     * @return
     */
    @RequestMapping("auditingCashOrder")
    @Function(label = "提现审核",description = "提现审核",resourse = "cashOrder.auditingCashOrder",parentRes = "cashOrder.openCashOrderPage",sort =2)
    @ResponseBody
    public BaseResp auditingOrderRefund(HttpServletRequest request,Long coId,Long coStatus,String remark){
        long sysUid=getCurLoginUserId(request);
        try{
            if(ObjectUtils.isEmpty(coStatus)){
                return errorForParam("审核状态不能为空");
            }else{
                if(coStatus.intValue()!= CashMoneyConstant.CASH_AUDITING_STATUS_FAILE&&coStatus.intValue()!=CashMoneyConstant.CASH_AUDITING_STATUS_SUCCESS){
                    return errorForParam("审核状态参数错误");
                }else if(coStatus.intValue()==CashMoneyConstant.CASH_AUDITING_STATUS_FAILE){
                    if(ObjectUtils.isEmpty(remark)){
                        return errorForParam("不同意提现时，请添加拒绝理由");
                    }
                }
            }
            cashOrderService.auditingCashOrder(sysUid,coId,coStatus,remark);
        }catch(AppException e){
            logger.info("提现申请审核失败:\r\n{}", StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }catch(Exception e){
            logger.info("提现申请审核失败:\r\n{}", StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }
        return success(true);
    }

    /**
     * 退款下划
     * @param request
     * @param ocId 提现订单id
     * @return
     */
    @RequestMapping("cashOrder")
    @Function(label = "提现下划",description = "提现下划",resourse = "cashOrder.cashOrder",parentRes = "cashOrder.openCashOrderPage",sort =3)
    @ResponseBody
    public BaseResp cashOrder(HttpServletRequest request,Long ocId){
        long sysUid=getCurLoginUserId(request);
        try{
            String ip=getRemoteAddrIp(request);
            String msg = cashOrderService.playMoneyToUser(ocId,sysUid,ip);
            if(ObjectUtils.isNotEmpty(msg)){
                return errorForSystem(msg);
            }
        }catch (AppException e){
            return errorForSystem(e.getMessage());
        }
        return success(true);
    }
    /**
     * 取得请求的ip地址
     * @param request
     * @return
     */
    private static String getRemoteAddrIp(HttpServletRequest request) {
        String ipFromNginx = request.getHeader("X-Real-IP");
        return ObjectUtils.isEmpty(ipFromNginx) ? request.getRemoteAddr() : ipFromNginx;
    }
}
