SET FOREIGN_KEY_CHECKS=0;
DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `save_type` varchar(255) DEFAULT NULL COMMENT '附件存放类型（0 本地，1 远程)',
  `name` varchar(200) DEFAULT NULL COMMENT '附件名称',
  `type` varchar(20) DEFAULT NULL COMMENT '附件类型',
  `status` bigint(1) DEFAULT '0' COMMENT '附件状态（0 未启用，1 己启用)未启用附件一天后进行删除处理',
  `show_url` varchar(500) DEFAULT NULL COMMENT '附件展示路径',
  `save_path` varchar(500) DEFAULT NULL COMMENT '附件存放的相对路径',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统附件表(用于存放上传物品)';
SET FOREIGN_KEY_CHECKS=1;
