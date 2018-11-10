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
    <jsp:param name="title" value="Blog"/>
</jsp:include>

<body onload="openSocket()">
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Blog"/>
    <jsp:param name="user" value="${user}"/>
</jsp:include>

<main>
    <div id="blogs"></div>
    <input id="testjee" class="form-control" type="number" hidden>
    <script type="text/javascript" src="js/blog.js"></script>
</main>

<jsp:include page="footer.jspf"/>
</body>

</html>