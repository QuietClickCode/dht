
$(function(){
	$("body").append("<div class='window'></div>");
	let s = $("<style></style>");
	s.text('.window{width: 100%;height: 100%;background-color: rgba(0,0,0,0.8);position: fixed;top: 0;left:0;overflow: hidden;text-align: center;z-index: 9999;display: none;}.window img{width: auto;height: auto;position: relative;top: 50%;max-width: 90%;max-height:100%;transform:translateY(-50%);}');
	$("head").append(s);
	$(".window").click(function(){
		$(this).hide();
		$(this).html('');
	});
});

function popover($this){

	let element = $($this).clone().removeAttr('style');
	$(".window").append(element);
	$(".window").show();
}

