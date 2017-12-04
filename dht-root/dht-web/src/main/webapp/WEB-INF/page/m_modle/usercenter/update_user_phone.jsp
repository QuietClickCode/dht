<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/11/26
  Time: 22:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>更换手机号</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        #replace_nav li{
            font-size: 0.25rem;
        }
    </style>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0)" class="icon-return" onclick="window.history.back(); return false;"></a>
    <span>更换手机号</span>
</div>

<form action="" method="post">
    <ul id="replace_nav" class="replace_nav clearfix">
        <li class="active">安全验证</li>
        <li>绑定手机</li>
    </ul>

    <div class="replace_container">
        <div class="replace_container_item active">
            <table class="bind_phone_box" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td2 td_line">
                        <input type="text" class="input_text user-phone" readonly/>
                    </td>
                    <td class="td_line">
                        <input type="button" class="btn_cod" value="获取验证码" onclick="test(this)" />
                    </td>
                </tr>
                <tr>
                    <td class="td3 td_line" colspan="2">
                        <input type="number" class="input_text validCode" placeholder="请输入验证码" />
                    </td>
                </tr>

                <tr>
                    <td class="td4" colspan="3">
                        <span class="finish_btn verifyValidCode">确定</span>
                    </td>
                </tr>
            </table>
        </div>
        <div class="replace_container_item">
            <table class="bind_phone_box" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td2 td_line">
                        <input type="number" class="input_text newPhoneNumber" placeholder="请输入新手机号码" />
                    </td>
                    <td class="td_line">
                        <input type="button" class="btn_cod" id="btn_cod" value="获取验证码" onclick="getvalidCode(this)" />
                    </td>
                </tr>
                <tr>
                    <td class="td3 td_line" colspan="2">
                        <input type="number" class="input_text newValidCode" placeholder="请输入验证码" />
                    </td>
                </tr>
                <tr>
                    <td class="td4" colspan="3">
                        <span class="finish_btn bind_phone">确定</span>
                    </td>
                </tr>
            </table>
        </div>
    </div>
</form>

<div class="prompt_box">
    <p>温馨提示:</p>
    <p>1、为保障你的账号安全,变更重要信息需要身份验证。</p>
    <p>2、有疑问请拨400-302860482或联系在线客服。</p>
</div>


<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer_mobile/layer.js"></script>
<script>
    var countdown=60;
    var id = "";
    function test($this) {
        settime($this);
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


    var phone;
    $(function(){
        $.ajax({
            url:"/user/queryLoginUserPayPwd",
            type:"post",
            dataType:"json",
            success:function (data) {
                let userPhone = data.userPhone;
                phone = data.userPhone;
                let p = userPhone.substr(0, 3) + "****" + userPhone.substr(7, 11);
                $(".user-phone").val(p);
            }
        });
    });

    $(".verifyValidCode").click(function () {
        let val = $(".validCode").val();
        $.ajax({
            url:"/user/verifyValidCode",
            type:"post",
            dataType:"json",
            data:{
                phone:phone,
                type:0,
                code:val,
                curDate:new Date()
            },
            success:function (data) {
                if(data.data == false) {
                    layer.open({
                        content: '验证码错误'
                        ,skin: 'msg'
                        ,time: 1
                    });

                }else if(data.data == true) {
                    layer.open({
                        content: '验证成功'
                        ,skin: 'msg'
                        ,time: 1
                    });

                    setTimeout(function(){
                        layer.close();
                        clearTimeout(setTime);
                        countdown = 60;
                        $("#replace_nav li").eq(1).addClass("active").siblings("#replace_nav li").removeClass("active");
                        $(".replace_container .replace_container_item").eq(1).
                            addClass("active").siblings(".replace_container .replace_container_item").removeClass("active");
                    },1000);
                }
            }
        });
    });
    
    function getvalidCode($this) {
        let phoneNumber = $(".newPhoneNumber").val();
        if(phoneNumber == ""){
            layer.open({
                content: '请输入新的手机号'
                ,skin: 'msg'
                ,time: 1
            });

            return;
        }
        settime($this);
        $.ajax({
            url:"/user/sendSmsValidCode",
            type:"post",
            dataType:"json",
            data:{
                phone:phoneNumber
            },
            success:function (data) {
                layer.open({
                    content: '短信已发送'
                    ,skin: 'msg'
                    ,time: 1
                });

            }
        });
    }

    $(".bind_phone").click(function () {
        let validCode = $(".newValidCode").val();
        let phoneNumber = $(".newPhoneNumber").val();
        if(validCode == "" || validCode.length != 6)
            layer.open({
                content: '验证码不能为空,长度不能超过六位'
                ,skin: 'msg'
                ,time: 1
            });

        $.ajax({
            url:"/user/bindPhone",
            type:"post",
            dataType:"json",
            data:{
                phone:phoneNumber,
                code:validCode
            },
            success:function (data) {
                if(data.data == true){
                    layer.open({
                        content: '绑定手机号成功'
                        ,skin: 'msg'
                        ,time: 1
                    });

                    setTimeout(function(){
                        history.back(-1);
                    },2000);
                }
            }
        });
    });
</script>
</body>

</html>