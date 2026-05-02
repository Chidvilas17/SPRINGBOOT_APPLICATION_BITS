# Spring Boot Assignment Report

## 1. Introduction
This project is a Library Management System built using Spring Boot. It handles information for two entities: `Author` and `Book`. The application provides operations to Create, Read, and Update data for these entities through a web interface built with JSP and styled with custom CSS.

## 2. Entity Relationship Design

### Entities
*   **Author**: Represents an author of books. Contains `id`, `name`, and `email`.
*   **Book**: Represents a book written by an author. Contains `id`, `title`, `isbn`, and a reference to the `Author`.

### Relationship
The relationship between `Author` and `Book` is a **One-to-Many** relationship. One Author can write multiple Books, but each Book is associated with only one Author.
*   In the `Author` entity: `@OneToMany(mappedBy = "author", cascade = CascadeType.ALL, orphanRemoval = true)`
*   In the `Book` entity: `@ManyToOne` with `@JoinColumn(name = "author_id", nullable = false)`

## 3. Implementation Details

### a) Populate Database
*   **JPA and H2 Database:** We utilized an in-memory H2 database for testing and demonstration purposes. Entities were annotated with JPA annotations (`@Entity`, `@Table`, `@Id`, etc.) which auto-generated the schema.
*   **Pre-population:** A `data.sql` script is located in `src/main/resources/`. It executes on application startup and inserts 10 sample rows for Authors and 10 sample rows for Books.

### b) Create Operation
*   **JSP Form:** Forms were created (`author-form.jsp` and `book-form.jsp`) using Spring's form tag library to bind fields securely.
*   **Controller Method:** The `LibraryController` handles POST requests mapped to `/authors/add` and `/books/add`. It validates the input using the `@Valid` annotation.
*   **Exception Handling:** In the controller, we catch `DataIntegrityViolationException` to gracefully handle cases such as duplicate ISBNs or emails, displaying an appropriate error message to the user instead of a raw stack trace.

### c) Read Operation
*   **JSP View:** `authors.jsp` and `books.jsp` iterate over the list of entities using JSTL `<c:forEach>` and display them in a styled table.
*   **Controller Binding:** The `LibraryController` fetches lists from the `LibraryService` and adds them to the model before returning the view name.
*   **Custom Query with Inner Join:** Inside the `BookRepository`, a custom query method `findAllBooksWithAuthors()` uses `@Query("SELECT b FROM Book b JOIN FETCH b.author a")` to fetch books along with their associated authors efficiently, avoiding the N+1 select problem.

### d) Update Operation
*   **Update Form:** An edit button in the books list navigates to `/books/edit/{id}`, pre-filling the existing `book-form.jsp` with the selected book's data.
*   **Update Controller:** The POST method handles updating the book. It preserves the existing ID and overrides the data using the `LibraryService` layer.

### e) Layers Architecture
*   **Controller:** Maps endpoints and binds View to Model.
*   **Service:** Business logic layer that delegates calls to Repositories.
*   **Repository:** Interfaces extending `JpaRepository` providing CRUD operations and custom queries.
*   **View:** JSP files configured via `application.properties`.

## 4. Challenges Faced & Solutions

1.  **Unique Constraint Violations:**
    *   *Challenge:* Initially, adding duplicate authors or books with the same email/ISBN threw an unhandled 500 error page.
    *   *Solution:* We caught `DataIntegrityViolationException` in the Controller layer and returned a friendly `errorMessage` attribute to the view to notify the user.
2.  **JSP and Spring Boot Integration:**
    *   *Challenge:* Spring Boot doesn't natively support JSP in executable JARs easily.
    *   *Solution:* We changed the packaging to `war` in `pom.xml`, included the `tomcat-embed-jasper` dependency, and added correct view resolver configurations in `application.properties`.
3.  **LazyInitializationException:**
    *   *Challenge:* Accessing an author's name from a book object in the view sometimes causes a session exception if fetched lazily.
    *   *Solution:* We used the `JOIN FETCH` clause in our custom JPQL query for retrieving Books to eagerly load the Author data during the initial database hit.

## 5. Github URL
The project source code is available at: [https://github.com/Chidvilas17/SPRINGBOOT_APPLICATION_BITS](https://github.com/Chidvilas17/SPRINGBOOT_APPLICATION_BITS)
