<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品优惠</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="<%=path%>/js/filestyle/open-iconic/font/css/open-iconic-bootstrap.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <div class="form-group">
        <sapn>优惠卷状态:</sapn>
        <select id="search_is_valid" name="search_is_valid"  class="form-control" style="width: auto;">
            <option value="">--全部--</option>
            <option value="0" selected="selected">有效</option>
            <option value="1">失效</option>
        </select>
    </div>
    <div class="form-group">
        <sapn>优惠卷类型:</sapn>
        <select id="search_gcp_type" name="search_gcp_type"  class="form-control" style="width: auto;">
            <option value="">--全部--</option>
            <option value="0">现金</option>
            <option value="1">折扣</option>
        </select>
    </div>
    <div class="form-group">
        <span>优惠卷名称:</span>
        <input type="text" class="form-control" id="search_gcp_name" placeholder="请输入优惠卷名">
    </div>
    <ex:perm url="coupon/queryCoupons">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>
    <ex:perm url="coupon/addCoupon">
        <button class="btn btn-default" type="button" onclick="addCoupon()">新增优惠卷</button>
    </ex:perm>
</div>
<div>
    <table id="goodsCouponTables" class="table table-hover" ></table>
</div>
<div class="modal fade" id="editorGoodsCoupon" role="dialog" aria-labelledby="editorGoodsCoupon">
    <div class="modal-dialog" role="document"  style="width: 80%; height: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorCouponTitle"></h4>
            </div>
            <div class="modal-body" style="overflow-y:auto;height:100%;">
                <form id="cpImagesForm" method="POST" style="margin-bottom: 0px;" enctype="multipart/form-data" action="/file/imageUpload?isWatermark=false&isCompress=false&imageUse=goods">
                    <div class="row">
                        <div class="col-lg-4" id="gcpMoneyDiv">
                            <div class="input-group form-group">
                                    <span class="input-group-addon">
                                        优惠卷图片:
                                    </span>
                                    <input type="file" id="cpImages">
                            </div>
                            <input id="cpLogo" name="cpLogo" type="hidden">
                        </div>
                    </div>
                </form>
                <form id="editorCouponForm">
                    <input type="hidden" name="gcpId" id="gcpId">
                    <input type="hidden" name="version" id="version">

                    <div class="row">
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                优惠卷名称:
                              </span>
                                <input type="text" class="form-control" name="cpName" id="cpName">
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    金额值:
                                </span>
                                <select id="cpCoinType" name="cpCoinType" class="form-control" onchange="cpCoinTypeChange()">
                                    <option value="0">常规优惠卷</option>
                                    <option value="1">拼手气优惠卷</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    优惠卷类型:
                                </span>
                                <select id="cpType" name="cpType" class="form-control" onchange="cpCoinTypeChange()">
                                    <option value="0">现金卷</option>
                                    <option value="1">折扣卷</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="form-group">
                                <div class="input-group col-xs-12">
                                    <div class="input-group-addon">
                                        使用条件:
                                    </div>
                                    <span class="form-control" style="width:40px;padding-right:0px;">满</span>
                                    <input type="text" class="form-control" name="cpTypecpUseCondition" id="cpTypecpUseCondition" placeholder="请输入优惠条件" style="width: 140px;padding-right: 2px;">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    使用范围:
                                </span>
                                <select id="cpIsRestricted" name="cpIsRestricted" class="form-control">
                                    <option value="0">无限制</option>
                                    <option value="1">指定商品种类</option>
                                    <option value="2">指定商品</option>
                                </select>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    优惠卷时效:
                                </span>
                                <input type="hidden" name="cpStartDate" id="cpStartDate">
                                <input type="hidden" name="cpEndDate" id="cpEndDate">
                                <input type="text" class="form-control" name="cpValidDate" id="cpValidDate">
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                是否叠加:
                                </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="cpIsOverlapUse" name="cpIsOverlapUse" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                是否优先使用:
                                </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="cpIsFirst" name="cpIsFirst" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                发放方式:
                                </span>
                                <select id="cpSendWay" name="cpSendWay" class="form-control">
                                    <option value="0">及时发放</option>
                                    <option value="1">定时发送</option>
                                    <option value="2">周期发送</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    优惠卷数量:
                                </span>
                                <input type="text" class="form-control" name="cpNum" id="cpNum">
                            </div>
                        </div>
                        <div class="col-lg-4" id="ptzjeDiv">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    优惠卷金额:
                                </span>
                                <input type="text" class="form-control" name="cpMoney" id="cpMoney">
                            </div>
                        </div>
                        <div class="col-lg-4" id="ptzzkDiv">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    优惠卷折扣:
                                </span>
                                <input type="text" class="form-control" name="cpDiscount" id="cpDiscount">
                            </div>
                        </div>
                        <div class="col-lg-4" id="psqzjeDiv">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    总金额:
                                </span>
                                <input type="text" class="form-control" name="totalCoin" id="totalCoin">
                            </div>
                        </div>
                        <div class="col-lg-4" id="psqzzkDiv">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    打折范围:
                                </span>
                                <input type="text" class="form-control" name="cpMinDiscount" id="cpMinDiscount" style="width: 60px;padding-right: 2px;">
                                <span class="form-control" style="width:70px;padding-right:0px;">折&nbsp;&nbsp;&nbsp;到</span>
                                <input type="text" class="form-control" name="cpMaxDiscount" id="cpMaxDiscount" style="width: 60px;padding-right: 2px;">
                                <span class="form-control" style="width:40px;padding-right:0px;">折</span>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cpContextEditor" class="control-label">卡券领取与使用规则:</label>
                        <input type="hidden" id="cpContext" name="cpContext">
                        <script type="text/plain" id="cpContextEditor" name="cpContextEditor" style="height:300px;">
                        </script>
<%--
                        <textarea class="form-control" id="orgDes" name="orgDes"></textarea>--%>
                    </div>
                    <%--<div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                :
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isValid" name="isValid" type="checkbox" />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>--%>
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
<script type="text/javascript" src="<%=path%>/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/form.js"></script>
<script type="text/javascript"  src="<%=path%>/js/daterangepicker/moment.js"></script>
<script type="text/javascript"  src="<%=path%>/js/daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.config.js"></script>
<script type="text/javascript" src="<%=path%>/ueditor/ueditor.all.min.js"> </script>
<script type="text/javascript" src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=path%>/js/filestyle/bootstrap-filestyle.min.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsCouponType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'cpName',
            title: '名称'
        },
        {
            field: 'cpType',
            title: '类型'
        },
        {
            field: 'cpName',
            title: '卡券图片'
        },
        {
            field: 'cpName',
            title: '领取条件'
        },
        {
            field: 'cpName',
            title: '使用条件'
        },
        {
            field: 'cpName',
            title: '使用范围'
        },
        {
            field: 'cpName',
            title: '开始时间'
        },
        {
            field: 'cpName',
            title: '结束时间'
        },
        {
            field: 'cpName',
            title: '发放开始时间'
        },
        {
            field: 'cpName',
            title: '发放结束时间'
        },
        {
            field: 'cpName',
            title: '发放方式'
        },
        {
            field: 'cpName',
            title: '总张数'
        },
        {
            field: 'cpName',
            title: '金额'
        },
        {
            field: 'cpName',
            title: '折扣'
        },
        {
            field: 'cpName',
            title: '能否叠加使用'
        },
        {
            field: 'cpName',
            title: '已发放卡券'
        },
        {
            field: 'cpName',
            title: '已使用'
        },
        {
            field: 'cpName',
            title: '已失效'
        },
        {
            field: 'cpName',
            title: '状态'
        },
        {
            field: 'cpType',
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
                rowDatas.set(row.gcpId,row);
                let html='';
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsCoupon(\''+row.gcpId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gcpId+'\')"">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/coupon/queryCoupons","goodsCouponTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorCouponForm #isValid").bootstrapSwitch();
        $("#editorCouponForm #cpIsOverlapUse").bootstrapSwitch();
        $("#editorCouponForm #cpIsFirst").bootstrapSwitch();
        $('#editorGoodsCoupon').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorCouponForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorCouponForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorCouponForm').data('bootstrapValidator').isValid()){
                return;
            }
            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorCouponForm").serializeObject();
            var flag =$("#editorCouponForm #isValid").bootstrapSwitch("state");
            var cpIsOverlapUse =$("#editorCouponForm #cpIsOverlapUse").bootstrapSwitch("state");
            if(flag){
                formData["isValid"]=0;
            }else{
                formData["isValid"]=1;
            }
            if(cpIsOverlapUse){
                formData["cpIsOverlapUse"]=0;
            }else{
                formData["cpIsOverlapUse"]=1;
            }
            let url="/goodsCoupon/addCoupon";
            if(editorGoodsCouponType==1){
                url="/goodsCoupon/editorGoodsCoupon";
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
        $('#cpValidDate').daterangepicker(
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
                $("#editorCouponForm #cpStartDate").val(start.format('YYYY-MM-DD HH:mm:ss'));
                $("#editorCouponForm #cpEndDate").val(end.format('YYYY-MM-DD HH:mm:ss'));
                $("#editorCouponForm #cpValidDate").val(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
            });
        //创建富文本编辑器
        var ue = UE.getEditor('cpContextEditor');
        //重写图片上传地址
        UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
        UE.Editor.prototype.getActionUrl = function(action) {
            //判断路径   这里是config.json 中设置执行上传的action名称
            if (action == 'uploadimage') {
                return ueditorUploadUrl("goods",false,false);
                //上传视频
            } else if (action == 'uploadvideo') {
                return '';
            } else {
                return this._bkGetActionUrl.call(this, action);
            }
        }
        $('#cpImagesForm #cpImages').filestyle({
            btnClass : "btn-primary",
            text:"选择文件",
            onChange:function(){
                console.log("选择了文件--------------------------》》》：")
            }
        });
    });
    /**
     * form 校验
     * */
    function formValidater(){
        $('#editorCouponForm')
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
                    cpName: {
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
                    cpTypecpUseCondition: {
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
            cpType: $("#search_gcp_type").val(),
            cpName: $("#search_gcp_name").val()
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#goodsCouponTables').bootstrapTable(
            "refresh",
            {
                url:"/coupon/queryCoupons"
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


    function editorGoodsCoupon(gcpId){
        editorGoodsCouponType=1;
        initFormData(gcpId);
        $("#editorCouponTitle").text("编辑优惠卷");
        $('#editorGoodsCoupon').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorCouponForm #gcpId").val("");
        $("#editorCouponForm #version").val("");
        $("#editorCouponForm #cpName").val("");
        $("#editorCouponForm #cpTypecpUseCondition").val("");
        $("#editorCouponForm #cpStartDate").val("");
        $("#editorCouponForm #cpEndDate").val("");
        $("#editorCouponForm #cpValidDate").val("");
        $("#editorCouponForm #gcpMoney").val("");
        $("#editorCouponForm #gcpDiscount").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorCouponForm #gcpId").val(rowData.gcpId);
            $("#editorCouponForm #version").val(rowData.version);
            $("#editorCouponForm #cpName").val(rowData.cpName);
            $("#editorCouponForm #cpType").val(rowData.cpType);
            $("#editorCouponForm #cpTypecpUseCondition").val(rowData.cpTypecpUseConditions);
            $("#editorCouponForm #gcpUnits").val(rowData.gcpUnits);
            $("#editorCouponForm #cpStartDate").val(rowData.cpStartDate);
            $("#editorCouponForm #cpEndDate").val(rowData.cpEndDate);
            if(rowData.cpStartDate){
                $("#editorCouponForm #cpValidDate").val(rowData.cpStartDate+" - "+rowData.cpEndDate);
            }else{
                $("#editorCouponForm #cpValidDate").val("");
            }
            $("#editorCouponForm #gcpMoney").val(rowData.gcpMoneys);
            $("#editorCouponForm #gcpDiscount").val(rowData.gcpDiscounts);
            var flag =false;
            if(rowData.isValid==0){
                flag=true;
            }
            $("#editorCouponForm #isValid").bootstrapSwitch("state",flag);

            var cpIsOverlapUse =false;
            if(rowData.cpIsOverlapUse==0){
                cpIsOverlapUse=true;
            }
            $("#editorCouponForm #cpIsOverlapUse").bootstrapSwitch("state",flag);
        }else{
            $("#editorCouponForm #cpType").val("0");
            $("#editorCouponForm #gcpMoneyDiv").show();
            $("#editorCouponForm #gcpDiscountDiv").hide();
            $("#editorCouponForm #cpValidDate").val("");
        }
        cpCoinTypeChange();
    }
    /**
     * 编辑部门
     **/
    function addCoupon(){
        editorGoodsCouponType=0;
        let orgId,orgPid;
        initFormData();
        $("#editorCouponForm #isValid").bootstrapSwitch("state",true);
        $("#editorCouponTitle").text("添加优惠卷");
        $('#editorGoodsCoupon').modal("show")
//        $("#edui_fixedlayer").css("z-index","999999");
    }
    function cpCoinTypeChange(){
        let selectCpCoinType=$("#editorCouponForm #cpCoinType").val();
        let selectCpType=$("#editorCouponForm #cpType").val();
        $("#editorCouponForm #ptzjeDiv").hide();
        $("#editorCouponForm #ptzzkDiv").hide();
        $("#editorCouponForm #psqzjeDiv").hide();
        $("#editorCouponForm #psqzzkDiv").hide();
        //常规优惠卷
        if(parseInt(selectCpCoinType,10)==0){
            //判断优惠倦类型 现金卷
            if(parseInt(selectCpType,10)==0){
                $("#editorCouponForm #ptzjeDiv").show();
                $("#editorCouponForm #cpDiscount").val("");
                $("#editorCouponForm #totalCoin").val("");
                $("#editorCouponForm #cpMinDiscount").val("");
                $("#editorCouponForm #cpMaxDiscount").val("");
            }else{
                $("#editorCouponForm #ptzzkDiv").show();
                $("#editorCouponForm #cpMoney").val("");
                $("#editorCouponForm #totalCoin").val("");
                $("#editorCouponForm #cpMinDiscount").val("");
                $("#editorCouponForm #cpMaxDiscount").val("");
            }
        //拼手气优惠卷
        }else{
            //判断优惠倦类型 现金卷
            if(parseInt(selectCpType,10)==0){
                $("#editorCouponForm #psqzjeDiv").show();
                $("#editorCouponForm #cpMoney").val("");
                $("#editorCouponForm #cpDiscount").val("");
                $("#editorCouponForm #cpMinDiscount").val("");
                $("#editorCouponForm #cpMaxDiscount").val("");
            }else{
                $("#editorCouponForm #psqzzkDiv").show();
                $("#editorCouponForm #cpMoney").val("");
                $("#editorCouponForm #cpDiscount").val("");
                $("#editorCouponForm #totalCoin").val("");
            }
        }
    }

</script>
</body>
</html>
