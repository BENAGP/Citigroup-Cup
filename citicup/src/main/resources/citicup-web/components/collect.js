
function collectContract(contractId){
    var res = "true";
    $.post('/api/contract/collect',{
        userId: window.localStorage.getItem("userId"),
        contractId:contractId
    }).done(response => {

    }).fail(err => {
        res = "false";
        alert("please try again！");
    });
    return res;
}

function cancleContract(contractId){
    var res = "true";
    $.post('/api/contract/cancelCollect',{
        userId: window.localStorage.getItem("userId"),
        contractId:contractId
    }).done(response => {

    }).fail(err => {
        res = "false";
        alert("please try again！");
    });
    return res;
}