-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: May 18, 2025 at 02:37 AM
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
-- Database: `voiceout`
--

-- --------------------------------------------------------

--
-- Table structure for table `archived_posts`
--

CREATE TABLE `archived_posts` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `content` text NOT NULL,
  `donation_goal` decimal(10,2) DEFAULT NULL,
  `donation_received` decimal(10,2) DEFAULT NULL,
  `likes` int(11) DEFAULT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp(),
  `archived_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `deleted_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `feedback`
--

CREATE TABLE `feedback` (
  `feedback_id` int(11) NOT NULL,
  `feedback_content` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `feedback`
--

INSERT INTO `feedback` (`feedback_id`, `feedback_content`) VALUES
(1, 'dfghjk'),
(2, 'dsssssssssssss'),
(3, 'Ey\n'),
(4, 'gfgfg');

-- --------------------------------------------------------

--
-- Table structure for table `site_donation`
--

CREATE TABLE `site_donation` (
  `donation_received` bigint(20) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `site_donation`
--

INSERT INTO `site_donation` (`donation_received`) VALUES
(6088),
(6088);

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `user_id` int(11) NOT NULL,
  `username` varchar(100) NOT NULL,
  `password` varchar(100) NOT NULL,
  `contact_num` varchar(20) NOT NULL,
  `gender` varchar(100) NOT NULL,
  `age` int(11) NOT NULL,
  `email` varchar(255) NOT NULL,
  `address` text NOT NULL,
  `name` varchar(255) NOT NULL,
  `creation_date` timestamp NOT NULL DEFAULT current_timestamp(),
  `liked_posts` text DEFAULT ''
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`user_id`, `username`, `password`, `contact_num`, `gender`, `age`, `email`, `address`, `name`, `creation_date`, `liked_posts`) VALUES
(39, '222222', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', '09128933333', 'Male', 23, 'fjdjk@gmail.com', 'wdfjkbwjdb', 'Royette', '2025-05-14 08:01:12', ',213,208,210,212,209,206'),
(40, '333333', '22823156469229858f46ac3c7950b011b981f5dd8bd5ed6908b3f932a6f4d258', '09123333333', 'Female', 213, 'wqedqw@gmail.com', 'qwdqwqweqwe', 'Jehlia', '2025-05-14 08:08:15', ''),
(41, '111111', '22823156469229858f46ac3c7950b011b981f5dd8bd5ed6908b3f932a6f4d258', '09213333333', 'Male', 213, 'afdsmkx@gmail.com', 'elo', 'Royette', '2025-05-15 12:32:01', '');

-- --------------------------------------------------------

--
-- Table structure for table `user_comment`
--

CREATE TABLE `user_comment` (
  `comment_id` int(11) NOT NULL,
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `comment_content` text NOT NULL,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `user_post`
--

CREATE TABLE `user_post` (
  `post_id` int(11) NOT NULL,
  `user_id` int(11) NOT NULL,
  `name` varchar(255) NOT NULL,
  `content` text NOT NULL,
  `donation_goal` decimal(10,2) DEFAULT 0.00,
  `donation_received` decimal(10,2) DEFAULT 0.00,
  `likes` int(11) DEFAULT 0,
  `created_at` timestamp NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_general_ci;

--
-- Dumping data for table `user_post`
--

INSERT INTO `user_post` (`post_id`, `user_id`, `name`, `content`, `donation_goal`, `donation_received`, `likes`, `created_at`) VALUES
(214, 39, 'Royette', 'Ey', 12.00, 0.00, 0, '2025-05-14 09:07:01'),
(215, 39, 'Royette', '1', 0.00, 0.00, 0, '2025-05-14 09:07:03'),
(216, 39, 'Royette', '2', 0.00, 0.00, 0, '2025-05-14 09:07:06'),
(217, 39, 'Royette', '3', 0.00, 0.00, 0, '2025-05-14 09:07:08'),
(218, 39, 'Royette', '4\n', 0.00, 0.00, 0, '2025-05-14 09:07:11'),
(219, 39, 'Royette', 'Post Week 1 - A', 0.00, 0.00, 0, '2025-01-30 14:41:10'),
(220, 39, 'Royette', 'Post Week 1 - B', 0.00, 0.00, 0, '2025-01-30 14:41:10'),
(221, 39, 'Royette', 'Post Week 2 - A', 0.00, 0.00, 0, '2025-02-06 14:41:10'),
(222, 39, 'Royette', 'Post Week 2 - B', 0.00, 0.00, 0, '2025-02-06 14:41:10'),
(223, 39, 'Royette', 'Post Week 3 - A', 0.00, 0.00, 0, '2025-02-13 14:41:10'),
(224, 39, 'Royette', 'Post Week 3 - B', 0.00, 0.00, 0, '2025-02-13 14:41:10'),
(225, 39, 'Royette', 'Post Week 4 - A', 0.00, 0.00, 0, '2025-02-20 14:41:10'),
(226, 39, 'Royette', 'Post Week 4 - B', 0.00, 0.00, 0, '2025-02-20 14:41:10'),
(227, 39, 'Royette', 'Post Week 5 - A', 0.00, 0.00, 0, '2025-02-27 14:41:10'),
(228, 39, 'Royette', 'Post Week 5 - B', 0.00, 0.00, 0, '2025-02-27 14:41:10'),
(229, 39, 'Royette', 'Post Week 6 - A', 0.00, 0.00, 0, '2025-03-06 14:41:10'),
(230, 39, 'Royette', 'Post Week 6 - B', 0.00, 0.00, 0, '2025-03-06 14:41:10'),
(231, 39, 'Royette', 'Post Week 7 - A', 0.00, 0.00, 0, '2025-03-13 14:41:10'),
(232, 39, 'Royette', 'Post Week 7 - B', 0.00, 0.00, 0, '2025-03-13 14:41:10'),
(233, 39, 'Royette', 'Post Week 8 - A', 0.00, 0.00, 0, '2025-03-20 14:41:10'),
(234, 39, 'Royette', 'Post Week 8 - B', 0.00, 0.00, 0, '2025-03-20 14:41:10'),
(235, 39, 'Royette', 'Post Week 9 - A', 0.00, 0.00, 0, '2025-03-27 14:41:10'),
(236, 39, 'Royette', 'Post Week 9 - B', 0.00, 0.00, 0, '2025-03-27 14:41:10'),
(237, 39, 'Royette', 'Post Week 10 - A', 0.00, 0.00, 0, '2025-04-03 14:41:10'),
(238, 39, 'Royette', 'Post Week 10 - B', 0.00, 0.00, 0, '2025-04-03 14:41:10'),
(239, 39, 'Royette', 'Post Week 11 - A', 0.00, 0.00, 0, '2025-04-10 14:41:10'),
(240, 39, 'Royette', 'Post Week 11 - B', 0.00, 0.00, 0, '2025-04-10 14:41:10'),
(241, 39, 'Royette', 'Post Week 12 - A', 0.00, 0.00, 0, '2025-04-17 14:41:10'),
(242, 39, 'Royette', 'Post Week 12 - B', 0.00, 0.00, 0, '2025-04-17 14:41:10'),
(243, 39, 'Royette', 'Post Week 13 - A', 0.00, 0.00, 0, '2025-04-24 14:41:10'),
(244, 39, 'Royette', 'Post Week 13 - B', 0.00, 0.00, 0, '2025-04-24 14:41:10'),
(245, 39, 'Royette', 'Post Week 14 - A', 0.00, 0.00, 0, '2025-05-01 14:41:10'),
(246, 39, 'Royette', 'Post Week 14 - B', 0.00, 0.00, 0, '2025-05-01 14:41:10'),
(247, 39, 'Royette', 'Post Week 15 - A', 0.00, 0.00, 0, '2025-05-08 14:41:10'),
(248, 39, 'Royette', 'Post Week 15 - B', 0.00, 0.00, 0, '2025-05-08 14:41:10');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `archived_posts`
--
ALTER TABLE `archived_posts`
  ADD PRIMARY KEY (`post_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `feedback`
--
ALTER TABLE `feedback`
  ADD PRIMARY KEY (`feedback_id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`user_id`),
  ADD UNIQUE KEY `username` (`username`),
  ADD UNIQUE KEY `email` (`email`);

--
-- Indexes for table `user_comment`
--
ALTER TABLE `user_comment`
  ADD PRIMARY KEY (`comment_id`),
  ADD KEY `post_id` (`post_id`),
  ADD KEY `user_id` (`user_id`);

--
-- Indexes for table `user_post`
--
ALTER TABLE `user_post`
  ADD PRIMARY KEY (`post_id`),
  ADD KEY `user_post_ibfk_1` (`user_id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `feedback`
--
ALTER TABLE `feedback`
  MODIFY `feedback_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT for table `users`
--
ALTER TABLE `users`
  MODIFY `user_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT for table `user_comment`
--
ALTER TABLE `user_comment`
  MODIFY `comment_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `user_post`
--
ALTER TABLE `user_post`
  MODIFY `post_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=249;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `archived_posts`
--
ALTER TABLE `archived_posts`
  ADD CONSTRAINT `archived_posts_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `user_comment`
--
ALTER TABLE `user_comment`
  ADD CONSTRAINT `user_comment_ibfk_1` FOREIGN KEY (`post_id`) REFERENCES `user_post` (`post_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `user_comment_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE;

--
-- Constraints for table `user_post`
--
ALTER TABLE `user_post`
  ADD CONSTRAINT `user_post_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
