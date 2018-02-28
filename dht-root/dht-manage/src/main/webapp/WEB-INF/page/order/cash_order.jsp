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
        <span>提现状态:</span>
        <input type="checkbox" name="search_cashStatus" value="0"/>待审核
        <input type="checkbox" name="search_cashStatus" value="1"/>审核失败
        <input type="checkbox" name="search_cashStatus" value="2"/>待下划
        <input type="checkbox" name="search_cashStatus" value="3" />提现成功
        <input type="checkbox" name="search_cashStatus" value="4"/>提现失败
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <span>提现人ID:</span>
        <input type="text" class="form-control" id="search_cashUid" placeholder="请输入提现人ID">
    </div>
    <div class="form-group">
        <span>提现人姓名:</span>
        <input type="text" class="form-control" id="search_cashNm" placeholder="请输入提现人姓名">
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <span>提现单号:</span>
        <input type="text" class="form-control" id="search_cashNo" placeholder="请输入提现单号">
    </div>&nbsp;&nbsp;
    <ex:perm url="cashOrder/queryCashOrderLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>&nbsp;&nbsp;
    </ex:perm>
</div>
<div>
    <table id="orderTables" ></table>
</div>
<div class="modal fade" id="cashOrderDialog" tabindex="-1" role="dialog" aria-labelledby="cashOrderDialog">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="cashOrderTitile"></h4>
            </div>
            <div class="modal-body">
                <form id="cashOrderDialogForm">
                    <input type="hidden" name="coId" id="coId">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  订单号:
                              </span>
                                <input type="text" class="form-control" readonly id="cashNo"/>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                申请人：
                              </span>
                                <input type="text" class="form-control" readonly id="cashNm"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  提现金额:
                              </span>
                                <input type="text" class="form-control" readonly id="cashMoney"/>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                审核状态：
                              </span>
                                <select id="coStatus" name="coStatus"  class="form-control" style="width: auto;">
                                    <option value="2">同意</option>
                                    <option value="1">不同意</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="remark" class="control-label">理由:</label>
                        <textarea class="form-control" id="remark" name="remark"></textarea>
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
    var cashOrderDialogType=0;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'coNo',
            title: '提现单号'
        },
        {
            field: 'coUid',
            title: '提现用户ID'
        },
        {
            field: 'userName',
            title: '提现用户姓名'
        },
        {
            field: 'coStatus',
            title: '提现状态',
            formatter:function(value,row,index){
                var html = '待审核';
                if(value==1){
                    html= "审核失败";
                }else if(value==2){
                    html='待下划';
                }else if(value==3){
                    html='提现成功';
                }else if(value==4){
                    html='提现失败';
                }
                return html;
            }
        },
        {
            field: 'cashMoney',
            title: '提现金额'
        },
        {
            field: 'coCreateTime',
            title: '创建时间'
        },
        {
            field: 'coRemark',
            title: '提现备注'
        },
        {
            field: 'auditingNm',
            title: '审核人'
        },
        {
            field: 'coAuditingTime',
            title: '审核时间'
        },
        {
            field: 'coAuditingRemark',
            title: '审核备注'
        },
        {
            field: 'cashNm',
            title: '下划人'
        },
        {
            field: 'coReturnTime',
            title: '下划时间'
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:240,
            formatter:function(value,row,index){
                rowDatas.set(row.coId,row);
                let html='';
                <ex:perm url="cashOrder/auditingCashOrder">
                if(row.coStatus==0){
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();auditingCashOrder(\''+row.coId+'\')"">提现申请审核</button>&nbsp;';
                }
                </ex:perm>
                <ex:perm url="cashOrder/cashOrder">
                if(row.coStatus==2||row.coStatus==4){
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();cashOrder(\''+row.coId+'\')"">提现下划</button>&nbsp;';
                }
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/cashOrder/queryCashOrderLists","orderTables","coId",treeColumns,queryParams)
        //初始华开关选择器
        $("#cashOrderDialogForm #isValid").bootstrapSwitch();
        $('#cashOrderDialog').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            var editSubmitIndex = layer.load(2);
            var formData=$("#cashOrderDialogForm").serializeObject();
            //取得form表单数据
            $.ajax({
                type:"post",
                url:"/cashOrder/auditingCashOrder",
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
                        $('#cashOrderDialog').modal('hide')
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
        var searchCashStatus="";
        $.each($('input[name="search_cashStatus"]:checked'),function(){
            searchCashStatus+=$(this).val()+",";
        });
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            cashNo: $("#search_cashNo").val(),
            cashStatus: searchCashStatus,
            cashUid: $("#search_cashUid").val(),
            cashNm: $("#search_cashNm").val()
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#orderTables').bootstrapTable(
                "refresh",
                {
                    url:"/cashOrder/queryCashOrderLists"
                }
        );
    }
    /**
     * 退款申请审核
     **/
    function auditingCashOrder(orId){
        cashOrderDialogType=1;
        initFormData(orId);
        $("#cashOrderTitile").text("提现审核");
        $('#cashOrderDialog').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#cashOrderDialogForm #coId").val("");
        $("#cashOrderDialogForm #cashNo").val("");
        $("#cashOrderDialogForm #cashNm").val("");
        $("#cashOrderDialogForm #cashMoney").val("");
        $("#cashOrderDialogForm #remark").val("");
    }
    /**
     * 初始化表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#cashOrderDialogForm #coId").val(rowData.coId);
            $("#cashOrderDialogForm #cashNo").val(rowData.coNo);
            $("#cashOrderDialogForm #cashNm").val(rowData.userName);
            $("#cashOrderDialogForm #cashMoney").val(rowData.cashMoney);
        }
    }

    /**
     * 退款操作
     * @param rdId
     */
    function cashOrder(ocId){
        layer.confirm('是否向选择的数据打款？', {
            btn: ['确定','取消'] //按钮
        }, function(){
            var cashOrderIndex = layer.load(2);
            //取得form表单数据
            $.ajax({
                type:"post",
                url:"/cashOrder/cashOrder",
                dataType: "json",
                data:{"ocId":ocId},
                success:function(data){
                    layer.close(cashOrderIndex);
                    if(data.status==0){
                        layer.msg(data.msg);
                        refreshTableData();
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        }, function(){

        });
    }
</script>
</body>
</html>
