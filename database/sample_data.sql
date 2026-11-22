-- AlumniConnect fictional sample data
-- Import this AFTER alumni_system_schema.sql.
-- All names, email addresses, companies, and records below are fictional.
-- Demo password for the two alumni and admin accounts: AlumniDemo123!
-- The password value is a BCrypt hash compatible with Spring Security.

INSERT INTO `users` (`id`, `email`, `first_name`, `last_name`, `password`, `role`) VALUES
(1, 'admin.demo@example.com', 'Alex', 'Morgan', '$2y$10$I6geP/wgEXuf1r17KOdhCuexBXFfZEmhLwVu2F0iDSq3qdTVuDQWK', 'ADMIN'),
(2, 'jordan.lee@example.com', 'Jordan', 'Lee', '$2y$10$I6geP/wgEXuf1r17KOdhCuexBXFfZEmhLwVu2F0iDSq3qdTVuDQWK', 'ALUMNI'),
(3, 'taylor.reed@example.com', 'Taylor', 'Reed', '$2y$10$I6geP/wgEXuf1r17KOdhCuexBXFfZEmhLwVu2F0iDSq3qdTVuDQWK', 'ALUMNI');

INSERT INTO `departments` (`id`, `department_name`, `faculty`) VALUES
(1, 'Computer Science', 'Faculty of Computing');

INSERT INTO `admins` (`admin_id`, `position`, `id`) VALUES
('ADM-DEMO-001', 'System Administrator', 1);

INSERT INTO `alumni` (`address`, `alumni_id`, `current_company`, `current_position`, `date_of_birth`, `degree`, `employed`, `gender`, `graduation_year`, `matric_number`, `phone_number`, `profile_picture`, `id`, `department_id`) VALUES
('Demo Street, Example City', 'ALU-DEMO-001', 'Example Technologies', 'Junior Software Developer', '2000-04-15', 'BSc Computer Science', b'1', 'FEMALE', 2024, 'DEMO/CS/001', '+00000000001', NULL, 2, 1),
('Sample Avenue, Example City', 'ALU-DEMO-002', 'Northstar Analytics', 'Data Analyst', '1999-09-20', 'BSc Computer Science', b'1', 'MALE', 2023, 'DEMO/CS/002', '+00000000002', NULL, 3, 1);

INSERT INTO `alumni_id_sequence` (`id`, `next_number`) VALUES
(1, 3);

INSERT INTO `skills` (`id`, `description`, `skill_name`) VALUES
(1, 'Programming with Java', 'Java'),
(2, 'Relational database querying', 'SQL'),
(3, 'Building web applications', 'Spring Boot'),
(4, 'Data analysis and reporting', 'Data Analysis'),
(5, 'Working with Python', 'Python');

INSERT INTO `alumni_skills` (`alumni_id`, `skill_id`) VALUES
(2, 1), (2, 2), (2, 3),
(3, 2), (3, 4), (3, 5);

INSERT INTO `employment_history` (`id`, `company_name`, `end_date`, `job_position`, `start_date`, `alumni_id`) VALUES
(1, 'Example Technologies', NULL, 'Junior Software Developer', '2024-07-01', 2),
(2, 'Northstar Analytics', NULL, 'Data Analyst', '2023-09-01', 3);

INSERT INTO `job_opportunities` (`job_opportunity_id`, `active`, `application_deadline`, `company_name`, `employment_type`, `job_description`, `job_title`, `location`, `posted_date`, `salary`, `created_by_id`, `department_id`) VALUES
(1, b'1', '2027-06-30', 'BrightPath Software', 'Full-time', 'Support the development and maintenance of web applications.', 'Junior Java Developer', 'Example City', '2026-11-01', 35000, 1, 1),
(2, b'1', '2027-05-31', 'InsightWorks', 'Full-time', 'Prepare datasets, analyse trends, and create reports for business teams.', 'Graduate Data Analyst', 'Example City', '2026-11-05', 32000, 1, 1);

INSERT INTO `job_skills` (`id`, `required`, `job_opportunity_id`, `skill_id`) VALUES
(1, b'1', 1, 1), (2, b'1', 1, 2), (3, b'1', 1, 3),
(4, b'1', 2, 2), (5, b'1', 2, 4), (6, b'0', 2, 5);

INSERT INTO `announcements` (`announcement_id`, `active`, `message`, `posted_date`, `title`, `admin_id`) VALUES
(1, b'1', 'This is a demonstration announcement containing fictional information for testing the AlumniConnect application.', '2026-11-10 09:00:00.000000', 'Welcome to AlumniConnect', 1);

INSERT INTO `events` (`event_id`, `active`, `description`, `event_date`, `location`, `max_participants`, `title`, `admin_id`) VALUES
(1, b'1', 'A fictional networking event for demonstration and testing purposes.', '2027-03-15', 'Example Conference Centre', 100, 'Alumni Networking Day', 1);

-- event_alumni is intentionally empty because event registration is not implemented.
