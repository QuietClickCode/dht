$(function(){ 
	$("[data-toggle='tooltip']").tooltip(); 
});

$(".appmsgItem").mouseover(function(){
	$(this).find(".preview_mask").show();
});

$(".appmsgItem").mouseout(function(){
	$(this).find(".preview_mask").hide();
});

$(".card_view").click(function(){
	$(".list_view").css("background-image","url(wx_img/icon_list_msg262dd5.png)");
	$(this).css("background-image","url(wx_img/icon_card_msg_hover262dd5.png)");
	$(".appmsg_list").show();
	$(".appmsg_list_v").hide();
});

$(".list_view").click(function(){
	$(".card_view").css("background-image","url(wx_img/icon_card_msg262dd5.png)");
	$(this).css("background-image","url(wx_img/icon_list_msg_hover262dd5.png)");
	$(".appmsg_list").hide();
	$(".appmsg_list_v").show();
});

$(".del_item").click(function(){
	let x = $(this).offset();
	let left = x.left - 130;
	let top = x.top + 25;
	$(".popover_label").css({
		left:left+"px",
		top:top+"px"
	});
	$(".popover_label").show();
});	


$(".close_popover").click(function(){
	$(".popover_label").hide();
});

$(".add_article_btn").click(function(){
	$(".add_article_list").show();
	return false;
});

$("html").click(function(){
	$(".add_article_list").hide();
});