# AlumniConnect

**Alumni Career Tracking and Skill Matching Job Recommendation System**

## 1. Project Overview

AlumniConnect is a web-based application developed to support alumni career tracking and skill-based job recommendations. It allows alumni to manage their profiles, record employment history, maintain professional skills and explore job opportunities that match their recorded skills. Administrators can manage alumni records, job opportunities, skills, events and announcements.

## 2. Live Application

**Application URL:** https://alumniconnect-frontend-glni.onrender.com

**Deployment Notice:** The application is hosted on Render. After a period of inactivity, the first request may take a few minutes to respond while the hosting service becomes active. Subsequent requests should respond normally.

## 3. Main Features

### Alumni
- Account registration and login.
- Profile management.
- Professional skills management.
- Employment history management.
- Viewing available job opportunities.
- Skill-based job recommendations.
- Display of matching and missing skills.
- Viewing events and announcements.

### Administrator
- Administrator dashboard.
- Alumni management.
- Job opportunity management.
- Skills management.
- Department management.
- Events management.
- Announcements management.

## 4. Technologies Used

| Component | Technologies |
|---|---|
| Backend | Java 17, Spring Boot 4.0.7 |
| Backend Frameworks | Spring Data JPA, Spring Security |
| Authentication | JWT |
| Frontend | HTML, CSS, JavaScript, Bootstrap 5 |
| Database | MySQL |
| Build Tool | Maven |

## 5. Recommendation Approach

The system compares the skills recorded in an alumnus's profile with the required skills associated with available job opportunities. It calculates a matching percentage based on the proportion of required skills possessed by the alumnus and displays matching and missing skills.

Only active and unexpired job opportunities are considered. The default recommendation threshold is 60%.

## 6. Project Structure

```text
Alumni Project/
│
├── README.md
├── README_HU.md
├── Screenshots/
├── cooladmin/
└── Project/
```

- `cooladmin/` – Frontend application.
- `Project/` – Spring Boot backend.
- `Screenshots/` – Application screenshots.

## 7. Local Installation and Setup

### Requirements
- Java Development Kit (JDK) 17.
- Maven.
- MySQL Server.
- A Java development environment such as IntelliJ IDEA.

### Database Configuration

1. Open the `Project` directory.
2. Navigate to `src/main/resources/`.
3. Copy `application-example.properties` and rename the copy to `application.properties`.
4. Configure the MySQL database connection details.
5. Set the required JWT secret.
6. Create or import the required database tables before starting the application.

**Note:** The application uses `spring.jpa.hibernate.ddl-auto=none`. Therefore, the database schema must be created or imported separately.

### Running the Backend

Open a terminal in the `Project` directory and execute:

```bash
mvn spring-boot:run
```

The backend uses port `8080` by default, unless otherwise configured.

### Running the Frontend

The frontend files are located in the `cooladmin/` directory. The frontend communicates with the backend through RESTful APIs.

For local execution, ensure that the frontend API configuration points to the locally running backend.

## 8. Limitations

The recommendation system depends on the skills and job opportunities available in the database. Its current implementation does not include event registration or an employer interface.

## 9. Evaluation

The system underwent functional testing and recommendation calculation checks. An initial user-based evaluation was also conducted to gather feedback on the system and its job recommendations.

---

**Developed as a Final-Year BSc Computer Science Project.**