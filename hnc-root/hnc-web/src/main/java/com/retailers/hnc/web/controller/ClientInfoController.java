package com.retailers.hnc.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.vo.ClientManageVo;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.base.BaseController;
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
@RequestMapping("clientInfo")
public class ClientInfoController extends BaseController {
    @Autowired
    ClientManageService clientManageService;

    @RequestMapping("/queryClientList")
    @ResponseBody
    public Map<String,Object> queryTeamList(PageUtils pageForm,Long emId,String registerTimes,Long tmLoginStatus){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        if(ObjectUtils.isNotEmpty(emId)){
            map.put("tmEmployee",emId);
        }
        map.put("tmLoginStatus",tmLoginStatus);
        if(ObjectUtils.isNotEmpty(registerTimes)){
            map.put("tmRegisterTime",registerTimes);
        }
        System.out.println(registerTimes);
        Pagination<ClientManageVo> teamPagination = clientManageService.queryClientManageListWeb(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/queryClientListByPhone")
    @ResponseBody
    public Map<String,Object> queryClientListByPhone(PageUtils pageForm,String phone,String registerTimes,Long tmLoginStatus){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Long emId = getEmpIdByWxPhone(phone);
        map.put("tmEmployee",emId);
        map.put("tmLoginStatus",tmLoginStatus);
        if(ObjectUtils.isNotEmpty(registerTimes)){
            map.put("tmRegisterTime",registerTimes);
        }
        System.out.println(registerTimes);
        Pagination<ClientManageVo> teamPagination = clientManageService.queryClientManageListWeb(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/addClient")
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

    @RequestMapping("/queryClientByCmId")
    @ResponseBody
    public Map queryClientByCmId(Long cmId){
        ClientManage clientManage = clientManageService.queryClientManageVoByTmId(cmId);
        Map map = new HashMap();
        map.put("row",clientManage);
        return map;
    }


    @RequestMapping("/updateClient")
    @ResponseBody
    public BaseResp updateClient(ClientManage clientManage){
        boolean flag = clientManageService.updateClientManage(clientManage);
        if(flag)
            return success("修改客户[" + clientManage.getTmName() + "]成功");
        else
            return errorForSystem("修改客户[" + clientManage.getTmName() + "]失败");
    }

    @RequestMapping("/updateClientManage")
    @ResponseBody
    public BaseResp updateClientManage(String tmInfo,Integer tmStatus,Long tmChannel,Long cmId){
        ClientManage clientManage = clientManageService.queryClientManageByTmId(cmId);
        clientManage.setTmInfo(tmInfo);
        clientManage.setTmStatus(tmStatus);
        clientManage.setTmChannel(tmChannel);
        boolean flag = clientManageService.updateClientManage(clientManage);
        if(flag)
            return success("修改客户[" + clientManage.getTmName() + "]成功");
        else
            return errorForSystem("修改客户[" + clientManage.getTmName() + "]失败");
    }

    @RequestMapping("/queryClientCount")
    @ResponseBody
    public HashMap<String,Integer> queryClientCount(){
        HashMap<String,Integer> clientManage = new HashMap<String,Integer>();
        clientManage.put("CurClientCount",clientManageService.queryCurClientCount());
        clientManage.put("ClientCount",clientManageService.queryClientCount());
        return clientManage;
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

    public Date dateFormat(String tmRegisterTimes){
        Date registerTime = null;
        if (!ObjectUtils.isEmpty(tmRegisterTimes)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                registerTime = sdf.parse(tmRegisterTimes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return registerTime;
    }
}
