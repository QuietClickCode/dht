<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>订单管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
</head>
<body>
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <input type="text" class="form-control" id="search_orderNo" placeholder="请输入订单号">
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <span>订单状态:</span>
        <input type="checkbox" name="search_orderStatus" value="0"/>未支付
        <input type="checkbox" name="search_orderStatus" value="1"/>支付中
        <input type="checkbox" name="search_orderStatus" value="2"/>支付失败
        <input type="checkbox" name="search_orderStatus" value="3" checked/>待发货
        <input type="checkbox" name="search_orderStatus" value="4"/>己发货
        <input type="checkbox" name="search_orderStatus" value="5"/>确认收货
        <input type="checkbox" name="search_orderStatus" value="6"/>发起退款
        <input type="checkbox" name="search_orderStatus" value="9"/>交易完成
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <span>订单类型:</span>
        <select id="search_orderType" name="search_orderType"  class="form-control" style="width: auto;">
            <option value="">--全部--</option>
            <option value="RECHARGE">充值</option>
            <option value="SHOPPING">购物</option>
            <option value="SECKILL">秒杀</option>
            <option value="SPECIAL_OFFER">特价</option>
            <option value="CUT_PRICE">确价</option>
        </select>
    </div>
    <div class="form-group">
        <span>支付方式:</span>
        <select id="search_orderPayWay" name="search_orderPayWay"  class="form-control" style="width: auto;">
            <option value="">--全部--</option>
            <option value="0">微信</option>
            <option value="1">支付宝</option>
            <option value="2">钱包</option>
        </select>
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <input type="text" class="form-control" id="search_orderBuyNm" placeholder="请输入购买人">
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <input type="text" class="form-control" id="search_orderLogisticsCode" placeholder="请输入快递单号">
    </div>&nbsp;&nbsp;</br>
    <div class="form-group">
        <input type="text" class="form-control" id="search_orderUaName" placeholder="请输入收货人姓名">
    </div>&nbsp;&nbsp;
    <div class="form-group">
        <input type="text" class="form-control" id="search_orderUaPhone" placeholder="请输入收货人电话">
    </div>&nbsp;&nbsp;
    <ex:perm url="order/queryOrderLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
</div>
<div>
    <table id="orderTables" ></table>
</div>
<div class="modal fade" id="sendGoodsDialog" tabindex="-1" role="dialog" aria-labelledby="sendGoodsDialog">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="sendGoodsTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="sendGoodsDialogForm">
                    <input type="hidden" name="orderId" id="orderId">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                快递公司:
                              </span>
                                <select id="logisticsCompany" name="logisticsCompany"  class="form-control" style="width: auto;">
                                    <option value="">--全部--</option>
                                    <option value="ZYFH">自营发货</option>
                                    <option value="">顺丰速运</option>
                                    <option value="HTKY">百世快递</option>
                                    <option value="ZTO">中通快递</option>
                                    <option value="STO">申通快递</option>
                                    <option value="YTO">圆通速递</option>
                                    <option value="YD">韵达速递</option>
                                    <option value="YZPY">邮政快递包裹</option>
                                    <option value="EMS">EMS</option>
                                    <option value="HHTT">天天快递</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  快递单号:
                              </span>
                                <input type="text" class="form-control" id="orderLogisticsCode" name="orderLogisticsCode" placeholder="请输入快递单号"/>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="form-group">
                        <label for="sendRemark" class="control-label">发货备注:</label>
                        <textarea class="form-control" id="sendRemark" name="sendRemark"></textarea>
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
    var sendGoodsDialogType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'orderNo',
            title: '订单号'
        },
        {
            field: 'orderType',
            title: '订单类型',
            formatter:function(value,row,index){
                var html = '购物';
                if(value=='RECHARGE'){
                    html= "充值";
                }else if(value=='SECKILL'){
                    html='秒杀';
                }else if(value=='SPECIAL_OFFER'){
                    html='特价';
                }else if(value=='CUT_PRICE'){
                    html='确价';
                }
                return html;
            }
        },
        {
            field: 'orderBuyNm',
            title: '购买人'
        },
        {
            field: 'orderGoodsTotalPrice',
            title: '订单金额'
        },
        {
            field: 'orderCreateDate',
            title: '创建时间'
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
            field: 'orderStatus',
            title: '状态',
            align : 'center',
            valign : 'middle',
            width:120,
            formatter:function(value,row,index){
                rowDatas.set(row.id,row);
                var html = '未支付';
                if(value==1){
                   html= "支付中";
                }else if(value==2){
                   html='支付失败';
               }else if(value==3){
                   html='待发货';
               }else if(value==4){
                   html='己发货';
               }else if(value==5){
                   html='确认收货';
               }else if(value==6){
                   html='发起退款';
               }else if(value==9){
                   html='交易完成';
               }
               return html;
            }
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:240,
            formatter:function(value,row,index){
                let html='';
                <ex:perm url="order/sendGoods">
                if(row.orderStatus==3){
                    html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();sendGoods(\''+row.id+'\')"">发货</button>&nbsp;';
                }
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createExpandTable("/order/queryOrderLists","orderTables","orgId",treeColumns,queryParams,editorDetail)
        //初始华开关选择器
        $("#sendGoodsDialogForm #isValid").bootstrapSwitch();
        $('#sendGoodsDialog').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("sendGoodsDialogForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#sendGoodsDialogForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#sendGoodsDialogForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);
            var sendData=new Array();
            var formData=$("#sendGoodsDialogForm").serializeObject();
            //取得form表单数据
            $.ajax({
                type:"post",
                url:"/order/sendGoods",
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
                        $('#sendGoodsDialog').modal('hide')
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
        formValidater();
    });
    /**
     * form 校验
     * */
    function formValidater(){
        $('#sendGoodsDialogForm')
            .bootstrapValidator({
                message: 'This value is not valid',
                //live: 'submitted',
                feedbackIcons: {
                    valid: 'glyphicon glyphicon-ok',
                    invalid: 'glyphicon glyphicon-remove',
                    validating: 'glyphicon glyphicon-refresh'
                },
                fields: {
                    logisticsCompany: {
                        message: '快递公司',
                        validators: {
                            notEmpty: {
                                message: '快递公司不能为空'
                            }
                        }
                    },
                    orderLogisticsCode: {
                        message: '快递单号不能为空',
                        validators: {
                            notEmpty: {
                                message: '快递单号不能为空'
                            },
                            stringLength: {
                                min: 5,
                                max: 30,
                                message: '快递单号长度在5-30之间'
                            }
                        }
                    }
                }
            });
    }
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
    function editorDetail(index, row) {
        let html='</br><table style="width:100%;" cellpadding="1" cellspacing="0" border="1"><tbody><tr>';
        html+='<td>商品图标</td><td>商品名称</td><td>商品价格</td><td>销售价格</td><td>购买数量</td>';
        html+='</tr></tbody>'
        for(var info of row.ods){
            html+='<tr><td><img src="'+info.imgUrl+'" alt="" style="width:64px;height:48px;"></td><td>'+info.gName+'</td><td>'+info.gdPrice+'</td><td>'+info.odMenberPrice+'</td><td>'+info.odBuyNumber+'</td></tr>'
        }
        html+='</table></br>';
        if(row.orderType!='RECHARGE'){
            html+='<span>收货人:</span><span>'+row.orderUaName+'</span>&nbsp;&nbsp;<br>';
            html+='<span>收货电话:</span><span>'+row.orderUaPhone+'</span>&nbsp;&nbsp;<br>';
            html+='<span>收货人地址:</span><span>'+row.orderUaAddress+'</span>&nbsp;&nbsp;<br>';
            if(row.orderLogisticsCode){
                html+='<span>快递单号:</span><span>'+row.orderLogisticsCode+'</span>&nbsp;&nbsp;<br>';
            }
        }
        return html;
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#orderTables').bootstrapTable(
                "refresh",
                {
                    url:"/order/queryOrderLists"
                }
        );
    }
    /**
     * 订单发货
     **/
    function sendGoods(orderId){
        sendGoodsDialogType=1;
        initFormData(orderId);
        $("#sendGoodsTitle").text("商品发货");
        $('#sendGoodsDialog').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#sendGoodsDialogForm #orderId").val("");
        $("#sendGoodsDialogForm #logisticsCompany").val("");
        $("#sendGoodsDialogForm #orderLogisticsCode").val("");
        $("#sendGoodsDialogForm #sendRemark").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#sendGoodsDialogForm #orderId").val(rowData.id);
        }
    }
</script>
</body>
</html>
