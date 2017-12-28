<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE>
<html>
<head>
    <meta charset="utf-8">
    <title>商品管理</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" href="<%=path%>/css/gclassa.css">
    <link rel="stylesheet" href="<%=path%>/css/gclassc.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/zTreeStyle/zTreeStyle.css">
    <link rel="stylesheet" href="<%=path%>/js/ztree/css/demo.css">
    <link rel="stylesheet" href="<%=path%>/js/timer/css/build.css">
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <link rel="stylesheet" type="text/css" href="http://cdn.bootcss.com/font-awesome/4.6.0/css/font-awesome.min.css">
    <link rel="stylesheet" href="<%=path%>/js/toast/css/toastr.css">


    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/ueditor.all.js"> </script>

    <!--建议手动加在语言，避免在ie下有时因为加载语言失败导致编辑器加载失败-->
    <!--这里加载的语言文件会覆盖你在配置项目里添加的语言类型，比如你在配置项目里配置的是英文，这里加载的中文，那最后就是中文-->
    <script type="text/javascript" charset="utf-8" src="<%=path%>/ueditor/lang/zh-cn/zh-cn.js"></script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/jquery.min.js"> </script>
    <script type="text/javascript" charset="utf-8" src="<%=path%>/js/common/form.js"> </script>
    <script type="text/javascript" src="<%=path%>/js/filestyle/bootstrap-filestyle.min.js"></script>
    <style>
        li{
            list-style: none;
        }
        #chosegclassdiv a:hover{
            background: #337AB7;
            cursor: pointer;
            text-decoration: none;
            color:#fff;
        }
        .myactive{
            background: #337AB7;
        }
    </style>
</head>

<body>
<script type="text/plain" id="j_ueditorupload" style="height:5px;display:none;" ></script>
<script>
    //实例化编辑器
    var o_ueditorupload = UE.getEditor('j_ueditorupload',
        {
            autoHeightEnabled:false
        });
    o_ueditorupload.ready(function ()
    {

        o_ueditorupload.hide();//隐藏编辑器

        //监听图片上传
        o_ueditorupload.addListener('beforeInsertImage', function (t,arg)
        {
            console.log(arg);
            console.log(t);

//            if(uploadImgFlag==1){
//                for(var i=0; i<arg.length; i++){
//                    var imgdiv = '<div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">'+
//                        '<img src="'+arg[i].src+'" style="width: 100%;height: 100%;" >'+
//                        '<div onclick="deleteGoodsImage(this,'+arg[i].alt+')" style="display: none; position: absolute;width: 100%;background-color: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>'+
//                        '</div>';
//                    $('#uploadImgBtn').before(imgdiv);
//                    newImgArr.push(arg[i].alt);
//                }
//            }else if(uploadImgFlag==0){
//                for(var i=0; i<arg.length; i++){
//                    var html = '<div onmouseenter="showDeleteImage(this)" onmouseleave="hideDeleteImage(this)" style="width:18%;height: 100px;position: relative;float: left;margin-left: 1%;margin-right: 1%;margin-top: 10px;">'+
//                        '<img src="'+arg[i].src+'" style="width: 100%;height: 100%;" >'+
//                        '<div onclick="deletegsgImage(this,'+arg[i].alt+')" style="display: none; position: absolute;width: 100%;background: red;color:white;top:0px;text-align: center;cursor: pointer;z-index: 10">删除</div>'+
//                        '</div>';
//                    $('#uploadGoodsSpecilgoodscredentialImgBtn').after(html);
//                    addImgArr.push(arg[i].alt);
//                }
//
//            }



            alert('这是图片地址：'+arg[0].src);
        });

        /* 文件上传监听
         * 需要在ueditor.all.min.js文件中找到
         * d.execCommand("insertHtml",l)
         * 之后插入d.fireEvent('afterUpfile',b)
         */
        o_ueditorupload.addListener('afterUpfile', function (t, arg)
        {
            console.log(arg)
            console.log(t)
            alert('这是文件地址：'+arg[0].url);
        });
    });

    //弹出图片上传的对话框
    function upImage()
    {
        var myImage = o_ueditorupload.getDialog("insertimage");
        myImage.open();
    }
    //弹出文件上传的对话框
    function upFiles()
    {
        var myFiles = o_ueditorupload.getDialog("attachment");
        myFiles.open();
    }

    //重写图片上传地址
    UE.Editor.prototype._bkGetActionUrl = UE.Editor.prototype.getActionUrl;
    UE.Editor.prototype.getActionUrl = function(action) {
        //判断路径   这里是config.json 中设置执行上传的action名称
        console.log(action)
        if (action == 'uploadimage') {
//            return 'http://localhost:8080/file/imageUpload?type=goods&isWatermark=false&isCompress=false';
            return ueditorUploadUrl("goods",false,false);
            //上传视频
        } else if (action == 'uploadvideo') {
            return '';
        } else {
            console.log(this._bkGetActionUrl.call(this, action));
            return this._bkGetActionUrl.call(this, action);
        }
    }
</script>
<div class="tab-pane active" id="goodsPane">
    <div class="modal-body" style="position: relative">
        <div class="row">
            <div class="col-lg-6" style="height:49px">
                <form id="cpImagesForm" method="POST" style="margin-bottom: 0px;" enctype="multipart/form-data">
                    <div class="row">
                        <div class="col-lg-4" id="cpLogoDiv">
                            <div class="input-group form-group">
                                    <span class="input-group-addon">
                                        项目Logo:
                                    </span>
                                <input type="file" id="dht_image_upload" name="dht_image_upload">
                            </div>
                        </div>
                        <div class="col-lg-4" id="clearCpLogoDiv" style="display: none">
                            <div class="input-group form-group">
                                <span class="input-group-addon">
                                        优惠卷图片:
                                    </span>
                                <button class="btn btn-default" type="button" onclick="clearCpLogo()">清除</button>
                            </div>
                        </div>
                        <div class="col-lg-4" id="uploadImageDiv" style="display: none">
                            <div class="input-group form-group">
                                <img src="" id="uploadImage" width="96px;" height="48px;">
                            </div>
                        </div>
                    </div>
                    <input type="hidden" id="cpLogo">
                </form>
            </div>
        </div>
        <form id="editorProjectForm">
            <input type="hidden" name="pid" id="pid">
            <input type="hidden" name="version" id="version">
            <input type="hidden" name="isDelete" id="isDelete">

            <div class="row">
                <div class="col-lg-6" style="height:49px;">
                    <div class="input-group form-group">
                        <span class="input-group-addon">
                            项目名称:
                        </span>
                        <input type="text" class="form-control" name="pname" id="pname">
                    </div>
                </div>
                <div class="col-lg-6" style="height:49px">
                    <div class="input-group form-group">
                        <span class="input-group-addon">
                             地理位置:
                        </span>
                        <input id="paddress" name="paddress" class="form-control" />
                    </div>
                </div>
                <div class="col-lg-6" style="height:49px">
                    <div class="input-group form-group">
                        <span class="input-group-addon">
                             占地面积:
                        </span>
                        <input id="parea" name="parea" class="form-control" />
                    </div>
                </div>
                <div class="col-lg-6" style="height:49px;">
                    <div class="input-group form-group">
                        <span class="input-group-addon">
                             容纳户数:
                        </span>
                        <input id="pnum" name="pnum"  class="form-control"/>
                    </div>
                </div>
                <br/>

                <br/>
                <div class="col-lg-12" style="height:49px">
                    <div class="input-group form-group">
                        <span class="input-group-addon">
                             宣传图片:
                        </span>
                        <div id="imgsdiv" class="row">
                            <div class="col-lg-2">
                                <img style="width: 100%;height: 100%">
                            </div>
                            <div class="col-lg-2">
                                <button class="btn btn-default" onclick="upImage();">+</button>
                            </div>
                        </div>
                    </div>
                </div>


                <div class="col-lg-12">
                    <div class="input-group form-group">
                        <span class="input-group-addon">
                            详情描述:
                        </span>
                        <input id="gdescription" name="gdescription" type="hidden">
                        <div style="width: 100%">
                            <script id="editor" type="text/plain" style="width:100%;height:500px;"></script>
                        </div>
                    </div>
                </div>
            </div>
        </form>
        <center>
            <button onclick="uploadProject();" class="btn btn-success" >保存</button>
        </center>

    </div>
</div>
<script src="/js/layer/layer.js"></script>
<script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>
<script>
    var formData=$("#editorGoodsForm").serializeObject();
    var editSubmitIndex;
    $(function () {
        $('#dht_image_upload').filestyle({
            btnClass : "btn-primary",
            text:"选择文件",
            onChange:function(){
                editSubmitIndex = layer.load(2);
                cpImagesFormSummit();
            }
        });
    });

    var fileUpload="/file/imageUpload?isWatermark=false&isCompress=false&imageUse=goods";
    function cpImagesFormSummit(){
        var formData = new FormData($( "#cpImagesForm" )[0]);
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
                    $("#uploadImageDiv").show();
                    $("#cpLogoDiv").hide();
                    $("#clearCpLogoDiv").show();
                    $("#uploadImage").attr("src",returndata.url);
                    $("#cpLogo").val(returndata.original);
                }
                layer.close(editSubmitIndex);
            },
            error: function (returndata) {
                layer.close(editSubmitIndex);
            }
        });
    }

    <!--加载项目-->
    function loadProject() {
        $.ajax({
            url:"/project/queryProject",
            type:"post",
            data: {},
            dataType: "json",
            success:function (data) {
                var project = data.project;
                if(project!=null){
                    var pid = project.pid;
                    var pname = project.pname;
                    var pnum = project.pnum;
                    var parea = project.parea;
                    var version = project.version;
                    var paddress = project.paddress;
                    var pdescription = project.pdescription;
                    var logoImgUrl = project.logoImgUrl;
                    var imgsList = project.imgsList;

                    $('#pid').val(pid);
                    $('#pname').val(pname);
                    $('#pnum').val(pnum);
                    $('#parea').val(parea);
                    $('#version').val(version);
                    $('#paddress').val(paddress);
                    ue.setContent(pdescription,false);
                }
            }
        });
    }

    <!--上传项目-->
    function uploadProject() {
//        $('#editorProjectForm').data('bootstrapValidator').validate();
//        if(!$('#editorProjectForm').data('bootstrapValidator').isValid()){
//            return;
//        }

        var pid = $('#pid').val();
        var pnum = $('#pnum').val();
        var pname = $('#pname').val();
        var parea = $('#parea').val();
        var version = $('#version').val();
        var paddress = $('#paddress').val();
        var pdescription = ue.getContent();
        var plogoid = $('#cpLogo').val();

        var url = '';
        if(pid==''){
            url = '/project/saveProject';
        }else{
            url = '/project/updateProject';
        }

        $.ajax({
            url:url,
            type:"post",
            data: {pid:pid,
                pname:pname,
                parea:parea,
                version:version,
                paddress:paddress,
                pdescription:pdescription,
                plogoid:plogoid,
                pnum:pnum},
            dataType: "json",
            success:function (data) {
                if(data.status==0){
                    layer.msg('操作成功');
                }
            }
        });
    }


    //清除文件
    function clearCpLogo(){
        $('#dht_image_upload').filestyle('clear');
        $("#uploadImageDiv").hide();
        $("#cpLogoDiv").show();
        $("#clearCpLogoDiv").hide();
        $("#cpLogo").val('');
    }
    function validteData() {
        $('#editorProjectForm').bootstrapValidator({
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
                pname: {
                    message: '项目名称未通过',
                    validators: {
                        notEmpty: {
                            message: '项目名称不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '项目名称长度在1-30之间'
                        }
                    }
                },
                paddress: {
                    message: '地址未通过',
                    validators: {
                        notEmpty: {
                            message: '地址不能为空'
                        },
                        stringLength: {
                            min: 1,
                            max: 30,
                            message: '地址长度在1-30之间'
                        }
                    }
                },
                parea: {
                    validators: {
                        notEmpty: {
                            message: '占地面积不能为空'
                        },
                        regexp: {
                            regexp: /\d/,
                            message: "只能输入数字"
                        }
                    }
                },
                pnum: {
                    validators: {
                        notEmpty: {
                            message: '容纳户数不能为空'
                        },
                        regexp: {
                            regexp: /\d/,
                            message: "只能输入数字"
                        }
                    }
                }
            }
        });
    }
</script>

<!--调用函数-->
<script>
    loadProject();
</script>

<!--百度编辑器-->
<script>
    var ue = UE.getEditor('editor');
</script>
</body>



</html>
