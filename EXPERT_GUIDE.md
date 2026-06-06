# 🏆 Spring Boot Archetype 3.x - Expert Guide

## 📚 Table of Contents
1. [Project Overview](#project-overview)
2. [Architecture & Technology Stack](#architecture--technology-stack)
3. [Setup & Installation](#setup--installation)
4. [Creating Projects from Archetype](#creating-projects-from-archetype)
5. [Database Configuration](#database-configuration)
6. [Reverse Engineering](#reverse-engineering)
7. [Development Workflow](#development-workflow)
8. [Testing & Code Coverage](#testing--code-coverage)
9. [API Documentation](#api-documentation)
10. [Troubleshooting](#troubleshooting)

---

## Project Overview

**spring-boot-archetype-3x** is a Maven Archetype that provides a production-ready template for creating microservices using:

- **Spring Boot 3.2.0** - Latest Spring Boot version
- **Java 21** - Latest LTS Java version
- **OpenAPI/Swagger** - API documentation
- **Flyway** - Database versioning & migrations
- **Hibernate Tools** - Reverse engineering from existing databases
- **JaCoCo** - Code coverage analysis
- **ModelMapper** - Object mapping
- **Lombok** - Code generation

### Key Benefits
✅ Standardized project structure  
✅ Multi-database support (MySQL, PostgreSQL, H2, Oracle)  
✅ Database schema versioning with Flyway  
✅ Automatic entity generation from DB  
✅ Pre-configured Swagger/OpenAPI  
✅ Code coverage tracking with JaCoCo  
✅ Multiple environment profiles  
✅ GPLv3 licensed

---

## Architecture & Technology Stack

### Core Dependencies

| Component | Version | Purpose |
|-----------|---------|---------|
| Spring Boot | 3.2.0 | Web framework & microservices foundation |
| Java | 21 | Programming language (LTS) |
| Spring Data JPA | 3.2.0 | ORM & data access |
| Flyway | 10.3.0-10.6.0 | Database migrations |
| Hibernate | 6.3.1 | ORM implementation |
| ModelMapper | 3.1.1 | Bean mapping |
| Lombok | 1.18.30 | Reduce boilerplate code |
| SpringDoc OpenAPI | 2.3.0 | Swagger UI & documentation |
| JUnit 5 | 5.10.1 | Testing framework |
| JaCoCo | 0.8.11 | Code coverage |

### Database Support

The archetype supports 4 database systems:

1. **MySQL** - Version 8.2.0 connector
2. **PostgreSQL** - Version 42.7.1 connector
3. **H2** - Version 2.2.224 (in-memory testing)
4. **Oracle** - Version 23.8.0.25.04 connector

Each database has:
- Dedicated Maven profile
- Flyway configuration file
- Hibernate reverse engineering config

---

## Setup & Installation

### Prerequisites
- Maven 3.8.0+
- Java 21 installed
- Git (optional, for cloning)

### Step 1: Clone the Repository
```bash
git clone https://github.com/FixBox/spring-boot-archetype-3x.git
cd spring-boot-archetype-3x
```

### Step 2: Install the Archetype Locally
```bash
mvn clean install
```

This command will:
- Build the Maven archetype
- Install it in your local repository: `~/.m2/repository/it/service/spring-boot-archetype-3x/1.2-SNAPSHOT/`
- Make it available for project generation

### Verify Installation
```bash
mvn archetype:list | grep spring-boot-archetype
```

You should see: `it.service:spring-boot-archetype-3x`

---

## Creating Projects from Archetype

### Method 1: Command Line (Recommended)

```bash
mvn archetype:generate \
  -B \
  -DarchetypeGroupId=it.service \
  -DarchetypeArtifactId=spring-boot-archetype-3x \
  -DarchetypeVersion=1.2-SNAPSHOT \
  -DgroupId=com.mycompany \
  -DartifactId=my-service \
  -Dversion=1.0.0
```

**Parameters:**
- `-B` → Batch mode (no interactive prompts)
- `-DgroupId` → Java package group (e.g., `com.mycompany`)
- `-DartifactId` → Project name (e.g., `my-service`)
- `-Dversion` → Initial version (default: 1.0-SNAPSHOT)

### Method 2: Interactive Mode

```bash
mvn archetype:generate \
  -DarchetypeGroupId=it.service \
  -DarchetypeArtifactId=spring-boot-archetype-3x \
  -DarchetypeVersion=1.2-SNAPSHOT
```

Maven will prompt you for:
- groupId
- artifactId
- version
- package

### Method 3: IntelliJ IDEA

1. **File → New → Project**
2. Select "Maven Archetype"
3. Click "Add Archetype"
4. Fill in:
   - GroupId: `it.service`
   - ArtifactId: `spring-boot-archetype-3x`
   - Version: `1.2-SNAPSHOT`
5. Complete the project creation wizard

### Generated Project Structure

```
my-service/
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/mycompany/
│   │   │       ├── Application.java
│   │   │       ├── controller/
│   │   │       ├── service/
│   │   │       ├── repository/
│   │   │       ├── model/
│   │   │       └── config/
│   │   └── resources/
│   │       ├── application.properties
│   │       ├── application-local.properties
│   │       ├── application-dev.properties
│   │       ├── flyWay.config/
│   │       │   ├── mySqlConfig.properties
│   │       │   ├── postgreSqlConfig.properties
│   │       │   ├── h2.properties
│   │       │   └── oracleSqlConfig.properties
│   │       ├── revegHmb.config/
│   │       │   ├── mySql/
│   │       │   ├── postgresSql/
│   │       │   ├── h2/
│   │       │   └── oracle/
│   │       └── db/
│   │           ├── migration/
│   │           └── sql/
│   └── test/
│       └── java/
│           └── com/mycompany/
│               └── ApplicationTests.java
├── pom.xml
├── README.md
└── .gitignore
```

---

## Database Configuration

### Configuring Your Database

#### 1. MySQL Configuration

**File:** `src/main/resources/application-mysql.properties`

```properties
# MySQL - JDBC URL
spring.datasource.url=jdbc:mysql://localhost:3306/myservice?serverTimezone=UTC
spring.datasource.driverClassName=com.mysql.cj.jdbc.Driver
spring.datasource.username=root
spring.datasource.password=your_password

# Hibernate Configuration
spring.jpa.database-platform=org.hibernate.dialect.MySQL8Dialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.show-sql=false
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.hibernate.naming.physical-strategy=org.hibernate.boot.model.naming.PhysicalNamingStrategyStandardImpl
```

**Flyway Config:** `src/main/resources/flyWay.config/mySqlConfig.properties`

```properties
flyway.driver=com.mysql.cj.jdbc.Driver
flyway.url=jdbc:mysql://localhost:3306/myservice?serverTimezone=UTC
flyway.user=root
flyway.password=your_password
flyway.locations=filesystem:src/main/resources/db/migration
```

#### 2. PostgreSQL Configuration

**File:** `src/main/resources/application-postgresql.properties`

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/myservice
spring.datasource.driverClassName=org.postgresql.Driver
spring.datasource.username=postgres
spring.datasource.password=your_password

spring.jpa.database-platform=org.hibernate.dialect.PostgreSQL9Dialect
spring.jpa.hibernate.ddl-auto=validate
spring.jpa.properties.hibernate.format_sql=true
```

#### 3. H2 Configuration (Testing)

**File:** `src/main/resources/application-h2.properties`

```properties
spring.datasource.url=jdbc:h2:mem:testdb
spring.datasource.driver-class-name=org.h2.Driver
spring.datasource.username=sa
spring.datasource.password=

spring.jpa.database-platform=org.hibernate.dialect.H2Dialect
spring.jpa.hibernate.ddl-auto=create-drop
spring.h2.console.enabled=true
```

Access H2 Console: http://localhost:8080/h2-console

#### 4. Oracle Configuration

**File:** `src/main/resources/application-oracle.properties`

```properties
spring.datasource.url=jdbc:oracle:thin:@localhost:1521:ORCL
spring.datasource.driverClassName=oracle.jdbc.driver.OracleDriver
spring.datasource.username=system
spring.datasource.password=your_password

spring.jpa.database-platform=org.hibernate.dialect.Oracle12cDialect
spring.jpa.hibernate.ddl-auto=validate
```

### Database Migration with Flyway

#### Create Migration Files

Location: `src/main/resources/db/migration/`

Naming convention: `V1__initial_schema.sql`

```sql
-- V1__initial_schema.sql
CREATE TABLE users (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    username VARCHAR(50) NOT NULL UNIQUE,
    email VARCHAR(100) NOT NULL UNIQUE,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE products (
    id BIGINT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(100) NOT NULL,
    price DECIMAL(10, 2),
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);
```

#### Run Migrations

**With specific profile:**

```bash
# MySQL
mvn flyway:migrate -P mySql-migration

# PostgreSQL
mvn flyway:migrate -P postgreSQL-migration

# H2
mvn flyway:migrate -P h2-migration

# Oracle
mvn flyway:migrate -P oracle-migration
```

**In application.properties:**

```properties
spring.flyway.enabled=true
spring.flyway.baseline-on-migrate=true
spring.flyway.locations=classpath:db/migration
```

---

## Reverse Engineering

Generate Entity classes, DAOs, and DDL from an existing database.

### Prerequisites

1. Your database must be running
2. Update Hibernate configuration files:
   - `src/main/resources/revegHmb.config/[db]/hibernate.properties`
   - `src/main/resources/revegHmb.config/[db]/reveng.xml`

### MySQL Example

**File:** `src/main/resources/revegHmb.config/mySql/hibernate.properties`

```properties
hibernate.connection.driver_class=com.mysql.cj.jdbc.Driver
hibernate.connection.url=jdbc:mysql://localhost:3306/myservice?serverTimezone=UTC
hibernate.connection.username=root
hibernate.connection.password=your_password
hibernate.show_sql=true
hibernate.format_sql=true
hibernate.dialect=org.hibernate.dialect.MySQL8Dialect
```

**File:** `src/main/resources/revegHmb.config/mySql/reveng.xml`

```xml
<hibernate-reverse-engineering>
    <type-mapping>
        <sql-type jdbc-type="VARCHAR" hibernate-type="string"/>
        <sql-type jdbc-type="BIGINT" hibernate-type="long"/>
        <sql-type jdbc-type="DECIMAL" hibernate-type="java.math.BigDecimal"/>
        <sql-type jdbc-type="TIMESTAMP" hibernate-type="java.time.LocalDateTime"/>
    </type-mapping>
    
    <!-- Exclude Flyway schema history table -->
    <table
            match-schema=".*"
            match-name="flyway_schema_history"
            exclude="true"
    />
    
    <!-- Include all tables matching pattern -->
    <table-filter
            match-schema=".*"
            match-name=".*"
            exclude="false"
            package="com.mycompany.model"
    />
</hibernate-reverse-engineering>
```

### Generate Entities

```bash
mvn hibernate-tools:hbm2java -P mySql-migration
```

Generated entities appear in: `target/generated-sources/com/mycompany/model/`

### Generate DAOs

```bash
mvn hibernate-tools:hbm2dao -P mySql-migration
```

### Generate DDL Schema

```bash
mvn hibernate-tools:hbm2ddl -P mySql-migration
```

### Copy Generated Code

Move generated classes to your `src/main/java/` directory:

```bash
cp -r target/generated-sources/com/mycompany/model/* src/main/java/com/mycompany/model/
```

---

## Development Workflow

### Project Structure Best Practices

```
src/main/java/com/mycompany/
├── Application.java                 # Spring Boot entry point
├── config/
│   ├── SwaggerConfig.java          # Swagger/OpenAPI configuration
│   ├── ModelMapperConfig.java       # ModelMapper bean configuration
│   └── DatabaseConfig.java          # Database configuration
├── controller/
│   ├── UserController.java
│   ├── ProductController.java
│   └── dto/
│       ├── UserDTO.java
│       └── ProductDTO.java
├── service/
│   ├── UserService.java
│   ├── UserServiceImpl.java
│   └── ProductService.java
├── repository/
│   ├── UserRepository.java
│   └── ProductRepository.java
├── model/
│   ├── User.java
│   ├── Product.java
│   └── BaseEntity.java
└── exception/
    ├── EntityNotFoundException.java
    └── GlobalExceptionHandler.java
```

### Creating an Entity with Lombok

**File:** `src/main/java/com/mycompany/model/User.java`

```java
package com.mycompany.model;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @Column(name = "username", unique = true, nullable = false)
    private String username;
    
    @Column(name = "email", unique = true, nullable = false)
    private String email;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
}
```

### Creating a Repository

**File:** `src/main/java/com/mycompany/repository/UserRepository.java`

```java
package com.mycompany.repository;

import com.mycompany.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);
}
```

### Creating a Service

**File:** `src/main/java/com/mycompany/service/UserService.java`

```java
package com.mycompany.service;

import com.mycompany.model.User;
import java.util.List;
import java.util.Optional;

public interface UserService {
    List<User> getAllUsers();
    Optional<User> getUserById(Long id);
    User createUser(User user);
    User updateUser(Long id, User user);
    void deleteUser(Long id);
}
```

**File:** `src/main/java/com/mycompany/service/impl/UserServiceImpl.java`

```java
package com.mycompany.service.impl;

import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import com.mycompany.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    
    private final UserRepository userRepository;
    
    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }
    
    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
    
    @Override
    public User createUser(User user) {
        return userRepository.save(user);
    }
    
    @Override
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
            .map(user -> {
                user.setUsername(userDetails.getUsername());
                user.setEmail(userDetails.getEmail());
                return userRepository.save(user);
            })
            .orElseThrow(() -> new RuntimeException("User not found"));
    }
    
    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
```

### Creating a REST Controller

**File:** `src/main/java/com/mycompany/controller/UserController.java`

```java
package com.mycompany.controller;

import com.mycompany.model.User;
import com.mycompany.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/users")
@RequiredArgsConstructor
@Tag(name = "User Management", description = "APIs for managing users")
public class UserController {
    
    private final UserService userService;
    
    @GetMapping
    @Operation(summary = "Get all users")
    public ResponseEntity<List<User>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    
    @GetMapping("/{id}")
    @Operation(summary = "Get user by ID")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        return userService.getUserById(id)
            .map(ResponseEntity::ok)
            .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping
    @Operation(summary = "Create new user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        User created = userService.createUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(created);
    }
    
    @PutMapping("/{id}")
    @Operation(summary = "Update user")
    public ResponseEntity<User> updateUser(
        @PathVariable Long id,
        @RequestBody User user) {
        User updated = userService.updateUser(id, user);
        return ResponseEntity.ok(updated);
    }
    
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete user")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
```

### ModelMapper Configuration

**File:** `src/main/java/com/mycompany/config/ModelMapperConfig.java`

```java
package com.mycompany.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ModelMapperConfig {
    
    @Bean
    public ModelMapper modelMapper() {
        ModelMapper modelMapper = new ModelMapper();
        // Configure mapping rules if needed
        return modelMapper;
    }
}
```

**Usage in Service:**

```java
@Service
@RequiredArgsConstructor
public class UserServiceImpl {
    
    private final ModelMapper modelMapper;
    
    public UserDTO convertToDTO(User user) {
        return modelMapper.map(user, UserDTO.class);
    }
    
    public User convertToEntity(UserDTO dto) {
        return modelMapper.map(dto, User.class);
    }
}
```

---

## Testing & Code Coverage

### Running Tests

```bash
# Run all tests
mvn test

# Run specific test class
mvn test -Dtest=UserServiceTest

# Run with code coverage
mvn test jacoco:report
```

### Writing Unit Tests

**File:** `src/test/java/com/mycompany/service/UserServiceTest.java`

```java
package com.mycompany.service;

import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import com.mycompany.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {
    
    @Mock
    private UserRepository userRepository;
    
    private UserService userService;
    
    @BeforeEach
    public void setUp() {
        userService = new UserServiceImpl(userRepository);
    }
    
    @Test
    public void testGetUserById() {
        // Arrange
        Long userId = 1L;
        User expectedUser = User.builder()
            .id(userId)
            .username("testuser")
            .email("test@example.com")
            .build();
        
        when(userRepository.findById(userId))
            .thenReturn(Optional.of(expectedUser));
        
        // Act
        Optional<User> result = userService.getUserById(userId);
        
        // Assert
        assertTrue(result.isPresent());
        assertEquals("testuser", result.get().getUsername());
        verify(userRepository, times(1)).findById(userId);
    }
    
    @Test
    public void testCreateUser() {
        // Arrange
        User user = User.builder()
            .username("newuser")
            .email("new@example.com")
            .build();
        
        User savedUser = User.builder()
            .id(1L)
            .username("newuser")
            .email("new@example.com")
            .build();
        
        when(userRepository.save(user)).thenReturn(savedUser);
        
        // Act
        User result = userService.createUser(user);
        
        // Assert
        assertNotNull(result.getId());
        assertEquals("newuser", result.getUsername());
        verify(userRepository, times(1)).save(user);
    }
}
```

### Integration Tests

```java
package com.mycompany;

import com.mycompany.controller.UserController;
import com.mycompany.model.User;
import com.mycompany.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIntegrationTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Autowired
    private UserRepository userRepository;
    
    @Test
    public void testGetAllUsers() throws Exception {
        mockMvc.perform(get("/api/users"))
            .andExpect(status().isOk())
            .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }
}
```

### Code Coverage Reports

After running tests:

```bash
mvn test jacoco:report
```

Open report: `target/site/jacoco/index.html`

**Coverage metrics displayed:**
- Lines covered
- Branches covered
- Methods covered
- Complexity

---

## API Documentation

### Swagger/OpenAPI Configuration

**File:** `src/main/java/com/mycompany/config/SwaggerConfig.java`

```java
package com.mycompany.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
    info = @Info(
        title = "My Service API",
        version = "1.0.0",
        description = "REST API documentation",
        contact = @Contact(
            name = "Support Team",
            email = "support@example.com"
        ),
        license = @License(
            name = "GNU General Public License v3",
            url = "https://www.gnu.org/licenses/gpl-3.0.html"
        )
    ),
    servers = {
        @Server(
            url = "http://localhost:8080",
            description = "Development Server"
        ),
        @Server(
            url = "https://api.example.com",
            description = "Production Server"
        )
    }
)
public class SwaggerConfig {
}
```

### Accessing Swagger UI

After starting your application:

```
http://localhost:8080/swagger-ui.html
```

### API Documentation in Code

Use OpenAPI annotations in your controllers:

```java
@GetMapping("/{id}")
@Operation(
    summary = "Get user by ID",
    description = "Retrieves a specific user by their ID"
)
@ApiResponses(value = {
    @ApiResponse(
        responseCode = "200",
        description = "User found"
    ),
    @ApiResponse(
        responseCode = "404",
        description = "User not found"
    )
})
public ResponseEntity<UserDTO> getUserById(
    @PathParameter(description = "User ID") @PathVariable Long id) {
    // implementation
}
```

---

## Development Profiles

The archetype includes multiple profiles for different environments:

### Available Profiles

```properties
# application.properties
spring.profiles.active=local

# application-local.properties      (Development local)
# application-dev.properties        (Development environment)
# application-prod.properties       (Production)
```

### Running with Profiles

```bash
# Local development
java -jar app.jar --spring.profiles.active=local

# Development environment
java -jar app.jar --spring.profiles.active=dev

# Production
java -jar app.jar --spring.profiles.active=prod

# H2 database (testing)
java -jar app.jar --spring.profiles.active=h2
```

### Maven Compilation with Profiles

```bash
mvn clean package -P local
```

---

## Running Your Service

### Development Mode

```bash
mvn spring-boot:run -Dspring-boot.run.arguments="--spring.profiles.active=local"
```

### Package and Run

```bash
# Build JAR
mvn clean package

# Run with profile
java -jar target/my-service-1.0.0.jar --spring.profiles.active=local
```

### Default Endpoints

After startup, you'll see logs like:

```
2025-01-15 14:25:44,833 INFO  [main] Tomcat started on port(s): 8080 (http) 
                                    with context path '/my-service'
2025-01-15 14:25:44,835 INFO  [main] Started Application in 5.234 seconds
```

**Access your application:**
- API: `http://localhost:8080/my-service/api/`
- Swagger: `http://localhost:8080/my-service/swagger-ui.html`
- H2 Console: `http://localhost:8080/my-service/h2-console`

---

## Troubleshooting

### Issue: Archetype not found

**Error:**
```
[ERROR] No archetype found in remote catalog.
```

**Solution:**
1. Reinstall archetype locally:
   ```bash
   mvn clean install
   ```

2. Verify it's in your local repository:
   ```bash
   ls ~/.m2/repository/it/service/spring-boot-archetype-3x/
   ```

### Issue: Database connection fails

**Error:**
```
ERROR Connection refused
```

**Solution:**
1. Verify database is running
2. Check connection properties in `application-[profile].properties`
3. Verify username/password
4. Check firewall and port accessibility

### Issue: Flyway migration fails

**Error:**
```
ERROR Detected a failed migration
```

**Solution:**
1. Check migration script for syntax errors
2. Review Flyway configuration in `flyWay.config/[db]Config.properties`
3. Validate SQL syntax for your database
4. Clean and reapply migrations (development only):
   ```bash
   mvn flyway:clean
   mvn flyway:migrate
   ```

### Issue: Entities not generated from database

**Error:**
```
No tables found in database
```

**Solution:**
1. Verify database connection in `revegHmb.config/[db]/hibernate.properties`
2. Check that tables actually exist in database
3. Review table filter patterns in `reveng.xml`
4. Ensure the user has SELECT permissions

### Issue: Swagger UI not loading

**Error:**
```
404 Not Found
```

**Solution:**
1. Verify SpringDoc dependency in `pom.xml`
2. Check `springdoc-openapi-starter-webmvc-ui` version
3. Access correct URL: `/swagger-ui.html` (not `/swagger-ui/`)
4. Check application context path in properties

### Issue: JaCoCo code coverage report missing

**Error:**
```
target/site/jacoco/ directory not found
```

**Solution:**
```bash
# Run tests first
mvn clean test

# Then generate report
mvn jacoco:report

# Or combined:
mvn clean test jacoco:report
```

---

## Best Practices

### 1. Database Versioning
- Always create new migration files (V1__, V2__, etc.)
- Never edit existing migration files
- Use descriptive names: `V1__create_users_table.sql`

### 2. Entity Design
- Always use `@Entity` and `@Table` annotations
- Include `@Id` and `@GeneratedValue` on primary key
- Use `@Column` for important field mappings
- Use Lombok annotations to reduce boilerplate

### 3. Repository Pattern
- Create one repository interface per entity
- Extend `JpaRepository<Entity, PrimaryKeyType>`
- Add custom query methods as needed
- Keep business logic in Service layer

### 4. Service Layer
- Create both interface and implementation
- Keep business logic here, not in controllers
- Handle exceptions and validation
- Use `@Service` annotation

### 5. Controller Design
- Use REST conventions
- Add OpenAPI annotations for documentation
- Return appropriate HTTP status codes
- Use DTOs for request/response

### 6. Testing
- Write unit tests for services
- Write integration tests for controllers
- Aim for 80%+ code coverage
- Use Mockito for mocking dependencies

### 7. API Security
- Implement authentication/authorization
- Use HTTPS in production
- Validate all inputs
- Follow OWASP guidelines

---

## Additional Resources

- **Spring Boot Docs**: https://spring.io/projects/spring-boot
- **Spring Data JPA**: https://spring.io/projects/spring-data-jpa
- **Flyway**: https://flywaydb.org/
- **Hibernate**: https://hibernate.org/
- **OpenAPI/Swagger**: https://www.openapis.org/
- **ModelMapper**: http://modelmapper.org/

---

## License

This archetype is released under the GNU General Public License v3 (GPLv3).
Projects generated using this archetype are not automatically subject to the same license, unless they include substantial portions of the code from the archetype itself.

---

**Last Updated:** January 2025  
**Version:** 1.2-SNAPSHOT
