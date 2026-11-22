-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3307
-- Generation Time: Nov 22, 2026 at 01:32 PM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `alumni_system`
--

-- --------------------------------------------------------

--
-- Table structure for table `admins`
--

CREATE TABLE `admins` (
  `admin_id` varchar(255) NOT NULL,
  `position` varchar(255) NOT NULL,
  `id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `alumni`
--

CREATE TABLE `alumni` (
  `address` varchar(255) DEFAULT NULL,
  `alumni_id` varchar(255) DEFAULT NULL,
  `current_company` varchar(255) DEFAULT NULL,
  `current_position` varchar(255) DEFAULT NULL,
  `date_of_birth` date DEFAULT NULL,
  `degree` varchar(255) DEFAULT NULL,
  `employed` bit(1) NOT NULL,
  `gender` enum('FEMALE','MALE') DEFAULT NULL,
  `graduation_year` int(11) DEFAULT NULL,
  `matric_number` varchar(255) DEFAULT NULL,
  `phone_number` varchar(255) DEFAULT NULL,
  `profile_picture` varchar(255) DEFAULT NULL,
  `id` bigint(20) NOT NULL,
  `department_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `alumni_id_sequence`
--

CREATE TABLE `alumni_id_sequence` (
  `id` int(11) NOT NULL,
  `next_number` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `alumni_skills`
--

CREATE TABLE `alumni_skills` (
  `alumni_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `announcements`
--

CREATE TABLE `announcements` (
  `announcement_id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `message` varchar(3000) NOT NULL,
  `posted_date` datetime(6) NOT NULL,
  `title` varchar(150) NOT NULL,
  `admin_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `departments`
--

CREATE TABLE `departments` (
  `id` bigint(20) NOT NULL,
  `department_name` varchar(255) NOT NULL,
  `faculty` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `employment_history`
--

CREATE TABLE `employment_history` (
  `id` bigint(20) NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `end_date` date DEFAULT NULL,
  `job_position` varchar(255) NOT NULL,
  `start_date` date NOT NULL,
  `alumni_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `events`
--

CREATE TABLE `events` (
  `event_id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `description` varchar(2000) NOT NULL,
  `event_date` date NOT NULL,
  `location` varchar(255) NOT NULL,
  `max_participants` int(11) NOT NULL,
  `title` varchar(255) NOT NULL,
  `admin_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `event_alumni`
--

CREATE TABLE `event_alumni` (
  `event_id` bigint(20) NOT NULL,
  `alumni_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_opportunities`
--

CREATE TABLE `job_opportunities` (
  `job_opportunity_id` bigint(20) NOT NULL,
  `active` bit(1) NOT NULL,
  `application_deadline` date NOT NULL,
  `company_name` varchar(255) NOT NULL,
  `employment_type` varchar(255) NOT NULL,
  `job_description` varchar(2000) NOT NULL,
  `job_title` varchar(255) NOT NULL,
  `location` varchar(255) NOT NULL,
  `posted_date` date NOT NULL,
  `salary` double NOT NULL,
  `created_by_id` bigint(20) DEFAULT NULL,
  `department_id` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `job_skills`
--

CREATE TABLE `job_skills` (
  `id` bigint(20) NOT NULL,
  `required` bit(1) NOT NULL,
  `job_opportunity_id` bigint(20) NOT NULL,
  `skill_id` bigint(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `skills`
--

CREATE TABLE `skills` (
  `id` bigint(20) NOT NULL,
  `description` varchar(500) DEFAULT NULL,
  `skill_name` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` bigint(20) NOT NULL,
  `email` varchar(255) NOT NULL,
  `first_name` varchar(255) NOT NULL,
  `last_name` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `role` enum('ADMIN','ALUMNI','EMPLOYER','STUDENT') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `admins`
--
ALTER TABLE `admins`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKrduos36liihdyhb3g7vpr5qnd` (`admin_id`);

--
-- Indexes for table `alumni`
--
ALTER TABLE `alumni`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK9s7ouctwymwb2e7w26jrovs5u` (`alumni_id`),
  ADD UNIQUE KEY `UKqritt5if7bnaej5ch02ys114i` (`matric_number`),
  ADD KEY `FKrrkykvaooshagau5elwdffv8d` (`department_id`);

--
-- Indexes for table `alumni_id_sequence`
--
ALTER TABLE `alumni_id_sequence`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `alumni_skills`
--
ALTER TABLE `alumni_skills`
  ADD KEY `FKlp7mvdr0owvbhouqnrgwf5wkt` (`skill_id`),
  ADD KEY `FK1ocvd90108aomvqt2od0pljbm` (`alumni_id`);

--
-- Indexes for table `announcements`
--
ALTER TABLE `announcements`
  ADD PRIMARY KEY (`announcement_id`),
  ADD KEY `FKr11nmg3rkt8i1ake5lm9xe05c` (`admin_id`);

--
-- Indexes for table `departments`
--
ALTER TABLE `departments`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKqyf2ekbfpnddm6f3rkgt39i9o` (`department_name`);

--
-- Indexes for table `employment_history`
--
ALTER TABLE `employment_history`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `events`
--
ALTER TABLE `events`
  ADD PRIMARY KEY (`event_id`),
  ADD KEY `FK4cf1auscnsaodjf981arkloc3` (`admin_id`);

--
-- Indexes for table `event_alumni`
--
ALTER TABLE `event_alumni`
  ADD KEY `FKm6h3l86di3nld1r3pbbgf531g` (`alumni_id`),
  ADD KEY `FK22w3799enxbymk2aneuyninrh` (`event_id`);

--
-- Indexes for table `job_opportunities`
--
ALTER TABLE `job_opportunities`
  ADD PRIMARY KEY (`job_opportunity_id`),
  ADD KEY `FKkhwjd6t6c4pkaklqnyi7x567g` (`created_by_id`),
  ADD KEY `FK5tq2pfdtwpa30sh2lx0o0pgmp` (`department_id`);

--
-- Indexes for table `job_skills`
--
ALTER TABLE `job_skills`
  ADD PRIMARY KEY (`id`),
  ADD KEY `FKabfclk480m8g01j4p18daeko0` (`job_opportunity_id`),
  ADD KEY `FKe10ho7um0atjm67b9dgokmfyx` (`skill_id`);

--
-- Indexes for table `skills`
--
ALTER TABLE `skills`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UKqdkr64bxreqdbn6b4k9vumvp3` (`skill_name`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`),
  ADD UNIQUE KEY `UK6dotkott2kjsp8vw4d0m25fb7` (`email`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `announcements`
--
ALTER TABLE `announcements`
  MODIFY `announcement_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `departments`
--
ALTER TABLE `departments`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `employment_history`
--
ALTER TABLE `employment_history`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `events`
--
ALTER TABLE `events`
  MODIFY `event_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `job_opportunities`
--
ALTER TABLE `job_opportunities`
  MODIFY `job_opportunity_id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `job_skills`
--
ALTER TABLE `job_skills`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `skills`
--
ALTER TABLE `skills`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `admins`
--
ALTER TABLE `admins`
  ADD CONSTRAINT `FKanhsicqm3lc8ya77tr7r0je18` FOREIGN KEY (`id`) REFERENCES `users` (`id`);

--
-- Constraints for table `alumni`
--
ALTER TABLE `alumni`
  ADD CONSTRAINT `FKqn4c3cbcqolieu3j40g4s3jhu` FOREIGN KEY (`id`) REFERENCES `users` (`id`),
  ADD CONSTRAINT `FKrrkykvaooshagau5elwdffv8d` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`);

--
-- Constraints for table `alumni_skills`
--
ALTER TABLE `alumni_skills`
  ADD CONSTRAINT `FK1ocvd90108aomvqt2od0pljbm` FOREIGN KEY (`alumni_id`) REFERENCES `alumni` (`id`),
  ADD CONSTRAINT `FKlp7mvdr0owvbhouqnrgwf5wkt` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`);

--
-- Constraints for table `announcements`
--
ALTER TABLE `announcements`
  ADD CONSTRAINT `FKr11nmg3rkt8i1ake5lm9xe05c` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`);

--
-- Constraints for table `events`
--
ALTER TABLE `events`
  ADD CONSTRAINT `FK4cf1auscnsaodjf981arkloc3` FOREIGN KEY (`admin_id`) REFERENCES `admins` (`id`);

--
-- Constraints for table `event_alumni`
--
ALTER TABLE `event_alumni`
  ADD CONSTRAINT `FK22w3799enxbymk2aneuyninrh` FOREIGN KEY (`event_id`) REFERENCES `events` (`event_id`),
  ADD CONSTRAINT `FKm6h3l86di3nld1r3pbbgf531g` FOREIGN KEY (`alumni_id`) REFERENCES `alumni` (`id`);

--
-- Constraints for table `job_opportunities`
--
ALTER TABLE `job_opportunities`
  ADD CONSTRAINT `FK5tq2pfdtwpa30sh2lx0o0pgmp` FOREIGN KEY (`department_id`) REFERENCES `departments` (`id`),
  ADD CONSTRAINT `FKkhwjd6t6c4pkaklqnyi7x567g` FOREIGN KEY (`created_by_id`) REFERENCES `users` (`id`);

--
-- Constraints for table `job_skills`
--
ALTER TABLE `job_skills`
  ADD CONSTRAINT `FKabfclk480m8g01j4p18daeko0` FOREIGN KEY (`job_opportunity_id`) REFERENCES `job_opportunities` (`job_opportunity_id`),
  ADD CONSTRAINT `FKe10ho7um0atjm67b9dgokmfyx` FOREIGN KEY (`skill_id`) REFERENCES `skills` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
