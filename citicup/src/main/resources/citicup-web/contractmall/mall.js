var contractList;

var totalCount = 0;
var PAGENUM = 8;
var contractFilterInfo = {
    "contractTradeSearch": {
        "yieldL":0,
        "maxDrawdownL":0,
        "winRateL":0,
        "profitLossRatioL":0,
        "marketCapitalCapacityL":0,
        "yieldR":100,
        "maxDrawdownR":100,
        "winRateR":100,
        "profitRossRatioR":100,
        "marketCapitalCapacityR":100000,
        "type":1
    },
    "userId":1,
    "page":0,
    "pageNum":PAGENUM
};

function getContractList(){
    $.ajax({
        url:"http://localhost:8080/api/contract/getList",
        async:false,
        type:"POST",
        data:contractFilterInfo,
        dataType: "JSON",
        success: function (data){
            if(data.length == 0){
                totalCount = PAGENUM * contractFilterInfo.page;
                alert("已是最后一页！");
                return ;
            }
            contractList = data;
            if(contractList.length == PAGENUM){
                totalCount = PAGENUM * (contractFilterInfo.page+2);
            }else{
                totalCount = PAGENUM * (contractFilterInfo.page+1);
            }
        },
        error: function (data){
            alert("Please try again latar,");
        }
    });
}

function drawer(index, xdata, y1data, y2data){

    var myChart = echarts.init(document.getElementById('graphdiv'+index));

    option = {
        grid: {
            left: '3%',
            right: '4%',
            bottom: '3%',
            top: '4%',
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
                type: 'inside',
                realtime: true,
                start: 0,
                end: 100
            }
        ],
        xAxis: {
            type: 'category',
            boundaryGap: false,
            data: [10,70]
        },
        yAxis: {
            type: 'value'
        },
        series: [
            {
                name: "回测利率",
                data: [10,70],
                type: 'line',
                sampling: 'average',
                areaStyle: {
                    normal: {
                        color: "#DAE4F4"
                    }
                },
                lineStyle: {
                    normal: {
                        width: 3,
                        color: '#4577C8'
                    }
                },
            },
            {
                name: "基准利率",
                data:[10,70],
                type: 'line',
                lineStyle: {
                    normal: {
                        width: 3,
                        color: "#A34240"
                    }
                },
            }
        ]
    };


    myChart.setOption(option);
}

layui.use(['laypage', 'layer'], function(){
    var laypage = layui.laypage
        ,layer = layui.layer;

    //总页数大于页码总数
    laypage.render({
        elem: 'paging'
        ,count: totalCount //数据总数
        ,limit: PAGENUM
        ,layout:[ 'prev', 'next']

        ,jump: function(obj,first){
            document.getElementById('futures').innerHTML = function(){
                contractFilterInfo.page = obj.curr-1;
                getContractList();
                obj.count = totalCount;

                var arr = []
                    ,thisData = contractList.concat().splice(obj.curr*obj.limit - obj.limit, obj.limit);
                layui.each(thisData, function(index, item){
                    var text = '<div class="futureboard">\n' +
                        '                <div class="future_name">'+item.contractName+'</div>\n' +
                        '                <div><img class="staricon" src="../imgs/star.png" />\n' +
                        '                    <button class="collectbutton">收藏</button></div>\n' +
                        '\n' +
                        '                <!-- lengend -->\n' +
                        '                <div class="legend">\n' +
                        '                    <div class="lengend_red"></div>\n' +
                        '                    <div class="lengend_red_text">基准利率</div>\n' +
                        '                    <div class="lengend_blue"></div>\n' +
                        '                    <div class="lengend_blue_text">回测利率</div>\n' +
                        '                </div>\n' +
                        '\n' +
                        '                <div class="graphdiv" id="graphdiv'+index+'">\n' +
                        '                </div>\n' +
                        '\n' +
                        '\n' +
                        '                <div class="info">\n' +
                        '                    <div class="profit">年华收益率</div>\n' +
                        '                    <div class="profittext">'+item.yearYield.toFixed(2)+'</div>\n' +
                        '\n' +
                        '                    <div class="divisioner1"></div>\n' +
                        '\n' +
                        '                    <div class="retracement">最大回撤</div>\n' +
                        '                    <div class="retracementtext">'+item.maxDrawdown.toFixed(2)+'</div>\n' +
                        '\n' +
                        '                    <div class="divisioner2"></div>\n' +
                        '\n' +
                        '                    <div class="carry">市场最大承载资金</div>\n' +
                        '                    <div class="carrytext"> '+item.marketCapitalCapacity.toFixed(2)+'  </div>\n' +
                        '\n' +
                        '                    <div class="win">胜率：</div>\n' +
                        '                    <div class="wintext">'+item.winRate+'</div>\n' +
                        '\n' +
                        '                    <div class="loss">盈亏比：</div>\n' +
                        '                    <div class="losstext">'+item.profitLossRatio+'</div>\n' +
                        '\n' +
                        '                </div>\n' +
                        '\n' +
                        '\n' +
                        '                <button class="buybutton">购买</button>\n' +
                        '\n' +
                        '            </div>';

                    arr.push(text);

                    //drawer(index,item.updateTimes, item.yields , item.fundOccupation);

                });
               return arr.join('');
            }();
        }
        ,theme: '#FD6857'
    });



});