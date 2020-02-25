-- phpMyAdmin SQL Dump
-- version 4.7.0
-- https://www.phpmyadmin.net/
--
-- Host: localhost:3306
-- Generation Time: Feb 25, 2020 at 11:39 AM
-- Server version: 5.6.34-log
-- PHP Version: 7.2.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `feo`
--

-- --------------------------------------------------------

--
-- Table structure for table `entry`
--

CREATE TABLE `entry` (
  `messageid` bigint(18) NOT NULL,
  `userid` bigint(18) NOT NULL,
  `emoteid` bigint(18) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `signup`
--

CREATE TABLE `signup` (
  `messageid` bigint(18) NOT NULL,
  `guildid` bigint(18) NOT NULL,
  `channelid` bigint(18) NOT NULL,
  `authorid` bigint(18) NOT NULL,
  `eventtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `tank` int(11) NOT NULL,
  `pld` int(11) NOT NULL,
  `war` int(11) NOT NULL,
  `drk` int(11) NOT NULL,
  `gnb` int(11) NOT NULL,
  `heal` int(11) NOT NULL,
  `whm` int(11) NOT NULL,
  `sch` int(11) NOT NULL,
  `ast` int(11) NOT NULL,
  `dps` int(11) NOT NULL,
  `mdps` int(11) NOT NULL,
  `mnk` int(11) NOT NULL,
  `drg` int(11) NOT NULL,
  `nin` int(11) NOT NULL,
  `sam` int(11) NOT NULL,
  `rdps` int(11) NOT NULL,
  `brd` int(11) NOT NULL,
  `mch` int(11) NOT NULL,
  `dnc` int(11) NOT NULL,
  `cdps` int(11) NOT NULL,
  `blm` int(11) NOT NULL,
  `smn` int(11) NOT NULL,
  `rdm` int(11) NOT NULL,
  `blu` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

-- --------------------------------------------------------

--
-- Table structure for table `spots`
--

CREATE TABLE `spots` (
  `messageid` bigint(18) NOT NULL,
  `spots` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `entry`
--
ALTER TABLE `entry`
  ADD PRIMARY KEY (`messageid`,`userid`);

--
-- Indexes for table `signup`
--
ALTER TABLE `signup`
  ADD PRIMARY KEY (`messageid`);

--
-- Indexes for table `spots`
--
ALTER TABLE `spots`
  ADD PRIMARY KEY (`messageid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
