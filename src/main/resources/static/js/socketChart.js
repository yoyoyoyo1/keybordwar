//消息对象数组
var msgObjArr = new Array();

var websocket = null;

//判断当前浏览器是否支持WebSocket， springboot是项目名
if ('WebSocket' in window) {
    websocket = new WebSocket("ws://localhost:8080/websocket/${from}/${to}");
} else {
    console.error("不支持WebSocket");
}

//连接发生错误的回调方法
websocket.onerror = function (e) {
    console.error("WebSocket连接发生错误");
};

//连接成功建立的回调方法
websocket.onopen = function () {
    //获取所有在线用户
    $.ajax({
        type: 'post',
        url: ctx + "/websocket/getOnlineList",
        contentType: 'application/json;charset=utf-8',
        dataType: 'json',
        data: {nickname:nickname},
        success: function (data) {
            if (data.length) {
                //列表
                for (var i = 0; i < data.length; i++) {
                    var nickName = data[i];
                    $("#messages-list").append("<div class=\"usr-msg-details\"><span class='usr-mg-info'>" + nickName + "</span><span id=\"" + nickName + "-status\">[在线]</span><div id=\"msg-notifc" + nickName + "\" class='msg-notifc'>0</div></div>");
                }

                //在线人数
                $("#onlineCount").text(data.length);
            }
        },
        error: function (xhr, status, error) {
            console.log("ajax错误！");
        }
    });
}

//接收到消息的回调方法
websocket.onmessage = function (event) {
    var messageJson = eval("(" + event.data + ")");

    //普通消息(私聊)
    if (messageJson.type == "1") {
        //来源用户
        var fromId = messageJson.fromId;
        //目标用户
        var toId = messageJson.toId;
        //消息
        var message = messageJson.content;

        //追加聊天数据
        setMessageInnerHTML(fromId.fromId,fromId.fromId, content);
    }

    //普通消息(群聊)
   /*if (messageJson.type == "2"){
        //来源用户
        var srcUser = messageJson.srcUser;
        //目标用户
        var tarUser = messageJson.tarUser;
        //消息
        var message = messageJson.message;

        //最加聊天数据
        setMessageInnerHTML(username,tarUser.username, message);
    }*/

    //对方不在线
    if (messageJson.type == "0"){
        //消息
        var message = messageJson.content;

        $("#messages-line").append(
            "<div class=\"hz-message-list\" style='text-align: center;'>" +
            "<div class=\"hz-message-list-text\">" +
            "<span>" + message + "</span>" +
            "</div>" +
            "</div>");
    }

    //在线人数
    if (messageJson.type == "onlineCount") {
        //取出username
        var onlineCount = messageJson.onlineCount;
        var nickName = messageJson.nickname;
        var oldOnlineCount = $("#onlineCount").text();

        //新旧在线人数对比
        if (oldOnlineCount < onlineCount) {
            if($("#" + nickName + "-status").length > 0){
                $("#" + nickName + "-status").text("[在线]");
            }else{
                $("#messages-list").append("<div class=\"usr-msg-details\"><span class='usr-mg-info'>" + nickName + "</span><span id=\"" + nickName + "-status\">[在线]</span><div id=\"msg-notifc" + userName + "\" class='msg-notifc'>0</div></div>");
            }
        } else {
            //有人下线
            $("#" + nickName + "-status").text("[离线]");
        }
        $("#onlineCount").text(onlineCount);
    }

}

//连接关闭的回调方法
websocket.onclose = function () {
    //alert("WebSocket连接关闭");
}

//将消息显示在对应聊天窗口    对于接收消息来说这里的toUserName就是来源用户，对于发送来说则相反
function setMessageInnerHTML(fromId,msgId, content) {
    //判断
    var childrens = $("#messages-list").children(".usr-msg-details");
    var isExist = false;
    for (var i = 0; i < childrens.length; i++) {
        var text = $(childrens[i]).find(".usr-mg-info").text();
        if (text == fromId) {
            isExist = true;
            break;
        }
    }
    if (!isExist) {
        //追加聊天对象
        msgObjArr.push({
            toId: fromId,
            message: [{msgId: msgId, content: content, date: NowTime()}]//封装数据
        });
        $("#messages-list").append("<div class=\"usr-msg-details\"><span class='usr-mg-info'>" + fromId + "</span><span id=\"" + fromId + "-status\">[在线]</span><div id=\"msg-notifc" + srcUserName + "\" class='msg-notifc'>0</div></div>");
    } else {
        //取出对象
        var isExist = false;
        for (var i = 0; i < msgObjArr.length; i++) {
            var obj = msgObjArr[i];
            if (obj.toId == fromId) {
                //保存最新数据
                obj.message.push({msgId: msgId, content: content, date: NowTime()});
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            //追加聊天对象
            msgObjArr.push({
                toId: fromId,
                message: [{msgId: msgId, content: content, date: NowTime()}]//封装数据
            });
        }
    }

    // 对于接收消息来说这里的toUserName就是来源用户，对于发送来说则相反
    var nickname = $("#toId").text();

    //刚好打开的是对应的聊天页面
    if (fromId == nickname) {
        $("#messages-line").append(
            "<div class=\"hz-message-list\">" +
            "<p class='usr-mg-info'>"+msgId+"：</p>" +
            "<div class=\"hz-message-list-text left\">" +
            "<span>" + content + "</span>" +
            "</div>" +
            "<div style=\" clear: both; \"></div>" +
            "</div>");
    } else {
        //小圆点++
        var conut = $("#msg-notifc" + fromId).text();
        $("#msg-notifc" + fromId).text(parseInt(conut) + 1);
        $("#msg-notifc" + fromId).css("opacity", "1");
    }
}

//发送消息
function send() {
    //消息
    var message = $("#messages-send-area").html();
    //目标用户名
    var toId = $("#toUserName").text();
    //登录用户名
    var fromId = $("#talks").text();
    websocket.send(JSON.stringify({
        "type": "1",
        "toId": {"username": toId},
        "fromId": {"username": fromId},
        "content": content
    }));
    $("#messages-line").append(
        "<div class=\"hz-message-list\">" +
        "<div class=\"hz-message-list-text right\">" +
        "<span>" + content + "</span>" +
        "</div>" +
        "</div>");
    $("#messages-send-area").html("");
    //取出对象
    if (msgObjArr.length > 0) {
        var isExist = false;
        for (var i = 0; i < msgObjArr.length; i++) {
            var obj = msgObjArr[i];
            if (obj.toUserName == toId) {
                //保存最新数据
                obj.message.push({username: fromId, content: content, date: NowTime()});
                isExist = true;
                break;
            }
        }
        if (!isExist) {
            //追加聊天对象
            msgObjArr.push({
                toUserName: toId,
                message: [{username: fromId, content: content, date: NowTime()}]//封装数据[{username:huanzi,message:"你好，我是欢子！",date:2018-04-29 22:48:00}]
            });
        }
    } else {
        //追加聊天对象
        msgObjArr.push({
            toUserName: toId,
            message: [{username: fromId, content: content, date: NowTime()}]//封装数据[{username:huanzi,message:"你好，我是欢子！",date:2018-04-29 22:48:00}]
        });
    }
}

//监听点击用户
$("body").on("click", ".messages-list", function () {
    $(".messages-list").css("background-color", "");
    $(this).css("background-color", "whitesmoke");
    $("#toUserName").text($(this).find(".usr-mg-info").text());

    //清空旧数据，从对象中取出并追加
    $("#messages-line").empty();
    $("#msg-notifc" + $("#toUserName").text()).text("0");
    $("#msg-notifc" + $("#toUserName").text()).css("opacity", "0");
    if (msgObjArr.length > 0) {
        for (var i = 0; i < msgObjArr.length; i++) {
            var obj = msgObjArr[i];
            if (obj.toUserName == $("#toUserName").text()) {
                //追加数据
                var messageArr = obj.message;
                if (messageArr.length > 0) {
                    for (var j = 0; j < messageArr.length; j++) {
                        var msgObj = messageArr[j];
                        var leftOrRight = "right";
                        var message = msgObj.message;
                        var msgId = msgObj.msgId;
                        var toUserName = $("#toUserName").text();

                        //当聊天窗口与msgUserName的人相同，文字在左边（对方/其他人），否则在右边（自己）
                        if (msgId == toUserName) {
                            leftOrRight = "left";
                        }

                        //但是如果点击的是自己，群聊的逻辑就不太一样了
                        if (username == toUserName && msgId != toUserName) {
                            leftOrRight = "left";
                        }

                        if (username == toUserName && msgId == toUserName) {
                            leftOrRight = "right";
                        }

                        var magUserName = leftOrRight == "left" ? "<p class='usr-mg-info'>"+msgId+"：</p>" : "";

                        $("#messages-line").append(
                            "<div class=\"hz-message-list\">" +
                            magUserName+
                            "<div class=\"hz-message-list-text " + leftOrRight + "\">" +
                            "<span>" + message + "</span>" +
                            "</div>" +
                            "<div style=\" clear: both; \"></div>" +
                            "</div>");
                    }
                }
                break;
            }
        }
    }
});

//获取当前时间
function NowTime() {
    var time = new Date();
    var year = time.getFullYear();//获取年
    var month = time.getMonth() + 1;//获取月
    var day = time.getDate();//获取天
    var hour = time.getHours();//获取小时
    var minu = time.getMinutes();//获取分钟
    var second = time.getSeconds();//获取秒
    var data = year + "-";
    if (month < 10) {
        data += "0";
    }
    data += month + "-";
    if (day < 10) {
        data += "0"
    }
    data += day + " ";
    if (hour < 10) {
        data += "0"
    }
    data += hour + ":";
    if (minu < 10) {
        data += "0"
    }
    data += minu + ":";
    if (second < 10) {
        data += "0"
    }
    data += second;
    return data;
}//消息对象数组