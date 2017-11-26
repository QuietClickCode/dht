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
                        <input type="number" class="input_text" name="" id="" value="" placeholder="138***888" />
                    </td>
                    <td class="td_line">
                        <input type="button" class="btn_cod" id="btn_cod" value="获取验证码" onclick="settime(this)" />
                    </td>
                </tr>
                <tr>
                    <td class="td3 td_line" colspan="2">
                        <input type="number" class="input_text" name="" id="" value="" placeholder="请输入验证码" />
                    </td>
                </tr>
            </table>
        </div>
        <div class="replace_container_item">
            <table class="bind_phone_box" border="0" cellspacing="0" cellpadding="0">
                <tr>
                    <td class="td2 td_line">
                        <input type="number" class="input_text" name="" id="" value="" placeholder="请输入新手机号码" />
                    </td>
                    <td class="td_line">
                        <input type="button" class="btn_cod" id="btn_cod" value="获取验证码" onclick="settime(this)" />
                    </td>
                </tr>
                <tr>
                    <td class="td3 td_line" colspan="2">
                        <input type="number" class="input_text" name="" id="" value="" placeholder="请输入验证码" />
                    </td>
                </tr>
                <tr>
                    <td class="td4" colspan="3">
                        <span class="finish_btn">确定</span>
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
<script>
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


    $(function(){
        $("#replace_nav > li").click(function(){
            var $this = $(this),
                index = $this.index("#replace_nav > li");
            $this.addClass("active").siblings("li").removeClass("active");
            $(".replace_container > .replace_container_item").eq(index).addClass("active").siblings(".replace_container_item").removeClass("active");
        });
    });
</script>
</body>

</html>