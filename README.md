# SecureDesk

### Role-Based IT Support Ticket Management API

SecureDesk is a Spring Boot REST API for managing IT support tickets with **Spring Security authentication, role-based authorization, BCrypt, JPA, DTOs, validation, CRUD operations, native SQL, and global exception handling**.

---

## ◈ Tech Stack

- **Java 21**
- **Spring Boot 4.1.1**
- **Spring Security**
- **Spring Data JPA / Hibernate**
- **MySQL**
- **Maven**
- **Lombok**
- **Bean Validation**

---

## ◈ Key Features

- Authentication with **Spring Security + HTTP Basic**
- **BCrypt** password hashing
- Custom `UserDetailsService` and `UserPrincipal`
- Role-based authorization: `USER` / `ADMIN`
- Ticket **CRUD operations**
- `User` → `Ticket` JPA relationship
- Request/response **DTOs**
- Bean Validation
- Centralized exception handling
- Native SQL query for active tickets
- Layered **Controller → Service → Repository** architecture

---

## ◈ Security Flow

```text
HTTP Request
     ↓
Spring Security
     ↓
DaoAuthenticationProvider
     ↓
UserDetailsService
     ↓
UserRepository → MySQL
     ↓
UserPrincipal
     ↓
BCrypt Verification
     ↓
Role-Based Authorization
     ↓
Controller
```

---

## ◈ API

| Method | Endpoint | Access |
|---|---|---|
| `POST` | `/SecureDesk/users/register` | Public |
| `GET` | `/SecureDesk/home` | Public |
| `GET` | `/SecureDesk/welcomeHome` | Authenticated |
| `POST` | `/SecureDesk/ticket` | USER |
| `GET` | `/SecureDesk/ticket/{id}` | Authenticated |
| `PUT` | `/SecureDesk/ticket/{id}` | USER |
| `DELETE` | `/SecureDesk/ticket/{id}` | USER |
| `GET` | `/SecureDesk/tickets` | ADMIN |
| `GET` | `/SecureDesk/tickets/active` | ADMIN |
| `PUT` | `/SecureDesk/ticket/{id}/status` | ADMIN |

---

## ◈ Project Structure

```text
controller
dto
entity
globalException
repository
security
service
```

---

## ◈ Database Relationship

```text
User 1 ─────────── * Ticket
```

A user can create multiple support tickets, while each ticket belongs to a user.

---

## ◈ Running Locally

```bash
git clone https://github.com/karl2603/SecureDesk.git
cd SecureDesk
mvn spring-boot:run
```

Create a MySQL database and configure the credentials in `application.properties`.