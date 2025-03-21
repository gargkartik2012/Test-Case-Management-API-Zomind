# **Test-Case-Management-API-Zomind**

A Spring Boot REST API for managing test cases, supporting **CRUD operations**, **MongoDB storage**, **pagination & filtering**, and **Swagger API documentation**.

---

## **📌 Features**
✔ **CRUD Operations** – Create, Read, Update, Delete test cases  
✔ **MongoDB Integration** – Stores test cases efficiently  
✔ **Pagination & Filtering** – Retrieve test cases by status & priority  
✔ **Validation & Exception Handling** – Ensures valid input and clear error messages  
✔ **Swagger Documentation** – Easily explore APIs  
✔ **Unit & Integration Tests** – JUnit & Mockito  
✔ **Logging with SLF4J** – Debugging made easy  

---

## **🛠 Tech Stack**
- **Backend:** Java 21, Spring Boot 3.2.2  
- **Database:** MongoDB (Spring Data MongoDB)  
- **Build Tool:** Maven  
- **Testing:** JUnit 5, Mockito  
- **API Documentation:** Springdoc OpenAPI (Swagger)  
- **Logging:** SLF4J + Logback  

---

## **🚀 API Endpoints**

### **Test Case Management**
| Method   | Endpoint                  | Description |
|----------|---------------------------|-------------|
| **GET**  | `/api/testcases`           | Retrieve all test cases (supports pagination & filtering) |
| **GET**  | `/api/testcases/{id}`      | Retrieve a single test case by ID |
| **POST** | `/api/testcases`           | Create a new test case |
| **PUT**  | `/api/testcases/{id}`      | Update an existing test case |
| **DELETE** | `/api/testcases/{id}`    | Delete a test case |

### **Query Parameters for Filtering**
| Parameter  | Description | Example |
|------------|-------------|---------|
| `status`   | Filter by status (`PENDING`, `IN_PROGRESS`, `PASSED`, `FAILED`) | `/api/testcases?status=PASSED` |
| `priority` | Filter by priority (`LOW`, `MEDIUM`, `HIGH`) | `/api/testcases?priority=HIGH` |
| `page`     | Page number (default = 0) | `/api/testcases?page=1&size=5` |
| `size`     | Number of records per page (default = 10) | `/api/testcases?page=0&size=5` |

---

## **📝 Test Case Model**
```json
{
  "title": "Login Test",
  "description": "Verify login functionality",
  "status": "PENDING",
  "priority": "HIGH"
}




