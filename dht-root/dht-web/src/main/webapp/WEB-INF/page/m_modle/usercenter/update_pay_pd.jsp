<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/11/26
  Time: 23:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改支付密码</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body class="bge6" style="background: #fff;">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" class="icon-return" onclick="window.history.back(); return false;"></a>
    <span>修改支付密码</span>
</div>

    <div class="set_pay_paw2">
        <div class="set_pay_box">
            <input type="password" maxlength="14" minlength="6" class="set_input_text"  id="oldPayPassword" value="" placeholder="输入当前密码" />
        </div>

        <div class="set_pay_box">
            <input type="password" maxlength="14" minlength="6" class="set_input_text"  id="payPassword" value="" placeholder="输入密码" />
        </div>

        <div class="set_pay_box">
            <input type="password" maxlength="14" minlength="6" class="set_input_text"  id="newPayPassword" value="" placeholder="确认新密码" />
        </div>
        <div class="set_pay_box">
            <p class="p1">注:此密码仅用于大汇堂余额支付使用</p>
        </div>
        <div class="set_pay_box">
            <button class="set_pay_btn" onclick="setPayPassword()">完成</button>
        </div>
    </div>


<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer_mobile/layer.js"></script>
<script>
    $(".set_input_text").blur(function(){
        let val = $(this).val();
        if(val.length != 6){
            $(".set_pay_btn").attr("disabled","disabled");
            layer.open({
                content: '请设置6位的支付密码'
                ,skin: 'msg'
                ,time: 1
            });
        }else{
            $(".set_pay_btn").removeAttr("disabled");
        }
    });

    function setPayPassword() {
        let paypwd = $("#payPassword").val();
        let affirmpwd = $("#newPayPassword").val();
        let olpwd = $("#oldPayPassword").val();
        if(paypwd != affirmpwd){
            layer.open({
                content: '两次输入的密码不一致'
                ,skin: 'msg'
                ,time: 1
            });
            return;
        }

        $.ajax({
            url:"/user/changePayPwd",
            type:"post",
            dataType:"json",
            data:{
                oldPayPwd:olpwd,
                payPwd:paypwd
            },
            success:function (data) {
                layer.open({
                    content: '修改支付密码成功'
                    ,skin: 'msg'
                    ,time: 1
                });
                setTimeout(function(){
                    history.back(-1);
                },1000);
            }
        });
    }
</script>
</body>

</html>