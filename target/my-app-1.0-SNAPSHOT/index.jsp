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
    <jsp:param name="title" value="Home"/>
</jsp:include>

<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Home"/>
    <jsp:param name="user" value="${user}"/>
</jsp:include>

<main>
    <c:if test="${errors.size()>0 }">
        <div class="danger">
            <ul>
                <c:forEach var="error" items="${errors}">
                    <li>${error }</li>
                </c:forEach>
            </ul>
        </div>
    </c:if>
    <c:choose>
        <c:when test="${not empty user}">
            <p>Welcome ${user.getFirstName()}!</p>
            <form method="post" action="Controller?action=LogOut">
                <input type="submit" id="logoutbutton" value="Log Out" class="form-control">
            </form>
        </c:when>
        <c:otherwise>
            <form method="post" action="Controller?action=LogIn" class="login">
                <div class="form-group row">
                    <div class="col-xs-2">
                        <label for="email">Your email </label>
                    </div>
                    <div class="col-xs-10">
                        <input type="text" id="email" name="email" value="jan@ucll.be" class="form-control">
                    </div>
                </div>
                <div class="form-group row">
                    <div class="col-xs-2">
                        <label for="password">Your password</label>
                    </div>
                    <div class="col-xs-10">
                        <input type="password" id="password" name="password" value="t" class="form-control">
                    </div>
                </div>
                <input type="submit" id="loginbutton" value="Log in" class="form-control">
            </form>
        </c:otherwise>
    </c:choose>
</main>

<jsp:include page="footer.jspf"/>
</body>

</html>