<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/11/24
  Time: 15:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>安全管理</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .my-data-list .setPayPassword .text2{
            display: none;
            color: dodgerblue;
        }
    </style>
</head>
<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>安全管理</span>
</div>

<div class="my-data-list">
    <a href="/user/updateUserPhone" class="displayB">
        <span class="name">更改手机号码</span>
        <i class="icon-data-right"></i>
        <span class="text2">151****2828</span>
    </a>
</div>
<div class="my-data-list">
    <a href="/user/updatePayPwd" class="displayB setPayPassword">
        <span class="name"></span>
        <i class="icon-data-right"></i>
        <span class="text2">立即设置</span>
    </a>
</div>

<script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
<script>
    $(function () {
        $.ajax({
            url:"/user/queryLoginUserPayPwd",
            type:"post",
            dataType:"json",
            success:function(data){
                if(data.flag == true){
                    $(".setPayPassword").attr("href","/user/setPayPwd");
                    $(".setPayPassword").find(".name").text("支付密码");
                    $(".setPayPassword .text2").show();
                }else{
                    $(".setPayPassword").attr("href","/user/updatePayPwd");
                    $(".setPayPassword").find(".name").text("修改支付密码");
                    $(".setPayPassword .text2").hide();
                }
            }
        });
    });
</script>
</body>
</html>