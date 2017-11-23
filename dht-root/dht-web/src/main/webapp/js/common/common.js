function isWeiXin() {
    var ua = window.navigator.userAgent.toLowerCase();
    if (ua.match(/MicroMessenger/i) == 'micromessenger') {
        //$("#rs").text("微信浏览器");
        return true;
    } else {
        //$("#rs").text("不是微信浏览器");
        false;
    }
}