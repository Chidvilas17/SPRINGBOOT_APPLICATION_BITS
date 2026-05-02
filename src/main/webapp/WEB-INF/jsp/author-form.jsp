<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Add Author</title>
    <link rel="stylesheet" type="text/css" href="/css/style.css">
</head>
<body>
    <header>
        <h1>Library Management System</h1>
    </header>
    <nav>
        <a href="/">Home</a>
        <a href="/authors">Manage Authors</a>
        <a href="/books">Manage Books</a>
    </nav>
    <div class="container">
        <h2>Add New Author</h2>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert">${errorMessage}</div>
        </c:if>

        <form:form action="/authors/add" modelAttribute="author" method="post">
            <div class="form-group">
                <label for="name">Author Name:</label>
                <form:input path="name" id="name" />
                <form:errors path="name" cssClass="error" />
            </div>
            
            <div class="form-group">
                <label for="email">Email Address:</label>
                <form:input path="email" id="email" type="email" />
                <form:errors path="email" cssClass="error" />
            </div>
            
            <button type="submit" class="btn">Save Author</button>
            <a href="/authors" class="btn btn-secondary">Cancel</a>
        </form:form>
    </div>
</body>
</html>
