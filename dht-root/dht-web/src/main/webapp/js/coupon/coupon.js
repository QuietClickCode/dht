/**
 * 展示优惠卷
 * @param couponId 元素id
 * @param row 行数据
 */
function showCoupon(couponId,row) {
    console.log(row)
    let html='<li><a href=""><div class="coupon_item_box clearfix">';
    html+='<div class="item_box_left"><span>￥</span>200</div>';
    html+='<div class="item_box_right"><p class="term">满200可用</p>';
    html+='<p class="time">2017-11-11</p></div></div><p class="p1">服装类商品通用</p>';
    html+='<span class="btn_span">立即使用</span></a></li>';
    $(couponId).append(html);
}
