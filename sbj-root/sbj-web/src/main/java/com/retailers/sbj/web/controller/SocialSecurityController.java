package com.retailers.sbj.web.controller;

import com.retailers.sbj.common.entity.SocialSecurity;
import com.retailers.sbj.common.service.SocialSecurityService;
import com.retailers.sbj.web.base.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * 社保卡绑卡控制器
 *
 * @author boylin
 * @create 2018-03-02 14:56
 **/
@RestController
@RequestMapping("/socialSecurity")
public class SocialSecurityController extends BaseController {
    @Autowired
    private SocialSecurityService socialSecurityService;

    @RequestMapping("/isReserve")
    private Map<String,Object> IsReserve(SocialSecurity socialSecurity){
        Map returnMap = new HashMap();
        returnMap.put("isReserve",socialSecurityService.isReserve(socialSecurity));
        return  returnMap;
    }

}
