<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>商品子类管理</title>
    <meta charset="UTF-8">
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="/js/validate/css/bootstrapValidator.min.css">

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>
    <script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>

</head>
<body>
<div id="toolbar" class="form-inline">
    <select id="openingMenu" class="form-control">
    </select>
    <div class="form-group" style="margin-left: 40px;">
        <label style="margin-bottom: 0px;font-weight: 400;margin-right: 10px;">当前可分配名额</label>
        <input type="text" class="form-control" id="MenberNum" readonly placeholder="当前可分配名额">
    </div>
</div>

<div>
    <table id="goodsClassificationTable" ></table>
</div>

<!-- 公用下拉择树 -->
<div id="menuContent" class="menuContent" style="display:none; position: absolute;z-index:1059">
    <ul id="treeDemo" class="ztree" style="margin-top:0; width:320px;"></ul>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<!-- 图标选择器-->
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker-iconset-all.js"></script>
<script type="text/javascript" src="<%=path%>/js/iconpicker/dist/js/bootstrap-iconpicker.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/bootstrap-switch.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/ztree/jquery.ztree.core.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/bootstrap/jquery.treegrid.extension.js"></script>
<script type="text/javascript" src="/js/ztree/jquery.ztree.excheck.min.js"></script>
<script type="text/javascript" src="/js/common/bootstrap_table.js"></script>
<script type="text/javascript" src="/js/common/form.js"></script>
<script src="/js/toast/js/toastr.js"></script>
<script src="/js/layer/layer.js"></script>

<%--加载开盘期数--%>
<script>
    var oid;
    var opening = new Map();
    var omenberNumber;
    $(function () {
        $.ajax({
            url:"/opening/queryOpeningList",
            method:"post",
            dataType:"json",
            data:{
                pageNo:1,
                pageSize:10000
            },
            success:function (data) {
                for(let i = 0;i<data.rows.length;i++){
                    opening.set(data.rows[i].oid, data.rows[i]);
                    $("#openingMenu").append("<option id='"+data.rows[i].oid+"' value='"+data.rows[i].oid+"'>"+data.rows[i].oname+"</option>");
                }
                if(data.rows.length != 0){
                    oid = data.rows[0].oid;
                    $("#MenberNum").val(data.rows[0].omenberNum);
                    omenberNumber = data.rows[0].omenberNum;
                    createEmployeeTable(oid);
                }
                $("#"+oid).attr("selected","selected");
            }
        });
    });
</script>



<script>
    $("#openingMenu").change(function () {
        oid = $(this).val();
        for (let key of opening.keys()) {
            if(key == oid){
                $("#MenberNum").val(opening.get(key).omenberNum);
                omenberNumber = opening.get(key).omenberNum;
            }
        }
        rowDatas.clear();
        $("#goodsClassificationTable").bootstrapTable('destroy');
        createEmployeeTable(oid);
        console.log(oid);
    });
</script>

<script>
    <%--添加预约关系--%>
    function addEmRelationship(id,teamId,num) {
        $.ajax({
            url:"/employeeRelationship/addEmRelationship",
            type:"post",
            dataType: "json",
            data:{
                pid:oid,
                emId:id,
                parentId:teamId,
                emReservation:num
            },
            success:function (data) {
                setMenber();
                layer.msg("添加预约关系成功",{time:'1000'});
            }
        });
    }

    function updateEmRelationship(id,num) {
        $.ajax({
            url:"/employeeRelationship/updateEmRelationship",
            type:"post",
            dataType: "json",
            data:{
                erId:id,
                emReservation:num
            },
            success:function (data) {
                setMenber();
                layer.msg("修改预约关系成功",{time:'1000'});
            }
        });
    }

    $('#goodsClassificationTable').on('blur', '.reservation', function(){
        reservationCount(this);
    })

    function setMenber(){
        let count = 0;
        for (let value of rowDatas.values()) {
            count = Number(count) + Number(value.emReservation);
        }
        console.log(count);
        $("#MenberNum").val(omenberNumber - count);
    }

    function reservationCount($this) {
        let count = 0;
        for (let value of rowDatas.values()) {
            count = Number(count) + Number(value.emReservation);
        }
        let val = $($this).val();
        let tid = $($this).attr("data-tid");
        let relationship = rowDatas.get(Number(tid));
        let reservation = relationship.emReservation;
        if(val == reservation)
            return;

        if(reservation == null && val == 0)
            return;
        let num = Number(count) - Number(reservation) + Number(val);
        let flag = Number(omenberNumber) - Number(num) >= 0;
        if(flag){
            if(relationship.erid != null){
                updateEmRelationship(relationship.erid,val);
                relationship.emReservation = val;
            }else{
                addEmRelationship(relationship.emid,relationship.emTeam,val);
                relationship.emReservation = val;
            }
        }else{
            layer.msg("超出了当前可分配名额");
        }
        return flag;
    }


</script>



<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
        function createEmployeeTable(id) {
            $('#goodsClassificationTable').bootstrapTable({
                url:"/employeeRelationship/queryAllEmRelationshipVoList",
                method: 'post',                      //请求方式（*）
                toolbar: '#toolbar',                //工具按钮用哪个容器
                striped: true,                      //是否显示行间隔色
                cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
                //pagination: true,                   //是否显示分页（*）
                sortable: false,                     //是否启用排序
                sortOrder: "asc",                   //排序方式
                queryParams: function () {
                    return {
                        pid:id
                    };
                },                                  //传递参数（*）
                sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
                pageNumber: 1,                      //初始化加载第一页，默认第一页
                pageSize: 10,                       //每页的记录行数（*）
                pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
//            search: true,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
                strictSearch: true,
                showColumns: true,                  //是否显示所有的列
                treeView: true,
                undefinedText:"",
                treeId:"tid",
                contentType:"application/x-www-form-urlencoded",
                treeField:"teamName",
                treePid:"emTeam",                         //上级菜单关联id
                treeRootLevel: 1,
                showRefresh: true,                  //是否显示刷新按钮
                minimumCountColumns: 2,             //最少允许的列数
                clickToSelect: true,                //是否启用点击选中行
                uniqueId: "tid",                     //每一行的唯一标识，一般为主键列
                showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
                selectItemName: 'parentItem',
                dataType:"json",
                columns: [{
                    checkbox: true
                },{
                    field: 'teamName',
                    align : 'left',
                    valign : 'middle',
                    title: '团队名称'
                },{
                    field: 'emName',
                    align : 'center',
                    valign : 'middle',
                    title: '置业顾问名称'

                },{
                    align : 'center',
                    valign : 'middle',
                    title: '团队总分配预约数',
                    formatter:function (value,row,index) {
                        let html = "";
                        if(row.emTeam != null){
                            if(row.parentId == null)
                                html += "<p id='a"+row.tid+"'></p>";
                        }

                        return html;
                    }

                },{
                    field: 'emReservation',
                    align : 'center',
                    valign : 'middle',
                    title: '置业顾问分配人数',
                    formatter:function (value,row,index) {
                        let html = "";
                        if(row.emTeam != null){
                            rowDatas.set(row.tid,row);
                            if(row.emReservation != null){
                                html = '<input type="email" class="form-control reservation" data-tid="'+row.tid+'" data-tid="'+row.tid+'" onclick="event.stopPropagation()" value="'+row.emReservation+'" ">';
                            }
                            else{
                                html = '<input type="email" onclick="event.stopPropagation()" data-tid="'+row.tid+'" data-tid="'+row.tid+'" class="form-control reservation" value="0" ">';
                            }
                        }
                        return html;
                    }

                }],
            });
            $('#goodsClassificationTable').on('load-success.bs.table', function (e, data){
                setMenber();
            });
        }
</script>
</body>
</html>
