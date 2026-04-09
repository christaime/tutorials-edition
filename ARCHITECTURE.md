# Technical Specifications & Architectural Choices

This document outlines the "Why" behind the technical decisions made in the Tutorial Editor project, highlighting senior-level software engineering practices.

## 🏗️ Architectural Overview

The system follows a decoupled Microservices-ready architecture, ensuring that the presentation layer and resource server remain independent and scalable.

## 💻 Frontend Architecture: Angular 19

### 1. Angular Signals
- **Choice:** Migration from RxJS-only state to Angular Signals.
- **Reason:** To achieve granular change detection. This reduces the performance overhead in complex editor views where dozens of fields may exist on a single page.

### 2. Functional Keycloak Integration (v19+)
- **Choice:** Implementing `provideKeycloak` and functional Guards.
- **Reason:** To eliminate deprecated class-based providers. This ensures the codebase remains maintainable and aligns with the latest industry standards for standalone Angular applications.
- **Silent SSO:** Implementation of `silent-check-sso.html` ensures session persistence without triggering third-party cookie blocks in modern browsers.

### 3. Strategy-Based AuthGuard
- **Choice:** A custom `AuthGuard` that accepts 'ANY' or 'ALL' role strategies.
- **Reason:** Enhances developer experience by allowing security policies to be defined declaratively in the route metadata without modifying guard logic.

## ⚙️ Backend Architecture: Spring Boot

### 1. Java 21 & Spring Boot 3
- **Choice:** Utilizing Virtual Threads (Project Loom) where applicable.
- **Reason:** To handle high-concurrency content delivery with minimal resource footprint.

### 2. Stateless Security
- **Choice:** JWT-based authentication via Spring Security and Keycloak.
- **Reason:** Ensures the backend remains stateless, allowing for horizontal scaling behind a load balancer.

## 🛡️ Infrastructure & DevOps

### 1. Server Hardening
- **Implementation:** Configuration of `ufw` firewalls, `rkhunter` for rootkit detection, and WireGuard VPN for secure administrative access.
- **Reason:** To demonstrate a "Security-First" mindset, ensuring that the production infrastructure is as robust as the application code.

### 2. Infrastructure as Code (IaC)
- **Tool:** Ansible.
- **Reason:** To ensure reproducible deployments across VPS environments, eliminating "it works on my machine" inconsistencies.

## 🗺️ Roadmap: Monetization Microservice
To support knowledge monetization, a future **Remuneration Service** is planned.
- **Tech:** Spring Boot + International Credit Card Gateway integration.
- **Pattern:** Sidecar or independent microservice to isolate PCI-compliant data from the core content service.
