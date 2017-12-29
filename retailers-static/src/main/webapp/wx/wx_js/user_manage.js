
/*黑名单和关注名单切换*/
$(".tab_list li").click(function(){
	$(this).css("border-bottom","3px solid #44b549");
	$(this).siblings().css("border","none");
	let index = $(this).index();
	if(index == 0){
		$(".inner_container_box").show();
		$(".user_global").show();
		$(".cell_layout").hide();
	}else if(index == 1){
		$(".inner_container_box").hide();
		$(".user_global").hide();
		$(".cell_layout").show();
	}
});

/*个人信息展示框*/
$(".header_img").mouseover(function(){
	let x = $(this).offset();
	let left = x.left;
	let top = x.top;
	let width = $(this).width();
 	$(".popover_ui").css({
 		left:left+width+"px",
 		top:top+"px"
 	});
 	$(".popover_ui").show();
});

$(".header_img").mouseout(function(){
	$(".popover_ui").hide();
});


/*添加标签弹出框*/
$(".addLabel").click(function(){
	let label = $(this).offset();
	let height = $(this).height();
	let width = $(this).width();
	let x = label.left - width + 11;
	let y = label.top + height + 23;
	$(".add_label").css({
		top:y + "px",
		left:x + "px"
	});

	$(".add_label").show();
});

/*关闭添加标签弹出框*/
$(".close_label").click(function(){
	$(".popover_label").hide();
});

$(".close_remark").click(function(){
	$(".update_remark").hide();
});

/*修改备注弹出框*/
$(".user_remark").click(function(){
	let index = $(this).index(".user_remark");
	let text = $(".remark_name_info").eq(index).find("a").text();
	$(".input_remark").val(text);
	$(".input_remark").attr("data-index",index);
	$(".input_remark").attr("data-name",text);
	let label = $(this).offset();
	let height = $(this).height();
	let width = $(this).width();
	let x = label.left - width - 48;
	let y = label.top + height + 24;
	$(".update_remark").css({
		top:y + "px",
		left:x + "px"
	});
	$(".update_remark").show();
});


/*提交修改备注*/
$(".sub_remark").click(function(){
	let index = $(".input_remark").attr("data-index");
	let name = $(".input_remark").attr("data-name");
	let val = $(".input_remark").val();
	let remark = $(".remark_name_info").eq(index).find("span").text();
	if(remark == "")
		$(".remark_name_info").eq(index).find("span").text("("+name+")");
	if(val != ""){
		alert(index);
		$(".remark_name_info").eq(index).find("a").text(val);
		
	}
	$(".update_remark").hide();
});


$(".popover_ui").mouseover(function(){
	$(this).show();
});

$(".popover_ui").mouseout(function(){
	let display = $(".popover_user_label").css("display");
	if(display == "none"){
		$(this).hide();
	}
});

$(".close_user_label").click(function(){
	$(".popover_user_label").hide();
});

/*用户标签展示弹出框*/
$(".user_label").click(function(){
	let index = $(this).index(".user_info .user_label");
	let label = $(this).offset();
	let left = label.left - 223;
	let top = label.top + 20;
	$(".popover_user_label").css({
		left:left+"px",
		top:top+"px"
	});
	$(".popover_user_label").attr("data-index",index);
	$(".popover_user_label").show();
});


$(".create_label").click(function(){
	$(this).hide();
	$(".create_label_flag").show();
});

/*添加新标签*/
$(".create_new_label").click(function(){
	let val = $(".input_label_name").val();
	if(val != null){
		let li = $("<li>"+val+"</li>");
		let span = $("<span>(0)</span>");
		li.append(span);
		$(".group_list").append(li);
		$(".input_label_name").val("");
	}
});

$(".closed").click(function(){
	$(".create_label_flag").hide();
	$(".create_label").show();
});


$(".sub_create_label").click(function(){
	let val = $(".create_label_1").val();
	if(val != ""){
		let li = $("<li>"+val+"</li>");
		let span = $("<span>(0)</span>");
		li.append(span);
		$(".group_list").append(li);
		$(".create_label_1").val("");
		$(".add_label").hide();
	}
});


/*全选和全不选*/
$(".check_all").change(function(){
	let flag = $(this).is(':checked');
	if(flag){
		$(":input[name='user']").prop("checked","checked");
		$(".add_label_btn").removeAttr("disabled");
		$(".blacklist").removeAttr("disabled");
	}else{
		$(".user_checkbox").removeAttr("checked");
		$(".add_label_btn").attr("disabled","disabled");
		$(".blacklist").attr("disabled","disabled");
	}
});

/*更改打标签和加入黑名单的状态*/
$(".user_checkbox").change(function(){
	let len = $(":input[name='user']:checked").length;
	if(len > 0){
		$(".add_label_btn").removeAttr("disabled");
		$(".blacklist").removeAttr("disabled");
	}else if(len == 0){
		$(".add_label_btn").attr("disabled","disabled");
		$(".blacklist").attr("disabled","disabled");
	}
});


/*加入黑名单*/
$(".blacklist").click(function(){
	$(":input[name='user']:checked").each(function(){
		let index = $(this).index(":input[name='user']");
		$("#userGroups tr").eq(index).remove();

	});
});

