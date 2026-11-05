
BAN-KAI INFINITY

Financial Transaction Intelligence, Fraud Detection & Risk Analysis Platform
Intelligent Transaction Monitoring | Automated Fraud Detection | Risk Classification

1. Introduction

BAN-KAI INFINITY is a financial platform built with Java 21 and Spring Boot. It is designed to automatically scan financial transactions in real-time, flag suspicious activities, and rate them by risk level.
The system uses rules to check transaction amounts, how fast a user is making transactions (velocity), high-risk categories, and accidental double-payments (duplicates). It saves all records to a PostgreSQL database and includes an interactive webpage (Swagger UI) where you can test the system's endpoints directly.

Main Highlights

• Web API Ready: Ready to receive transaction data from other apps or front-end screens.
• Database Protected: Saves a permanent audit history using a PostgreSQL database.
• Easy Testing: Includes a visual webpage layout to check how your endpoints react.
• Docker Setup: Run the entire backend engine and database together using just one terminal command.

2. Core Capabilities


Feature	Simple Description
Transaction Processing	Takes in financial transaction data securely via REST web links.
High-Value Detection	Flags any transaction that crosses a specific monetary limit.
High-Risk Categories	Instantly flags activities involving crypto, gambling, or cash advances.
Rapid Activity Check	Alerts you if a customer sends multiple transactions in a very short time.
Duplicate Prevention	Flags double-submitting errors (same ID, price, and type within minutes).
Risk Levels	Labels transactions as LOW, MEDIUM, or HIGH risk.
Database Storage	Permanently records all processed transactions to a secure database history.
API Web Documentation	Generates an interactive web guide (Swagger UI) for developers.
Error Handling	Catches typing mistakes or database bugs cleanly without crashing.
Containerisation	Packs the code up so it boots identically on any machine with Docker.


3. Technology Stack

• Language & Core: Java 21, Spring Boot (Spring Web, Spring Data JPA)
• Database System: PostgreSQL, Hibernate
• Dependency Manager: Apache Maven (with Maven Wrapper)
• Code Testing: JUnit, Mockito
• Visual Documentation: Swagger UI, OpenAPI 3
• Environment Tools: Docker, Docker Compose, IntelliJ IDEA, Git

4. Fraud Detection Engine

The system acts like a real-time filter. When a new transaction arrives, it passes through four distinct checks before receiving its final risk classification.

4.1 High-Value Transactions

Any transaction equal to or greater than R10,000.00 triggers this rule.
• Trigger Limit: ≥ R10,000.00
• Risk Assigned: HIGH

4.2 High-Risk Industries

Transactions checking into industries prone to chargebacks or quick money moves are tagged immediately.
• Monitored Sectors: GAMBLING, CRYPTO, CASH_ADVANCE
• Risk Assigned: MEDIUM

4.3 Rapid Transaction Spams (Velocity)

The software watches user action frequency. Multiple actions sent by the same user within minutes are intercepted.
• Time Check Window: 5 Minutes
• Risk Assigned: HIGH

4.4 Duplicate Payments

Protects users from double-clicking an payment button by looking for identical values hitting the engine simultaneously.
• Attributes Inspected: Same Customer ID, Same Amount, Same Category
• Time Check Window: 5 Minutes
• Risk Assigned: HIGH

4.5 Severity Flow Matrix

text
[ Incoming Transaction ]
|
+---> Matches High-Value, Rapid, or Duplicate? ---> HIGH RISK
|
+---> Matches High-Risk Category Only? ----------> MEDIUM RISK
|
+---> Matches No Rules? --------------------------> LOW RISK
Use code with caution.
Important Rule Logic: Suspicious transactions are not rejected or blocked from completing. They are safely recorded, labeled with the exact failure reason, and stored in the database so an analyst can look them over later.

5. System Architecture

BAN-KAI INFINITY splits up tasks into specific code layers so the system stays reliable and easy to update:
text
+-------------------------------------------------+

|               CLIENT / API REQUEST              |
+-------------------------------------------------+
|
v
+-------------------------------------------------+

|               CONTROLLER LAYER                  |
|       (Checks inputs for typing errors)         |
+-------------------------------------------------+
|
v
+-------------------------------------------------+

|                SERVICE LAYER                    |
|         (Runs the Fraud Rules Engine)           |
+-------------------------------------------------+

         |                  |                  |
         v                  v                  v
[High-Value]       [High-Risk]        [Duplicate /]
[Checking  ]       [Industries]       [Velocity   ]

         |                  |                  |
         +------------------+------------------+
                        |
                        v
+-------------------------------------------------+

|            RISK LEVEL CLASSIFIER                |
+-------------------------------------------------+
|
v
+-------------------------------------------------+

|               REPOSITORY LAYER                  |
|          (Prepares Database Queries)            |
+-------------------------------------------------+
|
v
+-------------------------------------------------+

|              POSTGRESQL DATABASE                |
+-------------------------------------------------+
Use code with caution.

Layer Roles

• Controller Layer: The front desk. It takes inbound requests, makes sure files aren't missing parts, and returns standard HTTP statuses.
• Service Layer: The brains. It runs the rules calculations and assigns final danger ratings.
• Repository Layer: The filing assistant. It maps your Java code actions directly to relational database query rows.

6. Database Table Schema

Transactions are saved inside a PostgreSQL table structured with the following fields:

Field Name	Data Category	Description
transactionId	String (Primary Key)	The unique tracking receipt ID for the transaction.
customerId	String	The ID code of the customer making the transaction.
amount	Decimal Number	The exact financial value of the transaction.
category	String	The type of shop or transaction (e.g., TRANSFER, CRYPTO).
suspicious	True / False	A true/false flag showing if any fraud rule was broken.
fraudReason	String	A brief text code telling you why it was flagged.
severity	Text Option	The danger rating: LOW, MEDIUM, or HIGH.
createdAt	Date & Time	The timestamp showing exactly when it was saved.


7. REST API Reference

• Base Local Address: http://localhost:8080

Endpoint Directives


Method	Web Address Link	Action Purpose
POST	/api/transactions	Submits a new transaction to be checked and saved.
GET	/api/transactions	Pulls back a list of all historical records.
GET	/api/transactions/{transactionId}	Fetches a single specific transaction using its ID number.


API Payload Example


1. Incoming Action Request (POST)

http
POST /api/transactions
Content-Type: application/json
Use code with caution.
json
{
"transactionId": "TXN001",
"customerId": "CUSTOMER001",
"amount": 15000,
"category": "TRANSFER"
}
Use code with caution.

2. Processed System Reply

json
{
"transactionId": "TXN001",
"amount": 15000,
"suspicious": true,
"reason": "HIGH_VALUE_TRANSACTION",
"severity": "HIGH"
}
Use code with caution.

Opening the Visual Interface (Swagger UI)

Once the server application is turned on and running locally, paste this web link into your browser to test live fields using a visual interface:
text
http://localhost:8080/swagger-ui/index.html
Use code with caution.

8. Installation & Setup


Prerequisites

Before you begin, install these required components onto your computer:
1. Java 21 Development Kit (JDK)
2. Docker Desktop
3. Git CLI
4. Optional: PostgreSQL database software (only needed if you plan to run the app manually without using Docker).

Downloading the Code

Run these commands inside your computer's terminal:
bash
git clone https://github.com
cd fraud-rule-engine
Use code with caution.

9. Running with Docker (Recommended Method)

Docker automates setting up your app and database so they connect correctly right out of the box.

Step 1: Create Your Settings File

Create a new text file named exactly .env inside your root directory folder and paste in these database credentials:
ini
DB_USERNAME=postgres
DB_PASSWORD=your_secure_postgresql_password
Use code with caution.

Step 2: Build and Boot Up

Launch your combined app and database instances in background mode with this single command:
bash
docker compose up --build -d
Use code with caution.

Step 3: Check System Status

Verify that both containers are up and running properly:
bash
docker compose ps
Use code with caution.

Step 4: Watch Server Output Logs

Read the continuous system boot logs to ensure everything is working fine:
bash
docker compose logs -f app
Use code with caution.

Step 5: Shut Down the Project

When you are done testing, turn off the application and clean up background tasks safely:
bash
docker compose down
Use code with caution.
⚠️ Warning: Avoid adding -v to the down command (docker compose down -v) unless you want to completely erase your saved database history.

10. Running Manually inside your IDE (IntelliJ)

1. Open up IntelliJ IDEA and import the project folder using the Maven Project import setting.
2. Verify that your IDE project settings are set to build using Java 21.
3. Make sure your local PostgreSQL database engine is active on your host machine.
4. Update the database credentials inside the project settings file: src/main/resources/application.properties.
5. Run the main Java startup file: FraudRuleEngineApplication.java.

11. Automated Testing

The project contains unit testing frameworks that simulate transactions to make sure the code logic doesn't break over time.

Scenarios Tested Automatically

• Handling clear, harmless transaction flows.
• Checking exact amount limits on high-value items.
• Scanning for suspicious industry labels.
• Tracking quick velocity spam flows and twin duplicate attempts.

How to Run the Tests

Open a terminal workspace inside the main directory and input:
bash
# For Windows machines:
.\mvnw.cmd test

# For macOS or Linux machines:
./mvnw test
Use code with caution.
If your test run completes successfully without any logic errors, your console will print out:
text
[INFO] ------------------------------------------------------------------------
[INFO] BUILD SUCCESS
[INFO] ------------------------------------------------------------------------
Use code with caution.

12. Response Handlers & Exceptions

The system translates complex server errors into easy-to-read web status codes:

Web Status Code	Meanings	Simple Cause
200 OK	Success	The transaction passed validation checks and was successfully saved.
400 BAD REQUEST	Data Mistake	The request body was messy, misspelled, or missing fields.
404 NOT FOUND	Missing File	You searched for a transactionId that doesn't exist in our records.
409 CONFLICT	Duplicate ID	You tried to insert a transaction using an ID that is already taken.


13. Security Setup

• Hidden Settings Configuration: Highly private database login details are kept outside your code files inside your local .env configuration template.
• Leak Protections: Specific configuration filters (.gitignore, .dockerignore) stop confidential security passwords from accidentally leaking out onto public GitHub servers.
• Payload Sanitation: Strict typing validations discard faulty data right away at the network gateway.

14. Potential Future Enhancements

• 🔄 Live Threshold Controls: Adjust fraud rules dynamically via a dashboard without needing to turn off the server.
• 🧠 Smart AI Analysis: Add machine-learning models to spot suspicious user habits automatically.
• 📊 Admin Visualization Portal: Design a web interface panel for fraud analysts to manage alerts.
• 🔐 Secure Login Infrastructure: Build username, password, and permissions controls (Spring Security) around all API access keys.

15. Project Quick Facts

• Project Category: Financial Technology / Backend Rule Engine
• Core Language Technology: Java 21 (Spring Boot Framework)
• Database Infrastructure: PostgreSQL Relational Database
Would you like to build an API testing file next, or plan out how a user login feature would look? Let me know!
• Deployment System: Multi-Stage Docker containers