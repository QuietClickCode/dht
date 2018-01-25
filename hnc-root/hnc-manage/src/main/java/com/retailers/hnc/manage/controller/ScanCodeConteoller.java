package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.ScanCode;
import com.retailers.hnc.common.service.ScanCodeService;
import com.retailers.hnc.common.vo.ScanCodeVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/31.
 */
@Controller
@RequestMapping("/scanCode")
public class ScanCodeConteoller extends BaseController {
    @Autowired
    private ScanCodeService codeService;

    @RequestMapping("/scanCodeMapping")
    @Menu(parentRes = "sys.manager.opening",resourse = "scanCode.scanCodeMapping",description = "添加扫码员",label = "添加扫码员")
    public String scanCodeMapping(){
        return "scan-code";
    }

    @RequestMapping("/queryScanCodeList")
    @Function(label="扫码员集合", description = "扫码员集合", resourse = "scanCode.queryScanCodeList",sort=1,parentRes="scanCode.scanCodeMapping")
    @ResponseBody
    public Map<String,Object> queryScanCodeList(PageUtils pageForm, Long oid){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        map.put("oid",oid);
        Pagination<ScanCodeVo> teamPagination = codeService.queryScanCodeList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/removeScanCode")
    @Function(label = "删除该扫码员",description = "删除该扫码员",resourse = "scanCode.removeScanCode",sort = 3,parentRes = "scanCode.scanCodeMapping")
    @ResponseBody
    public BaseResp removeScanCode(Long scId, HttpServletRequest request){
        Long eid = getCurLoginUserId(request);
        boolean flag = codeService.deleteScanCodeByScId(scId,eid);
        return  success(flag);
    }

    @RequestMapping("/addScanCode")
    @Function(label = "添加扫码员",description = "添加扫码员",resourse = "scanCode.addScanCode",sort = 3,parentRes = "scanCode.scanCodeMapping")
    @ResponseBody
    public BaseResp addScanCode(@RequestBody List<ScanCode> scanCodeList,HttpServletRequest request){
        Long eid = getCurLoginUserId(request);
        boolean flag = codeService.saveScanCode(scanCodeList,eid);
        if(flag)
            return success("添加扫码员成功");
        else
            return errorForSystem("添加扫码员失败");
    }

    @RequestMapping("/removeOpeningEmList")
    @Function(label = "删除所有和该开盘所绑定得扫码员",description = "删除所有和该开盘所绑定得扫码员",resourse = "scanCode.removeOpeningEmList",sort = 3,parentRes = "scanCode.scanCodeMapping")
    @ResponseBody
    public BaseResp removeOpeningEmList(Long oid){
        boolean flag = codeService.deleteOpeningEmployee(oid);
        System.out.println(flag);
        if(flag)
            return success("删除所有和该开盘所绑定得扫码员成功");
        else
            return errorForSystem("删除所有和该开盘所绑定得扫码员失败");
    }
}
