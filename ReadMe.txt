# Mobile Money B2C API

## Overview
This project provides an API for initiating Business-to-Customer (B2C) mobile money transactions using an event-driven architecture. It allows businesses to send money to individual mobile users via different telecom providers and handles SMS notifications asynchronously through event processing.

## Technologies Used
- **Spring Boot**: Backend framework
- **Spring Cloud Stream/Kafka/RabbitMQ**: Event-driven messaging
- **Lombok**: Simplifies Java code with annotations
- **Swagger (OpenAPI)**: API documentation
- **RESTful APIs**: HTTP-based communication

## Event-Driven Architecture
This system leverages an event-driven approach where:
- **B2C requests** are published as events to a messaging queue.
- **A consumer service** processes the request asynchronously and interacts with mobile money providers.
- **SMS notifications** are sent as separate events once the transaction is complete.

## Endpoints
### 1. B2C Request
**Endpoint:** `POST /api/v1/b2c`

**Description:** Sends money to a mobile number based on the telecom provider. The request is processed asynchronously using an event queue.

**Request Body:**
```json
{
  "senderMobileNumber": "43254345345",
  "narration": "Send money",
  "amount": 100,
  "telco": "Airtel",
  "reciepientMobileNo": "1111111111"
}
```

**Response:**
- **202 Accepted**: Request received and queued for processing.
- **400 Bad Request**: Invalid input data.
- **500 Internal Server Error**: Issues with processing the request.

## Installation & Setup
1. Clone the repository:
   ```sh
   git clone https://github.com/your-repo/KCB-assessment-mobile-sms-integration.git
   ```
2. Navigate to the project directory:
   ```sh
   cd KCB-assessment-mobile-sms-integration
   ```
3. Build and run the project using Maven:
   ```sh
   mvn spring-boot:run
   ```

## API Documentation
Once the application is running, visit:
```
http://localhost:8080/swagger-ui.html
```
To view and test API endpoints using Swagger UI.

## Contributing
1. Fork the repository.
2. Create a feature branch (`feature/your-feature`).
3. Commit your changes.
4. Open a pull request.

## License
This project is licensed under the MIT License.



Accessing the H2 Console
-----------------------------------------
Run your Spring Boot application.
Open a browser to http://localhost:8080/h2-console.
Provide the database connection information as below and connect:
JDBC URL: jdbc:h2:mem:testdb
User Name: kcb
Password: Empty or as configured in application.yml.
Commands to Run
Build and Run Containers:

docker-compose up --build

View Logs, to verify the H2 configuration:

docker logs app-container

Stop Containers:

docker-compose down