document.addEventListener('DOMContentLoaded', function () {

    let xHRObject = new XMLHttpRequest();
    let timeOut;

    function update() {
        if (timeOut) {
            clearTimeout(timeOut);
        }
        xHRObject.open('GET', 'Controller?action=GetFriends', true);
        xHRObject.onreadystatechange = getFriends;
        xHRObject.send(null);
    }


    function getFriends() {
        if (xHRObject.status === 200) {
            if (xHRObject.readyState === 4) {
                let friends = JSON.parse(xHRObject.responseText);
                createFriendList(friends);
                timeOut = setTimeout(update, 1000);
            }
        }
    }

    function createFriendList(friends) {
        let friendsDiv = document.getElementById('friends');
        let friendsList = friendsDiv.childNodes[0];

        if (friendsList != null) {
            friendsDiv.removeChild(friendsList);
        }

        friendsList = document.createElement('div');
        friends.forEach(friend => createFriend(friendsList, friend));
        friendsDiv.appendChild(friendsList);
    }

    function createFriend(friendsList, friend) {
        let friendRow = createElement(friendsList, 'div', friend.userId, 'friendRow');

        friendRow.addEventListener('click', () => {
            openChat(friend);
        });
        createElement(friendRow, 'i', friend.userId , 'fa fa-circle ' + getDotId(friend.status));
        createElement(friendRow, 'span', friend.userId, 'status-name' , friend.firstName);
        createElement(friendRow, 'span', friend.userId, 'status-text', ` (${friend.status})`);
    }

    update();

    document.getElementById('add-friend-form').onsubmit = function () {
        xHRObject.open('POST', 'Controller?action=AddFriend', true);
        xHRObject.setRequestHeader('Content-type', 'application/x-www-form-urlencoded');
        xHRObject.send('friend=' + encodeURI(document.getElementById('friend').value));
        document.getElementById('friend').value = '';
        update();
        return false;
    }

});

function createSimpleElement(type, content, parent, className) {
    let element = document.createElement(type);
    element.className = className;
    let elementContent = document.createTextNode(content);

    element.appendChild(elementContent);
    parent.appendChild(element);
}

function createElement(parent, type, id, className, content) {
    let element = document.createElement(type);
    element.id = id;
    element.className = className;
    element.textContent = content;
    parent.appendChild(element);
    return element;
}