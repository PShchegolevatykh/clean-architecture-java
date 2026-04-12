## Project Overview

Assume a modern Java backend using:

* Spring Boot
* Maven
* Clean Architecture
* Domain-Driven Design (DDD)
* CQRS (where appropriate)
* Docker (containerized deployment)

This file defines **non-negotiable architectural and coding constraints**.

---

## Architecture

Layers:

* Domain
* Application
* Infrastructure
* API

Rules:

* Domain has no dependencies
* Application depends only on Domain
* Infrastructure depends on Application and Domain
* API depends on Application
* No circular dependencies
* Do not violate layer boundaries

---

## Domain

* Encapsulate business logic inside entities
* Avoid anemic domain models
* Use Value Objects for domain concepts
* Enforce invariants inside aggregates
* No framework or infrastructure dependencies
* Domain must remain synchronous and pure

---

## Application

* Use CQRS where it adds clarity:

  * Commands mutate state
  * Queries read state
* One handler/service per use case
* Application layer orchestrates, not implements core business logic
* Business rules belong in Domain
* Validate all inputs
* No business logic in API layer

---

## Infrastructure

* Contains all external concerns (DB, messaging, HTTP, etc.)
* Do not leak persistence or external models outside Infrastructure
* Map persistence models to domain models explicitly
* Configuration must come from environment (no hardcoded secrets)

---

## API Layer

* Controllers must be thin:

  * Accept request
  * Call Application layer
  * Return response
* No business logic or data access
* Use DTOs for input/output
* Return standardized error responses

---

## Security

* Never trust external input; validate all inputs
* Enforce authorization at API/Application boundaries
* Do not expose internal domain or persistence models directly
* Avoid dynamic query construction; use parameterized queries / ORM
* Do not log sensitive data (tokens, passwords, personal data)
* Do not hardcode secrets; use environment/config
* Prefer deny-by-default access control
* Use framework-provided security features (Spring Security); avoid custom implementations

---

## Concurrency & Cancellation

* Do not block threads unnecessarily (avoid blocking I/O in reactive flows)
* Propagate request context where needed (timeouts, tracing)
* Handle interruptions properly (do not swallow InterruptedException)
* Domain layer must remain free of concurrency concerns

---

## Runtime Principles

* Stateless services (no shared in-memory state across instances)
* No reliance on local disk for persistence
* Configuration via environment variables
* Logs are structured and treated as event streams
* Services must start fast and shut down gracefully

---

## Authentication

* Use JWT/OAuth2-based authentication
* Validate issuer and audience
* Do not use request context objects (e.g., HttpServletRequest) outside API layer

---

## Testing

* Unit test Domain logic
* Test Application services/use cases
* Prefer integration tests over excessive mocking

---

## Coding Standards

* Prefer immutability where practical
* Avoid global/static mutable state
* Use dependency injection (Spring)
* Prefer explicit, readable code over clever abstractions

---

## Date/Time

* Store timestamps in UTC
* Avoid manual offset calculations
* Use java.time (e.g., Instant, ZonedDateTime) for time handling

---

## Performance

* Avoid N+1 queries
* Use pagination for large datasets
* Prefer projection/DTO queries for read operations

---

## Error Handling

* Use domain/application-specific exceptions
* Map errors to standardized API responses
* Do not expose internal details in production

---

## Logging

* Use structured logging
* Do not log sensitive data
* Log at system boundaries (API, Infrastructure)

---

## Build & Deployment

* Use Maven for dependency and build management
* Build immutable artifacts (JAR)
* Package services as Docker containers
* Do not depend on environment-specific behavior inside the application

---

## Agent Guidelines

When generating code:

* Respect architecture boundaries
* Keep Domain pure
* Place business logic in Domain, orchestration in Application
* Keep API layer thin
* Validate inputs
* Avoid unnecessary abstractions

If uncertain:

* Prefer clarity over cleverness
* Do not assume missing requirements
## Project Overview

Assume a modern Java backend using:

* Spring Boot
* Maven
* Clean Architecture
* Domain-Driven Design (DDD)
* CQRS (where appropriate)
* Docker (containerized deployment)

This file defines **non-negotiable architectural and coding constraints**.

---

## Architecture

Layers:

* Domain
* Application
* Infrastructure
* API

Rules:

* Domain has no dependencies
* Application depends only on Domain
* Infrastructure depends on Application and Domain
* API depends on Application
* No circular dependencies
* Do not violate layer boundaries

---

## Domain

* Encapsulate business logic inside entities
* Avoid anemic domain models
* Use Value Objects for domain concepts
* Enforce invariants inside aggregates
* No framework or infrastructure dependencies
* Domain must remain synchronous and pure

---

## Application

* Use CQRS where it adds clarity:

  * Commands mutate state
  * Queries read state
* One handler/service per use case
* Application layer orchestrates, not implements core business logic
* Business rules belong in Domain
* Validate all inputs
* No business logic in API layer

---

## Infrastructure

* Contains all external concerns (DB, messaging, HTTP, etc.)
* Do not leak persistence or external models outside Infrastructure
* Map persistence models to domain models explicitly
* Configuration must come from environment (no hardcoded secrets)

---

## API Layer

* Controllers must be thin:

  * Accept request
  * Call Application layer
  * Return response
* No business logic or data access
* Use DTOs for input/output
* Return standardized error responses

---

## Security

* Never trust external input; validate all inputs
* Enforce authorization at API/Application boundaries
* Do not expose internal domain or persistence models directly
* Avoid dynamic query construction; use parameterized queries / ORM
* Do not log sensitive data (tokens, passwords, personal data)
* Do not hardcode secrets; use environment/config
* Prefer deny-by-default access control
* Use framework-provided security features (Spring Security); avoid custom implementations

---

## Concurrency & Cancellation

* Do not block threads unnecessarily (avoid blocking I/O in reactive flows)
* Propagate request context where needed (timeouts, tracing)
* Handle interruptions properly (do not swallow InterruptedException)
* Domain layer must remain free of concurrency concerns

---

## Runtime Principles

* Stateless services (no shared in-memory state across instances)
* No reliance on local disk for persistence
* Configuration via environment variables
* Logs are structured and treated as event streams
* Services must start fast and shut down gracefully

---

## Authentication

* Use JWT/OAuth2-based authentication
* Validate issuer and audience
* Do not use request context objects (e.g., HttpServletRequest) outside API layer

---

## Testing

* Unit test Domain logic
* Test Application services/use cases
* Prefer integration tests over excessive mocking

---

## Coding Standards

* Prefer immutability where practical
* Avoid global/static mutable state
* Use dependency injection (Spring)
* Prefer explicit, readable code over clever abstractions

---

## Date/Time

* Store timestamps in UTC
* Avoid manual offset calculations
* Use java.time (e.g., Instant, ZonedDateTime) for time handling

---

## Performance

* Avoid N+1 queries
* Use pagination for large datasets
* Prefer projection/DTO queries for read operations

---

## Error Handling

* Use domain/application-specific exceptions
* Map errors to standardized API responses
* Do not expose internal details in production

---

## Logging

* Use structured logging
* Do not log sensitive data
* Log at system boundaries (API, Infrastructure)

---

## Build & Deployment

* Use Maven for dependency and build management
* Build immutable artifacts (JAR)
* Package services as Docker containers
* Do not depend on environment-specific behavior inside the application

---

## Agent Guidelines

When generating code:

* Respect architecture boundaries
* Keep Domain pure
* Place business logic in Domain, orchestration in Application
* Keep API layer thin
* Validate inputs
* Avoid unnecessary abstractions

If uncertain:

* Prefer clarity over cleverness
* Do not assume missing requirements
