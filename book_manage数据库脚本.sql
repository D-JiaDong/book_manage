-- --------------------------------------------------------
-- 主机:                           localhost
-- 服务器版本:                        8.0.19 - MySQL Community Server - GPL
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  8.2.0.4675
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 library_management_system1 的数据库结构
CREATE DATABASE IF NOT EXISTS `library_management_system1` /*!40100 DEFAULT CHARACTER SET utf8 */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `library_management_system1`;


-- 导出  表 library_management_system1.book_to_booktype 结构
CREATE TABLE IF NOT EXISTS `book_to_booktype` (
  `Book_id` int NOT NULL,
  `Type_id` int NOT NULL,
  PRIMARY KEY (`Book_id`,`Type_id`),
  KEY `FK_Relationship_2` (`Type_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  library_management_system1.book_to_booktype 的数据：~20 rows (大约)
/*!40000 ALTER TABLE `book_to_booktype` DISABLE KEYS */;
INSERT INTO `book_to_booktype` (`Book_id`, `Type_id`) VALUES
	(1, 1),
	(2, 1),
	(3, 1),
	(4, 1),
	(5, 2),
	(6, 2),
	(7, 2),
	(8, 2),
	(9, 2),
	(10, 3),
	(11, 3),
	(12, 3),
	(13, 4),
	(14, 4),
	(15, 5),
	(16, 5),
	(17, 5),
	(18, 5),
	(24, 5),
	(25, 5);
/*!40000 ALTER TABLE `book_to_booktype` ENABLE KEYS */;


-- 导出  表 library_management_system1.t_admin 结构
CREATE TABLE IF NOT EXISTS `t_admin` (
  `Adm_id` int NOT NULL AUTO_INCREMENT,
  `Adm_name` varchar(255) DEFAULT NULL,
  `Adm_sex` varchar(255) DEFAULT NULL,
  `Adm_age` int DEFAULT NULL,
  `Adm_tel` varchar(255) DEFAULT NULL,
  `Adm_pwd` varchar(255) DEFAULT NULL,
  `Adm_pic` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Adm_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  library_management_system1.t_admin 的数据：~6 rows (大约)
/*!40000 ALTER TABLE `t_admin` DISABLE KEYS */;
INSERT INTO `t_admin` (`Adm_id`, `Adm_name`, `Adm_sex`, `Adm_age`, `Adm_tel`, `Adm_pwd`, `Adm_pic`) VALUES
	(1, '小明', '男', 18, '12345687', '123456', '/static/upload/校徽.png'),
	(2, '小蓝', '女', 18, '213232', '1234', '/static/upload/校徽.png'),
	(3, '张三', '男', 28, '12930389779', '555555', '/static/upload/校徽.png'),
	(4, '王五', '男', 12, '21345', '333333', '/static/upload/校徽.png'),
	(5, '赵六', '男', 16, '1646262', '111111', '/static/upload/校徽.png'),
	(6, '陈雅琴', '男', 80, '110', '123456', '/static/upload/校徽.png');
/*!40000 ALTER TABLE `t_admin` ENABLE KEYS */;


-- 导出  表 library_management_system1.t_book 结构
CREATE TABLE IF NOT EXISTS `t_book` (
  `Book_id` int NOT NULL AUTO_INCREMENT,
  `Book_name` varchar(255) DEFAULT NULL,
  `Book_author` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `Book_price` float DEFAULT NULL,
  `Book_num` int DEFAULT NULL,
  PRIMARY KEY (`Book_id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- 正在导出表  library_management_system1.t_book 的数据：~20 rows (大约)
/*!40000 ALTER TABLE `t_book` DISABLE KEYS */;
INSERT INTO `t_book` (`Book_id`, `Book_name`, `Book_author`, `Book_price`, `Book_num`) VALUES
	(1, '活着', '余华', 26, 20),
	(2, '房思琪的初恋乐园', '林奕含', 30, 30),
	(3, '白夜行', '东野圭吾', 39.5, 40),
	(4, '解忧杂货店', '东野圭吾', 39.5, 38),
	(5, '撒哈拉的故事', '三毛', 15.8, 38),
	(6, '我们仨', '杨绛', 18.8, 38),
	(7, '看见', '柴静', 18.8, 37),
	(8, '浮生六记', '沈复', 5.7, 38),
	(9, '目送', '龙应台', 39, 38),
	(10, '小王子', '圣埃克苏佩里', 22, 38),
	(11, '局外人', '阿尔贝·加繆', 22, 38),
	(12, '时间秩序', '卡洛·罗韦利', 56, 37),
	(13, '从一到无穷大', 'G·伽莫夫', 29, 38),
	(14, '病毒星球', '卡尔·齐默', 48, 37),
	(15, 'Python编程', '埃里克·马瑟斯', 89, 38),
	(16, '算法图解', 'Aditya Bhargava', 49, 37),
	(17, '代码大全(第二版)', '史蒂夫·麦克康奈尔', 128, 38),
	(18, '编译原理与技术', '李文生', 42, 38),
	(24, '2', '1', 20, 20),
	(25, '3', '3', 3, 3);
/*!40000 ALTER TABLE `t_book` ENABLE KEYS */;


-- 导出  表 library_management_system1.t_booktype 结构
CREATE TABLE IF NOT EXISTS `t_booktype` (
  `Type_id` int NOT NULL AUTO_INCREMENT,
  `Type_name` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`Type_id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- 正在导出表  library_management_system1.t_booktype 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `t_booktype` DISABLE KEYS */;
INSERT INTO `t_booktype` (`Type_id`, `Type_name`) VALUES
	(1, '小说'),
	(2, '随笔'),
	(3, '散文'),
	(4, '科普'),
	(5, '编程'),
	(6, 'hhh'),
	(7, 'aaa');
/*!40000 ALTER TABLE `t_booktype` ENABLE KEYS */;


-- 导出  表 library_management_system1.t_user 结构
CREATE TABLE IF NOT EXISTS `t_user` (
  `User_id` int NOT NULL AUTO_INCREMENT,
  `User_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `User_sex` varchar(255) DEFAULT NULL,
  `User_age` int DEFAULT NULL,
  `User_tel` varchar(255) DEFAULT NULL,
  `User_pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `User_pic` varchar(255) DEFAULT NULL,
  `User_intro` varchar(512) DEFAULT NULL,
  PRIMARY KEY (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- 正在导出表  library_management_system1.t_user 的数据：~7 rows (大约)
/*!40000 ALTER TABLE `t_user` DISABLE KEYS */;
INSERT INTO `t_user` (`User_id`, `User_name`, `User_sex`, `User_age`, `User_tel`, `User_pwd`, `User_pic`, `User_intro`) VALUES
	(1, '小红', '女', 18, '1657613', '1234', '/static/upload/校徽.png', NULL),
	(2, '小亮', '男', 18, '22166', '1235', '/static/upload/校徽.png', NULL),
	(3, '笑脸', '男', 12, '123245', '123445345', '/static/upload/校徽.png', NULL),
	(4, '小华', '女', 18, '123456', '10000', '/static/upload/校徽.png', NULL),
	(5, '里昂', '男', 18, '15590032125', '122', '/static/upload/88002910-620b-4a47-8eca-db5a1a66b490BAGE.JPG', '123'),
	(14, 'll', '男', 18, '15590032125', '123456', '/static/upload/4a6fe583-7f3d-4309-bf36-dbf0e4b79d6cBAGE.JPG', '554'),
	(15, '名字', '男', 18, '15590032125', '123', '/static/upload/201539ce-c563-43c2-8d79-1f0f3449ab8fIMG_0513.JPG', '123');
/*!40000 ALTER TABLE `t_user` ENABLE KEYS */;


-- 导出  表 library_management_system1.user_to_book 结构
CREATE TABLE IF NOT EXISTS `user_to_book` (
  `User_id` int NOT NULL,
  `Book_id` int NOT NULL,
  PRIMARY KEY (`User_id`,`Book_id`),
  KEY `FK_Relationship_5` (`Book_id`),
  CONSTRAINT `FK_Relationship_5` FOREIGN KEY (`Book_id`) REFERENCES `t_book` (`Book_id`),
  CONSTRAINT `FK_Relationship_6` FOREIGN KEY (`User_id`) REFERENCES `t_user` (`User_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 正在导出表  library_management_system1.user_to_book 的数据：~5 rows (大约)
/*!40000 ALTER TABLE `user_to_book` DISABLE KEYS */;
INSERT INTO `user_to_book` (`User_id`, `Book_id`) VALUES
	(1, 5),
	(1, 7),
	(1, 12),
	(2, 14),
	(2, 16);
/*!40000 ALTER TABLE `user_to_book` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
