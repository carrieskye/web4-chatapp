document.addEventListener("DOMContentLoaded", function () {

    let xHRObject = new XMLHttpRequest();

    function update() {
        xHRObject.open("GET", "ManageStatusServlet", true);
        xHRObject.onreadystatechange = getStatus;
        xHRObject.send(null);
    }

    function getStatus() {
        if (xHRObject.status === 200) {
            if (xHRObject.readyState === 4) {
                let serverResponse = JSON.parse(xHRObject.responseText);
                console.log(serverResponse);
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
            createSimpleElement("div", status, statusDiv, "status-text");
        } else {
            let statusText = document.createTextNode(status);
            statusTextDiv.removeChild(statusTextDiv.childNodes[0]);
            statusTextDiv.appendChild(statusText);
        }
    }

    function createStatusDot(statusDiv, status) {
        let statusDotDiv = statusDiv.childNodes[1];

        if (statusDotDiv == null) {
            statusDotDiv = document.createElement("div");
        } else {
            statusDiv.removeChild(statusDotDiv);
        }

        statusDotDiv.className = getDotId(status);
        statusDiv.appendChild(statusDotDiv);
    }

    update();

});

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