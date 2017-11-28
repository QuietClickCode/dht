<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的资料</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">

</head>
<body class="bge6">
    <div class="specialty-title2 borderB">
        <a class="icon-return"  href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
        <span>我的资料</span>
    </div>
    <div class="my-data-list">
        <a href="javascript:void(0);" class="displayB">
            <span class="name">头像</span>
            <i class="icon-data-right"></i>
            <img class="user-head" src="" alt="..">
        </a>
    </div>
    <div class="my-data-list marginB2">
        <a href="javascript:void(0)" class="displayB">
            <span class="name">会员等级</span>
            <i class="icon-data-right"></i>
            <span class="text1"></span>
        </a>
    </div>
    <div class="my-data-list">
        <a href="/user/UserNickName" class="displayB">
            <span class="name">昵称</span>
            <i class="icon-data-right"></i>
            <span class="text1 nickName"></span>
        </a>
    </div>
    <div class="my-data-list" id="open_sex">
        <a href="javascript:void(0);" class="displayB">
            <span class="name">性别</span>
            <i class="icon-data-right"></i>
            <span class="text1" id="sex_name_box"></span>
        </a>
    </div>
    <div class="my-data-list marginB2">
        <a href="/user/UserPhone" class="displayB">
            <span class="name">我的手机号</span>
            <i class="icon-data-right"></i>
            <span class="text2 user-phone"></span>
        </a>
    </div>
    <div class="my-data-list">
        <a href="/user/userAddress" class="displayB">
            <span class="name">收货地址</span>
            <i class="icon-data-right"></i>
            <span class="text2 user-address"></span>
        </a>
    </div>
    <div class="my-data-list">
        <a href="" class="displayB">
            <span class="name">业务管理</span>
            <i class="icon-data-right"></i>
            <span class="text2">查看我的推荐和我的销售业绩</span>
        </a>
    </div>
    <div class="my-data-list">
        <a href="/user/UserSafety" class="displayB">
            <span class="name">安全管理</span>
            <i class="icon-data-right"></i>
            <span class="text2">密码设置</span>
        </a>
    </div>

    <!--性别弹窗-->
    <div class="sex_box" id="sex_box">
        <div class="sex_box_btn">
            <span class="male_box  sex_box_val" data-sex="0">男</span>
            <span class="female_box  sex_box_val" data-sex="1">女</span>
            <span class="cancel_box close_sex_box">取消</span>
        </div>
    </div>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js"></script>
    <script type="text/javascript">
        $("#open_sex").click(function(){
            $("#sex_box").show()
            var sex1 = $("#open_sex > .text1").val();
        })
        $(".close_sex_box").click(function(){
            $("#sex_box").hide()
        })

        $(".sex_box_val").click(function(){
            let $that = $(this);
            let sex = $that.attr("data-sex");
            $.ajax({
                type:"post",
                url:"/user/updateUserSex",
                dataType:"json",
                data:{
                    sex:sex
                },
                success:function(data){
                    let text = $that.text();
                    $("#sex_name_box").text(text);
                    $("#sex_box").hide();
                }
            });
        })

        $(function(){
            $.ajax({
                url:"/user/getUserInfo",
                type:"post",
                dataType:"json",
                success:function(data){
                    let userAddress = data.userAddress;
                    let userHeadSrc = data.UserHeaderSrc;
                    let name = data.nickName;
                    let sex = data.sex;
                    let userPhone = data.userPhone;
                    $(".nickName").text(name);
                    if(sex == 0)
                        $("#sex_name_box").text("男");
                    else if(sex == 1)
                        $("#sex_name_box").text("女");

                    if(userPhone == "")
                        $(".user-phone").text("未设置");
                    else
                        $(".user-phone").text(userPhone);
                    if(userAddress == "")
                        $(".user-address").text("填写收货地址");
                    else
                        $(".user-address").text(userAddress);
                    $(".user-head").attr("src",userHeadSrc);
                }
            });
        });
    </script>
</body>
</html>