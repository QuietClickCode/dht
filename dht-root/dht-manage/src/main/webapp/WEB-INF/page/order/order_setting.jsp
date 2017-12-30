<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>支付设置</title>
    <meta charset="UTF-8">
    <%@include file="/common/common_bs_head_css.jsp"%>
    <link rel="stylesheet" type="text/css" href="<%=path%>/css/wechat.css">
    <link rel="stylesheet" href="<%=path%>/js/filestyle/open-iconic/font/css/open-iconic-bootstrap.css">
</head>
<body>
<div id="mainbody">
    <div class="row">
        <div class="col-md-8">
            <br>
            <br>
            <form id="wxPayForm" method="POST" enctype="multipart/form-data" class="form-horizontal" data-value="0">
                <input type="hidden" class="form-control" id="wxCertificateCode" name="wxCertificateCode"/>
                <div class="form-group">
                    <label for="wxMchId" class="col-sm-3 control-label">订单失效时间：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="请输入订单失效时间,单位小时" id="wxMchId" name="wxMchId" value="${wxPay.wxMchId}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="wxApiKey" class="col-sm-3 control-label">自动确认收货时间：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="请输入自动确认收货时间,单位天" id="wxApiKey" name="wxApiKey" value="${wxPay.wxApiKey}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="wxApiKey" class="col-sm-3 control-label">订单完成时间：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="请输入订单完成时间,单位天" id="wxApiKey" name="wxApiKey" value="${wxPay.wxApiKey}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="wxApiKey" class="col-sm-3 control-label">平台默认快递费：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="请输入平台默认快递费,单位元" id="wxApiKey" name="wxApiKey" value="${wxPay.wxApiKey}" >
                    </div>
                </div>
                <div class="form-group">
                    <label for="wxApiKey" class="col-sm-3 control-label"></label>
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
    $('#wxCertificateCodeForm #dht_image_upload').filestyle({
        badge: true,
        input : false,
        btnClass : 'btn-primary',
        text:"替换文件",
        /*htmlIcon : '<span class="oi oi-folder"></span> ',*/
        onChange:function(){
            wxCodeFileFormSubmitIdx = layer.load(2);
            wxCodeFileFormSubmit();
        }
    });

    let fileUpload="/file/imageUpload?isWatermark=false&isCompress=false&imageUse=goods"
    function wxCodeFileFormSubmit(){
        var formData = new FormData($( "#wxCertificateCodeForm" )[0]);
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
                    $("#uploadNm").text(returndata.title);
                    $("#uploadNm").text(returndata.title);
                    $("#wxPayForm #wxCertificateCode").val(returndata.original);
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
            $('#wxPayForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#wxPayForm').data('bootstrapValidator').isValid()){
                layer.close(wxCodeFileFormSubmitIdx);
                submitFlag=false;
                return;
            }
            var formData=$("#wxPayForm").serializeObject();
            $.ajax({
                url: "/wx/editorWxPay",
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
                    layer.close(wxCodeFileFormSubmitIdx);
                }
            });
        }
    }

    /**
     * form 校验
     * */
    function formValidater(){
        $('#wxPayForm')
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
                    wxMchId: {
                        message: '微信商户号',
                        validators: {
                            notEmpty: {
                                message: '商户号不能为空'
                            }
                        }
                    },
                    wxApiKey: {
                        message: '微信商户秘钥',
                        validators: {
                            notEmpty: {
                                message: '微信商户秘钥不能为空'
                            }
                        }
                    }
                }
            });
    }
</script>
</body>
</html>
