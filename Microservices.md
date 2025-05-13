# IRCTC Microservices Overview

This document provides an overview of all microservices in the IRCTC system.

## Microservices List

1. **irctc_core**
   - The core service handling main business logic including train management, ticket booking, and user operations.
   - Contains modules for train management, ticket booking, user management, and more.

2. **irctc_customer**
   - Customer-facing service handling customer operations and interactions.
   - Likely includes customer profiles, preferences, and other customer-specific functionality.

3. **irctc_discovery**
   - Service discovery server using Eureka for service registration and discovery.
   - Annotated with `@EnableEurekaServer` to act as the discovery service.

4. **irctc_api_gateway**
   - API Gateway service that likely routes requests to appropriate microservices.
   - Acts as the single entry point for all client requests.

5. **irctc_config**
   - Configuration server managing configurations for all microservices.
   - Annotated with `@EnableConfigServer` to provide centralized configuration.

6. **irctc_notification_email**
   - Handles email notifications for the system.
   - Likely integrates with email services to send notifications to users.

## Architecture Notes

- The system follows a microservices architecture with clear separation of concerns.
- Uses Spring Cloud components like Eureka for service discovery and Config Server for centralized configuration.
- Each service is independently deployable and focuses on a specific business capability. 