package com.retailers.hnc.manage.controller;

import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.Opening;
import com.retailers.hnc.common.entity.Team;
import com.retailers.hnc.common.service.CheckUserService;
import com.retailers.hnc.common.service.OpeningService;
import com.retailers.hnc.common.service.TeamService;
import com.retailers.hnc.common.vo.CheckUserVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.utils.ObjectUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

/**
 * Created by Administrator on 2017/9/28.
 */
@Controller
@RequestMapping("checkUser")
public class CheckUserController extends BaseController {

    @Autowired
    CheckUserService checkUserService;

    @Autowired
    TeamService teamService;
    @Autowired
    OpeningService openingService;

    @RequestMapping("/employeeManageMapping")
    @Menu(parentRes = "sys.manager.employee",resourse = "checkUser.checkUserMapping",description = "业绩查询",label = "业绩查询")
    public String employeeManageMapping(){
        return "checkUser";
    }

    @RequestMapping("/checkUserTotalMapping")
    @Menu(parentRes = "sys.manager.employee",resourse = "checkUser.checkUserTotalMapping",description = "预约统计",label = "预约统计")
    public String checkUserTotalMapping(){
        return "checkUserTotal";
    }

    @RequestMapping("queryAchievement")
    @ResponseBody
    public Map queryAchievement(Long oid,String empIds,String tids){
        System.out.println("empIds:"+empIds);
        System.out.println("tids:"+tids);
        System.out.println(ObjectUtils.isEmpty(tids));
        List<Long> emIdList = null;
        List<Long> tidsList = null;
        if(!ObjectUtils.isEmpty(empIds))
            emIdList = StringToList(empIds);
        if(!ObjectUtils.isEmpty(tids))
            tidsList = StringToList(tids);
        Map params = new HashMap();
        if(ObjectUtils.isNotEmpty(tids)){
            params.put("tidList",tidsList);
        }else if(ObjectUtils.isNotEmpty(empIds)){
            params.put("emIdList",emIdList);
        }
        params.put("oid",oid);
        Map map = new HashMap();
        List<CheckUserVo> list = checkUserService.queryAchievement(params);
        long id = 30000;
        HashSet<Long> set = new HashSet<Long>();
        for(CheckUserVo checkUserVo:list){
            set.add(checkUserVo.getTid());
            checkUserVo.setTid(checkUserVo.getTid());
            checkUserVo.setTname("");
            checkUserVo.setLevel(2L);
            checkUserVo.setId(id++);
        }
        List<Team> teams = teamService.queryAllTeam();

        if(ObjectUtils.isNotEmpty(emIdList)){
            for (Long aLong:set){
                for (Team team : teams) {
                    if(aLong == team.getTid()){
                        CheckUserVo userVo = new CheckUserVo();
                        userVo.setId(team.getTid());
                        userVo.setTname(team.getTname());
                        userVo.setLevel(1L);
                        setCheckUserCount(userVo,list);
                        list.add(userVo);
                    }
                }
            }
        }else if(ObjectUtils.isNotEmpty(tidsList)){
            for (Long aLong : tidsList) {
                for (Team team : teams) {
                    if(aLong == team.getTid()){
                        CheckUserVo userVo = new CheckUserVo();
                        userVo.setId(team.getTid());
                        userVo.setTname(team.getTname());
                        userVo.setLevel(1L);
                        setCheckUserCount(userVo,list);
                        list.add(userVo);
                    }
                }
            }
        }else if(ObjectUtils.isEmpty(empIds)&&ObjectUtils.isEmpty(tids)){
            for (Team team : teams) {
                CheckUserVo userVo = new CheckUserVo();
                userVo.setId(team.getTid());
                userVo.setTname(team.getTname());
                userVo.setLevel(1L);
                setCheckUserCount(userVo,list);
                list.add(userVo);
            }
        }
        System.out.println(list.size());
        map.put("rows",list);
        return map;
    }

    @RequestMapping("queryCheckUserVoList")
    @ResponseBody
    public Map queryCheckUserVoList(String phone,Long isUse,String tids,String empIds,Long oid,int pageNo,int pageSize){
        Map map = new HashMap();
        if(ObjectUtils.isEmpty(oid)){
            Opening opening = openingService.queryLastOpening();
            if(ObjectUtils.isEmpty(opening)){
                return map;
            }else{
                oid = opening.getOid();
            }
        }
        List<Long> tidsList = null;
        if(ObjectUtils.isNotEmpty(tids)){
            tidsList = StringToList(tids);
        }
        List<Long> empIdsList = null;
        if(ObjectUtils.isNotEmpty(empIds)){
            empIdsList = StringToList(empIds);
        }
        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("isUse",isUse);
        params.put("oid",oid);
        params.put("tids",tidsList);
        params.put("empIds",empIdsList);

        Pagination<CheckUserVo> checkUserVos = checkUserService.queryCheckUserVoList(params,pageNo,pageSize);

        map.put("rows",checkUserVos.getData());
        map.put("total",checkUserVos.getTotalCount());
        return map;
    }

    @RequestMapping("CheckUserOnExcel")
    public void CheckUserOnExcel(Long oid, String tids, Long isUse, HttpServletResponse response) throws Exception{
        String fileName = "PerformanceTable.xls";// 文件名
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename="
                + fileName);
       /* List<Long> emIdList = null;
        List<Long> tidsList = null;
        if(!ObjectUtils.isEmpty(empIds))
            emIdList = StringToList(empIds);
        if(!ObjectUtils.isEmpty(tids))
            tidsList = StringToList(tids);
        Map params = new HashMap();
        if(ObjectUtils.isNotEmpty(tids)){
            params.put("tidList",tidsList);
        }else if(ObjectUtils.isNotEmpty(empIds)){
            params.put("emIdList",emIdList);
        }
        params.put("oid",oid);
        Map map = new HashMap();
        List<CheckUserVo> list = checkUserService.queryAchievement(params);*/

        List<Long> tidsList = null;
        if(ObjectUtils.isNotEmpty(tids)){
            tidsList = StringToList(tids);
        }

        Map params = new HashMap();
        params.put("isDelete",0L);
        params.put("isUse",isUse);
        params.put("oid",oid);
        params.put("tids",tidsList);
        Pagination<CheckUserVo> checkUserVos = checkUserService.queryCheckUserVoList(params,1,10000);
        List<CheckUserVo> list = checkUserVos.getData();

        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("团队名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("客户电话号码");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("所属置业顾问名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("是否到场");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，

        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            CheckUserVo checkUserVo = (CheckUserVo) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(checkUserVo.getTname());
            row.createCell((short) 1).setCellValue(checkUserVo.getClientName());
            row.createCell((short) 2).setCellValue( checkUserVo.getClientPhone());
            row.createCell((short) 3).setCellValue( checkUserVo.getEmpName());
            if(checkUserVo.getIsUse() == 1){
                row.createCell((short) 4).setCellValue("已到场");
            }else{
                row.createCell((short) 4).setCellValue("未到场");
            }

        }
        // 第六步，将文件存到指定位置
        try
        {
//            FileOutputStream fout = new FileOutputStream("E:/students.xls");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
//            fout.close();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @RequestMapping("outExcel")
    public void outExcel(Long oid, String empIds, String tids, HttpServletResponse response) throws Exception{
        String fileName = "PerformanceTable.xls";// 文件名
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename="
                + fileName);
        List<Long> emIdList = null;
        List<Long> tidsList = null;
        if(!ObjectUtils.isEmpty(empIds))
            emIdList = StringToList(empIds);
        if(!ObjectUtils.isEmpty(tids))
            tidsList = StringToList(tids);
        Map params = new HashMap();
        if(ObjectUtils.isNotEmpty(tids)){
            params.put("tidList",tidsList);
        }else if(ObjectUtils.isNotEmpty(empIds)){
            params.put("emIdList",emIdList);
        }
        params.put("oid",oid);
        Map map = new HashMap();
        List<CheckUserVo> list = checkUserService.queryAchievement(params);


        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("团队名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("未到场人数");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("已到场人数");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("总和");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，

        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            CheckUserVo checkUserVo = (CheckUserVo) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(checkUserVo.getTname());
            row.createCell((short) 1).setCellValue(checkUserVo.getEmpName());
            row.createCell((short) 2).setCellValue( checkUserVo.getNotuseNum());
            row.createCell((short) 3).setCellValue( checkUserVo.getUseNum());
            row.createCell((short) 4).setCellValue( checkUserVo.getUseNum()+checkUserVo.getNotuseNum());
        }
        // 第六步，将文件存到指定位置
        try
        {
//            FileOutputStream fout = new FileOutputStream("E:/students.xls");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
//            fout.close();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }

    @RequestMapping("exportExcel")
    public void exportExcl(Long oid, String empIds, String tids, HttpServletResponse response) throws Exception{
        String fileName = "PerformanceTable.xls";// 文件名
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename="
                + fileName);
        List<Long> emIdList = null;
        List<Long> tidsList = null;
        if(!ObjectUtils.isEmpty(empIds))
            emIdList = StringToList(empIds);
        if(!ObjectUtils.isEmpty(tids))
            tidsList = StringToList(tids);
        Map params = new HashMap();
        if(ObjectUtils.isNotEmpty(tids)){
            params.put("tidList",tidsList);
        }else if(ObjectUtils.isNotEmpty(empIds)){
            params.put("emIdList",emIdList);
        }
        params.put("oid",oid);
        Map map = new HashMap();
        List<CheckUserVo> list = checkUserService.queryAchievement(params);


        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("学生表一");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("团队名称");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("未到场人数");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("已到场人数");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("总和");
        cell.setCellStyle(style);

        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，

        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            CheckUserVo checkUserVo = (CheckUserVo) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(checkUserVo.getTname());
            row.createCell((short) 1).setCellValue(checkUserVo.getEmpName());
            row.createCell((short) 2).setCellValue( checkUserVo.getNotuseNum());
            row.createCell((short) 3).setCellValue( checkUserVo.getUseNum());
            row.createCell((short) 4).setCellValue( checkUserVo.getUseNum()+checkUserVo.getNotuseNum());
        }
        // 第六步，将文件存到指定位置
        try
        {
//            FileOutputStream fout = new FileOutputStream("E:/students.xls");
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
//            fout.close();
            out.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
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

    public void setCheckUserCount(CheckUserVo userVo,List<CheckUserVo> userVos){
        Long groupUseTotal = 0L;
        Long groupNoUseTotal = 0L;
        Long groupCount = 0L;
        for (CheckUserVo vo : userVos) {
            if(userVo.getId() == vo.getTid()){
                groupUseTotal += vo.getUseNum();
                groupNoUseTotal += vo.getNotuseNum();
                groupCount += vo.getCount();
            }
        }
        userVo.setNotuseNum(groupNoUseTotal);
        userVo.setUseNum(groupUseTotal);
        userVo.setCount(groupUseTotal + groupNoUseTotal);
    }
}
