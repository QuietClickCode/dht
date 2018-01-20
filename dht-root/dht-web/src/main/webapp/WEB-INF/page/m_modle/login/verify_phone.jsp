<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2018/1/20
  Time: 20:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>用户登录</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <link rel="stylesheet" type="text/css" href="/css/login.css">
    <style type="text/css">
        .form .code{
            overflow: hidden;
            position: relative;
        }

        #validateCodeImg{
            position: absolute;
            bottom: 0.1rem;
        }
        .container{
            display: none;
            position: fixed;
            top: 0;
            width: 100%;
            height: 100%;
            background-color: #fff;
        }

        .container_box{
            margin-top: 4rem;
        }
        .login_btn{
            display: block;
            text-align: center;
            padding-top: 0.3rem;
            padding-bottom: 0.3rem;
            width: 70%;
            margin-left: 15%;
            margin-bottom: 0.5rem;
            font-size: 0.3rem;
        }

        .container_box :first-child{
            background-color: #169bd5;
            color: #fff;
            border-radius: 0.1rem;
        }

        .container_box :last-child{
            border:1px solid rgba(0,0,0,0.2);
            border-radius: 0.1rem;
            margin-bottom: 0px;
        }
    </style>
</head>
<body>
<div class="login_app">
    <div class="form_login">
        <div class="login">
            <span class="user_register" style="">手机号码验证</span>
        </div>
        <div class="register_box" style="display: block;">
            <div class="form">
                <div class="input-wrp">
                    <input type="text" name="" class="phone_number" placeholder="输入手机号">
                </div>

                <div class="input-wrp" style="width: 60%; display: inline-block; float: left;">
                    <input type="text" name="" class="verify_code" placeholder="输入验证码">
                </div>

                <div style="display: inline-block;margin-top: 0.4rem;float: right;margin-right: 5%;">
                    <input type="button" class="get_verify_code" value="获取验证码" onclick="settime(this)">
                </div>
                <div class="sub_box" style="float: left;">
                    <a href="" class="sub" style="color: #fff;">验证</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script>
    var setTime;
    var countdown = 60;
    function settime(val) {
        if (countdown == 0) {
            val.removeAttribute("disabled");
            val.value="获取验证码";
            countdown = 60;
            return;
        } else {
            val.setAttribute("disabled", true);
            val.value="重新发送(" + countdown + ")";
            countdown--;
        }
        setTime = setTimeout(function() {
            settime(val)
        },1000)
    }
</script>
</body>
</html>