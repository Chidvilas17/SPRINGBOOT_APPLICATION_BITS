<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>
<!DOCTYPE html>
<html>
<head>
    <title>${book.id != null ? 'Edit Book' : 'Add Book'}</title>
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
        <h2>${book.id != null ? 'Edit Book' : 'Add New Book'}</h2>
        
        <c:if test="${not empty errorMessage}">
            <div class="alert">${errorMessage}</div>
        </c:if>

        <form:form action="${book.id != null ? '/books/edit/'.concat(book.id) : '/books/add'}" modelAttribute="book" method="post">
            <div class="form-group">
                <label for="title">Book Title:</label>
                <form:input path="title" id="title" />
                <form:errors path="title" cssClass="error" />
            </div>
            
            <div class="form-group">
                <label for="isbn">ISBN:</label>
                <form:input path="isbn" id="isbn" />
                <form:errors path="isbn" cssClass="error" />
            </div>
            
            <div class="form-group">
                <label for="author">Author:</label>
                <form:select path="author" id="author">
                    <form:option value="" label="-- Select Author --" />
                    <form:options items="${authors}" itemValue="id" itemLabel="name" />
                </form:select>
                <form:errors path="author" cssClass="error" />
            </div>
            
            <button type="submit" class="btn">${book.id != null ? 'Update Book' : 'Save Book'}</button>
            <a href="/books" class="btn btn-secondary">Cancel</a>
        </form:form>
    </div>
</body>
</html>
