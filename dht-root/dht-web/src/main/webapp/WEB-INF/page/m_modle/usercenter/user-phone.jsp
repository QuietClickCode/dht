<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/11/24
  Time: 10:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>绑定手机号</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <script src="/js/jquery-2.1.4.min.js"></script>
    <script src="/js/layer/layer.js"></script>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back();return false;" class="icon-return"></a>
    <span>绑定手机号</span>
</div>

<form action="" method="post">
    <table class="bind_phone_box" border="0" cellspacing="0" cellpadding="0">
        <tr>
            <td class="td1">手机号</td>
            <td class="td3" colspan="2">
                <input type="number" class="input_text" name="" id="check-phone" value="" placeholder="请输入手机号" />
            </td>
        </tr>
        <tr>
            <td class="td1">验证码</td>
            <td class="td2">
                <input type="number" class="input_text" name="" id="cod-input" value="" placeholder="请输入验证码" />
            </td>
            <td>
                <input type="button" class="btn_cod" id="btn_cod" onclick="settime()" value="获取验证码" />
            </td>
        </tr>
        <tr>
            <td class="td4" colspan="3">
                <span class="init_btn">确定</span>
            </td>
        </tr>
    </table>
</form>

<div class="prompt_box">
    <p>温馨提示:</p>
    <p>1、为保障你的账号安全,变更重要信息需要身份验证。</p>
    <p>2、有疑问请拨400-302860482或联系在线客服。</p>
</div>


<script src="/js/jquery-1.9.1.min.js"></script>
<script>
    var countdown=60;
    function settime() {
        let phone = $("#check-phone").val();
        console.log(phone);
        if(phone == ""){
            layer.msg("手机号码不能为空");
            return
        }
        else if(!phone.match(/^(((13[0-9]{1})|159|153)+\d{8})$/)){
            layer.msg("手机号码格式不正确");
            return;
        }

        $.ajax({
            type:"post",
            dataType:"json",
            url:"/user/sendSmsValidCode",
            data:{
                phone:phone
            },
            success:function (data) {

            }
        });

        /*if (countdown == 0) {

                $("#btn_cod").removeAttr("disabled");
                $("#btn_cod").val("获取验证码");
                countdown = 60;
                return;
            } else {
                $("#btn_cod").attr("disabled", true);
                $("#btn_cod").val("重新发送(" + countdown + ")");
                countdown--;
            }
            setTimeout(function() {
                settime()
            },1000)*/
    }


    /*var finishBtn = document.getElementsByClassName('init_btn')[0];   //确定按钮
    var phoneValue = document.getElementById('check-phone');			//手机号输入框
    var codValue = document.getElementById('cod-input');   				//验证码输入框
    var codBtn = document.getElementById('btn_cod');					//获取验证码按钮
    var countdown=60;
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
        setTimeout(function() {
            settime(val)
        },1000)
    }

    //验证手机格式
    codBtn.onclick = function () {
        if(phoneValue.value == '' || !(/^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(phoneValue.value))){
            console.log('号码有误');
//                this.removeAttribute('correct');
        }else {
//			    this.setAttribute('class', 'correct');
            settime(this)
        }
    };

    //确定按钮状态改变
    window.onload = function () {
        phoneValue.onkeyup = codValue.onkeyup = function () {
            if(phoneValue.value !== '' && /^(?:13\d|15[89])-?\d{5}(\d{3}|\*{3})$/.test(phoneValue.value) && codValue.value !==''){
                $(finishBtn).addClass('finish_btn');
            }else {
                $(finishBtn).removeClass('finish_btn');
            }
        };
    }*/

</script>
</body>

</html>