/**
 * Created by shea on 2018/9/13.
 */
// 基于准备好的dom，初始化echarts实例
let yield_chart = echarts.init(document.getElementById("yield_chart"));
let market_price_chart = echarts.init(document.getElementById("market_price_chart"));
let market_trading_chart = echarts.init(document.getElementById("market_trading_chart"));

const userId = 1;
const tradeId = 87;


function setData(info) {
    $("#contract_name").text(info.contractName);
    $("#risk_level").val(info.riskLevel);
    const temp = info.yearYield*100;
    const yearYield = temp.toFixed(0)+"%";
    $("#year_yield").text(yearYield);
    const near1WeekYield = formatDoubleToPercent(info.near1WeekYield);
    const near3WeekYield = formatDoubleToPercent(info.near3WeekYield);
    const near6WeekYield = formatDoubleToPercent(info.near6WeekYield);
    const near12WeekYield = formatDoubleToPercent(info.near12WeekYield);
    $("#near_1_week_yield").text(near1WeekYield);
    $("#near_3_week_yield").text(near3WeekYield);
    $("#near_6_week_yield").text(near6WeekYield);
    $("#near_12_week_yield").text(near12WeekYield);
    $('#ddl').text(info.ddl);
    const winRate = formatDoubleToPercent(info.winRate);
    const maxDrawdown = formatDoubleToPercent(info.maxDrawdown);
    const profitLossRatio = formatDoubleToPercent(info.profitLossRatio);
    $("#win_rate").text(winRate);
    $("#max_drawdown").text(maxDrawdown);
    $("#profit_loss_ratio").text(profitLossRatio);
    $("#market_capital_capacity").text("¥"+info.marketCapitalCapacity.toFixed(0)+"元");
    let option = {
        grid: {
            left: "0",
            right: "0",
            bottom: "3%",
            top: "4%",
            containLabel: true
        },
        dataZoom: [
            {
                show: true,
                realtime: true,
                start: 0,
                end: 100,
                height: 30,
                y: 215
            },
            {
                type: "inside",
                realtime: true,
                start: 0,
                end: 100
            }
        ],
        xAxis: {
            type: "category",
            boundaryGap: false,
            data: info.formatDates
        },
        yAxis: {
            type: "value"
        },
        series: [
            {
                name: "回测利率",
                data: info.yields,
                type: "line",
                sampling: "average",
                areaStyle: {
                    normal: {
                        color: "#DAE4F4"
                    }
                },
                lineStyle: {
                    normal: {
                        width: 2,
                        color: "#4577C8"
                    }
                },
            },
        ]
    };
    yield_chart.setOption(option);


}

function setHistortyMarket(info) {
    let price_option = {
        grid: {
            left: "2.5%",
            right: "3%",
            bottom: "-4%",
            top: "4%",
            containLabel: true
        },
        dataZoom: [
            {
                show: true,
                realtime: true,
                start: 0,
                end: 100,
                height: 30,
                y: -50
            },
            {
                type: "inside",
                realtime: true,
                start: 0,
                end: 100
            }
        ],
        xAxis: {
            show: false,
            type: "category",
            boundaryGap: false,
            data: info.formatDates
        },
        yAxis: {
            name: '价格',
            splitLine:{show: false},
            type: 'value',
            scale: true,
        },
        // legend: {
        //     data:[info.backFuturesName+"价格",info.nearbyFuturesName+"价格"]
        // },
        series: [
            {
                name: info.backFuturesName+"价格",
                data: info.backPrices.map(price=>{return price.toFixed(0)}),
                type: "line",
                sampling: "average",
                lineStyle: {
                    normal: {
                        width: 2,
                        color: "#f4327d"
                    }
                },
            },
            {
                name: info.nearbyFuturesName+"价格",
                data: info.nearbyPrices.map(price=>{return price.toFixed(0)}),
                type: "line",
                sampling: "average",
                lineStyle: {
                    normal: {
                        width: 2,
                        color: "#0141c9"
                    }
                },
            },
        ]
    };
    let trading_option = {
        grid: {
            left: "4%",
            right: "4%",
            bottom: "3%",
            top: "4%",
            containLabel: true
        },
        dataZoom: [
            {
                show: true,
                realtime: true,
                start: 0,
                end: 100,
                height: 30,
                y: -50
            },
            {
                type: "inside",
                realtime: true,
                start: 0,
                end: 100
            }
        ],
        xAxis: {
            type: "category",
            boundaryGap: false,
            data: info.formatDates
        },
        yAxis: [
            {
                name: '成交量',
                splitLine:{show: false},
                type: 'value',
                interval: 80,
                scale: true
                // nameLocation: 'end',
            }

        ],
        // legend: {
        //     data:[info.nearbyFuturesName+"成交量",info.backFuturesName+"成交量"]
        // },
        series: [
            {
                name: info.nearbyFuturesName+"成交量",
                data: info.nearbyTradings,
                type: "line",
                sampling: "average",
                areaStyle: {
                    normal: {
                        color: "#DAE4F4"
                    }
                },
                lineStyle: {
                    normal: {
                        width: 1,
                        color: "#4577C8"
                    }
                },
            },
            {
                name: info.backFuturesName+"成交量",
                data: info.backTradings,
                type: "line",
                sampling: "average",
                areaStyle: {
                    normal: {
                        color: "#ffd6e7"
                    }
                },
                lineStyle: {
                    normal: {
                        width: 1,
                        color: "#f489ac"
                    }
                },
            }
        ]
    };
    market_price_chart.setOption(price_option);
    market_trading_chart.setOption(trading_option);
}

function getHistoryMarket(info) {
    $.post("/api/contract/getHistoryMarket",{
        userId: userId,
        contractId: info.contractId,
    }).done(response=>{
        setHistortyMarket(response)
    }).fail(err=>{
        console.log(err);
    });
}

function formatDoubleToPercent(num) {
    if(num==null){
        return "暂无数据"
    }
    const temp = num*100;
    console.log(temp.toFixed(2)+"%")
    return temp.toFixed(2)+"%";
}

// yield_chart.setOption(option);

$.post("/api/contract/getDetail",{
    userId:userId,
    tradeId: tradeId
}).done(response=>{
    console.log(response);
    setData(response);
    getHistoryMarket(response);
}).fail(err=>{
    console.log(err);
})


function buy_how_many() {
    var risk_level = document.getElementById("risk_level_2").innerHTML;
    var uid = window.localStorage.getItem("userId");
    var cid = 3;
    var buy = document.getElementById("buy_how_many").value;
    if(buy<1000){
        layer.msg("输入金额小于1000，请重新输入");
        return;
    }
    $.post('/api/contract/buy', {
        userId: uid,
        contractId: cid,
        investment: buy,
        riskLevel: risk_level
    }).done(function (response) {
        console.log(response);
        layer.closeAll();
        layer.msg("购买成功");
    }).fail(function (err) {
        console.log("err",err);
        layer.closeAll();
        layer.msg("网络超时，请稍后再操作");
    })
}

function buy(){
    layer.open({
        type: 1,
        title: false,
        area: ['600px','400px'],
        closeBtn: 0,
        shadeClose: true,
        skin: 'yourclass',
        content: '<div ><b class="goumai">购买（IF1706-IF1703）</b><span class="mairu">买入金额</span><span class="riskLv">风险等级</span><div class="mairurisk"><span style="position: relative;top: 10px;left: 23px;" id="risk_level_2"></span></div><button class="up" style="outline:none;" onClick="risk_up_2()"><img src="上.png"></button><button class="down" style="outline:none;"  onClick="risk_down_2()"><img src="下.png"></button><input class="buy" id="buy_how_many" type="text" placeholder="最低金额1000元"><button onClick="buy_how_many()" class="buy_button">确认购买</button></div>'
    });
    var risk_level=document.getElementById("risk_level").innerHTML;
    var e = document.getElementById('risk_level_2');
    e.innerHTML = risk_level;
}

function change_pic(){
    var imgObj = document.getElementById("收藏_pic");
    var Flag=(imgObj.getAttribute("src",2)==="收藏.png");
    imgObj.src=Flag?"已收藏.png":"收藏.png";
}
function collect() {
    var uid = window.localStorage.getItem("userId");
    var cid = 3;
    if(uid===null){
        layer.msg("请登录");
        setTimeout("forward(\"/home/login.html\");",2000);
        return;
    }
    $.post("/api/contract/collect", {
        userId: uid,
        contractId: cid
    }).done(function(response){
        console.log(response);
        change_pic();
        layer.msg("收藏成功");
    }).fail(function (err) {
        console.log("err",err);
    })
}
function cancel_collect() {
    var uid = window.localStorage.getItem("userId");
    var cid = 3;
    $.post('/api/contract/cancelCollect', {
        userId: uid,
        contractId: cid
    }).done(function(response){
        console.log(response);
        change_pic();
        layer.msg("取消收藏");
    }).fail(function (err) {
        console.log("err",err);
    })
}
function collection() {
    var imgObj = document.getElementById("收藏_pic");
    if(imgObj.getAttribute("src")==="收藏.png"){
        collect();
        return;
    }
    else {
        cancel_collect();
        return;
    }
}

function getDetailByRiskLevel() {
    const riskLevel = $("#risk_level").val();
    const contractId = 3;
    const userId = 1;
    $.post("/api/contract/getDetailByRiskLevel",{
        userId: userId,
        contractId: contractId,
        riskLevel:riskLevel
    }).done(response=>{
        setData(response);
    }).fail(err=>{
        console.log(err);
    })
}


