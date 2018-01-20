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
    <style>
        #init_btn{
            background-color: #e83939;
            color: #fff;
            width: 100%;
            display: block;
            text-align: center;
            height: 0.8rem;
            line-height: 0.8rem;
        }
    </style>
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
                <input type="number" class="input_text user-phone" id="check-phone" placeholder="请输入手机号" />
            </td>
        </tr>
        <tr>
            <td class="td1">验证码</td>
            <td class="td2">
                <input type="number" class="input_text" name="" id="cod-input" value="" placeholder="请输入验证码" />
            </td>
            <td>
                <input type="button" class="btn_cod" id="btn_cod" onclick="getValidCode(this)" value="获取验证码" />
            </td>
        </tr>
        <tr>
            <td class="td4" colspan="3">
                <span class="init_btn" id="init_btn">确定</span>
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
<script src="/js/layer_mobile/layer.js"></script>
<script>

    var countdown=60;
    var id = "";
    function getValidCode($this) {
        if(verifyForm() == false)
            return;
        settime($this);
        phone = $(".user-phone").val();
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
        if($(".user-phone").val() == ""){
            layer.open({
                content: '手机号不能为空'
                ,skin: 'msg'
                ,time: 1
            });
            return false;
        }

//        if(!$(".user-phone").val().match(/^(((13[0-9]{1})|159|153)+\d{8})$/)){
//            layer.open({
//                content: '手机格式不正确'
//                ,skin: 'msg'
//                ,time: 1
//            });
//            return false;
//        }
        return true;
    }

    $("#init_btn").click(function(){
        let code = $("#cod-input").val();
        if(code == "") {
            layer.open({
                content: '验证码不能为空'
                ,skin: 'msg'
                ,time: 1
            });
            return;
        }

        $.ajax({
            url:"/user/bindPhone",
            type:"post",
            dataType:"json",
            data:{
                phone:phone,
                code:code
            },
            success:function(data){
                if(data.data == true){
                    layer.open({
                        content: '绑定手机成功'
                        ,skin: 'msg'
                        ,time: 1
                    });
                    setTimeout(function () {
                        window.history.back();
                    },1000);
                }else{
                    layer.open({
                        content: '验证码错误'
                        ,skin: 'msg'
                        ,time: 1
                    });
                }
            }
        });
    });

</script>
</body>

</html>