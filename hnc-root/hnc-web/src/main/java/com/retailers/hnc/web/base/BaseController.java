package com.retailers.hnc.web.base;


import com.retailers.hnc.common.entity.EmployeeManage;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.service.EmployeeManageService;
import com.retailers.hnc.web.constant.WebSystemConstant;
import com.retailers.tools.base.BaseWrite;
import com.retailers.tools.encrypt.DESUtils;
import com.retailers.tools.encrypt.DesKey;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 公用controller
 */
public class BaseController extends BaseWrite {

    @Autowired
    private ClientManageService clientManageService;
    @Autowired
    private EmployeeManageService employeeManageService;
    /**
     * 设置页面跳转路径
     * @param request
     * @param url 原本指向路径
     * @return
     */
    protected  String redirectUrl(HttpServletRequest request,String url){
        String type=request.getAttribute(WebSystemConstant.WEB_REQ_TYPE).toString();
        if(url.indexOf("/")!=0){
            url="/"+url;
        }
        return type+url;
    }

    protected  Long getClientIdByOpenId(String randStr){
        try{
            randStr = URLDecoder.decode(randStr,"utf-8");
            String openId = DESUtils.decryptDES(randStr, DesKey.WEB_KEY);
            Long uid = clientManageService.queryClientManageIdByOpenid(openId);
            return uid;
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
    protected  Long getEmpIdByWxPhone(String phone){
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("wxPhone",phone);
        List<EmployeeManage> employeeManages = employeeManageService.queryEmployeeManageList(params,1,1).getData();
        if(ObjectUtils.isEmpty(employeeManages)){
            return null;
        }
        Long eid = employeeManages.get(0).getEmId();
        return eid;
    }

}
