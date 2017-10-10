<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品优惠</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/daterangepicker/daterangepicker.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <sapn>优惠卷状态:</sapn>
        <select id="search_is_valid" name="search_is_valid"  class="selectpicker">
            <option value="">--全部--</option>
            <option value="0" selected="selected">有效</option>
            <option value="1">失效</option>
        </select>
    </div>
    <div class="form-group">
        <sapn>优惠卷类型:</sapn>
        <select id="search_gcp_type" name="search_gcp_type">
            <option value="">--全部--</option>
            <option value="0">现金</option>
            <option value="1">折扣</option>
        </select>
    </div>
    <div class="form-group">
        <span>优惠卷名称:</span>
        <input type="text" class="form-control" id="search_gcp_name" placeholder="请输入优惠卷名">
    </div>
    <ex:perm url="goodsCoupon/queryGoodsCoupons">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
    <ex:perm url="goodsCoupon/addGoodsCoupon">
        <button class="btn btn-default" type="button" onclick="addGoodsCoupon()">新增商品优惠</button>
    </ex:perm>
</div>
<div>
    <table id="goodsCouponTables" ></table>
</div>
<div class="modal fade" id="editorGoodsCoupon" role="dialog" aria-labelledby="editorGoodsCoupon">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorGoodsCouponTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsCouponForm">
                    <input type="hidden" name="uid" id="uid">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                优惠名称:
                              </span>
                                <input type="text" class="form-control" name="gcpName" id="gcpName">
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  类型:
                              </span>
                                <select id="gcpType" name="gcpType" class="form-control" onchange="gcpTypeChange()">
                                    <option value="0">现金</option>
                                    <option value="1">折扣</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                优惠条件:
                              </span>
                                <input type="text" class="form-control" name="gcpCondition" id="gcpCondition">
                            </div>
                        </div>
                        <%--<div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  职工姓名:
                              </span>
                                <input type="text" class="form-control" id="uname" name="uname"/>
                            </div>
                        </div>--%>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                开始时间:
                              </span>
                                <input type="hidden" name="gcpStartTimeStr" id="gcpStartTimeStr">
                                <input type="hidden" name="gcpEndTimeStr" id="gcpEndTimeStr">
                                <input type="text" class="form-control" name="gcpValidTime" id="gcpValidTime">
                            </div>
                        </div>
                        <%--<div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  结束时间:
                              </span>
                                <input type="text" class="form-control" id="gcpEndTimeStr" name="gcpEndTimeStr"/>
                            </div>
                        </div>--%>
                    </div>
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                能否叠加使用:
                                </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="gcpIsOverlapUse" name="gcpIsOverlapUse" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6">
                            <div class="input-group form-group">
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
                    <div class="row">
                        <div class="col-lg-6" id="gcpMoneyDiv">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                金额:
                              </span>
                                <input type="text" class="form-control" name="gcpMoney" id="gcpMoney">
                            </div>
                        </div>
                        <div class="col-lg-6" id="gcpDiscountDiv">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  折扣:
                              </span>
                                <input type="text" class="form-control" id="gcpDiscount" name="gcpDiscount"/>
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
    var editorGoodsCouponType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'gcpName',
            title: '优惠名称'
        },
        {
            field: 'gcpType',
            title: '类型',
            formatter:function(value,row,index){
                var html="现金";
                if(value==1){
                    html="折扣";
                }
                return html;
            }
        },
        {
            field: 'gcpCondition',
            title: '优惠条件'
        },
        {
            field: 'gcpStartTime',
            title: '开始时间'
        },
        {
            field: 'gcpEndTime',
            title: '结束时间'
        },
        {
            field: 'gcpMoney',
            title: '优惠金额'
        },
        {
            field: 'cpDiscount',
            title: '折扣'
        },
        {
            field: 'gcpIsOverlapUse',
            title: '能否叠加使用',
            formatter:function(value,row,index){
                var html="允许";
                if(value==1){
                    html="不允许";
                }
                return html;
            }
        },
        {
            field: 'isValid',
            title: '状态',
            formatter:function(value,row,index){
                var html="有效";
                if(value==1){
                    html="无效";
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
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorOrganization(\''+row.uid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gcpId+'\')"">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goodsCoupon/queryGoodsCoupons","goodsCouponTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorGoodsCouponForm #isValid").bootstrapSwitch();
        $("#editorGoodsCouponForm #gcpIsOverlapUse").bootstrapSwitch();
        $('#editorGoodsCoupon').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorGoodsCouponForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsCouponForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorGoodsCouponForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorGoodsCouponForm").serializeObject();
            var flag =$("#editorGoodsCouponForm #isValid").bootstrapSwitch("state");
            var gcpIsOverlapUse =$("#editorGoodsCouponForm #gcpIsOverlapUse").bootstrapSwitch("state");
            if(flag){
                formData["isValid"]=0;
            }else{
                formData["isValid"]=1;
            }
            if(gcpIsOverlapUse){
                formData["gcpIsOverlapUse"]=0;
            }else{
                formData["gcpIsOverlapUse"]=1;
            }
            let url="/goodsCoupon/addGoodsCoupon";
            if(editorGoodsCouponType==1){
                url="/sysUser/editorGoodsCoupon";
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
                        $('#editorGoodsCoupon').modal('hide')
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
        formValidater();
        var cb = function(start, end, label) {
            $('#gcpValidTime').html(start.format('YYYY-MM-DD HH:mm:ss'));
        };


        var optionSet1 = {
            startDate: moment().subtract(29, 'days'),

            showDropdowns: false,
            showWeekNumbers: false,
            timePicker: true,
            timePickerIncrement: 1,
            singleDatePicker: true,
            timePicker24Hour: true,
            locale: {
                format: 'YYYY-MM-DD HH:mm:ss',
                daysOfWeek: ['日', '一', '二', '三', '四', '五', '六'],
                monthNames: ['一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月', '九月', '十月', '十一月', '十二月'],
            },

            opens: 'left',
            buttonClasses: ['btn btn-default'],
            applyClass: 'btn-small btn-primary',
            cancelClass: 'btn-small',
            format: 'YYYY-MM-DD HH:mm:ss',

        };

        $('#gcpValidTime').html(moment().subtract(29, 'days').format('YYYY-MM-DD HH:mm:ss'));
        $('#gcpValidTime').daterangepicker(optionSet1, cb);
//
//       $('#gcpValidTime').daterangepicker(
//            {
////                minDate: moment(),    //最小时间
//                showDropdowns : true,
//                timePicker:true,
//                timePickerIncrement:30,
//                opens : 'right', //日期选择框的弹出位置
//                buttonClasses : [ 'btn btn-default' ],
//                applyClass : 'btn-small btn-primary blue',
//                cancelClass : 'btn-small',
//                locale : {
//                    format: 'YYYY-MM-DD HH:mm:ss',
//                    applyLabel : '确定',
//                    cancelLabel : '取消',
//                    fromLabel : '起始时间',
//                    toLabel : '结束时间',
//                    daysOfWeek : [ '日', '一', '二', '三', '四', '五', '六' ],
//                    monthNames : [ '一月', '二月', '三月', '四月', '五月', '六月',
//                        '七月', '八月', '九月', '十月', '十一月', '十二月' ],
//                    firstDay : 1
//                }
//            }, function(start, end, label) {
//               $("#editorGoodsCouponForm #gcpStartTimeStr").val(start.format('YYYY-MM-DD HH:mm:ss'));
//               $("#editorGoodsCouponForm #gcpEndTimeStr").val(end.format('YYYY-MM-DD HH:mm:ss'));
//               $("#editorGoodsCouponForm #gcpValidTime").val(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
//           });
    });
    /**
     * form 校验
     * */
    function formValidater(){
        $('#editorGoodsCouponForm')
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
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            isValid: $("#search_is_valid").val(),
            gcpType: $("#search_gcp_type").val(),
            gcpName: $("#search_gcp_name").val()
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsCouponTables').bootstrapTable(
            "refresh",
            {
                url:"/goodsCoupon/queryGoodsCoupons"
            }
        );
    }
    //删除确认框
    function deleteData(gcpId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeSysUser(gcpId);
        }, function(){
        });
    }
    /**
     * 删除部门
     **/
    function removeSysUser(gcpId){
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


    function editorOrganization(orgId){
        editorGoodsCouponType=1;
        initFormData(orgId);
        $("#editorGoodsCouponTitle").text("编辑职工");
        $('#editorGoodsCoupon').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsCouponForm #uid").val("");
        $("#editorGoodsCouponForm #version").val("");
        $("#editorGoodsCouponForm #uaccount").val("");
        $("#editorGoodsCouponForm #uname").val("");
        $("#editorGoodsCouponForm #orgIds").val("");
        $("#editorGoodsCouponForm #isValid").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsCouponForm #uid").val(rowData.uid);
            $("#editorGoodsCouponForm #version").val(rowData.version);
            $("#editorGoodsCouponForm #uaccount").val(rowData.uaccount);
            $("#editorGoodsCouponForm #uname").val(rowData.uname);
            $("#editorGoodsCouponForm #orgIds").val(rowData.orgIds);
            var flag =false;
            if(rowData.isValid==0){
                flag=true;
            }
            $("#editorGoodsCouponForm #isValid").bootstrapSwitch("state",flag);
            $("#editorGoodsCouponForm #orgIds").val(rowData.orgIds);
            $("#editorGoodsCouponForm #orgNms").val(rowData.orgNms);
        }else{
            $("#editorGoodsCouponForm #gcpType").val("0");
            $("#editorGoodsCouponForm #gcpMoneyDiv").show();
            $("#editorGoodsCouponForm #gcpDiscountDiv").hide();
        }
    }
    /**
     * 编辑部门
     **/
    function addGoodsCoupon(){
        editorGoodsCouponType=0;
        let orgId,orgPid;
        initFormData();
        $("#editorGoodsCouponForm #isValid").bootstrapSwitch("state",true);
        $("#editorGoodsCouponTitle").text("添加商品优惠");
        $('#editorGoodsCoupon').modal("show")
    }
    function gcpTypeChange(){
        let selectValue = $("#editorGoodsCouponForm #gcpType").val();
        if(parseInt(selectValue,10)==0){
            $("#editorGoodsCouponForm #gcpMoneyDiv").show();
            $("#editorGoodsCouponForm #gcpDiscountDiv").hide();
        }else{
            $("#editorGoodsCouponForm #gcpMoneyDiv").hide();
            $("#editorGoodsCouponForm #gcpDiscountDiv").show();
        }
    }

</script>
</body>
</html>
