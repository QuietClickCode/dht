/**
 * 初始化 树型表结构创建
 * @param url 请求数据的url
 * @param tableId 数据表格id
 * @param uniqueId 树型唯一值
 * @param columns 展示列表
 */
function createTable(url,tableId,uniqueId,columns,searchParams,toolbarId){
    if(!toolbarId){
        toolbarId='#toolbar';
    }
    //表格的初始化
    $('#'+tableId).bootstrapTable({
        url:url,
        method: 'post',                      //请求方式（*）
        toolbar:toolbarId ,                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                     //是否启用排序
        queryParams: function (params) {
            if(searchParams){
                var params=searchParams(this);
               return params;
            }else{
                return params;
            }
        },                                  //传递参数（*）
        pagination:true,
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: uniqueId,                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        selectItemName: 'parentItem',
        dataType: "json",
        columns: columns,
        contentType : "application/x-www-form-urlencoded"  //设置传入方式 可以用getparams 取得参数  默认为：application/json  json 方式传输
    });
}
/**
 * 初始化 树型表结构创建
 * @param url 请求数据的url
 * @param tableId 数据表格id
 * @param uniqueId 树型唯一值
 * @param columns 展示列表
 */
function createExpandTable(url,tableId,uniqueId,columns,searchParams,editorDetail,toolbarId){
    if(!toolbarId){
        toolbarId='#toolbar';
    }
    //表格的初始化
    $('#'+tableId).bootstrapTable({
        url:url,
        method: 'post',                      //请求方式（*）
        toolbar:toolbarId ,                //工具按钮用哪个容器
        striped: true,                      //是否显示行间隔色
        cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
        sortable: false,                     //是否启用排序
        queryParams: function (params) {
            if(searchParams){
                var params=searchParams(this);
                return params;
            }else{
                return params;
            }
        },                                  //传递参数（*）
        pagination:true,
        sidePagination: "server",           //分页方式：client客户端分页，server服务端分页（*）
        pageNumber: 1,                      //初始化加载第一页，默认第一页
        pageSize: 10,                       //每页的记录行数（*）
        pageList: [10, 25, 50, 100],        //可供选择的每页的行数（*）
        search: false,                       //是否显示表格搜索，此搜索是客户端搜索，不会进服务端，所以，个人感觉意义不大
        strictSearch: false,
        showColumns: true,                  //是否显示所有的列
        showRefresh: true,                  //是否显示刷新按钮
        minimumCountColumns: 2,             //最少允许的列数
        clickToSelect: true,                //是否启用点击选中行
        uniqueId: uniqueId,                     //每一行的唯一标识，一般为主键列
        showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
        selectItemName: 'parentItem',
        dataType: "json",
        columns: columns,
        detailView:true,//展示详情
        detailFormatter:function(index,row){
          if(editorDetail){
              return editorDetail(index,row);
          }
        },//收折内容
        contentType : "application/x-www-form-urlencoded"  //设置传入方式 可以用getparams 取得参数  默认为：application/json  json 方式传输
    });
}
