<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css"
      integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">

<style>
    <%@ include file="css/sample.css" %>
</style>

<header role="banner" class="banner">
    <nav class="chat-header">
        <div class="row">
            <div class="col-xs-6 text-left">
                <i class="fa fa-comment"></i>Carolyne's Chat App
            </div>
            <div class="col-xs-6 text-right">
                <ul>
                    <li><a href="Controller">Home</a></li>
                    <li><a href="Controller?action=Open&page=Blog">Blog</a></li>
                    <c:choose>
                        <c:when test="${not empty param.user}">
                            <li><a href="Controller?action=Open&page=Chat">Chat</a></li>
                        </c:when>
                        <c:otherwise>
                            <li><a href="Controller?action=Open&page=Register">Register</a></li>
                        </c:otherwise>
                    </c:choose>
                </ul>
            </div>
        </div>
    </nav>
</header>

<div class="title">
    <h2>
        ${param.title}
    </h2>
</div>