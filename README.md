<h1 align="center">Welcome to N26 Coding Challenge</h1>
> This is RESTful API made for statistics. The main use case for the API is to calculate real-time statistics for the last 60 seconds of transactions.

## 💻 Tech stack

- **Backend**
	- [Java](https://www.java.com/en/download)
	- [Spring Boot](https://spring.io/projects/spring-boot)

## 🧱 Project structure

### **REST API endpoints**

- **POST /transactions** - This endpoint is called to create a new transaction.
- **GET /statistics** - This endpoint returns the statistics computed on the transactions within the last 60 seconds.
- **DELETE /transactions** - This endpoint causes all existing transactions to be deleted
