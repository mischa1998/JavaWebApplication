<!doctype html>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    UserPage of my Example Application
</head>
<br>
<a href="/logout">Logout</a>
<body>
    <h4>User</h4>
    <p><h2><c:out value="${userName}"/></h2></p>
    <p><c:if test="${not empty list}">
        <c:forEach items="${list}" var="message">
            <c:out value="${message}"/>
            <br>
        </c:forEach>
        </c:if>
    </p>
    <form method="post" action="/addMessage">
        <input type="text" name="message">
        <input type="submit" value="add Message">
    </form>
    <form method="post" action="/messages">
        <input type="submit" value="Get messages">
    </form>
</body>
</html>