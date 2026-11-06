BAN-KAI INFINITY
Financial Transaction Fraud Detection System
BAN-KAI INFINITY is a Java Spring Boot application designed to identify potentially suspicious financial transactions.

The system receives transaction information, checks it against a set of fraud detection rules, assigns a risk severity, and stores the transaction in a PostgreSQL database.

This project demonstrates backend development, database integration, REST API development, automated testing and Docker containerisation.

What Does It Do?
When a transaction is submitted, BAN-KAI INFINITY checks for several indicators of potentially fraudulent activity:

High-value transactions: Transactions of R10,000 or more.
High-risk categories: Transactions involving categories such as gambling, cryptocurrency and cash advances.
Rapid transactions: Multiple transactions from the same customer within five minutes.
Duplicate transactions: Transactions with matching customer, amount and category within five minutes.
If a rule is triggered, the transaction is flagged as suspicious and assigned a risk severity.

Suspicious transactions are still stored in the database so they can be reviewed later.

Risk Severity
Severity

Description

LOW

No fraud detection rules were triggered.

MEDIUM

A high-risk transaction category was identified.

HIGH

A high-value, rapid or duplicate transaction was identified.

Technologies Used
Java – Main programming language.
Spring Boot – Backend application framework.
Spring Data JPA – Database interaction.
PostgreSQL – Relational database.
Maven – Dependency management and build tool.
Docker & Docker Compose – Application containerisation.
JUnit & Mockito – Automated testing.
Swagger / OpenAPI – API documentation.
Postman – API testing.
API Endpoints
The application provides the following REST API endpoints:

Method

Endpoint

Description

POST

/api/transactions

Submit and analyse a transaction.

GET

/api/transactions

Retrieve all transactions.

GET

/api/transactions/{transactionId}

Retrieve a transaction by ID.

Running the Application
Requirements
Java 21
Docker Desktop
Git
Clone the Repository
git clone https://github.com/Straw-Hat-Lucian/ban-kai-infinity.git
cd ban-kai-infinity
Configure Environment Variables
Create a .env file in the project root:

DB_USERNAME=postgres
DB_PASSWORD=your_database_password
Note: Replace your_database_password with your PostgreSQL password. Do not upload your .env file to GitHub.

Start Using Docker
Build and start the application:

docker compose up -d --build
Check that the containers are running:

docker compose ps
API Documentation
Once the application is running, open Swagger UI:

http://localhost:8080/swagger-ui/index.html

Swagger allows you to view the available endpoints, submit transactions and test the API.

You can also use Postman to send requests and inspect responses.

Running Tests
To run the automated tests:

Windows:

.\mvnw.cmd clean test
Linux / macOS:

./mvnw clean test
A successful build should display:

BUILD SUCCESS
Project Structure
src/
├── main/
│   └── java/
│       └── com/example/fraudruleengine/
│           ├── Transaction.java
│           ├── TransactionController.java
│           ├── FraudDetectionService.java
│           ├── TransactionRepository.java
│           ├── FraudSeverity.java
│           ├── FraudResult.java
│           └── GlobalExceptionHandler.java
│
└── test/
└── java/
└── com/example/fraudruleengine/
└── FraudDetectionServiceTest.java
Author
BAN-KAI INFINITY

A backend development project focused on financial transaction monitoring and fraud detection.

GitHub Repository:
https://github.com/Straw-Hat-Lucian/ban-kai-infinity

Detect. Analyse. Protect.