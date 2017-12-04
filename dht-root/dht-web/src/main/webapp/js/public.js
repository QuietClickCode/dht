"use strict";

function total() {
    $(".J_checkbox.active").each(function () {
        var t = $(this).parents(".J_product").find(".J_price").text(),
            e = $(this).parents(".J_product").find(".J_careNum").text();
        totalNum += t * e
    });
    var t = $(".J_checkbox.active").size();
    $("#J_productNum").text(t), totalNum = parseFloat(totalNum.toFixed(2)), $("#J_totalNum").text(totalNum)
}

$("#footer").html() || $.get("./footer.html").then(function (t) {
    $("#footer").html(t)
}), $(".J_careReduce").click(function () {
    var t = $(this).parents(".number-box").find(".J_careNum"), e = t.text();
    e <= 1 ? e = e : e--, t.text(e), total()
}), $(".J_careAdd").click(function () {
    var t = $(this).parents(".number-box").find(".J_careNum"), e = t.text();
    e++, t.text(e), total()
}), $(".J_checkbox").click(function () {
    $(this).toggleClass("active"), total()
});
var checkboxState = !0;
$(".J_allCheckbox").click(function () {
    $(this).toggleClass("active"), checkboxState ? ($(".J_checkbox").addClass("active"), checkboxState = !1) : ($(".J_checkbox").removeClass("active"), checkboxState = !0), total()
});
var totalNum = parseFloat($("#J_totalNum").text());
$(".J_checkbox").on("click", function () {
    $(this).parents(".J_careCheckbox").find(".J_checkbox").removeClass("active"), $(this).addClass("active")
});