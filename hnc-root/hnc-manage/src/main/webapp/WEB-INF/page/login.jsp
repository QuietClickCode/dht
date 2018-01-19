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
    <title>后台登录</title>
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
            height: 300px;
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
            margin-bottom: 30px;
            color: #444342;
            padding-top: 140px;
            text-shadow: 0.1em 0.1em 0.2em black;
            letter-spacing: 2px;
            font-size: 27px;
            color: #fff;
            font-weight: 500;
            background: url("/img/hncLogo.png") no-repeat;
            background-size: 100px 100px;
            background-position: top center;
        }
        .container_bd{
            position: relative;
            width: 320px;
            height: 70%;
            margin-left: 40px;
            background-color: rgba(255,255,255,0.6);
        }

        .form{
            margin-top: 50px;
            position: absolute;
        }

        .manage_info{
            width: 200px;
            height: 25px;
            background-color: #fff;
            margin-left: 60px;
            color: #808080;
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
            color: #fff;
            border-radius: 3px;
            font-size: 13px;
            line-height: 30px;
            text-decoration: none;
            background-color: #4e585a;
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
<span style="position: absolute;top: 20px;right: 20px"><a href="http://sm.cqhuanancheng.com/">扫码员登录</a></span>
<div class="container_box">
    <div class="container_hd">
        <p>华南城巴南华府后台登录</p>
    </div>
    <div class="container_bd">
        <div class="form">
            <div class="manage_info">
                <input type="text" name="" class="account" placeholder="用户名">
            </div>
            <div class="manage_info">
                <input type="password" name="" class="password" placeholder="密码">
            </div>
            <span href="javascript:void(0)" class="submit">登录</span>
        </div>
    </div>
</div>
<div class="contaniner_ft">
    <a href="http://www.miitbeian.gov.cn" target="_blank">渝ICP备18000231号</a>
</div>
<script>
    $(".submit").click(function () {
        let phone = $(".account").val();
        let pwd = $(".password").val();
        if(phone == ''){
            layer.msg("请输入帐户名",{time:"1000"});
            return;
        }

        if(pwd == ''){
            layer.msg("请输入密码",{time:"1000"});
            return;
        }

        $.ajax({
            url:'/sysUser/sysUserLogin',
            type:"post",
            dataType: "json",
            data:{
                uaccount:phone,
                upassword:pwd
            },
            success:function (data) {
                if(data.flag){
                    layer.msg("登陆成功",{time:1000});
                    window.location.href = "/index";
                }else{
                    layer.msg("账号或密码错误",{time:1000});
                }
            }
        });
    });
</script>
</body>
</html>