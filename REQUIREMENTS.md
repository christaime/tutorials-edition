# Software Development Requirements Specification - [Tutorials Editor] 

**Version:** 0.0.1
**Date:** 04/14/2025
**Author(s):** Mamekem Ngueguim Christelle

---

## 1. Introduction

### 1.1. Project Context
In order to help peoples to gain confidence in sharing their technical skills, it whould be great to have a platform enabling them to writte and submit articles to their mentors. After revisions and approvals, thoses articles could be published to the public for the benefit of all.

In the context of a global virtual cloud university where many peoples share their knoledge with who ever want to listen to them, it could be interesting to have a certain control on what you publish by having the guidance of 'elders'. This platform will provide to who ever want to share his knowledge, the approval of other skilled peoples before it is published and visible on the World Wide Web.

NB: Althout an application like this one may already exist, this will serve as a show case of our abilities to produice, deploy and maintain robust J2EE software.


### 1.2. Document Objectives

This document describe the requirements to build the web application. Nothing about his deployment is said here.
Technical tools required, and scope of functionalities to implement will be specify in this document.
The global deadline for the delivery of the first version of the project is 07/30/2025.

### 1.3. Target Audience
* This document is made for Developper and end user of the platform.

---

## 2. Project Presentation

### 2.1. Project Name
* The name of the project is Tutorial Editor.

### 2.2. General Project Description

Tutorial Editor should be able to:

#### a - Manage users of the platform, their identification/authentification and autorisations
 Initials admin/moderator users (at least one) should be created in the system. They whould have the responsability to manage the fields of study accepted on the platform, suspend or banned users if necessary. Be informed of any complain made about the activity on the platform.

 Users should be able to register for an account on the platform, and access it after verifying their identity via email. During the registration process, they should be able to mention fields of studies they are willing to share about, fields of studies which could be unexisting in the system. User should also be able, once their account creates, define the fields they are open to mentor other peoples on and how many simultaneous mentorship they can manage at most.

#### b - Manage types of fields of study.
 Althought fields of study would be created by users, it will be the responsability of admin users to check it and approuve it for a global use in the system. Fields unapproved will only be visible by the user who created it and have an expiration time (parameter define in the system).

#### c - Manage articles edition
 Users with active account will have the possibility to writte draft of tutorials on the platform. Once they finished with it, they will link it to some fields of studies (the max number of fields possibily linked to an article will be parameterized in the system). The process of mentoring will then start with them submitting a request to other users to share they thoughts about what they have written.
 A list of availables mentors would be presented to users with the number of articles they approved and lik to those articles if they already have been published.
 The user will then select a number of users (max number of mentor to which the request can be sent will be a parameter) to whom a request of mentorship will be sent.

 Users will also be able to send invitation to non register users to join the platform and approve their article.
 The content of articles may content simple text, formated text (markdown), images, vidéo, link etc... everything allow in a web page.
 Users will have the possibility to save snapshot of their articles for versionning control purpose.

 Once the mentor has accepted the request, he will have access to the article and make comment on it. Comment should be categorize in two principal category (article presentation, pertinence of the content).

 Comments made by the mentor will be available to the user and help him reorganize and improve the quality of his article.
 The user has the liberty to close his article, ending by this all activities on it. The article will be tagged as ready to publish by the system as soon as the required amount of mentor(parameterized in the system) have approved it.


#### d - Accept/reject a 'Review article' request made by another user.
 Once having an account on the platform with fields of study interrest to mentorship specified, a user will receive request from other user to review articles.
 If it suite him, he will accept or reject the request. A rejection should always have a comment for an explanation to the other party.

#### e - Articles publication management.
 For all articles ready to publish, the user will have the possibility to publish them and they will appear in the articles blogs page.
 A public side of the application (accessible by unregistered user) will give the possibility to users to show case their article for a certain amount of time.
 Registered and unregistered user will have the possibility to rate them. The more an article is appreciated, the more it is likely to stay longer visible (not archive) on the public page.
 A user will also be able to put the link to his article published on other platforms.

#### f - Moderation on the platform.
 A user can writte a complaint about a fake or inapropriate article to review. After examination of the complaint by moderators, actions like suspension of a user account can be made.

#### g - Browser between articles published and visible by everyone.
The 'Tutorials' page should give the possibility to user to research article by 'Author', 'Field of study', 'Date of publication'. It will be possible to sort by date of publication or by rate.
The articles corresponding to his filters will be shown to him and he will have the liberty to click on the one he want to see it.

#### h - Content Monetization & Revenue Distribution
 - The platform identifies high-value content based on engagement metrics (traffic, ratings, and completion rates) to initiate a monetization process.
 - Monetization Opt-in: Authors of high-performing tutorials are invited to monetize their work. The platform provides tiered pricing recommendations to ensure market competitiveness.
 - Access Control: Upon agreement, the tutorial transitions from "Open Access" to "Premium," requiring a one-time purchase for user access.
 - Automated Revenue Sharing: The sale agreement includes a predefined distribution model. Revenue is automatically split between the primary author, the platform, and any designated contributors or validators.
 - Payout Execution: Disbursement to all beneficiaries is handled asynchronously according to their configured payment profiles (e.g., International Credit Card/Bank Transfer).

### 2.3. Project Objectives (Functional and Non-Functional)
* **Functional Objectives:** 
 - User management,
 - Tutorial edition and publication management.
 - Tutorial Monetization

### 2.4. Non-Functional Objectives

- **Data Privacy & Security:** - **Identity & Access Management (IAM):** Strict enforcement of Role-Based Access Control (RBAC) via OpenID Connect (OIDC), ensuring that tutorial content and user data are accessible only to authorized entities.
    - **Data Persistence & Integrity:** Implementation of automated backup strategies and "Point-in-Time Recovery" (PITR) to prevent data loss and ensure long-term durability of published intellectual property.
    - **Encryption:** Encryption of sensitive data at rest and in transit (TLS 1.3) to safeguard user privacy.

- **Platform Availability & Resilience:**
    - **High Availability (HA):** Architectural design focused on zero-single-point-of-failure to ensure the platform remains accessible to global users 24/7.
    - **Infrastructure Hardening:** Mitigation of Distributed Denial of Service (DDoS) attacks and brute-force attempts through network-level rate limiting, firewall optimization (UFW), and automated intrusion detection (e.g., Fail2Ban/Rkhunter).
    - **Performance Optimization:** Use of reactive programming patterns (Angular Signals) and efficient database indexing to maintain low latency even under heavy concurrent loads.

### 2.5. Added Value and Expected Benefits
 - **Collaborative Knowledge Engineering:** Enable experts to co-author content through a structured workflow. This ensures that shared skills are not just documented but are validated and approved by peers, fostering a high level of trust and technical accuracy.
 - **Knowledge-to-Asset Transformation:** Provide a clear pathway to transform high-value intellectual property into a sustainable revenue stream through automated monetization and professional content distribution.

### 2.6. Project Scope (Inclusions and Exclusions)
* **Inclusions:** All the functionality mentionned in this document in the project description should be implemented.
* **Exclusions:** Non functional requirement about backup and availability of the service will be excluded for the moment.

### 2.7. Global System Configuration & Governance

To ensure platform stability, content quality, and resource management, the following global parameters are managed by the System Administrator. These values dictate the behavioral logic of the platform's microservices.

| Parameter Key | Type | Description |
| :--- | :--- | :--- |
| `AUTH_REGISTRATION_ENABLED` | Boolean | Toggles the public registration form. If disabled, new users can only join via invitation or manual admin creation. |
| `MAINTENANCE_MODE` | Boolean | When enabled, all non-admin traffic is redirected to a "System Maintenance" landing page. |
| `TUTORIAL_PUBLISH_AUTO_APPROVE` | Boolean | If False, all tutorials submitted for publication enter a "Pending Review" queue for manual Admin/Validator approval. |
| `TUTORIAL_MAX_PER_USER` | Integer | Limits the number of "Draft" tutorials a single user can maintain to prevent database clutter and resource abuse. |
| `MENTOR_MAX_SIMULTANEOUS_TUTORIALS` | Integer | Restricts the number of active mentorship/validation assignments a user can hold to ensure high-quality feedback. |
| `INVITE_DAILY_LIMIT_PER_USER` | Integer | Anti-spam measure: Maximum number of community invitations a user can dispatch within a 24-hour window. |
| `INVITE_COOLDOWN_PER_RECIPIENT` | Integer (Days) | Prevents harassment by limiting how often the same email address can be targeted for an invitation. |
| `INVITE_GLOBAL_HOURLY_CAP` | Integer | Protects SMTP reputation by capping total platform-wide invitation emails sent per hour. |
| `MONETIZATION_MIN_TRAFFIC_THRESHOLD` | Integer | (Proposed) The minimum number of views a tutorial must reach before the "Monetization Opt-in" becomes available to the author. |
| `MENTOR_MAX_TOPICS_PER_USER` | Integer | Limits how many specific domains a user can claim expertise in. |
| `TUTORIAL_MAX_MENTORS` | Integer | Maximum number of mentors allowed per specific tutorial project. |
| `UNVALIDATED_PUBLISH_LIMIT` | Integer | Max number of tutorials a user can publish without peer-validation. |
| `AI_SAFETY_CHECK_ENABLED` | Boolean | Toggle for the automated coherence and "Harmful Content" AI check. |

---
**Implementation Note:** These parameters should be retrieved by the Frontend at startup (via a Configuration API) to dynamically adjust the UI (e.g., hiding the "Invite" button or the "Register" link).
---

## 3. Functional Specifications

### 3.1. Use Case Catalog

The following use cases define the functional scope of the platform, categorized by their domain of influence.

#### **Module: Authentication & Identity**
- **UC01: User Login/Logout (OIDC):** Standard authentication flow via Keycloak.
- **UC02: Profile Management:** Managing user settings, including payment profiles.
- **UC11: Invite Friend to the Community:** Sending secure, personalized invitations to external users.
- **UC12: Community Registration:** New users joining the platform via an invitation or public link.

#### **Module: Content Authoring (The Editor)**
- **UC03: Create and Structure Tutorial (Draft):** Initializing a new tutorial and its hierarchical structure.
- **UC04: Manage Chapters and Steps:** Organizing the sequence and nesting of learning content.
- **UC05: Content Edition (Markdown/Media):** Writing the actual technical content with code snippets and images.
- **UC13: Collaboration Assignment:** Author assigning a registered user as a Contributor or Validator for a specific tutorial (In-app).

#### **Module: Quality Control & Publishing**
- **UC06: Submit for Validation:** Transitioning a tutorial from "Draft" to "In Review."
- **UC07: Review & Peer Approval:** The process of a Validator or Admin approving or rejecting a tutorial for publication.
- **UC14: Live Update/Version Control:** Handling modifications to already published content.

#### **Module: Monetization & Revenue**
- **UC08: Opt-in for Content Monetization:** High-performing authors transitioning their work to a "Premium" status.
- **UC09: Purchase Premium Access:** Learners purchasing access to tutorials via international credit cards.
- **UC10: Revenue Distribution:** Automated calculation and tracking of earnings for authors, contributors, and the platform.

#### **Module: System Administration**
- **UC15: Manage Global Parameters:** Adjusting rate limits, maintenance mode, and auto-approval toggles.
- **UC16: Audit Log Monitoring:** Reviewing system logs for spam detection or security incidents.

#### Use Case UC11: Invite Friend to the Community

* **Use Case Name:** Invite Friend to the Community
* **Primary Actor:** Authenticated User
* **Preconditions:** * User has a verified account.
    * User has not exceeded `INVITE_DAILY_LIMIT_PER_USER`.
* **Nominal Flow:**
    1.  User triggers the invitation form and enters the recipient's email.
    2.  System checks the local `AuditLog` for `INVITE_COOLDOWN_PER_RECIPIENT`.
    3.  System queries Keycloak to verify if the email is already registered.
    4.  **Condition: Not Registered & Within Limits:**
        - System records the invitation attempt.
        - System dispatches a personalized email via the platform's mail service.
        - System displays a "Success" notification.
* **Alternative Flows:**
    * **User Already Member:** System denies the email dispatch and informs the sender: "This expert is already part of our community!"
    * **Limit Reached:** System disables the "Send" button and informs the user when their limit will reset.
* **Exceptions / Errors:**
    * **E1: SMTP Reputation Protection:** If the global `INVITE_GLOBAL_HOURLY_CAP` is reached, invitations are queued or delayed to prevent being flagged as spam by external providers (Gmail, Outlook).
* **Postconditions:**
    * The platform's outgoing mail reputation is preserved.
    * Community growth is tracked via secure, non-repetitive invitation logs.
* **Priority:** Medium
* (Optional) UML use case diagrams.


### Use Case UC13: Request Project Mentorship

* **Use Case Name:** Request Project Mentorship
* **Primary Actor:** Content Author
* **Preconditions:** * Tutorial is in "Draft" or "Pending Validation" status.
    * Tutorial has an assigned "Topic."
* **Nominal Flow:**
    1. Author accesses the "Find a Mentor" dashboard.
    2. System filters users who have marked themselves "Available for Mentorship" in that specific Topic.
    3. System hides mentors who have reached `MENTOR_MAX_SIMULTANEOUS_TUTORIALS`.
    4. Author reviews mentor metrics (Success rate, bio, language).
    5. Author selects a mentor and clicks "Send Invitation."
    6. System records a "Pending Invitation" in the database.
    7. System sends an in-app notification to the Mentor.
* **Exceptions / Errors:**
    * **E1: Limit Reached:** System prevents the invitation if the tutorial already has the maximum allowed mentors (`TUTORIAL_MAX_MENTORS`).
* **Postconditions:**
    * An invitation is active; the tutorial status is locked to "Awaiting Response."
* **Priority:** High

### Use Case UC19: Respond to Mentorship Invitation

* **Use Case Name:** Respond to Mentorship Invitation
* **Primary Actor:** Potential Mentor
* **Preconditions:** * Mentor has received an invitation for a specific tutorial (UC13).
* **Nominal Flow (Acceptance):**
    1. Mentor views the invitation and the tutorial summary.
    2. Mentor clicks "Accept."
    3. System updates the Tutorial role mapping to include this Mentor.
    4. System notifies the Author.
    5. Tutorial status changes to "Active Mentorship."
* **Alternative Flow (Rejection):**
    1. Mentor clicks "Decline."
    2. System prompts for a "Reason for Rejection" (Optional text).
    3. System records the rejection for mentor-matching analytics.
    4. System notifies the Author and unlocks the "Find a Mentor" dashboard for them to try again.
* **Postconditions:**
    * The invitation is cleared. The system updates the Mentor's "Active Count" to ensure they don't exceed their global limit.
* **Priority:** High

### 3.2. Business Rules
* Description of the constraints, logic, and policies specific to the software's application domain.
* Examples: data validation, specific calculations, mandatory workflows.

### 3.3. Data Management
* Description of the data that will be manipulated by the software.
* Data structure (high level).
* Data flow (creation, reading, modification, deletion).
* Expected data volume.
* Requirements for data storage and security.

### 3.4. User Interfaces (UI)
* Description of the screens, interaction elements, and navigation.
* Mockups (wireframes) or prototypes (low or high fidelity) (to be included in the appendix or link to).
* Principles of ergonomics and accessibility.
* (Optional) Storyboards of complex user flows.

### 3.5. Interfaces with Other Systems (APIs)
* Description of interactions with other software or services.
* Data formats (e.g., JSON, XML).
* Communication protocols (e.g., REST, SOAP).
* Security requirements for data exchange.

---

## 4. Non-Functional Specifications

### 4.1. Performance
* Maximum response time for critical actions.
* Expected number of simultaneous users.
* Load and scalability management.

### 4.2. Security
* Requirements for authentication and authorization.
* Protection against common vulnerabilities (e.g., SQL injection, XSS).
* Management of sensitive data and regulatory compliance (e.g., GDPR).
* Backup and restore procedures.

### 4.3. Usability
* Ease of learning and use for the target users.
* User interface design principles.
* (Optional) Planned usability tests.

### 4.4. Reliability and Availability
* Fault tolerance and recovery mechanisms.
* Maximum tolerable downtime.
* Error monitoring and logging.

### 4.5. Maintainability
* Ease of modification, updating, and bug fixing of the code.
* Coding standards and internal documentation.
* Modularity and extensibility of the architecture.

### 4.6. Portability
* Platforms on which the software must operate (operating systems, browsers, mobile devices).

### 4.7. Accessibility
* Compliance with accessibility standards (e.g., WCAG).
* Consideration of users with disabilities.

### 4.8. Other Non-Functional Specifications
* Any other quality requirements specific to the project.

---

## 5. Constraints and Success Factors

### 5.1. Constraints
* **Technical Constraints:** Limitations related to technologies, existing infrastructures, etc.
* **Budgetary Constraints:** Maximum budget allocated to the project.
* **Time Constraints:** Important deadlines and milestones.
* **Legal and Regulatory Constraints:** Laws, standards, and regulations to be respected.
* **Other Constraints:** Any other significant limitations.

### 5.2. Key Success Factors
* Essential elements that must be present for the project to be considered successful.
* Examples: effective communication, team collaboration, regular validation with users.

---

## 6. Planning and Deadlines (Preliminary Information)

* (Optional) Proposed project phasing (main stages).
* (Optional) Preliminary deadlines for major deliverables.
* (Optional) Identification of dependencies between tasks.

**Note:** A detailed plan will generally be developed in a separate project management document.

---

## 7. Budget (Preliminary Information)

* (Optional) Preliminary cost estimation (development, infrastructure, licenses, etc.).

**Note:** A detailed budget will generally be developed in a separate project management document.

---

## 8. Appendices

* Mockups (wireframes) and prototypes.
* Diagrams (UML, data flow, etc.).
* Glossary of technical and business terms.
* References to other relevant documents.
* Any other useful supplementary information.

---

## 9. Validation and Approval

| Role                       | Name and Surname        | Signature | Date       |
| -------------------------- | -------------------- | --------- | ---------- |
| Client / Sponsor         |                      |           |            |
| Project Manager          |                      |           |            |
| Development Team Lead    |                      |           |            |
| ...                        |                      |           |            |

---
