package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.CashOrderService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.NumberUtils;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @author zhongp
 * @version 1.0.1
 * @data 2018/2/28
 */
@Controller
@RequestMapping("cashMoney")
public class CashOrderController extends BaseController {
    Logger logger= LoggerFactory.getLogger(CashOrderController.class);

    @Autowired
    private CashOrderService cashOrderService;

    /**
     * 用户提现
     * @param request
     * @param money 提现金额
     * @param remark 备注
     * @return
     */
    @RequestMapping("userCashMoney")
    @CheckSession(key = SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    @ResponseBody
    public BaseResp userCashMoney(HttpServletRequest request,Double money,String remark){
        if(ObjectUtils.isEmpty(money)){
            return errorForParam("提现金额不能为空");
        }
        long cashMoney= NumberUtils.priceChangeFen(NumberUtils.formaterNumber(money,2));
        long uid=getCurLoginUserId(request);
        try{
            Map<String,Object> rtn= cashOrderService.userCashMoney(uid,cashMoney,remark);
            return success(rtn);
        }catch(AppException e){
            logger.info(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }catch(Exception e){
            logger.info(StringUtils.getErrorInfoFromException(e));
            return errorForSystem(e.getMessage());
        }
    }
}
