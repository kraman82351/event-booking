# IRCTC Project - Technical Implementation Highlights

## Core Architecture

1. 6 Spring Boot microservices in monorepo
2. REST APIs
3. Eureka service discovery
4. Spring Cloud Config for centralized configuration
5. API Gateway for routing and auth

## Client Communication

6. Feign clients for service-to-service calls
7. RestTemplate for HTTP requests
8. WebClient for reactive APIs

## Resilience Patterns

9. Resilience4j Circuit Breaker
10. Automatic retry mechanism
11. Global exception handler

## Event-Driven Architecture

12. Kafka integration with consumers/producers
13. Async email notifications with Thymeleaf templates

## Data Management

## Caching

20. Redis caching with Spring Cache abstraction
21. Cache invalidation strategies

## Security

22. JWT authentication implementation
23. Custom authentication manager
24. BCrypt password hashing

## Cross-Cutting Concerns

25. AOP for logging and performance monitoring
26. Custom filters implementation
27. Custom annotations
28. OpenAPI/Swagger documentation

## Performance

29. ThreadPoolExecutor implementation
30. Synchronized blocks for thread safety

31. Profiles

## Scheduling

32. Scheduled cron jobs
33. Thymeleaf email templating

## Shared Components

33. Common Java library for shared DTOs/utils
