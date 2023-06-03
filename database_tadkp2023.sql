-- phpMyAdmin SQL Dump
-- version 5.2.0
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jun 03, 2023 at 01:46 AM
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
-- Database: `database_tadkp2023`
--

-- --------------------------------------------------------

--
-- Table structure for table `db_karyawan`
--

CREATE TABLE `db_karyawan` (
  `id_karyawan` int(10) NOT NULL,
  `nama` varchar(40) NOT NULL,
  `jabatan` varchar(15) NOT NULL,
  `tgl_lahir` varchar(12) NOT NULL,
  `no_telepon` varchar(14) NOT NULL,
  `agama` varchar(12) NOT NULL,
  `jenis_kelamin` varchar(10) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `db_karyawan`
--

INSERT INTO `db_karyawan` (`id_karyawan`, `nama`, `jabatan`, `tgl_lahir`, `no_telepon`, `agama`, `jenis_kelamin`) VALUES
(1, 'Annas Tri Widagdo', 'Magang', '03/10/2002', '082111111111', 'Islam', 'Laki-laki'),
(2, 'Ben', 'Staff', '14/12/1998', '082122222222', 'Kristen', 'Laki-laki'),
(3, 'Surya', 'IT', '13/07/1990', '083821212121', 'Buddha', 'Laki-laki'),
(4, 'Putri', 'Manager', '30/05/1970', '082323232323', 'Kristen', 'Perempuan'),
(6, 'Chen', 'Staff', '21/12/1994', '082167676767', 'Khonghucu', 'Perempuan'),
(12, 'Hamdu', 'Akuntan', '23/02/1992', '085299796655', 'Islam', 'Laki-laki'),
(21, 'Salsa', 'Staff', '21/03/1997', '085700011110', 'Islam', 'Perempuan'),
(22, 'Sony', 'Junior IT', '12/12/2000', '087711116666', 'Kristen', 'Laki-laki'),
(31, 'jack', 'Staff', '12/12/2000', '08312312', 'Kristen', 'Laki-laki'),
(33, 'Riko', 'Administrasi', '23/11/1995', '0877232323', 'Islam', 'Laki-laki');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `db_karyawan`
--
ALTER TABLE `db_karyawan`
  ADD PRIMARY KEY (`id_karyawan`) USING BTREE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
