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

1 - Manage users of the platform, their identification/authentification and autorisations
 Initials admin/moderator users (at least one) should be created in the system. They whould have the responsability to manage the fields of study accepted on the platform, suspend or banned users if necessary. Be informed of any complain made about the activity on the platform.

 Users should be able to register for an account on the platform, and access it after verifying their identity via email. During the registration process, they should be able to mention fields of studies they are willing to share about, fields of studies which could be unexisting in the system. User should also be able, once their account creates, define the fields they are open to mentor other peoples on and how many simultaneous mentorship they can manage at most.

2 - Manage types of fields of study.
 Althought fields of study would be created by users, it will be the responsability of admin users to check it and approuve it for a global use in the system. Fields unapproved will only be visible by the user who created it and have an expiration time (parameter define in the system).

3 - Manage articles edition
 Users with active account will have the possibility to writte draft of tutorials on the platform. Once they finished with it, they will link it to some fields of studies (the max number of fields possibily linked to an article will be parameterized in the system). The process of mentoring will then start with them submitting a request to other users to share they thoughts about what they have written.
 A list of availables mentors would be presented to users with the number of articles they approved and lik to those articles if they already have been published.
 The user will then select a number of users (max number of mentor to which the request can be sent will be a parameter) to whom a request of mentorship will be sent.

 Users will also be able to send invitation to non register users to join the platform and approve their article.
 The content of articles may content simple text, formated text (markdown), images, vidéo, link etc... everything allow in a web page.
 Users will have the possibility to save snapshot of their articles for versionning control purpose.

 Once the mentor has accepted the request, he will have access to the article and make comment on it. Comment should be categorize in two principal category (article presentation, pertinence of the content).

 Comments made by the mentor will be available to the user and help him reorganize and improve the quality of his article.
 The user has the liberty to close his article, ending by this all activities on it. The article will be tagged as ready to publish by the system as soon as the required amount of mentor(parameterized in the system) have approved it.


4 - Accept/reject a 'Review article' request made by another user.
 Once having an account on the platform with fields of study interrest to mentorship specified, a user will receive request from other user to review articles.
 If it suite him, he will accept or reject the request. A rejection should always have a comment for an explanation to the other party.

5 - Articles publication management.
 For all articles ready to publish, the user will have the possibility to publish them and they will appear in the articles blogs page.
 A public side of the application (accessible by unregistered user) will give the possibility to users to show case their article for a certain amount of time.
 Registered and unregistered user will have the possibility to rate them. The more an article is appreciated, the more it is likely to stay longer visible (not archive) on the public page.
 A user will also be able to put the link to his article published on other platforms.

5 - Moderation on the platform.
 A user can writte a complaint about a fake or inapropriate article to review. After examination of the complaint by moderators, actions like suspension of a user account can be made.

6 - Browser between articles published and visible by everyone.
The 'Tutorials' page should give the possibility to user to research article by 'Author', 'Field of study', 'Date of publication'. It will be possible to sort by date of publication or by rate.
The articles corresponding to his filters will be shown to him and he will have the liberty to click on the one he want to see it.


### 2.3. Project Objectives (Functional and Non-Functional)
* **Functional Objectives:** 
 - User management,
 - Tutorial edition and publication management.

* **Non-Functional Objectives:** 
 - Security of users personnal datas and articles(accessibility control and backkup). 
 - High availability of the platform (Prevention of DDos).

### 2.4. Added Value and Expected Benefits
* Allow people to work together in order to share some skill with other, with the confidence that it is a well known and approve practice.


### 2.5. Project Scope (Inclusions and Exclusions)
* **Inclusions:** All the functionality mentionned in this document in the project description should be implemented.
* **Exclusions:** Non functional requirement about backup and availability of the service will be excluded for the moment.

---

## 3. Functional Specifications

### 3.1. Use Cases / User Stories
* Detailed description of the interactions between users and the system to achieve specific goals.
* For each use case:
    * **Use Case Name:** Clear and descriptive title.
    * **Primary Actor:** The user or system that initiates the action.
    * **Preconditions:** The state of the system before the execution of the use case.
    * **Nominal Flow:** The normal sequence of steps.
    * **Alternative Flows:** The possible paths in case of different conditions.
    * **Exceptions / Errors:** Abnormal situations and how the system should handle them.
    * **Postconditions:** The state of the system after the execution of the use case.
    * **Priority:** (High, Medium, Low) - Importance of this functionality.
* (Optional) UML use case diagrams.

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
