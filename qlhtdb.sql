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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `email`
--

LOCK TABLES `email` WRITE;
/*!40000 ALTER TABLE `email` DISABLE KEYS */;
INSERT INTO `email` VALUES (1,1,'eren.yeager@gmail.com'),(2,2,'levi.ackerman@gmail.com'),(3,3,'armin.arlert@gmail.com'),(4,4,'mikasa.ackerman@gmail.com'),(5,5,'reiner.braun@gmail.com'),(6,6,'jean.kirstein@gmail.com'),(7,7,'2251012149tuan@ou.edu.vn');
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
) ENGINE=InnoDB AUTO_INCREMENT=57 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food`
--

LOCK TABLES `food` WRITE;
/*!40000 ALTER TABLE `food` DISABLE KEYS */;
INSERT INTO `food` VALUES (1,'Bánh mì ốp la 2 trứng','Bánh mì giòn rụm kẹp 2 quả trứng ốp la béo ngậy, lòng đào vàng óng, thêm chút dưa leo và đồ chua thanh mát, đơn giản mà đầy đủ dinh dưỡng cho bữa sáng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755967779/ug46wlzygzcks8twrhgh.jpg',23000,5,3),(2,'Bánh mì bò lá lốt','Thịt bò ướp đậm đà, cuộn trong lá lốt nướng thơm lừng, ăn kèm bánh mì nóng giòn, rau sống và nước sốt đặc trưng, mang hương vị dân dã nhưng khó quên.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755968891/s7wn5csra58cjbijurm4.jpg',29000,5,3),(3,'Bánh mì nem nướng','Nem nướng xay nhuyễn, nướng than thơm phức, vị ngọt của thịt quyện cùng nước chấm chua ngọt, tạo nên ổ bánh mì hấp dẫn khó cưỡng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755968935/cwrarxqbkw8lz9qhyast.jpg',27000,5,3),(4,'Bánh mì thịt nướng mỡ hành','Thịt nướng vàng óng, mềm ngọt, thấm vị ướp, rưới thêm mỡ hành béo bùi, kẹp cùng bánh mì nóng hổi và rau củ tươi giòn – sự kết hợp đậm đà và béo ngậy.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755968969/beiuvdar5nrrrzfzlfbt.jpg',29000,5,3),(5,'Bánh mì bì xíu mại','Ổ bánh mì đặc biệt kết hợp bì heo dai giòn và xíu mại mềm thơm, chan chút nước sốt đậm vị, tạo cảm giác vừa quen vừa lạ miệng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755968999/l82ie6qirzylj69kwmsj.jpg',27000,5,3),(6,'Bánh mì chả cá','Chả cá chiên vàng rụm, thơm lừng hương biển, kẹp trong bánh mì giòn cùng rau thơm và dưa chua, mang đến vị ngon lạ miệng, khó quên.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969053/ckqbw0tmp9vccfrabbai.jpg',27000,5,3),(7,'Bành mì gà nướng muối ớt','Thịt gà nướng cay mặn vừa đủ, dậy mùi muối ớt hấp dẫn, kết hợp rau sống và sốt mayonnaise béo ngậy, thích hợp cho tín đồ ăn cay.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969622/opcqvjmnsxlxcssknjnr.jpg',25000,5,3),(8,'Bánh mì bò mỡ chài lá lốt','Bò mỡ chài thơm ngọt, gói cùng lá lốt nướng than dậy hương, kẹp trong ổ bánh mì giòn rụm – sự kết hợp sáng tạo và độc đáo.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969675/rnd5hsowf73grpyawms2.jpg',29000,5,3),(9,'Gà rán','Gà vàng rộm, giòn tan bên ngoài, thịt mềm ngọt bên trong, ướp gia vị đậm đà – món ăn kinh điển khiến ai cũng mê.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969760/uonoe1kn1wmrieukwbo3.jpg',25000,5,12),(10,'Gà xiên lá chanh','Thịt gà xiên nướng thơm lừng hương lá chanh, cay nhẹ, ăn kèm nước sốt chua ngọt, vừa miệng, hấp dẫn vị giác.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969779/z0vztpxtqs2cygtfymbq.jpg',19000,5,12),(11,'Heo xiên nướng','Thịt heo xiên nướng vàng ruộm, thấm gia vị đậm đà, thơm mùi than hồng, ăn kèm rau sống hoặc bánh mì đều tuyệt.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969803/vlqxgflksxdriqx28xfx.webp',19000,5,12),(12,'Nem nướng xiên','Nem nướng thơm phức, vị ngọt tự nhiên của thịt quyện cùng nước chấm chua ngọt, hấp dẫn khó cưỡng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969828/wsuf3i4u0xzf7hdby73l.jpg',17000,5,12),(13,'Xúc xích','Xúc xích dai ngon, nướng vừa tới, giữ trọn vị thịt, thích hợp nhâm nhi hoặc ăn kèm bánh mì.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969854/ausxu0mme4gvcfhfrq3j.jpg',10000,5,12),(14,'Xúc xích phô mai','Xúc xích kết hợp phô mai kéo sợi béo ngậy, nướng vàng giòn bên ngoài, thơm phức bên trong – món ăn vặt “gây nghiện”.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969872/oppeatck4b8tsa4dfjii.jpg',16000,5,12),(15,'Lạp xưởng','Lạp xưởng nướng trên đá nóng, vỏ hơi cháy xém, thịt ngọt và thơm, ăn kèm rau sống hoặc bánh mì đều hợp.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969899/eu8z3dynbkqks0vd4qrg.jpg',18000,5,12),(16,'Cánh gà nướng tiêu','Cánh gà nướng tẩm ướp tiêu đen thơm nồng, vàng giòn bên ngoài, mềm mọng bên trong, cay nồng vừa phải, cực hợp nhâm nhi.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755969920/xrceuk1hpvl9hzppr5ga.jpg',19000,5,12),(17,'Bánh bao khoai môn','Bánh bao mềm mịn, nhân khoai môn béo ngậy, thơm nhẹ, ăn một miếng là cảm nhận vị ngọt tự nhiên và hương thơm thanh thoát.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970026/dvwcofxrpql9xdkrgygx.jpg',8000,5,1),(18,'Bánh bao xá xíu','Bánh bao trắng phau, nhân thịt xá xíu ngọt vừa, đậm đà gia vị, mềm mềm dai dai – món ăn nhẹ lý tưởng mọi lúc.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970044/ilancpdahuulsccp2k2b.jpg',24000,5,1),(19,'Bánh bao trứng cút trứng muối','Bánh bao kết hợp trứng cút và trứng muối béo ngậy, nhân thơm, vị mặn nhẹ quyện cùng vỏ bánh mềm xốp, cực kỳ hấp dẫn.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970062/vtqsrtuhfzthe9p07udc.jpg',24000,5,1),(20,'Bánh xếp','Vỏ bánh mỏng mềm, nhân thịt hoặc rau củ đậm đà, hấp vừa chín tới, ăn một miếng là cảm nhận vị ngọt thanh và dai nhẹ.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970109/oqhypepdvaduzvn6ywoc.jpg',7000,5,5),(21,'Há cảo','Há cảo trong suốt, nhân tôm, thịt hoặc rau củ tươi ngon, hấp nóng hổi, giữ trọn hương vị tự nhiên và mềm mại.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970136/qcgjsu9alccqgdxkukus.jpg',16000,5,5),(22,'Xíu mại','Xíu mại thơm phức, thịt mềm ngọt, hấp giữ nước, chấm chút nước sốt đậm đà là vừa miệng, thích hợp ăn kèm cơm hoặc bánh bao.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970514/kx23pebjxtqcfpw7xlno.jpg',16000,5,5),(23,'Bánh giò gà','Bánh giò mềm mịn, nhân gà xé thơm, đậm đà gia vị, vỏ bánh ấm nóng ôm trọn nhân béo ngọt, món ăn sáng lý tưởng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970572/paolu4aszykl4xl6eakn.jpg',18000,5,2),(24,'Bánh giò heo','Bánh giò vỏ mềm mịn, nhân thịt heo băm thơm lừng, đậm vị, ăn kèm chút nước mắm chua ngọt càng thêm hấp dẫn.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970589/b1oqaovx31vvozkvkxwb.jpg',18000,5,2),(25,'Mì trộn sốt thái','Sợi mì dai mềm, trộn cùng sốt Thái chua cay đặc trưng, rau sống và lạc rang, món ăn đậm vị, hấp dẫn vị giác.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970664/pkawhcjfdjjr9sjkxttg.jpg',15000,5,6),(26,'Mì trộn xốt tứ xuyên','Mì trộn cùng sốt Tứ Xuyên cay nồng, kết hợp thịt, rau tươi và chút hành phi thơm, tạo hương vị mạnh mẽ và kích thích.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970683/w1mwumxj1tjwgsk72tqc.jpg',15000,5,6),(27,'Mì trộn hải sản','Mì dai mềm kết hợp hải sản tươi ngon, trộn với rau thơm, nước sốt chua ngọt đậm đà – món ăn lạ miệng nhưng cực hấp dẫn.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970705/dxbmvdxib81ihlacqcp2.jpg',23000,5,6),(28,'Đồ chua','Rau củ tươi ngâm chua ngọt vừa phải, giòn giòn, tạo hương vị thanh mát, ăn kèm mọi món chính đều tăng độ hấp dẫn.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970759/gbpduklfr6ytpdryxyjl.jpg',2000,5,8),(29,'Trứng gà ốp la','Trứng ốp la vừa chín, lòng đỏ mềm mịn, vàng óng, ăn kèm bánh mì hoặc cơm đều thơm ngon, béo ngậy và đầy dinh dưỡng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970780/z6i9dyls7cughlui6vz1.jpg',6000,5,8),(30,'Trứng gà luộc','Trứng luộc chín vừa tới, lòng đỏ béo, trắng mịn, tiện lợi cho bữa sáng hoặc ăn nhẹ, bổ sung protein đầy đủ.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970805/upixrhuyna3roy2hmy9h.jpg',6000,5,8),(31,'Cà phê sữa tươi','Cà phê đậm vị hòa quyện cùng sữa tươi béo ngậy, tạo hương thơm nhẹ nhàng, uống lạnh hay nóng đều tuyệt.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970883/t70tgziqs1qkt6htaxfb.jpg',27000,5,4),(32,'Latte','Espresso hòa cùng sữa nóng, lớp bọt mịn mềm mại trên bề mặt, thơm và dịu nhẹ, phù hợp thưởng thức cả ngày.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970930/lvhs7hedkjbzldtii25l.jpg',25000,5,4),(33,'Americano','Espresso pha loãng với nước nóng, vị đắng nhẹ, thanh tao, giữ trọn hương cà phê nguyên chất.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970948/mmustbvsiq4bzhmewkdq.jpg',22000,5,4),(34,'Cà phê sữa yến mạch','Espresso kết hợp sữa yến mạch béo nhẹ, thơm thơm hạt ngọt tự nhiên, là lựa chọn lành mạnh và thơm ngon.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970971/kvqek6cwv8wnff1zctew.jpg',35000,5,4),(35,'Bạc xỉu kem muối','Cà phê sữa ngọt dịu kết hợp lớp kem muối béo mặn hấp dẫn, vừa uống vừa cảm nhận hương vị độc đáo.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755970991/flm8cv1on3sgae74q0eh.jpg',23000,5,4),(36,'Bạc xỉu','Cà phê sữa béo vừa phải, thơm mùi cà phê, uống dịu nhẹ, phù hợp cho những ai thích vị ngọt thanh.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971016/y6n9uwom95bemwyldud0.jpg',23000,5,4),(37,'Cà phê kem muối','Cà phê đen kết hợp kem muối béo thơm, tạo vị mặn ngọt cân bằng, thưởng thức cực đã.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971034/xcgau6vfylbocgbkkjzt.jpg',20000,5,4),(38,'Cà phê đen','Cà phê nguyên chất, rang đậm, vị đắng nồng, hương thơm đặc trưng, thích hợp cho tín đồ cà phê thuần.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971054/np9kh4kyrhcm3iwfddd4.jpg',17000,5,4),(39,'Cà phê sữa','Cà phê pha cùng sữa đặc, ngọt béo, thơm cà phê nồng nàn, món uống truyền thống được nhiều người yêu thích.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971070/owkvgjpa4teeslnqqnvd.jpg',17000,5,4),(40,'Cold Brew','Cà phê ủ lạnh, vị dịu, hương thơm tự nhiên, uống mát lạnh sảng khoái, thích hợp ngày nóng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971087/nhqdzguzqlrkfhsoulzj.jpg',25000,5,4),(41,'Trà tắc','Trà tắc chua ngọt thanh mát, thêm chút đá lạnh và lát tắc thơm, giải khát cực sảng khoái.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971159/mnhei3vb4pe01kylh1gl.png',17000,5,11),(42,'Trà đào','Trà đào ngọt nhẹ, thơm hương đào tươi, uống lạnh kèm đá, mang lại cảm giác mát lành và sảng khoái.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971199/w9hmqy7enbuc0oqkgwg2.jpg',23000,5,11),(43,'Trà xoài','Trà xoài vàng óng, ngọt tự nhiên, kết hợp trà thanh mát, uống lạnh cực đã miệng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971219/zt4gzi5qxxnhbex3sc4c.jpg',29000,5,11),(44,'Trà dâu','Trà dâu tươi ngon, màu đỏ bắt mắt, vị ngọt dịu và hương thơm trái cây tự nhiên, uống mát lạnh sảng khoái.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971241/z5ebwzq88q8m4b8gb1yg.jpg',29000,5,11),(45,'Trà sữa khoai môn','Trà sữa béo ngậy, kết hợp khoai môn thơm mềm, ngọt dịu, uống lạnh cực “gây nghiện”.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971321/twugv2tqbdzm2erfxusf.jpg',25000,5,10),(46,'Hồng trà sữa','Hồng trà sữa thanh mát, vị trà nhẹ hòa cùng sữa ngọt dịu, thưởng thức lạnh là tuyệt nhất.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971347/pr5jt0khzwzgg2o9ecim.jpg',25000,5,10),(47,'Lài sữa sương sáo','Trà sữa lài thơm dịu, kết hợp sương sáo giòn mát, tạo vị ngọt nhẹ, giải khát sảng khoái.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971374/klapp6ekcwqvkqbcpd5l.jpg',29000,5,10),(48,'Trà sữa Olong gạo','Trà sữa thơm béo kết hợp lông gạo dai dai, mang đến hương vị độc đáo và mới lạ.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971403/lxofdjs5ftth9wdskx7n.png',25000,5,10),(49,'Trà sữa Olong rang','Trà sữa béo thơm, kết hợp lông rang giòn nhẹ, tạo cảm giác vừa lạ vừa quen khi thưởng thức.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971430/cptg77us3l6mnrs8itdw.png',25000,5,10),(50,'Sữa đậu nành','Sữa đậu nành tươi, béo nhẹ, thơm mùi đậu tự nhiên, uống mát lạnh hoặc ấm đều bổ dưỡng và dễ uống.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971533/o2p1mo94yxpwh8jigfxr.jpg',15000,5,7),(51,'Sâm bí đao','Thức uống giải khát thanh mát, kết hợp sâm và bí đao, uống lạnh cực sảng khoái, giúp giải nhiệt ngày nóng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971554/wncba3rsbfxsmwpsb5e7.jpg',17000,5,7),(52,'Thạch tắc xí muội','Thạch dai giòn, kết hợp vị chua thanh của tắc và chút xí muội ngọt mặn, ăn kèm đồ uống hoặc tráng miệng cực đã.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971602/x13w4ilcfoctdsz3m4b4.jpg',9000,5,9),(53,'Thạch sương sáo','Thạch sương sáo mát lạnh, giòn sần sật, hương vị thanh mát, thích hợp ăn kèm trà, trà sữa hoặc món tráng miệng.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971633/mbgjzjiqd3tp6zhjwl1u.jpg',6000,5,9),(54,'Trân châu phô mai','Trân châu dai mềm, béo vị phô mai, thêm chút ngọt nhẹ, topping cực “gây nghiện” cho trà sữa và món uống.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971710/cxq3aztxqebcfx7f9srn.png',9000,5,9),(55,'Trân châu caramel','Trân châu ngọt vừa, thấm vị caramel thơm béo, dai mềm, ăn kèm trà sữa hoặc trà trái cây đều hợp.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971749/prwgoa5lqtlfoacumudl.jpg',9000,5,9),(56,'Trân châu trắng','Trân châu trắng dai mềm, thanh ngọt tự nhiên, topping nhẹ nhàng mà hấp dẫn cho mọi món đồ uống.','https://res.cloudinary.com/daupdu9bs/image/upload/v1755971777/ztk4odxatfu4n0ifdpqa.jpg',7000,5,9);
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
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_category`
--

LOCK TABLES `food_category` WRITE;
/*!40000 ALTER TABLE `food_category` DISABLE KEYS */;
INSERT INTO `food_category` VALUES (1,'Bánh bao'),(2,'Bánh giò'),(3,'Bánh mì'),(4,'Cà phê'),(5,'Dimsum'),(6,'Mì trộn'),(7,'Nước ép'),(8,'Topping đồ ăn'),(9,'Topping nước uống'),(10,'Trà sữa'),(11,'Trà trái cây'),(12,'Xiên que');
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
INSERT INTO `food_comment` VALUES (1,3,1,'Phở rất ngon và đậm đà!','2025-09-14 19:53:22'),(2,3,2,'Bánh mì ngon, giòn rụm','2025-09-14 19:53:22'),(3,2,1,'Mình thích món này, nên ăn nhiều hơn.','2025-09-14 19:53:22');
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
INSERT INTO `food_like` VALUES (1,3,1,'2025-09-14 19:53:22'),(2,3,2,'2025-09-14 19:53:22'),(3,2,1,'2025-09-14 19:53:22');
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
  `journey_name` varchar(100) NOT NULL,
  `total_amount` double NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `journey_name` (`journey_name`),
  CONSTRAINT `food_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `food_order_ibfk_2` FOREIGN KEY (`journey_name`) REFERENCES `journey` (`name`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_order`
--

LOCK TABLES `food_order` WRITE;
/*!40000 ALTER TABLE `food_order` DISABLE KEYS */;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `food_order_item`
--

LOCK TABLES `food_order_item` WRITE;
/*!40000 ALTER TABLE `food_order_item` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journey`
--

LOCK TABLES `journey` WRITE;
/*!40000 ALTER TABLE `journey` DISABLE KEYS */;
INSERT INTO `journey` VALUES (1,'JRN229',1,1,2,'2025-08-13 21:13:00','2025-08-13 22:09:00',56,'00:56:00','COMPLETED','2025-08-13 21:13:29',1),(2,'JRN720',1,1,5,'2025-08-08 21:13:00','2025-08-08 23:52:00',159,'02:39:00','COMPLETED','2025-08-13 21:13:48',1),(3,'JRN995',2,34,7,'2025-08-06 21:14:00','2025-08-07 16:36:00',1162,'19:22:00','COMPLETED','2025-08-13 21:14:05',1),(10,'JRN703',1,1,2,'2025-09-14 20:51:00','2025-09-14 21:47:00',56,'00:56:00','COMPLETED','2025-09-14 20:51:22',1);
/*!40000 ALTER TABLE `journey` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `notification_subscription`
--

DROP TABLE IF EXISTS `notification_subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `notification_subscription` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `journey_name` varchar(100) NOT NULL,
  `user_id` int NOT NULL,
  `created_at` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `journey_name` (`journey_name`,`user_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `notification_subscription_ibfk_1` FOREIGN KEY (`journey_name`) REFERENCES `journey` (`name`) ON DELETE CASCADE,
  CONSTRAINT `notification_subscription_ibfk_2` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `notification_subscription`
--

LOCK TABLES `notification_subscription` WRITE;
/*!40000 ALTER TABLE `notification_subscription` DISABLE KEYS */;
INSERT INTO `notification_subscription` VALUES (8,'JRN703',7,NULL);
/*!40000 ALTER TABLE `notification_subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `otp`
--

DROP TABLE IF EXISTS `otp`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `otp` (
  `id` int NOT NULL AUTO_INCREMENT,
  `email_id` int NOT NULL,
  `otp_code` varchar(50) NOT NULL,
  `expiry_time` datetime NOT NULL,
  PRIMARY KEY (`id`),
  KEY `email_id` (`email_id`),
  CONSTRAINT `otp_ibfk_1` FOREIGN KEY (`email_id`) REFERENCES `email` (`id`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `otp`
--

LOCK TABLES `otp` WRITE;
/*!40000 ALTER TABLE `otp` DISABLE KEYS */;
/*!40000 ALTER TABLE `otp` ENABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `phone`
--

LOCK TABLES `phone` WRITE;
/*!40000 ALTER TABLE `phone` DISABLE KEYS */;
INSERT INTO `phone` VALUES (1,1,'0123456789'),(2,2,'0987654321'),(3,3,'0911222333'),(4,4,'0123426789'),(5,5,'0987634321'),(6,6,'0991222333'),(7,7,'9999999999');
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
) ENGINE=InnoDB AUTO_INCREMENT=205 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service`
--

LOCK TABLES `service` WRITE;
/*!40000 ALTER TABLE `service` DISABLE KEYS */;
INSERT INTO `service` VALUES (1,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,1),(2,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,1),(3,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,1),(4,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,1),(5,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,1),(6,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,1),(7,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,2),(8,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,2),(9,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,2),(10,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,2),(11,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,2),(12,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,2),(13,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,3),(14,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,3),(15,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,3),(16,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,3),(17,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,3),(18,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,3),(19,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,4),(20,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,4),(21,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,4),(22,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,4),(23,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,4),(24,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,4),(25,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,5),(26,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,5),(27,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,5),(28,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,5),(29,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,5),(30,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,5),(31,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,6),(32,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,6),(33,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,6),(34,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,6),(35,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,6),(36,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,6),(37,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,7),(38,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,7),(39,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,7),(40,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,7),(41,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,7),(42,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,7),(43,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,8),(44,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,8),(45,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,8),(46,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,8),(47,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,8),(48,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,8),(49,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,9),(50,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,9),(51,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,9),(52,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,9),(53,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,9),(54,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,9),(55,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,10),(56,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,10),(57,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,10),(58,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,10),(59,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,10),(60,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,10),(61,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,11),(62,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,11),(63,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,11),(64,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,11),(65,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,11),(66,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,11),(67,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,12),(68,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,12),(69,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,12),(70,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,12),(71,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,12),(72,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,12),(73,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,13),(74,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,13),(75,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,13),(76,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,13),(77,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,13),(78,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,13),(79,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,14),(80,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,14),(81,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,14),(82,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,14),(83,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,14),(84,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,14),(85,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,15),(86,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,15),(87,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,15),(88,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,15),(89,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,15),(90,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,15),(91,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,16),(92,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,16),(93,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,16),(94,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,16),(95,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,16),(96,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,16),(97,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,17),(98,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,17),(99,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,17),(100,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,17),(101,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,17),(102,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,17),(103,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,18),(104,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,18),(105,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,18),(106,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,18),(107,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,18),(108,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,18),(109,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,19),(110,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,19),(111,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,19),(112,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,19),(113,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,19),(114,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,19),(115,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,20),(116,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,20),(117,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,20),(118,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,20),(119,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,20),(120,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,20),(121,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,21),(122,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,21),(123,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,21),(124,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,21),(125,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,21),(126,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,21),(127,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,22),(128,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,22),(129,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,22),(130,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,22),(131,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,22),(132,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,22),(133,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,23),(134,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,23),(135,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,23),(136,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,23),(137,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,23),(138,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,23),(139,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,24),(140,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,24),(141,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,24),(142,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,24),(143,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,24),(144,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,24),(145,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,25),(146,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,25),(147,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,25),(148,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,25),(149,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,25),(150,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,25),(151,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,26),(152,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,26),(153,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,26),(154,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,26),(155,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,26),(156,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,26),(157,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,27),(158,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,27),(159,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,27),(160,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,27),(161,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,27),(162,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,27),(163,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,28),(164,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,28),(165,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,28),(166,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,28),(167,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,28),(168,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,28),(169,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,29),(170,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,29),(171,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,29),(172,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,29),(173,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,29),(174,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,29),(175,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,30),(176,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,30),(177,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,30),(178,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,30),(179,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,30),(180,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,30),(181,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,31),(182,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',0,31),(183,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,31),(184,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,31),(185,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',0,31),(186,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,31),(187,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,32),(188,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,32),(189,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,32),(190,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,32),(191,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,32),(192,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,32),(193,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',1,33),(194,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,33),(195,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',0,33),(196,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',1,33),(197,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,33),(198,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',0,33),(199,'Phòng chờ VIP','Phòng chờ tiện nghi tại ga TPHCM','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/phongchovip_zax8mr.jpg',0,34),(200,'Gửi hành lý','Dịch vụ gửi hành lý an toàn tại ga Biên Hòa','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616551/guihanhly_zwvlnd.jpg',1,34),(201,'Ăn uống nhanh','Dịch vụ ăn uống tại ga Nha Trang','https://res.cloudinary.com/daupdu9bs/image/upload/v1754616552/fastfood_kvn8qu.jpg',1,34),(202,'Wifi','Hỗ trợ wifi truy cập internet','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314762/wifi_cxuzlm.jpg',0,34),(203,'Cửa hàng quà lưu niệm','Cung cấp các trang sức, đồ chơi, ...v.v lưu niệm','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314763/luuniem_omjwtu.jpg',1,34),(204,'Trạm y tế, phòng sơ cứu','Phòng khám đa khoa cung cấp đầy đủ dịch vụ sức khỏe','https://res.cloudinary.com/daupdu9bs/image/upload/v1757314761/phongkham_aikrts.jpg',1,34);
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
INSERT INTO `service_comment` VALUES (1,3,1,'Phòng chờ rộng rãi và thoải mái','2025-09-14 19:53:22'),(2,2,2,'Dịch vụ gửi hành lý rất tiện lợi','2025-09-14 19:53:22');
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
INSERT INTO `service_like` VALUES (1,3,1,'2025-09-14 19:53:22'),(2,2,2,'2025-09-14 19:53:22');
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
  `journey_name` varchar(100) NOT NULL,
  `station_id` int NOT NULL,
  `service_id` int NOT NULL,
  `created_at` datetime DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `journey_name` (`journey_name`),
  KEY `station_id` (`station_id`),
  KEY `service_id` (`service_id`),
  CONSTRAINT `service_order_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`id`) ON DELETE CASCADE,
  CONSTRAINT `service_order_ibfk_2` FOREIGN KEY (`journey_name`) REFERENCES `journey` (`name`) ON DELETE CASCADE,
  CONSTRAINT `service_order_ibfk_3` FOREIGN KEY (`station_id`) REFERENCES `station` (`id`) ON DELETE CASCADE,
  CONSTRAINT `service_order_ibfk_4` FOREIGN KEY (`service_id`) REFERENCES `service` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `service_order`
--

LOCK TABLES `service_order` WRITE;
/*!40000 ALTER TABLE `service_order` DISABLE KEYS */;
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
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'eren123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Eren','Yeager','https://res.cloudinary.com/daupdu9bs/image/upload/v1753343504/eren_fqrw3q.jpg','ADMIN',1),(2,'levi123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Levi','Ackerman','https://res.cloudinary.com/daupdu9bs/image/upload/v1753342986/levi_hvyxo8.jpg','ADMIN',1),(3,'armin123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Armin','Arlert','https://res.cloudinary.com/daupdu9bs/image/upload/v1753342986/armin_ndwqwd.jpg','STAFF',1),(4,'mikasa123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Mikasa','Ackerman','https://res.cloudinary.com/daupdu9bs/image/upload/v1753343372/mikasa_vto86i.jpg','STAFF',1),(5,'reiner123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Reiner','Braun','https://res.cloudinary.com/daupdu9bs/image/upload/v1753342986/reiner_tl9nie.jpg','PASSENGER',1),(6,'jean123','$2a$10$AC0M4jkseVK3ph9GYGnw4OMC/x7em3RURFDgQ1L7Y7IJPbWNQM4E2','Jean','Kirstein','https://res.cloudinary.com/daupdu9bs/image/upload/v1753342987/jean_mtroyu.jpg','PASSENGER',1),(7,'tuan123','$2a$10$QXb3ebMcrs01eJFs9sYbAOk7Bjyg5fvkfY4db6ctU3vRpWoJnM3DK','Kiều Minh','Tuấn','https://res.cloudinary.com/daupdu9bs/image/upload/v1757854825/nyuvucp59zmrsa7f1ej2.jpg','PASSENGER',1);
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

-- Dump completed on 2025-09-14 20:54:07
