$(function(){ 
	$("[data-toggle='tooltip']").tooltip();
	$(window).resize(function() {
  		let x = $(".edit_gray").eq(index).offset();
  		let left = x.left - 140;
		let top = x.top + 30;
		$("#compile_img_name").css({
			left:left+"px",
			top:top+"px"
		});
		$("#compile_img_name").show();


	});
});

/*全选和全不选*/
$(".check_all").change(function(){
	let flag = $(this).is(':checked');
	if(flag){
		$(":input[name='img_src']").prop("checked","checked");
		$(".move_group").removeAttr("disabled");
		$(".del_group").removeAttr("disabled");
		
	}else{
		$(":input[name='img_src']").removeAttr("checked");
		$(".move_group").attr("disabled","disabled");
		$(".del_group").attr("disabled","disabled");
	}
});

var index;
/*添加修改图片素材弹出框*/
$(".edit_gray").click(function(){
	let that = $(this);
	showPopover("#compile_img_name",that,10,9);
});


/*添加移动分组弹出框*/
$(".msg_card_opr_item_inner").click(function(){
	let that = $(this);
	showPopover("#group_select",that,10,9);
});

/*添加删除该图片素材弹出框*/
$(".del_gray").click(function(){
	let that = $(this);
	showPopover("#del_lib",that,10,9);
});

/*添加创建分组弹出框*/
$(".add_menu_item").click(function(){
	let that = $(this);
	showPopover("#create_group_popover",that,15);
});



/*添加关闭弹出框功能*/
$(".close_popover").click(function(){
	$(".popover_img_lib").hide();
});

/*移动所有选中的分组*/
$(".move_group").click(function(){
	if($(this).attr("disabled") == "disabled")
		return;
	let that = $(this);
	showPopover("#group_select",that);
});

/*删除所有选中的分组*/
$(".del_group").click(function(){
	if($(this).attr("disabled") == "disabled")
		return;
	let that = $(this);
	showPopover("#del_lib",that);
});

function showPopover(id,that,t,l){
	if(typeof(t) == "undefined")
		t = 0;
	if(typeof(l) == "undefined")
		l = 0;
	let w =	that.width() / 2;
	let h = that.height();
	let x = that.offset();
	let left = x.left - 137 + w - l;
	let top = x.top + 25 + h - t;
	$(id).css({
		left:left+"px",
		top:top+"px"
	});
	let i = $(id).attr("data-index");
	$(".popover_img_lib").eq(i).show();
	$(".popover_img_lib").eq(i).siblings(".popover_img_lib").hide();
}

$(":input[name='img_src']").change(function(){
	let len = $(":input[name='img_src']:checked").length;
	if(len > 0){
		$(".move_group").removeAttr("disabled");
		$(".del_group").removeAttr("disabled");
	}else if(len == 0){
		$(".move_group").attr("disabled","disabled");
		$(".del_group").attr("disabled","disabled");
	}
});