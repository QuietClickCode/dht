SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_aliyun_sms_template
-- ----------------------------
DROP TABLE IF EXISTS `t_aliyun_sms_template`;
CREATE TABLE `t_aliyun_sms_template` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `fun_code` varchar(255) DEFAULT NULL COMMENT '短信功能code',
  `fun_name` varchar(255) DEFAULT NULL COMMENT '短信用途',
  `code` varchar(255) DEFAULT NULL COMMENT '短信模版code',
  `context` varchar(255) DEFAULT NULL COMMENT '模版内容',
  `param` varchar(255) DEFAULT NULL COMMENT '传入参数',
  `create_uid` bigint(11) DEFAULT NULL COMMENT '创建人',
  `create_time` date DEFAULT NULL COMMENT '创建时间',
  `is_valid` int(1) DEFAULT NULL COMMENT '是否有效（0 有效，1 无效）',
  PRIMARY KEY (`id`),
  UNIQUE KEY `idx_fun_code_unique` (`fun_code`) USING BTREE COMMENT '短信用途key 唯 一索引'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='阿里云短信模版管理';
SET FOREIGN_KEY_CHECKS=1;
