/*添加菜单*/
var i = 0;
function addTab(index){
	i += 1;
	let item = $(".menu_item").eq(index);
	item.find(".add_menu_icon").hide();
	let $span = $("<span class='menu_item_name'>菜单名称</span>");
	item.append($span);
	item.attr("onclick","updateMenuName("+index+")");
	let nextItem = item.next();
	nextItem.attr("onclick","addTab("+i+")");
	let len = $(".menu_item:visible").length + 1;
	let w = $(".menu_list").width();
	let $w = w / len;

	if($(".menu_item:visible").length < 3){
		$(".sub_pre_menu_box").css("width",$w+"px");
		nextItem.css("width",$w+"px");
		nextItem.show();
		$(".menu_item").css("width",$w+"px");
		$(".menu_list").append($li);
	}
}


/*添加子菜单*/
function updateMenuName(index){
	let item = $(".menu_item").eq(index);
	item.find(".sub_menu").css("border","none");
	let val = item.find(".menu_item_name").text();
	item.css("border","1px solid rgb(68, 181, 73)");
	item.siblings().css("border","1px solid rgba(0,0,0,0.1)");
	item.find(".add_menu_icon").hide();
	$(".menu_from_body").hide();
	$(".viewSender").show();
	item.find(".sub_pre_menu_box").css("width",item.width());
	$(".sub_pre_menu_box").eq(index).show();
	demo(index);
	$(".setting_menu_name").attr("data-value",index);
	$(".setting_menu_name").val(val);
}


/*修改菜单名称*/
$(".setting_menu_name").change(function(){
	let index = $(this).attr("data-value");
	let val = $(this).val();
	$(".menu_item").eq(index).find("span").text(val);
});

/*隐藏其余子菜单*/
function demo(index){
	let len = $(".sub_pre_menu_box").length;
	for(let i = 0;i<len;i++){
		if(index != i){
			$(".sub_pre_menu_box").eq(i).hide();
		}	
	}
}


/*向右导航切换*/
$(".tab_nav_right").click(function(){
	$(".tab_list").animate({
		marginLeft:"-420px"
	});

	$(this).hide();
	$(".tab_nav_left").show();
});


/*向左导航切换*/
$(".tab_nav_left").click(function(){
	$(".tab_list").animate({
		marginLeft:"0px"
	});
	$(this).hide();
	$(".tab_nav_right").show();
});

/*添加子菜单*/
function addChildMenu(menu_item_index,name,type,val){
	let menu_box = $(".sub_first_menu").eq(menu_item_index);
	let len = $(".sub_pre_menu_box").eq(menu_item_index).find(".sub_menu").length;
	if(!name){
        name="子菜单名称";
	}
	if(len < 5){
		let subMenu = $("<li class='sub_menu' type='"+type+"' val='"+val+"'></li>");
		let title = $("<span class='menu_title'>"+name+"</span>");
		subMenu.append(title);
		menu_box.prepend(subMenu);
		menu_box.find(".sub_menu:not(:last)").each(function(){
			let index = $(this).index();
			$(this).attr("onclick","event.stopPropagation();childClickFun("+menu_item_index+","+index+")");
		});
	}else if(len == 5){
		let firstMenu = menu_box.find(".sub_menu").eq(4);
		firstMenu.find(".menu_add").hide();
		firstMenu.find(".menu_title").show().text(name);
		firstMenu.attr("onclick","event.stopPropagation();childClickFun("+menu_item_index+",4)");
	}
}

/*子菜单点击方法*/
function childClickFun(menu_item_index,index){
	$(".viewSender").hide();
	$(".menu_from_body").show();
	let menu_list = $(".sub_first_menu").eq(menu_item_index);
	menu_list.find(".sub_menu").eq(index).css("border","1px solid rgb(68, 181, 73)").siblings().css("border","1px solid rgba(0,0,0,0.1)");
	let text = menu_list.find(".sub_menu").eq(index).find(".menu_title").text();
	$(".menu_child_name").val(text);
	$(".menu_child_name").attr("menu-item-index",menu_item_index);
	$(".menu_child_name").attr("data-index",index);

	$("#appMsgArea").hide();
	$(".appMsg_info_area").show();
}

$(".menu_child_name").change(function(){
	let menu_item_index = $(this).attr("menu-item-index");
	let index = $(this).attr("data-index");
	let val = $(this).val();
	let menu_list = $(".sub_first_menu").eq(menu_item_index);
	menu_list.find(".sub_menu").eq(index).find(".menu_title").text(val);

});


$(".nav_tab").click(function(){
	let index = $(this).attr("data-value")
	$(".menu_content").eq(index).show().siblings().hide();
});

$(".tab_nav").click(function(){
	let index = $(this).index();
	$(".tab_content").eq(index).show().siblings().hide();
});

$(".global_extra").click(function(){
	$(".ui_draggable").show();
	$(".del_menu_dialog").show();
});

$(".choose_webapp").click(function(){
	$(".ui_draggable").show();
	$(".dialog_wrp").show();
});

$(".tab_nav").click(function(){
	$(this).find("a").css("color","#222");
	$(this).siblings().find("a").css("color","#8d8d8d");
});


function chooseMsgSender(){
	$(".ui_draggable").show();
	$(".send_card").show();
}

$(".closed").click(function(){
	$(".ui_draggable").hide();
	$(".ui_toggle").hide();
});