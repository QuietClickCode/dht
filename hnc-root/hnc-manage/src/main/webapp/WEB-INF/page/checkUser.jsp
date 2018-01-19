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
    <button class="btn btn-default team" type="button">对团队进行比对</button>
    <button class="btn btn-default employee" type="button">对置业顾问进行比对</button>
    <button class="btn btn-default test" type="button" onclick="searchAllData();">展示所有团队</button>
    <a id="exportExcelA" class="btn btn-primary" type="button" >导出表格</a>
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
                    createCheckUserTable(oid);
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
        notuseNumTotal = 0;
        useNumTotal = 0;
        countTotal = 0;
        rowDatas.clear();
        $("#goodsClassificationTable").bootstrapTable('destroy');
        createCheckUserTable(oid);
    });
</script>

<script>
    <%--根据团队来对比--%>
    $(".team").click(function () {
        let tids = "";
        let checkUserArray = $('#goodsClassificationTable').bootstrapTable('getAllSelections');
        console.log(checkUserArray);
        for(let i = 0;i<checkUserArray.length;i++) {
            if(checkUserArray[i].level != 1){
                layer.msg("只能选择团队");
                tids = "";
                return
            }
            if(i == checkUserArray.length - 1)
                tids += checkUserArray[i].id;
            else
                tids += checkUserArray[i].id+',';
            console.log(tids);
        }
        notuseNumTotal = 0;
        useNumTotal = 0;
        countTotal = 0;
        $("#goodsClassificationTable").bootstrapTable('destroy');
        rowDatas.clear();
        createCheckUserTable(oid,null,tids);
    });

    //根据置业顾问对比
    $(".employee").click(function () {
        let empids = "";
        let checkUserArray = $('#goodsClassificationTable').bootstrapTable('getAllSelections');
        console.log(checkUserArray);
        for(let i = 0;i<checkUserArray.length;i++) {
            if(checkUserArray[i].level != 2){
                layer.msg("只能选择置业顾问");
                empids = "";
                return
            }
            if(i == checkUserArray.length - 1)
                empids += checkUserArray[i].empId;
            else
                empids += checkUserArray[i].empId+',';
            console.log(empids);
        }
        notuseNumTotal = 0;
        useNumTotal = 0;
        countTotal = 0;
        $("#goodsClassificationTable").bootstrapTable('destroy');
        rowDatas.clear();
        createCheckUserTable(oid,empids,null);
    });
</script>

<script>
//    $(".test").click(function () {
//
//    });
    function searchAllData() {
        console.log(111)
        notuseNumTotal = 0;
        useNumTotal = 0;
        countTotal = 0;
        $("#goodsClassificationTable").bootstrapTable('destroy');
        createCheckUserTable(oid);
    }
</script>

<script type="text/javascript">
    //用于缓存资源表格数据
    var rowDatas=new Map();
    var notuseNumTotal = 0;
    var useNumTotal = 0;
    var countTotal = 0;
    function createCheckUserTable(id,emids,tids) {
        if(emids==undefined||emids==null){
            emids = null;
        }
        if(tids==undefined||tids==null){
            tids = null;
        }
        $('#exportExcelA').attr('href','/checkUser/exportExcel?oid='+id+'&empIds='+emids+'&tids='+tids);


        $('#goodsClassificationTable').bootstrapTable({
            url:"/checkUser/queryAchievement",
            method: 'post',                      //请求方式（*）
            toolbar: '#toolbar',                //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            //pagination: true,                   //是否显示分页（*）
            sortable: false,                     //是否启用排序
            sortOrder: "asc",                   //排序方式
            queryParams: function () {
                return {
                    tids:tids,
                    empIds:emids,
                    oid:id
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
            treeId:"id",
            contentType:"application/x-www-form-urlencoded",
            treeField:"tname",
            treePid:"tid",                         //上级菜单关联id
            treeRootLevel: 1,
            treeCollapseAll: true,
            showRefresh: true,                  //是否显示刷新按钮
            minimumCountColumns: 2,             //最少允许的列数
            clickToSelect: true,                //是否启用点击选中行
            uniqueId: "id",                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType:"json",
            columns: [{
                checkbox: true
            },{
                field: 'tname',
                align : 'left',
                valign : 'middle',
                title: '团队名称'
            },{
                field: 'empName',
                align : 'center',
                valign : 'middle',
                title: '置业顾问名称'

            },{
                field: 'notuseNum',
                align : 'center',
                valign : 'middle',
                title: '未登记(<span class="notuseNumTotal"></span>)',
                formatter:function (value,row,index) {
                    let html = "";
                    if(row.level == 1){
                        notuseNumTotal = Number(notuseNumTotal) + Number(row.notuseNum);
                        html += "<p class='notuseNum'>"+row.notuseNum+"</p>";
                    }else{
                        html += "<p>"+row.notuseNum+"</p>";
                    }
                    return html;
                }

            },{
                field: 'useNum',
                align : 'center',
                valign : 'middle',
                title: '已登记(<span class="useNumTotal"></span>)',
                formatter:function (value,row,index) {
                    let html = "";
                    if(row.level == 1){
                        useNumTotal = Number(useNumTotal) + Number(row.useNum);
                        html += "<p class='useNum'>"+row.useNum+"</p>";
                    }else{
                        html += "<p>"+row.useNum+"</p>";
                    }
                    return html;
                }

            },{
                field: 'count',
                align : 'center',
                valign : 'middle',
                title: '总和(<span class="countTotal"></span>)',
                formatter:function (value,row,index) {
                    let html = "";
                    if(row.level == 1){
                        countTotal = Number(countTotal) + Number(row.count);
                        html += "<p class='count'>"+row.count+"</p>";
                    }else{
                        let count = Number(row.useNum) + Number(row.notuseNum);
                        html += "<p>"+count+"</p>";
                    }
                    return html;
                }

            }],
        });

        $('#goodsClassificationTable').on('load-success.bs.table', function (e, data){
            $(".notuseNumTotal").text(notuseNumTotal);
            $(".useNumTotal").text(useNumTotal);
            $(".countTotal").text(countTotal);
        });
    }

    function refreshFloorTableData() {
        $('#goodsClassificationTable').bootstrapTable(
            "refresh",
            {
                url:"/checkUser/queryAchievement?oid="+oid
            }
        );
    }

//    $('body').on('click', '.glyphicon-refresh', function(event){
//
//        alert('正在进入...');
//        event.stopPropagation();
//    })
</script>
</body>
</html>
