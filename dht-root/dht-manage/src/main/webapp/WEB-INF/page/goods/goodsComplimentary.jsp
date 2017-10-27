<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>赠品管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">

    <link rel="stylesheet" href="<%=path%>/js/daterangepicker/daterangepicker.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/font-awesome-4.1.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <style>
        li{
            list-style:none;
        }
    </style>
</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoodsComplimentary">
        <button class="btn btn-primary" type="button" onclick="addGoodsComplimentary()">添加赠品</button>
    </ex:perm>

    <br>
    <div class="form-group" style="margin-top: 5px">
        <input type="text" class="form-control" id="search_GoodsComplimentary_name" placeholder="请输入赠品名称">
    </div>

    <ex:perm url="goods/queryGoodsComplimentaryLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()" style="margin-top: 5px">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsComplimentaryTables" ></table>
</div>
<div class="modal fade" id="editorGoodsComplimentary" tabindex="-1" role="dialog" aria-labelledby="editorGoodsComplimentary">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorGoodsComplimentaryTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsComplimentaryForm">
                    <input type="hidden" name="gcId" id="gcId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                赠品名称:
                              </span>
                                <input type="text" class="form-control" name="gcName" id="gcName">
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                 赠品赠送条件:
                              </span>
                                <input id="gcCondition" name="gcCondition" type="text" class="form-control"style="width: 62%">
                                <select class="form-control" id="gcType" name="gcType" style="width: 38%">
                                    <option value="1">元</option>
                                    <option value="0">件</option>
                                </select>
                            </div>
                        </div>

                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                是否叠加:
                              </span>
                                <div class="controls">
                                    <div class="switch" tabindex="0">
                                        <input id="isMultiuse" name="isMultiuse" type="checkbox" />
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

                        <div class="col-lg-6">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                赠送商品名称:
                              </span>
                                <input type="hidden" name="gid" id="gid">
                                <input id="gname" name="gname" type="text" class="form-control" disabled="disabled"/>
                            </div>
                        </div>
                    </div>
                </form>

                <center>
                    <div class="form-group" style="margin-top: 5px;display: inline-block">
                        <input type="text" class="form-control" id="search_Goods_name" placeholder="请输入商品名称">
                    </div>
                    <button class="btn btn-default" type="button" onclick="searchgoods()">查询</button>
                </center>
                <div class="row clearfix">
                    <div class="col-md-12 column">
                        <table class="table table-bordered">
                            <thead>
                            <tr>
                                <th style="width: 30px;text-align: center">
                                    &nbsp;
                                </th>
                                <th style="text-align: center" id="topgoodsname">
                                    商品名称
                                </th>
                            </tr>
                            </thead>
                            <tbody id="goodsTbody">

                            </tbody>
                        </table>
                    </div>
                </div>
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
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<!--赠品编辑-->
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsComplimentaryType=0;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gcName',
            title: '赠品名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'CreateTime',
            title: '操作',
            align : 'center',
            valign : 'middle',
            width:240,
            formatter:function(value,row,index){
                rowDatas.set(row.gcId,row);
                let html='';
                <ex:perm url="goods/editGoodsComplimentary">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsComplimentary(\''+row.gcId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoodsComplimentary">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gcId+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsComplimentaryLists","GoodsComplimentaryTables","gcId",treeColumns,queryParams)
        //初始华开关选择器
        $('#editorGoodsComplimentaryForm #isValid').bootstrapSwitch();
        $('#editorGoodsComplimentaryForm #isMultiuse').bootstrapSwitch();

        $('#editorGoodsComplimentary').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            clearFormValidation("editorGoodsComplimentaryForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsComplimentaryForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorGoodsComplimentaryForm').data('bootstrapValidator').isValid()){
                return;
            }

            if($('#gid').val()==''){
                layer.msg("请选择商品！");
                return;
            }

            var formData=$("#editorGoodsComplimentaryForm").serializeObject();

            var flag = false;
            flag = $("#editorGoodsComplimentaryForm #isValid").bootstrapSwitch("state");
            if(flag){
                formData["isValid"] = 1;
            }else{
                formData["isValid"] = 0;
            }

            flag = $("#editorGoodsComplimentaryForm #isMultiuse").bootstrapSwitch("state");
            if(flag){
                formData["isMultiuse"] = 1;
            }else{
                formData["isMultiuse"] = 0;
            }

            let url="/goods/addGoodsComplimentary";
            if(editorGoodsComplimentaryType==1){
                url="/goods/editGoodsComplimentary";
            }
            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    if(data.status==0){
                        //显示提示
                        layer.msg(data.msg);
                        //刷新数据
                        refreshTableData();
                        //关闭弹窗
                        $('#editorGoodsComplimentary').modal('hide')
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
        $('#editorGoodsComplimentaryForm')
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
                    gcName: {
                        message: '赠品名称未通过',
                        validators: {
                            notEmpty: {
                                message: '赠品名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '赠品名称长度在1-30之间'
                            }
                        }
                    },
                    gname: {
                        message: '赠送商品名称未通过',
                        validators: {
                            notEmpty: {
                                message: '赠送商品名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '赠送商品名称长度在1-30之间'
                            }
                        }
                    },
                    gcCondition: {
                        message: '赠送商品条件未通过',
                        validators: {
                            notEmpty: {
                                message: '赠送商品条件不能为空'
                            },
                            regexp:{
                                regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                                message:'优惠金额只允许在10位整数和2位小数范围内'
                            }
                        }
                    },

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
            gcName: $("#search_GoodsComplimentary_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsComplimentaryTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsComplimentaryLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsComplimentary(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品大类
     **/
    function removeGoodsComplimentary(glId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsComplimentary',
            dataType: "json",
            data:{gcId:glId},
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
    function editorGoodsComplimentary(orgId){
        editorGoodsComplimentaryType=1;
        initFormData(orgId);
        $("#editorGoodsComplimentaryTitle").text("编辑赠品");
        $('#editorGoodsComplimentary').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsComplimentaryForm #isMultiuse").bootstrapSwitch("state",false);
        $("#editorGoodsComplimentaryForm #isValid").bootstrapSwitch("state",true);
        $("#editorGoodsComplimentaryForm #gcId").val('');
        $("#editorGoodsComplimentaryForm #gcName").val('');
        $("#editorGoodsComplimentaryForm #gcCondition").val('');
        $("#editorGoodsComplimentaryForm #gcType").val('1');
        $("#editorGoodsComplimentaryForm #gid").val('');
        $("#editorGoodsComplimentaryForm #gname").val('');

        $('#goodsTbody').html('');
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            $("#editorGoodsComplimentaryForm #gcId").val(rowData.gcId);
            $("#editorGoodsComplimentaryForm #version").val(rowData.version);
            $("#editorGoodsComplimentaryForm #gcName").val(rowData.gcName);
            $("#editorGoodsComplimentaryForm #gcCondition").val(rowData.gcCondition);
            $("#editorGoodsComplimentaryForm #gcType").val(rowData.gcType);
            $("#editorGoodsComplimentaryForm #gid").val(rowData.gid);
            $("#editorGoodsComplimentaryForm #gname").val(rowData.gname);

            var flag = false;
            if(rowData.isMultiuse==1){
                flag = true;
            }
            $("#editorGoodsComplimentaryForm #isMultiuse").bootstrapSwitch("state",flag);

            flag = false;
            if(rowData.isValid==1){
                flag = true;
            }
            $("#editorGoodsComplimentaryForm #isValid").bootstrapSwitch("state",flag);

        }else{
            clearFormData();
        }
    }
    /**
     * 添加商品大类
     **/
    function addGoodsComplimentary(){
        editorGoodsComplimentaryType=0;
        initFormData(-1);
        $("#editorGoodsComplimentaryTitle").text("添加赠品");
        $('#editorGoodsComplimentary').modal("show")
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
            onCheck: onCheck
        }
    };

    function onCheck(e, treeId, treeNode) {
        var zTree = $.fn.zTree.getZTreeObj("orgTree"),
            nodes = zTree.getCheckedNodes(),
            v = "",vId="";
        nodes.sort(function compare(a,b){return a.id-b.id;});
        for (var i=0, l=nodes.length; i<l; i++) {
            v += nodes[i].name+",";
            vId += nodes[i].id+",";
        }
        var orgPname = $("#orgNms");
        var orgPid_ = $("#orgIds");
        orgPname.val(v);
        orgPid_.val(vId);
        //hideOrgTree();
    }

    function showOrgTree() {
        var cityObj = $("#orgNms");
        var cityOffset = $("#orgNms").offset();
        $("#orgNodeContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");
        $("body").bind("mousedown", onBodyDown);
    }
    function hideOrgTree() {
        $("#orgNodeContent").fadeOut("fast");
        $("body").unbind("mousedown", onBodyDown);
    }
    function onBodyDown(event) {
        if (!(event.target.id == "menuBtn" || event.target.id == "orgNodeContent" || $(event.target).parents("#orgNodeContent").length>0)) {
            hideOrgTree();
        }
    }
    
    function deleteGoodsComplimentaryList() {
        var objs = $('#GoodsComplimentaryTables') .bootstrapTable('getAllSelections');
        if(objs.length>0){
            layer.confirm('确定要删除选中的数据吗？', {
                btn: ['确认','取消'] //按钮
            }, function(){
                for(var i=0;i<objs.length;i++){
                    removeGoodsComplimentary(objs[i].glId);
                }
            }, function(){
            });
        }else{
            layer.msg("请选择需要删除的品牌！");
        }
    }


</script>

<script>
    function searchgoods() {
        var gname = $('#search_Goods_name').val();
        if(gname==''){
            layer.msg("商品名称不能为空");
            return;
        }

        $.ajax({
            type:"post",
            url:"/goods/queryGoodsLists",
            dataType: "json",
            data:{gname:gname,pageNo:1,pageSize:100},
            success:function(data){
                var rows = data.rows;
                var html = '';
                if(rows!=null && rows.length>0){
                    for(var i=0;i<rows.length;i++){
                        html += '<tr>'+
                                '<td>'+
                                '<div class="radio radio-info">'+
                                '<input id="radio'+i+'"  type="radio" name="goodsradio" onclick="clickradio(this)" value="'+rows[i].gid+'">'+
                                '<label for="radio'+i+'">'+
                                '</label>'+
                                '</div>'+
                                '</td>'+
                                '<td style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                                rows[i].gname+
                                '</td>'+
                                '</tr>';
                    }
                }
                if(html == ''){
                    html += '<tr>'+
                            '<td colspan="2" style="text-align: center;display:table-cell; vertical-align:bottom;">'+
                            '<span style="line-height: 100%;color:red;">暂时没有找到符合要求的商品</span>'+
                            '</td>'+
                            '</tr>';
                }
                $('#goodsTbody').html(html);


            }
        });
    }
    function clickradio(obj) {
        var gid = $(obj).val();
        var gname = $($(obj).parent().parent().parent().children().get(1)).html();;

        $('#editorGoodsComplimentaryForm #gid').val(gid);
        $('#editorGoodsComplimentaryForm #gname').attr('disabled');
        $('#editorGoodsComplimentaryForm #gname').val(gname);
    }
</script>
</body>
</html>
