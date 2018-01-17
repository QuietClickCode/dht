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
    <meta charset="utf-8"/>
    <script src="http://cdn.static.runoob.com/libs/jquery/2.1.1/jquery.min.js"></script>
    <script type="text/javascript" src="/js/layer/layer.js"></script>
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
            margin-top: -250px;
            margin-left: -200px;
        }

        .container_hd{
            position: relative;
            text-align: center;
            width: 100%;
            color: #444342;
            letter-spacing: 2px;
            font-size: 30px;
        }

        .container_bd{
            position: relative;
            width: 320px;
            height: 70%;
            margin-left: 40px;
            background-color: rgba(255,255,255,0.6);
        }

        .form{
            /*margin-top: 30px;*/
            position: absolute;
            width: 100%;
            height: 100%;
        }

        .manage_info{
            width: 200px;
            height: 25px;
            background-color: #fff;
            margin-left: 60px;
            color: #808080;
            border-radius: 3px;
            margin-bottom: 20px;
            position: absolute;
            bottom: 80px;
        }

        .manage_info input{
            /*border:none;*/
            border:0;
            font-size: 12px;
            outline:none;
            color: #808080;
            height: 20px;
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
            position: absolute;
            bottom: 30px;
            text-align: center;
        }

        .info{
            position: absolute;
            text-align: center;
            display: block;
            width: 100%;
            top: 35px;
        }

        .info p{
            text-align: center;

        }

        .danger{
            display: none;
            color: #a94442;
            background: url(/img/danger.png) no-repeat;
            background-size: 20px 20px;
            background-position: 95px;
            top: 0px
        }

        .success{
            color: #3c563d;
            background: url(/img/success.png) no-repeat;
            background-size: 24px 24px;
            background-position: 95px;
            top: 0px
        }
    </style>
</head>
<body>
<div class="container_box">
    <div class="container_hd">
        <p>入场信息验证</p>
    </div>
    <div class="container_bd">
        <div class="form">
            <div id="danger" class="info danger" style="display: none;">
                <p>验证失败</p>
            </div>

            <div id="success" class="info success" style="display: none;">
                <p>验证成功</p>
            </div>
            <div class="validateInfo info" style="display: none">
                <p id="msg">1111</p>
            </div>
            <div class="manage_info">
                <input type="text" name="" id="validateCode" placeholder="验证码">
            </div>

            <a href="javascript:void(0)" onclick="validateCode();" class="submit">验证</a>
        </div>
    </div>
</div>


<script>
    function validateCode() {
        var validateCode = $('#validateCode').val();
        if(validateCode==''){
            layer.msg('验证码不能为空');
            return;
        }
        $.ajax({
            url:'/checkUser/validateCheckUser',
            type:"post",
            dataType: "json",
            data:{
                validataCode:validateCode
            },
            success:function (data) {
                var status = data.status;
                if(status==3){
                    window.location.href='/login.jsp';
                }else if(status==-1){
                    $('#success').hide();
                    $('#danger').show();
                    $('#msg').html(data.msg);
                    $('#msg').show();
                    $('#msg').parent().show();
                }else{
                    $('#success').show();
                    $('#danger').hide();
                    $('#msg').hide();
                    $('#validateCode').val('');
                }
            }
        });
    }




</script>
</body>
</html>