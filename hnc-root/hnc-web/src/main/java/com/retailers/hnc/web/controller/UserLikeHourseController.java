package com.retailers.hnc.web.controller;

import com.retailers.hnc.common.entity.UserLikeHourse;
import com.retailers.hnc.common.service.ProjectService;
import com.retailers.hnc.common.service.UserLikeHourseService;
import com.retailers.hnc.common.vo.ProjectVo;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.base.BaseController;
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
@RequestMapping("userLikeHourse")
public class UserLikeHourseController extends BaseController {

    @Autowired
    UserLikeHourseService userLikeHourseService;

    @RequestMapping("saveUserLikeHourse")
    @CheckOpenId
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
