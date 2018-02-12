package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.entity.FamerUser;
import com.retailers.dht.common.entity.UserDetail;
import com.retailers.dht.common.service.FamerUserService;
import com.retailers.dht.common.service.UserDetailService;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
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
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("famerUser")
public class FamerUserController extends BaseController {

    @Autowired
    FamerUserService famerUserService;
    @Autowired
    UserDetailService userDetailService;

    @RequestMapping("queryCanBuyYsjqGoods")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    @ResponseBody
    public BaseResp queryIsysjq(Long famerid, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        BaseResp baseResp = new BaseResp();
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("uid",uid);
        List<UserDetail> userDetails = userDetailService.queryUserDetailList(params,1,1).getData();
        if(ObjectUtils.isEmpty(userDetails)){
            baseResp.setStatus(4);
            baseResp.setMsg("您的信息未完善");
            return baseResp;
        }

        params.put("fid",famerid);
        List<FamerUser> famerUsers = famerUserService.queryFamerUserList(params,1,1).getData();
        if(ObjectUtils.isEmpty(famerUsers)){
            baseResp.setStatus(5);
            baseResp.setMsg("您还未与该农户结亲");
            return baseResp;
        }

        return success("可以购买");
    }

    @RequestMapping("saveFamerUser")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    @ResponseBody
    public BaseResp saveFamerUser(Long famerid, HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("uid",uid);
        params.put("fid",famerid);
        List<FamerUser> famerUsers = famerUserService.queryFamerUserList(params,1,1).getData();
        if(ObjectUtils.isNotEmpty(famerUsers)){
            return errorForSystem("您已经与该农户结亲");
        }
        FamerUser famerUser = new FamerUser();
        famerUser.setIsDelete(0L);
        famerUser.setUid(uid);
        famerUser.setFid(famerid);
        famerUser.setFuTime(new Date());
        boolean flag = famerUserService.saveFamerUser(famerUser);
        if(flag){
            return success("结亲成功");
        }else{
            return errorForSystem("结亲失败");
        }
    }

    @RequestMapping("queryFamerUserImgUrlList")
    @ResponseBody
    public Map queryFamerUserImgUrlList(Long famerid){
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("fid",famerid);
        Pagination<String> pagination = famerUserService.queryFamerUserImgUrlList(params,1,999);
        List<String> imgurls = pagination.getData();
        Map map = new HashMap();
        map.put("rows",imgurls);
        map.put("total",pagination.getTotalCount());
        return map;
    }
}
