<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
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


        <div>
            <form method="POST" action="Controller?action=UpdateStatus">
                <p>
                    <label for="status">Change status</label>
                    <input type="text" id="status" name="status" required/>
                </p>
                <input type="submit" id="status-button" value="Change"/>
            </form>
        </div>
    </c:if>

    <script type="text/javascript" src="js/status.js"></script>
    <script type="text/javascript" src="js/friends.js"></script>
</main>

<jsp:include page="footer.jspf"/>
</body>

</html>