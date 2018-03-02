package com.retailers.sbj.web.controller;

import com.retailers.sbj.common.service.FloorManageService;
import com.retailers.sbj.common.vo.FloorManageVo;
import com.retailers.sbj.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/15.
 */

@Controller
@RequestMapping("floorManage")
public class FloorManageController extends BaseController{
    @Autowired
    FloorManageService manageService;

    @RequestMapping("/queryFloorList")
    @ResponseBody
    public Map<String,Object> queryFloorManageList(PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        Pagination<FloorManageVo> advertisingPagination = manageService.queryFloorManageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",advertisingPagination.getTotalCount());
        gtm.put("rows",advertisingPagination.getData());
        return gtm;
    }

}
