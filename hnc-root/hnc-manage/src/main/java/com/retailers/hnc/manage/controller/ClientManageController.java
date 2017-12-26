package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/26.
 */
@Controller
@RequestMapping("clientManage")
public class ClientManageController extends BaseController {
    @Autowired
    ClientManageService clientManageService;

    @RequestMapping("/clientManageMapping")
    @Menu(parentRes = "sys.manager.client",resourse = "clientManage.clientManageMapping",description = "客户管理",label = "客户管理")
    public String clientManageMapping(){
        return "clientManage/client_manage";
    }

    @RequestMapping("/queryClientList")
    @Function(label="客户集合", description = "客户集合", resourse = "clientManage.queryClientList",sort=1,parentRes="clientManage.clientManageMapping")
    @ResponseBody
    public Map<String,Object> queryTeamList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<ClientManage> teamPagination = clientManageService.queryClientManageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/addClient")
    @Function(label = "添加客户",description = "添加客户",resourse = "clientManage.addClient",sort = 3,parentRes = "clientManage.clientManageMapping")
    @ResponseBody
    public BaseResp addClient(ClientManage clientManage,String tmRegisterTimes){
        if(ObjectUtils.isNotEmpty(tmRegisterTimes)){
            clientManage = addDate(clientManage,tmRegisterTimes);
        }
        boolean flag = clientManageService.saveClientManage(clientManage)!=null;
        if(flag)
            return success("添加客户成功");
        else
            return success("添加客户失败");
    }

    @RequestMapping("/updateClient")
    @Function(label = "修改客户信息",description = "修改客户信息",resourse = "clientManage.updateClient",sort = 3,parentRes = "clientManage.clientManageMapping")
    @ResponseBody
    public BaseResp updateClient(ClientManage clientManage){
        boolean flag = clientManageService.updateClientManage(clientManage);
        if(flag)
            return success("修改客户[" + clientManage.getTmName() + "]成功");
        else
            return errorForSystem("修改客户[" + clientManage.getTmName() + "]失败");
    }

    public ClientManage addDate(ClientManage clientManage, String tmRegisterTimes){
        if (!ObjectUtils.isEmpty(tmRegisterTimes)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date spStarttime = sdf.parse(tmRegisterTimes);
                Date spEndtime = sdf.parse(tmRegisterTimes);
                clientManage.setTmRegisterTime(spStarttime);
                clientManage.setTmRegisterTime(spEndtime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return clientManage;
    }
}
