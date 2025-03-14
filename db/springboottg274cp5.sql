-- MySQL dump 10.13  Distrib 5.7.31, for Linux (x86_64)
--
-- Host: localhost    Database: springboottg274cp5
-- ------------------------------------------------------
-- Server version	5.7.31

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Current Database: `springboottg274cp5`
--

/*!40000 DROP DATABASE IF EXISTS `springboottg274cp5`*/;

CREATE DATABASE /*!32312 IF NOT EXISTS*/ `springboottg274cp5` /*!40100 DEFAULT CHARACTER SET utf8mb4 */;

USE `springboottg274cp5`;

--
-- Table structure for table `chat`
--

DROP TABLE IF EXISTS `chat`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `chat` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `adminid` bigint(20) DEFAULT NULL COMMENT '管理员id',
  `ask` longtext COLLATE utf8mb4_unicode_ci COMMENT '提问',
  `reply` longtext COLLATE utf8mb4_unicode_ci COMMENT '回复',
  `isreply` int(11) DEFAULT NULL COMMENT '是否回复',
  `isread` int(11) DEFAULT '0' COMMENT '已读/未读(1:已读,0:未读)',
  `uname` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '用户头像',
  `uimage` longtext COLLATE utf8mb4_unicode_ci COMMENT '用户名',
  `type` int(11) DEFAULT '1' COMMENT '内容类型(1:文本,2:图片,3:视频,4:文件,5:表情)',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教务秘书反馈';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `chat`
--

LOCK TABLES `chat` WRITE;
/*!40000 ALTER TABLE `chat` DISABLE KEYS */;
INSERT INTO `chat` VALUES (1,'2025-03-05 16:25:57',1,1,'提问1','回复1',1,1,'用户头像1','upload/chat_uimage1.jpg,upload/chat_uimage2.jpg,upload/chat_uimage3.jpg',1),(2,'2025-03-05 16:25:57',2,2,'提问2','回复2',2,2,'用户头像2','upload/chat_uimage2.jpg,upload/chat_uimage3.jpg,upload/chat_uimage4.jpg',2),(3,'2025-03-05 16:25:57',3,3,'提问3','回复3',3,3,'用户头像3','upload/chat_uimage3.jpg,upload/chat_uimage4.jpg,upload/chat_uimage5.jpg',3),(4,'2025-03-05 16:25:57',4,4,'提问4','回复4',4,4,'用户头像4','upload/chat_uimage4.jpg,upload/chat_uimage5.jpg,upload/chat_uimage6.jpg',4),(5,'2025-03-05 16:25:57',5,5,'提问5','回复5',5,5,'用户头像5','upload/chat_uimage5.jpg,upload/chat_uimage6.jpg,upload/chat_uimage7.jpg',5),(6,'2025-03-05 16:25:57',6,6,'提问6','回复6',6,6,'用户头像6','upload/chat_uimage6.jpg,upload/chat_uimage7.jpg,upload/chat_uimage8.jpg',6),(7,'2025-03-05 16:25:57',7,7,'提问7','回复7',7,7,'用户头像7','upload/chat_uimage7.jpg,upload/chat_uimage8.jpg,upload/chat_uimage1.jpg',7),(8,'2025-03-05 16:25:57',8,8,'提问8','回复8',8,8,'用户头像8','upload/chat_uimage8.jpg,upload/chat_uimage1.jpg,upload/chat_uimage2.jpg',8);
/*!40000 ALTER TABLE `chat` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `config`
--

DROP TABLE IF EXISTS `config`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `config` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `name` varchar(100) NOT NULL COMMENT '配置参数名称',
  `value` varchar(100) DEFAULT NULL COMMENT '配置参数值',
  `url` varchar(500) DEFAULT NULL COMMENT 'url',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='配置文件';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `config`
--

LOCK TABLES `config` WRITE;
/*!40000 ALTER TABLE `config` DISABLE KEYS */;
INSERT INTO `config` VALUES (1,'picture1','upload/picture1.jpg',NULL),(2,'picture2','upload/picture2.jpg',NULL),(3,'picture3','upload/picture3.jpg',NULL);
/*!40000 ALTER TABLE `config` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `dianziqianzhang`
--

DROP TABLE IF EXISTS `dianziqianzhang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `dianziqianzhang` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `mingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '名称',
  `yulan` longtext COLLATE utf8mb4_unicode_ci COMMENT '预览',
  `wenjian` longtext COLLATE utf8mb4_unicode_ci COMMENT '文件',
  `gaizhangshijian` datetime DEFAULT NULL COMMENT '盖章时间',
  `caozuoren` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '操作人',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='电子签章';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `dianziqianzhang`
--

LOCK TABLES `dianziqianzhang` WRITE;
/*!40000 ALTER TABLE `dianziqianzhang` DISABLE KEYS */;
INSERT INTO `dianziqianzhang` VALUES (1,'2025-03-05 16:25:57','名称1','upload/dianziqianzhang_yulan1.jpg,upload/dianziqianzhang_yulan2.jpg,upload/dianziqianzhang_yulan3.jpg','','2025-03-06 00:25:57','操作人1',1),(2,'2025-03-05 16:25:57','名称2','upload/dianziqianzhang_yulan2.jpg,upload/dianziqianzhang_yulan3.jpg,upload/dianziqianzhang_yulan4.jpg','','2025-03-06 00:25:57','操作人2',2),(3,'2025-03-05 16:25:57','名称3','upload/dianziqianzhang_yulan3.jpg,upload/dianziqianzhang_yulan4.jpg,upload/dianziqianzhang_yulan5.jpg','','2025-03-06 00:25:57','操作人3',3),(4,'2025-03-05 16:25:57','名称4','upload/dianziqianzhang_yulan4.jpg,upload/dianziqianzhang_yulan5.jpg,upload/dianziqianzhang_yulan6.jpg','','2025-03-06 00:25:57','操作人4',4),(5,'2025-03-05 16:25:57','名称5','upload/dianziqianzhang_yulan5.jpg,upload/dianziqianzhang_yulan6.jpg,upload/dianziqianzhang_yulan7.jpg','','2025-03-06 00:25:57','操作人5',5),(6,'2025-03-05 16:25:57','名称6','upload/dianziqianzhang_yulan6.jpg,upload/dianziqianzhang_yulan7.jpg,upload/dianziqianzhang_yulan8.jpg','','2025-03-06 00:25:57','操作人6',6),(7,'2025-03-05 16:25:57','名称7','upload/dianziqianzhang_yulan7.jpg,upload/dianziqianzhang_yulan8.jpg,upload/dianziqianzhang_yulan1.jpg','','2025-03-06 00:25:57','操作人7',7),(8,'2025-03-05 16:25:57','名称8','upload/dianziqianzhang_yulan8.jpg,upload/dianziqianzhang_yulan1.jpg,upload/dianziqianzhang_yulan2.jpg','','2025-03-06 00:25:57','操作人8',8);
/*!40000 ALTER TABLE `dianziqianzhang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jianchajilu`
--

DROP TABLE IF EXISTS `jianchajilu`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jianchajilu` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xueqi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学期',
  `fengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '封面',
  `jianchaxiang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检查项',
  `jianchaduixiang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '检查对象',
  `jiaoshigonghao` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批改老师',
  `lianxishouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系手机',
  `wanchengshijian` datetime DEFAULT NULL COMMENT '完成时间',
  `wanchengbaogao` longtext COLLATE utf8mb4_unicode_ci COMMENT '完成报告',
  `beizhu` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '备注',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='检查记录';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jianchajilu`
--

LOCK TABLES `jianchajilu` WRITE;
/*!40000 ALTER TABLE `jianchajilu` DISABLE KEYS */;
INSERT INTO `jianchajilu` VALUES (1,'2025-03-05 16:25:57','学期1','upload/jianchajilu_fengmian1.jpg,upload/jianchajilu_fengmian2.jpg,upload/jianchajilu_fengmian3.jpg','检查项1','检查对象1','教师工号1','批改老师1','联系手机1','2025-03-06 00:25:57','','备注1'),(2,'2025-03-05 16:25:57','学期2','upload/jianchajilu_fengmian2.jpg,upload/jianchajilu_fengmian3.jpg,upload/jianchajilu_fengmian4.jpg','检查项2','检查对象2','教师工号2','批改老师2','联系手机2','2025-03-06 00:25:57','','备注2'),(3,'2025-03-05 16:25:57','学期3','upload/jianchajilu_fengmian3.jpg,upload/jianchajilu_fengmian4.jpg,upload/jianchajilu_fengmian5.jpg','检查项3','检查对象3','教师工号3','批改老师3','联系手机3','2025-03-06 00:25:57','','备注3'),(4,'2025-03-05 16:25:57','学期4','upload/jianchajilu_fengmian4.jpg,upload/jianchajilu_fengmian5.jpg,upload/jianchajilu_fengmian6.jpg','检查项4','检查对象4','教师工号4','批改老师4','联系手机4','2025-03-06 00:25:57','','备注4'),(5,'2025-03-05 16:25:57','学期5','upload/jianchajilu_fengmian5.jpg,upload/jianchajilu_fengmian6.jpg,upload/jianchajilu_fengmian7.jpg','检查项5','检查对象5','教师工号5','批改老师5','联系手机5','2025-03-06 00:25:57','','备注5'),(6,'2025-03-05 16:25:57','学期6','upload/jianchajilu_fengmian6.jpg,upload/jianchajilu_fengmian7.jpg,upload/jianchajilu_fengmian8.jpg','检查项6','检查对象6','教师工号6','批改老师6','联系手机6','2025-03-06 00:25:57','','备注6'),(7,'2025-03-05 16:25:57','学期7','upload/jianchajilu_fengmian7.jpg,upload/jianchajilu_fengmian8.jpg,upload/jianchajilu_fengmian1.jpg','检查项7','检查对象7','教师工号7','批改老师7','联系手机7','2025-03-06 00:25:57','','备注7'),(8,'2025-03-05 16:25:57','学期8','upload/jianchajilu_fengmian8.jpg,upload/jianchajilu_fengmian1.jpg,upload/jianchajilu_fengmian2.jpg','检查项8','检查对象8','教师工号8','批改老师8','联系手机8','2025-03-06 00:25:57','','备注8');
/*!40000 ALTER TABLE `jianchajilu` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jianchaxiang`
--

DROP TABLE IF EXISTS `jianchaxiang`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jianchaxiang` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jianchaxiang` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '检查项',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='检查项';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jianchaxiang`
--

LOCK TABLES `jianchaxiang` WRITE;
/*!40000 ALTER TABLE `jianchaxiang` DISABLE KEYS */;
INSERT INTO `jianchaxiang` VALUES (1,'2025-03-05 16:25:57','检查项1'),(2,'2025-03-05 16:25:57','检查项2'),(3,'2025-03-05 16:25:57','检查项3'),(4,'2025-03-05 16:25:57','检查项4'),(5,'2025-03-05 16:25:57','检查项5'),(6,'2025-03-05 16:25:57','检查项6'),(7,'2025-03-05 16:25:57','检查项7'),(8,'2025-03-05 16:25:57','检查项8');
/*!40000 ALTER TABLE `jianchaxiang` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `jiaoshi`
--

DROP TABLE IF EXISTS `jiaoshi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `jiaoshi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `jiaoshigonghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '教师工号',
  `mima` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '密码',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '教师姓名',
  `xingbie` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '性别',
  `youxiang` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '邮箱',
  `lianxishouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系手机',
  `zhicheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '职称',
  `zhuanye` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '专业',
  `xueqi` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '学期',
  `kecheng` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '课程',
  `shangkebanji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '上课班级',
  `touxiang` longtext COLLATE utf8mb4_unicode_ci COMMENT '头像',
  `status` int(11) DEFAULT '0' COMMENT '状态',
  PRIMARY KEY (`id`),
  UNIQUE KEY `jiaoshigonghao` (`jiaoshigonghao`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='教师';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `jiaoshi`
--

LOCK TABLES `jiaoshi` WRITE;
/*!40000 ALTER TABLE `jiaoshi` DISABLE KEYS */;
INSERT INTO `jiaoshi` VALUES (11,'2025-03-05 16:25:57','教师工号1','123456','教师姓名1','男','773890001@qq.com','13823888881','高级讲师','软件工程','学期1','课程1','上课班级1','upload/jiaoshi_touxiang1.jpg',0),(12,'2025-03-05 16:25:57','教师工号2','123456','教师姓名2','男','773890002@qq.com','13823888882','高级讲师','软件工程','学期2','课程2','上课班级2','upload/jiaoshi_touxiang2.jpg',0),(13,'2025-03-05 16:25:57','教师工号3','123456','教师姓名3','男','773890003@qq.com','13823888883','高级讲师','软件工程','学期3','课程3','上课班级3','upload/jiaoshi_touxiang3.jpg',0),(14,'2025-03-05 16:25:57','教师工号4','123456','教师姓名4','男','773890004@qq.com','13823888884','高级讲师','软件工程','学期4','课程4','上课班级4','upload/jiaoshi_touxiang4.jpg',0),(15,'2025-03-05 16:25:57','教师工号5','123456','教师姓名5','男','773890005@qq.com','13823888885','高级讲师','软件工程','学期5','课程5','上课班级5','upload/jiaoshi_touxiang5.jpg',0),(16,'2025-03-05 16:25:57','教师工号6','123456','教师姓名6','男','773890006@qq.com','13823888886','高级讲师','软件工程','学期6','课程6','上课班级6','upload/jiaoshi_touxiang6.jpg',0),(17,'2025-03-05 16:25:57','教师工号7','123456','教师姓名7','男','773890007@qq.com','13823888887','高级讲师','软件工程','学期7','课程7','上课班级7','upload/jiaoshi_touxiang7.jpg',0),(18,'2025-03-05 16:25:57','教师工号8','123456','教师姓名8','男','773890008@qq.com','13823888888','高级讲师','软件工程','学期8','课程8','上课班级8','upload/jiaoshi_touxiang8.jpg',0);
/*!40000 ALTER TABLE `jiaoshi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `kaohecailiao`
--

DROP TABLE IF EXISTS `kaohecailiao`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `kaohecailiao` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xueqi` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学期',
  `kecheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '课程',
  `fengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '封面',
  `jianchaxiang` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '检查项',
  `jianchaduixiang` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '检查对象',
  `wenjian` longtext COLLATE utf8mb4_unicode_ci COMMENT '文件',
  `wanchengzhuangtai` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '完成状态',
  `renwuneirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '任务内容',
  `jiaoshigonghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批改老师',
  `lianxishouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系手机',
  `zhuanye` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专业',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='考核材料';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `kaohecailiao`
--

LOCK TABLES `kaohecailiao` WRITE;
/*!40000 ALTER TABLE `kaohecailiao` DISABLE KEYS */;
INSERT INTO `kaohecailiao` VALUES (1,'2025-03-05 16:25:57','学期1','课程1','upload/kaohecailiao_fengmian1.jpg,upload/kaohecailiao_fengmian2.jpg,upload/kaohecailiao_fengmian3.jpg','检查项1','检查对象1','','已审核','任务内容1','教师工号1','批改老师1','联系手机1','专业1',1),(2,'2025-03-05 16:25:57','学期2','课程2','upload/kaohecailiao_fengmian2.jpg,upload/kaohecailiao_fengmian3.jpg,upload/kaohecailiao_fengmian4.jpg','检查项2','检查对象2','','已审核','任务内容2','教师工号2','批改老师2','联系手机2','专业2',2),(3,'2025-03-05 16:25:57','学期3','课程3','upload/kaohecailiao_fengmian3.jpg,upload/kaohecailiao_fengmian4.jpg,upload/kaohecailiao_fengmian5.jpg','检查项3','检查对象3','','已审核','任务内容3','教师工号3','批改老师3','联系手机3','专业3',3),(4,'2025-03-05 16:25:57','学期4','课程4','upload/kaohecailiao_fengmian4.jpg,upload/kaohecailiao_fengmian5.jpg,upload/kaohecailiao_fengmian6.jpg','检查项4','检查对象4','','已审核','任务内容4','教师工号4','批改老师4','联系手机4','专业4',4),(5,'2025-03-05 16:25:57','学期5','课程5','upload/kaohecailiao_fengmian5.jpg,upload/kaohecailiao_fengmian6.jpg,upload/kaohecailiao_fengmian7.jpg','检查项5','检查对象5','','已审核','任务内容5','教师工号5','批改老师5','联系手机5','专业5',5),(6,'2025-03-05 16:25:57','学期6','课程6','upload/kaohecailiao_fengmian6.jpg,upload/kaohecailiao_fengmian7.jpg,upload/kaohecailiao_fengmian8.jpg','检查项6','检查对象6','','已审核','任务内容6','教师工号6','批改老师6','联系手机6','专业6',6),(7,'2025-03-05 16:25:57','学期7','课程7','upload/kaohecailiao_fengmian7.jpg,upload/kaohecailiao_fengmian8.jpg,upload/kaohecailiao_fengmian1.jpg','检查项7','检查对象7','','已审核','任务内容7','教师工号7','批改老师7','联系手机7','专业7',7),(8,'2025-03-05 16:25:57','学期8','课程8','upload/kaohecailiao_fengmian8.jpg,upload/kaohecailiao_fengmian1.jpg,upload/kaohecailiao_fengmian2.jpg','检查项8','检查对象8','','已审核','任务内容8','教师工号8','批改老师8','联系手机8','专业8',8);
/*!40000 ALTER TABLE `kaohecailiao` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `lunwenxinxi`
--

DROP TABLE IF EXISTS `lunwenxinxi`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `lunwenxinxi` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `xueqi` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学期',
  `lunwenmingcheng` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '论文名称',
  `xueshengxingming` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '学生姓名',
  `fengmian` longtext COLLATE utf8mb4_unicode_ci COMMENT '封面',
  `jianchaxiang` varchar(50) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '检查项',
  `jianchaduixiang` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '检查对象',
  `wenjian` longtext COLLATE utf8mb4_unicode_ci COMMENT '文件',
  `wanchengzhuangtai` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '完成状态',
  `renwuneirong` longtext COLLATE utf8mb4_unicode_ci COMMENT '任务内容',
  `jiaoshigonghao` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '教师工号',
  `jiaoshixingming` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '批改老师',
  `lianxishouji` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '联系手机',
  `zhuanye` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '专业',
  `userid` bigint(20) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='论文信息';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `lunwenxinxi`
--

LOCK TABLES `lunwenxinxi` WRITE;
/*!40000 ALTER TABLE `lunwenxinxi` DISABLE KEYS */;
INSERT INTO `lunwenxinxi` VALUES (1,'2025-03-05 16:25:57','学期1','论文名称1','学生姓名1','upload/lunwenxinxi_fengmian1.jpg,upload/lunwenxinxi_fengmian2.jpg,upload/lunwenxinxi_fengmian3.jpg','检查项1','检查对象1','','已审核','任务内容1','教师工号1','批改老师1','联系手机1','专业1',1),(2,'2025-03-05 16:25:57','学期2','论文名称2','学生姓名2','upload/lunwenxinxi_fengmian2.jpg,upload/lunwenxinxi_fengmian3.jpg,upload/lunwenxinxi_fengmian4.jpg','检查项2','检查对象2','','已审核','任务内容2','教师工号2','批改老师2','联系手机2','专业2',2),(3,'2025-03-05 16:25:57','学期3','论文名称3','学生姓名3','upload/lunwenxinxi_fengmian3.jpg,upload/lunwenxinxi_fengmian4.jpg,upload/lunwenxinxi_fengmian5.jpg','检查项3','检查对象3','','已审核','任务内容3','教师工号3','批改老师3','联系手机3','专业3',3),(4,'2025-03-05 16:25:57','学期4','论文名称4','学生姓名4','upload/lunwenxinxi_fengmian4.jpg,upload/lunwenxinxi_fengmian5.jpg,upload/lunwenxinxi_fengmian6.jpg','检查项4','检查对象4','','已审核','任务内容4','教师工号4','批改老师4','联系手机4','专业4',4),(5,'2025-03-05 16:25:57','学期5','论文名称5','学生姓名5','upload/lunwenxinxi_fengmian5.jpg,upload/lunwenxinxi_fengmian6.jpg,upload/lunwenxinxi_fengmian7.jpg','检查项5','检查对象5','','已审核','任务内容5','教师工号5','批改老师5','联系手机5','专业5',5),(6,'2025-03-05 16:25:57','学期6','论文名称6','学生姓名6','upload/lunwenxinxi_fengmian6.jpg,upload/lunwenxinxi_fengmian7.jpg,upload/lunwenxinxi_fengmian8.jpg','检查项6','检查对象6','','已审核','任务内容6','教师工号6','批改老师6','联系手机6','专业6',6),(7,'2025-03-05 16:25:57','学期7','论文名称7','学生姓名7','upload/lunwenxinxi_fengmian7.jpg,upload/lunwenxinxi_fengmian8.jpg,upload/lunwenxinxi_fengmian1.jpg','检查项7','检查对象7','','已审核','任务内容7','教师工号7','批改老师7','联系手机7','专业7',7),(8,'2025-03-05 16:25:57','学期8','论文名称8','学生姓名8','upload/lunwenxinxi_fengmian8.jpg,upload/lunwenxinxi_fengmian1.jpg,upload/lunwenxinxi_fengmian2.jpg','检查项8','检查对象8','','已审核','任务内容8','教师工号8','批改老师8','联系手机8','专业8',8);
/*!40000 ALTER TABLE `lunwenxinxi` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `systemintro`
--

DROP TABLE IF EXISTS `systemintro`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `systemintro` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `title` varchar(200) COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '标题',
  `subtitle` varchar(200) COLLATE utf8mb4_unicode_ci DEFAULT NULL COMMENT '副标题',
  `content` longtext COLLATE utf8mb4_unicode_ci NOT NULL COMMENT '内容',
  `picture1` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片1',
  `picture2` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片2',
  `picture3` longtext COLLATE utf8mb4_unicode_ci COMMENT '图片3',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci COMMENT='系统简介';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `systemintro`
--

LOCK TABLES `systemintro` WRITE;
/*!40000 ALTER TABLE `systemintro` DISABLE KEYS */;
INSERT INTO `systemintro` VALUES (1,'2025-03-05 16:25:57','系统简介','SYSTEM INTRODUCTION','在平静的海平面上，每个人都可以成为领航员。但如果只有阳光而没有阴影，只有欢乐而没有痛苦，那就不是完整的人生。就拿最幸福的人来说吧——他的幸福是一团纠结的纱线。痛苦和幸福轮番而至，让我们悲喜交集，甚至死亡都让人生更加可爱。人在生命的严肃时刻，在悲伤与丧亲的阴影下，才最接近真实的自我。在生活和事业的各个方面，才智的功能远不如性格，头脑的功能远不如心性，天分远不如自制力、毅力与教养。我始终认为内心开始过严肃生活的人，他外在的生活会开始变得更为俭朴。在一个奢侈浪费的年代，但愿我能让世人了解：人类真正的需求是多么的稀少。不重蹈覆辙才是真正的醒悟。比别人优秀并无任何高贵之处，真正的高贵在于超越从前的自我。','upload/systemintro_picture1.jpg','upload/systemintro_picture2.jpg','upload/systemintro_picture3.jpg');
/*!40000 ALTER TABLE `systemintro` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `token`
--

DROP TABLE IF EXISTS `token`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `token` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `userid` bigint(20) NOT NULL COMMENT '用户id',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `tablename` varchar(100) DEFAULT NULL COMMENT '表名',
  `role` varchar(100) DEFAULT NULL COMMENT '角色',
  `token` varchar(200) NOT NULL COMMENT '密码',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  `expiratedtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '过期时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='token表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `token`
--

LOCK TABLES `token` WRITE;
/*!40000 ALTER TABLE `token` DISABLE KEYS */;
/*!40000 ALTER TABLE `token` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `username` varchar(100) NOT NULL COMMENT '用户名',
  `password` varchar(100) NOT NULL COMMENT '密码',
  `image` varchar(200) DEFAULT NULL COMMENT '头像',
  `role` varchar(100) DEFAULT '管理员' COMMENT '角色',
  `addtime` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '新增时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='管理员表';
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'admin','admin','upload/image1.jpg','管理员','2025-03-05 16:25:57');
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

-- Dump completed on 2025-03-06 16:57:05
