<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
<style>
    <%@ include file="css/sample.css" %>
</style>

<!DOCTYPE html>

<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>

<body id="chatBody">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat"/>
    <jsp:param name="user" value="${user}"/>
</jsp:include>

<main>
    <c:if test="${not empty user}">
        <div class="row">
            <div class="col-xs-2">
                <h3>Friend list</h3>
                <div id="friends"></div>

                <div class="clearfix newFriendForm">
                    <form id="add-friend-form" method="POST" action="Controller?action=AddFriend">
                        <input type="text" id="friend" name="friend" title="friend" class="form-control newFriendInput"
                               placeholder="New friend" required/>
                    </form>
                </div>

                <h3>Your status</h3>
                <div id="user-status"></div>

                <div class="clearfix updateStatusForm">
                    <form id="update-status-form" method="POST" action="Controller?action=UpdateStatus">
                        <input type="text" id="status" name="status" class="form-control updateStatusInput"
                               placeholder="Update status" required/>
                        <datalist id="status-options">
                            <option>online</option>
                            <option>away</option>
                            <option>offline</option>
                        </datalist>
                    </form>
                </div>
            </div>
            <div class="col-xs-9 chat col-xs-offset-1">

                <div class="chatTitle">
                    <h2>
                        <span id="chatTitleName"></span>
                        <i id="chatTitleDot" class="fa fa-circle dotBig"></i>
                    </h2>
                </div>
                <div id="chatWindow"></div>

                <div>
                <textarea id="newMessage" class="newMessageText form-control" rows="3"
                          placeholder="Type a message...">
                    </textarea>
                    <button class="form-control sendMessageButton" onclick="sendMessage()">Send</button>
                </div>
            </div>
        </div>

    </c:if>

    <script type="text/javascript" src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/status.js"></script>
    <script type="text/javascript" src="js/friends.js"></script>
    <script type="text/javascript" src="js/chat.js"></script>
</main>

<jsp:include page="footer.jspf"/>
</body>

</html>