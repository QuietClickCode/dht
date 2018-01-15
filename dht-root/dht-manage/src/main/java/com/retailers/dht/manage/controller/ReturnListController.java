package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.entity.ReturnList;
import com.retailers.dht.common.service.ReturnListService;
import com.retailers.dht.manage.base.BaseController;
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
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("goods")
public class ReturnListController extends BaseController {

    @Autowired
    ReturnListService returnListService;

    @RequestMapping("/openReturnList")
    @Menu(parentRes = "sys.manager.goods",resourse = "goods.openReturnList",description = "返现类型管理",sort = 1,label = "返现类型管理")
    public String openReturnList(){
        return "goods/returnList";
    }

    @RequestMapping("editReturnList")
    @Function(label = "编辑返现类型",parentRes = "goods.openReturnList",resourse = "goods.editReturnList",description = "编辑返现类型",sort = 2)
    @ResponseBody
    public BaseResp editReturnList(ReturnList ReturnList){
        boolean flag = returnListService.updateReturnList(ReturnList);
        if(flag){
            return success("修改返现类型["+ReturnList.getRtName()+"]成功");
        }else{
            return errorForSystem("修改返现类型["+ReturnList.getRtName()+"]失败");
        }
    }

    @RequestMapping("/removeReturnList")
    @Function(label="删除返现类型", description = "删除返现类型", resourse = "goods.removeReturnList",sort=3,parentRes="goods.openReturnList")
    @ResponseBody
    public BaseResp removeReturnList(Long rtId){
        boolean flag=returnListService.deleteReturnListByRtId(rtId);
        return success(flag);
    }

    @RequestMapping("/queryReturnListLists")
    @Function(label="返现类型列表", description = "所有返现类型列表", resourse = "goods.queryReturnListLists",sort=1,parentRes="goods.openReturnList")
    @ResponseBody
    public  Map<String,Object> queryReturnListLists(String rtName, PageUtils pageForm){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("rtName",rtName);
        map.put("isDelete",0);
        Pagination<ReturnList> ReturnListPagination = returnListService.queryReturnListList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",ReturnListPagination.getTotalCount());
        gtm.put("rows",ReturnListPagination.getData());
        return gtm;
    }

//    @RequestMapping("/queryReturnListById")
//    @Function(label="返现类型", description = "返现类型", resourse = "goods.queryReturnListById",sort=1,parentRes="goods.openReturnList")
//    @ResponseBody
//    public  Map<String,Object> queryReturnListById(Long gtId){
//        Map<String,Object> map = new HashMap<String,Object>();
//        map.put("ReturnList",returnListService.queryReturnListByGtId(gtId));
//        return  map;
//    }

    @RequestMapping("/addReturnList")
    @Function(label="增加返现类型", description = "增加返现类型", resourse = "goods.addReturnList",parentRes="goods.openReturnList")
    @ResponseBody
    public BaseResp addReturnList(ReturnList ReturnList){
        ReturnList.setIsDelete(0L);
        boolean flag=returnListService.saveReturnList(ReturnList);
        return success(flag);
    }
}
