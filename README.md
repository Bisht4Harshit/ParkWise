# 🚗 ParkWise - Parking Management System

A secure backend REST API for managing parking lots, vehicles, and bookings. ParkWise allows users to book parking slots while enabling parking lot owners to manage their parking facilities efficiently.

---

## 📌 Features

### 🔐 Authentication & Authorization
- User Registration & Login
- JWT Authentication
- Spring Security
- BCrypt Password Encryption
- Role-Based Authorization (USER, OWNER, ADMIN)

### 🚙 Vehicle Management
- Add Vehicle
- View My Vehicles
- Update Vehicle
- Delete Vehicle

### 🅿️ Parking Lot Management
- Owners can create parking lots
- Update parking lot details
- Delete parking lots
- View owned parking lots
- Automatic parking slot generation

### 📅 Booking Management
- Book a parking slot
- View booking history
- Cancel booking
- Complete booking
- Automatic slot status updates

### ⚙️ Business Logic
- Vehicle ownership validation
- Parking lot ownership validation
- Slot availability checking
- Prevent booking occupied slots
- Automatic slot release after cancellation/completion

---

## 🛠 Tech Stack

- Java 21
- Spring Boot
- Spring Security
- JWT
- Hibernate / JPA
- MySQL
- Maven
- Lombok

---

## 📂 Project Structure

```
src
 ├── config
 ├── controller
 ├── dto
 │     ├── request
 │     └── response
 ├── entity
 ├── enums
 ├── repository
 ├── security
 ├── service
 └── ParkWiseApplication
```

---

## 🗃 Database Design

### Entities

- User
- Vehicle
- ParkingLot
- ParkingSlot
- Booking
- Payment *(Future Enhancement)*

---

## 🔄 Booking Workflow

```
User Login
     │
     ▼
Select Vehicle
     │
     ▼
Choose Parking Slot
     │
     ▼
Create Booking
     │
     ▼
Slot Status → OCCUPIED
     │
 ┌───┴───────────┐
 ▼               ▼
Cancel       Complete
 │               │
 ▼               ▼
AVAILABLE    AVAILABLE
```

---

## 🔐 Security

- JWT Authentication
- Stateless Session Management
- BCrypt Password Encoding
- Role-Based Access Control
- Protected REST APIs

---

## 🚀 Running the Project

### Clone Repository

```bash
git clone https://github.com/Bisht4Harshit/ParkWise.git
```

### Configure Database

Update `application.properties`

```properties
spring.datasource.url=jdbc:mysql://localhost:3306/parkwise
spring.datasource.username=your_username
spring.datasource.password=your_password
```

### Run

```bash
mvn spring-boot:run
```

---

## 📌 Future Enhancements

- Payment Gateway Integration
- QR Code Based Entry & Exit
- Email Notifications
- Parking Fee Calculation
- Swagger/OpenAPI Documentation
- Dashboard & Analytics

---

## 👨‍💻 Author

**Harshit Bisht**

GitHub: https://github.com/Bisht4Harshit
