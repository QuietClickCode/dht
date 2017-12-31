package com.retailers.hnc.web.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.FloorRelationship;
import com.retailers.hnc.common.entity.HouseTypeManage;
import com.retailers.hnc.common.service.HouseTypeManageService;
import com.retailers.hnc.common.vo.HouseTypeManageVo;
import com.retailers.hnc.web.annotation.CheckOpenId;
import com.retailers.hnc.web.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/18.
 */
@Controller
@RequestMapping("houseManage")
public class HouseTypeManageController extends BaseController{
    @Autowired
    HouseTypeManageService houseTypeManageService;

    @RequestMapping("/queryHouseType")
    @CheckOpenId
    @ResponseBody
    public Map<String,Object> queryHouseTypeList(PageUtils pageForm,String randStr){
        Long uid = getClientIdByOpenId(randStr);
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        map.put("uid",uid);
        List<HouseTypeManageVo> list = houseTypeManageService.queryHourseTypeManageVoWithUid(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("rows",list);
        return gtm;
    }

}
