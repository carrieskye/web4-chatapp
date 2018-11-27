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
    <jsp:param name="title" value="Register"/>
</jsp:include>

<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Register"/>
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

    <form method="post" action="Controller?action=Register" class="register">
        <div class="row">
            <div class="col-xs-6 leftCol">
                <div class="form-group">
                    <label for="firstName">First name</label>
                    <input type="text" id="firstName" name="firstName" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="lastName">Last name</label>
                    <input type="text" id="lastName" name="lastName" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="age">Age</label>
                    <input type="number" id="age" name="age" class="form-control" min="0" max="150" required>
                </div>
                <div class="form-group">
                    <label for="gender">Gender</label>
                    <input type="radio" id="gender" name="gender" value="male"> Male<br>
                    <input type="radio" name="gender" value="female"> Female<br>
                </div>
            </div>
            <div class="col-xs-6 rightCol">
                <div class="form-group">
                    <label for="email">Your email </label>
                    <input type="email" id="email" name="email" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="password">Your password</label>
                    <input type="password" id="password" name="password" class="form-control" required>
                </div>
                <div class="form-group">
                    <label for="passwordRepeated">Confirm password</label>
                    <input type="password" id="passwordRepeated" name="passwordRepeated" class="form-control" required>
                </div>
            </div>
        </div>
        <input type="submit" id="registerButton" value="Register" class="form-control">
    </form>

    <script type="text/javascript" src="//code.jquery.com/jquery-3.3.1.min.js"></script>
    <script type="text/javascript" src="js/register.js"></script>
</main>

<jsp:include page="footer.jspf"/>
</body>

</html>