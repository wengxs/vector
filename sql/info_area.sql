SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `info_area`;
CREATE TABLE `info_area` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `code` varchar(12) NOT NULL COMMENT '地区编码',
  `name` varchar(32) NOT NULL COMMENT '地区名称',
  `full_name` varchar(64) NOT NULL COMMENT '地区全称',
  `level` int NOT NULL COMMENT '地区等级',
  `parent_code` varchar(12) NOT NULL COMMENT '上级编码',
  PRIMARY KEY (`id`)
) COMMENT = '地区信息';

SET FOREIGN_KEY_CHECKS = 1;
