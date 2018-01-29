package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.service.OrderRefundService;
import com.retailers.dht.common.vo.OrderRefundVo;
import com.retailers.dht.common.vo.OrderVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
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
}
