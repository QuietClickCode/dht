package com.retailers.dht.manage.controller;

import com.retailers.auth.annotation.Function;
import com.retailers.auth.annotation.Menu;
import com.retailers.dht.common.constant.OrderConstant;
import com.retailers.dht.common.service.OrderService;
import com.retailers.dht.common.vo.OrderDetailVo;
import com.retailers.dht.common.vo.OrderVo;
import com.retailers.dht.common.vo.UserVo;
import com.retailers.dht.manage.base.BaseController;
import com.retailers.mybatis.common.constant.SysParameterConfigConstant;
import com.retailers.mybatis.common.entity.SysParameterConfig;
import com.retailers.mybatis.common.service.SysParameterConfigService;
import com.retailers.mybatis.pagination.Pagination;
import com.retailers.tools.base.BaseResp;
import com.retailers.tools.exception.AppException;
import com.retailers.tools.utils.*;
import com.retailers.tools.utils.DateUtil;
import org.apache.poi.hssf.record.chart.SheetPropertiesRecord;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.streaming.SXSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;
import java.util.*;

/**
 * 订单管理
 * @author zhongp
 * @version 1.0.1
 */
@Controller
@RequestMapping("order")
public class OrderController extends BaseController {
    Logger logger = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    /**
     * 打开订单列表
     * @return
     */
    @RequestMapping("openOrderPage")
    @Menu(label = "订单列表",description = "订单列表",resourse = "order.openOrderPage",parentRes = "sys.manager.orderManage",sort = 1)
    public String openOrderPage(){
        return "order/order";
    }

    /**
     * 取得订单例表
     * @param request
     * @param orderNo 订单号
     * @param orderType 订单类型
     * @param orderStatus 订单状态
     * @param orderPayWay 支付方式
     * @param orderBuyNm 购买人
     * @param orderBuyUid 购买人id
     * @param orderLogisticsCode 快递单号
     * @param orderUaName 收货人姓名
     * @param orderUaPhone 收货人电话
     * @param pageForm 分页信息
     * @return
     */
    @RequestMapping("queryOrderLists")
    @Function(label = "查询订单列表",description = "查询订单列表",resourse = "order.queryOrderLists",parentRes = "order.openOrderPage",sort = 1)
    @ResponseBody
    public Map<String,Object> queryOrderLists(HttpServletRequest request,String orderNo,String orderType,String orderStatus,Long orderPayWay,String orderBuyNm,
                                    String orderBuyUid,String orderLogisticsCode,String orderUaName,String orderUaPhone,PageUtils pageForm){
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("orderNo",orderNo);
        if(ObjectUtils.isNotEmpty(orderType)){
            List<String> ots=new ArrayList<String>();
            for(String ot:orderType.split(",")){
                ots.add(ot);
            }
            params.put("orderType",ots);
        }
        if(ObjectUtils.isNotEmpty(orderStatus)){
            List<Long> oss=new ArrayList<Long>();
            for(String os:orderStatus.split(",")){
                if(ObjectUtils.isNotEmpty(os)){
                    oss.add(Long.parseLong(os));
                }
            }
            params.put("orderStatus",oss);
        }
        params.put("orderNo",orderNo);
        params.put("orderPayWay",orderPayWay);
        params.put("orderBuyNm",orderBuyNm);
        params.put("orderLogisticsCode",orderLogisticsCode);
        params.put("orderUaName",orderUaName);
        params.put("orderUaPhone",orderUaPhone);
        Pagination<OrderVo> pages = orderService.queryOrderLists(params,pageForm.getPageNo(),pageForm.getPageSize());
        return queryPages(pages);
    }

    /**
     * 商品发货
     * @param request
     * @param orderId 订单id
     * @param orderLogisticsCode 快递单号
     * @return
     */
    @RequestMapping("sendGoods")
    @Function(label = "商品发货",description = "商品发货",resourse = "order.sendGoods",parentRes = "order.openOrderPage",sort =2)
    @ResponseBody
    public BaseResp sendGoods(HttpServletRequest request,Long orderId,String logisticsCompany,String orderLogisticsCode,String sendRemark){
        long sysUid=getCurLoginUserId(request);
        if(ObjectUtils.isEmpty(logisticsCompany)){
            return errorForParam("请选择物流公司");
        }
        if(ObjectUtils.isEmpty(orderLogisticsCode)){
            return errorForParam("请输入快递单号");
        }
        try{
            orderService.sendGoods(sysUid,orderId,logisticsCompany,orderLogisticsCode,sendRemark);
        }catch (AppException e){
            e.printStackTrace();
            return errorForSystem(e.getMessage());
        }catch (Exception e){
            return errorForSystem(e.getMessage());
        }
        return success(true);
    }

    /**
     * 打开订单设置
     * @return
     */
    @RequestMapping("openOrderSettingPage")
    @Menu(label = "订单基础设置",description = "订单基础设置",resourse = "order.openOrderSettingPage",parentRes = "sys.manager.orderManage",sort = 2)
    public ModelAndView openOrderSettingPage(){
        ModelAndView modelAndView=new ModelAndView();
        modelAndView.addObject("params",SysParameterConfigConstant.params);
        modelAndView.setViewName("order/order_setting");
        return modelAndView;
    }

    /**
     * 订单设置
     * @param request
     * @param orderExpireDate
     * @param orderConfirmDate
     * @param orderCompleteDate
     * @param defaultLogisPrice
     * @return
     */
    @RequestMapping("orderSetting")
    @ResponseBody
    public BaseResp orderSetting(HttpServletRequest request,String orderExpireDate,String orderConfirmDate,String orderCompleteDate,String defaultLogisPrice){
        if(ObjectUtils.isEmpty(orderExpireDate)){
            return errorForParam("订单失效时间不能为空");
        }
        if(ObjectUtils.isEmpty(orderConfirmDate)){
            return errorForParam("自动确认收货时间不能为空");
        }
        if(ObjectUtils.isEmpty(orderCompleteDate)){
            return errorForParam("订单完成时间不能为空");
        }
        if(ObjectUtils.isEmpty(defaultLogisPrice)){
            return errorForParam("平台默认快递费不能为空");
        }
        orderService.orderSetting(orderExpireDate,orderConfirmDate,orderCompleteDate,defaultLogisPrice);

        return success(true);
    }

    @RequestMapping("exportOrderDatas")
    @Function(label = "订单导出",description = "订单导出",resourse = "order.exportOrderDatas",parentRes = "order.openOrderPage",sort =3)
    public void exportOrderDatas(HttpServletResponse response,String orderNo,String orderType,String orderStatus,Long orderPayWay,String orderBuyNm,
                                 String orderLogisticsCode,String orderUaName,String orderUaPhone) throws Exception{
        logger.info("传入用户姓名：[{}]，收货人姓名:[{}]",orderBuyNm,orderUaName);
        if(ObjectUtils.isNotEmpty(orderBuyNm)){
            orderBuyNm= new String(orderBuyNm.getBytes("iso-8859-1"),"utf-8");
        }
        if(ObjectUtils.isNotEmpty(orderUaName)){
            orderUaName= new String(orderUaName.getBytes("iso-8859-1"),"utf-8");
        }
        logger.info("转码后用户姓名：[{}]，收货人姓名:[{}]",orderBuyNm,orderUaName);
        String fileName= StringUtils.formate(DateUtil.dateToString(new Date(),DateUtil.DATE_LONG_SMAIL_FORMAT),".xls");
        response.setContentType("application/x-msdownload");
        response.setHeader("Content-Disposition", "attachment; filename="+ fileName);
        Workbook wb = new SXSSFWorkbook(100); // 关键语句
        Sheet sheet = wb.createSheet("订单数据列表"); // 工作表对象
        //序号	订单编号	订单人姓名	会员名	会员ID	推荐	推荐人ID	订单人电话	收件人地址	订单商品总额	备注	下单时间	付款时间	商品名称	商品数量	商品单价	商品总额	快递单号	快递公司	积分抵扣	优惠券抵扣	核销处理人

        List<String> columnKey=Arrays.asList("id","orderNo","orderStatus","orderUaName","orderUaPhone","orderUaAddress","orderBuyNm","orderBuyUid","totalPrice","orderCreateDate","orderPayCallbackDate","gName","gsName","odBuyNumber","gprice","odMenberPrice","orderLogisticsCode","orderLogisticsNm");
        Map<String,String> title=new HashMap<String, String>();
        title.put("id","序号");
        title.put("orderNo","订单编号");
        title.put("orderStatus","订单状态");
        title.put("orderUaName","订单人姓名");
        title.put("orderUaPhone","订单人电话");
        title.put("orderUaAddress","收件人地址");
        title.put("orderBuyUid","会员ID");
        title.put("orderBuyNm","会员名");
        title.put("totalPrice","订单商品总额");
//        title.put("","备注");
        title.put("orderCreateDate","下单时间");
        title.put("orderPayCallbackDate","付款时间");
        title.put("gName","商品名称");
        title.put("gsName","商品规格");
        title.put("odBuyNumber","商品数量");
        title.put("gprice","商品单价");
        title.put("odMenberPrice","商品总额");
        title.put("orderLogisticsCode","快递单号");
        title.put("orderLogisticsNm","快递公司");

        Map<String,CellStyle> cellStyle=new HashMap<String, CellStyle>();


        CellStyle dbStyle = wb.createCellStyle();
        dbStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0.00"));
        CellStyle intStyle = wb.createCellStyle();
        intStyle.setDataFormat(HSSFDataFormat.getBuiltinFormat("0"));

        cellStyle.put("totalPrice",dbStyle);
        cellStyle.put("odMenberPrice",dbStyle);
        cellStyle.put("gprice",dbStyle);
        cellStyle.put("odBuyNumber",intStyle);
        int curRow=0;
        Row nRow = sheet.createRow(curRow); // 行对象
        curRow++;
        generateExcelRow(columnKey,title,nRow,false,null);
        //生成行数据
        Map<String,Object> params=new HashMap<String,Object>();
        params.put("orderNo",orderNo);
        if(ObjectUtils.isNotEmpty(orderType)){
            List<String> ots=new ArrayList<String>();
            for(String ot:orderType.split(",")){
                ots.add(ot);
            }
            params.put("orderType",ots);
        }
        if(ObjectUtils.isNotEmpty(orderStatus)){
            List<Long> oss=new ArrayList<Long>();
            for(String os:orderStatus.split(",")){
                if(ObjectUtils.isNotEmpty(os)){
                    oss.add(Long.parseLong(os));
                }
            }
            params.put("orderStatus",oss);
        }
        params.put("orderNo",orderNo);
        params.put("orderPayWay",orderPayWay);
        params.put("orderBuyNm",orderBuyNm);
        params.put("orderLogisticsCode",orderLogisticsCode);
        params.put("orderUaName",orderUaName);
        params.put("orderUaPhone",orderUaPhone);
        List<OrderVo> orders = orderService.queryOrderLists(params);
        List<Map<String,String>> datas=new ArrayList<Map<String, String>>();
        Map<String,String> logisNm=new HashMap<String, String>();
        logisNm.put("ZYFH","自营发货");
        logisNm.put("SF","顺丰速运");
        logisNm.put("","顺丰速运");
        logisNm.put("HTKY","百世快递");
        logisNm.put("ZTO","中通快递");
        logisNm.put("STO","申通快递");
        logisNm.put("YTO","圆通速递");
        logisNm.put("YD","韵达速递");
        logisNm.put("YZPY","邮政快递包裹");
        logisNm.put("EMS","EMS");
        logisNm.put("HHTT","天天快递");
        for(OrderVo order:orders){
            int rows=1;
            String orderStstus="";
            switch (order.getOrderStatus()){
                case OrderConstant.ORDER_STATUS_CREATE:
                    orderStstus="未支付";
                    break;
                case OrderConstant.ORDER_STATUS_PAY_FAILE:
                    orderStstus="支付失败";
                    break;
                case OrderConstant.ORDER_STATUS_PAY_SUCCESS:
                    orderStstus="待发货";
                    break;
                case OrderConstant.ORDER_STATUS_PAY_SEND_GOODS:
                    orderStstus="己发货";
                    break;
                case OrderConstant.ORDER_STATUS_PAY_SEND_GOODS_RECEIPT:
                    orderStstus="确认收货";
                    break;
                case 6:
                    orderStstus="起发退款";
                    break;
                case OrderConstant.ORDER_STATUS_PAY_SEND_CANCEL:
                    orderStstus="取消订单";
                    break;
                case OrderConstant.ORDER_STATUS_PAY_EXPIRE_TIME:
                    orderStstus="订单失效";
                    break;
                case OrderConstant.ORDER_STATUS_PAY_END:
                    orderStstus="交易完成";
                    break;
            }

            if(ObjectUtils.isNotEmpty(order.getOds())){
                rows=order.getOds().size();
                for(OrderDetailVo odv:order.getOds()){
                    Map<String,String> row=new HashMap<String, String>();
                    row.put("id",order.getId()+"");
                    row.put("orderNo",order.getOrderNo());
                    row.put("orderStatus",orderStstus);
                    row.put("orderUaName",order.getOrderUaName());
                    row.put("orderUaPhone",order.getOrderUaPhone());
                    row.put("orderUaAddress",order.getOrderUaAddress());
                    row.put("orderBuyUid",order.getOrderBuyUid()+"");
                    row.put("orderBuyNm",order.getOrderBuyNm());
                    long totalPrice= 0l;
                    if(ObjectUtils.isNotEmpty(order.getOrderLogisticsPrice())){
                        totalPrice=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(Double.parseDouble(order.getOrderLogisticsPrice())));
                    }
                    if(ObjectUtils.isNotEmpty(order.getOrderMenberPrice())){
                        totalPrice+=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(Double.parseDouble(order.getOrderMenberPrice())));
                    }

                    row.put("totalPrice",NumberUtils.formaterNumberPower(totalPrice));
                    row.put("","备注");
                    row.put("orderCreateDate",DateUtil.dateToString(order.getOrderCreateDate()));
                    row.put("orderPayCallbackDate","");
                    if(ObjectUtils.isNotEmpty(order.getOrderPayCallbackDate())){
                        row.put("orderPayCallbackDate",DateUtil.dateToString(order.getOrderPayCallbackDate()));
                    }
                    row.put("gName",odv.getgName());
                    row.put("gsName",odv.getGsName());
                    row.put("odBuyNumber",odv.getOdBuyNumber()+"");
                    long gPrice=0l;
                    //取得商品单价
                    if(ObjectUtils.isNotEmpty(odv.getOdMenberPrice())){
                        gPrice=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(Double.parseDouble(odv.getOdMenberPrice())))/odv.getOdBuyNumber();
                    }
                    row.put("gprice",NumberUtils.formaterNumberPower(gPrice));
                    row.put("odMenberPrice",odv.getOdMenberPrice());
                    row.put("orderLogisticsCode",order.getOrderLogisticsCode());
                    row.put("orderLogisticsNm",logisNm.get(order.getOrderLogisticsCompany()));
                    datas.add(row);
                }
            }else{
                Map<String,String> row=new HashMap<String, String>();
                row.put("id",order.getId()+"");
                row.put("orderNo",order.getOrderNo());
                row.put("orderStatus",orderStstus);
                row.put("orderUaName",order.getOrderUaName());
                row.put("orderUaPhone",order.getOrderUaPhone());
                row.put("orderUaAddress",order.getOrderUaAddress());
                row.put("orderBuyUid",order.getOrderBuyUid()+"");
                row.put("orderBuyNm",order.getOrderBuyNm());
                long totalPrice= 0l;
                if(ObjectUtils.isNotEmpty(order.getOrderLogisticsPrice())){
                    totalPrice=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(Double.parseDouble(order.getOrderLogisticsPrice())));
                }
                if(ObjectUtils.isNotEmpty(order.getOrderMenberPrice())){
                    totalPrice+=NumberUtils.priceChangeFen(NumberUtils.formaterNumberr(Double.parseDouble(order.getOrderMenberPrice())));
                }

                row.put("totalPrice",NumberUtils.formaterNumberPower(totalPrice));
                row.put("","备注");
                row.put("orderCreateDate",DateUtil.dateToString(order.getOrderCreateDate()));
                row.put("orderPayCallbackDate","");
                if(ObjectUtils.isNotEmpty(order.getOrderPayCallbackDate())){
                    row.put("orderPayCallbackDate",DateUtil.dateToString(order.getOrderPayCallbackDate()));
                }
                row.put("gName","");
                row.put("gsName","");
                row.put("odBuyNumber","");
                row.put("gprice","");
                row.put("odMenberPrice","");
                row.put("orderLogisticsCode","");
                row.put("orderLogisticsNm","");
                datas.add(row);
            }
        }

        for(Map<String,String> maps:datas){
            nRow = sheet.createRow(curRow); // 行对象
            curRow++;
            generateExcelRow(columnKey,maps,nRow,true,cellStyle);
        }
        // 第六步，将文件存到指定位置
        try
        {
            OutputStream out = response.getOutputStream();
            wb.write(out);
            out.flush();
            out.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 生成报表
     * @param columnKey
     * @param maps
     * @param row
     */
    private void generateExcelRow(List<String> columnKey,Map<String,String> maps,Row row,boolean isFormate,Map<String,CellStyle> style){
        Cell nCell = null; // 列对象
        int curColumn=0;
        Object obj =null;
        // 生成表头
        for(String column:columnKey){
            nCell = row.createCell(curColumn);
            curColumn++;
            if(isFormate){
                if(style.containsKey(column)){
                    if(ObjectUtils.isNotEmpty(maps.get(column))){
                        nCell.setCellValue(NumberUtils.formaterNumber(Double.parseDouble(maps.get(column)),2));
                    }else{
                        nCell.setCellValue(0);
                    }
                    nCell.setCellStyle(style.get(column));
                    continue;
                }
            }
            nCell.setCellValue(maps.get(column));

        }
    }
}
