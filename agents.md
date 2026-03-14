# Java Clean Architecture & CI/CD Guidelines

This document provides architectural rules and coding standards for AI agents working on this project.

## 1. Core Architecture: Clean Architecture (Hexagonal)

### Repository Structure (GitOps Friendly)
The project is a flattened multi-module Maven project at the repository root to ensure full compatibility with modern CI/CD pipelines (GitHub Actions, GitLab CI, Jenkins).
- **Project Root**: Contains `pom.xml`, `Dockerfile`, `docker-compose.yml`, and CI configuration (`.github/`).
- **Modules**:
  - `domain`: Enterprise logic (Entities, Domain Exceptions, Base Classes). **No external dependencies**.
  - `application`: Business logic (Use Cases, Services, DTOs, Repository Interfaces). Depends on `domain`.
  - `infrastructure`: Implementation details (Spring Data JPA, Persistence, External Services). Depends on `application` and `domain`.
  - `api`: The entry point (Spring Boot Controllers, Global Exception Handling, Configuration). Depends on `application`.

### CI/CD & GitOps Readiness
- **GitHub Actions**: Pipeline defined in `.github/workflows/ci.yml`. It automates building, testing, and Docker image verification.
- **Statelessness**: The application is fully stateless (12-factor principle VI), making it ready for Kubernetes/GitOps deployments.
- **Environment Parity**: Configuration is strictly via environment variables (e.g., `DATABASE_URL`, `PORT`).

### Use Case-Based Folders
Business logic is organized by domain entity in `application/src/main/java/com/cleanarch/flashcards/application/features`.
**Structure:** `features/{entityName}/...`

**Example:**
```
/features/flashcards
  /commands/create
    CreateFlashcardCommand.java (DTO for creation)
  FlashcardService.java (Implementation of IFlashcardService)
```
- **Rule:** Prefer `Service`-based orchestration as the current project standard. 
- **Rule:** Use `record` for DTOs and Command/Query objects.
- **Rule:** Interfaces for services and repositories must be defined in `application/common/interfaces`.

---

## 2. Domain Layer Rules

- **Pure Java:** No dependencies on Spring Data, Hibernate, or Web frameworks. 
- **Behavior-Driven:** Encapsulate business rules within Entities. Avoid anemic models.
- **Base Class:** All entities must inherit from `BaseEntity` (provides `id`, `createdAt`, and `updatedAt`).
- **UUIDv7:** Use UUID v7 for all entity IDs. It provides the benefits of UUIDs (distributed generation, security) with the performance of `Long` (time-ordered, index-friendly). Use `UuidV7Generator` for generation.
- **Validation:** Enforce invariants in constructors or domain methods. Throw `DomainException` or specific variants if rules are violated.
- **Strong Typing:** Use Domain Entities for complex business logic, not DTOs.

---

## 3. Application Layer (Services & DTOs)

- **Services:** Orchestrate the flow of data. Use repository interfaces for persistence.
- **DTOs:** Return `FlashcardDto` (record) to the API layer. Never expose Domain Entities directly.
- **Validation:** Use Jakarta Bean Validation (`@NotNull`, `@NotBlank`, etc.) in DTOs.
- **Dependency Injection:** Use Constructor Injection. Avoid field injection (`@Autowired`).

---

## 4. Infrastructure & Persistence

- **ORM:** Spring Data JPA with Hibernate.
- **Database:** H2 (In-memory) for local dev/tests, with SQLite support via configuration.
- **Entity Mapping:** Use separate JPA entities in `infrastructure` if the domain entity and database schema diverge significantly.
- **Repositories:** Implement the interfaces defined in the `application` layer.

---

## 5. API Layer (Web)

- **Controllers:** Thin controllers. Delegate all business logic to Services.
- **Async/Reactive:** Current project uses standard Servlet stack (blocking). Endpoints should return `ResponseEntity<T>`.
- **Errors:** Handled via `@RestControllerAdvice` (`GlobalExceptionHandler`). Map exceptions to `ProblemDetails` format (RFC 7807).

---

## 6. 12-Factor App Guidelines

The application is built to be cloud-native and follow 12-factor principles:
- **I. Codebase:** One codebase tracked in revision control, many deploys.
- **III. Config:** Store config in the environment. Use environment variables for:
  - `PORT`: Server port (default: 8080).
  - `DATABASE_URL`: JDBC connection string.
- **VI. Processes:** Execute the app as one or more stateless processes. 
- **VII. Port binding:** Export services via port binding.
- **IX. Disposability:** Fast startup and graceful shutdown.
- **XI. Logs:** Treat logs as event streams. Use standard output (stdout).

---

## 7. Testing

- **Framework:** JUnit 5, AssertJ.
- **Unit Tests:** For Domain logic and Services (using Mockito).
- **Integration Tests:** Use `@SpringBootTest` with `@AutoConfigureMockMvc` for API testing.
- **Naming:** `should_ExpectedBehavior_when_Condition` (e.g., `should_ReturnFlashcard_when_IdExists`).

---

## 8. Coding Standards

- **Java Version:** **Java 21 (LTS)**. Use modern features (Records, Pattern Matching, Sealed Classes).
- **Indentation:** **SPACES** (4 per level).
- **Maven:** Use Maven for dependency management and build lifecycle.
- **Clean Code:** Use meaningful names, small methods, and follow SOLID principles.
