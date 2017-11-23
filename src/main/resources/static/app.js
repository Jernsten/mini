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
            showMessage(JSON.parse(message.body).username + ': ' + JSON.parse(message.body).content);
        });
    });
}

function sendMessage() {
    stompClient.send("/app/message", {}, JSON.stringify({'message': $("#message").val(), 'username': $("#username").val()}));
}

function showMessage(message) {
    $("#messages").append("<tr><td>" + message + "</td></tr>");
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