-- phpMyAdmin SQL Dump
-- version 3.4.5
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Jun 19, 2013 at 09:41 AM
-- Server version: 5.5.16
-- PHP Version: 5.3.8

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `artistaone`
--

-- --------------------------------------------------------

--
-- Table structure for table `address`
--

CREATE TABLE IF NOT EXISTS `address` (
  `id` varchar(255) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `city` varchar(255) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `zip` varchar(255) DEFAULT NULL,
  `country` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `address`
--

INSERT INTO `address` (`id`, `address`, `city`, `state`, `zip`, `country`, `date_created`) VALUES
('063efe4ca5cf446da1d26c4e81bb6c99', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:31:13'),
('0a4da9b264ec402bb67731f06bfca310', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-17 23:27:01'),
('119d2f5f48e94de4b848bcc3739fbee8', NULL, NULL, NULL, '****', NULL, '2013-06-18 23:41:07'),
('13098c7257c441f3ad5636ec4d62d56a', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:08:18'),
('14d1e4ff7fd7436c9adfd6c8373250d8', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:13:03'),
('17c4503bd9cf46a29152dfbf1c2697da', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:17:13'),
('187d28eba13e489b97a30b3acec817dd', NULL, 'Berkely', 'CA', '94704', 'USA', NULL),
('191bb690a99f4e66bfc90c5b0ca858ae', NULL, 'Agadir', 'Souss', '****', 'Morocco', '2013-06-18 23:58:42'),
('1c3ea58daa6344c480e3ca0c756efbbf', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:33:28'),
('1cc766ba625f4f2e857cebe9e60a446c', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:50:04'),
('20f00ef703584ecab86cb532070fb1f2', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 20:55:41'),
('25f83f0dafba4ba7a4e1e2180379f8ae', NULL, 'dsad', 'safsd', '****', 'fsdf', '2013-06-19 00:14:43'),
('289659d3cb57433fa8829d039cbd9169', NULL, 'Tamazert', 'Souss', '****', 'Morocco', '2013-06-19 00:34:40'),
('39b3f8097d4545c899ec2fe6360bfe8e', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:30:58'),
('3bc35eec1bc140dfab2169d9dd0f1097', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:28:01'),
('3d25f12af38f4f68a0f53703437de1f6', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:31:31'),
('3e55c2b9b26f480a9e57f2533a19b094', NULL, 'Agadir', 'Souss', '****', 'Morocco', '2013-06-19 00:37:13'),
('3f0695b0c6c74d57be6c228d2b71af8b', NULL, NULL, NULL, '****', NULL, '2013-06-18 23:45:07'),
('426eebd75b9746f9a93d7b5244895cab', NULL, NULL, NULL, '****', NULL, '2013-06-18 23:31:41'),
('62ecce1f2f39423a8b68fdc28b23ab7a', NULL, 'Montreal', 'Quebec', '****', 'Canada', '2013-06-19 00:08:08'),
('65e7ed3ded1d4aeba33a6ac6d61633d3', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:28:53'),
('680876674b944d048b87c8b806b9077f', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:26:51'),
('683eac352dec4d2b9c80fe809a3d9633', NULL, 'vgdfg', 'fdhgfh', '****', 'fghgfj', '2013-06-19 00:17:45'),
('6d44b7f8d91849a6a3ce0b69bdf282c6', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:39:03'),
('6e364e4671a741b4a11449e24272ace4', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 20:59:18'),
('7184914b24cc444aaf351a7241d80458', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:49:07'),
('7ce8f9b03e634737be42bf7b833818e0', NULL, 'fghfgh', 'fghfg', '****', 'hfghfgh', '2013-06-19 00:26:08'),
('85247ca7616347d8b21c69552e56aec4', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:00:25'),
('8af33181d61a40a0b273db58fad90c8d', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:24:40'),
('8f7e00f8532a4145ae5342a2b5595273', NULL, NULL, NULL, '****', NULL, '2013-06-18 23:36:05'),
('9143a5fd5f7a4b89823bca9efb023b71', NULL, 'Dcheira', 'Souss', '****', 'Morocco', '2013-06-19 00:05:25'),
('95331610a9404a41a4bc4890cd835af5', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 20:43:07'),
('980f1f14dd104a9a837a4a657babc520', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 20:47:44'),
('a5c01c4521c74b11a01cc06d8387e78a', NULL, 'Agadir', 'Souss', '****', 'Morocco', '2013-06-19 00:27:51'),
('a64c82a8e1b549059069d63d1fd1bcb0', NULL, NULL, NULL, '****', NULL, '2013-06-18 23:34:28'),
('a6c9af4e18c7484b962e16dfd0800609', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:25:52'),
('ae438cac4e2b4dc2b0e1eec035422e90', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:19:50'),
('b05fec0662e04d58bda87565a5238b4c', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 20:36:42'),
('b3e25517050d43218ec46a6b9854b8b7', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:10:26'),
('bbe944d105854592872b3d77510d20e9', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:07:33'),
('bc9d6e2e21bf4139a9af0f36211ad6d7', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:36:33'),
('c19abea28d0d41e188e78a7a1613e7d9', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:55:13'),
('ce7cd2f22c144abda4839a2dbab205fb', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:34:03'),
('d2d748bc2d4d40e5a52e166f839462b9', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 20:58:47'),
('d6462fac85b245fca2e81996b9c269a2', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:05:37'),
('dd6902060f4b48be9a612de1d771fe13', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 20:30:55'),
('deb14e6ff2da4b0aaf7f378c8de4da73', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:28:47'),
('debd1aaf76f24097a841531c0a43302c', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:01:40'),
('e72736c947694e318563bc76b72a4e7e', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 23:21:20'),
('e7a77e744d674eebb8568af7d30570ca', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 20:48:54'),
('e84708f45d82403a9acc721e336173ab', NULL, 'bcvbcb', 'vcbcbvc', '****', 'bvbcvc', '2013-06-19 00:30:36'),
('eb849a55af804ac19d2e19b3a73e885c', NULL, 'sdfsf', 'sdfdsfg', '****', 'fdgfd', '2013-06-19 00:10:50'),
('fd1773dec26341eda3499c94f6390629', NULL, 'Berkely', 'CA', '94704', 'USA', '2013-06-18 22:50:34');

-- --------------------------------------------------------

--
-- Table structure for table `artistas`
--

CREATE TABLE IF NOT EXISTS `artistas` (
  `id` varchar(255) NOT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `age` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `profile_image_id` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  `location_id` varchar(255) DEFAULT NULL,
  `billing_address_id` varchar(255) DEFAULT NULL,
  `mailing_address_id` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_artistas_profileImage_1` (`profile_image_id`),
  KEY `ix_artistas_location_2` (`location_id`),
  KEY `ix_artistas_BillingAddress_3` (`billing_address_id`),
  KEY `ix_artistas_MailingAddress_4` (`mailing_address_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `artistas`
--

INSERT INTO `artistas` (`id`, `first_name`, `last_name`, `user_name`, `age`, `password`, `email`, `profile_image_id`, `date_created`, `location_id`, `billing_address_id`, `mailing_address_id`) VALUES
('493c591fd76040179a2cb3da5ee2d0d4', 'Alfred', 'Hitchko', 'berberacrobat', 0, 'riweutiureit', NULL, '0aae8dbcd3454390b4144abf2985a329', '2013-06-18 20:36:42', 'b05fec0662e04d58bda87565a5238b4c', NULL, NULL),
('95600375a69c417dac844e76aa7567ba', 'Jimmy', 'Wellow', 'berberacrobat', 0, 'kdjlkfsd', NULL, '94455f45c40b46b3a1b2e62074ebcca3', '2013-06-18 20:47:44', '980f1f14dd104a9a837a4a657babc520', NULL, NULL),
('9eabf578228c4bcca6e139142c90638c', 'Iwa', 'hayah', 'hayah', 0, 'dasd', NULL, NULL, '2013-06-19 00:34:25', '289659d3cb57433fa8829d039cbd9169', NULL, NULL),
('a13e77dfbcb847a7807f2ea88556d60f', 'San', 'rais', 'san', 0, 'asdasdsad', NULL, 'ef287891762542aaa42ba81152d54661', '2013-06-19 00:37:00', '3e55c2b9b26f480a9e57f2533a19b094', NULL, NULL),
('dba3b030f4f042fd9333017ad4726616', 'Micheal', 'Jackson', NULL, 0, 'sjfiudfs', NULL, '9034a566b86247ea801a410809179e17', '2013-06-17 23:27:01', '0a4da9b264ec402bb67731f06bfca310', NULL, NULL),
('e80392ac2c9d46b68ce7f863180a5c1b', 'Elhassan', 'Rais', NULL, 0, 'safhsiufhsid', NULL, '2966e481833645fb8e33b553b73ca94b', '2013-06-17 23:24:38', '187d28eba13e489b97a30b3acec817dd', NULL, NULL),
('fbc0b7c361f942819ca9df5051209994', 'James', 'Bond', 'berberacrobat', 0, 'dksakjdfkasjlfsdfg', NULL, '3dbd7b6389eb40a7bebf8a7ee663c86d', '2013-06-18 20:43:07', '95331610a9404a41a4bc4890cd835af5', NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `play_evolutions`
--

CREATE TABLE IF NOT EXISTS `play_evolutions` (
  `id` int(11) NOT NULL,
  `hash` varchar(255) NOT NULL,
  `applied_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  `apply_script` text,
  `revert_script` text,
  `state` varchar(255) DEFAULT NULL,
  `last_problem` text,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `play_evolutions`
--

INSERT INTO `play_evolutions` (`id`, `hash`, `applied_at`, `apply_script`, `revert_script`, `state`, `last_problem`) VALUES
(1, '678308e89064e7cf0ec0369d192521839494a3b4', '2013-06-18 06:15:53', 'create table address (\nid                        varchar(255) not null,\naddress                   varchar(255),\ncity                      varchar(255),\nstate                     varchar(255),\nzip                       varchar(255),\ncountry                   varchar(255),\ndate_created              datetime,\nconstraint pk_address primary key (id))\n;\n\ncreate table profileImages (\nid                        varchar(255) not null,\nurl                       varchar(255),\ndescription               varchar(255),\ndate_created              datetime,\nconstraint pk_profileImages primary key (id))\n;\n\ncreate table artistas (\nid                        varchar(255) not null,\nfirst_name                varchar(255),\nlast_name                 varchar(255),\nuser_name                 varchar(255),\nage                       integer,\npassword                  varchar(255),\nemail                     varchar(255),\nprofile_image_id          varchar(255),\ndate_created              datetime,\nlocation_id               varchar(255),\nbilling_address_id        varchar(255),\nmailing_address_id        varchar(255),\nconstraint pk_artistas primary key (id))\n;\n\ncreate table videos (\nid                        varchar(255) not null,\nuser_id                   varchar(255),\nurl                       varchar(255),\ndescription               varchar(255),\ndate_created              datetime,\nconstraint pk_videos primary key (id))\n;\n\nalter table artistas add constraint fk_artistas_profileImage_1 foreign key (profile_image_id) references profileImages (id) on delete restrict on update restrict;\ncreate index ix_artistas_profileImage_1 on artistas (profile_image_id);\nalter table artistas add constraint fk_artistas_location_2 foreign key (location_id) references address (id) on delete restrict on update restrict;\ncreate index ix_artistas_location_2 on artistas (location_id);\nalter table artistas add constraint fk_artistas_BillingAddress_3 foreign key (billing_address_id) references address (id) on delete restrict on update restrict;\ncreate index ix_artistas_BillingAddress_3 on artistas (billing_address_id);\nalter table artistas add constraint fk_artistas_MailingAddress_4 foreign key (mailing_address_id) references address (id) on delete restrict on update restrict;\ncreate index ix_artistas_MailingAddress_4 on artistas (mailing_address_id);\nalter table videos add constraint fk_videos_user_5 foreign key (user_id) references artistas (id) on delete restrict on update restrict;\ncreate index ix_videos_user_5 on videos (user_id);', 'SET FOREIGN_KEY_CHECKS=0;\n\ndrop table address;\n\ndrop table profileImages;\n\ndrop table artistas;\n\ndrop table videos;\n\nSET FOREIGN_KEY_CHECKS=1;', 'applied', '');

-- --------------------------------------------------------

--
-- Table structure for table `profileimages`
--

CREATE TABLE IF NOT EXISTS `profileimages` (
  `id` varchar(255) NOT NULL,
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `profileimages`
--

INSERT INTO `profileimages` (`id`, `url`, `description`, `date_created`) VALUES
('0aae8dbcd3454390b4144abf2985a329', 'artistasPhotos/alfred/1371613027112artista6.jpg', 'artista6.jpg', '2013-06-18 20:37:07'),
('2966e481833645fb8e33b553b73ca94b', 'artistasPhotos/me/1371536729219artista3.jpg', 'artista3.jpg', '2013-06-17 23:25:29'),
('3dbd7b6389eb40a7bebf8a7ee663c86d', 'artistasPhotos/james/1371613639453artista7.jpg', 'artista7.jpg', '2013-06-18 20:47:19'),
('9034a566b86247ea801a410809179e17', 'artistasPhotos/m/1371536872624artista4.jpg', 'artista4.jpg', '2013-06-17 23:27:52'),
('94455f45c40b46b3a1b2e62074ebcca3', 'artistasPhotos/wellow/1371613710127Picture_11_bigger.png', 'Picture_11_bigger.png', '2013-06-18 20:48:30'),
('ef287891762542aaa42ba81152d54661', 'artistasPhotos/san/1371627442094hassan.jpg', 'hassan.jpg', '2013-06-19 00:37:22');

-- --------------------------------------------------------

--
-- Table structure for table `videos`
--

CREATE TABLE IF NOT EXISTS `videos` (
  `id` varchar(255) NOT NULL,
  `user_id` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `date_created` datetime DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `ix_videos_user_5` (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `videos`
--

INSERT INTO `videos` (`id`, `user_id`, `url`, `description`, `date_created`) VALUES
('098650573d2643d4bf01666f30ea28ff', NULL, NULL, NULL, '2013-06-19 00:34:40'),
('0e73caaa8d4c43e693306a976e0d9f43', NULL, NULL, NULL, '2013-06-19 00:10:50'),
('0fb0f0f49f944cc3b2bd91a80ac43640', NULL, NULL, NULL, '2013-06-18 23:45:07'),
('206884ff64314f449299958ca5ac2e81', NULL, NULL, NULL, '2013-06-18 20:36:50'),
('27e43c5a3b2f4e78a8d5c8918af75d24', NULL, NULL, NULL, '2013-06-19 00:30:36'),
('3725c92d55614e2cbfb70032627fa447', NULL, NULL, NULL, '2013-06-18 20:47:11'),
('3dcefaca3b134c4b8c6d960519164eb8', NULL, NULL, NULL, '2013-06-19 00:08:08'),
('446d006a07e8426d827c265b77ef00bb', NULL, NULL, NULL, '2013-06-18 23:41:07'),
('4eb784618e7c4552b4b88511293b713e', NULL, NULL, NULL, '2013-06-19 00:37:13'),
('58137860972a4740bda13a1f6af430f1', NULL, NULL, NULL, '2013-06-18 23:34:28'),
('71b8185e68f24a0e9966a9d9f13fc806', NULL, NULL, NULL, '2013-06-19 00:17:45'),
('720f36e71c024d5f95aed114b14eaf66', NULL, NULL, NULL, '2013-06-18 23:58:42'),
('79df3c6da54c4f3d98ff29667d643f43', NULL, NULL, NULL, '2013-06-18 23:31:41'),
('7c55ef2f94e647f28a14fc0938473a30', NULL, NULL, NULL, '2013-06-19 00:27:51'),
('8981a81ae3b34768aca88b5875cccd2d', NULL, NULL, NULL, '2013-06-18 23:56:14'),
('95569603605245f68bdcc7fc890d989c', NULL, NULL, NULL, '2013-06-18 20:48:23'),
('b228f3d9d0114282b5f6ff5d034a7c73', NULL, NULL, NULL, '2013-06-19 00:14:43'),
('c038c4f3e6194d99b528103c310310df', NULL, NULL, NULL, '2013-06-19 00:05:25'),
('ccd8fa36e22646fb8f5079de8b286032', NULL, NULL, NULL, '2013-06-19 00:26:08'),
('d3bbd7a845a54d639ef623024aa3d21b', NULL, NULL, NULL, '2013-06-18 23:54:58'),
('f86ae1210dfd4d30a54c656ea5a8499d', NULL, NULL, NULL, '2013-06-18 23:36:05');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `artistas`
--
ALTER TABLE `artistas`
  ADD CONSTRAINT `fk_artistas_BillingAddress_3` FOREIGN KEY (`billing_address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `fk_artistas_location_2` FOREIGN KEY (`location_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `fk_artistas_MailingAddress_4` FOREIGN KEY (`mailing_address_id`) REFERENCES `address` (`id`),
  ADD CONSTRAINT `fk_artistas_profileImage_1` FOREIGN KEY (`profile_image_id`) REFERENCES `profileimages` (`id`);

--
-- Constraints for table `videos`
--
ALTER TABLE `videos`
  ADD CONSTRAINT `fk_videos_user_5` FOREIGN KEY (`user_id`) REFERENCES `artistas` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
