<!DOCTYPE>
<html>
<head>
    <title>
        texas-holdem-calculator
    </title>
    <style type="text/css">
        .player {
            border: 1px solid;
            display: inline-block;
        }

        .card {
            height: 175px;
            float: left;
        }
    </style>
    <script src="/js/vue.js"></script>
    <script src="/js/axios.min.js"></script>
</head>
<body>
<h1>there are ${persons} in the game,preflop is:</h1>
<#list   map?keys as mykey>
    <#assign mapUser=map[mykey] >
<div class="player">
    <#list mapUser as contents >
        <div class="card">
            <img width="100px" height="175px" src="/img/${contents.number}${contents.flower?substring(0,1)}.png">
        </div>
    </#list>
    <div style="clear:both"></div>
</div>
</#list>
</body>
var vm = new Vue({
el: '#page',
data: {
tabindex: window.localStorage.getItem("tabindex") ? parseInt(window.localStorage.getItem("tabindex")) : 1,
datalist: [],
mescroll: null,
chooseimg: "",
showmodal:false,
loading:false
},
filters: {
formatDate: function (value) {
let date = new Date(value);
let y = date.getFullYear();
let MM = date.getMonth() + 1;
MM = MM < 10 ? ('0' + MM) : MM;
let d = date.getDate();
d = d < 10 ? ('0' + d) : d;
let h = date.getHours();
h = h < 10 ? ('0' + h) : h;
let m = date.getMinutes();
m = m < 10 ? ('0' + m) : m;
let s = date.getSeconds();
s = s < 10 ? ('0' + s) : s;
return y + '-' + MM + '-' + d + ' ' + h + ':' + m + ':' + s;
}
},

mounted: function () {
var self = this;
self.mescroll = new MeScroll("datalist", { //请至少在vue的mounted生命周期初始化mescroll,以确保您配置的id能够被找到
up: {
callback: self.upCallback, //上拉回调
//以下参数可删除,不配置
isBounce: false, //此处禁止ios回弹,解析(务必认真阅读,特别是最后一点): http://www.mescroll.com/qa.html#q10
//page:{size:8}, //可配置每页8条数据,默认10
toTop: { //配置回到顶部按钮
src: "/app/img/mescroll-totop.png", //默认滚动到1000px显示,可配置offset修改
//html: null, //html标签内容,默认null; 如果同时设置了src,则优先取src
offset: 500
},
empty: { //配置列表无任何数据的提示
warpId: "datalist",
icon: "/app/img/mescroll-empty.png",
tip: "暂无相关数据或网络错误", //提示
//						  	}
},
}
});
},
methods: {
imgScc: function (liitem) {
this.chooseimg = liitem
this.showmodal = true;
},
close:function(){
this.showmodal = false;
},
loaddata: function (index, page) {
console.log(this.loading)
if(this.loading){
return
}
this.loading = true
if(page == undefined){
this.datalist = [];
}

var _this = this;
console.log(_this.tabindex,index)
if (_this.tabindex != index) {
_this.tabindex = index;
window.localStorage.setItem("tabindex", index);
console.log("重置清空list");
_this.datalist = [];

}
var url = "";
var pagenum = 0;
var pagesize = 10;
if (page) {
pagenum = page.num - 1;
pagesize = page.size;
}

switch (index) {
case 1:
//url = "/app/mock/mock_index.json";
url = "/api/fault/fault?orderStatus=FAULT_ORDER_STATUS_PENDING&page=" + pagenum + "&size=" + pagesize;
break;
case 2:
url = "/api/fault/fault?orderStatus=FAULT_ORDER_STATUS_BEING&page=" + pagenum + "&size=" + pagesize;
break;
case 3:
url = "/api/fault/fault?orderStatus=FAULT_ORDER_STATUS_APPROVAL&page=" + pagenum + "&size=" + pagesize;
//  url = "/app/mock/mock_index.json";
break;
case 4:
url = "/api/fault/fault?orderStatus=FAULT_ORDER_STATUS_STOCKS&page=" + pagenum + "&size=" + pagesize;
break;
}
axiosinstance.get(url, {}).then(function (res) {
_this.loading = false
if (page && page.num == 1) {
console.log("下拉刷新")
_this.datalist = [];
}
console.log(res.data.list)
_this.datalist = _this.datalist.concat(res.data.list);
_this.mescroll.endBySize(res.data.list.length, res.data.total);
}).catch(function (res) {
_this.loading = false
_this.mescroll.endBySize(0, 0);
console.log(res);
});
},
//上拉回调 page = {num:1, size:10}; num:当前页 ,默认从1开始; size:每页数据条数,默认10
upCallback: function (page) {
console.log(page.num, page.size)
this.loaddata(this.tabindex, page)
},
},
created: function () {
var aaa = window.localStorage.getItem("tabindex")
// alert(aaa)
}
})
</script>

