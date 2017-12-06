<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品优惠</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="<%=path%>/js/filestyle/open-iconic/font/css/open-iconic-bootstrap.css">
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
<div class="modal fade" id="editorCoupon" role="dialog" aria-labelledby="editorCoupon">
    <div class="modal-dialog" role="document"  style="width: 80%; height: 70%;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorCouponTitle"></h4>
            </div>
            <div class="modal-body" style="overflow-y:auto;height:100%;">
                <form id="cpImagesForm" method="POST" style="margin-bottom: 0px;" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-lg-4" id="cpLogoDiv">
                            <div class="input-group form-group">
                                    <span class="input-group-addon">
                                        优惠卷图片:
                                    </span>
                                    <input type="file" id="dht_image_upload" name="dht_image_upload">
                            </div>
                        </div>
                        <div class="col-lg-4" id="clearCpLogoDiv">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                        优惠卷图片:
                                    </span>
                                <button class="btn btn-default" type="button" onclick="clearCpLogo()">清除</button>
                            </div>
                        </div>
                        <div class="col-lg-4" id="uploadImageDiv">
                            <div class="input-group form-group">
                                <img src="" id="uploadImage" width="96px;" height="48px;">
                            </div>
                        </div>
                    </div>
                </form>
                <form id="editorCouponForm">
                    <input type="hidden" name="cpId" id="cpId">
                    <input type="hidden" name="version" id="version">
                    <input id="cpLogo" name="cpLogo" type="hidden">
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
                                    <input type="text" class="form-control" name="cpUseCondition" id="cpUseCondition" placeholder="请输入优惠条件" style="width: 140px;padding-right: 2px;">
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    使用范围:
                                </span>
                                <select id="cpIsRestricted" name="cpIsRestricted" class="form-control" onclick="syfwChange()">
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
                                <select id="cpSendWay" name="cpSendWay" class="form-control" onchange="cpSendWayChange()">
                                    <option value="0">及时发放</option>
                                    <option value="1">定时发送</option>
                                    <option value="2">周期发送</option>
                                </select>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-lg-4" id="cpSendEndDateDiv">
                            <div class="form-group">
                                <div class="input-group col-xs-12">
                                    <span class="input-group-addon">
                                        领取结束时间:
                                    </span>
                                    <input type="text" class="form-control" name="cpSendEndDateValid" id="cpSendEndDateValid">
                                </div>
                            </div>
                        </div>
                        <!-- 定时发送时间范围-->
                        <div class="col-lg-4" id="cpSendTimingDateDiv">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    领取时间范围:
                                </span>
                                <input type="hidden" name="cpSendStartDate" id="cpSendStartDate">
                                <input type="hidden" name="cpSendEndDate" id="cpSendEndDate">
                                <input type="text" class="form-control" name="cpSendTimingDateValid" id="cpSendTimingDateValid">
                            </div>
                        </div>
                        <div class="col-lg-4" id="cpSendCycleDateDiv1">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    开始发送时间:
                                </span>
                                <input type="text" class="form-control" name="cpSendCycleDateValid" id="cpSendCycleDateValid">
                            </div>
                        </div>
                        <div class="col-lg-4" id="cpSendCycleDateDiv2">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    发送周期:
                                </span>
                                <div class="form-control">
                                    <label class="radio-inline">
                                        <input type="radio" name="cpCycleSendType" id="cpCycleSendTypeYear" value="0" checked> 年
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="cpCycleSendType" id="cpCycleSendTypeQuarter"  value="1">季
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="cpCycleSendType" id="cpCycleSendTypeMonth" value="2" checked> 月
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="cpCycleSendType" id="cpCycleSendTypeWeek"  value="3">周
                                    </label>
                                    <label class="radio-inline">
                                        <input type="radio" name="cpCycleSendType" id="cpCycleSendTypeDay"  value="3">日
                                    </label>
                                </div>
                            </div>
                        </div>
                        <div class="col-lg-4" id="cpSendCycleDateDiv3">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    发送次数:
                                </span>
                                <input type="text" class="form-control" name="cpSendNum" id="cpSendNum">
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
                                <input type="text" class="form-control" name="cpTotalMoney" id="cpTotalMoney">
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
                        <!-- 优惠卷指定商品种类 -->
                        <div class="col-lg-4" id="yhjzdspzl">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    指定商品种类:
                                </span>
                                <input type="hidden" class="form-control" name="spzlIds" id="spzlIds">
                                <input type="text" class="form-control" name="spzlNames" id="spzlNames" onclick="showGoodsTypeTrees(); return false;">
                            </div>
                        </div>
                        <!-- 优惠卷指定商品 -->
                        <div class="col-lg-4" id="yhjzdsp">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                    指定商品:
                                </span>
                                <input type="hidden" class="form-control" name="spIds" id="spIds">
                                <input type="text" class="form-control" name="spNames" id="spNames">
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="cpContext" class="control-label">卡券领取与使用规则:</label>
                        <script type="text/plain" id="cpContext" name="cpContext" style="height:300px;">
                        </script>
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
<div id="goodsTypeContext" class="goodsTypeContext" style="display:none; position: absolute;z-index:1059">
    <ul id="goodsTypeTrees" class="ztree" style="margin-top:0; width:320px;"></ul>
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
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorCouponType=0;
    var editSubmitIndex;
    var treeColumns=[
        {checkbox: true},
        {
            field: 'cpName',
            title: '名称'
        },
        {
            field: 'cpType',
            title: '类型',
            formatter:function(value,row,index){
                var html="现金卷";
                if(value==1){
                    html="折扣卷";
                }
                return html;
            }
        },
        {
            field: 'cpLogoUrl',
            title: '卡券图片',
            formatter:function(value,row,index){
                var html="";
                if(value){
                    html ='<img src="'+value+'" width="96px;" height="48px;">';
                }
                return html;
            }
        },
/*        {
            field: 'cpName',
            title: '领取条件'
        },*/
        {
            field: 'cpUseCondition',
            title: '使用条件'
        },
        {
            field: 'cpName',
            title: '使用范围'
        },
        {
            field: 'cpStartDate',
            title: '开始时间'
        },
        {
            field: 'cpEndDate',
            title: '结束时间'
        },
        {
            field: 'cpSendStartDate',
            title: '发放开始时间'
        },
        {
            field: 'cpSendEndDate',
            title: '发放结束时间'
        },
        {
            field: 'cpSendWay',
            title: '发放方式'
        },
        {
            field: 'cpNum',
            title: '总张数'
        },
        {
            field: 'cpMoney',
            title: '金额'
        },
        {
            field: 'cpDiscount',
            title: '折扣'
        },
        {
            field: 'cpIsOverlapUse',
            title: '能否叠加使用',
            formatter:function(value,row,index){
                var html="允许"
                if(value==1){
                    html="禁止";
                }
                return html;
            }
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
                rowDatas.set(row.cpId,row);
                let html='';
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorCoupon(\''+row.cpId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.cpId+'\')"">删除</button>';
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
        $('#editorCoupon').on('hide.bs.modal', function () {
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
            editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorCouponForm").serializeObject();
            var flag =$("#editorCouponForm #isValid").bootstrapSwitch("state");
            var cpIsOverlapUse =$("#editorCouponForm #cpIsOverlapUse").bootstrapSwitch("state");
            var cpIsFirst =$("#editorCouponForm #cpIsFirst").bootstrapSwitch("state");
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
            if(cpIsFirst){
                formData["cpIsFirst"]=0;
            }else{
                formData["cpIsFirst"]=1;
            }

            let url="/coupon/addCoupon";
            if(editorCouponType==1){
                url="/coupon/editorCoupon";
            }
            var context = UE.getEditor('cpContext').getContent();
            formData["cpContext"]=context;
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
                        $('#editorCoupon').modal('hide')
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
        formValidater();
        dateTools("#cpValidDate","#editorCouponForm #cpStartDate","#editorCouponForm #cpEndDate","#editorCouponForm #cpValidDate",false);
        dateTools("#cpSendEndDateValid","","#editorCouponForm #cpSendEndDate","#editorCouponForm #cpSendEndDateValid",true);
        dateTools("#cpSendTimingDateValid","#editorCouponForm #cpSendStartDate","#editorCouponForm #cpSendEndDate","#editorCouponForm #cpSendTimingDateValid",false);
        dateTools("#cpSendCycleDateValid","#editorCouponForm #cpSendStartDate","","#editorCouponForm #cpSendCycleDateValid",true);
        //创建富文本编辑器
        var ue = UE.getEditor('cpContext');
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
        $('#cpImagesForm #dht_image_upload').filestyle({
            btnClass : "btn-primary",
            text:"选择文件",
            onChange:function(){
                editSubmitIndex = layer.load(2);
                cpImagesFormSummit();
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
                    cpUseCondition: {
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
    function deleteData(cpId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeSysUser(cpId);
        }, function(){
        });
    }
    /**
     * 删除部门
     **/
    function removeSysUser(cpId){
        $.ajax({
            type:"post",
            url:'/goodsCoupon/delGoodsCoupon',
            dataType: "json",
            data:{cpId:cpId},
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

    /**
     * 优惠卷编辑
     *
     **/
    function editorCoupon(cpId){
        editorCouponType=1;
        initFormData(cpId);
        $("#editorCouponTitle").text("编辑优惠卷");
        $('#editorCoupon').modal("show");
        //加载商品类型树
        queryGoodsTyps(cpId);
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorCouponForm #cpId").val("");
        $("#editorCouponForm #version").val("");
        $("#editorCouponForm #cpName").val("");
        $("#editorCouponForm #cpUseCondition").val("");
        $("#editorCouponForm #cpStartDate").val("");
        $("#editorCouponForm #cpEndDate").val("");
        $("#editorCouponForm #cpValidDate").val("");
        $("#editorCouponForm #gcpMoney").val("");
        $("#editorCouponForm #gcpDiscount").val("");
        //清空富文本内容
        UE.getEditor('cpContext').setContent('');
        //清空草稿箱
        UE.getEditor('cpContext').execCommand( "clearlocaldata" );
        //清空上传内容
        $('#cpImagesForm #dht_image_upload').filestyle('clear');
        $("#cpImagesForm #uploadImageDiv").hide();
        $("#cpImagesForm #cpLogoDiv").show();
        $("#cpImagesForm #clearCpLogoDiv").hide();
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorCouponForm #cpId").val(rowData.cpId);
            $("#editorCouponForm #version").val(rowData.version);
            $("#editorCouponForm #cpName").val(rowData.cpName);
            $("#editorCouponForm #cpLogo").val(rowData.cpLogo);
            if(rowData.cpLogo){
                $("#cpImagesForm #uploadImageDiv").show();
                $("#cpImagesForm #cpLogoDiv").hide();
                $("#cpImagesForm #clearCpLogoDiv").show();
                $("#cpImagesForm #uploadImage").attr("src",rowData.cpLogoUrl);
            }else{
                $("#cpImagesForm #uploadImageDiv").hide();
                $("#cpImagesForm #cpLogoDiv").show();
                $("#cpImagesForm #clearCpLogoDiv").hide();
            }
            $("#editorCouponForm #cpCoinType").val(rowData.cpCoinType);
            $("#editorCouponForm #cpType").val(rowData.cpType);
            $("#editorCouponForm #cpIsRestricted").val(rowData.cpIsRestricted);
            $("#editorCouponForm #cpUseCondition").val(rowData.cpUseCondition);

            $("#editorCouponForm #cpStartDate").val(rowData.cpStartDate);
            $("#editorCouponForm #cpEndDate").val(rowData.cpEndDate);
            $("#editorCouponForm #cpSendStartDate").val(rowData.cpSendStartDate);
            $("#editorCouponForm #cpSendEndDate").val(rowData.cpSendEndDate);
            if(rowData.cpStartDate){
                $("#editorCouponForm #cpValidDate").val(rowData.cpStartDate+" - "+rowData.cpEndDate);
            }else{
                $("#editorCouponForm #cpValidDate").val("");
            }
            //能否叠加使用
            var cpIsOverlapUse =false;
            if(rowData.cpIsOverlapUse==0){
                cpIsOverlapUse=true;
            }
            $("#editorCouponForm #cpIsOverlapUse").bootstrapSwitch("state",cpIsOverlapUse);
            //是否优先使用
            var cpIsFirst =false;
            if(rowData.cpIsFirst==0){
                cpIsFirst=true;
            }
            $("#editorCouponForm #cpIsFirst").bootstrapSwitch("state",cpIsFirst);
            //发放方式
            $("#editorCouponForm #cpSendWay").val(rowData.cpSendWay);
            //周期发送类型
            $("#editorCouponForm input[name='cpCycleSendType'][value='"+rowData.cpCycleSendType+"']").attr("checked",true);
            $("#editorCouponForm #cpSendNum").val(rowData.cpSendNum);
            if(rowData.cpSendStartDate){
                $("#editorCouponForm #cpSendTimingDateValid").val(rowData.cpSendStartDate+" - "+rowData.cpSendEndDate);
            }else{
                $("#editorCouponForm #cpSendTimingDateValid").val("");
            }
            $("#editorCouponForm #cpNum").val(rowData.cpNum);
            $("#editorCouponForm #cpMoney").val(rowData.cpMoney);
            $("#editorCouponForm #cpDiscount").val(rowData.cpDiscount);
            $("#editorCouponForm #cpTotalMoney").val(rowData.cpTotalMoney);
            $("#editorCouponForm #cpMinDiscount").val(rowData.cpMinDiscount);
            $("#editorCouponForm #cpMaxDiscount").val(rowData.cpMaxDiscount);
            UE.getEditor('cpContext').setContent(rowData.cpContext);
            //设置数据
            if(rowData.cpIsRestricted==1){
                $("#editorCouponForm #spzlIds").val(rowData.relevanceId);
                $("#editorCouponForm #spzlNames").val(rowData.relevanceNm);

            }if(rowData.cpIsRestricted==2){
                $("#editorCouponForm #spIds").val(rowData.relevanceId);
                $("#editorCouponForm #spNames").val(rowData.relevanceNm);
            }

        }else{
            $("#editorCouponForm #cpType").val("0");
            $("#editorCouponForm #cpLogoDiv").show();
            $("#editorCouponForm #gcpDiscountDiv").hide();
            $("#editorCouponForm #cpValidDate").val("");
            $("#editorCouponForm #cpSendEndDateValid").val("");
            $("#editorCouponForm #cpSendTimingDateValid").val("");
            $("#editorCouponForm #cpSendWay").val(0);
            $("#cpImagesForm #cpLogoDiv").show();
            $("#cpImagesForm #clearCpLogoDiv").hide();
            $("#editorCouponForm #spzlIds").val('');
            $("#editorCouponForm #spzlNames").val('');
            $("#editorCouponForm #spIds").val('');
            $("#editorCouponForm #spNames").val('');
        }
        cpCoinTypeChange();
        cpSendWayChange();
        syfwChange();
    }
    /**
     * 编辑部门
     **/
    function addCoupon(){
        editorCouponType=0;
        let orgId,orgPid;
        initFormData();
        $("#uploadImageDiv").hide();
        $("#editorCouponForm #isValid").bootstrapSwitch("state",true);
        $("#editorCouponTitle").text("添加优惠卷");
        $('#editorCoupon').modal("show")
        queryGoodsTyps();
    }
    /**
     * 优惠卷优惠额度变更
     */
    function cpCoinTypeChange(){
        let selectCpCoinType=$("#editorCouponForm #cpCoinType").val();
        let selectCpType=$("#editorCouponForm #cpType").val();
        $("#editorCouponForm #ptzjeDiv").hide();
        $("#editorCouponForm #ptzzkDiv").hide();
        $("#editorCouponForm #psqzjeDiv").hide();
        $("#editorCouponForm #psqzzkDiv").hide();
//        $("#editorCouponForm #yhjzdspzl").hide();
//        $("#editorCouponForm #yhjzdsp").hide();
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
    let fileUpload="/file/imageUpload?isWatermark=false&isCompress=false&imageUse=goods"
    function cpImagesFormSummit(){
        var formData = new FormData($( "#cpImagesForm" )[0]);
        $.ajax({
            url: fileUpload,
            type: 'POST',
            data: formData,
            async: false,
            cache: false,
            contentType: false,
            processData: false,
            dataType: "json",
            success: function (returndata) {
                if(returndata.state=="SUCCESS"){
                    $("#uploadImageDiv").show();
                    $("#cpLogoDiv").hide();
                    $("#clearCpLogoDiv").show();
                    $("#uploadImage").attr("src",returndata.url);
                    $("#editorCouponForm #cpLogo").val(returndata.original);
                }
                layer.close(editSubmitIndex);
            },
            error: function (returndata) {
                layer.close(editSubmitIndex);
            }
        });
    }
    /**
     * 初始化晶期空间
     * @param id
     * @param startDate
     * @param endDate
     * @param showDate
     * @param singleDate
     */
    function dateTools(id,startDate,endDate,showDate,singleDate){
        let daterConfig={};
        daterConfig.startDate= moment();
        daterConfig.minDate= moment();
        daterConfig.showDropdowns= true;
        daterConfig.timePicker=true;
        daterConfig.timePickerIncrement= 5;
        daterConfig.timePicker24Hour=24; //24 小时制
        daterConfig.opens= 'right'; //日期选择框的弹出位置
        daterConfig.singleDatePicker=singleDate; //设置成单日历
        daterConfig.applyClass= 'btn-small btn-primary blue';
        daterConfig.cancelClass= 'btn-small';
        daterConfig.format= 'YYYY-MM-DD HH:mm:ss';
        let locale={};
        locale.format='YYYY-MM-DD HH:mm:ss';
        locale.applyLabel='确定';
        locale.cancelLabel='取消';
        locale.fromLabel='起始时间';
        locale.toLabel='结束时间';
        locale.daysOfWeek= [ '日', '一', '二', '三', '四', '五', '六' ];
        locale.monthNames=[ '一月', '二月', '三月', '四月', '五月', '六月',
            '七月', '八月', '九月', '十月', '十一月', '十二月' ];
        locale.firstDay=1;
        daterConfig.locale=locale;
        $(id).daterangepicker(
            daterConfig, function(start, end, label) {
                if(!singleDate){
                    $(startDate).val(start.format('YYYY-MM-DD HH:mm:ss'));
                    $(endDate).val(end.format('YYYY-MM-DD HH:mm:ss'));
                    $(showDate).val(start.format('YYYY-MM-DD HH:mm:ss') + ' - ' + end.format('YYYY-MM-DD HH:mm:ss'));
                }else{
                    if(startDate){
                        $(startDate).val(start.format('YYYY-MM-DD HH:mm:ss'));
                        $(endDate).val('');
                    }
                    if(endDate){
                        $(startDate).val('');
                        $(endDate).val(start.format('YYYY-MM-DD HH:mm:ss'));
                    }
                    $(showDate).val(start.format('YYYY-MM-DD HH:mm:ss'));
                }
            });
    }
    /**
     * 发送方式改变事件
     */
    function cpSendWayChange() {
        let sendWaySelectVal=$("#editorCouponForm #cpSendWay").val();
        $("#editorCouponForm #cpSendEndDateDiv").hide();
        $("#editorCouponForm #cpSendTimingDateDiv").hide();
        $("#editorCouponForm #cpSendCycleDateDiv1").hide();
        $("#editorCouponForm #cpSendCycleDateDiv2").hide();
        $("#editorCouponForm #cpSendCycleDateDiv3").hide();
        if(parseInt(sendWaySelectVal,10)==0){
            $("#editorCouponForm #cpSendEndDateDiv").show();
            $("#editorCouponForm #cpSendStartDate").val('');
            $("#editorCouponForm #cpSendTimingDateValid").val('');
            $("#editorCouponForm #cpSendCycleDateValid").val('');
        }else if(parseInt(sendWaySelectVal,10)==1){
            $("#editorCouponForm #cpSendTimingDateDiv").show();
            $("#editorCouponForm #cpSendEndDateValid").val('');
            $("#editorCouponForm #cpSendCycleDateValid").val('');
        }else if(parseInt(sendWaySelectVal,10)==2){
            $("#editorCouponForm #cpSendCycleDateDiv1").show();
            $("#editorCouponForm #cpSendCycleDateDiv2").show();
            $("#editorCouponForm #cpSendCycleDateDiv3").show();
            $("#editorCouponForm #cpSendEndDateValid").val('');
            $("#editorCouponForm #cpSendTimingDateValid").val('');
            $("#editorCouponForm #cpSendEndDate").val('');
        }
    }
    //清除文件
    function clearCpLogo(){
        $('#cpImagesForm #dht_image_upload').filestyle('clear');
        $("#cpImagesForm #uploadImageDiv").hide();
        $("#cpImagesForm #cpLogoDiv").show();
        $("#cpImagesForm #clearCpLogoDiv").hide();
        $("#editorCouponForm #cpLogo").val('');
    }
    /**
     * 优惠卷使用范围改变事件
     */
    function syfwChange(){
        let selectVal=$("#editorCouponForm #cpIsRestricted").val();
        $("#editorCouponForm #yhjzdspzl").hide();
        $("#editorCouponForm #yhjzdsp").hide();
        if(selectVal==1){
            $("#editorCouponForm #yhjzdspzl").show();
            $("#editorCouponForm #spIds").val("");
            $("#editorCouponForm #spNames").val("");
        }if(selectVal==2){
            $("#editorCouponForm #yhjzdsp").show();
            $("#editorCouponForm #spzlIds").val("");
            $("#editorCouponForm #spzlNames").val("");
        }
    }
    //当前选中优惠卷id
    var selectCurCouponId;
    //
    var goodsTypsTreesObj;
    var zNodes;
    /**
     * 取得商品类型
     **/
    function queryGoodsTyps(couponId){
        selectCurCouponId=couponId;
        $.fn.zTree.init($("#goodsTypeTrees"), setting, zNodes);
        $.ajax({
            type:"post",//请求方式
            url:"/goods/goodsTypeTree",//发送请求地址
            dataType:"json",
            data:{//发送给数据库的数据
                id:couponId,
                type:1
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
    /***********************************树型菜单********************************************************/
//
//    var orgPermissionTreeSetting = {
//        check: {
//            enable: true
//        },
//        data: {
//            simpleData: {
//                enable: true
//            }
//        }
//    };
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
        var orgPname = $("#spzlNames");
        var orgPid_ = $("#spzlIds");
        orgPname.val(v);
        orgPid_.val(vId);
    }

    function showGoodsTypeTrees() {
        var cityObj = $("#spzlNames");
        var cityOffset = $("#spzlNames").offset();
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
