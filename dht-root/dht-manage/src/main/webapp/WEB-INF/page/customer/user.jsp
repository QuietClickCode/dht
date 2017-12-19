<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>会员管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
</head>
<body>
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
        <select id="search_r_cashback" name="search_type"  class="form-control" style="width: auto;">
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
                    <input type="hidden" name="rid" id="rid">
                    <input type="hidden" name="version" id="version">
                    <input type="hidden" name="rsnapshot" id="rsnapshot">
                    <input type="hidden" name="rlogo" id="rlogo">
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

                    </div>
                    <div class="row">
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
                <form id="rlogoImageForm" method="POST" style="margin-bottom: 0px;" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-lg-4" id="rlogoDiv">
                            <div class="input-group form-group">
                                    <span class="input-group-addon">
                                        会员卡图片:
                                    </span>
                                <input type="file" id="dht_image_upload" name="dht_image_upload">
                            </div>
                        </div>
                        <div class="col-lg-4" id="clearrlogoDiv">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                        会员卡图片:
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
    var editorCustomerType=0;

    var treeColumns=[
        {checkbox: true},
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
                rowDatas.set(row.rid,row);
                let html='';
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorCustomer(\''+row.rid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goodsCoupon/delGoodsCoupon">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.rid+'\')"">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/customer/queryCustomerLists","customerListsTables","orgId",treeColumns,queryParams)
        //初始华开关选择器
        $("#editorCustomerForm #isValid").bootstrapSwitch();
        $("#editorCustomerForm #rcashback").bootstrapSwitch();
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
            var flag =$("#editorCustomerForm #isValid").bootstrapSwitch("state");
            var rcashback =$("#editorCustomerForm #rcashback").bootstrapSwitch("state");
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
            if(editorCustomerType==1){
                url="/recharge/editorCustomer";
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
                        $('#editorCustomer').modal('hide')
                    }else{
                        layer.msg(data.msg);
                    }
                }
            });
        });
        formValidater();
        $('#rlogoImageForm #dht_image_upload').filestyle({
            btnClass : "btn-primary",
            text:"选择文件",
            onChange:function(){
                editSubmitIndex = layer.load(2);
                imagesFormSummit();
            }
        });
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
                        rprice: {
                            message: '充值金额校验',
                            validators: {
                                notEmpty: {
                                    message: '充值金额不能为空'
                                },
                                regexp:{
                                    regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                                    message:'充值金额只允许在2位整数和2位小数范围内'
                                }
                            }
                        },
                        rdiscount: {
                            message: '享受折扣校验',
                            validators: {
                                notEmpty: {
                                    message: '享受折扣不能为空'
                                },
                                regexp:{
                                    regexp:/^([1-9]{1}|0)(\.\d{1,2})?$/,
                                    message:'享受折扣只允许在2位整数和2位小数范围内'
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
    function editorCustomer(gcpId){
        editorCustomerType=1;
        initFormData(gcpId);
        $("#editorCustomerTitle").text("编辑商品优惠");
        $('#editorCustomer').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorCustomerForm #rid").val("");
        $("#editorCustomerForm #version").val("");
        $("#editorCustomerForm #rsnapshot").val("");
        $("#editorCustomerForm #rlogo").val("");
        $("#editorCustomerForm #rprice").val("");
        $("#editorCustomerForm #rdiscount").val("");
        $("#editorCustomerForm #rname").val("");
        $("#editorCustomerForm #rcashback").bootstrapSwitch("state",false);
        $("#editorCustomerForm #isValid").bootstrapSwitch("state",true);
        initRlogoForm();
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorCustomerForm #rid").val(rowData.rid);
            $("#editorCustomerForm #version").val(rowData.version);
            $("#editorCustomerForm #rsnapshot").val(rowData.rsnapshot);
            $("#editorCustomerForm #rlogo").val(rowData.rlogo);
            $("#editorCustomerForm #rprice").val(rowData.rpriceFormater);
            $("#editorCustomerForm #rdiscount").val(rowData.rdiscountFormater);
            $("#editorCustomerForm #rname").val(rowData.rname);
            initRlogoForm(rowData.rlogo,rowData.rlogoUrl);
            var rcashback =false;
            if(rowData.rcashback==0){
                rcashback=true;
            }
            $("#editorCustomerForm #rcashback").bootstrapSwitch("state",rcashback);
            var isValid =false;
            if(rowData.isValid==0){
                isValid=true;
            }
            $("#editorCustomerForm #isValid").bootstrapSwitch("state",isValid);
        }else{
            clearFormData();
        }
    }
    /**
     * 编辑部门
     **/
    function addRecharge(){
        editorCustomerType=0;
        let orgId,orgPid;
        initFormData();
        $("#editorCustomerForm #isValid").bootstrapSwitch("state",true);
        $("#editorCustomerTitle").text("添加充值金额");
        $('#editorCustomer').modal("show")
    }
    let fileUpload=ueditorUploadUrl("goods",false,false);
    /**
     * 图片上传
     */
    function imagesFormSummit(){
        var formData = new FormData($( "#rlogoImageForm" )[0]);
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
                    initRlogoForm(returndata.original,returndata.url);
                }
                layer.close(editSubmitIndex);
            },
            error: function (returndata) {
                layer.close(editSubmitIndex);
            }
        });
    }
    /**
     * 初始化会员卡表单
     * @param rLogo 表单id
     * @param showImageUrl 显示图片
     */
    function initRlogoForm(rLogo,showImageUrl){
        if(rLogo){
            $("#rlogoImageForm #uploadImageDiv").show();
            $("#rlogoImageForm #rlogoDiv").hide();
            $("#rlogoImageForm #clearrlogoDiv").show();
            $("#rlogoImageForm #uploadImage").attr("src",showImageUrl);
            $("#editorCustomerForm #rlogo").val(rLogo);
        }else{
            $("#rlogoImageForm #uploadImageDiv").hide();
            $("#rlogoImageForm #rlogoDiv").show();
            $("#rlogoImageForm #clearrlogoDiv").hide();
        }
    }
</script>
</body>
</html>
