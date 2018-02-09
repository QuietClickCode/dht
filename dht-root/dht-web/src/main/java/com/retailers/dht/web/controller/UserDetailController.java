package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.UserDetail;
import com.retailers.dht.common.entity.UserDetail;
import com.retailers.dht.common.service.UserDetailService;
import com.retailers.dht.common.service.UserDetailService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("userDetail")
public class UserDetailController extends BaseController {

    @Autowired
    UserDetailService userDetailService;

    @RequestMapping("savaUserDetail")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    @ResponseBody
    public BaseResp queryIsysjq(UserDetail userDetail, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        userDetail.setUid(uid);
        userDetail.setIsDelete(0L);
        boolean flag = userDetailService.saveUserDetail(userDetail);
        if(flag){
            return success("保存成功");
        }else{
            return errorForSystem("保存失败");
        }
    }
}
