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
        <input type="checkbox" name="search_orderStatus" value="3"/>待发货
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
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorSysUserForm">
                    <input type="hidden" name="uid" id="uid">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                订单号:
                              </span>
                                <input type="text" class="form-control" name="orderNo" id="orderNo">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  订单状态:
                              </span>
                                <input type="text" class="form-control" id="orderStatus" name="orderStatus"/>
                            </div>
                        </div>
                    </div>
                    <br>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                所属部门:
                              </span>
                                <input type="hidden" id="orgIds" name="orgIds"/>
                                <input type="text" class="form-control" aria-label="..." id="orgNms" name="orgNms"  onclick="showOrgTree(); return false;">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group">
                              <span class="input-group-addon">
                                是否有效:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isValid" name="isValid" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
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
    var editorSysUserType=0;
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
                <ex:perm url="sysUser/editorSysUser">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorOrganization(\''+row.uid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="	sysUser/delSysUser">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.uid+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/order/queryOrderLists","orderTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorSysUserForm #isValid").bootstrapSwitch();
        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorSysUserForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorSysUserForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorSysUserForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorSysUserForm").serializeObject();
            var flag =$("#editorSysUserForm #isValid").bootstrapSwitch("state");
            if(flag){
                formData["isValid"]=0;
            }else{
                formData["isValid"]=1;
            }
            let url="/sysUser/addSysUser";
            if(editorSysUserType==1){
                url="/sysUser/editorSysUser";
            }
            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
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
                        $('#editorSysUser').modal('hide')
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
        $('#editorSysUserForm')
                .bootstrapValidator({
                    message: 'This value is not valid',
                    //live: 'submitted',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        uaccount: {
                            message: '职工账号校验未通过',
                            validators: {
                                notEmpty: {
                                    message: '职工登录账号不能为空'
                                },
                                stringLength: {
                                    min: 1,
                                    max: 30,
                                    message: '职工登录账号长度在4-30之间'
                                }
                            }
                        },
                        uname: {
                            message: '职工姓名校验未通过',
                            validators: {
                                notEmpty: {
                                    message: '职工姓名不能为空'
                                },
                                stringLength: {
                                    min: 2,
                                    max: 10,
                                    message: '职工姓名长度在2-10之间'
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
        console.log();

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
    //删除确认框
    function deleteData(uid){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeSysUser(uid);
        }, function(){
        });
    }
    /**
     * 删除部门
     **/
    function removeSysUser(uid){
        $.ajax({
            type:"post",
            url:'/sysUser/delSysUser',
            dataType: "json",
            data:{uid:uid},
            success:function(data){
                if(data.status==0){
                    layer.msg("删除成功");
                    refreshTableData();
                }else{
                    layer.msg(data.msg);
                }
            }
        });
    }


    var zNodes;
    function editorOrganization(orgId){
        editorSysUserType=1;
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑职工");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorSysUserForm #uid").val("");
        $("#editorSysUserForm #version").val("");
        $("#editorSysUserForm #uaccount").val("");
        $("#editorSysUserForm #uname").val("");
        $("#editorSysUserForm #orgIds").val("");
        $("#editorSysUserForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorSysUserForm #uid").val(rowData.uid);
            $("#editorSysUserForm #version").val(rowData.version);
            $("#editorSysUserForm #uaccount").val(rowData.uaccount);
            $("#editorSysUserForm #uname").val(rowData.uname);
            $("#editorSysUserForm #orgIds").val(rowData.orgIds);
            var flag =false;
            if(rowData.isValid==0){
                flag=true;
            }
            $("#editorSysUserForm #isValid").bootstrapSwitch("state",flag);
            $("#editorSysUserForm #orgIds").val(rowData.orgIds);
            $("#editorSysUserForm #orgNms").val(rowData.orgNms);
        }
    }
    /**
     * 编辑部门
     **/
    function addSysUser(){
        editorSysUserType=0;
        let orgId,orgPid;
        initFormData();
        $("#editorSysUserForm #isValid").bootstrapSwitch("state",true);
        $("#editorSysUserTitle").text("添加职工");
        $('#editorSysUser').modal("show")
    }
</script>
</body>
</html>
