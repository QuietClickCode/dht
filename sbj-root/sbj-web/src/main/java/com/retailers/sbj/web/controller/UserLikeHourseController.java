package com.retailers.sbj.web.controller;

import com.retailers.sbj.common.entity.UserLikeHourse;
import com.retailers.sbj.common.service.UserLikeHourseService;
import com.retailers.sbj.web.base.BaseController;
import com.retailers.tools.base.BaseResp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("userLikeHourse")
public class UserLikeHourseController extends BaseController {

    @Autowired
    UserLikeHourseService userLikeHourseService;

    @RequestMapping("saveUserLikeHourse")
    @ResponseBody
    public BaseResp userLikeHourseService(String randStr,Long hid,Long isLike){
        Long cid = getClientIdByOpenId(randStr);
        UserLikeHourse userLikeHourse = new UserLikeHourse();
        userLikeHourse.setHid(hid);
        userLikeHourse.setIsDelete(0L);
        userLikeHourse.setIsLike(isLike);
        userLikeHourse.setUid(cid);
        boolean flag = userLikeHourseService.saveUserLikeHourse(userLikeHourse);
        return success(flag);
    }

}
