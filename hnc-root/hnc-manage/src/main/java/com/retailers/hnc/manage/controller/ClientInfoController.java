package com.retailers.hnc.manage.controller;

import com.alibaba.fastjson.JSON;
import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.hnc.common.entity.ClientManage;
import com.retailers.hnc.common.service.ClientManageService;
import com.retailers.hnc.common.vo.CheckUserVo;
import com.retailers.hnc.common.vo.ClientManageVo;
import com.retailers.hnc.manage.base.BaseController;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.utils.ObjectUtils;
import com.retailers.tools.utils.PageUtils;
import org.apache.poi.hssf.usermodel.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by niconiconi on 2017/12/26.
 */
@Controller
@RequestMapping("clientInfo")
public class ClientInfoController extends BaseController {
    @Autowired
    ClientManageService clientManageService;

    @RequestMapping("/clientInfoMapping")
    @Menu(parentRes = "sys.manager.client",resourse = "clientInfo.clientInfoMapping",description = "客户信息",label = "客户信息")
    public String clientManageMapping(){
        return "clientManage/client";
    }

    @RequestMapping("/EmployeeRelationMapping")
    @Menu(parentRes = "sys.manager.client",resourse = "clientInfo.EmployeeRelationMapping",description = "查看绑定客户",label = "查看绑定客户")
    public String EmployeeRelationMapping(){
        return "clientManage/clientRelationship";
    }

    @RequestMapping("/queryClientList")
    @Function(label="客户集合", description = "客户集合", resourse = "clientInfo.queryClientList",sort=1,parentRes="clientInfo.clientInfoMapping")
    @ResponseBody
    public Map<String,Object> queryTeamList(PageUtils pageForm,Long emId,String registerTimes,String tmName){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        map.put("tmEmployee",emId);
        map.put("tmName",tmName);
        map.put("tmRegisterTime",dateFormat(registerTimes));
        System.out.println(registerTimes);
        System.out.println(dateFormat(registerTimes));
        Pagination<ClientManageVo> teamPagination = clientManageService.queryClientManageList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("/queryClientListVo")
    @ResponseBody
    public Map<String,Object> queryClientListVo(PageUtils pageForm,Long emId,String registerTimes,String tmName){
        Map<String,Object> map = new HashMap<String,Object>();
        map.put("isDelete",0);
        map.put("tmEmployee",emId);
        map.put("tmName",tmName);
        map.put("tmRegisterTime",dateFormat(registerTimes));
        System.out.println(registerTimes);
        System.out.println(dateFormat(registerTimes));
        Pagination<ClientManageVo> teamPagination = clientManageService.queryClientManageVoList(map,pageForm.getPageNo(),pageForm.getPageSize());
        Map<String,Object> gtm = new HashMap<String,Object>();
        gtm.put("total",teamPagination.getTotalCount());
        gtm.put("rows",teamPagination.getData());
        return gtm;
    }

    @RequestMapping("outExcel")
    public void outExcel(PageUtils pageForm,Long emId,String registerTimes,String tmName, HttpServletResponse response) throws Exception{
        String fileName = "PerformanceTable.xls";// 文件名
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename="
                + fileName);

        List<ClientManageVo> list = (List<ClientManageVo>) queryClientListVo(pageForm,emId, registerTimes, tmName).get("rows");
        System.out.println(JSON.toJSONString(queryClientListVo(pageForm,emId, registerTimes, tmName)));
        if(ObjectUtils.isEmpty(list)){
            return;
        }
        // 第一步，创建一个webbook，对应一个Excel文件
        HSSFWorkbook wb = new HSSFWorkbook();
        // 第二步，在webbook中添加一个sheet,对应Excel文件中的sheet
        HSSFSheet sheet = wb.createSheet("sheet1");
        // 第三步，在sheet中添加表头第0行,注意老版本poi对Excel的行数列数有限制short
        HSSFRow row = sheet.createRow((int) 0);
        // 第四步，创建单元格，并设置值表头 设置表头居中
        HSSFCellStyle style = wb.createCellStyle();
        style.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 创建一个居中格式

        HSSFCell cell = row.createCell((short) 0);
        cell.setCellValue("姓名");
        cell.setCellStyle(style);
        cell = row.createCell((short) 1);
        cell.setCellValue("性别");
        cell.setCellStyle(style);
        cell = row.createCell((short) 2);
        cell.setCellValue("置业顾问");
        cell.setCellStyle(style);
        cell = row.createCell((short) 3);
        cell.setCellValue("年龄");
        cell.setCellStyle(style);
        cell = row.createCell((short) 4);
        cell.setCellValue("购房状态");
        cell.setCellStyle(style);
        cell = row.createCell((short) 5);
        cell.setCellValue("电话");
        cell.setCellStyle(style);
        cell = row.createCell((short) 6);
        cell.setCellValue("登记时间");
        cell.setCellStyle(style);
        cell = row.createCell((short) 7);
        cell.setCellValue("来访渠道");
        cell.setCellStyle(style);
        cell = row.createCell((short) 8);
        cell.setCellValue("客户备注");
        cell.setCellStyle(style);


        // 第五步，写入实体数据 实际应用中这些数据从数据库得到，

        for (int i = 0; i < list.size(); i++)
        {
            row = sheet.createRow((int) i + 1);
            ClientManageVo clientManageVo = (ClientManageVo) list.get(i);
            // 第四步，创建单元格，并设置值
            row.createCell((short) 0).setCellValue(clientManageVo.getTmName());
            Integer sex = clientManageVo.getTmSex();
//            System.out.println("sex:"+sex);
            if(ObjectUtils.isEmpty(sex)){
                row.createCell((short) 1).setCellValue("未知");
            }else{
                if(sex==1){
                    row.createCell((short) 1).setCellValue("男");
                }else{
                    row.createCell((short) 1).setCellValue("女");
                }
            }

            row.createCell((short) 2).setCellValue( clientManageVo.getEmpName());
            row.createCell((short) 3).setCellValue( clientManageVo.getTmAge());
            Integer buyStatus = clientManageVo.getTmStatus();
            if(ObjectUtils.isEmpty(buyStatus)){
                row.createCell((short) 4).setCellValue( "未知");
            }else{
                if(buyStatus==1){
                    row.createCell((short) 4).setCellValue( "已购房");
                }else{
                    row.createCell((short) 4).setCellValue( "未购房");
                }
            }


            row.createCell((short) 5).setCellValue(clientManageVo.getTmPhone());
            row.createCell((short) 6).setCellValue(   new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(clientManageVo.getTmRegisterTime()));
            row.createCell((short) 7).setCellValue( clientManageVo.getChennelName());
            row.createCell((short) 8).setCellValue( clientManageVo.getTmInfo());
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


    @RequestMapping("/addClient")
    @Function(label = "添加客户",description = "添加客户",resourse = "clientInfo.addClient",sort = 3,parentRes = "clientInfo.clientInfoMapping")
    @ResponseBody
    public BaseResp addClient(ClientManage clientManage,String tmRegisterTimes){
        if(ObjectUtils.isNotEmpty(tmRegisterTimes)){
            clientManage = addDate(clientManage,tmRegisterTimes);
        }
        boolean flag = clientManageService.saveClientManage(clientManage)!=null;
        if(flag)
            return success("添加客户成功");
        else
            return success("添加客户失败");
    }

    @RequestMapping("/updateClient")
    @Function(label = "修改客户信息",description = "修改客户信息",resourse = "clientInfo.updateClient",sort = 3,parentRes = "clientInfo.clientInfoMapping")
    @ResponseBody
    public BaseResp updateClient(ClientManage clientManage){
        boolean flag = clientManageService.updateClientManage(clientManage);
        if(flag)
            return success("修改客户[" + clientManage.getTmName() + "]成功");
        else
            return errorForSystem("修改客户[" + clientManage.getTmName() + "]失败");
    }

    @RequestMapping("/queryClientCount")
    @Function(label = "查询客户登记总数",description = "查询客户登记总数",resourse = "clientInfo.queryClientCount",sort = 3,parentRes = "clientInfo.clientInfoMapping")
    @ResponseBody
    public HashMap<String,Integer> queryClientCount(){
        HashMap<String,Integer> clientManage = new HashMap<String,Integer>();
        clientManage.put("CurClientCount",clientManageService.queryCurClientCount());
        clientManage.put("ClientCount",clientManageService.queryClientCount());
        return clientManage;
    }

    public ClientManage addDate(ClientManage clientManage, String tmRegisterTimes){
        if (!ObjectUtils.isEmpty(tmRegisterTimes)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date spStarttime = sdf.parse(tmRegisterTimes);
                Date spEndtime = sdf.parse(tmRegisterTimes);
                clientManage.setTmRegisterTime(spStarttime);
                clientManage.setTmRegisterTime(spEndtime);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return clientManage;
    }

    public Date dateFormat(String tmRegisterTimes){
        Date registerTime = null;
        if (!ObjectUtils.isEmpty(tmRegisterTimes)) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                registerTime = sdf.parse(tmRegisterTimes);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return registerTime;
    }
}
