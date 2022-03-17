//获取元素
var getElem = function (selector) {
    return document.querySelector(selector);
}
var getAllElem = function (selector) {
    return document.querySelectorAll(selector);
}

//获取元素的样式
var getCls = function (element) {
    return element.getAttribute('class');
}
//设置元素的样式
var setCls = function (element,cls) {
    return element.setAttribute('class',cls);
}
//添加元素的样式
var addCls = function (element,cls) {
    var baseCls = getCls(element);
    if (baseCls.indexOf(cls) === -1) {
        setCls(element,baseCls +' '+ cls);//注意' '中间的空格
    }
    return;
}
//删除元素的样式
var delCls = function (element,cls) {
    var baseCls = getCls(element);
    if (baseCls.indexOf(cls) > -1){
        //// 更精确的需要用正则表达式 ,因为这里只用于切换 _animate_init所以没事
        setCls(element,baseCls.split(cls).join(' ').replace(/\s+/g,' ') );
    }
}
//初始化
var screenAnimateElements={
    '.screen':['.screen-heading','.screen-heading-descrip',]
}
//设置屏内元素为初始状态
var setScreenAnimateInit = function(screenCls){
    var screen = document.querySelector(screenCls);//获取当前屏的元素
    var animateElements = screenAnimateElements[screenCls];//需要设置动画的元素
    for (var i = 0;i<animateElements.length;i++){
        var element = document.querySelector(animateElements[i]);
        var baseCls = element.getAttribute('class');
        element.setAttribute('class',baseCls + ' ' + animateElements[i].substr(1) + '-animate-init');
    }
}

// 设置播放屏内动画
var playScreenAnimateDone = function(screenCls){
    var screen = document.querySelector(screenCls);//获取当前屏的元素
    var animateElements = screenAnimateElements[screenCls];//需要设置动画的元素
    for (var i = 0;i<animateElements.length;i++){
        var element = document.querySelector(animateElements[i]);
        var baseCls = element.getAttribute('class');

        element.setAttribute('class',baseCls.replace('-animate-init','-animate-done'));
    }
}

window.onload = function () {
    for(k in screenAnimateElements){
        //	优化第一屏动画
        if (k === '.screen'){
            continue;
        }
        setScreenAnimateInit(k);
    }
}

var navItems = getAllElem('.header-nav-item');
var switchNavItemsActive = function(idx){
    for (var i =0;i<navItems.length;i++){
        delCls(navItems[i],'header-nav-item-status-active');
    }
    addCls(navItems[idx],'header-nav-item-status-active');
}
switchNavItemsActive(0);
window.onscroll = function () {
    var top = document.documentElement.scrollTop||document.body.scrollTop;
    //此处注意做兼容处理,有的浏览器可能不支持document.body.scrollTop
    if (top > 640){
        addCls(getElem('.header'),'header-status-white');
    }else {
        delCls(getElem('.header'),'header-status-white');
        switchNavItemsActive(0);
    }
    playScreenAnimateDone('.screen');
}
//滑动门特效
var navTip = getElem('.header-nav-tip');
var setTip = function (idx,lib) { //idx:索引 lib:导航项
    lib[idx].onmouseover = function () {
        console.log(this,idx);
        navTip.style.left = (idx * 88) + 'px';
    }

    var activeIdx = 0;
    lib[idx].onmouseout = function () {
        console.log(this,idx);
        for (var i =0;i<lib.length;i++){
            if (getCls(lib[i]).indexOf('header-nav-item-status-active')>-1) {
                activeIdx = i;
                break;
            }
        }
        navTip.style.left = (activeIdx * 88) + 'px';
    }
}

for (var i = 0;i < navItems.length;i++) {
    setTip(i,navItems);
}
//播放第一屏动画
setTimeout(function () {
    playScreenAnimateDone('.screen');
},200)