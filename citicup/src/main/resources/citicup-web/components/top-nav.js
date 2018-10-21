/**
 * Created by shea on 2018/8/9.
 */
// let top_nav_islogin = true;
let info_disabled = false;
if (window.localStorage.getItem("userId")) {
    top_nav_islogin = true;
} else {
    top_nav_islogin = false;
}
let login_index = 0;
let info_index = 0;
let person_nav_tab =
    top_nav_islogin ?
        "<div class='tab' topage='/person/collection.html'>个人中心</div> " :
        "<div class='tab' onclick='showLogin()'>登录</div>";

const nav =
    "<div>" +
    "<div class='logo'>" +
    "<img src='../imgs/logo4.png'> " +
    "</div>" +
    person_nav_tab +
    "<div class='tab' topage='/transaction/transaction.html'>我的交易</div> " +
    "<div class='tab' topage='/contractmall/contractmall.html'>合约商城</div> " +
    "<div class='tab' topage='/home/home.html'>首页</div> " +
    "</div>";


$(".top-nav").append(nav);
active_tab = window.location.pathname;
console.log(active_tab)
$(".top-nav .tab[topage='" + active_tab + "']").addClass("active");


$(".top-nav .tab").on('click', function () {
    // console.log($(this).attr("topage"));
    const topage = $(this).attr("topage");
    if(topage){
        if (topage.split("/")[1] == "transaction" || topage.split("/")[1] == "person") {
            if (!top_nav_islogin) showLogin();
            else forward(topage);
        } else {
            forward(topage);
        }
    }
});

function showLogin() {
    login_index = layer.open({
        type: 1,
        title: false,
        area: ['450px', '500px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'yourclass',
        content: "<div id='login_pad' class = 'registerpad' >" +
        "<h1 class='login'>登陆</h1>" +
        "<label>" +
        "<input class='usrname' type='text' placeholder='账号'>" +
        "</label>" +
        "<label>" +
        "<input class='password' type='password' placeholder='密码'>" +
        "</label>" +
        "<label>" +
        "<input class='submit' type='submit' value='提交' onclick='login()'>" +
        "</label>" +
        "</div>",
    });
}

function login ()
{
    const password = $("#login_pad").find(".password").val();
    const username = $("#login_pad").find(".usrname").val();
    if(username === '')
    {
        layer.msg("请输入用户名！");
        return;
    }
    if(password === '')
    {
        layer.msg("请输入密码！");
        return;
    }

    $.get('/api/user/preLogin').done(encryptPara=>{
        console.log(encryptPara);
        let pub = new RSAKey();
        pub.setPublic(encryptPara.modulus,encryptPara.exponent);
        let unencrypted_data = encryptPara.eventId+",b"+password;
        let encrypted_password = pub.encryptB(getByteArray(unencrypted_data)).toString(16);
        doLogin(username,encrypted_password,encryptPara);

    }).fail(error=>{
        layer.msg(error.responseText);
    })

}

function doLogin(username,encrypted_password,encryptPara) {
    $.post('/api/user/login',{
        'username': username,
        'password': encrypted_password,
        'bizToken':encryptPara.bizToken,
    }).done(response=>{
        console.log(response);
        setUser(response);
        if(!response.isCompleted)
        {
            layer.close(login_index);
            layer.alert("用户信息不完整,请补完信息！",function (index) {
                layer.close(index);
                showInfo();
                // getAccounts();
            });
        }else {
            forward("/home/home.html");
        }

    }).fail(err=>{
        layer.msg(err.responseText);
    })
}

function getAccounts() {
    $.get('/api/user/getAccounts').done(accounts=>{
        console.log(accounts);

    }).fail(error=>{
        layer.msg(error.responseText);
    })
}

function perfectInfo() {
    $.post('/api/user/perfectInfo', {
        userId: window.localStorage.getItem("userId"),
        email: $("#info_pad").find(".email").val(),
        nickname: $("#info_pad").find(".nickname").val(),
        preferRiskLevel: null,
        avatar: $("#avatar-pic").attr("src"),
    }).done(response => {
        console.log(response);
        layer.msg("修改成功！");
        layer.close(info_index);
    }).fail(err => {
        layer.msg(err.responseText)
    })
}

function showInfo() {
    info_index = layer.open({
        type: 1,
        title: false,
        area: ['450px', '700px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'yourclass',
        content: "<div id='info_pad' class='registerpad'>" +
        "<h5 class='login'>完善信息</h5>" +
        "<div style='text-align: center;font-size: 12px;color: #a8a8a8;'>系统检测到您是第一次登录网站，请完善您的信息</div>" +
        "<input class='nickname' type='text' placeholder='昵称'>" +
        "<input class='email' type='text' placeholder='邮箱'>" +
        "<div class='layui-upload' style='text-align: center;margin-top: 30px'>" +
        "<div style='text-align: center'>" +
        "<div id='uploadAvatar' style='text-align: center;color: #959595;border: 1px solid #e6e6e6;width: 200px;height: 200px;margin-left: 125px;cursor: pointer'>" +
        "<div style='font-size: 72px;margin-top: 30px '>+</div>" +
        "<div style='font-size: 14px' >上传头像</div>" +
        "</div>" +
        "<img src='../imgs/avatar.png'  class='layui-upload-img' style='width: 200px;height: 200px;background-color: white;border: 1px solid #f4f4f4;display: none' id='avatar-pic'>" +
        // "<p id='demoText'></p>"+
        // "<button type='button' class='layui-btn' id='uploadAvatar' style='width: 200px;margin-top: 20px'>上传头像</button>"+
        "</div>" +
        "<label>" +
        "<input disabled='"+info_disabled+"' class='submit' type='submit' value='提交' onclick='perfectInfo()'>" +

        "<input class='submit' type='submit' style='background-color: #e6e6e6;color: #5E5E5E;margin-top: 10px' value='跳过' onclick='closeInfo()'>" +
        "</label>" +
        "</div>" +
        "</div>",
    });
    layui.use('upload', function () {
        info_disabled = true;
        var upload = layui.upload;

        //执行实例
        var uploadInst = upload.render({
            elem: '#uploadAvatar' //绑定元素
            , url: '/api/user/postAvatar' //上传接口
            , data: {
                userId: 1
            },
            before: function (obj) {
                //预读本地文件示例，不支持ie8
                obj.preview(function (index, file, result) {
                });
            }

            , done: function (res) {
                $('#avatar-pic').attr('src', res.url);//图片链接（base64）
                $('#avatar-pic').css('display', 'inline');
                $('#uploadAvatar').css('display', 'none');
                //上传完毕回调
                info_disabled = false;
                $("#info_pad").find(".submit").attr("disabled",info_disabled);
                // avatar = res.url;
                console.log(res);
            }
            , error: function (err) {
                //请求异常回调
                layer.msg(data.responseText);
            }
        });
    });

}

function closeInfo() {
    layer.close(info_index)
}

function uploadPic() {
    var file = fileInput.files[0];

    var formData = new FormData();
    formData.append("userId", uid);
    formData.append("file", file);
    $.ajax({
        url: '/api/user/postAvatar',
        type: 'POST',
        data: formData,
        processData: false,
        contentType: false,
        dataType: 'text',
        success: function (msg) {
            console.log('ggg');
            console.log(msg);
            previewSrc = msg;
            $("#preview").css("display", "block");
            $("#preview").attr("src", msg);
        },
        error: function (msg) {
            console.log(msg)
        }
    })
}

const footer = "<div>Copy Right ©2018 Machine Insight </div>";
$(".footer").append(footer);



