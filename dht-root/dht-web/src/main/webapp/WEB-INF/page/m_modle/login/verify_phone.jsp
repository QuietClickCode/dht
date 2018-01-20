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
        <!-- 是否绑定微信-->
        <input type="hidden" name="isBindWx" id="isBindWx" value="${isBindWx}">
        <!-- 登陆后重定向url -->
        <input type="hidden" name="redirectUrl" id="redirectUrl" value="${redirectUrl}">
        <div class="register_box" style="display: block;">
            <div class="form">
                <div class="input-wrp">
                    <input type="text" name="phone_number" id="phone_number" class="phone_number"  placeholder="输入手机号">
                </div>

                <div class="input-wrp" style="width: 60%; display: inline-block; float: left;">
                    <input type="text" name="verify_code" id="verify_code" class="verify_code"  placeholder="输入验证码">
                </div>

                <div style="display: inline-block;margin-top: 0.4rem;float: right;margin-right: 5%;">
                    <input type="button" class="get_verify_code" value="获取验证码" onclick="getValidCode(this)">
                </div>
                <div class="sub_box" style="float: left;">
                    <a href="javascript:void(0);" class="sub" style="color: #fff;" onclick="userLogin()">验证</a>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer_mobile/layer.js"></script>
<script>
//    var setTime;
//    var countdown = 60;
//    function settime(val) {
//        if (countdown == 0) {
//            val.removeAttribute("disabled");
//            val.value="获取验证码";
//            countdown = 60;
//            return;
//        } else {
//            val.setAttribute("disabled", true);
//            val.value="重新发送(" + countdown + ")";
//            countdown--;
//        }
//        setTime = setTimeout(function() {
//            settime(val)
//        },1000)
//    }
    var isLoging=false;
    /**
     * 用户登陆
     */
    function userLogin(){
        if(!isLoging&&verifyForm()){
            isLoging=true;
            let loginData=new Object();
            loginData["phone"]=$("#phone_number").val();
            loginData["validateCode"]=$("#verify_code").val();
            loginData["isBindWx"]=$("#isBindWx").val();
            $.ajax({
                url: "/user/userPhoneLogin",
                type: "post",
                dataType: "json",
                data:loginData,
                success: function (data) {
                    isLoging=false;
                    if(data.status==0){
                        //提示
                        layer.open({
                            content: "手机绑定成功,即将为你跳转页面",
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


    var countdown=60;
    var id = "";
    function getValidCode($this) {
        if(verifyForm() == false)
            return;
        settime($this);
        phone = $("#phone_number").val();
        $.ajax({
            url:"/user/sendSmsValidCode",
            type:"post",
            dataType:"json",
            data:{
                phone:phone
            },
            success:function (data) {
                id = data.data;
                layer.open({
                    content: '短信已发送'
                    ,skin: 'msg'
                    ,time: 1
                });
            }
        });
    }
    var setTime;
    var phone;
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


    function verifyForm() {
        if($("#phone_number").val() == ""){
            layer.open({
                content: '手机号不能为空'
                ,skin: 'msg'
                ,time: 1
            });
            return false;
        }
        return true;
    }
</script>
</body>
</html>