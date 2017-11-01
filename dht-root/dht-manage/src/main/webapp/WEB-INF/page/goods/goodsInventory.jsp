<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品库存管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
</head>
<body>
<div id="toolbar" class="form-inline">
    <br>
    <div class="form-group" >
        <input type="text" class="form-control" id="search_Goods_name" placeholder="请输入商品名称">
    </div>

    <ex:perm url="/goods/queryGoodsLists">
        <button class="btn btn-default" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="GoodsInventoryTables" ></table>
</div>
<div class="modal fade" id="editorSysUser" tabindex="-1" role="dialog" aria-labelledby="editorSysUser">
    <div class="modal-dialog" role="document"  style="width: 800px;">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="editorSysUserTitle"></h4>
            </div>
            <div class="modal-body">
                <div id="goodsSpecificatiodiv" style="display: none;">

                </div>
                <div id="gstabeldiv" class="row clearfix" style="margin-top: 5px">
                    <input type="hidden" name="gid" id="gid">
                    <div class="col-md-12 column">
                        <table class="table table-bordered" id="gstabel">

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



<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<!--商品库存-->
<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    //编辑部门类型 0 新增 1 修改
    var editorGoodsInventoryType=0;
    var treeColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gname',
            title: '商品名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'inventory',
            title: '库存',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'residueInventory',
            title: '剩余库存',
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
                rowDatas.set(row.gid,row);
                let html='';
                <ex:perm url="goods/editGoodsInventory">
                html+='<button type="button" data-loading-text="Loading..." class="btn btn-primary" autocomplete="off" onclick="event.stopPropagation();editorGoodsInventory(\''+row.gid+'\')"">编辑</button>&nbsp;';
                </ex:perm>
                return html;
            }
        }
    ]

    $(function () {
        createTable("/goods/queryGoodsInventoryLists","GoodsInventoryTables","gid",treeColumns,queryParams)

        //编辑按钮提交操作
        $("#editSubmit").click("click",function(e){

            var check = $('#ggsvaldetailTbody').find('input[type=text]');
            if(check!=null && check.length>0) {
                for (var i = 0; i < check.length; i++) {
                    if(isNaN(check[i].value)){
                        layer.msg("请填写数字");
                        return;
                    }
                }
            }
            url="/goods/editGoodsInventory";
            var trs = $('#ggsvaldetailTbody').children();
            if(trs!=null && trs.length>0){
                for(var i=0;i<trs.length;i++){
                    var tds = $(trs[i]).children();

                    var gdId = $(tds[tds.length-4]).find('input[type=hidden]')[0].value;
                    var addinventory = $(tds[tds.length-2]).find('input')[0].value;
                    var reduceinventory = $(tds[tds.length-1]).find('input')[0].value;

                    var inventory = '';
                    if(addinventory!=''){
                        inventory = addinventory;
                    }else if(reduceinventory!=''){
                        inventory = '-'+reduceinventory;
                    }

                    if(inventory!=''){
                        $.ajax({
                            type:"post",
                            url:"/goods/editGoodsInventory",
                            dataType: "json",
                            async :false,
                            data:{gdId:gdId,inventory:inventory},
                            success:function(data){
                                if(data.status==0){
                                    layer.msg("操作成功");
                                }else {
                                    layer.msg("操作失败");
                                }
                            }
                        });
                    }
                }
            }
            $('#editorSysUser').modal("hide");
            refreshTableData();
        });

    });
    /**
     * 查询条件
     **/
    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            gname: $("#search_Goods_name").val(),
        };
    }
    /**
     * 刷新表格数据
     **/
    function refreshTableData() {
        $('#GoodsInventoryTables').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsInventoryLists"
            }
        );
    }

    function editorGoodsInventory(gid){
        editorGoodsInventoryType=1;
        initFormData(gid);
        $('#gstabel').html('');
        $("#editorSysUserTitle").text("编辑库存");
        $('#editorSysUser').modal("show");
    }

    function initFormData(key){
        var rowData=rowDatas.get(parseInt(key,10));
        if(rowData){
            var gid = rowData.gid;
            $('#gid').val(gid);
            getgoods(gid);
        }
    }

</script>

<script>

    function getgoods(gid){
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsById",
            dataType: "json",
            data:{gid:gid},
            success:function(data){
                var goods = data.row;
                if(goods!=null){
                    getggHome(goods.gclassification);
                }
            }
        });
    }

    function getggHome(ggId) {
        $.ajax({
                type:"post",
                url:"/goods/queryGoodsClassificationById",
                dataType: "json",
                data:{ggId:ggId,pageNo:1,pageSize:2},
                success:function(data){
                var goodsClassification = data.goodsClassification;
                if(goodsClassification!=null){
                    $.ajax({
                        type:"post",
                        url:"/goods/queryGoodsTypeById",
                        dataType: "json",
                        data:{gtId:goodsClassification.ggHome},
                        success:function(data){
                            var goodsType = data.goodsType;
                            if(goodsType!=null && goodsType.isSpecification==1){
                                loadSpecifications(goodsType.gtId);
                            }

                        }
                    });

                }
            }
        });
    }

    function loadSpecifications(gtId) {
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGtgsrelLists",
            dataType: "json",
            data:{gtId:gtId,pageNo:1,pageSize:100},
            success:function(data){
                var gtgsrels = data.rows;
                var html = '';
                if(gtgsrels != null && gtgsrels.length>0){
                    for(var i=0; i<gtgsrels.length; i++){
                        html += '<div class="row">'+
                            '<div class="col-lg-1" style="text-align: right;margin-top: 10px">'+
                            '<input type="hidden" name="specificationId" value="'+gtgsrels[i].gsId+'">'+
                            '<span >'+gtgsrels[i].gsname+'</span>'+
                            '</div>'+
                            '<div class="col-lg-11">'+
                            '<div class="row">'+
                            '</div>'+
                            '</div>'+
                            '</div>';
                    }
                    $('#goodsSpecificatiodiv').html(html);
                    loadgsvals();
                }
            }
        });
    }
    <!--加载规格值-->
    function loadgsvals() {
        var gsIds = $('input[name="specificationId"]');
        if(gsIds!=null && gsIds.length>0){
            for(var i=0; i<gsIds.length; i++){
                loadgsval(gsIds[i],i,gsIds.length-1);
            }
        }
    }
    function loadgsval(obj,x,y) {
        var showobj = $($(obj).parent().next().children().get(0));
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsGsvalLists",
            dataType: "json",
            async:false,
            data:{gsId:$(obj).val(),pageNo:1,pageSize:100},
            success:function(data){
                var html = '';
                var rows = data.rows;
                if(rows!=null && rows.length>0){
                    for(var i=0; i<rows.length; i++){
                        html += '<div class="col-lg-2">'+
                            '<div class="checkbox checkbox-info" style="display: block">'+
                            '<input onclick="createtabel();" id="gsvalId'+i+rows[i].gsvVal+'" class="styled" name="gsvalId" type="checkbox" value="'+rows[i].gsvId+'">'+
                            '<label for="gsvalId'+i+rows[i].gsvVal+'">'+ rows[i].gsvVal+'</label>'+
                            '</div>'+
                            '</div>';
                    }
                    showobj.html(html);
                    if(x==y){
                        var gid = $('#gid').val();
                        inithavegsandcreatetabel(gid);
                    }
                }
            }
        });
    }
    <!--创建表格-->
    function createtabel() {
        var gsIds = new Array();
        var gsNames = new Array();
        var gsvalId = $('input[name="gsvalId"]:checked');
        var index = 0;
        var a = 0;
        for(var i=0; i<gsvalId.length; i++){
            var parentdiv = $($(gsvalId[i]).parent().parent().parent().parent().prevAll().get(0));
            var gsid = $(parentdiv.find('input').get(0));
            if(gsIds.length==0){
                gsIds.push(gsid);
                gsNames.push($(parentdiv.find('span').get(0)));
            }else{
                var flag = false;
                for (var j=0; j<gsIds.length; j++){
                    if(gsid.val() == gsIds[j].val()){
                        flag = true;
                    }
                }
                if(!flag){
                    gsIds.push(gsid);
                    gsNames.push($(parentdiv.find('span').get(0)));
                }
            }

        }

        index = gsIds.length;
        if(index == 0){
            $('#gstabeldiv').hide();
        }else{
            var html = '<thead><tr>';

            for(var i=0; i<gsNames.length; i++){
                html += '<th > <input type="hidden" value="'+gsIds[i].val()+'">'+gsNames[i].html()+'</th>';
            }
            html += '<th >'+
                '库存'+
                '</th>'+
                '<th >'+
                '剩余库存'+
                '</th>'+
                '<th >'+
                '添加库存'+
                '</th>'+
                '<th >'+
                '减少库存'+
                '</th>'+
                '</tr>'+
                '</thead>'+
                '<tbody id="ggsvaldetailTbody">';
            if(index == 1){
                var tabelIdArr = new Array();
                var tabelNameArr = new Array();
                for(var i=0; i<gsvalId.length; i++){
                    tabelIdArr[i] = gsvalId[i].value;
                    tabelNameArr[i] = $(gsvalId[i]).next().html();
                }
                for(var i=0; i<tabelIdArr.length; i++){
                    html += '<tr>'+
                        '<td> <input type="hidden" value="'+tabelIdArr[i]+'"> '+tabelNameArr[i]+' </td>'+
                        '<td> <input disabled="disabled" class="form-control" type="text" placeholder="库存"> </td>'+
                        '<td> <input disabled="disabled" class="form-control" type="text" placeholder="剩余库存"> </td>'+
                        '<td> <input class="form-control" type="text" placeholder="请输入添加库存量"> </td>'+
                        '<td> <input class="form-control" type="text" placeholder="请输入减少库存量"> </td>'+
                        '</tr>';
                }
            }
            if(index == 2){
                var gsvalNamesArr1 = new Array();
                var gsvalNamesArr2 = new Array();

                var gsvalIdsArr1 = gsIds[0].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr1.length; i++){
                    gsvalNamesArr1.push($(gsvalIdsArr1[i]).next());
                }

                var gsvalIdsArr2 = gsIds[1].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr2.length; i++){
                    gsvalNamesArr2.push($(gsvalIdsArr2[i]).next());
                }

                for(var i=0; i<gsvalIdsArr1.length; i++){
                    for(var j=0; j<gsvalIdsArr2.length; j++){
                        html += '<tr>'+
                            '<td> <input type="hidden" value="'+gsvalIdsArr1[i].value+'"> '+gsvalNamesArr1[i].html()+' </td>'+
                            '<td> <input type="hidden" value="'+gsvalIdsArr2[j].value+'"> '+gsvalNamesArr2[j].html()+' </td>'+
                            '<td> <input disabled="disabled" class="form-control" type="text" placeholder="库存"> </td>'+
                            '<td> <input disabled="disabled" class="form-control" type="text" placeholder="剩余库存"> </td>'+
                            '<td> <input class="form-control" type="text" placeholder="请输入添加库存量"> </td>'+
                            '<td> <input class="form-control" type="text" placeholder="请输入减少库存量"> </td>'+
                            '</tr>';
                    }
                }
            }
            if(index == 3){
                var gsvalNamesArr1 = new Array();
                var gsvalNamesArr2 = new Array();
                var gsvalNamesArr3 = new Array();

                var gsvalIdsArr1 = gsIds[0].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr1.length; i++){
                    gsvalNamesArr1.push($(gsvalIdsArr1[i]).next());
                }

                var gsvalIdsArr2 = gsIds[1].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr2.length; i++){
                    gsvalNamesArr2.push($(gsvalIdsArr2[i]).next());
                }

                var gsvalIdsArr3 = gsIds[2].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr3.length; i++){
                    gsvalNamesArr3.push($(gsvalIdsArr3[i]).next());
                }

                for(var i=0; i<gsvalIdsArr1.length; i++){
                    for(var j=0; j<gsvalIdsArr2.length; j++){
                        for(var x=0; x<gsvalIdsArr3.length; x++){
                            html += '<tr>'+
                                '<td> <input type="hidden" value="'+gsvalIdsArr1[i].value+'"> '+gsvalNamesArr1[i].html()+' </td>'+
                                '<td> <input type="hidden" value="'+gsvalIdsArr2[j].value+'"> '+gsvalNamesArr2[j].html()+' </td>'+
                                '<td> <input type="hidden" value="'+gsvalIdsArr3[x].value+'"> '+gsvalNamesArr3[x].html()+' </td>'+
                                '<td> <input class="form-control" type="text" disabled="disabled" placeholder="库存"> </td>'+
                                '<td> <input class="form-control" type="text" disabled="disabled" placeholder="剩余库存"> </td>'+
                                '<td> <input class="form-control" type="text" placeholder="请输入添加库存量"> </td>'+
                                '<td> <input class="form-control" type="text" placeholder="请输入减少库存量"> </td>'+
                                '</tr>';
                        }
                    }
                }
            }
            if(index == 4){
                var gsvalNamesArr1 = new Array();
                var gsvalNamesArr2 = new Array();
                var gsvalNamesArr3 = new Array();
                var gsvalNamesArr4 = new Array();

                var gsvalIdsArr1 = gsIds[0].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr1.length; i++){
                    gsvalNamesArr1.push($(gsvalIdsArr1[i]).next());
                }

                var gsvalIdsArr2 = gsIds[1].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr2.length; i++){
                    gsvalNamesArr2.push($(gsvalIdsArr2[i]).next());
                }

                var gsvalIdsArr3 = gsIds[2].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr3.length; i++){
                    gsvalNamesArr3.push($(gsvalIdsArr3[i]).next());
                }

                var gsvalIdsArr4 = gsIds[3].parent().next().find('input:checked');

                for(var i=0; i<gsvalIdsArr4.length; i++){
                    gsvalNamesArr4.push($(gsvalIdsArr4[i]).next());
                }

                for(var i=0; i<gsvalIdsArr1.length; i++){
                    for(var j=0; j<gsvalIdsArr2.length; j++){
                        for(var x=0; x<gsvalIdsArr3.length; x++){
                            for(var y=0; y<gsvalIdsArr4.length; y++){
                                html += '<tr>'+
                                    '<td> <input type="hidden" value="'+gsvalIdsArr1[i].value+'"> '+gsvalNamesArr1[i].html()+' </td>'+
                                    '<td> <input type="hidden" value="'+gsvalIdsArr2[j].value+'"> '+gsvalNamesArr2[j].html()+' </td>'+
                                    '<td> <input type="hidden" value="'+gsvalIdsArr3[x].value+'"> '+gsvalNamesArr3[x].html()+' </td>'+
                                    '<td> <input type="hidden" value="'+gsvalIdsArr4[y].value+'"> '+gsvalNamesArr4[y].html()+' </td>'+
                                    '<td> <input class="form-control" type="text" disabled="disabled" placeholder="库存"> </td>'+
                                    '<td> <input class="form-control" type="text" disabled="disabled" placeholder="剩余库存"> </td>'+
                                    '<td> <input class="form-control" type="text" placeholder="请输入添加库存量"> </td>'+
                                    '<td> <input class="form-control" type="text" placeholder="请输入减少库存量"> </td>'+
                                    '</tr>';
                            }
                        }
                    }
                }
            }

            html += '</tbody>';
            $('#gstabel').html(html);
            $('#gstabeldiv').show();
        }
    }

    function inithavegsandcreatetabel(gid) {
        var allgscheckbox = $('#goodsSpecificatiodiv').find('input[type=checkbox]');
        $.ajax({
            type:"post",
            url:"/goods/queryGgsrelLists",
            dataType: "json",
            data:{gid:gid,pageNo:1,pageSize:1000},
            success:function(data){
                var rows = data.rows;
                if(rows!=null && rows.length>0){
                    for (var i=0; i<rows.length; i++){
                        for(var j=0; j<allgscheckbox.length; j++){
                            if(rows[i].gsvId == allgscheckbox[j].value){
                                $(allgscheckbox[j]).attr('checked','checked');
                            }
                        }
                    }
                    createtabel();
                    loadgsdata();
                }
            }
        });
    }

    function loadgsdata() {
        var ggsvaldetailtrs = $('#ggsvaldetailTbody').children();
        var gid = $('#gid').val();
        if(ggsvaldetailtrs!=null && ggsvaldetailtrs.length>0){
            for(var i=0;i<ggsvaldetailtrs.length; i++){
                var ggsvaldetailtds = $(ggsvaldetailtrs[i]).children();
                var gdIds = new Array();
                for(var j=0; j<ggsvaldetailtds.length-4; j++){
                    var gsvId = $(ggsvaldetailtds[j]).find('input').get(0).value;
                    $.ajax({
                        type:"post",
                        url:"/goods/queryGoodsGgsvalDetailLists",
                        dataType: "json",
                        async:false,
                        data:{gid:gid,gsvId:gsvId,pageNo:1,pageSize:100},
                        success:function(data){
                            var rows = data.rows;
                            if(rows!=null && rows.length>0) {
                                for (var k = 0; k < rows.length; k++) {
                                    gdIds.push(rows[k].gdId);
                                }

                            }
                        }
                    });
                }

                var gdId = getMostTimes(gdIds);

                if(gdId == null){
                    $(ggsvaldetailtrs[i]).remove();
                }else{
                    $.ajax({
                        type:"post",
                        url:"/goods/queryGoodsDetailById",
                        dataType: "json",
                        async:false,
                        data:{gdId:gdId},
                        success:function(data){
                            var goodsDetail = data.goodsDetail;
                            $(ggsvaldetailtds[ggsvaldetailtds.length-4]).find('input').get(0).value = goodsDetail.gdInventory;
                            $(ggsvaldetailtds[ggsvaldetailtds.length-3]).find('input').get(0).value = goodsDetail.gdResidueinventory;
                            var myhiddengdId = '<input type="hidden" value="'+gdId+'">';
                            $(ggsvaldetailtds[ggsvaldetailtds.length-4]).prepend(myhiddengdId);
                        }
                    });
                }

            }
        }

    }

    function getMostTimes(arr) {
        if(arr!=null && arr.length>0){
            var MostTimeObj = null;
            var times = new Array();
            for(var i=0; i<arr.length;i++){
                var index = 0;
                for(var j=0; j<arr.length;j++){
                    if(arr[i]==arr[j]){
                        index ++;
                    }
                }
                times.push(index);
            }
            var largst = 0;
            for(var i=0; i<times.length;i++){
                if(times[i]>largst){
                    largst = times[i];
                }
            }

            var gsIds = new Array();
            var gsvalId = $('input[name="gsvalId"]:checked');
            var index = 0;
            var a = 0;
            for(var i=0; i<gsvalId.length; i++){
                var parentdiv = $($(gsvalId[i]).parent().parent().parent().parent().prevAll().get(0));
                var gsid = $(parentdiv.find('input').get(0));
                if(gsIds.length==0){
                    gsIds.push(gsid);
                }else{
                    var flag = false;
                    for (var j=0; j<gsIds.length; j++){
                        if(gsid.val() == gsIds[j].val()){
                            flag = true;
                        }
                    }
                    if(!flag){
                        gsIds.push(gsid);
                    }
                }

            }

            if(largst==1 && (arr.length>1 && gsIds.length>0)){
                return null;
            }

            var flag;
            for(var i=0; i<times.length;i++){
                if(times[i]==largst){
                    flag = arr[i];
                    break;
                }
            }
            return flag;

        }
    }
</script>
</body>
</html>
