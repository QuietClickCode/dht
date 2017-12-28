<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <title>公众号设置</title>
    <meta charset="UTF-8">
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/wechat.css">
    <link rel="stylesheet" href="<%=path%>/js/filestyle/open-iconic/font/css/open-iconic-bootstrap.css">
</head>
<body>
<div id="mainbody">
    <div class="row">
        <div class="col-md-6">
            <form id="wxCodeForm" method="POST" enctype="multipart/form-data" class="form-horizontal" data-value="0">
                <div class="form-group">
                    <label class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <p>请登录公众号获取以下信息！<a  target="_blank" href="https://mp.weixin.qq.com/cgi-bin/loginpage?t=wxm2-login&lang=zh_CN">进入公众号平台</a></p>
                    </div>
                </div>
                <div class="form-group"  id="wxCodeFileUploadDiv">
                    <label for="dht_image_upload" class="col-sm-3 control-label">微信二维码:</label>
                    <div class="col-sm-6">
                        <input type="file" id="dht_image_upload" name="dht_image_upload">
                    </div>
                </div>

                <div class="form-group" id="showWxCodeImageDiv" style="display:none;">
                    <label for="dht_image_upload" class="col-sm-3 control-label">微信二维码:</label>
                    <div class="col-sm-6">
                        <img src="" id="uploadImage" width="160px;" height="160px;">&nbsp;&nbsp;
                        <button class="btn btn-default" onclick="clearCpLogo()" type="button">清除</button>
                    </div>
                </div>
            </form>
            <form class="form-horizontal" data-value="0" id="wxInfoForm">
                <input type="hidden" class="form-control" id="wxQrCode" name="wxQrCode"/>
                <div class="form-group">
                    <label for="wxOriginalId" class="col-sm-3 control-label">微信原始ID:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="微信原始ID" id="wxOriginalId" name="wxOriginalId" value="${curWx.wxOriginalId}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="wxName" class="col-sm-3 control-label">微信号:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" id="wxName" name="wxName" value="${curWx.wxName}" data-toggle="popover" data-placement="right">
                    </div>
                </div>

                <div class="form-group">
                    <label for="appId" class="col-sm-3 control-label">AppId:</label>
                    <div class="col-sm-6">
                        <input type="text" name="appId" class="form-control" id="appId" value="${curWx.appId}" data-toggle="popover" data-placement="right">
                    </div>
                </div>

                <div class="form-group">
                    <label for="appSecret" class="col-sm-3 control-label">AppSecret:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="appSecret" value="${curWx.appSecret}" id="appSecret">
                    </div>
                </div>
                <div class="form-group">
                    <label for="wxDomainName" class="col-sm-3 control-label">认证域名:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="wxDomainName" value="${curWx.wxDomainName}" id="wxDomainName" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="wxDomainUrl" class="col-sm-3 control-label">接收消息地址:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="wxDomainUrl" value="${curWx.wxDomainUrl}" id="wxDomainUrl" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label for="wxToken" class="col-sm-3 control-label">token:</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" name="wxToken" value="${curWx.wxToken}" id="wxToken" readonly>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <button class="btn btn-default" onclick="saveWxInfo()" type="button">保存</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
<%@include file="/common/common_bs_head_js.jsp"%>
<script type="text/javascript" src="<%=path%>/js/filestyle/bootstrap-filestyle.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/common/form.js"></script>
<script type="text/javascript">
    $("#showWxCodeImageDiv").hide();
    $("#wxCodeFileUploadDiv").hide();
    var wxQcCodeId="${curWx.wxQrCode}";
    $(function () {
       if(wxQcCodeId){
           $("#uploadImage").attr("src",'${curWx.wxQrCodeUrl}');
           $("#wxInfoForm #wxQrCode").val("${curWx.wxQrCode}");
           $("#showWxCodeImageDiv").show();
       }else{
           $("#wxCodeFileUploadDiv").show();
       }
        formValidater();
    });
    var wxCodeFileFormSubmitIdx;
    $('#wxCodeForm #dht_image_upload').filestyle({
        btnClass : "btn-primary",
        text:"选择文件",
        onChange:function(){
            wxCodeFileFormSubmitIdx = layer.load(2);
            wxCodeFileFormSubmit();
        }
    });

    let fileUpload="/file/imageUpload?isWatermark=false&isCompress=false&imageUse=goods"
    function wxCodeFileFormSubmit(){
        var formData = new FormData($( "#wxCodeForm" )[0]);
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
                    $("#showWxCodeImageDiv").show();
                    $("#wxCodeFileUploadDiv").hide();
                    $("#uploadImage").attr("src",returndata.url);
                    $("#wxInfoForm #wxQrCode").val(returndata.original);
                }
                layer.close(wxCodeFileFormSubmitIdx);
            },
            error: function (returndata) {
                layer.close(wxCodeFileFormSubmitIdx);
            }
        });
    }
    /**
     * 保存信息
     */
    var submitFlag=false;
    function saveWxInfo(){
        wxCodeFileFormSubmitIdx = layer.load(2);
        if(!submitFlag){
            submitFlag=true;
            //开启校验
            $('#wxInfoForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#wxInfoForm').data('bootstrapValidator').isValid()){
                layer.close(wxCodeFileFormSubmitIdx);
                submitFlag=false;
                return;
            }
            var formData=$("#wxInfoForm").serializeObject();
            $.ajax({
                url: "/wx/editorWxManager",
                type: 'POST',
                data: formData,
                dataType: "json",
                success: function (data) {
                    layer.close(wxCodeFileFormSubmitIdx);
                   if(data.status==0){
                       layer.msg("绑定成功");
                   }else{
                       let errorMsg=data.msg;
                       if(!errorMsg){
                           errorMsg="保存失败，请联系管理员";
                       }
                       layer.msg(errorMsg);
                   }
                   submitFlag=false;
                },
                error: function (returndata) {
                    layer.close(editSubmitIndex);
                }
            });
        }
    }
    //清除文件
    function clearCpLogo(){
        $('#wxInfoForm #dht_image_upload').filestyle('clear');
        $("#showWxCodeImageDiv").hide();
        $("#wxCodeFileUploadDiv").show();
        $("#wxInfoForm #wxQrCode").val('');
    }

    /**
     * form 校验
     * */
    function formValidater(){
        $('#wxInfoForm')
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
                    wxOriginalId: {
                        message: '微信原始ID',
                        validators: {
                            notEmpty: {
                                message: '微信原始ID不能为空'
                            }
                        }
                    },
                    appId: {
                        message: '微信AppId',
                        validators: {
                            notEmpty: {
                                message: '微信AppId不能为空'
                            }
                        }
                    },
                    appSecret: {
                        message: '微信AppSecret',
                        validators: {
                            notEmpty: {
                                message: '微信AppSecret不能为空'
                            }
                        }
                    }
                }
            });
    }
</script>
</body>
</html>
