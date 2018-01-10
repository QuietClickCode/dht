package com.retailers.dht.web.controller;

import com.retailers.auth.annotation.CheckSession;
import com.retailers.auth.constant.SystemConstant;
import com.retailers.dht.common.service.GoodsIsbuycpService;
import com.retailers.dht.common.vo.GoodsVo;
import com.retailers.dht.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goodsIsbuycp")
public class GoodsIsBuycpController extends BaseController {

    @Autowired
    GoodsIsbuycpService goodsIsbuycpService;

    @RequestMapping("/querygoodsIsbuycp")
    @CheckSession(key= SystemConstant.LOG_USER_SESSION_KEY,msg = SystemConstant.USER_UN_LOGIN_ALERT_MSG)
    @ResponseBody
    public  Map<String,Object> queryGoodsById(Long cpId,HttpServletRequest request){
        Long uid = getCurLoginUserId(request);
        Map params = new HashMap();
        params.put("cpId",cpId);
        params.put("isDelete",0L);
        params.put("uid",uid);
        Pagination<GoodsVo> pagination = goodsIsbuycpService.queryGoodsIsbuycpList(params,1,1);
        Map<String,Object> gtm = new HashMap<String,Object>();
        if(!ObjectUtils.isEmpty(pagination.getData())){
            gtm.put("row",pagination.getData().get(0));
        }
        return gtm;
    }

}
