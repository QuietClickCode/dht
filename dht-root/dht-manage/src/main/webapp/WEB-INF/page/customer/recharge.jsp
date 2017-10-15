<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>充值管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/daterangepicker/daterangepicker.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <sapn>是否启用:</sapn>
        <select id="search_is_valid" name="search_is_valid"  class="form-control" style="width: auto;">
            <option value="" selected="selected">--全部--</option>
            <option value="0" >启用</option>
            <option value="1">未启用</option>
        </select>
    </div>
    <div class="form-group">
        <sapn>是否返现:</sapn>
        <select id="search_r_cashback" name="search_r_cashback"  class="form-control" style="width: auto;">
            <option value="">--全部--</option>
            <option value="0">是</option>
            <option value="1">否</option>
        </select>
    </div>
    <div class="form-group">
        <span>等级名称:</span>
        <input type="text" class="form-control" id="search_r_name" placeholder="请输入等级名称">
    </div>
    <ex:perm url="recharge/queryRechargeLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
    <ex:perm url="recharge/addRecharge">
        <button class="btn btn-default" type="button" onclick="addRecharge()">新增充值金额</button>
    </ex:perm>
</div>
<div>
    <table id="rechargeTables" ></table>
</div>
<div class="modal fade" id="editorRecharge" role="dialog" aria-labelledby="editorRecharge">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorRechargeTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorRechargeForm">
                    <input type="hidden" name="rid" id="rid">
                    <input type="hidden" name="version" id="version">
                    <input type="hidden" name="rsnapshot" id="rsnapshot">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    充值金额:
                                </span>
                                <input type="text" class="form-control" name="rprice" id="rprice">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    享受折扣:
                                </span>
                                <input type="text" class="form-control" name="rdiscount" id="rdiscount">
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="input-group col-xs-12">
                                    <div class="input-group-addon">
                                        会员等级名称:
                                    </div>
                                    <input type="text" class="form-control" name="rname" id="rname" placeholder="请输入会员等级名称">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6" id="gcpMoneyDiv">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                会员卡图片:
                              </span>
                                <input type="text" class="form-control" name="gcpMoney" id="gcpMoney" placeholder="请输入优惠金额"/>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                是否返现:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="rcashback" name="rcashback" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                是否启用:
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
<!-- 公用下拉择树 -->
<div id="orgNodeContent" class="orgNodeContent" style="display:none; position: absolute;z-index:1059">
    <ul id="orgTree" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script type="text/javascript"  src="/js/daterangepicker/moment.js"></script>
<script type="text/javascript"  src="/js/daterangepicker/daterangepicker.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorRechargeType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'rprice',
            title: '金额'
        },
        {
            field: 'rdiscount',
            title: '享受折扣（折）'
        },
        {
            field: 'rname',
            title: '等级名称'
        },
        {
            field: 'rcashback',
            title: '是否返现',
            formatter:function(value,row,index){
                var html="是";
                if(value==1){
                    html="否";
                }
                return html;
            }
        },
        {
            field: 'isValid',
            title: '是否启用',
            formatter:function(value,row,index){
                var html="启用";
                if(value==1){
                    html="未启用";
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
                rowDatas.set(row.gcpId,row);
                let html='';
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorRecharge(\''+row.gcpId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gcpId+'\')"">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/recharge/queryRechargeLists","rechargeTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorRechargeForm #isValid").bootstrapSwitch();
        $("#editorRechargeForm #rcashback").bootstrapSwitch();
        $('#editorRecharge').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorRechargeForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorRechargeForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorRechargeForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorRechargeForm").serializeObject();
            var flag =$("#editorRechargeForm #isValid").bootstrapSwitch("state");
            var rcashback =$("#editorRechargeForm #rcashback").bootstrapSwitch("state");
            if(flag){
                formData["isValid"]=0;
            }else{
                formData["isValid"]=1;
            }
            if(rcashback){
                formData["rcashback"]=0;
            }else{
                formData["rcashback"]=1;
            }
            let url="/recharge/addRecharge";
            if(editorRechargeType==1){
                url="/goodsCoupon/editorRecharge";
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
                        $('#editorRecharge').modal('hide')
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
        formValidater();
        $('#gcpValidTime').daterangepicker(
            {
                startDate: moment(),
                minDate : moment(),
                showDropdowns : true,
                timePicker:true,
                timePickerIncrement:5,
                timePicker24Hour:true,//24 小时制
                opens : 'right', //日期选择框的弹出位置
                buttonClasses : [ 'btn btn-default' ],
                applyClass : 'btn-small btn-primary blue',
                cancelClass : 'btn-small',
                format: 'YYYY-MM-DD HH:mm:ss',
                locale : {
                    format: 'YYYY-MM-DD HH:mm:ss',
                    applyLabel : '确定',
                    cancelLabel : '取消',
                    fromLabel : '起始时间',
                    toLabel : '结束时间',
                    daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
                    monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
                        '七月', '八月', '九月', '十月', '十一月', '十二月' ],
                    firstDay : 1
                }
            }, function(start, end, label) {
                $("#editorRechargeForm #gcpStartTime").val(start.format('YYYY-MM-DD HH:mm:ss'));
                $("#editorRechargeForm #gcpEndTime").val(end.format('YYYY-MM-DD HH:mm:ss'));
                $("#editorRechargeForm #gcpValidTime").val(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
            });
    });
    /**
     * form 校验
     * */
    function formValidater(){
        $('#editorRechargeForm')
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
                    gcpName: {
                        message: '优惠名称不能为空',
                        validators: {
                            notEmpty: {
                                message: '优惠名称不能为空'
                            },
                            stringLength: {
                                min: 2,
                                max: 25,
                                message: '优惠名称长度在4-30之间'
                            }
                        }
                    },
                    gcpCondition: {
                        message: '优惠条件校验未通过',
                        validators: {
                            notEmpty: {
                                message: '优惠条件不能为空'
                            },
                            regexp:{
                                regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                                message:'优惠条件只允许在10位整数和2位小数范围内'
                            }
                        }
                    },
                    gcpMoney: {
                        message: '金额不能为空',
                        validators: {
                            notEmpty: {
                                message: '金额不能为空'
                            },
                            regexp:{
                                regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                                message:'优惠金额只允许在10位整数和2位小数范围内'
                            }
                        }
                    },
                    gcpDiscount: {
                        message: '折扣不能为空',
                        validators: {
                            notEmpty: {
                                message: '折扣不能为空'
                            },
                            regexp:{
                                regexp:/^([1-9]{1}|0)(\.\d{1,2})?$/,
                                message:'优惠折扣只允许在2位整数和2位小数范围内'
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
            isValid: $("#search_is_valid").val(),
            rcashback: $("#search_r_cashback").val(),
            rName: $("#search_r_name").val()
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#rechargeTables').bootstrapTable(
            "refresh",
            {
                url:"/recharge/queryRechargeLists"
            }
        );
    }
    //删除确认框
    function deleteData(gcpId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeRecharge(gcpId);
        }, function(){
        });
    }
    /**
     * 删除充值金额
     **/
    function removeRecharge(gcpId){
        $.ajax({
            type:"post",
            url:'/goodsCoupon/delGoodsCoupon',
            dataType: "json",
            data:{gcpId:gcpId},
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


    function editorRecharge(gcpId){
        editorRechargeType=1;
        initFormData(gcpId);
        $("#editorRechargeTitle").text("编辑商品优惠");
        $('#editorRecharge').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorRechargeForm #gcpId").val("");
        $("#editorRechargeForm #version").val("");
        $("#editorRechargeForm #gcpName").val("");
        $("#editorRechargeForm #gcpCondition").val("");
        $("#editorRechargeForm #gcpStartTime").val("");
        $("#editorRechargeForm #gcpEndTime").val("");
        $("#editorRechargeForm #gcpValidTime").val("");
        $("#editorRechargeForm #gcpMoney").val("");
        $("#editorRechargeForm #gcpDiscount").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorRechargeForm #gcpId").val(rowData.gcpId);
            $("#editorRechargeForm #version").val(rowData.version);
            $("#editorRechargeForm #gcpName").val(rowData.gcpName);
            $("#editorRechargeForm #gcpType").val(rowData.gcpType);
            $("#editorRechargeForm #gcpCondition").val(rowData.gcpConditions);
            $("#editorRechargeForm #gcpUnits").val(rowData.gcpUnits);
            $("#editorRechargeForm #gcpStartTime").val(rowData.gcpStartTime);
            $("#editorRechargeForm #gcpEndTime").val(rowData.gcpEndTime);
            if(rowData.gcpStartTime){
                $("#editorRechargeForm #gcpValidTime").val(rowData.gcpStartTime+" - "+rowData.gcpEndTime);
            }else{
                $("#editorRechargeForm #gcpValidTime").val("");
            }
            $("#editorRechargeForm #gcpMoney").val(rowData.gcpMoneys);
            $("#editorRechargeForm #gcpDiscount").val(rowData.gcpDiscounts);
            var flag =false;
            if(rowData.isValid==0){
                flag=true;
            }
            $("#editorRechargeForm #isValid").bootstrapSwitch("state",flag);

            var gcpIsOverlapUse =false;
            if(rowData.gcpIsOverlapUse==0){
                gcpIsOverlapUse=true;
            }
            $("#editorRechargeForm #gcpIsOverlapUse").bootstrapSwitch("state",flag);
            gcpTypeChange();
        }else{
            $("#editorRechargeForm #gcpType").val("0");
            $("#editorRechargeForm #gcpMoneyDiv").show();
            $("#editorRechargeForm #gcpDiscountDiv").hide();
            $("#editorRechargeForm #gcpValidTime").val("");
        }
    }
    /**
     * 编辑部门
     **/
    function addRecharge(){
        editorRechargeType=0;
        let orgId,orgPid;
        initFormData();
        $("#editorRechargeForm #isValid").bootstrapSwitch("state",true);
        $("#editorRechargeTitle").text("添加充值金额");
        $('#editorRecharge').modal("show")
    }
    function gcpTypeChange(){
        let selectValue = $("#editorRechargeForm #gcpType").val();
        if(parseInt(selectValue,10)==0){
            $("#editorRechargeForm #gcpMoneyDiv").show();
            $("#editorRechargeForm #gcpDiscountDiv").hide();
            $("#editorRechargeForm #gcpDiscount").val("");
        }else if(parseInt(selectValue,10)==1){
            $("#editorRechargeForm #gcpMoneyDiv").hide();
            $("#editorRechargeForm #gcpDiscountDiv").show();
            $("#editorRechargeForm #gcpMoney").val("");
        }else if(parseInt(selectValue,10)==2){
            $("#editorRechargeForm #gcpMoneyDiv").hide();
            $("#editorRechargeForm #gcpDiscountDiv").hide();
            $("#editorRechargeForm #gcpDiscount").val("");
            $("#editorRechargeForm #gcpMoney").val("");
        }
    }

</script>
</body>
</html>
