# 🔐 JWT Spring Demo

[![Java](https://img.shields.io/badge/Java-17-blue)](https://www.oracle.com/java/)
[![Spring Boot](https://img.shields.io/badge/Spring%20Boot-3.0-brightgreen)](https://spring.io/projects/spring-boot)
[![License](https://img.shields.io/badge/License-MIT-yellow.svg)](LICENSE)
[![Build](https://img.shields.io/badge/Build-Maven-success)](https://maven.apache.org/)

A secure and minimal Spring Boot application demonstrating **JWT (JSON Web Token) authentication and authorization** with role-based access control using Spring Security.

---

## ✨ Features

- ✅ Stateless JWT-based login system
- ✅ Secure password hashing (BCrypt)
- ✅ Custom `UserDetailsService` for DB authentication
- ✅ Role-based access control (`ROLE_USER`, `ROLE_ADMIN`)
- ✅ Token validation, parsing, and expiration handling
- ✅ Secure API endpoints with access restrictions
- ✅ Custom Security Filter for token processing

---

## ⚙️ Tech Stack

- **Java 17+**
- **Spring Boot**
- **Spring Security**
- **JWT (io.jsonwebtoken / JJWT)**
- **Maven**
- **H2 / MySQL** (any JPA-supported DB)
- **Postman** for testing

---

## 📁 Project Structure

JWT_Spring_Demo/                                                       
├── controller/ # API endpoints (login, test)                               
├── jwtconfig/ # JWT utility, filter, config                        
├── model/ # Entity classes (User, Role)                                                
├── repository/ # JPA repositories                              
├── service/ # UserDetailsService implementation                              
├── SecurityDemoApplication.java                           
└── application.properties                         
  


## 🚀 Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/Purav22/JWT_Spring_Demo.git
cd JWT_Spring_Demo
2. Configure the Application
In src/main/resources/application.properties:

properties
Copy
Edit
# JWT Settings
jwt.secret=your-256-bit-secret-key-here
jwt.expiration=86400000  # 1 day in milliseconds

# DB Config (example for H2)
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=
spring.jpa.hibernate.ddl-auto=update
💡 For MySQL, change the DB URL, driver, username/password accordingly.





📬 API Endpoints
Method	Endpoint	           Description                Auth Required
POST    /api/auth/login      Login and receive JWT          ❌ No
POST    /api/auth/register   Register new user              ❌ No
GET     /api/user/profile    Get user profile               ✅ Yes
GET     /api/user/admin      Access admin-only endpoint     ✅ Yes (ADMIN)

🔑 Use JWT in the request header as Authorization: Bearer <token>
