<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Library Management System</title>
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
        <h2>Welcome to the Library Management System</h2>
        <p>This is a Spring Boot application built for managing Books and Authors. Use the navigation links above to view, add, or update data.</p>
        
        <div style="margin-top: 30px;">
            <h3>Quick Links</h3>
            <ul>
                <li><a href="/authors">View All Authors</a></li>
                <li><a href="/books">View All Books</a></li>
                <li><a href="/authors/add">Add New Author</a></li>
                <li><a href="/books/add">Add New Book</a></li>
            </ul>
        </div>
    </div>
</body>
</html>
