-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 28, 2023 at 03:20 PM
-- Server version: 10.4.27-MariaDB
-- PHP Version: 8.2.0

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `cantinapp`
--

-- --------------------------------------------------------

--
-- Table structure for table `employee`
--

CREATE TABLE `employee` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `class` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `employee`
--

INSERT INTO `employee` (`id`, `name`, `class`) VALUES
(24, 'igorMalvado', 2),
(31, 'guilhermeTesteNovo', 0),
(32, 'sthefany lopez', 0),
(36, 'Guilherme', 0);

-- --------------------------------------------------------

--
-- Table structure for table `passwordreset`
--

CREATE TABLE `passwordreset` (
  `pwdResetId` int(11) NOT NULL,
  `pwdResetEmail` text NOT NULL,
  `pwdResetSelector` text NOT NULL,
  `pwdResetToken` longtext NOT NULL,
  `pwdResetExpires` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `passwordreset`
--

INSERT INTO `passwordreset` (`pwdResetId`, `pwdResetEmail`, `pwdResetSelector`, `pwdResetToken`, `pwdResetExpires`) VALUES
(9, 'lucsaz42@gmail.com', 'e3a3ef37972222b4', '$2y$10$dv.q3Zn7FGmKssW6wuWevOTGsq2pPEdSa7YYkIcP3BrGJnIlrB1ea', '1677538103');

-- --------------------------------------------------------

--
-- Table structure for table `product`
--

CREATE TABLE `product` (
  `id` int(11) NOT NULL,
  `name` varchar(45) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `image` varchar(50) DEFAULT NULL,
  `productType` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `product`
--

INSERT INTO `product` (`id`, `name`, `price`, `image`, `productType`) VALUES
(30, 'kevytos maicon ku', 123, '2016_01_07_skin_2016010711052136268.png', 2),
(31, 'Fatia de Bolo de Chocolate', 3.5, 'bolochocolate.jpg', 3),
(32, 'Clube Social Original', 2, 'clubesocial.jpg', 1),
(33, 'Bolo de chocolate vegano', 123.43, 'bolochocolate.jpg', 0),
(34, 'havan', 202, 'MicrosoftTeams-image.png', 1),
(35, 'abadabadu', 2000, 'imageremovebgpreview.png', 3),
(38, 'jaa', 23, 'default-image.png', 3),
(39, 'fd', 31, 'default-image.png', 1),
(40, 'ggg', 8, 'default-image.png', 1),
(41, 'sla', 123, 'default-image.png', 1),
(42, 'kevytos tomate +', 100.5, 'imageremovebgpreview.png', 3),
(43, 'adsasd', 200, 'default-image.png', 1),
(44, 'kevytos rat', 123, 'default-image.png', 1);

-- --------------------------------------------------------

--
-- Table structure for table `scale`
--

CREATE TABLE `scale` (
  `id` int(11) NOT NULL,
  `id_employee` int(11) DEFAULT NULL,
  `id_turn` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `scale`
--

INSERT INTO `scale` (`id`, `id_employee`, `id_turn`) VALUES
(30, 31, 55),
(32, 31, 57),
(33, 32, 57),
(40, 31, 68);

-- --------------------------------------------------------

--
-- Table structure for table `turn`
--

CREATE TABLE `turn` (
  `id` int(11) NOT NULL,
  `day` date DEFAULT NULL,
  `period` int(11) DEFAULT NULL,
  `class` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `turn`
--

INSERT INTO `turn` (`id`, `day`, `period`, `class`) VALUES
(55, '2022-02-25', 0, 0),
(57, '2022-02-10', 0, 0),
(68, '2022-07-10', 0, 0);

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `id` int(11) NOT NULL,
  `username` varchar(45) DEFAULT NULL,
  `name` varchar(45) DEFAULT NULL,
  `password` varchar(2000) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `isUser` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`id`, `username`, `name`, `password`, `email`, `isUser`) VALUES
(1, 'aila', 'Aila', '$2y$10$h8Dr/02Wwsf0wvlId8fbyO8F7JGGfgWw14AevkFf.WHRHzC6yewQi', 'aila@email.com', 2),
(4, 'lucas', 'Lucas Santos ', '$2y$10$NMheCPpUoKA40vAZYrUMTeRE013UWU38TFLXWkW6Vw7uc5GCfT6gu', 'lucsaz42@gmail.com', 2),
(5, 'leliss', 'leandro', '$2y$10$fTihhUeJwHfN7geZL8pVKuMiPXoSPLQpLd.6pXOjqxn3w.exjumeO', 'leandroferreiramine@gmail.com', 2),
(6, 'ailasilva', 'Aila', '$2y$10$it7Ne6KWnyXU20qOTLbq6uPX85JkZznM4t8gLXLZGLCVA6poNqbNK', 'ailavbsilva@gmail.com', 2),
(7, 'lucasteste', 'Lucas Santos', '$2y$10$IeHRB/fUqOQTKoy1jYsFUu4wo7wb2RkPR5SS08cM.OEW5i9FwsgGW', 'lucassantos.va030@academico.ifsul.edu.br', 2),
(8, 'lucastst', 'lucas', '$2y$10$rES8RkH4sIkpIc259nvcku54b.pe0z6zXvl0FjYhN1RtsLtLqLB2S', 'lucas@', 2),
(9, 'lucasss', '123', '$2y$10$bWrLjxUwC3S2yk3rl9FrCOZ7ky/Ck83gV1IIJWbcmo5uKRVg2o8TO', 'lucas@', 1),
(10, 'lucsaz42@gmail.com', 'lucsaz42@gmail.com', '$2y$10$icknbu1OEjEDivF0W5/i7eH.4NDMm3YrkIWPPwFpV8ro7Di79j72e', 'lucsaz40@gmail.com', 1),
(27, 'gui', 'Guilherme Neis', '$2y$10$xZHwBod1O5Aoj6VwDRYE8.ItM0ssIWIsFZ8XVLPxD/OtkI8sGk5sO', 'guilhermeneis132@gmail.com', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `employee`
--
ALTER TABLE `employee`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `passwordreset`
--
ALTER TABLE `passwordreset`
  ADD PRIMARY KEY (`pwdResetId`);

--
-- Indexes for table `product`
--
ALTER TABLE `product`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `scale`
--
ALTER TABLE `scale`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_employee` (`id_employee`),
  ADD KEY `scale_ibfk_2` (`id_turn`);

--
-- Indexes for table `turn`
--
ALTER TABLE `turn`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `employee`
--
ALTER TABLE `employee`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=37;

--
-- AUTO_INCREMENT for table `passwordreset`
--
ALTER TABLE `passwordreset`
  MODIFY `pwdResetId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT for table `product`
--
ALTER TABLE `product`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=45;

--
-- AUTO_INCREMENT for table `scale`
--
ALTER TABLE `scale`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=43;

--
-- AUTO_INCREMENT for table `turn`
--
ALTER TABLE `turn`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=70;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=28;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `scale`
--
ALTER TABLE `scale`
  ADD CONSTRAINT `scale_ibfk_1` FOREIGN KEY (`id_employee`) REFERENCES `employee` (`id`),
  ADD CONSTRAINT `scale_ibfk_2` FOREIGN KEY (`id_turn`) REFERENCES `turn` (`id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
