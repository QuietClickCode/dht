/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50718
Source Host           : localhost:3306
Source Database       : cra

Target Server Type    : MYSQL
Target Server Version : 50718
File Encoding         : 65001

Date: 2017-09-27 10:24:55
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS `t_menu`;
CREATE TABLE `t_menu` (
  `id` int(10) NOT NULL AUTO_INCREMENT COMMENT '菜单id',
  `parent_id` int(10) DEFAULT NULL COMMENT '父级菜单id',
  `type` int(1) DEFAULT NULL COMMENT '菜单类型（0资源 ，1 菜单，2 功能按钮)',
  `resourse` char(200) DEFAULT NULL COMMENT '资源名称',
  `url` char(200) DEFAULT NULL COMMENT '菜单的url',
  `label` char(200) DEFAULT NULL COMMENT '菜单名称',
  `icon` char(200) DEFAULT NULL COMMENT '图片地址（相对）',
  `is_valid` int(1) DEFAULT NULL COMMENT '是否有效(0启用，1 未启用）',
  `description` char(200) DEFAULT NULL COMMENT '菜单描述',
  `sort` int(1) DEFAULT NULL COMMENT '菜单排序',
  `is_delete` bigint(1) DEFAULT '0' COMMENT '是否删除（0 未删除，1 删除）',
  `is_change` bigint(1) DEFAULT '0' COMMENT '是否变更（前端是否变更过资源属性，如果己变更则以前端口为准）',
  `version` bigint(3) DEFAULT '0' COMMENT '数据版本号',
  PRIMARY KEY (`id`),
  UNIQUE KEY `menu_resourse_unique` (`resourse`) USING BTREE,
  KEY `menu_fk_parse_id` (`parent_id`),
  KEY `idx_valid_delete` (`is_valid`,`is_delete`),
  CONSTRAINT `menu_fk_parse_id` FOREIGN KEY (`parent_id`) REFERENCES `t_menu` (`id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Table structure for t_organization
-- ----------------------------
DROP TABLE IF EXISTS `t_organization`;
CREATE TABLE `t_organization` (
  `org_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '组织结构id',
  `org_name` varchar(120) DEFAULT NULL COMMENT '组织结构名称',
  `org_pid` bigint(11) DEFAULT NULL COMMENT '上级组织',
  `org_des` varchar(480) DEFAULT NULL COMMENT '组织结构描述',
  `org_sort` int(3) DEFAULT NULL COMMENT '显示顺序',
  `is_valid` int(2) DEFAULT '0' COMMENT '是否启用（0 启用，1 未启用）',
  `is_delete` int(2) DEFAULT '0' COMMENT '是否删除（0 正常，1 删除）',
  `version` int(5) DEFAULT '0' COMMENT '数据版本',
  PRIMARY KEY (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='组织结构';

-- ----------------------------
-- Table structure for t_organization_user
-- ----------------------------
DROP TABLE IF EXISTS `t_organization_user`;
CREATE TABLE `t_organization_user` (
  `os_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '组织结构人员id',
  `os_org_id` bigint(11) DEFAULT NULL COMMENT '组织id',
  `os_su_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`os_id`),
  UNIQUE KEY `index_org_user` (`os_org_id`,`os_su_id`) COMMENT '组织人员唯一索引（一个部门只能关联一次人员）'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='组织机构人员';

-- ----------------------------
-- Table structure for t_org_menu_permission
-- ----------------------------
DROP TABLE IF EXISTS `t_org_menu_permission`;
CREATE TABLE `t_org_menu_permission` (
  `om_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `rs_id` bigint(11) DEFAULT NULL,
  `org_id` bigint(11) DEFAULT NULL,
  PRIMARY KEY (`om_id`),
  UNIQUE KEY `idx_menu_org_id_unique` (`rs_id`,`org_id`) COMMENT '菜单部门唯一值',
  KEY `rs_id` (`rs_id`),
  KEY `org_id` (`org_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='菜单部门权限';

-- ----------------------------
-- Table structure for t_org_user
-- ----------------------------
DROP TABLE IF EXISTS `t_org_user`;
CREATE TABLE `t_org_user` (
  `ou_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `ou_org_id` bigint(11) DEFAULT NULL COMMENT '部门id',
  `ou_s_id` bigint(11) DEFAULT NULL COMMENT '用户id',
  PRIMARY KEY (`ou_id`),
  KEY `ou_fk_org_id` (`ou_org_id`),
  KEY `ou_fk_user_id` (`ou_s_id`),
  CONSTRAINT `ou_fk_org_id` FOREIGN KEY (`ou_org_id`) REFERENCES `t_org_menu_permission` (`org_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `ou_fk_user_id` FOREIGN KEY (`ou_s_id`) REFERENCES `t_sys_user` (`u_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='部门人员表';

-- ----------------------------
-- Table structure for t_sys_user
-- ----------------------------
DROP TABLE IF EXISTS `t_sys_user`;
CREATE TABLE `t_sys_user` (
  `u_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `u_account` varchar(64) DEFAULT NULL COMMENT '登录帐号',
  `u_name` varchar(64) DEFAULT NULL COMMENT '用户名',
  `u_password` varchar(64) DEFAULT NULL COMMENT '密码',
  `u_create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_valid` bigint(1) DEFAULT '0' COMMENT '用户状态（0 启用，1 停用）',
  `is_delete` bigint(1) DEFAULT '0' COMMENT '是否删除（0 未删除，1 删除）',
  `version` bigint(5) DEFAULT '0' COMMENT '数据乐观锁',
  PRIMARY KEY (`u_id`),
  UNIQUE KEY `idx_account_unique` (`u_account`) COMMENT '唯一登录信息'
) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8 COMMENT='公司员工表';
SET FOREIGN_KEY_CHECKS=1;
