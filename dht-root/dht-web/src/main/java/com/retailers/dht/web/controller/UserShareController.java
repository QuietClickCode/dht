package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.BuyCar;
import com.retailers.dht.common.entity.UserShare;
import com.retailers.dht.common.service.BuyCarService;
import com.retailers.dht.common.service.UserShareService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("userShare")
public class UserShareController extends BaseController{
    @Autowired
    UserShareService userShareService;


    @RequestMapping("/saveUserShare")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    @ResponseBody
    public BaseResp saveUserShare(UserShare userShare, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        userShare.setIsDelete(0L);
        userShare.setUid(uid);
        userShare.setCreateTime(new Date());
        boolean flag = userShareService.saveUserShare(userShare);
        return  success(flag);
    }

    
}
