# Customer Support ChatBot (Spring Boot + Groq AI + WebSockets)

## Project Overview
Customer Support ChatBot is a full-stack, enterprise-grade AI assistant application designed to provide instantaneous, context-aware automated chat resolutions. Powered by high-speed open-source Large Language Models (LLMs) via Groq Cloud infrastructure, the application stores conversations persistently in a cloud database and streams messages dynamically in real time without screen reloads.

👉 **Live Demo:** [https://support-chatbot-l4kc.onrender.com](https://support-chatbot-l4kc.onrender.com)

---

## Technologies Used
* **Java 17** & **Spring Boot (3.x)** - Main backend application framework.
* **Spring AI** - Core abstraction interface re-routed to handle Groq Cloud completions.
* **Spring Security** - Stateful form-based authentication and granular role authorization.
* **Spring Data JPA** - Object-Relational Mapping pipeline connected to the data layer.
* **WebSockets (STOMP + SockJS)** - Low-latency bidirectional persistent connection layer.
* **Thymeleaf** - Server-side Java template engine rendering UI components.
* **Neon PostgreSQL** - Fully managed serverless cloud transactional database.
* **Docker** - Lightweight multi-stage builds running on resource-optimized virtualization layers.
* **Maven** - Project dependency and lifecycle management system.
* **Bootstrap 5** - Modern front-end framework engineered for cross-device viewport continuity.

---

## Architecture
```text
Client (Browser UI) ←——[WebSockets / STOMP]——→ Controller (WebSocket Handler)
                                                      ↓
                                                Service Layer
                                             🔀 /             \ 🔌
                                 (Groq Cloud AI via API)    (Neon PostgreSQL via JPA)
```
* **Security Filter Chain** - Intercepts incoming requests to manage sessions and authorize endpoint privileges.
* **WebSocket Controller** - Handles low-latency messaging frames rather than traditional HTTP polling.
* **Service Layer** - Integrates custom context orchestration, history processing, and Groq AI stream routing.
* **Repository Layer** - Seamlessly maps data snapshots into a remote, secure PostgreSQL serverless instance.
* **Responsive View Layer (Thymeleaf)** - Adapts on-the-fly to serve optimized visual displays across all devices.

---

## Features
* **Real-Time Streaming** - Built-in WebSockets (STOMP) allow users to chat instantly without refresh lag.
* **Groq Cloud AI Core** - Integrates ultra-fast inference using the `llama-3.1-8b-instant` open-source model.
* **Context Preservation** - Fetches past chat histories smoothly from the database every session refresh.
* **Role-Based Authorization** - Secure logins separating USER permissions from ADMIN permissions.
* **Database Management** - Full capability to wipe local records using a "Clear History" protocol.
* **Hybrid Layout Responsiveness** - Adaptive grid elements displaying perfectly on all screen sizes.

---

## Database
* Neon PostgreSQL serverless cloud instance.
* Hibernate automatic DDL management lifecycle schema synchronization.
* **User Entity** - Stores authenticated encrypted identity profiles, usernames, and system permission roles.
* **ChatMessage Entity** - Tracks conversational sequence logs containing sender states, message strings, and timestamps.

---

## Security & Session Management
* Production-ready protection configured through Spring Security architectures.
* Secure password encoding policies out of the box.
* Dynamic configuration variables utilizing environment parameters (`${GROQ_API_KEY}` and `${NEON_DB_PASSWORD}`).
* Comprehensive session control workflows featuring automated invalidations upon logout.

---

## API / Web Flow
| Route | Description |
|---|---|
| `/login` | Fully responsive user sign-in portal |
| `/register` | Permission-selection credential enrollment page |
| `/chat` | Main real-time responsive chat dashboard |
| `/admin` | Control center auditing global database logs |
| `/logout` | Securely terminates user session |
| `ws://.../ws-chat` | Handshake route initializing WebSocket channels |

---

## Deployment
* **Containerization** - Custom two-stage Docker container structure using `eclipse-temurin:17-jre-alpine`.
* **Render Web Hosting** - Linked via GitHub CI/CD pipeline triggering automated redeployments on every commit.
* **Memory Optimization** - Configured with explicit JVM bounds (`-Xmx512m` / `-Xmx300m`) for free-tier performance.

---

## API Testing
Integrated message validation frameworks audited via browser developer networks, terminal log outputs, and remote Neon SQL statement monitors for comprehensive system verification.

---

## Author
**Prathmesh Dalavi**
