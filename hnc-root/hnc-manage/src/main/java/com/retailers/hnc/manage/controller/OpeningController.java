package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.service.OpeningService;
import com.retailers.hnc.common.vo.OpeningVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
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

    @RequestMapping("/openOpening")
    @Menu(parentRes = "sys.manager.opening",resourse = "Opening.openOpening",description = "开盘管理",sort = 1,label = "开盘管理")
    public String openOpening(){
        return "opening";
    }

    @RequestMapping("saveOpening")
    @ResponseBody
    public BaseResp editOpening(Opening opening){
        boolean flag = OpeningService.saveOpening(opening);
        return success(flag);
    }
    @RequestMapping("updateOpening")
    @ResponseBody
    public BaseResp updateOpening(Opening opening){
        boolean flag = OpeningService.updateOpening(opening);
        return success(flag);
    }
    @RequestMapping("queryOpeningList")
    @ResponseBody
    public Map<String,Object> queryOpening(String oname,int pageNo,int pageSize){
        Map params = new HashMap();
        params.put("oname",oname);
        params.put("isDelete",0L);
        Pagination<OpeningVo> pagination = OpeningService.queryOpeningVoList(params,pageNo,pageSize);
        return queryPages(pagination);
    }

    @RequestMapping("queryOFrel")
    @ResponseBody
    public Map<String,Object> queryOFrel(Long oid){
        Map params = new HashMap();
        params.put("oname",oid);
        params.put("isDelete",0L);
        Pagination<Opening> pagination = null;
        return queryPages(pagination);
    }
}
