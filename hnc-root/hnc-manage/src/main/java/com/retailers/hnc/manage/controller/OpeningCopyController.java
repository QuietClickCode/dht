package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.OpeningCopy;
import com.retailers.hnc.common.service.OpeningCopyService;
import com.retailers.hnc.common.vo.OpeningCopyVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
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
@RequestMapping("openingCopy")
public class OpeningCopyController extends BaseController {
    @Autowired
    OpeningCopyService openingCopyService;

    @RequestMapping("/openingCopyMapping")
    @Menu(parentRes = "sys.manager.log",resourse = "openingCopy.openingCopyMapping",description = "开盘日志",label = "开盘日志")
    public String openingCopyMapping(){
        return "log/openingLog";
    }

    @RequestMapping("/queryopeningCopyList")
    @Function(label="开盘日志集合", description = "开盘日志集合", resourse = "openingCopy.queryopeningCopyList",sort=1,parentRes="openingCopy.openingCopyMapping")
    @ResponseBody
    public Map<String,Object> queryopeningCopyList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        Pagination<OpeningCopyVo> openingCopyPagination = openingCopyService.queryOpeningCopyList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",openingCopyPagination.getTotalCount());
        gtm.put("rows",openingCopyPagination.getData());
        return gtm;
    }


}
