# Smart Campus REST API (JAX-RS)

## Overview

This project implements a RESTful API for managing a Smart Campus system using  JAX-RS (Jersey). The system manages Rooms, Sensors, and Sensor Readings, allowing facilities management systems to interact with campus data efficiently.

The API follows REST principles including resource-based design, proper HTTP methods, and structured JSON responses.

##  Base URL

http://localhost:8080/SmartCampus/api/v1


## Discovery Endpoint

**GET /**

Returns API metadata and available resources.

### Example Response

```json
{
  "contact": "admin@smartcampus.com",
  "resources": {
    "rooms": "/api/v1/rooms",
    "sensors": "/api/v1/sensors"
  },
  "version": "v1"
}


##  Room Management

###  GET /rooms

Retrieve all rooms.

###  POST /rooms

Create a new room.

```json
{
  "id": "ROOM1",
  "name": "Lecture Hall",
  "capacity": 100
}
```

###  GET /rooms/{id}

Retrieve a specific room.

###  DELETE /rooms/{id}

Delete a room (only if no sensors are assigned).

---

##  Sensor Management

###  GET /sensors

Retrieve all sensors.

###  GET /sensors?type=CO2

Filter sensors by type.

###  POST /sensors

Create a new sensor.

```json
{
  "id": "S1",
  "type": "CO2",
  "status": "ACTIVE",
  "currentValue": 0,
  "roomId": "ROOM1"
}
```

---

##  Sensor Readings (Sub-Resource)

###  GET /sensors/{id}/readings

Retrieve sensor readings.

###  POST /sensors/{id}/readings

Add a new reading.

```json
{
  "id": "R1",
  "timestamp": 1710000000,
  "value": 25.5
}

## Technologies Used

* Java (JAX-RS)
* Jersey
* Maven
* Apache Tomcat



##  How to Run

1. Open project in NetBeans
2. Clean and Build
3. Run project on Tomcat
4. Access:
http://localhost:8080/SmartCampus/api/v1

1.	Default Lifecycle of a JAX-RS Resource Class

JAX-RS resource classes operate under a per-request lifecycle by default. This means that every incoming HTTP request results in the instantiation of a new resource object. This instance handles the request and is then discarded. This design ensures isolation between requests and eliminates shared mutable state unless explicitly introduced. This lifecycle simplifies concurrency handling because each thread works on its own instance. However, it introduces challenges for maintaining state. Any in-memory data structures such as lists or maps defined as instance variables will not persist across requests. Therefore, developers must use static variables, singleton service layers, or persistent storage such as databases. When shared memory is used, concurrency issues must be addressed. Non-thread-safe collections like HashMap can result in race conditions and data corruption. Thread-safe alternatives like ConcurrentHashMap or synchronized blocks must be used. This architectural choice forces developers to consciously manage shared state and prevents accidental data inconsistencies in multi-threaded environments.


2.	Hypermedia (HATEOAS)

HATEOAS is a constraint of REST architecture that enables clients to dynamically navigate resources through links provided in API responses. Instead of relying on hardcoded endpoints, clients follow hyperlinks embedded in responses. This approach significantly reduces coupling between client and server. APIs become self-descriptive, meaning clients do not need extensive external documentation. Changes in URI structures do not break clients as long as link relations remain consistent. From a developer perspective, HATEOAS improves maintainability and scalability. It allows APIs to evolve over time while maintaining backward compatibility. It also supports discoverability, making APIs more intuitive and user-friendly.


3.	Returning IDs vs Full Objects

Returning only IDs reduces payload size and is beneficial for large datasets or low-bandwidth environments. However, it requires additional requests to retrieve full details, increasing latency and complexity. Returning full objects reduces the number of API calls and simplifies client logic, especially in user interface development. However, it increases payload size and server processing. The decision depends on trade-offs between network efficiency and usability. Modern APIs often use pagination, partial responses, or field filtering to balance these concerns.


4.	DELETE Idempotency

DELETE is considered idempotent because executing the same request multiple times results in the same final state. For example, deleting a resource removes it from the system. Subsequent DELETE requests do not change the state further, even if the response differs. This property is critical for distributed systems, where network failures may cause retries. Idempotency ensures
 
system stability and predictable behavior under repeated operations.


5.	@Consumes(MediaType.APPLICATION_JSON)

The @Consumes annotation specifies the media type a resource method can process. If a client sends a request with a mismatched Content-Type, the JAX-RS runtime cannot find a suitable message body reader and returns HTTP 415 Unsupported Media Type. This mechanism enforces strict API contracts and ensures that data is processed in a predictable format. It also prevents malformed or unexpected input from being processed, enhancing robustness and security.


6.	QueryParam vs PathParam

Query parameters are designed for filtering and searching collections. They are flexible, optional, and support multiple criteria. For example, /resources?type=A&status;=active allows dynamic filtering. Path parameters are intended for identifying specific resources and are not suitable for filtering. Using them for filtering leads to rigid and less maintainable APIs. Query parameters align with REST principles and provide scalability as requirements evolve.


7.	Sub-Resource Locator Pattern

The sub-resource locator pattern allows delegation of request handling to separate classes. Instead of defining deeply nested paths in a single class, responsibility is distributed across multiple resource classes. This improves modularity, readability, and maintainability. It also aligns with object-oriented design principles, enabling reuse and easier testing. Large APIs benefit significantly from this approach as it prevents monolithic controller structures.


8.	HTTP 422 vs 404

HTTP 422 indicates that a request is syntactically correct but semantically invalid. For example, referencing a non-existent entity within a valid JSON payload. HTTP 404 indicates that the requested resource itself does not exist. Using 422 provides more precise error semantics and improves API clarity. This distinction helps clients better understand and handle errors appropriately.


9.	Security Risks of Stack Traces

Exposing stack traces reveals sensitive internal details such as class names, file structures, frameworks, and database queries. Attackers can exploit this information to identify vulnerabilities and craft targeted attacks. Best practices include returning generic error messages to clients while logging detailed information internally. This approach balances usability and security.


10.	JAX-RS Filters
 
Filters provide a centralized mechanism for handling cross-cutting concerns such as logging, authentication, and request modification. They intercept requests and responses globally, reducing code duplication. Using filters improves maintainability, ensures consistency, and keeps resource classes focused on business logic. This leads to cleaner and more scalable architectures.





This project demonstrates a fully functional RESTful API using JAX-RS, incorporating best practices such as resource hierarchy, error handling, filtering, and logging.
