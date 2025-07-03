# Library API

A simple Spring Boot-based REST API for managing books and borrowers.

## Features
- Register borrowers
- Register books
- Borrow and return books
- View available books

## Getting Started

### Run with Docker Compose
```bash
docker-compose up --build
```

### API Docs
Visit [http://localhost:8080/swagger-ui.html](http://localhost:8080/swagger-ui.html)

##How to run: 
USING Docker::: docker-compose up --build

Exposed port: 8080

H2 console: /h2-console at localhost:8080/h2-console ::: http://localhost:8080/h2-console/

URLs to try for testing once the application starts running ::

1. To get all books:: (GET) http://localhost:8080/api/books   

2. Register a borrower:: (POST) http://localhost:8080/api/borrowers  
[use data as raw and json format 
Sample data :::: {
    "name":"ARYA",
    "email":"aryaveer@gmail.com"
}]

3.To add new book:: (POST) http://localhost:8080/api/books
[Select data as RAW, Json as format
{
    "isbn":"978-123450",
    "title":"Microservices Trends",
    "author":"GVMR",
    "isborrowed":"false"
}]

4. Borrow a book :: (POST) http://localhost:8080/api/borrow/1?borrowerId=1

5. Return a book :: (POST) http://localhost:8080/api/return/1


