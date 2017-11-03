"use strict";var TouchSlide=function(e){e=e||{};var t={slideCell:e.slideCell||"#touchSlide",titCell:e.titCell||".hd li",mainCell:e.mainCell||".bd",effect:e.effect||"left",autoPlay:e.autoPlay||!1,delayTime:e.delayTime||200,interTime:e.interTime||2500,defaultIndex:e.defaultIndex||0,titOnClassName:e.titOnClassName||"on",autoPage:e.autoPage||!1,prevCell:e.prevCell||".prev",nextCell:e.nextCell||".next",pageStateCell:e.pageStateCell||".pageState",pnLoop:"undefined "==e.pnLoop||e.pnLoop,startFun:e.startFun||null,endFun:e.endFun||null,switchLoad:e.switchLoad||null},n=document.getElementById(t.slideCell.replace("#",""));if(!n)return!1;var a=function(e,t){e=e.split(" ");var n=[];t=t||document;var a=[t];for(var l in e)0!=e[l].length&&n.push(e[l]);for(var l in n){if(0==a.length)return!1;var i=[];for(var r in a)if("#"==n[l][0])i.push(document.getElementById(n[l].replace("#","")));else if("."==n[l][0])for(var o=a[r].getElementsByTagName("*"),s=0;s<o.length;s++){var c=o[s].className;c&&-1!=c.search(new RegExp("\\b"+n[l].replace(".","")+"\\b"))&&i.push(o[s])}else for(var o=a[r].getElementsByTagName(n[l]),s=0;s<o.length;s++)i.push(o[s]);a=i}return 0!=a.length&&a[0]!=t&&a},l=function(e,t){!e||!t||e.className&&-1!=e.className.search(new RegExp("\\b"+t+"\\b"))||(e.className+=(e.className?" ":"")+t)},i=function(e,t){!e||!t||e.className&&-1==e.className.search(new RegExp("\\b"+t+"\\b"))||(e.className=e.className.replace(new RegExp("\\s*\\b"+t+"\\b","g"),""))},r=t.effect,o=a(t.prevCell,n)[0],s=a(t.nextCell,n)[0],c=a(t.pageStateCell)[0],u=a(t.mainCell,n)[0];if(!u)return!1;var d,f,p=u.children.length,v=a(t.titCell,n),m=v?v.length:p,h=t.switchLoad,g=parseInt(t.defaultIndex),T=parseInt(t.delayTime),L=parseInt(t.interTime),b="false"!=t.autoPlay&&0!=t.autoPlay,C="false"!=t.autoPage&&0!=t.autoPage,w="false"!=t.pnLoop&&0!=t.pnLoop,y=g,x=null,N=null,E=null,I=0,P=0,k=0,M=0,S=/hp-tablet/gi.test(navigator.appVersion),F="ontouchstart"in window&&!S,D=F?"touchstart":"mousedown",B=F?"touchmove":"",O=F?"touchend":"mouseup",A=u.parentNode.clientWidth,H=p;if(0==m&&(m=p),C){m=p,v=v[0],v.innerHTML="";var R="";if(1==t.autoPage||"true"==t.autoPage)for(var z=0;m>z;z++)R+="<li>"+(z+1)+"</li>";else for(var z=0;m>z;z++)R+=t.autoPage.replace("$",z+1);v.innerHTML=R,v=v.children}"leftLoop"==r&&(H+=2,u.appendChild(u.children[0].cloneNode(!0)),u.insertBefore(u.children[p-1].cloneNode(!0),u.children[0])),d=function(e,t){var n=document.createElement("div");n.innerHTML=t,n=n.children[0];var a=e.cloneNode(!0);return n.appendChild(a),e.parentNode.replaceChild(n,e),u=a,n}(u,'<div class="tempWrap" style="overflow:hidden; position:relative;"></div>'),u.style.cssText="width:"+H*A+"px;position:relative;overflow:hidden;padding:0;margin:0;";for(var z=0;H>z;z++)u.children[z].style.cssText="display:table-cell;vertical-align:top;width:"+A+"px";var W=function(){"function"==typeof t.startFun&&t.startFun(g,m)},X=function(){"function"==typeof t.endFun&&t.endFun(g,m)},Y=function(e){var t=("leftLoop"==r?g+1:g)+e,n=function(e){for(var t=u.children[e].getElementsByTagName("img"),n=0;n<t.length;n++)t[n].getAttribute(h)&&(t[n].setAttribute("src",t[n].getAttribute(h)),t[n].removeAttribute(h))};if(n(t),"leftLoop"==r)switch(t){case 0:n(p);break;case 1:n(p+1);break;case p:n(0);break;case p+1:n(1)}},V=function(){A=d.clientWidth,u.style.width=H*A+"px";for(var e=0;H>e;e++)u.children[e].style.width=A+"px";Z(-("leftLoop"==r?g+1:g)*A,0)};window.addEventListener("resize",V,!1);var Z=function(e,t,n){n=n?n.style:u.style,n.webkitTransitionDuration=n.MozTransitionDuration=n.msTransitionDuration=n.OTransitionDuration=n.transitionDuration=t+"ms",n.webkitTransform="translate("+e+"px,0)translateZ(0)",n.msTransform=n.MozTransform=n.OTransform="translateX("+e+"px)"},$=function(e){switch(r){case"left":g>=m?g=e?g-1:0:0>g&&(g=e?0:m-1),null!=h&&Y(0),Z(-g*A,T),y=g;break;case"leftLoop":null!=h&&Y(0),Z(-(g+1)*A,T),-1==g?(N=setTimeout(function(){Z(-m*A,0)},T),g=m-1):g==m&&(N=setTimeout(function(){Z(-A,0)},T),g=0),y=g}W(),E=setTimeout(function(){X()},T);for(var n=0;m>n;n++)i(v[n],t.titOnClassName),n==g&&l(v[n],t.titOnClassName);0==w&&(i(s,"nextStop"),i(o,"prevStop"),0==g?l(o,"prevStop"):g==m-1&&l(s,"nextStop")),c&&(c.innerHTML="<span>"+(g+1)+"</span>/"+m)};if($(),b&&(x=setInterval(function(){g++,$()},L)),v)for(var z=0;m>z;z++)!function(){var e=z;v[e].addEventListener("click",function(){clearTimeout(N),clearTimeout(E),g=e,$()})}();s&&s.addEventListener("click",function(){(1==w||g!=m-1)&&(clearTimeout(N),clearTimeout(E),g++,$())}),o&&o.addEventListener("click",function(){(1==w||0!=g)&&(clearTimeout(N),clearTimeout(E),g--,$())});var j=function(e){clearTimeout(N),clearTimeout(E),f=void 0,k=0;var t=F?e.touches[0]:e;I=t.pageX,P=t.pageY,u.addEventListener(B,q,!1),u.addEventListener(O,G,!1)},q=function(e){if(!F||!(e.touches.length>1||e.scale&&1!==e.scale)){var t=F?e.touches[0]:e;if(k=t.pageX-I,M=t.pageY-P,void 0===f&&(f=!!(f||Math.abs(k)<Math.abs(M))),!f){switch(e.preventDefault(),b&&clearInterval(x),r){case"left":(0==g&&k>0||g>=m-1&&0>k)&&(k*=.4),Z(-g*A+k,0);break;case"leftLoop":Z(-(g+1)*A+k,0)}null!=h&&Math.abs(k)>A/3&&Y(k>-0?-1:1)}}},G=function e(t){0!=k&&(t.preventDefault(),f||(Math.abs(k)>A/10&&(k>0?g--:g++),$(!0),b&&(x=setInterval(function(){g++,$()},L))),u.removeEventListener(B,q,!1),u.removeEventListener(O,e,!1))};u.addEventListener(D,j,!1)};