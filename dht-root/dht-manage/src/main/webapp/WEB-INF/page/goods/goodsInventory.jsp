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
            //getgoods(gid);
            loadgoodsdetailonce(gid);
        }
    }

</script>

<script>
    function loadgoodsdetailonce(gid) {
        $.ajax({
            type:"post",
            url:"/goods/queryGoodsDetailOnce",
            dataType: "json",
            data:{gid:gid},
            success:function(data){
                var html = '';
                $('#gstabel').html('');
                var rows = data.rows;
                if(rows!=null&&rows.length>0){
                    var gsid = rows[0].gsid.split(' ');
                    var gsname = rows[0].gsname.split(' ');
                    html = '<thead><tr>';
                    for(var j=0;j<gsid.length;j++){
                        html += '<th> <input value="'+gsid[j]+'" type="hidden">'+gsname[j]+'</th>';
                    }
                    html += '<th>库存</th><th>剩余库存</th><th>添加库存</th><th>减少库存</th></tr></thead>';
                    $('#gstabel').append(html);
                    html = '<tbody id="ggsvaldetailTbody">';

                    for(var i=0;i<rows.length;i++){
                        var hasgsvalid = rows[i].hasgsvalid.split(' ');
                        var hasgsval = rows[i].hasgsval.split(' ');
                        html += '<tr>';
                        for(var j=0;j<hasgsvalid.length;j++){
                            html += '<td> <input value="'+hasgsvalid[j]+'" type="hidden" /> '+hasgsval[j]+' </td>';
                        }
                        html += '<td><input type="hidden" value="'+rows[i].gdId+'"> <input value="'+rows[i].gdInventory+'" class="form-control" placeholder="库存" disabled="disabled" type="text" /> </td>'+
                            '<td> <input value="'+rows[i].gdResidueinventory+'" class="form-control" placeholder="剩余库存" disabled="disabled" type="text" /> </td>'+
                            '<td> <input  class="form-control" placeholder="请输入添加库存量" type="text" /> </td>'+
                            '<td> <input  class="form-control" placeholder="请输入减少库存量" type="text" /> </td>'+
                            '</tr>';
                    }
                    html += '</tbody>';
                    $('#gstabel').append(html);
                }
            }
        });
    }
</script>
</body>
</html>
