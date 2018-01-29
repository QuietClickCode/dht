<%--
&lt;%&ndash;
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/12/1
  Time: 14:31
  To change this template use File | Settings | File Templates.
&ndash;%&gt;
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
<style>
    .wrap{
        width: 100%;
        height: 100%;
        padding-top: 2.4rem;
    }
    .wrap_title{
        width: 100%;
        height: 1rem;
        text-align: center;
        line-height: 1rem;
        font-size: .3rem;
        color: #333;
    }
    .wrap_tip_box{
        width: 6.06rem;
        height: .6rem;
        margin: auto;
        line-height: .6rem;
        font-size: .2rem;
        color: #808080;
    }
    .inputBoxContainer{
        width: 6.06rem;
        height: 1rem;
        margin: 0 auto;
        position: relative;
        box-sizing: border-box;
        background: #fff;
    }
    .inputBoxContainer .bogusInput{
        width: 100%;
        height: 100%;
        border: #0e75dc 1px solid;
        overflow: hidden;
        position: absolute;
        z-index: 0;
    }
    .inputBoxContainer .realInput{
        width: 100%;
        height: 100%;
        position: absolute;
        top:0;
        left: 0;
        z-index: 1;
        filter:alpha(opacity=0);
        -moz-opacity:0;
        opacity:0;
    }
    .inputBoxContainer .bogusInput input{
        padding: 0;
        width: 1rem;
        height: 100%;
        float:left;
        background: #ffffff;
        text-align: center;
        font-size: .8rem;
        border: none;
        border-right: #e6e6e6 1px solid;
        box-sizing: border-box;
    }
    .inputBoxContainer .bogusInput input:last-child{
        border: none;
    }
    .wrap_btn_box{
        width: 100%;
        margin-top: .2rem;
        padding: 0 .2rem;
    }
    .confirmButton{
        width: 100%;
        height: .8rem;
        text-align: center;
        line-height: .8rem;
        background: #da3836;
        color: #fff;
        border: #da3836 1px solid;
        border-radius: 4px;
        display: block;
        font-size: .3rem;
        margin-bottom: .2rem;
    }
    .showValue{
        width: 2.4rem;
        height: .22rem;
        line-height: .22rem;
        font-size: .16rem;
        text-align: center;
        margin: 0 auto;
    }
</style>
<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>修改支付密码</span>
</div>
<div class="wrap">
    <div class="wrap_title">请输入原有的支付密码</div>
    <div class="inputBoxContainer" data-id="0" id="inputBoxContainer">
        <input type="text" class="realInput"/>
        <div class="bogusInput">
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
        </div>
    </div>
    <div class="wrap_tip_box">注:此密码仅用于大汇堂余额支付使用</div>
    <div class="wrap_btn_box">
        <button id="confirmButton" class="confirmButton">完成</button>
    </div>
</div>


<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer_mobile/layer.js"></script>
<script>
    (function(){
        var container = document.getElementById("inputBoxContainer");
        boxInput = {
            maxLength:"",
            realInput:"",
            bogusInput:"",
            bogusInputArr:"",
            callback:"",
            init:function(fun){
                var that = this;
                this.callback = fun;
                that.realInput = container.children[0];
                that.bogusInput = container.children[1];
                that.bogusInputArr = that.bogusInput.children;
                that.maxLength = that.bogusInputArr[0].getAttribute("maxlength");
                that.realInput.oninput = function(){
                    that.setValue();
                }
                that.realInput.onpropertychange = function(){
                    that.setValue();
                }
            },
            setValue:function(){
                this.realInput.value = this.realInput.value.replace(/\D/g,"");
                var real_str = this.realInput.value;
                for(var i = 0 ; i < this.maxLength ; i++){
                    this.bogusInputArr[i].value = real_str[i]?real_str[i]:"";
                }
                if(real_str.length >= this.maxLength){
                    this.realInput.value = real_str.substring(0,6);
                    this.callback();
                }
            },
            getBoxInputValue:function(){
                var realValue = "";
                for(var i in this.bogusInputArr){
                    if(!this.bogusInputArr[i].value){
                        break;
                    }
                    realValue += this.bogusInputArr[i].value;
                }
                return realValue;
            }
        }
    })()
    boxInput.init(function(){
        getValue();
    });
    function getValue(){
        return boxInput.getBoxInputValue();
    }
    var oldPayPwd;
    var newPayPwd;
    $("#confirmButton").click(function () {
        let id = $(".inputBoxContainer").attr("data-id");
        if(id == 0){
            oldPayPwd = getValue();
            $(".bogusInput input").val("");
            $(".realInput").val("");
            $(".wrap_title").text("请输入新的支付密码");
            $(".inputBoxContainer").attr("data-id",1);
        }else if(id == 1){
            newPayPwd = getValue();
            $.ajax({
                url:"/user/changePayPwd",
                type:"post",
                dataType:"json",
                data:{
                    oldPayPwd:oldPayPwd,
                    payPwd:newPayPwd
                },
                success:function (data) {
                    if(data.status == 0){
                        layer.open({
                            content: '修改支付密码成功'
                            ,skin: 'msg'
                            ,time: 1
                        });
                        setTimeout(function(){
                         history.back(-1);
                         },1000);
                    }else if(data.status == -1){
                        layer.open({
                            content: data.msg
                            ,skin: 'msg'
                            ,time: 1
                        });
                        $(".bogusInput input").val("");
                        $(".realInput").val("");
                        $(".wrap_title").text("请输入原有的支付密码");
                        $(".inputBoxContainer").attr("data-id",0);
                    }
                }
            });
        }
    });
</script>
</body>

</html>--%>

<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/12/1
  Time: 14:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>设置支付密码</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<style>
    input,button,select,textarea{outline:none;}
    .wrap{
        width: 100%;
        height: 100%;
        padding-top: 0.7rem;
    }
    .wrap_title{
        width: 100%;
        height: 1rem;
        text-align: center;
        line-height: 1rem;
        font-size: .3rem;
        color: #333;
    }
    .wrap_tip_box{
        width: 6.06rem;
        height: .6rem;
        margin: auto;
        line-height: .6rem;
        font-size: .2rem;
        color: #808080;
    }
    .inputBoxContainer{
        width: 6.06rem;
        height: 1rem;
        margin: 0 auto;
        position: relative;
        box-sizing: border-box;
        background: #fff;
    }
    .inputBoxContainer .bogusInput{
        width: 100%;
        height: 100%;
        border: rgba(0,0,0,0.1) 1px solid;
        overflow: hidden;
        position: absolute;
        z-index: 0;
    }
    .inputBoxContainer .realInput{
        width: 100%;
        height: 100%;
        position: absolute;
        top:0;
        left: 0;
        z-index: 1;
        filter:alpha(opacity=0);
        -moz-opacity:0;
        opacity:0;
    }
    .inputBoxContainer .bogusInput input{
        padding: 0;
        width: 1rem;
        height: 100%;
        float:left;
        background: #ffffff;
        text-align: center;
        font-size: .8rem;
        border: none;
        border-right: #e6e6e6 1px solid;
        box-sizing: border-box;
    }
    .inputBoxContainer .bogusInput input:last-child{
        border: none;
    }
    .wrap_btn_box{
        width: 100%;
        margin-top: .2rem;
        padding: 0 .2rem;
    }
    .confirmButton{
        width: 6.5rem;
        margin: 0 auto;
        height: 1rem;
        text-align: center;
        line-height: 1rem;
        background: #108ee9;
        color: #fff;
        border: #108ee9 1px solid;
        border-radius: 4px;
        display: block;
        font-size: .3rem;
        margin-bottom: .2rem;
        outline：none;
    }

    .confirmButton:hover,.confirmButton:link,.confirmButton:visited,.confirmButton:active{
        border: #108ee9 1px solid;
    }

    .showValue{
        width: 2.4rem;
        height: .22rem;
        line-height: .22rem;
        font-size: .16rem;
        text-align: center;
        margin: 0 auto;
    }

    .wrap_tips{
        width: 100%;
        height:1.5rem;
        background-color: #fff;
        text-align: center;
        line-height: 1.5rem;
        font-size: 0.35rem;
        color: #373737;
        font-family: sans-serif;
    }

    .tips{
        font-family: sans-serif;
    }

    .tips span{
        font-weight: 550;
        color: #323232;
        font-family: sans-serif;
    }

    .bge6{
        background-color: #f5f4f9;
    }
</style>
<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>设置支付密码</span>
</div>

<div class="wrap_tips">
    <span class="tips">请输入当前支付密码</span>
</div>
<div class="wrap">
    <div class="inputBoxContainer" id="inputBoxContainer">
        <input type="text" class="realInput"/>
        <div class="bogusInput">
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
            <input type="password" maxlength="6" disabled/>
        </div>
    </div>
    <div class="wrap_tip_box">注:此密码仅用于大汇堂余额支付使用</div>
    <div class="wrap_btn_box" style="display: none;">
        <button id="confirmButton" class="confirmButton">完成</button>
    </div>
</div>


<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer_mobile/layer.js"></script>
<script>
    (function(){
        var container = document.getElementById("inputBoxContainer");
        boxInput = {
            maxLength:"",
            realInput:"",
            bogusInput:"",
            bogusInputArr:"",
            callback:"",
            init:function(fun){
                var that = this;
                this.callback = fun;
                that.realInput = container.children[0];
                that.bogusInput = container.children[1];
                that.bogusInputArr = that.bogusInput.children;
                that.maxLength = that.bogusInputArr[0].getAttribute("maxlength");
                that.realInput.oninput = function(){
                    that.setValue();
                }
                that.realInput.onpropertychange = function(){
                    that.setValue();
                }
            },
            setValue:function(){
                this.realInput.value = this.realInput.value.replace(/\D/g,"");
                var real_str = this.realInput.value;
                for(var i = 0 ; i < this.maxLength ; i++){
                    this.bogusInputArr[i].value = real_str[i]?real_str[i]:"";
                }
                if(real_str.length >= this.maxLength){
                    this.realInput.value = real_str.substring(0,6);
                    this.callback();
                }

                if(oldPwd == "" && this.realInput.value.length == 6){
                    oldPwd = this.realInput.value;
                    $(".tips").text("请输入新支付密码");
                    $(".bogusInput input").val("");
                    $(".realInput").val("");
                    console.log(oldPwd);
                }else if(oldPwd != "" && this.realInput.value.length == 6){
                    if(payPwd == "" && this.realInput.value.length == 6){
                        $(".tips").text("请确认支付密码");
                        payPwd = this.realInput.value;
                        $(".bogusInput input").val("");
                        $(".realInput").val("");
                    }

                    if(payPwd != "" && this.realInput.value.length == 6){
                        verifyPwd = this.realInput.value;
                        $(".wrap_btn_box").show();
                    }
                }

                if(payPwd != "" && payPwd != "" && this.realInput.value.length != 6){
                    $(".wrap_btn_box").hide();
                }
            },
            getBoxInputValue:function(){
                var realValue = "";
                for(var i in this.bogusInputArr){
                    if(!this.bogusInputArr[i].value){
                        break;
                    }
                    realValue += this.bogusInputArr[i].value;
                }
                return realValue;
            }
        }
    })()
    boxInput.init(function(){
        getValue();
    });

    function getValue(){
        return boxInput.getBoxInputValue();
    }

    var oldPwd = "";
    var payPwd = "";
    var verifyPwd = "";
    $("#confirmButton").click(function () {
        if(payPwd != verifyPwd){
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
                oldPayPwd:oldPwd,
                payPwd:payPwd
            },
            success:function (data) {
                if(data.status == 0){
                    layer.open({
                        content: '修改支付密码成功'
                        ,skin: 'msg'
                        ,time: 1
                    });
                    setTimeout(function(){
                        history.back(-1);
                    },1000);
                }else if(data.status == -1){
                    layer.open({
                        content: data.msg
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
