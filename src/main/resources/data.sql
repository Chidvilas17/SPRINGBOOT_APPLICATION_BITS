-- Populate Authors (10 rows)
INSERT INTO authors (name, email) VALUES ('J.K. Rowling', 'jk@example.com');
INSERT INTO authors (name, email) VALUES ('George R.R. Martin', 'george@example.com');
INSERT INTO authors (name, email) VALUES ('J.R.R. Tolkien', 'tolkien@example.com');
INSERT INTO authors (name, email) VALUES ('Agatha Christie', 'agatha@example.com');
INSERT INTO authors (name, email) VALUES ('Stephen King', 'stephen@example.com');
INSERT INTO authors (name, email) VALUES ('Dan Brown', 'dan@example.com');
INSERT INTO authors (name, email) VALUES ('Isaac Asimov', 'isaac@example.com');
INSERT INTO authors (name, email) VALUES ('Arthur C. Clarke', 'arthur@example.com');
INSERT INTO authors (name, email) VALUES ('Jane Austen', 'jane@example.com');
INSERT INTO authors (name, email) VALUES ('Charles Dickens', 'charles@example.com');

-- Populate Books (10 rows)
-- J.K. Rowling (ID: 1)
INSERT INTO books (title, isbn, author_id) VALUES ('Harry Potter and the Sorcerer''s Stone', '978-0439708180', 1);
INSERT INTO books (title, isbn, author_id) VALUES ('Harry Potter and the Chamber of Secrets', '978-0439064873', 1);
-- George R.R. Martin (ID: 2)
INSERT INTO books (title, isbn, author_id) VALUES ('A Game of Thrones', '978-0553103540', 2);
-- J.R.R. Tolkien (ID: 3)
INSERT INTO books (title, isbn, author_id) VALUES ('The Hobbit', '978-0547928227', 3);
INSERT INTO books (title, isbn, author_id) VALUES ('The Fellowship of the Ring', '978-0618346257', 3);
-- Agatha Christie (ID: 4)
INSERT INTO books (title, isbn, author_id) VALUES ('And Then There Were None', '978-0312330523', 4);
-- Stephen King (ID: 5)
INSERT INTO books (title, isbn, author_id) VALUES ('The Shining', '978-0385121675', 5);
-- Dan Brown (ID: 6)
INSERT INTO books (title, isbn, author_id) VALUES ('The Da Vinci Code', '978-0385504205', 6);
-- Isaac Asimov (ID: 7)
INSERT INTO books (title, isbn, author_id) VALUES ('Foundation', '978-0553293357', 7);
-- Jane Austen (ID: 9)
INSERT INTO books (title, isbn, author_id) VALUES ('Pride and Prejudice', '978-0141439518', 9);
