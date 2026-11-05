BAN-KAI INFINITY
---
Financial Transaction Intelligence, Fraud Detection & Risk Analysis Platform
Intelligent Transaction Monitoring | Automated Fraud Detection | Risk Classification
-------
1. Introduction
BAN-KAI INFINITY is a financial transaction intelligence and fraud detection platform developed using Java and Spring Boot.

The system is designed to analyse financial transactions, identify potentially fraudulent activity and classify suspicious transactions according to predefined detection rules.

By evaluating transaction values, customer activity, transaction categories and duplicate transaction patterns, BAN-KAI INFINITY provides a structured approach to identifying financial risk.

The application exposes RESTful API endpoints, integrates with a PostgreSQL database for persistent storage, and provides interactive API documentation through Swagger UI.

The project also incorporates Docker containerisation, automated testing and global exception handling to support a structured and maintainable backend application.

2. Core Capabilities
   Feature
--------------
Description

Transaction Processing

Accepts and processes financial transaction events through REST APIs.

High-Value Detection

Identifies transactions that meet or exceed a predefined financial threshold.

High-Risk Categories

Flags transactions associated with predefined high-risk categories.

Rapid Transaction Detection

Identifies multiple transactions from the same customer within a short period.

Duplicate Detection

Identifies transactions with matching customer IDs, amounts and categories within a defined time window.

Risk Classification

Assigns LOW, MEDIUM or HIGH severity levels.

Database Integration

Stores and retrieves transaction records using PostgreSQL.

API Documentation

Provides interactive endpoint documentation through Swagger UI.

Exception Handling

Handles invalid input and database integrity errors.

Containerisation

Supports deployment using Docker and Docker Compose.

3. Technology Stack
   Backend Development
---------------
Java 21
Spring Boot
Spring Web
Spring Data JPA
Database & Persistence

PostgreSQL
Hibernate / JPA
Build & Dependency Management

Apache Maven
Maven Wrapper
Testing

JUnit
Mockito
API Documentation

Swagger UI
OpenAPI
Containerisation & Development Tools

Docker
Docker Compose
IntelliJ IDEA
Git & GitHub
4. Fraud Detection Engine
   The core functionality of BAN-KAI INFINITY is its fraud detection engine, which evaluates incoming transactions against multiple detection criteria.
------------------------------
4.1 High-Value Transaction Detection
Transactions with an amount equal to or greater than R10,000.00 are flagged as suspicious.

Detection threshold: R10,000.00

Severity: HIGH

4.2 High-Risk Transaction Categories
Transactions belonging to predefined high-risk categories are flagged for additional attention.

Supported categories:

GAMBLING
CRYPTO
CASH_ADVANCE
Severity: MEDIUM

4.3 Rapid Transaction Detection
The system evaluates recent transaction activity associated with a customer.

Multiple transactions from the same customer within a five-minute period can trigger a fraud alert.

Severity: HIGH

4.4 Duplicate Transaction Detection
The system identifies potentially duplicated transactions by evaluating:

Customer ID
Transaction amount
Transaction category
Transaction timestamp
Matching transactions within the defined five-minute period trigger a fraud alert.

Severity: HIGH

4.5 Risk Severity Classification
Every processed transaction receives a severity classification.

Severity

Classification

LOW

No fraud detection rule triggered.

MEDIUM

A high-risk transaction category was detected.

HIGH

A high-value, rapid or duplicate transaction was detected.

Suspicious transactions are recorded and returned through the API with their corresponding fraud detection reason and severity level.

5. System Architecture
   BAN-KAI INFINITY follows a layered backend architecture that separates application responsibilities.
------------------
                 CLIENT / API REQUEST
                         |
                         v
                 TRANSACTION CONTROLLER
                         |
                         v
                 FRAUD DETECTION SERVICE
                         |
              +----------+----------+
              |          |          |
              v          v          v
           HIGH-VALUE  HIGH-RISK  DUPLICATE /
           DETECTION   CATEGORY   RAPID DETECTION
                       DETECTION
                         |
                         v
                  RISK CLASSIFICATION
                         |
                         v
                 TRANSACTION REPOSITORY
                         |
                         v
                    POSTGRESQL
Application Layers
Controller Layer

Receives HTTP requests, validates incoming transaction data and returns appropriate API responses.

Service Layer

Contains the business logic responsible for evaluating transactions, applying fraud detection rules and determining risk severity.

Repository Layer

Uses Spring Data JPA to communicate with the PostgreSQL database.

Entity Layer

Defines the transaction data structure and its database representation.

Exception Handling Layer

Provides centralised handling of validation errors and database integrity exceptions.

6. Database Design
   BAN-KAI INFINITY uses PostgreSQL as its relational database management system.
--------------
Transaction records are persisted to support retrieval and transaction analysis.

Transaction Data
The transaction entity contains the following information:

Field

Description

transactionId

Unique transaction identifier.

customerId

Identifier of the customer initiating the transaction.

amount

Financial transaction amount.

category

Transaction category.

suspicious

Indicates whether the transaction triggered a fraud rule.

fraudReason

Describes the fraud detection rule triggered.

severity

Assigned transaction risk classification.

createdAt

Timestamp indicating when the transaction was created.

The application uses Spring Data JPA for database operations and transaction persistence.

7. REST API Documentation
   The application exposes RESTful endpoints for transaction submission and retrieval.
--------------
Base URL
http://localhost:8080
Available Endpoints
HTTP Method

Endpoint

Function

POST

/api/transactions

Submit and evaluate a transaction.

GET

/api/transactions

Retrieve all stored transactions.

GET

/api/transactions/{transactionId}

Retrieve a specific transaction.

Example: Submit a Transaction
Request

POST /api/transactions
Content-Type: application/json
Request Body

{
"transactionId": "TXN001",
"customerId": "CUSTOMER001",
"amount": 15000,
"category": "TRANSFER"
}
Example Response

{
"transactionId": "TXN001",
"amount": 15000,
"suspicious": true,
"reason": "HIGH_VALUE_TRANSACTION",
"severity": "HIGH"
}
Swagger UI
Interactive API documentation is available through Swagger UI.

Once the application is running, navigate to:

http://localhost:8080/swagger-ui/index.html

Swagger allows developers to explore available endpoints, submit requests and inspect API responses.

8. Installation & Setup
   Prerequisites
   Before running the application, ensure the following are installed:
-----------------
Java 21 or compatible configured JDK.
IntelliJ IDEA or another Java IDE.
Docker Desktop (for containerised execution).
Git.
For local execution without Docker, PostgreSQL must also be installed and running.

Clone the Repository
git clone https://github.com/Straw-Hat-Lucian/fraud-rule-engine.git
Navigate to the project directory:

cd fraud-rule-engine
9. Running with Docker
   BAN-KAI INFINITY supports Docker Compose to simplify application deployment and database management.
---------------
Docker Compose manages two primary services:

Application Container: Runs the Spring Boot application.
Database Container: Runs PostgreSQL.
Step 1: Configure Environment Variables
Create a .env file in the project root directory.

Add your PostgreSQL credentials:

DB_USERNAME=postgres
DB_PASSWORD=your_postgresql_password
Replace the example password with your own database password.

Step 2: Build and Start the Application
Run:

docker compose up --build -d
This builds the application image and starts the application and database containers.

Step 3: Verify Container Status
docker compose ps
Both application and database containers should be running.

Step 4: View Application Logs
docker compose logs app
Step 5: Stop the Application
docker compose down
Database Persistence
PostgreSQL uses a named Docker volume to preserve database data between container restarts.

Important: Avoid using docker compose down -v unless you intentionally want to delete the persistent database volume and its stored data.

10. Running Locally Through IntelliJ IDEA
    The application can also be executed directly through IntelliJ IDEA.
-------------
Clone or open the project in IntelliJ IDEA.
Ensure the required Java SDK is configured.
Start PostgreSQL.
Configure the database connection in application.properties.
Build the project using Maven.
Locate FraudRuleEngineApplication.java.
Run the application.
The Spring Boot application starts on port 8080 by default.

11. Automated Testing
    BAN-KAI INFINITY includes automated unit tests using JUnit and Mockito.
-----------------
The tests evaluate the fraud detection service under different transaction scenarios.

Test Coverage
Normal transaction processing.
High-value transaction detection.
High-risk category detection.
Rapid transaction detection.
Duplicate transaction detection.
Execute Tests
Windows PowerShell

.\mvnw.cmd test
Linux / macOS

./mvnw test
A successful test execution should conclude with:

BUILD SUCCESS
12. Validation & Exception Handling
    The application implements request validation and centralised exception handling to improve API reliability.
-------------
Examples of handled HTTP responses include:

HTTP Status

Description

200 OK

Transaction successfully processed.

400 Bad Request

Invalid or incomplete transaction data.

404 Not Found

Requested transaction could not be found.

409 Conflict

Database integrity conflict.

Fraud detection alerts do not automatically reject transactions. Suspicious transactions are processed, recorded and returned with their corresponding risk information.

13. Security Considerations
    The project incorporates basic security-conscious development practices:
-------------
Environment variables are used to configure database credentials for Docker Compose.
The .env file is excluded through .gitignore.
The .dockerignore file excludes unnecessary files and sensitive local configuration from the Docker build context.
Database credentials should never be committed to GitHub.
Request validation is applied to incoming transaction data.
Note: The current implementation is a development-stage application and does not yet include production authentication, authorisation or advanced secrets management.

14. Potential Future Enhancements
    BAN-KAI INFINITY provides a foundation for further financial technology development.
------------
Potential enhancements include:

Configurable fraud detection thresholds.
Advanced customer transaction behaviour analysis.
Machine learning-based fraud detection.
Real-time transaction monitoring.
Fraud analytics and reporting dashboard.
Authentication and role-based access control.
Audit logging and transaction history.
Advanced risk scoring algorithms.
Integration with external financial transaction services.
15. Project Information
    Project Name: BAN-KAI INFINITY
---------------
Category: Financial Technology / Backend Development

Primary Language: Java

Framework: Spring Boot

Database: PostgreSQL

Deployment: Docker & Docker Compose

Repository: GitHub – BAN-KAI INFINITY


BAN-KAI INFINITY
Financial Transaction Intelligence. Fraud Detection. Risk Analysis.

Built with Java, Spring Boot, PostgreSQL and Docker.

