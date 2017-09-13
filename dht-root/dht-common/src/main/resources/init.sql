DROP TABLE IF EXISTS `t_attachment`;
CREATE TABLE `t_attachment` (
  `id` bigint(11) NOT NULL,
  `uu_id` varchar(255) DEFAULT NULL COMMENT '附件唯一id（同一图片压缩后uuid 不变）',
  `name` varchar(200) DEFAULT NULL COMMENT '附件名称',
  `type` varchar(20) DEFAULT NULL COMMENT '附件类型',
  `status` bigint(1) DEFAULT '0' COMMENT '附件状态（0 未启用，1 己启用)未启用附件一天后进行删除处理',
  `path` varchar(480) DEFAULT NULL COMMENT '存放路径（相对位置）',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='系统附件表(用于存放上传物品)';
