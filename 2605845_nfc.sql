-- phpMyAdmin SQL Dump
-- version 4.6.4
-- https://www.phpmyadmin.net/
--
-- Host: fdb18.biz.nf
-- Generation Time: Mar 27, 2018 at 04:22 PM
-- Server version: 5.7.20-log
-- PHP Version: 5.5.38

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `2605845_nfc`
--

-- --------------------------------------------------------

--
-- Table structure for table `doctor`
--

CREATE TABLE `doctor` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `mobile` varchar(500) DEFAULT NULL,
  `address` varchar(900) DEFAULT NULL,
  `email` varchar(600) DEFAULT NULL,
  `pass` varchar(700) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `doctor`
--

INSERT INTO `doctor` (`id`, `name`, `mobile`, `address`, `email`, `pass`) VALUES
(0, '', '', '', '', ''),
(12, 'Samarjeet ', '9898989898', 'Pune ', 'sam@gmail.com', '123456789'),
(31, 'amit', '9632587412', 'manegao', 'amit@gmail.com', '12345'),
(42, 'sam', '9823736337', 'had', 'ssa@gmail.com', '2769'),
(99, 'Asif nadaf', '9632581460', 'punek', 'asif@gmail.comm', '0000'),
(234, 'ywy', '9658695800', 'hwhs', 'jdhdh@hhj.com', '0000'),
(580, 'yt', '6958586980', 'pint', 'ghj@gh.ch', '9898'),
(12345, 'shubham', '8888091665', 'Miraj', 'sss@gmail.com', 'ss123'),
(77777, 'dhawal', '7798635779', 'Pune', 'd7@gmail.com', '12345'),
(654123, 'Dr.Ambrish Dharmadhikari', '9875466451', 'Mumbai', 'ambrishd@gmail.com', 'ambrish123');

-- --------------------------------------------------------

--
-- Table structure for table `pdf`
--

CREATE TABLE `pdf` (
  `id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `pdf`
--

INSERT INTO `pdf` (`id`, `url`, `name`, `user_id`) VALUES
(1, 'http://192.168.43.252/nfcapp/uploads/1.pdf', 'heart operation', '321'),
(2, 'http://ypratiksha6.co.nf//nfcapp/uploads/2.pdf', '123', '22222'),
(3, 'http://shubhamshah.co.nf//nfcapp/uploads/3.pdf', 'A', '7777'),
(4, 'http://shubhamshah.co.nf//nfcapp/uploads/4.pdf', 'user 1 pdf', '1'),
(5, 'http://shubhamshah.co.nf//nfcapp/uploads/5.pdf', 'cardiac data', '1'),
(6, 'http://shubhamshah.co.nf//nfcapp/uploads/6.pdf', 'test', '1'),
(7, 'http://shubhamshah.co.nf//nfcapp/uploads/7.pdf', 'Firstreport', '987987'),
(8, 'http://shubhamshah.co.nf//nfcapp/uploads/8.pdf', 'Rakesh1strep', '1234567'),
(9, 'http://shubhamshah.co.nf//nfcapp/uploads/9.pdf', 'Report', '852963'),
(10, 'http://shubhamshah.co.nf//nfcapp/uploads/10.pdf', '', '2'),
(11, 'http://shubhamshah.co.nf//nfcapp/uploads/11.doc', '', '2'),
(12, 'http://shubhamshah.co.nf//nfcapp/uploads/12.doc', '', '2');

-- --------------------------------------------------------

--
-- Table structure for table `police`
--

CREATE TABLE `police` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `mobile` varchar(500) DEFAULT NULL,
  `address` varchar(900) DEFAULT NULL,
  `email` varchar(600) DEFAULT NULL,
  `pass` varchar(700) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `police`
--

INSERT INTO `police` (`id`, `name`, `mobile`, `address`, `email`, `pass`) VALUES
(369, 'alex', '9658470858', 'hshs', 'add@ghj.cim', '9090'),
(2001, 'poppop', '9639639630', 'pune', 'popo@ghj.con', '0000'),
(0, '', '', '', '', ''),
(30, 'Bhavana mali', '9657860532', 'loni', 'bhavanamali9654@gmail.com', 'pande1234');

-- --------------------------------------------------------

--
-- Table structure for table `policedoc`
--

CREATE TABLE `policedoc` (
  `id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `policedoc`
--

INSERT INTO `policedoc` (`id`, `url`, `name`, `user_id`) VALUES
(1, 'http://shubhamshah.co.nf//nfcapp/police/1.pdf', 'first case', '1'),
(2, 'http://shubhamshah.co.nf//nfcapp/police/2.pdf', 'second case', '1');

-- --------------------------------------------------------

--
-- Table structure for table `prescription`
--

CREATE TABLE `prescription` (
  `id` int(11) NOT NULL,
  `user_id` varchar(900) DEFAULT NULL,
  `doc_id` varchar(900) DEFAULT NULL,
  `pres` varchar(1000) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prescription`
--

INSERT INTO `prescription` (`id`, `user_id`, `doc_id`, `pres`) VALUES
(1, '321', '123', 'eat healthy'),
(2, '5678', '123', 'mnbvcxzasdfghjkloiuytrewqasdfghjklmnbvcxzsdrt zxcvbjk ertyu kjbv sdfvb jhvedf dfgokj sxcvk vbkjhdf jhdfjn ed uygfdcvn ytrdsdb '),
(3, '123', '97', 'sdfghjklmnbvcertyuihb sdfg tfxcvb jhgfdxc hfghcvb ntfgc bnfc v gb jm'),
(4, '22222', '77777', '123dnjdj'),
(5, '22222', '77777', 'Shhehe'),
(6, '321', '147', 'Ok'),
(7, '7777', '9999', 'Take rest'),
(8, '1', '9975', 'Rrteviev'),
(9, '1', '9975', 'sumo cold'),
(10, '', '', ''),
(11, '1', '12', 'medicold'),
(12, '1', 'en99', 'abcd'),
(13, '987987', '654123', 'Crocin\nCelin 500\nEvion 600'),
(14, '1234567', '654123', 'Antoxid\nLimcee'),
(15, '987987', '654123', 'Uguug'),
(16, '852963', '654123', 'Dhdohe'),
(17, '2', '42', 'publish paper'),
(18, '2', '42', 'offer later');

-- --------------------------------------------------------

--
-- Table structure for table `register`
--

CREATE TABLE `register` (
  `Username` varchar(255) NOT NULL,
  `Password` varchar(255) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `register`
--

INSERT INTO `register` (`Username`, `Password`) VALUES
('pqr', '1');

-- --------------------------------------------------------

--
-- Table structure for table `rto`
--

CREATE TABLE `rto` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `mobile` varchar(500) DEFAULT NULL,
  `address` varchar(900) DEFAULT NULL,
  `email` varchar(600) DEFAULT NULL,
  `pass` varchar(700) DEFAULT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rto`
--

INSERT INTO `rto` (`id`, `name`, `mobile`, `address`, `email`, `pass`) VALUES
(1001, 'RTI', '9696585858', 'pune', 'rto@gmail.com', '0000'),
(54, 'harshada vhawal', '7020404118', 'pune', 'harshada@gmail.com', '54321');

-- --------------------------------------------------------

--
-- Table structure for table `rtodoc`
--

CREATE TABLE `rtodoc` (
  `id` int(11) NOT NULL,
  `url` varchar(255) NOT NULL,
  `name` varchar(255) DEFAULT NULL,
  `user_id` text NOT NULL
) ENGINE=MyISAM DEFAULT CHARSET=latin1;

--
-- Dumping data for table `rtodoc`
--

INSERT INTO `rtodoc` (`id`, `url`, `name`, `user_id`) VALUES
(1, 'http://shubhamshah.co.nf//nfcapp/rto/1.pdf', 'license', '1');

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE `users` (
  `id` int(11) NOT NULL,
  `name` varchar(500) DEFAULT NULL,
  `mobile` varchar(500) DEFAULT NULL,
  `address` varchar(900) DEFAULT NULL,
  `email` varchar(600) DEFAULT NULL,
  `pass` varchar(700) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `name`, `mobile`, `address`, `email`, `pass`) VALUES
(1, 'pradip', '9977556666', 'pune', 'prad@gmail.com', '0000'),
(2, 'ashutosh', '9658741230', 'pune', 'ashu@gmail.com', '9999'),
(27, 'sam', '9823736337', 'pune', 'ssavalajakar0@gmail.com', '2769'),
(220, 'Amit', '9966332255', 'pune', 'putr@gmail.com', '12345'),
(321, 'pratik', '9638521472', 'pune', 'y@p.c', 'qwerty'),
(2001, 'abhi', '9696858547', 'pune', 'hjs@hjj.com', '0000'),
(2255, 'Hhhhh', '1234567890', 'Dndn', 'dhawal.d7@gmail.com', 'qwertyu'),
(5678, '12345', '2345t', '345', 'jkl', 'mno'),
(7777, 'admin', '1234567890', 'Pune', 'admin@nfc.com', 'admin123'),
(11111, 'ramesh', '9966553388', 'Pune', 'ramesh@gmail.com', 'ramesh123'),
(12345, 'Samarjeet ', '9696969696', 'Pune ', 'samarjeet@gmail.com', '123456789'),
(22222, 'Snnndn', '1234567890', 'Dnddndn', 'dhawal.d7@gmail.com', '123456'),
(98765, 'abc', '1234567899', 'Pune', 'abc@gmail.com', 'doctor'),
(123456, 'Dhawal', '7798635779', 'Pune', 'dhawal.d7@gmail.com', 'dhawal'),
(190796, 'Tejasvi patel', '9960822801', 'Surat', 'tejasvigp@gmail.com', 'tejasvi123'),
(852963, 'Shubham', '7798635779', 'Pune', 'shubham.pise34@gmail.com', 'shubham123'),
(987654, 'doctor', '1234567890', 'Pune', 'dhawal.d7@gmail.com', 'doctor'),
(987987, 'Rajiv Khanna', '9985236974', 'Kolkata', 'rajivkhanna@gmail.com', 'rajiv123'),
(1234567, 'Rakesh Mishra', '9935978632', 'Pune', 'rakeshm12@gmail.com', 'rakesh123'),
(5556398, 'Jay ', '9898989898', 'Pune ', 'jay1@gmail.com', '12345'),
(2147483647, 'Pratik', '9876543210', 'pune', 'y@p.c', 'abcd');

--
-- Indexes for dumped tables
--

--
-- Indexes for table `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `pdf`
--
ALTER TABLE `pdf`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `police`
--
ALTER TABLE `police`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `policedoc`
--
ALTER TABLE `policedoc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `prescription`
--
ALTER TABLE `prescription`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `register`
--
ALTER TABLE `register`
  ADD PRIMARY KEY (`Username`);

--
-- Indexes for table `rto`
--
ALTER TABLE `rto`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `rtodoc`
--
ALTER TABLE `rtodoc`
  ADD PRIMARY KEY (`id`);

--
-- Indexes for table `users`
--
ALTER TABLE `users`
  ADD PRIMARY KEY (`id`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `pdf`
--
ALTER TABLE `pdf`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=13;
--
-- AUTO_INCREMENT for table `policedoc`
--
ALTER TABLE `policedoc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;
--
-- AUTO_INCREMENT for table `prescription`
--
ALTER TABLE `prescription`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;
--
-- AUTO_INCREMENT for table `rtodoc`
--
ALTER TABLE `rtodoc`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
