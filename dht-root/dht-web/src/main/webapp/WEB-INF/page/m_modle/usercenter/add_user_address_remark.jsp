<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0,user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>新加地址</title>
	<link rel="stylesheet" href="/css/style.css">
	<link rel="stylesheet" type="text/css" href="/we-ui/weui.css">
	<link rel="stylesheet" type="text/css" href="/js/city/jquery-weui.css">
</head>

<body class="bge6">
    <div class="specialty-title2 borderB" style="font-size: 1.4rem;height: 1.8rem;margin-top: 5px">
		<a class="icon-return"  href="javascript:void(0);" onclick="window.history.back(); return false;"></a>
        <span>新加地址</span>
    </div>

	<form id="userAddressForm" class="weui-cells weui-cells_form">
		<div class="page">
			<div class="bd">
				<input type="hidden" name="uaId" id="uaId"/>
				<input type="hidden" name="version" id="version"/>
				<div class="weui_cells weui_cells_form">
					<div class="weui_cell">
						<div class="weui_cell_hd" ><label class="weui_label" style="width:4rem;">收货人:</label></div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" name="uaName" id="uaName" placeholder="请填写收货人姓名"/>
						</div>
					</div>
					<div class="weui_cell">
						<div class="weui_cell_hd"><label class="weui_label" style="width:4rem;">联系方式:</label></div>
						<div class="weui_cell_bd weui_cell_primary">
							<input class="weui_input" type="text" name="uaPhone" id="uaPhone" placeholder="请填写联系人号码"/>
						</div>
					</div>
					<div class="weui_cell">
						<div class="weui_cell_hd"><label class="weui_label" style="width:4rem;">省市区</label></div>
						<div class="weui_cell_bd weui_cell_primary">
							<input id="city-picker" class="weui_input" placeholder="请选择省市区县" readonly="readonly" data-code="" data-codes="" type="text">
						</div>
					</div>
					<div class="weui_cells_title">详细地址</div>
					<div class="weui_cells weui_cells_form">
						<div class="weui_cell">
							<div class="weui_cell_bd weui_cell_primary">
								<textarea class="weui_textarea" placeholder="请输入街道、楼牌号" rows="5" name="uaAddress" id="uaAddress" ></textarea>
							</div>
						</div>
					</div>
				</div>

				<a href="javascript:;" class="weui_btn weui_btn_warn">保存地址</a>
			</div>
		</div>
	</form>
    <script src="/js/jquery-1.9.1.min.js"></script>
    <script src="/js/city/jquery-weui.js"></script>
    <script src="/js/city/city-picker.js"></script>
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
            $("#city-picker").cityPicker({
                title: "请选择省市区/县",
                onChange: function (picker, values, displayValues) {
                    console.log(values, displayValues);
                }
            });
	    });
	</script>
</body>

</html>
