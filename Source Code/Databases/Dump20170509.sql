-- MySQL dump 10.13  Distrib 5.7.17, for Win64 (x86_64)
--
-- Host: localhost    Database: hcs_schema
-- ------------------------------------------------------
-- Server version	5.7.17-log

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
-- Table structure for table `patient_account_table`
--

DROP TABLE IF EXISTS `patient_account_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `patient_account_table` (
  `patient_name` varchar(45) NOT NULL,
  `address` varchar(100) DEFAULT NULL,
  `phone_number` varchar(45) DEFAULT NULL,
  `email` varchar(45) DEFAULT NULL,
  `SSN` varchar(45) NOT NULL,
  `insurance` varchar(45) DEFAULT 'HCS HealthCare',
  PRIMARY KEY (`SSN`),
  UNIQUE KEY `SSN_UNIQUE` (`SSN`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `patient_account_table`
--

LOCK TABLES `patient_account_table` WRITE;
/*!40000 ALTER TABLE `patient_account_table` DISABLE KEYS */;
INSERT INTO `patient_account_table` VALUES ('George Green','100 Green Road','100-000-0000','george.green@guerrillamail.de','100-00-0000','HCS HealthCare'),('Henry Howard','200 Howard Road','200-000-0000','henry.howard@guerrillamail.de','200-00-0000','HCS HealthCare'),('Jackie June','300 June Road','300-000-0000','jackie.june@guerrillamail.de','300-00-0000','HCS HealthCare'),('Kaley Kyle','400 Kyle Road','400-000-0000','kaley.kyle@guerrillamail.de','400-00-0000','HCS HealthCare');
/*!40000 ALTER TABLE `patient_account_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `payment_table`
--

DROP TABLE IF EXISTS `payment_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `payment_table` (
  `patient_name` varchar(45) NOT NULL,
  `date` varchar(45) NOT NULL,
  `payment_type` varchar(45) NOT NULL,
  `is_paid` varchar(45) CHARACTER SET utf8 COLLATE utf8_bin NOT NULL DEFAULT '0',
  `amount` double DEFAULT '0',
  `payment_ref` varchar(45) DEFAULT NULL,
  `card` varchar(45) DEFAULT NULL,
  `receipt_given` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patient_name`,`date`,`payment_type`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `payment_table`
--

LOCK TABLES `payment_table` WRITE;
/*!40000 ALTER TABLE `payment_table` DISABLE KEYS */;
INSERT INTO `payment_table` VALUES ('George Green','2017-05-09','Copay','Paid',30,'864944024','1000-0000-0000-0000','on'),('George Green','2017-05-09','Invoice','Paid',100,'150081119','1000-0000-0000-0000','on'),('Kaley Kyle','2017-05-09','Invoice','Unpaid',25,NULL,NULL,NULL);
/*!40000 ALTER TABLE `payment_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `permission_table`
--

DROP TABLE IF EXISTS `permission_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `permission_table` (
  `user_type` varchar(45) NOT NULL,
  `name` varchar(45) NOT NULL,
  PRIMARY KEY (`user_type`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `permission_table`
--

LOCK TABLES `permission_table` WRITE;
/*!40000 ALTER TABLE `permission_table` DISABLE KEYS */;
INSERT INTO `permission_table` VALUES ('staff','Staff Larry');
/*!40000 ALTER TABLE `permission_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reports_table`
--

DROP TABLE IF EXISTS `reports_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reports_table` (
  `date` varchar(45) NOT NULL,
  `doctor` varchar(45) DEFAULT NULL,
  `num_patients` int(11) DEFAULT '0',
  `income` double DEFAULT '0',
  PRIMARY KEY (`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reports_table`
--

LOCK TABLES `reports_table` WRITE;
/*!40000 ALTER TABLE `reports_table` DISABLE KEYS */;
INSERT INTO `reports_table` VALUES ('2017-05-09','Bob Brown',1,130);
/*!40000 ALTER TABLE `reports_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `schedule_table`
--

DROP TABLE IF EXISTS `schedule_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `schedule_table` (
  `doctor` varchar(45) NOT NULL,
  `date` varchar(10) NOT NULL,
  `half_hour` varchar(45) NOT NULL,
  `patient_name` varchar(45) NOT NULL,
  PRIMARY KEY (`doctor`,`date`,`half_hour`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `schedule_table`
--

LOCK TABLES `schedule_table` WRITE;
/*!40000 ALTER TABLE `schedule_table` DISABLE KEYS */;
/*!40000 ALTER TABLE `schedule_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `treatment_table`
--

DROP TABLE IF EXISTS `treatment_table`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `treatment_table` (
  `patient_name` varchar(45) NOT NULL,
  `date` varchar(10) NOT NULL,
  `weight` varchar(45) DEFAULT NULL,
  `height` varchar(45) DEFAULT NULL,
  `blood_pressure` varchar(45) DEFAULT NULL,
  `reason_for_visit` varchar(100) DEFAULT NULL,
  `treatment` varchar(100) DEFAULT NULL,
  `prescription` varchar(100) DEFAULT NULL,
  `doctor` varchar(45) DEFAULT NULL,
  PRIMARY KEY (`patient_name`,`date`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `treatment_table`
--

LOCK TABLES `treatment_table` WRITE;
/*!40000 ALTER TABLE `treatment_table` DISABLE KEYS */;
INSERT INTO `treatment_table` VALUES ('George Green','2017-05-09','125','5\'60','123','High Fever','Dietary Advice','Vicks','Bob Brown');
/*!40000 ALTER TABLE `treatment_table` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'hcs_schema'
--

--
-- Dumping routines for database 'hcs_schema'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-05-09 10:00:25
