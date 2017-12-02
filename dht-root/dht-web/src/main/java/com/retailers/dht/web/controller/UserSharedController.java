package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.UserShared;
import com.retailers.dht.common.service.UserSharedService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;
import java.util.Date;

/**
 * Created by niconiconi on 2017/10/30.
 */
@Controller
@RequestMapping("userShared")
public class UserSharedController extends BaseController{
    @Autowired
    UserSharedService userSharedService;

    @RequestMapping("/saveUserShared")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY)
    @ResponseBody
    public BaseResp saveUserShare(UserShared userShared, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        Long shareUserId = getShareUserId(request);

        if(shareUserId==uid|| ObjectUtils.isEmpty(shareUserId)){
            return success(true);
        }
        userShared.setIsDelete(0L);
        userShared.setUid(uid);
        userShared.setCreatTime(new Date());
        boolean flag = userSharedService.saveUserShared(userShared);
        return  success(flag);
    }
    
}
