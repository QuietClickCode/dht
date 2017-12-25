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

import java.text.SimpleDateFormat;
import java.util.Date;
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
    public BaseResp editOpening(Opening opening,String ostartTimes,String oendTimes,String floors){
        opening.setIsDelete(0L);
        addDate(opening,ostartTimes,oendTimes);
        boolean flag = OpeningService.saveOpening(opening,floors);
        return success(flag);
    }
    @RequestMapping("updateOpening")
    @ResponseBody
    public BaseResp updateOpening(Opening opening,String ostartTimes,String oendTimes,String floors){
        addDate(opening,ostartTimes,oendTimes);
        boolean flag = OpeningService.updateOpening(opening,floors);
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

    @RequestMapping("removeOpening")
    @ResponseBody
    public BaseResp removeOpening(Long oid){
        boolean flag = OpeningService.deleteOpeningByOid(oid);
        return success(flag);
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

    public void addDate(Opening opening,String ostartTimes,String oendTimes){
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date ostartTime = sdf.parse(ostartTimes);
            Date oendTime = sdf.parse(oendTimes);
            opening.setOstartTime(ostartTime);
            opening.setOendTime(oendTime);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
