<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2018/1/12
  Time: 19:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>修改密码</title>
    <%@include file="/common/common_bs_head_css.jsp"%>
    <script src="/js/jquery.min.js"></script>
    <style type="text/css">
        html{
            width: 100%;
            height: 100%;
            background: url(/img/bgimage.jpg) no-repeat;
            background-position: top;
            background-size: 100% 100%;
            overflow: hidden;
        }

        .container_box{
            width: 400px;
            height: 380px;
            position: absolute;
            top: 50%;
            left: 50%;
            margin-top: -350px;
            margin-left: -200px;
        }

        .container_hd{
            position: relative;
            text-align: center;
            width: 100%;
            padding-top: 100px;
            margin-bottom: 30px;
            color: #ffffff;
            text-shadow: 0.1em 0.1em 0.2em black;
            letter-spacing: 2px;
            font-size: 27px;
            padding-top: 140px;
            background: url("/img/hncLogo.png") no-repeat;
            background-size: 100px 100px;
            background-position: top;
            line-height: 25px;
        }

        .container_bd{
            position: relative;
            width: 320px;
            height: 70%;
            margin-left: 40px;
            background-color: rgba(255,255,255,0.3);
        }

        .form{
            margin-top: 50px;
            position: absolute;
        }

        .manage_info{
            width: 200px;
            height: 25px;
            color: #000000;
            background-color: #fff;
            margin-left: 60px;
            border-radius: 3px;
            margin-bottom: 20px;
        }

        .manage_info input{
            /*border:none;*/
            border:0;
            font-size: 12px;
            outline:none;
            color: #808080;
            margin-top: 2.5px;
            height: 20px;
            background-color: #fff;
            margin-left: 10px;
        }

        .submit{
            width: 200px;
            height: 30px;
            display: block;
            color: #000;
            border-radius: 3px;
            font-size: 13px;
            line-height: 30px;
            text-decoration: none;
            background-color: #e7e1d5;
            margin-left: 60px;
            margin-bottom: 20px;
            text-align: center;
            cursor: pointer;
        }

        .contaniner_ft{
            position: absolute;
            z-index: 9999;
            bottom: 0;
            right: 0;
        }

        .contaniner_ft a{
            color: #fff;
        }
    </style>
</head>
<body>
<script type="text/javascript" src="/js/layer/layer.js"></script>
<div class="container_box">
    <div class="container_hd">
        <p>修改密码</p>
    </div>
    <div class="container_bd" style="height: 80%">
        <div class="form">
            <div class="manage_info">
                <input type="text" name="" class="account" id="account" placeholder="用户名" style="width: 190px">
            </div>
            <div class="manage_info">
                <input type="password" name="" class="password" id="oldpwd" placeholder="密码" style="width: 190px">
            </div>
            <div class="manage_info">
                <input type="password" name="" class="password" id="newpwd" placeholder="请输入新密码" style="width: 190px">
            </div>
            <div class="manage_info">
                <input type="password" name="" class="password" id="renewpwd" placeholder="请确认新密码" style="width: 190px">
            </div>
            <span onclick="resetpassword();" class="submit">修改</span>
        </div>
    </div>
</div>
<script>
    function resetpassword() {
        var account = $('#account').val();
        var oldpwd = $('#oldpwd').val();
        var newpwd = $('#newpwd').val();
        var renewpwd = $('#renewpwd').val();

        if(account==''){
            layer.msg('账户不能为空');
            return;
        }
        if(oldpwd==''){
            layer.msg('密码不能为空');
            return;
        }
        if(newpwd==''){
            layer.msg('新密码不能为空');
            return;
        }
        if(renewpwd==''){
            layer.msg('新密码不能为空');
            return;
        }
        if(renewpwd!=newpwd){
            layer.msg('两次密码不一致');
            return;
        }
        $.ajax({
            type:"post",
            url:'/sysUser/resetUserPassword',
            dataType: "json",
            async:false,
            data:{account:account,oldPassword:oldpwd,newPassword:newpwd},
            success:function(data){
                var status = data.status;
                layer.msg(data.msg);
                if(status==0){
                    setTimeout(function () {
                        window.location.href='/login';
                    },1000);
                }

            }
        });

    }
</script>
</body>
</html>