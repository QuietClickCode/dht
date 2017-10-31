

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for t_wx_manager
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_manager`;
CREATE TABLE `t_wx_manager` (
  `wx_id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '微信管理id',
  `wx_name` varchar(64) DEFAULT NULL COMMENT '微信号',
  `wx_original_id` varchar(256) DEFAULT NULL COMMENT '微信原始id',
  `wx_type` int(1) DEFAULT NULL COMMENT '公众号类型',
  `app_id` varchar(64) DEFAULT NULL COMMENT '公众号id（与app_secret 使用取得公众号token)',
  `app_secret` varchar(64) DEFAULT NULL COMMENT '公众号秘钥（与app_id 使用取得公众号token)',
  `wx_domain_name` varchar(128) DEFAULT NULL COMMENT '公众号权限域名',
  `wx_domain_url` varchar(123) DEFAULT NULL COMMENT '回调地址',
  `wx_token` varchar(255) DEFAULT NULL COMMENT '微信token',
  `create_uid` bigint(11) DEFAULT NULL COMMENT '创建人',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `is_delete` int(1) DEFAULT '0' COMMENT '是否删除（0 未删除，1 己删除）',
  `is_valid` int(1) DEFAULT '0' COMMENT '是否有效（0 有效，1 失效）',
  `version` int(5) DEFAULT '0' COMMENT '数据乐观锁',
  PRIMARY KEY (`wx_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='公众号管理';


SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_wx_access_token
-- ----------------------------
DROP TABLE IF EXISTS `t_wx_access_token`;
CREATE TABLE `t_wx_access_token` (
  `wat_id` bigint(11) NOT NULL AUTO_INCREMENT,
  `wat_wx_id` bigint(11) NOT NULL COMMENT '关联微信id',
  `wat_token` bigint(11) NOT NULL COMMENT 'access_token是公众号的全局唯一接口调用凭据',
  `wat_token_create_time` datetime DEFAULT NULL COMMENT '申请时间',
  `wat_token_expires_time` datetime DEFAULT NULL COMMENT '失效时间',
  `wat_token_expires` int(8) DEFAULT NULL COMMENT 'token 有效时间（一般是7200秒，失效时间会提前五分钟）',
  PRIMARY KEY (`wat_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='微信认证token';

