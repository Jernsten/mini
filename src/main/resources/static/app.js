var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#messages").html("");
}

// Stomp setup
function connect() {
    var socket = new SockJS('/gs-guide-websocket');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/incoming/messages', function (message) {
            showMessage(message);
        });
    });
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({
        'message': $("#message").val(),
        'username': $("#username").val()
    }));
}

function showMessage(message) {
//    $("#messages").append("<tr><td class=\"speaker\">" + JSON.parse(message.body).username + "</td><td class=\"speach\">" + JSON.parse(message.body).content + "</td></tr>");

    console.log($("#name"));

    if (document.getElementById('name').innerText == JSON.parse(message.body).username) {
        var out = "<li class=\"self\"><div class='msg'>" +
            "<div class='user'>" + JSON.parse(message.body).username + "</div>" +
            "<p>" + JSON.parse(message.body).content + "</p>" +
            "<time>...time...</time></div></li>";
    } else {
        var out = "<li class=\"other\"><div class='msg'>" +
            "<div class='user'>" + JSON.parse(message.body).username + "</div>" +
            "<p>" + JSON.parse(message.body).content + "</p>" +
            "<time>...time...</time></div></li>";
    }

    $("#messages").append(out);


    $(".chat").animate({ scrollTop: $(document).height()}, 1000);
}

function disconnect() {
    if (stompClient != null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

// Page settings
$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });

    $("#connect").click(function () {
        connect();
    }); //

    $(window).on("beforeunload", function () {
        disconnect();
    });

    $("#send").click(function () {
        sendMessage();
        document.querySelector("#message").value = '';
    });

    connect();
});