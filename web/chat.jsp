<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<jsp:include page="head.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>

<body>
<jsp:include page="header.jsp">
    <jsp:param name="title" value="Chat"/>
</jsp:include>

<main>
    <c:if test="${user!=null}">
        <div class="status-message">${user.getStatus()}</div>
        <c:choose>
            <c:when test="${user.getStatus()=='online'}">
                <div class="status-online"></div>
            </c:when>
            <c:when test="${user.getStatus()=='offline'}">
                <div class="status-offline"></div>
            </c:when>
            <c:when test="${user.getStatus()=='away'}">
                <div class="status-away"></div>
            </c:when>
            <c:otherwise>
                <div class="status-modified"></div>
            </c:otherwise>
        </c:choose>
    </c:if>
</main>

<jsp:include page="footer.jspf">
    <jsp:param name="title" value="Chat"/>
</jsp:include>
</body>

</html>