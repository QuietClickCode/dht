<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>我的地址</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>
<style>
	.label_box {font-size:12px;cursor:pointer;}
.label_box i {
	font-size:12px;
	font-style:normal;
	display:inline-block;
	width:16px;
	height:16px;
	text-align:center;
	line-height:14px;
	color:#fff;
	vertical-align:middle;
	margin:-2px 2px 1px 0px;
	border:#999 1px solid;
}

input[type="checkbox"],input[type="radio"] {display:none;}
input[type="radio"] + i {border-radius:16px;}
input[type="checkbox"] + i {border-radius:16px;}
input[type="checkbox"]:checked + i,input[type="radio"]:checked + i {background:#e93d3d;border:#e93d3d 1px solid;}
input[type="checkbox"]:disabled + i,input[type="radio"]:disabled + i {border-color:#ccc;}
input[type="checkbox"]:checked:disabled + i,input[type="radio"]:checked:disabled + i {background:#ccc;}
</style>

<style>
    #addressLists .add_address_footer{
        position: fixed;
    }

    .my_address_item_bottom a{
        font-size:.25rem;
    }

    #my_address_list{
        margin-bottom: 1rem;
    }

    #my_address_list li{
        position: relative;
    }

    .order_back_btn{
        width: 2.9rem;
    }

    .my_address_L .label_box i{
        margin: 0;
        margin-top: .2rem;
    }

    #my_address_list .setDefaultAddress{
        position: absolute;
        top: 0.2rem;
        right: 0.2rem;
        color: red;
    }

    .defaultAddress{
        position: absolute;
        top: 0.2rem;
        right: 0.2rem;
    }
</style>
<body class="bge6">
    <div class="specialty-title2 borderB">
        <a class="icon-return"  href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
        <span>我的地址</span>
    </div>
    <div class="order_back_box" id="noAddressList">
        <img src="/img/max-address.png"/>
        <p class="p1">你还没有添加地址哦~</p>
        <a href="/userAddress/openAddUserAddress" class="order_back_btn" style="color: #ea2f3e;">添加收货地址</a>
    </div>
    <div id="addressLists" style="display: none;">
        <ul class="my_address_list" id="my_address_list">
        </ul>
        <div class="add_address_footer">
            <a href="/userAddress/openAddUserAddress"><span class="add_address_footer_btn">新增地址</span></a>
        </div>
    </div>
    <script src="/js/jquery-1.9.1.min.js"></script>
	<script>
		var countdown=60;
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
			setTimeout(function() {
				settime(val)
			},1000)
		}

		$(function(){
	        //取得用户收货地址
            queryUserAddress(pageNo,pageSize);
	    });


        function queryUserAddress(pageNo,pageSize){
            if(pageNo==1){
                $('#my_address_list').html('');
            }
            $.ajax({
                type:"post",
                url:'/userAddress/queryUserAddress',
                dataType: "json",
                async:false,
                data:{"pageSize":pageSize,"pageNo":pageNo},
                success:function(data){
                    if(data.rows.length != 0){
                        $("#noAddressList").hide();
                        $("#addressLists").show();
                        for(var row of data.rows){
                            let checked="";
                            let defaultAddress = '<a href="javascript:void(0)" onclick="defaultUserAddress('+row.uaId+')" class="setDefaultAddress">设为默认地址</a>';
                            if(row.uaIsDefault==0){
                                checked='checked="checked"';
                                defaultAddress = '<p href="javascript:void(0)" class="defaultAddress">默认地址</p>';
                            }
                            var html='<li><div class="my_address_item_top"><div class="my_address_L">';
                            html+='<label class="label_box"><input type="radio" name="abc" '+checked+'><i>✓</i></label>';
                            html+='</div><div class="my_address_R"><p class="p1"><span class="span1">'+row.uaName+'</span>&emsp;';
                            html+='<span class="span2">'+row.uaPhone+'</span></p><p class="p2">'+row.uaAllAddress+'</p>'+defaultAddress+'';
                            html+='</div></div><div class="my_address_item_bottom"><a href="/userAddress/openAddUserAddress?uaId='+row.uaId+'" class="mr_3">编辑</a><a class="del_user_address" href="javascript:void(0)" onclick="delAddress('+row.uaId+',this)">删除</a></div></li>';
                            $("#my_address_list").append(html);
                        }
                    }else{
                        if($("#my_address_list").length == 0){
                            $("#noAddressList").show();
                            $("#addressLists").hide();
                        }
                    }
                }
            });
        }

        function delAddress(id,$this) {
            let index = $($this).index(".del_user_address");
            $.ajax({
                type:"post",
                url:"/userAddress/removeUserAddress",
                dataType:"json",
                data:{
                    uaId:id
                },
                success:function (data) {
                    $("#my_address_list li").eq(index).remove();
                    if($("#my_address_list li").length == 0){
                        $("#noAddressList").show();
                        $("#addressLists").hide();
                    }
                }
            });
        }

        function defaultUserAddress(id){
            $.ajax({
                url:"/userAddress/defaultUserAddress",
                type:"post",
                dataType:"json",
                data:{
                    uaId:id
                },
                success:function(data){
                    location.reload();
                }
            });
        }
        
        var scrollTop;
        var pageSize = 10;
        var pageNo = 1;
        $(window).scroll(function () {
            var scrollT = $(window).scrollTop();
            scrollTop = 250;
            if(scrollT > scrollTop){
                ++pageNo;
                queryUserAddress(pageNo,pageSize);
                scrollTop = scrollTop * 2;
            }
        })
	</script>
</body>

</html>
