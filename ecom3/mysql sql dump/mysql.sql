-- MySQL dump 10.13  Distrib 5.5.21, for Linux (i686)
--
-- Host: stusql.dcs.shef.ac.uk    Database: acp11omm
-- ------------------------------------------------------
-- Server version	5.0.95

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Not dumping tablespaces as no INFORMATION_SCHEMA.FILES table on this server
--

--
-- Table structure for table `affiliation`
--

DROP TABLE IF EXISTS `affiliation`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `affiliation` (
  `afiliationId` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL,
  `mainAuthorId` int(11) NOT NULL,
  PRIMARY KEY  (`afiliationId`),
  KEY `fk_affiliation_user` (`userId`),
  KEY `fk_affiliation_mainAuthor` (`mainAuthorId`)
) ENGINE=MyISAM AUTO_INCREMENT=389 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `affiliation`
--

LOCK TABLES `affiliation` WRITE;
/*!40000 ALTER TABLE `affiliation` DISABLE KEYS */;
INSERT INTO `affiliation` VALUES (229,74,65),(230,75,65),(231,75,65),(232,78,66),(233,79,66),(234,79,66),(235,81,67),(236,82,67),(237,82,67),(238,85,68),(239,86,68),(240,86,68),(241,92,69),(242,92,69),(243,92,69),(244,92,70),(245,92,70),(246,92,70),(247,92,71),(248,92,71),(249,92,71),(250,92,72),(251,92,72),(252,92,72),(253,92,73),(254,92,73),(255,92,73),(256,92,74),(257,92,74),(258,92,74),(259,92,75),(260,92,75),(261,92,75),(262,92,76),(263,92,76),(264,92,76),(265,92,77),(266,92,77),(267,92,77),(268,92,78),(269,92,78),(270,92,78),(271,92,79),(272,92,79),(273,92,79),(274,92,80),(275,92,80),(276,92,80),(277,92,81),(278,92,81),(279,92,81),(280,92,82),(281,92,82),(282,92,82),(283,92,83),(284,92,83),(285,92,83),(286,92,84),(287,92,84),(288,92,84),(289,92,85),(290,92,85),(291,92,85),(292,92,86),(293,92,86),(294,92,86),(295,92,87),(296,92,87),(297,92,87),(298,92,88),(299,92,88),(300,92,88),(301,92,89),(302,92,89),(303,92,89),(304,92,90),(305,92,90),(306,92,90),(307,92,91),(308,92,91),(309,92,91),(310,92,92),(311,92,92),(312,92,92),(313,92,93),(314,92,93),(315,92,93),(316,92,94),(317,92,94),(318,92,94),(319,92,95),(320,92,95),(321,92,95),(322,92,96),(323,92,96),(324,92,96),(325,92,97),(326,92,97),(327,92,97),(328,92,98),(329,92,98),(330,92,98),(331,92,99),(332,92,99),(333,92,99),(334,92,100),(335,92,100),(336,92,100),(337,92,101),(338,92,101),(339,92,101),(340,92,102),(341,92,102),(342,92,102),(343,92,103),(344,92,103),(345,92,103),(346,92,104),(347,92,104),(348,92,104),(349,92,105),(350,92,105),(351,92,105),(352,108,106),(353,108,106),(354,108,106),(355,110,107),(356,111,107),(357,112,107),(358,114,108),(359,108,108),(360,108,108),(361,108,109),(362,108,109),(363,108,109),(364,117,110),(365,118,110),(366,119,110),(367,108,111),(368,108,111),(369,108,111),(370,128,112),(371,129,112),(372,130,112),(373,108,113),(374,108,113),(375,108,113),(376,108,114),(377,108,114),(378,108,114),(379,108,115),(380,108,115),(381,108,115),(382,108,116),(383,108,116),(384,108,116),(385,108,117),(386,108,117),(387,108,117),(388,149,118);
/*!40000 ALTER TABLE `affiliation` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `article`
--

DROP TABLE IF EXISTS `article`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `article` (
  `articleId` int(11) NOT NULL auto_increment,
  `issueId` int(11) NOT NULL,
  `publishDate` timestamp NULL default CURRENT_TIMESTAMP,
  `abstract` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `fulltext` varchar(255) default NULL,
  `submissionId` int(11) NOT NULL,
  PRIMARY KEY  (`articleId`),
  KEY `fk_article_issue` (`issueId`),
  KEY `submissionId` (`submissionId`)
) ENGINE=MyISAM AUTO_INCREMENT=23 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `article`
--

LOCK TABLES `article` WRITE;
/*!40000 ALTER TABLE `article` DISABLE KEYS */;
INSERT INTO `article` VALUES (21,22,'2012-05-01 17:27:15','Abstract 3','submission3',NULL,116),(22,23,'2012-05-03 18:06:31','aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa3333','ECOM103',NULL,125);
/*!40000 ALTER TABLE `article` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment`
--

DROP TABLE IF EXISTS `comment`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment` (
  `commentId` int(11) NOT NULL auto_increment,
  `comment` varchar(255) default NULL,
  `submissionId` int(11) NOT NULL,
  `cmTypeId` int(11) NOT NULL,
  `response` varchar(255) default NULL,
  `userId` int(11) NOT NULL,
  PRIMARY KEY  (`commentId`),
  KEY `fk_comment_type` (`cmTypeId`),
  KEY `fk_comment_submission` (`submissionId`),
  KEY `fk_comment_user` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=44 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment`
--

LOCK TABLES `comment` WRITE;
/*!40000 ALTER TABLE `comment` DISABLE KEYS */;
INSERT INTO `comment` VALUES (22,'this has been plagiarized',77,21,'ok',91),(29,'coment 1',116,28,'response 1',137),(30,'coment 2',114,29,'response 2',137),(26,'',76,25,'no',93),(27,'',76,26,'no',93),(28,'he\'s a tool',81,27,'',89),(31,'second coment',116,30,'response 2',138),(32,'coment 3',116,31,'response 3',139),(33,'coment 3',115,32,'response 3',139),(34,'coment 33',116,33,'response 33',139),(35,'coment 33',115,34,'response 33',139),(36,'coment 22',115,35,'response 22',138),(37,'comebnt 22',116,36,'response 22',138),(38,'coment 22',114,37,'response 22',138),(43,'opopo',122,42,'ok',153);
/*!40000 ALTER TABLE `comment` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `comment_type`
--

DROP TABLE IF EXISTS `comment_type`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `comment_type` (
  `cmTypeId` int(11) NOT NULL auto_increment,
  `roleId` int(11) NOT NULL,
  `title` varchar(255) default NULL,
  PRIMARY KEY  (`cmTypeId`),
  KEY `fk_commentType_role` (`roleId`)
) ENGINE=MyISAM AUTO_INCREMENT=43 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `comment_type`
--

LOCK TABLES `comment_type` WRITE;
/*!40000 ALTER TABLE `comment_type` DISABLE KEYS */;
INSERT INTO `comment_type` VALUES (2,3,'Bad Review'),(3,3,'Dont publish it!'),(4,3,'No title'),(5,3,'No title'),(6,3,'title 1'),(7,3,'new'),(8,3,'new'),(9,3,'new'),(10,3,'new'),(11,3,'new'),(12,3,'new'),(13,3,''),(14,3,''),(15,3,''),(16,3,''),(17,3,''),(18,3,''),(19,3,''),(20,3,''),(21,3,''),(22,3,''),(23,3,''),(24,3,''),(25,3,''),(26,3,''),(27,3,''),(28,3,''),(29,3,''),(30,3,''),(31,3,''),(32,3,''),(33,3,''),(34,3,''),(35,3,''),(36,3,''),(37,3,''),(38,3,''),(39,3,''),(40,3,''),(41,3,''),(42,3,'');
/*!40000 ALTER TABLE `comment_type` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `credit`
--

DROP TABLE IF EXISTS `credit`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `credit` (
  `creditId` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL,
  `credit` int(11) NOT NULL default '0',
  PRIMARY KEY  (`creditId`),
  KEY `fk_credit_user` (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=37 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `credit`
--

LOCK TABLES `credit` WRITE;
/*!40000 ALTER TABLE `credit` DISABLE KEYS */;
INSERT INTO `credit` VALUES (20,115,1),(21,137,5),(22,138,6),(23,139,5),(24,109,0),(25,113,0),(26,141,2),(27,144,5),(28,143,6),(29,116,2),(30,120,2),(31,127,0),(32,148,0),(33,142,1),(34,146,0),(35,151,0),(36,152,0);
/*!40000 ALTER TABLE `credit` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `crticism`
--

DROP TABLE IF EXISTS `crticism`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `crticism` (
  `critismId` int(11) NOT NULL auto_increment,
  `description` varchar(255) default NULL,
  `content` varchar(255) default NULL,
  `response` varchar(255) default NULL,
  `reviewerId` int(11) default NULL,
  `accepted` varchar(255) default NULL,
  `reviewId` int(11) NOT NULL,
  PRIMARY KEY  (`critismId`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `crticism`
--

LOCK TABLES `crticism` WRITE;
/*!40000 ALTER TABLE `crticism` DISABLE KEYS */;
INSERT INTO `crticism` VALUES (3,'','change title','',91,'',81),(4,'','BBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBBB','',97,'',91),(5,'','CCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCCC','respond to c',96,'',92),(8,'','','',93,'',78),(6,'','change title new','done!',97,'',91),(7,'','new criticism','changed',94,'',93),(9,'','dick','',89,'',99),(10,'','dsadasdsadasdasda','',83,'',90),(11,'','ttttrter','',83,'',90),(12,'','critc 1','resp 1',137,'',107),(13,'','second critiscisis','',138,'',109),(14,'','critcisisnm3','',139,'',112),(15,'','crisitis3','',139,'',113),(16,'','change title','done',137,'',132),(17,'','crit','1',137,'',133),(18,'','crit 2','2',142,'',134),(19,'','crit 3','3',143,'',135),(20,'','sux','',153,'',136);
/*!40000 ALTER TABLE `crticism` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `expertise`
--

DROP TABLE IF EXISTS `expertise`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `expertise` (
  `expertieId` int(11) NOT NULL,
  `expertiseLevel` varchar(255) default NULL,
  PRIMARY KEY  (`expertieId`)
) ENGINE=MyISAM DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `expertise`
--

LOCK TABLES `expertise` WRITE;
/*!40000 ALTER TABLE `expertise` DISABLE KEYS */;
INSERT INTO `expertise` VALUES (1,'expert'),(2,'knowledgeable'),(3,'outsider');
/*!40000 ALTER TABLE `expertise` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `file`
--

DROP TABLE IF EXISTS `file`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `file` (
  `fileId` int(11) NOT NULL auto_increment,
  `filename` varchar(255) default NULL,
  `filepath` varchar(255) default NULL,
  `submissionId` int(11) NOT NULL,
  `version` int(11) default '1',
  PRIMARY KEY  (`fileId`),
  KEY `fk_file_submission` (`submissionId`)
) ENGINE=MyISAM AUTO_INCREMENT=131 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `file`
--

LOCK TABLES `file` WRITE;
/*!40000 ALTER TABLE `file` DISABLE KEYS */;
INSERT INTO `file` VALUES (7,'JAVA','http://docs.oracle.com/javaee/6/tutorial/doc/javaeetutorial6.pdf',9,0),(8,'ECOM','',10,0),(9,'ECOM','',11,0),(10,'ECOM','',12,0),(11,'ECOM','',13,0),(12,'ECOM','',14,0),(13,'ECOM','',15,0),(14,'ECOM','',16,0),(15,'ECOM','',17,0),(16,'ECOM','',18,0),(17,'ECOM','',19,0),(18,'ECOM','',20,0),(19,'ECOM','',21,0),(20,'ECOM','',22,0),(21,'ECOM','',23,0),(22,'ECOM','',24,0),(23,'ECOM','',25,0),(24,'TITLE1','',26,0),(25,'ECOM','',27,0),(26,'Software Eng','',28,0),(27,'final TITLE','',31,0),(28,'1','',32,0),(29,'2','',33,0),(30,'3','',34,0),(31,'new submission','',35,0),(32,'hhhhhh','',36,0),(33,'565656','',37,0),(34,'78','',38,0),(35,'5555555555','',39,0),(36,'omar 1','',40,0),(37,'omar 22','',41,0),(38,'ECOM','',42,0),(39,'ECOM 2','',43,0),(40,'ECOM 3','',44,0),(41,'ECOM 4','',45,0),(42,'ECOM','',46,0),(43,'ECOM 2','',47,0),(44,'ECOM 3','',48,0),(45,'ECOM 4','',49,0),(46,'ECOM 5','',50,0),(47,'ECOM 6','',51,0),(48,'ECOM 7','',52,0),(49,'ECOM 8','',53,0),(50,'ECOM 9','',54,0),(51,'ECOM 10','',55,0),(52,'ECOM','',56,0),(53,'ECOM','',57,0),(54,'ECOM 2','',58,0),(55,'ECOM','',59,0),(56,'ECOM 5','',60,0),(57,'ECOM55','',61,0),(58,'ECOM55','',62,0),(59,'ECOM55','',63,0),(60,'ECOM55','',64,0),(61,'ECOM55','',65,0),(62,'ECOM55','',66,0),(63,'ECOM55','',67,0),(64,'ECOM55','',68,0),(65,'ECOM55','',69,0),(66,'ECOM55','',70,0),(67,'ECOM55','',71,0),(68,'ECOM55','',72,0),(69,'ECOM','',73,0),(70,'ECOM','',74,0),(71,'ECOM','',75,0),(72,'title ecom','',76,0),(73,'title ecom 2','',77,0),(74,'title ecom 3','',78,0),(75,'title ecom 3','',79,0),(76,'title ecom 4','',80,0),(77,'title ecom 5','',81,0),(78,'title ecom 6','',82,0),(79,'','',83,0),(80,'A','',84,0),(81,'','',85,0),(82,'','',86,0),(83,'','',87,0),(84,'','',88,0),(85,'ECOM','',89,0),(86,'ECOM','',90,0),(87,'ECOM','',91,0),(88,'ECOM','',92,0),(89,'ECOM 2','',93,0),(90,'ECOM 2','',94,0),(91,'ECOM 2','',95,0),(92,'ECOM 2','',96,0),(93,'ECOM 2','',97,0),(94,'ECOM 2','',98,0),(95,'ECOM 2','',99,0),(96,'ECOM 2','',100,0),(97,'ECOM 2','',101,0),(98,'ECOM 2','',102,0),(99,'ECOM 2','',103,0),(100,'ECOM 2','',104,0),(101,'ECOM 2','',105,0),(102,'ECOM 2','',106,0),(103,'ECOM 2','',107,0),(104,'4545','',108,0),(105,'ghjkhyuk','',109,0),(106,'my article','',110,0),(107,'test','',111,0),(108,'ECOM 5','',112,0),(109,'ECOM 5','',113,0),(110,'submission1','',114,0),(111,'submission2','',115,0),(112,'submission3','',116,0),(113,'submission4','',117,0),(114,'submission5','',118,0),(115,'test','',119,0),(116,'submission6','',120,0),(117,'submission11','',121,0),(118,'submission12','',122,0),(119,'ECOM100','',123,0),(120,'ECOM','',124,0),(121,'ECOM101','',125,0),(122,'45445565546','',126,0),(123,'article1','',127,0),(124,'article1','',128,0),(125,'terst','',129,0),(126,'vahid hashemi','',130,0),(127,'ECOM100jkhjkh','',131,0),(128,'ECOM100km,n,mn','',132,0),(129,'cvbcvxbv','',133,0),(130,'stupid idea','',134,0);
/*!40000 ALTER TABLE `file` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `issueId` int(11) NOT NULL auto_increment,
  `volumeId` int(11) NOT NULL,
  `year` varchar(30) default NULL,
  `issueNumber` varchar(255) default NULL,
  `active` int(11) default '1',
  `created` timestamp NOT NULL default CURRENT_TIMESTAMP,
  PRIMARY KEY  (`issueId`),
  KEY `issue_volume` (`volumeId`)
) ENGINE=MyISAM AUTO_INCREMENT=24 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (22,4,'2012','',0,'2012-05-01 20:04:24'),(23,4,'2012','',0,'2012-05-03 18:16:09');
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `journal`
--

DROP TABLE IF EXISTS `journal`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `journal` (
  `journalId` int(11) NOT NULL auto_increment,
  `title` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  `templateId` int(11) default NULL,
  PRIMARY KEY  (`journalId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `journal`
--

LOCK TABLES `journal` WRITE;
/*!40000 ALTER TABLE `journal` DISABLE KEYS */;
INSERT INTO `journal` VALUES (2,'AWESOME','54555555555545sdfsffsdffsd',NULL),(3,'AWESOME','54555555555545sdfsffsdffsd',NULL),(4,'AWESOME','54555555555545sdfsffsdffsd',NULL);
/*!40000 ALTER TABLE `journal` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `keyword`
--

DROP TABLE IF EXISTS `keyword`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `keyword` (
  `keywordId` int(11) NOT NULL auto_increment,
  `keyword` varchar(255) default NULL,
  `submissionId` int(11) NOT NULL,
  `articleId` int(11) NOT NULL,
  PRIMARY KEY  (`keywordId`)
) ENGINE=MyISAM AUTO_INCREMENT=193 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `keyword`
--

LOCK TABLES `keyword` WRITE;
/*!40000 ALTER TABLE `keyword` DISABLE KEYS */;
INSERT INTO `keyword` VALUES (2,'KEY 1',7,0),(3,'KEY 2',7,0),(4,'KEY 3',7,0),(5,'KEY 4',7,0),(6,'KEY 1',8,0),(7,'KEY 2',8,0),(8,'KEY 3',8,0),(9,'KEY 4',8,0),(10,'KEY 1',9,0),(11,'KEY 2',9,0),(12,'KEY 3',9,0),(13,'KEY 4',9,0),(14,'ecom',11,0),(15,'java',11,0),(16,'ecom',12,0),(17,'java',12,0),(18,'ecom',13,0),(19,'java',13,0),(20,'ecom',14,0),(21,'java',14,0),(22,'ecom',15,0),(23,'java',15,0),(24,'ecom',16,0),(25,'java',16,0),(26,'ecom',17,0),(27,'java',17,0),(28,'ecom',18,0),(29,'java',18,0),(30,'ecom',19,0),(31,'java',19,0),(32,'ecom',20,0),(33,'java',20,0),(34,'ecom',21,0),(35,'java',21,0),(36,'ecom',22,0),(37,'java',22,0),(38,'ecom',23,0),(39,'java',23,0),(40,'ecom',24,0),(41,'java',24,0),(42,'ecom',25,0),(43,'java',25,0),(44,'a',26,0),(45,'c',26,0),(46,'ecom',27,0),(47,'c',27,0),(48,'ecom',28,0),(49,'java',28,0),(50,'A',31,0),(51,'A',31,0),(52,'',32,0),(53,'',32,0),(54,'',33,0),(55,'',33,0),(56,'',34,0),(57,'',34,0),(58,'',35,0),(59,'',35,0),(60,'8888',36,0),(61,'8888',36,0),(62,'56565656',37,0),(63,'56565656',37,0),(64,'78',38,0),(65,'78',38,0),(66,'55555',39,0),(67,'555555555',39,0),(68,'',40,0),(69,'',40,0),(70,'',41,0),(71,'',41,0),(72,'',42,0),(73,'',42,0),(74,'',43,0),(75,'',43,0),(76,'',44,0),(77,'',44,0),(78,'',45,0),(79,'',45,0),(80,'',46,0),(81,'',46,0),(82,'',47,0),(83,'',47,0),(84,'',48,0),(85,'',48,0),(86,'',49,0),(87,'',49,0),(88,'',50,0),(89,'',50,0),(90,'',51,0),(91,'',51,0),(92,'',52,0),(93,'',52,0),(94,'',53,0),(95,'',53,0),(96,'',54,0),(97,'',54,0),(98,'',55,0),(99,'',55,0),(100,'',56,0),(101,'',56,0),(102,'',57,0),(103,'',57,0),(104,'',58,0),(105,'',58,0),(106,'',59,0),(107,'',59,0),(108,'keyword1',71,0),(109,'keyword2.keyword3',71,0),(110,'keyword4',71,0),(111,'keyword1',72,0),(112,'keyword2.keyword3',72,0),(113,'keyword4',72,0),(114,'keyword1',73,0),(115,'keyword2.keyword3',73,0),(116,'keyword4',73,0),(117,'keyword1',74,0),(118,'keyword2.keyword3',74,0),(119,'keyword4',74,0),(120,'keyword1',75,0),(121,'keyword2.keyword3',75,0),(122,'keyword4',75,0),(123,'keyword1',76,0),(124,'keyword 2',76,0),(125,'AAA',77,16),(126,'',78,0),(127,'',79,0),(128,'',80,0),(129,'',81,0),(130,'',82,0),(131,'',83,0),(132,'',84,18),(133,'',85,0),(134,'',86,0),(135,'',87,0),(136,'',88,0),(137,'',89,0),(138,'',90,0),(139,'',91,0),(140,'',92,0),(141,'',93,0),(142,'',94,0),(143,'',95,0),(144,'',96,0),(145,'',97,0),(146,'',98,0),(147,'',99,0),(148,'',100,0),(149,'',101,0),(150,'',102,0),(151,'',103,0),(152,'',104,0),(153,'',105,0),(154,'',106,0),(155,'',107,0),(156,'cool',108,0),(157,' stuff',108,0),(158,' awesome',108,0),(159,'hjghj',109,0),(160,'key1',110,0),(161,'key2',110,0),(162,'key1',111,0),(163,'',112,0),(164,'',113,0),(165,'Submission1',114,0),(166,' first',114,0),(167,'',115,0),(168,'',116,21),(169,'submission4',117,0),(170,' fourth',117,0),(171,'submission5',118,19),(172,' fifth',118,19),(173,'',119,0),(174,'submission65',120,0),(175,' sixth',120,0),(176,'',121,0),(177,'',122,0),(178,'',123,0),(179,'',124,0),(180,'submit13',125,22),(181,'submit14',125,22),(182,'submit',125,22),(183,'14',125,22),(184,'key1',126,0),(185,'key1',127,0),(186,'key1',128,0),(187,'hj',129,0),(188,'key',130,0),(189,'key',131,0),(190,'key',132,0),(191,'key',133,0),(192,'keys',134,0);
/*!40000 ALTER TABLE `keyword` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `letter`
--

DROP TABLE IF EXISTS `letter`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `letter` (
  `LetterId` int(11) NOT NULL auto_increment,
  `ArticleId` int(11) NOT NULL,
  `letter` text,
  PRIMARY KEY  (`LetterId`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `letter`
--

LOCK TABLES `letter` WRITE;
/*!40000 ALTER TABLE `letter` DISABLE KEYS */;
INSERT INTO `letter` VALUES (1,16,'LETERRRR R R R R R RE REW RQCWECWECRCWE'),(2,16,'letter 2'),(3,21,'vfgfdgdfgdfgdfgdfgd');
/*!40000 ALTER TABLE `letter` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `main_author`
--

DROP TABLE IF EXISTS `main_author`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `main_author` (
  `mainAuthorId` int(11) NOT NULL auto_increment,
  `authorId` int(11) NOT NULL,
  `submissionId` int(11) NOT NULL,
  PRIMARY KEY  (`mainAuthorId`),
  KEY `fk_mainauthor_submission` (`submissionId`)
) ENGINE=MyISAM AUTO_INCREMENT=128 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `main_author`
--

LOCK TABLES `main_author` WRITE;
/*!40000 ALTER TABLE `main_author` DISABLE KEYS */;
INSERT INTO `main_author` VALUES (65,73,72),(66,77,73),(67,80,74),(68,84,75),(69,91,76),(70,94,77),(71,97,78),(72,95,79),(73,95,80),(74,95,81),(75,95,82),(76,92,83),(77,93,84),(78,92,85),(79,92,86),(80,92,87),(81,92,88),(82,0,89),(83,0,90),(84,0,91),(85,0,92),(86,0,93),(87,0,94),(88,85,95),(89,98,96),(90,98,97),(91,98,98),(92,98,99),(93,98,100),(94,98,101),(95,98,102),(96,99,103),(97,100,104),(98,101,105),(99,102,106),(100,103,107),(101,104,108),(102,105,109),(103,0,110),(104,106,111),(105,84,112),(106,107,113),(107,109,114),(108,113,115),(109,115,116),(110,116,117),(111,120,118),(112,127,119),(113,141,120),(114,115,121),(115,145,122),(116,146,123),(117,147,124),(118,148,125),(119,150,126),(120,151,127),(121,152,128),(122,153,129),(123,154,130),(124,155,131),(125,156,132),(126,157,133),(127,158,134);
/*!40000 ALTER TABLE `main_author` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `review`
--

DROP TABLE IF EXISTS `review`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `review` (
  `reviewId` int(11) NOT NULL auto_increment,
  `submissionId` int(11) NOT NULL,
  `reviewerId` int(11) NOT NULL,
  `reviewerExpertiseId` int(11) NOT NULL,
  `scoreId` int(11) NOT NULL,
  `reviewDate` timestamp NULL default CURRENT_TIMESTAMP,
  `reviewText` varchar(255) default NULL,
  `criticisimId` int(11) NOT NULL,
  PRIMARY KEY  (`reviewId`),
  KEY `fk_review_submission` (`submissionId`),
  KEY `fk_review_score` (`scoreId`),
  KEY `fk_review_expertise` (`reviewerExpertiseId`),
  KEY `fk_review_reviewerId` (`reviewerId`),
  KEY `fk_review_criticism` (`criticisimId`)
) ENGINE=MyISAM AUTO_INCREMENT=147 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `review`
--

LOCK TABLES `review` WRITE;
/*!40000 ALTER TABLE `review` DISABLE KEYS */;
INSERT INTO `review` VALUES (106,116,137,1,1,'2012-01-30 00:00:00','first review',14),(107,114,137,2,3,'2012-01-30 00:00:00','first review',12),(108,115,137,1,1,'2012-01-30 00:00:00','first review',15),(109,116,138,1,1,'2012-01-30 00:00:00','second review',14),(110,115,138,1,1,'2012-01-30 00:00:00','second review',15),(111,114,138,1,1,'2012-01-30 00:00:00','second review',0),(112,116,139,1,1,'2012-01-30 00:00:00','review 3',14),(113,115,139,3,4,'2012-01-30 00:00:00','review 3 ',15),(114,114,139,3,4,'2012-01-30 00:00:00','review 3',0),(115,118,115,1,1,'2012-01-30 00:00:00','review 1',0),(116,117,115,1,1,'2012-01-30 00:00:00','review 2',0),(117,120,115,1,1,'2012-01-30 00:00:00','review 3',0),(118,118,144,1,1,'2012-01-30 00:00:00','review 6',0),(119,117,144,1,1,'2012-01-30 00:00:00','review 4',0),(120,120,144,1,1,'2012-01-30 00:00:00','review 5',0),(121,118,143,1,1,'2012-01-30 00:00:00','review5',0),(122,117,143,1,1,'2012-01-30 00:00:00','review 4',0),(123,120,143,1,1,'2012-01-30 00:00:00','review 6',0),(124,119,116,1,1,'2012-01-30 00:00:00','good2',0),(125,121,116,1,1,'2012-01-30 00:00:00','good1',0),(126,119,120,1,1,'2012-01-30 00:00:00','good 4',0),(127,121,120,1,1,'2012-01-30 00:00:00','good 3',0),(128,121,141,1,1,'2012-01-30 00:00:00','good 5',0),(129,119,141,1,1,'2012-01-30 00:00:00','good 6',0),(130,122,116,1,1,'2012-01-30 00:00:00','good8',20),(131,122,137,0,0,'2012-01-30 00:00:00','',20),(132,124,137,1,1,'2012-01-30 00:00:00','change 2',16),(133,125,137,1,1,'2012-01-30 00:00:00','review4',19),(137,125,142,1,1,'2012-01-30 00:00:00','review 5',0),(135,125,143,3,4,'2012-01-30 00:00:00','review 3',19),(136,122,153,2,4,'2012-01-30 00:00:00','fgugfughughigigh',20),(138,123,148,1,1,'2012-01-30 00:00:00','r',0),(139,127,148,1,1,'2012-01-30 00:00:00','r2',0),(140,128,148,1,1,'2012-01-30 00:00:00','r4',0),(141,128,137,1,1,'2012-01-30 00:00:00','r1',0),(142,127,138,1,1,'2012-01-30 00:00:00','r1',0),(143,128,138,1,1,'2012-01-30 00:00:00','r1',0),(144,123,138,1,1,'2012-01-30 00:00:00','r5',0),(145,123,139,1,1,'2012-01-30 00:00:00','r4',0),(146,127,139,1,1,'2012-01-30 00:00:00','r55',0);
/*!40000 ALTER TABLE `review` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reviewer_apply`
--

DROP TABLE IF EXISTS `reviewer_apply`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reviewer_apply` (
  `id` int(11) NOT NULL auto_increment,
  `firstName` varchar(200) default NULL,
  `lastName` varchar(200) default NULL,
  `email` varchar(200) default NULL,
  `msg` varchar(200) default NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=30 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reviewer_apply`
--

LOCK TABLES `reviewer_apply` WRITE;
/*!40000 ALTER TABLE `reviewer_apply` DISABLE KEYS */;
INSERT INTO `reviewer_apply` VALUES (12,'','','',''),(29,'vahid','hashemi','svahid.hashemi@yahoo.com','vahid is rev');
/*!40000 ALTER TABLE `reviewer_apply` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `revision`
--

DROP TABLE IF EXISTS `revision`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `revision` (
  `revisionId` int(11) NOT NULL auto_increment,
  `abstract` varchar(255) default NULL,
  `title` varchar(255) default NULL,
  `revisionDate` timestamp NULL default CURRENT_TIMESTAMP,
  `version` int(11) default '0',
  PRIMARY KEY  (`revisionId`)
) ENGINE=MyISAM AUTO_INCREMENT=115 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `revision`
--

LOCK TABLES `revision` WRITE;
/*!40000 ALTER TABLE `revision` DISABLE KEYS */;
INSERT INTO `revision` VALUES (56,'abstract','title ecom','2012-04-20 16:14:32',0),(57,'abstract 2','title ecom 2','2012-04-20 16:58:31',0),(58,'abstract 3','title ecom 3  ok','2012-04-20 17:02:13',1),(59,'abstract 3','title ecom 3','2012-04-20 17:02:57',0),(60,'abstract 4','title ecom 4','2012-04-20 17:03:34',0),(61,'abstract 5','title ecom 5','2012-04-20 17:03:57',0),(62,'abstract 6','title ecom 6','2012-04-20 17:04:16',0),(63,'','','2012-04-23 14:01:27',0),(64,'new','new','2012-04-23 14:21:22',2),(65,'','','2012-04-23 17:04:23',0),(66,'','','2012-04-23 17:08:05',0),(67,'','','2012-04-23 17:10:36',0),(68,'','','2012-04-23 17:13:42',0),(69,'abstract 2','ECOM','2012-04-24 20:12:05',0),(70,'abstract 2','ECOM','2012-04-24 20:14:56',0),(71,'abstract 2','ECOM','2012-04-24 20:17:55',0),(72,'abstract 2','ECOM','2012-04-24 20:19:41',0),(73,'Abstract 1','ECOM 2','2012-04-25 11:45:57',0),(74,'Abstract 1','ECOM 2','2012-04-25 11:49:51',0),(75,'Abstract 1','ECOM 2','2012-04-25 11:50:47',0),(76,'Abstract 1','ECOM 2','2012-04-25 11:55:54',0),(77,'Abstract 1','ECOM 2','2012-04-25 12:08:38',0),(78,'Abstract 1','ECOM 2','2012-04-25 12:12:22',0),(79,'Abstract 1','ECOM 2','2012-04-25 12:12:45',0),(80,'Abstract 1','ECOM 2','2012-04-25 12:17:25',0),(81,'Abstract 1','ECOM 2','2012-04-25 12:17:48',0),(82,'Abstract 1','ECOM 2','2012-04-25 12:19:11',0),(83,'Abstract 1','ECOM 2','2012-04-25 12:24:34',0),(84,'Abstract 1','ECOM 2','2012-04-25 12:42:36',0),(85,'Abstract 1','ECOM 2','2012-04-25 12:44:05',0),(86,'Abstract 1','ECOM 2','2012-04-25 12:51:52',0),(87,'Abstract 1','ECOM 2','2012-04-25 12:53:10',0),(88,'ssdfgdfgdfggd 145','4545','2012-04-26 16:40:34',0),(89,'hjkhkhjk','ghjkhyuk','2012-04-26 17:14:01',0),(90,'my abs','my article','2012-04-26 17:49:26',0),(91,'test','test','2012-04-26 17:51:33',0),(92,'abstract 4','ECOM 5','2012-04-27 15:53:16',0),(93,'abstract 4','ECOM 5','2012-04-27 15:59:14',0),(94,'Abstract 1','submission1','2012-04-30 15:25:51',0),(95,'abstract 2','submission2','2012-04-30 15:29:44',0),(96,'Abstract 3','submission3','2012-04-30 15:32:40',0),(97,'Abstract 4','submission4','2012-04-30 15:36:19',0),(98,'abstract 5','submission5','2012-04-30 15:37:30',0),(99,'test','test','2012-04-30 16:06:44',0),(100,'Abstract 6','submission6','2012-04-30 18:24:24',0),(101,'Abstract 11','submission11','2012-05-01 17:31:46',0),(102,'Abstract 12','submission12','2012-05-01 18:06:44',0),(103,'Abstract 1','ECOM100','2012-05-02 18:04:49',0),(104,'abstract 2','ECOM1.2','2012-05-02 18:13:32',2),(105,'aaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaaa3333','ECOM103','2012-05-03 16:36:45',2),(107,'article1art','article1','2012-05-03 17:03:32',0),(106,'46546546+5+65+','45445565546','2012-05-03 17:00:41',0),(108,'article1article1article1','article1','2012-05-03 17:04:50',0),(109,'tedtd','terst','2012-05-03 17:05:48',0),(110,'vahid hashemi','vahid hashemi','2012-05-03 19:09:45',0),(111,'ECOM100jkhjkhECOM100jkhjkhECOM100jkhjkhECOM100jkhjkh','ECOM100jkhjkh','2012-05-03 19:18:11',0),(112,',mn,mnkjhkljklj','ECOM100km,n,mn','2012-05-03 19:19:36',0),(113,'bvbvbvx','cvbcvxbv','2012-05-03 19:36:39',0),(114,'stupid idea','stupid idea','2012-05-03 19:41:20',0);
/*!40000 ALTER TABLE `revision` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `role_list`
--

DROP TABLE IF EXISTS `role_list`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `role_list` (
  `roleId` int(11) NOT NULL auto_increment,
  `roleName` varchar(255) default NULL,
  `description` varchar(255) default NULL,
  PRIMARY KEY  (`roleId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `role_list`
--

LOCK TABLES `role_list` WRITE;
/*!40000 ALTER TABLE `role_list` DISABLE KEYS */;
INSERT INTO `role_list` VALUES (1,'Author','Author'),(2,'Reviewer','Reviewer'),(3,'Editor','Editor'),(4,'Temp','Temporary Review');
/*!40000 ALTER TABLE `role_list` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `score`
--

DROP TABLE IF EXISTS `score`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `score` (
  `scoreId` int(11) NOT NULL auto_increment,
  `scoreName` varchar(255) default NULL,
  `scoreLevel` varchar(255) default NULL,
  PRIMARY KEY  (`scoreId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `score`
--

LOCK TABLES `score` WRITE;
/*!40000 ALTER TABLE `score` DISABLE KEYS */;
INSERT INTO `score` VALUES (1,'detractor','1'),(2,'indifferent','2'),(3,'favourable','3'),(4,'champion','4');
/*!40000 ALTER TABLE `score` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission`
--

DROP TABLE IF EXISTS `submission`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission` (
  `submissionId` int(11) NOT NULL auto_increment,
  `statusId` int(11) NOT NULL,
  `revisionId` int(11) NOT NULL,
  `submissionDate` timestamp NULL default CURRENT_TIMESTAMP,
  `mainAuthorId` int(11) NOT NULL,
  PRIMARY KEY  (`submissionId`),
  KEY `statusId` (`statusId`),
  KEY `fk_submission_mainAuthor` (`mainAuthorId`),
  KEY `fk_submission_revision` (`revisionId`)
) ENGINE=MyISAM AUTO_INCREMENT=135 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission`
--

LOCK TABLES `submission` WRITE;
/*!40000 ALTER TABLE `submission` DISABLE KEYS */;
INSERT INTO `submission` VALUES (134,8,114,'2012-05-03 19:41:20',158),(133,8,113,'2012-05-03 19:36:39',157),(132,8,112,'2012-05-03 19:19:36',156),(131,8,111,'2012-05-03 19:18:11',155),(130,8,110,'2012-05-03 19:09:45',154),(129,8,109,'2012-05-03 17:05:48',153),(128,9,108,'2012-05-03 17:04:50',152),(127,9,107,'2012-05-03 17:03:32',151),(126,8,106,'2012-05-03 17:00:41',150),(125,10,105,'2012-05-03 16:36:45',148),(124,2,104,'2012-05-02 18:13:32',147),(123,9,103,'2012-05-02 18:04:49',146),(122,2,102,'2012-05-01 18:06:44',145),(121,9,101,'2012-05-01 17:31:47',115),(120,9,100,'2012-04-30 18:24:24',141),(114,9,94,'2012-04-30 15:25:51',109),(115,9,95,'2012-04-30 15:29:44',113),(116,10,96,'2012-04-30 15:32:40',115),(117,9,97,'2012-04-30 15:36:19',116),(118,20,98,'2012-04-30 15:37:30',120),(119,9,99,'2012-04-30 16:06:44',127);
/*!40000 ALTER TABLE `submission` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `submission_status`
--

DROP TABLE IF EXISTS `submission_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `submission_status` (
  `statusId` int(11) NOT NULL auto_increment,
  `statusTitle` varchar(255) default NULL,
  PRIMARY KEY  (`statusId`)
) ENGINE=MyISAM AUTO_INCREMENT=21 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `submission_status`
--

LOCK TABLES `submission_status` WRITE;
/*!40000 ALTER TABLE `submission_status` DISABLE KEYS */;
INSERT INTO `submission_status` VALUES (1,'submited'),(2,'review'),(3,'to publish'),(4,'selected'),(5,'temp review'),(8,'waiting file'),(9,'credit'),(10,'published'),(20,'Rejected');
/*!40000 ALTER TABLE `submission_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `Id` int(11) NOT NULL auto_increment,
  `issue` varchar(255) default NULL,
  `keyword` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  PRIMARY KEY  (`Id`)
) ENGINE=MyISAM AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (1,'yes','no',NULL),(2,'yes','no','omarmercado8@hotmail.com'),(3,'yes','no','omarmercado8@hotmail.com'),(4,'yes','',''),(5,'yes','',''),(6,'yes','no','omarmercado8@hotmail.co'),(7,'yes','no','omarmercado8@hotmail.comm'),(8,'yes','','omarmercado8@hotmail.com');
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `template`
--

DROP TABLE IF EXISTS `template`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `template` (
  `template_id` int(10) NOT NULL auto_increment,
  `template_name` varchar(255) default NULL,
  `template_filename` varchar(255) default NULL,
  `selected` int(10) default NULL,
  PRIMARY KEY  (`template_id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `template`
--

LOCK TABLES `template` WRITE;
/*!40000 ALTER TABLE `template` DISABLE KEYS */;
INSERT INTO `template` VALUES (1,'simple','bootstrap.css',0),(2,'magazine','bootstrap2.css',0),(3,'blue','bootstrap3.css',1);
/*!40000 ALTER TABLE `template` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `temporary_selected`
--

DROP TABLE IF EXISTS `temporary_selected`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `temporary_selected` (
  `id` int(11) NOT NULL auto_increment,
  `submissionId` int(11) NOT NULL,
  `reviewerId` int(11) NOT NULL,
  PRIMARY KEY  (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=74 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `temporary_selected`
--

LOCK TABLES `temporary_selected` WRITE;
/*!40000 ALTER TABLE `temporary_selected` DISABLE KEYS */;
/*!40000 ALTER TABLE `temporary_selected` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `userId` int(11) NOT NULL auto_increment,
  `firstName` varchar(255) default NULL,
  `lastName` varchar(255) default NULL,
  `email` varchar(255) default NULL,
  `userName` varchar(255) default NULL,
  `passWord` varchar(255) default NULL,
  PRIMARY KEY  (`userId`)
) ENGINE=MyISAM AUTO_INCREMENT=160 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (83,'Editor','Editor','userEd','userEd','DFD783497B61B3D59B806868F39A5FF7'),(119,'name4','name4','omar4@gmail4.com','omar4@gmail4.com',NULL),(118,'name4','name4','omar4@gmail3.com','omar4@gmail3.com',NULL),(117,'name4','name4','omar4@gmail2.com','omar4@gmail2.com',NULL),(115,'name3','name3','omar3@gmail.com','omar3@gmail.com','2E39FCE141A0DBC15904908D7C994B5F'),(114,'name2','name2','omar2@gmail2.com','omar2@gmail2.com',NULL),(113,'name2','name2','omar2@gmail.com','omar2@gmail.com','B8E3A662ADA206144544BAFC82B7DD6C'),(112,'name 4','name 4','omar@gmail4.com','omar@gmail4.com',NULL),(110,'name 2','name 2','omar@gmail2.com','omar@gmail2.com',NULL),(111,'name 3','name 3','omar@gmail3.com','omar@gmail3.com',NULL),(116,'name4','name4','omar4@gmail.com','omar4@gmail.com','0DFC368047CF8FEC92D1103AC39A9571'),(94,'aaaa','bbbb','aaaa',NULL,NULL),(109,'name 1','name 1','omar@gmail.com','omar@gmail.com','6382D821D75FDD7F3B2420F3B497A65F'),(107,'name 1','name 1','omarmercado8@gmail.com','omarmercado8@gmail.com','49CA33ECE048F5A7116A85BCAD0293F7'),(108,'','','','',NULL),(120,'name5','name5','omar5@gmail.com','omar5@gmail.com','ADF7EB864F78A3B4AD6164228033D923'),(136,'jijkjkj','r6','reviewer6@gmail.com','reviewer6@gmail.com','HtWOQX'),(135,'r6','r6','reviewer6@gmail.com','reviewer6@gmail.com','aF8RGF'),(134,'r6','r6','reviewer6@gmail.com','reviewer6@gmail.com','RWIE5H'),(133,'r6','r6','reviewer6@gmail.com','reviewer6@gmail.com','e6zl9l'),(132,'r6','r6','reviewer6@gmail.com','reviewer6@gmail.com','pRNNrG'),(131,'r5','r5','reviewer5@gmail.com','reviewer5@gmail.com','6m2a7B'),(127,'test1','test1','testtesttest@hotmail.com','testtesttest@hotmail.com','3F7307600CE8F0D99B6F0CA24C899812'),(128,'test2','test2','testtesttest2@hotmail.com','testtesttest2@hotmail.com',NULL),(129,'test3','test3','testtesttest3@hotmail.com','testtesttest3@hotmail.com',NULL),(130,'test4','test4','testtesttest4@hotmail.com','testtesttest4@hotmail.com',NULL),(137,'r4','r4','reviewer4@gmail.com','reviewer4@gmail.com','270D8EE42A3D85AC1342B53568BBA0E7'),(138,'r3','r3','reviewer3@gmail.com','reviewer3@gmail.com','FD1A52A277464AC566884D1CBBEF03A9'),(139,'r2','r2','reviewer2@gmail.com','reviewer2@gmail.com','10F54979F3B4104FBCC6690C6EE0EA62'),(140,'r1','r1','reviewer@gmail.com','reviewer@gmail.com','774C958C660D2C9AEA863FD7DCF6F201'),(141,'name 6','name 6','omar6@gmail.com','omar6@gmail.com','936FB3C2C2D3B71D96E8A903EAF5CFCC'),(142,'r9','r9','reviewer9@gmail.com','reviewer9@gmail.com','83797F6AFAB44E2C24565D585B2EEE28'),(143,'r8','r8','reviewer8@gmail.com','reviewer8@gmail.com','3F72BC91227BF7AD13847F873A834441'),(144,'r7','r7','reviewer7@gmail.com','reviewer7@gmail.com','9F313062C878D879540E5F89710F5E68'),(145,'name 12','name 12','omar7@gmail.com','omar7@gmail.com','1FB491512184D01E1A720D3A8D25EBEF'),(146,'name 1','name 1','omarmercado99@hotmail.com','omarmercado99@hotmail.com','A4478D4F127A8B4668A00E46BC2ADF22'),(147,'name 1','name 1','omar10@gmail.com','omar10@gmail.com','71EE29756D008C10155E016104CC9889'),(148,'name 13','name 13','omar13@gmail.com','omar13@gmail.com','D681C33A1079903307981D287EC9CFC5'),(149,'name 2','name 2','omar14@gmail2.com','omar14@gmail2.com',NULL),(150,'vahid','hashemi','svahid.hashemi@gmail.com','svahid.hashemi@gmail.com','5C128D6EEA9A4804689D6334E91063E0'),(151,'vahid','hashemi2','wanabeunknonwn@gmail.com','wanabeunknonwn@gmail.com','044F8D35F07672A5806FCE9990087DC5'),(152,'vahid','hashemi3','wisdomtooth84@gmail.com','wisdomtooth84@gmail.com','3A8EDA41C50EF07D15AC630F82A55F52'),(153,'ghghgh','ghghhg','aca08dss@shef.ac.uk','aca08dss@shef.ac.uk','1CB3BC937C06E71170E31716CC941BC8'),(154,'vahid hashemi','vahid hashemi','someone@email.com','someone@email.com','E8869C14543C302B48A075DB03A986C1'),(155,'ECOM100jkhjkh','ECOM100jkhjkh','test@gmail.com','test@gmail.com','F7C35104EB0B13A7AE942E0B2D34DA9B'),(156,'lkjlkj','lkjklj','omahhr13@gmail.com','omahhr13@gmail.com','3F25692655F5C33DB684A3D2D11FEF60'),(157,'xcvbv','cvbvcxb','omar131@gmail.com','omar131@gmail.com','26E12D694B946B20A66AEA1D4A522F65'),(158,'hassan','hasasn','svahid.hashemi@yahoo.com','svahid.hashemi@yahoo.com','2C71540F96D5A922D997ABAD8CAB436A');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_role`
--

DROP TABLE IF EXISTS `user_role`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_role` (
  `userRoldId` int(11) NOT NULL auto_increment,
  `userId` int(11) NOT NULL,
  `roleId` int(11) NOT NULL,
  PRIMARY KEY  (`userRoldId`),
  KEY `fk_userRole_user` (`userId`),
  KEY `fk_userRole_roleList` (`roleId`)
) ENGINE=MyISAM AUTO_INCREMENT=81 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_role`
--

LOCK TABLES `user_role` WRITE;
/*!40000 ALTER TABLE `user_role` DISABLE KEYS */;
INSERT INTO `user_role` VALUES (23,83,3),(24,84,4),(25,87,2),(26,88,2),(27,89,2),(28,90,2),(29,91,1),(30,93,2),(31,94,2),(32,95,1),(33,96,2),(34,97,4),(35,98,1),(36,99,1),(37,100,1),(38,101,1),(39,102,1),(40,103,1),(41,104,3),(42,105,1),(43,106,1),(44,107,1),(45,109,4),(46,113,4),(47,115,4),(48,116,3),(49,120,4),(50,121,2),(51,122,2),(52,123,2),(53,124,2),(54,125,2),(55,126,2),(56,127,4),(57,131,2),(58,136,1),(59,137,2),(60,138,2),(61,139,2),(62,140,2),(63,141,4),(64,142,2),(65,143,2),(66,144,2),(67,145,1),(68,146,4),(69,147,1),(70,148,1),(71,150,1),(72,151,4),(73,152,4),(74,153,2),(75,154,1),(76,155,1),(77,156,1),(78,157,1),(79,158,2);
/*!40000 ALTER TABLE `user_role` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `volume`
--

DROP TABLE IF EXISTS `volume`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `volume` (
  `volumeId` int(11) NOT NULL auto_increment,
  `journalId` int(11) NOT NULL,
  `year` varchar(30) default NULL,
  `description` varchar(255) default NULL,
  `volumeNumber` varchar(255) default NULL,
  `active` int(11) default '1',
  PRIMARY KEY  (`volumeId`),
  KEY `volume_journal` (`journalId`)
) ENGINE=MyISAM AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `volume`
--

LOCK TABLES `volume` WRITE;
/*!40000 ALTER TABLE `volume` DISABLE KEYS */;
INSERT INTO `volume` VALUES (4,0,'2012','','',0);
/*!40000 ALTER TABLE `volume` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2012-05-04  9:54:38
