<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新加地址</title>
    <script src="/js/Adaptive.js"></script>
    <link rel="stylesheet" href="/css/style.css">
</head>

<body class="bge6">
    <div class="specialty-title2 borderB">
		<a class="icon-return"  href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
        <span>新加地址</span>
    </div>

	<ul class="add_adress_box">
		<li>
			<div class="add_adress_box_L">收货人</div>
			<div class="add_adress_box_R">
				<input type="text" class="input_text" name="" id="" value="请填写收货人姓名" />
			</div>
		</li>
		<li>
			<div class="add_adress_box_L">联系方式</div>
			<div class="add_adress_box_R">
				<input type="text" class="input_text" name="" id="" value="请填写联系人号码" />
			</div>
		</li>
		<li>
			<div class="add_adress_box_L">省</div>
			<div class="add_adress_box_R">
				<select name="" class="select_box">
					<option value="">请选择省</option>
				</select>
			</div>
		</li>
		<li>
			<div class="add_adress_box_L">市</div>
			<div class="add_adress_box_R">
				<select name="" class="select_box">
					<option value="">请选择市</option>
				</select>
			</div>
		</li>
		<li>
			<div class="add_adress_box_L">区/县</div>
			<div class="add_adress_box_R">
				<select name="" class="select_box">
					<option value="">请选择区/县</option>
				</select>
			</div>
		</li>
		<li>
			<div class="add_adress_box_L">详细地址</div>
			<div class="add_adress_box_R">
				<input type="text" class="input_text" name="" id="" value="请输入街道、楼牌号" />
			</div>
		</li>
		<li>
			<div class="add_adress_box_L">邮编</div>
			<div class="add_adress_box_R">
				<input type="text" class="input_text" name="" id="" value="请填写邮政编码" />
			</div>
		</li>
	</ul>

	<div class="add_address_footer">
		<span class="add_address_footer_btn">保存地址</span>
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
	        $("#replace_nav > li").click(function(){
	            var $this = $(this),
	                index = $this.index("#replace_nav > li");
	            $this.addClass("active").siblings("li").removeClass("active");
	            $(".replace_container > .replace_container_item").eq(index).addClass("active").siblings(".replace_container_item").removeClass("active");
	        });
	    });
	</script>
</body>

</html>
