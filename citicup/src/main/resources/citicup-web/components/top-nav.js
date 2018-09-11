/**
 * Created by shea on 2018/8/9.
 */
let top_nav_islogin;
if(window.localStorage.getItem("userId")){
    top_nav_islogin=true;
}else {
    top_nav_islogin = false;
}
const nav =
    "<div>" +
    "<div class='logo'>" +
    "<img src='../imgs/logo4.png'> " +
    "</div>" +
    "<div class='tab' topage='/person/collection.html'>个人中心</div> "+
    "<div class='tab' topage='/transaction/transaction.html'>我的交易</div> "+
    "<div class='tab' topage='/contractmall/contractmall.html'>合约商城</div> "+
    "<div class='tab' topage='/home/home.html'>首页</div> "+
    "</div>";


$(".top-nav").append(nav);
active_tab = window.location.pathname;
console.log(active_tab)
$(".top-nav .tab[topage='"+active_tab+"']").addClass("active");


$(".top-nav .tab").on('click',function () {
    // console.log($(this).attr("topage"));
    const topage = $(this).attr("topage");
    if(topage.split("/")[1]=="transaction"||topage.split("/")[1]=="person"){
        if(!top_nav_islogin)forward("/home/login.html");
        else forward(topage);
    }else {
        forward(topage);
    }
});

const footer = "<div>Copy Right ©2018 Machine Insight </div>";
$(".footer").append(footer);



