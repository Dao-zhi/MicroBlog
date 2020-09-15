/*
Navicat MySQL Data Transfer

Source Server         : blog
Source Server Version : 80018
Source Host           : localhost:3306
Source Database       : blog

Target Server Type    : MYSQL
Target Server Version : 80018
File Encoding         : 65001

Date: 2020-09-15 20:19:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `bc`
-- ----------------------------
DROP TABLE IF EXISTS `bc`;
CREATE TABLE `bc` (
  `BC_id` int(11) NOT NULL AUTO_INCREMENT,
  `Blog_id` int(11) NOT NULL,
  `Comment_id` int(11) NOT NULL,
  PRIMARY KEY (`BC_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of bc
-- ----------------------------

-- ----------------------------
-- Table structure for `blog`
-- ----------------------------
DROP TABLE IF EXISTS `blog`;
CREATE TABLE `blog` (
  `Blog_id` int(11) NOT NULL AUTO_INCREMENT,
  `Blog_content` varchar(139) NOT NULL,
  `Blog_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`Blog_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of blog
-- ----------------------------
INSERT INTO `blog` VALUES ('1', '今天的天气很好！', '2020-09-12 10:19:05');
INSERT INTO `blog` VALUES ('2', '啦啦啦啦啦', '2020-09-15 18:08:40');
INSERT INTO `blog` VALUES ('5', '哈哈哈 太搞笑了', '2020-09-12 11:27:36');
INSERT INTO `blog` VALUES ('6', '在想啥', '2020-09-12 11:36:25');
INSERT INTO `blog` VALUES ('7', '又到了发博客的时间', '2020-09-15 19:16:40');
INSERT INTO `blog` VALUES ('8', '发博客', '2020-09-14 10:11:50');
INSERT INTO `blog` VALUES ('9', '在清华大学发表', '2020-09-14 22:59:39');
INSERT INTO `blog` VALUES ('10', '在发表一次', '2020-09-15 18:41:22');
INSERT INTO `blog` VALUES ('11', '下雨了', '2020-09-15 19:42:29');
INSERT INTO `blog` VALUES ('12', '终于上完课了', '2020-09-15 19:42:39');

-- ----------------------------
-- Table structure for `cc`
-- ----------------------------
DROP TABLE IF EXISTS `cc`;
CREATE TABLE `cc` (
  `CC_id` int(11) NOT NULL AUTO_INCREMENT,
  `Comment_id` int(11) NOT NULL,
  `CL_id` int(11) NOT NULL,
  PRIMARY KEY (`CC_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of cc
-- ----------------------------

-- ----------------------------
-- Table structure for `fan`
-- ----------------------------
DROP TABLE IF EXISTS `fan`;
CREATE TABLE `fan` (
  `FU_id` int(11) NOT NULL AUTO_INCREMENT,
  `Fan_id` int(11) NOT NULL,
  `User_id` int(11) NOT NULL,
  PRIMARY KEY (`FU_id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of fan
-- ----------------------------
INSERT INTO `fan` VALUES ('1', '173', '188');

-- ----------------------------
-- Table structure for `ub`
-- ----------------------------
DROP TABLE IF EXISTS `ub`;
CREATE TABLE `ub` (
  `UB_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) NOT NULL,
  `Blog_id` int(11) NOT NULL,
  PRIMARY KEY (`UB_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ub
-- ----------------------------
INSERT INTO `ub` VALUES ('1', '173', '1');
INSERT INTO `ub` VALUES ('2', '173', '2');
INSERT INTO `ub` VALUES ('3', '173', '3');
INSERT INTO `ub` VALUES ('4', '173', '4');
INSERT INTO `ub` VALUES ('5', '173', '5');
INSERT INTO `ub` VALUES ('6', '173', '6');
INSERT INTO `ub` VALUES ('7', '173', '7');
INSERT INTO `ub` VALUES ('8', '188', '8');
INSERT INTO `ub` VALUES ('9', '188', '9');
INSERT INTO `ub` VALUES ('10', '173', '10');
INSERT INTO `ub` VALUES ('11', '190', '11');
INSERT INTO `ub` VALUES ('12', '190', '12');

-- ----------------------------
-- Table structure for `uc`
-- ----------------------------
DROP TABLE IF EXISTS `uc`;
CREATE TABLE `uc` (
  `UC_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_id` int(11) NOT NULL,
  `Comment_id` int(11) NOT NULL,
  PRIMARY KEY (`UC_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of uc
-- ----------------------------

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `User_id` int(11) NOT NULL AUTO_INCREMENT,
  `User_pwd` varchar(255) NOT NULL,
  `User_name` varchar(255) NOT NULL,
  `User_gender` int(11) DEFAULT NULL,
  `User_birthday` datetime DEFAULT NULL,
  `User_info` varchar(255) DEFAULT NULL,
  `User_time` timestamp NOT NULL ON UPDATE CURRENT_TIMESTAMP,
  `User_address` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`User_id`)
) ENGINE=InnoDB AUTO_INCREMENT=191 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('173', '123456abc', 'Daozhi', '0', '2020-09-09 10:38:18', '我是一个大好人', '2020-09-12 11:01:22', '电子科技大学清水河校区');
INSERT INTO `user` VALUES ('188', '12345678', 'lalala', '1', null, '我是个大学霸', '2020-09-14 22:59:15', '清华大学');
INSERT INTO `user` VALUES ('189', '123abcabc', 'Bigman', null, null, null, '2020-09-15 19:18:28', null);
INSERT INTO `user` VALUES ('190', 'zxcvbnm', 'Haha', null, null, null, '2020-09-15 19:42:05', null);
