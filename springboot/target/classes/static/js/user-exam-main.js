//获取元素
var getElem = function (selector) {
    return document.querySelector(selector);
}
var getAllElem = function (selector) {
    return document.querySelectorAll(selector);
}
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

/* 从此处往下为导航栏的页面*/

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
/* 从此处往上为导航栏的页面*/

let li=document.getElementsByClassName('litest');
let div=document.getElementsByClassName('select-test-question');
for(let i=0;i<li.length;i++){
    li[i].index=i;
    li[i].onmouseover=function (){
        this.style.backgroundColor='#58FAF4';
        for(let j=0;j<div.length;j++){
            div[j].style.display='none';
        }
        div[this.index].style.display='block';
    }
    li[i].onmouseout=function (){
        li[i].style.backgroundColor='#fff';
    }
}

//悬浮栏相关
var menubox = document.getElementById("area"); //area为菜单栏的id
var cli_on = document.getElementById("on"); //on为按钮
var flag = false, timer = null, initime = null, r_len = 0;
if(menubox.style.right=== 0){
    flag = true;
}
else{
    flag = false;
}
cli_on.onclick = function () {
    //为on按钮绑定click事件
    clearTimeout(initime);
    //根据状态flag执开展开收缩
    if (flag) {
        r_len = 0;
        timer = setInterval(slideright, 10);
    } else {
        r_len = -350;
        timer = setInterval(slideleft, 10);
    }
}
//展开
function slideright() {
    if (r_len <= -350) {
        clearInterval(timer);
        flag = !flag;
        return false;
    }else{
        r_len -= 5;
        menubox.style.right = r_len + 'px';
    }
}
//收缩
function slideleft() {
    if (r_len >= 0) {
        clearInterval(timer);
        flag = !flag;
        return false;
    } else {
        r_len += 5;
        menubox.style.right = r_len + 'px';
    }
}

// 时间
//用于实现显示当前时间效果
function clock(){
    var hours=document.getElementById('hours');
    var minutes=document.getElementById('minutes');
    var seconds=document.getElementById('seconds');
    var h=new Date().getHours();
    var m=new Date().getMinutes();
    var s=new Date().getSeconds();
    hours.innerHTML=h;
    minutes.innerHTML=m;
    seconds.innerHTML=s;
}
var interval=setInterval(clock,1000);