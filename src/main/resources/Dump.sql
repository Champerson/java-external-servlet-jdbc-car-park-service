-- MySQL dump 10.13  Distrib 8.0.19, for Win64 (x86_64)
--
-- Host: localhost    Database: car_park_service_db
-- ------------------------------------------------------
-- Server version	8.0.19

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `assignments`
--

DROP TABLE IF EXISTS `assignments`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `assignments` (
  `assignment_id` bigint NOT NULL AUTO_INCREMENT,
  `assignment_accepted` tinyint NOT NULL,
  `assignment_creation_time` datetime NOT NULL,
  `assignment_start_date` date DEFAULT NULL,
  `assignment_bus_id` bigint DEFAULT NULL,
  `assignment_route_id` bigint DEFAULT NULL,
  `assignment_user_id` bigint DEFAULT NULL,
  PRIMARY KEY (`assignment_id`),
  UNIQUE KEY `id_UNIQUE` (`assignment_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `assignments`
--

LOCK TABLES `assignments` WRITE;
/*!40000 ALTER TABLE `assignments` DISABLE KEYS */;
INSERT INTO `assignments` VALUES (18,0,'2020-05-10 23:54:21','2020-05-08',8,19,16),(19,1,'2020-05-10 23:54:23','2020-05-06',10,19,13),(20,0,'2020-05-10 23:54:25','2020-05-10',12,19,0);
/*!40000 ALTER TABLE `assignments` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `buses`
--

DROP TABLE IF EXISTS `buses`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `buses` (
  `bus_id` bigint NOT NULL AUTO_INCREMENT,
  `bus_number` varchar(255) NOT NULL,
  `bus_model` varchar(255) DEFAULT NULL,
  `bus_passengers_capacity` int DEFAULT NULL,
  `bus_mileage` int DEFAULT NULL,
  `bus_creation_time` datetime NOT NULL,
  `bus_colour_en` varchar(255) DEFAULT NULL,
  `bus_colour_ua` varchar(255) DEFAULT NULL,
  `bus_notes_en` varchar(255) DEFAULT NULL,
  `bus_notes_ua` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`bus_id`),
  UNIQUE KEY `id_UNIQUE` (`bus_id`),
  UNIQUE KEY `number_UNIQUE` (`bus_number`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `buses`
--

LOCK TABLES `buses` WRITE;
/*!40000 ALTER TABLE `buses` DISABLE KEYS */;
INSERT INTO `buses` VALUES (8,'AA4170HX','Mersedes',60,25000,'2020-05-10 23:06:28','red','червоний','',''),(9,'AA1221BN','Brabus',45,12000,'2020-05-10 23:08:05','yellow','жовтый','Need to pass inspection','Потребує техогляду'),(10,'AA6703MH','Bogdan',65,37000,'2020-05-10 23:09:33','red','червоний','',''),(11,'AA0056FN','Mersedes',60,45000,'2020-05-10 23:10:37','white','білий','',''),(12,'AA3245AB','Bogdan',65,10000,'2020-05-10 23:11:22','white','білий','','');
/*!40000 ALTER TABLE `buses` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `routes`
--

DROP TABLE IF EXISTS `routes`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `routes` (
  `route_id` bigint NOT NULL AUTO_INCREMENT,
  `route_number` varchar(255) NOT NULL,
  `route_length` int DEFAULT NULL,
  `route_creation_time` datetime NOT NULL,
  `route_description_en` varchar(255) DEFAULT NULL,
  `route_description_ua` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`route_id`),
  UNIQUE KEY `number_UNIQUE` (`route_number`),
  UNIQUE KEY `id_UNIQUE` (`route_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `routes`
--

LOCK TABLES `routes` WRITE;
/*!40000 ALTER TABLE `routes` DISABLE KEYS */;
INSERT INTO `routes` VALUES (19,'445',12,'2020-05-10 22:54:28','Revutskogo - Bajana','Ревуцького - Бажана'),(20,'542',6,'2020-05-10 23:16:11','Kharkivske Shosse - Osokorki','Харківське шоссе - Осокорки');
/*!40000 ALTER TABLE `routes` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `user_id` bigint NOT NULL AUTO_INCREMENT,
  `user_login` varchar(255) NOT NULL,
  `user_password` varchar(255) NOT NULL,
  `user_email` varchar(255) DEFAULT NULL,
  `user_phone` varchar(255) DEFAULT NULL,
  `user_name` varchar(255) DEFAULT NULL,
  `user_age` int DEFAULT NULL,
  `user_creation_time` datetime NOT NULL,
  `user_access_role` varchar(255) NOT NULL,
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `login_UNIQUE` (`user_login`),
  UNIQUE KEY `id_UNIQUE` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (11,'Andrew','8aae3a73a9a43ee6b04dfd986fe9d136','andrew@gmail.com','0637774411','Andrew',20,'2020-05-10 22:21:56','DRIVER'),(12,'admin','21232f297a57a5a743894a0e4a801fc3','admin@gmail.com','0638882233','Oleg',20,'2020-05-10 23:02:44','ADMIN'),(13,'Alex','a08372b70196c21a9229cf04db6b7ceb','alex@gmail.com','0634445599','Alex',30,'2020-05-10 23:17:48','DRIVER'),(14,'Tanya','4163d678e68f506fc9cfb79ef4292a03','tanya@gmail.com','0634442211','Tanya',32,'2020-05-10 23:18:34','DRIVER'),(16,'Kolia','52c23bdd8cdb3b2ce7f82f63be8fccc7','kolia@gmail.com','0635554422','Kolia',21,'2020-05-10 23:56:37','DRIVER');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2020-05-11  0:59:16
