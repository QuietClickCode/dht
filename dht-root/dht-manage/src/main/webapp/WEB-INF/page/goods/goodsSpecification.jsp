<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品规格管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoodsSpecification">
        <button class="btn btn-primary" type="button" onclick="addGoodsSpecification()" style="margin-bottom: 5px">添加商品规格</button>
    </ex:perm>
    <br>
    <div class="form-group" >
        <input type="text" class="form-control" id="search_GoodsSpecification_name" placeholder="请输入商品规格名称">
    </div>

    <ex:perm url="goods/queryGoodsSpecificationLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsSpecificationTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <form id="editorGoodsSpecificationForm">
                    <input type="hidden" name="gsId" id="gsId">
                    <input type="hidden" name="version" id="version">
                    <div class="row">
                        <div class="col-lg-12">
                            <div class="input-group form-group">
                              <span class="input-group-addon">
                                商品规格名称:
                              </span>
                                <input type="text" class="form-control" name="gsName" id="gsName">
                            </div>
                        </div>
                        <div class="col-lg-12">
                            <div class="input-group">
                              <span class="input-group-addon">
                                添加商品规格值:
                              </span>
                                <button class="btn btn-default" onclick="addvalues()">+</button>
                            </div>
                        </div>
                    </div>
                    <br>
                </form>
                    <div class="row" id="gsvaldiv">
                        <div class="col-lg-3" style="color: red;">
                            <div class="input-group form-group">
                            <input class="form-control" type="text" placeholder="请输入规格值" style="float: left;width: 70%">
                            <span  style="float: left;margin-left:3px;margin-top: 5px; display: block">删除</span>
                            </div>
                        </div>
                    </div>

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal" id="close">关闭</button>
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
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsSpecificationType=0;
    var orgPermissionTreeObj;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gsName',
            title: '商品规格名称',
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
                rowDatas.set(row.gsId,row);
                let html='';
                <ex:perm url="goods/editGoodsSpecification">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsSpecification(\''+row.gsId+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                <ex:perm url="goods/removeGoodsSpecification">
                html+='<button type="button" id="myButton" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();deleteData(\''+row.gsId+'\',this)">删除</button>';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsSpecificationLists","GoodsSpecificationTables","gtId",treeColumns,queryParams)
        //初始华开关选择器


        $('#editorSysUser').on('hide.bs.modal', function () {
            //清除数据
            clearFormData();
            //隐藏下拉菜单
            hideOrgTree();
            clearFormValidation("editorGoodsSpecificationForm",formValidater)
        });

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){
            //开启校验
            $('#editorGoodsSpecificationForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#editorGoodsSpecificationForm').data('bootstrapValidator').isValid()){
                return;
            }
            var gsvals = $("input[name='gsval']");
            var flag = 0;
            for(var i=0; i<gsvals.length; i++){
                if(gsvals[i].value==''){
                    flag =1;
                }
            }
            if(flag == 1){
                layer.msg('请将数据填写完整！');
                return;
            }

            var editSubmitIndex = layer.load(2);

            var sendData=new Array();
            var formData=$("#editorGoodsSpecificationForm").serializeObject();

            let url="/goods/addGoodsSpecification";
            if(editorGoodsSpecificationType==1){
                url="/goods/editGoodsSpecification";
            }

            //取得form表单数据
            $.ajax({
                type:"post",
                url:url,
                dataType: "json",
                data:formData,
                success:function(data){
                    layer.close(editSubmitIndex);
                    var goodsSpecification = data.goodsSpecification;
                    if(url == '/goods/addGoodsSpecification'){
                        if(goodsSpecification!=null){
                            //显示提示
                            layer.msg("操作成功！");
                            //刷新数据
                            refreshTableData();
                            //关闭弹窗
                            $('#editorSysUser').modal('hide');

                            if(url=='/goods/addGoodsSpecification'){
                                formData["gsId"]=goodsSpecification.gsId;
                            }

                            uploadgsvs(formData["gsId"]);
                            refreshTableData();
                            $('#editorSysUser').modal("hide");
                        }else{
                            layer.msg("操作失败！");
                        }
                    }else {
                        if (data.status ==0){
                            uploadgsvs(formData["gsId"]);
                            layer.msg("操作成功！");
                            refreshTableData();
                            $('#editorSysUser').modal("hide");
                        }else {
                            layer.msg("操作失败！");
                        }
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
        $('#editorGoodsSpecificationForm')
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
                    gsName: {
                        message: '商品规格名称未通过',
                        validators: {
                            notEmpty: {
                                message: '商品规格名称不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '商品规格名称长度在1-30之间'
                            }
                        }
                    },
                    gsval: {
                        message: '商品规格值未通过',
                        validators: {
                            notEmpty: {
                                message: '商品规格值不能为空'
                            },
                            stringLength: {
                                min: 1,
                                max: 30,
                                message: '商品规格值长度在1-30之间'
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
            gsName: $("#search_GoodsSpecification_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsSpecificationTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsSpecificationLists"
            }
        );
    }
    //删除确认框
    function deleteData(gtId){
        //询问框
        layer.confirm('确定要删除选中的数据吗？', {
            btn: ['确认','取消'] //按钮
        }, function(){
            removeGoodsSpecification(gtId);
        }, function(){
        });
    }
    /**
     * 删除商品规格
     **/
    function removeGoodsSpecification(gsId){
        $.ajax({
            type:"post",
            url:'/goods/removeGoodsSpecification',
            dataType: "json",
            data:{gsId:gsId},
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
    function editorGoodsSpecification(orgId){
        editorGoodsSpecificationType=1;
        initFormData(orgId);
        $("#editorSysUserTitle").text("编辑商品规格");
        $('#editorSysUser').modal("show")
    }
    /**
     * 清除form 表单数据
     * */
    function clearFormData(){
        $("#editorGoodsSpecificationForm #gsName").val("");
        $("#gsId").val("");
        $("#editorGoodsSpecificationForm #version").val("");
        $('#gsvaldiv').html('');
    }
    /**
     * 清除form 表单数据
     * */
    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            clearFormData();
            $('#gsId').val(rowData.gsId);
            $('#version').val(rowData.version);
            $('#gsName').val(rowData.gsName);
            $.ajax({
                type:"post",
                url:'/goods/queryGoodsGsvalLists',
                dataType: "json",
                data:{gsId:rowData.gsId,PageNo:1,pageSize:100},
                success:function(data){
                    var gsvals = data.rows;
                    var gsvaldiv = $('#gsvaldiv');
                    if(gsvals!=null){
                        for(var i=0; i<gsvals.length; i++){
                            var html = '<div class="col-lg-3" style="color: red;">'+
                                '<div class="input-group form-group">' +
                                '<input value="'+gsvals[i].gsvId+'" type="hidden">'+
                                '<input class="form-control" onfocus="focusgsval(this)" onblur="blurgsval(this)" value="'+gsvals[i].gsvVal+'" type="text" name="gsval" placeholder="请输入规格值" style="float: left;width: 70%">'+
                                '<span onclick="deletegsval(this)"  style="float: left;margin-left:3px;margin-top: 5px; display: block;cursor: pointer">删除</span>'+
                                '</div>'+
                                '</div>';
                            gsvaldiv.prepend(html);
                        }
                    }
                }
            });


        }else{
            clearFormData();
        }
    }
    /**
     * 添加商品规格
     **/
    function addGoodsSpecification(){
        editorGoodsSpecificationType=0;
        initFormData(-1);

        $("#editorSysUserTitle").text("添加商品规格");
        $('#editorSysUser').modal("show")
    }

    var newArr = new Array();
    var delArr = new Array();
    var updArr = new Array();
    var gsvindex = '';
    /**
     * 添加规格值输入框
     */
    function addvalues() {
        var gsvaldiv = $('#gsvaldiv');
        var html = '<div class="col-lg-3" style="color: red;">'+
            '<div class="input-group form-group">'+
            '<input class="form-control newval" onfocus="focusgsval(this)" onblur="blurgsval(this)" type="text" name="gsval" placeholder="请输入规格值" style="float: left;width: 70%">'+
            '<span onclick="deletegsval(this)"  style="float: left;margin-left:3px;margin-top: 5px; display: block;cursor: pointer">删除</span>'+
            '</div>'+
            '</div>';
        gsvaldiv.prepend(html);
        var firstchild = gsvaldiv.children('div').get(0);
        newArr.push($($(firstchild).children().get(0)).children().get(0));
    }
    /**
     * 刪除规格值输入框
     */
    function deletegsval(obj) {
        var flag = 0;
        var delparent = $(obj).parent().parent();
        for(var i=0; i<newArr.length; i++){
            if(delparent.is($(newArr[i]))){
                $(newArr).splice(i,1);
                flag = 1;
            }
        }
        if(flag == 0){
            delArr.push($(obj).prevAll("[type=hidden]")[0]);
        }
        $(obj).parent().parent().remove();

    }

    function focusgsval(obj) {
        gsvindex = $(obj).val();
    }
    function blurgsval(obj) {
        if(gsvindex!=$(obj).val()){
            var myflag = 0;
            for(var i=0; i<newArr.length; i++){
                if($(obj).is($(newArr[i]))){
                    myflag = 1;
                }
            }
            if(myflag == 0){
                updArr.push(obj);
            }

        }
    }

    function uploadgsvs(gsId) {
//        alert("刪除數組"+delArr.length);
//        alert("新增數組"+$('.newval').length);
//        alert("更新數組"+updArr.length);

        <!--新增-->
        savegsvs(gsId);
        <!--刪除-->
        deletegsvs();
        <!--修改-->
        updategsvs(gsId);

        delArr=[];
        newArr=[];
        updArr=[];
    }

    function savegsvs(gsId) {
        for(var i=0; i<newArr.length; i++){
            $.ajax({
                type:"post",
                url:'/goods/addGoodsGsval',
                dataType: "json",
                data:{gsId:gsId,gsvVal:newArr[i].value},
                success:function(data){
                    var status = data.status;
                    if(status==-1){
                        layer.msg(data.msg);
                    }
                }
            });
        }
    }
    function deletegsvs() {
        for(var i=0; i<delArr.length; i++){
            $.ajax({
                type:"post",
                url:'/goods/removeGoodsGsval',
                dataType: "json",
                data:{gsvId:delArr[i].value},
                success:function(data){

                }
            });
        }
    }
    function updategsvs(gsId) {
        for(var i=0; i<updArr.length; i++){
            console.log(updArr);
            var nowobj = $(updArr[i]);
            var hideobj = nowobj.prevAll("[type=hidden]").get(0);
            $.ajax({
                type:"post",
                url:'/goods/editGoodsGsval',
                dataType: "json",
                data:{gsvId:hideobj.value,gsvVal:nowobj.val(),gsId:gsId,isDelete:0},
                success:function(data){
                    var status = data.status;
                    if(status==-1){
                        layer.msg(data.msg);
                    }
                }
            });
        }
    }


    $('#close').click(function () {
        delArr=[];
        newArr=[];
        updArr=[];
    });
</script>


<script>

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
</script>
</body>
</html>
