window.onload = getStatus;

let xHRObject = new XMLHttpRequest();

function getStatus() {
    xHRObject.open("GET", "ManageStatusServlet", true);
    xHRObject.onreadystatechange = getData;
    xHRObject.send(null);
}

function getData() {
    if (xHRObject.status === 200) {
        if (xHRObject.readyState === 4) {
            let serverResponse = JSON.parse(xHRObject.responseText);
            let status = serverResponse.status;
            let statusDiv = document.getElementById("user-status");
            createStatusMessage(statusDiv, status);
            createStatusDot(statusDiv, status);
            setTimeout(getStatus, 2000);
        }
    }
}

function createStatusMessage(statusDiv, status) {
    let statusTextDiv = statusDiv.childNodes[0];

    if (statusTextDiv == null) {
        statusTextDiv = document.createElement('div');
        statusTextDiv.id = "statusText";
        let statusText = document.createTextNode(status);
        statusTextDiv.appendChild(statusText);
        statusDiv.appendChild(statusTextDiv);
    } else {
        let statusText = document.createTextNode(status);
        statusTextDiv.removeChild(statusTextDiv.childNodes[0]);
        statusTextDiv.appendChild(statusText);
    }
}

function createStatusDot(statusDiv, status) {
    let statusDotDiv = statusDiv.childNodes[1];

    if (statusDotDiv == null) {
        statusDotDiv = document.createElement('div');
        statusDotDiv.id = getDotId(status);
        statusDiv.appendChild(statusDotDiv);
    } else {
        statusDotDiv.id = getDotId(status);
    }
}

function getDotId(status) {
    switch (status) {
        case "online":
            return "status-online";
        case "offline":
            return "status-offline";
        case "away":
            return "status-away";
        default:
            return "status-modified";
    }

}