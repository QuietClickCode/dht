<%@page pageEncoding="UTF-8"%>

<style>
    .goods_group{
        width: 100%;
        margin-top: 10px;
        background-color: #f4f5f9;
        padding: 5px;
        overflow: hidden;
        text-align: center;
    }

    #remove_goods{
        padding: 3px 30px;
        text-decoration: none;
    }

    #commonSelectGoods li{
        width: 80%;
        margin-left: 10%;
        margin-right: 10%;
        height: 30px;
        line-height: 30px;
        text-align: center;
        border: 1px solid rgba(0,0,0,0.1);
        list-style: none;
        margin-top: 10px;
        margin-bottom: 15px;
        border-radius: 5px;
        position: relative;
    }

    .remove_icon{
        width: 15px;
        height: 15px;
        background: url(/img/remove_icon.png)no-repeat center;
        background-size: 15px 15px;
        display: block;
        text-decoration: none;
        color: #D4D4D4;
        position: absolute;
        display: none;
        right: -6px;
        top: -6px;
        cursor: pointer;
    }

    #commonSelectGoods{
        height: 400px;
        overflow: auto;
    }
</style>
<!--公用商品选择器 -->
<div class="modal fade" id="commonGoodsSelectDialog"  role="dialog" aria-hidden="true">
    <div class="modal-dialog" style="width: 70%;height: 60%">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="modaltitle"><i class="fa fa-envelope-o"></i>商品选择</h4>
            </div>
            <div class="row">
                <div class="col-lg-2" style="overflow:auto;padding-right: 0px;">
                    <ul id="goodsSelectTree" class="ztree" style="width: auto;margin-top: 56px;height: 450px;"></ul>
                </div>
                <!-- 商品下拉列表-->
                <div class="col-lg-8">
                    <div id="commonGoodsSelectToolbar" class="form-inline">
                        <input type="text" class="form-control" id="search_Goods_name" placeholder="请输入商品名称">
                        <select id="search_Goods_check" class="form-control">
                            <option value="">审核状态</option>
                            <option value="0">未审核</option>
                            <option value="1">已审核</option>
                            <option value="2">未通过审核</option>
                        </select>
                        <button class="btn btn-default" type="button" onclick="refreshCommonGoodsSelectData()">查询</button>
                    </div>
                    <div>
                        <table id="commonGoodsSelectTableDatas" ></table>
                    </div>
                </div>
                <div class="col-lg-2" style="padding-left: 0px;">
                    <div class="goods_group">
                        <a class="btn btn-default" id="remove_goods" href="#" role="button">清空</a>
                    </div>
                    <ul id="commonSelectGoods">

                    </ul>

                </div>
            </div>

            <div class="modal-footer clearfix">

                <button type="button" class="btn btn-danger" onclick="cancelCommonGoodsSelectButton();"><i class="fa fa-times"></i> 取消</button>
                <button type="button" class="btn btn-primary pull-left" onclick="confirmCommonGoodsSelectButton();"><i class="fa fa-envelope"></i>确认</button>

            </div>
        </div>
    </div>
</div>
<script>


    //用于缓存资源表格数据
    var commonGoodsSelectDatas=new Map();
    var commonGoodsSelectGtId;
    var goodsSelectColumns=[
        {   checkbox: true,
            align : 'center',
            valign : 'middle',
            formatter:commonGoodsSelectCheckFormatter

        },
        {
            field: 'gname',
            title: '商品名称',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gclassificationName',
            title: '商品分类',
            align : 'center',
            valign : 'middle'
        },
        {
            field: 'gmaindirection',
            title: '主推方向',
            align : 'center',
            valign : 'middle',
            formatter:function(value,row,index){
                let html='';
                if(row.gmaindirection==0){
                    html+='乡村';
                }else if(row.gmaindirection==1){
                    html+='城镇';
                }else{
                    html+='乡村和城镇';
                }
                return html;
            }
        }
    ]
    function commonGoodsSelectCheckFormatter(value, row, index) {
        let curId = row.gid;
        if(commonGoodsSelectValuesMaps.has(curId)){
            return {
                checked : true//设置选中
            };
        }
        return {
            checked : false//设置选中
        };
    }

    /**
     *公用商品选 择器取消按钮
     */
    function cancelCommonGoodsSelectButton(){
        $('#commonGoodsSelectDialog').modal('hide');
    }
    /**
     *公用商品选择器确认按钮事件
     */
    function confirmCommonGoodsSelectButton(){
        if(commonGoodsSelectCallbackFun){
            let ids="",names="";
            if(commonGoodsSelectValuesMaps.size>0){
                for(var key of commonGoodsSelectValuesMaps.keys()){
                    ids+=key+",";
                    names+=commonGoodsSelectValuesMaps.get(key)+",";
                }
            }
            commonGoodsSelectCallbackFun(ids,names);
        }
        $('#commonGoodsSelectDialog').modal('hide');
    }
    var rtnCommonGoodsSelectIds,rtnCommonGoodsSelectNms;
    var commonGoodsSelectCallbackFun;
    function onloadGoodsSelectDialog(initIds,initNms,callback){
        //关闭弹窗 销毁校验，再重新添加校验
        $('#commonGoodsSelectDialog').on('hide.bs.modal', function () {
            commonGoodsSelectGtId=null;
            commonGoodsSelectValuesMaps.clear();
            $("#commonSelectGoods").html("");
        });
        rtnCommonGoodsSelectIds=initIds;
        rtnCommonGoodsSelectNms=initNms;
        commonGoodsSelectCallbackFun=callback;
        pullCommonGoodsSelectTrees();
        commonGoodsSelectLoadData();
    }
    /**
     * 打开公用商品选择器
     */
    function openGoodsSelectDialog(){
        //初始化数据
        let sIds=$(rtnCommonGoodsSelectIds).val();
        let sNms=$(rtnCommonGoodsSelectNms).val();
        if(sIds){
            let sids_=sIds.split(",");
            let sNms_=sNms.split(",");
            for(var i=0;i<sids_.length;i++){
                if(sids_[i]){
                    commonGoodsSelectValuesMaps.set(parseInt(sids_[i],10),sNms_[i]);
                    let child="<li id='"+sids_[i]+"' onmouseleave='remove_child_icon(this)' onmouseover='add_remove_icon(this)'>"+sNms_[i]+"</li>";
                    $("#commonSelectGoods").append(child);
                }
            }
        }
        refreshCommonGoodsSelectData();
        $('#commonGoodsSelectDialog').modal('show');
    }
    var goodsSelectTreeNodes;
    /**
     * 取得商品类型
     **/
    function pullCommonGoodsSelectTrees(){
        $.ajax({
            type:"post",//请求方式
            url:"/goods/goodsTypeTree",//发送请求地址
            dataType:"json",
            data:{//发送给数据库的数据
                type:0
            },
            //请求成功后的回调函数有两个参数
            success:function(data,textStatus){
                if(data.status==0){
                    let goodsSelectTreeNodes = data.data;
                    var zTree=$.fn.zTree.init($("#goodsSelectTree"), goodsSelectTreeSetting, goodsSelectTreeNodes);
                }else{
                    alert(data.msg);
                }
            },error:function(data,textStatus){
                alert(data);
            }
        });
    }
    /**
     * 刷新表格数据
     **/
    function refreshCommonGoodsSelectData() {
        $('#commonGoodsSelectTableDatas').bootstrapTable(
            "refresh",
            {
                url:"/goods/queryGoodsLists"
            }
        );
    }
    /**
     *加载商品列表数据
     */
    function commonGoodsSelectLoadData(){
        //表格的初始化
        $('#commonGoodsSelectTableDatas').bootstrapTable({
            url:'/goods/queryGoodsLists',
            method: 'post',                      //请求方式（*）
            toolbar:'#commonGoodsSelectToolbar' ,  //工具按钮用哪个容器
            striped: true,                      //是否显示行间隔色
            cache: false,                       //是否使用缓存，默认为true，所以一般情况下需要设置一下这个属性（*）
            sortable: false,                     //是否启用排序
            queryParams: function (params) {
                var params=commonGoodsParams(this);
                return params;
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
            uniqueId: 'gid',                     //每一行的唯一标识，一般为主键列
            showToggle: true,                    //是否显示详细视图和列表视图的切换按钮
            selectItemName: 'parentItem',
            dataType: "json",
            columns: goodsSelectColumns,
            onCheckAll:function(rows){
                for(var row of rows){
                    if(!commonGoodsSelectValuesMaps.has(row.gid)){
                        commonGoodsSelectValuesMaps.set(row.gid,row.gname);
                        let child="<li id='"+row.gid+"' onmouseleave='remove_child_icon(this)' onmouseover='add_remove_icon(this)'>"+row.gname+"</li>";
                        $("#commonSelectGoods").append(child);
                    }
                }
            },
            //点击每一个单选框时触发的操作
            onCheck:function(row){
                //判断是否己经存在
                if(!commonGoodsSelectValuesMaps.has(row.gid)){
                    commonGoodsSelectValuesMaps.set(row.gid,row.gname);
                    let child="<li id='"+row.gid+"' onmouseleave='remove_child_icon(this)' onmouseover='add_remove_icon(this)' >"+row.gname+"</li>";
                    $("#commonSelectGoods").append(child);
                }
            },
            //取消每一个单选框时对应的操作；
            onUncheck:function(row){
                //判断是否己经存在
                if(commonGoodsSelectValuesMaps.has(row.gid)){
                    commonGoodsSelectValuesMaps.delete(row.gid);
                    $("#commonSelectGoods #"+row.gid).remove();
                }
            },
            onUncheckAll:function(rows){
                for(var row of rows){
                    if(commonGoodsSelectValuesMaps.has(row.gid)){
                        commonGoodsSelectValuesMaps.delete(row.gid);
                        $("#commonSelectGoods #"+row.gid).remove();
                    }
                }
            },
            contentType : "application/x-www-form-urlencoded"  //设置传入方式 可以用getparams 取得参数  默认为：application/json  json 方式传输
        });
    }
    var commonGoodsSelectValuesMaps=new Map();
    /**
     * 查询条件
     **/
    function commonGoodsParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            gname: $("#search_Goods_name").val(),
            gclassification: commonGoodsSelectGtId,
            gmaindirection: $("#search_Goods_gmaindirection").val(),
            isChecked: 1
        };
    }
    /***********************************************************************************/
    var goodsSelectTreeSetting = {
        view: {
            dblClickExpand: false
        },
        data: {
            simpleData: {
                enable: true
            }
        },
        callback: {
            onClick: goodsSelectTreeClick
        }
    };
    function goodsSelectTreeClick(e, treeId, treeNode) {
        commonGoodsSelectGtId=treeNode.id;
        refreshCommonGoodsSelectData();
    }


    $("#remove_goods").click(function () {
        $("#commonSelectGoods li").remove();
        commonGoodsSelectValuesMaps.clear();
        $("#commonGoodsSelectTableDatas tbody tr").removeClass("selected");
        $(".bs-checkbox .th-inner input").removeAttr("checked");
        $("#commonGoodsSelectTableDatas tbody tr :input[name='parentItem']").each(function () {
            $(this).removeAttr("checked");
        });
    });

    //鼠标移动到元素上添加删除图标
    function add_remove_icon($this) {
        let len = $($this).find(".remove_icon").length;
        if (len == 0){
            $($this).append('<span class="remove_icon" onmouseleave="event.stopPropagation();remove_icon(this)" onclick="remove_icon_goods(this)"></span>');
            $($this).find(".remove_icon").show();
        }
        return;
    }

    function remove_icon($this) {
        $($this).remove();
    }
    
    function remove_child_icon($this) {
        $($this).find("span").remove();
    }

    //删除选中的商品
    function remove_icon_goods($this) {
        let id = $($this).parent().attr("id");
        $($this).parent().remove();
        $("#commonGoodsSelectTableDatas tbody .selected").each(function () {
            let gid = $(this).attr("data-uniqueid");
             if(id == gid){
                $(this).removeClass("selected");
                $(this).find(":input[name='parentItem']").removeAttr("checked");
                $(".bs-checkbox .th-inner input").removeAttr("checked");
             }
         });
        for(var key of commonGoodsSelectValuesMaps.keys()){
            if(key == id){
                commonGoodsSelectValuesMaps.delete(key);
                console.log(commonGoodsSelectValuesMaps.size);
            }
        }
    }

</script>

