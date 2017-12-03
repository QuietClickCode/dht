<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/12/3
  Time: 12:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <link rel="stylesheet" type="text/css" href="http://apps.bdimg.com/libs/bootstrap/3.3.4/css/bootstrap.css">
    <style>
        body{
            background-color: #f3f3f4;
        }

        .loginForm{
            text-align: center;
        }

        .middle-box{
            width: 300px;
            max-width: 400px;
            z-index: 100;
            margin: 0 auto;
            text-align: center;
            display: block;
            margin-top: 300px;
        }

        .form-group .single-line {
            border-radius: 1px;
            border: 1px solid #e5e6e7;
            height: 34px;
        }

        .full-width{
            margin-bottom: 15px;
            width: 100%!important;
            background-color: #1ab394;
            border-color: #1ab394;
            color: #FFF;
            border-radius: 3px;
        }
    </style>
</head>
<body>
    <div class="middle-box">
        <form class="loginForm" role="form" action="/">
            <div class="form-group">
                <input type="text" class="form-control syUserAccount single-line" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control single-line" placeholder="密码" required="">
            </div>
            <button type="submit" onclick="event.stopPropagation();test()" class="btn btn-primary block full-width m-b">登 录</button>
        </form>
    </div>
    <script type="text/javascript" src="/js/bootstrap/bootbox.min.js"></script>
    <script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>

    <script type="text/javascript">
        function test() {
            let name = $(".syUserAccount");
            $.ajax({
                url:"/sysUser/querySyUserByAccount",
                type:"post",
                dataType:"json",
                data:{
                    account:name
                },
                success:function (data) {
                    
                }
            });
        }
    </script>
</body>
</html>
