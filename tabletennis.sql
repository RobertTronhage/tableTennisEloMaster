-- MySQL dump 10.13  Distrib 8.0.34, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: tabletennis
-- ------------------------------------------------------
-- Server version	8.0.35

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
-- Table structure for table `game_matches`
--

DROP TABLE IF EXISTS `game_matches`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `game_matches` (
  `id` bigint NOT NULL AUTO_INCREMENT,
  `player1` bigint NOT NULL,
  `player2` bigint NOT NULL,
  `winning_player` bigint NOT NULL,
  PRIMARY KEY (`id`),
  KEY `player1_idx` (`player1`),
  KEY `player2_idx` (`player2`),
  KEY `winning_player_idx` (`winning_player`),
  CONSTRAINT `player1` FOREIGN KEY (`player1`) REFERENCES `players` (`player_id`),
  CONSTRAINT `player2` FOREIGN KEY (`player2`) REFERENCES `players` (`player_id`),
  CONSTRAINT `winning_player` FOREIGN KEY (`winning_player`) REFERENCES `players` (`player_id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `game_matches`
--

LOCK TABLES `game_matches` WRITE;
/*!40000 ALTER TABLE `game_matches` DISABLE KEYS */;
INSERT INTO `game_matches` VALUES (1,3,4,4),(2,3,4,4),(3,3,4,4),(4,3,4,4),(5,3,7,5),(6,8,4,4),(7,8,4,4),(8,8,4,4),(9,7,4,4),(10,7,4,4),(11,7,4,7),(12,7,4,7),(13,4,5,4),(14,4,5,4),(15,4,5,4),(16,4,5,4),(17,4,5,4),(18,4,7,7),(19,4,7,7),(20,4,7,7);
/*!40000 ALTER TABLE `game_matches` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Temporary view structure for view `leaderboard_view`
--

DROP TABLE IF EXISTS `leaderboard_view`;
/*!50001 DROP VIEW IF EXISTS `leaderboard_view`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `leaderboard_view` AS SELECT 
 1 AS `player_id`,
 1 AS `name`,
 1 AS `elo`*/;
SET character_set_client = @saved_cs_client;

--
-- Table structure for table `players`
--

DROP TABLE IF EXISTS `players`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `players` (
  `player_id` bigint NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `elo` float NOT NULL DEFAULT '800',
  PRIMARY KEY (`player_id`),
  UNIQUE KEY `name_UNIQUE` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `players`
--

LOCK TABLES `players` WRITE;
/*!40000 ALTER TABLE `players` DISABLE KEYS */;
INSERT INTO `players` VALUES (3,'stefan',800),(4,'Charlie',785),(5,'kjnasdkjnaskdjn',800),(7,'robert',815),(8,'jens',800);
/*!40000 ALTER TABLE `players` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Final view structure for view `leaderboard_view`
--

/*!50001 DROP VIEW IF EXISTS `leaderboard_view`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`robert`@`%` SQL SECURITY DEFINER */
/*!50001 VIEW `leaderboard_view` AS select `players`.`player_id` AS `player_id`,`players`.`name` AS `name`,`players`.`elo` AS `elo` from `players` order by `players`.`elo` desc */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2024-01-19 13:23:04
