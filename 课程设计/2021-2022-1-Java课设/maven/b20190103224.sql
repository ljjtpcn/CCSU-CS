-- MySQL dump 10.13  Distrib 5.5.40, for Win64 (x86)
--
-- Host: 118.31.7.116    Database: b20190103224
-- ------------------------------------------------------
-- Server version	5.6.50-log

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
-- Table structure for table `manager`
--

DROP TABLE IF EXISTS `manager`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `manager` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(20) DEFAULT NULL,
  `pwd` varchar(20) DEFAULT NULL,
  `sex` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `manager`
--

LOCK TABLES `manager` WRITE;
/*!40000 ALTER TABLE `manager` DISABLE KEYS */;
INSERT INTO `manager` VALUES (1,'ljj','rootroot','男'),(2,'db','rootroot','男'),(3,'test','rootroot','女'),(5,'test2','rootroot','男'),(6,'111','111','女'),(7,'admin','123456','男'),(8,'123','123','男'),(9,'admin123','123456','男'),(10,'1234','123','男'),(11,'233','456','男'),(12,'1','1','女'),(13,'123456','11','女');
/*!40000 ALTER TABLE `manager` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `students`
--

DROP TABLE IF EXISTS `students`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `students` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `username` varchar(255) DEFAULT NULL COMMENT '用户名',
  `changeMoney` double(30,3) DEFAULT NULL COMMENT '改变金额',
  `classify` varchar(255) DEFAULT NULL COMMENT '分类',
  `modifyDate` date DEFAULT NULL COMMENT '记录日期',
  `note` varchar(255) DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `students`
--

LOCK TABLES `students` WRITE;
/*!40000 ALTER TABLE `students` DISABLE KEYS */;
INSERT INTO `students` VALUES (2,'test',111.000,'服饰装扮','2021-04-23','买衣服'),(3,'test',335.000,'日用百货','2021-12-08','买了个空调'),(4,'test',-478.000,'数码电器','2021-08-21','买了个手机'),(5,'test',-123.000,'交通出行','2021-11-30','打了个的士'),(6,'test',358.000,'医疗健康','2021-12-09','感冒药'),(7,'test',1250.000,'投资理财','2021-11-29','aaa'),(8,'test',24.000,'充值缴费','2021-07-16','bbb'),(10,'test',2510.000,'红包','2021-03-17','ccc'),(13,'test',1245.000,'充值缴费','2021-02-10','fff'),(14,'test',111.000,'投资理财','2021-06-02','ggg'),(15,'test',34.000,'服饰装扮','2021-05-12','hhh'),(16,'test',-458.000,'餐饮美食','2021-02-11','eee'),(17,'test',895.000,'红包','2021-07-09','ggg'),(19,'test',877.000,'日用百货','2021-12-15','qqq'),(20,'test',409.000,'数码电器','2021-01-13','www'),(22,'test',-464.000,'红包','2021-08-05','rttt'),(23,'test',878.000,'红包','2021-12-17','aaaaa'),(24,'test',500.000,'转账','2021-08-11','dddd'),(25,'test',-2000.000,'充值缴费','2021-11-26','fff'),(26,'test',90.000,'投资理财','2021-06-02','财'),(28,'test',-1800.000,'餐饮美食','2021-12-09','eee'),(29,'test',-999.000,'红包','2021-02-14','ggg'),(30,'test',999.000,'交通出行','2021-01-15','tes'),(31,'test',20.000,'日用百货','2021-03-12','qqq'),(32,'test',2258.000,'数码电器','2021-09-08','www'),(33,'test',56.000,'数码电器','2021-05-13','err'),(34,'test',655.000,'红包','2021-08-05','rttt'),(35,'test',-878.000,'红包','2021-08-17','aaaaa'),(41,'test',105.000,'dawdaw','2020-11-21','dada'),(42,'test',60.000,'达娃大','2021-12-01','达瓦'),(43,'test',99.320,'衣服消费','2021-11-17','今天冷买了衣服'),(45,'test',158.000,'dwada','2021-01-14','wdadawd'),(46,'test',158.326,'吃饭','2021-05-15','......'),(47,'test',-125.000,'吃饭','2021-02-10','吃饭'),(48,'test',-1890.000,'买电脑','2021-10-21',NULL),(49,'test',-125.000,'买花','2021-05-20',NULL),(50,'test',789.000,'送礼物','2021-06-16',NULL),(51,'test',568.000,'吃饭','2021-06-22',NULL),(52,'test',-987.000,'吃西餐','2021-08-12',NULL),(53,'test',-1587.000,'早餐','2021-09-30',NULL),(55,'test',-658.000,'买车','2021-02-02',NULL),(56,'test',589.000,'存银行','2021-05-04',NULL),(57,'test',687.000,'投资理财','2021-04-13',NULL),(58,'test',654.000,'吃饭','2021-06-18','霸王餐'),(59,'test',-677.000,'娱乐','2021-11-10',NULL),(60,'test',-741.000,'KTV','2021-06-22',NULL),(61,'test',-2500.000,'旅行','2021-01-06',NULL),(62,'test',-2500.000,'KTV','2021-05-25',NULL),(63,'test',-684.000,'旅行','2021-03-04',NULL),(64,'test',-654.000,'文具','2021-03-13',NULL),(65,'test',-458.000,'电脑','2021-02-12',NULL),(66,'test',-520.000,'礼物','2020-05-20','情人节礼物'),(69,'test',-252.000,'餐饮','2021-06-08','晚饭'),(70,'ljj',99.000,'唯一','2021-11-22','嗯呢'),(71,'ljj',-199.000,'唯二','2021-12-24','呢饿呢'),(72,'db',111.000,'测试','2020-12-24','测试'),(74,'ljj',111.000,'1','1122-01-12','13'),(77,'1234',15461564096854064.000,'kkk','2021-12-24','22'),(78,'1234',15461564096854064.000,'kkk','2021-02-17','22'),(82,'test',1025.000,'王者荣耀','2021-10-08','猴子皮肤'),(83,'test',547.000,'王者荣耀','2021-10-06','猴子皮肤'),(84,'test',914.000,'王者荣耀','2021-01-01','猴子皮肤'),(85,'test',125.000,'王者荣耀','2021-12-22','猴子皮肤'),(86,'test',500.000,'奇迹暖暖','2021-03-09','奇迹暖暖衣服'),(87,'test',-1200.000,'所谓的万','2021-04-14','不知道买了啥'),(88,'test',-2141.000,'达瓦大','2021-07-15','凑数'),(97,'admin123',100.000,'吃饭','2021-12-29','无'),(99,'test',11.200,'分类','2021-08-08','1');
/*!40000 ALTER TABLE `students` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2022-01-03 22:01:15
