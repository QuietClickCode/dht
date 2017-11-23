<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>充值</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
	<style>
		html,body{
			width: 100%;
			height: 100%;
		}
	</style>
</head>
<body class="bge6">
    <div class="specialty-title2 borderB">
        <a href="" class="icon-return" href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
        <span>充值</span>
    </div>
	<div class="recharge_container">
		<ul class="recharge_container_list clearfix" id="rechargeLists">
		</ul>
		<div class="recharge_btn_box">
			<a href="javascript:void(0);" class="recharge_btn" onclick="userRechage()">确认</a>
		</div>
	</div>
    <script type="text/javascript" src="/js/jquery-1.9.1.min.js" ></script>
	<script>
		var curSelectId="";
		$(function(){

			loadRechargeLists();
		})
		/**
		 * 取得充值卡例表
		 */
		function loadRechargeLists(){
			$("#rechargeLists").html("");
			$.ajax({
				type:"post",
				url:"/recharge/queryRechargeLists",
				dataType: "json",
				success:function(data){
					if(data.status==0){
						let rows=data.data;
						for(var row of rows){
							if(row.rid){
								let html = '<li><div class="recharge_container_item" attr="'+row.rid+'"><span class="span_tip2">'+row.rname+'</span>';
								html+='<span class="span_tip1"></span><span class="span_select"></span><p class="p1">'+row.rprice+'<span>元</span></p>'
								html+='<p class="p2"><span class="span1">'+row.rdiscount+'折</span>';
								let isCash='不返现';
								if(row.rcashback==1){
									isCash='返现';
								}
								html+='<span class="span2">'+isCash+'</span></p></div></li>'
								$("#rechargeLists").append(html);
							}
						}
                        $('.recharge_container_item').click(function(){
                            $(".recharge_container_item").removeClass("active")
                            $(this).addClass("active")
                            curSelectId=$(this).attr("attr");
                        });
					}
				}
			});
		}
		var isSubmit=false;
		function userRechage(){
			console.log(curSelectId)
			if(!isSubmit){
				isSubmit=true;
				$.ajax({
					type:"post",
					url:"/recharge/userRecharges",
					dataType: "json",
					data:{"rid":curSelectId},
					success:function(data){
						isSubmit=false;
						if(data.status==0){
							let orderNo=data.data.orderNo;
							let price=data.data.price;
							window.location.href="/wxPay/payInfo?orderNo="+orderNo+"&price="+price;
						}
					}
				});
			}
		}
	</script>
</body>
</html>
