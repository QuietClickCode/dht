<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>退款管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
</head>
<body>
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <span>退款状态:</span>
        <input type="checkbox" name="search_status" value="0"/>待审核
        <input type="checkbox" name="search_status" value="1"/>审核失败
        <input type="checkbox" name="search_status" value="2"/>审核成功
        <input type="checkbox" name="search_status" value="3" />发起退款
        <input type="checkbox" name="search_status" value="4"/>退款成功
        <input type="checkbox" name="search_status" value="5"/>退款失败
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <span>申请人ID:</span>
        <input type="text" class="form-control" id="search_buyId" placeholder="请输入购买人ID">
    </div>
    <div class="form-group">
        <span>申请人姓名:</span>
        <input type="text" class="form-control" id="search_buyNm" placeholder="请输入购买人姓名">
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <span>购物单号:</span>
        <input type="text" class="form-control" id="search_orderNo" placeholder="请输入购物单号">
    </div>&nbsp;&nbsp;
    <ex:perm url="refund/queryRefundList">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>&nbsp;&nbsp;
    </ex:perm>
</div>
<div>
    <table id="orderTables" ></table>
</div>
<div class="modal fade" id="orderRefundDialog" tabindex="-1" role="dialog" aria-labelledby="orderRefundDialog">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="orderRefundTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="orderRefundDialogForm">
                    <input type="hidden" name="orId" id="rdId">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  订单号:
                              </span>
                                <input type="text" class="form-control" readonly id="orderNo"/>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                申请人：
                              </span>
                                <input type="text" class="form-control" readonly id="buyName"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  退款金额:
                              </span>
                                <input type="text" class="form-control" readonly id="refundPrice"/>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                审核状态：
                              </span>
                                <select id="rdStatus" name="status"  class="form-control" style="width: auto;">
                                    <option value="2">同意</option>
                                    <option value="1">不同意</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="rdRemark" class="control-label">理由:</label>
                        <textarea class="form-control" id="rdRemark" name="remark"></textarea>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" id="editSubmit">确认</button>
            </div>
        </div>
    </div>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var orderRefundDialogType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'rdOrderNo',
            title: '退款订单号'
        },
        {
            field: 'orderBuyUid',
            title: '申请人ID'
        },
        {
            field: 'buyName',
            title: '申请人姓名'
        },
        {
            field: 'rdStatus',
            title: '退款状态',
            formatter:function(value,row,index){
                var html = '待审核';
                if(value==1){
                    html= "审核失败";
                }else if(value==2){
                    html='审核成功';
                }else if(value==3){
                    html='发起退款';
                }else if(value==4){
                    html='退款成功';
                }else if(value==5){
                    html='退款失败';
                }
                return html;
            }
        },
        {
            field: 'rdPriceStr',
            title: '退款金额'
        },
        {
            field: 'rdCreateDate',
            title: '创建时间'
        },
        {
            field: 'rdRemark',
            title: '退款备注'
        },
        {
            field: 'uName',
            title: '审核人'
        },
        {
            field: 'rdAuditingDate',
            title: '审核时间'
        },
        {
            field: 'rdAuditingRemark',
            title: '审核备注'
        },
        {
            field: 'rdSendDate',
            title: '退款时间'
        },

        {
            field: 'orderPayCallbackDate',
            title: '支付时间'
        },
        {
            field: 'orderPayWay',
            title: '支付方式',
            formatter:function(value,row,index){
                var html = '微信';
                if(value==1){
                    html= "支付宝";
                }else if(value==2){
                    html='钱包';
                }
                return html;
            }
        },
        {
            field: 'orderUaName',
            title: '收货人'
        },
        {
            field: 'orderUaPhone',
            title: '收货人电话'
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:240,
            formatter:function(value,row,index){
                rowDatas.set(row.rdId,row);
                let html='';
                <ex:perm url="refund/auditingOrderRefund">
                if(row.rdStatus==0){
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();auditingOrderRefund(\''+row.rdId+'\')"">退款申请审核</button>&nbsp;';
                }
                </ex:perm>
                <ex:perm url="refund/orderRefund">
                if(row.rdStatus==2){
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();orderRefund(\''+row.rdId+'\')"">退款</button>&nbsp;';
                }
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createExpandTable("/refund/queryRefundList","orderTables","orgId",treeColumns,queryParams,editorDetail)
        //初始华开关选择器
        $("#orderRefundDialogForm #isValid").bootstrapSwitch();
        $('#orderRefundDialog').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            var editSubmitIndex = layer.load(2);
            var formData=$("#orderRefundDialogForm").serializeObject();
            //取得form表单数据
            $.ajax({
                type:"post",
                url:"/refund/auditingOrderRefund",
                dataType: "json",
                data:formData,
                success:function(data){
                    layer.close(editSubmitIndex);
                    if(data.status==0){
                        //显示提示
                        layer.msg(data.msg);
                        //刷新数据
                        refreshTableData();
                        //关闭弹窗
                        $('#orderRefundDialog').modal('hide')
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
    });

    /**
     * 查询条件
     **/
    function queryParams(that){
        var selectOrderStatus="";
        $.each($('input[name="search_orderStatus"]:checked'),function(){
            selectOrderStatus+=$(this).val()+",";
        });
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            orderNo: $("#search_orderNo").val(),
            orderType: $("#search_orderType").val(),
            orderStatus: selectOrderStatus,
            orderPayWay: $("#search_orderPayWay").val(),
            orderBuyNm: $("#search_orderBuyNm").val(),
            orderLogisticsCode: $("#search_orderLogisticsCode").val(),
            orderUaName: $("#search_orderUaName").val(),
            orderUaPhone: $("#search_orderUaPhone").val()
        };
    }
//    var logisNm=new Map();
//    logisNm.set("ZYFH","自营发货");
//    logisNm.set("SF","顺丰速运");
//    logisNm.set("HTKY","百世快递");
//    logisNm.set("ZTO","中通快递");
//    logisNm.set("STO","申通快递");
//    logisNm.set("YTO","圆通速递");
//    logisNm.set("YD","韵达速递");
//    logisNm.set("YZPY","邮政快递包裹");
//    logisNm.set("EMS","EMS");
//    logisNm.set("HHTT","天天快递");

    function editorDetail(index, row) {
//        let html='</br><table style="width:100%;" cellpadding="1" cellspacing="0" border="1"><tbody><tr>';
//        html+='<td>商品图标</td><td>商品名称</td><td>商品价格</td><td>销售价格</td><td>商品规格</td><td>购买数量</td>';
//        html+='</tr></tbody>'
//        for(var info of row.ods){
//            html+='<tr><td><img src="'+info.imgUrl+'" alt="" style="width:64px;height:48px;"></td><td>'+info.gName+'</td><td>'+info.gdPrice+'</td><td>'+info.odMenberPrice+'</td><td>'+info.gsName+'</td><td>'+info.odBuyNumber+'</td></tr>'
//        }
//        html+='</table></br>';
//        if(row.orderType!='RECHARGE'){
//            if(row.orderLogisticsCode){
//                html+='<span>物流公司:</span><span>'+logisNm.get(row.orderLogisticsCompany)+'</span>&nbsp;&nbsp;<br>';
//                html+='<span>物流单号:</span><span>'+row.orderLogisticsCode+'</span>&nbsp;&nbsp;<br>';
//            }
//            html+='<span>收货人:</span><span>'+row.orderUaName+'</span>&nbsp;&nbsp;<br>';
//            html+='<span>收货电话:</span><span>'+row.orderUaPhone+'</span>&nbsp;&nbsp;<br>';
//            html+='<span>收货人地址:</span><span>'+row.orderUaAddress+'</span>&nbsp;&nbsp;<br>';
//            if(row.orderLogisticsCode){
//                html+='<span>快递单号:</span><span>'+row.orderLogisticsCode+'</span>&nbsp;&nbsp;<br>';
//            }
//        }
//        return html;
        return "";
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#orderTables').bootstrapTable(
                "refresh",
                {
                    url:"/refund/queryRefundList"
                }
        );
    }
    /**
     * 退款申请审核
     **/
    function auditingOrderRefund(orId){
        orderRefundDialogType=1;
        initFormData(orId);
        $("#orderRefundTitle").text("退款审核");
        $('#orderRefundDialog').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#orderRefundDialogForm #rdId").val("");
        $("#orderRefundDialogForm #orderNo").val("");
        $("#orderRefundDialogForm #buyName").val("");
        $("#orderRefundDialogForm #refundPrice").val("");
        $("#orderRefundDialogForm #rdRemark").val("");
    }
    /**
     * 初始化表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#orderRefundDialogForm #rdId").val(rowData.rdId);
            $("#orderRefundDialogForm #orderNo").val(rowData.rdOrderNo);
            $("#orderRefundDialogForm #buyName").val(rowData.buyName);
            $("#orderRefundDialogForm #refundPrice").val(rowData.rdPrice);
        }
    }

    /**
     * 退款操作
     * @param rdId
     */
    function orderRefund(rdId){
        var orderRefundIndex = layer.load(2);
        //取得form表单数据
        $.ajax({
            type:"post",
            url:"/refund/orderRefund",
            dataType: "json",
            data:{"orId":rdId},
            success:function(data){
                layer.close(orderRefundIndex);
                if(data.status==0){
                    layer.msg(data.msg);
                    refreshTableData();
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }
</script>
</body>
</html>
