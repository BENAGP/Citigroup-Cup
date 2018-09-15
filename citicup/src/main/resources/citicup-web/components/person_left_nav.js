/**
 * Created by shea on 2018/9/15.
 */
let active_left_tab;
const person_left_nav =
   "<div class='person_title'>个人中心</div>"+
   "<div class='person_board'>"+
   "<div topage='/manage/manage.html' class='left-tab' pos='40'>账户管理</div>"+
   "<br/>"+
   "<div topage='/password/password.html' class='left-tab' pos='126'>登录密码</div>"+
   "<br/>"+
   "<div topage='/person/collection.html' class='left-tab' pos='215'>我的收藏</div>"+
   "<br/>"+
   "<div topage='/messages/messages.html' class='left-tab' pos='304'>我的消息</div>"+

   "<div class='right_block'></div>"+
    "</div>";

$(".person_left_nav").append(person_left_nav);

active_left_tab = window.location.pathname;
$(".person_left_nav .left-tab[topage='" + active_left_tab + "']").addClass("left-active");
$(".right_block").css("top",$(".left-active").attr("pos")+"px");


$(".person_left_nav .left-tab").on('click', function () {
    // console.log($(this).attr("topage"));
    const topage = $(this).attr("topage");
    forward(topage);
});