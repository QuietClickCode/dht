package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.auth.vo.ZTreeVo;
import com.retailers.dht.common.entity.Famer;
import com.retailers.dht.common.service.FamerService;
import com.retailers.dht.common.vo.FamerVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("famer")
public class FamerController extends BaseController {

    @Autowired
    FamerService famerService;

    @RequestMapping("/openFamer")
    @Menu(parentRes = "sys.manager.ysjq",resourse = "famer.openFamer",description = "农户管理",sort = 1,label = "农户管理")
    public String openFamer(){
        return "ysjq/famer";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, true));   //true:允许输入空值，false:不能为空值
    }

    @RequestMapping("editFamer")
    @Function(label = "编辑农户",parentRes = "famer.openFamer",resourse = "Famer.editFamer",description = "编辑农户",sort = 2)
    @ResponseBody
    public BaseResp editFamer(Famer Famer,Long oldImg, HttpServletRequest request){
        boolean flag = famerService.updateFamer(Famer, oldImg);
        if(flag){
            return success("修改农户["+Famer.getFname()+"]成功");
        }else{
            return errorForSystem("修改农户["+Famer.getFname()+"]失败");
        }
    }

    @RequestMapping("/removeFamer")
    @Function(label="删除农户", description = "删除农户", resourse = "Famer.removeFamer",sort=3,parentRes="famer.openFamer")
    @ResponseBody
    public BaseResp removeFamer(Long fid){
        Famer famer = famerService.queryFamerByFid(fid);
        famer.setIsDelete(1L);
        boolean flag=famerService.updateFamer(famer, famer.getFimg());
        return success(flag);
    }

    @RequestMapping("/queryFamerLists")
    @Function(label="农户列表", description = "所有农户列表", resourse = "Famer.queryFamerLists",sort=1,parentRes="famer.openFamer")
    @ResponseBody
    public  Map<String,Object> queryFamerLists(String fname,Long gclassification,Long gmaindirection,Long isChecked,PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("fname",fname);
        map.put("gclassification",gclassification);
        map.put("gmaindirection",gmaindirection);
        map.put("isChecked",isChecked);
        map.put("isDelete",0);
        Pagination<FamerVo> FamerPagination = famerService.queryFamerList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",FamerPagination.getTotalCount());
        gtm.put("rows",FamerPagination.getData());
        return gtm;
    }

    @RequestMapping("/addFamer")
    @Function(label="增加农户", description = "增加农户", resourse = "Famer.addFamer",parentRes="famer.openFamer")
    @ResponseBody
    public BaseResp addFamer(Famer Famer, HttpServletRequest request){
        Famer.setIsDelete(0L);
        boolean falg=famerService.saveFamer(Famer);
        if (falg){
            return success("新增成功");
        }else {
            return success("新增失败");
        }
    }
}
