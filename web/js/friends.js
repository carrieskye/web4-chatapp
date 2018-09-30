document.addEventListener("DOMContentLoaded", function () {

    let xHRObject = new XMLHttpRequest();

    function update() {
        xHRObject.open("GET", "ManageFriendsServlet", true);
        xHRObject.onreadystatechange = getFriends;
        xHRObject.send(null);
    }

    function getFriends() {
        if (xHRObject.status === 200) {
            if (xHRObject.readyState === 4) {
                let friends = JSON.parse(xHRObject.responseText);
                console.log(friends);
                createTable(friends);
                setTimeout(getFriends, 2000);
            }
        }
    }

    function createTable(friends) {
        let friendsDiv = document.getElementById("friends");
        let friendsTable = friendsDiv.childNodes[0];

        if (friendsTable != null) {
            friendsDiv.removeChild(friendsTable);
        }

        friendsTable = document.createElement("table");
        friendsTable.id = "friends-table";
        createHeader(friendsTable);
        friends.forEach(friend => createRow(friendsTable, friend));
        friendsDiv.appendChild(friendsTable);
    }

    function createHeader(friendsTable) {
        let friendsHeader = document.createElement("tr");
        createSimpleElement("th", "Name", friendsHeader);
        createSimpleElement("th", "Status", friendsHeader);
        friendsTable.appendChild(friendsHeader);
    }

    function createRow(friendsTable, friend) {
        let friendsRow = document.createElement("tr");
        createSimpleElement("td", friend.firstName, friendsRow);
        createFriendStatus(friendsRow, friend);
        friendsTable.appendChild(friendsRow);
    }

    function createFriendStatus(friendsRow, friend){
        let statusCell = document.createElement("td");

        createSimpleElement("div", friend.status, statusCell, "status-text");

        let statusDotDiv = document.createElement("div");
        statusDotDiv.className = getDotId(friend.status);
        statusCell.appendChild(statusDotDiv);

        friendsRow.appendChild(statusCell);
    }

    update();

});

function createSimpleElement(type, content, parent, className) {
    let element = document.createElement(type);
    element.className = className;
    let elementContent = document.createTextNode(content);

    element.appendChild(elementContent);
    parent.appendChild(element);
}