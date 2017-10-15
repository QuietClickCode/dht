package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.GoodsCoupon;
import com.retailers.dht.common.entity.Recharge;
import com.retailers.dht.common.service.RechargeService;
import com.retailers.dht.common.vo.GoodsCouponVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2017/10/15
 */
@Controller
@RequestMapping("recharge")
public class RechargeController extends BaseController {

    @Autowired
    private RechargeService rechargeService;

    @RequestMapping("openRechargePage")
    @Menu(label = "充值管理",description = "充值管理",resourse = "recharge.openRechargePage",parentRes = "sys.manager.customer",sort = 3)
    private String openRechargePage(){
        return "customer/recharge";
    }

    /**
     * 弃值列表
     * @param rName 等级名称
     * @param rcashback 是否返现
     * @param isValid 充值 状态
     * @param pageForm 分页信息
     * @return
     */
    @RequestMapping("queryRechargeLists")
    @Function(label="充值列表", description = "充值列表", resourse = "recharge.queryRechargeLists",parentRes="recharge.openRechargePage",sort=1)
    @ResponseBody
    public Map<String,Object> queryRechargeLists(String rName, Long rcashback, Long isValid, PageUtils pageForm){
        Map<String,Object> params=new HashMap<String, Object>();
        params.put("rName",rName);
        params.put("rcashback",rcashback);
        params.put("isValid",isValid);
        params.put("isDelete",0);
        Pagination<Recharge> pages= rechargeService.queryRechargeList(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }

    /**
     * 添加充值金额
     * @param recharge 充值金额数据
     * @return
     */
    @RequestMapping("addRecharge")
    @Function(label="添加充值金额", description = "添加充值金额", resourse = "recharge.addRecharge",parentRes="recharge.openRechargePage",sort=2)
    @ResponseBody
    public BaseResp addRecharge(Recharge recharge){
//        try{
//            validateParams(goodsCoupon);
//        }catch(Exception e){
//            return errorForParam(e.getMessage());
//        }
        recharge.setIsDelete(SystemConstant.SYS_IS_DELETE_NO);
        boolean flag = rechargeService.saveRecharge(recharge);
        if(flag){
            return success("添加充值金额成功");
        }else{
            return errorForSystem("添加充值金额失败");
        }
    }
}
