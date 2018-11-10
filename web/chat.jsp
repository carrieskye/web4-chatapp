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

<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat"/>
    <jsp:param name="user" value="${user}"/>
</jsp:include>

<main>
    <c:if test="${not empty user}">
        <div id="user-status"></div>
        <div id="friends"></div>

        <div class="clearfix">
            <label for="status" class="chatLabel">Update status</label>
            <form id="update-status-form" method="POST" action="Controller?action=UpdateStatus">
                <input type="text" id="status" name="status" class="form-control col-xs-3" required/>
                <datalist id="status-options">
                    <option>online</option>
                    <option>away</option>
                    <option>offline</option>
                </datalist>
                <div class="col-xs-3">
                    <input type="submit" id="status-button" class="form-control" value="Change"/>
                </div>
            </form>
        </div>

        <div>
            <label for="friend" class="chatLabel">Add friend</label>
            <form id="add-friend-form" method="POST" action="Controller?action=AddFriend">
                <input type="text" id="friend" name="friend" title="friend" class="form-control col-xs-3" required/>
                <div class="col-xs-3">
                    <input type="submit" id="new-friend-button" class="form-control" value="Add"/>
                </div>
            </form>
        </div>
    </c:if>

    <script type="text/javascript" src="js/status.js"></script>
    <script type="text/javascript" src="js/friends.js"></script>
</main>

<jsp:include page="footer.jspf"/>
</body>

</html>