document.addEventListener('DOMContentLoaded', function () {

    let xHRObject = new XMLHttpRequest();
    let timeOut;

    function update() {
        if (timeOut) {
            clearTimeout(timeOut);
        }
        xHRObject.open('GET', 'Controller?action=GetStatus', true);
        xHRObject.onreadystatechange = getStatus;
        xHRObject.send(null);
    }

    function getStatus() {
        if (xHRObject.status === 200) {
            if (xHRObject.readyState === 4) {
                let serverResponse = JSON.parse(xHRObject.responseText);
                createStatusMessage(serverResponse);
                timeOut = setTimeout(update, 2000);
            }
        }
    }

    function createStatusMessage(response) {
        let statusDiv = document.getElementById('user-status');
        let userStatus = statusDiv.childNodes[0];

        if (userStatus != null) {
            statusDiv.removeChild(userStatus);
        }

        let friendRow = createElement(statusDiv, 'div', undefined, 'statusRow');
        createElement(friendRow, 'i', undefined, 'fa fa-circle ' + getDotId(response.status));
        createElement(friendRow, 'span', undefined, 'status-name', response.firstName);
        createElement(friendRow, 'span', undefined, 'status-text', ` (${response.status})`);

    }

    document.getElementById('update-status-form').onsubmit = function () {
        xHRObject.open('POST', 'Controller?action=UpdateStatus', true);
        xHRObject.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xHRObject.send('status=' + encodeURI(document.getElementById('status').value));
        document.getElementById('status').value = '';
        update();
        return false;
    };

    update();

});

function getDotId(status) {
    switch (status) {
        case 'online':
            return 'status-online';
        case 'offline':
            return 'status-offline';
        case 'away':
            return 'status-away';
        default:
            return 'status-modified';
    }
}