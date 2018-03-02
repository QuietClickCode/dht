package com.retailers.sbj.web.controller;

import com.retailers.sbj.common.entity.Opening;
import com.retailers.sbj.common.service.OpeningService;
import com.retailers.sbj.common.vo.OpeningVo;
import com.retailers.sbj.web.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("opening")
public class OpeningController extends BaseController {

    @Autowired
    OpeningService OpeningService;


    @RequestMapping("queryOpeningList")
    @ResponseBody
    public Map<String,Object> queryOpening(String oname,int pageNo,int pageSize){
        Map params = new HashMap();
        params.put("oname",oname);
        params.put("isDelete",0L);
        List<OpeningVo> list = OpeningService.queryOpeningVoList(params,pageNo,pageSize).getData();
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(list)){
            map.put("rows",list);
        }
        return map;
    }

    @RequestMapping("queryEarlyOpening")
    @ResponseBody
    public Map<String,Object> queryEarlyOpening(){
        Opening opening = OpeningService.queryEarlyOpening();
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(opening)){
            map.put("row",opening);
        }
        return map;
    }

    @RequestMapping("queryLastOpening")
    @ResponseBody
    public Map<String,Object> queryLastOpening(){
        Opening opening = OpeningService.queryLastOpening();
        Map map = new HashMap();
        if(ObjectUtils.isNotEmpty(opening)){
            map.put("row",opening);
        }
        return map;
    }

    @RequestMapping("queryOFrelByOid")
    @ResponseBody
    public Map<String,Object> queryOFrelByOid(Long oid){
        Map params = new HashMap();
        params.put("oid",oid);
        params.put("isDelete",0L);
        List<OpeningVo> list = OpeningService.queryOFrelByOid(oid);
        Map map = new HashMap();
        map.put("ofRelList",list);
        return map;
    }

}
