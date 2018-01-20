<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>会员管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
</head>
<body id="contextDiv">
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <sapn>会员帐号:</sapn>
        <input type="text" class="form-control" id="search_loginNm" placeholder="请输入会员帐号">
    </div>
    <div class="form-group">
        <sapn>会员名称:</sapn>
        <input type="text" class="form-control" id="search_userNm" placeholder="请输入会员名称">
    </div>
    <div class="form-group">
        <span>会员手机号:</span>
        <input type="text" class="form-control" id="search_phone" placeholder="请输入会员手机号">
    </div>
    <div class="form-group">
        <span>会员类型:</span>
        <select id="search_type" name="search_type"  class="form-control" style="width: auto;">
            <option value="">--全部--</option>
            <option value="0">普通会员</option>
            <option value="1">兼职推广人员</option>
            <option value="2">推广人员</option>
        </select>
    </div>
    <ex:perm url="customer/queryCustomerLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
</div>
<div id="tableContext">
    <table id="customerListsTables" class="table text-nowrap"></table>
</div>
<div class="modal fade" id="editorCustomer" role="dialog" aria-labelledby="editorCustomer">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorCustomerTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorCustomerForm">
                    <input type="hidden" name="uid" id="uid">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    登陆帐号:
                                </span>
                                <input type="text" class="form-control" name="uaccount" id="uaccount" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    账户名称:
                                </span>
                                <input type="text" class="form-control" name="uname" id="uname" readonly="readonly">
                            </div>
                        </div>

                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    手机号:
                                </span>
                                <input type="text" class="form-control" name="uphone" id="uphone" readonly="readonly">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="input-group col-xs-12">
                                    <div class="input-group-addon">
                                        会员类型:
                                    </div>
                                    <select id="utype" name="utype"  class="form-control" style="width: auto;" onclick="utypeChange()">
                                        <option value="">--全部--</option>
                                        <option value="0">普通会员</option>
                                        <option value="1">兼职推广人员</option>
                                        <option value="2">推广人员</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row" id="commissionDiv">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                首单提成:
                              </span>
                              <input id="ufirstCommission" name="ufirstCommission" class="form-control" type="text" />
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="input-group col-xs-12">
                                    <div class="input-group-addon">
                                        消费提成:
                                    </div>
                                    <input type="text" class="form-control" name="urecommendCommission" id="urecommendCommission" placeholder="请输入会员等级名称">
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
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorCustomerType=0;

    var treeColumns=[
        {checkbox: true},
        {
            field: 'uid',
            title: '会员ID'
        },
        {
            field: 'uaccount',
            title: '登陆帐号'
        },
        {
            field: 'uname',
            title: '会员名称'
        },
        {
            field: 'uphone',
            title: '手机号'
        },
        {
            field: 'heardUrl',
            title: '会员头像',
            formatter:function(value,row,index){
                var html="";
                if(value){
                    html ='<img src="'+value+'" width="96px;" height="48px;">';
                }
                return html;
            }
        },
        {
            field: 'rechageNm',
            title: '会员等级'
        },
        {
            field: 'uTotalWallet',
            title: '充值总额'
        },
        {
            field: 'uCurWallet',
            title: '帐户余额'
        },
        {
            field: 'consumeTotal',
            title: '总消费'
        },
        {
            field: 'walletConsumeTotal',
            title: '钱包消费'
        },
        {
            field: 'onLineConsumeTotal',
            title: '第三方消费'
        },
        {
            field: 'ucreateTime',
            title: '创建时间'
        },
        {
            field: 'uRecommendNm',
            title: '推荐用户'
        },
        {
            field: 'openId',
            title: '公众号ID'
        },
        {
            field: 'utype',
            title: '会员类型',
            formatter:function(value,row,index){
                var html="平台会员";
                if(value==1){
                    html="兼职推广人员"
                }else if(value==3){
                    html="推广人员"
                }
                return html;
            }
        },
        {
            field: 'ufirstCommission',
            title: '首单提成'
        },
        {
            field: 'urecommendCommission',
            title: '消费提成'
        },
        {
            field: 'ustatus',
            title: '会员状态',
            formatter:function(value,row,index){
                var html="正常";
                if(value==1){
                    html="锁定";
                }else if(value==2){
                    html="注销";
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
                rowDatas.set(row.uid,row);
                let html='';
                <ex:perm url="customer/editorUserType">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorCustomer(\''+row.uid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/customer/queryCustomerLists","customerListsTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $('#editorCustomer').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorCustomerForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorCustomerForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorCustomerForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorCustomerForm").serializeObject();
            url="/customer/editorUserType";
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
                        $('#editorCustomer').modal('hide')
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
        formValidater();
        $("#contextDiv").height($(window).height()-80);
        $("#tableContext").height($(window).height()-90);
        $('#customerListsTables').bootstrapTable("resetView");
    });
    $(window).resize(function() {
        $("#contextDiv").height($(window).height()-80);
        $("#tableContext").height($(window).height()-90);
        $('#customerListsTables').bootstrapTable("resetView");
    });
    /**
     * form 校验
     * */
    function formValidater(){
        $('#editorCustomerForm')
                .bootstrapValidator({
                    container: 'tooltip',
                    //不能编辑 隐藏 不可见的不做校验
                    excluded: [':disabled', ':hidden', ':not(:visible)'],
                    message: 'This value is not valid',
                    //live: 'submitted',
                    feedbackIcons: {
                        valid: 'glyphicon glyphicon-ok',
                        invalid: 'glyphicon glyphicon-remove',
                        validating: 'glyphicon glyphicon-refresh'
                    },
                    fields: {
                        ufirstCommission: {
                            message: '首单提成不能为空',
                            validators: {
                                notEmpty: {
                                    message: '首单提成不能为空'
                                },
                                regexp:{
                                    regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                                    message:'首单提成只允许在2位整数和2位小数范围内'
                                }
                            }
                        },
                        urecommendCommission: {
                            message: '消费提成',
                            validators: {
                                notEmpty: {
                                    message: '消费提成不能为空'
                                },
                                regexp:{
                                    regexp:/^([1-9]{1}|0)(\.\d{1,2})?$/,
                                    message:'消费提成只允许在2位整数和2位小数范围内'
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
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            loginNm: $("#search_loginNm").val(),
            userNm: $("#search_userNm").val(),
            phone: $("#search_phone").val(),
            type: $("#search_type").val()
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#customerListsTables').bootstrapTable(
                "refresh",
                {
                    url:"/customer/queryCustomerLists"
                }
        );
    }
    //删除确认框
    function deleteData(rid){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeRecharge(rid);
        }, function(){
        });
    }
    /**
     * 删除充值金额
     **/
    function removeRecharge(rid){
        $.ajax({
            type:"post",
            url:'/recharge/delRecharge',
            dataType: "json",
            data:{rid:rid},
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
    function editorCustomer(uid){
        editorCustomerType=1;
        initFormData(uid);
        utypeChange();
        $("#editorCustomerTitle").text("设置会员类型");
        $('#editorCustomer').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorCustomerForm #uid").val("");
        $("#editorCustomerForm #uaccount").val("");
        $("#editorCustomerForm #uname").val("");
        $("#editorCustomerForm #uphone").val("");
        $("#editorCustomerForm #utype").val("");
        $("#editorCustomerForm #ufirstCommission").val("");
        $("#editorCustomerForm #urecommendCommission").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorCustomerForm #uid").val(rowData.uid);
            $("#editorCustomerForm #uaccount").val(rowData.uaccount);
            $("#editorCustomerForm #uname").val(rowData.uname);
            $("#editorCustomerForm #uphone").val(rowData.uphone);
            $("#editorCustomerForm #utype").val(rowData.utype);
            $("#editorCustomerForm #ufirstCommission").val(rowData.ufirstCommission);
            $("#editorCustomerForm #urecommendCommission").val(rowData.urecommendCommission);
        }else{
            clearFormData();
        }
    }
    /**
     * 用户类型改变事件
     */
    function  utypeChange() {
        let valSelect=$("#editorCustomerForm #utype").val();
        $("#commissionDiv").hide();
        if(valSelect==0){
            $("#commissionDiv").hide();
        }else{
            $("#commissionDiv").show();
        }
    }
</script>
</body>
</html>
