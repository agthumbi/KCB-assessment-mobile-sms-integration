# Mobile Money B2C API

## Overview
This project provides an API for initiating Business-to-Customer (B2C) mobile money transactions using an event-driven architecture. It allows businesses to send money to individual mobile users via different telecom providers and handles SMS notifications asynchronously through event processing.

## Technologies Used
- **Spring Boot**: Backend framework
- **Lombok**: Simplifies Java code with annotations
- **Swagger (OpenAPI)**: API documentation
- **RESTful APIs**: HTTP-based communication
- ** We are assuming the abracts are using even-driven architecture

## Event-Driven Architecture
This system leverages an event-driven approach where:
- **B2C requests** are sent to vendor abstractions
- **A consumer service** processes the request asynchronously and interacts with mobile money providers.
- **SMS notifications** are sent as separate events once the transaction is complete.

## Endpoints
### 1. B2C Request
**Endpoint:** `POST /api/v1/b2c`

**Description:** Sends money to a mobile number based on the telecom provider.

**Request Body:**
```json
{
  "senderMobileNumber": "0722000000",
  "narration": "Send money",
  "amount": 100,
  "telco": "M-PESA",
  "reciepientMobileNo": "0721000000"
}
```


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
## Avoid Hardcoding Secrets
```
google:
  client-id: ###############
  client-secret: ############
```


For better testing purpose disable the oauth2 plugins and comment the code as follows:
application.yml
<!--		<dependency>-->
<!--			<groupId>org.springframework.boot</groupId>-->
<!--			<artifactId>spring-boot-starter-oauth2-client</artifactId>-->
<!--		</dependency>-->
<!--		<dependency>-->
<!--			<groupId>org.springframework.boot</groupId>-->
<!--			<artifactId>spring-boot-starter-security</artifactId>-->
<!--		</dependency>-->
<!--		<dependency>-->
<!--			<groupId>org.springframework.security</groupId>-->
<!--			<artifactId>spring-security-test</artifactId>-->
<!--			<scope>test</scope>-->
<!--		</dependency>-->
AuthController.java
//package com.example.assessment.controller;
//
//import org.springframework.security.core.annotation.AuthenticationPrincipal;
//import org.springframework.security.oauth2.core.oidc.user.OidcUser;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//@RestController
//@RequestMapping("/api")
//public class AuthController {
//
//    @GetMapping("/user")
//    public OidcUser userInfo(@AuthenticationPrincipal OidcUser user) {
//        return user; // Returns Google profile info as JSON
//    }
//
//    @GetMapping("/public")
//    public String publicEndpoint() {
//        return "This is a public endpoint!";
//    }
//}
//


SecurityConfig.java
//package com.example.assessment.configs;
//import org.springframework.context.annotation.Bean;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import static org.springframework.security.config.Customizer.withDefaults;
//
//@EnableWebSecurity
//public class SecurityConfig {
//
//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers(
//                                "/v3/api-docs/**", "/swagger-ui/**", "/swagger-ui.html" // ✅ Allow Swagger
//                        ).permitAll()
//                        .anyRequest().authenticated()
//                )
//                .oauth2Login(withDefaults())  // ✅ Only allow OAuth2 login (Removes form login)
//                .csrf(csrf -> csrf.disable()) // (Optional) Disable CSRF for APIs
//                .logout(logout -> logout.logoutSuccessUrl("/public"));
//
//        return http.build();
//    }
//}
SecurityCOnfigTest.java
//package com.example.assessment.controller;
//
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
//import org.springframework.security.test.context.support.WithMockUser;
//import org.springframework.test.web.servlet.MockMvc;
//
//import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.oauth2Login;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@WebMvcTest
//public class SecurityConfigTest {
//
//    @Autowired
//    private MockMvc mockMvc;
//
//    @Test
//    void givenAuthenticatedUser_whenAccessingSecuredEndpoint_thenSuccess() throws Exception {
//        mockMvc.perform(get("/user").with(oauth2Login()))
//                .andExpect(status().isOk());
//    }
//
//    @Test
//    void givenUnauthenticatedUser_whenAccessingSecuredEndpoint_thenUnauthorized() throws Exception {
//        mockMvc.perform(get("/user"))
//                .andExpect(status().isUnauthorized());
//    }
//}

and pom.xml
<!--		<dependency>-->
<!--			<groupId>org.springframework.boot</groupId>-->
<!--			<artifactId>spring-boot-starter-oauth2-client</artifactId>-->
<!--		</dependency>-->
<!--		<dependency>-->
<!--			<groupId>org.springframework.boot</groupId>-->
<!--			<artifactId>spring-boot-starter-security</artifactId>-->
<!--		</dependency>-->
<!--		<dependency>-->
<!--			<groupId>org.springframework.security</groupId>-->
<!--			<artifactId>spring-security-test</artifactId>-->
<!--			<scope>test</scope>-->
<!--		</dependency>-->



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
