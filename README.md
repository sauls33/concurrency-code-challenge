
# Order Matching System - Code Challenge

## Project Overview

This project implements an **Order Matching System** designed to handle high-concurrency and low-latency order matching, simulating a basic trading system. The system receives `BUY` and `SELL` orders, matches compatible pairs based on price, and processes them asynchronously. The project is built using **Spring Boot** with **WebFlux** and **Netty** for high-performance, non-blocking I/O. 

This project serves as a code challenge, where you are expected to complete the methods marked with TODO tags, optimize the existing functionality, and ensure the system can handle processing 100,000 orders efficiently by the end.

## Features
- **Non-blocking Reactive API**: Utilizes Spring WebFlux for handling asynchronous requests.
- **Order Matching**: Matches `BUY` and `SELL` orders based on price, ensuring that a `BUY` order price is greater than or equal to the `SELL` order price.
- **Concurrency Handling**: Uses Netty's non-blocking event-driven architecture for handling concurrent order submissions efficiently.
- **Response Handling**: Returns appropriate JSON responses using `ResponseEntity` for both success and failure scenarios.

## State
The project includes several foundational classes that outline the core functionality. You are expected to build upon these classes by refining the existing logic or adding new features.
- **Order Processing API**: A POST endpoint (`/orders/submit`) that accepts order details (`type`, `price`, and `quantity`) and processes them asynchronously.
- **Order Matching**: The system matches orders based on price and removes matched orders from the queues.
- **Error Handling**: Provides structured JSON responses for success and failure cases.
- **Asynchronous Processing**: Built with WebFlux and Reactor for non-blocking, asynchronous order handling.

### Example Request:
```bash
curl -X POST "http://localhost:8080/orders/submit?ordertype=BUY&price=50&quantity=10"
```

### Example Success Response (HTTP 201):
```json
{
  "success": true,
  "message": "Order processed successfully",
  "data": "Order processed: Order{id=123456789, type=BUY, price=50.0, quantity=10, timestamp=1634231827901}"
}
```

### Example Error Response (HTTP 500):
```json
{
  "success": false,
  "message": "Order processing failed",
  "data": "Error message or exception details"
}
```

## Installation and Usage

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/your-repo/order-matching-system.git
   cd concurrency-code-challenge
   ```

2. **Build the Project**:
   Build the project using Maven:
   ```bash
   mvn clean install
   ```

3. **Run the Application**:
   Start the Spring Boot application:
   ```bash
   mvn spring-boot:run
   ```

   The application will be running on `http://localhost:8080`.

4. **Submit Orders**:
   You can submit `BUY` or `SELL` orders using the `/orders/submit` endpoint with query parameters `ordertype`, `price`, and `quantity`. For example:
   ```bash
   curl -X POST "http://localhost:8080/orders/submit?ordertype=BUY&price=50&quantity=10"
   ```

5. **Check Responses**:
   Responses will be returned in JSON format, indicating whether the order was successfully processed or if there was an error.

## Contribution Guidelines
This project is part of a code challenge. If you are working on this challenge, please make sure to:
- Follow good coding practices (e.g., SOLID principles, proper exception handling).
- Ensure the system remains performant and scalable.
- Write clean, maintainable code, and document any additional features or improvements you implement.

## License
This project is licensed under the MIT License.
