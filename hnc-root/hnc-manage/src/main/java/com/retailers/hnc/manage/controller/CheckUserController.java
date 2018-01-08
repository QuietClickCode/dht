package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.service.CheckUserService;
import com.retailers.hnc.common.vo.CheckUserVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.tools.utils.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("checkUser")
public class CheckUserController extends BaseController {

    @Autowired
    CheckUserService checkUserService;

    @RequestMapping("/employeeManageMapping")
    @Menu(parentRes = "sys.manager.employee",resourse = "checkUser.checkUserMapping",description = "业绩查询",label = "业绩查询")
    public String employeeManageMapping(){
        return "checkUser";
    }

    @RequestMapping("queryAchievement")
    @ResponseBody
    public Map queryAchievement(Long oid,String empIds,String tids){
        List<Long> emIdList = StringToList(empIds);
        List<Long> tidsList = StringToList(tids);
        Map params = new HashMap();
        if(ObjectUtils.isNotEmpty(tids)){
            params.put("tidList",tidsList);
        }else{
            params.put("emIdList",emIdList);
        }
        params.put("oid",oid);
        Map map = new HashMap();
        List<CheckUserVo> list = checkUserService.queryAchievement(params);

        for(CheckUserVo checkUserVo:list){
            checkUserVo.setTid(-checkUserVo.getTid());
        }

        //加载组
        CheckUserVo checkUserVo = new CheckUserVo();
        List<CheckUserVo> teamList = new ArrayList<CheckUserVo>();
        for(CheckUserVo checkUserVo1:list){
            Long tid = checkUserVo1.getTid();
            String tname = checkUserVo1.getTname();

            checkUserVo.setCuId(tid);
            checkUserVo.setClientName(tname);

            if(ObjectUtils.isNotEmpty(list)){
                boolean flag = true;
                for(CheckUserVo checkUserVo2:teamList){
                    if(checkUserVo2.getCuId()==tid){
                        flag = false;
                        break;
                    }
                }
                if(flag){
                    teamList.add(checkUserVo);
                }
            }else{
                teamList.add(checkUserVo);
        }
        }
        list.addAll(teamList);


        list = queryEmployeeTree(list);

        map.put("rows",list);
        return map;
    }

    //分页相关方法
    public List<CheckUserVo> queryEmployeeTree(List<CheckUserVo> relationshipVos) {
        List<CheckUserVo> rtnList=new ArrayList<CheckUserVo>();
        Map<Long,Map<Long,Long>> child=new HashMap<Long, Map<Long, Long>>();
        queryEmployeeNode(relationshipVos,child);
        Map<Long,Long> alloShow=new HashMap<Long, Long>();
        queryAllEmployee(null,child,alloShow);
        for(CheckUserVo vo:relationshipVos){
            if(ObjectUtils.isEmpty(vo.getTid())){
                vo.setLevel(1l);
                rtnList.add(vo);
            }else{
                if(alloShow.containsKey(vo.getTid())){
                    rtnList.add(vo);
                }
                vo.setLevel(2l);
            }
        }
        return rtnList;
    }

    private void queryEmployeeNode(List<CheckUserVo> list, Map<Long,Map<Long,Long>> child){
        for(CheckUserVo vo:list){
            Long parentId=vo.getTid();
            if(ObjectUtils.isEmpty(parentId)){
                parentId=-1l;
            }
            if(child.containsKey(parentId)){
                child.get(parentId).put(vo.getTid(),vo.getIsDelete());
            }else{
                Map<Long,Long> maps=new HashMap<Long, Long>();
                maps.put(vo.getTid(),vo.getIsDelete());
                child.put(parentId,maps);
            }
        }
    }
    private void queryAllEmployee(Long parentId,Map<Long,Map<Long,Long>> map,Map<Long,Long>allowMap){
        if(ObjectUtils.isEmpty(parentId)){
            parentId=-1l;
        }
        Map<Long,Long> child=map.get(parentId);
        if(ObjectUtils.isNotEmpty(child)&&!child.isEmpty()){
            for(Long key:child.keySet()){
                allowMap.put(key,key);
                if(map.containsKey(key)){
                    queryAllEmployee(key,map,allowMap);
                }
            }
        }
    }

    public List<Long> StringToList(String str){
        String[] strArr = str.split(",");
        if(ObjectUtils.isNotEmpty(strArr)){
            List<Long> list = new ArrayList<Long>();
            for(String strStr:strArr){
                Long l = Long.parseLong(strStr);
                list.add(l);
            }
            return list;
        }
        return null;
    }
}
