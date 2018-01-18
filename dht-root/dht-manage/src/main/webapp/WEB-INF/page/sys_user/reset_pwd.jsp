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
            background: url(/img/dht_bg.jpg) no-repeat;
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
            margin-top: -300px;
            margin-left: -200px;
        }

        .container_hd{
            position: relative;
            text-align: right;
            width: 100%;
            padding-top: 100px;
            margin-bottom: 30px;
            padding-right: 40px;
            color: #00923f;
            letter-spacing: 2px;
            font-size: 27px;
            line-height: 25px;
            background: url(/img/dht_logo.png)no-repeat;
            background-size: 100px 110px;
            background-position: bottom left 40px;
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
        <p>大汇堂修改密码</p>
    </div>
    <div class="container_bd">
        <div class="form">
            <div class="manage_info">
                <input type="text" name="" class="account" placeholder="用户名">
            </div>
            <div class="manage_info">
                <input type="password" name="" class="password" placeholder="密码">
            </div>
            <div class="manage_info">
                <input type="password" name="" class="password" placeholder="请输入新密码">
            </div>
            <span href="javascript:void(0)" class="submit">登录</span>
        </div>
    </div>
</div>

</body>
</html>