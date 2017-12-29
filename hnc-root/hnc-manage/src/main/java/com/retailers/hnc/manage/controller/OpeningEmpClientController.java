package com.retailers.hnc.manage.controller;

import com.retailers.hnc.common.service.OpeningEmpClientService;
import com.retailers.hnc.common.vo.ClientManageVo;
import com.retailers.hnc.manage.base.BaseController;
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
 * Created by niconiconi on 2017/12/27.
 */
@Controller
@RequestMapping("OpeningEmpClient")
public class OpeningEmpClientController extends BaseController{
    @Autowired
    OpeningEmpClientService openingEmpClientService;

    @RequestMapping("queryNotGivenList")
    @ResponseBody
    public Map<String,Object> queryOpening(Long oid, HttpServletRequest request, int pageNo, int pageSize){
        Long eid = getCurLoginUserId(request);
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(eid)){
            Map params = new HashMap();
            params.put("oid",oid);
            params.put("eid",eid);
            List<ClientManageVo> list = openingEmpClientService.queryNotGivenList(params,pageNo,pageSize);
            map.put("rows",list);
        }
        return map;
    }

}
