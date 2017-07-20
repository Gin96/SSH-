/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50527
Source Host           : 127.0.0.1:3306
Source Database       : innersystem

Target Server Type    : MYSQL
Target Server Version : 50527
File Encoding         : 65001

Date: 2017-07-20 11:17:34
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `author_resources`
-- ----------------------------
DROP TABLE IF EXISTS `author_resources`;
CREATE TABLE `author_resources` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL,
  `path` varchar(255) DEFAULT NULL,
  `parentId` int(11) DEFAULT NULL,
  `rorder` double DEFAULT NULL,
  `createTs` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author_resources
-- ----------------------------
INSERT INTO `author_resources` VALUES ('1', '权限管理', '', null, '1', '2017-07-07 17:18:24', '根节点权限管理');
INSERT INTO `author_resources` VALUES ('2', '资源管理', '/res/toList.htmlx', '1', '2', '2017-07-17 21:22:45', '权限管理的子节点---资源管理');
INSERT INTO `author_resources` VALUES ('3', '角色管理', '/role/toList.htmlx', '1', '3', '2017-07-17 21:22:55', '权限管理的子节点---角色管理');
INSERT INTO `author_resources` VALUES ('4', '手动添加资源一', '/add/new1.htmlx', null, '1', '2017-07-07 20:24:33', '测试');
INSERT INTO `author_resources` VALUES ('5', '手动添加er', '/add/new2.htmlx', null, '2', '2017-07-07 20:30:40', '测试添加资源');
INSERT INTO `author_resources` VALUES ('6', '用户管理', '/user/toList.htmlx', '1', '5', '2017-07-17 21:22:16', '添加用户角色在权限管理中');

-- ----------------------------
-- Table structure for `author_role`
-- ----------------------------
DROP TABLE IF EXISTS `author_role`;
CREATE TABLE `author_role` (
  `roleId` int(11) NOT NULL AUTO_INCREMENT,
  `roleName` varchar(50) DEFAULT NULL,
  `createTs` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `orderBy` double DEFAULT NULL,
  `note` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author_role
-- ----------------------------
INSERT INTO `author_role` VALUES ('1', '权限管理员', '2017-07-07 17:21:40', '1', '配置权限');
INSERT INTO `author_role` VALUES ('2', '角色管理员', '2017-07-07 17:23:10', '2', '配置角色权限');

-- ----------------------------
-- Table structure for `author_role_resouces`
-- ----------------------------
DROP TABLE IF EXISTS `author_role_resouces`;
CREATE TABLE `author_role_resouces` (
  `resoucesId` int(11) NOT NULL DEFAULT '0',
  `roleId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`resoucesId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author_role_resouces
-- ----------------------------
INSERT INTO `author_role_resouces` VALUES ('1', '1');
INSERT INTO `author_role_resouces` VALUES ('1', '2');
INSERT INTO `author_role_resouces` VALUES ('2', '1');
INSERT INTO `author_role_resouces` VALUES ('2', '2');
INSERT INTO `author_role_resouces` VALUES ('3', '1');
INSERT INTO `author_role_resouces` VALUES ('3', '2');
INSERT INTO `author_role_resouces` VALUES ('6', '2');

-- ----------------------------
-- Table structure for `author_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `author_user_role`;
CREATE TABLE `author_user_role` (
  `userId` varchar(32) NOT NULL DEFAULT '',
  `roleId` int(11) NOT NULL DEFAULT '0',
  PRIMARY KEY (`userId`,`roleId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of author_user_role
-- ----------------------------
INSERT INTO `author_user_role` VALUES ('1', '1');

-- ----------------------------
-- Table structure for `simplecode`
-- ----------------------------
DROP TABLE IF EXISTS `simplecode`;
CREATE TABLE `simplecode` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `codeType` varchar(3) DEFAULT NULL,
  `code` varchar(6) DEFAULT NULL,
  `value` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of simplecode
-- ----------------------------
INSERT INTO `simplecode` VALUES ('1', '100', '100001', '普通用户');
INSERT INTO `simplecode` VALUES ('2', '100', '100002', '无权限用户');
INSERT INTO `simplecode` VALUES ('3', '100', '100003', '管理员用户');
INSERT INTO `simplecode` VALUES ('4', '100', '100004', '超级管理员用户');

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `userId` varchar(32) NOT NULL DEFAULT '',
  `uname` varchar(21) DEFAULT NULL,
  `upass` varchar(32) DEFAULT NULL,
  `userType` varchar(6) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `status` varchar(6) DEFAULT NULL,
  `registerDate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `phone` varchar(11) DEFAULT NULL,
  `province` varchar(6) DEFAULT NULL,
  `city` varchar(6) DEFAULT NULL,
  `area` varchar(6) DEFAULT NULL,
  `detail` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`userId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'zhangsan', 'zhangsan', '100003', '1@qq.com', '100001', '2017-07-17 14:50:08', '15830409054', '100001', '100002', '300001', '河北邯郸市');
INSERT INTO `user` VALUES ('2', 'aaa', 'aaa', '100001', null, null, '2017-07-07 10:06:39', null, null, null, null, null);
