CREATE DATABASE  IF NOT EXISTS `qlhtdb` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `qlhtdb`;
-- MySQL dump 10.13  Distrib 8.0.41, for Win64 (x86_64)
--
-- Host: localhost    Database: qlhtdb
-- ------------------------------------------------------
-- Server version	8.0.41

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
-- Table structure for table `admins`
--

DROP TABLE IF EXISTS `admins`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `admins` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `admins_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `admins`
--

LOCK TABLES `admins` WRITE;
/*!40000 ALTER TABLE `admins` DISABLE KEYS */;
INSERT INTO `admins` VALUES (1),(2);
/*!40000 ALTER TABLE `admins` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `email`
--

DROP TABLE IF EXISTS `email`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `email` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`),
  UNIQUE KEY `user_id` (`user_id`),
  CONSTRAINT `email_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,1,'eren.yeager@gmail.com'),(2,2,'levi.ackerman@gmail.com'),(3,3,'armin.arlert@gmail.com'),(4,4,'mikasa.ackerman@gmail.com'),(5,5,'reiner.braun@gmail.com'),(6,6,'jean.kirstein@gmail.com');
/*!40000 ALTER TABLE `email` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food`
--

DROP TABLE IF EXISTS `food`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `price` double NOT NULL,
  `quantity` int NOT NULL,
  `category_id` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `category_id` (`category_id`),
  CONSTRAINT `food_ibfk_1` FOREIGN KEY (`category_id`) REFERENCES `food_category` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Phở bò','Phở bò truyền thống Hà Nội','https://res.cloudinary.com/daupdu9bs/image/upload/v1754815772/bhgv8qxo7d6doprgqgbf.jpg',50000,100,2),(2,'Bánh mì','Bánh mì kẹp thịt đặc biệt','https://res.cloudinary.com/daupdu9bs/image/upload/v1754815789/bd4nlevdroxgiokyt33u.webp',20000,150,2),(3,'Cà phê sữa đá','Cà phê phin truyền thống','https://res.cloudinary.com/daupdu9bs/image/upload/v1754815733/texlj0ydngdvejw4ygjd.jpg',15000,200,1),(4,'Bạc xỉu','Cà phê sữa đặc biệt','https://res.cloudinary.com/daupdu9bs/image/upload/v1754815753/ewcadlfxrbnc2irtdq6d.jpg',20000,250,1);
/*!40000 ALTER TABLE `food` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_category`
--

DROP TABLE IF EXISTS `food_category`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_category` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_category`
--

LOCK TABLES `food_category` WRITE;
/*!40000 ALTER TABLE `food_category` DISABLE KEYS */;
INSERT INTO `food_category` VALUES (2,'Đồ ăn'),(1,'Nước uống');
/*!40000 ALTER TABLE `food_category` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_comment`
--

DROP TABLE IF EXISTS `food_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `food_id` int NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `food_id` (`food_id`),
  CONSTRAINT `food_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `food_comment_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_comment`
--

LOCK TABLES `food_comment` WRITE;
/*!40000 ALTER TABLE `food_comment` DISABLE KEYS */;
INSERT INTO `food_comment` VALUES (1,3,1,'Phở rất ngon và đậm đà!','2025-08-13 21:19:24'),(2,3,2,'Bánh mì ngon, giòn rụm','2025-08-13 21:19:24'),(3,2,1,'Mình thích món này, nên ăn nhiều hơn.','2025-08-13 21:19:24');
/*!40000 ALTER TABLE `food_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_like`
--

DROP TABLE IF EXISTS `food_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `food_id` int NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `food_id` (`food_id`),
  CONSTRAINT `food_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `food_like_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_like`
--

LOCK TABLES `food_like` WRITE;
/*!40000 ALTER TABLE `food_like` DISABLE KEYS */;
INSERT INTO `food_like` VALUES (1,3,1,'2025-08-13 21:19:24'),(2,3,2,'2025-08-13 21:19:24'),(3,2,1,'2025-08-13 21:19:24');
/*!40000 ALTER TABLE `food_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_order`
--

DROP TABLE IF EXISTS `food_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `user_id` int NOT NULL,
  `journey_id` int NOT NULL,
  `total_amount` double NOT NULL,
  `payment_status` enum('PENDING','PAID') NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `journey_id` (`journey_id`),
  CONSTRAINT `food_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `food_order_ibfk_2` FOREIGN KEY (`journey_id`) REFERENCES `journey` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_order`
--

LOCK TABLES `food_order` WRITE;
/*!40000 ALTER TABLE `food_order` DISABLE KEYS */;
INSERT INTO `food_order` VALUES (1,'Đơn hàng 1',3,1,65000,'PAID','2025-08-13 21:19:24'),(2,'Đơn hàng 2',3,1,20000,'PENDING','2025-08-13 21:19:24');
/*!40000 ALTER TABLE `food_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `food_order_item`
--

DROP TABLE IF EXISTS `food_order_item`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `food_order_item` (
  `id` int NOT NULL AUTO_INCREMENT,
  `food_order_id` int NOT NULL,
  `food_id` int NOT NULL,
  `quantity` int NOT NULL,
  `price` double NOT NULL,
  PRIMARY KEY (`id`),
  KEY `food_order_id` (`food_order_id`),
  KEY `food_id` (`food_id`),
  CONSTRAINT `food_order_item_ibfk_1` FOREIGN KEY (`food_order_id`) REFERENCES `food_order` (`id`) ON DELETE CASCADE,
  CONSTRAINT `food_order_item_ibfk_2` FOREIGN KEY (`food_id`) REFERENCES `food` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_order_item`
--

LOCK TABLES `food_order_item` WRITE;
/*!40000 ALTER TABLE `food_order_item` DISABLE KEYS */;
INSERT INTO `food_order_item` VALUES (1,1,1,1,50000),(2,1,3,1,15000),(3,2,2,1,20000);
/*!40000 ALTER TABLE `food_order_item` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journey`
--

DROP TABLE IF EXISTS `journey`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `journey` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `train_id` int NOT NULL,
  `departure_station_id` int NOT NULL,
  `arrival_station_id` int NOT NULL,
  `departure_time` datetime NOT NULL,
  `arrival_time` datetime NOT NULL,
  `total_distance` int NOT NULL,
  `total_travel_time` varchar(10) NOT NULL,
  `status` enum('WAITTING','RUNNING','COMPLETED') NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  `created_by` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`),
  KEY `created_by` (`created_by`),
  CONSTRAINT `journey_ibfk_1` FOREIGN KEY (`created_by`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journey`
--

LOCK TABLES `journey` WRITE;
/*!40000 ALTER TABLE `journey` DISABLE KEYS */;
INSERT INTO `journey` VALUES (1,'JRN229',1,1,2,'2025-08-13 21:13:00','2025-08-13 22:09:00',56,'00:56:00','COMPLETED','2025-08-13 21:13:29',1),(2,'JRN720',1,1,5,'2025-08-08 21:13:00','2025-08-08 23:52:00',159,'02:39:00','COMPLETED','2025-08-13 21:13:48',1),(3,'JRN995',2,34,7,'2025-08-06 21:14:00','2025-08-07 16:36:00',1162,'19:22:00','COMPLETED','2025-08-13 21:14:05',1);
/*!40000 ALTER TABLE `journey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `passenger`
--

DROP TABLE IF EXISTS `passenger`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `passenger` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `passenger_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `passenger`
--

LOCK TABLES `passenger` WRITE;
/*!40000 ALTER TABLE `passenger` DISABLE KEYS */;
INSERT INTO `passenger` VALUES (5),(6);
/*!40000 ALTER TABLE `passenger` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `phone`
--

DROP TABLE IF EXISTS `phone`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `phone` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `user_id` (`user_id`),
  UNIQUE KEY `phone` (`phone`),
  CONSTRAINT `phone_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,1,'0123456789'),(2,2,'0987654321'),(3,3,'0911222333'),(4,4,'0123426789'),(5,5,'0987634321'),(6,6,'0991222333');
/*!40000 ALTER TABLE `phone` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service`
--

DROP TABLE IF EXISTS `service`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `description` text,
  `image` varchar(255) DEFAULT NULL,
  `is_active` tinyint(1) NOT NULL DEFAULT '1',
  `station_id` int NOT NULL,
  PRIMARY KEY (`id`),
  KEY `station_id` (`station_id`),
  CONSTRAINT `service_ibfk_1` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,1),(2,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,1),(3,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,1);
/*!40000 ALTER TABLE `service` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_comment`
--

DROP TABLE IF EXISTS `service_comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_comment` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `service_id` int NOT NULL,
  `content` text NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `service_comment_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `service_comment_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_comment`
--

LOCK TABLES `service_comment` WRITE;
/*!40000 ALTER TABLE `service_comment` DISABLE KEYS */;
INSERT INTO `service_comment` VALUES (1,3,1,'Phòng chờ rộng rãi và thoải mái','2025-08-13 21:19:24'),(2,2,2,'Dịch vụ gửi hành lý rất tiện lợi','2025-08-13 21:19:24');
/*!40000 ALTER TABLE `service_comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_like`
--

DROP TABLE IF EXISTS `service_like`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_like` (
  `id` int NOT NULL AUTO_INCREMENT,
  `user_id` int NOT NULL,
  `service_id` int NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `service_like_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `service_like_ibfk_2` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_like`
--

LOCK TABLES `service_like` WRITE;
/*!40000 ALTER TABLE `service_like` DISABLE KEYS */;
INSERT INTO `service_like` VALUES (1,3,1,'2025-08-13 21:19:24'),(2,2,2,'2025-08-13 21:19:24');
/*!40000 ALTER TABLE `service_like` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `service_order`
--

DROP TABLE IF EXISTS `service_order`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `service_order` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `user_id` int NOT NULL,
  `journey_id` int NOT NULL,
  `station_id` int NOT NULL,
  `service_id` int NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `journey_id` (`journey_id`),
  KEY `station_id` (`station_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `service_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `service_order_ibfk_2` FOREIGN KEY (`journey_id`) REFERENCES `journey` (`id`) ON DELETE CASCADE,
  CONSTRAINT `service_order_ibfk_3` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE,
  CONSTRAINT `service_order_ibfk_4` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_order`
--

LOCK TABLES `service_order` WRITE;
/*!40000 ALTER TABLE `service_order` DISABLE KEYS */;
INSERT INTO `service_order` VALUES (1,'Dịch vụ 1',3,1,1,1,'2025-08-13 21:19:24'),(2,'Dịch vụ 2',3,1,2,2,'2025-08-13 21:19:24');
/*!40000 ALTER TABLE `service_order` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `staff`
--

DROP TABLE IF EXISTS `staff`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `staff` (
  `id` int NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `staff_ibfk_1` FOREIGN KEY (`id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `staff`
--

LOCK TABLES `staff` WRITE;
/*!40000 ALTER TABLE `staff` DISABLE KEYS */;
INSERT INTO `staff` VALUES (3),(4);
/*!40000 ALTER TABLE `staff` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `station`
--

DROP TABLE IF EXISTS `station`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `station` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  `image` varchar(255) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `station`
--

LOCK TABLES `station` WRITE;
/*!40000 ALTER TABLE `station` DISABLE KEYS */;
INSERT INTO `station` VALUES (1,'Hà Nội','https://res.cloudinary.com/daupdu9bs/image/upload/v1754705876/w1qzniirtkqwpllaydjq.jpg'),(2,'Phủ Lý','https://res.cloudinary.com/daupdu9bs/image/upload/v1754698668/wpzspoj2ju62wqxi4xtu.jpg'),(3,'Nam Định','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750165/r2eqh9mhseeokfqcx280.jpg'),(4,'Ninh Bình','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750213/yf7nfthbigazy0dflld9.jpg'),(5,'Thanh Hóa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750245/jqyhynnryqpijvwqfnep.jpg'),(6,'Vinh','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750280/kbcur2jlacwofzo4h1nm.jpg'),(7,'Đồng Hới','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750323/gnoz4w2bmdfin5oslabz.jpg'),(8,'Đông Hà','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750369/yx3wg3x2olieebhhcthx.jpg'),(9,'Huế','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750405/limsjxmbomo14oa0q2io.jpg'),(10,'Đà Nẵng','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750495/vumtcqr2l6maqe0uksnv.jpg'),(11,'Trà Kiệu','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750566/chlxpjkgu7p34h3dtey7.jpg'),(12,'Tam Kỳ','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750590/y6ifugopfakvgyrtyr6x.jpg'),(13,'Núi Thành','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750645/d2gyavwvzangfiard8xy.jpg'),(14,'Quảng Ngãi','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750719/oevsm63usgh9js1syfet.jpg'),(15,'Đức Phổ','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750754/rshlxu5offog9daxysdb.jpg'),(16,'Bồng Sơn','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750781/uy1kayuhtjkqtd3ua4vr.jpg'),(17,'Diêu Trì','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750829/e0qxrkaexohachvlnogd.jpg'),(18,'Quy Nhơn','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750869/walxmtt9jlcdxluutn7y.jpg'),(19,'La Hai','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750910/hp70rcfxvivlu1jqfmoh.jpg'),(20,'Tuy Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754750934/u6hnxbmrlqyuahqrzgsn.jpg'),(21,'Phú Hiệp','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751048/rnxaqm7n6imw9hylyh8j.jpg'),(22,'Giã','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751095/qmdfhcs6z15bnicunivy.jpg'),(23,'Ninh Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751113/xh5rutxuezshgpmrodtp.jpg'),(24,'Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751157/yqjffqhiaxbbnvxlvzif.webp'),(25,'Tháp Chàm','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751203/pt01ujkeyafb4ifekepq.png'),(26,'Sông Mao','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751252/gzwlsihcqxii1qvueni8.jpg'),(27,'Phan Thiết','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751270/gocjwlchhk3abn9iu4kc.webp'),(28,'Bình Thuận','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751315/rlxepzase9xkbgtt5akz.jpg'),(29,'Gia Huynh','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751346/vfcydycjgbzkfpao1dhz.jpg'),(30,'Gia Ray','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751592/t0du7chdhll74xohwvne.jpg'),(31,'Long Khánh','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751478/guhnncixjem4fir7x5y5.jpg'),(32,'Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751499/ewp8ufydgkpqoqmk3821.png'),(33,'Dĩ An','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751533/an7rssnb9auny3khmdep.jpg'),(34,'Sài Gòn','https://res.cloudinary.com/daupdu9bs/image/upload/v1754751561/wltruxryjgmtz2dcuocn.jpg');
/*!40000 ALTER TABLE `station` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train`
--

DROP TABLE IF EXISTS `train`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train`
--

LOCK TABLES `train` WRITE;
/*!40000 ALTER TABLE `train` DISABLE KEYS */;
INSERT INTO `train` VALUES (13,'NA1'),(14,'NA2'),(9,'SD1'),(10,'SD2'),(1,'SE1'),(2,'SE2'),(11,'SE21'),(12,'SE22'),(3,'SNT1'),(4,'SNT2'),(7,'SPT1'),(8,'SPT2'),(5,'SQN1'),(6,'SQN2');
/*!40000 ALTER TABLE `train` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `train_route`
--

DROP TABLE IF EXISTS `train_route`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `train_route` (
  `id` int NOT NULL AUTO_INCREMENT,
  `train_id` int NOT NULL,
  `departure_station_id` int DEFAULT NULL,
  `arrival_station_id` int DEFAULT NULL,
  `distance` int NOT NULL,
  `travel_time` time NOT NULL,
  `stop_order` int NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `train_id` (`train_id`,`stop_order`),
  KEY `departure_station_id` (`departure_station_id`),
  KEY `arrival_station_id` (`arrival_station_id`),
  CONSTRAINT `train_route_ibfk_1` FOREIGN KEY (`train_id`) REFERENCES `train` (`id`) ON DELETE CASCADE,
  CONSTRAINT `train_route_ibfk_2` FOREIGN KEY (`departure_station_id`) REFERENCES `station` (`id`) ON DELETE SET NULL,
  CONSTRAINT `train_route_ibfk_3` FOREIGN KEY (`arrival_station_id`) REFERENCES `station` (`id`) ON DELETE SET NULL
) ENGINE=InnoDB AUTO_INCREMENT=139 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `train_route`
--

LOCK TABLES `train_route` WRITE;
/*!40000 ALTER TABLE `train_route` DISABLE KEYS */;
INSERT INTO `train_route` VALUES (1,1,1,2,56,'00:56:00',1),(2,1,2,3,41,'00:41:00',2),(3,1,3,5,62,'01:02:00',3),(4,1,5,6,139,'02:19:00',4),(5,1,6,7,180,'03:00:00',5),(6,1,7,8,117,'01:57:00',6),(7,1,8,9,66,'01:06:00',7),(8,1,9,10,103,'01:43:00',8),(9,1,10,12,66,'01:06:00',9),(10,1,12,14,72,'01:12:00',10),(11,1,14,17,160,'02:40:00',11),(12,1,17,20,117,'01:57:00',12),(13,1,20,24,103,'01:43:00',13),(14,1,24,25,40,'00:40:00',14),(15,1,25,28,144,'02:24:00',15),(16,1,28,32,144,'02:24:00',16),(17,1,32,34,30,'00:30:00',17),(18,2,34,32,30,'00:30:00',1),(19,2,32,28,144,'02:24:00',2),(20,2,28,25,144,'02:24:00',3),(21,2,25,24,40,'00:40:00',4),(22,2,24,20,103,'01:43:00',5),(23,2,20,17,117,'01:57:00',6),(24,2,17,14,160,'02:40:00',7),(25,2,14,12,72,'01:12:00',8),(26,2,12,10,66,'01:06:00',9),(27,2,10,9,103,'01:43:00',10),(28,2,9,8,66,'01:06:00',11),(29,2,8,7,117,'01:57:00',12),(30,2,7,6,180,'03:00:00',13),(31,2,6,5,139,'02:19:00',14),(32,2,5,3,62,'01:02:00',15),(33,2,3,2,41,'00:41:00',16),(34,2,2,1,56,'00:56:00',17),(35,3,24,25,40,'00:40:00',1),(36,3,25,32,288,'04:48:00',2),(37,3,32,33,10,'00:10:00',3),(38,3,33,34,19,'00:19:00',4),(39,4,34,33,19,'00:19:00',1),(40,4,33,32,10,'00:10:00',2),(41,4,32,25,288,'04:48:00',3),(42,4,25,24,40,'00:40:00',4),(43,5,18,17,10,'00:10:00',1),(44,5,17,20,117,'01:57:00',2),(45,5,20,21,29,'00:29:00',3),(46,5,21,22,42,'00:42:00',4),(47,5,22,23,33,'00:33:00',5),(48,5,23,24,34,'00:34:00',6),(49,5,24,25,40,'00:40:00',7),(50,5,25,28,144,'02:24:00',8),(51,5,28,32,144,'02:24:00',9),(52,5,32,34,30,'00:30:00',10),(53,6,34,32,30,'00:30:00',1),(54,6,32,28,144,'02:24:00',2),(55,6,28,25,144,'02:24:00',3),(56,6,25,24,40,'00:40:00',4),(57,6,24,23,34,'00:34:00',5),(58,6,23,22,33,'00:33:00',6),(59,6,22,21,42,'00:42:00',7),(60,6,21,20,29,'00:29:00',8),(61,6,20,17,117,'01:57:00',9),(62,6,17,18,10,'00:10:00',10),(63,7,27,29,40,'00:40:00',1),(64,7,29,30,48,'00:48:00',2),(65,7,30,31,16,'00:16:00',3),(66,7,31,32,50,'00:50:00',4),(67,7,32,33,10,'00:10:00',5),(68,7,33,34,19,'00:19:00',6),(69,8,34,33,19,'00:19:00',1),(70,8,33,32,10,'00:10:00',2),(71,8,32,31,50,'00:50:00',3),(72,8,31,30,16,'00:16:00',4),(73,8,30,29,48,'00:48:00',5),(74,8,29,27,40,'00:40:00',6),(75,9,17,19,75,'01:15:00',1),(76,9,19,20,40,'00:40:00',2),(77,9,20,22,71,'01:11:00',3),(78,9,22,23,33,'00:33:00',4),(79,9,23,25,174,'02:54:00',5),(80,9,25,26,104,'01:44:00',6),(81,9,26,28,38,'00:38:00',7),(82,9,28,31,127,'02:07:00',8),(83,9,31,32,50,'00:50:00',9),(84,9,32,33,10,'00:10:00',10),(85,9,33,34,19,'00:19:00',11),(86,10,34,33,19,'00:19:00',1),(87,10,33,32,10,'00:10:00',2),(88,10,32,31,50,'00:50:00',3),(89,10,31,28,127,'02:07:00',4),(90,10,28,26,38,'00:38:00',5),(91,10,26,25,104,'01:44:00',6),(92,10,25,23,174,'02:54:00',7),(93,10,23,22,33,'00:33:00',8),(94,10,22,21,71,'01:11:00',9),(95,10,21,20,40,'00:40:00',10),(96,10,20,19,75,'01:15:00',11),(97,11,34,32,30,'00:30:00',1),(98,11,32,31,50,'00:50:00',2),(99,11,31,28,127,'02:07:00',3),(100,11,28,25,144,'02:24:00',4),(101,11,25,26,40,'00:40:00',5),(102,11,26,23,34,'00:34:00',6),(103,11,23,20,103,'01:43:00',7),(104,11,20,24,117,'01:57:00',8),(105,11,24,22,71,'01:11:00',9),(106,11,22,21,36,'00:36:00',10),(107,11,21,12,48,'00:48:00',11),(108,11,12,11,62,'01:02:00',12),(109,11,11,10,21,'00:21:00',13),(110,11,10,9,20,'00:20:00',14),(111,11,9,8,39,'00:39:00',15),(112,11,8,7,103,'01:43:00',16),(113,12,7,8,103,'01:43:00',1),(114,12,8,9,39,'00:39:00',2),(115,12,9,10,20,'00:20:00',3),(116,12,10,11,21,'00:21:00',4),(117,12,11,12,62,'01:02:00',5),(118,12,12,21,48,'00:48:00',6),(119,12,21,22,36,'00:36:00',7),(120,12,22,24,71,'01:11:00',8),(121,12,24,20,117,'01:57:00',9),(122,12,20,23,103,'01:43:00',10),(123,12,23,26,34,'00:34:00',11),(124,12,26,25,40,'00:40:00',12),(125,12,25,28,144,'02:24:00',13),(126,12,28,31,127,'02:07:00',14),(127,12,31,32,50,'00:50:00',15),(128,12,32,34,30,'00:30:00',16),(129,13,1,2,56,'00:56:00',1),(130,13,2,3,41,'00:41:00',2),(131,13,3,4,32,'00:32:00',3),(132,13,4,5,62,'01:02:00',4),(133,13,5,6,139,'02:19:00',5),(134,14,6,5,139,'02:19:00',1),(135,14,5,4,62,'01:02:00',2),(136,14,4,3,32,'00:32:00',3),(137,14,3,2,41,'00:41:00',4),(138,14,2,1,56,'00:56:00',5);
/*!40000 ALTER TABLE `train_route` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `users` (
  `id` int NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `password` varchar(255) NOT NULL,
  `first_name` varchar(50) DEFAULT NULL,
  `last_name` varchar(50) DEFAULT NULL,
  `avatar` varchar(255) DEFAULT NULL,
  `role` enum('ADMIN','STAFF','PASSENGER') NOT NULL,
  `is_active` tinyint(1) DEFAULT '1',
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'eren123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Eren','Yeager','https://res.cloudinary.com/daupdu9bs/image/upload/v1753343504/eren_fqrw3q.jpg','ADMIN',1),(2,'levi123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Levi','Ackerman','https://res.cloudinary.com/daupdu9bs/image/upload/v1753342986/levi_hvyxo8.jpg','ADMIN',1),(3,'armin123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Armin','Arlert','https://res.cloudinary.com/daupdu9bs/image/upload/v1753342986/armin_ndwqwd.jpg','STAFF',1),(4,'mikasa123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Mikasa','Ackerman','https://res.cloudinary.com/daupdu9bs/image/upload/v1753343372/mikasa_vto86i.jpg','STAFF',1),(5,'reiner123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Reiner','Braun','https://res.cloudinary.com/daupdu9bs/image/upload/v1753342986/reiner_tl9nie.jpg','PASSENGER',1),(6,'jean123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Jean','Kirstein','https://res.cloudinary.com/daupdu9bs/image/upload/v1753342987/jean_mtroyu.jpg','PASSENGER',1);
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

-- Dump completed on 2025-08-13 21:20:40
