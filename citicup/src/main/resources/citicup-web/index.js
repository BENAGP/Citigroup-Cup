/**
 * Created by shea on 2018/7/29.
 */
let base = new Base64();
const username = 'SandboxUser5';
const password = 'P@ssUser5$';

const arr = [1,2,3,4,5,6];
console.log(JSON.stringify(arr));

function Person(name) {
    const age = 8;
    this.name = name;
    return {
        sayage: function () {
            window.alert(age);
        }
    }
}

function Student(name) {
    this.name = name;
}
var student = new Student("bbb");
Student.prototype = new Person("ttt");
var p = new Person("aaa");
Student.prototype.sayName = function () {
    window.alert(this.name);
};
// window.alert(p.sayName());
// student.sayName();
p.sayName();
p.sayage();

function test() {
    getClientAccessToken().then(res => {
        console.log(res);
        console.log(res.access_token);
        const access_token = res.access_token;
        getUUID().then(uuid => {
            getRSASecurityParams(access_token,uuid).then(data=>{
                getUUID().then(uuid2=>{
                    console.log(data.encrypted_password);
                    console.log(data.bizToken);
                    getLoginAccessToken(uuid2,data.encrypted_password,data.bizToken).then(res=>{
                        $("#access_token").text(res.access_token);
                        getUUID().then(uuid3=>{
                            $("#uuid").text(uuid3);
                        })
                    });
                })
            }).catch(err=>{
                consoe.log(err);
            });
        }).catch(err=>{

            }
        )

    }).catch(err => {

    })
}


function getClientAccessToken() {
    return new Promise((resolve,reject)=>{
        $.ajax({
            type: 'post',
            timeout: 10000,
            headers: {
                'Authorization':"Basic "+window.btoa(decodeURIComponent(encodeURIComponent(key+":"+secret))),
                'Content-Type':'application/x-www-form-urlencoded',
                'Accept':'application/json',
            },
            url: 'https://sandbox.apihub.citi.com/gcb/api/clientCredentials/oauth2/token/hk/gcb',
            data: {
                'grant_type':'client_credentials',
                'scope':'/api',
            },
            success: function(response) {
                resolve(response);

            },
            error: function(err) {
                reject(err);
            },
            complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数　
            }
        });
    })
}

function getUUID() {
    return new Promise((resolve,reject)=>{
        $.get('/api/gene/uuid').done(response=>{
            console.log(response);
            resolve(response);
        }).fail(err=>{
            console.log(err);
            reject(err);
        })
    });
}

function getRSASecurityParams(access_token,uuid) {
    return new Promise((resolve,reject)=>{
        $.ajax({
            type: 'get',
            timeout: 10000,
            headers: {
                'Authorization':"Bearer "+access_token,
                'Content-Type':'application/json',
                'client_id': key,
                'uuid':uuid,
            },
            url: 'https://sandbox.apihub.citi.com/gcb/api/security/e2eKey',
            observe: 'response',
            success: function (response,status,request) {
                const eventid = request.getResponseHeader('eventid');
                const bizToken = request.getResponseHeader('bizToken');
                const modulus = response.modulus;
                const exponent = response.exponent;
                console.log(eventid);
                console.log(bizToken);
                console.log(modulus);
                console.log(exponent);
                let pub = new RSAKey();
                pub.setPublic(modulus,exponent);
                let unencrypted_data = eventid+",b"+password;
                let encrypted_password = pub.encryptB(getByteArray(unencrypted_data)).toString(16);
                console.log(encrypted_password);
                resolve({'encrypted_password':encrypted_password,'bizToken':bizToken});
            },
            error: function (err) {
                reject(err);
            },
            // complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数　
            //     resolve(XMLHttpRequest);
            // }
        });
    });
}

function getLoginAccessToken(uuid,encrypted_password,bizToken) {
    console.log(uuid);
    console.log(encrypted_password);
    console.log(bizToken);
    return new Promise((resolve,reject)=>{
        $.ajax({
            type: 'post',
            timeout: 10000,
            headers: {
                'Authorization':"Basic "+window.btoa(decodeURIComponent(encodeURIComponent(key+":"+secret))),
                'Content-Type':'application/x-www-form-urlencoded',
                'Accept':'application/json',
                'uuid':uuid,
                'bizToken': bizToken,
            },
            url: 'https://sandbox.apihub.citi.com/gcb/api/password/oauth2/token/hk/gcb',
            data: {
                'grant_type':'password',
                'scope':'/api',
                'username': username,
                'password': encrypted_password,
                // 'password': '50649ae68a22d8c239e6728903c9af03acb54df7d9f4788965ddabd95198ff89d322a251d5299ad162ca8ad3c062d2be0b90c8e03794cc3aa87d3e592a630749b2b44a931ffc6182af439dd44147aec9de70e0c994e51c1d35ceb6be70e6e239a2381652c7e829665378dee07c556e7fd43fbf0bb6665d38dbf0e32afa48377f412f45a9c0ba239cd8000d889b26c13677476b004a877cd8b4b4cc1f17817c352cfd350b491cbbe6c25c94a8aab2d6976f732ea3e7c1571db0d141691c2db2a0e039cfaa4aff5b79c1558692e9f7291d62dc3a6c5e40a7b5539af12058228718b5f6aec18d33b01da0267e23f705be8b083e19881b4da20b9259804905ef9bb7',
            },
            success: function(response) {
                resolve(response);

            },
            error: function(err) {
                reject(err);
            },
            complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数　
            }
        });
    })
}

function register() {
    $.post('/api/user/register',{
        email: "875934635@qq.com",
        psw: "86ewr"
    }).done(response=>{
        console.log(response);
    }).fail(err=>{
        console.log("err",err);
    })
}

function test2() {
    $.ajax({
        type: 'get',
        timeout: 10000,
        // beforeSend: function(xhr) {
        //     xhr.setRequestHeader("Access-Toke");
        // },
        headers: {
            'Authorization':"Bearer "+$("#access_token").text(),
            'Content-Type':'application/json',
            'Accept':'application/json',
            'client_id':key,
            'uuid':$("#uuid").text(),
        },
        url: 'https://sandbox.apihub.citi.com/gcb/api/v1/accounts',
        success: function(data) {
            console.log('accounts',data);
        },
        error: function(err) {
            console.error('accounts-arr',err);
        },
        complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数　
        }
    });
    // $.get("/api/gene/uuid").done(function (uuid) {
    //     console.log('uuid-success',uuid);
    //     $.ajax({
    //         type: 'get',
    //         timeout: 10000,
    //         // beforeSend: function(xhr) {
    //         //     xhr.setRequestHeader("Access-Toke");
    //         // },
    //         headers: {
    //             'Authorization':"Bearer "+window.localStorage.getItem('access_token'),
    //             // 'Content-Type':'application/x-www-form-urlencoded',
    //             'Accept':'application/json',
    //             'client_id':key,
    //             'uuid':uuid,
    //         },
    //         url: 'https://sandbox.apihub.citi.com/gcb/api/v1/accounts?nextStartIndex=1',
    //         success: function(data) {
    //             console.log('accounts',JSON.parse(data));
    //         },
    //         error: function(err) {
    //             console.error('accounts-arr',err);
    //         },
    //         complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数　
    //         }
    //     });
    //
    // }).fail(function (data) {
    //     console.log('uuid'+data.responseText);
    //     $.ajax({
    //         type: 'get',
    //         timeout: 10000,
    //         // beforeSend: function(xhr) {
    //         //     xhr.setRequestHeader("Access-Toke");
    //         // },
    //         headers: {
    //             'Authorization':"Bearer "+window.localStorage.getItem('access_token'),
    //             // 'Content-Type':'application/x-www-form-urlencoded',
    //             'Accept':'application/json',
    //             'Client_ID':key,
    //             'uuid':'8120e2d6-0381-4e52-9d90-a5bf771ff243',
    //         },
    //         url: 'https://sandbox.apihub.citi.com/gcb/api/v1/accounts?nextStartIndex=',
    //         success: function(data) {
    //             console.log('accounts',JSON.parse(data));
    //         },
    //         error: function(err) {
    //             console.error('accounts-arr',err);
    //         },
    //         complete: function(XMLHttpRequest, status) { //请求完成后最终执行参数　
    //         }
    //     });
    // });


}

function initParams() {
    $.get('/api/file/initParams').done(response=>{
        console.log(response);
    }).fail(err=>{
        console.log("err",err);
    })
}
