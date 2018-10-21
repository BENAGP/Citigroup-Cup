let totalPageNum;

function initComment() {
    $("#comment_input").val("");
    $.get("/api/comment/pageNum", {
        contractId: contractId
    }).done(function (data) {
        console.log(data);
        totalPageNum = data;
        displayComment();
    })
}

function displayComment() {
    laypage.render({
        elem: 'comment-area'
        ,count: totalPageNum
        ,curr: location.hash.replace('#!page=', '') //获取起始页
        ,layout: ['prev', 'next']
        ,hash: 'page' //自定义hash值
        ,jump: function(obj){
            //obj包含了当前分页的所有参数，比如：
            console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
            console.log(obj.limit); //得到每页显示的条数
            $.post("/api/comment/getList", {
                contractId: contractId,
                userId: userId,
                page: obj.curr - 1,
                pageNum: obj.limit
            }).done(function (data) {
                let html = '';
                for (let i = 0; i < data.length; i++) {
                    html += `<div class="comment-bar">
                        <div class="avatar-bar">
                            <img src="${data[i]['userAvatar']}" class="avatar"/>
                            <div class="username">
                                ${data[i]['userNickname']}
                            </div>
                        </div>
                        <div class="comment-text">
                            <div class="comment-main">
                                ${data[i]['content']}
                            </div>
                            ${data[i]['fatherCommentId'] ? `<div class="comment-sub">
                                ${'@' + data[i]['fatherUserNickname'] + " " + data[i]['fatherCommentContent']} 
                            </div>` : ''}
                            <div class="comment-time">
                                发送于 ${transformDate(data[i]['createTime'])}
                            </div>
                            <div id="replay-${data[i]['commentId']}" onclick="replayComment('${data[i]['commentId']}')" class="comment-replay">
                                回复
                            </div>
                            <div id="replay-bar-${data[i]['commentId']}" style="display: none">
                            <div class="comment-replay-container">
                                <textarea id="replay-area-${data[i]['commentId']}" class='comment-text-area' placeholder="回复@rhys"></textarea>
                            </div>
                            <button class="mibtn-primary" onclick="replay('${data[i]['commentId']}')">回复</button>
                            <button class="mibtn-secondry" onclick="cancelReplay('${data[i]['commentId']}')">取消</button>
                            </div>
                        </div>
                        <hr/>
                    </div>`;
                }
                $("#comment-area").html(html);
            }).fail(function (e) {
                layer.msg(e.responseText);
            })
        }
    });
}

function postComment() {
    let comment = $("#comment_input").val();
    console.log(comment);
    $.post("/api/comment/postComment", {
        contractId: contractId,
        userId: userId,
        content: comment
    }).done(function (data) {
        console.log(data);
        initComment();
    }).fail(function (e) {
        layer.msg(e.responseText);
    })
}

function replayComment(commentId) {
    console.log("replay");
    $("#replay-" + commentId).css("display", "none");
    $("#replay-bar-" + commentId).css("display", "");
}

function replay(commentId) {
    let content = $("#replay-area-" + commentId).val();
    if (!content || content === "") {
        layer.msg("请输入评论");
        return;
    }
    $("#replay-" + commentId).css("display", "");
    $("#replay-bar-" + commentId).css("display", "none");
    $.post("/api/comment/reply", {
        contractId: contractId,
        userId: userId,
        commentId: commentId,
        content: content
    }).done(function (data) {
        console.log(data);
        initComment();
    }).fail(function (e) {
        layer.msg(e.responseText);
    })
}

function cancelReplay(commentId) {
    $("#replay-" + commentId).css("display", "");
    $("#replay-bar-" + commentId).css("display", "none");
}