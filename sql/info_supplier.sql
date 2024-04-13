SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `info_supplier`;
CREATE TABLE `info_supplier`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `name` varchar(64) NOT NULL COMMENT '供应商',
  `contacts` varchar(32) NULL COMMENT '联系人',
  `mobile` varchar(32) NULL COMMENT '联系电话',
  `province` varchar(32) NULL COMMENT '所在省份',
  `address` varchar(64) NULL COMMENT '详细地址',
  PRIMARY KEY (`id`)
) COMMENT = '供应商信息';

SET FOREIGN_KEY_CHECKS = 1;
