package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.ScanCodeCopy;
import com.retailers.hnc.common.service.ScanCodeCopyService;
import com.retailers.hnc.common.vo.ScanCodeCopyVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/22.
 */
@Controller
@RequestMapping("scanCodeCopy")
public class ScanCodeCopyController extends BaseController {
    @Autowired
    ScanCodeCopyService scanCodeCopyService;

    @RequestMapping("/scanCodeCopyMapping")
    @Menu(parentRes = "sys.manager.log",resourse = "scanCodeCopy.scanCodeCopyMapping",description = "扫码员日志",label = "扫码员日志")
    public String scanCodeCopyMapping(){
        return "log/scanCodeCopy";
    }

    @RequestMapping("/queryscanCodeCopyList")
    @Function(label="扫码员日志集合", description = "扫码员日志集合", resourse = "scanCodeCopy.queryscanCodeCopyList",sort=1,parentRes="scanCodeCopy.scanCodeCopyMapping")
    @ResponseBody
    public Map<String,Object> queryscanCodeCopyList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        Pagination<ScanCodeCopyVo> scanCodeCopyPagination = scanCodeCopyService.queryScanCodeCopyList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",scanCodeCopyPagination.getTotalCount());
        gtm.put("rows",scanCodeCopyPagination.getData());
        return gtm;
    }


}
