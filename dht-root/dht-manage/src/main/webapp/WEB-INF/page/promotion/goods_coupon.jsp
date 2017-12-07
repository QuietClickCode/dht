<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品优惠</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
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
                    <input type="hidden" name="gcpId" id="gcpId">
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
                                    <option value="2">包邮</option>
                                </select>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="form-group">
                                <div class="input-group col-xs-12">
                                    <div class="input-group-addon">
                                        优惠条件:
                                    </div>
                                    <span class="form-control" style="width:40px;padding-right:0px;">满</span>
                                    <input type="text" class="form-control" name="gcpCondition" id="gcpCondition" placeholder="请输入优惠条件" style="width: 140px;padding-right: 2px;">
                                    <select class="form-control" style="width: 60px;padding-right: 2px;" id="gcpUnits" name="gcpUnits">
                                        <option value="0">元</option>
                                        <option value="1">件</option>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-6" id="gcpMoneyDiv">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                金额:
                              </span>
                                <input type="text" class="form-control" name="gcpMoney" id="gcpMoney" placeholder="请输入优惠金额"/>
                            </div>
                        </div>
                        <div class="col-lg-6" id="gcpDiscountDiv">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                  折扣:
                              </span>
                                <input type="text" class="form-control" id="gcpDiscount" name="gcpDiscount"  placeholder="请输入优惠折扣，如98折输入 9.8"/>
                            </div>
                        </div>
                    </div>

                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                开始时间:
                              </span>
                                <input type="hidden" name="gcpStartTime" id="gcpStartTime">
                                <input type="hidden" name="gcpEndTime" id="gcpEndTime">
                                <input type="text" class="form-control" name="gcpValidTime" id="gcpValidTime">
                            </div>
                        </div>
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
                    </div>
                    <div class="row">
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
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    是否限制范围:
                                </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="gcpIsRestricted" name="gcpIsRestricted" type="checkbox" />
                                    </div>
                                </div>
                                <%--<select id="gcpIsRestricted" name="gcpIsRestricted" class="form-control" onclick="syfwChange()">
                                    <option value="0">无限制</option>
                                    <option value="1">指定商品种类</option>
                                    <option value="2">指定商品</option>
                                </select>--%>
                            </div>
                        </div>
                    </div>
                    <div id="syfwxzDiv" style="display: none;">
                        <br>
                        <div class="form-group">
                            <label for="spzlNm" class="control-label">指定商品子类:</label>
                            <input type="hidden" id="spzlId" name="spzlId"/>
                            <textarea class="form-control" id="spzlNm" name="spzlNm" onclick="showGoodsTypeTrees();"></textarea>
                        </div>
                        <br>
                        <div class="form-group">
                            <label for="spNm" class="control-label">指定商品:</label>
                            <input type="hidden" id="spId" name="spId"/>
                            <textarea class="form-control" id="spNm" name="spNm"></textarea>
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
<div id="goodsTypeContext" class="goodsTypeContext" style="display:none; position: absolute;z-index:1059">
    <ul id="goodsTypeTrees" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script type="text/javascript"  src="/js/daterangepicker/moment.js"></script>
<script type="text/javascript"  src="/js/daterangepicker/daterangepicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.excheck.min.js"></script>
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
            field: 'gcpConditions',
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
            field: 'gcpMoneys',
            title: '优惠金额'
        },
        {
            field: 'gcpDiscounts',
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
        createTable("/goodsCoupon/queryGoodsCoupons","goodsCouponTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorGoodsCouponForm #isValid").bootstrapSwitch();
        $("#editorGoodsCouponForm #gcpIsOverlapUse").bootstrapSwitch();
        $("#editorGoodsCouponForm #gcpIsRestricted").bootstrapSwitch({
            onText:'限制',
            offText:'不限制'
        });
        $('#editorGoodsCouponForm #gcpIsRestricted').on('switchChange.bootstrapSwitch', function (event,state) {
            if(state){
                $("#editorGoodsCouponForm #syfwxzDiv").show();
            }else{
                $("#editorGoodsCouponForm #syfwxzDiv").hide();
            }
        });
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
            let gcpIsRestricted =$("#editorGoodsCouponForm #gcpIsRestricted").bootstrapSwitch("state");
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
            if(gcpIsRestricted){
                formData["gcpIsRestricted"]=1
            }else{
                formData["gcpIsRestricted"]=0
            }
            let url="/goodsCoupon/addGoodsCoupon";
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
               $("#editorGoodsCouponForm #gcpStartTime").val(start.format('YYYY-MM-DD HH:mm:ss'));
               $("#editorGoodsCouponForm #gcpEndTime").val(end.format('YYYY-MM-DD HH:mm:ss'));
               $("#editorGoodsCouponForm #gcpValidTime").val(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
           });
    });
    /**
     * form 校验
     * */
    function formValidater(){
        $('#editorGoodsCouponForm')
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
            removeGoodsCoupon(gcpId);
        }, function(){
        });
    }
    /**
     * 删除商品优惠
     **/
    function removeGoodsCoupon(gcpId){
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
        $("#editorGoodsCouponTitle").text("编辑商品优惠");
        $('#editorGoodsCoupon').modal("show")
        queryGoodsTyps(gcpId);
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsCouponForm #gcpId").val("");
        $("#editorGoodsCouponForm #version").val("");
        $("#editorGoodsCouponForm #gcpName").val("");
        $("#editorGoodsCouponForm #gcpCondition").val("");
        $("#editorGoodsCouponForm #gcpStartTime").val("");
        $("#editorGoodsCouponForm #gcpEndTime").val("");
        $("#editorGoodsCouponForm #gcpValidTime").val("");
        $("#editorGoodsCouponForm #gcpMoney").val("");
        $("#editorGoodsCouponForm #gcpDiscount").val("");
        $("#editorGoodsCouponForm #spzlNm").val("");
        $("#editorGoodsCouponForm #spzlId").val("");
        $("#editorGoodsCouponForm #spId").val("");
        $("#editorGoodsCouponForm #spNm").val("");
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsCouponForm #gcpId").val(rowData.gcpId);
            $("#editorGoodsCouponForm #version").val(rowData.version);
            $("#editorGoodsCouponForm #gcpName").val(rowData.gcpName);
            $("#editorGoodsCouponForm #gcpType").val(rowData.gcpType);
            $("#editorGoodsCouponForm #gcpCondition").val(rowData.gcpConditions);
            $("#editorGoodsCouponForm #gcpUnits").val(rowData.gcpUnits);
            $("#editorGoodsCouponForm #gcpStartTime").val(rowData.gcpStartTime);
            $("#editorGoodsCouponForm #gcpEndTime").val(rowData.gcpEndTime);
            $("#editorGoodsCouponForm #spzlId").val(rowData.spzlId);
            $("#editorGoodsCouponForm #spzlNm").val(rowData.spzlNm);
            $("#editorGoodsCouponForm #spId").val(rowData.spId);
            $("#editorGoodsCouponForm #spNm").val(rowData.spNm);
            if(rowData.gcpStartTime){
                $("#editorGoodsCouponForm #gcpValidTime").val(rowData.gcpStartTime+" - "+rowData.gcpEndTime);
            }else{
                $("#editorGoodsCouponForm #gcpValidTime").val("");
            }
            $("#editorGoodsCouponForm #gcpMoney").val(rowData.gcpMoneys);
            $("#editorGoodsCouponForm #gcpDiscount").val(rowData.gcpDiscounts);
            var flag =false;
            if(rowData.isValid==0){
                flag=true;
            }
            $("#editorGoodsCouponForm #isValid").bootstrapSwitch("state",flag);
            var gcpIsRestrictedFlag=false;
            $("#editorGoodsCouponForm #syfwxzDiv").hide();
            if(rowData.gcpIsRestricted==1){
                gcpIsRestrictedFlag=true;
                $("#editorGoodsCouponForm #syfwxzDiv").show();
            }
            $("#editorGoodsCouponForm #gcpIsRestricted").bootstrapSwitch("state",gcpIsRestrictedFlag);

            var gcpIsOverlapUse =false;
            if(rowData.gcpIsOverlapUse==0){
                gcpIsOverlapUse=true;
            }
            $("#editorGoodsCouponForm #gcpIsOverlapUse").bootstrapSwitch("state",flag);

            gcpTypeChange();
        }else{
            $("#editorGoodsCouponForm #gcpType").val("0");
            $("#editorGoodsCouponForm #gcpMoneyDiv").show();
            $("#editorGoodsCouponForm #gcpDiscountDiv").hide();
            $("#editorGoodsCouponForm #gcpValidTime").val("");
            $("#editorGoodsCouponForm #syfwxzDiv").hide();
            $("#editorGoodsCouponForm #gcpIsRestricted").bootstrapSwitch("state",false);
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
        queryGoodsTyps();
    }
    function gcpTypeChange(){
        let selectValue = $("#editorGoodsCouponForm #gcpType").val();
        if(parseInt(selectValue,10)==0){
            $("#editorGoodsCouponForm #gcpMoneyDiv").show();
            $("#editorGoodsCouponForm #gcpDiscountDiv").hide();
            $("#editorGoodsCouponForm #gcpDiscount").val("");
        }else if(parseInt(selectValue,10)==1){
            $("#editorGoodsCouponForm #gcpMoneyDiv").hide();
            $("#editorGoodsCouponForm #gcpDiscountDiv").show();
            $("#editorGoodsCouponForm #gcpMoney").val("");
        }else if(parseInt(selectValue,10)==2){
            $("#editorGoodsCouponForm #gcpMoneyDiv").hide();
            $("#editorGoodsCouponForm #gcpDiscountDiv").hide();
            $("#editorGoodsCouponForm #gcpDiscount").val("");
            $("#editorGoodsCouponForm #gcpMoney").val("");
        }
    }

    //当前选中优惠卷id
    var selectCurGoodsCouponId;
    //
    var goodsTypsTreesObj;
    var zNodes;

    /**
     * 取得商品类型
     **/
    function queryGoodsTyps(gcpId){
        selectCurGoodsCouponId=gcpId;
        $.fn.zTree.init($("#goodsTypeTrees"), setting, zNodes);
        $.ajax({
            type:"post",//请求方式
            url:"/goods/goodsTypeTree",//发送请求地址
            dataType:"json",
            data:{//发送给数据库的数据
                id:gcpId,
                type:0
            },
            //请求成功后的回调函数有两个参数
            success:function(data,textStatus){
                if(data.status==0){
                    let zNodes = data.data;
                    var zTree=$.fn.zTree.init($("#goodsTypeTrees"), setting, zNodes);
                }else{
                    alert(data.msg);
                }
            },error:function(data,textStatus){
                alert(data);
            }
        });
    }

    /***********************************************************************************/
    var setting = {
        check: {
            enable: true,
            chkboxType: { "Y" : "s", "N" : "s" }
        },
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onCheck: onClick
        }
    };
    function onClick(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("goodsTypeTrees"),
            nodes = zTree.getCheckedNodes(),
            v = "",vId="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            if(!nodes[i].getCheckStatus().half){
                v += nodes[i].name+",";
                vId += nodes[i].id+",";
            }
        }
        var orgPname = $("#spzlNm");
        var orgPid_ = $("#spzlId");
        orgPname.val(v);
        orgPid_.val(vId);
    }

    function showGoodsTypeTrees() {
        var cityObj = $("#spzlNm");
        var cityOffset = $("#spzlNm").offset();
        $("#goodsTypeContext").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideOrgTree() {
        $("#goodsTypeContext").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "goodsTypeContext" || $(event.target).parents("#goodsTypeContext").length>0)) {
            hideOrgTree();
        }
    }
</script>
</body>
</html>
