SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `info_product`;
CREATE TABLE `info_product`  (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `name` varchar(64) NOT NULL COMMENT '商品名称',
  `factory` varchar(64) NULL COMMENT '生产厂家',
  PRIMARY KEY (`id`)
) COMMENT = '产品信息';

SET FOREIGN_KEY_CHECKS = 1;
