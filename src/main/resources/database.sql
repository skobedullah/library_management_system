CREATE DATABASE  IF NOT EXISTS `lmsdb`;
USE `lmsdb`;

CREATE TABLE IF NOT EXISTS `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `email` varchar(45) NOT NULL UNIQUE,
  `password` varchar(45) DEFAULT NULL,
  `role` varchar(45) DEFAULT NULL,
  `about` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);


--
-- Dumping admin data for table ``
--


INSERT INTO `user` VALUES 
	(1001,'ADMIN-1','admin1@login','password','ROLE_ADMIN','about admin1'),
	(1002,'ADMIN-2','admin2@login','password','ROLE_ADMIN','about admin2');
	


CREATE TABLE IF NOT EXISTS `book` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL UNIQUE,
  `title` varchar(45) DEFAULT NULL,
  `author` varchar(45) NOT NULL,
  `description` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
);