SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

DROP TABLE IF EXISTS `warehouse_product_lot`;
CREATE TABLE `warehouse_product_lot` (
  `id` bigint NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `create_by` varchar(32) NOT NULL DEFAULT '' COMMENT '创建人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
  `update_by` varchar(32) NOT NULL DEFAULT '' COMMENT '更新人',
  `product_id` bigint NOT NULL COMMENT '产品ID',
  `lot_no` varchar(32) NOT NULL COMMENT '生产批号',
  `prodDate` date NOT NULL COMMENT '生产日期',
  `expiredDate` date NOT NULL COMMENT '有效日期',
  `total_qty` int NOT NULL DEFAULT 0 COMMENT '总数量',
  `qualified_qty` int NOT NULL DEFAULT 0 COMMENT '合格数量',
  `unqualified_qty` int NOT NULL DEFAULT 0 COMMENT '不合格数量',
  PRIMARY KEY (`id`)
) COMMENT = '产品批号';

SET FOREIGN_KEY_CHECKS = 1;
