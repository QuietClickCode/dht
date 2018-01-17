<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
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

        .update_password{
            margin-top: 5px;
            margin-bottom: 5px;
            float: right;
        }

        .update_password a{
            font-size: 10px;
        }
    </style>
</head>
<body>
    <div class="middle-box" id="login">
        <form class="loginForm" role="form">
            <div class="form-group">
                <input type="text" class="form-control syUserAccount single-line" placeholder="用户名" required="">
            </div>
            <div class="form-group" style="margin: 0px;">
                <input type="password" class="form-control single-line sysUserPwd" placeholder="密码" required="">
            </div>
            <div class="update_password">
                <a class="edit_sys_pwd" href="javascript:void(0)">修改密码?</a>
            </div>
            <button type="button" onclick="test()" class="btn btn-primary full-width">登 录</button>
        </form>
    </div>

    <div class="middle-box" id="edit_password" style="display: none;">
        <form class="loginForm" role="form">
            <div class="form-group">
                <input type="text" class="form-control syUserAccount single-line" id="user_account" placeholder="用户名" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control single-line sysUserPwd" id="user_pwd" placeholder="密码" required="">
            </div>
            <div class="form-group">
                <input type="password" class="form-control single-line sysUserPwd" id="new_pwd" placeholder="请输入新密码" required="">
            </div>
            <button type="button"  class="btn btn-primary full-width reset_pwd">重置密码</button>
        </form>
    </div>
    <%--<script type="text/javascript" src="/js/validate/bootstrapValidator.min.js"></script>--%>
    <script type="text/javascript" charset="utf-8" src="/js/jquery.min.js"> </script>
    <script type="text/javascript" src="/js/layer/layer.js"></script>
    <script type="text/javascript">

        var isSaves=false;
        function test() {
            if(!isSaves){
                isSaves=true;
                let name = $(".syUserAccount").val();
                let pwd = $(".sysUserPwd").val();
                $.ajax({
                    url:"/sysUser/querySyUserByAccount",
                    type:"post",
                    dataType:"json",
                    data:{
                        account:name,
                        sysUserPwd:pwd
                    },
                    success:function (data) {
                        isSaves=false;
                        if(data.status == 0){
                            layer.msg("登陆成功",{time:1000});
                            window.location.href = "/home";
                        }else{
                            layer.msg(data.msg,{time:1000});
                        }
                    }
                });
            }
        }

        $(".edit_sys_pwd").click(function () {
            $("#login").hide();
            $("#edit_password").show();

        });

        $(".reset_pwd").click(function () {
            let pwd = $("#user_pwd").val();
            let new_pwd = $("#new_pwd").val();
            let account = $("#user_account").val();

            $.ajax({
                url:"/sysUser/editSysUserPassword",
                type:"post",
                dataType:"json",
                data:{
                    account:account,
                    sysUserPwd:pwd,
                    newPwd:new_pwd
                },
                success:function (data) {
                    if(data.msg == true){
                        $("#login").show();
                        $("#edit_password").hide();
                    }else{
                        layer.msg(data.msg,{time:1000});
                    }
                }
            });

        });
    </script>
</body>
</html>
