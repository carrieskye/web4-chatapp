<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

<style>
    <%@ include file="css/sample.css" %>
</style>

<header role="banner">
    <nav class="chat-header">
        <ul>
            <li><i class="fa fa-comment"></i>Carolyne's Chat App</li>
            <li><a href="Controller">Home</a></li>
            <c:if test="${not empty param.user}">
                <li><a href="Controller?action=Chat">Chat</a></li>
            </c:if>
        </ul>
    </nav>
</header>

<div class="title">
    <h2>
        ${param.title}
    </h2>
</div>