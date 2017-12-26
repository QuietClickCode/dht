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
    </style>
</head>
<body>
<div class="login_app">
    <div class="form_login">
        <div class="login">
            <span class="user_login active">用户登录</span>
            <span class="user_register">快速注册</span>
        </div>
        <!-- 是否绑定微信-->
        <input type="hidden" name="isBindWx" id="isBindWx" value="${isBindWx}">
        <!-- 登陆后重定向url -->
        <input type="hidden" name="redirectUrl" id="redirectUrl" value="${redirectUrl}">

        <div class="login_box">
            <form id="userLoginForm">
                <div class="form">
                    <div class="input-wrp username">
                        <input type="text" name="account" id="account" placeholder="用户名/手机号">
                    </div>

                    <div class="input-wrp password">
                        <input type="password" name="pwd" id="pwd" placeholder="请输入密码">
                    </div>

                    <div class="input-wrp code">
                        <input type="text" name="validateCode" id="validateCode" placeholder="请输入验证码">
                        <img onclick="changeCode()" id="validateCodeImg">
                    </div>
                </div>

                <div class="sub_box">
                    <a href="javascript:void(0)" class="change_password">忘记密码？</a>
                    <a href="javascript:void(0);" class="sub" style="color: #fff" onclick="userLogin()">登陆</a>
                </div>
            </form>
        </div>

        <div class="register_box">
            <div class="form">
                <div class="input-wrp">
                    <input type="text" name="" class="phone_number" placeholder="输入手机号">
                </div>

                <div class="input-wrp" style="width: 60%; display: inline-block; float: left;">
                    <input type="text" name="" class="verify_code" placeholder="输入验证码">
                </div>

                <div style="display: inline-block;margin-top: 0.4rem;float: right;margin-right: 5%;">
                    <a class="get_verify_code">获取验证码</a>
                </div>
                <div class="sub_box" style="float: left;">
                    <a href="" class="sub" style="color: #fff;">注册</a>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="ui_draggable" id="sfczzhDiv"></div>
<div class="popover" id="sfczzhtsDiv">
    <div class="dialog">
        <div class="dialog_hd">
            <a>是否存在账号?</a>
        </div>
        <div class="dialog_ft">
            <div class="dialog_ft_box">
                <a href="javascript:void(0);" onclick="wxLoginNoUser()">无账号</a>
            </div>
            <div class="dialog_ft_box">
                <a href="javascript:void(0);" onclick="haveUser()">已有账号</a>
            </div>
        </div>
    </div>
</div>
<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer_mobile/layer.js"></script>
<script>
    $(".login span").click(function(){
        let index = $(this).index(".login span");
        if(index == 0){
            $(".user_login").addClass("active");
            $(".login span").eq(index).siblings().removeClass("active");
            $(".login_box").show();
            $(".register_box").hide();
        }else if(index == 1){
            $(".user_register").addClass("active");
            $(".login span").eq(index).siblings().removeClass("active");
            $(".login_box").hide();
            $(".register_box").show();
        }
    });
    $(function(){
        let isBindWx=${isBindWx};
        if(isBindWx==false){
            haveUser();
        }
        changeCode();
    });

    var isLoging=false;
    /**
     * 用户登陆
     */
    function userLogin(){
        if(!isLoging){
            isLoging=true;
            let loginData=new Object();
            loginData["account"]=$("#userLoginForm #account").val();
            loginData["pwd"]=$("#userLoginForm #pwd").val();
            loginData["validateCode"]=$("#userLoginForm #validateCode").val();
            loginData["isBindWx"]=$("#isBindWx").val();
            $.ajax({
                url: "/user/userLogin",
                type: "post",
                dataType: "json",
                data:loginData,
                success: function (data) {
                    isLoging=false;
                    if(data.status==0){
                        //提示
                        layer.open({
                            content: "登陆成功，即将为你跳转",
                            skin: 'msg',
                            time: 2, //2秒后自动关闭
                            end:function(){
                                let redirectUrl=$("#redirectUrl").val();
                                if(redirectUrl){
                                    window.location.href=redirectUrl;
                                }else{
                                    window.location.href="/";
                                }
                            }
                        });
                    }
                    //提示
                    layer.open({
                        content: data.msg,
                        skin: 'msg',
                        time: 2 //2秒后自动关闭
                    });
                }
            });
        }
    }
    //改变验证码
    function changeCode(){
        var date = new Date();
        var time = date.getFullYear()+""+(date.getMonth()+1)+""+date.getDate()+""+date.getHours()+""+date.getMinutes()+""+date.getSeconds();
        var url = "/pcrimg?time="+time;
        $("#validateCodeImg").attr("src",url);
    }
    /**
     * 微信登陆 没有关联用户时 无帐号选择
     * @type {boolean}
     */
    var wxLoginFlag = false;
    function wxLoginNoUser(){
        if(!wxLoginFlag){
            wxLoginFlag=true;
            $.ajax({
                url: "/user/wxLoginNoUser",
                type: "post",
                dataType: "json",
                success: function (data) {
                    wxLoginFlag=false;
                    if(data.status==0){
                        let redirectUrl=$("#redirectUrl").val();
                        if(redirectUrl){
                            window.location.href=redirectUrl;
                        }else{
                            window.location.href="/";
                        }
                    }
                    //提示
                    layer.open({
                        content: data.msg,
                        skin: 'msg',
                        time: 2 //2秒后自动关闭
                    });
                }
            });
        }
    }

    function haveUser(){
        $("#sfczzhDiv").hide();
        $("#sfczzhtsDiv").hide();
    }
</script>
</body>
</html>
