<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/11/28
  Time: 16:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>设置支付密码</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body class="bge6" style="background: #fff;">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0)" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>设置支付密码</span>
</div>

<div class="set_pay_paw2">
    <div class="set_pay_box">
        <input type="password" class="set_input_text payPassword" value="" placeholder="请输入六位密码" />
    </div>
    <div class="set_pay_box">
        <input type="password" class="set_input_text affirmPassword" value="" placeholder="请确认支付密码" />
    </div>
    <div class="set_pay_box">
        <p class="p1">注:此密码仅用于大汇堂余额支付使用</p>
    </div>
    <div class="set_pay_box">
        <button class="set_pay_btn">完成</button>
    </div>

</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script>
    $(".set_pay_btn").click(function(){
        let p1 = $(".payPassword").val();
        let p2 = $(".affirmPassword").val();
        if(p1 == "" && p2 == ""){
            layer.msg("请输入密码");
            return;
        }

        if(p1.length != 6 && p2.length != 6){
            layer.msg("密码长度不能超过6位");
            return;
        }

        if(p1 != p2){
            layer.msg("两次输入的密码不相等");
            return;
        }

        $.ajax({
            url:"/user/addPayPwd",
            type:"post",
            dataType:"json",
            data:{
                payPwd:p2
            },
            success:function(data){
                layer.msg("设置支付密码成功");
                setTimeout(function(){
                    history.back(-1);
                },1000);
            }
        });
    });
</script>
</body>

</html>