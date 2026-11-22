# AlumniConnect
### Alumni Career Tracking and Skill Matching Job Recommendation System

## 1. Project Overview

AlumniConnect is a web-based system developed to support alumni career tracking, professional engagement and skill-based job recommendations.

The system allows alumni to create and manage their profiles, record employment history, register skills, explore job opportunities and receive job recommendations based on their skills.

Administrators can manage alumni records, departments, job opportunities, skills, events and announcements through an administrative interface.

## 2. Application Links

- **Live Application:** https://alumniconnect-frontend-glni.onrender.com
- **GitHub Repository:** https://github.com/benedictajulius005-bot/AlumniConnect

*Note: The live application is hosted on Render. The first visit may take some time if the hosting service needs to restart the application.*

## 3. Main Features

**Alumni**
- Account registration and login.
- Profile management.
- Employment history management.
- Skills management.
- Job opportunity browsing.
- Skill-based job recommendations.
- Viewing matching and missing skills.
- Access to events and announcements.

**Administrator**
- Administrative dashboard.
- Alumni management.
- Job opportunity management.
- Skills and department management.
- Events management.
- Announcements management.

## 4. Technology Stack

| Component | Technology |
|---|---|
| Programming Language | Java 17 |
| Backend Framework | Spring Boot 4.0.7 |
| Backend Architecture | REST API |
| Data Access | Spring Data JPA / Hibernate |
| Security | Spring Security and JWT |
| Frontend | HTML, CSS, JavaScript |
| UI Framework | Bootstrap 5 |
| Database | MySQL |
| Build Tool | Maven |

## 5. Job Recommendation Approach

AlumniConnect uses a skill-based matching approach to recommend job opportunities.

The system compares the skills registered in an alumnus's profile with the skills required by available job opportunities.

It calculates a matching percentage and identifies matching and missing skills. The default recommendation threshold is 60%. Only active job opportunities that have not passed their application deadlines are considered.

## 6. Project Structure

```text
AlumniConnect/
│
├── Project/                 # Spring Boot backend
│   ├── src/
│   ├── pom.xml
│   └── ...
│
├── cooladmin/               # Frontend application
│
├── database/
│   ├── alumni_system_schema.sql
│   └── sample_data.sql
│
├── Screenshots/             # Application screenshots
│
├── README.md
└── README_HU.md
```

## 7. Installation and Setup

### Prerequisites

- JDK 17
- Maven
- MySQL Server or MariaDB
- IntelliJ IDEA or another Java IDE
- A modern web browser

### Step 1: Clone the Repository

```bash
git clone https://github.com/benedictajulius005-bot/AlumniConnect.git
cd AlumniConnect
```

### Step 2: Create the Database

Create a database named `alumni_system` in MySQL:

```sql
CREATE DATABASE alumni_system;
```

### Step 3: Import the Database Files

The `database` directory contains two SQL files:

- **alumni_system_schema.sql:** Contains the database structure, including the 13 tables, indexes and relationships.
- **sample_data.sql:** Contains fictional demonstration records for testing.

Import `alumni_system_schema.sql` first, followed by `sample_data.sql`, into the `alumni_system` database using phpMyAdmin or the MySQL command line.

### Step 4: Configure the Backend

Navigate to:

```text
Project/src/main/resources/
```

Copy `application-example.properties` and rename the copy to `application.properties`.

Configure your local database connection and JWT secret.

Example configuration:

```properties
server.port=8080
spring.application.name=Project

spring.datasource.url=jdbc:mysql://localhost:3306/alumni_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect

spring.jpa.open-in-view=false

app.recommendation.threshold=60
app.jwt.secret=REPLACE_WITH_YOUR_OWN_SECRET
app.jwt.expiration=86400000
```

Adjust the database port, username and password to match your local MySQL configuration.

**Security note:** Do not upload your actual `application.properties` file, database credentials or JWT secret to GitHub.

### Step 5: Run the Backend

Open a terminal inside the `Project` directory and execute:

```bash
mvn spring-boot:run
```

The backend will run at:

```text
http://localhost:8080
```

### Step 6: Run the Frontend

The frontend application is located in the `cooladmin` directory.

Open it using a suitable local development server and ensure its API configuration points to the locally running backend.

## 8. Database Design

The MySQL database, `alumni_system`, contains 13 tables:

| Table | Purpose |
|---|---|
| users | User accounts and roles |
| admins | Administrator information |
| alumni | Alumni profiles |
| departments | Department information |
| alumni_id_sequence | Alumni ID sequence |
| skills | Available skills |
| alumni_skills | Alumni-skill relationships |
| employment_history | Employment records |
| job_opportunities | Job listings |
| job_skills | Job-required skills |
| events | Alumni events |
| event_alumni | Event-alumni relationships |
| announcements | System announcements |

The database uses primary keys and foreign-key relationships to maintain data integrity.

## 9. Implementation Scope and Limitations

The current implementation includes alumni registration and authentication, profile management, employment history, skills management, job opportunities, job recommendations, events and announcements.

Event registration and an employer interface are not included in the current implementation and may be considered for future development.

## 10. Screenshots

The `Screenshots` directory contains images demonstrating the application's interface and implemented features.

## 11. Project Information

**Project Title:** Design and Implementation of a Spring Boot-Based Alumni Career Tracking and Skill Matching Job Recommendation System.

**Project Type:** Final-Year BSc Computer Science Project.

**Backend:** Java Spring Boot.

**Frontend:** HTML, CSS, JavaScript and Bootstrap 5.

**Database:** MySQL.