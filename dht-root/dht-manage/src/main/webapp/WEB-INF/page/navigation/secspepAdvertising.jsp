<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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
    <link rel="stylesheet" href="<%=path%>/js/toast/css/toastr.css">
    <link rel="stylesheet" href="/js/validate/css/bootstrapValidator.min.css">
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"> </script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="/js/common/form.js"> </script>
    <script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>
</head>
<body>
<div id="toolbar" class="form-inline">
    <ex:perm url="goods/addGoods">
        <button class="btn btn-primary" type="button" onclick="addFloorAdvertising()">添加特价/秒杀广告</button>
    </ex:perm>
    <br>

    <ex:perm url="goods/queryGoodsLists">
        <input type="text" class="form-control" id="search_Advertising_name" placeholder="请输入广告名称">
        <button class="btn btn-default" style="margin-top: 5px" type="button" onclick="refreshTableData()">查询</button>
    </ex:perm>

</div>
<div>
    <table id="goodsClassificationTable" ></table>
</div>
<%--添加楼层广告--%>
<div class="modal fade" id="saveFloorAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="">新增楼层广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="addFloorAdv">
                    <div class="form-group">
                        <label for="img" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <input id="img" name="dht_image_upload" type="file"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="AdvName" class="col-sm-2 control-label">广告名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="AdvName" id="AdvName" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="client" checked class="client" value="0">
                                    移动端
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="client" class="client" value="1" disabled="disabled">
                                    PC端
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="client" class="client" value="2" disabled="disabled">
                                    小程序
                                </label>
                            </div>
                        </div>
                    </div>
                    
                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" name="url" id="url" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="order" id="order" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="saveFloorAdv()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--编辑导航--%>
<div class="modal fade" id="updateAdvertising" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title">编辑该楼层广告</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" id="updateFloorAdvertising">
                    <div class="form-group">
                        <label for="uploadImage" class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <img id="showImg" style="width: 50px;height: 50px;display: inline-block;">
                            <input id="uploadImage" style="display: inline-block" name="dht_image_upload" type="file"/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">广告名称</label>
                        <div class="col-sm-10">
                            <input type="text" name="setName" id="AdvertisingName" class="form-control">
                        </div>
                    </div>


                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">客户端</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" checked class="updateClient" value="0">移动端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" disabled="disabled" class="updateClient" value="1">PC端
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="updateClient" disabled="disabled" class="updateClient" value="2">小程序
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">状态</label>
                        <div class="col-sm-10">
                            <label class="radio-inline">
                                <input type="radio" name="isShow" class="isShow" value="1">显示
                            </label>
                            <label class="radio-inline">
                                <input type="radio" name="isShow" class="isShow" value="0">不显示
                            </label>
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">链接</label>
                        <div class="col-sm-10">
                            <input type="text" name="setUrl" id="setAdvertUrl" class="form-control">
                        </div>
                    </div>

                    <div class="form-group">
                        <label for="" class="col-sm-2 control-label">排序</label>
                        <div class="col-sm-10">
                            <input type="text" name="setOrder" id="setAdvertOrder" class="form-control">
                        </div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="updateFloor()">确定</button>
            </div>
        </div>
    </div>
</div>


<%--图片展示模态框--%>
<div class="modal fade" id="showImageModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" >图片展示</h4>
            </div>
            <div class="modal-body">
                <img src="">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                <button type="submit" class="btn btn-primary" onclick="saveFloorAdv()">确定</button>
            </div>
        </div>
    </div>
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
<script type="text/javascript">
    $.fn.serializeObject = function() {
        var o = {};
        var a = this.serializeArray();
        $.each(a, function() {
            if (o[this.name]) {
                if (!o[this.name].push) {
                    o[this.name] = [o[this.name]];
                }
                o[this.name].push(this.value || '');
            } else {
                o[this.name] = this.value || '';
            }
        });
        return o;
    };
    //用于缓存资源表格数据
    var rowDatas=new Map();
    var columns = [{
        checkbox: true
    },{
        field: 'saName',
        align : 'left',
        valign : 'middle',
        title: '名称'

    },{
        field: 'saOrder',
        align : 'center',
        valign : 'middle',
        title: '排序'

    },{
        field: 'imageUrl',
        title: '图片',
        align : 'center',
        valign : 'middle',
        formatter:function (value,row,index) {
            rowDatas.set(row.said,row);
            let html;
            html = "<img style='width: 50px;height: 50px;cursor: pointer;'  onclick='event.stopPropagation();wtffff()'  src="+row.imageUrl+">";
            return html;
        }
    }
        ,{
            field: 'isShow',
            align : 'center',
            valign : 'middle',
            title: '是否显示',
            formatter:function (value,row,index) {
                rowDatas.set(row.said,row);
                let html='';
                let show = row.isShow;
                let isShow;
                if(show == 1)
                    isShow = "显示";
                else
                    isShow = "不显示"
                html+='<p>'+isShow+'</p>'
                return html;
            }
        },{
            align : 'center',
            valign : 'middle',
            title: '编辑',
            formatter:function (value,row,index) {
                rowDatas.set(row.said,row);
                let html;
                html='<button class="btn btn-primary" type="button" onclick="event.stopPropagation();updateFloorAdvertising(\''+row.saId+'\',\''+row.imageId+'\',\''+row.imageUrl+'\',\''+row.saName+'\',\''+row.saClient+'\',\''+row.isShow+'\',\''+row.url+'\',\''+row.saOrder+'\')">编辑</button>';
                return html;
            }
        },{
            align : 'center',
            valign : 'middle',
            title: '删除',
            formatter:function (value,row,index) {
                rowDatas.set(row.said,row);
                let html;
                html='<button class="btn btn-default" type="button" onclick="event.stopPropagation();deleteFloorAdv(\''+row.saId+'\')">删除</button>';
                return html;
            }
        }
    ]


    $(function () {
        //表格的初始化
        createTable("/secspepAdvertising/querysecspepAdvertisingList","goodsClassificationTable","saId",columns,queryParams);
    });

    function queryParams(that){
        return {
            pageSize: that.pageSize,
            pageNo: that.pageNumber,
            saName: $('#search_Advertising_name').val()
        };
    }

    var said;
    var imageId;
    /*打开编辑楼层广告模态框*/
    function updateFloorAdvertising(id,imgId,imageUrl,name,client,isshow,url,order) {
        $("#AdvertisingName").val(name);
        radioChoose(".updateClient",client);
        radioChoose(".isShow",isshow);
        $("#setAdvertUrl").val(url);
        $("#setAdvertOrder").val(order);
        $("#showImg").attr("src",imageUrl);
        said = id;
        imageId = imgId;
        $("#updateAdvertising").modal("show");
    }

    function radioChoose(className,num) {
        for(let i = 0;i<$(className).length;i++){
            if($(className).eq(i).val() == num)
                $(className)[i].checked = 'checked';
        }
    }
    
    $("#uploadImage").change(function () {
        let path = $(this).val();
        $("#showImg").attr("src","");
        $("#showImg").attr("src",path);

        var r= new FileReader();
        f=document.getElementById('uploadImage').files[0];
        r.readAsDataURL(f);
        r.onload=function  (e) {
            document.getElementById('showImg').src=this.result;
        };
    });

    /*编辑楼层广告*/
    function updateFloor() {
        let bootstrapValidator = $("#updateFloorAdvertising").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;

        let imgPath = $("#uploadImage").val();
        let name = $("#AdvertisingName").val();
        let client = $(".updateClient:checked").val();
        let show = $(".isShow:checked").val();
        let url = $("#setAdvertUrl").val();
        let order = $("#setAdvertOrder").val();
        if(imgPath != ""){
            var fd = new FormData($("#updateFloorAdvertising")[0]);
            fd.append("imageUse","image/jpeg");
            fd.append("isWatermark","false");
            fd.append("isCompress", "false");
            $.ajax({
                url:"/file/imageUpload",
                type:"post",
                data: fd,
                processData : false,
                contentType : false,
                success:function (data) {
                    var imagepath = JSON.parse(data).original;
                    $.ajax({
                        url:"/secspepAdvertising/updatesecspepAdvertising",
                        method:"post",
                        dataType:"json",
                        data:{
                            saId:said,
                            imageId:imagepath,
                            url:url,
                            saName:name,
                            saOrder:order,
                            ssShow:show,
                            saClient:client
                        },
                        success:function (data) {
                            $("#updateAdvertising").modal("hide");
                            refreshTableData();
                            layer.msg(data.msg);
                        }
                    });
                }
            });
        }else{
            $.ajax({
                url:"/secspepAdvertising/updatesecspepAdvertising",
                method:"post",
                dataType:"json",
                data:{
                    saId:said,
                    imageId:imageId,
                    url:url,
                    saName:name,
                    saOrder:order,
                    isShow:show,
                    saClient:client
                },
                success:function (data) {
                    $("#updateAdvertising").modal("hide");
                    refreshTableData();
                    layer.msg(data.msg);
                }
            });
        }

    }

    /*打开新增楼层广告模态框*/
    function addFloorAdvertising() {
        $("#saveFloorAdvertising").modal("show");
    }


    function refreshTableData() {
        $('#goodsClassificationTable').bootstrapTable(
            "refresh",
            {
                url:"/secspepAdvertising/querysecspepAdvertisingList"
            }
        );
    }
    
    function saveFloorAdv() {
        let bootstrapValidator = $("#addFloorAdv").data('bootstrapValidator');
        bootstrapValidator.validate();
        if(!bootstrapValidator.isValid())
            return;

        let img = $("#img").val();
        let name = $("#AdvName").val();
        let client = $(".client:checked").val();
        let url = $("#url").val();
        let order = $("#order").val();
        if(img != ""){
            var fd = new FormData($("#addFloorAdv")[0]);
            fd.append("imageUse","image/jpeg");
            fd.append("isWatermark","false");
            fd.append("isCompress", "false");
            $.ajax({
                url:"/file/imageUpload",
                type:"post",
                data: fd,
                processData : false,
                contentType : false,
                success:function (data) {
                    var imagepath = JSON.parse(data).original;
                    $.ajax({
                        url:"/secspepAdvertising/addsecspepAdvertising",
                        method:"post",
                        dataType:"json",
                        data:{
                            imageId:imagepath,
                            url:url,
                            saName:name,
                            saOrder:order,
                            saClient:client,
                            version:0,
                            isShow:1,
                            isDelete:0
                        },
                        success:function (data) {
                            $("#saveFloorAdvertising").modal("hide");
                            refreshTableData();
                            layer.msg(data.msg);
                            $("#addFloorAdv").data('bootstrapValidator').resetForm(true);
                        }
                    });
                }
            });
        }
    }

    function deleteFloorAdv(id) {
        $.ajax({
            url:"/secspepAdvertising/deletesecspepAdvertising",
            method:"post",
            dataType:"json",
            data:{
                saId:id
            },
            success:function (data) {
                refreshTableData();
                layer.msg(data.msg)
            }
        });
    }

</script>


<script>
    toastr.options = {

        closeButton: false,
        debug: false,
        progressBar: false,
        positionClass: "toast-bottom-center",
        onclick: null,
        showDuration: "300",
        hideDuration: "1000",
        timeOut: "2000",
        extendedTimeOut: "1000",
        showEasing: "swing",
        hideEasing: "linear",
        showMethod: "fadeIn",
        hideMethod: "fadeOut"
    };
</script>

<%--表单校验--%>
<script type="text/javascript">
    $(function () {
        $('#addFloorAdv').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                AdvName: {
                    validators: {
                        notEmpty: {
                            message: '分类名称不能为空'
                        }
                    }
                },
                order: {
                    validators: {
                        notEmpty: {
                            message: '排序不能为空'
                        },
                        regexp: {
                            regexp: /\d/,
                            message: "只能输入数字"
                        }
                    }
                },
                client: {
                    validators: {
                        notEmpty: {
                            message: '必须选择一个客户端对象'
                        }
                    }
                },
                url: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        }
                    }
                },
                dht_image_upload: {
                    validators: {
                        notEmpty: {
                            message: '文件不能为空'
                        }
                    }
                }
            }
        });

        $('#updateFloorAdvertising').bootstrapValidator({
            message: 'This value is not valid',
            feedbackIcons: {
                valid: 'glyphicon glyphicon-ok',
                invalid: 'glyphicon glyphicon-remove',
                validating: 'glyphicon glyphicon-refresh'
            },
            fields: {
                setName: {
                    validators: {
                        notEmpty: {
                            message: '广告名称不能为空'
                        }
                    }
                },
                setOrder: {
                    validators: {
                        notEmpty: {
                            message: '排序不能为空'
                        },
                        regexp: {
                            regexp: /\d/,
                            message: "只能输入数字"
                        }
                    }
                },
                setUrl: {
                    validators: {
                        notEmpty: {
                            message: '链接不能为空'
                        }
                    }
                }
            }
        });
    });
</script>

</body>
</html>
