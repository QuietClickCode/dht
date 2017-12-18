/**
 * Created by zpapj on 2017/11/15.
 */
$("#footer").html() || $.get("/footer.html").then(function(t) {
    $("#footer").html(t)
    selectFooter(curFooter)

});
/**
 *选中底部导航条
 * @param id
 */
function selectFooter(id){
    $("#footer_div li").removeClass("active");
    if(id!='#footer_special'){
        $("#footer_div "+id).addClass("active");
    }
}