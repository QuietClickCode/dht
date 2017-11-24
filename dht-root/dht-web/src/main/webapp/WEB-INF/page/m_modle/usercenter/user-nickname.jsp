<%--
  Created by IntelliJ IDEA.
  User: niconiconi
  Date: 2017/11/24
  Time: 15:39
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>修改昵称</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
    <style>
        .sub_Name{
            line-height: 1rem;
            text-align: center;
            font-size: 0.3rem;
            border: 1px solid rgba(0,0,0,0.1);
            margin-top: 1rem;
            height: 1rem;
            margin-left: 1rem;
            margin-right: 1rem;
            border-radius: 0.2rem;
            background-color: rgba(255,0,0,0.3);
        }

    </style>
</head>

<body class="bge6">
<div class="specialty-title2 borderB">
    <a href="javascript:void(0);" onclick="window.history.back(); return false;" class="icon-return"></a>
    <span>修改昵称</span>
</div>

<div class="modify_name_box">
    <input type="text" class="modify_name_input" name="" id="" value="" />
    <span class="clear_input">x</span>
</div>
<div class="sub_Name">
    <a href="javascript:void(0)">修改昵称</a>
</div>

<script src="/js/jquery-1.9.1.min.js"></script>
<script src="/js/layer/layer.js"></script>
<script>
    $(function(){
        $(".clear_input").click(function() {
            $(".modify_name_input").val("").focus(); // 清空并获得焦点
        });
    })

    $(".sub_Name").click(function() {
        let val = $(".modify_name_input").val();
        if(val == ""){
            layer.msg("昵称不能为空");
            return;
        }
        $.ajax({
            type:"post",
            url:"/user/updateUserName",
            dataType:"json",
            data:{
                name:val
            },
            success:function (data) {
                layer.msg(data.msg);
            }
        });
    });
</script>
</body>

</html>