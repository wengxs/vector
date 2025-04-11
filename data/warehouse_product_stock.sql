SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `warehouse_product_stock`;
CREATE TABLE `warehouse_product_stock` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `total_stock` int NOT NULL DEFAULT 0 COMMENT '总库存',
  `usable_stock` int NOT NULL DEFAULT 0 COMMENT '可用库存',
  `locked_stock` int NOT NULL DEFAULT 0 COMMENT '锁定库存',
  PRIMARY KEY (`id`)
) COMMENT = '产品库存';

SET FOREIGN_KEY_CHECKS = 1;
