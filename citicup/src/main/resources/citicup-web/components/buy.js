
var currentId = 0;
function buy(id){
    currentId = id;
    showBuyBoard();
    currentContract = contractList[id];
}

function showBuyBoard(){
    $("#buytitle").html("购买");
    $("#contentdiv").css("opacity","0.4");
    $("#buyBoard").css("visibility","visible");
}

function buyboardClosed(){
    $("#contentdiv").css("opacity","1");
    $("#buyBoard").css("visibility","hidden");
}

function payboardClosed(){
    $("#contentdiv").css("opacity","1");
    $("#orderboard").css("visibility","hidden");
}

var accounts = ['355a515030616a53576b6a65797359506a634175764a734a3238314e4668627349486a676f7449463949453d',
    '3739334c4d3463614356474f6d7650667a737656664652677747796855646c5552745a43346d37423653553d',
    '41375159436b366b32335a6b566d53315753684d2b69464f43427347654b496e2f6a4f6d4971546e622f773d'];
var account='';
var accountNum = 0;

var money = 0;
var payee  = '7977557255484c7345546c4e53424766634b6c53756841672b556857626e395253334b70416449676b42673d';


function finallyClicked(){
    layui.use('layer', function(){ //独立版的layer无需执行这一句
        var layer = layui.layer; //独立版的layer无需执行这一句
        layer.open({
            type: 1
            ,title: ' '
            ,content: '<div class="tipbeforebuy">购买的金额超过了当前市场最大承载资金，<br/>请问是否继续?</div>'+
            "<div style='text-align: center;padding-bottom: 30px'>"
            ,btn: ['继续', '取消']
            ,btnAlign: 'c' //按钮居中
            ,shade: 0 //不显示遮罩
            ,yes: function(){

                //调用top-nav.js
                if(document.getElementById("account1").checked==true){
                    account = 'XXXXXX1847';
                    accountNum = 0;
                }else if(document.getElementById("account2").checked==true){
                    account = 'XXXXXX7918';
                    accountNum = 1;
                }else if(document.getElementById("account3").checked==true){
                    account = 'XXXXXX5840';
                    accountNum = 2;
                }else{
                    layer.msg("请选择账户！");
                    layer.closeAll();
                    return;
                }

                layer.closeAll();
                buyboardClosed();
                payInfo();

            }
            ,btn2: function(){
            }
        });
    });
}

function payInfo(){
    $("#orderboard").css("visibility","visible");
    money = $("#moneyinput").val();
    $("#orderaccount").html("账户："+account);
    $("#ordermoney").html("金额："+money);
    var currisk = $("#riskinput").val();
    $("#orderrisk").html( "风险："+currisk  );
}

function payClicked(){
    transferPreProgress(accounts[accountNum],money,payee);
    layer.msg("购买成功");
    $("#buy"+currentId).html('已购买');
    document.getElementById("buy"+currentId).setAttribute("disabled", true);
    // }else{
    //     alert("购买失败,请重试！")
    // }
    payboardClosed();
}

