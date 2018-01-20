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
            <form id="orderSettingForm" method="POST" enctype="multipart/form-data" class="form-horizontal" data-value="0">
                <div class="form-group">
                    <label for="orderExpireDate" class="col-sm-3 control-label">订单失效时间：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="请输入订单失效时间,单位小时" id="orderExpireDate" name="orderExpireDate" value="${params.ORDER_EXPIRE_DATE}" >
                    </div>
                    <label for="orderExpireDate" class="col-sm-3 control-label" style="text-align:left;color:#8a8686;">请输入订单失效时间,单位小时</label>
                </div>
                <div class="form-group">
                    <label for="orderConfirmDate" class="col-sm-3 control-label">自动确认收货时间：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="请输入自动确认收货时间,单位天" id="orderConfirmDate" name="orderConfirmDate" value="${params.ORDER_CONFIRM_DATE}" >
                    </div>
                    <label class="col-sm-3 control-label" style="text-align:left;color:#8a8686;">请输入自动确认收货时间,单位天</label>
                </div>
                <div class="form-group">
                    <label for="orderCompleteDate" class="col-sm-3 control-label">订单完成时间：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="请输入订单完成时间,单位天" id="orderCompleteDate" name="orderCompleteDate" value="${params.ORDER_COMPLETE_DATE}" >
                    </div>
                    <label class="col-sm-3 control-label" style="text-align:left;color:#8a8686;">请输入订单完成时间,单位天</label>
                </div>
                <div class="form-group">
                    <label for="defaultLogisPrice" class="col-sm-3 control-label">平台默认快递费：</label>
                    <div class="col-sm-6">
                        <input type="text" class="form-control" placeholder="请输入平台默认快递费,单位元" id="defaultLogisPrice" name="defaultLogisPrice" value="${params.DEFAULT_LOGISTICS_PRICE/100}" >
                    </div>
                    <label class="col-sm-3 control-label" style="text-align:left;color:#8a8686;">请输入平台默认快递费,单位元</label>
                </div>
                <div class="form-group">
                    <label class="col-sm-3 control-label"></label>
                    <div class="col-sm-6">
                        <button class="btn btn-default" onclick="saveOrderSetting()" type="button">保存</button>
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
    $(function () {
        formValidater();
    });
    /**
     * 保存信息
     */
    var submitFlag=false;
    function saveOrderSetting(){
        wxCodeFileFormSubmitIdx = layer.load(2);
        if(!submitFlag){
            submitFlag=true;
            //开启校验
            $('#orderSettingForm').data('bootstrapValidator').validate();
            //判断校验是否通过
            if(!$('#orderSettingForm').data('bootstrapValidator').isValid()){
                layer.close(wxCodeFileFormSubmitIdx);
                submitFlag=false;
                return;
            }
            var formData=$("#orderSettingForm").serializeObject();
            $.ajax({
                url: "/order/orderSetting",
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
        $('#orderSettingForm')
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
                    orderExpireDate: {
                        message: '订单失效时间不能为空',
                        validators: {
                            notEmpty: {
                                message: '订单失效时间不能为空'
                            },
                            regexp:{
                                regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                                message:'订单失效时间只允许在10位整数和2位小数范围内'
                            }
                        }
                    },
                    orderConfirmDate: {
                        message: '自动确认收货时间不能为空',
                        validators: {
                            notEmpty: {
                                message: '自动确认收货时间不能为空'
                            },
                            regexp:{
                                regexp:/^([0-9]{1,10}|0)?$/,
                                message:'自动确认收货时间只能为整数'
                            }
                        }
                    },
                    orderCompleteDate: {
                        message: '订单完成时间不能为空',
                        validators: {
                            notEmpty: {
                                message: '订单完成时间不能为空'
                            },
                            regexp:{
                                regexp:/^([0-9]{1,10}|0)?$/,
                                message:'订单完成时间只能为整数'
                            }
                        }
                    },
                    defaultLogisPrice: {
                        message: '平台默认快递费不能为空',
                        validators: {
                            notEmpty: {
                                message: '平台默认快递费不能为空'
                            },
                            regexp:{
                                regexp:/^([0-9]{1,10}|0)(\.\d{1,2})?$/,
                                message:'平台默认快递费只允许在10位整数和2位小数范围内'
                            }
                        }
                    }
                }
            });
    }
</script>
</body>
</html>
