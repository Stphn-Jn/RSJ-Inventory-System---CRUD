-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Apr 28, 2025 at 06:35 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.0.30

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `rsjess`
--

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `pname` varchar(255) NOT NULL,
  `price` int(255) NOT NULL,
  `qty` int(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `pname`, `price`, `qty`) VALUES
(11, 'SN74LS86N', 40, 10),
(12, 'SN74LS00N', 35, 26),
(13, 'NE555N', 30, 76),
(14, 'LED BLUE', 5, 456),
(15, 'LED RED', 5, 456),
(16, 'LED GREEN', 5, 456),
(17, 'LED RGB 4 PIN', 10, 432),
(18, 'LED RGB 2 PIN', 10, 234),
(19, 'LED BLUE', 5, 241),
(20, 'LED IR RECEIVER', 20, 131),
(21, 'LED IR TRASMITER', 20, 234),
(22, 'LED WHITE', 5, 242),
(23, 'LED YELLOW', 5, 5345),
(24, 'BUZZER PASSIVE', 25, 24),
(25, 'BUZZER ACTIVE', 20, 179),
(26, 'PUSH BUTTON SMALL', 3, 1214),
(27, '4 BIT DIP SWITCH', 35, 234),
(28, 'POTENTIOMETER 6PIN', 45, 34),
(29, 'POTENTIOMETER 3PIN', 35, 79),
(30, 'SWTICH 3 PIN', 15, 2),
(31, 'RESISTOR 1/4', 3, 23542),
(32, 'RESISTOR 1/2', 5, 967),
(33, 'RESISTOR 1W', 6, 324),
(34, 'RESISTOR 2W', 8, 345),
(36, 'sadjp', 2, 2),
(38, 'dw', 2, 2),
(39, 'hotdog', 11, 100);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=40;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
