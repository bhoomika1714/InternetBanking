


# Internet Banking Application

A **full-stack Internet Banking application** built with **Spring Boot**, **MongoDB**, and **Thymeleaf**. This project simulates real-world banking operations, including **account management, OTP-based authentication, email notifications, and balance overview**.

---

##  Technologies Used
- **Backend:** Java, Spring Boot
- **Database:** MongoDB
- **Frontend:** Thymeleaf (server-side rendering)
- **Email & OTP Verification:** SendGrid, Twilio
- **Build Tool:** Maven
- **Version Control:** Git & GitHub

---

##  Key Features
- **User Registration & Login**
  - OTP-based login and verification
  - Secure profile management
- **Account Management**
  - View linked bank accounts
  - Check account balances
- **Transactions**
  - Simulate transfers between accounts
  - Transaction history overview
- **Email Notifications**
  - Send OTPs and alerts via SendGrid
- **Spring Boot & MongoDB Integration**
  - Fully functional backend with REST APIs
  - MongoDB collections for Users, Accounts, Transactions, and OTPs

---

## 📂 Project Structure
```

Internet-Banking/
│
├── src/main/java/com/bankingapp/
│   ├── controller/       # REST controllers
│   ├── service/          # Business logic
│   ├── model/            # Data models
│   ├── repository/       # MongoDB repositories
│   └── InternetBankingApplication.java
│
├── src/main/resources/
│   └── application.yml    # Spring Boot configuration
│
├── pom.xml               # Maven configuration
└── README.md             # Project documentation

````

---

##  How to Run Locally
1. Clone the repository:
```bash
git clone https://github.com/bhoomika1714/InternetBanking.git
````

2. Navigate to the project directory:

```bash
cd Internet-Banking
```

3. Install dependencies and run:

```bash
mvn spring-boot:run
```

4. Open your browser at `http://localhost:8080`

---

##  Learning Outcomes

* Hands-on experience with **Spring Boot & MongoDB**
* Implemented **OTP authentication and email notifications**
* Developed a **secure banking backend**
* Practiced **REST API design** and server-side rendering with **Thymeleaf**
* Managed **project version control** using Git & GitHub

---

##  Future Enhancements

* Integration with real banking APIs
* Add **QR code payments**
* Implement **transaction analytics and budgeting tools**
* Enhance security with **BCrypt password encoding** and **JWT authentication**

```

