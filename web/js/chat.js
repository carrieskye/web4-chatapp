window.user = '';
window.friends = [];
window.currentChat = undefined;
window.lastMessageTime = 0;
window.datePrevious = new Date(2000, 0, 0);
window.timePrevious = new Date(2000, 0, 0);
window.timeOut = false;

$(async function () {
    window.user = await getStatus();
    window.friends = await getFriends();
    window.currentChat = window.friends[0];
    $("#newMessage").val('');
    $('#chatTitleName').text(`Chat with ${window.currentChat.firstName}`);
    $('#chatTitleDot').addClass(`${getDotId(window.currentChat.status)}`);
    getMessages();
    $("#chatBody").show();
});

const getStatus = () => new Promise((resolve, reject) => {
    $.get('Controller?action=GetStatus')
        .then(function (result) {
            resolve(result.userId);
        })
        .fail(function (error) {
            console.error('Get status went wrong', error);
            reject(error);
        });
});

const getFriends = () => new Promise((resolve, reject) => {
    $.get('Controller?action=GetFriends')
        .then(function (result) {
            resolve(result);
        })
        .fail(function (error) {
            console.error('Get friends went wrong', error);
            reject(error);
        });
});


function getMessages() {
    if (window.timeOut) clearTimeout(window.timeOut);
    $.get(`Controller?action=GetMessages&friend=${window.currentChat.userId}&lastTimeStamp=${window.lastMessageTime}`)
        .then(function (result) {
            for (let i = 0; i < result.length; i++) {
                drawMessage(result[i]);
            }
            if (result.length > 0) {
                if (result.length === 1) $('#chatWindow > div:last > p:first').hide().slideDown(500);
                window.lastMessageTime = new Date(result[result.length - 1].timeStamp).getTime();
                $('#chatWindow').animate({scrollTop: $("#chatWindow").prop("scrollHeight")}, 1000);
            }
            window.timeOut = setTimeout(getMessages, 1000);
        })
        .fail(function (error) {
            console.error('Get messages went wrong', error);
        });
}

function drawMessage(message) {
    let date = new Date(new Date(message.timeStamp * 1000).toDateString());
    if (date > window.datePrevious) {
        $(`<div class="messageDate">${date.toDateString()}</div>`).appendTo('#chatWindow');
        window.datePrevious = date;
    }
    const isOwn = message.senderId === window.user;
    const sameTime = timeIsEqual(window.timePrevious, new Date(message.timeStamp * 1000));
    if (sameTime) {
        let lastChild = $('#chatWindow')[0].lastChild.lastChild;
        $('#chatWindow')[0].lastChild.removeChild(lastChild);
    }
    $(`<div id="message${message.messageId}" class="${isOwn ? 'sendMessage' : 'receivedMessage'}">`
        + `<p class="${isOwn ? 'sendMessageText' : 'receivedMessageText'}${sameTime ? ' sameTime' : ''}">${message.message}</p>`
        + `<p class="messageTime">${toTimeString(message.timeStamp * 1000)}</p></div>`)
        .appendTo('#chatWindow');
    window.timePrevious = new Date(message.timeStamp * 1000);
}

function sendMessage() {
    const newMessage = $("#newMessage").val();
    $.post(`Controller?action=SendMessage&friend=${window.currentChat.userId}&message=${newMessage}`)
        .then(function () {
            $("#newMessage").val('');
            getMessages();
        })
        .fail(function (error) {
            console.error('Get messages went wrong', error);
        });
}

function toTimeString(timeStamp) {
    const date = new Date(timeStamp);
    return `${date.getHours()}:${`0${date.getMinutes()}`.substr(-2)}`;
}

function timeIsEqual(time1, time2) {
    return time1.getMonth() === time2.getMonth() && time1.getDate() === time2.getDate()
        && time1.getHours() === time2.getHours() && time1.getMinutes() === time2.getMinutes();
}

async function openChat(friend) {
    $('#chatWindow').empty();
    $('#chatTitleDot').removeClass(`${getDotId(window.currentChat.status)}`);
    window.lastMessageTime = 0;
    window.datePrevious = new Date(2000, 0, 0);
    window.timePrevious = new Date(2000, 0, 0);
    window.currentChat = friend;
    $('#chatTitleName').text(`Chat with ${window.currentChat.firstName}`);
    $('#chatTitleDot').addClass(`${getDotId(window.currentChat.status)}`);
    getMessages();
}

$('#newMessage').keyup(function (e) {
    if (e.keyCode === 13) {
        sendMessage();
    }
});