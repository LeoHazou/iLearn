CREATE DATABASE  IF NOT EXISTS `ilearn` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `ilearn`;
-- MySQL dump 10.13  Distrib 5.6.13, for Win32 (x86)
--
-- Host: 127.0.0.1    Database: ilearn
-- ------------------------------------------------------
-- Server version	5.6.15

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
-- Table structure for table `package`
--

DROP TABLE IF EXISTS `package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `package` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Name` varchar(45) NOT NULL,
  `Description` varchar(45) NOT NULL,
  `Price` float(6,2) NOT NULL,
  `Study_Material` tinyint(1) NOT NULL DEFAULT '0',
  `Study_Material_Link` varchar(255) NOT NULL DEFAULT '',
  `Number_Of_Questions` int(5) NOT NULL,
  `Published` tinyint(1) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `package`
--

LOCK TABLES `package` WRITE;
/*!40000 ALTER TABLE `package` DISABLE KEYS */;
INSERT INTO `package` VALUES (5,'Basic Multiplication','Multiplication of number sets from 0 - 12',1.99,1,'pdf\\Multiplication_Study_Guide.pdf',12,1),(6,'Basic Math Word Problems','Word problems for the 4 basic operations',3.99,1,'',15,1),(7,'Chemistry 101','Periodic Table',2.49,0,'',6,1),(8,'English 101','Synonyms',1.49,0,'',7,1),(9,'Aramaic 101','Meaning of common Aramaic words',3.79,0,'',10,0);
/*!40000 ALTER TABLE `package` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `question`
--

DROP TABLE IF EXISTS `question`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `question` (
  `ID` int(10) NOT NULL AUTO_INCREMENT,
  `Pkg_ID` int(11) NOT NULL,
  `Question` varchar(255) NOT NULL,
  `Correct_Answer` varchar(255) NOT NULL,
  `Wrong_Answer1` varchar(255) NOT NULL,
  `Wrong_Answer2` varchar(255) NOT NULL,
  `Wrong_Answer3` varchar(255) NOT NULL,
  `Explanation` varchar(255) NOT NULL,
  PRIMARY KEY (`ID`,`Pkg_ID`),
  KEY `fk_question_Package1_idx` (`Pkg_ID`),
  CONSTRAINT `fk_question_Package1` FOREIGN KEY (`Pkg_ID`) REFERENCES `package` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=65 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `question`
--

LOCK TABLES `question` WRITE;
/*!40000 ALTER TABLE `question` DISABLE KEYS */;
INSERT INTO `question` VALUES (13,5,'1 x 10 =','10','11','1','9','Any number multiplied by 1 stays the same.'),(14,5,'3 x 10 =','30','13','31','3.10','when multiplying by 10, add a zero to the right of the number.'),(15,5,'4 x 4 =','16','44','8','14','A number multiplied by itself is a square.'),(16,5,'5 X 9 =','45','59','14','95','When multiplying by 5, the right digit of the result is 0 or 5.'),(17,5,'11 X 8 = ','88','19','91','32','When 11 is multiplied by a single digit, the answer is two single digits.'),(18,5,'3 x 7 =','21','10','37','73','When multiplying by 3, the sum of the individual numbers in the result is divisible by 3.'),(19,5,'2 x 12 = ','24','22','14','12','When multiplying by 2, add the number to itself.'),(20,5,'6 x 4 = ','24','10','64','46','When you multiply 6 by an even number, they right digit of the result is the same as the number, '),(21,5,'9 x 7 = ','63','97','16','79','When multiplying a single digit by 9,  add the answer\'s digits together, you get 9.'),(22,5,'3 x 4 =','12','34','7','43','When multiplying a number by 4, double the number, then double it again.'),(23,5,'10 x 0 =','0','100','10','010','Any number multiplied by 0 becomes 0.'),(24,5,'6 x 6 = ','36','66','12','0','A number multiplied by itself is a square.'),(26,6,'A builder is building 7 apartments unit, 4 units have 3 bedrooms, and 3 units have 1 bedroom. How many bedrooms total?','15','12','7','14','(4 x 3 ) + ( 3 x 1 ) = '),(27,6,'Michael has 8 Oranges and 4 friends. If he wants to give every friend 1 Oranges, how many Oranges are left with Michael?','4','0','8','1','8 - (4 x 1 ) = '),(28,6,'Scott went to the store 70 times last month. He buys 5 apples each time he goes to the store. How many apples did Scott buy last month?','350','35','75','321','70 times 5'),(29,6,'Lori has 45 boxes of Skittles. Each box holds 3 Skittles. How many Skittles does Lori have?','135','145','90','150','45 boxes with 3 in each is 45 times 3'),(30,6,'Elizabeth starts with 30 bananas. She buys 72 more. How many bananas does Elizabeth end with?','102','112','92','372','30 added to 72'),(31,6,'Mildred collects 77 oranges. Mildred\'s father gives Mildred 2 more. How many oranges does Mildred have?','79','75','72','77','2 added to 77'),(32,6,'Rachel removes 3 bottle caps from a jar. There were originally 84 bottle caps in the jar. How many bottle caps are left in the jar?','81','87','80','84','Take 3 from 84'),(33,6,'There are 57 crayons in a box. Kenneth takes 4 crayons. How many are left?','53','61','55','54','Take 4 from 57'),(34,6,'Eric wants to split a collection of peanuts into groups of 8. Eric has 64 peanuts. How many groups will be created?','8','6','9','64','Divide 64 by 8.'),(35,6,'Jack is inviting 4 friends to a party. He has 4 cookies. How many cookies will each friend get?','1','4','2','0','4 cookies divided by 4 people'),(36,6,'Harry is inviting 3 friends to a party. He has 12 cookies. How many cookies will each friend get?','4','3','1','6','12 cookies divided by 3 people'),(37,6,'Starbursts come in packages of 4. Roy ate 10 Starbursts. How many boxes did he open and how many Starbursts does he have left?','3 Boxes opened, 2 Starbursts leftover. ','2 Boxes opened, 2 Starbursts leftover.','3 Boxes opened, 3 Starbursts leftover.','4 Boxes opened, 2 Starbursts leftover.','(10/4) = 2.5 , but he can\'t open half a box so he opened 3. (4 x 3 ) - 10 = 2 remaining.'),(39,7,'The Atomic Mass number for Hydrogen is:','1','0','-1','None of these','It has no Neutrons.'),(40,7,'What is the Atomic number of Carbon:','6','12.1','17','29','Carbon code is C'),(41,7,'What is the element symbol for Clorine?','Cl','Cd','Ci','None of these','The name is Latin is same as in English'),(42,7,'What is the element symbol for Helium?','He','H','Hl','Hm','Its the second element in the periodic table'),(43,7,'What is the element symbol for Titanium?','Ti','T','Ta','None of these','Latin name same as English Name'),(44,7,'What is the Atomic mass number of Nitrogen?','14.007','17.004','13.004','7','Nitrogen has an equal number of protons and neutrons.'),(45,8,'Run','Travel Rapidly','Walk','Journey','None of these','Running is faster than walking.'),(46,8,'Tolerate:','Put up with','Fix','Total two numbers','Total rating of performance','John tolerates hot weather.'),(47,8,'Ruin','Destroy','Run in a pattern','Type of Nordic stone','Short person','The rain ruined our vacation'),(48,8,'Jeopardize:','Risk','Jurassic Park dinasaur','Geo patterns','None of these','The show Jeopardy'),(49,8,'Engine','Power Plant','German name','Japanese fighting style','English cuisine','Engine check light is on!'),(50,8,'Horse:','Mare','Cub','Toddler','Corvette','The male is called Stallion'),(51,8,'Fluid:','Liquid','Florine ','Float','None of these','If it\'s not fluid, it could be solid.'),(52,9,'Shlomo means:','Peace','Eager','Neat','Center','Hi!'),(53,9,'Arroyo','Leo','Arrow','Bear','Table','Saint Arroryo the Great'),(54,9,'M-sh-iHo','Christ','Evening','Lighter','Abrahamic','Jesus'),(55,9,'Ono','I am','The number one','Steady','Warm','Ono Americanoyo.'),(56,9,'I-sh-to','The number 6','Ishtar the godess','Planet','Tray','average of 5 and 7.'),(57,9,'Sphero','Sphere','Drawer','Vehicle','Pencil','Geometry of 0.'),(58,9,'Melfono','Teacher','Telephone','Survey','Money','PhD'),(59,9,'Sh-am-sh-o','Sun','Moon','Venus','Jupiter','Bright'),(60,9,'Hab Li','Give me','Speak to','Sit with','Talk to','Our daily bread.'),(61,9,'Ramsho','Evening','Eye lashes','Morning','Ear','Moon'),(62,6,'Leo has an average of 95% in two test, if the score on the first test was 98% what is the second score?','92%','96%','88%','95%','95 = ( X + 98 ) /2'),(63,6,'Ahmed has 4 classes, each class is 45 minutes long, how much time does he spend in all 4 classes?','3 hours','2 hours','2.5 Hours','None of these','45 minutes x 4 then convert to hours.'),(64,6,'If the common tip is 10% and the bill was $25 dollars, what is the total with tip?','$27.5','$50','$22.5','None of these','25 + (10 * 25 )/100');
/*!40000 ALTER TABLE `question` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user`
--

DROP TABLE IF EXISTS `user`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `Type` varchar(45) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Password` varchar(45) NOT NULL,
  `First_Name` varchar(35) NOT NULL,
  `Last_Name` varchar(35) NOT NULL,
  `Date_Of_Birth` varchar(45) NOT NULL,
  `Gender` varchar(45) NOT NULL,
  PRIMARY KEY (`ID`),
  UNIQUE KEY `ID_UNIQUE` (`ID`),
  UNIQUE KEY `Email_UNIQUE` (`Email`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user`
--

LOCK TABLES `user` WRITE;
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` VALUES (5,'SuperAdministrator','leohazou@gmail.com','0f759dd1ea6c4c76cedc299039ca4f23','Leo','Hazou','1983-08-07','Male'),(6,'Administrator','aazhari@outlook.com','9193ce3b31332b03f7d8af056c692b84','Ahmed','Azhari','1984-01-01','Male'),(7,'User','ridzee.riddhi@gmail.com','912b7bc95fb9e6885a4685746433f39a','Riddhi','Patel','1990-01-03','Female'),(9,'Administrator','hazouhex@msn.com','eca8975f57265224972ebf2a16174b8e','Leo','Hazou','1986-08-03','Male');
/*!40000 ALTER TABLE `user` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_grade`
--

DROP TABLE IF EXISTS `user_grade`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_grade` (
  `ID` int(11) NOT NULL AUTO_INCREMENT,
  `User_ID` int(11) NOT NULL,
  `Package_ID` int(11) NOT NULL,
  `Grade` float(6,2) NOT NULL,
  PRIMARY KEY (`ID`,`User_ID`,`Package_ID`),
  KEY `fk_User_Has_Package_Grade_User_Has_Package1` (`User_ID`,`Package_ID`),
  CONSTRAINT `fk_User_Has_Package_Grade_User_Has_Package1` FOREIGN KEY (`User_ID`, `Package_ID`) REFERENCES `user_has_package` (`User_ID`, `Package_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_grade`
--

LOCK TABLES `user_grade` WRITE;
/*!40000 ALTER TABLE `user_grade` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_grade` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `user_has_package`
--

DROP TABLE IF EXISTS `user_has_package`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `user_has_package` (
  `User_ID` int(11) NOT NULL,
  `Package_ID` int(11) NOT NULL,
  PRIMARY KEY (`User_ID`,`Package_ID`),
  KEY `fk_User_has_Package_Package1_idx` (`Package_ID`),
  KEY `fk_User_has_Package_User_idx` (`User_ID`),
  CONSTRAINT `fk_User_has_Package_Package1` FOREIGN KEY (`Package_ID`) REFERENCES `package` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  CONSTRAINT `fk_User_has_Package_User` FOREIGN KEY (`User_ID`) REFERENCES `user` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `user_has_package`
--

LOCK TABLES `user_has_package` WRITE;
/*!40000 ALTER TABLE `user_has_package` DISABLE KEYS */;
/*!40000 ALTER TABLE `user_has_package` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2014-05-02  0:09:30
