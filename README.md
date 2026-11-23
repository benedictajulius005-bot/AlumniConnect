# AlumniConnect
### Design and Implementation of a Spring Boot-Based Alumni Career Tracking and Skill Matching Job Recommendation System

## 1. Project Overview

AlumniConnect is a web-based alumni career tracking and skill-matching job recommendation system developed to support alumni engagement, professional development, and career opportunities.

The system provides a platform where alumni can create and manage their profiles, record employment history, maintain their professional skills, explore job opportunities, and receive job recommendations based on their registered skills.

Administrators can manage alumni records, departments, job opportunities, required skills, events, and announcements through an administrative interface.

The project aims to improve the connection between alumni skills and available employment opportunities through a skill-based job recommendation approach.

## 2. Application Links

- **Live Application:** https://alumniconnect-frontend-glni.onrender.com
- **GitHub Repository:** https://github.com/benedictajulius005-bot/AlumniConnect

**Note:** The application is hosted on Render. The first visit may take some time if the hosting service needs to start the application.

## 3. Main Features

### Alumni Features

- Account registration and login.
- Alumni profile creation and management.
- Employment history management.
- Skills registration and management.
- Browsing available job opportunities.
- Skill-based job recommendations.
- Viewing matching and missing skills for recommended jobs.
- Viewing events and announcements.

### Administrator Features

- Administrative dashboard.
- Alumni account and profile management.
- Job opportunity management.
- Skills management.
- Department management.
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
| Frontend | HTML, CSS and JavaScript |
| UI Framework | Bootstrap 5 |
| Database | MySQL |
| Build Tool | Maven |
| API Testing | Postman |
| Development IDEs | IntelliJ IDEA and Visual Studio Code |

## 5. Job Recommendation Approach

AlumniConnect uses a skill-based matching approach to recommend job opportunities to alumni.

The recommendation system compares the skills registered in an alumnus's profile with the skills required by available job opportunities.

The system calculates a matching percentage and identifies:

- **Matching skills:** Skills that an alumnus possesses and that are required by a job opportunity.
- **Missing skills:** Skills required by a job opportunity that are not registered in the alumnus's profile.
- **Match percentage:** The percentage of required skills that match the alumnus's registered skills.

The default recommendation threshold is 60%. Only active job opportunities with application deadlines that have not passed are considered for recommendations.

This approach helps alumni identify job opportunities that are relevant to their existing skills and recognise areas where additional skills may be required.

## 6. Project Structure

The project is organised into the following main directories:

```text
AlumniConnect/
│
├── Project/                         # Spring Boot backend
│   ├── src/
│   │   ├── main/
│   │   │   ├── java/
│   │   │   │   └── Alumni/Project/
│   │   │   │       ├── controller/
│   │   │   │       ├── dto/
│   │   │   │       ├── entity/
│   │   │   │       ├── enums/
│   │   │   │       ├── exception/
│   │   │   │       ├── repository/
│   │   │   │       ├── security/
│   │   │   │       ├── service/
│   │   │   │       └── util/
│   │   │   └── resources/
│   │   └── test/
│   ├── pom.xml
│   ├── mvnw
│   └── mvnw.cmd
│
├── cooladmin/                       # Frontend application
│   ├── css/
│   ├── js/
│   ├── images/
│   ├── vendor/
│   ├── login.html
│   ├── register.html
│   ├── profile.html
│   ├── jobs.html
│   ├── recommendations.html
│   └── ...
│
├── database/
│   ├── alumni_system_schema.sql
│   └── sample_data.sql
│
├── Screenshots/                     # Application screenshots
│
├── README.md
└── README_HU.md
```

## 7. Installation and Setup

Follow these instructions to run AlumniConnect locally.

### Prerequisites

Ensure the following software is installed:

- Java Development Kit (JDK) 17.
- MySQL Server or MariaDB.
- Visual Studio Code.
- Visual Studio Code Live Server extension.
- IntelliJ IDEA or another Java IDE (optional).
- A modern web browser.

Maven can be installed separately, or the Maven Wrapper included in the project can be used.

### Step 1: Clone the Repository

Open a terminal and execute:

```bash
git clone https://github.com/benedictajulius005-bot/AlumniConnect.git
cd AlumniConnect
```

### Step 2: Create the Database

Open MySQL or phpMyAdmin and create a database named `alumni_system`.

```sql
CREATE DATABASE alumni_system;
```

### Step 3: Import the Database Files

The `database` directory contains two SQL files:

- **alumni_system_schema.sql:** Contains the database structure, including tables, primary keys, indexes, and foreign-key relationships.
- **sample_data.sql:** Contains fictional demonstration records for testing the application.

Import the files in the following order:

1. Import `alumni_system_schema.sql`.
2. Import `sample_data.sql`.

Both files should be imported into the `alumni_system` database.

You can use phpMyAdmin or the MySQL command line to import the files.

**Important:** The database schema must be imported before starting the backend because Hibernate is configured not to generate the database tables automatically.

### Step 4: Configure the Backend

Navigate to:

```text
Project/src/main/resources/
```

Locate `application-example.properties` and make a copy named:

```text
application.properties
```

Open the new `application.properties` file and configure your local database connection.

Example configuration:

```properties
server.port=8080
spring.application.name=Project

# Database Configuration
spring.datasource.url=jdbc:mysql://localhost:3306/alumni_system?useSSL=false&serverTimezone=UTC
spring.datasource.username=root
spring.datasource.password=YOUR_DATABASE_PASSWORD
spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver

# JPA / Hibernate Configuration
spring.jpa.hibernate.ddl-auto=none
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.open-in-view=false

# Job Recommendation Configuration
app.recommendation.threshold=60

# JWT Configuration
app.jwt.secret=REPLACE_WITH_YOUR_OWN_SECRET
app.jwt.expiration=86400000
```

Adjust the database port, username, and password to match your local MySQL configuration.

Replace the JWT secret with your own secure secret.

**Security note:** Never upload your actual `application.properties` file, database password, or JWT secret to GitHub.

### Step 5: Run the Backend

Open the `Project` directory in IntelliJ IDEA or open a terminal inside that directory.

Using the Maven Wrapper on Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

Alternatively, if Maven is installed:

```bash
mvn spring-boot:run
```

When the application starts successfully, the backend will be available at:

```text
http://localhost:8080
```

Keep the backend running while using the frontend locally.

### Step 6: Run the Frontend

The frontend application is located in the `cooladmin` directory.

To run it using Visual Studio Code:

1. Open the `AlumniConnect` project folder in Visual Studio Code.
2. Open the `cooladmin` folder.
3. Install the **Live Server** extension if it is not already installed.
4. Locate `login.html`.
5. Right-click `login.html` and select **Open with Live Server**.
6. The frontend will open in your default web browser.

The frontend uses `cooladmin/js/api-config.js` to determine the backend address automatically.

- When running locally through `localhost` or `127.0.0.1`, it connects to `http://localhost:8080`.
- When running online, it connects to the deployed backend.

No manual API address changes are required when switching between local and online use.

**Note:** The frontend is a static HTML, CSS, and JavaScript application. It does not require an npm installation or build command.

## 8. Database Design

The MySQL database is named `alumni_system` and contains 13 tables.

| Table | Description |
|---|---|
| `users` | Stores user accounts and roles. |
| `admins` | Stores administrator information. |
| `alumni` | Stores alumni profile information. |
| `departments` | Stores academic department information. |
| `alumni_id_sequence` | Manages the alumni ID sequence. |
| `skills` | Stores available skills. |
| `alumni_skills` | Connects alumni profiles with registered skills. |
| `employment_history` | Stores alumni employment records. |
| `job_opportunities` | Stores available job opportunities. |
| `job_skills` | Connects job opportunities with their required skills. |
| `events` | Stores alumni events. |
| `event_alumni` | Represents relationships between alumni and events. |
| `announcements` | Stores system announcements. |

The database uses primary keys and foreign-key relationships to maintain data integrity and establish relationships between the different entities.

## 9. Testing and Evaluation

The application was tested during development to assess the functionality of its main features and the interaction between the frontend, backend, and database.

### Functional Testing

Postman was used to test the backend REST API endpoints during development.

The implemented application features were also checked, including:

- User registration and authentication.
- Alumni profile management.
- Employment history management.
- Skills management.
- Job opportunity management.
- Job recommendation functionality.
- Events and announcements management.

### Recommendation Evaluation

The relevance of the job recommendations was evaluated with five participants.

The recommendation system received an average relevance rating of **2.8 out of 5**.

This result provides an initial indication of how participants perceived the relevance of the recommendations. However, the evaluation involved a small number of participants, so the findings should not be treated as conclusive evidence of recommendation effectiveness.

Further evaluation with a larger group of users and additional recommendation criteria could improve the assessment.

## 10. Implementation Status and Limitations

The following features have been implemented:

- Alumni account registration and login.
- Alumni profile management.
- Employment history management.
- Skills management.
- Job opportunity management.
- Skill-based job recommendations.
- Events management.
- Announcements management.
- Administrative management features.

The following features are not included in the current implementation:

- Event registration by alumni.
- A separate employer interface.

These features may be considered for further development as part of future work.

## 11. Screenshots

The `Screenshots` directory contains screenshots demonstrating the application's user interface and implemented features.

The screenshots include:

- Login and registration pages.
- Alumni dashboard.
- Alumni profile.
- Employment history.
- Skills management.
- Job opportunities.
- Job recommendations.
- Events and announcements.
- Administrative dashboard and management pages.

## 12. Future Work

Possible areas for future development include:

- Implementing event registration functionality.
- Developing a separate employer interface.
- Improving the job recommendation approach.
- Conducting further evaluation with a larger group of participants.
- Introducing additional features to support alumni career development and engagement.

## 13. Project Information

**Project Title:** Design and Implementation of a Spring Boot-Based Alumni Career Tracking and Skill Matching Job Recommendation System.

**Project Type:** Final-Year BSc Computer Science Project.

**Backend:** Java and Spring Boot.

**Frontend:** HTML, CSS, JavaScript and Bootstrap 5.

**Database:** MySQL.

**Repository:** https://github.com/benedictajulius005-bot/AlumniConnect